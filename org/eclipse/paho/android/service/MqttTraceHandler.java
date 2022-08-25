package org.eclipse.paho.android.service;

public interface MqttTraceHandler {
  void traceDebug(String paramString1, String paramString2);
  
  void traceError(String paramString1, String paramString2);
  
  void traceException(String paramString1, String paramString2, Exception paramException);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\android\service\MqttTraceHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */