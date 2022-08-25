package org.eclipse.paho.client.mqttv3.logging;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.MemoryHandler;

public class JSR47Logger implements Logger {
  private String catalogID = null;
  
  private Logger julLogger = null;
  
  private ResourceBundle logMessageCatalog = null;
  
  private String loggerName = null;
  
  private String resourceName = null;
  
  private ResourceBundle traceMessageCatalog = null;
  
  protected static void dumpMemoryTrace47(Logger paramLogger) {
    if (paramLogger != null) {
      Handler[] arrayOfHandler = paramLogger.getHandlers();
      for (byte b = 0;; b++) {
        if (b >= arrayOfHandler.length) {
          dumpMemoryTrace47(paramLogger.getParent());
          break;
        } 
        if (arrayOfHandler[b] instanceof MemoryHandler)
          synchronized (arrayOfHandler[b]) {
            ((MemoryHandler)arrayOfHandler[b]).push();
            return;
          }  
      } 
    } 
  }
  
  private String getResourceMessage(ResourceBundle paramResourceBundle, String paramString) {
    String str;
    try {
      str = paramResourceBundle.getString(paramString);
    } catch (MissingResourceException missingResourceException) {
      str = paramString;
    } 
    return str;
  }
  
  private void logToJsr47(Level paramLevel, String paramString1, String paramString2, String paramString3, ResourceBundle paramResourceBundle, String paramString4, Object[] paramArrayOfObject, Throwable paramThrowable) {
    paramString3 = paramString4;
    if (paramString4.indexOf("=====") == -1)
      paramString3 = MessageFormat.format(getResourceMessage(paramResourceBundle, paramString4), paramArrayOfObject); 
    StringBuffer stringBuffer = new StringBuffer(String.valueOf(this.resourceName));
    stringBuffer.append(": ");
    stringBuffer.append(paramString3);
    LogRecord logRecord = new LogRecord(paramLevel, stringBuffer.toString());
    logRecord.setSourceClassName(paramString1);
    logRecord.setSourceMethodName(paramString2);
    logRecord.setLoggerName(this.loggerName);
    if (paramThrowable != null)
      logRecord.setThrown(paramThrowable); 
    this.julLogger.log(logRecord);
  }
  
  private Level mapJULLevel(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 7:
        return Level.FINEST;
      case 6:
        return Level.FINER;
      case 5:
        return Level.FINE;
      case 4:
        return Level.CONFIG;
      case 3:
        return Level.INFO;
      case 2:
        return Level.WARNING;
      case 1:
        break;
    } 
    return Level.SEVERE;
  }
  
  public void config(String paramString1, String paramString2, String paramString3) {
    log(4, paramString1, paramString2, paramString3, null, null);
  }
  
  public void config(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject) {
    log(4, paramString1, paramString2, paramString3, paramArrayOfObject, null);
  }
  
  public void config(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject, Throwable paramThrowable) {
    log(4, paramString1, paramString2, paramString3, paramArrayOfObject, paramThrowable);
  }
  
  public void dumpTrace() {
    dumpMemoryTrace47(this.julLogger);
  }
  
  public void fine(String paramString1, String paramString2, String paramString3) {
    trace(5, paramString1, paramString2, paramString3, null, null);
  }
  
  public void fine(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject) {
    trace(5, paramString1, paramString2, paramString3, paramArrayOfObject, null);
  }
  
  public void fine(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject, Throwable paramThrowable) {
    trace(5, paramString1, paramString2, paramString3, paramArrayOfObject, paramThrowable);
  }
  
  public void finer(String paramString1, String paramString2, String paramString3) {
    trace(6, paramString1, paramString2, paramString3, null, null);
  }
  
  public void finer(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject) {
    trace(6, paramString1, paramString2, paramString3, paramArrayOfObject, null);
  }
  
  public void finer(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject, Throwable paramThrowable) {
    trace(6, paramString1, paramString2, paramString3, paramArrayOfObject, paramThrowable);
  }
  
  public void finest(String paramString1, String paramString2, String paramString3) {
    trace(7, paramString1, paramString2, paramString3, null, null);
  }
  
  public void finest(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject) {
    trace(7, paramString1, paramString2, paramString3, paramArrayOfObject, null);
  }
  
  public void finest(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject, Throwable paramThrowable) {
    trace(7, paramString1, paramString2, paramString3, paramArrayOfObject, paramThrowable);
  }
  
  public String formatMessage(String paramString, Object[] paramArrayOfObject) {
    try {
      String str = this.logMessageCatalog.getString(paramString);
      paramString = str;
    } catch (MissingResourceException missingResourceException) {}
    return paramString;
  }
  
  public void info(String paramString1, String paramString2, String paramString3) {
    log(3, paramString1, paramString2, paramString3, null, null);
  }
  
  public void info(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject) {
    log(3, paramString1, paramString2, paramString3, paramArrayOfObject, null);
  }
  
  public void info(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject, Throwable paramThrowable) {
    log(3, paramString1, paramString2, paramString3, paramArrayOfObject, paramThrowable);
  }
  
  public void initialise(ResourceBundle paramResourceBundle, String paramString1, String paramString2) {
    this.traceMessageCatalog = this.logMessageCatalog;
    this.resourceName = paramString2;
    this.loggerName = paramString1;
    this.julLogger = Logger.getLogger(this.loggerName);
    this.logMessageCatalog = paramResourceBundle;
    this.traceMessageCatalog = paramResourceBundle;
    this.catalogID = this.logMessageCatalog.getString("0");
  }
  
  public boolean isLoggable(int paramInt) {
    return this.julLogger.isLoggable(mapJULLevel(paramInt));
  }
  
  public void log(int paramInt, String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject, Throwable paramThrowable) {
    Level level = mapJULLevel(paramInt);
    if (this.julLogger.isLoggable(level))
      logToJsr47(level, paramString1, paramString2, this.catalogID, this.logMessageCatalog, paramString3, paramArrayOfObject, paramThrowable); 
  }
  
  public void setResourceName(String paramString) {
    this.resourceName = paramString;
  }
  
  public void severe(String paramString1, String paramString2, String paramString3) {
    log(1, paramString1, paramString2, paramString3, null, null);
  }
  
  public void severe(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject) {
    log(1, paramString1, paramString2, paramString3, paramArrayOfObject, null);
  }
  
  public void severe(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject, Throwable paramThrowable) {
    log(1, paramString1, paramString2, paramString3, paramArrayOfObject, paramThrowable);
  }
  
  public void trace(int paramInt, String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject, Throwable paramThrowable) {
    Level level = mapJULLevel(paramInt);
    if (this.julLogger.isLoggable(level))
      logToJsr47(level, paramString1, paramString2, this.catalogID, this.traceMessageCatalog, paramString3, paramArrayOfObject, paramThrowable); 
  }
  
  public void warning(String paramString1, String paramString2, String paramString3) {
    log(2, paramString1, paramString2, paramString3, null, null);
  }
  
  public void warning(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject) {
    log(2, paramString1, paramString2, paramString3, paramArrayOfObject, null);
  }
  
  public void warning(String paramString1, String paramString2, String paramString3, Object[] paramArrayOfObject, Throwable paramThrowable) {
    log(2, paramString1, paramString2, paramString3, paramArrayOfObject, paramThrowable);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\logging\JSR47Logger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */