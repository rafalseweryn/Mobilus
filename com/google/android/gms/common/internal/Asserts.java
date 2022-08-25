package com.google.android.gms.common.internal;

import android.os.Looper;
import android.util.Log;

public final class Asserts {
  private Asserts() {
    throw new AssertionError("Uninstantiable");
  }
  
  public static void checkMainThread(String paramString) {
    if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
      String str1 = String.valueOf(Thread.currentThread());
      String str2 = String.valueOf(Looper.getMainLooper().getThread());
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 57 + String.valueOf(str2).length());
      stringBuilder.append("checkMainThread: current thread ");
      stringBuilder.append(str1);
      stringBuilder.append(" IS NOT the main thread ");
      stringBuilder.append(str2);
      stringBuilder.append("!");
      Log.e("Asserts", stringBuilder.toString());
      throw new IllegalStateException(paramString);
    } 
  }
  
  public static void checkNotMainThread(String paramString) {
    if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
      String str1 = String.valueOf(Thread.currentThread());
      String str2 = String.valueOf(Looper.getMainLooper().getThread());
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 56 + String.valueOf(str2).length());
      stringBuilder.append("checkNotMainThread: current thread ");
      stringBuilder.append(str1);
      stringBuilder.append(" IS the main thread ");
      stringBuilder.append(str2);
      stringBuilder.append("!");
      Log.e("Asserts", stringBuilder.toString());
      throw new IllegalStateException(paramString);
    } 
  }
  
  public static void checkNotNull(Object paramObject) {
    if (paramObject == null)
      throw new IllegalArgumentException("null reference"); 
  }
  
  public static void checkNotNull(Object paramObject1, Object paramObject2) {
    if (paramObject1 == null)
      throw new IllegalArgumentException(String.valueOf(paramObject2)); 
  }
  
  public static void checkNull(Object paramObject) {
    if (paramObject != null)
      throw new IllegalArgumentException("non-null reference"); 
  }
  
  public static void checkState(boolean paramBoolean) {
    if (!paramBoolean)
      throw new IllegalStateException(); 
  }
  
  public static void checkState(boolean paramBoolean, Object paramObject) {
    if (!paramBoolean)
      throw new IllegalStateException(String.valueOf(paramObject)); 
  }
  
  public static void checkState(boolean paramBoolean, String paramString, Object... paramVarArgs) {
    if (!paramBoolean)
      throw new IllegalStateException(String.format(paramString, paramVarArgs)); 
  }
  
  public static void fail(Object paramObject) {
    throw new IllegalStateException(String.valueOf(paramObject));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\Asserts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */