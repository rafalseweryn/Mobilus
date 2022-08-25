package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.internal.Asserts;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public final class ObjectDataBuffer<T> extends AbstractDataBuffer<T> implements DataBufferObserver.Observable, ObjectExclusionFilterable<T> {
  private final ArrayList<Integer> zzob = new ArrayList<>();
  
  private final HashSet<Integer> zzoe = new HashSet<>();
  
  private DataBufferObserverSet zzof = new DataBufferObserverSet();
  
  private final ArrayList<T> zzog = new ArrayList<>();
  
  public ObjectDataBuffer() {
    super(null);
    zzcl();
  }
  
  public ObjectDataBuffer(ArrayList<T> paramArrayList) {
    super(null);
    zzcl();
  }
  
  public ObjectDataBuffer(T... paramVarArgs) {
    super(null);
    zzcl();
  }
  
  private final void zzcl() {
    this.zzob.clear();
    int i = this.zzog.size();
    for (byte b = 0; b < i; b++) {
      if (!this.zzoe.contains(Integer.valueOf(b)))
        this.zzob.add(Integer.valueOf(b)); 
    } 
  }
  
  public final void add(T paramT) {
    int i = this.zzog.size();
    this.zzog.add(paramT);
    zzcl();
    if (this.zzof.hasObservers()) {
      Asserts.checkState(this.zzoe.contains(Integer.valueOf(i)) ^ true);
      int j = this.zzob.size();
      boolean bool1 = false;
      if (j > 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      Asserts.checkState(bool2);
      ArrayList<Integer> arrayList = this.zzob;
      j--;
      boolean bool2 = bool1;
      if (((Integer)arrayList.get(j)).intValue() == i)
        bool2 = true; 
      Asserts.checkState(bool2);
      this.zzof.onDataRangeInserted(j, 1);
    } 
  }
  
  public final void addObserver(DataBufferObserver paramDataBufferObserver) {
    this.zzof.addObserver(paramDataBufferObserver);
  }
  
  public final void filterOut(T paramT) {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzog : Ljava/util/ArrayList;
    //   4: invokevirtual size : ()I
    //   7: istore_2
    //   8: iconst_0
    //   9: istore_3
    //   10: iload_3
    //   11: istore #4
    //   13: iconst_m1
    //   14: istore #5
    //   16: iload #5
    //   18: istore #6
    //   20: iload #6
    //   22: istore #7
    //   24: iload #6
    //   26: istore #8
    //   28: iload_3
    //   29: iload_2
    //   30: if_icmpge -> 225
    //   33: iload #4
    //   35: istore #6
    //   37: iload #5
    //   39: istore #9
    //   41: iload #8
    //   43: istore #10
    //   45: iload #7
    //   47: istore #11
    //   49: aload_0
    //   50: getfield zzoe : Ljava/util/HashSet;
    //   53: iload_3
    //   54: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   57: invokevirtual contains : (Ljava/lang/Object;)Z
    //   60: ifne -> 203
    //   63: iinc #5, 1
    //   66: aload_1
    //   67: aload_0
    //   68: getfield zzog : Ljava/util/ArrayList;
    //   71: iload_3
    //   72: invokevirtual get : (I)Ljava/lang/Object;
    //   75: invokevirtual equals : (Ljava/lang/Object;)Z
    //   78: ifeq -> 150
    //   81: aload_0
    //   82: getfield zzoe : Ljava/util/HashSet;
    //   85: iload_3
    //   86: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   89: invokevirtual add : (Ljava/lang/Object;)Z
    //   92: pop
    //   93: iload #7
    //   95: istore #11
    //   97: aload_0
    //   98: getfield zzof : Lcom/google/android/gms/common/data/DataBufferObserverSet;
    //   101: invokevirtual hasObservers : ()Z
    //   104: ifeq -> 136
    //   107: iload #8
    //   109: ifge -> 130
    //   112: iload #5
    //   114: istore #10
    //   116: iconst_1
    //   117: istore #6
    //   119: iload #6
    //   121: istore #11
    //   123: iload #5
    //   125: istore #9
    //   127: goto -> 203
    //   130: iload #7
    //   132: iconst_1
    //   133: iadd
    //   134: istore #11
    //   136: iconst_1
    //   137: istore #6
    //   139: iload #5
    //   141: istore #9
    //   143: iload #8
    //   145: istore #10
    //   147: goto -> 203
    //   150: iload #4
    //   152: istore #6
    //   154: iload #5
    //   156: istore #9
    //   158: iload #8
    //   160: istore #10
    //   162: iload #7
    //   164: istore #11
    //   166: iload #8
    //   168: iflt -> 203
    //   171: aload_0
    //   172: invokespecial zzcl : ()V
    //   175: aload_0
    //   176: getfield zzof : Lcom/google/android/gms/common/data/DataBufferObserverSet;
    //   179: iload #8
    //   181: iload #7
    //   183: invokevirtual onDataRangeRemoved : (II)V
    //   186: iload #5
    //   188: iload #7
    //   190: isub
    //   191: istore #9
    //   193: iconst_0
    //   194: istore #6
    //   196: iconst_m1
    //   197: istore #10
    //   199: iload #10
    //   201: istore #11
    //   203: iinc #3, 1
    //   206: iload #6
    //   208: istore #4
    //   210: iload #9
    //   212: istore #5
    //   214: iload #10
    //   216: istore #8
    //   218: iload #11
    //   220: istore #7
    //   222: goto -> 28
    //   225: iload #4
    //   227: ifeq -> 234
    //   230: aload_0
    //   231: invokespecial zzcl : ()V
    //   234: iload #8
    //   236: iflt -> 250
    //   239: aload_0
    //   240: getfield zzof : Lcom/google/android/gms/common/data/DataBufferObserverSet;
    //   243: iload #8
    //   245: iload #7
    //   247: invokevirtual onDataRangeRemoved : (II)V
    //   250: return
  }
  
  public final void filterOutRaw(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzoe : Ljava/util/HashSet;
    //   4: iload_1
    //   5: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   8: invokevirtual add : (Ljava/lang/Object;)Z
    //   11: istore_2
    //   12: aload_0
    //   13: getfield zzof : Lcom/google/android/gms/common/data/DataBufferObserverSet;
    //   16: invokevirtual hasObservers : ()Z
    //   19: ifeq -> 70
    //   22: iload_2
    //   23: ifeq -> 70
    //   26: iconst_0
    //   27: istore_3
    //   28: aload_0
    //   29: getfield zzob : Ljava/util/ArrayList;
    //   32: invokevirtual size : ()I
    //   35: istore #4
    //   37: iload_3
    //   38: iload #4
    //   40: if_icmpge -> 70
    //   43: aload_0
    //   44: getfield zzob : Ljava/util/ArrayList;
    //   47: iload_3
    //   48: invokevirtual get : (I)Ljava/lang/Object;
    //   51: checkcast java/lang/Integer
    //   54: invokevirtual intValue : ()I
    //   57: iload_1
    //   58: if_icmpne -> 64
    //   61: goto -> 72
    //   64: iinc #3, 1
    //   67: goto -> 37
    //   70: iconst_m1
    //   71: istore_3
    //   72: aload_0
    //   73: invokespecial zzcl : ()V
    //   76: iload_3
    //   77: iflt -> 89
    //   80: aload_0
    //   81: getfield zzof : Lcom/google/android/gms/common/data/DataBufferObserverSet;
    //   84: iload_3
    //   85: iconst_1
    //   86: invokevirtual onDataRangeRemoved : (II)V
    //   89: return
  }
  
  public final T get(int paramInt) {
    return this.zzog.get(getRawPosition(paramInt));
  }
  
  public final int getCount() {
    return this.zzog.size() - this.zzoe.size();
  }
  
  public final Bundle getMetadata() {
    return null;
  }
  
  public final int getPositionFromRawPosition(int paramInt) {
    int i = -1;
    byte b = 0;
    while (b <= paramInt) {
      int j = i;
      if (!this.zzoe.contains(Integer.valueOf(b)))
        j = i + 1; 
      b++;
      i = j;
    } 
    return i;
  }
  
  public final T getRaw(int paramInt) {
    return this.zzog.get(paramInt);
  }
  
  public final int getRawCount() {
    return this.zzog.size();
  }
  
  public final int getRawPosition(int paramInt) {
    if (paramInt < 0 || paramInt >= super.getCount()) {
      StringBuilder stringBuilder = new StringBuilder(53);
      stringBuilder.append("Position ");
      stringBuilder.append(paramInt);
      stringBuilder.append(" is out of bounds for this buffer");
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    return ((Integer)this.zzob.get(paramInt)).intValue();
  }
  
  public final void insertRaw(int paramInt, T paramT) {
    this.zzog.add(paramInt, paramT);
    HashSet<Integer> hashSet = new HashSet(this.zzoe.size());
    null = this.zzoe.iterator();
    int i = paramInt;
    while (null.hasNext()) {
      Integer integer = null.next();
      if (integer.intValue() < paramInt) {
        i--;
        continue;
      } 
      hashSet.add(Integer.valueOf(integer.intValue() + 1));
      null.remove();
    } 
    for (Integer integer : hashSet)
      this.zzoe.add(integer); 
    zzcl();
    if (this.zzof.hasObservers())
      this.zzof.onDataRangeInserted(i, 1); 
  }
  
  public final boolean isRawPositionFiltered(int paramInt) {
    return this.zzoe.contains(Integer.valueOf(paramInt));
  }
  
  public final void notifyChanged(T paramT) {
    if (!this.zzof.hasObservers())
      return; 
    byte b = 0;
    int i = this.zzob.size();
    while (b < i) {
      if (paramT.equals(this.zzog.get(((Integer)this.zzob.get(b)).intValue())))
        this.zzof.onDataRangeChanged(b, 1); 
      b++;
    } 
  }
  
  public final void release() {
    this.zzof.clear();
  }
  
  public final void removeObserver(DataBufferObserver paramDataBufferObserver) {
    this.zzof.removeObserver(paramDataBufferObserver);
  }
  
  public final void removeRaw(int paramInt) {
    this.zzog.remove(paramInt);
    boolean bool = this.zzoe.remove(Integer.valueOf(paramInt));
    HashSet<Integer> hashSet = new HashSet(this.zzoe.size());
    Iterator<Integer> iterator = this.zzoe.iterator();
    int i = paramInt;
    while (iterator.hasNext()) {
      Integer integer = iterator.next();
      if (integer.intValue() < paramInt) {
        i--;
        continue;
      } 
      iterator.remove();
      hashSet.add(Integer.valueOf(integer.intValue() - 1));
    } 
    for (Integer integer : hashSet)
      this.zzoe.add(integer); 
    zzcl();
    if (!bool && this.zzof.hasObservers())
      this.zzof.onDataRangeRemoved(i, 1); 
  }
  
  public final boolean setRaw(int paramInt, T paramT) {
    this.zzog.set(paramInt, paramT);
    int i = this.zzoe.contains(Integer.valueOf(paramInt)) ^ true;
    if (i != 0 && this.zzof.hasObservers()) {
      byte b = 0;
      int j = this.zzob.size();
      while (b < j) {
        if (((Integer)this.zzob.get(b)).intValue() == paramInt) {
          this.zzof.onDataRangeChanged(b, 1);
          return i;
        } 
        b++;
      } 
    } 
    return i;
  }
  
  public final void unfilter(T paramT) {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzog : Ljava/util/ArrayList;
    //   4: invokevirtual size : ()I
    //   7: istore_2
    //   8: iconst_0
    //   9: istore_3
    //   10: iload_3
    //   11: istore #4
    //   13: iload #4
    //   15: istore #5
    //   17: iconst_m1
    //   18: istore #6
    //   20: iload #6
    //   22: istore #7
    //   24: iload #4
    //   26: istore #8
    //   28: iload_3
    //   29: iload_2
    //   30: if_icmpge -> 262
    //   33: aload_0
    //   34: getfield zzoe : Ljava/util/HashSet;
    //   37: iload_3
    //   38: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   41: invokevirtual contains : (Ljava/lang/Object;)Z
    //   44: ifne -> 106
    //   47: iinc #5, 1
    //   50: iload #8
    //   52: istore #4
    //   54: iload #5
    //   56: istore #9
    //   58: iload #6
    //   60: istore #10
    //   62: iload #7
    //   64: istore #11
    //   66: iload #6
    //   68: iflt -> 240
    //   71: aload_0
    //   72: invokespecial zzcl : ()V
    //   75: aload_0
    //   76: getfield zzof : Lcom/google/android/gms/common/data/DataBufferObserverSet;
    //   79: iload #6
    //   81: iload #7
    //   83: invokevirtual onDataRangeInserted : (II)V
    //   86: iload #5
    //   88: iload #7
    //   90: iadd
    //   91: istore #9
    //   93: iconst_0
    //   94: istore #4
    //   96: iconst_m1
    //   97: istore #10
    //   99: iload #10
    //   101: istore #11
    //   103: goto -> 240
    //   106: aload_1
    //   107: aload_0
    //   108: getfield zzog : Ljava/util/ArrayList;
    //   111: iload_3
    //   112: invokevirtual get : (I)Ljava/lang/Object;
    //   115: invokevirtual equals : (Ljava/lang/Object;)Z
    //   118: ifeq -> 190
    //   121: aload_0
    //   122: getfield zzoe : Ljava/util/HashSet;
    //   125: iload_3
    //   126: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   129: invokevirtual remove : (Ljava/lang/Object;)Z
    //   132: pop
    //   133: iload #7
    //   135: istore #11
    //   137: aload_0
    //   138: getfield zzof : Lcom/google/android/gms/common/data/DataBufferObserverSet;
    //   141: invokevirtual hasObservers : ()Z
    //   144: ifeq -> 176
    //   147: iload #6
    //   149: ifge -> 170
    //   152: iload #5
    //   154: istore #10
    //   156: iconst_1
    //   157: istore #4
    //   159: iload #4
    //   161: istore #11
    //   163: iload #5
    //   165: istore #9
    //   167: goto -> 240
    //   170: iload #7
    //   172: iconst_1
    //   173: iadd
    //   174: istore #11
    //   176: iconst_1
    //   177: istore #4
    //   179: iload #5
    //   181: istore #9
    //   183: iload #6
    //   185: istore #10
    //   187: goto -> 240
    //   190: iload #8
    //   192: istore #4
    //   194: iload #5
    //   196: istore #9
    //   198: iload #6
    //   200: istore #10
    //   202: iload #7
    //   204: istore #11
    //   206: aload_0
    //   207: getfield zzof : Lcom/google/android/gms/common/data/DataBufferObserverSet;
    //   210: invokevirtual hasObservers : ()Z
    //   213: ifeq -> 240
    //   216: iload #8
    //   218: istore #4
    //   220: iload #5
    //   222: istore #9
    //   224: iload #6
    //   226: istore #10
    //   228: iload #7
    //   230: istore #11
    //   232: iload #6
    //   234: iflt -> 240
    //   237: goto -> 71
    //   240: iinc #3, 1
    //   243: iload #4
    //   245: istore #8
    //   247: iload #9
    //   249: istore #5
    //   251: iload #10
    //   253: istore #6
    //   255: iload #11
    //   257: istore #7
    //   259: goto -> 28
    //   262: iload #8
    //   264: ifeq -> 271
    //   267: aload_0
    //   268: invokespecial zzcl : ()V
    //   271: iload #6
    //   273: iflt -> 287
    //   276: aload_0
    //   277: getfield zzof : Lcom/google/android/gms/common/data/DataBufferObserverSet;
    //   280: iload #6
    //   282: iload #7
    //   284: invokevirtual onDataRangeInserted : (II)V
    //   287: return
  }
  
  public final void unfilterRaw(int paramInt) {
    boolean bool = this.zzoe.remove(Integer.valueOf(paramInt));
    zzcl();
    if (this.zzof.hasObservers()) {
      byte b2;
      if (!bool)
        return; 
      byte b1 = -1;
      byte b = 0;
      int i = this.zzob.size();
      while (true) {
        b2 = b1;
        if (b < i) {
          if (((Integer)this.zzob.get(b)).intValue() == paramInt) {
            b2 = b;
            break;
          } 
          b++;
          continue;
        } 
        break;
      } 
      if (b2 >= 0)
        this.zzof.onDataRangeInserted(b2, 1); 
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\ObjectDataBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */