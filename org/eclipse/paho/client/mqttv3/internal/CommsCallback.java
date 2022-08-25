package org.eclipse.paho.client.mqttv3.internal;

import java.util.Vector;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubComp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class CommsCallback implements Runnable {
  private static final String CLASS_NAME;
  
  private static final int INBOUND_QUEUE_SIZE = 10;
  
  private static final Logger log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", CLASS_NAME);
  
  private Thread callbackThread;
  
  private ClientComms clientComms;
  
  private ClientState clientState;
  
  private Vector completeQueue;
  
  private Object lifecycle = new Object();
  
  private Vector messageQueue;
  
  private MqttCallback mqttCallback;
  
  private boolean quiescing = false;
  
  public boolean running = false;
  
  private Object spaceAvailable = new Object();
  
  private Object workAvailable = new Object();
  
  CommsCallback(ClientComms paramClientComms) {
    this.clientComms = paramClientComms;
    this.messageQueue = new Vector(10);
    this.completeQueue = new Vector(10);
    log.setResourceName(paramClientComms.getClient().getClientId());
  }
  
  private void handleActionComplete(MqttToken paramMqttToken) throws MqttException {
    // Byte code:
    //   0: aload_1
    //   1: monitorenter
    //   2: getstatic org/eclipse/paho/client/mqttv3/internal/CommsCallback.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   5: getstatic org/eclipse/paho/client/mqttv3/internal/CommsCallback.CLASS_NAME : Ljava/lang/String;
    //   8: ldc 'handleActionComplete'
    //   10: ldc '705'
    //   12: iconst_1
    //   13: anewarray java/lang/Object
    //   16: dup
    //   17: iconst_0
    //   18: aload_1
    //   19: getfield internalTok : Lorg/eclipse/paho/client/mqttv3/internal/Token;
    //   22: invokevirtual getKey : ()Ljava/lang/String;
    //   25: aastore
    //   26: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   31: aload_1
    //   32: getfield internalTok : Lorg/eclipse/paho/client/mqttv3/internal/Token;
    //   35: invokevirtual notifyComplete : ()V
    //   38: aload_1
    //   39: getfield internalTok : Lorg/eclipse/paho/client/mqttv3/internal/Token;
    //   42: invokevirtual isNotified : ()Z
    //   45: ifne -> 87
    //   48: aload_0
    //   49: getfield mqttCallback : Lorg/eclipse/paho/client/mqttv3/MqttCallback;
    //   52: ifnull -> 82
    //   55: aload_1
    //   56: instanceof org/eclipse/paho/client/mqttv3/MqttDeliveryToken
    //   59: ifeq -> 82
    //   62: aload_1
    //   63: invokevirtual isComplete : ()Z
    //   66: ifeq -> 82
    //   69: aload_0
    //   70: getfield mqttCallback : Lorg/eclipse/paho/client/mqttv3/MqttCallback;
    //   73: aload_1
    //   74: checkcast org/eclipse/paho/client/mqttv3/MqttDeliveryToken
    //   77: invokeinterface deliveryComplete : (Lorg/eclipse/paho/client/mqttv3/IMqttDeliveryToken;)V
    //   82: aload_0
    //   83: aload_1
    //   84: invokevirtual fireActionEvent : (Lorg/eclipse/paho/client/mqttv3/MqttToken;)V
    //   87: aload_1
    //   88: invokevirtual isComplete : ()Z
    //   91: ifeq -> 119
    //   94: aload_1
    //   95: instanceof org/eclipse/paho/client/mqttv3/MqttDeliveryToken
    //   98: ifne -> 111
    //   101: aload_1
    //   102: invokevirtual getActionCallback : ()Lorg/eclipse/paho/client/mqttv3/IMqttActionListener;
    //   105: instanceof org/eclipse/paho/client/mqttv3/IMqttActionListener
    //   108: ifeq -> 119
    //   111: aload_1
    //   112: getfield internalTok : Lorg/eclipse/paho/client/mqttv3/internal/Token;
    //   115: iconst_1
    //   116: invokevirtual setNotified : (Z)V
    //   119: aload_1
    //   120: invokevirtual isComplete : ()Z
    //   123: ifeq -> 134
    //   126: aload_0
    //   127: getfield clientState : Lorg/eclipse/paho/client/mqttv3/internal/ClientState;
    //   130: aload_1
    //   131: invokevirtual notifyComplete : (Lorg/eclipse/paho/client/mqttv3/MqttToken;)V
    //   134: aload_1
    //   135: monitorexit
    //   136: return
    //   137: astore_2
    //   138: aload_1
    //   139: monitorexit
    //   140: aload_2
    //   141: athrow
    // Exception table:
    //   from	to	target	type
    //   2	82	137	finally
    //   82	87	137	finally
    //   87	111	137	finally
    //   111	119	137	finally
    //   119	134	137	finally
    //   134	136	137	finally
    //   138	140	137	finally
  }
  
  private void handleMessage(MqttPublish paramMqttPublish) throws MqttException, Exception {
    if (this.mqttCallback != null) {
      String str = paramMqttPublish.getTopicName();
      log.fine(CLASS_NAME, "handleMessage", "713", new Object[] { new Integer(paramMqttPublish.getMessageId()), str });
      this.mqttCallback.messageArrived(str, paramMqttPublish.getMessage());
      if (paramMqttPublish.getMessage().getQos() == 1) {
        this.clientComms.internalSend((MqttWireMessage)new MqttPubAck(paramMqttPublish), new MqttToken(this.clientComms.getClient().getClientId()));
      } else if (paramMqttPublish.getMessage().getQos() == 2) {
        this.clientComms.deliveryComplete(paramMqttPublish);
        MqttPubComp mqttPubComp = new MqttPubComp(paramMqttPublish);
        this.clientComms.internalSend((MqttWireMessage)mqttPubComp, new MqttToken(this.clientComms.getClient().getClientId()));
      } 
    } 
  }
  
  public void asyncOperationComplete(MqttToken paramMqttToken) {
    if (this.running) {
      this.completeQueue.addElement(paramMqttToken);
      synchronized (this.workAvailable) {
        log.fine(CLASS_NAME, "asyncOperationComplete", "715", new Object[] { paramMqttToken.internalTok.getKey() });
        this.workAvailable.notifyAll();
      } 
    } else {
      try {
        handleActionComplete(paramMqttToken);
      } catch (Throwable throwable) {
        log.fine(CLASS_NAME, "asyncOperationComplete", "719", null, throwable);
        this.clientComms.shutdownConnection(null, new MqttException(throwable));
      } 
    } 
  }
  
  public void connectionLost(MqttException paramMqttException) {
    try {
      if (this.mqttCallback != null && paramMqttException != null) {
        log.fine(CLASS_NAME, "connectionLost", "708", new Object[] { paramMqttException });
        this.mqttCallback.connectionLost((Throwable)paramMqttException);
      } 
    } catch (Throwable throwable) {
      log.fine(CLASS_NAME, "connectionLost", "720", new Object[] { throwable });
    } 
  }
  
  public void fireActionEvent(MqttToken paramMqttToken) {
    if (paramMqttToken != null) {
      IMqttActionListener iMqttActionListener = paramMqttToken.getActionCallback();
      if (iMqttActionListener != null)
        if (paramMqttToken.getException() == null) {
          log.fine(CLASS_NAME, "fireActionEvent", "716", new Object[] { paramMqttToken.internalTok.getKey() });
          iMqttActionListener.onSuccess((IMqttToken)paramMqttToken);
        } else {
          log.fine(CLASS_NAME, "fireActionEvent", "716", new Object[] { paramMqttToken.internalTok.getKey() });
          iMqttActionListener.onFailure((IMqttToken)paramMqttToken, (Throwable)paramMqttToken.getException());
        }  
    } 
  }
  
  protected Thread getThread() {
    return this.callbackThread;
  }
  
  public boolean isQuiesced() {
    return (this.quiescing && this.completeQueue.size() == 0 && this.messageQueue.size() == 0);
  }
  
  public void messageArrived(MqttPublish paramMqttPublish) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mqttCallback : Lorg/eclipse/paho/client/mqttv3/MqttCallback;
    //   4: ifnull -> 138
    //   7: aload_0
    //   8: getfield spaceAvailable : Ljava/lang/Object;
    //   11: astore_2
    //   12: aload_2
    //   13: monitorenter
    //   14: aload_0
    //   15: getfield running : Z
    //   18: ifeq -> 75
    //   21: aload_0
    //   22: getfield quiescing : Z
    //   25: ifne -> 75
    //   28: aload_0
    //   29: getfield messageQueue : Ljava/util/Vector;
    //   32: invokevirtual size : ()I
    //   35: istore_3
    //   36: iload_3
    //   37: bipush #10
    //   39: if_icmpge -> 45
    //   42: goto -> 75
    //   45: getstatic org/eclipse/paho/client/mqttv3/internal/CommsCallback.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   48: getstatic org/eclipse/paho/client/mqttv3/internal/CommsCallback.CLASS_NAME : Ljava/lang/String;
    //   51: ldc_w 'messageArrived'
    //   54: ldc_w '709'
    //   57: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   62: aload_0
    //   63: getfield spaceAvailable : Ljava/lang/Object;
    //   66: ldc2_w 200
    //   69: invokevirtual wait : (J)V
    //   72: goto -> 14
    //   75: aload_2
    //   76: monitorexit
    //   77: aload_0
    //   78: getfield quiescing : Z
    //   81: ifne -> 138
    //   84: aload_0
    //   85: getfield messageQueue : Ljava/util/Vector;
    //   88: aload_1
    //   89: invokevirtual addElement : (Ljava/lang/Object;)V
    //   92: aload_0
    //   93: getfield workAvailable : Ljava/lang/Object;
    //   96: astore_2
    //   97: aload_2
    //   98: monitorenter
    //   99: getstatic org/eclipse/paho/client/mqttv3/internal/CommsCallback.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   102: getstatic org/eclipse/paho/client/mqttv3/internal/CommsCallback.CLASS_NAME : Ljava/lang/String;
    //   105: ldc_w 'messageArrived'
    //   108: ldc_w '710'
    //   111: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   116: aload_0
    //   117: getfield workAvailable : Ljava/lang/Object;
    //   120: invokevirtual notifyAll : ()V
    //   123: aload_2
    //   124: monitorexit
    //   125: goto -> 138
    //   128: astore_1
    //   129: aload_2
    //   130: monitorexit
    //   131: aload_1
    //   132: athrow
    //   133: astore_1
    //   134: aload_2
    //   135: monitorexit
    //   136: aload_1
    //   137: athrow
    //   138: return
    //   139: astore #4
    //   141: goto -> 14
    // Exception table:
    //   from	to	target	type
    //   14	36	133	finally
    //   45	72	139	java/lang/InterruptedException
    //   45	72	133	finally
    //   75	77	133	finally
    //   99	125	128	finally
    //   129	131	128	finally
    //   134	136	133	finally
  }
  
  public void quiesce() {
    this.quiescing = true;
    synchronized (this.spaceAvailable) {
      log.fine(CLASS_NAME, "quiesce", "711");
      this.spaceAvailable.notifyAll();
      return;
    } 
  }
  
  public void run() {
    // Byte code:
    //   0: aload_0
    //   1: getfield running : Z
    //   4: ifne -> 8
    //   7: return
    //   8: aload_0
    //   9: getfield workAvailable : Ljava/lang/Object;
    //   12: astore_1
    //   13: aload_1
    //   14: monitorenter
    //   15: aload_0
    //   16: getfield running : Z
    //   19: ifeq -> 66
    //   22: aload_0
    //   23: getfield messageQueue : Ljava/util/Vector;
    //   26: invokevirtual isEmpty : ()Z
    //   29: ifeq -> 66
    //   32: aload_0
    //   33: getfield completeQueue : Ljava/util/Vector;
    //   36: invokevirtual isEmpty : ()Z
    //   39: ifeq -> 66
    //   42: getstatic org/eclipse/paho/client/mqttv3/internal/CommsCallback.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   45: getstatic org/eclipse/paho/client/mqttv3/internal/CommsCallback.CLASS_NAME : Ljava/lang/String;
    //   48: ldc_w 'run'
    //   51: ldc_w '704'
    //   54: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   59: aload_0
    //   60: getfield workAvailable : Ljava/lang/Object;
    //   63: invokevirtual wait : ()V
    //   66: aload_1
    //   67: monitorexit
    //   68: goto -> 84
    //   71: astore_2
    //   72: aload_1
    //   73: monitorexit
    //   74: aload_2
    //   75: athrow
    //   76: astore_1
    //   77: goto -> 313
    //   80: astore_3
    //   81: goto -> 228
    //   84: aload_0
    //   85: getfield running : Z
    //   88: ifeq -> 210
    //   91: aload_0
    //   92: getfield completeQueue : Ljava/util/Vector;
    //   95: astore_1
    //   96: aload_1
    //   97: monitorenter
    //   98: aload_0
    //   99: getfield completeQueue : Ljava/util/Vector;
    //   102: invokevirtual isEmpty : ()Z
    //   105: ifne -> 131
    //   108: aload_0
    //   109: getfield completeQueue : Ljava/util/Vector;
    //   112: iconst_0
    //   113: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   116: checkcast org/eclipse/paho/client/mqttv3/MqttToken
    //   119: astore_2
    //   120: aload_0
    //   121: getfield completeQueue : Ljava/util/Vector;
    //   124: iconst_0
    //   125: invokevirtual removeElementAt : (I)V
    //   128: goto -> 133
    //   131: aconst_null
    //   132: astore_2
    //   133: aload_1
    //   134: monitorexit
    //   135: aload_2
    //   136: ifnull -> 144
    //   139: aload_0
    //   140: aload_2
    //   141: invokespecial handleActionComplete : (Lorg/eclipse/paho/client/mqttv3/MqttToken;)V
    //   144: aload_0
    //   145: getfield messageQueue : Ljava/util/Vector;
    //   148: astore_1
    //   149: aload_1
    //   150: monitorenter
    //   151: aload_0
    //   152: getfield messageQueue : Ljava/util/Vector;
    //   155: invokevirtual isEmpty : ()Z
    //   158: ifne -> 184
    //   161: aload_0
    //   162: getfield messageQueue : Ljava/util/Vector;
    //   165: iconst_0
    //   166: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   169: checkcast org/eclipse/paho/client/mqttv3/internal/wire/MqttPublish
    //   172: astore_2
    //   173: aload_0
    //   174: getfield messageQueue : Ljava/util/Vector;
    //   177: iconst_0
    //   178: invokevirtual removeElementAt : (I)V
    //   181: goto -> 186
    //   184: aconst_null
    //   185: astore_2
    //   186: aload_1
    //   187: monitorexit
    //   188: aload_2
    //   189: ifnull -> 210
    //   192: aload_0
    //   193: aload_2
    //   194: invokespecial handleMessage : (Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttPublish;)V
    //   197: goto -> 210
    //   200: astore_2
    //   201: aload_1
    //   202: monitorexit
    //   203: aload_2
    //   204: athrow
    //   205: astore_2
    //   206: aload_1
    //   207: monitorexit
    //   208: aload_2
    //   209: athrow
    //   210: aload_0
    //   211: getfield quiescing : Z
    //   214: ifeq -> 272
    //   217: aload_0
    //   218: getfield clientState : Lorg/eclipse/paho/client/mqttv3/internal/ClientState;
    //   221: invokevirtual checkQuiesceLock : ()Z
    //   224: pop
    //   225: goto -> 272
    //   228: getstatic org/eclipse/paho/client/mqttv3/internal/CommsCallback.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   231: getstatic org/eclipse/paho/client/mqttv3/internal/CommsCallback.CLASS_NAME : Ljava/lang/String;
    //   234: ldc_w 'run'
    //   237: ldc_w '714'
    //   240: aconst_null
    //   241: aload_3
    //   242: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;Ljava/lang/Throwable;)V
    //   247: aload_0
    //   248: iconst_0
    //   249: putfield running : Z
    //   252: aload_0
    //   253: getfield clientComms : Lorg/eclipse/paho/client/mqttv3/internal/ClientComms;
    //   256: astore_1
    //   257: new org/eclipse/paho/client/mqttv3/MqttException
    //   260: astore_2
    //   261: aload_2
    //   262: aload_3
    //   263: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   266: aload_1
    //   267: aconst_null
    //   268: aload_2
    //   269: invokevirtual shutdownConnection : (Lorg/eclipse/paho/client/mqttv3/MqttToken;Lorg/eclipse/paho/client/mqttv3/MqttException;)V
    //   272: aload_0
    //   273: getfield spaceAvailable : Ljava/lang/Object;
    //   276: astore_2
    //   277: aload_2
    //   278: monitorenter
    //   279: getstatic org/eclipse/paho/client/mqttv3/internal/CommsCallback.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   282: getstatic org/eclipse/paho/client/mqttv3/internal/CommsCallback.CLASS_NAME : Ljava/lang/String;
    //   285: ldc_w 'run'
    //   288: ldc_w '706'
    //   291: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   296: aload_0
    //   297: getfield spaceAvailable : Ljava/lang/Object;
    //   300: invokevirtual notifyAll : ()V
    //   303: aload_2
    //   304: monitorexit
    //   305: goto -> 0
    //   308: astore_1
    //   309: aload_2
    //   310: monitorexit
    //   311: aload_1
    //   312: athrow
    //   313: aload_0
    //   314: getfield spaceAvailable : Ljava/lang/Object;
    //   317: astore_2
    //   318: aload_2
    //   319: monitorenter
    //   320: getstatic org/eclipse/paho/client/mqttv3/internal/CommsCallback.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   323: getstatic org/eclipse/paho/client/mqttv3/internal/CommsCallback.CLASS_NAME : Ljava/lang/String;
    //   326: ldc_w 'run'
    //   329: ldc_w '706'
    //   332: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   337: aload_0
    //   338: getfield spaceAvailable : Ljava/lang/Object;
    //   341: invokevirtual notifyAll : ()V
    //   344: aload_2
    //   345: monitorexit
    //   346: aload_1
    //   347: athrow
    //   348: astore_1
    //   349: aload_2
    //   350: monitorexit
    //   351: aload_1
    //   352: athrow
    //   353: astore_2
    //   354: goto -> 84
    // Exception table:
    //   from	to	target	type
    //   8	15	353	java/lang/InterruptedException
    //   8	15	80	java/lang/Throwable
    //   8	15	76	finally
    //   15	66	71	finally
    //   66	68	71	finally
    //   72	74	71	finally
    //   74	76	353	java/lang/InterruptedException
    //   74	76	80	java/lang/Throwable
    //   74	76	76	finally
    //   84	98	80	java/lang/Throwable
    //   84	98	76	finally
    //   98	128	205	finally
    //   133	135	205	finally
    //   139	144	80	java/lang/Throwable
    //   139	144	76	finally
    //   144	151	80	java/lang/Throwable
    //   144	151	76	finally
    //   151	181	200	finally
    //   186	188	200	finally
    //   192	197	80	java/lang/Throwable
    //   192	197	76	finally
    //   201	203	200	finally
    //   203	205	80	java/lang/Throwable
    //   203	205	76	finally
    //   206	208	205	finally
    //   208	210	80	java/lang/Throwable
    //   208	210	76	finally
    //   210	225	80	java/lang/Throwable
    //   210	225	76	finally
    //   228	272	76	finally
    //   279	305	308	finally
    //   309	311	308	finally
    //   320	346	348	finally
    //   349	351	348	finally
  }
  
  public void setCallback(MqttCallback paramMqttCallback) {
    this.mqttCallback = paramMqttCallback;
  }
  
  public void setClientState(ClientState paramClientState) {
    this.clientState = paramClientState;
  }
  
  public void start(String paramString) {
    synchronized (this.lifecycle) {
      if (!this.running) {
        this.messageQueue.clear();
        this.completeQueue.clear();
        this.running = true;
        this.quiescing = false;
        Thread thread = new Thread();
        this(this, paramString);
        this.callbackThread = thread;
        this.callbackThread.start();
      } 
      return;
    } 
  }
  
  public void stop() {
    synchronized (this.lifecycle) {
      if (this.running) {
        log.fine(CLASS_NAME, "stop", "700");
        this.running = false;
        boolean bool = Thread.currentThread().equals(this.callbackThread);
        if (!bool)
          try {
            synchronized (this.workAvailable) {
              log.fine(CLASS_NAME, "stop", "701");
              this.workAvailable.notifyAll();
              this.callbackThread.join();
            } 
          } catch (InterruptedException interruptedException) {} 
      } 
      this.callbackThread = null;
      log.fine(CLASS_NAME, "stop", "703");
      return;
    } 
  }
  
  static {
    Class<?> clazz1 = class$0;
    Class<?> clazz2 = clazz1;
    if (clazz1 == null)
      try {
        clazz2 = Class.forName("org.eclipse.paho.client.mqttv3.internal.CommsCallback");
        class$0 = clazz2;
      } catch (ClassNotFoundException classNotFoundException) {
        throw new NoClassDefFoundError(classNotFoundException.getMessage());
      }  
    CLASS_NAME = classNotFoundException.getName();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\CommsCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */