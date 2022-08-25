package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

public class DataBufferRef {
  protected final DataHolder mDataHolder;
  
  protected int mDataRow;
  
  private int zznj;
  
  public DataBufferRef(DataHolder paramDataHolder, int paramInt) {
    this.mDataHolder = (DataHolder)Preconditions.checkNotNull(paramDataHolder);
    setDataRow(paramInt);
  }
  
  protected void copyToBuffer(String paramString, CharArrayBuffer paramCharArrayBuffer) {
    this.mDataHolder.copyToBuffer(paramString, this.mDataRow, this.zznj, paramCharArrayBuffer);
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject instanceof DataBufferRef) {
      paramObject = paramObject;
      if (Objects.equal(Integer.valueOf(((DataBufferRef)paramObject).mDataRow), Integer.valueOf(this.mDataRow)) && Objects.equal(Integer.valueOf(((DataBufferRef)paramObject).zznj), Integer.valueOf(this.zznj)) && ((DataBufferRef)paramObject).mDataHolder == this.mDataHolder)
        return true; 
    } 
    return false;
  }
  
  protected boolean getBoolean(String paramString) {
    return this.mDataHolder.getBoolean(paramString, this.mDataRow, this.zznj);
  }
  
  protected byte[] getByteArray(String paramString) {
    return this.mDataHolder.getByteArray(paramString, this.mDataRow, this.zznj);
  }
  
  protected int getDataRow() {
    return this.mDataRow;
  }
  
  protected double getDouble(String paramString) {
    return this.mDataHolder.getDouble(paramString, this.mDataRow, this.zznj);
  }
  
  protected float getFloat(String paramString) {
    return this.mDataHolder.getFloat(paramString, this.mDataRow, this.zznj);
  }
  
  protected int getInteger(String paramString) {
    return this.mDataHolder.getInteger(paramString, this.mDataRow, this.zznj);
  }
  
  protected long getLong(String paramString) {
    return this.mDataHolder.getLong(paramString, this.mDataRow, this.zznj);
  }
  
  protected String getString(String paramString) {
    return this.mDataHolder.getString(paramString, this.mDataRow, this.zznj);
  }
  
  public boolean hasColumn(String paramString) {
    return this.mDataHolder.hasColumn(paramString);
  }
  
  protected boolean hasNull(String paramString) {
    return this.mDataHolder.hasNull(paramString, this.mDataRow, this.zznj);
  }
  
  public int hashCode() {
    return Objects.hashCode(new Object[] { Integer.valueOf(this.mDataRow), Integer.valueOf(this.zznj), this.mDataHolder });
  }
  
  public boolean isDataValid() {
    return !this.mDataHolder.isClosed();
  }
  
  protected Uri parseUri(String paramString) {
    return this.mDataHolder.parseUri(paramString, this.mDataRow, this.zznj);
  }
  
  protected void setDataRow(int paramInt) {
    boolean bool;
    if (paramInt >= 0 && paramInt < this.mDataHolder.getCount()) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkState(bool);
    this.mDataRow = paramInt;
    this.zznj = this.mDataHolder.getWindowIndex(this.mDataRow);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\DataBufferRef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */