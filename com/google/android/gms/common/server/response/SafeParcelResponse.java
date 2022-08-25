package com.google.android.gms.common.server.response;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.MapUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Class(creator = "SafeParcelResponseCreator")
@VisibleForTesting
public class SafeParcelResponse extends FastSafeParcelableJsonResponse {
  public static final Parcelable.Creator<SafeParcelResponse> CREATOR = new SafeParcelResponseCreator();
  
  private final String mClassName;
  
  @VersionField(getter = "getVersionCode", id = 1)
  private final int zzal;
  
  @Field(getter = "getFieldMappingDictionary", id = 3)
  private final FieldMappingDictionary zzwn;
  
  @Field(getter = "getParcel", id = 2)
  private final Parcel zzxq;
  
  private final int zzxr;
  
  private int zzxs;
  
  private int zzxt;
  
  @Constructor
  SafeParcelResponse(@Param(id = 1) int paramInt, @Param(id = 2) Parcel paramParcel, @Param(id = 3) FieldMappingDictionary paramFieldMappingDictionary) {
    String str;
    this.zzal = paramInt;
    this.zzxq = (Parcel)Preconditions.checkNotNull(paramParcel);
    this.zzxr = 2;
    this.zzwn = paramFieldMappingDictionary;
    if (this.zzwn == null) {
      paramParcel = null;
    } else {
      str = this.zzwn.getRootClassName();
    } 
    this.mClassName = str;
    this.zzxs = 2;
  }
  
  private SafeParcelResponse(SafeParcelable paramSafeParcelable, FieldMappingDictionary paramFieldMappingDictionary, String paramString) {
    this.zzal = 1;
    this.zzxq = Parcel.obtain();
    paramSafeParcelable.writeToParcel(this.zzxq, 0);
    this.zzxr = 1;
    this.zzwn = (FieldMappingDictionary)Preconditions.checkNotNull(paramFieldMappingDictionary);
    this.mClassName = (String)Preconditions.checkNotNull(paramString);
    this.zzxs = 2;
  }
  
  public SafeParcelResponse(FieldMappingDictionary paramFieldMappingDictionary) {
    this(paramFieldMappingDictionary, paramFieldMappingDictionary.getRootClassName());
  }
  
  public SafeParcelResponse(FieldMappingDictionary paramFieldMappingDictionary, String paramString) {
    this.zzal = 1;
    this.zzxq = Parcel.obtain();
    this.zzxr = 0;
    this.zzwn = (FieldMappingDictionary)Preconditions.checkNotNull(paramFieldMappingDictionary);
    this.mClassName = (String)Preconditions.checkNotNull(paramString);
    this.zzxs = 0;
  }
  
  public static HashMap<String, String> convertBundleToStringMap(Bundle paramBundle) {
    HashMap<Object, Object> hashMap = new HashMap<>();
    for (String str : paramBundle.keySet())
      hashMap.put(str, paramBundle.getString(str)); 
    return (HashMap)hashMap;
  }
  
  public static Bundle convertStringMapToBundle(HashMap<String, String> paramHashMap) {
    Bundle bundle = new Bundle();
    for (String str : paramHashMap.keySet())
      bundle.putString(str, paramHashMap.get(str)); 
    return bundle;
  }
  
  public static <T extends FastJsonResponse & SafeParcelable> SafeParcelResponse from(T paramT) {
    String str = paramT.getClass().getCanonicalName();
    FieldMappingDictionary fieldMappingDictionary = zza((FastJsonResponse)paramT);
    return new SafeParcelResponse((SafeParcelable)paramT, fieldMappingDictionary, str);
  }
  
  public static FieldMappingDictionary generateDictionary(Class<? extends FastJsonResponse> paramClass) {
    String str;
    try {
      return zza(paramClass.newInstance());
    } catch (InstantiationException instantiationException) {
      str = String.valueOf(paramClass.getCanonicalName());
      if (str.length() != 0) {
        str = "Could not instantiate an object of type ".concat(str);
      } else {
        str = new String("Could not instantiate an object of type ");
      } 
      throw new IllegalStateException(str, instantiationException);
    } catch (IllegalAccessException illegalAccessException) {
      str = String.valueOf(str.getCanonicalName());
      if (str.length() != 0) {
        str = "Could not access object of type ".concat(str);
      } else {
        str = new String("Could not access object of type ");
      } 
      throw new IllegalStateException(str, illegalAccessException);
    } 
  }
  
  private static FieldMappingDictionary zza(FastJsonResponse paramFastJsonResponse) {
    FieldMappingDictionary fieldMappingDictionary = new FieldMappingDictionary((Class)paramFastJsonResponse.getClass());
    zza(fieldMappingDictionary, paramFastJsonResponse);
    fieldMappingDictionary.copyInternalFieldMappings();
    fieldMappingDictionary.linkFields();
    return fieldMappingDictionary;
  }
  
  private static void zza(FieldMappingDictionary paramFieldMappingDictionary, FastJsonResponse paramFastJsonResponse) {
    Class<?> clazz = paramFastJsonResponse.getClass();
    if (!paramFieldMappingDictionary.hasFieldMappingForClass((Class)clazz)) {
      Map<String, FastJsonResponse.Field<?, ?>> map = paramFastJsonResponse.getFieldMappings();
      paramFieldMappingDictionary.put((Class)clazz, map);
      Iterator<String> iterator = map.keySet().iterator();
      while (iterator.hasNext()) {
        FastJsonResponse.Field field = map.get(iterator.next());
        clazz = field.getConcreteType();
        if (clazz != null)
          try {
            zza(paramFieldMappingDictionary, (FastJsonResponse)clazz.newInstance());
          } catch (InstantiationException instantiationException) {
            String str = String.valueOf(field.getConcreteType().getCanonicalName());
            if (str.length() != 0) {
              str = "Could not instantiate an object of type ".concat(str);
            } else {
              str = new String("Could not instantiate an object of type ");
            } 
            throw new IllegalStateException(str, instantiationException);
          } catch (IllegalAccessException illegalAccessException) {
            String str = String.valueOf(field.getConcreteType().getCanonicalName());
            if (str.length() != 0) {
              str = "Could not access object of type ".concat(str);
            } else {
              str = new String("Could not access object of type ");
            } 
            throw new IllegalStateException(str, illegalAccessException);
          }  
      } 
    } 
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, Object paramObject) {
    switch (paramInt) {
      default:
        paramStringBuilder = new StringBuilder(26);
        paramStringBuilder.append("Unknown type = ");
        paramStringBuilder.append(paramInt);
        throw new IllegalArgumentException(paramStringBuilder.toString());
      case 11:
        throw new IllegalArgumentException("Method does not accept concrete type.");
      case 10:
        MapUtils.writeStringMapToJson(paramStringBuilder, (HashMap)paramObject);
        return;
      case 9:
        paramStringBuilder.append("\"");
        paramStringBuilder.append(Base64Utils.encodeUrlSafe((byte[])paramObject));
        paramStringBuilder.append("\"");
        return;
      case 8:
        paramStringBuilder.append("\"");
        paramStringBuilder.append(Base64Utils.encode((byte[])paramObject));
        paramStringBuilder.append("\"");
        return;
      case 7:
        paramStringBuilder.append("\"");
        paramStringBuilder.append(JsonUtils.escapeString(paramObject.toString()));
        paramStringBuilder.append("\"");
        return;
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
        break;
    } 
    paramStringBuilder.append(paramObject);
  }
  
  private final void zza(StringBuilder paramStringBuilder, Map<String, FastJsonResponse.Field<?, ?>> paramMap, Parcel paramParcel) {
    // Byte code:
    //   0: new android/util/SparseArray
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #4
    //   9: aload_2
    //   10: invokeinterface entrySet : ()Ljava/util/Set;
    //   15: invokeinterface iterator : ()Ljava/util/Iterator;
    //   20: astore #5
    //   22: aload #5
    //   24: invokeinterface hasNext : ()Z
    //   29: ifeq -> 64
    //   32: aload #5
    //   34: invokeinterface next : ()Ljava/lang/Object;
    //   39: checkcast java/util/Map$Entry
    //   42: astore_2
    //   43: aload #4
    //   45: aload_2
    //   46: invokeinterface getValue : ()Ljava/lang/Object;
    //   51: checkcast com/google/android/gms/common/server/response/FastJsonResponse$Field
    //   54: invokevirtual getSafeParcelableFieldId : ()I
    //   57: aload_2
    //   58: invokevirtual put : (ILjava/lang/Object;)V
    //   61: goto -> 22
    //   64: aload_1
    //   65: bipush #123
    //   67: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   70: pop
    //   71: aload_3
    //   72: invokestatic validateObjectHeader : (Landroid/os/Parcel;)I
    //   75: istore #6
    //   77: iconst_0
    //   78: istore #7
    //   80: aload_3
    //   81: invokevirtual dataPosition : ()I
    //   84: iload #6
    //   86: if_icmpge -> 1147
    //   89: aload_3
    //   90: invokestatic readHeader : (Landroid/os/Parcel;)I
    //   93: istore #8
    //   95: aload #4
    //   97: iload #8
    //   99: invokestatic getFieldId : (I)I
    //   102: invokevirtual get : (I)Ljava/lang/Object;
    //   105: checkcast java/util/Map$Entry
    //   108: astore #5
    //   110: aload #5
    //   112: ifnull -> 80
    //   115: iload #7
    //   117: ifeq -> 128
    //   120: aload_1
    //   121: ldc_w ','
    //   124: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   127: pop
    //   128: aload #5
    //   130: invokeinterface getKey : ()Ljava/lang/Object;
    //   135: checkcast java/lang/String
    //   138: astore_2
    //   139: aload #5
    //   141: invokeinterface getValue : ()Ljava/lang/Object;
    //   146: checkcast com/google/android/gms/common/server/response/FastJsonResponse$Field
    //   149: astore #5
    //   151: aload_1
    //   152: ldc_w '"'
    //   155: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: pop
    //   159: aload_1
    //   160: aload_2
    //   161: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: pop
    //   165: aload_1
    //   166: ldc_w '":'
    //   169: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: pop
    //   173: aload #5
    //   175: invokevirtual hasConverter : ()Z
    //   178: ifeq -> 434
    //   181: aload #5
    //   183: invokevirtual getTypeOut : ()I
    //   186: tableswitch default -> 248, 0 -> 407, 1 -> 397, 2 -> 384, 3 -> 371, 4 -> 358, 5 -> 348, 6 -> 335, 7 -> 325, 8 -> 315, 9 -> 315, 10 -> 302, 11 -> 292
    //   248: aload #5
    //   250: invokevirtual getTypeOut : ()I
    //   253: istore #7
    //   255: new java/lang/StringBuilder
    //   258: dup
    //   259: bipush #36
    //   261: invokespecial <init> : (I)V
    //   264: astore_1
    //   265: aload_1
    //   266: ldc_w 'Unknown field out type = '
    //   269: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   272: pop
    //   273: aload_1
    //   274: iload #7
    //   276: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   279: pop
    //   280: new java/lang/IllegalArgumentException
    //   283: dup
    //   284: aload_1
    //   285: invokevirtual toString : ()Ljava/lang/String;
    //   288: invokespecial <init> : (Ljava/lang/String;)V
    //   291: athrow
    //   292: new java/lang/IllegalArgumentException
    //   295: dup
    //   296: ldc 'Method does not accept concrete type.'
    //   298: invokespecial <init> : (Ljava/lang/String;)V
    //   301: athrow
    //   302: aload_3
    //   303: iload #8
    //   305: invokestatic createBundle : (Landroid/os/Parcel;I)Landroid/os/Bundle;
    //   308: invokestatic convertBundleToStringMap : (Landroid/os/Bundle;)Ljava/util/HashMap;
    //   311: astore_2
    //   312: goto -> 417
    //   315: aload_3
    //   316: iload #8
    //   318: invokestatic createByteArray : (Landroid/os/Parcel;I)[B
    //   321: astore_2
    //   322: goto -> 417
    //   325: aload_3
    //   326: iload #8
    //   328: invokestatic createString : (Landroid/os/Parcel;I)Ljava/lang/String;
    //   331: astore_2
    //   332: goto -> 417
    //   335: aload_3
    //   336: iload #8
    //   338: invokestatic readBoolean : (Landroid/os/Parcel;I)Z
    //   341: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   344: astore_2
    //   345: goto -> 417
    //   348: aload_3
    //   349: iload #8
    //   351: invokestatic createBigDecimal : (Landroid/os/Parcel;I)Ljava/math/BigDecimal;
    //   354: astore_2
    //   355: goto -> 417
    //   358: aload_3
    //   359: iload #8
    //   361: invokestatic readDouble : (Landroid/os/Parcel;I)D
    //   364: invokestatic valueOf : (D)Ljava/lang/Double;
    //   367: astore_2
    //   368: goto -> 417
    //   371: aload_3
    //   372: iload #8
    //   374: invokestatic readFloat : (Landroid/os/Parcel;I)F
    //   377: invokestatic valueOf : (F)Ljava/lang/Float;
    //   380: astore_2
    //   381: goto -> 417
    //   384: aload_3
    //   385: iload #8
    //   387: invokestatic readLong : (Landroid/os/Parcel;I)J
    //   390: invokestatic valueOf : (J)Ljava/lang/Long;
    //   393: astore_2
    //   394: goto -> 417
    //   397: aload_3
    //   398: iload #8
    //   400: invokestatic createBigInteger : (Landroid/os/Parcel;I)Ljava/math/BigInteger;
    //   403: astore_2
    //   404: goto -> 417
    //   407: aload_3
    //   408: iload #8
    //   410: invokestatic readInt : (Landroid/os/Parcel;I)I
    //   413: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   416: astore_2
    //   417: aload_0
    //   418: aload_1
    //   419: aload #5
    //   421: aload_0
    //   422: aload #5
    //   424: aload_2
    //   425: invokevirtual getOriginalValue : (Lcom/google/android/gms/common/server/response/FastJsonResponse$Field;Ljava/lang/Object;)Ljava/lang/Object;
    //   428: invokespecial zzb : (Ljava/lang/StringBuilder;Lcom/google/android/gms/common/server/response/FastJsonResponse$Field;Ljava/lang/Object;)V
    //   431: goto -> 1141
    //   434: aload #5
    //   436: invokevirtual isTypeOutArray : ()Z
    //   439: ifeq -> 707
    //   442: aload_1
    //   443: ldc_w '['
    //   446: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   449: pop
    //   450: aload #5
    //   452: invokevirtual getTypeOut : ()I
    //   455: tableswitch default -> 516, 0 -> 690, 1 -> 675, 2 -> 662, 3 -> 649, 4 -> 636, 5 -> 626, 6 -> 613, 7 -> 600, 8 -> 589, 9 -> 589, 10 -> 589, 11 -> 527
    //   516: new java/lang/IllegalStateException
    //   519: dup
    //   520: ldc_w 'Unknown field type out.'
    //   523: invokespecial <init> : (Ljava/lang/String;)V
    //   526: athrow
    //   527: aload_3
    //   528: iload #8
    //   530: invokestatic createParcelArray : (Landroid/os/Parcel;I)[Landroid/os/Parcel;
    //   533: astore_2
    //   534: aload_2
    //   535: arraylength
    //   536: istore #8
    //   538: iconst_0
    //   539: istore #7
    //   541: iload #7
    //   543: iload #8
    //   545: if_icmpge -> 700
    //   548: iload #7
    //   550: ifle -> 561
    //   553: aload_1
    //   554: ldc_w ','
    //   557: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   560: pop
    //   561: aload_2
    //   562: iload #7
    //   564: aaload
    //   565: iconst_0
    //   566: invokevirtual setDataPosition : (I)V
    //   569: aload_0
    //   570: aload_1
    //   571: aload #5
    //   573: invokevirtual getConcreteTypeFieldMappingFromDictionary : ()Ljava/util/Map;
    //   576: aload_2
    //   577: iload #7
    //   579: aaload
    //   580: invokespecial zza : (Ljava/lang/StringBuilder;Ljava/util/Map;Landroid/os/Parcel;)V
    //   583: iinc #7, 1
    //   586: goto -> 541
    //   589: new java/lang/UnsupportedOperationException
    //   592: dup
    //   593: ldc_w 'List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported'
    //   596: invokespecial <init> : (Ljava/lang/String;)V
    //   599: athrow
    //   600: aload_1
    //   601: aload_3
    //   602: iload #8
    //   604: invokestatic createStringArray : (Landroid/os/Parcel;I)[Ljava/lang/String;
    //   607: invokestatic writeStringArray : (Ljava/lang/StringBuilder;[Ljava/lang/String;)V
    //   610: goto -> 700
    //   613: aload_1
    //   614: aload_3
    //   615: iload #8
    //   617: invokestatic createBooleanArray : (Landroid/os/Parcel;I)[Z
    //   620: invokestatic writeArray : (Ljava/lang/StringBuilder;[Z)V
    //   623: goto -> 700
    //   626: aload_3
    //   627: iload #8
    //   629: invokestatic createBigDecimalArray : (Landroid/os/Parcel;I)[Ljava/math/BigDecimal;
    //   632: astore_2
    //   633: goto -> 682
    //   636: aload_1
    //   637: aload_3
    //   638: iload #8
    //   640: invokestatic createDoubleArray : (Landroid/os/Parcel;I)[D
    //   643: invokestatic writeArray : (Ljava/lang/StringBuilder;[D)V
    //   646: goto -> 700
    //   649: aload_1
    //   650: aload_3
    //   651: iload #8
    //   653: invokestatic createFloatArray : (Landroid/os/Parcel;I)[F
    //   656: invokestatic writeArray : (Ljava/lang/StringBuilder;[F)V
    //   659: goto -> 700
    //   662: aload_1
    //   663: aload_3
    //   664: iload #8
    //   666: invokestatic createLongArray : (Landroid/os/Parcel;I)[J
    //   669: invokestatic writeArray : (Ljava/lang/StringBuilder;[J)V
    //   672: goto -> 700
    //   675: aload_3
    //   676: iload #8
    //   678: invokestatic createBigIntegerArray : (Landroid/os/Parcel;I)[Ljava/math/BigInteger;
    //   681: astore_2
    //   682: aload_1
    //   683: aload_2
    //   684: invokestatic writeArray : (Ljava/lang/StringBuilder;[Ljava/lang/Object;)V
    //   687: goto -> 700
    //   690: aload_1
    //   691: aload_3
    //   692: iload #8
    //   694: invokestatic createIntArray : (Landroid/os/Parcel;I)[I
    //   697: invokestatic writeArray : (Ljava/lang/StringBuilder;[I)V
    //   700: ldc_w ']'
    //   703: astore_2
    //   704: goto -> 1039
    //   707: aload #5
    //   709: invokevirtual getTypeOut : ()I
    //   712: tableswitch default -> 776, 0 -> 1130, 1 -> 1114, 2 -> 1100, 3 -> 1086, 4 -> 1072, 5 -> 1062, 6 -> 1048, 7 -> 1009, 8 -> 986, 9 -> 963, 10 -> 813, 11 -> 787
    //   776: new java/lang/IllegalStateException
    //   779: dup
    //   780: ldc_w 'Unknown field type out'
    //   783: invokespecial <init> : (Ljava/lang/String;)V
    //   786: athrow
    //   787: aload_3
    //   788: iload #8
    //   790: invokestatic createParcel : (Landroid/os/Parcel;I)Landroid/os/Parcel;
    //   793: astore_2
    //   794: aload_2
    //   795: iconst_0
    //   796: invokevirtual setDataPosition : (I)V
    //   799: aload_0
    //   800: aload_1
    //   801: aload #5
    //   803: invokevirtual getConcreteTypeFieldMappingFromDictionary : ()Ljava/util/Map;
    //   806: aload_2
    //   807: invokespecial zza : (Ljava/lang/StringBuilder;Ljava/util/Map;Landroid/os/Parcel;)V
    //   810: goto -> 1141
    //   813: aload_3
    //   814: iload #8
    //   816: invokestatic createBundle : (Landroid/os/Parcel;I)Landroid/os/Bundle;
    //   819: astore_2
    //   820: aload_2
    //   821: invokevirtual keySet : ()Ljava/util/Set;
    //   824: astore #5
    //   826: aload #5
    //   828: invokeinterface size : ()I
    //   833: pop
    //   834: aload_1
    //   835: ldc_w '{'
    //   838: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   841: pop
    //   842: aload #5
    //   844: invokeinterface iterator : ()Ljava/util/Iterator;
    //   849: astore #5
    //   851: iconst_1
    //   852: istore #7
    //   854: aload #5
    //   856: invokeinterface hasNext : ()Z
    //   861: ifeq -> 956
    //   864: aload #5
    //   866: invokeinterface next : ()Ljava/lang/Object;
    //   871: checkcast java/lang/String
    //   874: astore #9
    //   876: iload #7
    //   878: ifne -> 889
    //   881: aload_1
    //   882: ldc_w ','
    //   885: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   888: pop
    //   889: aload_1
    //   890: ldc_w '"'
    //   893: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   896: pop
    //   897: aload_1
    //   898: aload #9
    //   900: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   903: pop
    //   904: aload_1
    //   905: ldc_w '"'
    //   908: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   911: pop
    //   912: aload_1
    //   913: ldc_w ':'
    //   916: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   919: pop
    //   920: aload_1
    //   921: ldc_w '"'
    //   924: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   927: pop
    //   928: aload_1
    //   929: aload_2
    //   930: aload #9
    //   932: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   935: invokestatic escapeString : (Ljava/lang/String;)Ljava/lang/String;
    //   938: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   941: pop
    //   942: aload_1
    //   943: ldc_w '"'
    //   946: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   949: pop
    //   950: iconst_0
    //   951: istore #7
    //   953: goto -> 854
    //   956: ldc_w '}'
    //   959: astore_2
    //   960: goto -> 1039
    //   963: aload_3
    //   964: iload #8
    //   966: invokestatic createByteArray : (Landroid/os/Parcel;I)[B
    //   969: astore_2
    //   970: aload_1
    //   971: ldc_w '"'
    //   974: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   977: pop
    //   978: aload_2
    //   979: invokestatic encodeUrlSafe : ([B)Ljava/lang/String;
    //   982: astore_2
    //   983: goto -> 1029
    //   986: aload_3
    //   987: iload #8
    //   989: invokestatic createByteArray : (Landroid/os/Parcel;I)[B
    //   992: astore_2
    //   993: aload_1
    //   994: ldc_w '"'
    //   997: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1000: pop
    //   1001: aload_2
    //   1002: invokestatic encode : ([B)Ljava/lang/String;
    //   1005: astore_2
    //   1006: goto -> 1029
    //   1009: aload_3
    //   1010: iload #8
    //   1012: invokestatic createString : (Landroid/os/Parcel;I)Ljava/lang/String;
    //   1015: astore_2
    //   1016: aload_1
    //   1017: ldc_w '"'
    //   1020: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1023: pop
    //   1024: aload_2
    //   1025: invokestatic escapeString : (Ljava/lang/String;)Ljava/lang/String;
    //   1028: astore_2
    //   1029: aload_1
    //   1030: aload_2
    //   1031: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1034: pop
    //   1035: ldc_w '"'
    //   1038: astore_2
    //   1039: aload_1
    //   1040: aload_2
    //   1041: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1044: pop
    //   1045: goto -> 1141
    //   1048: aload_1
    //   1049: aload_3
    //   1050: iload #8
    //   1052: invokestatic readBoolean : (Landroid/os/Parcel;I)Z
    //   1055: invokevirtual append : (Z)Ljava/lang/StringBuilder;
    //   1058: pop
    //   1059: goto -> 1141
    //   1062: aload_3
    //   1063: iload #8
    //   1065: invokestatic createBigDecimal : (Landroid/os/Parcel;I)Ljava/math/BigDecimal;
    //   1068: astore_2
    //   1069: goto -> 1121
    //   1072: aload_1
    //   1073: aload_3
    //   1074: iload #8
    //   1076: invokestatic readDouble : (Landroid/os/Parcel;I)D
    //   1079: invokevirtual append : (D)Ljava/lang/StringBuilder;
    //   1082: pop
    //   1083: goto -> 1141
    //   1086: aload_1
    //   1087: aload_3
    //   1088: iload #8
    //   1090: invokestatic readFloat : (Landroid/os/Parcel;I)F
    //   1093: invokevirtual append : (F)Ljava/lang/StringBuilder;
    //   1096: pop
    //   1097: goto -> 1141
    //   1100: aload_1
    //   1101: aload_3
    //   1102: iload #8
    //   1104: invokestatic readLong : (Landroid/os/Parcel;I)J
    //   1107: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   1110: pop
    //   1111: goto -> 1141
    //   1114: aload_3
    //   1115: iload #8
    //   1117: invokestatic createBigInteger : (Landroid/os/Parcel;I)Ljava/math/BigInteger;
    //   1120: astore_2
    //   1121: aload_1
    //   1122: aload_2
    //   1123: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1126: pop
    //   1127: goto -> 1141
    //   1130: aload_1
    //   1131: aload_3
    //   1132: iload #8
    //   1134: invokestatic readInt : (Landroid/os/Parcel;I)I
    //   1137: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1140: pop
    //   1141: iconst_1
    //   1142: istore #7
    //   1144: goto -> 80
    //   1147: aload_3
    //   1148: invokevirtual dataPosition : ()I
    //   1151: iload #6
    //   1153: if_icmpeq -> 1194
    //   1156: new java/lang/StringBuilder
    //   1159: dup
    //   1160: bipush #37
    //   1162: invokespecial <init> : (I)V
    //   1165: astore_1
    //   1166: aload_1
    //   1167: ldc_w 'Overread allowed size end='
    //   1170: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1173: pop
    //   1174: aload_1
    //   1175: iload #6
    //   1177: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1180: pop
    //   1181: new com/google/android/gms/common/internal/safeparcel/SafeParcelReader$ParseException
    //   1184: dup
    //   1185: aload_1
    //   1186: invokevirtual toString : ()Ljava/lang/String;
    //   1189: aload_3
    //   1190: invokespecial <init> : (Ljava/lang/String;Landroid/os/Parcel;)V
    //   1193: athrow
    //   1194: aload_1
    //   1195: bipush #125
    //   1197: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   1200: pop
    //   1201: return
  }
  
  private final void zzb(FastJsonResponse.Field<?, ?> paramField) {
    if (!paramField.isValidSafeParcelableFieldId())
      throw new IllegalStateException("Field does not have a valid safe parcelable field id."); 
    if (this.zzxq == null)
      throw new IllegalStateException("Internal Parcel object is null."); 
    switch (this.zzxs) {
      default:
        throw new IllegalStateException("Unknown parse state in SafeParcelResponse.");
      case 2:
        throw new IllegalStateException("Attempted to parse JSON with a SafeParcelResponse object that is already filled with data.");
      case 1:
        return;
      case 0:
        break;
    } 
    this.zzxt = SafeParcelWriter.beginObjectHeader(this.zzxq);
    this.zzxs = 1;
  }
  
  private final void zzb(StringBuilder paramStringBuilder, FastJsonResponse.Field<?, ?> paramField, Object paramObject) {
    if (paramField.isTypeInArray()) {
      paramObject = paramObject;
      paramStringBuilder.append("[");
      int i = paramObject.size();
      for (byte b = 0; b < i; b++) {
        if (b != 0)
          paramStringBuilder.append(","); 
        zza(paramStringBuilder, paramField.getTypeIn(), paramObject.get(b));
      } 
      paramStringBuilder.append("]");
      return;
    } 
    zza(paramStringBuilder, paramField.getTypeIn(), paramObject);
  }
  
  public <T extends FastJsonResponse> void addConcreteTypeArrayInternal(FastJsonResponse.Field<?, ?> paramField, String paramString, ArrayList<T> paramArrayList) {
    zzb(paramField);
    ArrayList<Parcel> arrayList = new ArrayList();
    paramArrayList.size();
    paramArrayList = paramArrayList;
    int i = paramArrayList.size();
    byte b = 0;
    while (b < i) {
      T t = paramArrayList.get(b);
      b++;
      arrayList.add(((SafeParcelResponse)t).getParcel());
    } 
    SafeParcelWriter.writeParcelList(this.zzxq, paramField.getSafeParcelableFieldId(), arrayList, true);
  }
  
  public <T extends FastJsonResponse> void addConcreteTypeInternal(FastJsonResponse.Field<?, ?> paramField, String paramString, T paramT) {
    zzb(paramField);
    Parcel parcel = ((SafeParcelResponse)paramT).getParcel();
    SafeParcelWriter.writeParcel(this.zzxq, paramField.getSafeParcelableFieldId(), parcel, true);
  }
  
  public Map<String, FastJsonResponse.Field<?, ?>> getFieldMappings() {
    return (this.zzwn == null) ? null : this.zzwn.getFieldMapping(this.mClassName);
  }
  
  public Parcel getParcel() {
    switch (this.zzxs) {
      default:
        return this.zzxq;
      case 0:
        this.zzxt = SafeParcelWriter.beginObjectHeader(this.zzxq);
        break;
      case 1:
        break;
    } 
    SafeParcelWriter.finishObjectHeader(this.zzxq, this.zzxt);
    this.zzxs = 2;
  }
  
  public Object getValueObject(String paramString) {
    throw new UnsupportedOperationException("Converting to JSON does not require this method.");
  }
  
  public int getVersionCode() {
    return this.zzal;
  }
  
  public <T extends SafeParcelable> T inflate(Parcelable.Creator<T> paramCreator) {
    Parcel parcel = getParcel();
    parcel.setDataPosition(0);
    return (T)paramCreator.createFromParcel(parcel);
  }
  
  public boolean isPrimitiveFieldSet(String paramString) {
    throw new UnsupportedOperationException("Converting to JSON does not require this method.");
  }
  
  protected void setBigDecimalInternal(FastJsonResponse.Field<?, ?> paramField, String paramString, BigDecimal paramBigDecimal) {
    zzb(paramField);
    SafeParcelWriter.writeBigDecimal(this.zzxq, paramField.getSafeParcelableFieldId(), paramBigDecimal, true);
  }
  
  protected void setBigDecimalsInternal(FastJsonResponse.Field<?, ?> paramField, String paramString, ArrayList<BigDecimal> paramArrayList) {
    zzb(paramField);
    int i = paramArrayList.size();
    BigDecimal[] arrayOfBigDecimal = new BigDecimal[i];
    for (byte b = 0; b < i; b++)
      arrayOfBigDecimal[b] = paramArrayList.get(b); 
    SafeParcelWriter.writeBigDecimalArray(this.zzxq, paramField.getSafeParcelableFieldId(), arrayOfBigDecimal, true);
  }
  
  protected void setBigIntegerInternal(FastJsonResponse.Field<?, ?> paramField, String paramString, BigInteger paramBigInteger) {
    zzb(paramField);
    SafeParcelWriter.writeBigInteger(this.zzxq, paramField.getSafeParcelableFieldId(), paramBigInteger, true);
  }
  
  protected void setBigIntegersInternal(FastJsonResponse.Field<?, ?> paramField, String paramString, ArrayList<BigInteger> paramArrayList) {
    zzb(paramField);
    int i = paramArrayList.size();
    BigInteger[] arrayOfBigInteger = new BigInteger[i];
    for (byte b = 0; b < i; b++)
      arrayOfBigInteger[b] = paramArrayList.get(b); 
    SafeParcelWriter.writeBigIntegerArray(this.zzxq, paramField.getSafeParcelableFieldId(), arrayOfBigInteger, true);
  }
  
  protected void setBooleanInternal(FastJsonResponse.Field<?, ?> paramField, String paramString, boolean paramBoolean) {
    zzb(paramField);
    SafeParcelWriter.writeBoolean(this.zzxq, paramField.getSafeParcelableFieldId(), paramBoolean);
  }
  
  protected void setBooleansInternal(FastJsonResponse.Field<?, ?> paramField, String paramString, ArrayList<Boolean> paramArrayList) {
    zzb(paramField);
    int i = paramArrayList.size();
    boolean[] arrayOfBoolean = new boolean[i];
    for (byte b = 0; b < i; b++)
      arrayOfBoolean[b] = ((Boolean)paramArrayList.get(b)).booleanValue(); 
    SafeParcelWriter.writeBooleanArray(this.zzxq, paramField.getSafeParcelableFieldId(), arrayOfBoolean, true);
  }
  
  protected void setDecodedBytesInternal(FastJsonResponse.Field<?, ?> paramField, String paramString, byte[] paramArrayOfbyte) {
    zzb(paramField);
    SafeParcelWriter.writeByteArray(this.zzxq, paramField.getSafeParcelableFieldId(), paramArrayOfbyte, true);
  }
  
  protected void setDoubleInternal(FastJsonResponse.Field<?, ?> paramField, String paramString, double paramDouble) {
    zzb(paramField);
    SafeParcelWriter.writeDouble(this.zzxq, paramField.getSafeParcelableFieldId(), paramDouble);
  }
  
  protected void setDoublesInternal(FastJsonResponse.Field<?, ?> paramField, String paramString, ArrayList<Double> paramArrayList) {
    zzb(paramField);
    int i = paramArrayList.size();
    double[] arrayOfDouble = new double[i];
    for (byte b = 0; b < i; b++)
      arrayOfDouble[b] = ((Double)paramArrayList.get(b)).doubleValue(); 
    SafeParcelWriter.writeDoubleArray(this.zzxq, paramField.getSafeParcelableFieldId(), arrayOfDouble, true);
  }
  
  protected void setFloatInternal(FastJsonResponse.Field<?, ?> paramField, String paramString, float paramFloat) {
    zzb(paramField);
    SafeParcelWriter.writeFloat(this.zzxq, paramField.getSafeParcelableFieldId(), paramFloat);
  }
  
  protected void setFloatsInternal(FastJsonResponse.Field<?, ?> paramField, String paramString, ArrayList<Float> paramArrayList) {
    zzb(paramField);
    int i = paramArrayList.size();
    float[] arrayOfFloat = new float[i];
    for (byte b = 0; b < i; b++)
      arrayOfFloat[b] = ((Float)paramArrayList.get(b)).floatValue(); 
    SafeParcelWriter.writeFloatArray(this.zzxq, paramField.getSafeParcelableFieldId(), arrayOfFloat, true);
  }
  
  protected void setIntegerInternal(FastJsonResponse.Field<?, ?> paramField, String paramString, int paramInt) {
    zzb(paramField);
    SafeParcelWriter.writeInt(this.zzxq, paramField.getSafeParcelableFieldId(), paramInt);
  }
  
  protected void setIntegersInternal(FastJsonResponse.Field<?, ?> paramField, String paramString, ArrayList<Integer> paramArrayList) {
    zzb(paramField);
    int i = paramArrayList.size();
    int[] arrayOfInt = new int[i];
    for (byte b = 0; b < i; b++)
      arrayOfInt[b] = ((Integer)paramArrayList.get(b)).intValue(); 
    SafeParcelWriter.writeIntArray(this.zzxq, paramField.getSafeParcelableFieldId(), arrayOfInt, true);
  }
  
  protected void setLongInternal(FastJsonResponse.Field<?, ?> paramField, String paramString, long paramLong) {
    zzb(paramField);
    SafeParcelWriter.writeLong(this.zzxq, paramField.getSafeParcelableFieldId(), paramLong);
  }
  
  protected void setLongsInternal(FastJsonResponse.Field<?, ?> paramField, String paramString, ArrayList<Long> paramArrayList) {
    zzb(paramField);
    int i = paramArrayList.size();
    long[] arrayOfLong = new long[i];
    for (byte b = 0; b < i; b++)
      arrayOfLong[b] = ((Long)paramArrayList.get(b)).longValue(); 
    SafeParcelWriter.writeLongArray(this.zzxq, paramField.getSafeParcelableFieldId(), arrayOfLong, true);
  }
  
  protected void setStringInternal(FastJsonResponse.Field<?, ?> paramField, String paramString1, String paramString2) {
    zzb(paramField);
    SafeParcelWriter.writeString(this.zzxq, paramField.getSafeParcelableFieldId(), paramString2, true);
  }
  
  protected void setStringMapInternal(FastJsonResponse.Field<?, ?> paramField, String paramString, Map<String, String> paramMap) {
    zzb(paramField);
    Bundle bundle = new Bundle();
    for (String str : paramMap.keySet())
      bundle.putString(str, paramMap.get(str)); 
    SafeParcelWriter.writeBundle(this.zzxq, paramField.getSafeParcelableFieldId(), bundle, true);
  }
  
  protected void setStringsInternal(FastJsonResponse.Field<?, ?> paramField, String paramString, ArrayList<String> paramArrayList) {
    zzb(paramField);
    int i = paramArrayList.size();
    String[] arrayOfString = new String[i];
    for (byte b = 0; b < i; b++)
      arrayOfString[b] = paramArrayList.get(b); 
    SafeParcelWriter.writeStringArray(this.zzxq, paramField.getSafeParcelableFieldId(), arrayOfString, true);
  }
  
  public String toString() {
    Preconditions.checkNotNull(this.zzwn, "Cannot convert to JSON on client side.");
    Parcel parcel = getParcel();
    parcel.setDataPosition(0);
    StringBuilder stringBuilder = new StringBuilder(100);
    zza(stringBuilder, this.zzwn.getFieldMapping(this.mClassName), parcel);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    StringBuilder stringBuilder;
    FieldMappingDictionary fieldMappingDictionary;
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, getVersionCode());
    SafeParcelWriter.writeParcel(paramParcel, 2, getParcel(), false);
    switch (this.zzxr) {
      default:
        paramInt = this.zzxr;
        stringBuilder = new StringBuilder(34);
        stringBuilder.append("Invalid creation type: ");
        stringBuilder.append(paramInt);
        throw new IllegalStateException(stringBuilder.toString());
      case 1:
      case 2:
        fieldMappingDictionary = this.zzwn;
        break;
      case 0:
        fieldMappingDictionary = null;
        break;
    } 
    SafeParcelWriter.writeParcelable((Parcel)stringBuilder, 3, (Parcelable)fieldMappingDictionary, paramInt, false);
    SafeParcelWriter.finishObjectHeader((Parcel)stringBuilder, i);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\server\response\SafeParcelResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */