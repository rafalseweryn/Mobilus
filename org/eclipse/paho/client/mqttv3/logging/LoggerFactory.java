package org.eclipse.paho.client.mqttv3.logging;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LoggerFactory {
  private static final String CLASS_NAME;
  
  public static final String MQTT_CLIENT_MSG_CAT = "org.eclipse.paho.client.mqttv3.internal.nls.logcat";
  
  private static String jsr47LoggerClassName = "org.eclipse.paho.client.mqttv3.logging.JSR47Logger";
  
  private static String overrideloggerClassName;
  
  static {
    Class<?> clazz1 = class$0;
    Class<?> clazz2 = clazz1;
    if (clazz1 == null)
      try {
        clazz2 = Class.forName("org.eclipse.paho.client.mqttv3.logging.LoggerFactory");
        class$0 = clazz2;
      } catch (ClassNotFoundException classNotFoundException) {
        throw new NoClassDefFoundError(classNotFoundException.getMessage());
      }  
    CLASS_NAME = classNotFoundException.getName();
  }
  
  public static Logger getLogger(String paramString1, String paramString2) {
    String str1 = overrideloggerClassName;
    String str2 = str1;
    if (str1 == null)
      str2 = jsr47LoggerClassName; 
    Logger logger = getLogger(str2, ResourceBundle.getBundle(paramString1), paramString2, null);
    if (logger == null)
      throw new MissingResourceException("Error locating the logging class", CLASS_NAME, paramString2); 
    return logger;
  }
  
  private static Logger getLogger(String paramString1, ResourceBundle paramResourceBundle, String paramString2, String paramString3) {
    try {
      Class<?> clazz = Class.forName(paramString1);
      if (clazz != null) {
        try {
          Logger logger = (Logger)clazz.newInstance();
          logger.initialise(paramResourceBundle, paramString2, paramString3);
        } catch (IllegalAccessException illegalAccessException) {
          return null;
        } catch (InstantiationException instantiationException) {
          return null;
        } catch (ExceptionInInitializerError exceptionInInitializerError) {
          return null;
        } catch (SecurityException securityException) {
          return null;
        } 
      } else {
        clazz = null;
      } 
      return (Logger)clazz;
    } catch (NoClassDefFoundError noClassDefFoundError) {
      return null;
    } catch (ClassNotFoundException classNotFoundException) {
      return null;
    } 
  }
  
  public static String getLoggingProperty(String paramString) {
    try {
      NoClassDefFoundError noClassDefFoundError;
      Class<?> clazz1 = Class.forName("java.util.logging.LogManager");
      Object object = clazz1.getMethod("getLogManager", new Class[0]).invoke(null, null);
      Class<?> clazz2 = class$1;
      Class<?> clazz3 = clazz2;
      if (clazz2 == null)
        try {
          clazz3 = Class.forName("java.lang.String");
          class$1 = clazz3;
        } catch (ClassNotFoundException classNotFoundException) {
          noClassDefFoundError = new NoClassDefFoundError();
          this(classNotFoundException.getMessage());
          throw noClassDefFoundError;
        }  
      String str = (String)clazz1.getMethod("getProperty", new Class[] { (Class)noClassDefFoundError }).invoke(object, new Object[] { classNotFoundException });
    } catch (Exception exception) {
      exception = null;
    } 
    return (String)exception;
  }
  
  public static void setLogger(String paramString) {
    overrideloggerClassName = paramString;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\logging\LoggerFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */