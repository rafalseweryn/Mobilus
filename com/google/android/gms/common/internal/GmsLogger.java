package com.google.android.gms.common.internal;

import android.util.Log;

public final class GmsLogger {
  public static final int MAX_PII_TAG_LENGTH = 15;
  
  public static final int MAX_TAG_LENGTH = 23;
  
  private static final String zzub;
  
  private final String zzuc;
  
  private final String zzud;
  
  public GmsLogger(String paramString) {
    this(paramString, null);
  }
  
  public GmsLogger(String paramString1, String paramString2) {
    boolean bool;
    Preconditions.checkNotNull(paramString1, "log tag cannot be null");
    if (paramString1.length() <= 23) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "tag \"%s\" is longer than the %d character maximum", new Object[] { paramString1, Integer.valueOf(23) });
    this.zzuc = paramString1;
    if (paramString2 == null || paramString2.length() <= 0) {
      this.zzud = null;
      return;
    } 
    this.zzud = paramString2;
  }
  
  public static boolean isBuildPiiEnabled() {
    return false;
  }
  
  private final String zza(String paramString, Object... paramVarArgs) {
    paramString = String.format(paramString, paramVarArgs);
    return (this.zzud == null) ? paramString : this.zzud.concat(paramString);
  }
  
  private final String zzl(String paramString) {
    return (this.zzud == null) ? paramString : this.zzud.concat(paramString);
  }
  
  public final boolean canLog(int paramInt) {
    return Log.isLoggable(this.zzuc, paramInt);
  }
  
  public final boolean canLogPii() {
    return false;
  }
  
  public final void d(String paramString1, String paramString2) {
    if (canLog(3))
      Log.d(paramString1, zzl(paramString2)); 
  }
  
  public final void d(String paramString1, String paramString2, Throwable paramThrowable) {
    if (canLog(3))
      Log.d(paramString1, zzl(paramString2), paramThrowable); 
  }
  
  public final void dfmt(String paramString1, String paramString2, Object... paramVarArgs) {
    if (canLog(3))
      Log.d(paramString1, zza(paramString2, paramVarArgs)); 
  }
  
  public final void e(String paramString1, String paramString2) {
    if (canLog(6))
      Log.e(paramString1, zzl(paramString2)); 
  }
  
  public final void e(String paramString1, String paramString2, Throwable paramThrowable) {
    if (canLog(6))
      Log.e(paramString1, zzl(paramString2), paramThrowable); 
  }
  
  public final void efmt(String paramString1, String paramString2, Object... paramVarArgs) {
    if (canLog(6))
      Log.e(paramString1, zza(paramString2, paramVarArgs)); 
  }
  
  public final String getTag() {
    return this.zzuc;
  }
  
  public final void i(String paramString1, String paramString2) {
    if (canLog(4))
      Log.i(paramString1, zzl(paramString2)); 
  }
  
  public final void i(String paramString1, String paramString2, Throwable paramThrowable) {
    if (canLog(4))
      Log.i(paramString1, zzl(paramString2), paramThrowable); 
  }
  
  public final void ifmt(String paramString1, String paramString2, Object... paramVarArgs) {
    if (canLog(4))
      Log.i(paramString1, zza(paramString2, paramVarArgs)); 
  }
  
  public final void pii(String paramString1, String paramString2) {
    if (canLogPii()) {
      paramString1 = String.valueOf(paramString1);
      String str = String.valueOf(" PII_LOG");
      if (str.length() != 0) {
        paramString1 = paramString1.concat(str);
      } else {
        paramString1 = new String(paramString1);
      } 
      Log.i(paramString1, zzl(paramString2));
    } 
  }
  
  public final void pii(String paramString1, String paramString2, Throwable paramThrowable) {
    if (canLogPii()) {
      String str = String.valueOf(paramString1);
      paramString1 = String.valueOf(" PII_LOG");
      if (paramString1.length() != 0) {
        paramString1 = str.concat(paramString1);
      } else {
        paramString1 = new String(str);
      } 
      Log.i(paramString1, zzl(paramString2), paramThrowable);
    } 
  }
  
  public final void piifmt(String paramString1, String paramString2, Object... paramVarArgs) {
    if (canLogPii()) {
      paramString1 = String.valueOf(paramString1);
      String str = String.valueOf(" PII_LOG");
      if (str.length() != 0) {
        paramString1 = paramString1.concat(str);
      } else {
        paramString1 = new String(paramString1);
      } 
      Log.i(paramString1, zza(paramString2, paramVarArgs));
    } 
  }
  
  public final void v(String paramString1, String paramString2) {
    if (canLog(2))
      Log.v(paramString1, zzl(paramString2)); 
  }
  
  public final void v(String paramString1, String paramString2, Throwable paramThrowable) {
    if (canLog(2))
      Log.v(paramString1, zzl(paramString2), paramThrowable); 
  }
  
  public final void vfmt(String paramString1, String paramString2, Object... paramVarArgs) {
    if (canLog(2))
      Log.v(paramString1, zza(paramString2, paramVarArgs)); 
  }
  
  public final void w(String paramString1, String paramString2) {
    if (canLog(5))
      Log.w(paramString1, zzl(paramString2)); 
  }
  
  public final void w(String paramString1, String paramString2, Throwable paramThrowable) {
    if (canLog(5))
      Log.w(paramString1, zzl(paramString2), paramThrowable); 
  }
  
  public final void wfmt(String paramString1, String paramString2, Object... paramVarArgs) {
    if (canLog(5))
      Log.w(this.zzuc, zza(paramString2, paramVarArgs)); 
  }
  
  public final GmsLogger withMessagePrefix(String paramString) {
    return new GmsLogger(this.zzuc, paramString);
  }
  
  public final void wtf(String paramString1, String paramString2, Throwable paramThrowable) {
    if (canLog(7)) {
      Log.e(paramString1, zzl(paramString2), paramThrowable);
      Log.wtf(paramString1, zzl(paramString2), paramThrowable);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\GmsLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */