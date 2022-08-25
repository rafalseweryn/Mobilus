package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.Objects;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

@VisibleForTesting
public final class ArrayUtils {
  public static int[] appendToArray(int[] paramArrayOfint, int paramInt) {
    if (paramArrayOfint == null || paramArrayOfint.length == 0) {
      paramArrayOfint = new int[1];
      paramArrayOfint[paramArrayOfint.length - 1] = paramInt;
      return paramArrayOfint;
    } 
    paramArrayOfint = Arrays.copyOf(paramArrayOfint, paramArrayOfint.length + 1);
    paramArrayOfint[paramArrayOfint.length - 1] = paramInt;
    return paramArrayOfint;
  }
  
  public static <T> T[] appendToArray(T[] paramArrayOfT, T paramT) {
    if (paramArrayOfT == null && paramT == null)
      throw new IllegalArgumentException("Cannot generate array of generic type w/o class info"); 
    if (paramArrayOfT == null) {
      paramArrayOfT = (T[])Array.newInstance(paramT.getClass(), 1);
    } else {
      paramArrayOfT = Arrays.copyOf(paramArrayOfT, paramArrayOfT.length + 1);
    } 
    paramArrayOfT[paramArrayOfT.length - 1] = paramT;
    return paramArrayOfT;
  }
  
  public static <T> T[] concat(T[]... paramVarArgs) {
    if (paramVarArgs.length == 0)
      return (T[])Array.newInstance(paramVarArgs.getClass(), 0); 
    byte b = 0;
    int i = b;
    while (b < paramVarArgs.length) {
      i += (paramVarArgs[b]).length;
      b++;
    } 
    Object[] arrayOfObject = Arrays.copyOf((Object[])paramVarArgs[0], i);
    i = (paramVarArgs[0]).length;
    for (b = 1; b < paramVarArgs.length; b++) {
      T[] arrayOfT = paramVarArgs[b];
      System.arraycopy(arrayOfT, 0, arrayOfObject, i, arrayOfT.length);
      i += arrayOfT.length;
    } 
    return (T[])arrayOfObject;
  }
  
  public static byte[] concatByteArrays(byte[]... paramVarArgs) {
    if (paramVarArgs.length == 0)
      return new byte[0]; 
    byte b = 0;
    int i = b;
    while (b < paramVarArgs.length) {
      i += (paramVarArgs[b]).length;
      b++;
    } 
    byte[] arrayOfByte = Arrays.copyOf(paramVarArgs[0], i);
    i = (paramVarArgs[0]).length;
    for (b = 1; b < paramVarArgs.length; b++) {
      byte[] arrayOfByte1 = paramVarArgs[b];
      System.arraycopy(arrayOfByte1, 0, arrayOfByte, i, arrayOfByte1.length);
      i += arrayOfByte1.length;
    } 
    return arrayOfByte;
  }
  
  public static boolean contains(byte[] paramArrayOfbyte, byte paramByte) {
    if (paramArrayOfbyte == null)
      return false; 
    int i = paramArrayOfbyte.length;
    for (byte b = 0; b < i; b++) {
      if (paramArrayOfbyte[b] == paramByte)
        return true; 
    } 
    return false;
  }
  
  public static boolean contains(char[] paramArrayOfchar, char paramChar) {
    if (paramArrayOfchar == null)
      return false; 
    int i = paramArrayOfchar.length;
    for (byte b = 0; b < i; b++) {
      if (paramArrayOfchar[b] == paramChar)
        return true; 
    } 
    return false;
  }
  
  public static boolean contains(double[] paramArrayOfdouble, double paramDouble) {
    if (paramArrayOfdouble == null)
      return false; 
    int i = paramArrayOfdouble.length;
    for (byte b = 0; b < i; b++) {
      if (paramArrayOfdouble[b] == paramDouble)
        return true; 
    } 
    return false;
  }
  
  public static boolean contains(float[] paramArrayOffloat, float paramFloat1, float paramFloat2) {
    if (paramArrayOffloat == null)
      return false; 
    int i = paramArrayOffloat.length;
    for (byte b = 0; b < i; b++) {
      float f = paramArrayOffloat[b];
      if (paramFloat1 - paramFloat2 <= f && f <= paramFloat1 + paramFloat2)
        return true; 
    } 
    return false;
  }
  
  public static boolean contains(int[] paramArrayOfint, int paramInt) {
    if (paramArrayOfint == null)
      return false; 
    int i = paramArrayOfint.length;
    for (byte b = 0; b < i; b++) {
      if (paramArrayOfint[b] == paramInt)
        return true; 
    } 
    return false;
  }
  
  public static <T> boolean contains(T[] paramArrayOfT, T paramT) {
    return (indexOf(paramArrayOfT, paramT) >= 0);
  }
  
  public static boolean contains(short[] paramArrayOfshort, short paramShort) {
    if (paramArrayOfshort == null)
      return false; 
    int i = paramArrayOfshort.length;
    for (byte b = 0; b < i; b++) {
      if (paramArrayOfshort[b] == paramShort)
        return true; 
    } 
    return false;
  }
  
  public static boolean contains(boolean[] paramArrayOfboolean, boolean paramBoolean) {
    if (paramArrayOfboolean == null)
      return false; 
    int i = paramArrayOfboolean.length;
    for (byte b = 0; b < i; b++) {
      if (paramArrayOfboolean[b] == paramBoolean)
        return true; 
    } 
    return false;
  }
  
  public static boolean containsIgnoreCase(String[] paramArrayOfString, String paramString) {
    if (paramArrayOfString == null)
      return false; 
    int i = paramArrayOfString.length;
    for (byte b = 0; b < i; b++) {
      String str = paramArrayOfString[b];
      if (str == paramString)
        return true; 
      if (str != null && str.equalsIgnoreCase(paramString))
        return true; 
    } 
    return false;
  }
  
  public static boolean equalsAnyOrder(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2) {
    if (paramArrayOfObject1 == paramArrayOfObject2)
      return true; 
    if (paramArrayOfObject1 == null) {
      i = 0;
    } else {
      i = paramArrayOfObject1.length;
    } 
    if (paramArrayOfObject2 == null) {
      j = 0;
    } else {
      j = paramArrayOfObject2.length;
    } 
    if (i == 0 && j == 0)
      return true; 
    if (i != j)
      return false; 
    zza zza = new zza(i);
    int j = paramArrayOfObject1.length;
    int i;
    for (i = 0; i < j; i++)
      zza.zzb(paramArrayOfObject1[i]); 
    j = paramArrayOfObject2.length;
    for (i = 0; i < j; i++)
      zza.zzc(paramArrayOfObject2[i]); 
    Iterator iterator = zza.zzzb.values().iterator();
    while (iterator.hasNext()) {
      if (((zza)iterator.next()).count != 0)
        return false; 
    } 
    return true;
  }
  
  public static <T> int indexOf(T[] paramArrayOfT, T paramT) {
    byte b2;
    byte b1 = 0;
    if (paramArrayOfT != null) {
      b2 = paramArrayOfT.length;
    } else {
      b2 = 0;
    } 
    while (b1 < b2) {
      if (Objects.equal(paramArrayOfT[b1], paramT))
        return b1; 
      b1++;
    } 
    return -1;
  }
  
  public static <T> ArrayList<T> newArrayList() {
    return new ArrayList<>();
  }
  
  public static <T> int rearrange(T[] paramArrayOfT, Predicate<T> paramPredicate) {
    byte b = 0;
    if (paramArrayOfT != null) {
      if (paramArrayOfT.length == 0)
        return 0; 
      int i = paramArrayOfT.length;
      int j;
      for (j = 0; b < i; j = k) {
        int k = j;
        if (paramPredicate.apply(paramArrayOfT[b])) {
          if (j != b) {
            T t = paramArrayOfT[j];
            paramArrayOfT[j] = paramArrayOfT[b];
            paramArrayOfT[b] = t;
          } 
          k = j + 1;
        } 
        b++;
      } 
      return j;
    } 
    return 0;
  }
  
  public static int[] removeAll(int[] paramArrayOfint1, int... paramVarArgs1) {
    int j;
    if (paramArrayOfint1 == null)
      return null; 
    if (paramVarArgs1 == null || paramVarArgs1.length == 0)
      return Arrays.copyOf(paramArrayOfint1, paramArrayOfint1.length); 
    int[] arrayOfInt = new int[paramArrayOfint1.length];
    int i = paramVarArgs1.length;
    byte b = 0;
    if (i == 1) {
      int k = paramArrayOfint1.length;
      b = 0;
      i = b;
      while (true) {
        j = i;
        if (b < k) {
          int m = paramArrayOfint1[b];
          j = i;
          if (paramVarArgs1[0] != m) {
            arrayOfInt[i] = m;
            j = i + 1;
          } 
          b++;
          i = j;
          continue;
        } 
        break;
      } 
    } else {
      int k = paramArrayOfint1.length;
      i = 0;
      while (true) {
        j = i;
        if (b < k) {
          int m = paramArrayOfint1[b];
          j = i;
          if (!contains(paramVarArgs1, m)) {
            arrayOfInt[i] = m;
            j = i + 1;
          } 
          b++;
          i = j;
          continue;
        } 
        break;
      } 
    } 
    return resize(arrayOfInt, j);
  }
  
  public static <T> T[] removeAll(T[] paramArrayOfT1, T... paramVarArgs1) {
    int j;
    if (paramArrayOfT1 == null)
      return null; 
    if (paramVarArgs1 == null || paramVarArgs1.length == 0)
      return Arrays.copyOf(paramArrayOfT1, paramArrayOfT1.length); 
    Object[] arrayOfObject = (Object[])Array.newInstance(paramVarArgs1.getClass().getComponentType(), paramArrayOfT1.length);
    int i = paramVarArgs1.length;
    byte b = 0;
    if (i == 1) {
      int k = paramArrayOfT1.length;
      b = 0;
      i = b;
      while (true) {
        j = i;
        if (b < k) {
          T t = paramArrayOfT1[b];
          j = i;
          if (!Objects.equal(paramVarArgs1[0], t)) {
            arrayOfObject[i] = t;
            j = i + 1;
          } 
          b++;
          i = j;
          continue;
        } 
        break;
      } 
    } else {
      int k = paramArrayOfT1.length;
      i = 0;
      while (true) {
        j = i;
        if (b < k) {
          T t = paramArrayOfT1[b];
          j = i;
          if (!contains(paramVarArgs1, t)) {
            arrayOfObject[i] = t;
            j = i + 1;
          } 
          b++;
          i = j;
          continue;
        } 
        break;
      } 
    } 
    return resize((T[])arrayOfObject, j);
  }
  
  public static int[] resize(int[] paramArrayOfint, int paramInt) {
    if (paramArrayOfint == null)
      return null; 
    int[] arrayOfInt = paramArrayOfint;
    if (paramInt != paramArrayOfint.length)
      arrayOfInt = Arrays.copyOf(paramArrayOfint, paramInt); 
    return arrayOfInt;
  }
  
  public static <T> T[] resize(T[] paramArrayOfT, int paramInt) {
    if (paramArrayOfT == null)
      return null; 
    T[] arrayOfT = paramArrayOfT;
    if (paramInt != paramArrayOfT.length)
      arrayOfT = Arrays.copyOf(paramArrayOfT, paramInt); 
    return arrayOfT;
  }
  
  public static <T> ArrayList<T> toArrayList(Collection<T> paramCollection) {
    return (paramCollection == null) ? null : new ArrayList<>(paramCollection);
  }
  
  public static <T> ArrayList<T> toArrayList(T[] paramArrayOfT) {
    int i = paramArrayOfT.length;
    ArrayList<T> arrayList = new ArrayList(i);
    for (byte b = 0; b < i; b++)
      arrayList.add(paramArrayOfT[b]); 
    return arrayList;
  }
  
  public static long[] toLongArray(Collection<Long> paramCollection) {
    byte b = 0;
    if (paramCollection == null || paramCollection.size() == 0)
      return new long[0]; 
    long[] arrayOfLong = new long[paramCollection.size()];
    Iterator<Long> iterator = paramCollection.iterator();
    while (iterator.hasNext()) {
      arrayOfLong[b] = ((Long)iterator.next()).longValue();
      b++;
    } 
    return arrayOfLong;
  }
  
  public static long[] toLongArray(Long[] paramArrayOfLong) {
    byte b = 0;
    if (paramArrayOfLong == null)
      return new long[0]; 
    long[] arrayOfLong = new long[paramArrayOfLong.length];
    while (b < paramArrayOfLong.length) {
      arrayOfLong[b] = paramArrayOfLong[b].longValue();
      b++;
    } 
    return arrayOfLong;
  }
  
  public static int[] toPrimitiveArray(Collection<Integer> paramCollection) {
    byte b = 0;
    if (paramCollection == null || paramCollection.size() == 0)
      return new int[0]; 
    int[] arrayOfInt = new int[paramCollection.size()];
    Iterator<Integer> iterator = paramCollection.iterator();
    while (iterator.hasNext()) {
      arrayOfInt[b] = ((Integer)iterator.next()).intValue();
      b++;
    } 
    return arrayOfInt;
  }
  
  public static int[] toPrimitiveArray(Integer[] paramArrayOfInteger) {
    byte b = 0;
    if (paramArrayOfInteger == null)
      return new int[0]; 
    int[] arrayOfInt = new int[paramArrayOfInteger.length];
    while (b < paramArrayOfInteger.length) {
      arrayOfInt[b] = paramArrayOfInteger[b].intValue();
      b++;
    } 
    return arrayOfInt;
  }
  
  public static String[] toStringArray(Collection<String> paramCollection) {
    return (paramCollection == null || paramCollection.size() == 0) ? new String[0] : paramCollection.<String>toArray(new String[paramCollection.size()]);
  }
  
  public static Boolean[] toWrapperArray(boolean[] paramArrayOfboolean) {
    if (paramArrayOfboolean == null)
      return null; 
    int i = paramArrayOfboolean.length;
    Boolean[] arrayOfBoolean = new Boolean[i];
    for (byte b = 0; b < i; b++)
      arrayOfBoolean[b] = Boolean.valueOf(paramArrayOfboolean[b]); 
    return arrayOfBoolean;
  }
  
  public static Byte[] toWrapperArray(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte == null)
      return null; 
    int i = paramArrayOfbyte.length;
    Byte[] arrayOfByte = new Byte[i];
    for (byte b = 0; b < i; b++)
      arrayOfByte[b] = Byte.valueOf(paramArrayOfbyte[b]); 
    return arrayOfByte;
  }
  
  public static Character[] toWrapperArray(char[] paramArrayOfchar) {
    if (paramArrayOfchar == null)
      return null; 
    int i = paramArrayOfchar.length;
    Character[] arrayOfCharacter = new Character[i];
    for (byte b = 0; b < i; b++)
      arrayOfCharacter[b] = Character.valueOf(paramArrayOfchar[b]); 
    return arrayOfCharacter;
  }
  
  public static Double[] toWrapperArray(double[] paramArrayOfdouble) {
    if (paramArrayOfdouble == null)
      return null; 
    int i = paramArrayOfdouble.length;
    Double[] arrayOfDouble = new Double[i];
    for (byte b = 0; b < i; b++)
      arrayOfDouble[b] = Double.valueOf(paramArrayOfdouble[b]); 
    return arrayOfDouble;
  }
  
  public static Float[] toWrapperArray(float[] paramArrayOffloat) {
    if (paramArrayOffloat == null)
      return null; 
    int i = paramArrayOffloat.length;
    Float[] arrayOfFloat = new Float[i];
    for (byte b = 0; b < i; b++)
      arrayOfFloat[b] = Float.valueOf(paramArrayOffloat[b]); 
    return arrayOfFloat;
  }
  
  public static Integer[] toWrapperArray(int[] paramArrayOfint) {
    if (paramArrayOfint == null)
      return null; 
    int i = paramArrayOfint.length;
    Integer[] arrayOfInteger = new Integer[i];
    for (byte b = 0; b < i; b++)
      arrayOfInteger[b] = Integer.valueOf(paramArrayOfint[b]); 
    return arrayOfInteger;
  }
  
  public static Long[] toWrapperArray(long[] paramArrayOflong) {
    if (paramArrayOflong == null)
      return null; 
    int i = paramArrayOflong.length;
    Long[] arrayOfLong = new Long[i];
    for (byte b = 0; b < i; b++)
      arrayOfLong[b] = Long.valueOf(paramArrayOflong[b]); 
    return arrayOfLong;
  }
  
  public static Short[] toWrapperArray(short[] paramArrayOfshort) {
    if (paramArrayOfshort == null)
      return null; 
    int i = paramArrayOfshort.length;
    Short[] arrayOfShort = new Short[i];
    for (byte b = 0; b < i; b++)
      arrayOfShort[b] = Short.valueOf(paramArrayOfshort[b]); 
    return arrayOfShort;
  }
  
  public static void writeArray(StringBuilder paramStringBuilder, double[] paramArrayOfdouble) {
    int i = paramArrayOfdouble.length;
    for (byte b = 0; b < i; b++) {
      if (b != 0)
        paramStringBuilder.append(","); 
      paramStringBuilder.append(Double.toString(paramArrayOfdouble[b]));
    } 
  }
  
  public static void writeArray(StringBuilder paramStringBuilder, float[] paramArrayOffloat) {
    int i = paramArrayOffloat.length;
    for (byte b = 0; b < i; b++) {
      if (b != 0)
        paramStringBuilder.append(","); 
      paramStringBuilder.append(Float.toString(paramArrayOffloat[b]));
    } 
  }
  
  public static void writeArray(StringBuilder paramStringBuilder, int[] paramArrayOfint) {
    int i = paramArrayOfint.length;
    for (byte b = 0; b < i; b++) {
      if (b != 0)
        paramStringBuilder.append(","); 
      paramStringBuilder.append(Integer.toString(paramArrayOfint[b]));
    } 
  }
  
  public static void writeArray(StringBuilder paramStringBuilder, long[] paramArrayOflong) {
    int i = paramArrayOflong.length;
    for (byte b = 0; b < i; b++) {
      if (b != 0)
        paramStringBuilder.append(","); 
      paramStringBuilder.append(Long.toString(paramArrayOflong[b]));
    } 
  }
  
  public static <T> void writeArray(StringBuilder paramStringBuilder, T[] paramArrayOfT) {
    int i = paramArrayOfT.length;
    for (byte b = 0; b < i; b++) {
      if (b != 0)
        paramStringBuilder.append(","); 
      paramStringBuilder.append(paramArrayOfT[b].toString());
    } 
  }
  
  public static void writeArray(StringBuilder paramStringBuilder, boolean[] paramArrayOfboolean) {
    int i = paramArrayOfboolean.length;
    for (byte b = 0; b < i; b++) {
      if (b != 0)
        paramStringBuilder.append(","); 
      paramStringBuilder.append(Boolean.toString(paramArrayOfboolean[b]));
    } 
  }
  
  public static void writeStringArray(StringBuilder paramStringBuilder, String[] paramArrayOfString) {
    int i = paramArrayOfString.length;
    for (byte b = 0; b < i; b++) {
      if (b != 0)
        paramStringBuilder.append(","); 
      paramStringBuilder.append("\"");
      paramStringBuilder.append(paramArrayOfString[b]);
      paramStringBuilder.append("\"");
    } 
  }
  
  private static final class zza {
    HashMap<Object, zza> zzzb;
    
    zza(int param1Int) {
      this.zzzb = new HashMap<>(param1Int);
    }
    
    private final zza zzd(Object param1Object) {
      zza zza1 = this.zzzb.get(param1Object);
      zza zza2 = zza1;
      if (zza1 == null) {
        zza2 = new zza();
        this.zzzb.put(param1Object, zza2);
      } 
      return zza2;
    }
    
    final void zzb(Object param1Object) {
      param1Object = zzd(param1Object);
      ((zza)param1Object).count++;
    }
    
    final void zzc(Object param1Object) {
      param1Object = zzd(param1Object);
      ((zza)param1Object).count--;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\ArrayUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */