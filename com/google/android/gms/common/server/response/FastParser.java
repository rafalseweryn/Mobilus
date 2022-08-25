package com.google.android.gms.common.server.response;

import android.util.Log;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class FastParser<T extends FastJsonResponse> {
  private static final char[] zzwv = new char[] { 'u', 'l', 'l' };
  
  private static final char[] zzww = new char[] { 'r', 'u', 'e' };
  
  private static final char[] zzwx = new char[] { 'r', 'u', 'e', '"' };
  
  private static final char[] zzwy = new char[] { 'a', 'l', 's', 'e' };
  
  private static final char[] zzwz = new char[] { 'a', 'l', 's', 'e', '"' };
  
  private static final char[] zzxa = new char[] { '\n' };
  
  private static final zza<Integer> zzxc = new zza();
  
  private static final zza<Long> zzxd = new zzb();
  
  private static final zza<Float> zzxe = new zzc();
  
  private static final zza<Double> zzxf = new zzd();
  
  private static final zza<Boolean> zzxg = new zze();
  
  private static final zza<String> zzxh = new zzf();
  
  private static final zza<BigInteger> zzxi = new zzg();
  
  private static final zza<BigDecimal> zzxj = new zzh();
  
  private final char[] zzwq = new char[1];
  
  private final char[] zzwr = new char[32];
  
  private final char[] zzws = new char[1024];
  
  private final StringBuilder zzwt = new StringBuilder(32);
  
  private final StringBuilder zzwu = new StringBuilder(1024);
  
  private final Stack<Integer> zzxb = new Stack<>();
  
  private final int zza(BufferedReader paramBufferedReader, char[] paramArrayOfchar) throws ParseException, IOException {
    char c1;
    char c = zzj(paramBufferedReader);
    if (c == '\000')
      throw new ParseException("Unexpected EOF"); 
    if (c == ',')
      throw new ParseException("Missing value"); 
    if (c == 'n') {
      zzb(paramBufferedReader, zzwv);
      return 0;
    } 
    paramBufferedReader.mark(1024);
    if (c == '"') {
      c = Character.MIN_VALUE;
      int i = c;
      while (true) {
        c1 = c;
        if (c < paramArrayOfchar.length) {
          c1 = c;
          if (paramBufferedReader.read(paramArrayOfchar, c, 1) != -1) {
            char c2 = paramArrayOfchar[c];
            if (Character.isISOControl(c2))
              throw new ParseException("Unexpected control character while reading string"); 
            if (c2 == '"' && i == 0) {
              paramBufferedReader.reset();
              paramBufferedReader.skip((c + 1));
              return c;
            } 
            if (c2 == '\\') {
              i ^= 0x1;
            } else {
              i = 0;
            } 
            c++;
            continue;
          } 
        } 
        break;
      } 
    } else {
      paramArrayOfchar[0] = (char)c;
      c = '\001';
      while (true) {
        c1 = c;
        if (c < paramArrayOfchar.length) {
          c1 = c;
          if (paramBufferedReader.read(paramArrayOfchar, c, 1) != -1) {
            if (paramArrayOfchar[c] == '}' || paramArrayOfchar[c] == ',' || Character.isWhitespace(paramArrayOfchar[c]) || paramArrayOfchar[c] == ']') {
              paramBufferedReader.reset();
              paramBufferedReader.skip((c - 1));
              paramArrayOfchar[c] = (char)Character.MIN_VALUE;
              return c;
            } 
            c++;
            continue;
          } 
        } 
        break;
      } 
    } 
    if (c1 == paramArrayOfchar.length)
      throw new ParseException("Absurdly long value"); 
    throw new ParseException("Unexpected EOF");
  }
  
  private final String zza(BufferedReader paramBufferedReader) throws ParseException, IOException {
    StringBuilder stringBuilder;
    this.zzxb.push(Integer.valueOf(2));
    char c = zzj(paramBufferedReader);
    if (c != '"') {
      if (c != ']') {
        if (c != '}') {
          stringBuilder = new StringBuilder(19);
          stringBuilder.append("Unexpected token: ");
          stringBuilder.append(c);
          throw new ParseException(stringBuilder.toString());
        } 
        zzk(2);
        return null;
      } 
      zzk(2);
      zzk(1);
      zzk(5);
      return null;
    } 
    this.zzxb.push(Integer.valueOf(3));
    String str = zzb((BufferedReader)stringBuilder, this.zzwr, this.zzwt, null);
    zzk(3);
    if (zzj((BufferedReader)stringBuilder) != ':')
      throw new ParseException("Expected key/value separator"); 
    return str;
  }
  
  private final String zza(BufferedReader paramBufferedReader, char[] paramArrayOfchar1, StringBuilder paramStringBuilder, char[] paramArrayOfchar2) throws ParseException, IOException {
    char c = zzj(paramBufferedReader);
    if (c != '"') {
      if (c != 'n')
        throw new ParseException("Expected string"); 
      zzb(paramBufferedReader, zzwv);
      return null;
    } 
    return zzb(paramBufferedReader, paramArrayOfchar1, paramStringBuilder, paramArrayOfchar2);
  }
  
  private final <T extends FastJsonResponse> ArrayList<T> zza(BufferedReader paramBufferedReader, FastJsonResponse.Field<?, ?> paramField) throws ParseException, IOException {
    ArrayList<FastJsonResponse> arrayList = new ArrayList();
    char c = zzj(paramBufferedReader);
    if (c != ']') {
      if (c != 'n') {
        StringBuilder stringBuilder;
        if (c != '{') {
          stringBuilder = new StringBuilder(19);
          stringBuilder.append("Unexpected token: ");
          stringBuilder.append(c);
          throw new ParseException(stringBuilder.toString());
        } 
        Stack<Integer> stack = this.zzxb;
        while (true) {
          stack.push(Integer.valueOf(1));
          try {
            FastJsonResponse fastJsonResponse = paramField.newConcreteTypeInstance();
            if (zza((BufferedReader)stringBuilder, fastJsonResponse)) {
              arrayList.add(fastJsonResponse);
              c = zzj((BufferedReader)stringBuilder);
              if (c != ',') {
                if (c != ']') {
                  stringBuilder = new StringBuilder(19);
                  stringBuilder.append("Unexpected token: ");
                  stringBuilder.append(c);
                  throw new ParseException(stringBuilder.toString());
                } 
                zzk(5);
                return (ArrayList)arrayList;
              } 
              if (zzj((BufferedReader)stringBuilder) != '{')
                throw new ParseException("Expected start of next object in array"); 
              Stack<Integer> stack1 = this.zzxb;
              continue;
            } 
            return (ArrayList)arrayList;
          } catch (InstantiationException instantiationException) {
            throw new ParseException("Error instantiating inner object", instantiationException);
          } catch (IllegalAccessException illegalAccessException) {
            throw new ParseException("Error instantiating inner object", illegalAccessException);
          } 
        } 
      } 
      zzb((BufferedReader)illegalAccessException, zzwv);
      zzk(5);
      return null;
    } 
    zzk(5);
    return (ArrayList)arrayList;
  }
  
  private final <O> ArrayList<O> zza(BufferedReader paramBufferedReader, zza<O> paramzza) throws ParseException, IOException {
    char c = zzj(paramBufferedReader);
    if (c == 'n') {
      zzb(paramBufferedReader, zzwv);
      return null;
    } 
    if (c != '[')
      throw new ParseException("Expected start of array"); 
    this.zzxb.push(Integer.valueOf(5));
    ArrayList<O> arrayList = new ArrayList();
    while (true) {
      paramBufferedReader.mark(1024);
      c = zzj(paramBufferedReader);
      if (c != '\000') {
        if (c != ',') {
          if (c != ']') {
            paramBufferedReader.reset();
            arrayList.add(paramzza.zzh(this, paramBufferedReader));
            continue;
          } 
          zzk(5);
          return arrayList;
        } 
        continue;
      } 
      throw new ParseException("Unexpected EOF");
    } 
  }
  
  private final boolean zza(BufferedReader paramBufferedReader, FastJsonResponse paramFastJsonResponse) throws ParseException, IOException {
    Map<String, FastJsonResponse.Field<?, ?>> map = paramFastJsonResponse.getFieldMappings();
    String str = zza(paramBufferedReader);
    if (str == null) {
      zzk(1);
      return false;
    } 
    while (str != null) {
      StringBuilder stringBuilder2;
      StringBuilder stringBuilder1;
      HashMap<Object, Object> hashMap;
      byte[] arrayOfByte;
      int i;
      FastJsonResponse.Field<?, ?> field = map.get(str);
      if (field == null) {
        str = zzb(paramBufferedReader);
        continue;
      } 
      this.zzxb.push(Integer.valueOf(4));
      switch (field.getTypeIn()) {
        default:
          i = field.getTypeIn();
          stringBuilder2 = new StringBuilder(30);
          stringBuilder2.append("Invalid field type ");
          stringBuilder2.append(i);
          throw new ParseException(stringBuilder2.toString());
        case 11:
          if (field.isTypeInArray()) {
            i = zzj((BufferedReader)stringBuilder2);
            if (i == 110) {
              zzb((BufferedReader)stringBuilder2, zzwv);
              paramFastJsonResponse.addConcreteTypeArrayInternal(field, field.getOutputFieldName(), null);
              break;
            } 
            this.zzxb.push(Integer.valueOf(5));
            if (i != 91)
              throw new ParseException("Expected array start"); 
            paramFastJsonResponse.addConcreteTypeArrayInternal(field, field.getOutputFieldName(), zza((BufferedReader)stringBuilder2, field));
            break;
          } 
          i = zzj((BufferedReader)stringBuilder2);
          if (i == 110) {
            zzb((BufferedReader)stringBuilder2, zzwv);
            paramFastJsonResponse.addConcreteTypeInternal(field, field.getOutputFieldName(), null);
            break;
          } 
          this.zzxb.push(Integer.valueOf(1));
          if (i != 123)
            throw new ParseException("Expected start of object"); 
          try {
            FastJsonResponse fastJsonResponse = field.newConcreteTypeInstance();
            zza((BufferedReader)stringBuilder2, fastJsonResponse);
            paramFastJsonResponse.addConcreteTypeInternal(field, field.getOutputFieldName(), fastJsonResponse);
          } catch (InstantiationException instantiationException) {
            throw new ParseException("Error instantiating inner object", instantiationException);
          } catch (IllegalAccessException illegalAccessException) {
            throw new ParseException("Error instantiating inner object", illegalAccessException);
          } 
          break;
        case 10:
          i = zzj((BufferedReader)illegalAccessException);
          if (i == 110) {
            zzb((BufferedReader)illegalAccessException, zzwv);
            str = null;
          } else {
            if (i != 123)
              throw new ParseException("Expected start of a map object"); 
            this.zzxb.push(Integer.valueOf(1));
            hashMap = new HashMap<>();
            while (true) {
              i = zzj((BufferedReader)illegalAccessException);
              if (i != 0) {
                String str2;
                if (i != 34) {
                  if (i != 125)
                    continue; 
                  zzk(1);
                  continue;
                } 
                String str3 = zzb((BufferedReader)illegalAccessException, this.zzwr, this.zzwt, null);
                if (zzj((BufferedReader)illegalAccessException) != ':') {
                  str2 = String.valueOf(str3);
                  if (str2.length() != 0) {
                    str2 = "No map value found for key ".concat(str2);
                  } else {
                    str2 = new String("No map value found for key ");
                  } 
                  throw new ParseException(str2);
                } 
                if (zzj((BufferedReader)str2) != '"') {
                  str2 = String.valueOf(str3);
                  if (str2.length() != 0) {
                    str2 = "Expected String value for key ".concat(str2);
                  } else {
                    str2 = new String("Expected String value for key ");
                  } 
                  throw new ParseException(str2);
                } 
                hashMap.put(str3, zzb((BufferedReader)str2, this.zzwr, this.zzwt, null));
                char c1 = zzj((BufferedReader)str2);
                if (c1 != ',') {
                  if (c1 == '}')
                    continue; 
                  stringBuilder1 = new StringBuilder(48);
                  stringBuilder1.append("Unexpected character while parsing string map: ");
                  stringBuilder1.append(c1);
                  throw new ParseException(stringBuilder1.toString());
                } 
                continue;
              } 
              throw new ParseException("Unexpected EOF");
            } 
          } 
          paramFastJsonResponse.setStringMap((FastJsonResponse.Field)field, (Map)hashMap);
          break;
        case 9:
          arrayOfByte = Base64Utils.decodeUrlSafe(zza((BufferedReader)stringBuilder1, this.zzws, this.zzwu, zzxa));
          paramFastJsonResponse.setDecodedBytes((FastJsonResponse.Field)field, arrayOfByte);
          break;
        case 8:
          arrayOfByte = Base64Utils.decode(zza((BufferedReader)stringBuilder1, this.zzws, this.zzwu, zzxa));
          paramFastJsonResponse.setDecodedBytes((FastJsonResponse.Field)field, arrayOfByte);
          break;
        case 7:
          if (field.isTypeInArray()) {
            paramFastJsonResponse.setStrings((FastJsonResponse.Field)field, zza((BufferedReader)stringBuilder1, zzxh));
            break;
          } 
          paramFastJsonResponse.setString((FastJsonResponse.Field)field, zzc((BufferedReader)stringBuilder1));
          break;
        case 6:
          if (field.isTypeInArray()) {
            paramFastJsonResponse.setBooleans((FastJsonResponse.Field)field, zza((BufferedReader)stringBuilder1, zzxg));
            break;
          } 
          paramFastJsonResponse.setBoolean((FastJsonResponse.Field)field, zza((BufferedReader)stringBuilder1, false));
          break;
        case 5:
          if (field.isTypeInArray()) {
            paramFastJsonResponse.setBigDecimals((FastJsonResponse.Field)field, zza((BufferedReader)stringBuilder1, zzxj));
            break;
          } 
          paramFastJsonResponse.setBigDecimal((FastJsonResponse.Field)field, zzi((BufferedReader)stringBuilder1));
          break;
        case 4:
          if (field.isTypeInArray()) {
            paramFastJsonResponse.setDoubles((FastJsonResponse.Field)field, zza((BufferedReader)stringBuilder1, zzxf));
            break;
          } 
          paramFastJsonResponse.setDouble((FastJsonResponse.Field)field, zzh((BufferedReader)stringBuilder1));
          break;
        case 3:
          if (field.isTypeInArray()) {
            paramFastJsonResponse.setFloats((FastJsonResponse.Field)field, zza((BufferedReader)stringBuilder1, zzxe));
            break;
          } 
          paramFastJsonResponse.setFloat((FastJsonResponse.Field)field, zzg((BufferedReader)stringBuilder1));
          break;
        case 2:
          if (field.isTypeInArray()) {
            paramFastJsonResponse.setLongs((FastJsonResponse.Field)field, zza((BufferedReader)stringBuilder1, zzxd));
            break;
          } 
          paramFastJsonResponse.setLong((FastJsonResponse.Field)field, zze((BufferedReader)stringBuilder1));
          break;
        case 1:
          if (field.isTypeInArray()) {
            paramFastJsonResponse.setBigIntegers((FastJsonResponse.Field)field, zza((BufferedReader)stringBuilder1, zzxi));
            break;
          } 
          paramFastJsonResponse.setBigInteger((FastJsonResponse.Field)field, zzf((BufferedReader)stringBuilder1));
          break;
        case 0:
          if (field.isTypeInArray()) {
            paramFastJsonResponse.setIntegers((FastJsonResponse.Field)field, zza((BufferedReader)stringBuilder1, zzxc));
            break;
          } 
          paramFastJsonResponse.setInteger((FastJsonResponse.Field)field, zzd((BufferedReader)stringBuilder1));
          break;
      } 
      zzk(4);
      zzk(2);
      char c = zzj((BufferedReader)stringBuilder1);
      if (c != ',') {
        if (c != '}') {
          stringBuilder1 = new StringBuilder(55);
          stringBuilder1.append("Expected end of object or field separator, but found: ");
          stringBuilder1.append(c);
          throw new ParseException(stringBuilder1.toString());
        } 
        arrayOfByte = null;
        continue;
      } 
      String str1 = zza((BufferedReader)stringBuilder1);
    } 
    PostProcessor<? extends FastJsonResponse> postProcessor = paramFastJsonResponse.getPostProcessor();
    if (postProcessor != null)
      postProcessor.postProcess(paramFastJsonResponse); 
    zzk(1);
    return true;
  }
  
  private final boolean zza(BufferedReader paramBufferedReader, boolean paramBoolean) throws ParseException, IOException {
    while (true) {
      char c = zzj(paramBufferedReader);
      if (c != '"') {
        StringBuilder stringBuilder;
        char[] arrayOfChar;
        if (c != 'f') {
          if (c != 'n') {
            if (c != 't') {
              stringBuilder = new StringBuilder(19);
              stringBuilder.append("Unexpected token: ");
              stringBuilder.append(c);
              throw new ParseException(stringBuilder.toString());
            } 
            if (paramBoolean) {
              arrayOfChar = zzwx;
            } else {
              arrayOfChar = zzww;
            } 
            zzb((BufferedReader)stringBuilder, arrayOfChar);
            return true;
          } 
          zzb((BufferedReader)stringBuilder, zzwv);
          return false;
        } 
        if (paramBoolean) {
          arrayOfChar = zzwz;
        } else {
          arrayOfChar = zzwy;
        } 
        zzb((BufferedReader)stringBuilder, arrayOfChar);
        return false;
      } 
      if (paramBoolean)
        throw new ParseException("No boolean value found in string"); 
      paramBoolean = true;
    } 
  }
  
  private final String zzb(BufferedReader paramBufferedReader) throws ParseException, IOException {
    // Byte code:
    //   0: aload_1
    //   1: sipush #1024
    //   4: invokevirtual mark : (I)V
    //   7: aload_0
    //   8: aload_1
    //   9: invokespecial zzj : (Ljava/io/BufferedReader;)C
    //   12: istore_2
    //   13: iload_2
    //   14: bipush #34
    //   16: if_icmpeq -> 365
    //   19: iload_2
    //   20: bipush #44
    //   22: if_icmpeq -> 355
    //   25: iconst_1
    //   26: istore_3
    //   27: iload_2
    //   28: bipush #91
    //   30: if_icmpeq -> 161
    //   33: iload_2
    //   34: bipush #123
    //   36: if_icmpeq -> 56
    //   39: aload_1
    //   40: invokevirtual reset : ()V
    //   43: aload_0
    //   44: aload_1
    //   45: aload_0
    //   46: getfield zzws : [C
    //   49: invokespecial zza : (Ljava/io/BufferedReader;[C)I
    //   52: pop
    //   53: goto -> 410
    //   56: aload_0
    //   57: getfield zzxb : Ljava/util/Stack;
    //   60: iconst_1
    //   61: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   64: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   67: pop
    //   68: aload_1
    //   69: bipush #32
    //   71: invokevirtual mark : (I)V
    //   74: aload_0
    //   75: aload_1
    //   76: invokespecial zzj : (Ljava/io/BufferedReader;)C
    //   79: istore #4
    //   81: iload #4
    //   83: bipush #125
    //   85: if_icmpne -> 96
    //   88: aload_0
    //   89: iconst_1
    //   90: invokespecial zzk : (I)V
    //   93: goto -> 410
    //   96: iload #4
    //   98: bipush #34
    //   100: if_icmpne -> 124
    //   103: aload_1
    //   104: invokevirtual reset : ()V
    //   107: aload_0
    //   108: aload_1
    //   109: invokespecial zza : (Ljava/io/BufferedReader;)Ljava/lang/String;
    //   112: pop
    //   113: aload_0
    //   114: aload_1
    //   115: invokespecial zzb : (Ljava/io/BufferedReader;)Ljava/lang/String;
    //   118: ifnonnull -> 113
    //   121: goto -> 88
    //   124: new java/lang/StringBuilder
    //   127: dup
    //   128: bipush #18
    //   130: invokespecial <init> : (I)V
    //   133: astore_1
    //   134: aload_1
    //   135: ldc_w 'Unexpected token '
    //   138: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: pop
    //   142: aload_1
    //   143: iload #4
    //   145: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   148: pop
    //   149: new com/google/android/gms/common/server/response/FastParser$ParseException
    //   152: dup
    //   153: aload_1
    //   154: invokevirtual toString : ()Ljava/lang/String;
    //   157: invokespecial <init> : (Ljava/lang/String;)V
    //   160: athrow
    //   161: aload_0
    //   162: getfield zzxb : Ljava/util/Stack;
    //   165: iconst_5
    //   166: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   169: invokevirtual push : (Ljava/lang/Object;)Ljava/lang/Object;
    //   172: pop
    //   173: aload_1
    //   174: bipush #32
    //   176: invokevirtual mark : (I)V
    //   179: aload_0
    //   180: aload_1
    //   181: invokespecial zzj : (Ljava/io/BufferedReader;)C
    //   184: bipush #93
    //   186: if_icmpne -> 197
    //   189: aload_0
    //   190: iconst_5
    //   191: invokespecial zzk : (I)V
    //   194: goto -> 410
    //   197: aload_1
    //   198: invokevirtual reset : ()V
    //   201: iconst_0
    //   202: istore_2
    //   203: iload_2
    //   204: istore #5
    //   206: iload_3
    //   207: ifle -> 189
    //   210: aload_0
    //   211: aload_1
    //   212: invokespecial zzj : (Ljava/io/BufferedReader;)C
    //   215: istore #4
    //   217: iload #4
    //   219: ifne -> 233
    //   222: new com/google/android/gms/common/server/response/FastParser$ParseException
    //   225: dup
    //   226: ldc_w 'Unexpected EOF while parsing array'
    //   229: invokespecial <init> : (Ljava/lang/String;)V
    //   232: athrow
    //   233: iload #4
    //   235: invokestatic isISOControl : (C)Z
    //   238: ifeq -> 252
    //   241: new com/google/android/gms/common/server/response/FastParser$ParseException
    //   244: dup
    //   245: ldc_w 'Unexpected control character while reading array'
    //   248: invokespecial <init> : (Ljava/lang/String;)V
    //   251: athrow
    //   252: iload #5
    //   254: istore #6
    //   256: iload #4
    //   258: bipush #34
    //   260: if_icmpne -> 277
    //   263: iload #5
    //   265: istore #6
    //   267: iload_2
    //   268: ifne -> 277
    //   271: iload #5
    //   273: iconst_1
    //   274: ixor
    //   275: istore #6
    //   277: iload_3
    //   278: istore #5
    //   280: iload #4
    //   282: bipush #91
    //   284: if_icmpne -> 300
    //   287: iload_3
    //   288: istore #5
    //   290: iload #6
    //   292: ifne -> 300
    //   295: iload_3
    //   296: iconst_1
    //   297: iadd
    //   298: istore #5
    //   300: iload #5
    //   302: istore_3
    //   303: iload #4
    //   305: bipush #93
    //   307: if_icmpne -> 323
    //   310: iload #5
    //   312: istore_3
    //   313: iload #6
    //   315: ifne -> 323
    //   318: iload #5
    //   320: iconst_1
    //   321: isub
    //   322: istore_3
    //   323: iload #4
    //   325: bipush #92
    //   327: if_icmpne -> 346
    //   330: iload #6
    //   332: ifeq -> 346
    //   335: iload_2
    //   336: iconst_1
    //   337: ixor
    //   338: istore_2
    //   339: iload #6
    //   341: istore #5
    //   343: goto -> 206
    //   346: iconst_0
    //   347: istore_2
    //   348: iload #6
    //   350: istore #5
    //   352: goto -> 206
    //   355: new com/google/android/gms/common/server/response/FastParser$ParseException
    //   358: dup
    //   359: ldc 'Missing value'
    //   361: invokespecial <init> : (Ljava/lang/String;)V
    //   364: athrow
    //   365: aload_1
    //   366: aload_0
    //   367: getfield zzwq : [C
    //   370: invokevirtual read : ([C)I
    //   373: iconst_m1
    //   374: if_icmpne -> 388
    //   377: new com/google/android/gms/common/server/response/FastParser$ParseException
    //   380: dup
    //   381: ldc_w 'Unexpected EOF while parsing string'
    //   384: invokespecial <init> : (Ljava/lang/String;)V
    //   387: athrow
    //   388: aload_0
    //   389: getfield zzwq : [C
    //   392: iconst_0
    //   393: caload
    //   394: istore_3
    //   395: iconst_0
    //   396: istore_2
    //   397: iload_3
    //   398: bipush #34
    //   400: if_icmpne -> 486
    //   403: iload_2
    //   404: ifeq -> 410
    //   407: goto -> 486
    //   410: aload_0
    //   411: aload_1
    //   412: invokespecial zzj : (Ljava/io/BufferedReader;)C
    //   415: istore #4
    //   417: iload #4
    //   419: bipush #44
    //   421: if_icmpeq -> 475
    //   424: iload #4
    //   426: bipush #125
    //   428: if_icmpeq -> 468
    //   431: new java/lang/StringBuilder
    //   434: dup
    //   435: bipush #18
    //   437: invokespecial <init> : (I)V
    //   440: astore_1
    //   441: aload_1
    //   442: ldc_w 'Unexpected token '
    //   445: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   448: pop
    //   449: aload_1
    //   450: iload #4
    //   452: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   455: pop
    //   456: new com/google/android/gms/common/server/response/FastParser$ParseException
    //   459: dup
    //   460: aload_1
    //   461: invokevirtual toString : ()Ljava/lang/String;
    //   464: invokespecial <init> : (Ljava/lang/String;)V
    //   467: athrow
    //   468: aload_0
    //   469: iconst_2
    //   470: invokespecial zzk : (I)V
    //   473: aconst_null
    //   474: areturn
    //   475: aload_0
    //   476: iconst_2
    //   477: invokespecial zzk : (I)V
    //   480: aload_0
    //   481: aload_1
    //   482: invokespecial zza : (Ljava/io/BufferedReader;)Ljava/lang/String;
    //   485: areturn
    //   486: iload_3
    //   487: bipush #92
    //   489: if_icmpne -> 499
    //   492: iload_2
    //   493: iconst_1
    //   494: ixor
    //   495: istore_2
    //   496: goto -> 501
    //   499: iconst_0
    //   500: istore_2
    //   501: aload_1
    //   502: aload_0
    //   503: getfield zzwq : [C
    //   506: invokevirtual read : ([C)I
    //   509: iconst_m1
    //   510: if_icmpne -> 524
    //   513: new com/google/android/gms/common/server/response/FastParser$ParseException
    //   516: dup
    //   517: ldc_w 'Unexpected EOF while parsing string'
    //   520: invokespecial <init> : (Ljava/lang/String;)V
    //   523: athrow
    //   524: aload_0
    //   525: getfield zzwq : [C
    //   528: iconst_0
    //   529: caload
    //   530: istore #4
    //   532: iload #4
    //   534: istore_3
    //   535: iload #4
    //   537: invokestatic isISOControl : (C)Z
    //   540: ifeq -> 397
    //   543: new com/google/android/gms/common/server/response/FastParser$ParseException
    //   546: dup
    //   547: ldc 'Unexpected control character while reading string'
    //   549: invokespecial <init> : (Ljava/lang/String;)V
    //   552: athrow
  }
  
  private static String zzb(BufferedReader paramBufferedReader, char[] paramArrayOfchar1, StringBuilder paramStringBuilder, char[] paramArrayOfchar2) throws ParseException, IOException {
    paramStringBuilder.setLength(0);
    paramBufferedReader.mark(paramArrayOfchar1.length);
    int i = 0;
    int j = i;
    while (true) {
      int k = paramBufferedReader.read(paramArrayOfchar1);
      if (k != -1) {
        int m = i;
        byte b = 0;
        i = j;
        j = m;
        label36: for (m = b; m < k; m++) {
          char c = paramArrayOfchar1[m];
          if (Character.isISOControl(c)) {
            if (paramArrayOfchar2 != null)
              for (b = 0; b < paramArrayOfchar2.length; b++) {
                if (paramArrayOfchar2[b] == c) {
                  b = 1;
                  continue label36;
                } 
              }  
            b = 0;
            if (b == 0)
              throw new ParseException("Unexpected control character while reading string"); 
          } 
          if (c == '"' && j == 0) {
            paramStringBuilder.append(paramArrayOfchar1, 0, m);
            paramBufferedReader.reset();
            paramBufferedReader.skip((m + 1));
            return (i != 0) ? JsonUtils.unescapeString(paramStringBuilder.toString()) : paramStringBuilder.toString();
          } 
          if (c == '\\') {
            j ^= 0x1;
            i = 1;
          } else {
            j = 0;
          } 
        } 
        paramStringBuilder.append(paramArrayOfchar1, 0, k);
        paramBufferedReader.mark(paramArrayOfchar1.length);
        m = i;
        i = j;
        j = m;
        continue;
      } 
      throw new ParseException("Unexpected EOF while parsing string");
    } 
  }
  
  private final void zzb(BufferedReader paramBufferedReader, char[] paramArrayOfchar) throws ParseException, IOException {
    for (int i = 0; i < paramArrayOfchar.length; i += j) {
      int j = paramBufferedReader.read(this.zzwr, 0, paramArrayOfchar.length - i);
      if (j == -1)
        throw new ParseException("Unexpected EOF"); 
      for (byte b = 0; b < j; b++) {
        if (paramArrayOfchar[b + i] != this.zzwr[b])
          throw new ParseException("Unexpected character"); 
      } 
    } 
  }
  
  private final String zzc(BufferedReader paramBufferedReader) throws ParseException, IOException {
    return zza(paramBufferedReader, this.zzwr, this.zzwt, null);
  }
  
  private final int zzd(BufferedReader paramBufferedReader) throws ParseException, IOException {
    int i = zza(paramBufferedReader, this.zzws);
    if (i == 0)
      return 0; 
    char[] arrayOfChar = this.zzws;
    if (i > 0) {
      int j;
      int k;
      byte b;
      int m;
      if (arrayOfChar[0] == '-') {
        j = Integer.MIN_VALUE;
        k = 1;
        b = k;
      } else {
        b = 0;
        j = -2147483647;
        k = b;
      } 
      if (k < i) {
        m = k + 1;
        k = Character.digit(arrayOfChar[k], 10);
        if (k < 0)
          throw new ParseException("Unexpected non-digit character"); 
        int n = -k;
        k = m;
        m = n;
      } else {
        m = 0;
      } 
      while (k < i) {
        int n = Character.digit(arrayOfChar[k], 10);
        if (n < 0)
          throw new ParseException("Unexpected non-digit character"); 
        if (m < -214748364)
          throw new ParseException("Number too large"); 
        m *= 10;
        if (m < j + n)
          throw new ParseException("Number too large"); 
        m -= n;
        k++;
      } 
      if (b != 0) {
        if (k > 1)
          return m; 
        throw new ParseException("No digits to parse");
      } 
      return -m;
    } 
    throw new ParseException("No number to parse");
  }
  
  private final long zze(BufferedReader paramBufferedReader) throws ParseException, IOException {
    int i = zza(paramBufferedReader, this.zzws);
    if (i == 0)
      return 0L; 
    char[] arrayOfChar = this.zzws;
    if (i > 0) {
      long l1;
      byte b2;
      long l2;
      byte b1 = 0;
      if (arrayOfChar[0] == '-') {
        l1 = Long.MIN_VALUE;
        b1 = 1;
      } else {
        l1 = -9223372036854775807L;
      } 
      if (b1 < i) {
        b2 = b1 + 1;
        int j = Character.digit(arrayOfChar[b1], 10);
        if (j < 0)
          throw new ParseException("Unexpected non-digit character"); 
        l2 = -j;
      } else {
        b2 = b1;
        l2 = 0L;
      } 
      while (b2 < i) {
        int j = Character.digit(arrayOfChar[b2], 10);
        if (j < 0)
          throw new ParseException("Unexpected non-digit character"); 
        if (l2 < -922337203685477580L)
          throw new ParseException("Number too large"); 
        l2 *= 10L;
        long l = j;
        if (l2 < l1 + l)
          throw new ParseException("Number too large"); 
        l2 -= l;
        b2++;
      } 
      if (b1 != 0) {
        if (b2 > 1)
          return l2; 
        throw new ParseException("No digits to parse");
      } 
      return -l2;
    } 
    throw new ParseException("No number to parse");
  }
  
  private final BigInteger zzf(BufferedReader paramBufferedReader) throws ParseException, IOException {
    int i = zza(paramBufferedReader, this.zzws);
    return (i == 0) ? null : new BigInteger(new String(this.zzws, 0, i));
  }
  
  private final float zzg(BufferedReader paramBufferedReader) throws ParseException, IOException {
    int i = zza(paramBufferedReader, this.zzws);
    return (i == 0) ? 0.0F : Float.parseFloat(new String(this.zzws, 0, i));
  }
  
  private final double zzh(BufferedReader paramBufferedReader) throws ParseException, IOException {
    int i = zza(paramBufferedReader, this.zzws);
    return (i == 0) ? 0.0D : Double.parseDouble(new String(this.zzws, 0, i));
  }
  
  private final BigDecimal zzi(BufferedReader paramBufferedReader) throws ParseException, IOException {
    int i = zza(paramBufferedReader, this.zzws);
    return (i == 0) ? null : new BigDecimal(new String(this.zzws, 0, i));
  }
  
  private final char zzj(BufferedReader paramBufferedReader) throws ParseException, IOException {
    if (paramBufferedReader.read(this.zzwq) == -1)
      return Character.MIN_VALUE; 
    while (Character.isWhitespace(this.zzwq[0])) {
      if (paramBufferedReader.read(this.zzwq) == -1)
        return Character.MIN_VALUE; 
    } 
    return this.zzwq[0];
  }
  
  private final void zzk(int paramInt) throws ParseException {
    if (this.zzxb.isEmpty()) {
      StringBuilder stringBuilder = new StringBuilder(46);
      stringBuilder.append("Expected state ");
      stringBuilder.append(paramInt);
      stringBuilder.append(" but had empty stack");
      throw new ParseException(stringBuilder.toString());
    } 
    int i = ((Integer)this.zzxb.pop()).intValue();
    if (i != paramInt) {
      StringBuilder stringBuilder = new StringBuilder(46);
      stringBuilder.append("Expected state ");
      stringBuilder.append(paramInt);
      stringBuilder.append(" but had ");
      stringBuilder.append(i);
      throw new ParseException(stringBuilder.toString());
    } 
  }
  
  public void parse(InputStream paramInputStream, T paramT) throws ParseException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(paramInputStream), 1024);
    try {
      this.zzxb.push(Integer.valueOf(0));
      char c = zzj(bufferedReader);
      if (c != '\000') {
        ParseException parseException1;
        if (c != '[') {
          if (c != '{') {
            parseException1 = new ParseException();
            StringBuilder stringBuilder = new StringBuilder();
            this(19);
            stringBuilder.append("Unexpected token: ");
            stringBuilder.append(c);
            this(stringBuilder.toString());
            throw parseException1;
          } 
          this.zzxb.push(Integer.valueOf(1));
          zza(bufferedReader, (FastJsonResponse)parseException1);
        } else {
          this.zzxb.push(Integer.valueOf(5));
          Map<String, FastJsonResponse.Field<?, ?>> map = parseException1.getFieldMappings();
          if (map.size() != 1) {
            parseException1 = new ParseException();
            this("Object array response class must have a single Field");
            throw parseException1;
          } 
          FastJsonResponse.Field<?, ?> field = (FastJsonResponse.Field)((Map.Entry)map.entrySet().iterator().next()).getValue();
          ArrayList<FastJsonResponse> arrayList = zza(bufferedReader, field);
          parseException1.addConcreteTypeArrayInternal(field, field.getOutputFieldName(), arrayList);
        } 
        zzk(0);
        try {
          bufferedReader.close();
          return;
        } catch (IOException iOException) {
          Log.w("FastParser", "Failed to close reader while parsing.");
          return;
        } 
      } 
      ParseException parseException = new ParseException();
      this("No data to parse");
      throw parseException;
    } catch (IOException iOException1) {
      ParseException parseException = new ParseException();
      this(iOException1);
      throw parseException;
    } finally {}
    try {
      iOException.close();
    } catch (IOException iOException1) {
      Log.w("FastParser", "Failed to close reader while parsing.");
    } 
    throw paramT;
  }
  
  public void parse(String paramString, T paramT) throws ParseException {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(paramString.getBytes());
    try {
      parse(byteArrayInputStream, paramT);
    } finally {
      try {
        iOException.close();
      } catch (IOException iOException1) {
        Log.w("FastParser", "Failed to close the input stream while parsing.");
      } 
    } 
  }
  
  public static class ParseException extends Exception {
    public ParseException(String param1String) {
      super(param1String);
    }
    
    public ParseException(String param1String, Throwable param1Throwable) {
      super(param1String, param1Throwable);
    }
    
    public ParseException(Throwable param1Throwable) {
      super(param1Throwable);
    }
  }
  
  private static interface zza<O> {
    O zzh(FastParser param1FastParser, BufferedReader param1BufferedReader) throws FastParser.ParseException, IOException;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\server\response\FastParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */