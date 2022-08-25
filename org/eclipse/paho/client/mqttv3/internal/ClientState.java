package org.eclipse.paho.client.mqttv3.internal;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPingReq;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubComp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubRec;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubRel;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class ClientState {
  private static final String CLASS_NAME;
  
  private static final int DEFAULT_MAX_INFLIGHT = 10;
  
  private static final int MAX_MSG_ID = 65535;
  
  private static final int MIN_MSG_ID = 1;
  
  private static final String PERSISTENCE_CONFIRMED_PREFIX = "sc-";
  
  private static final String PERSISTENCE_RECEIVED_PREFIX = "r-";
  
  private static final String PERSISTENCE_SENT_PREFIX = "s-";
  
  private static final Logger log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", CLASS_NAME);
  
  private int actualInFlight = 0;
  
  private CommsCallback callback = null;
  
  private boolean cleanSession;
  
  private ClientComms clientComms = null;
  
  private boolean connected = false;
  
  private int inFlightPubRels = 0;
  
  private Hashtable inUseMsgIds;
  
  private Hashtable inboundQoS2 = null;
  
  private long keepAlive;
  
  private long lastInboundActivity = 0L;
  
  private long lastOutboundActivity = 0L;
  
  private long lastPing = 0L;
  
  private int maxInflight = 10;
  
  private int nextMsgId = 0;
  
  private Hashtable outboundQoS1 = null;
  
  private Hashtable outboundQoS2 = null;
  
  private volatile Vector pendingFlows;
  
  private volatile Vector pendingMessages;
  
  private MqttClientPersistence persistence;
  
  private MqttWireMessage pingCommand;
  
  private int pingOutstanding = 0;
  
  private Object pingOutstandingLock = new Object();
  
  private MqttPingSender pingSender = null;
  
  private Object queueLock = new Object();
  
  private Object quiesceLock = new Object();
  
  private boolean quiescing = false;
  
  private CommsTokenStore tokenStore;
  
  protected ClientState(MqttClientPersistence paramMqttClientPersistence, CommsTokenStore paramCommsTokenStore, CommsCallback paramCommsCallback, ClientComms paramClientComms, MqttPingSender paramMqttPingSender) throws MqttException {
    log.setResourceName(paramClientComms.getClient().getClientId());
    log.finer(CLASS_NAME, "<Init>", "");
    this.inUseMsgIds = new Hashtable<>();
    this.pendingMessages = new Vector(this.maxInflight);
    this.pendingFlows = new Vector();
    this.outboundQoS2 = new Hashtable<>();
    this.outboundQoS1 = new Hashtable<>();
    this.inboundQoS2 = new Hashtable<>();
    this.pingCommand = (MqttWireMessage)new MqttPingReq();
    this.inFlightPubRels = 0;
    this.actualInFlight = 0;
    this.persistence = paramMqttClientPersistence;
    this.callback = paramCommsCallback;
    this.tokenStore = paramCommsTokenStore;
    this.clientComms = paramClientComms;
    this.pingSender = paramMqttPingSender;
    restoreState();
  }
  
  private void decrementInFlight() {
    synchronized (this.queueLock) {
      this.actualInFlight--;
      Logger logger = log;
      String str = CLASS_NAME;
      Integer integer = new Integer();
      this(this.actualInFlight);
      logger.fine(str, "decrementInFlight", "646", new Object[] { integer });
      if (!checkQuiesceLock())
        this.queueLock.notifyAll(); 
      return;
    } 
  }
  
  private int getNextMessageId() throws MqttException {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield nextMsgId : I
    //   6: istore_1
    //   7: iconst_0
    //   8: istore_2
    //   9: aload_0
    //   10: aload_0
    //   11: getfield nextMsgId : I
    //   14: iconst_1
    //   15: iadd
    //   16: putfield nextMsgId : I
    //   19: aload_0
    //   20: getfield nextMsgId : I
    //   23: ldc 65535
    //   25: if_icmple -> 33
    //   28: aload_0
    //   29: iconst_1
    //   30: putfield nextMsgId : I
    //   33: iload_2
    //   34: istore_3
    //   35: aload_0
    //   36: getfield nextMsgId : I
    //   39: iload_1
    //   40: if_icmpne -> 60
    //   43: iinc #2, 1
    //   46: iload_2
    //   47: istore_3
    //   48: iload_2
    //   49: iconst_2
    //   50: if_icmpne -> 60
    //   53: sipush #32001
    //   56: invokestatic createMqttException : (I)Lorg/eclipse/paho/client/mqttv3/MqttException;
    //   59: athrow
    //   60: aload_0
    //   61: getfield inUseMsgIds : Ljava/util/Hashtable;
    //   64: astore #4
    //   66: new java/lang/Integer
    //   69: astore #5
    //   71: aload #5
    //   73: aload_0
    //   74: getfield nextMsgId : I
    //   77: invokespecial <init> : (I)V
    //   80: iload_3
    //   81: istore_2
    //   82: aload #4
    //   84: aload #5
    //   86: invokevirtual containsKey : (Ljava/lang/Object;)Z
    //   89: ifne -> 9
    //   92: new java/lang/Integer
    //   95: astore #4
    //   97: aload #4
    //   99: aload_0
    //   100: getfield nextMsgId : I
    //   103: invokespecial <init> : (I)V
    //   106: aload_0
    //   107: getfield inUseMsgIds : Ljava/util/Hashtable;
    //   110: aload #4
    //   112: aload #4
    //   114: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   117: pop
    //   118: aload_0
    //   119: getfield nextMsgId : I
    //   122: istore_3
    //   123: aload_0
    //   124: monitorexit
    //   125: iload_3
    //   126: ireturn
    //   127: astore #4
    //   129: aload_0
    //   130: monitorexit
    //   131: aload #4
    //   133: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	127	finally
    //   9	33	127	finally
    //   35	43	127	finally
    //   53	60	127	finally
    //   60	80	127	finally
    //   82	123	127	finally
  }
  
  private String getReceivedPersistenceKey(MqttWireMessage paramMqttWireMessage) {
    StringBuffer stringBuffer = new StringBuffer("r-");
    stringBuffer.append(paramMqttWireMessage.getMessageId());
    return stringBuffer.toString();
  }
  
  private String getSendConfirmPersistenceKey(MqttWireMessage paramMqttWireMessage) {
    StringBuffer stringBuffer = new StringBuffer("sc-");
    stringBuffer.append(paramMqttWireMessage.getMessageId());
    return stringBuffer.toString();
  }
  
  private String getSendPersistenceKey(MqttWireMessage paramMqttWireMessage) {
    StringBuffer stringBuffer = new StringBuffer("s-");
    stringBuffer.append(paramMqttWireMessage.getMessageId());
    return stringBuffer.toString();
  }
  
  private void insertInOrder(Vector<MqttWireMessage> paramVector, MqttWireMessage paramMqttWireMessage) {
    int i = paramMqttWireMessage.getMessageId();
    for (byte b = 0;; b++) {
      if (b >= paramVector.size()) {
        paramVector.addElement(paramMqttWireMessage);
        return;
      } 
      if (((MqttWireMessage)paramVector.elementAt(b)).getMessageId() > i) {
        paramVector.insertElementAt(paramMqttWireMessage, b);
        return;
      } 
    } 
  }
  
  private Vector reOrder(Vector<MqttWireMessage> paramVector) {
    Vector vector = new Vector();
    if (paramVector.size() == 0)
      return vector; 
    byte b = 0;
    int i = 0;
    int j = i;
    int k = j;
    int m = k;
    int n = j;
    j = i;
    while (true) {
      if (j >= paramVector.size()) {
        if (65535 - n + ((MqttWireMessage)paramVector.elementAt(0)).getMessageId() > k) {
          j = 0;
        } else {
          j = m;
        } 
        for (m = j;; m++) {
          if (m >= paramVector.size()) {
            for (m = b;; m++) {
              if (m >= j)
                return vector; 
              vector.addElement(paramVector.elementAt(m));
            } 
            break;
          } 
          vector.addElement(paramVector.elementAt(m));
        } 
        break;
      } 
      int i1 = ((MqttWireMessage)paramVector.elementAt(j)).getMessageId();
      n = i1 - n;
      i = k;
      if (n > k) {
        m = j;
        i = n;
      } 
      j++;
      n = i1;
      k = i;
    } 
  }
  
  private void releaseMessageId(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield inUseMsgIds : Ljava/util/Hashtable;
    //   6: astore_2
    //   7: new java/lang/Integer
    //   10: astore_3
    //   11: aload_3
    //   12: iload_1
    //   13: invokespecial <init> : (I)V
    //   16: aload_2
    //   17: aload_3
    //   18: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   21: pop
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: astore_3
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_3
    //   29: athrow
    // Exception table:
    //   from	to	target	type
    //   2	22	25	finally
  }
  
  private void restoreInflightMessages() {
    this.pendingMessages = new Vector(this.maxInflight);
    this.pendingFlows = new Vector();
    Enumeration<Object> enumeration = this.outboundQoS2.keys();
    while (true) {
      if (!enumeration.hasMoreElements()) {
        enumeration = this.outboundQoS1.keys();
        while (true) {
          if (!enumeration.hasMoreElements()) {
            this.pendingFlows = reOrder(this.pendingFlows);
            this.pendingMessages = reOrder(this.pendingMessages);
            return;
          } 
          Object object1 = enumeration.nextElement();
          MqttPublish mqttPublish = (MqttPublish)this.outboundQoS1.get(object1);
          mqttPublish.setDuplicate(true);
          log.fine(CLASS_NAME, "restoreInflightMessages", "612", new Object[] { object1 });
          insertInOrder(this.pendingMessages, (MqttWireMessage)mqttPublish);
        } 
        break;
      } 
      Object object = enumeration.nextElement();
      MqttWireMessage mqttWireMessage = (MqttWireMessage)this.outboundQoS2.get(object);
      if (mqttWireMessage instanceof MqttPublish) {
        log.fine(CLASS_NAME, "restoreInflightMessages", "610", new Object[] { object });
        mqttWireMessage.setDuplicate(true);
        insertInOrder(this.pendingMessages, mqttWireMessage);
        continue;
      } 
      if (mqttWireMessage instanceof MqttPubRel) {
        log.fine(CLASS_NAME, "restoreInflightMessages", "611", new Object[] { object });
        insertInOrder(this.pendingFlows, mqttWireMessage);
      } 
    } 
  }
  
  private MqttWireMessage restoreMessage(String paramString, MqttPersistable paramMqttPersistable) throws MqttException {
    try {
      MqttWireMessage mqttWireMessage = MqttWireMessage.createWireMessage(paramMqttPersistable);
    } catch (MqttException mqttException) {
      log.fine(CLASS_NAME, "restoreMessage", "602", new Object[] { paramString }, (Throwable)mqttException);
      if (mqttException.getCause() instanceof java.io.EOFException) {
        if (paramString != null)
          this.persistence.remove(paramString); 
        mqttException = null;
        log.fine(CLASS_NAME, "restoreMessage", "601", new Object[] { paramString, mqttException });
        return (MqttWireMessage)mqttException;
      } 
    } 
    log.fine(CLASS_NAME, "restoreMessage", "601", new Object[] { paramString, mqttException });
    return (MqttWireMessage)mqttException;
  }
  
  public MqttToken checkForActivity() throws MqttException {
    // Byte code:
    //   0: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   3: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME : Ljava/lang/String;
    //   6: ldc_w 'checkForActivity'
    //   9: ldc_w '616'
    //   12: iconst_0
    //   13: anewarray java/lang/Object
    //   16: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   21: aload_0
    //   22: getfield quiesceLock : Ljava/lang/Object;
    //   25: astore_1
    //   26: aload_1
    //   27: monitorenter
    //   28: aload_0
    //   29: getfield quiescing : Z
    //   32: istore_2
    //   33: aconst_null
    //   34: astore_3
    //   35: aconst_null
    //   36: astore #4
    //   38: iload_2
    //   39: ifeq -> 46
    //   42: aload_1
    //   43: monitorexit
    //   44: aconst_null
    //   45: areturn
    //   46: aload_1
    //   47: monitorexit
    //   48: aload_0
    //   49: invokevirtual getKeepAlive : ()J
    //   52: pop2
    //   53: aload_3
    //   54: astore_1
    //   55: aload_0
    //   56: getfield connected : Z
    //   59: ifeq -> 837
    //   62: aload_3
    //   63: astore_1
    //   64: aload_0
    //   65: getfield keepAlive : J
    //   68: lconst_0
    //   69: lcmp
    //   70: ifle -> 837
    //   73: invokestatic currentTimeMillis : ()J
    //   76: lstore #5
    //   78: aload_0
    //   79: getfield pingOutstandingLock : Ljava/lang/Object;
    //   82: astore_3
    //   83: aload_3
    //   84: monitorenter
    //   85: aload_0
    //   86: getfield pingOutstanding : I
    //   89: ifle -> 278
    //   92: aload_0
    //   93: getfield lastInboundActivity : J
    //   96: lstore #7
    //   98: aload_0
    //   99: getfield keepAlive : J
    //   102: lstore #9
    //   104: aload_3
    //   105: astore_1
    //   106: lload #5
    //   108: lload #7
    //   110: lsub
    //   111: lload #9
    //   113: bipush #100
    //   115: i2l
    //   116: ladd
    //   117: lcmp
    //   118: iflt -> 278
    //   121: aload_1
    //   122: astore_3
    //   123: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   126: astore #11
    //   128: aload_1
    //   129: astore_3
    //   130: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME : Ljava/lang/String;
    //   133: astore #12
    //   135: aload_1
    //   136: astore_3
    //   137: new java/lang/Long
    //   140: astore #13
    //   142: aload_1
    //   143: astore_3
    //   144: aload #13
    //   146: aload_0
    //   147: getfield keepAlive : J
    //   150: invokespecial <init> : (J)V
    //   153: aload_1
    //   154: astore_3
    //   155: new java/lang/Long
    //   158: astore #14
    //   160: aload_1
    //   161: astore_3
    //   162: aload #14
    //   164: aload_0
    //   165: getfield lastOutboundActivity : J
    //   168: invokespecial <init> : (J)V
    //   171: aload_1
    //   172: astore_3
    //   173: new java/lang/Long
    //   176: astore #15
    //   178: aload_1
    //   179: astore_3
    //   180: aload #15
    //   182: aload_0
    //   183: getfield lastInboundActivity : J
    //   186: invokespecial <init> : (J)V
    //   189: aload_1
    //   190: astore_3
    //   191: new java/lang/Long
    //   194: astore #4
    //   196: aload_1
    //   197: astore_3
    //   198: aload #4
    //   200: lload #5
    //   202: invokespecial <init> : (J)V
    //   205: aload_1
    //   206: astore_3
    //   207: new java/lang/Long
    //   210: astore #16
    //   212: aload_1
    //   213: astore_3
    //   214: aload #16
    //   216: aload_0
    //   217: getfield lastPing : J
    //   220: invokespecial <init> : (J)V
    //   223: aload_1
    //   224: astore_3
    //   225: aload #11
    //   227: aload #12
    //   229: ldc_w 'checkForActivity'
    //   232: ldc_w '619'
    //   235: iconst_5
    //   236: anewarray java/lang/Object
    //   239: dup
    //   240: iconst_0
    //   241: aload #13
    //   243: aastore
    //   244: dup
    //   245: iconst_1
    //   246: aload #14
    //   248: aastore
    //   249: dup
    //   250: iconst_2
    //   251: aload #15
    //   253: aastore
    //   254: dup
    //   255: iconst_3
    //   256: aload #4
    //   258: aastore
    //   259: dup
    //   260: iconst_4
    //   261: aload #16
    //   263: aastore
    //   264: invokeinterface severe : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   269: aload_1
    //   270: astore_3
    //   271: sipush #32000
    //   274: invokestatic createMqttException : (I)Lorg/eclipse/paho/client/mqttv3/MqttException;
    //   277: athrow
    //   278: aload_3
    //   279: astore #13
    //   281: aload #13
    //   283: astore_3
    //   284: aload_0
    //   285: getfield pingOutstanding : I
    //   288: ifne -> 481
    //   291: aload #13
    //   293: astore_3
    //   294: lload #5
    //   296: aload_0
    //   297: getfield lastOutboundActivity : J
    //   300: lsub
    //   301: aload_0
    //   302: getfield keepAlive : J
    //   305: ldc2_w 2
    //   308: lmul
    //   309: lcmp
    //   310: iflt -> 481
    //   313: aload #13
    //   315: astore_3
    //   316: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   319: astore #4
    //   321: aload #13
    //   323: astore_3
    //   324: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME : Ljava/lang/String;
    //   327: astore #16
    //   329: aload #13
    //   331: astore_3
    //   332: new java/lang/Long
    //   335: astore #12
    //   337: aload #13
    //   339: astore_3
    //   340: aload #12
    //   342: aload_0
    //   343: getfield keepAlive : J
    //   346: invokespecial <init> : (J)V
    //   349: aload #13
    //   351: astore_3
    //   352: new java/lang/Long
    //   355: astore #11
    //   357: aload #13
    //   359: astore_3
    //   360: aload #11
    //   362: aload_0
    //   363: getfield lastOutboundActivity : J
    //   366: invokespecial <init> : (J)V
    //   369: aload #13
    //   371: astore_3
    //   372: new java/lang/Long
    //   375: astore #14
    //   377: aload #13
    //   379: astore_3
    //   380: aload #14
    //   382: aload_0
    //   383: getfield lastInboundActivity : J
    //   386: invokespecial <init> : (J)V
    //   389: aload #13
    //   391: astore_3
    //   392: new java/lang/Long
    //   395: astore_1
    //   396: aload #13
    //   398: astore_3
    //   399: aload_1
    //   400: lload #5
    //   402: invokespecial <init> : (J)V
    //   405: aload #13
    //   407: astore_3
    //   408: new java/lang/Long
    //   411: astore #15
    //   413: aload #13
    //   415: astore_3
    //   416: aload #15
    //   418: aload_0
    //   419: getfield lastPing : J
    //   422: invokespecial <init> : (J)V
    //   425: aload #13
    //   427: astore_3
    //   428: aload #4
    //   430: aload #16
    //   432: ldc_w 'checkForActivity'
    //   435: ldc_w '642'
    //   438: iconst_5
    //   439: anewarray java/lang/Object
    //   442: dup
    //   443: iconst_0
    //   444: aload #12
    //   446: aastore
    //   447: dup
    //   448: iconst_1
    //   449: aload #11
    //   451: aastore
    //   452: dup
    //   453: iconst_2
    //   454: aload #14
    //   456: aastore
    //   457: dup
    //   458: iconst_3
    //   459: aload_1
    //   460: aastore
    //   461: dup
    //   462: iconst_4
    //   463: aload #15
    //   465: aastore
    //   466: invokeinterface severe : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   471: aload #13
    //   473: astore_3
    //   474: sipush #32002
    //   477: invokestatic createMqttException : (I)Lorg/eclipse/paho/client/mqttv3/MqttException;
    //   480: athrow
    //   481: aload #13
    //   483: astore_3
    //   484: aload_0
    //   485: getfield pingOutstanding : I
    //   488: ifne -> 513
    //   491: aload #13
    //   493: astore_3
    //   494: lload #5
    //   496: aload_0
    //   497: getfield lastInboundActivity : J
    //   500: lsub
    //   501: aload_0
    //   502: getfield keepAlive : J
    //   505: bipush #100
    //   507: i2l
    //   508: lsub
    //   509: lcmp
    //   510: ifge -> 535
    //   513: aload #13
    //   515: astore_3
    //   516: lload #5
    //   518: aload_0
    //   519: getfield lastOutboundActivity : J
    //   522: lsub
    //   523: aload_0
    //   524: getfield keepAlive : J
    //   527: bipush #100
    //   529: i2l
    //   530: lsub
    //   531: lcmp
    //   532: iflt -> 720
    //   535: aload #13
    //   537: astore_3
    //   538: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   541: astore #11
    //   543: aload #13
    //   545: astore_3
    //   546: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME : Ljava/lang/String;
    //   549: astore #15
    //   551: aload #13
    //   553: astore_3
    //   554: new java/lang/Long
    //   557: astore_1
    //   558: aload #13
    //   560: astore_3
    //   561: aload_1
    //   562: aload_0
    //   563: getfield keepAlive : J
    //   566: invokespecial <init> : (J)V
    //   569: aload #13
    //   571: astore_3
    //   572: new java/lang/Long
    //   575: astore #4
    //   577: aload #13
    //   579: astore_3
    //   580: aload #4
    //   582: aload_0
    //   583: getfield lastOutboundActivity : J
    //   586: invokespecial <init> : (J)V
    //   589: aload #13
    //   591: astore_3
    //   592: new java/lang/Long
    //   595: astore #16
    //   597: aload #13
    //   599: astore_3
    //   600: aload #16
    //   602: aload_0
    //   603: getfield lastInboundActivity : J
    //   606: invokespecial <init> : (J)V
    //   609: aload #13
    //   611: astore_3
    //   612: aload #11
    //   614: aload #15
    //   616: ldc_w 'checkForActivity'
    //   619: ldc_w '620'
    //   622: iconst_3
    //   623: anewarray java/lang/Object
    //   626: dup
    //   627: iconst_0
    //   628: aload_1
    //   629: aastore
    //   630: dup
    //   631: iconst_1
    //   632: aload #4
    //   634: aastore
    //   635: dup
    //   636: iconst_2
    //   637: aload #16
    //   639: aastore
    //   640: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   645: aload #13
    //   647: astore_3
    //   648: new org/eclipse/paho/client/mqttv3/MqttToken
    //   651: astore_1
    //   652: aload #13
    //   654: astore_3
    //   655: aload_1
    //   656: aload_0
    //   657: getfield clientComms : Lorg/eclipse/paho/client/mqttv3/internal/ClientComms;
    //   660: invokevirtual getClient : ()Lorg/eclipse/paho/client/mqttv3/IMqttAsyncClient;
    //   663: invokeinterface getClientId : ()Ljava/lang/String;
    //   668: invokespecial <init> : (Ljava/lang/String;)V
    //   671: aload #13
    //   673: astore_3
    //   674: aload_0
    //   675: getfield tokenStore : Lorg/eclipse/paho/client/mqttv3/internal/CommsTokenStore;
    //   678: aload_1
    //   679: aload_0
    //   680: getfield pingCommand : Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttWireMessage;
    //   683: invokevirtual saveToken : (Lorg/eclipse/paho/client/mqttv3/MqttToken;Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttWireMessage;)V
    //   686: aload #13
    //   688: astore_3
    //   689: aload_0
    //   690: getfield pendingFlows : Ljava/util/Vector;
    //   693: aload_0
    //   694: getfield pingCommand : Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttWireMessage;
    //   697: iconst_0
    //   698: invokevirtual insertElementAt : (Ljava/lang/Object;I)V
    //   701: aload #13
    //   703: astore_3
    //   704: aload_0
    //   705: invokevirtual getKeepAlive : ()J
    //   708: lstore #9
    //   710: aload #13
    //   712: astore_3
    //   713: aload_0
    //   714: invokevirtual notifyQueueLock : ()V
    //   717: goto -> 765
    //   720: aload #13
    //   722: astore_3
    //   723: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   726: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME : Ljava/lang/String;
    //   729: ldc_w 'checkForActivity'
    //   732: ldc_w '634'
    //   735: aconst_null
    //   736: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   741: aload #13
    //   743: astore_3
    //   744: lconst_1
    //   745: aload_0
    //   746: invokevirtual getKeepAlive : ()J
    //   749: lload #5
    //   751: aload_0
    //   752: getfield lastOutboundActivity : J
    //   755: lsub
    //   756: lsub
    //   757: invokestatic max : (JJ)J
    //   760: lstore #9
    //   762: aload #4
    //   764: astore_1
    //   765: aload #13
    //   767: astore_3
    //   768: aload #13
    //   770: monitorexit
    //   771: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   774: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME : Ljava/lang/String;
    //   777: ldc_w 'checkForActivity'
    //   780: ldc_w '624'
    //   783: iconst_1
    //   784: anewarray java/lang/Object
    //   787: dup
    //   788: iconst_0
    //   789: new java/lang/Long
    //   792: dup
    //   793: lload #9
    //   795: invokespecial <init> : (J)V
    //   798: aastore
    //   799: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   804: aload_0
    //   805: getfield pingSender : Lorg/eclipse/paho/client/mqttv3/MqttPingSender;
    //   808: lload #9
    //   810: invokeinterface schedule : (J)V
    //   815: goto -> 837
    //   818: astore_1
    //   819: aload_3
    //   820: astore #13
    //   822: aload #13
    //   824: astore_3
    //   825: aload #13
    //   827: monitorexit
    //   828: aload_1
    //   829: athrow
    //   830: astore_1
    //   831: aload_3
    //   832: astore #13
    //   834: goto -> 822
    //   837: aload_1
    //   838: areturn
    //   839: astore_3
    //   840: aload_1
    //   841: monitorexit
    //   842: aload_3
    //   843: athrow
    // Exception table:
    //   from	to	target	type
    //   28	33	839	finally
    //   42	44	839	finally
    //   46	48	839	finally
    //   85	104	818	finally
    //   123	128	830	finally
    //   130	135	830	finally
    //   137	142	830	finally
    //   144	153	830	finally
    //   155	160	830	finally
    //   162	171	830	finally
    //   173	178	830	finally
    //   180	189	830	finally
    //   191	196	830	finally
    //   198	205	830	finally
    //   207	212	830	finally
    //   214	223	830	finally
    //   225	269	830	finally
    //   271	278	830	finally
    //   284	291	830	finally
    //   294	313	830	finally
    //   316	321	830	finally
    //   324	329	830	finally
    //   332	337	830	finally
    //   340	349	830	finally
    //   352	357	830	finally
    //   360	369	830	finally
    //   372	377	830	finally
    //   380	389	830	finally
    //   392	396	830	finally
    //   399	405	830	finally
    //   408	413	830	finally
    //   416	425	830	finally
    //   428	471	830	finally
    //   474	481	830	finally
    //   484	491	830	finally
    //   494	513	830	finally
    //   516	535	830	finally
    //   538	543	830	finally
    //   546	551	830	finally
    //   554	558	830	finally
    //   561	569	830	finally
    //   572	577	830	finally
    //   580	589	830	finally
    //   592	597	830	finally
    //   600	609	830	finally
    //   612	645	830	finally
    //   648	652	830	finally
    //   655	671	830	finally
    //   674	686	830	finally
    //   689	701	830	finally
    //   704	710	830	finally
    //   713	717	830	finally
    //   723	741	830	finally
    //   744	762	830	finally
    //   768	771	830	finally
    //   825	828	830	finally
    //   840	842	839	finally
  }
  
  protected boolean checkQuiesceLock() {
    int i = this.tokenStore.count();
    if (this.quiescing && i == 0 && this.pendingFlows.size() == 0 && this.callback.isQuiesced()) {
      log.fine(CLASS_NAME, "checkQuiesceLock", "626", new Object[] { new Boolean(this.quiescing), new Integer(this.actualInFlight), new Integer(this.pendingFlows.size()), new Integer(this.inFlightPubRels), Boolean.valueOf(this.callback.isQuiesced()), new Integer(i) });
      synchronized (this.quiesceLock) {
        this.quiesceLock.notifyAll();
        return true;
      } 
    } 
    return false;
  }
  
  protected void clearState() throws MqttException {
    log.fine(CLASS_NAME, "clearState", ">");
    this.persistence.clear();
    this.inUseMsgIds.clear();
    this.pendingMessages.clear();
    this.pendingFlows.clear();
    this.outboundQoS2.clear();
    this.outboundQoS1.clear();
    this.inboundQoS2.clear();
    this.tokenStore.clear();
  }
  
  protected void close() {
    this.inUseMsgIds.clear();
    this.pendingMessages.clear();
    this.pendingFlows.clear();
    this.outboundQoS2.clear();
    this.outboundQoS1.clear();
    this.inboundQoS2.clear();
    this.tokenStore.clear();
    this.inUseMsgIds = null;
    this.pendingMessages = null;
    this.pendingFlows = null;
    this.outboundQoS2 = null;
    this.outboundQoS1 = null;
    this.inboundQoS2 = null;
    this.tokenStore = null;
    this.callback = null;
    this.clientComms = null;
    this.persistence = null;
    this.pingCommand = null;
  }
  
  public void connected() {
    log.fine(CLASS_NAME, "connected", "631");
    this.connected = true;
    this.pingSender.start();
  }
  
  protected void deliveryComplete(MqttPublish paramMqttPublish) throws MqttPersistenceException {
    log.fine(CLASS_NAME, "deliveryComplete", "641", new Object[] { new Integer(paramMqttPublish.getMessageId()) });
    this.persistence.remove(getReceivedPersistenceKey((MqttWireMessage)paramMqttPublish));
    this.inboundQoS2.remove(new Integer(paramMqttPublish.getMessageId()));
  }
  
  public void disconnected(MqttException paramMqttException) {
    log.fine(CLASS_NAME, "disconnected", "633", new Object[] { paramMqttException });
    this.connected = false;
    try {
      if (this.cleanSession)
        clearState(); 
      this.pendingMessages.clear();
      this.pendingFlows.clear();
      synchronized (this.pingOutstandingLock) {
        this.pingOutstanding = 0;
      } 
    } catch (MqttException mqttException) {}
  }
  
  protected MqttWireMessage get() throws MqttException {
    // Byte code:
    //   0: aload_0
    //   1: getfield queueLock : Ljava/lang/Object;
    //   4: astore_1
    //   5: aload_1
    //   6: monitorenter
    //   7: aconst_null
    //   8: astore_2
    //   9: aload_2
    //   10: ifnull -> 21
    //   13: aload_1
    //   14: monitorexit
    //   15: aload_2
    //   16: areturn
    //   17: astore_2
    //   18: goto -> 381
    //   21: aload_0
    //   22: getfield pendingMessages : Ljava/util/Vector;
    //   25: invokevirtual isEmpty : ()Z
    //   28: ifeq -> 41
    //   31: aload_0
    //   32: getfield pendingFlows : Ljava/util/Vector;
    //   35: invokevirtual isEmpty : ()Z
    //   38: ifne -> 68
    //   41: aload_0
    //   42: getfield pendingFlows : Ljava/util/Vector;
    //   45: invokevirtual isEmpty : ()Z
    //   48: ifeq -> 109
    //   51: aload_0
    //   52: getfield actualInFlight : I
    //   55: istore_3
    //   56: aload_0
    //   57: getfield maxInflight : I
    //   60: istore #4
    //   62: iload_3
    //   63: iload #4
    //   65: if_icmplt -> 109
    //   68: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   71: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME : Ljava/lang/String;
    //   74: ldc_w 'get'
    //   77: ldc_w '644'
    //   80: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   85: aload_0
    //   86: getfield queueLock : Ljava/lang/Object;
    //   89: invokevirtual wait : ()V
    //   92: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   95: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME : Ljava/lang/String;
    //   98: ldc_w 'get'
    //   101: ldc_w '647'
    //   104: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   109: aload_0
    //   110: getfield connected : Z
    //   113: ifne -> 164
    //   116: aload_0
    //   117: getfield pendingFlows : Ljava/util/Vector;
    //   120: invokevirtual isEmpty : ()Z
    //   123: ifne -> 143
    //   126: aload_0
    //   127: getfield pendingFlows : Ljava/util/Vector;
    //   130: iconst_0
    //   131: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   134: checkcast org/eclipse/paho/client/mqttv3/internal/wire/MqttWireMessage
    //   137: instanceof org/eclipse/paho/client/mqttv3/internal/wire/MqttConnect
    //   140: ifne -> 164
    //   143: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   146: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME : Ljava/lang/String;
    //   149: ldc_w 'get'
    //   152: ldc_w '621'
    //   155: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   160: aload_1
    //   161: monitorexit
    //   162: aconst_null
    //   163: areturn
    //   164: aload_0
    //   165: getfield pendingFlows : Ljava/util/Vector;
    //   168: invokevirtual isEmpty : ()Z
    //   171: ifne -> 259
    //   174: aload_0
    //   175: getfield pendingFlows : Ljava/util/Vector;
    //   178: iconst_0
    //   179: invokevirtual remove : (I)Ljava/lang/Object;
    //   182: checkcast org/eclipse/paho/client/mqttv3/internal/wire/MqttWireMessage
    //   185: astore_2
    //   186: aload_2
    //   187: instanceof org/eclipse/paho/client/mqttv3/internal/wire/MqttPubRel
    //   190: ifeq -> 251
    //   193: aload_0
    //   194: aload_0
    //   195: getfield inFlightPubRels : I
    //   198: iconst_1
    //   199: iadd
    //   200: putfield inFlightPubRels : I
    //   203: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   206: astore #5
    //   208: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME : Ljava/lang/String;
    //   211: astore #6
    //   213: new java/lang/Integer
    //   216: astore #7
    //   218: aload #7
    //   220: aload_0
    //   221: getfield inFlightPubRels : I
    //   224: invokespecial <init> : (I)V
    //   227: aload #5
    //   229: aload #6
    //   231: ldc_w 'get'
    //   234: ldc_w '617'
    //   237: iconst_1
    //   238: anewarray java/lang/Object
    //   241: dup
    //   242: iconst_0
    //   243: aload #7
    //   245: aastore
    //   246: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   251: aload_0
    //   252: invokevirtual checkQuiesceLock : ()Z
    //   255: pop
    //   256: goto -> 9
    //   259: aload_0
    //   260: getfield pendingMessages : Ljava/util/Vector;
    //   263: invokevirtual isEmpty : ()Z
    //   266: ifne -> 9
    //   269: aload_0
    //   270: getfield actualInFlight : I
    //   273: aload_0
    //   274: getfield maxInflight : I
    //   277: if_icmpge -> 361
    //   280: aload_0
    //   281: getfield pendingMessages : Ljava/util/Vector;
    //   284: iconst_0
    //   285: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   288: checkcast org/eclipse/paho/client/mqttv3/internal/wire/MqttWireMessage
    //   291: astore_2
    //   292: aload_0
    //   293: getfield pendingMessages : Ljava/util/Vector;
    //   296: iconst_0
    //   297: invokevirtual removeElementAt : (I)V
    //   300: aload_0
    //   301: aload_0
    //   302: getfield actualInFlight : I
    //   305: iconst_1
    //   306: iadd
    //   307: putfield actualInFlight : I
    //   310: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   313: astore #7
    //   315: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME : Ljava/lang/String;
    //   318: astore #6
    //   320: new java/lang/Integer
    //   323: astore #5
    //   325: aload #5
    //   327: aload_0
    //   328: getfield actualInFlight : I
    //   331: invokespecial <init> : (I)V
    //   334: aload #7
    //   336: aload #6
    //   338: ldc_w 'get'
    //   341: ldc_w '623'
    //   344: iconst_1
    //   345: anewarray java/lang/Object
    //   348: dup
    //   349: iconst_0
    //   350: aload #5
    //   352: aastore
    //   353: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   358: goto -> 9
    //   361: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   364: getstatic org/eclipse/paho/client/mqttv3/internal/ClientState.CLASS_NAME : Ljava/lang/String;
    //   367: ldc_w 'get'
    //   370: ldc_w '622'
    //   373: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   378: goto -> 9
    //   381: aload_1
    //   382: monitorexit
    //   383: aload_2
    //   384: athrow
    //   385: astore #6
    //   387: goto -> 109
    // Exception table:
    //   from	to	target	type
    //   13	15	17	finally
    //   21	41	17	finally
    //   41	62	17	finally
    //   68	109	385	java/lang/InterruptedException
    //   68	109	17	finally
    //   109	143	17	finally
    //   143	162	17	finally
    //   164	251	17	finally
    //   251	256	17	finally
    //   259	358	17	finally
    //   361	378	17	finally
    //   381	383	17	finally
  }
  
  public Properties getDebug() {
    Properties properties = new Properties();
    properties.put("In use msgids", this.inUseMsgIds);
    properties.put("pendingMessages", this.pendingMessages);
    properties.put("pendingFlows", this.pendingFlows);
    properties.put("maxInflight", new Integer(this.maxInflight));
    properties.put("nextMsgID", new Integer(this.nextMsgId));
    properties.put("actualInFlight", new Integer(this.actualInFlight));
    properties.put("inFlightPubRels", new Integer(this.inFlightPubRels));
    properties.put("quiescing", Boolean.valueOf(this.quiescing));
    properties.put("pingoutstanding", new Integer(this.pingOutstanding));
    properties.put("lastOutboundActivity", new Long(this.lastOutboundActivity));
    properties.put("lastInboundActivity", new Long(this.lastInboundActivity));
    properties.put("outboundQoS2", this.outboundQoS2);
    properties.put("outboundQoS1", this.outboundQoS1);
    properties.put("inboundQoS2", this.inboundQoS2);
    properties.put("tokens", this.tokenStore);
    return properties;
  }
  
  protected long getKeepAlive() {
    return this.keepAlive;
  }
  
  protected void notifyComplete(MqttToken paramMqttToken) throws MqttException {
    MqttWireMessage mqttWireMessage = paramMqttToken.internalTok.getWireMessage();
    if (mqttWireMessage != null && mqttWireMessage instanceof MqttAck) {
      log.fine(CLASS_NAME, "notifyComplete", "629", new Object[] { new Integer(mqttWireMessage.getMessageId()), paramMqttToken, mqttWireMessage });
      MqttAck mqttAck = (MqttAck)mqttWireMessage;
      if (mqttAck instanceof org.eclipse.paho.client.mqttv3.internal.wire.MqttPubAck) {
        this.persistence.remove(getSendPersistenceKey(mqttWireMessage));
        this.outboundQoS1.remove(new Integer(mqttAck.getMessageId()));
        decrementInFlight();
        releaseMessageId(mqttWireMessage.getMessageId());
        this.tokenStore.removeToken(mqttWireMessage);
        log.fine(CLASS_NAME, "notifyComplete", "650", new Object[] { new Integer(mqttAck.getMessageId()) });
      } else if (mqttAck instanceof MqttPubComp) {
        this.persistence.remove(getSendPersistenceKey(mqttWireMessage));
        this.persistence.remove(getSendConfirmPersistenceKey(mqttWireMessage));
        this.outboundQoS2.remove(new Integer(mqttAck.getMessageId()));
        this.inFlightPubRels--;
        decrementInFlight();
        releaseMessageId(mqttWireMessage.getMessageId());
        this.tokenStore.removeToken(mqttWireMessage);
        log.fine(CLASS_NAME, "notifyComplete", "645", new Object[] { new Integer(mqttAck.getMessageId()), new Integer(this.inFlightPubRels) });
      } 
      checkQuiesceLock();
    } 
  }
  
  public void notifyQueueLock() {
    synchronized (this.queueLock) {
      log.fine(CLASS_NAME, "notifyQueueLock", "638");
      this.queueLock.notifyAll();
      return;
    } 
  }
  
  protected void notifyReceivedAck(MqttAck paramMqttAck) throws MqttException {
    this.lastInboundActivity = System.currentTimeMillis();
    log.fine(CLASS_NAME, "notifyReceivedAck", "627", new Object[] { new Integer(paramMqttAck.getMessageId()), paramMqttAck });
    MqttToken mqttToken = this.tokenStore.getToken((MqttWireMessage)paramMqttAck);
    if (paramMqttAck instanceof MqttPubRec) {
      send((MqttWireMessage)new MqttPubRel((MqttPubRec)paramMqttAck), mqttToken);
    } else if (paramMqttAck instanceof org.eclipse.paho.client.mqttv3.internal.wire.MqttPubAck || paramMqttAck instanceof MqttPubComp) {
      notifyResult((MqttWireMessage)paramMqttAck, mqttToken, null);
    } else if (paramMqttAck instanceof org.eclipse.paho.client.mqttv3.internal.wire.MqttPingResp) {
      synchronized (this.pingOutstandingLock) {
        this.pingOutstanding = Math.max(0, this.pingOutstanding - 1);
        notifyResult((MqttWireMessage)paramMqttAck, mqttToken, null);
        if (this.pingOutstanding == 0)
          this.tokenStore.removeToken((MqttWireMessage)paramMqttAck); 
        log.fine(CLASS_NAME, "notifyReceivedAck", "636", new Object[] { new Integer(this.pingOutstanding) });
      } 
    } else if (paramMqttAck instanceof MqttConnack) {
      MqttConnack mqttConnack = (MqttConnack)paramMqttAck;
      int i = mqttConnack.getReturnCode();
      if (i == 0) {
        synchronized (this.queueLock) {
          if (this.cleanSession) {
            clearState();
            this.tokenStore.saveToken(mqttToken, (MqttWireMessage)paramMqttAck);
          } 
          this.inFlightPubRels = 0;
          this.actualInFlight = 0;
          restoreInflightMessages();
          connected();
          this.clientComms.connectComplete(mqttConnack, null);
          notifyResult((MqttWireMessage)paramMqttAck, mqttToken, null);
          this.tokenStore.removeToken((MqttWireMessage)paramMqttAck);
          synchronized (this.queueLock) {
            this.queueLock.notifyAll();
          } 
        } 
      } else {
        throw ExceptionHelper.createMqttException(i);
      } 
    } else {
      notifyResult((MqttWireMessage)paramMqttAck, mqttToken, null);
      releaseMessageId(paramMqttAck.getMessageId());
      this.tokenStore.removeToken((MqttWireMessage)paramMqttAck);
    } 
    checkQuiesceLock();
  }
  
  public void notifyReceivedBytes(int paramInt) {
    if (paramInt > 0)
      this.lastInboundActivity = System.currentTimeMillis(); 
    log.fine(CLASS_NAME, "notifyReceivedBytes", "630", new Object[] { new Integer(paramInt) });
  }
  
  protected void notifyReceivedMsg(MqttWireMessage paramMqttWireMessage) throws MqttException {
    this.lastInboundActivity = System.currentTimeMillis();
    log.fine(CLASS_NAME, "notifyReceivedMsg", "651", new Object[] { new Integer(paramMqttWireMessage.getMessageId()), paramMqttWireMessage });
    if (!this.quiescing) {
      if (paramMqttWireMessage instanceof MqttPublish) {
        MqttPublish mqttPublish = (MqttPublish)paramMqttWireMessage;
        switch (mqttPublish.getMessage().getQos()) {
          default:
            return;
          case 2:
            this.persistence.put(getReceivedPersistenceKey(paramMqttWireMessage), (MqttPersistable)mqttPublish);
            this.inboundQoS2.put(new Integer(mqttPublish.getMessageId()), mqttPublish);
            send((MqttWireMessage)new MqttPubRec(mqttPublish), null);
          case 0:
          case 1:
            break;
        } 
        if (this.callback != null)
          this.callback.messageArrived(mqttPublish); 
      } 
      if (paramMqttWireMessage instanceof MqttPubRel) {
        MqttPublish mqttPublish = (MqttPublish)this.inboundQoS2.get(new Integer(paramMqttWireMessage.getMessageId()));
        if (mqttPublish != null)
          if (this.callback != null)
            this.callback.messageArrived(mqttPublish);  
        send((MqttWireMessage)new MqttPubComp(paramMqttWireMessage.getMessageId()), null);
      } 
    } 
  }
  
  protected void notifyResult(MqttWireMessage paramMqttWireMessage, MqttToken paramMqttToken, MqttException paramMqttException) {
    paramMqttToken.internalTok.markComplete(paramMqttWireMessage, paramMqttException);
    if (paramMqttWireMessage != null && paramMqttWireMessage instanceof MqttAck && !(paramMqttWireMessage instanceof MqttPubRec)) {
      log.fine(CLASS_NAME, "notifyResult", "648", new Object[] { paramMqttToken.internalTok.getKey(), paramMqttWireMessage, paramMqttException });
      this.callback.asyncOperationComplete(paramMqttToken);
    } 
    if (paramMqttWireMessage == null) {
      log.fine(CLASS_NAME, "notifyResult", "649", new Object[] { paramMqttToken.internalTok.getKey(), paramMqttException });
      this.callback.asyncOperationComplete(paramMqttToken);
    } 
  }
  
  protected void notifySent(MqttWireMessage paramMqttWireMessage) {
    this.lastOutboundActivity = System.currentTimeMillis();
    log.fine(CLASS_NAME, "notifySent", "625", new Object[] { paramMqttWireMessage.getKey() });
    MqttToken mqttToken = this.tokenStore.getToken(paramMqttWireMessage);
    mqttToken.internalTok.notifySent();
    if (paramMqttWireMessage instanceof MqttPingReq) {
      synchronized (this.pingOutstandingLock) {
        long l = System.currentTimeMillis();
        synchronized (this.pingOutstandingLock) {
          this.lastPing = l;
          this.pingOutstanding++;
          Logger logger = log;
          null = CLASS_NAME;
          Integer integer = new Integer();
          this(this.pingOutstanding);
          logger.fine((String)null, "notifySent", "635", new Object[] { integer });
        } 
      } 
    } else if (paramMqttWireMessage instanceof MqttPublish && ((MqttPublish)paramMqttWireMessage).getMessage().getQos() == 0) {
      mqttToken.internalTok.markComplete(null, null);
      this.callback.asyncOperationComplete(mqttToken);
      decrementInFlight();
      releaseMessageId(paramMqttWireMessage.getMessageId());
      this.tokenStore.removeToken(paramMqttWireMessage);
      checkQuiesceLock();
    } 
  }
  
  public void notifySentBytes(int paramInt) {
    if (paramInt > 0)
      this.lastOutboundActivity = System.currentTimeMillis(); 
    log.fine(CLASS_NAME, "notifySentBytes", "631", new Object[] { new Integer(paramInt) });
  }
  
  public void quiesce(long paramLong) {
    if (paramLong > 0L) {
      log.fine(CLASS_NAME, "quiesce", "637", new Object[] { new Long(paramLong) });
      synchronized (this.queueLock) {
        this.quiescing = true;
        this.callback.quiesce();
        notifyQueueLock();
        synchronized (this.quiesceLock) {
          int i = this.tokenStore.count();
          if (i > 0 || this.pendingFlows.size() > 0 || !this.callback.isQuiesced()) {
            Logger logger = log;
            String str = CLASS_NAME;
            Integer integer1 = new Integer();
            this(this.actualInFlight);
            null = new Integer();
            super(this.pendingFlows.size());
            Integer integer2 = new Integer();
            this(this.inFlightPubRels);
            Integer integer3 = new Integer();
            this(i);
            logger.fine(str, "quiesce", "639", new Object[] { integer1, null, integer2, integer3 });
            this.quiesceLock.wait(paramLong);
          } 
        } 
        /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=SYNTHETIC_LOCAL_VARIABLE_4} */
        synchronized (this.queueLock) {
          this.pendingMessages.clear();
          this.pendingFlows.clear();
          this.quiescing = false;
          this.actualInFlight = 0;
          log.fine(CLASS_NAME, "quiesce", "640");
        } 
      } 
    } 
  }
  
  public Vector resolveOldTokens(MqttException paramMqttException) {
    log.fine(CLASS_NAME, "resolveOldTokens", "632", new Object[] { paramMqttException });
    MqttException mqttException = paramMqttException;
    if (paramMqttException == null)
      mqttException = new MqttException(32102); 
    Vector vector = this.tokenStore.getOutstandingTokens();
    Enumeration<MqttToken> enumeration = vector.elements();
    while (true) {
      if (!enumeration.hasMoreElements())
        return vector; 
      synchronized ((MqttToken)enumeration.nextElement()) {
        if (!null.isComplete() && !null.internalTok.isCompletePending() && null.getException() == null)
          null.internalTok.setException(mqttException); 
        if (!(null instanceof org.eclipse.paho.client.mqttv3.MqttDeliveryToken))
          this.tokenStore.removeToken(null.internalTok.getKey()); 
      } 
    } 
  }
  
  protected void restoreState() throws MqttException {
    Enumeration<String> enumeration = this.persistence.keys();
    int i = this.nextMsgId;
    Vector vector = new Vector();
    log.fine(CLASS_NAME, "restoreState", "600");
    while (true) {
      Enumeration<String> enumeration1;
      if (!enumeration.hasMoreElements()) {
        enumeration1 = vector.elements();
        while (true) {
          if (!enumeration1.hasMoreElements()) {
            this.nextMsgId = i;
            return;
          } 
          String str1 = enumeration1.nextElement();
          log.fine(CLASS_NAME, "restoreState", "609", new Object[] { str1 });
          this.persistence.remove(str1);
        } 
        break;
      } 
      String str = enumeration.nextElement();
      MqttWireMessage mqttWireMessage = restoreMessage(str, this.persistence.get(str));
      if (mqttWireMessage != null) {
        if (str.startsWith("r-")) {
          log.fine(CLASS_NAME, "restoreState", "604", new Object[] { str, mqttWireMessage });
          this.inboundQoS2.put(new Integer(mqttWireMessage.getMessageId()), mqttWireMessage);
          continue;
        } 
        if (str.startsWith("s-")) {
          MqttPublish mqttPublish = (MqttPublish)mqttWireMessage;
          i = Math.max(mqttPublish.getMessageId(), i);
          if (this.persistence.containsKey(getSendConfirmPersistenceKey((MqttWireMessage)mqttPublish))) {
            MqttPubRel mqttPubRel = (MqttPubRel)restoreMessage(str, this.persistence.get(getSendConfirmPersistenceKey((MqttWireMessage)mqttPublish)));
            if (mqttPubRel != null) {
              log.fine(CLASS_NAME, "restoreState", "605", new Object[] { str, mqttWireMessage });
              this.outboundQoS2.put(new Integer(mqttPubRel.getMessageId()), mqttPubRel);
            } else {
              log.fine(CLASS_NAME, "restoreState", "606", new Object[] { str, mqttWireMessage });
            } 
          } else {
            mqttPublish.setDuplicate(true);
            if (mqttPublish.getMessage().getQos() == 2) {
              log.fine(CLASS_NAME, "restoreState", "607", new Object[] { str, mqttWireMessage });
              this.outboundQoS2.put(new Integer(mqttPublish.getMessageId()), mqttPublish);
            } else {
              log.fine(CLASS_NAME, "restoreState", "608", new Object[] { str, mqttWireMessage });
              this.outboundQoS1.put(new Integer(mqttPublish.getMessageId()), mqttPublish);
            } 
          } 
          (this.tokenStore.restoreToken(mqttPublish)).internalTok.setClient(this.clientComms.getClient());
          this.inUseMsgIds.put(new Integer(mqttPublish.getMessageId()), new Integer(mqttPublish.getMessageId()));
          continue;
        } 
        if (str.startsWith("sc-")) {
          MqttPubRel mqttPubRel = (MqttPubRel)mqttWireMessage;
          if (!this.persistence.containsKey(getSendPersistenceKey((MqttWireMessage)mqttPubRel)))
            enumeration1.addElement(str); 
        } 
      } 
    } 
  }
  
  public void send(MqttWireMessage paramMqttWireMessage, MqttToken paramMqttToken) throws MqttException {
    Integer integer;
    if (paramMqttWireMessage.isMessageIdRequired() && paramMqttWireMessage.getMessageId() == 0)
      paramMqttWireMessage.setMessageId(getNextMessageId()); 
    if (paramMqttToken != null)
      try {
        paramMqttToken.internalTok.setMessageID(paramMqttWireMessage.getMessageId());
      } catch (Exception exception) {} 
    if (paramMqttWireMessage instanceof MqttPublish) {
      synchronized (this.queueLock) {
        MqttException mqttException;
        Integer integer1;
        Hashtable<Integer, MqttException> hashtable1;
        Hashtable<Integer, MqttException> hashtable2;
        Integer integer2;
        if (this.actualInFlight >= this.maxInflight) {
          Logger logger1 = log;
          String str1 = CLASS_NAME;
          integer = new Integer();
          this(this.actualInFlight);
          logger1.fine(str1, "send", "613", new Object[] { integer });
          mqttException = new MqttException();
          this(32202);
          throw mqttException;
        } 
        MqttMessage mqttMessage = ((MqttPublish)mqttException).getMessage();
        Logger logger = log;
        String str = CLASS_NAME;
        Integer integer3 = new Integer();
        this(mqttException.getMessageId());
        Integer integer4 = new Integer();
        this(mqttMessage.getQos());
        logger.fine(str, "send", "628", new Object[] { integer3, integer4, mqttException });
        switch (mqttMessage.getQos()) {
          case 2:
            hashtable2 = this.outboundQoS2;
            integer1 = new Integer();
            this(mqttException.getMessageId());
            hashtable2.put(integer1, mqttException);
            this.persistence.put(getSendPersistenceKey((MqttWireMessage)mqttException), (MqttPersistable)mqttException);
            break;
          case 1:
            hashtable1 = this.outboundQoS1;
            integer2 = new Integer();
            this(mqttException.getMessageId());
            hashtable1.put(integer2, mqttException);
            this.persistence.put(getSendPersistenceKey((MqttWireMessage)mqttException), (MqttPersistable)mqttException);
            break;
        } 
        this.tokenStore.saveToken((MqttToken)integer, (MqttWireMessage)mqttException);
        this.pendingMessages.addElement(mqttException);
        this.queueLock.notifyAll();
      } 
    } else {
      log.fine(CLASS_NAME, "send", "615", new Object[] { new Integer(paramMqttWireMessage.getMessageId()), paramMqttWireMessage });
      if (paramMqttWireMessage instanceof org.eclipse.paho.client.mqttv3.internal.wire.MqttConnect) {
        synchronized (this.queueLock) {
          this.tokenStore.saveToken((MqttToken)integer, paramMqttWireMessage);
          this.pendingFlows.insertElementAt(paramMqttWireMessage, 0);
          this.queueLock.notifyAll();
        } 
      } else {
        if (paramMqttWireMessage instanceof MqttPingReq) {
          this.pingCommand = paramMqttWireMessage;
        } else if (paramMqttWireMessage instanceof MqttPubRel) {
          this.outboundQoS2.put(new Integer(paramMqttWireMessage.getMessageId()), paramMqttWireMessage);
          this.persistence.put(getSendConfirmPersistenceKey(paramMqttWireMessage), (MqttPersistable)paramMqttWireMessage);
        } else if (paramMqttWireMessage instanceof MqttPubComp) {
          this.persistence.remove(getReceivedPersistenceKey(paramMqttWireMessage));
        } 
        synchronized (this.queueLock) {
          if (!(paramMqttWireMessage instanceof MqttAck))
            this.tokenStore.saveToken((MqttToken)integer, paramMqttWireMessage); 
          this.pendingFlows.addElement(paramMqttWireMessage);
          this.queueLock.notifyAll();
          return;
        } 
      } 
    } 
  }
  
  protected void setCleanSession(boolean paramBoolean) {
    this.cleanSession = paramBoolean;
  }
  
  public void setKeepAliveInterval(long paramLong) {
    this.keepAlive = paramLong;
  }
  
  protected void setKeepAliveSecs(long paramLong) {
    this.keepAlive = paramLong * 1000L;
  }
  
  protected void undo(MqttPublish paramMqttPublish) throws MqttPersistenceException {
    synchronized (this.queueLock) {
      Logger logger = log;
      String str = CLASS_NAME;
      Integer integer1 = new Integer();
      this(paramMqttPublish.getMessageId());
      Integer integer2 = new Integer();
      this(paramMqttPublish.getMessage().getQos());
      logger.fine(str, "undo", "618", new Object[] { integer1, integer2 });
      if (paramMqttPublish.getMessage().getQos() == 1) {
        Hashtable hashtable = this.outboundQoS1;
        Integer integer = new Integer();
        this(paramMqttPublish.getMessageId());
        hashtable.remove(integer);
      } else {
        Hashtable hashtable = this.outboundQoS2;
        integer2 = new Integer();
        this(paramMqttPublish.getMessageId());
        hashtable.remove(integer2);
      } 
      this.pendingMessages.removeElement(paramMqttPublish);
      this.persistence.remove(getSendPersistenceKey((MqttWireMessage)paramMqttPublish));
      this.tokenStore.removeToken((MqttWireMessage)paramMqttPublish);
      checkQuiesceLock();
      return;
    } 
  }
  
  static {
    Class<?> clazz1 = class$0;
    Class<?> clazz2 = clazz1;
    if (clazz1 == null)
      try {
        clazz2 = Class.forName("org.eclipse.paho.client.mqttv3.internal.ClientState");
        class$0 = clazz2;
      } catch (ClassNotFoundException classNotFoundException) {
        throw new NoClassDefFoundError(classNotFoundException.getMessage());
      }  
    CLASS_NAME = classNotFoundException.getName();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\ClientState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */