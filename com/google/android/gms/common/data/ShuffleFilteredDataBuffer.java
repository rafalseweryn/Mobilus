package com.google.android.gms.common.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ShuffleFilteredDataBuffer<T> extends FilteredDataBuffer<T> {
  private final List<Integer> zzoh;
  
  private final int zzoi;
  
  public ShuffleFilteredDataBuffer(DataBuffer<T> paramDataBuffer, int paramInt) {
    super(paramDataBuffer);
    this.zzoi = paramInt;
    int i = this.zzoi;
    int j = this.mDataBuffer.getCount();
    if (i > j)
      throw new IllegalArgumentException("numIndexes must be smaller or equal to max"); 
    ArrayList<Integer> arrayList = new ArrayList(j);
    for (paramInt = 0; paramInt < j; paramInt++)
      arrayList.add(Integer.valueOf(paramInt)); 
    Collections.shuffle(arrayList);
    this.zzoh = arrayList.subList(0, i);
  }
  
  public final int computeRealPosition(int paramInt) {
    if (paramInt < 0 || paramInt >= getCount()) {
      StringBuilder stringBuilder = new StringBuilder(53);
      stringBuilder.append("Position ");
      stringBuilder.append(paramInt);
      stringBuilder.append(" is out of bounds for this buffer");
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    return ((Integer)this.zzoh.get(paramInt)).intValue();
  }
  
  public final int getCount() {
    return Math.min(this.zzoi, this.mDataBuffer.getCount());
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\ShuffleFilteredDataBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */