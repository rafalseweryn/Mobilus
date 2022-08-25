package com.google.android.gms.internal.stable;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class zzi {
  private static final Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
  
  private static HashMap<String, String> zzagq;
  
  private static final Uri zzagv = Uri.parse("content://com.google.android.gsf.gservices/prefix");
  
  private static final Pattern zzagw = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
  
  private static final Pattern zzagx = Pattern.compile("^(0|false|f|off|no|n)$", 2);
  
  private static final AtomicBoolean zzagy = new AtomicBoolean();
  
  private static final HashMap<String, Boolean> zzagz = new HashMap<>();
  
  private static final HashMap<String, Integer> zzaha = new HashMap<>();
  
  private static final HashMap<String, Long> zzahb = new HashMap<>();
  
  private static final HashMap<String, Float> zzahc = new HashMap<>();
  
  private static Object zzahd;
  
  private static boolean zzahe;
  
  private static String[] zzahf = new String[0];
  
  public static int getInt(ContentResolver paramContentResolver, String paramString, int paramInt) {
    Object object = zzb(paramContentResolver);
    Integer integer2 = zza(zzaha, paramString, Integer.valueOf(paramInt));
    if (integer2 != null)
      return integer2.intValue(); 
    String str = zza(paramContentResolver, paramString, (String)null);
    if (str != null)
      try {
        int i = Integer.parseInt(str);
        Integer integer = Integer.valueOf(i);
        paramInt = i;
        zza(object, zzaha, paramString, integer);
        return paramInt;
      } catch (NumberFormatException numberFormatException) {} 
    Integer integer1 = integer2;
    zza(object, zzaha, paramString, integer1);
    return paramInt;
  }
  
  public static long getLong(ContentResolver paramContentResolver, String paramString, long paramLong) {
    Object object = zzb(paramContentResolver);
    Long long_2 = zza(zzahb, paramString, Long.valueOf(paramLong));
    if (long_2 != null)
      return long_2.longValue(); 
    String str = zza(paramContentResolver, paramString, (String)null);
    if (str != null)
      try {
        long l = Long.parseLong(str);
        Long long_ = Long.valueOf(l);
        paramLong = l;
        zza(object, zzahb, paramString, long_);
        return paramLong;
      } catch (NumberFormatException numberFormatException) {} 
    Long long_1 = long_2;
    zza(object, zzahb, paramString, long_1);
    return paramLong;
  }
  
  private static <T> T zza(HashMap<String, T> paramHashMap, String paramString, T paramT) {
    // Byte code:
    //   0: ldc com/google/android/gms/internal/stable/zzi
    //   2: monitorenter
    //   3: aload_0
    //   4: aload_1
    //   5: invokevirtual containsKey : (Ljava/lang/Object;)Z
    //   8: ifeq -> 31
    //   11: aload_0
    //   12: aload_1
    //   13: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   16: astore_0
    //   17: aload_0
    //   18: ifnull -> 24
    //   21: goto -> 26
    //   24: aload_2
    //   25: astore_0
    //   26: ldc com/google/android/gms/internal/stable/zzi
    //   28: monitorexit
    //   29: aload_0
    //   30: areturn
    //   31: ldc com/google/android/gms/internal/stable/zzi
    //   33: monitorexit
    //   34: aconst_null
    //   35: areturn
    //   36: astore_0
    //   37: ldc com/google/android/gms/internal/stable/zzi
    //   39: monitorexit
    //   40: aload_0
    //   41: athrow
    // Exception table:
    //   from	to	target	type
    //   3	17	36	finally
    //   26	29	36	finally
    //   31	34	36	finally
    //   37	40	36	finally
  }
  
  public static String zza(ContentResolver paramContentResolver, String paramString1, String paramString2) {
    // Byte code:
    //   0: ldc com/google/android/gms/internal/stable/zzi
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic zza : (Landroid/content/ContentResolver;)V
    //   7: getstatic com/google/android/gms/internal/stable/zzi.zzahd : Ljava/lang/Object;
    //   10: astore_3
    //   11: getstatic com/google/android/gms/internal/stable/zzi.zzagq : Ljava/util/HashMap;
    //   14: aload_1
    //   15: invokevirtual containsKey : (Ljava/lang/Object;)Z
    //   18: ifeq -> 46
    //   21: getstatic com/google/android/gms/internal/stable/zzi.zzagq : Ljava/util/HashMap;
    //   24: aload_1
    //   25: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   28: checkcast java/lang/String
    //   31: astore_0
    //   32: aload_0
    //   33: ifnull -> 39
    //   36: goto -> 41
    //   39: aload_2
    //   40: astore_0
    //   41: ldc com/google/android/gms/internal/stable/zzi
    //   43: monitorexit
    //   44: aload_0
    //   45: areturn
    //   46: getstatic com/google/android/gms/internal/stable/zzi.zzahf : [Ljava/lang/String;
    //   49: astore #4
    //   51: aload #4
    //   53: arraylength
    //   54: istore #5
    //   56: iconst_0
    //   57: istore #6
    //   59: iload #6
    //   61: iload #5
    //   63: if_icmpge -> 160
    //   66: aload_1
    //   67: aload #4
    //   69: iload #6
    //   71: aaload
    //   72: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   75: ifeq -> 154
    //   78: getstatic com/google/android/gms/internal/stable/zzi.zzahe : Z
    //   81: ifeq -> 93
    //   84: getstatic com/google/android/gms/internal/stable/zzi.zzagq : Ljava/util/HashMap;
    //   87: invokevirtual isEmpty : ()Z
    //   90: ifeq -> 149
    //   93: getstatic com/google/android/gms/internal/stable/zzi.zzahf : [Ljava/lang/String;
    //   96: astore #4
    //   98: getstatic com/google/android/gms/internal/stable/zzi.zzagq : Ljava/util/HashMap;
    //   101: aload_0
    //   102: aload #4
    //   104: invokestatic zza : (Landroid/content/ContentResolver;[Ljava/lang/String;)Ljava/util/Map;
    //   107: invokevirtual putAll : (Ljava/util/Map;)V
    //   110: iconst_1
    //   111: putstatic com/google/android/gms/internal/stable/zzi.zzahe : Z
    //   114: getstatic com/google/android/gms/internal/stable/zzi.zzagq : Ljava/util/HashMap;
    //   117: aload_1
    //   118: invokevirtual containsKey : (Ljava/lang/Object;)Z
    //   121: ifeq -> 149
    //   124: getstatic com/google/android/gms/internal/stable/zzi.zzagq : Ljava/util/HashMap;
    //   127: aload_1
    //   128: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   131: checkcast java/lang/String
    //   134: astore_0
    //   135: aload_0
    //   136: ifnull -> 144
    //   139: aload_0
    //   140: astore_2
    //   141: goto -> 144
    //   144: ldc com/google/android/gms/internal/stable/zzi
    //   146: monitorexit
    //   147: aload_2
    //   148: areturn
    //   149: ldc com/google/android/gms/internal/stable/zzi
    //   151: monitorexit
    //   152: aload_2
    //   153: areturn
    //   154: iinc #6, 1
    //   157: goto -> 59
    //   160: ldc com/google/android/gms/internal/stable/zzi
    //   162: monitorexit
    //   163: aload_0
    //   164: getstatic com/google/android/gms/internal/stable/zzi.CONTENT_URI : Landroid/net/Uri;
    //   167: aconst_null
    //   168: aconst_null
    //   169: iconst_1
    //   170: anewarray java/lang/String
    //   173: dup
    //   174: iconst_0
    //   175: aload_1
    //   176: aastore
    //   177: aconst_null
    //   178: invokevirtual query : (Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   181: astore #7
    //   183: aload #7
    //   185: ifnull -> 263
    //   188: aload #7
    //   190: invokeinterface moveToFirst : ()Z
    //   195: ifne -> 201
    //   198: goto -> 263
    //   201: aload #7
    //   203: iconst_1
    //   204: invokeinterface getString : (I)Ljava/lang/String;
    //   209: astore #4
    //   211: aload #4
    //   213: astore_0
    //   214: aload #4
    //   216: ifnull -> 233
    //   219: aload #4
    //   221: astore_0
    //   222: aload #4
    //   224: aload_2
    //   225: invokevirtual equals : (Ljava/lang/Object;)Z
    //   228: ifeq -> 233
    //   231: aload_2
    //   232: astore_0
    //   233: aload_3
    //   234: aload_1
    //   235: aload_0
    //   236: invokestatic zza : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V
    //   239: aload_0
    //   240: ifnull -> 245
    //   243: aload_0
    //   244: astore_2
    //   245: aload #7
    //   247: ifnull -> 257
    //   250: aload #7
    //   252: invokeinterface close : ()V
    //   257: aload_2
    //   258: areturn
    //   259: astore_0
    //   260: goto -> 283
    //   263: aload_3
    //   264: aload_1
    //   265: aconst_null
    //   266: invokestatic zza : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V
    //   269: aload #7
    //   271: ifnull -> 281
    //   274: aload #7
    //   276: invokeinterface close : ()V
    //   281: aload_2
    //   282: areturn
    //   283: aload #7
    //   285: ifnull -> 295
    //   288: aload #7
    //   290: invokeinterface close : ()V
    //   295: aload_0
    //   296: athrow
    //   297: astore_0
    //   298: ldc com/google/android/gms/internal/stable/zzi
    //   300: monitorexit
    //   301: aload_0
    //   302: athrow
    // Exception table:
    //   from	to	target	type
    //   3	32	297	finally
    //   41	44	297	finally
    //   46	56	297	finally
    //   66	93	297	finally
    //   93	135	297	finally
    //   144	147	297	finally
    //   149	152	297	finally
    //   160	163	297	finally
    //   188	198	259	finally
    //   201	211	259	finally
    //   222	231	259	finally
    //   233	239	259	finally
    //   263	269	259	finally
    //   298	301	297	finally
  }
  
  private static Map<String, String> zza(ContentResolver paramContentResolver, String... paramVarArgs) {
    Cursor cursor = paramContentResolver.query(zzagv, null, null, paramVarArgs, null);
    null = new TreeMap<>();
    if (cursor == null)
      return (Map)null; 
    try {
      while (cursor.moveToNext())
        null.put(cursor.getString(0), cursor.getString(1)); 
      return (Map)null;
    } finally {
      cursor.close();
    } 
  }
  
  private static void zza(ContentResolver paramContentResolver) {
    if (zzagq == null) {
      zzagy.set(false);
      zzagq = new HashMap<>();
      zzahd = new Object();
      zzahe = false;
      paramContentResolver.registerContentObserver(CONTENT_URI, true, new zzj(null));
      return;
    } 
    if (zzagy.getAndSet(false)) {
      zzagq.clear();
      zzagz.clear();
      zzaha.clear();
      zzahb.clear();
      zzahc.clear();
      zzahd = new Object();
      zzahe = false;
    } 
  }
  
  private static void zza(Object paramObject, String paramString1, String paramString2) {
    // Byte code:
    //   0: ldc com/google/android/gms/internal/stable/zzi
    //   2: monitorenter
    //   3: aload_0
    //   4: getstatic com/google/android/gms/internal/stable/zzi.zzahd : Ljava/lang/Object;
    //   7: if_acmpne -> 19
    //   10: getstatic com/google/android/gms/internal/stable/zzi.zzagq : Ljava/util/HashMap;
    //   13: aload_1
    //   14: aload_2
    //   15: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   18: pop
    //   19: ldc com/google/android/gms/internal/stable/zzi
    //   21: monitorexit
    //   22: return
    //   23: astore_0
    //   24: ldc com/google/android/gms/internal/stable/zzi
    //   26: monitorexit
    //   27: aload_0
    //   28: athrow
    // Exception table:
    //   from	to	target	type
    //   3	19	23	finally
    //   19	22	23	finally
    //   24	27	23	finally
  }
  
  private static <T> void zza(Object paramObject, HashMap<String, T> paramHashMap, String paramString, T paramT) {
    // Byte code:
    //   0: ldc com/google/android/gms/internal/stable/zzi
    //   2: monitorenter
    //   3: aload_0
    //   4: getstatic com/google/android/gms/internal/stable/zzi.zzahd : Ljava/lang/Object;
    //   7: if_acmpne -> 25
    //   10: aload_1
    //   11: aload_2
    //   12: aload_3
    //   13: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   16: pop
    //   17: getstatic com/google/android/gms/internal/stable/zzi.zzagq : Ljava/util/HashMap;
    //   20: aload_2
    //   21: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   24: pop
    //   25: ldc com/google/android/gms/internal/stable/zzi
    //   27: monitorexit
    //   28: return
    //   29: astore_0
    //   30: ldc com/google/android/gms/internal/stable/zzi
    //   32: monitorexit
    //   33: aload_0
    //   34: athrow
    // Exception table:
    //   from	to	target	type
    //   3	25	29	finally
    //   25	28	29	finally
    //   30	33	29	finally
  }
  
  public static boolean zza(ContentResolver paramContentResolver, String paramString, boolean paramBoolean) {
    Object object = zzb(paramContentResolver);
    Boolean bool2 = zza(zzagz, paramString, Boolean.valueOf(paramBoolean));
    if (bool2 != null)
      return bool2.booleanValue(); 
    String str = zza(paramContentResolver, paramString, (String)null);
    Boolean bool1 = bool2;
    boolean bool = paramBoolean;
    if (str != null)
      if (str.equals("")) {
        bool1 = bool2;
        bool = paramBoolean;
      } else if (zzagw.matcher(str).matches()) {
        bool1 = Boolean.valueOf(true);
        bool = true;
      } else if (zzagx.matcher(str).matches()) {
        bool1 = Boolean.valueOf(false);
        bool = false;
      } else {
        StringBuilder stringBuilder = new StringBuilder("attempt to read gservices key ");
        stringBuilder.append(paramString);
        stringBuilder.append(" (value \"");
        stringBuilder.append(str);
        stringBuilder.append("\") as boolean");
        Log.w("Gservices", stringBuilder.toString());
        bool = paramBoolean;
        bool1 = bool2;
      }  
    zza(object, zzagz, paramString, bool1);
    return bool;
  }
  
  private static Object zzb(ContentResolver paramContentResolver) {
    // Byte code:
    //   0: ldc com/google/android/gms/internal/stable/zzi
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic zza : (Landroid/content/ContentResolver;)V
    //   7: getstatic com/google/android/gms/internal/stable/zzi.zzahd : Ljava/lang/Object;
    //   10: astore_0
    //   11: ldc com/google/android/gms/internal/stable/zzi
    //   13: monitorexit
    //   14: aload_0
    //   15: areturn
    //   16: astore_0
    //   17: ldc com/google/android/gms/internal/stable/zzi
    //   19: monitorexit
    //   20: aload_0
    //   21: athrow
    // Exception table:
    //   from	to	target	type
    //   3	14	16	finally
    //   17	20	16	finally
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\internal\stable\zzi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */