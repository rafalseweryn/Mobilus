package com.google.android.gms.common.data;

import java.util.ArrayList;
import java.util.HashSet;

public final class PositionFilteredDataBuffer<T> extends FilteredDataBuffer<T> {
  private final ArrayList<Integer> zzob = new ArrayList<>();
  
  private final HashSet<Integer> zzoe = new HashSet<>();
  
  public PositionFilteredDataBuffer(AbstractDataBuffer<T> paramAbstractDataBuffer) {
    super(paramAbstractDataBuffer);
    zzcl();
  }
  
  private final void zzcl() {
    this.zzob.clear();
    int i = this.mDataBuffer.getCount();
    for (byte b = 0; b < i; b++) {
      if (!this.zzoe.contains(Integer.valueOf(b)))
        this.zzob.add(Integer.valueOf(b)); 
    } 
  }
  
  public final void clearFilters() {
    this.zzoe.clear();
    zzcl();
  }
  
  public final int computeRealPosition(int paramInt) {
    if (paramInt < 0 || paramInt >= getCount()) {
      StringBuilder stringBuilder = new StringBuilder(53);
      stringBuilder.append("Position ");
      stringBuilder.append(paramInt);
      stringBuilder.append(" is out of bounds for this buffer");
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    return ((Integer)this.zzob.get(paramInt)).intValue();
  }
  
  public final void filterOut(int paramInt) {
    if (paramInt >= 0 && paramInt <= this.mDataBuffer.getCount()) {
      this.zzoe.add(Integer.valueOf(paramInt));
      zzcl();
    } 
  }
  
  public final int getCount() {
    return this.mDataBuffer.getCount() - this.zzoe.size();
  }
  
  public final void unfilter(int paramInt) {
    this.zzoe.remove(Integer.valueOf(paramInt));
    zzcl();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\PositionFilteredDataBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */