package android.support.v4.util;

import android.support.annotation.RestrictTo;
import java.io.PrintWriter;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class TimeUtils {
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static final int HUNDRED_DAY_FIELD_LEN = 19;
  
  private static final int SECONDS_PER_DAY = 86400;
  
  private static final int SECONDS_PER_HOUR = 3600;
  
  private static final int SECONDS_PER_MINUTE = 60;
  
  private static char[] sFormatStr;
  
  private static final Object sFormatSync = new Object();
  
  static {
    sFormatStr = new char[24];
  }
  
  private static int accumField(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3) {
    return (paramInt1 > 99 || (paramBoolean && paramInt3 >= 3)) ? (paramInt2 + 3) : ((paramInt1 > 9 || (paramBoolean && paramInt3 >= 2)) ? (paramInt2 + 2) : ((paramBoolean || paramInt1 > 0) ? (paramInt2 + 1) : 0));
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static void formatDuration(long paramLong1, long paramLong2, PrintWriter paramPrintWriter) {
    if (paramLong1 == 0L) {
      paramPrintWriter.print("--");
      return;
    } 
    formatDuration(paramLong1 - paramLong2, paramPrintWriter, 0);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static void formatDuration(long paramLong, PrintWriter paramPrintWriter) {
    formatDuration(paramLong, paramPrintWriter, 0);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static void formatDuration(long paramLong, PrintWriter paramPrintWriter, int paramInt) {
    synchronized (sFormatSync) {
      paramInt = formatDurationLocked(paramLong, paramInt);
      String str = new String();
      this(sFormatStr, 0, paramInt);
      paramPrintWriter.print(str);
      return;
    } 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static void formatDuration(long paramLong, StringBuilder paramStringBuilder) {
    synchronized (sFormatSync) {
      int i = formatDurationLocked(paramLong, 0);
      paramStringBuilder.append(sFormatStr, 0, i);
      return;
    } 
  }
  
  private static int formatDurationLocked(long paramLong, int paramInt) {
    boolean bool1;
    boolean bool2;
    int n;
    boolean bool3;
    byte b;
    if (sFormatStr.length < paramInt)
      sFormatStr = new char[paramInt]; 
    char[] arrayOfChar = sFormatStr;
    int i = paramLong cmp 0L;
    if (i == 0) {
      while (paramInt - 1 > 0)
        arrayOfChar[0] = (char)' '; 
      arrayOfChar[0] = (char)'0';
      return 1;
    } 
    if (i > 0) {
      j = 43;
    } else {
      j = 45;
      paramLong = -paramLong;
    } 
    int k = (int)(paramLong % 1000L);
    i = (int)Math.floor((paramLong / 1000L));
    if (i > 86400) {
      m = i / 86400;
      i -= 86400 * m;
    } else {
      m = 0;
    } 
    if (i > 3600) {
      bool1 = i / 3600;
      i -= bool1 * 3600;
    } else {
      bool1 = false;
    } 
    if (i > 60) {
      bool2 = i / 60;
      n = i - bool2 * 60;
    } else {
      bool2 = false;
      n = i;
    } 
    if (paramInt != 0) {
      i = accumField(m, 1, false, 0);
      if (i > 0) {
        bool3 = true;
      } else {
        bool3 = false;
      } 
      i += accumField(bool1, 1, bool3, 2);
      if (i > 0) {
        bool3 = true;
      } else {
        bool3 = false;
      } 
      i += accumField(bool2, 1, bool3, 2);
      if (i > 0) {
        bool3 = true;
      } else {
        bool3 = false;
      } 
      int i1 = i + accumField(n, 1, bool3, 2);
      if (i1 > 0) {
        i = 3;
      } else {
        i = 0;
      } 
      i1 += accumField(k, 2, true, i) + 1;
      i = 0;
      while (true) {
        b = i;
        if (i1 < paramInt) {
          arrayOfChar[i] = (char)' ';
          i++;
          i1++;
          continue;
        } 
        break;
      } 
    } else {
      b = 0;
    } 
    arrayOfChar[b] = (char)j;
    int j = b + 1;
    if (paramInt != 0) {
      paramInt = 1;
    } else {
      paramInt = 0;
    } 
    int m = printField(arrayOfChar, m, 'd', j, false, 0);
    if (m != j) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    if (paramInt != 0) {
      i = 2;
    } else {
      i = 0;
    } 
    m = printField(arrayOfChar, bool1, 'h', m, bool3, i);
    if (m != j) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    if (paramInt != 0) {
      i = 2;
    } else {
      i = 0;
    } 
    m = printField(arrayOfChar, bool2, 'm', m, bool3, i);
    if (m != j) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    if (paramInt != 0) {
      i = 2;
    } else {
      i = 0;
    } 
    i = printField(arrayOfChar, n, 's', m, bool3, i);
    if (paramInt != 0 && i != j) {
      paramInt = 3;
    } else {
      paramInt = 0;
    } 
    paramInt = printField(arrayOfChar, k, 'm', i, true, paramInt);
    arrayOfChar[paramInt] = (char)'s';
    return paramInt + 1;
  }
  
  private static int printField(char[] paramArrayOfchar, int paramInt1, char paramChar, int paramInt2, boolean paramBoolean, int paramInt3) {
    // Byte code:
    //   0: iload #4
    //   2: ifne -> 12
    //   5: iload_3
    //   6: istore #6
    //   8: iload_1
    //   9: ifle -> 149
    //   12: iload #4
    //   14: ifeq -> 23
    //   17: iload #5
    //   19: iconst_3
    //   20: if_icmpge -> 29
    //   23: iload_1
    //   24: bipush #99
    //   26: if_icmple -> 61
    //   29: iload_1
    //   30: bipush #100
    //   32: idiv
    //   33: istore #7
    //   35: aload_0
    //   36: iload_3
    //   37: iload #7
    //   39: bipush #48
    //   41: iadd
    //   42: i2c
    //   43: i2c
    //   44: castore
    //   45: iload_3
    //   46: iconst_1
    //   47: iadd
    //   48: istore #6
    //   50: iload_1
    //   51: iload #7
    //   53: bipush #100
    //   55: imul
    //   56: isub
    //   57: istore_1
    //   58: goto -> 64
    //   61: iload_3
    //   62: istore #6
    //   64: iload #4
    //   66: ifeq -> 75
    //   69: iload #5
    //   71: iconst_2
    //   72: if_icmpge -> 94
    //   75: iload_1
    //   76: bipush #9
    //   78: if_icmpgt -> 94
    //   81: iload #6
    //   83: istore #7
    //   85: iload_1
    //   86: istore #5
    //   88: iload_3
    //   89: iload #6
    //   91: if_icmpeq -> 123
    //   94: iload_1
    //   95: bipush #10
    //   97: idiv
    //   98: istore_3
    //   99: aload_0
    //   100: iload #6
    //   102: iload_3
    //   103: bipush #48
    //   105: iadd
    //   106: i2c
    //   107: i2c
    //   108: castore
    //   109: iload #6
    //   111: iconst_1
    //   112: iadd
    //   113: istore #7
    //   115: iload_1
    //   116: iload_3
    //   117: bipush #10
    //   119: imul
    //   120: isub
    //   121: istore #5
    //   123: aload_0
    //   124: iload #7
    //   126: iload #5
    //   128: bipush #48
    //   130: iadd
    //   131: i2c
    //   132: i2c
    //   133: castore
    //   134: iload #7
    //   136: iconst_1
    //   137: iadd
    //   138: istore_1
    //   139: aload_0
    //   140: iload_1
    //   141: iload_2
    //   142: i2c
    //   143: castore
    //   144: iload_1
    //   145: iconst_1
    //   146: iadd
    //   147: istore #6
    //   149: iload #6
    //   151: ireturn
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v\\util\TimeUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */