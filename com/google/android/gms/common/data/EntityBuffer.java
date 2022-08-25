package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class EntityBuffer<T> extends AbstractDataBuffer<T> {
  private boolean zzoa = false;
  
  private ArrayList<Integer> zzob;
  
  protected EntityBuffer(DataHolder paramDataHolder) {
    super(paramDataHolder);
  }
  
  private final void zzck() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzoa : Z
    //   6: ifne -> 233
    //   9: aload_0
    //   10: getfield mDataHolder : Lcom/google/android/gms/common/data/DataHolder;
    //   13: invokevirtual getCount : ()I
    //   16: istore_1
    //   17: new java/util/ArrayList
    //   20: astore_2
    //   21: aload_2
    //   22: invokespecial <init> : ()V
    //   25: aload_0
    //   26: aload_2
    //   27: putfield zzob : Ljava/util/ArrayList;
    //   30: iload_1
    //   31: ifle -> 228
    //   34: aload_0
    //   35: getfield zzob : Ljava/util/ArrayList;
    //   38: iconst_0
    //   39: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   42: invokevirtual add : (Ljava/lang/Object;)Z
    //   45: pop
    //   46: aload_0
    //   47: invokevirtual getPrimaryDataMarkerColumn : ()Ljava/lang/String;
    //   50: astore_3
    //   51: aload_0
    //   52: getfield mDataHolder : Lcom/google/android/gms/common/data/DataHolder;
    //   55: iconst_0
    //   56: invokevirtual getWindowIndex : (I)I
    //   59: istore #4
    //   61: aload_0
    //   62: getfield mDataHolder : Lcom/google/android/gms/common/data/DataHolder;
    //   65: aload_3
    //   66: iconst_0
    //   67: iload #4
    //   69: invokevirtual getString : (Ljava/lang/String;II)Ljava/lang/String;
    //   72: astore_2
    //   73: iconst_1
    //   74: istore #4
    //   76: iload #4
    //   78: iload_1
    //   79: if_icmpge -> 228
    //   82: aload_0
    //   83: getfield mDataHolder : Lcom/google/android/gms/common/data/DataHolder;
    //   86: iload #4
    //   88: invokevirtual getWindowIndex : (I)I
    //   91: istore #5
    //   93: aload_0
    //   94: getfield mDataHolder : Lcom/google/android/gms/common/data/DataHolder;
    //   97: aload_3
    //   98: iload #4
    //   100: iload #5
    //   102: invokevirtual getString : (Ljava/lang/String;II)Ljava/lang/String;
    //   105: astore #6
    //   107: aload #6
    //   109: ifnonnull -> 190
    //   112: new java/lang/NullPointerException
    //   115: astore #7
    //   117: aload_3
    //   118: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   121: invokevirtual length : ()I
    //   124: istore_1
    //   125: new java/lang/StringBuilder
    //   128: astore_2
    //   129: aload_2
    //   130: iload_1
    //   131: bipush #78
    //   133: iadd
    //   134: invokespecial <init> : (I)V
    //   137: aload_2
    //   138: ldc 'Missing value for markerColumn: '
    //   140: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: pop
    //   144: aload_2
    //   145: aload_3
    //   146: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: pop
    //   150: aload_2
    //   151: ldc ', at row: '
    //   153: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: pop
    //   157: aload_2
    //   158: iload #4
    //   160: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   163: pop
    //   164: aload_2
    //   165: ldc ', for window: '
    //   167: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: pop
    //   171: aload_2
    //   172: iload #5
    //   174: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   177: pop
    //   178: aload #7
    //   180: aload_2
    //   181: invokevirtual toString : ()Ljava/lang/String;
    //   184: invokespecial <init> : (Ljava/lang/String;)V
    //   187: aload #7
    //   189: athrow
    //   190: aload_2
    //   191: astore #7
    //   193: aload #6
    //   195: aload_2
    //   196: invokevirtual equals : (Ljava/lang/Object;)Z
    //   199: ifne -> 219
    //   202: aload_0
    //   203: getfield zzob : Ljava/util/ArrayList;
    //   206: iload #4
    //   208: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   211: invokevirtual add : (Ljava/lang/Object;)Z
    //   214: pop
    //   215: aload #6
    //   217: astore #7
    //   219: iinc #4, 1
    //   222: aload #7
    //   224: astore_2
    //   225: goto -> 76
    //   228: aload_0
    //   229: iconst_1
    //   230: putfield zzoa : Z
    //   233: aload_0
    //   234: monitorexit
    //   235: return
    //   236: astore_2
    //   237: aload_0
    //   238: monitorexit
    //   239: aload_2
    //   240: athrow
    // Exception table:
    //   from	to	target	type
    //   2	30	236	finally
    //   34	73	236	finally
    //   82	107	236	finally
    //   112	190	236	finally
    //   193	215	236	finally
    //   228	233	236	finally
    //   233	235	236	finally
    //   237	239	236	finally
  }
  
  public final T get(int paramInt) {
    zzck();
    return getEntry(zzi(paramInt), getChildCount(paramInt));
  }
  
  protected int getChildCount(int paramInt) {
    if (paramInt >= 0) {
      int i;
      if (paramInt == this.zzob.size())
        return 0; 
      if (paramInt == this.zzob.size() - 1) {
        i = this.mDataHolder.getCount();
      } else {
        i = ((Integer)this.zzob.get(paramInt + 1)).intValue();
      } 
      i -= ((Integer)this.zzob.get(paramInt)).intValue();
      if (i == 1) {
        paramInt = zzi(paramInt);
        int j = this.mDataHolder.getWindowIndex(paramInt);
        String str = getChildDataMarkerColumn();
        if (str != null && this.mDataHolder.getString(str, paramInt, j) == null)
          return 0; 
      } 
      return i;
    } 
    return 0;
  }
  
  protected String getChildDataMarkerColumn() {
    return null;
  }
  
  public int getCount() {
    zzck();
    return this.zzob.size();
  }
  
  protected abstract T getEntry(int paramInt1, int paramInt2);
  
  protected abstract String getPrimaryDataMarkerColumn();
  
  final int zzi(int paramInt) {
    if (paramInt < 0 || paramInt >= this.zzob.size()) {
      StringBuilder stringBuilder = new StringBuilder(53);
      stringBuilder.append("Position ");
      stringBuilder.append(paramInt);
      stringBuilder.append(" is out of bounds for this buffer");
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    return ((Integer)this.zzob.get(paramInt)).intValue();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\EntityBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */