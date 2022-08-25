package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache<K, V> {
  private int createCount;
  
  private int evictionCount;
  
  private int hitCount;
  
  private final LinkedHashMap<K, V> map;
  
  private int maxSize;
  
  private int missCount;
  
  private int putCount;
  
  private int size;
  
  public LruCache(int paramInt) {
    if (paramInt <= 0)
      throw new IllegalArgumentException("maxSize <= 0"); 
    this.maxSize = paramInt;
    this.map = new LinkedHashMap<>(0, 0.75F, true);
  }
  
  private int safeSizeOf(K paramK, V paramV) {
    int i = sizeOf(paramK, paramV);
    if (i < 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Negative size: ");
      stringBuilder.append(paramK);
      stringBuilder.append("=");
      stringBuilder.append(paramV);
      throw new IllegalStateException(stringBuilder.toString());
    } 
    return i;
  }
  
  @Nullable
  protected V create(@NonNull K paramK) {
    return null;
  }
  
  public final int createCount() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield createCount : I
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  protected void entryRemoved(boolean paramBoolean, @NonNull K paramK, @NonNull V paramV1, @Nullable V paramV2) {}
  
  public final void evictAll() {
    trimToSize(-1);
  }
  
  public final int evictionCount() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield evictionCount : I
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  @Nullable
  public final V get(@NonNull K paramK) {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull -> 14
    //   4: new java/lang/NullPointerException
    //   7: dup
    //   8: ldc 'key == null'
    //   10: invokespecial <init> : (Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_0
    //   15: monitorenter
    //   16: aload_0
    //   17: getfield map : Ljava/util/LinkedHashMap;
    //   20: aload_1
    //   21: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   24: astore_2
    //   25: aload_2
    //   26: ifnull -> 43
    //   29: aload_0
    //   30: aload_0
    //   31: getfield hitCount : I
    //   34: iconst_1
    //   35: iadd
    //   36: putfield hitCount : I
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_2
    //   42: areturn
    //   43: aload_0
    //   44: aload_0
    //   45: getfield missCount : I
    //   48: iconst_1
    //   49: iadd
    //   50: putfield missCount : I
    //   53: aload_0
    //   54: monitorexit
    //   55: aload_0
    //   56: aload_1
    //   57: invokevirtual create : (Ljava/lang/Object;)Ljava/lang/Object;
    //   60: astore_3
    //   61: aload_3
    //   62: ifnonnull -> 67
    //   65: aconst_null
    //   66: areturn
    //   67: aload_0
    //   68: monitorenter
    //   69: aload_0
    //   70: aload_0
    //   71: getfield createCount : I
    //   74: iconst_1
    //   75: iadd
    //   76: putfield createCount : I
    //   79: aload_0
    //   80: getfield map : Ljava/util/LinkedHashMap;
    //   83: aload_1
    //   84: aload_3
    //   85: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   88: astore_2
    //   89: aload_2
    //   90: ifnull -> 106
    //   93: aload_0
    //   94: getfield map : Ljava/util/LinkedHashMap;
    //   97: aload_1
    //   98: aload_2
    //   99: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   102: pop
    //   103: goto -> 121
    //   106: aload_0
    //   107: aload_0
    //   108: getfield size : I
    //   111: aload_0
    //   112: aload_1
    //   113: aload_3
    //   114: invokespecial safeSizeOf : (Ljava/lang/Object;Ljava/lang/Object;)I
    //   117: iadd
    //   118: putfield size : I
    //   121: aload_0
    //   122: monitorexit
    //   123: aload_2
    //   124: ifnull -> 137
    //   127: aload_0
    //   128: iconst_0
    //   129: aload_1
    //   130: aload_3
    //   131: aload_2
    //   132: invokevirtual entryRemoved : (ZLjava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   135: aload_2
    //   136: areturn
    //   137: aload_0
    //   138: aload_0
    //   139: getfield maxSize : I
    //   142: invokevirtual trimToSize : (I)V
    //   145: aload_3
    //   146: areturn
    //   147: astore_1
    //   148: aload_0
    //   149: monitorexit
    //   150: aload_1
    //   151: athrow
    //   152: astore_1
    //   153: aload_0
    //   154: monitorexit
    //   155: aload_1
    //   156: athrow
    // Exception table:
    //   from	to	target	type
    //   16	25	152	finally
    //   29	41	152	finally
    //   43	55	152	finally
    //   69	89	147	finally
    //   93	103	147	finally
    //   106	121	147	finally
    //   121	123	147	finally
    //   148	150	147	finally
    //   153	155	152	finally
  }
  
  public final int hitCount() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield hitCount : I
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  public final int maxSize() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield maxSize : I
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  public final int missCount() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield missCount : I
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  @Nullable
  public final V put(@NonNull K paramK, @NonNull V paramV) {
    // Byte code:
    //   0: aload_1
    //   1: ifnull -> 96
    //   4: aload_2
    //   5: ifnonnull -> 11
    //   8: goto -> 96
    //   11: aload_0
    //   12: monitorenter
    //   13: aload_0
    //   14: aload_0
    //   15: getfield putCount : I
    //   18: iconst_1
    //   19: iadd
    //   20: putfield putCount : I
    //   23: aload_0
    //   24: aload_0
    //   25: getfield size : I
    //   28: aload_0
    //   29: aload_1
    //   30: aload_2
    //   31: invokespecial safeSizeOf : (Ljava/lang/Object;Ljava/lang/Object;)I
    //   34: iadd
    //   35: putfield size : I
    //   38: aload_0
    //   39: getfield map : Ljava/util/LinkedHashMap;
    //   42: aload_1
    //   43: aload_2
    //   44: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   47: astore_3
    //   48: aload_3
    //   49: ifnull -> 67
    //   52: aload_0
    //   53: aload_0
    //   54: getfield size : I
    //   57: aload_0
    //   58: aload_1
    //   59: aload_3
    //   60: invokespecial safeSizeOf : (Ljava/lang/Object;Ljava/lang/Object;)I
    //   63: isub
    //   64: putfield size : I
    //   67: aload_0
    //   68: monitorexit
    //   69: aload_3
    //   70: ifnull -> 81
    //   73: aload_0
    //   74: iconst_0
    //   75: aload_1
    //   76: aload_3
    //   77: aload_2
    //   78: invokevirtual entryRemoved : (ZLjava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   81: aload_0
    //   82: aload_0
    //   83: getfield maxSize : I
    //   86: invokevirtual trimToSize : (I)V
    //   89: aload_3
    //   90: areturn
    //   91: astore_1
    //   92: aload_0
    //   93: monitorexit
    //   94: aload_1
    //   95: athrow
    //   96: new java/lang/NullPointerException
    //   99: dup
    //   100: ldc 'key == null || value == null'
    //   102: invokespecial <init> : (Ljava/lang/String;)V
    //   105: athrow
    // Exception table:
    //   from	to	target	type
    //   13	48	91	finally
    //   52	67	91	finally
    //   67	69	91	finally
    //   92	94	91	finally
  }
  
  public final int putCount() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield putCount : I
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  @Nullable
  public final V remove(@NonNull K paramK) {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull -> 14
    //   4: new java/lang/NullPointerException
    //   7: dup
    //   8: ldc 'key == null'
    //   10: invokespecial <init> : (Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_0
    //   15: monitorenter
    //   16: aload_0
    //   17: getfield map : Ljava/util/LinkedHashMap;
    //   20: aload_1
    //   21: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   24: astore_2
    //   25: aload_2
    //   26: ifnull -> 44
    //   29: aload_0
    //   30: aload_0
    //   31: getfield size : I
    //   34: aload_0
    //   35: aload_1
    //   36: aload_2
    //   37: invokespecial safeSizeOf : (Ljava/lang/Object;Ljava/lang/Object;)I
    //   40: isub
    //   41: putfield size : I
    //   44: aload_0
    //   45: monitorexit
    //   46: aload_2
    //   47: ifnull -> 58
    //   50: aload_0
    //   51: iconst_0
    //   52: aload_1
    //   53: aload_2
    //   54: aconst_null
    //   55: invokevirtual entryRemoved : (ZLjava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   58: aload_2
    //   59: areturn
    //   60: astore_1
    //   61: aload_0
    //   62: monitorexit
    //   63: aload_1
    //   64: athrow
    // Exception table:
    //   from	to	target	type
    //   16	25	60	finally
    //   29	44	60	finally
    //   44	46	60	finally
    //   61	63	60	finally
  }
  
  public void resize(int paramInt) {
    // Byte code:
    //   0: iload_1
    //   1: ifgt -> 14
    //   4: new java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 'maxSize <= 0'
    //   10: invokespecial <init> : (Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_0
    //   15: monitorenter
    //   16: aload_0
    //   17: iload_1
    //   18: putfield maxSize : I
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_0
    //   24: iload_1
    //   25: invokevirtual trimToSize : (I)V
    //   28: return
    //   29: astore_2
    //   30: aload_0
    //   31: monitorexit
    //   32: aload_2
    //   33: athrow
    // Exception table:
    //   from	to	target	type
    //   16	23	29	finally
    //   30	32	29	finally
  }
  
  public final int size() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield size : I
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  protected int sizeOf(@NonNull K paramK, @NonNull V paramV) {
    return 1;
  }
  
  public final Map<K, V> snapshot() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new java/util/LinkedHashMap
    //   5: dup
    //   6: aload_0
    //   7: getfield map : Ljava/util/LinkedHashMap;
    //   10: invokespecial <init> : (Ljava/util/Map;)V
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
    //   2	14	18	finally
  }
  
  public final String toString() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield hitCount : I
    //   6: aload_0
    //   7: getfield missCount : I
    //   10: iadd
    //   11: istore_1
    //   12: iload_1
    //   13: ifeq -> 29
    //   16: aload_0
    //   17: getfield hitCount : I
    //   20: bipush #100
    //   22: imul
    //   23: iload_1
    //   24: idiv
    //   25: istore_1
    //   26: goto -> 31
    //   29: iconst_0
    //   30: istore_1
    //   31: getstatic java/util/Locale.US : Ljava/util/Locale;
    //   34: ldc 'LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]'
    //   36: iconst_4
    //   37: anewarray java/lang/Object
    //   40: dup
    //   41: iconst_0
    //   42: aload_0
    //   43: getfield maxSize : I
    //   46: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   49: aastore
    //   50: dup
    //   51: iconst_1
    //   52: aload_0
    //   53: getfield hitCount : I
    //   56: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   59: aastore
    //   60: dup
    //   61: iconst_2
    //   62: aload_0
    //   63: getfield missCount : I
    //   66: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   69: aastore
    //   70: dup
    //   71: iconst_3
    //   72: iload_1
    //   73: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   76: aastore
    //   77: invokestatic format : (Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   80: astore_2
    //   81: aload_0
    //   82: monitorexit
    //   83: aload_2
    //   84: areturn
    //   85: astore_2
    //   86: aload_0
    //   87: monitorexit
    //   88: aload_2
    //   89: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	85	finally
    //   16	26	85	finally
    //   31	81	85	finally
  }
  
  public void trimToSize(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield size : I
    //   6: iflt -> 135
    //   9: aload_0
    //   10: getfield map : Ljava/util/LinkedHashMap;
    //   13: invokevirtual isEmpty : ()Z
    //   16: ifeq -> 29
    //   19: aload_0
    //   20: getfield size : I
    //   23: ifeq -> 29
    //   26: goto -> 135
    //   29: aload_0
    //   30: getfield size : I
    //   33: iload_1
    //   34: if_icmple -> 132
    //   37: aload_0
    //   38: getfield map : Ljava/util/LinkedHashMap;
    //   41: invokevirtual isEmpty : ()Z
    //   44: ifeq -> 50
    //   47: goto -> 132
    //   50: aload_0
    //   51: getfield map : Ljava/util/LinkedHashMap;
    //   54: invokevirtual entrySet : ()Ljava/util/Set;
    //   57: invokeinterface iterator : ()Ljava/util/Iterator;
    //   62: invokeinterface next : ()Ljava/lang/Object;
    //   67: checkcast java/util/Map$Entry
    //   70: astore_2
    //   71: aload_2
    //   72: invokeinterface getKey : ()Ljava/lang/Object;
    //   77: astore_3
    //   78: aload_2
    //   79: invokeinterface getValue : ()Ljava/lang/Object;
    //   84: astore_2
    //   85: aload_0
    //   86: getfield map : Ljava/util/LinkedHashMap;
    //   89: aload_3
    //   90: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   93: pop
    //   94: aload_0
    //   95: aload_0
    //   96: getfield size : I
    //   99: aload_0
    //   100: aload_3
    //   101: aload_2
    //   102: invokespecial safeSizeOf : (Ljava/lang/Object;Ljava/lang/Object;)I
    //   105: isub
    //   106: putfield size : I
    //   109: aload_0
    //   110: aload_0
    //   111: getfield evictionCount : I
    //   114: iconst_1
    //   115: iadd
    //   116: putfield evictionCount : I
    //   119: aload_0
    //   120: monitorexit
    //   121: aload_0
    //   122: iconst_1
    //   123: aload_3
    //   124: aload_2
    //   125: aconst_null
    //   126: invokevirtual entryRemoved : (ZLjava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   129: goto -> 0
    //   132: aload_0
    //   133: monitorexit
    //   134: return
    //   135: new java/lang/IllegalStateException
    //   138: astore_2
    //   139: new java/lang/StringBuilder
    //   142: astore_3
    //   143: aload_3
    //   144: invokespecial <init> : ()V
    //   147: aload_3
    //   148: aload_0
    //   149: invokevirtual getClass : ()Ljava/lang/Class;
    //   152: invokevirtual getName : ()Ljava/lang/String;
    //   155: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: pop
    //   159: aload_3
    //   160: ldc '.sizeOf() is reporting inconsistent results!'
    //   162: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: pop
    //   166: aload_2
    //   167: aload_3
    //   168: invokevirtual toString : ()Ljava/lang/String;
    //   171: invokespecial <init> : (Ljava/lang/String;)V
    //   174: aload_2
    //   175: athrow
    //   176: astore_3
    //   177: aload_0
    //   178: monitorexit
    //   179: aload_3
    //   180: athrow
    // Exception table:
    //   from	to	target	type
    //   2	26	176	finally
    //   29	47	176	finally
    //   50	121	176	finally
    //   132	134	176	finally
    //   135	176	176	finally
    //   177	179	176	finally
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v\\util\LruCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */