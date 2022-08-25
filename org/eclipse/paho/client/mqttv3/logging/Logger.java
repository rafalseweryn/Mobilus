package org.eclipse.paho.client.mqttv3.logging;

import java.util.ResourceBundle;

public interface Logger {
  public static final int CONFIG = 4;
  
  public static final int FINE = 5;
  
  public static final int FINER = 6;
  
  public static final int FINEST = 7;
  
  public static final int INFO = 3;
  
  public static final int SEVERE = 1;
  
  public static final int WARNING = 2;
  
  void config(String paramString1, String paramString2, String paramString3);
  
  void config(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject);
  
  void config(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject, Throwable paramThrowable);
  
  void dumpTrace();
  
  void fine(String paramString1, String paramString2, String paramString3);
  
  void fine(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject);
  
  void fine(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject, Throwable paramThrowable);
  
  void finer(String paramString1, String paramString2, String paramString3);
  
  void finer(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject);
  
  void finer(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject, Throwable paramThrowable);
  
  void finest(String paramString1, String paramString2, String paramString3);
  
  void finest(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject);
  
  void finest(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject, Throwable paramThrowable);
  
  String formatMessage(String paramString, Object[] paramArrayOfObject);
  
  void info(String paramString1, String paramString2, String paramString3);
  
  void info(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject);
  
  void info(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject, Throwable paramThrowable);
  
  void initialise(ResourceBundle paramResourceBundle, String paramString1, String paramString2);
  
  boolean isLoggable(int paramInt);
  
  void log(int paramInt, String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject, Throwable paramThrowable);
  
  void setResourceName(String paramString);
  
  void severe(String paramString1, String paramString2, String paramString3);
  
  void severe(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject);
  
  void severe(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject, Throwable paramThrowable);
  
  void trace(int paramInt, String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject, Throwable paramThrowable);
  
  void warning(String paramString1, String paramString2, String paramString3);
  
  void warning(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject);
  
  void warning(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject, Throwable paramThrowable);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\logging\Logger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */