package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Bundle;
import com.google.android.gms.common.util.VisibleForTesting;

public final class TransientDataHolder {
  private String zzos;
  
  private String zzot;
  
  private DataHolder.Builder zzou;
  
  public TransientDataHolder(String[] paramArrayOfString) {
    this(paramArrayOfString, null, null, null);
  }
  
  public TransientDataHolder(String[] paramArrayOfString, String paramString1, String paramString2, String paramString3) {
    DataHolder.Builder builder;
    this.zzos = paramString2;
    this.zzot = paramString3;
    if (paramString1 != null) {
      builder = DataHolder.builder(paramArrayOfString, paramString1);
    } else {
      builder = DataHolder.builder((String[])builder);
    } 
    this.zzou = builder;
  }
  
  public final void addRow(ContentValues paramContentValues) {
    this.zzou.withRow(paramContentValues);
  }
  
  @VisibleForTesting
  public final DataHolder build(int paramInt) {
    return build(paramInt, new Bundle(), -1);
  }
  
  public final DataHolder build(int paramInt1, Bundle paramBundle, int paramInt2) {
    paramBundle.putString("next_page_token", this.zzot);
    paramBundle.putString("prev_page_token", this.zzos);
    return this.zzou.build(paramInt1, paramBundle, paramInt2);
  }
  
  public final boolean containsRowWithValue(String paramString, Object paramObject) {
    return this.zzou.containsRowWithValue(paramString, paramObject);
  }
  
  public final int getCount() {
    return this.zzou.getCount();
  }
  
  public final String getNextToken() {
    return this.zzot;
  }
  
  public final String getPrevToken() {
    return this.zzos;
  }
  
  public final void modifyUniqueRowValue(Object paramObject1, String paramString, Object paramObject2) {
    this.zzou.modifyUniqueRowValue(paramObject1, paramString, paramObject2);
  }
  
  @VisibleForTesting
  public final void removeRows(String paramString, Object paramObject) {
    this.zzou.removeRowsWithValue(paramString, paramObject);
  }
  
  public final void setNextToken(String paramString) {
    this.zzot = paramString;
  }
  
  public final void setPrevToken(String paramString) {
    this.zzos = paramString;
  }
  
  public final void sortData(String paramString) {
    this.zzou.sort(paramString);
  }
  
  public final void sortDataDescending(String paramString) {
    this.zzou.descendingSort(paramString);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\TransientDataHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */