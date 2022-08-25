package com.google.android.gms.common.util;

import android.content.Context;
import android.os.DropBoxManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.concurrent.GuardedBy;

public final class CrashUtils {
  private static final String[] zzzc = new String[] { "android.", "com.android.", "dalvik.", "java.", "javax." };
  
  private static DropBoxManager zzzd;
  
  private static boolean zzze = false;
  
  private static boolean zzzf = false;
  
  private static boolean zzzg = false;
  
  private static int zzzh = -1;
  
  @GuardedBy("CrashUtils.class")
  private static int zzzi;
  
  @GuardedBy("CrashUtils.class")
  private static int zzzj;
  
  public static boolean addDynamiteErrorToDropBox(Context paramContext, Throwable paramThrowable) {
    return addErrorToDropBoxInternal(paramContext, paramThrowable, 536870912);
  }
  
  @Deprecated
  public static boolean addErrorToDropBox(Context paramContext, Throwable paramThrowable) {
    return addDynamiteErrorToDropBox(paramContext, paramThrowable);
  }
  
  public static boolean addErrorToDropBoxInternal(Context paramContext, String paramString1, String paramString2, int paramInt) {
    return zza(paramContext, paramString1, paramString2, paramInt, null);
  }
  
  public static boolean addErrorToDropBoxInternal(Context paramContext, Throwable paramThrowable, int paramInt) {
    try {
      Preconditions.checkNotNull(paramContext);
      Preconditions.checkNotNull(paramThrowable);
      if (!isPackageSide())
        return false; 
      Throwable throwable = paramThrowable;
      if (!zzdb()) {
        paramThrowable = zza(paramThrowable);
        throwable = paramThrowable;
        if (paramThrowable == null)
          return false; 
      } 
      return zza(paramContext, Log.getStackTraceString(throwable), ProcessUtils.getMyProcessName(), paramInt, throwable);
    } catch (Exception exception) {
      boolean bool;
      try {
        bool = zzdb();
      } catch (Exception exception1) {
        Log.e("CrashUtils", "Error determining which process we're running in!", exception1);
        bool = false;
      } 
      if (bool)
        throw exception; 
      Log.e("CrashUtils", "Error adding exception to DropBox!", exception);
      return false;
    } 
  }
  
  private static boolean isPackageSide() {
    return zzze ? zzzf : false;
  }
  
  public static boolean isSystemClassPrefixInternal(String paramString) {
    if (TextUtils.isEmpty(paramString))
      return false; 
    String[] arrayOfString = zzzc;
    int i = arrayOfString.length;
    for (byte b = 0; b < i; b++) {
      if (paramString.startsWith(arrayOfString[b]))
        return true; 
    } 
    return false;
  }
  
  @VisibleForTesting
  public static void setTestVariables(DropBoxManager paramDropBoxManager, boolean paramBoolean1, boolean paramBoolean2, int paramInt) {
    // Byte code:
    //   0: ldc com/google/android/gms/common/util/CrashUtils
    //   2: monitorenter
    //   3: iconst_1
    //   4: putstatic com/google/android/gms/common/util/CrashUtils.zzze : Z
    //   7: aload_0
    //   8: putstatic com/google/android/gms/common/util/CrashUtils.zzzd : Landroid/os/DropBoxManager;
    //   11: iload_1
    //   12: putstatic com/google/android/gms/common/util/CrashUtils.zzzg : Z
    //   15: iload_2
    //   16: putstatic com/google/android/gms/common/util/CrashUtils.zzzf : Z
    //   19: iload_3
    //   20: putstatic com/google/android/gms/common/util/CrashUtils.zzzh : I
    //   23: iconst_0
    //   24: putstatic com/google/android/gms/common/util/CrashUtils.zzzi : I
    //   27: iconst_0
    //   28: putstatic com/google/android/gms/common/util/CrashUtils.zzzj : I
    //   31: ldc com/google/android/gms/common/util/CrashUtils
    //   33: monitorexit
    //   34: return
    //   35: astore_0
    //   36: ldc com/google/android/gms/common/util/CrashUtils
    //   38: monitorexit
    //   39: aload_0
    //   40: athrow
    // Exception table:
    //   from	to	target	type
    //   3	31	35	finally
  }
  
  @VisibleForTesting
  private static String zza(Context paramContext, String paramString1, String paramString2, int paramInt) {
    // Byte code:
    //   0: ldc com/google/android/gms/common/util/CrashUtils
    //   2: monitorenter
    //   3: new java/lang/StringBuilder
    //   6: astore #4
    //   8: aload #4
    //   10: sipush #1024
    //   13: invokespecial <init> : (I)V
    //   16: aload #4
    //   18: ldc 'Process: '
    //   20: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: pop
    //   24: aload #4
    //   26: aload_2
    //   27: invokestatic nullToEmpty : (Ljava/lang/String;)Ljava/lang/String;
    //   30: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: pop
    //   34: aload #4
    //   36: ldc '\\n'
    //   38: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: pop
    //   42: aload #4
    //   44: ldc 'Package: com.google.android.gms'
    //   46: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: pop
    //   50: ldc 12451009
    //   52: istore #5
    //   54: ldc '12.4.51 (020308-{{cl}})'
    //   56: astore #6
    //   58: invokestatic zzdb : ()Z
    //   61: istore #7
    //   63: aload #6
    //   65: astore_2
    //   66: iload #5
    //   68: istore #8
    //   70: iload #7
    //   72: ifeq -> 137
    //   75: aload_0
    //   76: invokestatic packageManager : (Landroid/content/Context;)Lcom/google/android/gms/common/wrappers/PackageManagerWrapper;
    //   79: aload_0
    //   80: invokevirtual getPackageName : ()Ljava/lang/String;
    //   83: iconst_0
    //   84: invokevirtual getPackageInfo : (Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   87: astore #9
    //   89: aload #9
    //   91: getfield versionCode : I
    //   94: istore #8
    //   96: aload #6
    //   98: astore_2
    //   99: aload #9
    //   101: getfield versionName : Ljava/lang/String;
    //   104: ifnull -> 113
    //   107: aload #9
    //   109: getfield versionName : Ljava/lang/String;
    //   112: astore_2
    //   113: goto -> 137
    //   116: astore_2
    //   117: goto -> 125
    //   120: astore_2
    //   121: iload #5
    //   123: istore #8
    //   125: ldc 'CrashUtils'
    //   127: ldc 'Error while trying to get the package information! Using static version.'
    //   129: aload_2
    //   130: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   133: pop
    //   134: aload #6
    //   136: astore_2
    //   137: aload #4
    //   139: ldc ' v'
    //   141: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: pop
    //   145: aload #4
    //   147: iload #8
    //   149: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   152: pop
    //   153: aload_2
    //   154: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   157: ifne -> 243
    //   160: aload_2
    //   161: astore #6
    //   163: aload_2
    //   164: ldc '('
    //   166: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   169: ifeq -> 219
    //   172: aload_2
    //   173: astore #6
    //   175: aload_2
    //   176: ldc ')'
    //   178: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   181: ifne -> 219
    //   184: aload_2
    //   185: astore #6
    //   187: aload_2
    //   188: ldc '-'
    //   190: invokevirtual endsWith : (Ljava/lang/String;)Z
    //   193: ifeq -> 207
    //   196: aload_2
    //   197: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   200: ldc '111111111'
    //   202: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   205: astore #6
    //   207: aload #6
    //   209: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   212: ldc ')'
    //   214: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   217: astore #6
    //   219: aload #4
    //   221: ldc ' ('
    //   223: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: pop
    //   227: aload #4
    //   229: aload #6
    //   231: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: pop
    //   235: aload #4
    //   237: ldc ')'
    //   239: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   242: pop
    //   243: aload #4
    //   245: ldc '\\n'
    //   247: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   250: pop
    //   251: aload #4
    //   253: ldc 'Build: '
    //   255: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   258: pop
    //   259: aload #4
    //   261: getstatic android/os/Build.FINGERPRINT : Ljava/lang/String;
    //   264: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   267: pop
    //   268: aload #4
    //   270: ldc '\\n'
    //   272: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: pop
    //   276: invokestatic isDebuggerConnected : ()Z
    //   279: ifeq -> 290
    //   282: aload #4
    //   284: ldc 'Debugger: Connected\\n'
    //   286: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   289: pop
    //   290: iload_3
    //   291: ifeq -> 317
    //   294: aload #4
    //   296: ldc 'DD-EDD: '
    //   298: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   301: pop
    //   302: aload #4
    //   304: iload_3
    //   305: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   308: pop
    //   309: aload #4
    //   311: ldc '\\n'
    //   313: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   316: pop
    //   317: aload #4
    //   319: ldc '\\n'
    //   321: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   324: pop
    //   325: aload_1
    //   326: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   329: ifne -> 339
    //   332: aload #4
    //   334: aload_1
    //   335: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   338: pop
    //   339: invokestatic zzdb : ()Z
    //   342: ifeq -> 372
    //   345: getstatic com/google/android/gms/common/util/CrashUtils.zzzh : I
    //   348: iflt -> 358
    //   351: getstatic com/google/android/gms/common/util/CrashUtils.zzzh : I
    //   354: istore_3
    //   355: goto -> 374
    //   358: aload_0
    //   359: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
    //   362: ldc 'logcat_for_system_app_crash'
    //   364: iconst_0
    //   365: invokestatic getInt : (Landroid/content/ContentResolver;Ljava/lang/String;I)I
    //   368: istore_3
    //   369: goto -> 374
    //   372: iconst_0
    //   373: istore_3
    //   374: iload_3
    //   375: ifle -> 626
    //   378: aload #4
    //   380: ldc '\\n'
    //   382: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   385: pop
    //   386: aconst_null
    //   387: astore #6
    //   389: aconst_null
    //   390: astore_2
    //   391: aload_2
    //   392: astore_0
    //   393: new java/lang/ProcessBuilder
    //   396: astore_1
    //   397: aload_2
    //   398: astore_0
    //   399: aload_1
    //   400: bipush #13
    //   402: anewarray java/lang/String
    //   405: dup
    //   406: iconst_0
    //   407: ldc '/system/bin/logcat'
    //   409: aastore
    //   410: dup
    //   411: iconst_1
    //   412: ldc '-v'
    //   414: aastore
    //   415: dup
    //   416: iconst_2
    //   417: ldc 'time'
    //   419: aastore
    //   420: dup
    //   421: iconst_3
    //   422: ldc '-b'
    //   424: aastore
    //   425: dup
    //   426: iconst_4
    //   427: ldc_w 'events'
    //   430: aastore
    //   431: dup
    //   432: iconst_5
    //   433: ldc '-b'
    //   435: aastore
    //   436: dup
    //   437: bipush #6
    //   439: ldc_w 'system'
    //   442: aastore
    //   443: dup
    //   444: bipush #7
    //   446: ldc '-b'
    //   448: aastore
    //   449: dup
    //   450: bipush #8
    //   452: ldc_w 'main'
    //   455: aastore
    //   456: dup
    //   457: bipush #9
    //   459: ldc '-b'
    //   461: aastore
    //   462: dup
    //   463: bipush #10
    //   465: ldc_w 'crash'
    //   468: aastore
    //   469: dup
    //   470: bipush #11
    //   472: ldc_w '-t'
    //   475: aastore
    //   476: dup
    //   477: bipush #12
    //   479: iload_3
    //   480: invokestatic valueOf : (I)Ljava/lang/String;
    //   483: aastore
    //   484: invokespecial <init> : ([Ljava/lang/String;)V
    //   487: aload_2
    //   488: astore_0
    //   489: aload_1
    //   490: iconst_1
    //   491: invokevirtual redirectErrorStream : (Z)Ljava/lang/ProcessBuilder;
    //   494: invokevirtual start : ()Ljava/lang/Process;
    //   497: astore #9
    //   499: aload_2
    //   500: astore_0
    //   501: aload #9
    //   503: invokevirtual getOutputStream : ()Ljava/io/OutputStream;
    //   506: invokevirtual close : ()V
    //   509: aload_2
    //   510: astore_0
    //   511: aload #9
    //   513: invokevirtual getErrorStream : ()Ljava/io/InputStream;
    //   516: invokevirtual close : ()V
    //   519: aload_2
    //   520: astore_0
    //   521: new java/io/InputStreamReader
    //   524: astore_1
    //   525: aload_2
    //   526: astore_0
    //   527: aload_1
    //   528: aload #9
    //   530: invokevirtual getInputStream : ()Ljava/io/InputStream;
    //   533: invokespecial <init> : (Ljava/io/InputStream;)V
    //   536: sipush #8192
    //   539: newarray char
    //   541: astore_0
    //   542: aload_1
    //   543: aload_0
    //   544: invokevirtual read : ([C)I
    //   547: istore_3
    //   548: iload_3
    //   549: ifle -> 564
    //   552: aload #4
    //   554: aload_0
    //   555: iconst_0
    //   556: iload_3
    //   557: invokevirtual append : ([CII)Ljava/lang/StringBuilder;
    //   560: pop
    //   561: goto -> 542
    //   564: aload_1
    //   565: invokevirtual close : ()V
    //   568: goto -> 626
    //   571: astore_0
    //   572: aload_1
    //   573: astore_2
    //   574: aload_0
    //   575: astore_1
    //   576: aload_2
    //   577: astore_0
    //   578: goto -> 616
    //   581: astore_2
    //   582: goto -> 593
    //   585: astore_1
    //   586: goto -> 616
    //   589: astore_2
    //   590: aload #6
    //   592: astore_1
    //   593: aload_1
    //   594: astore_0
    //   595: ldc 'CrashUtils'
    //   597: ldc_w 'Error running logcat'
    //   600: aload_2
    //   601: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   604: pop
    //   605: aload_1
    //   606: ifnull -> 626
    //   609: aload_1
    //   610: invokevirtual close : ()V
    //   613: goto -> 626
    //   616: aload_0
    //   617: ifnull -> 624
    //   620: aload_0
    //   621: invokevirtual close : ()V
    //   624: aload_1
    //   625: athrow
    //   626: aload #4
    //   628: invokevirtual toString : ()Ljava/lang/String;
    //   631: astore_0
    //   632: ldc com/google/android/gms/common/util/CrashUtils
    //   634: monitorexit
    //   635: aload_0
    //   636: areturn
    //   637: astore_0
    //   638: ldc com/google/android/gms/common/util/CrashUtils
    //   640: monitorexit
    //   641: aload_0
    //   642: athrow
    //   643: astore_0
    //   644: goto -> 509
    //   647: astore_0
    //   648: goto -> 519
    //   651: astore_0
    //   652: goto -> 626
    //   655: astore_0
    //   656: goto -> 624
    // Exception table:
    //   from	to	target	type
    //   3	50	637	finally
    //   58	63	637	finally
    //   75	96	120	java/lang/Exception
    //   75	96	637	finally
    //   99	113	116	java/lang/Exception
    //   99	113	637	finally
    //   125	134	637	finally
    //   137	160	637	finally
    //   163	172	637	finally
    //   175	184	637	finally
    //   187	207	637	finally
    //   207	219	637	finally
    //   219	243	637	finally
    //   243	290	637	finally
    //   294	317	637	finally
    //   317	339	637	finally
    //   339	355	637	finally
    //   358	369	637	finally
    //   378	386	637	finally
    //   393	397	589	java/io/IOException
    //   393	397	585	finally
    //   399	487	589	java/io/IOException
    //   399	487	585	finally
    //   489	499	589	java/io/IOException
    //   489	499	585	finally
    //   501	509	643	java/io/IOException
    //   501	509	585	finally
    //   511	519	647	java/io/IOException
    //   511	519	585	finally
    //   521	525	589	java/io/IOException
    //   521	525	585	finally
    //   527	536	589	java/io/IOException
    //   527	536	585	finally
    //   536	542	581	java/io/IOException
    //   536	542	571	finally
    //   542	548	581	java/io/IOException
    //   542	548	571	finally
    //   552	561	581	java/io/IOException
    //   552	561	571	finally
    //   564	568	651	java/io/IOException
    //   564	568	637	finally
    //   595	605	585	finally
    //   609	613	651	java/io/IOException
    //   609	613	637	finally
    //   620	624	655	java/io/IOException
    //   620	624	637	finally
    //   624	626	637	finally
    //   626	632	637	finally
  }
  
  @VisibleForTesting
  private static Throwable zza(Throwable paramThrowable) {
    // Byte code:
    //   0: ldc com/google/android/gms/common/util/CrashUtils
    //   2: monitorenter
    //   3: new java/util/LinkedList
    //   6: astore_1
    //   7: aload_1
    //   8: invokespecial <init> : ()V
    //   11: aload_0
    //   12: ifnull -> 28
    //   15: aload_1
    //   16: aload_0
    //   17: invokevirtual push : (Ljava/lang/Object;)V
    //   20: aload_0
    //   21: invokevirtual getCause : ()Ljava/lang/Throwable;
    //   24: astore_0
    //   25: goto -> 11
    //   28: aconst_null
    //   29: astore_0
    //   30: iconst_0
    //   31: istore_2
    //   32: aload_1
    //   33: invokevirtual isEmpty : ()Z
    //   36: ifne -> 266
    //   39: aload_1
    //   40: invokevirtual pop : ()Ljava/lang/Object;
    //   43: checkcast java/lang/Throwable
    //   46: astore_3
    //   47: aload_3
    //   48: invokevirtual getStackTrace : ()[Ljava/lang/StackTraceElement;
    //   51: astore #4
    //   53: new java/util/ArrayList
    //   56: astore #5
    //   58: aload #5
    //   60: invokespecial <init> : ()V
    //   63: new java/lang/StackTraceElement
    //   66: astore #6
    //   68: aload #6
    //   70: aload_3
    //   71: invokevirtual getClass : ()Ljava/lang/Class;
    //   74: invokevirtual getName : ()Ljava/lang/String;
    //   77: ldc_w '<filtered>'
    //   80: ldc_w '<filtered>'
    //   83: iconst_1
    //   84: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
    //   87: aload #5
    //   89: aload #6
    //   91: invokevirtual add : (Ljava/lang/Object;)Z
    //   94: pop
    //   95: aload #4
    //   97: arraylength
    //   98: istore #7
    //   100: iconst_0
    //   101: istore #8
    //   103: iload #8
    //   105: iload #7
    //   107: if_icmpge -> 214
    //   110: aload #4
    //   112: iload #8
    //   114: aaload
    //   115: astore_3
    //   116: aload_3
    //   117: invokevirtual getClassName : ()Ljava/lang/String;
    //   120: astore #9
    //   122: aload_3
    //   123: invokevirtual getFileName : ()Ljava/lang/String;
    //   126: astore #6
    //   128: aload #6
    //   130: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   133: ifne -> 153
    //   136: aload #6
    //   138: ldc_w ':com.google.android.gms'
    //   141: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   144: ifeq -> 153
    //   147: iconst_1
    //   148: istore #10
    //   150: goto -> 156
    //   153: iconst_0
    //   154: istore #10
    //   156: iload_2
    //   157: iload #10
    //   159: ior
    //   160: istore_2
    //   161: aload_3
    //   162: astore #6
    //   164: iload #10
    //   166: ifne -> 200
    //   169: aload_3
    //   170: astore #6
    //   172: aload #9
    //   174: invokestatic isSystemClassPrefixInternal : (Ljava/lang/String;)Z
    //   177: ifne -> 200
    //   180: new java/lang/StackTraceElement
    //   183: astore #6
    //   185: aload #6
    //   187: ldc_w '<filtered>'
    //   190: ldc_w '<filtered>'
    //   193: ldc_w '<filtered>'
    //   196: iconst_1
    //   197: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
    //   200: aload #5
    //   202: aload #6
    //   204: invokevirtual add : (Ljava/lang/Object;)Z
    //   207: pop
    //   208: iinc #8, 1
    //   211: goto -> 103
    //   214: aload_0
    //   215: ifnonnull -> 232
    //   218: new java/lang/Throwable
    //   221: astore_0
    //   222: aload_0
    //   223: ldc_w '<filtered>'
    //   226: invokespecial <init> : (Ljava/lang/String;)V
    //   229: goto -> 247
    //   232: new java/lang/Throwable
    //   235: dup
    //   236: ldc_w '<filtered>'
    //   239: aload_0
    //   240: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   243: astore_0
    //   244: goto -> 229
    //   247: aload_0
    //   248: aload #5
    //   250: iconst_0
    //   251: anewarray java/lang/StackTraceElement
    //   254: invokevirtual toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
    //   257: checkcast [Ljava/lang/StackTraceElement;
    //   260: invokevirtual setStackTrace : ([Ljava/lang/StackTraceElement;)V
    //   263: goto -> 32
    //   266: iload_2
    //   267: ifne -> 275
    //   270: ldc com/google/android/gms/common/util/CrashUtils
    //   272: monitorexit
    //   273: aconst_null
    //   274: areturn
    //   275: ldc com/google/android/gms/common/util/CrashUtils
    //   277: monitorexit
    //   278: aload_0
    //   279: areturn
    //   280: astore_0
    //   281: ldc com/google/android/gms/common/util/CrashUtils
    //   283: monitorexit
    //   284: aload_0
    //   285: athrow
    // Exception table:
    //   from	to	target	type
    //   3	11	280	finally
    //   15	25	280	finally
    //   32	100	280	finally
    //   116	147	280	finally
    //   172	200	280	finally
    //   200	208	280	finally
    //   218	229	280	finally
    //   232	244	280	finally
    //   247	263	280	finally
  }
  
  private static boolean zza(Context paramContext, String paramString1, String paramString2, int paramInt, Throwable paramThrowable) {
    // Byte code:
    //   0: ldc com/google/android/gms/common/util/CrashUtils
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic checkNotNull : (Ljava/lang/Object;)Ljava/lang/Object;
    //   7: pop
    //   8: invokestatic isPackageSide : ()Z
    //   11: ifeq -> 155
    //   14: aload_1
    //   15: invokestatic isEmptyOrWhitespace : (Ljava/lang/String;)Z
    //   18: ifeq -> 24
    //   21: goto -> 155
    //   24: aload_1
    //   25: invokevirtual hashCode : ()I
    //   28: istore #5
    //   30: aload #4
    //   32: ifnonnull -> 43
    //   35: getstatic com/google/android/gms/common/util/CrashUtils.zzzj : I
    //   38: istore #6
    //   40: goto -> 50
    //   43: aload #4
    //   45: invokevirtual hashCode : ()I
    //   48: istore #6
    //   50: getstatic com/google/android/gms/common/util/CrashUtils.zzzi : I
    //   53: iload #5
    //   55: if_icmpne -> 75
    //   58: getstatic com/google/android/gms/common/util/CrashUtils.zzzj : I
    //   61: istore #7
    //   63: iload #7
    //   65: iload #6
    //   67: if_icmpne -> 75
    //   70: ldc com/google/android/gms/common/util/CrashUtils
    //   72: monitorexit
    //   73: iconst_0
    //   74: ireturn
    //   75: iload #5
    //   77: putstatic com/google/android/gms/common/util/CrashUtils.zzzi : I
    //   80: iload #6
    //   82: putstatic com/google/android/gms/common/util/CrashUtils.zzzj : I
    //   85: getstatic com/google/android/gms/common/util/CrashUtils.zzzd : Landroid/os/DropBoxManager;
    //   88: ifnull -> 99
    //   91: getstatic com/google/android/gms/common/util/CrashUtils.zzzd : Landroid/os/DropBoxManager;
    //   94: astore #4
    //   96: goto -> 111
    //   99: aload_0
    //   100: ldc_w 'dropbox'
    //   103: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   106: checkcast android/os/DropBoxManager
    //   109: astore #4
    //   111: aload #4
    //   113: ifnull -> 150
    //   116: aload #4
    //   118: ldc_w 'system_app_crash'
    //   121: invokevirtual isTagEnabled : (Ljava/lang/String;)Z
    //   124: ifne -> 130
    //   127: goto -> 150
    //   130: aload #4
    //   132: ldc_w 'system_app_crash'
    //   135: aload_0
    //   136: aload_1
    //   137: aload_2
    //   138: iload_3
    //   139: invokestatic zza : (Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
    //   142: invokevirtual addText : (Ljava/lang/String;Ljava/lang/String;)V
    //   145: ldc com/google/android/gms/common/util/CrashUtils
    //   147: monitorexit
    //   148: iconst_1
    //   149: ireturn
    //   150: ldc com/google/android/gms/common/util/CrashUtils
    //   152: monitorexit
    //   153: iconst_0
    //   154: ireturn
    //   155: ldc com/google/android/gms/common/util/CrashUtils
    //   157: monitorexit
    //   158: iconst_0
    //   159: ireturn
    //   160: astore_0
    //   161: ldc com/google/android/gms/common/util/CrashUtils
    //   163: monitorexit
    //   164: aload_0
    //   165: athrow
    // Exception table:
    //   from	to	target	type
    //   3	21	160	finally
    //   24	30	160	finally
    //   35	40	160	finally
    //   43	50	160	finally
    //   50	63	160	finally
    //   75	96	160	finally
    //   99	111	160	finally
    //   116	127	160	finally
    //   130	145	160	finally
  }
  
  private static boolean zzdb() {
    return zzze ? zzzg : false;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ErrorDialogData {
    public static final int AVG_CRASH_FREQ = 2;
    
    public static final int BINDER_CRASH = 268435456;
    
    public static final int DYNAMITE_CRASH = 536870912;
    
    public static final int FORCED_SHUSHED_BY_WRAPPER = 4;
    
    public static final int NONE = 0;
    
    public static final int POPUP_FREQ = 1;
    
    public static final int SUPPRESSED = 1073741824;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\CrashUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */