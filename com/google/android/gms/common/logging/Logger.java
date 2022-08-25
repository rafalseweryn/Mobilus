package com.google.android.gms.common.logging;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.GmsLogger;
import java.util.Locale;

public class Logger {
  private final String mTag;
  
  private final String zzud;
  
  private final GmsLogger zzvd;
  
  private final int zzve;
  
  private Logger(String paramString1, String paramString2) {
    this.zzud = paramString2;
    this.mTag = paramString1;
    this.zzvd = new GmsLogger(paramString1);
    byte b;
    for (b = 2; 7 >= b && !Log.isLoggable(this.mTag, b); b++);
    this.zzve = b;
  }
  
  public Logger(String paramString, String... paramVarArgs) {
    this(paramString, str);
  }
  
  public void d(String paramString, Throwable paramThrowable, @Nullable Object... paramVarArgs) {
    if (isLoggable(3))
      Log.d(this.mTag, format(paramString, paramVarArgs), paramThrowable); 
  }
  
  public void d(String paramString, @Nullable Object... paramVarArgs) {
    if (isLoggable(3))
      Log.d(this.mTag, format(paramString, paramVarArgs)); 
  }
  
  public void e(String paramString, Throwable paramThrowable, @Nullable Object... paramVarArgs) {
    Log.e(this.mTag, format(paramString, paramVarArgs), paramThrowable);
  }
  
  public void e(String paramString, @Nullable Object... paramVarArgs) {
    Log.e(this.mTag, format(paramString, paramVarArgs));
  }
  
  public String elidePii(Object paramObject) {
    boolean bool = this.zzvd.canLogPii();
    if (paramObject == null)
      return "<NULL>"; 
    paramObject = paramObject.toString().trim();
    return (String)(paramObject.isEmpty() ? "<EMPTY>" : (bool ? paramObject : String.format("<ELLIDED:%s>", new Object[] { Integer.valueOf(paramObject.hashCode()) })));
  }
  
  protected String format(String paramString, @Nullable Object... paramVarArgs) {
    String str = paramString;
    if (paramVarArgs != null) {
      str = paramString;
      if (paramVarArgs.length > 0)
        str = String.format(Locale.US, paramString, paramVarArgs); 
    } 
    return this.zzud.concat(str);
  }
  
  public String getTag() {
    return this.mTag;
  }
  
  public void i(String paramString, Throwable paramThrowable, @Nullable Object... paramVarArgs) {
    Log.i(this.mTag, format(paramString, paramVarArgs), paramThrowable);
  }
  
  public void i(String paramString, @Nullable Object... paramVarArgs) {
    Log.i(this.mTag, format(paramString, paramVarArgs));
  }
  
  public boolean isLoggable(int paramInt) {
    return (this.zzve <= paramInt);
  }
  
  public boolean isPiiLoggable() {
    return this.zzvd.canLogPii();
  }
  
  public void v(String paramString, Throwable paramThrowable, @Nullable Object... paramVarArgs) {
    if (isLoggable(2))
      Log.v(this.mTag, format(paramString, paramVarArgs), paramThrowable); 
  }
  
  public void v(String paramString, @Nullable Object... paramVarArgs) {
    if (isLoggable(2))
      Log.v(this.mTag, format(paramString, paramVarArgs)); 
  }
  
  public void w(String paramString, Throwable paramThrowable, @Nullable Object... paramVarArgs) {
    Log.w(this.mTag, format(paramString, paramVarArgs), paramThrowable);
  }
  
  public void w(String paramString, @Nullable Object... paramVarArgs) {
    Log.w(this.mTag, format(paramString, paramVarArgs));
  }
  
  public void w(Throwable paramThrowable) {
    Log.w(this.mTag, paramThrowable);
  }
  
  public void wtf(String paramString, Throwable paramThrowable, @Nullable Object... paramVarArgs) {
    Log.wtf(this.mTag, format(paramString, paramVarArgs), paramThrowable);
  }
  
  @SuppressLint({"WtfWithoutException"})
  public void wtf(String paramString, @Nullable Object... paramVarArgs) {
    Log.wtf(this.mTag, format(paramString, paramVarArgs));
  }
  
  public void wtf(Throwable paramThrowable) {
    Log.wtf(this.mTag, paramThrowable);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\logging\Logger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */