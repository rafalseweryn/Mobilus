package org.eclipse.paho.client.mqttv3.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class SimpleLogFormatter extends Formatter {
  private static final String LS = System.getProperty("line.separator");
  
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
  
  public String format(LogRecord paramLogRecord) {
    StringBuffer stringBuffer1 = new StringBuffer();
    stringBuffer1.append(paramLogRecord.getLevel().getName());
    stringBuffer1.append("\t");
    StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(MessageFormat.format("{0, date, yy-MM-dd} {0, time, kk:mm:ss.SSSS} ", new Object[] { new Date(paramLogRecord.getMillis()) })));
    stringBuffer2.append("\t");
    stringBuffer1.append(stringBuffer2.toString());
    String str2 = paramLogRecord.getSourceClassName();
    String str1 = "";
    if (str2 != null) {
      int i = str2.length();
      if (i > 20) {
        str1 = paramLogRecord.getSourceClassName().substring(i - 19);
      } else {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2);
        stringBuffer.append(new char[] { ' ' }, 0, 1);
        str1 = stringBuffer.toString();
      } 
    } 
    stringBuffer1.append(str1);
    stringBuffer1.append("\t");
    stringBuffer1.append(" ");
    stringBuffer1.append(left(paramLogRecord.getSourceMethodName(), 23, ' '));
    stringBuffer1.append("\t");
    stringBuffer1.append(paramLogRecord.getThreadID());
    stringBuffer1.append("\t");
    stringBuffer1.append(formatMessage(paramLogRecord));
    stringBuffer1.append(LS);
    if (paramLogRecord.getThrown() != null) {
      PrintWriter printWriter;
      stringBuffer1.append("Throwable occurred: ");
      Throwable throwable = paramLogRecord.getThrown();
      str1 = null;
      try {
        StringWriter stringWriter = new StringWriter();
        this();
        PrintWriter printWriter1 = new PrintWriter();
        this(stringWriter);
        try {
          throwable.printStackTrace(printWriter1);
          stringBuffer1.append(stringWriter.toString());
        } finally {
          stringWriter = null;
        } 
      } finally {}
      if (printWriter != null)
        try {
          printWriter.close();
        } catch (Exception exception) {} 
      throw paramLogRecord;
    } 
    return stringBuffer1.toString();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\logging\SimpleLogFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */