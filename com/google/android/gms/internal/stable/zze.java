package com.google.android.gms.internal.stable;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import java.util.HashMap;

public final class zze {
  public static class zza implements BaseColumns {
    private static HashMap<Uri, zzh> zzagq = new HashMap<>();
    
    private static zzh zza(ContentResolver param1ContentResolver, Uri param1Uri) {
      // Byte code:
      //   0: getstatic com/google/android/gms/internal/stable/zze$zza.zzagq : Ljava/util/HashMap;
      //   3: aload_1
      //   4: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
      //   7: checkcast com/google/android/gms/internal/stable/zzh
      //   10: astore_2
      //   11: aload_2
      //   12: ifnonnull -> 49
      //   15: new com/google/android/gms/internal/stable/zzh
      //   18: dup
      //   19: invokespecial <init> : ()V
      //   22: astore_2
      //   23: getstatic com/google/android/gms/internal/stable/zze$zza.zzagq : Ljava/util/HashMap;
      //   26: aload_1
      //   27: aload_2
      //   28: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   31: pop
      //   32: aload_0
      //   33: aload_1
      //   34: iconst_1
      //   35: new com/google/android/gms/internal/stable/zzf
      //   38: dup
      //   39: aconst_null
      //   40: aload_2
      //   41: invokespecial <init> : (Landroid/os/Handler;Lcom/google/android/gms/internal/stable/zzh;)V
      //   44: invokevirtual registerContentObserver : (Landroid/net/Uri;ZLandroid/database/ContentObserver;)V
      //   47: aload_2
      //   48: areturn
      //   49: aload_2
      //   50: getfield zzagu : Ljava/util/concurrent/atomic/AtomicBoolean;
      //   53: iconst_0
      //   54: invokevirtual getAndSet : (Z)Z
      //   57: ifeq -> 91
      //   60: aload_2
      //   61: monitorenter
      //   62: aload_2
      //   63: getfield zzags : Ljava/util/HashMap;
      //   66: invokevirtual clear : ()V
      //   69: new java/lang/Object
      //   72: astore_0
      //   73: aload_0
      //   74: invokespecial <init> : ()V
      //   77: aload_2
      //   78: aload_0
      //   79: putfield zzagt : Ljava/lang/Object;
      //   82: aload_2
      //   83: monitorexit
      //   84: aload_2
      //   85: areturn
      //   86: astore_0
      //   87: aload_2
      //   88: monitorexit
      //   89: aload_0
      //   90: athrow
      //   91: aload_2
      //   92: areturn
      // Exception table:
      //   from	to	target	type
      //   62	84	86	finally
      //   87	89	86	finally
    }
    
    protected static String zza(ContentResolver param1ContentResolver, Uri param1Uri, String param1String) {
      // Byte code:
      //   0: ldc com/google/android/gms/internal/stable/zze$zza
      //   2: monitorenter
      //   3: aload_0
      //   4: aload_1
      //   5: invokestatic zza : (Landroid/content/ContentResolver;Landroid/net/Uri;)Lcom/google/android/gms/internal/stable/zzh;
      //   8: astore_3
      //   9: ldc com/google/android/gms/internal/stable/zze$zza
      //   11: monitorexit
      //   12: aload_3
      //   13: monitorenter
      //   14: aload_3
      //   15: getfield zzagt : Ljava/lang/Object;
      //   18: astore #4
      //   20: aload_3
      //   21: getfield zzags : Ljava/util/HashMap;
      //   24: aload_2
      //   25: invokevirtual containsKey : (Ljava/lang/Object;)Z
      //   28: ifeq -> 47
      //   31: aload_3
      //   32: getfield zzags : Ljava/util/HashMap;
      //   35: aload_2
      //   36: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
      //   39: checkcast java/lang/String
      //   42: astore_0
      //   43: aload_3
      //   44: monitorexit
      //   45: aload_0
      //   46: areturn
      //   47: aload_3
      //   48: monitorexit
      //   49: aconst_null
      //   50: astore #5
      //   52: aconst_null
      //   53: astore #6
      //   55: aload_0
      //   56: aload_1
      //   57: iconst_1
      //   58: anewarray java/lang/String
      //   61: dup
      //   62: iconst_0
      //   63: ldc 'value'
      //   65: aastore
      //   66: ldc 'name=?'
      //   68: iconst_1
      //   69: anewarray java/lang/String
      //   72: dup
      //   73: iconst_0
      //   74: aload_2
      //   75: aastore
      //   76: aconst_null
      //   77: invokevirtual query : (Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   80: astore #7
      //   82: aload #7
      //   84: ifnull -> 166
      //   87: aload #7
      //   89: invokeinterface moveToFirst : ()Z
      //   94: ifne -> 100
      //   97: goto -> 166
      //   100: aload #7
      //   102: iconst_0
      //   103: invokeinterface getString : (I)Ljava/lang/String;
      //   108: astore #8
      //   110: aload_3
      //   111: aload #4
      //   113: aload_2
      //   114: aload #8
      //   116: invokestatic zza : (Lcom/google/android/gms/internal/stable/zzh;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V
      //   119: aload #8
      //   121: astore_1
      //   122: aload #7
      //   124: ifnull -> 281
      //   127: aload #7
      //   129: invokeinterface close : ()V
      //   134: aload #8
      //   136: areturn
      //   137: astore_0
      //   138: goto -> 153
      //   141: astore_0
      //   142: aload #7
      //   144: astore #6
      //   146: goto -> 283
      //   149: astore_0
      //   150: aconst_null
      //   151: astore #8
      //   153: aload #7
      //   155: astore #6
      //   157: aload_0
      //   158: astore #7
      //   160: aload #6
      //   162: astore_0
      //   163: goto -> 200
      //   166: aload_3
      //   167: aload #4
      //   169: aload_2
      //   170: aconst_null
      //   171: invokestatic zza : (Lcom/google/android/gms/internal/stable/zzh;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V
      //   174: aload #7
      //   176: ifnull -> 186
      //   179: aload #7
      //   181: invokeinterface close : ()V
      //   186: aconst_null
      //   187: areturn
      //   188: astore_0
      //   189: goto -> 283
      //   192: astore #7
      //   194: aconst_null
      //   195: astore #8
      //   197: aload #5
      //   199: astore_0
      //   200: aload_0
      //   201: astore #6
      //   203: new java/lang/StringBuilder
      //   206: astore #5
      //   208: aload_0
      //   209: astore #6
      //   211: aload #5
      //   213: ldc 'Can't get key '
      //   215: invokespecial <init> : (Ljava/lang/String;)V
      //   218: aload_0
      //   219: astore #6
      //   221: aload #5
      //   223: aload_2
      //   224: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   227: pop
      //   228: aload_0
      //   229: astore #6
      //   231: aload #5
      //   233: ldc ' from '
      //   235: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   238: pop
      //   239: aload_0
      //   240: astore #6
      //   242: aload #5
      //   244: aload_1
      //   245: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   248: pop
      //   249: aload_0
      //   250: astore #6
      //   252: ldc 'GoogleSettings'
      //   254: aload #5
      //   256: invokevirtual toString : ()Ljava/lang/String;
      //   259: aload #7
      //   261: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   264: pop
      //   265: aload #8
      //   267: astore_1
      //   268: aload_0
      //   269: ifnull -> 281
      //   272: aload_0
      //   273: invokeinterface close : ()V
      //   278: aload #8
      //   280: astore_1
      //   281: aload_1
      //   282: areturn
      //   283: aload #6
      //   285: ifnull -> 295
      //   288: aload #6
      //   290: invokeinterface close : ()V
      //   295: aload_0
      //   296: athrow
      //   297: astore_0
      //   298: aload_3
      //   299: monitorexit
      //   300: aload_0
      //   301: athrow
      //   302: astore_0
      //   303: ldc com/google/android/gms/internal/stable/zze$zza
      //   305: monitorexit
      //   306: aload_0
      //   307: athrow
      // Exception table:
      //   from	to	target	type
      //   3	12	302	finally
      //   14	45	297	finally
      //   47	49	297	finally
      //   55	82	192	android/database/SQLException
      //   55	82	188	finally
      //   87	97	149	android/database/SQLException
      //   87	97	141	finally
      //   100	110	149	android/database/SQLException
      //   100	110	141	finally
      //   110	119	137	android/database/SQLException
      //   110	119	141	finally
      //   166	174	149	android/database/SQLException
      //   166	174	141	finally
      //   203	208	188	finally
      //   211	218	188	finally
      //   221	228	188	finally
      //   231	239	188	finally
      //   242	249	188	finally
      //   252	265	188	finally
      //   298	300	297	finally
      //   303	306	302	finally
    }
    
    private static void zza(zzh param1zzh, Object param1Object, String param1String1, String param1String2) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_1
      //   3: aload_0
      //   4: getfield zzagt : Ljava/lang/Object;
      //   7: if_acmpne -> 20
      //   10: aload_0
      //   11: getfield zzags : Ljava/util/HashMap;
      //   14: aload_2
      //   15: aload_3
      //   16: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   19: pop
      //   20: aload_0
      //   21: monitorexit
      //   22: return
      //   23: astore_1
      //   24: aload_0
      //   25: monitorexit
      //   26: aload_1
      //   27: athrow
      // Exception table:
      //   from	to	target	type
      //   2	20	23	finally
      //   20	22	23	finally
      //   24	26	23	finally
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\internal\stable\zze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */