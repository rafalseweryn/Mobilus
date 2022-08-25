package com.google.android.gms.common.data;

import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@VisibleForTesting
public final class ExclusionFilteredDataBuffer<T> extends FilteredDataBuffer<T> implements DataBufferObserver.Observable, ExclusionFilterable {
  private AbstractDataBuffer<T> zzoc;
  
  private final String zzod;
  
  private final HashSet<Integer> zzoe = new HashSet<>();
  
  private DataBufferObserverSet zzof;
  
  public ExclusionFilteredDataBuffer(AbstractDataBuffer<T> paramAbstractDataBuffer, String paramString) {
    super(paramAbstractDataBuffer);
    this.zzoc = paramAbstractDataBuffer;
    this.zzod = paramString;
    this.zzof = new DataBufferObserverSet();
  }
  
  private final ArrayList<Integer> zza(String paramString, ArrayList<Integer> paramArrayList) {
    ArrayList<Integer> arrayList = new ArrayList();
    if (paramArrayList != null)
      paramArrayList.clear(); 
    DataHolder dataHolder = this.zzoc.mDataHolder;
    String str = this.zzod;
    boolean bool = this.zzoc instanceof EntityBuffer;
    int i = this.zzoc.getCount();
    byte b = 0;
    int j;
    for (j = 0; b < i; j = k) {
      int k;
      if (bool) {
        k = ((EntityBuffer)this.zzoc).zzi(b);
      } else {
        k = b;
      } 
      String str1 = dataHolder.getString(str, k, dataHolder.getWindowIndex(k));
      if (paramArrayList != null) {
        if (this.zzoe.contains(Integer.valueOf(b))) {
          byte b1 = -j;
          k = j;
          j = b1 - 1;
        } else {
          k = j + 1;
        } 
      } else {
        k = j;
      } 
      if (!TextUtils.isEmpty(str1) && str1.equals(paramString)) {
        arrayList.add(Integer.valueOf(b));
        if (paramArrayList != null)
          paramArrayList.add(Integer.valueOf(j)); 
      } 
      b++;
    } 
    return arrayList;
  }
  
  public final void addObserver(DataBufferObserver paramDataBufferObserver) {
    this.zzof.addObserver(paramDataBufferObserver);
  }
  
  public final void clearFilters() {
    if (!this.zzof.hasObservers()) {
      this.zzoe.clear();
      return;
    } 
    int i = this.zzoe.size();
    Integer[] arrayOfInteger = new Integer[i];
    arrayOfInteger = (Integer[])this.zzoe.toArray((Object[])arrayOfInteger);
    Arrays.sort((Object[])arrayOfInteger);
    byte b1 = 0;
    byte b2 = 0;
    int j = b2;
    while (true) {
      int k;
      if (b1 < i) {
        k = arrayOfInteger[b1].intValue();
        this.zzoe.remove(Integer.valueOf(k));
        if (b2) {
          if (k == j + b2) {
            b2++;
          } else {
            this.zzof.onDataRangeRemoved(j, b2);
            j = k;
            b2 = 1;
          } 
          b1++;
          continue;
        } 
      } else {
        break;
      } 
      j = k;
      b2 = 1;
    } 
    if (b2 > 0)
      this.zzof.onDataRangeRemoved(j, b2); 
  }
  
  public final int computeRealPosition(int paramInt) {
    if (this.zzoe.isEmpty())
      return paramInt; 
    if (paramInt < 0 || paramInt >= getCount()) {
      StringBuilder stringBuilder = new StringBuilder(53);
      stringBuilder.append("Position ");
      stringBuilder.append(paramInt);
      stringBuilder.append(" is out of bounds for this buffer");
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    int i = this.mDataBuffer.getCount();
    byte b = 0;
    int j;
    for (j = 0; b < i; j = k) {
      int k = j;
      if (!this.zzoe.contains(Integer.valueOf(b))) {
        if (j == paramInt)
          return b; 
        k = j + 1;
      } 
      b++;
    } 
    return -1;
  }
  
  public final void filterOut(String paramString) {
    if (TextUtils.isEmpty(paramString))
      return; 
    ArrayList<Integer> arrayList2 = null;
    if (this.zzof.hasObservers())
      arrayList2 = new ArrayList(); 
    ArrayList<Integer> arrayList1 = zza(paramString, arrayList2);
    if (this.zzof.hasObservers()) {
      Object object1;
      Object object2;
      int i = arrayList1.size() - 1;
      boolean bool1 = false;
      boolean bool2 = bool1;
      while (true) {
        int j;
        if (i >= 0) {
          boolean bool3;
          j = ((Integer)arrayList2.get(i)).intValue();
          if (j < 0) {
            bool3 = true;
          } else {
            bool3 = false;
          } 
          Object object3 = object1;
          Object object4 = object2;
          if (!bool3) {
            int m = ((Integer)arrayList1.get(i)).intValue();
            this.zzoe.add(Integer.valueOf(m));
            if (object1 != null) {
              if (j == object2 - 1) {
                int n = object2 - 1;
                m = object1 + 1;
              } else {
                this.zzof.onDataRangeRemoved(object2, object1);
                m = 1;
                int n = j;
              } 
              continue;
            } 
          } else {
            continue;
          } 
        } else {
          break;
        } 
        boolean bool = true;
        int k = j;
        i--;
        object1 = SYNTHETIC_LOCAL_VARIABLE_8;
        object2 = SYNTHETIC_LOCAL_VARIABLE_9;
      } 
      if (object1 > null)
        this.zzof.onDataRangeRemoved(object2, object1); 
      return;
    } 
    this.zzoe.addAll(arrayList1);
  }
  
  public final int getCount() {
    return this.mDataBuffer.getCount() - this.zzoe.size();
  }
  
  public final void release() {
    super.release();
    this.zzof.clear();
  }
  
  public final void removeObserver(DataBufferObserver paramDataBufferObserver) {
    this.zzof.removeObserver(paramDataBufferObserver);
  }
  
  public final void unfilter(String paramString) {
    if (TextUtils.isEmpty(paramString))
      return; 
    ArrayList<Integer> arrayList2 = null;
    if (this.zzof.hasObservers())
      arrayList2 = new ArrayList(); 
    ArrayList<Integer> arrayList1 = zza(paramString, arrayList2);
    if (this.zzof.hasObservers()) {
      Object object1;
      Object object2;
      int i = arrayList1.size() - 1;
      boolean bool1 = false;
      boolean bool2 = bool1;
      while (true) {
        if (i >= 0) {
          boolean bool3;
          int j = ((Integer)arrayList2.get(i)).intValue();
          if (j < 0) {
            bool3 = true;
          } else {
            bool3 = false;
          } 
          Object object3 = object1;
          Object object4 = object2;
          if (bool3) {
            int k = ((Integer)arrayList1.get(i)).intValue();
            this.zzoe.remove(Integer.valueOf(k));
            int m = -j - 1;
            if (object1 != null) {
              if (m == object2) {
                k = object1 + 1;
                Object object = object2;
              } else {
                this.zzof.onDataRangeInserted(object2, object1);
                k = 1;
              } 
              continue;
            } 
          } else {
            continue;
          } 
        } else {
          break;
        } 
        boolean bool = true;
        i--;
        object1 = SYNTHETIC_LOCAL_VARIABLE_8;
        object2 = SYNTHETIC_LOCAL_VARIABLE_9;
      } 
      if (object1 > null)
        this.zzof.onDataRangeInserted(object2, object1); 
      return;
    } 
    this.zzoe.removeAll(arrayList1);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\ExclusionFilteredDataBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */