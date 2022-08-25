package com.google.android.gms.common.data;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Iterator;

@VisibleForTesting
public final class ConcatenatedDataBuffer<T> implements DataBuffer<T>, ExclusionFilterable, TextFilterable {
  private Bundle mBundle;
  
  private final ArrayList<DataBuffer<T>> zznf = new ArrayList<>();
  
  private final ArrayList<Integer> zzng = new ArrayList<>();
  
  private int zznh;
  
  public ConcatenatedDataBuffer() {}
  
  public ConcatenatedDataBuffer(DataBuffer<T> paramDataBuffer) {
    append(paramDataBuffer);
  }
  
  public static <T> ConcatenatedDataBuffer<T> extend(ConcatenatedDataBuffer<T> paramConcatenatedDataBuffer, DataBuffer<T> paramDataBuffer) {
    ConcatenatedDataBuffer<T> concatenatedDataBuffer = new ConcatenatedDataBuffer();
    ArrayList<DataBuffer<T>> arrayList = paramConcatenatedDataBuffer.zznf;
    int i = arrayList.size();
    byte b = 0;
    while (b < i) {
      DataBuffer dataBuffer = (DataBuffer)arrayList.get(b);
      b++;
      concatenatedDataBuffer.append(dataBuffer);
    } 
    concatenatedDataBuffer.append(paramDataBuffer);
    return concatenatedDataBuffer;
  }
  
  public final void append(DataBuffer<T> paramDataBuffer) {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull -> 5
    //   4: return
    //   5: aload_0
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield zznf : Ljava/util/ArrayList;
    //   11: invokevirtual isEmpty : ()Z
    //   14: ifeq -> 56
    //   17: new android/os/Bundle
    //   20: astore_2
    //   21: aload_2
    //   22: invokespecial <init> : ()V
    //   25: aload_0
    //   26: aload_2
    //   27: putfield mBundle : Landroid/os/Bundle;
    //   30: aload_1
    //   31: invokeinterface getMetadata : ()Landroid/os/Bundle;
    //   36: astore_2
    //   37: aload_2
    //   38: ifnull -> 56
    //   41: aload_0
    //   42: getfield mBundle : Landroid/os/Bundle;
    //   45: ldc 'prev_page_token'
    //   47: aload_2
    //   48: ldc 'prev_page_token'
    //   50: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   53: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   56: aload_0
    //   57: getfield zznf : Ljava/util/ArrayList;
    //   60: aload_1
    //   61: invokevirtual add : (Ljava/lang/Object;)Z
    //   64: pop
    //   65: aload_0
    //   66: invokevirtual computeCounts : ()V
    //   69: aload_1
    //   70: invokeinterface getMetadata : ()Landroid/os/Bundle;
    //   75: astore_1
    //   76: aload_1
    //   77: ifnull -> 98
    //   80: aload_0
    //   81: getfield mBundle : Landroid/os/Bundle;
    //   84: ldc 'next_page_token'
    //   86: aload_1
    //   87: ldc 'next_page_token'
    //   89: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   92: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   95: goto -> 107
    //   98: aload_0
    //   99: getfield mBundle : Landroid/os/Bundle;
    //   102: ldc 'next_page_token'
    //   104: invokevirtual remove : (Ljava/lang/String;)V
    //   107: aload_0
    //   108: monitorexit
    //   109: return
    //   110: astore_1
    //   111: aload_0
    //   112: monitorexit
    //   113: aload_1
    //   114: athrow
    // Exception table:
    //   from	to	target	type
    //   7	37	110	finally
    //   41	56	110	finally
    //   56	76	110	finally
    //   80	95	110	finally
    //   98	107	110	finally
    //   107	109	110	finally
    //   111	113	110	finally
  }
  
  public final void clearFilters() {
    int i = this.zznf.size();
    for (byte b = 0; b < i; b++) {
      DataBuffer dataBuffer = this.zznf.get(b);
      if (dataBuffer instanceof ExclusionFilterable)
        ((ExclusionFilterable)dataBuffer).clearFilters(); 
    } 
    computeCounts();
  }
  
  @Deprecated
  public final void close() {
    release();
  }
  
  public final void computeCounts() {
    this.zzng.clear();
    int i = this.zznf.size();
    byte b = 0;
    int j;
    for (j = 0; b < i; j = k) {
      DataBuffer dataBuffer = this.zznf.get(b);
      int k = j;
      if (dataBuffer != null)
        k = j + dataBuffer.getCount(); 
      this.zzng.add(Integer.valueOf(k));
      b++;
    } 
    this.zznh = j;
  }
  
  public final void filterOut(String paramString) {
    int i = this.zznf.size();
    for (byte b = 0; b < i; b++) {
      DataBuffer dataBuffer = this.zznf.get(b);
      if (dataBuffer instanceof ExclusionFilterable)
        ((ExclusionFilterable)dataBuffer).filterOut(paramString); 
    } 
    computeCounts();
  }
  
  public final T get(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iconst_0
    //   3: istore_2
    //   4: aload_0
    //   5: getfield zznf : Ljava/util/ArrayList;
    //   8: invokevirtual size : ()I
    //   11: istore_3
    //   12: iload_2
    //   13: iload_3
    //   14: if_icmpge -> 89
    //   17: aload_0
    //   18: getfield zzng : Ljava/util/ArrayList;
    //   21: iload_2
    //   22: invokevirtual get : (I)Ljava/lang/Object;
    //   25: checkcast java/lang/Integer
    //   28: invokevirtual intValue : ()I
    //   31: istore #4
    //   33: iload_1
    //   34: iload #4
    //   36: if_icmpge -> 83
    //   39: aload_0
    //   40: getfield zznf : Ljava/util/ArrayList;
    //   43: iload_2
    //   44: invokevirtual get : (I)Ljava/lang/Object;
    //   47: checkcast com/google/android/gms/common/data/DataBuffer
    //   50: astore #5
    //   52: aload #5
    //   54: ifnull -> 83
    //   57: aload #5
    //   59: iload_1
    //   60: iload #4
    //   62: isub
    //   63: aload #5
    //   65: invokeinterface getCount : ()I
    //   70: iadd
    //   71: invokeinterface get : (I)Ljava/lang/Object;
    //   76: astore #5
    //   78: aload_0
    //   79: monitorexit
    //   80: aload #5
    //   82: areturn
    //   83: iinc #2, 1
    //   86: goto -> 12
    //   89: aload_0
    //   90: monitorexit
    //   91: aconst_null
    //   92: areturn
    //   93: astore #5
    //   95: aload_0
    //   96: monitorexit
    //   97: aload #5
    //   99: athrow
    // Exception table:
    //   from	to	target	type
    //   4	12	93	finally
    //   17	33	93	finally
    //   39	52	93	finally
    //   57	80	93	finally
    //   89	91	93	finally
    //   95	97	93	finally
  }
  
  public final int getCount() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zznh : I
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
    //   2	9	11	finally
    //   12	14	11	finally
  }
  
  public final Bundle getMetadata() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mBundle : Landroid/os/Bundle;
    //   6: astore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: aload_1
    //   10: areturn
    //   11: astore_1
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_1
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	11	finally
    //   12	14	11	finally
  }
  
  @Deprecated
  public final boolean isClosed() {
    return false;
  }
  
  public final Iterator<T> iterator() {
    return new DataBufferIterator<>(this);
  }
  
  public final void release() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iconst_0
    //   3: istore_1
    //   4: aload_0
    //   5: getfield zznf : Ljava/util/ArrayList;
    //   8: invokevirtual size : ()I
    //   11: istore_2
    //   12: iload_1
    //   13: iload_2
    //   14: if_icmpge -> 45
    //   17: aload_0
    //   18: getfield zznf : Ljava/util/ArrayList;
    //   21: iload_1
    //   22: invokevirtual get : (I)Ljava/lang/Object;
    //   25: checkcast com/google/android/gms/common/data/DataBuffer
    //   28: astore_3
    //   29: aload_3
    //   30: ifnull -> 39
    //   33: aload_3
    //   34: invokeinterface release : ()V
    //   39: iinc #1, 1
    //   42: goto -> 12
    //   45: aload_0
    //   46: getfield zznf : Ljava/util/ArrayList;
    //   49: invokevirtual clear : ()V
    //   52: aload_0
    //   53: getfield zzng : Ljava/util/ArrayList;
    //   56: invokevirtual clear : ()V
    //   59: aload_0
    //   60: aconst_null
    //   61: putfield mBundle : Landroid/os/Bundle;
    //   64: aload_0
    //   65: monitorexit
    //   66: return
    //   67: astore_3
    //   68: aload_0
    //   69: monitorexit
    //   70: aload_3
    //   71: athrow
    // Exception table:
    //   from	to	target	type
    //   4	12	67	finally
    //   17	29	67	finally
    //   33	39	67	finally
    //   45	66	67	finally
    //   68	70	67	finally
  }
  
  public final void setFilterTerm(Context paramContext, TextFilterable.StringFilter paramStringFilter, String paramString) {
    int i = this.zznf.size();
    for (byte b = 0; b < i; b++) {
      DataBuffer dataBuffer = this.zznf.get(b);
      if (dataBuffer instanceof TextFilterable)
        ((TextFilterable)dataBuffer).setFilterTerm(paramContext, paramStringFilter, paramString); 
    } 
    computeCounts();
  }
  
  public final void setFilterTerm(Context paramContext, String paramString) {
    int i = this.zznf.size();
    for (byte b = 0; b < i; b++) {
      DataBuffer dataBuffer = this.zznf.get(b);
      if (dataBuffer instanceof TextFilterable)
        ((TextFilterable)dataBuffer).setFilterTerm(paramContext, paramString); 
    } 
    computeCounts();
  }
  
  public final Iterator<T> singleRefIterator() {
    return iterator();
  }
  
  public final void unfilter(String paramString) {
    int i = this.zznf.size();
    for (byte b = 0; b < i; b++) {
      DataBuffer dataBuffer = this.zznf.get(b);
      if (dataBuffer instanceof ExclusionFilterable)
        ((ExclusionFilterable)dataBuffer).unfilter(paramString); 
    } 
    computeCounts();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\ConcatenatedDataBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */