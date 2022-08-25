package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.reflect.Field;
import javax.annotation.concurrent.GuardedBy;

public final class DynamiteModule {
  public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION;
  
  public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING;
  
  public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION;
  
  public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION_NO_FORCE_STAGING;
  
  public static final VersionPolicy PREFER_LOCAL;
  
  public static final VersionPolicy PREFER_REMOTE;
  
  @GuardedBy("DynamiteModule.class")
  private static Boolean zzabr;
  
  @GuardedBy("DynamiteModule.class")
  private static IDynamiteLoader zzabs;
  
  @GuardedBy("DynamiteModule.class")
  private static IDynamiteLoaderV2 zzabt;
  
  @GuardedBy("DynamiteModule.class")
  private static String zzabu;
  
  private static final ThreadLocal<zza> zzabv = new ThreadLocal<>();
  
  private static final VersionPolicy.IVersions zzabw = new zza();
  
  private final Context zzabx;
  
  static {
    PREFER_REMOTE = new zzb();
    PREFER_LOCAL = new zzc();
    PREFER_HIGHEST_OR_LOCAL_VERSION = new zzd();
    PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zze();
    PREFER_HIGHEST_OR_REMOTE_VERSION = new zzf();
    PREFER_HIGHEST_OR_REMOTE_VERSION_NO_FORCE_STAGING = new zzg();
  }
  
  private DynamiteModule(Context paramContext) {
    this.zzabx = (Context)Preconditions.checkNotNull(paramContext);
  }
  
  public static int getLocalVersion(Context paramContext, String paramString) {
    try {
      String str;
      ClassLoader classLoader = paramContext.getApplicationContext().getClassLoader();
      null = String.valueOf(paramString).length();
      StringBuilder stringBuilder = new StringBuilder();
      this(null + 61);
      stringBuilder.append("com.google.android.gms.dynamite.descriptors.");
      stringBuilder.append(paramString);
      stringBuilder.append(".ModuleDescriptor");
      Class<?> clazz = classLoader.loadClass(stringBuilder.toString());
      Field field1 = clazz.getDeclaredField("MODULE_ID");
      Field field2 = clazz.getDeclaredField("MODULE_VERSION");
      if (!field1.get(null).equals(paramString)) {
        str = String.valueOf(field1.get(null));
        null = String.valueOf(str).length();
        int i = String.valueOf(paramString).length();
        StringBuilder stringBuilder1 = new StringBuilder();
        this(null + 51 + i);
        stringBuilder1.append("Module descriptor id '");
        stringBuilder1.append(str);
        stringBuilder1.append("' didn't match expected id '");
        stringBuilder1.append(paramString);
        stringBuilder1.append("'");
        Log.e("DynamiteModule", stringBuilder1.toString());
        return 0;
      } 
      return str.getInt(null);
    } catch (ClassNotFoundException classNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString).length() + 45);
      stringBuilder.append("Local module descriptor class for ");
      stringBuilder.append(paramString);
      stringBuilder.append(" not found.");
      Log.w("DynamiteModule", stringBuilder.toString());
      return 0;
    } catch (Exception exception) {
      String str = String.valueOf(exception.getMessage());
      if (str.length() != 0) {
        str = "Failed to load module descriptor class: ".concat(str);
      } else {
        str = new String("Failed to load module descriptor class: ");
      } 
      Log.e("DynamiteModule", str);
      return 0;
    } 
  }
  
  public static Uri getQueryUri(String paramString, boolean paramBoolean) {
    String str;
    if (paramBoolean) {
      str = "api_force_staging";
    } else {
      str = "api";
    } 
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 42 + String.valueOf(paramString).length());
    stringBuilder.append("content://com.google.android.gms.chimera/");
    stringBuilder.append(str);
    stringBuilder.append("/");
    stringBuilder.append(paramString);
    return Uri.parse(stringBuilder.toString());
  }
  
  public static int getRemoteVersion(Context paramContext, String paramString) {
    return getRemoteVersion(paramContext, paramString, false);
  }
  
  public static int getRemoteVersion(Context paramContext, String paramString, boolean paramBoolean) {
    // Byte code:
    //   0: ldc com/google/android/gms/dynamite/DynamiteModule
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/dynamite/DynamiteModule.zzabr : Ljava/lang/Boolean;
    //   6: astore_3
    //   7: aload_3
    //   8: astore #4
    //   10: aload_3
    //   11: ifnonnull -> 283
    //   14: aload_0
    //   15: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   18: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   21: ldc com/google/android/gms/dynamite/DynamiteModule$DynamiteLoaderClassLoader
    //   23: invokevirtual getName : ()Ljava/lang/String;
    //   26: invokevirtual loadClass : (Ljava/lang/String;)Ljava/lang/Class;
    //   29: astore_3
    //   30: aload_3
    //   31: ldc 'sClassLoader'
    //   33: invokevirtual getDeclaredField : (Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   36: astore #4
    //   38: aload_3
    //   39: monitorenter
    //   40: aload #4
    //   42: aconst_null
    //   43: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   46: checkcast java/lang/ClassLoader
    //   49: astore #5
    //   51: aload #5
    //   53: ifnull -> 85
    //   56: aload #5
    //   58: invokestatic getSystemClassLoader : ()Ljava/lang/ClassLoader;
    //   61: if_acmpne -> 72
    //   64: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   67: astore #4
    //   69: goto -> 204
    //   72: aload #5
    //   74: invokestatic zza : (Ljava/lang/ClassLoader;)V
    //   77: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   80: astore #4
    //   82: goto -> 204
    //   85: ldc_w 'com.google.android.gms'
    //   88: aload_0
    //   89: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   92: invokevirtual getPackageName : ()Ljava/lang/String;
    //   95: invokevirtual equals : (Ljava/lang/Object;)Z
    //   98: ifeq -> 113
    //   101: aload #4
    //   103: aconst_null
    //   104: invokestatic getSystemClassLoader : ()Ljava/lang/ClassLoader;
    //   107: invokevirtual set : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   110: goto -> 64
    //   113: aload_0
    //   114: aload_1
    //   115: iload_2
    //   116: invokestatic zzb : (Landroid/content/Context;Ljava/lang/String;Z)I
    //   119: istore #6
    //   121: getstatic com/google/android/gms/dynamite/DynamiteModule.zzabu : Ljava/lang/String;
    //   124: ifnull -> 182
    //   127: getstatic com/google/android/gms/dynamite/DynamiteModule.zzabu : Ljava/lang/String;
    //   130: invokevirtual isEmpty : ()Z
    //   133: ifeq -> 139
    //   136: goto -> 182
    //   139: new com/google/android/gms/dynamite/zzh
    //   142: astore #5
    //   144: aload #5
    //   146: getstatic com/google/android/gms/dynamite/DynamiteModule.zzabu : Ljava/lang/String;
    //   149: invokestatic getSystemClassLoader : ()Ljava/lang/ClassLoader;
    //   152: invokespecial <init> : (Ljava/lang/String;Ljava/lang/ClassLoader;)V
    //   155: aload #5
    //   157: invokestatic zza : (Ljava/lang/ClassLoader;)V
    //   160: aload #4
    //   162: aconst_null
    //   163: aload #5
    //   165: invokevirtual set : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   168: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   171: putstatic com/google/android/gms/dynamite/DynamiteModule.zzabr : Ljava/lang/Boolean;
    //   174: aload_3
    //   175: monitorexit
    //   176: ldc com/google/android/gms/dynamite/DynamiteModule
    //   178: monitorexit
    //   179: iload #6
    //   181: ireturn
    //   182: aload_3
    //   183: monitorexit
    //   184: ldc com/google/android/gms/dynamite/DynamiteModule
    //   186: monitorexit
    //   187: iload #6
    //   189: ireturn
    //   190: astore #5
    //   192: aload #4
    //   194: aconst_null
    //   195: invokestatic getSystemClassLoader : ()Ljava/lang/ClassLoader;
    //   198: invokevirtual set : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   201: goto -> 64
    //   204: aload_3
    //   205: monitorexit
    //   206: goto -> 278
    //   209: astore #4
    //   211: aload_3
    //   212: monitorexit
    //   213: aload #4
    //   215: athrow
    //   216: astore #4
    //   218: aload #4
    //   220: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   223: astore #4
    //   225: aload #4
    //   227: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   230: invokevirtual length : ()I
    //   233: istore #6
    //   235: new java/lang/StringBuilder
    //   238: astore_3
    //   239: aload_3
    //   240: iload #6
    //   242: bipush #30
    //   244: iadd
    //   245: invokespecial <init> : (I)V
    //   248: aload_3
    //   249: ldc_w 'Failed to load module via V2: '
    //   252: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   255: pop
    //   256: aload_3
    //   257: aload #4
    //   259: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: pop
    //   263: ldc 'DynamiteModule'
    //   265: aload_3
    //   266: invokevirtual toString : ()Ljava/lang/String;
    //   269: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   272: pop
    //   273: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   276: astore #4
    //   278: aload #4
    //   280: putstatic com/google/android/gms/dynamite/DynamiteModule.zzabr : Ljava/lang/Boolean;
    //   283: ldc com/google/android/gms/dynamite/DynamiteModule
    //   285: monitorexit
    //   286: aload #4
    //   288: invokevirtual booleanValue : ()Z
    //   291: ifeq -> 352
    //   294: aload_0
    //   295: aload_1
    //   296: iload_2
    //   297: invokestatic zzb : (Landroid/content/Context;Ljava/lang/String;Z)I
    //   300: istore #6
    //   302: iload #6
    //   304: ireturn
    //   305: astore_0
    //   306: aload_0
    //   307: invokevirtual getMessage : ()Ljava/lang/String;
    //   310: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   313: astore_0
    //   314: aload_0
    //   315: invokevirtual length : ()I
    //   318: ifeq -> 332
    //   321: ldc_w 'Failed to retrieve remote module version: '
    //   324: aload_0
    //   325: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   328: astore_0
    //   329: goto -> 343
    //   332: new java/lang/String
    //   335: dup
    //   336: ldc_w 'Failed to retrieve remote module version: '
    //   339: invokespecial <init> : (Ljava/lang/String;)V
    //   342: astore_0
    //   343: ldc 'DynamiteModule'
    //   345: aload_0
    //   346: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   349: pop
    //   350: iconst_0
    //   351: ireturn
    //   352: aload_0
    //   353: aload_1
    //   354: iload_2
    //   355: invokestatic zza : (Landroid/content/Context;Ljava/lang/String;Z)I
    //   358: ireturn
    //   359: astore_0
    //   360: ldc com/google/android/gms/dynamite/DynamiteModule
    //   362: monitorexit
    //   363: aload_0
    //   364: athrow
    //   365: astore #4
    //   367: goto -> 77
    // Exception table:
    //   from	to	target	type
    //   3	7	359	finally
    //   14	40	216	java/lang/ClassNotFoundException
    //   14	40	216	java/lang/IllegalAccessException
    //   14	40	216	java/lang/NoSuchFieldException
    //   14	40	359	finally
    //   40	51	209	finally
    //   56	64	209	finally
    //   64	69	209	finally
    //   72	77	365	com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   72	77	209	finally
    //   77	82	209	finally
    //   85	110	209	finally
    //   113	136	190	com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   113	136	209	finally
    //   139	174	190	com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   139	174	209	finally
    //   174	176	209	finally
    //   176	179	359	finally
    //   182	184	209	finally
    //   184	187	359	finally
    //   192	201	209	finally
    //   204	206	209	finally
    //   211	213	209	finally
    //   213	216	216	java/lang/ClassNotFoundException
    //   213	216	216	java/lang/IllegalAccessException
    //   213	216	216	java/lang/NoSuchFieldException
    //   213	216	359	finally
    //   218	278	359	finally
    //   278	283	359	finally
    //   283	286	359	finally
    //   294	302	305	com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   360	363	359	finally
  }
  
  @VisibleForTesting
  public static Boolean getUseV2ForTesting() {
    // Byte code:
    //   0: ldc com/google/android/gms/dynamite/DynamiteModule
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/dynamite/DynamiteModule.zzabr : Ljava/lang/Boolean;
    //   6: astore_0
    //   7: ldc com/google/android/gms/dynamite/DynamiteModule
    //   9: monitorexit
    //   10: aload_0
    //   11: areturn
    //   12: astore_0
    //   13: ldc com/google/android/gms/dynamite/DynamiteModule
    //   15: monitorexit
    //   16: aload_0
    //   17: athrow
    // Exception table:
    //   from	to	target	type
    //   3	7	12	finally
  }
  
  public static DynamiteModule load(Context paramContext, VersionPolicy paramVersionPolicy, String paramString) throws LoadingException {
    zza zza1 = zzabv.get();
    zza zza2 = new zza(null);
    zzabv.set(zza2);
    try {
      LoadingException loadingException2;
      DynamiteModule dynamiteModule;
      LoadingException loadingException1;
      VersionPolicy.SelectionResult selectionResult = paramVersionPolicy.selectModule(paramContext, paramString, zzabw);
      int i = selectionResult.localVersion;
      int j = selectionResult.remoteVersion;
      int k = String.valueOf(paramString).length();
      int m = String.valueOf(paramString).length();
      StringBuilder stringBuilder = new StringBuilder();
      this(k + 68 + m);
      stringBuilder.append("Considering local module ");
      stringBuilder.append(paramString);
      stringBuilder.append(":");
      stringBuilder.append(i);
      stringBuilder.append(" and remote module ");
      stringBuilder.append(paramString);
      stringBuilder.append(":");
      stringBuilder.append(j);
      Log.i("DynamiteModule", stringBuilder.toString());
      if (selectionResult.selection == 0 || (selectionResult.selection == -1 && selectionResult.localVersion == 0) || (selectionResult.selection == 1 && selectionResult.remoteVersion == 0)) {
        loadingException2 = new LoadingException();
        m = selectionResult.localVersion;
        j = selectionResult.remoteVersion;
        StringBuilder stringBuilder1 = new StringBuilder();
        this(91);
        stringBuilder1.append("No acceptable module found. Local version is ");
        stringBuilder1.append(m);
        stringBuilder1.append(" and remote version is ");
        stringBuilder1.append(j);
        stringBuilder1.append(".");
        this(stringBuilder1.toString(), (zza)null);
        throw loadingException2;
      } 
      if (selectionResult.selection == -1) {
        DynamiteModule dynamiteModule1 = zzd((Context)loadingException2, paramString);
        dynamiteModule = dynamiteModule1;
        if (zza2.zzaby != null) {
          dynamiteModule = dynamiteModule1;
        } else {
          return dynamiteModule;
        } 
      } else {
        m = selectionResult.selection;
        if (m == 1) {
          try {
            return zza((Context)dynamiteModule, paramString, selectionResult.remoteVersion);
          } catch (LoadingException loadingException) {}
        } else {
          loadingException1 = new LoadingException();
          m = selectionResult.selection;
          StringBuilder stringBuilder1 = new StringBuilder();
          this(47);
          stringBuilder1.append("VersionPolicy returned invalid code:");
          stringBuilder1.append(m);
          this(stringBuilder1.toString(), (zza)null);
          throw loadingException1;
        } 
      } 
      return (DynamiteModule)loadingException1;
    } finally {
      if (zza2.zzaby != null)
        zza2.zzaby.close(); 
      zzabv.set(zza1);
    } 
  }
  
  public static Cursor queryForDynamiteModule(Context paramContext, String paramString, boolean paramBoolean) {
    return paramContext.getContentResolver().query(getQueryUri(paramString, paramBoolean), null, null, null, null);
  }
  
  @VisibleForTesting
  public static void resetInternalStateForTesting() {
    // Byte code:
    //   0: ldc com/google/android/gms/dynamite/DynamiteModule
    //   2: monitorenter
    //   3: aconst_null
    //   4: putstatic com/google/android/gms/dynamite/DynamiteModule.zzabs : Lcom/google/android/gms/dynamite/IDynamiteLoader;
    //   7: aconst_null
    //   8: putstatic com/google/android/gms/dynamite/DynamiteModule.zzabt : Lcom/google/android/gms/dynamite/IDynamiteLoaderV2;
    //   11: aconst_null
    //   12: putstatic com/google/android/gms/dynamite/DynamiteModule.zzabu : Ljava/lang/String;
    //   15: aconst_null
    //   16: putstatic com/google/android/gms/dynamite/DynamiteModule.zzabr : Ljava/lang/Boolean;
    //   19: ldc com/google/android/gms/dynamite/DynamiteModule$DynamiteLoaderClassLoader
    //   21: monitorenter
    //   22: aconst_null
    //   23: putstatic com/google/android/gms/dynamite/DynamiteModule$DynamiteLoaderClassLoader.sClassLoader : Ljava/lang/ClassLoader;
    //   26: ldc com/google/android/gms/dynamite/DynamiteModule$DynamiteLoaderClassLoader
    //   28: monitorexit
    //   29: ldc com/google/android/gms/dynamite/DynamiteModule
    //   31: monitorexit
    //   32: return
    //   33: astore_0
    //   34: ldc com/google/android/gms/dynamite/DynamiteModule$DynamiteLoaderClassLoader
    //   36: monitorexit
    //   37: aload_0
    //   38: athrow
    //   39: astore_0
    //   40: ldc com/google/android/gms/dynamite/DynamiteModule
    //   42: monitorexit
    //   43: aload_0
    //   44: athrow
    // Exception table:
    //   from	to	target	type
    //   3	22	39	finally
    //   22	29	33	finally
    //   34	37	33	finally
    //   37	39	39	finally
  }
  
  @VisibleForTesting
  public static void setUseV2ForTesting(Boolean paramBoolean) {
    // Byte code:
    //   0: ldc com/google/android/gms/dynamite/DynamiteModule
    //   2: monitorenter
    //   3: aload_0
    //   4: putstatic com/google/android/gms/dynamite/DynamiteModule.zzabr : Ljava/lang/Boolean;
    //   7: ldc com/google/android/gms/dynamite/DynamiteModule
    //   9: monitorexit
    //   10: return
    //   11: astore_0
    //   12: ldc com/google/android/gms/dynamite/DynamiteModule
    //   14: monitorexit
    //   15: aload_0
    //   16: athrow
    // Exception table:
    //   from	to	target	type
    //   3	7	11	finally
  }
  
  private static int zza(Context paramContext, String paramString, boolean paramBoolean) {
    IDynamiteLoader iDynamiteLoader = zzg(paramContext);
    if (iDynamiteLoader == null)
      return 0; 
    try {
      return iDynamiteLoader.getModuleVersion2(ObjectWrapper.wrap(paramContext), paramString, paramBoolean);
    } catch (RemoteException remoteException) {
      String str = String.valueOf(remoteException.getMessage());
      if (str.length() != 0) {
        str = "Failed to retrieve remote module version: ".concat(str);
      } else {
        str = new String("Failed to retrieve remote module version: ");
      } 
      Log.w("DynamiteModule", str);
      return 0;
    } 
  }
  
  private static Context zza(Context paramContext, String paramString, int paramInt, Cursor paramCursor, IDynamiteLoaderV2 paramIDynamiteLoaderV2) {
    try {
      return (Context)ObjectWrapper.unwrap(paramIDynamiteLoaderV2.loadModule2(ObjectWrapper.wrap(paramContext), paramString, paramInt, ObjectWrapper.wrap(paramCursor)));
    } catch (Exception exception) {
      String str = String.valueOf(exception.toString());
      if (str.length() != 0) {
        str = "Failed to load DynamiteLoader: ".concat(str);
      } else {
        str = new String("Failed to load DynamiteLoader: ");
      } 
      Log.e("DynamiteModule", str);
      return null;
    } 
  }
  
  private static DynamiteModule zza(Context paramContext, String paramString, int paramInt) throws LoadingException {
    // Byte code:
    //   0: ldc com/google/android/gms/dynamite/DynamiteModule
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/dynamite/DynamiteModule.zzabr : Ljava/lang/Boolean;
    //   6: astore_3
    //   7: ldc com/google/android/gms/dynamite/DynamiteModule
    //   9: monitorexit
    //   10: aload_3
    //   11: ifnonnull -> 26
    //   14: new com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   17: dup
    //   18: ldc_w 'Failed to determine which loading route to use.'
    //   21: aconst_null
    //   22: invokespecial <init> : (Ljava/lang/String;Lcom/google/android/gms/dynamite/zza;)V
    //   25: athrow
    //   26: aload_3
    //   27: invokevirtual booleanValue : ()Z
    //   30: ifeq -> 40
    //   33: aload_0
    //   34: aload_1
    //   35: iload_2
    //   36: invokestatic zzc : (Landroid/content/Context;Ljava/lang/String;I)Lcom/google/android/gms/dynamite/DynamiteModule;
    //   39: areturn
    //   40: aload_0
    //   41: aload_1
    //   42: iload_2
    //   43: invokestatic zzb : (Landroid/content/Context;Ljava/lang/String;I)Lcom/google/android/gms/dynamite/DynamiteModule;
    //   46: areturn
    //   47: astore_0
    //   48: ldc com/google/android/gms/dynamite/DynamiteModule
    //   50: monitorexit
    //   51: aload_0
    //   52: athrow
    // Exception table:
    //   from	to	target	type
    //   3	10	47	finally
    //   48	51	47	finally
  }
  
  @GuardedBy("DynamiteModule.class")
  private static void zza(ClassLoader paramClassLoader) throws LoadingException {
    try {
      zzabt = IDynamiteLoaderV2.Stub.asInterface(paramClassLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]));
      return;
    } catch (ClassNotFoundException|IllegalAccessException|InstantiationException|java.lang.reflect.InvocationTargetException|NoSuchMethodException classNotFoundException) {
      throw new LoadingException("Failed to instantiate dynamite loader", classNotFoundException, null);
    } 
  }
  
  private static int zzb(Context paramContext, String paramString, boolean paramBoolean) throws LoadingException {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: iload_2
    //   3: invokestatic queryForDynamiteModule : (Landroid/content/Context;Ljava/lang/String;Z)Landroid/database/Cursor;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull -> 137
    //   11: aload_1
    //   12: astore_0
    //   13: aload_1
    //   14: invokeinterface moveToFirst : ()Z
    //   19: ifne -> 25
    //   22: goto -> 137
    //   25: aload_1
    //   26: astore_0
    //   27: aload_1
    //   28: iconst_0
    //   29: invokeinterface getInt : (I)I
    //   34: istore_3
    //   35: aload_1
    //   36: astore #4
    //   38: iload_3
    //   39: ifle -> 118
    //   42: aload_1
    //   43: astore_0
    //   44: ldc com/google/android/gms/dynamite/DynamiteModule
    //   46: monitorenter
    //   47: aload_1
    //   48: iconst_2
    //   49: invokeinterface getString : (I)Ljava/lang/String;
    //   54: putstatic com/google/android/gms/dynamite/DynamiteModule.zzabu : Ljava/lang/String;
    //   57: ldc com/google/android/gms/dynamite/DynamiteModule
    //   59: monitorexit
    //   60: aload_1
    //   61: astore_0
    //   62: getstatic com/google/android/gms/dynamite/DynamiteModule.zzabv : Ljava/lang/ThreadLocal;
    //   65: invokevirtual get : ()Ljava/lang/Object;
    //   68: checkcast com/google/android/gms/dynamite/DynamiteModule$zza
    //   71: astore #5
    //   73: aload_1
    //   74: astore #4
    //   76: aload #5
    //   78: ifnull -> 118
    //   81: aload_1
    //   82: astore #4
    //   84: aload_1
    //   85: astore_0
    //   86: aload #5
    //   88: getfield zzaby : Landroid/database/Cursor;
    //   91: ifnonnull -> 118
    //   94: aload_1
    //   95: astore_0
    //   96: aload #5
    //   98: aload_1
    //   99: putfield zzaby : Landroid/database/Cursor;
    //   102: aconst_null
    //   103: astore #4
    //   105: goto -> 118
    //   108: astore #4
    //   110: ldc com/google/android/gms/dynamite/DynamiteModule
    //   112: monitorexit
    //   113: aload_1
    //   114: astore_0
    //   115: aload #4
    //   117: athrow
    //   118: aload #4
    //   120: ifnull -> 130
    //   123: aload #4
    //   125: invokeinterface close : ()V
    //   130: iload_3
    //   131: ireturn
    //   132: astore #4
    //   134: goto -> 181
    //   137: aload_1
    //   138: astore_0
    //   139: ldc 'DynamiteModule'
    //   141: ldc_w 'Failed to retrieve remote module version.'
    //   144: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   147: pop
    //   148: aload_1
    //   149: astore_0
    //   150: new com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   153: astore #4
    //   155: aload_1
    //   156: astore_0
    //   157: aload #4
    //   159: ldc_w 'Failed to connect to dynamite module ContentResolver.'
    //   162: aconst_null
    //   163: invokespecial <init> : (Ljava/lang/String;Lcom/google/android/gms/dynamite/zza;)V
    //   166: aload_1
    //   167: astore_0
    //   168: aload #4
    //   170: athrow
    //   171: astore_1
    //   172: aconst_null
    //   173: astore_0
    //   174: goto -> 222
    //   177: astore #4
    //   179: aconst_null
    //   180: astore_1
    //   181: aload_1
    //   182: astore_0
    //   183: aload #4
    //   185: instanceof com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   188: ifeq -> 196
    //   191: aload_1
    //   192: astore_0
    //   193: aload #4
    //   195: athrow
    //   196: aload_1
    //   197: astore_0
    //   198: new com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   201: astore #5
    //   203: aload_1
    //   204: astore_0
    //   205: aload #5
    //   207: ldc_w 'V2 version check failed'
    //   210: aload #4
    //   212: aconst_null
    //   213: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;Lcom/google/android/gms/dynamite/zza;)V
    //   216: aload_1
    //   217: astore_0
    //   218: aload #5
    //   220: athrow
    //   221: astore_1
    //   222: aload_0
    //   223: ifnull -> 232
    //   226: aload_0
    //   227: invokeinterface close : ()V
    //   232: aload_1
    //   233: athrow
    // Exception table:
    //   from	to	target	type
    //   0	7	177	java/lang/Exception
    //   0	7	171	finally
    //   13	22	132	java/lang/Exception
    //   13	22	221	finally
    //   27	35	132	java/lang/Exception
    //   27	35	221	finally
    //   44	47	132	java/lang/Exception
    //   44	47	221	finally
    //   47	60	108	finally
    //   62	73	132	java/lang/Exception
    //   62	73	221	finally
    //   86	94	132	java/lang/Exception
    //   86	94	221	finally
    //   96	102	132	java/lang/Exception
    //   96	102	221	finally
    //   110	113	108	finally
    //   115	118	132	java/lang/Exception
    //   115	118	221	finally
    //   139	148	132	java/lang/Exception
    //   139	148	221	finally
    //   150	155	132	java/lang/Exception
    //   150	155	221	finally
    //   157	166	132	java/lang/Exception
    //   157	166	221	finally
    //   168	171	132	java/lang/Exception
    //   168	171	221	finally
    //   183	191	221	finally
    //   193	196	221	finally
    //   198	203	221	finally
    //   205	216	221	finally
    //   218	221	221	finally
  }
  
  private static DynamiteModule zzb(Context paramContext, String paramString, int paramInt) throws LoadingException {
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString).length() + 51);
    stringBuilder.append("Selected remote version of ");
    stringBuilder.append(paramString);
    stringBuilder.append(", version >= ");
    stringBuilder.append(paramInt);
    Log.i("DynamiteModule", stringBuilder.toString());
    IDynamiteLoader iDynamiteLoader = zzg(paramContext);
    if (iDynamiteLoader == null)
      throw new LoadingException("Failed to create IDynamiteLoader.", null); 
    try {
      IObjectWrapper iObjectWrapper = iDynamiteLoader.createModuleContext(ObjectWrapper.wrap(paramContext), paramString, paramInt);
      if (ObjectWrapper.unwrap(iObjectWrapper) == null)
        throw new LoadingException("Failed to load remote module.", null); 
      return new DynamiteModule((Context)ObjectWrapper.unwrap(iObjectWrapper));
    } catch (RemoteException remoteException) {
      throw new LoadingException("Failed to load remote module.", remoteException, null);
    } 
  }
  
  private static DynamiteModule zzc(Context paramContext, String paramString, int paramInt) throws LoadingException {
    // Byte code:
    //   0: new java/lang/StringBuilder
    //   3: dup
    //   4: aload_1
    //   5: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   8: invokevirtual length : ()I
    //   11: bipush #51
    //   13: iadd
    //   14: invokespecial <init> : (I)V
    //   17: astore_3
    //   18: aload_3
    //   19: ldc_w 'Selected remote version of '
    //   22: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: pop
    //   26: aload_3
    //   27: aload_1
    //   28: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: pop
    //   32: aload_3
    //   33: ldc_w ', version >= '
    //   36: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: pop
    //   40: aload_3
    //   41: iload_2
    //   42: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   45: pop
    //   46: ldc 'DynamiteModule'
    //   48: aload_3
    //   49: invokevirtual toString : ()Ljava/lang/String;
    //   52: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   55: pop
    //   56: ldc com/google/android/gms/dynamite/DynamiteModule
    //   58: monitorenter
    //   59: getstatic com/google/android/gms/dynamite/DynamiteModule.zzabt : Lcom/google/android/gms/dynamite/IDynamiteLoaderV2;
    //   62: astore_3
    //   63: ldc com/google/android/gms/dynamite/DynamiteModule
    //   65: monitorexit
    //   66: aload_3
    //   67: ifnonnull -> 82
    //   70: new com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   73: dup
    //   74: ldc_w 'DynamiteLoaderV2 was not cached.'
    //   77: aconst_null
    //   78: invokespecial <init> : (Ljava/lang/String;Lcom/google/android/gms/dynamite/zza;)V
    //   81: athrow
    //   82: getstatic com/google/android/gms/dynamite/DynamiteModule.zzabv : Ljava/lang/ThreadLocal;
    //   85: invokevirtual get : ()Ljava/lang/Object;
    //   88: checkcast com/google/android/gms/dynamite/DynamiteModule$zza
    //   91: astore #4
    //   93: aload #4
    //   95: ifnull -> 150
    //   98: aload #4
    //   100: getfield zzaby : Landroid/database/Cursor;
    //   103: ifnonnull -> 109
    //   106: goto -> 150
    //   109: aload_0
    //   110: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   113: aload_1
    //   114: iload_2
    //   115: aload #4
    //   117: getfield zzaby : Landroid/database/Cursor;
    //   120: aload_3
    //   121: invokestatic zza : (Landroid/content/Context;Ljava/lang/String;ILandroid/database/Cursor;Lcom/google/android/gms/dynamite/IDynamiteLoaderV2;)Landroid/content/Context;
    //   124: astore_0
    //   125: aload_0
    //   126: ifnonnull -> 141
    //   129: new com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   132: dup
    //   133: ldc_w 'Failed to get module context'
    //   136: aconst_null
    //   137: invokespecial <init> : (Ljava/lang/String;Lcom/google/android/gms/dynamite/zza;)V
    //   140: athrow
    //   141: new com/google/android/gms/dynamite/DynamiteModule
    //   144: dup
    //   145: aload_0
    //   146: invokespecial <init> : (Landroid/content/Context;)V
    //   149: areturn
    //   150: new com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   153: dup
    //   154: ldc_w 'No result cursor'
    //   157: aconst_null
    //   158: invokespecial <init> : (Ljava/lang/String;Lcom/google/android/gms/dynamite/zza;)V
    //   161: athrow
    //   162: astore_0
    //   163: ldc com/google/android/gms/dynamite/DynamiteModule
    //   165: monitorexit
    //   166: aload_0
    //   167: athrow
    // Exception table:
    //   from	to	target	type
    //   59	66	162	finally
    //   163	166	162	finally
  }
  
  private static DynamiteModule zzd(Context paramContext, String paramString) {
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {
      paramString = "Selected local version of ".concat(paramString);
    } else {
      paramString = new String("Selected local version of ");
    } 
    Log.i("DynamiteModule", paramString);
    return new DynamiteModule(paramContext.getApplicationContext());
  }
  
  private static IDynamiteLoader zzg(Context paramContext) {
    // Byte code:
    //   0: ldc com/google/android/gms/dynamite/DynamiteModule
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/dynamite/DynamiteModule.zzabs : Lcom/google/android/gms/dynamite/IDynamiteLoader;
    //   6: ifnull -> 18
    //   9: getstatic com/google/android/gms/dynamite/DynamiteModule.zzabs : Lcom/google/android/gms/dynamite/IDynamiteLoader;
    //   12: astore_0
    //   13: ldc com/google/android/gms/dynamite/DynamiteModule
    //   15: monitorexit
    //   16: aload_0
    //   17: areturn
    //   18: invokestatic getInstance : ()Lcom/google/android/gms/common/GoogleApiAvailabilityLight;
    //   21: aload_0
    //   22: invokevirtual isGooglePlayServicesAvailable : (Landroid/content/Context;)I
    //   25: ifeq -> 33
    //   28: ldc com/google/android/gms/dynamite/DynamiteModule
    //   30: monitorexit
    //   31: aconst_null
    //   32: areturn
    //   33: aload_0
    //   34: ldc_w 'com.google.android.gms'
    //   37: iconst_3
    //   38: invokevirtual createPackageContext : (Ljava/lang/String;I)Landroid/content/Context;
    //   41: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   44: ldc_w 'com.google.android.gms.chimera.container.DynamiteLoaderImpl'
    //   47: invokevirtual loadClass : (Ljava/lang/String;)Ljava/lang/Class;
    //   50: invokevirtual newInstance : ()Ljava/lang/Object;
    //   53: checkcast android/os/IBinder
    //   56: invokestatic asInterface : (Landroid/os/IBinder;)Lcom/google/android/gms/dynamite/IDynamiteLoader;
    //   59: astore_0
    //   60: aload_0
    //   61: ifnull -> 118
    //   64: aload_0
    //   65: putstatic com/google/android/gms/dynamite/DynamiteModule.zzabs : Lcom/google/android/gms/dynamite/IDynamiteLoader;
    //   68: ldc com/google/android/gms/dynamite/DynamiteModule
    //   70: monitorexit
    //   71: aload_0
    //   72: areturn
    //   73: astore_0
    //   74: aload_0
    //   75: invokevirtual getMessage : ()Ljava/lang/String;
    //   78: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   81: astore_0
    //   82: aload_0
    //   83: invokevirtual length : ()I
    //   86: ifeq -> 100
    //   89: ldc_w 'Failed to load IDynamiteLoader from GmsCore: '
    //   92: aload_0
    //   93: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   96: astore_0
    //   97: goto -> 111
    //   100: new java/lang/String
    //   103: dup
    //   104: ldc_w 'Failed to load IDynamiteLoader from GmsCore: '
    //   107: invokespecial <init> : (Ljava/lang/String;)V
    //   110: astore_0
    //   111: ldc 'DynamiteModule'
    //   113: aload_0
    //   114: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   117: pop
    //   118: ldc com/google/android/gms/dynamite/DynamiteModule
    //   120: monitorexit
    //   121: aconst_null
    //   122: areturn
    //   123: astore_0
    //   124: ldc com/google/android/gms/dynamite/DynamiteModule
    //   126: monitorexit
    //   127: aload_0
    //   128: athrow
    // Exception table:
    //   from	to	target	type
    //   3	16	123	finally
    //   18	31	123	finally
    //   33	60	73	java/lang/Exception
    //   33	60	123	finally
    //   64	68	73	java/lang/Exception
    //   64	68	123	finally
    //   68	71	123	finally
    //   74	97	123	finally
    //   100	111	123	finally
    //   111	118	123	finally
    //   118	121	123	finally
    //   124	127	123	finally
  }
  
  public final Context getModuleContext() {
    return this.zzabx;
  }
  
  public final IBinder instantiate(String paramString) throws LoadingException {
    try {
      return (IBinder)this.zzabx.getClassLoader().loadClass(paramString).newInstance();
    } catch (ClassNotFoundException|InstantiationException|IllegalAccessException classNotFoundException) {
      paramString = String.valueOf(paramString);
      if (paramString.length() != 0) {
        paramString = "Failed to instantiate module class: ".concat(paramString);
      } else {
        paramString = new String("Failed to instantiate module class: ");
      } 
      throw new LoadingException(paramString, classNotFoundException, null);
    } 
  }
  
  @DynamiteApi
  public static class DynamiteLoaderClassLoader {
    @GuardedBy("DynamiteLoaderClassLoader.class")
    public static ClassLoader sClassLoader;
  }
  
  public static class LoadingException extends Exception {
    private LoadingException(String param1String) {
      super(param1String);
    }
    
    private LoadingException(String param1String, Throwable param1Throwable) {
      super(param1String, param1Throwable);
    }
  }
  
  public static interface VersionPolicy {
    SelectionResult selectModule(Context param1Context, String param1String, IVersions param1IVersions) throws DynamiteModule.LoadingException;
    
    public static interface IVersions {
      int getLocalVersion(Context param2Context, String param2String);
      
      int getRemoteVersion(Context param2Context, String param2String, boolean param2Boolean) throws DynamiteModule.LoadingException;
    }
    
    public static class SelectionResult {
      public int localVersion = 0;
      
      public int remoteVersion = 0;
      
      public int selection = 0;
    }
  }
  
  public static interface IVersions {
    int getLocalVersion(Context param1Context, String param1String);
    
    int getRemoteVersion(Context param1Context, String param1String, boolean param1Boolean) throws DynamiteModule.LoadingException;
  }
  
  public static class SelectionResult {
    public int localVersion = 0;
    
    public int remoteVersion = 0;
    
    public int selection = 0;
  }
  
  private static final class zza {
    public Cursor zzaby;
    
    private zza() {}
  }
  
  private static final class zzb implements VersionPolicy.IVersions {
    private final int zzabz;
    
    private final int zzaca;
    
    public zzb(int param1Int1, int param1Int2) {
      this.zzabz = param1Int1;
      this.zzaca = 0;
    }
    
    public final int getLocalVersion(Context param1Context, String param1String) {
      return this.zzabz;
    }
    
    public final int getRemoteVersion(Context param1Context, String param1String, boolean param1Boolean) {
      return 0;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\dynamite\DynamiteModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */