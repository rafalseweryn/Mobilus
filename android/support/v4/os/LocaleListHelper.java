package android.support.v4.os;

import android.os.Build;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.Size;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
final class LocaleListHelper {
  private static final Locale EN_LATN;
  
  private static final Locale LOCALE_AR_XB;
  
  private static final Locale LOCALE_EN_XA;
  
  private static final int NUM_PSEUDO_LOCALES = 2;
  
  private static final String STRING_AR_XB = "ar-XB";
  
  private static final String STRING_EN_XA = "en-XA";
  
  @GuardedBy("sLock")
  private static LocaleListHelper sDefaultAdjustedLocaleList;
  
  @GuardedBy("sLock")
  private static LocaleListHelper sDefaultLocaleList;
  
  private static final Locale[] sEmptyList = new Locale[0];
  
  private static final LocaleListHelper sEmptyLocaleList = new LocaleListHelper(new Locale[0]);
  
  @GuardedBy("sLock")
  private static Locale sLastDefaultLocale;
  
  @GuardedBy("sLock")
  private static LocaleListHelper sLastExplicitlySetLocaleList;
  
  private static final Object sLock;
  
  private final Locale[] mList;
  
  @NonNull
  private final String mStringRepresentation;
  
  static {
    LOCALE_EN_XA = new Locale("en", "XA");
    LOCALE_AR_XB = new Locale("ar", "XB");
    EN_LATN = LocaleHelper.forLanguageTag("en-Latn");
    sLock = new Object();
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  LocaleListHelper(@NonNull Locale paramLocale, LocaleListHelper paramLocaleListHelper) {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial <init> : ()V
    //   4: aload_1
    //   5: ifnonnull -> 18
    //   8: new java/lang/NullPointerException
    //   11: dup
    //   12: ldc 'topLocale is null'
    //   14: invokespecial <init> : (Ljava/lang/String;)V
    //   17: athrow
    //   18: iconst_0
    //   19: istore_3
    //   20: aload_2
    //   21: ifnonnull -> 30
    //   24: iconst_0
    //   25: istore #4
    //   27: goto -> 37
    //   30: aload_2
    //   31: getfield mList : [Ljava/util/Locale;
    //   34: arraylength
    //   35: istore #4
    //   37: iconst_0
    //   38: istore #5
    //   40: iload #5
    //   42: iload #4
    //   44: if_icmpge -> 70
    //   47: aload_1
    //   48: aload_2
    //   49: getfield mList : [Ljava/util/Locale;
    //   52: iload #5
    //   54: aaload
    //   55: invokevirtual equals : (Ljava/lang/Object;)Z
    //   58: ifeq -> 64
    //   61: goto -> 73
    //   64: iinc #5, 1
    //   67: goto -> 40
    //   70: iconst_m1
    //   71: istore #5
    //   73: iload #5
    //   75: iconst_m1
    //   76: if_icmpne -> 85
    //   79: iconst_1
    //   80: istore #6
    //   82: goto -> 88
    //   85: iconst_0
    //   86: istore #6
    //   88: iload #6
    //   90: iload #4
    //   92: iadd
    //   93: istore #7
    //   95: iload #7
    //   97: anewarray java/util/Locale
    //   100: astore #8
    //   102: aload #8
    //   104: iconst_0
    //   105: aload_1
    //   106: invokevirtual clone : ()Ljava/lang/Object;
    //   109: checkcast java/util/Locale
    //   112: aastore
    //   113: iload #5
    //   115: iconst_m1
    //   116: if_icmpne -> 160
    //   119: iconst_0
    //   120: istore #5
    //   122: iload #5
    //   124: iload #4
    //   126: if_icmpge -> 235
    //   129: iload #5
    //   131: iconst_1
    //   132: iadd
    //   133: istore #6
    //   135: aload #8
    //   137: iload #6
    //   139: aload_2
    //   140: getfield mList : [Ljava/util/Locale;
    //   143: iload #5
    //   145: aaload
    //   146: invokevirtual clone : ()Ljava/lang/Object;
    //   149: checkcast java/util/Locale
    //   152: aastore
    //   153: iload #6
    //   155: istore #5
    //   157: goto -> 122
    //   160: iconst_0
    //   161: istore #6
    //   163: iload #6
    //   165: iload #5
    //   167: if_icmpge -> 201
    //   170: iload #6
    //   172: iconst_1
    //   173: iadd
    //   174: istore #9
    //   176: aload #8
    //   178: iload #9
    //   180: aload_2
    //   181: getfield mList : [Ljava/util/Locale;
    //   184: iload #6
    //   186: aaload
    //   187: invokevirtual clone : ()Ljava/lang/Object;
    //   190: checkcast java/util/Locale
    //   193: aastore
    //   194: iload #9
    //   196: istore #6
    //   198: goto -> 163
    //   201: iinc #5, 1
    //   204: iload #5
    //   206: iload #4
    //   208: if_icmpge -> 235
    //   211: aload #8
    //   213: iload #5
    //   215: aload_2
    //   216: getfield mList : [Ljava/util/Locale;
    //   219: iload #5
    //   221: aaload
    //   222: invokevirtual clone : ()Ljava/lang/Object;
    //   225: checkcast java/util/Locale
    //   228: aastore
    //   229: iinc #5, 1
    //   232: goto -> 204
    //   235: new java/lang/StringBuilder
    //   238: dup
    //   239: invokespecial <init> : ()V
    //   242: astore_1
    //   243: iload_3
    //   244: istore #5
    //   246: iload #5
    //   248: iload #7
    //   250: if_icmpge -> 288
    //   253: aload_1
    //   254: aload #8
    //   256: iload #5
    //   258: aaload
    //   259: invokestatic toLanguageTag : (Ljava/util/Locale;)Ljava/lang/String;
    //   262: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   265: pop
    //   266: iload #5
    //   268: iload #7
    //   270: iconst_1
    //   271: isub
    //   272: if_icmpge -> 282
    //   275: aload_1
    //   276: bipush #44
    //   278: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   281: pop
    //   282: iinc #5, 1
    //   285: goto -> 246
    //   288: aload_0
    //   289: aload #8
    //   291: putfield mList : [Ljava/util/Locale;
    //   294: aload_0
    //   295: aload_1
    //   296: invokevirtual toString : ()Ljava/lang/String;
    //   299: putfield mStringRepresentation : Ljava/lang/String;
    //   302: return
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  LocaleListHelper(@NonNull Locale... paramVarArgs) {
    if (paramVarArgs.length == 0) {
      this.mList = sEmptyList;
      this.mStringRepresentation = "";
    } else {
      Locale[] arrayOfLocale = new Locale[paramVarArgs.length];
      HashSet<Locale> hashSet = new HashSet();
      StringBuilder stringBuilder = new StringBuilder();
      for (byte b = 0; b < paramVarArgs.length; b++) {
        StringBuilder stringBuilder1;
        Locale locale = paramVarArgs[b];
        if (locale == null) {
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("list[");
          stringBuilder1.append(b);
          stringBuilder1.append("] is null");
          throw new NullPointerException(stringBuilder1.toString());
        } 
        if (hashSet.contains(locale)) {
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("list[");
          stringBuilder1.append(b);
          stringBuilder1.append("] is a repetition");
          throw new IllegalArgumentException(stringBuilder1.toString());
        } 
        locale = (Locale)locale.clone();
        arrayOfLocale[b] = locale;
        stringBuilder.append(LocaleHelper.toLanguageTag(locale));
        if (b < stringBuilder1.length - 1)
          stringBuilder.append(','); 
        hashSet.add(locale);
      } 
      this.mList = arrayOfLocale;
      this.mStringRepresentation = stringBuilder.toString();
    } 
  }
  
  private Locale computeFirstMatch(Collection<String> paramCollection, boolean paramBoolean) {
    Locale locale;
    int i = computeFirstMatchIndex(paramCollection, paramBoolean);
    if (i == -1) {
      paramCollection = null;
    } else {
      locale = this.mList[i];
    } 
    return locale;
  }
  
  private int computeFirstMatchIndex(Collection<String> paramCollection, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mList : [Ljava/util/Locale;
    //   4: arraylength
    //   5: iconst_1
    //   6: if_icmpne -> 11
    //   9: iconst_0
    //   10: ireturn
    //   11: aload_0
    //   12: getfield mList : [Ljava/util/Locale;
    //   15: arraylength
    //   16: ifne -> 21
    //   19: iconst_m1
    //   20: ireturn
    //   21: iload_2
    //   22: ifeq -> 48
    //   25: aload_0
    //   26: getstatic android/support/v4/os/LocaleListHelper.EN_LATN : Ljava/util/Locale;
    //   29: invokespecial findFirstMatchIndex : (Ljava/util/Locale;)I
    //   32: istore_3
    //   33: iload_3
    //   34: ifne -> 39
    //   37: iconst_0
    //   38: ireturn
    //   39: iload_3
    //   40: ldc 2147483647
    //   42: if_icmpge -> 48
    //   45: goto -> 51
    //   48: ldc 2147483647
    //   50: istore_3
    //   51: aload_1
    //   52: invokeinterface iterator : ()Ljava/util/Iterator;
    //   57: astore_1
    //   58: aload_1
    //   59: invokeinterface hasNext : ()Z
    //   64: ifeq -> 104
    //   67: aload_0
    //   68: aload_1
    //   69: invokeinterface next : ()Ljava/lang/Object;
    //   74: checkcast java/lang/String
    //   77: invokestatic forLanguageTag : (Ljava/lang/String;)Ljava/util/Locale;
    //   80: invokespecial findFirstMatchIndex : (Ljava/util/Locale;)I
    //   83: istore #4
    //   85: iload #4
    //   87: ifne -> 92
    //   90: iconst_0
    //   91: ireturn
    //   92: iload #4
    //   94: iload_3
    //   95: if_icmpge -> 58
    //   98: iload #4
    //   100: istore_3
    //   101: goto -> 58
    //   104: iload_3
    //   105: ldc 2147483647
    //   107: if_icmpne -> 112
    //   110: iconst_0
    //   111: ireturn
    //   112: iload_3
    //   113: ireturn
  }
  
  private int findFirstMatchIndex(Locale paramLocale) {
    for (byte b = 0; b < this.mList.length; b++) {
      if (matchScore(paramLocale, this.mList[b]) > 0)
        return b; 
    } 
    return Integer.MAX_VALUE;
  }
  
  @NonNull
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  static LocaleListHelper forLanguageTags(@Nullable String paramString) {
    if (paramString == null || paramString.isEmpty())
      return getEmptyLocaleList(); 
    String[] arrayOfString = paramString.split(",", -1);
    Locale[] arrayOfLocale = new Locale[arrayOfString.length];
    for (byte b = 0; b < arrayOfLocale.length; b++)
      arrayOfLocale[b] = LocaleHelper.forLanguageTag(arrayOfString[b]); 
    return new LocaleListHelper(arrayOfLocale);
  }
  
  @NonNull
  @Size(min = 1L)
  static LocaleListHelper getAdjustedDefault() {
    getDefault();
    synchronized (sLock) {
      return sDefaultAdjustedLocaleList;
    } 
  }
  
  @NonNull
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  @Size(min = 1L)
  static LocaleListHelper getDefault() {
    Locale locale = Locale.getDefault();
    synchronized (sLock) {
      if (!locale.equals(sLastDefaultLocale)) {
        sLastDefaultLocale = locale;
        if (sDefaultLocaleList != null && locale.equals(sDefaultLocaleList.get(0)))
          return sDefaultLocaleList; 
        LocaleListHelper localeListHelper = new LocaleListHelper();
        this(locale, sLastExplicitlySetLocaleList);
        sDefaultLocaleList = localeListHelper;
        sDefaultAdjustedLocaleList = sDefaultLocaleList;
      } 
      return sDefaultLocaleList;
    } 
  }
  
  @NonNull
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  static LocaleListHelper getEmptyLocaleList() {
    return sEmptyLocaleList;
  }
  
  private static String getLikelyScript(Locale paramLocale) {
    if (Build.VERSION.SDK_INT >= 21) {
      String str = paramLocale.getScript();
      return !str.isEmpty() ? str : "";
    } 
    return "";
  }
  
  private static boolean isPseudoLocale(String paramString) {
    return ("en-XA".equals(paramString) || "ar-XB".equals(paramString));
  }
  
  private static boolean isPseudoLocale(Locale paramLocale) {
    return (LOCALE_EN_XA.equals(paramLocale) || LOCALE_AR_XB.equals(paramLocale));
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  static boolean isPseudoLocalesOnly(@Nullable String[] paramArrayOfString) {
    if (paramArrayOfString == null)
      return true; 
    if (paramArrayOfString.length > 3)
      return false; 
    int i = paramArrayOfString.length;
    for (byte b = 0; b < i; b++) {
      String str = paramArrayOfString[b];
      if (!str.isEmpty() && !isPseudoLocale(str))
        return false; 
    } 
    return true;
  }
  
  @IntRange(from = 0L, to = 1L)
  private static int matchScore(Locale paramLocale1, Locale paramLocale2) {
    boolean bool = paramLocale1.equals(paramLocale2);
    boolean bool1 = true;
    if (bool)
      return 1; 
    if (!paramLocale1.getLanguage().equals(paramLocale2.getLanguage()))
      return 0; 
    if (isPseudoLocale(paramLocale1) || isPseudoLocale(paramLocale2))
      return 0; 
    String str = getLikelyScript(paramLocale1);
    if (str.isEmpty()) {
      String str1 = paramLocale1.getCountry();
      boolean bool2 = bool1;
      if (!str1.isEmpty())
        if (str1.equals(paramLocale2.getCountry())) {
          bool2 = bool1;
        } else {
          bool2 = false;
        }  
      return bool2;
    } 
    return str.equals(getLikelyScript(paramLocale2));
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  static void setDefault(@NonNull @Size(min = 1L) LocaleListHelper paramLocaleListHelper) {
    setDefault(paramLocaleListHelper, 0);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  static void setDefault(@NonNull @Size(min = 1L) LocaleListHelper paramLocaleListHelper, int paramInt) {
    if (paramLocaleListHelper == null)
      throw new NullPointerException("locales is null"); 
    if (paramLocaleListHelper.isEmpty())
      throw new IllegalArgumentException("locales is empty"); 
    synchronized (sLock) {
      sLastDefaultLocale = paramLocaleListHelper.get(paramInt);
      Locale.setDefault(sLastDefaultLocale);
      sLastExplicitlySetLocaleList = paramLocaleListHelper;
      sDefaultLocaleList = paramLocaleListHelper;
      if (paramInt == 0) {
        sDefaultAdjustedLocaleList = sDefaultLocaleList;
      } else {
        paramLocaleListHelper = new LocaleListHelper();
        this(sLastDefaultLocale, sDefaultLocaleList);
        sDefaultAdjustedLocaleList = paramLocaleListHelper;
      } 
      return;
    } 
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof LocaleListHelper))
      return false; 
    paramObject = ((LocaleListHelper)paramObject).mList;
    if (this.mList.length != paramObject.length)
      return false; 
    for (byte b = 0; b < this.mList.length; b++) {
      if (!this.mList[b].equals(paramObject[b]))
        return false; 
    } 
    return true;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  Locale get(int paramInt) {
    Locale locale;
    if (paramInt >= 0 && paramInt < this.mList.length) {
      locale = this.mList[paramInt];
    } else {
      locale = null;
    } 
    return locale;
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  Locale getFirstMatch(String[] paramArrayOfString) {
    return computeFirstMatch(Arrays.asList(paramArrayOfString), false);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  int getFirstMatchIndex(String[] paramArrayOfString) {
    return computeFirstMatchIndex(Arrays.asList(paramArrayOfString), false);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  int getFirstMatchIndexWithEnglishSupported(Collection<String> paramCollection) {
    return computeFirstMatchIndex(paramCollection, true);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  int getFirstMatchIndexWithEnglishSupported(String[] paramArrayOfString) {
    return getFirstMatchIndexWithEnglishSupported(Arrays.asList(paramArrayOfString));
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  Locale getFirstMatchWithEnglishSupported(String[] paramArrayOfString) {
    return computeFirstMatch(Arrays.asList(paramArrayOfString), true);
  }
  
  public int hashCode() {
    int i = 1;
    for (byte b = 0; b < this.mList.length; b++)
      i = i * 31 + this.mList[b].hashCode(); 
    return i;
  }
  
  @IntRange(from = -1L)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  int indexOf(Locale paramLocale) {
    for (byte b = 0; b < this.mList.length; b++) {
      if (this.mList[b].equals(paramLocale))
        return b; 
    } 
    return -1;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  boolean isEmpty() {
    boolean bool;
    if (this.mList.length == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @IntRange(from = 0L)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  int size() {
    return this.mList.length;
  }
  
  @NonNull
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  String toLanguageTags() {
    return this.mStringRepresentation;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[");
    for (byte b = 0; b < this.mList.length; b++) {
      stringBuilder.append(this.mList[b]);
      if (b < this.mList.length - 1)
        stringBuilder.append(','); 
    } 
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\os\LocaleListHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */