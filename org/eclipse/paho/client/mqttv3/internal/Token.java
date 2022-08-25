package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSuback;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class Token {
  private static final String CLASS_NAME;
  
  private static final Logger log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", CLASS_NAME);
  
  private IMqttActionListener callback = null;
  
  private IMqttAsyncClient client = null;
  
  private volatile boolean completed = false;
  
  private MqttException exception = null;
  
  private String key;
  
  protected MqttMessage message = null;
  
  private int messageID = 0;
  
  private boolean notified = false;
  
  private boolean pendingComplete = false;
  
  private MqttWireMessage response = null;
  
  private Object responseLock = new Object();
  
  private boolean sent = false;
  
  private Object sentLock = new Object();
  
  private String[] topics = null;
  
  private Object userContext = null;
  
  public Token(String paramString) {
    log.setResourceName(paramString);
  }
  
  public boolean checkResult() throws MqttException {
    if (getException() != null)
      throw getException(); 
    return true;
  }
  
  public IMqttActionListener getActionCallback() {
    return this.callback;
  }
  
  public IMqttAsyncClient getClient() {
    return this.client;
  }
  
  public MqttException getException() {
    return this.exception;
  }
  
  public int[] getGrantedQos() {
    int[] arrayOfInt = new int[0];
    if (this.response instanceof MqttSuback)
      arrayOfInt = ((MqttSuback)this.response).getGrantedQos(); 
    return arrayOfInt;
  }
  
  public String getKey() {
    return this.key;
  }
  
  public MqttMessage getMessage() {
    return this.message;
  }
  
  public int getMessageID() {
    return this.messageID;
  }
  
  public MqttWireMessage getResponse() {
    return this.response;
  }
  
  public boolean getSessionPresent() {
    boolean bool;
    if (this.response instanceof MqttConnack) {
      bool = ((MqttConnack)this.response).getSessionPresent();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String[] getTopics() {
    return this.topics;
  }
  
  public Object getUserContext() {
    return this.userContext;
  }
  
  public MqttWireMessage getWireMessage() {
    return this.response;
  }
  
  public boolean isComplete() {
    return this.completed;
  }
  
  protected boolean isCompletePending() {
    return this.pendingComplete;
  }
  
  protected boolean isInUse() {
    return (getClient() != null && !isComplete());
  }
  
  public boolean isNotified() {
    return this.notified;
  }
  
  protected void markComplete(MqttWireMessage paramMqttWireMessage, MqttException paramMqttException) {
    log.fine(CLASS_NAME, "markComplete", "404", new Object[] { getKey(), paramMqttWireMessage, paramMqttException });
    synchronized (this.responseLock) {
      if (paramMqttWireMessage instanceof org.eclipse.paho.client.mqttv3.internal.wire.MqttAck)
        this.message = null; 
      this.pendingComplete = true;
      this.response = paramMqttWireMessage;
      this.exception = paramMqttException;
      return;
    } 
  }
  
  protected void notifyComplete() {
    log.fine(CLASS_NAME, "notifyComplete", "404", new Object[] { getKey(), this.response, this.exception });
    synchronized (this.responseLock) {
      if (this.exception == null && this.pendingComplete) {
        this.completed = true;
        this.pendingComplete = false;
      } else {
        this.pendingComplete = false;
      } 
      this.responseLock.notifyAll();
      synchronized (this.sentLock) {
        this.sent = true;
        this.sentLock.notifyAll();
        return;
      } 
    } 
  }
  
  protected void notifySent() {
    log.fine(CLASS_NAME, "notifySent", "403", new Object[] { getKey() });
    synchronized (this.responseLock) {
      this.response = null;
      this.completed = false;
      synchronized (this.sentLock) {
        this.sent = true;
        this.sentLock.notifyAll();
        return;
      } 
    } 
  }
  
  public void reset() throws MqttException {
    if (isInUse())
      throw new MqttException(32201); 
    log.fine(CLASS_NAME, "reset", "410", new Object[] { getKey() });
    this.client = null;
    this.completed = false;
    this.response = null;
    this.sent = false;
    this.exception = null;
    this.userContext = null;
  }
  
  public void setActionCallback(IMqttActionListener paramIMqttActionListener) {
    this.callback = paramIMqttActionListener;
  }
  
  protected void setClient(IMqttAsyncClient paramIMqttAsyncClient) {
    this.client = paramIMqttAsyncClient;
  }
  
  public void setException(MqttException paramMqttException) {
    synchronized (this.responseLock) {
      this.exception = paramMqttException;
      return;
    } 
  }
  
  public void setKey(String paramString) {
    this.key = paramString;
  }
  
  public void setMessage(MqttMessage paramMqttMessage) {
    this.message = paramMqttMessage;
  }
  
  public void setMessageID(int paramInt) {
    this.messageID = paramInt;
  }
  
  public void setNotified(boolean paramBoolean) {
    this.notified = paramBoolean;
  }
  
  public void setTopics(String[] paramArrayOfString) {
    this.topics = paramArrayOfString;
  }
  
  public void setUserContext(Object paramObject) {
    this.userContext = paramObject;
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("key=");
    stringBuffer.append(getKey());
    stringBuffer.append(" ,topics=");
    if (getTopics() != null)
      for (byte b = 0; b < (getTopics()).length; b++) {
        stringBuffer.append(getTopics()[b]);
        stringBuffer.append(", ");
      }  
    stringBuffer.append(" ,usercontext=");
    stringBuffer.append(getUserContext());
    stringBuffer.append(" ,isComplete=");
    stringBuffer.append(isComplete());
    stringBuffer.append(" ,isNotified=");
    stringBuffer.append(isNotified());
    stringBuffer.append(" ,exception=");
    stringBuffer.append(getException());
    stringBuffer.append(" ,actioncallback=");
    stringBuffer.append(getActionCallback());
    return stringBuffer.toString();
  }
  
  public void waitForCompletion() throws MqttException {
    waitForCompletion(-1L);
  }
  
  public void waitForCompletion(long paramLong) throws MqttException {
    log.fine(CLASS_NAME, "waitForCompletion", "407", new Object[] { getKey(), new Long(paramLong), this });
    if (waitForResponse(paramLong) == null && !this.completed) {
      log.fine(CLASS_NAME, "waitForCompletion", "406", new Object[] { getKey(), this });
      this.exception = new MqttException(32000);
      throw this.exception;
    } 
    checkResult();
  }
  
  protected MqttWireMessage waitForResponse() throws MqttException {
    return waitForResponse(-1L);
  }
  
  protected MqttWireMessage waitForResponse(long paramLong) throws MqttException {
    synchronized (this.responseLock) {
      String str3;
      Logger logger = log;
      String str1 = CLASS_NAME;
      String str2 = getKey();
      Long long_ = new Long();
      this(paramLong);
      Boolean bool1 = new Boolean();
      this(this.sent);
      Boolean bool2 = new Boolean();
      this(this.completed);
      if (this.exception == null) {
        str3 = "false";
      } else {
        str3 = "true";
      } 
      MqttWireMessage mqttWireMessage = this.response;
      MqttException mqttException = this.exception;
      logger.fine(str1, "waitForResponse", "400", new Object[] { str2, long_, bool1, bool2, str3, mqttWireMessage, this }, (Throwable)mqttException);
      while (!this.completed) {
        MqttException mqttException1 = this.exception;
        if (mqttException1 == null)
          try {
            Logger logger1 = log;
            String str = CLASS_NAME;
            str1 = getKey();
            Long long_1 = new Long();
            this(paramLong);
            logger1.fine(str, "waitForResponse", "408", new Object[] { str1, long_1 });
            if (paramLong <= 0L) {
              this.responseLock.wait();
            } else {
              this.responseLock.wait(paramLong);
            } 
          } catch (InterruptedException interruptedException) {
            mqttException1 = new MqttException();
            this(interruptedException);
            this.exception = mqttException1;
          }  
        if (!this.completed) {
          if (this.exception != null) {
            log.fine(CLASS_NAME, "waitForResponse", "401", null, (Throwable)this.exception);
            throw this.exception;
          } 
          if (paramLong > 0L)
            break; 
        } 
      } 
      log.fine(CLASS_NAME, "waitForResponse", "402", new Object[] { getKey(), this.response });
      return this.response;
    } 
  }
  
  public void waitUntilSent() throws MqttException {
    synchronized (this.sentLock) {
      synchronized (this.responseLock) {
        if (this.exception != null)
          throw this.exception; 
        while (true) {
          if (this.sent) {
            if (!this.sent) {
              if (this.exception == null)
                throw ExceptionHelper.createMqttException(6); 
              throw this.exception;
            } 
            return;
          } 
          try {
            log.fine(CLASS_NAME, "waitUntilSent", "409", new Object[] { getKey() });
            this.sentLock.wait();
          } catch (InterruptedException interruptedException) {}
        } 
      } 
    } 
  }
  
  static {
    Class<?> clazz1 = class$0;
    Class<?> clazz2 = clazz1;
    if (clazz1 == null)
      try {
        clazz2 = Class.forName("org.eclipse.paho.client.mqttv3.internal.Token");
        class$0 = clazz2;
      } catch (ClassNotFoundException classNotFoundException) {
        throw new NoClassDefFoundError(classNotFoundException.getMessage());
      }  
    CLASS_NAME = classNotFoundException.getName();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\Token.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */