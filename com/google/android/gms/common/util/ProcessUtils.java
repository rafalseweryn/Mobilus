package com.google.android.gms.common.util;

import android.os.Binder;
import android.os.Process;
import android.os.StrictMode;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import javax.annotation.Nullable;

public class ProcessUtils {
  private static String zzaai;
  
  private static int zzaaj;
  
  @Nullable
  public static String getCallingProcessName() {
    int i = Binder.getCallingPid();
    return (i == zzde()) ? getMyProcessName() : zzl(i);
  }
  
  @Nullable
  public static String getMyProcessName() {
    if (zzaai == null)
      zzaai = zzl(zzde()); 
    return zzaai;
  }
  
  public static boolean hasSystemGroups() throws SystemGroupsNotAvailableException {
    BufferedReader bufferedReader1 = null;
    BufferedReader bufferedReader2 = null;
    BufferedReader bufferedReader3 = bufferedReader2;
    try {
      int i = zzde();
      bufferedReader3 = bufferedReader2;
      StringBuilder stringBuilder = new StringBuilder();
      bufferedReader3 = bufferedReader2;
      this(24);
      bufferedReader3 = bufferedReader2;
      stringBuilder.append("/proc/");
      bufferedReader3 = bufferedReader2;
      stringBuilder.append(i);
      bufferedReader3 = bufferedReader2;
      stringBuilder.append("/status");
      bufferedReader3 = bufferedReader2;
    } catch (IOException null) {
    
    } finally {
      Exception exception1;
      exception = null;
      bufferedReader2 = bufferedReader3;
    } 
    bufferedReader3 = bufferedReader2;
    SystemGroupsNotAvailableException systemGroupsNotAvailableException = new SystemGroupsNotAvailableException();
    bufferedReader3 = bufferedReader2;
    this("Unable to access /proc/pid/status.", exception);
    Exception exception;
    bufferedReader3 = bufferedReader2;
    throw systemGroupsNotAvailableException;
  }
  
  private static int zzde() {
    if (zzaaj == 0)
      zzaaj = Process.myPid(); 
    return zzaaj;
  }
  
  private static boolean zzk(BufferedReader paramBufferedReader) throws IOException, SystemGroupsNotAvailableException {
    while (true) {
      String str = paramBufferedReader.readLine();
      if (str != null) {
        str = str.trim();
        if (str.startsWith("Groups:")) {
          String[] arrayOfString = str.substring(7).trim().split("\\s", -1);
          int i = arrayOfString.length;
          byte b = 0;
          while (true) {
            if (b < i) {
              str = arrayOfString[b];
              try {
                long l = Long.parseLong(str);
                if (l >= 1000L && l < 2000L)
                  return true; 
              } catch (NumberFormatException numberFormatException) {}
              b++;
              continue;
            } 
            return false;
          } 
        } 
        continue;
      } 
      throw new SystemGroupsNotAvailableException("Missing Groups entry from proc/pid/status.");
    } 
  }
  
  @Nullable
  private static String zzl(int paramInt) {
    BufferedReader bufferedReader;
    String str = null;
    if (paramInt <= 0)
      return null; 
    try {
      Exception exception;
      StringBuilder stringBuilder = new StringBuilder();
      this(25);
      stringBuilder.append("/proc/");
      stringBuilder.append(paramInt);
      stringBuilder.append("/cmdline");
      BufferedReader bufferedReader1 = zzm(stringBuilder.toString());
      try {
        return str;
      } catch (IOException iOException) {
      
      } finally {
        Exception exception1 = null;
        bufferedReader = bufferedReader1;
      } 
      IOUtils.closeQuietly(bufferedReader);
      throw exception;
    } catch (IOException iOException) {
    
    } finally {
      IOUtils.closeQuietly(bufferedReader);
    } 
    IOUtils.closeQuietly((Closeable)SYNTHETIC_LOCAL_VARIABLE_2);
    return null;
  }
  
  private static BufferedReader zzm(String paramString) throws IOException {
    StrictMode.ThreadPolicy threadPolicy = StrictMode.allowThreadDiskReads();
    try {
      FileReader fileReader = new FileReader();
      this(paramString);
      return new BufferedReader(fileReader);
    } finally {
      StrictMode.setThreadPolicy(threadPolicy);
    } 
  }
  
  public static class SystemGroupsNotAvailableException extends Exception {
    SystemGroupsNotAvailableException(String param1String) {
      super(param1String);
    }
    
    SystemGroupsNotAvailableException(String param1String, Throwable param1Throwable) {
      super(param1String, param1Throwable);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\ProcessUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */