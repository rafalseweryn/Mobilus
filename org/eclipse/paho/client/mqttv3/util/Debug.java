package org.eclipse.paho.client.mqttv3.util;

import java.util.Enumeration;
import java.util.Properties;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class Debug {
  private static final String CLASS_NAME;
  
  private static final String lineSep;
  
  private static final Logger log = LoggerFactory.getLogger("org.eclipse.paho.client.mqttv3.internal.nls.logcat", CLASS_NAME);
  
  private static final String separator = "==============";
  
  private String clientID;
  
  private ClientComms comms;
  
  static {
    lineSep = System.getProperty("line.separator", "\n");
  }
  
  public Debug(String paramString, ClientComms paramClientComms) {
    this.clientID = paramString;
    this.comms = paramClientComms;
    log.setResourceName(paramString);
  }
  
  public static String dumpProperties(Properties paramProperties, String paramString) {
    StringBuffer stringBuffer1 = new StringBuffer();
    Enumeration<?> enumeration = paramProperties.propertyNames();
    StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(lineSep));
    stringBuffer2.append("==============");
    stringBuffer2.append(" ");
    stringBuffer2.append(paramString);
    stringBuffer2.append(" ");
    stringBuffer2.append("==============");
    stringBuffer2.append(lineSep);
    stringBuffer1.append(stringBuffer2.toString());
    while (true) {
      StringBuffer stringBuffer3;
      if (!enumeration.hasMoreElements()) {
        stringBuffer3 = new StringBuffer("==========================================");
        stringBuffer3.append(lineSep);
        stringBuffer1.append(stringBuffer3.toString());
        return stringBuffer1.toString();
      } 
      String str = (String)enumeration.nextElement();
      StringBuffer stringBuffer4 = new StringBuffer(String.valueOf(left(str, 28, ' ')));
      stringBuffer4.append(":  ");
      stringBuffer4.append(stringBuffer3.get(str));
      stringBuffer4.append(lineSep);
      stringBuffer1.append(stringBuffer4.toString());
    } 
  }
  
  public static String left(String paramString, int paramInt, char paramChar) {
    if (paramString.length() >= paramInt)
      return paramString; 
    StringBuffer stringBuffer = new StringBuffer(paramInt);
    stringBuffer.append(paramString);
    paramInt -= paramString.length();
    while (true) {
      if (--paramInt < 0)
        return stringBuffer.toString(); 
      stringBuffer.append(paramChar);
    } 
  }
  
  public void dumpBaseDebug() {
    dumpVersion();
    dumpSystemProperties();
    dumpMemoryTrace();
  }
  
  public void dumpClientComms() {
    if (this.comms != null) {
      Properties properties = this.comms.getDebug();
      Logger logger = log;
      String str = CLASS_NAME;
      StringBuffer stringBuffer = new StringBuffer(String.valueOf(this.clientID));
      stringBuffer.append(" : ClientComms");
      logger.fine(str, "dumpClientComms", dumpProperties(properties, stringBuffer.toString()).toString());
    } 
  }
  
  public void dumpClientDebug() {
    dumpClientComms();
    dumpConOptions();
    dumpClientState();
    dumpBaseDebug();
  }
  
  public void dumpClientState() {
    if (this.comms != null && this.comms.getClientState() != null) {
      Properties properties = this.comms.getClientState().getDebug();
      Logger logger = log;
      String str = CLASS_NAME;
      StringBuffer stringBuffer = new StringBuffer(String.valueOf(this.clientID));
      stringBuffer.append(" : ClientState");
      logger.fine(str, "dumpClientState", dumpProperties(properties, stringBuffer.toString()).toString());
    } 
  }
  
  public void dumpConOptions() {
    if (this.comms != null) {
      Properties properties = this.comms.getConOptions().getDebug();
      Logger logger = log;
      String str = CLASS_NAME;
      StringBuffer stringBuffer = new StringBuffer(String.valueOf(this.clientID));
      stringBuffer.append(" : Connect Options");
      logger.fine(str, "dumpConOptions", dumpProperties(properties, stringBuffer.toString()).toString());
    } 
  }
  
  protected void dumpMemoryTrace() {
    log.dumpTrace();
  }
  
  public void dumpSystemProperties() {
    Properties properties = System.getProperties();
    log.fine(CLASS_NAME, "dumpSystemProperties", dumpProperties(properties, "SystemProperties").toString());
  }
  
  protected void dumpVersion() {
    StringBuffer stringBuffer1 = new StringBuffer();
    StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(lineSep));
    stringBuffer2.append("==============");
    stringBuffer2.append(" Version Info ");
    stringBuffer2.append("==============");
    stringBuffer2.append(lineSep);
    stringBuffer1.append(stringBuffer2.toString());
    stringBuffer2 = new StringBuffer(String.valueOf(left("Version", 20, ' ')));
    stringBuffer2.append(":  ");
    stringBuffer2.append(ClientComms.VERSION);
    stringBuffer2.append(lineSep);
    stringBuffer1.append(stringBuffer2.toString());
    stringBuffer2 = new StringBuffer(String.valueOf(left("Build Level", 20, ' ')));
    stringBuffer2.append(":  ");
    stringBuffer2.append(ClientComms.BUILD_LEVEL);
    stringBuffer2.append(lineSep);
    stringBuffer1.append(stringBuffer2.toString());
    stringBuffer2 = new StringBuffer("==========================================");
    stringBuffer2.append(lineSep);
    stringBuffer1.append(stringBuffer2.toString());
    log.fine(CLASS_NAME, "dumpVersion", stringBuffer1.toString());
  }
  
  static {
    Class<?> clazz1 = class$0;
    Class<?> clazz2 = clazz1;
    if (clazz1 == null)
      try {
        clazz2 = Class.forName("org.eclipse.paho.client.mqttv3.internal.ClientComms");
        class$0 = clazz2;
      } catch (ClassNotFoundException classNotFoundException) {
        throw new NoClassDefFoundError(classNotFoundException.getMessage());
      }  
    CLASS_NAME = classNotFoundException.getName();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv\\util\Debug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */