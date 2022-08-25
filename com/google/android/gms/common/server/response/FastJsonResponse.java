package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.server.converter.ConverterWrapper;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public abstract class FastJsonResponse {
  protected static final String QUOTE = "\"";
  
  private int zzwk;
  
  private byte[] zzwl;
  
  private boolean zzwm;
  
  public static InputStream getUnzippedStream(byte[] paramArrayOfbyte) {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(paramArrayOfbyte);
    if (IOUtils.isGzipByteBuffer(paramArrayOfbyte))
      try {
        return new GZIPInputStream(byteArrayInputStream);
      } catch (IOException iOException) {} 
    return byteArrayInputStream;
  }
  
  private final <I, O> void zza(Field<I, O> paramField, I paramI) {
    StringBuilder stringBuilder;
    int i;
    String str = paramField.getOutputFieldName();
    paramI = (I)paramField.convert(paramI);
    switch (paramField.getTypeOut()) {
      default:
        i = paramField.getTypeOut();
        stringBuilder = new StringBuilder(44);
        stringBuilder.append("Unsupported type for conversion: ");
        stringBuilder.append(i);
        throw new IllegalStateException(stringBuilder.toString());
      case 8:
      case 9:
        if (zzb(str, paramI)) {
          setDecodedBytesInternal((Field<?, ?>)stringBuilder, str, (byte[])paramI);
          return;
        } 
        return;
      case 7:
        setStringInternal((Field<?, ?>)stringBuilder, str, (String)paramI);
        return;
      case 6:
        if (zzb(str, paramI)) {
          setBooleanInternal((Field<?, ?>)stringBuilder, str, ((Boolean)paramI).booleanValue());
          return;
        } 
        return;
      case 5:
        setBigDecimalInternal((Field<?, ?>)stringBuilder, str, (BigDecimal)paramI);
        return;
      case 4:
        if (zzb(str, paramI)) {
          setDoubleInternal((Field<?, ?>)stringBuilder, str, ((Double)paramI).doubleValue());
          return;
        } 
        return;
      case 2:
        if (zzb(str, paramI)) {
          setLongInternal((Field<?, ?>)stringBuilder, str, ((Long)paramI).longValue());
          return;
        } 
        return;
      case 1:
        setBigIntegerInternal((Field<?, ?>)stringBuilder, str, (BigInteger)paramI);
        return;
      case 0:
        break;
    } 
    if (zzb(str, paramI))
      setIntegerInternal((Field<?, ?>)stringBuilder, str, ((Integer)paramI).intValue()); 
  }
  
  private static void zza(StringBuilder paramStringBuilder, Field paramField, Object paramObject) {
    String str;
    if (paramField.getTypeIn() == 11) {
      str = ((FastJsonResponse)paramField.getConcreteType().cast(paramObject)).toString();
    } else if (str.getTypeIn() == 7) {
      paramStringBuilder.append("\"");
      paramStringBuilder.append(JsonUtils.escapeString((String)paramObject));
      str = "\"";
    } else {
      paramStringBuilder.append(paramObject);
      return;
    } 
    paramStringBuilder.append(str);
  }
  
  private static <O> boolean zzb(String paramString, O paramO) {
    if (paramO == null) {
      if (Log.isLoggable("FastJsonResponse", 6)) {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString).length() + 58);
        stringBuilder.append("Output field (");
        stringBuilder.append(paramString);
        stringBuilder.append(") has a null value, but expected a primitive");
        Log.e("FastJsonResponse", stringBuilder.toString());
      } 
      return false;
    } 
    return true;
  }
  
  public <T extends FastJsonResponse> void addConcreteType(String paramString, T paramT) {
    throw new UnsupportedOperationException("Concrete type not supported");
  }
  
  public <T extends FastJsonResponse> void addConcreteTypeArray(String paramString, ArrayList<T> paramArrayList) {
    throw new UnsupportedOperationException("Concrete type array not supported");
  }
  
  public <T extends FastJsonResponse> void addConcreteTypeArrayInternal(Field<?, ?> paramField, String paramString, ArrayList<T> paramArrayList) {
    addConcreteTypeArray(paramString, paramArrayList);
  }
  
  public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> paramField, String paramString, T paramT) {
    addConcreteType(paramString, paramT);
  }
  
  public HashMap<String, Object> getConcreteTypeArrays() {
    return null;
  }
  
  public HashMap<String, Object> getConcreteTypes() {
    return null;
  }
  
  public abstract Map<String, Field<?, ?>> getFieldMappings();
  
  protected Object getFieldValue(Field paramField) {
    String str = paramField.getOutputFieldName();
    if (paramField.getConcreteType() != null) {
      HashMap<String, Object> hashMap;
      boolean bool;
      if (getValueObject(paramField.getOutputFieldName()) == null) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkState(bool, "Concrete field shouldn't be value object: %s", new Object[] { paramField.getOutputFieldName() });
      if (paramField.isTypeOutArray()) {
        hashMap = getConcreteTypeArrays();
      } else {
        hashMap = getConcreteTypes();
      } 
      if (hashMap != null)
        return hashMap.get(str); 
      try {
        char c = Character.toUpperCase(str.charAt(0));
        String str1 = str.substring(1);
        int i = String.valueOf(str1).length();
        StringBuilder stringBuilder = new StringBuilder();
        this(i + 4);
        stringBuilder.append("get");
        stringBuilder.append(c);
        stringBuilder.append(str1);
        str1 = stringBuilder.toString();
        return getClass().getMethod(str1, new Class[0]).invoke(this, new Object[0]);
      } catch (Exception exception) {
        throw new RuntimeException(exception);
      } 
    } 
    return getValueObject(exception.getOutputFieldName());
  }
  
  protected <O, I> I getOriginalValue(Field<I, O> paramField, Object paramObject) {
    return (I)((Field.zza(paramField) != null) ? (Object)paramField.convertBack((O)paramObject) : paramObject);
  }
  
  public PostProcessor<? extends FastJsonResponse> getPostProcessor() {
    return null;
  }
  
  public byte[] getResponseBody() {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzwm : Z
    //   4: invokestatic checkState : (Z)V
    //   7: aconst_null
    //   8: astore_1
    //   9: aconst_null
    //   10: astore_2
    //   11: aload_2
    //   12: astore_3
    //   13: new java/util/zip/GZIPInputStream
    //   16: astore #4
    //   18: aload_2
    //   19: astore_3
    //   20: new java/io/ByteArrayInputStream
    //   23: astore #5
    //   25: aload_2
    //   26: astore_3
    //   27: aload #5
    //   29: aload_0
    //   30: getfield zzwl : [B
    //   33: invokespecial <init> : ([B)V
    //   36: aload_2
    //   37: astore_3
    //   38: aload #4
    //   40: aload #5
    //   42: invokespecial <init> : (Ljava/io/InputStream;)V
    //   45: sipush #4096
    //   48: newarray byte
    //   50: astore_1
    //   51: new java/io/ByteArrayOutputStream
    //   54: astore_3
    //   55: aload_3
    //   56: invokespecial <init> : ()V
    //   59: aload #4
    //   61: aload_1
    //   62: iconst_0
    //   63: sipush #4096
    //   66: invokevirtual read : ([BII)I
    //   69: istore #6
    //   71: iload #6
    //   73: iconst_m1
    //   74: if_icmpeq -> 88
    //   77: aload_3
    //   78: aload_1
    //   79: iconst_0
    //   80: iload #6
    //   82: invokevirtual write : ([BII)V
    //   85: goto -> 59
    //   88: aload_3
    //   89: invokevirtual flush : ()V
    //   92: aload_3
    //   93: invokevirtual toByteArray : ()[B
    //   96: astore_3
    //   97: aload #4
    //   99: invokevirtual close : ()V
    //   102: aload_3
    //   103: areturn
    //   104: astore_1
    //   105: goto -> 139
    //   108: astore_3
    //   109: goto -> 119
    //   112: astore_1
    //   113: aload_3
    //   114: astore #4
    //   116: goto -> 139
    //   119: aload #4
    //   121: astore_3
    //   122: aload_0
    //   123: getfield zzwl : [B
    //   126: astore_1
    //   127: aload #4
    //   129: ifnull -> 137
    //   132: aload #4
    //   134: invokevirtual close : ()V
    //   137: aload_1
    //   138: areturn
    //   139: aload #4
    //   141: ifnull -> 149
    //   144: aload #4
    //   146: invokevirtual close : ()V
    //   149: aload_1
    //   150: athrow
    //   151: astore_3
    //   152: aload_1
    //   153: astore #4
    //   155: goto -> 119
    //   158: astore #4
    //   160: goto -> 102
    //   163: astore_3
    //   164: goto -> 137
    //   167: astore_3
    //   168: goto -> 149
    // Exception table:
    //   from	to	target	type
    //   13	18	151	java/io/IOException
    //   13	18	112	finally
    //   20	25	151	java/io/IOException
    //   20	25	112	finally
    //   27	36	151	java/io/IOException
    //   27	36	112	finally
    //   38	45	151	java/io/IOException
    //   38	45	112	finally
    //   45	59	108	java/io/IOException
    //   45	59	104	finally
    //   59	71	108	java/io/IOException
    //   59	71	104	finally
    //   77	85	108	java/io/IOException
    //   77	85	104	finally
    //   88	97	108	java/io/IOException
    //   88	97	104	finally
    //   97	102	158	java/io/IOException
    //   122	127	112	finally
    //   132	137	163	java/io/IOException
    //   144	149	167	java/io/IOException
  }
  
  public int getResponseCode() {
    Preconditions.checkState(this.zzwm);
    return this.zzwk;
  }
  
  protected abstract Object getValueObject(String paramString);
  
  protected boolean isConcreteTypeArrayFieldSet(String paramString) {
    throw new UnsupportedOperationException("Concrete type arrays not supported");
  }
  
  protected boolean isConcreteTypeFieldSet(String paramString) {
    throw new UnsupportedOperationException("Concrete types not supported");
  }
  
  protected boolean isFieldSet(Field paramField) {
    return (paramField.getTypeOut() == 11) ? (paramField.isTypeOutArray() ? isConcreteTypeArrayFieldSet(paramField.getOutputFieldName()) : isConcreteTypeFieldSet(paramField.getOutputFieldName())) : isPrimitiveFieldSet(paramField.getOutputFieldName());
  }
  
  protected abstract boolean isPrimitiveFieldSet(String paramString);
  
  public <T extends FastJsonResponse> void parseNetworkResponse(int paramInt, byte[] paramArrayOfbyte) throws FastParser.ParseException {
    this.zzwk = paramInt;
    this.zzwl = paramArrayOfbyte;
    this.zzwm = true;
    InputStream inputStream = getUnzippedStream(paramArrayOfbyte);
    try {
      FastParser<FastJsonResponse> fastParser = new FastParser<>();
      this();
      fastParser.parse(inputStream, this);
    } finally {
      try {
        iOException.close();
      } catch (IOException iOException1) {}
    } 
  }
  
  public final <O> void setBigDecimal(Field<BigDecimal, O> paramField, BigDecimal paramBigDecimal) {
    if (Field.zza(paramField) != null) {
      zza(paramField, paramBigDecimal);
      return;
    } 
    setBigDecimalInternal(paramField, paramField.getOutputFieldName(), paramBigDecimal);
  }
  
  protected void setBigDecimal(String paramString, BigDecimal paramBigDecimal) {
    throw new UnsupportedOperationException("BigDecimal not supported");
  }
  
  protected void setBigDecimalInternal(Field<?, ?> paramField, String paramString, BigDecimal paramBigDecimal) {
    setBigDecimal(paramString, paramBigDecimal);
  }
  
  public final <O> void setBigDecimals(Field<ArrayList<BigDecimal>, O> paramField, ArrayList<BigDecimal> paramArrayList) {
    if (Field.zza(paramField) != null) {
      zza(paramField, paramArrayList);
      return;
    } 
    setBigDecimalsInternal(paramField, paramField.getOutputFieldName(), paramArrayList);
  }
  
  protected void setBigDecimals(String paramString, ArrayList<BigDecimal> paramArrayList) {
    throw new UnsupportedOperationException("BigDecimal list not supported");
  }
  
  protected void setBigDecimalsInternal(Field<?, ?> paramField, String paramString, ArrayList<BigDecimal> paramArrayList) {
    setBigDecimals(paramString, paramArrayList);
  }
  
  public final <O> void setBigInteger(Field<BigInteger, O> paramField, BigInteger paramBigInteger) {
    if (Field.zza(paramField) != null) {
      zza(paramField, paramBigInteger);
      return;
    } 
    setBigIntegerInternal(paramField, paramField.getOutputFieldName(), paramBigInteger);
  }
  
  protected void setBigInteger(String paramString, BigInteger paramBigInteger) {
    throw new UnsupportedOperationException("BigInteger not supported");
  }
  
  protected void setBigIntegerInternal(Field<?, ?> paramField, String paramString, BigInteger paramBigInteger) {
    setBigInteger(paramString, paramBigInteger);
  }
  
  public final <O> void setBigIntegers(Field<ArrayList<BigInteger>, O> paramField, ArrayList<BigInteger> paramArrayList) {
    if (Field.zza(paramField) != null) {
      zza(paramField, paramArrayList);
      return;
    } 
    setBigIntegersInternal(paramField, paramField.getOutputFieldName(), paramArrayList);
  }
  
  protected void setBigIntegers(String paramString, ArrayList<BigInteger> paramArrayList) {
    throw new UnsupportedOperationException("BigInteger list not supported");
  }
  
  protected void setBigIntegersInternal(Field<?, ?> paramField, String paramString, ArrayList<BigInteger> paramArrayList) {
    setBigIntegers(paramString, paramArrayList);
  }
  
  public final <O> void setBoolean(Field<Boolean, O> paramField, boolean paramBoolean) {
    if (Field.zza(paramField) != null) {
      zza(paramField, Boolean.valueOf(paramBoolean));
      return;
    } 
    setBooleanInternal(paramField, paramField.getOutputFieldName(), paramBoolean);
  }
  
  protected void setBoolean(String paramString, boolean paramBoolean) {
    throw new UnsupportedOperationException("Boolean not supported");
  }
  
  protected void setBooleanInternal(Field<?, ?> paramField, String paramString, boolean paramBoolean) {
    setBoolean(paramString, paramBoolean);
  }
  
  public final <O> void setBooleans(Field<ArrayList<Boolean>, O> paramField, ArrayList<Boolean> paramArrayList) {
    if (Field.zza(paramField) != null) {
      zza(paramField, paramArrayList);
      return;
    } 
    setBooleansInternal(paramField, paramField.getOutputFieldName(), paramArrayList);
  }
  
  protected void setBooleans(String paramString, ArrayList<Boolean> paramArrayList) {
    throw new UnsupportedOperationException("Boolean list not supported");
  }
  
  protected void setBooleansInternal(Field<?, ?> paramField, String paramString, ArrayList<Boolean> paramArrayList) {
    setBooleans(paramString, paramArrayList);
  }
  
  public final <O> void setDecodedBytes(Field<byte[], O> paramField, byte[] paramArrayOfbyte) {
    if (Field.zza(paramField) != null) {
      zza((Field)paramField, paramArrayOfbyte);
      return;
    } 
    setDecodedBytesInternal(paramField, paramField.getOutputFieldName(), paramArrayOfbyte);
  }
  
  protected void setDecodedBytes(String paramString, byte[] paramArrayOfbyte) {
    throw new UnsupportedOperationException("byte[] not supported");
  }
  
  protected void setDecodedBytesInternal(Field<?, ?> paramField, String paramString, byte[] paramArrayOfbyte) {
    setDecodedBytes(paramString, paramArrayOfbyte);
  }
  
  public final <O> void setDouble(Field<Double, O> paramField, double paramDouble) {
    if (Field.zza(paramField) != null) {
      zza(paramField, Double.valueOf(paramDouble));
      return;
    } 
    setDoubleInternal(paramField, paramField.getOutputFieldName(), paramDouble);
  }
  
  protected void setDouble(String paramString, double paramDouble) {
    throw new UnsupportedOperationException("Double not supported");
  }
  
  protected void setDoubleInternal(Field<?, ?> paramField, String paramString, double paramDouble) {
    setDouble(paramString, paramDouble);
  }
  
  public final <O> void setDoubles(Field<ArrayList<Double>, O> paramField, ArrayList<Double> paramArrayList) {
    if (Field.zza(paramField) != null) {
      zza(paramField, paramArrayList);
      return;
    } 
    setDoublesInternal(paramField, paramField.getOutputFieldName(), paramArrayList);
  }
  
  protected void setDoubles(String paramString, ArrayList<Double> paramArrayList) {
    throw new UnsupportedOperationException("Double list not supported");
  }
  
  protected void setDoublesInternal(Field<?, ?> paramField, String paramString, ArrayList<Double> paramArrayList) {
    setDoubles(paramString, paramArrayList);
  }
  
  public final <O> void setFloat(Field<Float, O> paramField, float paramFloat) {
    if (Field.zza(paramField) != null) {
      zza(paramField, Float.valueOf(paramFloat));
      return;
    } 
    setFloatInternal(paramField, paramField.getOutputFieldName(), paramFloat);
  }
  
  protected void setFloat(String paramString, float paramFloat) {
    throw new UnsupportedOperationException("Float not supported");
  }
  
  protected void setFloatInternal(Field<?, ?> paramField, String paramString, float paramFloat) {
    setFloat(paramString, paramFloat);
  }
  
  public final <O> void setFloats(Field<ArrayList<Float>, O> paramField, ArrayList<Float> paramArrayList) {
    if (Field.zza(paramField) != null) {
      zza(paramField, paramArrayList);
      return;
    } 
    setFloatsInternal(paramField, paramField.getOutputFieldName(), paramArrayList);
  }
  
  protected void setFloats(String paramString, ArrayList<Float> paramArrayList) {
    throw new UnsupportedOperationException("Float list not supported");
  }
  
  protected void setFloatsInternal(Field<?, ?> paramField, String paramString, ArrayList<Float> paramArrayList) {
    setFloats(paramString, paramArrayList);
  }
  
  public final <O> void setInteger(Field<Integer, O> paramField, int paramInt) {
    if (Field.zza(paramField) != null) {
      zza(paramField, Integer.valueOf(paramInt));
      return;
    } 
    setIntegerInternal(paramField, paramField.getOutputFieldName(), paramInt);
  }
  
  protected void setInteger(String paramString, int paramInt) {
    throw new UnsupportedOperationException("Integer not supported");
  }
  
  protected void setIntegerInternal(Field<?, ?> paramField, String paramString, int paramInt) {
    setInteger(paramString, paramInt);
  }
  
  public final <O> void setIntegers(Field<ArrayList<Integer>, O> paramField, ArrayList<Integer> paramArrayList) {
    if (Field.zza(paramField) != null) {
      zza(paramField, paramArrayList);
      return;
    } 
    setIntegersInternal(paramField, paramField.getOutputFieldName(), paramArrayList);
  }
  
  protected void setIntegers(String paramString, ArrayList<Integer> paramArrayList) {
    throw new UnsupportedOperationException("Integer list not supported");
  }
  
  protected void setIntegersInternal(Field<?, ?> paramField, String paramString, ArrayList<Integer> paramArrayList) {
    setIntegers(paramString, paramArrayList);
  }
  
  public final <O> void setLong(Field<Long, O> paramField, long paramLong) {
    if (Field.zza(paramField) != null) {
      zza(paramField, Long.valueOf(paramLong));
      return;
    } 
    setLongInternal(paramField, paramField.getOutputFieldName(), paramLong);
  }
  
  protected void setLong(String paramString, long paramLong) {
    throw new UnsupportedOperationException("Long not supported");
  }
  
  protected void setLongInternal(Field<?, ?> paramField, String paramString, long paramLong) {
    setLong(paramString, paramLong);
  }
  
  public final <O> void setLongs(Field<ArrayList<Long>, O> paramField, ArrayList<Long> paramArrayList) {
    if (Field.zza(paramField) != null) {
      zza(paramField, paramArrayList);
      return;
    } 
    setLongsInternal(paramField, paramField.getOutputFieldName(), paramArrayList);
  }
  
  protected void setLongs(String paramString, ArrayList<Long> paramArrayList) {
    throw new UnsupportedOperationException("Long list not supported");
  }
  
  protected void setLongsInternal(Field<?, ?> paramField, String paramString, ArrayList<Long> paramArrayList) {
    setLongs(paramString, paramArrayList);
  }
  
  public final <O> void setString(Field<String, O> paramField, String paramString) {
    if (Field.zza(paramField) != null) {
      zza(paramField, paramString);
      return;
    } 
    setStringInternal(paramField, paramField.getOutputFieldName(), paramString);
  }
  
  protected void setString(String paramString1, String paramString2) {
    throw new UnsupportedOperationException("String not supported");
  }
  
  protected void setStringInternal(Field<?, ?> paramField, String paramString1, String paramString2) {
    setString(paramString1, paramString2);
  }
  
  public final <O> void setStringMap(Field<Map<String, String>, O> paramField, Map<String, String> paramMap) {
    if (Field.zza(paramField) != null) {
      zza(paramField, paramMap);
      return;
    } 
    setStringMapInternal(paramField, paramField.getOutputFieldName(), paramMap);
  }
  
  protected void setStringMap(String paramString, Map<String, String> paramMap) {
    throw new UnsupportedOperationException("String map not supported");
  }
  
  protected void setStringMapInternal(Field<?, ?> paramField, String paramString, Map<String, String> paramMap) {
    setStringMap(paramString, paramMap);
  }
  
  public final <O> void setStrings(Field<ArrayList<String>, O> paramField, ArrayList<String> paramArrayList) {
    if (Field.zza(paramField) != null) {
      zza(paramField, paramArrayList);
      return;
    } 
    setStringsInternal(paramField, paramField.getOutputFieldName(), paramArrayList);
  }
  
  protected void setStrings(String paramString, ArrayList<String> paramArrayList) {
    throw new UnsupportedOperationException("String list not supported");
  }
  
  protected void setStringsInternal(Field<?, ?> paramField, String paramString, ArrayList<String> paramArrayList) {
    setStrings(paramString, paramArrayList);
  }
  
  public String toString() {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getFieldMappings : ()Ljava/util/Map;
    //   4: astore_1
    //   5: new java/lang/StringBuilder
    //   8: dup
    //   9: bipush #100
    //   11: invokespecial <init> : (I)V
    //   14: astore_2
    //   15: aload_1
    //   16: invokeinterface keySet : ()Ljava/util/Set;
    //   21: invokeinterface iterator : ()Ljava/util/Iterator;
    //   26: astore_3
    //   27: aload_3
    //   28: invokeinterface hasNext : ()Z
    //   33: ifeq -> 354
    //   36: aload_3
    //   37: invokeinterface next : ()Ljava/lang/Object;
    //   42: checkcast java/lang/String
    //   45: astore #4
    //   47: aload_1
    //   48: aload #4
    //   50: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   55: checkcast com/google/android/gms/common/server/response/FastJsonResponse$Field
    //   58: astore #5
    //   60: aload_0
    //   61: aload #5
    //   63: invokevirtual isFieldSet : (Lcom/google/android/gms/common/server/response/FastJsonResponse$Field;)Z
    //   66: ifeq -> 27
    //   69: aload_0
    //   70: aload #5
    //   72: aload_0
    //   73: aload #5
    //   75: invokevirtual getFieldValue : (Lcom/google/android/gms/common/server/response/FastJsonResponse$Field;)Ljava/lang/Object;
    //   78: invokevirtual getOriginalValue : (Lcom/google/android/gms/common/server/response/FastJsonResponse$Field;Ljava/lang/Object;)Ljava/lang/Object;
    //   81: astore #6
    //   83: aload_2
    //   84: invokevirtual length : ()I
    //   87: ifne -> 105
    //   90: ldc_w '{'
    //   93: astore #7
    //   95: aload_2
    //   96: aload #7
    //   98: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: pop
    //   102: goto -> 113
    //   105: ldc_w ','
    //   108: astore #7
    //   110: goto -> 95
    //   113: aload_2
    //   114: ldc '"'
    //   116: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: pop
    //   120: aload_2
    //   121: aload #4
    //   123: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: pop
    //   127: aload_2
    //   128: ldc_w '":'
    //   131: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: pop
    //   135: aload #6
    //   137: ifnonnull -> 155
    //   140: ldc_w 'null'
    //   143: astore #7
    //   145: aload_2
    //   146: aload #7
    //   148: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: pop
    //   152: goto -> 27
    //   155: aload #5
    //   157: invokevirtual getTypeOut : ()I
    //   160: tableswitch default -> 188, 8 -> 256, 9 -> 236, 10 -> 224
    //   188: aload #5
    //   190: invokevirtual isTypeInArray : ()Z
    //   193: ifeq -> 343
    //   196: aload #6
    //   198: checkcast java/util/ArrayList
    //   201: astore #7
    //   203: aload_2
    //   204: ldc_w '['
    //   207: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   210: pop
    //   211: iconst_0
    //   212: istore #8
    //   214: aload #7
    //   216: invokevirtual size : ()I
    //   219: istore #9
    //   221: goto -> 287
    //   224: aload_2
    //   225: aload #6
    //   227: checkcast java/util/HashMap
    //   230: invokestatic writeStringMapToJson : (Ljava/lang/StringBuilder;Ljava/util/HashMap;)V
    //   233: goto -> 27
    //   236: aload_2
    //   237: ldc '"'
    //   239: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   242: pop
    //   243: aload #6
    //   245: checkcast [B
    //   248: invokestatic encodeUrlSafe : ([B)Ljava/lang/String;
    //   251: astore #7
    //   253: goto -> 273
    //   256: aload_2
    //   257: ldc '"'
    //   259: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: pop
    //   263: aload #6
    //   265: checkcast [B
    //   268: invokestatic encode : ([B)Ljava/lang/String;
    //   271: astore #7
    //   273: aload_2
    //   274: aload #7
    //   276: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   279: pop
    //   280: ldc '"'
    //   282: astore #7
    //   284: goto -> 145
    //   287: iload #8
    //   289: iload #9
    //   291: if_icmpge -> 335
    //   294: iload #8
    //   296: ifle -> 307
    //   299: aload_2
    //   300: ldc_w ','
    //   303: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: pop
    //   307: aload #7
    //   309: iload #8
    //   311: invokevirtual get : (I)Ljava/lang/Object;
    //   314: astore #4
    //   316: aload #4
    //   318: ifnull -> 329
    //   321: aload_2
    //   322: aload #5
    //   324: aload #4
    //   326: invokestatic zza : (Ljava/lang/StringBuilder;Lcom/google/android/gms/common/server/response/FastJsonResponse$Field;Ljava/lang/Object;)V
    //   329: iinc #8, 1
    //   332: goto -> 287
    //   335: ldc_w ']'
    //   338: astore #7
    //   340: goto -> 145
    //   343: aload_2
    //   344: aload #5
    //   346: aload #6
    //   348: invokestatic zza : (Ljava/lang/StringBuilder;Lcom/google/android/gms/common/server/response/FastJsonResponse$Field;Ljava/lang/Object;)V
    //   351: goto -> 27
    //   354: aload_2
    //   355: invokevirtual length : ()I
    //   358: ifle -> 376
    //   361: ldc_w '}'
    //   364: astore #7
    //   366: aload_2
    //   367: aload #7
    //   369: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   372: pop
    //   373: goto -> 384
    //   376: ldc_w '{}'
    //   379: astore #7
    //   381: goto -> 366
    //   384: aload_2
    //   385: invokevirtual toString : ()Ljava/lang/String;
    //   388: areturn
  }
  
  @Class(creator = "FieldCreator")
  @VisibleForTesting
  public static class Field<I, O> extends AbstractSafeParcelable {
    public static final FieldCreator CREATOR = new FieldCreator();
    
    protected final Class<? extends FastJsonResponse> mConcreteType;
    
    @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getConcreteTypeName", id = 8)
    protected final String mConcreteTypeName;
    
    @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getOutputFieldName", id = 6)
    protected final String mOutputFieldName;
    
    @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getSafeParcelableFieldId", id = 7)
    protected final int mSafeParcelableFieldId;
    
    @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getTypeIn", id = 2)
    protected final int mTypeIn;
    
    @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "isTypeInArray", id = 3)
    protected final boolean mTypeInArray;
    
    @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getTypeOut", id = 4)
    protected final int mTypeOut;
    
    @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "isTypeOutArray", id = 5)
    protected final boolean mTypeOutArray;
    
    @VersionField(getter = "getVersionCode", id = 1)
    private final int zzal;
    
    private FieldMappingDictionary zzwn;
    
    @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getWrappedConverter", id = 9, type = "com.google.android.gms.common.server.converter.ConverterWrapper")
    private FastJsonResponse.FieldConverter<I, O> zzwo;
    
    @Constructor
    Field(@Param(id = 1) int param1Int1, @Param(id = 2) int param1Int2, @Param(id = 3) boolean param1Boolean1, @Param(id = 4) int param1Int3, @Param(id = 5) boolean param1Boolean2, @Param(id = 6) String param1String1, @Param(id = 7) int param1Int4, @Param(id = 8) String param1String2, @Param(id = 9) ConverterWrapper param1ConverterWrapper) {
      this.zzal = param1Int1;
      this.mTypeIn = param1Int2;
      this.mTypeInArray = param1Boolean1;
      this.mTypeOut = param1Int3;
      this.mTypeOutArray = param1Boolean2;
      this.mOutputFieldName = param1String1;
      this.mSafeParcelableFieldId = param1Int4;
      if (param1String2 == null) {
        this.mConcreteType = null;
        this.mConcreteTypeName = null;
      } else {
        this.mConcreteType = (Class)SafeParcelResponse.class;
        this.mConcreteTypeName = param1String2;
      } 
      if (param1ConverterWrapper == null) {
        this.zzwo = null;
        return;
      } 
      this.zzwo = param1ConverterWrapper.unwrap();
    }
    
    protected Field(int param1Int1, boolean param1Boolean1, int param1Int2, boolean param1Boolean2, String param1String, int param1Int3, Class<? extends FastJsonResponse> param1Class, FastJsonResponse.FieldConverter<I, O> param1FieldConverter) {
      this.zzal = 1;
      this.mTypeIn = param1Int1;
      this.mTypeInArray = param1Boolean1;
      this.mTypeOut = param1Int2;
      this.mTypeOutArray = param1Boolean2;
      this.mOutputFieldName = param1String;
      this.mSafeParcelableFieldId = param1Int3;
      this.mConcreteType = param1Class;
      if (param1Class == null) {
        param1String = null;
      } else {
        param1String = param1Class.getCanonicalName();
      } 
      this.mConcreteTypeName = param1String;
      this.zzwo = param1FieldConverter;
    }
    
    public static Field<byte[], byte[]> forBase64(String param1String) {
      return (Field)new Field<>(8, false, 8, false, param1String, -1, null, null);
    }
    
    @VisibleForTesting
    public static Field<byte[], byte[]> forBase64(String param1String, int param1Int) {
      return (Field)new Field<>(8, false, 8, false, param1String, param1Int, null, null);
    }
    
    @VisibleForTesting
    public static Field<byte[], byte[]> forBase64UrlSafe(String param1String) {
      return (Field)new Field<>(9, false, 9, false, param1String, -1, null, null);
    }
    
    @VisibleForTesting
    public static Field<byte[], byte[]> forBase64UrlSafe(String param1String, int param1Int) {
      return (Field)new Field<>(9, false, 9, false, param1String, param1Int, null, null);
    }
    
    public static Field<BigDecimal, BigDecimal> forBigDecimal(String param1String) {
      return new Field<>(5, false, 5, false, param1String, -1, null, null);
    }
    
    @VisibleForTesting
    public static Field<BigDecimal, BigDecimal> forBigDecimal(String param1String, int param1Int) {
      return new Field<>(5, false, 5, false, param1String, param1Int, null, null);
    }
    
    public static Field<ArrayList<BigDecimal>, ArrayList<BigDecimal>> forBigDecimals(String param1String) {
      return new Field<>(5, true, 5, true, param1String, -1, null, null);
    }
    
    public static Field<ArrayList<BigDecimal>, ArrayList<BigDecimal>> forBigDecimals(String param1String, int param1Int) {
      return new Field<>(5, true, 5, true, param1String, param1Int, null, null);
    }
    
    public static Field<BigInteger, BigInteger> forBigInteger(String param1String) {
      return new Field<>(1, false, 1, false, param1String, -1, null, null);
    }
    
    public static Field<BigInteger, BigInteger> forBigInteger(String param1String, int param1Int) {
      return new Field<>(1, false, 1, false, param1String, param1Int, null, null);
    }
    
    public static Field<ArrayList<BigInteger>, ArrayList<BigInteger>> forBigIntegers(String param1String) {
      return new Field<>(0, true, 1, true, param1String, -1, null, null);
    }
    
    public static Field<ArrayList<BigInteger>, ArrayList<BigInteger>> forBigIntegers(String param1String, int param1Int) {
      return new Field<>(0, true, 1, true, param1String, param1Int, null, null);
    }
    
    public static Field<Boolean, Boolean> forBoolean(String param1String) {
      return new Field<>(6, false, 6, false, param1String, -1, null, null);
    }
    
    public static Field<Boolean, Boolean> forBoolean(String param1String, int param1Int) {
      return new Field<>(6, false, 6, false, param1String, param1Int, null, null);
    }
    
    public static Field<ArrayList<Boolean>, ArrayList<Boolean>> forBooleans(String param1String) {
      return new Field<>(6, true, 6, true, param1String, -1, null, null);
    }
    
    public static Field<ArrayList<Boolean>, ArrayList<Boolean>> forBooleans(String param1String, int param1Int) {
      return new Field<>(6, true, 6, true, param1String, param1Int, null, null);
    }
    
    public static <T extends FastJsonResponse> Field<T, T> forConcreteType(String param1String, int param1Int, Class<T> param1Class) {
      return new Field<>(11, false, 11, false, param1String, param1Int, param1Class, null);
    }
    
    public static <T extends FastJsonResponse> Field<T, T> forConcreteType(String param1String, Class<T> param1Class) {
      return new Field<>(11, false, 11, false, param1String, -1, param1Class, null);
    }
    
    public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> forConcreteTypeArray(String param1String, int param1Int, Class<T> param1Class) {
      return new Field<>(11, true, 11, true, param1String, param1Int, param1Class, null);
    }
    
    public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> forConcreteTypeArray(String param1String, Class<T> param1Class) {
      return new Field<>(11, true, 11, true, param1String, -1, param1Class, null);
    }
    
    public static Field<Double, Double> forDouble(String param1String) {
      return new Field<>(4, false, 4, false, param1String, -1, null, null);
    }
    
    public static Field<Double, Double> forDouble(String param1String, int param1Int) {
      return new Field<>(4, false, 4, false, param1String, param1Int, null, null);
    }
    
    public static Field<ArrayList<Double>, ArrayList<Double>> forDoubles(String param1String) {
      return new Field<>(4, true, 4, true, param1String, -1, null, null);
    }
    
    public static Field<ArrayList<Double>, ArrayList<Double>> forDoubles(String param1String, int param1Int) {
      return new Field<>(4, true, 4, true, param1String, param1Int, null, null);
    }
    
    public static Field<Float, Float> forFloat(String param1String) {
      return new Field<>(3, false, 3, false, param1String, -1, null, null);
    }
    
    public static Field<Float, Float> forFloat(String param1String, int param1Int) {
      return new Field<>(3, false, 3, false, param1String, param1Int, null, null);
    }
    
    public static Field<ArrayList<Float>, ArrayList<Float>> forFloats(String param1String) {
      return new Field<>(3, true, 3, true, param1String, -1, null, null);
    }
    
    public static Field<ArrayList<Float>, ArrayList<Float>> forFloats(String param1String, int param1Int) {
      return new Field<>(3, true, 3, true, param1String, param1Int, null, null);
    }
    
    public static Field<Integer, Integer> forInteger(String param1String) {
      return new Field<>(0, false, 0, false, param1String, -1, null, null);
    }
    
    @VisibleForTesting
    public static Field<Integer, Integer> forInteger(String param1String, int param1Int) {
      return new Field<>(0, false, 0, false, param1String, param1Int, null, null);
    }
    
    public static Field<ArrayList<Integer>, ArrayList<Integer>> forIntegers(String param1String) {
      return new Field<>(0, true, 0, true, param1String, -1, null, null);
    }
    
    @VisibleForTesting
    public static Field<ArrayList<Integer>, ArrayList<Integer>> forIntegers(String param1String, int param1Int) {
      return new Field<>(0, true, 0, true, param1String, param1Int, null, null);
    }
    
    @VisibleForTesting
    public static Field<Long, Long> forLong(String param1String) {
      return new Field<>(2, false, 2, false, param1String, -1, null, null);
    }
    
    public static Field<Long, Long> forLong(String param1String, int param1Int) {
      return new Field<>(2, false, 2, false, param1String, param1Int, null, null);
    }
    
    @VisibleForTesting
    public static Field<ArrayList<Long>, ArrayList<Long>> forLongs(String param1String) {
      return new Field<>(2, true, 2, true, param1String, -1, null, null);
    }
    
    public static Field<ArrayList<Long>, ArrayList<Long>> forLongs(String param1String, int param1Int) {
      return new Field<>(2, true, 2, true, param1String, param1Int, null, null);
    }
    
    public static Field<String, String> forString(String param1String) {
      return new Field<>(7, false, 7, false, param1String, -1, null, null);
    }
    
    public static Field<String, String> forString(String param1String, int param1Int) {
      return new Field<>(7, false, 7, false, param1String, param1Int, null, null);
    }
    
    public static Field<HashMap<String, String>, HashMap<String, String>> forStringMap(String param1String) {
      return new Field<>(10, false, 10, false, param1String, -1, null, null);
    }
    
    public static Field<HashMap<String, String>, HashMap<String, String>> forStringMap(String param1String, int param1Int) {
      return new Field<>(10, false, 10, false, param1String, param1Int, null, null);
    }
    
    public static Field<ArrayList<String>, ArrayList<String>> forStrings(String param1String) {
      return new Field<>(7, true, 7, true, param1String, -1, null, null);
    }
    
    public static Field<ArrayList<String>, ArrayList<String>> forStrings(String param1String, int param1Int) {
      return new Field<>(7, true, 7, true, param1String, param1Int, null, null);
    }
    
    public static Field withConverter(String param1String, int param1Int, FastJsonResponse.FieldConverter<?, ?> param1FieldConverter, boolean param1Boolean) {
      return new Field<>(param1FieldConverter.getTypeIn(), param1Boolean, param1FieldConverter.getTypeOut(), false, param1String, param1Int, null, param1FieldConverter);
    }
    
    public static <T extends FastJsonResponse.FieldConverter> Field withConverter(String param1String, int param1Int, Class<T> param1Class, boolean param1Boolean) {
      try {
        return withConverter(param1String, param1Int, (FastJsonResponse.FieldConverter<?, ?>)param1Class.newInstance(), param1Boolean);
      } catch (InstantiationException instantiationException) {
        throw new RuntimeException(instantiationException);
      } catch (IllegalAccessException illegalAccessException) {
        throw new RuntimeException(illegalAccessException);
      } 
    }
    
    public static Field withConverter(String param1String, FastJsonResponse.FieldConverter<?, ?> param1FieldConverter, boolean param1Boolean) {
      return withConverter(param1String, -1, param1FieldConverter, param1Boolean);
    }
    
    public static <T extends FastJsonResponse.FieldConverter> Field withConverter(String param1String, Class<T> param1Class, boolean param1Boolean) {
      return withConverter(param1String, -1, param1Class, param1Boolean);
    }
    
    private final String zzcz() {
      return (this.mConcreteTypeName == null) ? null : this.mConcreteTypeName;
    }
    
    private final ConverterWrapper zzda() {
      return (this.zzwo == null) ? null : ConverterWrapper.wrap(this.zzwo);
    }
    
    public O convert(I param1I) {
      return this.zzwo.convert(param1I);
    }
    
    public I convertBack(O param1O) {
      return this.zzwo.convertBack(param1O);
    }
    
    public Field<I, O> copyForDictionary() {
      return new Field(this.zzal, this.mTypeIn, this.mTypeInArray, this.mTypeOut, this.mTypeOutArray, this.mOutputFieldName, this.mSafeParcelableFieldId, this.mConcreteTypeName, zzda());
    }
    
    public Class<? extends FastJsonResponse> getConcreteType() {
      return this.mConcreteType;
    }
    
    public Map<String, Field<?, ?>> getConcreteTypeFieldMappingFromDictionary() {
      Preconditions.checkNotNull(this.mConcreteTypeName);
      Preconditions.checkNotNull(this.zzwn);
      return this.zzwn.getFieldMapping(this.mConcreteTypeName);
    }
    
    public String getOutputFieldName() {
      return this.mOutputFieldName;
    }
    
    public int getSafeParcelableFieldId() {
      return this.mSafeParcelableFieldId;
    }
    
    public int getTypeIn() {
      return this.mTypeIn;
    }
    
    public int getTypeOut() {
      return this.mTypeOut;
    }
    
    public int getVersionCode() {
      return this.zzal;
    }
    
    public boolean hasConverter() {
      return (this.zzwo != null);
    }
    
    public boolean isTypeInArray() {
      return this.mTypeInArray;
    }
    
    public boolean isTypeOutArray() {
      return this.mTypeOutArray;
    }
    
    public boolean isValidSafeParcelableFieldId() {
      return (this.mSafeParcelableFieldId != -1);
    }
    
    public FastJsonResponse newConcreteTypeInstance() throws InstantiationException, IllegalAccessException {
      if (this.mConcreteType == SafeParcelResponse.class) {
        Preconditions.checkNotNull(this.zzwn, "The field mapping dictionary must be set if the concrete type is a SafeParcelResponse object.");
        return new SafeParcelResponse(this.zzwn, this.mConcreteTypeName);
      } 
      return this.mConcreteType.newInstance();
    }
    
    public void setFieldMappingDictionary(FieldMappingDictionary param1FieldMappingDictionary) {
      this.zzwn = param1FieldMappingDictionary;
    }
    
    public String toString() {
      Objects.ToStringHelper toStringHelper = Objects.toStringHelper(this).add("versionCode", Integer.valueOf(this.zzal)).add("typeIn", Integer.valueOf(this.mTypeIn)).add("typeInArray", Boolean.valueOf(this.mTypeInArray)).add("typeOut", Integer.valueOf(this.mTypeOut)).add("typeOutArray", Boolean.valueOf(this.mTypeOutArray)).add("outputFieldName", this.mOutputFieldName).add("safeParcelFieldId", Integer.valueOf(this.mSafeParcelableFieldId)).add("concreteTypeName", zzcz());
      Class<? extends FastJsonResponse> clazz = getConcreteType();
      if (clazz != null)
        toStringHelper.add("concreteType.class", clazz.getCanonicalName()); 
      if (this.zzwo != null)
        toStringHelper.add("converterName", this.zzwo.getClass().getCanonicalName()); 
      return toStringHelper.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      int i = SafeParcelWriter.beginObjectHeader(param1Parcel);
      SafeParcelWriter.writeInt(param1Parcel, 1, getVersionCode());
      SafeParcelWriter.writeInt(param1Parcel, 2, getTypeIn());
      SafeParcelWriter.writeBoolean(param1Parcel, 3, isTypeInArray());
      SafeParcelWriter.writeInt(param1Parcel, 4, getTypeOut());
      SafeParcelWriter.writeBoolean(param1Parcel, 5, isTypeOutArray());
      SafeParcelWriter.writeString(param1Parcel, 6, getOutputFieldName(), false);
      SafeParcelWriter.writeInt(param1Parcel, 7, getSafeParcelableFieldId());
      SafeParcelWriter.writeString(param1Parcel, 8, zzcz(), false);
      SafeParcelWriter.writeParcelable(param1Parcel, 9, (Parcelable)zzda(), param1Int, false);
      SafeParcelWriter.finishObjectHeader(param1Parcel, i);
    }
  }
  
  public static interface FieldConverter<I, O> {
    O convert(I param1I);
    
    I convertBack(O param1O);
    
    int getTypeIn();
    
    int getTypeOut();
  }
  
  public static interface FieldType {
    public static final int BASE64 = 8;
    
    public static final int BASE64_URL_SAFE = 9;
    
    public static final int BIG_DECIMAL = 5;
    
    public static final int BIG_INTEGER = 1;
    
    public static final int BOOLEAN = 6;
    
    public static final int CONCRETE_TYPE = 11;
    
    public static final int DOUBLE = 4;
    
    public static final int FLOAT = 3;
    
    public static final int INT = 0;
    
    public static final int LONG = 2;
    
    public static final int STRING = 7;
    
    public static final int STRING_MAP = 10;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\server\response\FastJsonResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */