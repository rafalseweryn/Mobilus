package com.google.protobuf;

import java.nio.ByteBuffer;

final class Utf8 {
  private static final long ASCII_MASK_LONG = -9187201950435737472L;
  
  public static final int COMPLETE = 0;
  
  public static final int MALFORMED = -1;
  
  static final int MAX_BYTES_PER_CHAR = 3;
  
  private static final int UNSAFE_COUNT_ASCII_THRESHOLD = 16;
  
  private static final Processor processor;
  
  static {
    SafeProcessor safeProcessor;
    if (UnsafeProcessor.isAvailable() && !Android.isOnAndroidDevice()) {
      UnsafeProcessor unsafeProcessor = new UnsafeProcessor();
    } else {
      safeProcessor = new SafeProcessor();
    } 
    processor = safeProcessor;
  }
  
  static String decodeUtf8(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2) throws InvalidProtocolBufferException {
    return processor.decodeUtf8(paramByteBuffer, paramInt1, paramInt2);
  }
  
  static String decodeUtf8(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws InvalidProtocolBufferException {
    return processor.decodeUtf8(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  static int encode(CharSequence paramCharSequence, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return processor.encodeUtf8(paramCharSequence, paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  static void encodeUtf8(CharSequence paramCharSequence, ByteBuffer paramByteBuffer) {
    processor.encodeUtf8(paramCharSequence, paramByteBuffer);
  }
  
  static int encodedLength(CharSequence paramCharSequence) {
    int k;
    int i = paramCharSequence.length();
    byte b;
    for (b = 0; b < i && paramCharSequence.charAt(b) < ''; b++);
    int j = i;
    while (true) {
      k = j;
      if (b < i) {
        k = paramCharSequence.charAt(b);
        if (k < 2048) {
          j += 127 - k >>> 31;
          b++;
          continue;
        } 
        k = j + encodedLengthGeneral(paramCharSequence, b);
      } 
      break;
    } 
    if (k < i) {
      paramCharSequence = new StringBuilder();
      paramCharSequence.append("UTF-8 length does not fit in int: ");
      paramCharSequence.append(k + 4294967296L);
      throw new IllegalArgumentException(paramCharSequence.toString());
    } 
    return k;
  }
  
  private static int encodedLengthGeneral(CharSequence paramCharSequence, int paramInt) {
    int i = paramCharSequence.length();
    int j = 0;
    while (paramInt < i) {
      int k;
      char c = paramCharSequence.charAt(paramInt);
      if (c < 'ࠀ') {
        j += 127 - c >>> 31;
        k = paramInt;
      } else {
        int m = j + 2;
        j = m;
        k = paramInt;
        if ('?' <= c) {
          j = m;
          k = paramInt;
          if (c <= '?') {
            if (Character.codePointAt(paramCharSequence, paramInt) < 65536)
              throw new UnpairedSurrogateException(paramInt, i); 
            k = paramInt + 1;
            j = m;
          } 
        } 
      } 
      paramInt = k + 1;
    } 
    return j;
  }
  
  private static int estimateConsecutiveAscii(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2) {
    int i;
    for (i = paramInt1; i < paramInt2 - 7 && (paramByteBuffer.getLong(i) & 0x8080808080808080L) == 0L; i += 8);
    return i - paramInt1;
  }
  
  private static int incompleteStateFor(int paramInt) {
    int i = paramInt;
    if (paramInt > -12)
      i = -1; 
    return i;
  }
  
  private static int incompleteStateFor(int paramInt1, int paramInt2) {
    if (paramInt1 > -12 || paramInt2 > -65)
      return -1; 
    paramInt1 ^= paramInt2 << 8;
    return paramInt1;
  }
  
  private static int incompleteStateFor(int paramInt1, int paramInt2, int paramInt3) {
    return (paramInt1 > -12 || paramInt2 > -65 || paramInt3 > -65) ? -1 : (paramInt1 ^ paramInt2 << 8 ^ paramInt3 << 16);
  }
  
  private static int incompleteStateFor(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int paramInt3) {
    switch (paramInt3) {
      default:
        throw new AssertionError();
      case 2:
        return incompleteStateFor(paramInt1, paramByteBuffer.get(paramInt2), paramByteBuffer.get(paramInt2 + 1));
      case 1:
        return incompleteStateFor(paramInt1, paramByteBuffer.get(paramInt2));
      case 0:
        break;
    } 
    return incompleteStateFor(paramInt1);
  }
  
  private static int incompleteStateFor(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    byte b = paramArrayOfbyte[paramInt1 - 1];
    switch (paramInt2 - paramInt1) {
      default:
        throw new AssertionError();
      case 2:
        return incompleteStateFor(b, paramArrayOfbyte[paramInt1], paramArrayOfbyte[paramInt1 + 1]);
      case 1:
        return incompleteStateFor(b, paramArrayOfbyte[paramInt1]);
      case 0:
        break;
    } 
    return incompleteStateFor(b);
  }
  
  static boolean isValidUtf8(ByteBuffer paramByteBuffer) {
    return processor.isValidUtf8(paramByteBuffer, paramByteBuffer.position(), paramByteBuffer.remaining());
  }
  
  public static boolean isValidUtf8(byte[] paramArrayOfbyte) {
    return processor.isValidUtf8(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public static boolean isValidUtf8(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return processor.isValidUtf8(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  static int partialIsValidUtf8(int paramInt1, ByteBuffer paramByteBuffer, int paramInt2, int paramInt3) {
    return processor.partialIsValidUtf8(paramInt1, paramByteBuffer, paramInt2, paramInt3);
  }
  
  public static int partialIsValidUtf8(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3) {
    return processor.partialIsValidUtf8(paramInt1, paramArrayOfbyte, paramInt2, paramInt3);
  }
  
  private static class DecodeUtil {
    private static void handleFourBytes(byte param1Byte1, byte param1Byte2, byte param1Byte3, byte param1Byte4, char[] param1ArrayOfchar, int param1Int) throws InvalidProtocolBufferException {
      if (isNotTrailingByte(param1Byte2) || (param1Byte1 << 28) + param1Byte2 + 112 >> 30 != 0 || isNotTrailingByte(param1Byte3) || isNotTrailingByte(param1Byte4))
        throw InvalidProtocolBufferException.invalidUtf8(); 
      int i = (param1Byte1 & 0x7) << 18 | trailingByteValue(param1Byte2) << 12 | trailingByteValue(param1Byte3) << 6 | trailingByteValue(param1Byte4);
      param1ArrayOfchar[param1Int] = highSurrogate(i);
      param1ArrayOfchar[param1Int + 1] = lowSurrogate(i);
    }
    
    private static void handleOneByte(byte param1Byte, char[] param1ArrayOfchar, int param1Int) {
      param1ArrayOfchar[param1Int] = (char)(char)param1Byte;
    }
    
    private static void handleThreeBytes(byte param1Byte1, byte param1Byte2, byte param1Byte3, char[] param1ArrayOfchar, int param1Int) throws InvalidProtocolBufferException {
      if (isNotTrailingByte(param1Byte2) || (param1Byte1 == -32 && param1Byte2 < -96) || (param1Byte1 == -19 && param1Byte2 >= -96) || isNotTrailingByte(param1Byte3))
        throw InvalidProtocolBufferException.invalidUtf8(); 
      param1ArrayOfchar[param1Int] = (char)(char)((param1Byte1 & 0xF) << 12 | trailingByteValue(param1Byte2) << 6 | trailingByteValue(param1Byte3));
    }
    
    private static void handleTwoBytes(byte param1Byte1, byte param1Byte2, char[] param1ArrayOfchar, int param1Int) throws InvalidProtocolBufferException {
      if (param1Byte1 < -62 || isNotTrailingByte(param1Byte2))
        throw InvalidProtocolBufferException.invalidUtf8(); 
      param1ArrayOfchar[param1Int] = (char)(char)((param1Byte1 & 0x1F) << 6 | trailingByteValue(param1Byte2));
    }
    
    private static char highSurrogate(int param1Int) {
      return (char)((param1Int >>> 10) + 55232);
    }
    
    private static boolean isNotTrailingByte(byte param1Byte) {
      boolean bool;
      if (param1Byte > -65) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private static boolean isOneByte(byte param1Byte) {
      boolean bool;
      if (param1Byte >= 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private static boolean isThreeBytes(byte param1Byte) {
      boolean bool;
      if (param1Byte < -16) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private static boolean isTwoBytes(byte param1Byte) {
      boolean bool;
      if (param1Byte < -32) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private static char lowSurrogate(int param1Int) {
      return (char)((param1Int & 0x3FF) + 56320);
    }
    
    private static int trailingByteValue(byte param1Byte) {
      return param1Byte & 0x3F;
    }
  }
  
  static abstract class Processor {
    private static int partialIsValidUtf8(ByteBuffer param1ByteBuffer, int param1Int1, int param1Int2) {
      param1Int1 += Utf8.estimateConsecutiveAscii(param1ByteBuffer, param1Int1, param1Int2);
      while (true) {
        if (param1Int1 >= param1Int2)
          return 0; 
        int i = param1Int1 + 1;
        byte b = param1ByteBuffer.get(param1Int1);
        param1Int1 = i;
        if (b < 0) {
          if (b < -32) {
            if (i >= param1Int2)
              return b; 
            if (b < -62 || param1ByteBuffer.get(i) > -65)
              return -1; 
            param1Int1 = i + 1;
            continue;
          } 
          if (b < -16) {
            if (i >= param1Int2 - 1)
              return Utf8.incompleteStateFor(param1ByteBuffer, b, i, param1Int2 - i); 
            param1Int1 = i + 1;
            i = param1ByteBuffer.get(i);
            if (i > -65 || (b == -32 && i < -96) || (b == -19 && i >= -96) || param1ByteBuffer.get(param1Int1) > -65)
              return -1; 
            param1Int1++;
            continue;
          } 
          if (i >= param1Int2 - 2)
            return Utf8.incompleteStateFor(param1ByteBuffer, b, i, param1Int2 - i); 
          param1Int1 = i + 1;
          i = param1ByteBuffer.get(i);
          if (i <= -65 && (b << 28) + i + 112 >> 30 == 0) {
            i = param1Int1 + 1;
            if (param1ByteBuffer.get(param1Int1) <= -65) {
              param1Int1 = i + 1;
              if (param1ByteBuffer.get(i) > -65)
                break; 
              continue;
            } 
          } 
          break;
        } 
      } 
      return -1;
    }
    
    final String decodeUtf8(ByteBuffer param1ByteBuffer, int param1Int1, int param1Int2) throws InvalidProtocolBufferException {
      if (param1ByteBuffer.hasArray()) {
        int i = param1ByteBuffer.arrayOffset();
        return decodeUtf8(param1ByteBuffer.array(), i + param1Int1, param1Int2);
      } 
      return param1ByteBuffer.isDirect() ? decodeUtf8Direct(param1ByteBuffer, param1Int1, param1Int2) : decodeUtf8Default(param1ByteBuffer, param1Int1, param1Int2);
    }
    
    abstract String decodeUtf8(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws InvalidProtocolBufferException;
    
    final String decodeUtf8Default(ByteBuffer param1ByteBuffer, int param1Int1, int param1Int2) throws InvalidProtocolBufferException {
      if ((param1Int1 | param1Int2 | param1ByteBuffer.limit() - param1Int1 - param1Int2) < 0)
        throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", new Object[] { Integer.valueOf(param1ByteBuffer.limit()), Integer.valueOf(param1Int1), Integer.valueOf(param1Int2) })); 
      int i = param1Int1 + param1Int2;
      char[] arrayOfChar = new char[param1Int2];
      for (param1Int2 = 0; param1Int1 < i; param1Int2++) {
        byte b = param1ByteBuffer.get(param1Int1);
        if (!Utf8.DecodeUtil.isOneByte(b))
          break; 
        param1Int1++;
        Utf8.DecodeUtil.handleOneByte(b, arrayOfChar, param1Int2);
      } 
      int j = param1Int2;
      param1Int2 = param1Int1;
      for (param1Int1 = j; param1Int2 < i; param1Int1 = param1Int1 + 1 + 1) {
        j = param1Int2 + 1;
        byte b1 = param1ByteBuffer.get(param1Int2);
        if (Utf8.DecodeUtil.isOneByte(b1)) {
          param1Int2 = param1Int1 + 1;
          Utf8.DecodeUtil.handleOneByte(b1, arrayOfChar, param1Int1);
          param1Int1 = param1Int2;
          param1Int2 = j;
          while (param1Int2 < i) {
            b1 = param1ByteBuffer.get(param1Int2);
            if (!Utf8.DecodeUtil.isOneByte(b1))
              break; 
            param1Int2++;
            Utf8.DecodeUtil.handleOneByte(b1, arrayOfChar, param1Int1);
            param1Int1++;
          } 
          continue;
        } 
        if (Utf8.DecodeUtil.isTwoBytes(b1)) {
          if (j >= i)
            throw InvalidProtocolBufferException.invalidUtf8(); 
          Utf8.DecodeUtil.handleTwoBytes(b1, param1ByteBuffer.get(j), arrayOfChar, param1Int1);
          param1Int2 = j + 1;
          param1Int1++;
          continue;
        } 
        if (Utf8.DecodeUtil.isThreeBytes(b1)) {
          if (j >= i - 1)
            throw InvalidProtocolBufferException.invalidUtf8(); 
          param1Int2 = j + 1;
          Utf8.DecodeUtil.handleThreeBytes(b1, param1ByteBuffer.get(j), param1ByteBuffer.get(param1Int2), arrayOfChar, param1Int1);
          param1Int2++;
          param1Int1++;
          continue;
        } 
        if (j >= i - 2)
          throw InvalidProtocolBufferException.invalidUtf8(); 
        param1Int2 = j + 1;
        byte b2 = param1ByteBuffer.get(j);
        j = param1Int2 + 1;
        Utf8.DecodeUtil.handleFourBytes(b1, b2, param1ByteBuffer.get(param1Int2), param1ByteBuffer.get(j), arrayOfChar, param1Int1);
        param1Int2 = j + 1;
      } 
      return new String(arrayOfChar, 0, param1Int1);
    }
    
    abstract String decodeUtf8Direct(ByteBuffer param1ByteBuffer, int param1Int1, int param1Int2) throws InvalidProtocolBufferException;
    
    abstract int encodeUtf8(CharSequence param1CharSequence, byte[] param1ArrayOfbyte, int param1Int1, int param1Int2);
    
    final void encodeUtf8(CharSequence param1CharSequence, ByteBuffer param1ByteBuffer) {
      if (param1ByteBuffer.hasArray()) {
        int i = param1ByteBuffer.arrayOffset();
        param1ByteBuffer.position(Utf8.encode(param1CharSequence, param1ByteBuffer.array(), param1ByteBuffer.position() + i, param1ByteBuffer.remaining()) - i);
      } else if (param1ByteBuffer.isDirect()) {
        encodeUtf8Direct(param1CharSequence, param1ByteBuffer);
      } else {
        encodeUtf8Default(param1CharSequence, param1ByteBuffer);
      } 
    }
    
    final void encodeUtf8Default(CharSequence param1CharSequence, ByteBuffer param1ByteBuffer) {
      // Byte code:
      //   0: aload_1
      //   1: invokeinterface length : ()I
      //   6: istore_3
      //   7: aload_2
      //   8: invokevirtual position : ()I
      //   11: istore #4
      //   13: iconst_0
      //   14: istore #5
      //   16: iload #5
      //   18: iload_3
      //   19: if_icmpge -> 75
      //   22: iload #4
      //   24: istore #6
      //   26: iload #5
      //   28: istore #7
      //   30: aload_1
      //   31: iload #5
      //   33: invokeinterface charAt : (I)C
      //   38: istore #8
      //   40: iload #8
      //   42: sipush #128
      //   45: if_icmpge -> 75
      //   48: iload #4
      //   50: istore #6
      //   52: iload #5
      //   54: istore #7
      //   56: aload_2
      //   57: iload #4
      //   59: iload #5
      //   61: iadd
      //   62: iload #8
      //   64: i2b
      //   65: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
      //   68: pop
      //   69: iinc #5, 1
      //   72: goto -> 16
      //   75: iload #5
      //   77: iload_3
      //   78: if_icmpne -> 100
      //   81: iload #4
      //   83: istore #6
      //   85: iload #5
      //   87: istore #7
      //   89: aload_2
      //   90: iload #4
      //   92: iload #5
      //   94: iadd
      //   95: invokevirtual position : (I)Ljava/nio/Buffer;
      //   98: pop
      //   99: return
      //   100: iload #4
      //   102: iload #5
      //   104: iadd
      //   105: istore #4
      //   107: iload #5
      //   109: iload_3
      //   110: if_icmpge -> 596
      //   113: iload #4
      //   115: istore #6
      //   117: iload #5
      //   119: istore #7
      //   121: aload_1
      //   122: iload #5
      //   124: invokeinterface charAt : (I)C
      //   129: istore #9
      //   131: iload #9
      //   133: sipush #128
      //   136: if_icmpge -> 160
      //   139: iload #4
      //   141: istore #6
      //   143: iload #5
      //   145: istore #7
      //   147: aload_2
      //   148: iload #4
      //   150: iload #9
      //   152: i2b
      //   153: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
      //   156: pop
      //   157: goto -> 587
      //   160: iload #9
      //   162: sipush #2048
      //   165: if_icmpge -> 236
      //   168: iload #4
      //   170: iconst_1
      //   171: iadd
      //   172: istore #6
      //   174: iload #9
      //   176: bipush #6
      //   178: iushr
      //   179: sipush #192
      //   182: ior
      //   183: i2b
      //   184: istore #10
      //   186: iload #6
      //   188: istore #7
      //   190: aload_2
      //   191: iload #4
      //   193: iload #10
      //   195: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
      //   198: pop
      //   199: iload #6
      //   201: istore #7
      //   203: aload_2
      //   204: iload #6
      //   206: iload #9
      //   208: bipush #63
      //   210: iand
      //   211: sipush #128
      //   214: ior
      //   215: i2b
      //   216: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
      //   219: pop
      //   220: iload #6
      //   222: istore #4
      //   224: goto -> 587
      //   227: astore #11
      //   229: iload #7
      //   231: istore #6
      //   233: goto -> 612
      //   236: iload #9
      //   238: ldc 55296
      //   240: if_icmplt -> 493
      //   243: ldc 57343
      //   245: iload #9
      //   247: if_icmpge -> 253
      //   250: goto -> 493
      //   253: iload #5
      //   255: iconst_1
      //   256: iadd
      //   257: istore #7
      //   259: iload #7
      //   261: iload_3
      //   262: if_icmpeq -> 453
      //   265: iload #4
      //   267: istore #5
      //   269: aload_1
      //   270: iload #7
      //   272: invokeinterface charAt : (I)C
      //   277: istore #12
      //   279: iload #4
      //   281: istore #5
      //   283: iload #9
      //   285: iload #12
      //   287: invokestatic isSurrogatePair : (CC)Z
      //   290: ifne -> 300
      //   293: iload #7
      //   295: istore #5
      //   297: goto -> 453
      //   300: iload #4
      //   302: istore #5
      //   304: iload #9
      //   306: iload #12
      //   308: invokestatic toCodePoint : (CC)I
      //   311: istore #13
      //   313: iload #4
      //   315: iconst_1
      //   316: iadd
      //   317: istore #8
      //   319: iload #13
      //   321: bipush #18
      //   323: iushr
      //   324: sipush #240
      //   327: ior
      //   328: i2b
      //   329: istore #10
      //   331: iload #8
      //   333: istore #5
      //   335: aload_2
      //   336: iload #4
      //   338: iload #10
      //   340: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
      //   343: pop
      //   344: iload #8
      //   346: iconst_1
      //   347: iadd
      //   348: istore #6
      //   350: iload #13
      //   352: bipush #12
      //   354: iushr
      //   355: bipush #63
      //   357: iand
      //   358: sipush #128
      //   361: ior
      //   362: i2b
      //   363: istore #10
      //   365: iload #6
      //   367: istore #5
      //   369: aload_2
      //   370: iload #8
      //   372: iload #10
      //   374: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
      //   377: pop
      //   378: iload #6
      //   380: iconst_1
      //   381: iadd
      //   382: istore #4
      //   384: iload #13
      //   386: bipush #6
      //   388: iushr
      //   389: bipush #63
      //   391: iand
      //   392: sipush #128
      //   395: ior
      //   396: i2b
      //   397: istore #10
      //   399: iload #4
      //   401: istore #5
      //   403: aload_2
      //   404: iload #6
      //   406: iload #10
      //   408: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
      //   411: pop
      //   412: iload #4
      //   414: istore #5
      //   416: aload_2
      //   417: iload #4
      //   419: iload #13
      //   421: bipush #63
      //   423: iand
      //   424: sipush #128
      //   427: ior
      //   428: i2b
      //   429: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
      //   432: pop
      //   433: iload #7
      //   435: istore #5
      //   437: goto -> 587
      //   440: astore #11
      //   442: iload #5
      //   444: istore #6
      //   446: iload #7
      //   448: istore #5
      //   450: goto -> 612
      //   453: iload #4
      //   455: istore #6
      //   457: iload #5
      //   459: istore #7
      //   461: new com/google/protobuf/Utf8$UnpairedSurrogateException
      //   464: astore #11
      //   466: iload #4
      //   468: istore #6
      //   470: iload #5
      //   472: istore #7
      //   474: aload #11
      //   476: iload #5
      //   478: iload_3
      //   479: invokespecial <init> : (II)V
      //   482: iload #4
      //   484: istore #6
      //   486: iload #5
      //   488: istore #7
      //   490: aload #11
      //   492: athrow
      //   493: iload #4
      //   495: iconst_1
      //   496: iadd
      //   497: istore #8
      //   499: iload #9
      //   501: bipush #12
      //   503: iushr
      //   504: sipush #224
      //   507: ior
      //   508: i2b
      //   509: istore #10
      //   511: iload #8
      //   513: istore #7
      //   515: aload_2
      //   516: iload #4
      //   518: iload #10
      //   520: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
      //   523: pop
      //   524: iload #8
      //   526: iconst_1
      //   527: iadd
      //   528: istore #4
      //   530: iload #9
      //   532: bipush #6
      //   534: iushr
      //   535: bipush #63
      //   537: iand
      //   538: sipush #128
      //   541: ior
      //   542: i2b
      //   543: istore #10
      //   545: iload #4
      //   547: istore #6
      //   549: iload #5
      //   551: istore #7
      //   553: aload_2
      //   554: iload #8
      //   556: iload #10
      //   558: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
      //   561: pop
      //   562: iload #4
      //   564: istore #6
      //   566: iload #5
      //   568: istore #7
      //   570: aload_2
      //   571: iload #4
      //   573: iload #9
      //   575: bipush #63
      //   577: iand
      //   578: sipush #128
      //   581: ior
      //   582: i2b
      //   583: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
      //   586: pop
      //   587: iinc #5, 1
      //   590: iinc #4, 1
      //   593: goto -> 107
      //   596: iload #4
      //   598: istore #6
      //   600: iload #5
      //   602: istore #7
      //   604: aload_2
      //   605: iload #4
      //   607: invokevirtual position : (I)Ljava/nio/Buffer;
      //   610: pop
      //   611: return
      //   612: aload_2
      //   613: invokevirtual position : ()I
      //   616: istore #4
      //   618: iload #5
      //   620: iload #6
      //   622: aload_2
      //   623: invokevirtual position : ()I
      //   626: isub
      //   627: iconst_1
      //   628: iadd
      //   629: invokestatic max : (II)I
      //   632: istore #7
      //   634: new java/lang/StringBuilder
      //   637: dup
      //   638: invokespecial <init> : ()V
      //   641: astore_2
      //   642: aload_2
      //   643: ldc 'Failed writing '
      //   645: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   648: pop
      //   649: aload_2
      //   650: aload_1
      //   651: iload #5
      //   653: invokeinterface charAt : (I)C
      //   658: invokevirtual append : (C)Ljava/lang/StringBuilder;
      //   661: pop
      //   662: aload_2
      //   663: ldc ' at index '
      //   665: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   668: pop
      //   669: aload_2
      //   670: iload #4
      //   672: iload #7
      //   674: iadd
      //   675: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   678: pop
      //   679: new java/lang/ArrayIndexOutOfBoundsException
      //   682: dup
      //   683: aload_2
      //   684: invokevirtual toString : ()Ljava/lang/String;
      //   687: invokespecial <init> : (Ljava/lang/String;)V
      //   690: athrow
      //   691: astore #11
      //   693: iload #7
      //   695: istore #5
      //   697: goto -> 612
      //   700: astore #11
      //   702: goto -> 442
      // Exception table:
      //   from	to	target	type
      //   30	40	691	java/lang/IndexOutOfBoundsException
      //   56	69	691	java/lang/IndexOutOfBoundsException
      //   89	99	691	java/lang/IndexOutOfBoundsException
      //   121	131	691	java/lang/IndexOutOfBoundsException
      //   147	157	691	java/lang/IndexOutOfBoundsException
      //   190	199	227	java/lang/IndexOutOfBoundsException
      //   203	220	227	java/lang/IndexOutOfBoundsException
      //   269	279	700	java/lang/IndexOutOfBoundsException
      //   283	293	700	java/lang/IndexOutOfBoundsException
      //   304	313	700	java/lang/IndexOutOfBoundsException
      //   335	344	440	java/lang/IndexOutOfBoundsException
      //   369	378	700	java/lang/IndexOutOfBoundsException
      //   403	412	440	java/lang/IndexOutOfBoundsException
      //   416	433	440	java/lang/IndexOutOfBoundsException
      //   461	466	691	java/lang/IndexOutOfBoundsException
      //   474	482	691	java/lang/IndexOutOfBoundsException
      //   490	493	691	java/lang/IndexOutOfBoundsException
      //   515	524	227	java/lang/IndexOutOfBoundsException
      //   553	562	691	java/lang/IndexOutOfBoundsException
      //   570	587	691	java/lang/IndexOutOfBoundsException
      //   604	611	691	java/lang/IndexOutOfBoundsException
    }
    
    abstract void encodeUtf8Direct(CharSequence param1CharSequence, ByteBuffer param1ByteBuffer);
    
    final boolean isValidUtf8(ByteBuffer param1ByteBuffer, int param1Int1, int param1Int2) {
      boolean bool = false;
      if (partialIsValidUtf8(0, param1ByteBuffer, param1Int1, param1Int2) == 0)
        bool = true; 
      return bool;
    }
    
    final boolean isValidUtf8(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) {
      boolean bool = false;
      if (partialIsValidUtf8(0, param1ArrayOfbyte, param1Int1, param1Int2) == 0)
        bool = true; 
      return bool;
    }
    
    final int partialIsValidUtf8(int param1Int1, ByteBuffer param1ByteBuffer, int param1Int2, int param1Int3) {
      if (param1ByteBuffer.hasArray()) {
        int i = param1ByteBuffer.arrayOffset();
        return partialIsValidUtf8(param1Int1, param1ByteBuffer.array(), param1Int2 + i, i + param1Int3);
      } 
      return param1ByteBuffer.isDirect() ? partialIsValidUtf8Direct(param1Int1, param1ByteBuffer, param1Int2, param1Int3) : partialIsValidUtf8Default(param1Int1, param1ByteBuffer, param1Int2, param1Int3);
    }
    
    abstract int partialIsValidUtf8(int param1Int1, byte[] param1ArrayOfbyte, int param1Int2, int param1Int3);
    
    final int partialIsValidUtf8Default(int param1Int1, ByteBuffer param1ByteBuffer, int param1Int2, int param1Int3) {
      int i = param1Int2;
      if (param1Int1 != 0) {
        if (param1Int2 >= param1Int3)
          return param1Int1; 
        byte b = (byte)param1Int1;
        if (b < -32) {
          if (b >= -62) {
            param1Int1 = param1Int2 + 1;
            if (param1ByteBuffer.get(param1Int2) > -65)
              return -1; 
          } else {
            return -1;
          } 
        } else if (b < -16) {
          byte b1 = (byte)(param1Int1 >> 8 ^ 0xFFFFFFFF);
          param1Int1 = b1;
          i = param1Int2;
          if (b1 == 0) {
            i = param1Int2 + 1;
            param1Int1 = param1ByteBuffer.get(param1Int2);
            if (i >= param1Int3)
              return Utf8.incompleteStateFor(b, param1Int1); 
          } 
          if (param1Int1 <= -65 && (b != -32 || param1Int1 >= -96) && (b != -19 || param1Int1 < -96)) {
            param1Int1 = i + 1;
            if (param1ByteBuffer.get(i) > -65)
              return -1; 
          } else {
            return -1;
          } 
        } else {
          i = (byte)(param1Int1 >> 8 ^ 0xFFFFFFFF);
          int k = 0;
          if (i == 0) {
            int m = param1Int2 + 1;
            byte b1 = param1ByteBuffer.get(param1Int2);
            i = b1;
            param1Int2 = k;
            param1Int1 = m;
            if (m >= param1Int3)
              return Utf8.incompleteStateFor(b, b1); 
          } else {
            byte b1 = (byte)(param1Int1 >> 16);
            param1Int1 = param1Int2;
            param1Int2 = b1;
          } 
          k = param1Int2;
          int j = param1Int1;
          if (param1Int2 == 0) {
            j = param1Int1 + 1;
            k = param1ByteBuffer.get(param1Int1);
            if (j >= param1Int3)
              return Utf8.incompleteStateFor(b, i, k); 
          } 
          if (i <= -65 && (b << 28) + i + 112 >> 30 == 0 && k <= -65) {
            i = j + 1;
            if (param1ByteBuffer.get(j) > -65)
              return -1; 
          } else {
            return -1;
          } 
          param1Int1 = i;
        } 
        return partialIsValidUtf8(param1ByteBuffer, param1Int1, param1Int3);
      } 
      param1Int1 = i;
    }
    
    abstract int partialIsValidUtf8Direct(int param1Int1, ByteBuffer param1ByteBuffer, int param1Int2, int param1Int3);
  }
  
  static final class SafeProcessor extends Processor {
    private static int partialIsValidUtf8(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) {
      while (param1Int1 < param1Int2 && param1ArrayOfbyte[param1Int1] >= 0)
        param1Int1++; 
      if (param1Int1 >= param1Int2) {
        param1Int1 = 0;
      } else {
        param1Int1 = partialIsValidUtf8NonAscii(param1ArrayOfbyte, param1Int1, param1Int2);
      } 
      return param1Int1;
    }
    
    private static int partialIsValidUtf8NonAscii(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) {
      while (true) {
        if (param1Int1 >= param1Int2)
          return 0; 
        int i = param1Int1 + 1;
        byte b = param1ArrayOfbyte[param1Int1];
        param1Int1 = i;
        if (b < 0) {
          if (b < -32) {
            if (i >= param1Int2)
              return b; 
            if (b >= -62) {
              param1Int1 = i + 1;
              if (param1ArrayOfbyte[i] > -65)
                return -1; 
              continue;
            } 
            return -1;
          } 
          if (b < -16) {
            if (i >= param1Int2 - 1)
              return Utf8.incompleteStateFor(param1ArrayOfbyte, i, param1Int2); 
            int j = i + 1;
            param1Int1 = param1ArrayOfbyte[i];
            if (param1Int1 <= -65 && (b != -32 || param1Int1 >= -96) && (b != -19 || param1Int1 < -96)) {
              param1Int1 = j + 1;
              if (param1ArrayOfbyte[j] > -65)
                return -1; 
              continue;
            } 
            return -1;
          } 
          if (i >= param1Int2 - 2)
            return Utf8.incompleteStateFor(param1ArrayOfbyte, i, param1Int2); 
          param1Int1 = i + 1;
          i = param1ArrayOfbyte[i];
          if (i <= -65 && (b << 28) + i + 112 >> 30 == 0) {
            i = param1Int1 + 1;
            if (param1ArrayOfbyte[param1Int1] <= -65) {
              param1Int1 = i + 1;
              if (param1ArrayOfbyte[i] > -65)
                break; 
              continue;
            } 
          } 
          break;
        } 
      } 
      return -1;
    }
    
    String decodeUtf8(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws InvalidProtocolBufferException {
      if ((param1Int1 | param1Int2 | param1ArrayOfbyte.length - param1Int1 - param1Int2) < 0)
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[] { Integer.valueOf(param1ArrayOfbyte.length), Integer.valueOf(param1Int1), Integer.valueOf(param1Int2) })); 
      int i = param1Int1 + param1Int2;
      char[] arrayOfChar = new char[param1Int2];
      for (param1Int2 = 0; param1Int1 < i; param1Int2++) {
        byte b = param1ArrayOfbyte[param1Int1];
        if (!Utf8.DecodeUtil.isOneByte(b))
          break; 
        param1Int1++;
        Utf8.DecodeUtil.handleOneByte(b, arrayOfChar, param1Int2);
      } 
      int j = param1Int2;
      param1Int2 = param1Int1;
      for (param1Int1 = j; param1Int2 < i; param1Int1 = param1Int1 + 1 + 1) {
        j = param1Int2 + 1;
        byte b2 = param1ArrayOfbyte[param1Int2];
        if (Utf8.DecodeUtil.isOneByte(b2)) {
          param1Int2 = param1Int1 + 1;
          Utf8.DecodeUtil.handleOneByte(b2, arrayOfChar, param1Int1);
          param1Int1 = param1Int2;
          param1Int2 = j;
          while (param1Int2 < i) {
            byte b = param1ArrayOfbyte[param1Int2];
            if (!Utf8.DecodeUtil.isOneByte(b))
              break; 
            param1Int2++;
            Utf8.DecodeUtil.handleOneByte(b, arrayOfChar, param1Int1);
            param1Int1++;
          } 
          continue;
        } 
        if (Utf8.DecodeUtil.isTwoBytes(b2)) {
          if (j >= i)
            throw InvalidProtocolBufferException.invalidUtf8(); 
          Utf8.DecodeUtil.handleTwoBytes(b2, param1ArrayOfbyte[j], arrayOfChar, param1Int1);
          param1Int2 = j + 1;
          param1Int1++;
          continue;
        } 
        if (Utf8.DecodeUtil.isThreeBytes(b2)) {
          if (j >= i - 1)
            throw InvalidProtocolBufferException.invalidUtf8(); 
          param1Int2 = j + 1;
          Utf8.DecodeUtil.handleThreeBytes(b2, param1ArrayOfbyte[j], param1ArrayOfbyte[param1Int2], arrayOfChar, param1Int1);
          param1Int2++;
          param1Int1++;
          continue;
        } 
        if (j >= i - 2)
          throw InvalidProtocolBufferException.invalidUtf8(); 
        param1Int2 = j + 1;
        byte b1 = param1ArrayOfbyte[j];
        j = param1Int2 + 1;
        Utf8.DecodeUtil.handleFourBytes(b2, b1, param1ArrayOfbyte[param1Int2], param1ArrayOfbyte[j], arrayOfChar, param1Int1);
        param1Int2 = j + 1;
      } 
      return new String(arrayOfChar, 0, param1Int1);
    }
    
    String decodeUtf8Direct(ByteBuffer param1ByteBuffer, int param1Int1, int param1Int2) throws InvalidProtocolBufferException {
      return decodeUtf8Default(param1ByteBuffer, param1Int1, param1Int2);
    }
    
    int encodeUtf8(CharSequence param1CharSequence, byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) {
      int i = param1CharSequence.length();
      int j = param1Int2 + param1Int1;
      param1Int2 = 0;
      while (param1Int2 < i) {
        int m = param1Int2 + param1Int1;
        if (m < j) {
          char c = param1CharSequence.charAt(param1Int2);
          if (c < '') {
            param1ArrayOfbyte[m] = (byte)(byte)c;
            param1Int2++;
          } 
        } 
      } 
      if (param1Int2 == i)
        return param1Int1 + i; 
      int k = param1Int1 + param1Int2;
      param1Int1 = param1Int2;
      while (true) {
        if (param1Int1 < i) {
          char c = param1CharSequence.charAt(param1Int1);
          if (c < '' && k < j) {
            param1Int2 = k + 1;
            param1ArrayOfbyte[k] = (byte)(byte)c;
          } else if (c < 'ࠀ' && k <= j - 2) {
            int m = k + 1;
            param1ArrayOfbyte[k] = (byte)(byte)(c >>> 6 | 0x3C0);
            param1Int2 = m + 1;
            param1ArrayOfbyte[m] = (byte)(byte)(c & 0x3F | 0x80);
          } else if ((c < '?' || '?' < c) && k <= j - 3) {
            param1Int2 = k + 1;
            param1ArrayOfbyte[k] = (byte)(byte)(c >>> 12 | 0x1E0);
            k = param1Int2 + 1;
            param1ArrayOfbyte[param1Int2] = (byte)(byte)(c >>> 6 & 0x3F | 0x80);
            param1Int2 = k + 1;
            param1ArrayOfbyte[k] = (byte)(byte)(c & 0x3F | 0x80);
          } else {
            if (k <= j - 4) {
              param1Int2 = param1Int1 + 1;
              if (param1Int2 != param1CharSequence.length()) {
                char c1 = param1CharSequence.charAt(param1Int2);
                if (!Character.isSurrogatePair(c, c1)) {
                  param1Int1 = param1Int2;
                } else {
                  param1Int1 = Character.toCodePoint(c, c1);
                  int m = k + 1;
                  param1ArrayOfbyte[k] = (byte)(byte)(param1Int1 >>> 18 | 0xF0);
                  k = m + 1;
                  param1ArrayOfbyte[m] = (byte)(byte)(param1Int1 >>> 12 & 0x3F | 0x80);
                  m = k + 1;
                  param1ArrayOfbyte[k] = (byte)(byte)(param1Int1 >>> 6 & 0x3F | 0x80);
                  k = m + 1;
                  param1ArrayOfbyte[m] = (byte)(byte)(param1Int1 & 0x3F | 0x80);
                  param1Int1 = param1Int2;
                  param1Int2 = k;
                  param1Int1++;
                  k = param1Int2;
                } 
              } 
              throw new Utf8.UnpairedSurrogateException(param1Int1 - 1, i);
            } 
            if ('?' <= c && c <= '?') {
              param1Int2 = param1Int1 + 1;
              if (param1Int2 == param1CharSequence.length() || !Character.isSurrogatePair(c, param1CharSequence.charAt(param1Int2)))
                throw new Utf8.UnpairedSurrogateException(param1Int1, i); 
            } 
            param1CharSequence = new StringBuilder();
            param1CharSequence.append("Failed writing ");
            param1CharSequence.append(c);
            param1CharSequence.append(" at index ");
            param1CharSequence.append(k);
            throw new ArrayIndexOutOfBoundsException(param1CharSequence.toString());
          } 
        } else {
          break;
        } 
        param1Int1++;
        k = param1Int2;
      } 
      return k;
    }
    
    void encodeUtf8Direct(CharSequence param1CharSequence, ByteBuffer param1ByteBuffer) {
      encodeUtf8Default(param1CharSequence, param1ByteBuffer);
    }
    
    int partialIsValidUtf8(int param1Int1, byte[] param1ArrayOfbyte, int param1Int2, int param1Int3) {
      int i = param1Int2;
      if (param1Int1 != 0) {
        if (param1Int2 >= param1Int3)
          return param1Int1; 
        byte b = (byte)param1Int1;
        if (b < -32) {
          if (b >= -62) {
            param1Int1 = param1Int2 + 1;
            if (param1ArrayOfbyte[param1Int2] > -65)
              return -1; 
          } else {
            return -1;
          } 
        } else if (b < -16) {
          byte b1 = (byte)(param1Int1 >> 8 ^ 0xFFFFFFFF);
          param1Int1 = b1;
          i = param1Int2;
          if (b1 == 0) {
            i = param1Int2 + 1;
            param1Int1 = param1ArrayOfbyte[param1Int2];
            if (i >= param1Int3)
              return Utf8.incompleteStateFor(b, param1Int1); 
          } 
          if (param1Int1 <= -65 && (b != -32 || param1Int1 >= -96) && (b != -19 || param1Int1 < -96)) {
            param1Int1 = i + 1;
            if (param1ArrayOfbyte[i] > -65)
              return -1; 
          } else {
            return -1;
          } 
        } else {
          i = (byte)(param1Int1 >> 8 ^ 0xFFFFFFFF);
          int j = 0;
          if (i == 0) {
            int m = param1Int2 + 1;
            byte b1 = param1ArrayOfbyte[param1Int2];
            i = b1;
            param1Int2 = j;
            param1Int1 = m;
            if (m >= param1Int3)
              return Utf8.incompleteStateFor(b, b1); 
          } else {
            j = (byte)(param1Int1 >> 16);
            param1Int1 = param1Int2;
            param1Int2 = j;
          } 
          int k = param1Int2;
          j = param1Int1;
          if (param1Int2 == 0) {
            j = param1Int1 + 1;
            k = param1ArrayOfbyte[param1Int1];
            if (j >= param1Int3)
              return Utf8.incompleteStateFor(b, i, k); 
          } 
          if (i <= -65 && (b << 28) + i + 112 >> 30 == 0 && k <= -65) {
            i = j + 1;
            if (param1ArrayOfbyte[j] > -65)
              return -1; 
          } else {
            return -1;
          } 
          param1Int1 = i;
        } 
        return partialIsValidUtf8(param1ArrayOfbyte, param1Int1, param1Int3);
      } 
      param1Int1 = i;
    }
    
    int partialIsValidUtf8Direct(int param1Int1, ByteBuffer param1ByteBuffer, int param1Int2, int param1Int3) {
      return partialIsValidUtf8Default(param1Int1, param1ByteBuffer, param1Int2, param1Int3);
    }
  }
  
  static class UnpairedSurrogateException extends IllegalArgumentException {
    UnpairedSurrogateException(int param1Int1, int param1Int2) {
      super(stringBuilder.toString());
    }
  }
  
  static final class UnsafeProcessor extends Processor {
    static boolean isAvailable() {
      boolean bool;
      if (UnsafeUtil.hasUnsafeArrayOperations() && UnsafeUtil.hasUnsafeByteBufferOperations()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private static int partialIsValidUtf8(long param1Long, int param1Int) {
      int i = unsafeEstimateConsecutiveAscii(param1Long, param1Int);
      param1Long += i;
      param1Int -= i;
      while (true) {
        long l;
        byte b = 0;
        i = param1Int;
        param1Int = b;
        while (true) {
          l = param1Long;
          if (i > 0) {
            l = param1Long + 1L;
            param1Int = UnsafeUtil.getByte(param1Long);
            if (param1Int >= 0) {
              i--;
              param1Long = l;
              continue;
            } 
          } 
          break;
        } 
        if (i == 0)
          return 0; 
        i--;
        if (param1Int < -32) {
          if (i == 0)
            return param1Int; 
          i--;
          if (param1Int >= -62) {
            param1Long = 1L + l;
            param1Int = i;
            if (UnsafeUtil.getByte(l) > -65)
              return -1; 
            continue;
          } 
          return -1;
        } 
        if (param1Int < -16) {
          if (i < 2)
            return unsafeIncompleteStateFor(l, param1Int, i); 
          i -= 2;
          long l1 = l + 1L;
          b = UnsafeUtil.getByte(l);
          if (b <= -65 && (param1Int != -32 || b >= -96) && (param1Int != -19 || b < -96)) {
            param1Long = 1L + l1;
            param1Int = i;
            if (UnsafeUtil.getByte(l1) > -65)
              return -1; 
            continue;
          } 
          return -1;
        } 
        if (i < 3)
          return unsafeIncompleteStateFor(l, param1Int, i); 
        i -= 3;
        param1Long = l + 1L;
        b = UnsafeUtil.getByte(l);
        if (b <= -65 && (param1Int << 28) + b + 112 >> 30 == 0) {
          l = param1Long + 1L;
          if (UnsafeUtil.getByte(param1Long) <= -65) {
            param1Long = 1L + l;
            param1Int = i;
            if (UnsafeUtil.getByte(l) > -65)
              break; 
            continue;
          } 
        } 
        break;
      } 
      return -1;
    }
    
    private static int partialIsValidUtf8(byte[] param1ArrayOfbyte, long param1Long, int param1Int) {
      int i = unsafeEstimateConsecutiveAscii(param1ArrayOfbyte, param1Long, param1Int);
      param1Int -= i;
      param1Long += i;
      while (true) {
        long l;
        byte b = 0;
        i = param1Int;
        param1Int = b;
        while (true) {
          l = param1Long;
          if (i > 0) {
            l = param1Long + 1L;
            param1Int = UnsafeUtil.getByte(param1ArrayOfbyte, param1Long);
            if (param1Int >= 0) {
              i--;
              param1Long = l;
              continue;
            } 
          } 
          break;
        } 
        if (i == 0)
          return 0; 
        i--;
        if (param1Int < -32) {
          if (i == 0)
            return param1Int; 
          i--;
          if (param1Int >= -62) {
            param1Long = 1L + l;
            param1Int = i;
            if (UnsafeUtil.getByte(param1ArrayOfbyte, l) > -65)
              return -1; 
            continue;
          } 
          return -1;
        } 
        if (param1Int < -16) {
          if (i < 2)
            return unsafeIncompleteStateFor(param1ArrayOfbyte, param1Int, l, i); 
          i -= 2;
          long l1 = l + 1L;
          b = UnsafeUtil.getByte(param1ArrayOfbyte, l);
          if (b <= -65 && (param1Int != -32 || b >= -96) && (param1Int != -19 || b < -96)) {
            param1Long = 1L + l1;
            param1Int = i;
            if (UnsafeUtil.getByte(param1ArrayOfbyte, l1) > -65)
              return -1; 
            continue;
          } 
          return -1;
        } 
        if (i < 3)
          return unsafeIncompleteStateFor(param1ArrayOfbyte, param1Int, l, i); 
        i -= 3;
        param1Long = l + 1L;
        b = UnsafeUtil.getByte(param1ArrayOfbyte, l);
        if (b <= -65 && (param1Int << 28) + b + 112 >> 30 == 0) {
          l = param1Long + 1L;
          if (UnsafeUtil.getByte(param1ArrayOfbyte, param1Long) <= -65) {
            param1Long = 1L + l;
            param1Int = i;
            if (UnsafeUtil.getByte(param1ArrayOfbyte, l) > -65)
              break; 
            continue;
          } 
        } 
        break;
      } 
      return -1;
    }
    
    private static int unsafeEstimateConsecutiveAscii(long param1Long, int param1Int) {
      if (param1Int < 16)
        return 0; 
      int i = 8 - ((int)param1Long & 0x7);
      int j = i;
      while (j > 0) {
        if (UnsafeUtil.getByte(param1Long) < 0)
          return i - j; 
        j--;
        param1Long = 1L + param1Long;
      } 
      for (j = param1Int - i; j >= 8 && (UnsafeUtil.getLong(param1Long) & 0x8080808080808080L) == 0L; j -= 8)
        param1Long += 8L; 
      return param1Int - j;
    }
    
    private static int unsafeEstimateConsecutiveAscii(byte[] param1ArrayOfbyte, long param1Long, int param1Int) {
      byte b = 0;
      if (param1Int < 16)
        return 0; 
      while (b < param1Int) {
        if (UnsafeUtil.getByte(param1ArrayOfbyte, param1Long) < 0)
          return b; 
        b++;
        param1Long = 1L + param1Long;
      } 
      return param1Int;
    }
    
    private static int unsafeIncompleteStateFor(long param1Long, int param1Int1, int param1Int2) {
      switch (param1Int2) {
        default:
          throw new AssertionError();
        case 2:
          return Utf8.incompleteStateFor(param1Int1, UnsafeUtil.getByte(param1Long), UnsafeUtil.getByte(param1Long + 1L));
        case 1:
          return Utf8.incompleteStateFor(param1Int1, UnsafeUtil.getByte(param1Long));
        case 0:
          break;
      } 
      return Utf8.incompleteStateFor(param1Int1);
    }
    
    private static int unsafeIncompleteStateFor(byte[] param1ArrayOfbyte, int param1Int1, long param1Long, int param1Int2) {
      switch (param1Int2) {
        default:
          throw new AssertionError();
        case 2:
          return Utf8.incompleteStateFor(param1Int1, UnsafeUtil.getByte(param1ArrayOfbyte, param1Long), UnsafeUtil.getByte(param1ArrayOfbyte, param1Long + 1L));
        case 1:
          return Utf8.incompleteStateFor(param1Int1, UnsafeUtil.getByte(param1ArrayOfbyte, param1Long));
        case 0:
          break;
      } 
      return Utf8.incompleteStateFor(param1Int1);
    }
    
    String decodeUtf8(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws InvalidProtocolBufferException {
      if ((param1Int1 | param1Int2 | param1ArrayOfbyte.length - param1Int1 - param1Int2) < 0)
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[] { Integer.valueOf(param1ArrayOfbyte.length), Integer.valueOf(param1Int1), Integer.valueOf(param1Int2) })); 
      int i = param1Int1 + param1Int2;
      char[] arrayOfChar = new char[param1Int2];
      for (param1Int2 = 0; param1Int1 < i; param1Int2++) {
        byte b = UnsafeUtil.getByte(param1ArrayOfbyte, param1Int1);
        if (!Utf8.DecodeUtil.isOneByte(b))
          break; 
        param1Int1++;
        Utf8.DecodeUtil.handleOneByte(b, arrayOfChar, param1Int2);
      } 
      int j = param1Int2;
      param1Int2 = param1Int1;
      for (param1Int1 = j; param1Int2 < i; param1Int1 = param1Int1 + 1 + 1) {
        j = param1Int2 + 1;
        byte b1 = UnsafeUtil.getByte(param1ArrayOfbyte, param1Int2);
        if (Utf8.DecodeUtil.isOneByte(b1)) {
          param1Int2 = param1Int1 + 1;
          Utf8.DecodeUtil.handleOneByte(b1, arrayOfChar, param1Int1);
          param1Int1 = param1Int2;
          param1Int2 = j;
          while (param1Int2 < i) {
            b1 = UnsafeUtil.getByte(param1ArrayOfbyte, param1Int2);
            if (!Utf8.DecodeUtil.isOneByte(b1))
              break; 
            param1Int2++;
            Utf8.DecodeUtil.handleOneByte(b1, arrayOfChar, param1Int1);
            param1Int1++;
          } 
          continue;
        } 
        if (Utf8.DecodeUtil.isTwoBytes(b1)) {
          if (j >= i)
            throw InvalidProtocolBufferException.invalidUtf8(); 
          Utf8.DecodeUtil.handleTwoBytes(b1, UnsafeUtil.getByte(param1ArrayOfbyte, j), arrayOfChar, param1Int1);
          param1Int2 = j + 1;
          param1Int1++;
          continue;
        } 
        if (Utf8.DecodeUtil.isThreeBytes(b1)) {
          if (j >= i - 1)
            throw InvalidProtocolBufferException.invalidUtf8(); 
          param1Int2 = j + 1;
          Utf8.DecodeUtil.handleThreeBytes(b1, UnsafeUtil.getByte(param1ArrayOfbyte, j), UnsafeUtil.getByte(param1ArrayOfbyte, param1Int2), arrayOfChar, param1Int1);
          param1Int2++;
          param1Int1++;
          continue;
        } 
        if (j >= i - 2)
          throw InvalidProtocolBufferException.invalidUtf8(); 
        param1Int2 = j + 1;
        byte b2 = UnsafeUtil.getByte(param1ArrayOfbyte, j);
        j = param1Int2 + 1;
        Utf8.DecodeUtil.handleFourBytes(b1, b2, UnsafeUtil.getByte(param1ArrayOfbyte, param1Int2), UnsafeUtil.getByte(param1ArrayOfbyte, j), arrayOfChar, param1Int1);
        param1Int2 = j + 1;
      } 
      return new String(arrayOfChar, 0, param1Int1);
    }
    
    String decodeUtf8Direct(ByteBuffer param1ByteBuffer, int param1Int1, int param1Int2) throws InvalidProtocolBufferException {
      long l3;
      if ((param1Int1 | param1Int2 | param1ByteBuffer.limit() - param1Int1 - param1Int2) < 0)
        throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", new Object[] { Integer.valueOf(param1ByteBuffer.limit()), Integer.valueOf(param1Int1), Integer.valueOf(param1Int2) })); 
      long l1 = UnsafeUtil.addressOffset(param1ByteBuffer) + param1Int1;
      long l2 = param1Int2 + l1;
      char[] arrayOfChar = new char[param1Int2];
      param1Int1 = 0;
      while (true) {
        param1Int2 = param1Int1;
        l3 = l1;
        if (l1 < l2) {
          byte b = UnsafeUtil.getByte(l1);
          if (!Utf8.DecodeUtil.isOneByte(b)) {
            param1Int2 = param1Int1;
            l3 = l1;
            break;
          } 
          l1++;
          Utf8.DecodeUtil.handleOneByte(b, arrayOfChar, param1Int1);
          param1Int1++;
          continue;
        } 
        break;
      } 
      while (true) {
        param1Int1 = param1Int2;
        l1 = l3;
        while (l1 < l2) {
          l3 = l1 + 1L;
          byte b1 = UnsafeUtil.getByte(l1);
          if (Utf8.DecodeUtil.isOneByte(b1)) {
            param1Int2 = param1Int1 + 1;
            Utf8.DecodeUtil.handleOneByte(b1, arrayOfChar, param1Int1);
            param1Int1 = param1Int2;
            l1 = l3;
            while (l1 < l2) {
              b1 = UnsafeUtil.getByte(l1);
              if (!Utf8.DecodeUtil.isOneByte(b1))
                break; 
              l1++;
              Utf8.DecodeUtil.handleOneByte(b1, arrayOfChar, param1Int1);
              param1Int1++;
            } 
            continue;
          } 
          if (Utf8.DecodeUtil.isTwoBytes(b1)) {
            if (l3 >= l2)
              throw InvalidProtocolBufferException.invalidUtf8(); 
            l1 = l3 + 1L;
            Utf8.DecodeUtil.handleTwoBytes(b1, UnsafeUtil.getByte(l3), arrayOfChar, param1Int1);
            param1Int1++;
            continue;
          } 
          if (Utf8.DecodeUtil.isThreeBytes(b1)) {
            if (l3 >= l2 - 1L)
              throw InvalidProtocolBufferException.invalidUtf8(); 
            l1 = l3 + 1L;
            Utf8.DecodeUtil.handleThreeBytes(b1, UnsafeUtil.getByte(l3), UnsafeUtil.getByte(l1), arrayOfChar, param1Int1);
            param1Int1++;
            l1++;
            continue;
          } 
          if (l3 >= l2 - 2L)
            throw InvalidProtocolBufferException.invalidUtf8(); 
          l1 = l3 + 1L;
          byte b2 = UnsafeUtil.getByte(l3);
          long l = l1 + 1L;
          byte b3 = UnsafeUtil.getByte(l1);
          l3 = l + 1L;
          Utf8.DecodeUtil.handleFourBytes(b1, b2, b3, UnsafeUtil.getByte(l), arrayOfChar, param1Int1);
          param1Int2 = param1Int1 + 1 + 1;
        } 
        return new String(arrayOfChar, 0, param1Int1);
      } 
    }
    
    int encodeUtf8(CharSequence param1CharSequence, byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) {
      StringBuilder stringBuilder;
      long l1 = param1Int1;
      long l2 = param1Int2 + l1;
      int i = param1CharSequence.length();
      if (i > param1Int2 || param1ArrayOfbyte.length - param1Int2 < param1Int1) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Failed writing ");
        stringBuilder.append(param1CharSequence.charAt(i - 1));
        stringBuilder.append(" at index ");
        stringBuilder.append(param1Int1 + param1Int2);
        throw new ArrayIndexOutOfBoundsException(stringBuilder.toString());
      } 
      param1Int2 = 0;
      while (param1Int2 < i) {
        param1Int1 = param1CharSequence.charAt(param1Int2);
        if (param1Int1 < 128) {
          UnsafeUtil.putByte((byte[])stringBuilder, l1, (byte)param1Int1);
          param1Int2++;
          l1 = 1L + l1;
        } 
      } 
      long l3 = l1;
      param1Int1 = param1Int2;
      if (param1Int2 == i)
        return (int)l1; 
      while (true) {
        if (param1Int1 < i) {
          char c = param1CharSequence.charAt(param1Int1);
          if (c < '' && l3 < l2) {
            l1 = l3 + 1L;
            UnsafeUtil.putByte((byte[])stringBuilder, l3, (byte)c);
          } else if (c < 'ࠀ' && l3 <= l2 - 2L) {
            long l = l3 + 1L;
            UnsafeUtil.putByte((byte[])stringBuilder, l3, (byte)(c >>> 6 | 0x3C0));
            l1 = l + 1L;
            UnsafeUtil.putByte((byte[])stringBuilder, l, (byte)(c & 0x3F | 0x80));
          } else if ((c < '?' || '?' < c) && l3 <= l2 - 3L) {
            l1 = l3 + 1L;
            UnsafeUtil.putByte((byte[])stringBuilder, l3, (byte)(c >>> 12 | 0x1E0));
            l3 = l1 + 1L;
            UnsafeUtil.putByte((byte[])stringBuilder, l1, (byte)(c >>> 6 & 0x3F | 0x80));
            l1 = l3 + 1L;
            UnsafeUtil.putByte((byte[])stringBuilder, l3, (byte)(c & 0x3F | 0x80));
          } else {
            if (l3 <= l2 - 4L) {
              param1Int2 = param1Int1 + 1;
              if (param1Int2 != i) {
                char c1 = param1CharSequence.charAt(param1Int2);
                if (!Character.isSurrogatePair(c, c1)) {
                  param1Int1 = param1Int2;
                } else {
                  param1Int1 = Character.toCodePoint(c, c1);
                  l1 = l3 + 1L;
                  UnsafeUtil.putByte((byte[])stringBuilder, l3, (byte)(param1Int1 >>> 18 | 0xF0));
                  l3 = l1 + 1L;
                  UnsafeUtil.putByte((byte[])stringBuilder, l1, (byte)(param1Int1 >>> 12 & 0x3F | 0x80));
                  long l = l3 + 1L;
                  UnsafeUtil.putByte((byte[])stringBuilder, l3, (byte)(param1Int1 >>> 6 & 0x3F | 0x80));
                  l1 = l + 1L;
                  UnsafeUtil.putByte((byte[])stringBuilder, l, (byte)(param1Int1 & 0x3F | 0x80));
                  param1Int1 = param1Int2;
                  param1Int1++;
                  l3 = l1;
                } 
              } 
              throw new Utf8.UnpairedSurrogateException(param1Int1 - 1, i);
            } 
            if ('?' <= c && c <= '?') {
              param1Int2 = param1Int1 + 1;
              if (param1Int2 == i || !Character.isSurrogatePair(c, param1CharSequence.charAt(param1Int2)))
                throw new Utf8.UnpairedSurrogateException(param1Int1, i); 
            } 
            param1CharSequence = new StringBuilder();
            param1CharSequence.append("Failed writing ");
            param1CharSequence.append(c);
            param1CharSequence.append(" at index ");
            param1CharSequence.append(l3);
            throw new ArrayIndexOutOfBoundsException(param1CharSequence.toString());
          } 
        } else {
          break;
        } 
        param1Int1++;
        l3 = l1;
      } 
      return (int)l3;
    }
    
    void encodeUtf8Direct(CharSequence param1CharSequence, ByteBuffer param1ByteBuffer) {
      int k;
      long l4;
      long l1 = UnsafeUtil.addressOffset(param1ByteBuffer);
      long l2 = param1ByteBuffer.position() + l1;
      long l3 = param1ByteBuffer.limit() + l1;
      int i = param1CharSequence.length();
      if (i > l3 - l2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed writing ");
        stringBuilder.append(param1CharSequence.charAt(i - 1));
        stringBuilder.append(" at index ");
        stringBuilder.append(param1ByteBuffer.limit());
        throw new ArrayIndexOutOfBoundsException(stringBuilder.toString());
      } 
      int j = 0;
      while (true) {
        k = 128;
        l4 = 1L;
        if (j < i) {
          char c = param1CharSequence.charAt(j);
          if (c < '') {
            UnsafeUtil.putByte(l2, (byte)c);
            j++;
            l2++;
            continue;
          } 
        } 
        break;
      } 
      long l5 = l2;
      int m = j;
      if (j == i) {
        param1ByteBuffer.position((int)(l2 - l1));
        return;
      } 
      while (true) {
        if (m < i) {
          char c = param1CharSequence.charAt(m);
          if (c < k && l5 < l3) {
            UnsafeUtil.putByte(l5, (byte)c);
            l2 = l4;
            l5 += l4;
            l4 = l2;
            j = m;
            m = k;
            l2 = l5;
          } else if (c < 'ࠀ' && l5 <= l3 - 2L) {
            l2 = l5 + l4;
            UnsafeUtil.putByte(l5, (byte)(c >>> 6 | 0x3C0));
            UnsafeUtil.putByte(l2, (byte)(c & 0x3F | 0x80));
            k = 128;
            l2 += l4;
            j = m;
            m = k;
          } else if ((c < '?' || '?' < c) && l5 <= l3 - 3L) {
            l2 = l5 + l4;
            UnsafeUtil.putByte(l5, (byte)(c >>> 12 | 0x1E0));
            l4 = l2 + l4;
            UnsafeUtil.putByte(l2, (byte)(c >>> 6 & 0x3F | 0x80));
            UnsafeUtil.putByte(l4, (byte)(c & 0x3F | 0x80));
            l2 = l4 + 1L;
            l4 = 1L;
            k = 128;
            j = m;
            m = k;
          } else {
            if (l5 <= l3 - 4L) {
              j = m + 1;
              if (j != i) {
                char c1 = param1CharSequence.charAt(j);
                if (!Character.isSurrogatePair(c, c1)) {
                  m = j;
                } else {
                  k = Character.toCodePoint(c, c1);
                  l4 = l5 + 1L;
                  UnsafeUtil.putByte(l5, (byte)(k >>> 18 | 0xF0));
                  l2 = l4 + 1L;
                  m = 128;
                  UnsafeUtil.putByte(l4, (byte)(k >>> 12 & 0x3F | 0x80));
                  l5 = l2 + 1L;
                  UnsafeUtil.putByte(l2, (byte)(k >>> 6 & 0x3F | 0x80));
                  l4 = 1L;
                  l2 = l5 + 1L;
                  UnsafeUtil.putByte(l5, (byte)(k & 0x3F | 0x80));
                  j++;
                  k = m;
                  l5 = l2;
                  m = j;
                } 
              } 
              throw new Utf8.UnpairedSurrogateException(m - 1, i);
            } 
            if ('?' <= c && c <= '?') {
              j = m + 1;
              if (j == i || !Character.isSurrogatePair(c, param1CharSequence.charAt(j)))
                throw new Utf8.UnpairedSurrogateException(m, i); 
            } 
            param1CharSequence = new StringBuilder();
            param1CharSequence.append("Failed writing ");
            param1CharSequence.append(c);
            param1CharSequence.append(" at index ");
            param1CharSequence.append(l5);
            throw new ArrayIndexOutOfBoundsException(param1CharSequence.toString());
          } 
        } else {
          break;
        } 
        j++;
        k = m;
        l5 = l2;
        m = j;
      } 
      param1ByteBuffer.position((int)(l5 - l1));
    }
    
    int partialIsValidUtf8(int param1Int1, byte[] param1ArrayOfbyte, int param1Int2, int param1Int3) {
      long l3;
      int i = param1ArrayOfbyte.length;
      boolean bool = false;
      if ((param1Int2 | param1Int3 | i - param1Int3) < 0)
        throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", new Object[] { Integer.valueOf(param1ArrayOfbyte.length), Integer.valueOf(param1Int2), Integer.valueOf(param1Int3) })); 
      long l1 = param1Int2;
      long l2 = param1Int3;
      if (param1Int1 != 0) {
        if (l1 >= l2)
          return param1Int1; 
        i = (byte)param1Int1;
        if (i < -32) {
          if (i >= -62) {
            l3 = l1 + 1L;
            if (UnsafeUtil.getByte(param1ArrayOfbyte, l1) > -65)
              return -1; 
          } else {
            return -1;
          } 
        } else if (i < -16) {
          param1Int2 = (byte)(param1Int1 >> 8 ^ 0xFFFFFFFF);
          long l = l1;
          param1Int1 = param1Int2;
          if (param1Int2 == 0) {
            l = l1 + 1L;
            param1Int1 = UnsafeUtil.getByte(param1ArrayOfbyte, l1);
            if (l >= l2)
              return Utf8.incompleteStateFor(i, param1Int1); 
          } 
          if (param1Int1 <= -65 && (i != -32 || param1Int1 >= -96) && (i != -19 || param1Int1 < -96)) {
            l3 = l + 1L;
            if (UnsafeUtil.getByte(param1ArrayOfbyte, l) > -65)
              return -1; 
          } else {
            return -1;
          } 
        } else {
          param1Int2 = (byte)(param1Int1 >> 8 ^ 0xFFFFFFFF);
          if (param1Int2 == 0) {
            l3 = l1 + 1L;
            param1Int2 = UnsafeUtil.getByte(param1ArrayOfbyte, l1);
            if (l3 >= l2)
              return Utf8.incompleteStateFor(i, param1Int2); 
            l1 = l3;
            param1Int1 = bool;
          } else {
            param1Int1 = (byte)(param1Int1 >> 16);
          } 
          param1Int3 = param1Int1;
          long l = l1;
          if (param1Int1 == 0) {
            l = l1 + 1L;
            param1Int3 = UnsafeUtil.getByte(param1ArrayOfbyte, l1);
            if (l >= l2)
              return Utf8.incompleteStateFor(i, param1Int2, param1Int3); 
          } 
          if (param1Int2 <= -65 && (i << 28) + param1Int2 + 112 >> 30 == 0 && param1Int3 <= -65) {
            l3 = l + 1L;
            if (UnsafeUtil.getByte(param1ArrayOfbyte, l) > -65)
              return -1; 
          } else {
            return -1;
          } 
        } 
      } else {
        l3 = l1;
      } 
      return partialIsValidUtf8(param1ArrayOfbyte, l3, (int)(l2 - l3));
    }
    
    int partialIsValidUtf8Direct(int param1Int1, ByteBuffer param1ByteBuffer, int param1Int2, int param1Int3) {
      long l3;
      int i = param1ByteBuffer.limit();
      boolean bool = false;
      if ((param1Int2 | param1Int3 | i - param1Int3) < 0)
        throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", new Object[] { Integer.valueOf(param1ByteBuffer.limit()), Integer.valueOf(param1Int2), Integer.valueOf(param1Int3) })); 
      long l1 = UnsafeUtil.addressOffset(param1ByteBuffer) + param1Int2;
      long l2 = (param1Int3 - param1Int2) + l1;
      if (param1Int1 != 0) {
        if (l1 >= l2)
          return param1Int1; 
        i = (byte)param1Int1;
        if (i < -32) {
          if (i >= -62) {
            l3 = l1 + 1L;
            if (UnsafeUtil.getByte(l1) > -65)
              return -1; 
          } else {
            return -1;
          } 
        } else if (i < -16) {
          param1Int2 = (byte)(param1Int1 >> 8 ^ 0xFFFFFFFF);
          long l = l1;
          param1Int1 = param1Int2;
          if (param1Int2 == 0) {
            l = l1 + 1L;
            param1Int1 = UnsafeUtil.getByte(l1);
            if (l >= l2)
              return Utf8.incompleteStateFor(i, param1Int1); 
          } 
          if (param1Int1 <= -65 && (i != -32 || param1Int1 >= -96) && (i != -19 || param1Int1 < -96)) {
            l3 = l + 1L;
            if (UnsafeUtil.getByte(l) > -65)
              return -1; 
          } else {
            return -1;
          } 
        } else {
          param1Int2 = (byte)(param1Int1 >> 8 ^ 0xFFFFFFFF);
          if (param1Int2 == 0) {
            l3 = l1 + 1L;
            param1Int2 = UnsafeUtil.getByte(l1);
            if (l3 >= l2)
              return Utf8.incompleteStateFor(i, param1Int2); 
            l1 = l3;
            param1Int1 = bool;
          } else {
            param1Int1 = (byte)(param1Int1 >> 16);
          } 
          param1Int3 = param1Int1;
          long l = l1;
          if (param1Int1 == 0) {
            l = l1 + 1L;
            param1Int3 = UnsafeUtil.getByte(l1);
            if (l >= l2)
              return Utf8.incompleteStateFor(i, param1Int2, param1Int3); 
          } 
          if (param1Int2 <= -65 && (i << 28) + param1Int2 + 112 >> 30 == 0 && param1Int3 <= -65) {
            l3 = l + 1L;
            if (UnsafeUtil.getByte(l) > -65)
              return -1; 
          } else {
            return -1;
          } 
        } 
      } else {
        l3 = l1;
      } 
      return partialIsValidUtf8(l3, (int)(l2 - l3));
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\Utf8.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */