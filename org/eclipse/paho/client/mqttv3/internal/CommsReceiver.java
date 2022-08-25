package org.eclipse.paho.client.mqttv3.internal;

import java.io.InputStream;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttInputStream;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class CommsReceiver implements Runnable {
  private static final String CLASS_NAME;
  
  private static final Logger log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", CLASS_NAME);
  
  private ClientComms clientComms = null;
  
  private ClientState clientState = null;
  
  private MqttInputStream in;
  
  private Object lifecycle = new Object();
  
  private Thread recThread = null;
  
  private volatile boolean receiving;
  
  private boolean running = false;
  
  private CommsTokenStore tokenStore = null;
  
  public CommsReceiver(ClientComms paramClientComms, ClientState paramClientState, CommsTokenStore paramCommsTokenStore, InputStream paramInputStream) {
    this.in = new MqttInputStream(paramClientState, paramInputStream);
    this.clientComms = paramClientComms;
    this.clientState = paramClientState;
    this.tokenStore = paramCommsTokenStore;
    log.setResourceName(paramClientComms.getClient().getClientId());
  }
  
  public boolean isReceiving() {
    return this.receiving;
  }
  
  public boolean isRunning() {
    return this.running;
  }
  
  public void run() {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: getfield running : Z
    //   6: ifeq -> 274
    //   9: aload_0
    //   10: getfield in : Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttInputStream;
    //   13: ifnonnull -> 19
    //   16: goto -> 274
    //   19: getstatic org/eclipse/paho/client/mqttv3/internal/CommsReceiver.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   22: getstatic org/eclipse/paho/client/mqttv3/internal/CommsReceiver.CLASS_NAME : Ljava/lang/String;
    //   25: ldc 'run'
    //   27: ldc '852'
    //   29: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   34: aload_0
    //   35: getfield in : Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttInputStream;
    //   38: invokevirtual available : ()I
    //   41: ifle -> 49
    //   44: iconst_1
    //   45: istore_2
    //   46: goto -> 51
    //   49: iconst_0
    //   50: istore_2
    //   51: aload_0
    //   52: iload_2
    //   53: putfield receiving : Z
    //   56: aload_0
    //   57: getfield in : Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttInputStream;
    //   60: invokevirtual readMqttWireMessage : ()Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttWireMessage;
    //   63: astore_3
    //   64: aload_0
    //   65: iconst_0
    //   66: putfield receiving : Z
    //   69: aload_3
    //   70: instanceof org/eclipse/paho/client/mqttv3/internal/wire/MqttAck
    //   73: ifeq -> 143
    //   76: aload_0
    //   77: getfield tokenStore : Lorg/eclipse/paho/client/mqttv3/internal/CommsTokenStore;
    //   80: aload_3
    //   81: invokevirtual getToken : (Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttWireMessage;)Lorg/eclipse/paho/client/mqttv3/MqttToken;
    //   84: astore #4
    //   86: aload #4
    //   88: ifnull -> 131
    //   91: aload #4
    //   93: monitorenter
    //   94: aload_0
    //   95: getfield clientState : Lorg/eclipse/paho/client/mqttv3/internal/ClientState;
    //   98: aload_3
    //   99: checkcast org/eclipse/paho/client/mqttv3/internal/wire/MqttAck
    //   102: invokevirtual notifyReceivedAck : (Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttAck;)V
    //   105: aload #4
    //   107: monitorexit
    //   108: aload #4
    //   110: astore_1
    //   111: goto -> 259
    //   114: astore_1
    //   115: aload #4
    //   117: monitorexit
    //   118: aload_1
    //   119: athrow
    //   120: astore_3
    //   121: goto -> 162
    //   124: astore_3
    //   125: aload #4
    //   127: astore_1
    //   128: goto -> 228
    //   131: new org/eclipse/paho/client/mqttv3/MqttException
    //   134: astore_1
    //   135: aload_1
    //   136: bipush #6
    //   138: invokespecial <init> : (I)V
    //   141: aload_1
    //   142: athrow
    //   143: aload_0
    //   144: getfield clientState : Lorg/eclipse/paho/client/mqttv3/internal/ClientState;
    //   147: aload_3
    //   148: invokevirtual notifyReceivedMsg : (Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttWireMessage;)V
    //   151: goto -> 259
    //   154: astore_1
    //   155: goto -> 267
    //   158: astore_3
    //   159: aload_1
    //   160: astore #4
    //   162: getstatic org/eclipse/paho/client/mqttv3/internal/CommsReceiver.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   165: getstatic org/eclipse/paho/client/mqttv3/internal/CommsReceiver.CLASS_NAME : Ljava/lang/String;
    //   168: ldc 'run'
    //   170: ldc '853'
    //   172: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   177: aload_0
    //   178: iconst_0
    //   179: putfield running : Z
    //   182: aload #4
    //   184: astore_1
    //   185: aload_0
    //   186: getfield clientComms : Lorg/eclipse/paho/client/mqttv3/internal/ClientComms;
    //   189: invokevirtual isDisconnecting : ()Z
    //   192: ifne -> 259
    //   195: aload_0
    //   196: getfield clientComms : Lorg/eclipse/paho/client/mqttv3/internal/ClientComms;
    //   199: astore #5
    //   201: new org/eclipse/paho/client/mqttv3/MqttException
    //   204: astore_1
    //   205: aload_1
    //   206: sipush #32109
    //   209: aload_3
    //   210: invokespecial <init> : (ILjava/lang/Throwable;)V
    //   213: aload #5
    //   215: aload #4
    //   217: aload_1
    //   218: invokevirtual shutdownConnection : (Lorg/eclipse/paho/client/mqttv3/MqttToken;Lorg/eclipse/paho/client/mqttv3/MqttException;)V
    //   221: aload #4
    //   223: astore_1
    //   224: goto -> 259
    //   227: astore_3
    //   228: getstatic org/eclipse/paho/client/mqttv3/internal/CommsReceiver.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   231: getstatic org/eclipse/paho/client/mqttv3/internal/CommsReceiver.CLASS_NAME : Ljava/lang/String;
    //   234: ldc 'run'
    //   236: ldc '856'
    //   238: aconst_null
    //   239: aload_3
    //   240: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;Ljava/lang/Throwable;)V
    //   245: aload_0
    //   246: iconst_0
    //   247: putfield running : Z
    //   250: aload_0
    //   251: getfield clientComms : Lorg/eclipse/paho/client/mqttv3/internal/ClientComms;
    //   254: aload_1
    //   255: aload_3
    //   256: invokevirtual shutdownConnection : (Lorg/eclipse/paho/client/mqttv3/MqttToken;Lorg/eclipse/paho/client/mqttv3/MqttException;)V
    //   259: aload_0
    //   260: iconst_0
    //   261: putfield receiving : Z
    //   264: goto -> 2
    //   267: aload_0
    //   268: iconst_0
    //   269: putfield receiving : Z
    //   272: aload_1
    //   273: athrow
    //   274: getstatic org/eclipse/paho/client/mqttv3/internal/CommsReceiver.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   277: getstatic org/eclipse/paho/client/mqttv3/internal/CommsReceiver.CLASS_NAME : Ljava/lang/String;
    //   280: ldc 'run'
    //   282: ldc '854'
    //   284: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   289: return
    // Exception table:
    //   from	to	target	type
    //   19	44	227	org/eclipse/paho/client/mqttv3/MqttException
    //   19	44	158	java/io/IOException
    //   19	44	154	finally
    //   51	86	227	org/eclipse/paho/client/mqttv3/MqttException
    //   51	86	158	java/io/IOException
    //   51	86	154	finally
    //   91	94	124	org/eclipse/paho/client/mqttv3/MqttException
    //   91	94	120	java/io/IOException
    //   91	94	154	finally
    //   94	108	114	finally
    //   115	118	114	finally
    //   118	120	124	org/eclipse/paho/client/mqttv3/MqttException
    //   118	120	120	java/io/IOException
    //   118	120	154	finally
    //   131	143	124	org/eclipse/paho/client/mqttv3/MqttException
    //   131	143	120	java/io/IOException
    //   131	143	154	finally
    //   143	151	227	org/eclipse/paho/client/mqttv3/MqttException
    //   143	151	158	java/io/IOException
    //   143	151	154	finally
    //   162	182	154	finally
    //   185	221	154	finally
    //   228	259	154	finally
  }
  
  public void start(String paramString) {
    log.fine(CLASS_NAME, "start", "855");
    synchronized (this.lifecycle) {
      if (!this.running) {
        this.running = true;
        Thread thread = new Thread();
        this(this, paramString);
        this.recThread = thread;
        this.recThread.start();
      } 
      return;
    } 
  }
  
  public void stop() {
    synchronized (this.lifecycle) {
      log.fine(CLASS_NAME, "stop", "850");
      if (this.running) {
        this.running = false;
        this.receiving = false;
        boolean bool = Thread.currentThread().equals(this.recThread);
        if (!bool)
          try {
            this.recThread.join();
          } catch (InterruptedException interruptedException) {} 
      } 
      this.recThread = null;
      log.fine(CLASS_NAME, "stop", "851");
      return;
    } 
  }
  
  static {
    Class<?> clazz1 = class$0;
    Class<?> clazz2 = clazz1;
    if (clazz1 == null)
      try {
        clazz2 = Class.forName("org.eclipse.paho.client.mqttv3.internal.CommsReceiver");
        class$0 = clazz2;
      } catch (ClassNotFoundException classNotFoundException) {
        throw new NoClassDefFoundError(classNotFoundException.getMessage());
      }  
    CLASS_NAME = classNotFoundException.getName();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\CommsReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */