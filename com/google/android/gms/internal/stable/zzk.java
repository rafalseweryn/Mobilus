package com.google.android.gms.internal.stable;

public final class zzk {
  private static final zzl zzahg;
  
  private static final int zzahh;
  
  static {
    // Byte code:
    //   0: iconst_1
    //   1: istore_0
    //   2: invokestatic zzdw : ()Ljava/lang/Integer;
    //   5: astore_1
    //   6: aload_1
    //   7: ifnull -> 34
    //   10: aload_1
    //   11: invokevirtual intValue : ()I
    //   14: bipush #19
    //   16: if_icmplt -> 34
    //   19: new com/google/android/gms/internal/stable/zzp
    //   22: astore_2
    //   23: aload_2
    //   24: invokespecial <init> : ()V
    //   27: goto -> 149
    //   30: astore_2
    //   31: goto -> 69
    //   34: ldc 'com.google.devtools.build.android.desugar.runtime.twr_disable_mimic'
    //   36: invokestatic getBoolean : (Ljava/lang/String;)Z
    //   39: iconst_1
    //   40: ixor
    //   41: ifeq -> 55
    //   44: new com/google/android/gms/internal/stable/zzo
    //   47: dup
    //   48: invokespecial <init> : ()V
    //   51: astore_2
    //   52: goto -> 149
    //   55: new com/google/android/gms/internal/stable/zzk$zza
    //   58: dup
    //   59: invokespecial <init> : ()V
    //   62: astore_2
    //   63: goto -> 149
    //   66: astore_2
    //   67: aconst_null
    //   68: astore_1
    //   69: getstatic java/lang/System.err : Ljava/io/PrintStream;
    //   72: astore_3
    //   73: ldc com/google/android/gms/internal/stable/zzk$zza
    //   75: invokevirtual getName : ()Ljava/lang/String;
    //   78: astore #4
    //   80: new java/lang/StringBuilder
    //   83: dup
    //   84: aload #4
    //   86: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   89: invokevirtual length : ()I
    //   92: sipush #132
    //   95: iadd
    //   96: invokespecial <init> : (I)V
    //   99: astore #5
    //   101: aload #5
    //   103: ldc 'An error has occured when initializing the try-with-resources desuguring strategy. The default strategy '
    //   105: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: pop
    //   109: aload #5
    //   111: aload #4
    //   113: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: pop
    //   117: aload #5
    //   119: ldc 'will be used. The error is: '
    //   121: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: pop
    //   125: aload_3
    //   126: aload #5
    //   128: invokevirtual toString : ()Ljava/lang/String;
    //   131: invokevirtual println : (Ljava/lang/String;)V
    //   134: aload_2
    //   135: getstatic java/lang/System.err : Ljava/io/PrintStream;
    //   138: invokevirtual printStackTrace : (Ljava/io/PrintStream;)V
    //   141: new com/google/android/gms/internal/stable/zzk$zza
    //   144: dup
    //   145: invokespecial <init> : ()V
    //   148: astore_2
    //   149: aload_2
    //   150: putstatic com/google/android/gms/internal/stable/zzk.zzahg : Lcom/google/android/gms/internal/stable/zzl;
    //   153: aload_1
    //   154: ifnonnull -> 160
    //   157: goto -> 165
    //   160: aload_1
    //   161: invokevirtual intValue : ()I
    //   164: istore_0
    //   165: iload_0
    //   166: putstatic com/google/android/gms/internal/stable/zzk.zzahh : I
    //   169: return
    // Exception table:
    //   from	to	target	type
    //   2	6	66	java/lang/Throwable
    //   10	27	30	java/lang/Throwable
    //   34	52	30	java/lang/Throwable
    //   55	63	30	java/lang/Throwable
  }
  
  public static void zza(Throwable paramThrowable1, Throwable paramThrowable2) {
    zzahg.zza(paramThrowable1, paramThrowable2);
  }
  
  private static Integer zzdw() {
    try {
      return (Integer)Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
    } catch (Exception exception) {
      System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
      exception.printStackTrace(System.err);
      return null;
    } 
  }
  
  static final class zza extends zzl {
    public final void zza(Throwable param1Throwable1, Throwable param1Throwable2) {}
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\internal\stable\zzk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */