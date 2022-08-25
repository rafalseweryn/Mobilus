package com.google.android.gms.common.util;

import java.io.ByteArrayOutputStream;
import java.util.StringTokenizer;

public final class HexDumpUtils {
  public static byte[] bytesFromString(String paramString) {
    StringTokenizer stringTokenizer = new StringTokenizer(paramString, " \t\n");
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    while (stringTokenizer.hasMoreTokens()) {
      try {
        byteArrayOutputStream.write(Integer.parseInt(stringTokenizer.nextToken(), 16) & 0xFF);
      } catch (NumberFormatException numberFormatException) {
        return null;
      } 
    } 
    return numberFormatException.toByteArray();
  }
  
  public static String dump(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: ifnull -> 397
    //   4: aload_0
    //   5: arraylength
    //   6: ifeq -> 397
    //   9: iload_1
    //   10: iflt -> 397
    //   13: iload_2
    //   14: ifle -> 397
    //   17: iload_1
    //   18: iload_2
    //   19: iadd
    //   20: aload_0
    //   21: arraylength
    //   22: if_icmple -> 28
    //   25: goto -> 397
    //   28: bipush #57
    //   30: istore #4
    //   32: iload_3
    //   33: ifeq -> 40
    //   36: bipush #75
    //   38: istore #4
    //   40: new java/lang/StringBuilder
    //   43: dup
    //   44: iload #4
    //   46: iload_2
    //   47: bipush #16
    //   49: iadd
    //   50: iconst_1
    //   51: isub
    //   52: bipush #16
    //   54: idiv
    //   55: imul
    //   56: invokespecial <init> : (I)V
    //   59: astore #5
    //   61: iload_2
    //   62: istore #4
    //   64: iconst_0
    //   65: istore #6
    //   67: iload #6
    //   69: istore #7
    //   71: iload #4
    //   73: ifle -> 391
    //   76: iload #6
    //   78: ifne -> 148
    //   81: iload_2
    //   82: ldc 65536
    //   84: if_icmpge -> 121
    //   87: ldc '%04X:'
    //   89: astore #8
    //   91: iconst_1
    //   92: anewarray java/lang/Object
    //   95: astore #9
    //   97: aload #9
    //   99: iconst_0
    //   100: iload_1
    //   101: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   104: aastore
    //   105: aload #5
    //   107: aload #8
    //   109: aload #9
    //   111: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   114: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   117: pop
    //   118: goto -> 142
    //   121: ldc '%08X:'
    //   123: astore #8
    //   125: iconst_1
    //   126: anewarray java/lang/Object
    //   129: astore #9
    //   131: aload #9
    //   133: iconst_0
    //   134: iload_1
    //   135: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   138: aastore
    //   139: goto -> 105
    //   142: iload_1
    //   143: istore #10
    //   145: goto -> 171
    //   148: iload #7
    //   150: istore #10
    //   152: iload #6
    //   154: bipush #8
    //   156: if_icmpne -> 171
    //   159: aload #5
    //   161: ldc ' -'
    //   163: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: pop
    //   167: iload #7
    //   169: istore #10
    //   171: aload #5
    //   173: ldc ' %02X'
    //   175: iconst_1
    //   176: anewarray java/lang/Object
    //   179: dup
    //   180: iconst_0
    //   181: aload_0
    //   182: iload_1
    //   183: baload
    //   184: sipush #255
    //   187: iand
    //   188: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   191: aastore
    //   192: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   195: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: pop
    //   199: iload #4
    //   201: iconst_1
    //   202: isub
    //   203: istore #11
    //   205: iinc #6, 1
    //   208: iload_3
    //   209: ifeq -> 346
    //   212: iload #6
    //   214: bipush #16
    //   216: if_icmpeq -> 224
    //   219: iload #11
    //   221: ifne -> 346
    //   224: bipush #16
    //   226: iload #6
    //   228: isub
    //   229: istore #7
    //   231: iload #7
    //   233: ifle -> 260
    //   236: iconst_0
    //   237: istore #4
    //   239: iload #4
    //   241: iload #7
    //   243: if_icmpge -> 260
    //   246: aload #5
    //   248: ldc '   '
    //   250: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   253: pop
    //   254: iinc #4, 1
    //   257: goto -> 239
    //   260: iload #7
    //   262: bipush #8
    //   264: if_icmplt -> 275
    //   267: aload #5
    //   269: ldc '  '
    //   271: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   274: pop
    //   275: aload #5
    //   277: ldc '  '
    //   279: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   282: pop
    //   283: iconst_0
    //   284: istore #4
    //   286: iload #4
    //   288: iload #6
    //   290: if_icmpge -> 346
    //   293: aload_0
    //   294: iload #10
    //   296: iload #4
    //   298: iadd
    //   299: baload
    //   300: i2c
    //   301: istore #7
    //   303: iload #7
    //   305: bipush #32
    //   307: if_icmplt -> 324
    //   310: iload #7
    //   312: bipush #126
    //   314: if_icmpgt -> 324
    //   317: iload #7
    //   319: istore #12
    //   321: goto -> 332
    //   324: bipush #46
    //   326: istore #7
    //   328: iload #7
    //   330: istore #12
    //   332: aload #5
    //   334: iload #12
    //   336: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   339: pop
    //   340: iinc #4, 1
    //   343: goto -> 286
    //   346: iload #6
    //   348: bipush #16
    //   350: if_icmpeq -> 362
    //   353: iload #6
    //   355: istore #4
    //   357: iload #11
    //   359: ifne -> 373
    //   362: aload #5
    //   364: bipush #10
    //   366: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   369: pop
    //   370: iconst_0
    //   371: istore #4
    //   373: iinc #1, 1
    //   376: iload #4
    //   378: istore #6
    //   380: iload #10
    //   382: istore #7
    //   384: iload #11
    //   386: istore #4
    //   388: goto -> 71
    //   391: aload #5
    //   393: invokevirtual toString : ()Ljava/lang/String;
    //   396: areturn
    //   397: aconst_null
    //   398: areturn
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\HexDumpUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */