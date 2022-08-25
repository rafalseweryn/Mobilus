package com.google.android.gms.common.internal;

import android.content.ContentValues;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.util.ThreadUtils;

public final class Preconditions {
  private Preconditions() {
    throw new AssertionError("Uninstantiable");
  }
  
  public static void checkArgument(boolean paramBoolean) {
    if (!paramBoolean)
      throw new IllegalArgumentException(); 
  }
  
  public static void checkArgument(boolean paramBoolean, Object paramObject) {
    if (!paramBoolean)
      throw new IllegalArgumentException(String.valueOf(paramObject)); 
  }
  
  public static void checkArgument(boolean paramBoolean, String paramString, Object... paramVarArgs) {
    if (!paramBoolean)
      throw new IllegalArgumentException(String.format(paramString, paramVarArgs)); 
  }
  
  public static int checkElementIndex(int paramInt1, int paramInt2) {
    return checkElementIndex(paramInt1, paramInt2, "index");
  }
  
  public static int checkElementIndex(int paramInt1, int paramInt2, String paramString) {
    if (paramInt1 < 0 || paramInt1 >= paramInt2) {
      String str;
      if (paramInt1 < 0) {
        paramString = format("%s (%s) must not be negative", new Object[] { paramString, Integer.valueOf(paramInt1) });
      } else {
        StringBuilder stringBuilder;
        if (paramInt2 < 0) {
          stringBuilder = new StringBuilder(26);
          stringBuilder.append("negative size: ");
          stringBuilder.append(paramInt2);
          throw new IllegalArgumentException(stringBuilder.toString());
        } 
        str = format("%s (%s) must be less than size (%s)", new Object[] { stringBuilder, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
      } 
      throw new IndexOutOfBoundsException(str);
    } 
    return paramInt1;
  }
  
  public static void checkHandlerThread(Handler paramHandler) {
    if (Looper.myLooper() != paramHandler.getLooper())
      throw new IllegalStateException("Must be called on the handler thread"); 
  }
  
  public static void checkMainThread(String paramString) {
    if (!ThreadUtils.isMainThread())
      throw new IllegalStateException(paramString); 
  }
  
  public static String checkNotEmpty(String paramString) {
    if (TextUtils.isEmpty(paramString))
      throw new IllegalArgumentException("Given String is empty or null"); 
    return paramString;
  }
  
  public static String checkNotEmpty(String paramString, Object paramObject) {
    if (TextUtils.isEmpty(paramString))
      throw new IllegalArgumentException(String.valueOf(paramObject)); 
    return paramString;
  }
  
  public static void checkNotMainThread() {
    checkNotMainThread("Must not be called on the main application thread");
  }
  
  public static void checkNotMainThread(String paramString) {
    if (ThreadUtils.isMainThread())
      throw new IllegalStateException(paramString); 
  }
  
  @NonNull
  public static <T> T checkNotNull(@Nullable T paramT) {
    if (paramT == null)
      throw new NullPointerException("null reference"); 
    return paramT;
  }
  
  @NonNull
  public static <T> T checkNotNull(T paramT, Object paramObject) {
    if (paramT == null)
      throw new NullPointerException(String.valueOf(paramObject)); 
    return paramT;
  }
  
  public static void checkNotNullIfPresent(String paramString, ContentValues paramContentValues) {
    if (paramContentValues.containsKey(paramString) && paramContentValues.get(paramString) == null)
      throw new IllegalArgumentException(String.valueOf(paramString).concat(" cannot be set to null")); 
  }
  
  public static int checkNotZero(int paramInt) {
    if (paramInt == 0)
      throw new IllegalArgumentException("Given Integer is zero"); 
    return paramInt;
  }
  
  public static int checkNotZero(int paramInt, Object paramObject) {
    if (paramInt == 0)
      throw new IllegalArgumentException(String.valueOf(paramObject)); 
    return paramInt;
  }
  
  public static long checkNotZero(long paramLong) {
    if (paramLong == 0L)
      throw new IllegalArgumentException("Given Long is zero"); 
    return paramLong;
  }
  
  public static long checkNotZero(long paramLong, Object paramObject) {
    if (paramLong == 0L)
      throw new IllegalArgumentException(String.valueOf(paramObject)); 
    return paramLong;
  }
  
  public static int checkPositionIndex(int paramInt1, int paramInt2) {
    return checkPositionIndex(paramInt1, paramInt2, "index");
  }
  
  public static int checkPositionIndex(int paramInt1, int paramInt2, String paramString) {
    if (paramInt1 < 0 || paramInt1 > paramInt2)
      throw new IndexOutOfBoundsException(zza(paramInt1, paramInt2, paramString)); 
    return paramInt1;
  }
  
  public static void checkPositionIndexes(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt1 < 0 || paramInt2 < paramInt1 || paramInt2 > paramInt3) {
      if (paramInt1 < 0 || paramInt1 > paramInt3) {
        String str1 = zza(paramInt1, paramInt3, "start index");
        throw new IndexOutOfBoundsException(str1);
      } 
      if (paramInt2 < 0 || paramInt2 > paramInt3) {
        String str1 = zza(paramInt2, paramInt3, "end index");
        throw new IndexOutOfBoundsException(str1);
      } 
      String str = format("end index (%s) must not be less than start index (%s)", new Object[] { Integer.valueOf(paramInt2), Integer.valueOf(paramInt1) });
      throw new IndexOutOfBoundsException(str);
    } 
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
  
  public static String checkTag(String paramString) {
    if (TextUtils.isEmpty(paramString))
      throw new IllegalArgumentException("Tag must not be empty or null"); 
    if (paramString.length() > 23)
      throw new IllegalArgumentException("Tag must not be greater than 23 chars."); 
    return paramString;
  }
  
  private static String format(String paramString, Object... paramVarArgs) {
    StringBuilder stringBuilder = new StringBuilder(paramString.length() + paramVarArgs.length * 16);
    int i = 0;
    int j = 0;
    while (i < paramVarArgs.length) {
      int k = paramString.indexOf("%s", j);
      if (k != -1) {
        stringBuilder.append(paramString.substring(j, k));
        stringBuilder.append(paramVarArgs[i]);
        j = k + 2;
        i++;
      } 
    } 
    stringBuilder.append(paramString.substring(j));
    if (i < paramVarArgs.length) {
      stringBuilder.append(" [");
      j = i + 1;
      stringBuilder.append(paramVarArgs[i]);
      for (i = j; i < paramVarArgs.length; i++) {
        stringBuilder.append(", ");
        stringBuilder.append(paramVarArgs[i]);
      } 
      stringBuilder.append("]");
    } 
    return stringBuilder.toString();
  }
  
  private static String zza(int paramInt1, int paramInt2, String paramString) {
    StringBuilder stringBuilder;
    if (paramInt1 < 0)
      return format("%s (%s) must not be negative", new Object[] { paramString, Integer.valueOf(paramInt1) }); 
    if (paramInt2 < 0) {
      stringBuilder = new StringBuilder(26);
      stringBuilder.append("negative size: ");
      stringBuilder.append(paramInt2);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    return format("%s (%s) must not be greater than size (%s)", new Object[] { stringBuilder, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\Preconditions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */