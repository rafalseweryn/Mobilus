package com.google.android.gms.common.stats;

import android.support.v4.util.SimpleArrayMap;

public class PassiveTimedConnectionMatcher {
  private final long zzym = 60000L;
  
  private final int zzyn = 10;
  
  private final SimpleArrayMap<String, Long> zzyo = new SimpleArrayMap(10);
  
  public PassiveTimedConnectionMatcher() {}
  
  public PassiveTimedConnectionMatcher(int paramInt, long paramLong) {}
  
  public Long get(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzyo : Landroid/support/v4/util/SimpleArrayMap;
    //   6: aload_1
    //   7: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   10: checkcast java/lang/Long
    //   13: astore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: areturn
    //   18: astore_1
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	18	finally
    //   19	21	18	finally
  }
  
  public Long put(String paramString) {
    // Byte code:
    //   0: invokestatic elapsedRealtime : ()J
    //   3: lstore_2
    //   4: aload_0
    //   5: getfield zzym : J
    //   8: lstore #4
    //   10: aload_0
    //   11: monitorenter
    //   12: aload_0
    //   13: getfield zzyo : Landroid/support/v4/util/SimpleArrayMap;
    //   16: invokevirtual size : ()I
    //   19: aload_0
    //   20: getfield zzyn : I
    //   23: if_icmplt -> 153
    //   26: aload_0
    //   27: getfield zzyo : Landroid/support/v4/util/SimpleArrayMap;
    //   30: invokevirtual size : ()I
    //   33: iconst_1
    //   34: isub
    //   35: istore #6
    //   37: iload #6
    //   39: iflt -> 81
    //   42: lload_2
    //   43: aload_0
    //   44: getfield zzyo : Landroid/support/v4/util/SimpleArrayMap;
    //   47: iload #6
    //   49: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   52: checkcast java/lang/Long
    //   55: invokevirtual longValue : ()J
    //   58: lsub
    //   59: lload #4
    //   61: lcmp
    //   62: ifle -> 75
    //   65: aload_0
    //   66: getfield zzyo : Landroid/support/v4/util/SimpleArrayMap;
    //   69: iload #6
    //   71: invokevirtual removeAt : (I)Ljava/lang/Object;
    //   74: pop
    //   75: iinc #6, -1
    //   78: goto -> 37
    //   81: lload #4
    //   83: ldc2_w 2
    //   86: ldiv
    //   87: lstore #4
    //   89: aload_0
    //   90: getfield zzyn : I
    //   93: istore #6
    //   95: new java/lang/StringBuilder
    //   98: astore #7
    //   100: aload #7
    //   102: bipush #94
    //   104: invokespecial <init> : (I)V
    //   107: aload #7
    //   109: ldc 'The max capacity '
    //   111: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: pop
    //   115: aload #7
    //   117: iload #6
    //   119: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   122: pop
    //   123: aload #7
    //   125: ldc ' is not enough. Current durationThreshold is: '
    //   127: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: pop
    //   131: aload #7
    //   133: lload #4
    //   135: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   138: pop
    //   139: ldc 'ConnectionTracker'
    //   141: aload #7
    //   143: invokevirtual toString : ()Ljava/lang/String;
    //   146: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   149: pop
    //   150: goto -> 12
    //   153: aload_0
    //   154: getfield zzyo : Landroid/support/v4/util/SimpleArrayMap;
    //   157: aload_1
    //   158: lload_2
    //   159: invokestatic valueOf : (J)Ljava/lang/Long;
    //   162: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   165: checkcast java/lang/Long
    //   168: astore_1
    //   169: aload_0
    //   170: monitorexit
    //   171: aload_1
    //   172: areturn
    //   173: astore_1
    //   174: aload_0
    //   175: monitorexit
    //   176: aload_1
    //   177: athrow
    // Exception table:
    //   from	to	target	type
    //   12	37	173	finally
    //   42	75	173	finally
    //   81	150	173	finally
    //   153	171	173	finally
    //   174	176	173	finally
  }
  
  public boolean remove(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzyo : Landroid/support/v4/util/SimpleArrayMap;
    //   6: aload_1
    //   7: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   10: ifnull -> 18
    //   13: iconst_1
    //   14: istore_2
    //   15: goto -> 20
    //   18: iconst_0
    //   19: istore_2
    //   20: aload_0
    //   21: monitorexit
    //   22: iload_2
    //   23: ireturn
    //   24: astore_1
    //   25: aload_0
    //   26: monitorexit
    //   27: aload_1
    //   28: athrow
    // Exception table:
    //   from	to	target	type
    //   2	13	24	finally
    //   20	22	24	finally
    //   25	27	24	finally
  }
  
  public boolean removeByPrefix(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iconst_0
    //   3: istore_2
    //   4: iconst_0
    //   5: istore_3
    //   6: iload_2
    //   7: aload_0
    //   8: invokevirtual size : ()I
    //   11: if_icmpge -> 69
    //   14: aload_0
    //   15: getfield zzyo : Landroid/support/v4/util/SimpleArrayMap;
    //   18: iload_2
    //   19: invokevirtual keyAt : (I)Ljava/lang/Object;
    //   22: checkcast java/lang/String
    //   25: astore #4
    //   27: iload_3
    //   28: istore #5
    //   30: aload #4
    //   32: ifnull -> 60
    //   35: iload_3
    //   36: istore #5
    //   38: aload #4
    //   40: aload_1
    //   41: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   44: ifeq -> 60
    //   47: aload_0
    //   48: getfield zzyo : Landroid/support/v4/util/SimpleArrayMap;
    //   51: aload #4
    //   53: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   56: pop
    //   57: iconst_1
    //   58: istore #5
    //   60: iinc #2, 1
    //   63: iload #5
    //   65: istore_3
    //   66: goto -> 6
    //   69: aload_0
    //   70: monitorexit
    //   71: iload_3
    //   72: ireturn
    //   73: astore_1
    //   74: aload_0
    //   75: monitorexit
    //   76: aload_1
    //   77: athrow
    // Exception table:
    //   from	to	target	type
    //   6	27	73	finally
    //   38	57	73	finally
    //   69	71	73	finally
    //   74	76	73	finally
  }
  
  public int size() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzyo : Landroid/support/v4/util/SimpleArrayMap;
    //   6: invokevirtual size : ()I
    //   9: istore_1
    //   10: aload_0
    //   11: monitorexit
    //   12: iload_1
    //   13: ireturn
    //   14: astore_2
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_2
    //   18: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	14	finally
    //   15	17	14	finally
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\stats\PassiveTimedConnectionMatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */