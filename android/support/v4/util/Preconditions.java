package android.support.v4.util;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.text.TextUtils;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class Preconditions {
  public static void checkArgument(boolean paramBoolean) {
    if (!paramBoolean)
      throw new IllegalArgumentException(); 
  }
  
  public static void checkArgument(boolean paramBoolean, Object paramObject) {
    if (!paramBoolean)
      throw new IllegalArgumentException(String.valueOf(paramObject)); 
  }
  
  public static float checkArgumentFinite(float paramFloat, String paramString) {
    if (Float.isNaN(paramFloat)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(" must not be NaN");
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    if (Float.isInfinite(paramFloat)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(" must not be infinite");
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    return paramFloat;
  }
  
  public static float checkArgumentInRange(float paramFloat1, float paramFloat2, float paramFloat3, String paramString) {
    if (Float.isNaN(paramFloat1)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(" must not be NaN");
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    if (paramFloat1 < paramFloat2)
      throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too low)", new Object[] { paramString, Float.valueOf(paramFloat2), Float.valueOf(paramFloat3) })); 
    if (paramFloat1 > paramFloat3)
      throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too high)", new Object[] { paramString, Float.valueOf(paramFloat2), Float.valueOf(paramFloat3) })); 
    return paramFloat1;
  }
  
  public static int checkArgumentInRange(int paramInt1, int paramInt2, int paramInt3, String paramString) {
    if (paramInt1 < paramInt2)
      throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", new Object[] { paramString, Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) })); 
    if (paramInt1 > paramInt3)
      throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", new Object[] { paramString, Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) })); 
    return paramInt1;
  }
  
  public static long checkArgumentInRange(long paramLong1, long paramLong2, long paramLong3, String paramString) {
    if (paramLong1 < paramLong2)
      throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", new Object[] { paramString, Long.valueOf(paramLong2), Long.valueOf(paramLong3) })); 
    if (paramLong1 > paramLong3)
      throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", new Object[] { paramString, Long.valueOf(paramLong2), Long.valueOf(paramLong3) })); 
    return paramLong1;
  }
  
  @IntRange(from = 0L)
  public static int checkArgumentNonnegative(int paramInt) {
    if (paramInt < 0)
      throw new IllegalArgumentException(); 
    return paramInt;
  }
  
  @IntRange(from = 0L)
  public static int checkArgumentNonnegative(int paramInt, String paramString) {
    if (paramInt < 0)
      throw new IllegalArgumentException(paramString); 
    return paramInt;
  }
  
  public static long checkArgumentNonnegative(long paramLong) {
    if (paramLong < 0L)
      throw new IllegalArgumentException(); 
    return paramLong;
  }
  
  public static long checkArgumentNonnegative(long paramLong, String paramString) {
    if (paramLong < 0L)
      throw new IllegalArgumentException(paramString); 
    return paramLong;
  }
  
  public static int checkArgumentPositive(int paramInt, String paramString) {
    if (paramInt <= 0)
      throw new IllegalArgumentException(paramString); 
    return paramInt;
  }
  
  public static float[] checkArrayElementsInRange(float[] paramArrayOffloat, float paramFloat1, float paramFloat2, String paramString) {
    StringBuilder stringBuilder1;
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(paramString);
    stringBuilder2.append(" must not be null");
    checkNotNull(paramArrayOffloat, stringBuilder2.toString());
    for (byte b = 0; b < paramArrayOffloat.length; b++) {
      float f = paramArrayOffloat[b];
      if (Float.isNaN(f)) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramString);
        stringBuilder1.append("[");
        stringBuilder1.append(b);
        stringBuilder1.append("] must not be NaN");
        throw new IllegalArgumentException(stringBuilder1.toString());
      } 
      if (f < paramFloat1)
        throw new IllegalArgumentException(String.format(Locale.US, "%s[%d] is out of range of [%f, %f] (too low)", new Object[] { paramString, Integer.valueOf(b), Float.valueOf(paramFloat1), Float.valueOf(paramFloat2) })); 
      if (f > paramFloat2)
        throw new IllegalArgumentException(String.format(Locale.US, "%s[%d] is out of range of [%f, %f] (too high)", new Object[] { paramString, Integer.valueOf(b), Float.valueOf(paramFloat1), Float.valueOf(paramFloat2) })); 
    } 
    return (float[])stringBuilder1;
  }
  
  public static <T> T[] checkArrayElementsNotNull(T[] paramArrayOfT, String paramString) {
    StringBuilder stringBuilder;
    if (paramArrayOfT == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(" must not be null");
      throw new NullPointerException(stringBuilder.toString());
    } 
    for (byte b = 0; b < stringBuilder.length; b++) {
      if (stringBuilder[b] == null)
        throw new NullPointerException(String.format(Locale.US, "%s[%d] must not be null", new Object[] { paramString, Integer.valueOf(b) })); 
    } 
    return (T[])stringBuilder;
  }
  
  @NonNull
  public static <C extends Collection<T>, T> C checkCollectionElementsNotNull(C paramC, String paramString) {
    StringBuilder stringBuilder;
    if (paramC == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(" must not be null");
      throw new NullPointerException(stringBuilder.toString());
    } 
    long l = 0L;
    Iterator iterator = stringBuilder.iterator();
    while (iterator.hasNext()) {
      if (iterator.next() == null)
        throw new NullPointerException(String.format(Locale.US, "%s[%d] must not be null", new Object[] { paramString, Long.valueOf(l) })); 
      l++;
    } 
    return (C)stringBuilder;
  }
  
  public static <T> Collection<T> checkCollectionNotEmpty(Collection<T> paramCollection, String paramString) {
    StringBuilder stringBuilder;
    if (paramCollection == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(" must not be null");
      throw new NullPointerException(stringBuilder.toString());
    } 
    if (stringBuilder.isEmpty()) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(" is empty");
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    return (Collection<T>)stringBuilder;
  }
  
  public static int checkFlagsArgument(int paramInt1, int paramInt2) {
    if ((paramInt1 & paramInt2) != paramInt1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Requested flags 0x");
      stringBuilder.append(Integer.toHexString(paramInt1));
      stringBuilder.append(", but only 0x");
      stringBuilder.append(Integer.toHexString(paramInt2));
      stringBuilder.append(" are allowed");
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    return paramInt1;
  }
  
  @NonNull
  public static <T> T checkNotNull(T paramT) {
    if (paramT == null)
      throw new NullPointerException(); 
    return paramT;
  }
  
  @NonNull
  public static <T> T checkNotNull(T paramT, Object paramObject) {
    if (paramT == null)
      throw new NullPointerException(String.valueOf(paramObject)); 
    return paramT;
  }
  
  public static void checkState(boolean paramBoolean) {
    checkState(paramBoolean, null);
  }
  
  public static void checkState(boolean paramBoolean, String paramString) {
    if (!paramBoolean)
      throw new IllegalStateException(paramString); 
  }
  
  @NonNull
  public static <T extends CharSequence> T checkStringNotEmpty(T paramT) {
    if (TextUtils.isEmpty((CharSequence)paramT))
      throw new IllegalArgumentException(); 
    return paramT;
  }
  
  @NonNull
  public static <T extends CharSequence> T checkStringNotEmpty(T paramT, Object paramObject) {
    if (TextUtils.isEmpty((CharSequence)paramT))
      throw new IllegalArgumentException(String.valueOf(paramObject)); 
    return paramT;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v\\util\Preconditions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */