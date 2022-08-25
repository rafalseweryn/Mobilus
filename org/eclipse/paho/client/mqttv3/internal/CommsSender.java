package org.eclipse.paho.client.mqttv3.internal;

import java.io.OutputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttOutputStream;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class CommsSender implements Runnable {
  private static final String CLASS_NAME;
  
  private static final Logger log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", CLASS_NAME);
  
  private ClientComms clientComms = null;
  
  private ClientState clientState = null;
  
  private Object lifecycle = new Object();
  
  private MqttOutputStream out;
  
  private boolean running = false;
  
  private Thread sendThread = null;
  
  private CommsTokenStore tokenStore = null;
  
  public CommsSender(ClientComms paramClientComms, ClientState paramClientState, CommsTokenStore paramCommsTokenStore, OutputStream paramOutputStream) {
    this.out = new MqttOutputStream(paramClientState, paramOutputStream);
    this.clientComms = paramClientComms;
    this.clientState = paramClientState;
    this.tokenStore = paramCommsTokenStore;
    log.setResourceName(paramClientComms.getClient().getClientId());
  }
  
  private void handleRunException(MqttWireMessage paramMqttWireMessage, Exception paramException) {
    MqttException mqttException;
    log.fine(CLASS_NAME, "handleRunException", "804", null, paramException);
    if (!(paramException instanceof MqttException)) {
      mqttException = new MqttException(32109, paramException);
    } else {
      mqttException = (MqttException)paramException;
    } 
    this.running = false;
    this.clientComms.shutdownConnection(null, mqttException);
  }
  
  public void run() {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: getfield running : Z
    //   6: ifeq -> 210
    //   9: aload_0
    //   10: getfield out : Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttOutputStream;
    //   13: ifnonnull -> 19
    //   16: goto -> 210
    //   19: aload_0
    //   20: getfield clientState : Lorg/eclipse/paho/client/mqttv3/internal/ClientState;
    //   23: invokevirtual get : ()Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttWireMessage;
    //   26: astore_2
    //   27: aload_2
    //   28: ifnull -> 165
    //   31: getstatic org/eclipse/paho/client/mqttv3/internal/CommsSender.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   34: getstatic org/eclipse/paho/client/mqttv3/internal/CommsSender.CLASS_NAME : Ljava/lang/String;
    //   37: ldc 'run'
    //   39: ldc '802'
    //   41: iconst_2
    //   42: anewarray java/lang/Object
    //   45: dup
    //   46: iconst_0
    //   47: aload_2
    //   48: invokevirtual getKey : ()Ljava/lang/String;
    //   51: aastore
    //   52: dup
    //   53: iconst_1
    //   54: aload_2
    //   55: aastore
    //   56: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   61: aload_2
    //   62: instanceof org/eclipse/paho/client/mqttv3/internal/wire/MqttAck
    //   65: ifeq -> 88
    //   68: aload_0
    //   69: getfield out : Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttOutputStream;
    //   72: aload_2
    //   73: invokevirtual write : (Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttWireMessage;)V
    //   76: aload_0
    //   77: getfield out : Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttOutputStream;
    //   80: invokevirtual flush : ()V
    //   83: aload_2
    //   84: astore_1
    //   85: goto -> 207
    //   88: aload_0
    //   89: getfield tokenStore : Lorg/eclipse/paho/client/mqttv3/internal/CommsTokenStore;
    //   92: aload_2
    //   93: invokevirtual getToken : (Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttWireMessage;)Lorg/eclipse/paho/client/mqttv3/MqttToken;
    //   96: astore_3
    //   97: aload_2
    //   98: astore_1
    //   99: aload_3
    //   100: ifnull -> 207
    //   103: aload_3
    //   104: monitorenter
    //   105: aload_0
    //   106: getfield out : Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttOutputStream;
    //   109: aload_2
    //   110: invokevirtual write : (Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttWireMessage;)V
    //   113: aload_0
    //   114: getfield out : Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttOutputStream;
    //   117: invokevirtual flush : ()V
    //   120: goto -> 133
    //   123: astore_1
    //   124: aload_2
    //   125: instanceof org/eclipse/paho/client/mqttv3/internal/wire/MqttDisconnect
    //   128: ifne -> 133
    //   131: aload_1
    //   132: athrow
    //   133: aload_0
    //   134: getfield clientState : Lorg/eclipse/paho/client/mqttv3/internal/ClientState;
    //   137: aload_2
    //   138: invokevirtual notifySent : (Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttWireMessage;)V
    //   141: aload_3
    //   142: monitorexit
    //   143: aload_2
    //   144: astore_1
    //   145: goto -> 207
    //   148: astore_1
    //   149: aload_3
    //   150: monitorexit
    //   151: aload_1
    //   152: athrow
    //   153: astore_3
    //   154: aload_2
    //   155: astore_1
    //   156: goto -> 191
    //   159: astore_3
    //   160: aload_2
    //   161: astore_1
    //   162: goto -> 201
    //   165: getstatic org/eclipse/paho/client/mqttv3/internal/CommsSender.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   168: getstatic org/eclipse/paho/client/mqttv3/internal/CommsSender.CLASS_NAME : Ljava/lang/String;
    //   171: ldc 'run'
    //   173: ldc '803'
    //   175: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   180: aload_0
    //   181: iconst_0
    //   182: putfield running : Z
    //   185: aload_2
    //   186: astore_1
    //   187: goto -> 207
    //   190: astore_3
    //   191: aload_0
    //   192: aload_1
    //   193: aload_3
    //   194: invokespecial handleRunException : (Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttWireMessage;Ljava/lang/Exception;)V
    //   197: goto -> 207
    //   200: astore_3
    //   201: aload_0
    //   202: aload_1
    //   203: aload_3
    //   204: invokespecial handleRunException : (Lorg/eclipse/paho/client/mqttv3/internal/wire/MqttWireMessage;Ljava/lang/Exception;)V
    //   207: goto -> 2
    //   210: getstatic org/eclipse/paho/client/mqttv3/internal/CommsSender.log : Lorg/eclipse/paho/client/mqttv3/logging/Logger;
    //   213: getstatic org/eclipse/paho/client/mqttv3/internal/CommsSender.CLASS_NAME : Ljava/lang/String;
    //   216: ldc 'run'
    //   218: ldc '805'
    //   220: invokeinterface fine : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   225: return
    // Exception table:
    //   from	to	target	type
    //   19	27	200	org/eclipse/paho/client/mqttv3/MqttException
    //   19	27	190	java/lang/Exception
    //   31	83	159	org/eclipse/paho/client/mqttv3/MqttException
    //   31	83	153	java/lang/Exception
    //   88	97	159	org/eclipse/paho/client/mqttv3/MqttException
    //   88	97	153	java/lang/Exception
    //   103	105	159	org/eclipse/paho/client/mqttv3/MqttException
    //   103	105	153	java/lang/Exception
    //   105	113	148	finally
    //   113	120	123	java/io/IOException
    //   113	120	148	finally
    //   124	133	148	finally
    //   133	143	148	finally
    //   149	151	148	finally
    //   151	153	159	org/eclipse/paho/client/mqttv3/MqttException
    //   151	153	153	java/lang/Exception
    //   165	185	159	org/eclipse/paho/client/mqttv3/MqttException
    //   165	185	153	java/lang/Exception
  }
  
  public void start(String paramString) {
    synchronized (this.lifecycle) {
      if (!this.running) {
        this.running = true;
        Thread thread = new Thread();
        this(this, paramString);
        this.sendThread = thread;
        this.sendThread.start();
      } 
      return;
    } 
  }
  
  public void stop() {
    synchronized (this.lifecycle) {
      log.fine(CLASS_NAME, "stop", "800");
      if (this.running) {
        this.running = false;
        boolean bool = Thread.currentThread().equals(this.sendThread);
        if (!bool)
          try {
            this.clientState.notifyQueueLock();
            this.sendThread.join();
          } catch (InterruptedException interruptedException) {} 
      } 
      this.sendThread = null;
      log.fine(CLASS_NAME, "stop", "801");
      return;
    } 
  }
  
  static {
    Class<?> clazz1 = class$0;
    Class<?> clazz2 = clazz1;
    if (clazz1 == null)
      try {
        clazz2 = Class.forName("org.eclipse.paho.client.mqttv3.internal.CommsSender");
        class$0 = clazz2;
      } catch (ClassNotFoundException classNotFoundException) {
        throw new NoClassDefFoundError(classNotFoundException.getMessage());
      }  
    CLASS_NAME = classNotFoundException.getName();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\CommsSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */