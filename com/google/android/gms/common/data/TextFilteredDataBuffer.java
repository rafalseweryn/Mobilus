package com.google.android.gms.common.data;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Locale;

public final class TextFilteredDataBuffer<T> extends FilteredDataBuffer<T> implements TextFilterable {
  private final ArrayList<Integer> zzob = new ArrayList<>();
  
  private AbstractDataBuffer<T> zzoc;
  
  private final String zzoo;
  
  private String zzop;
  
  private TextFilterable.StringFilter zzoq;
  
  private Locale zzor;
  
  public TextFilteredDataBuffer(AbstractDataBuffer<T> paramAbstractDataBuffer, String paramString) {
    super(paramAbstractDataBuffer);
    this.zzoc = paramAbstractDataBuffer;
    this.zzoo = paramString;
  }
  
  private final String zzh(String paramString) {
    paramString = paramString.toLowerCase(this.zzor);
    StringBuilder stringBuilder = new StringBuilder();
    int i = paramString.length();
    for (byte b = 0; b < i; b++) {
      if (!Character.isIdentifierIgnorable(paramString.charAt(b)))
        stringBuilder.append(paramString.charAt(b)); 
    } 
    return stringBuilder.toString();
  }
  
  public final int computeRealPosition(int paramInt) {
    if (TextUtils.isEmpty(this.zzop))
      return paramInt; 
    if (paramInt < 0 || paramInt >= this.zzob.size()) {
      StringBuilder stringBuilder = new StringBuilder(53);
      stringBuilder.append("Position ");
      stringBuilder.append(paramInt);
      stringBuilder.append(" is out of bounds for this buffer");
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    return ((Integer)this.zzob.get(paramInt)).intValue();
  }
  
  public final int getCount() {
    return TextUtils.isEmpty(this.zzop) ? this.mDataBuffer.getCount() : this.zzob.size();
  }
  
  public final void setFilterTerm(Context paramContext, TextFilterable.StringFilter paramStringFilter, String paramString) {
    Preconditions.checkNotNull(paramStringFilter);
    this.zzop = paramString;
    this.zzoq = paramStringFilter;
    if (!TextUtils.isEmpty(this.zzop)) {
      this.zzor = (paramContext.getResources().getConfiguration()).locale;
      this.zzop = zzh(this.zzop);
      this.zzob.clear();
      DataHolder dataHolder = this.zzoc.mDataHolder;
      String str = this.zzoo;
      boolean bool = this.zzoc instanceof EntityBuffer;
      byte b = 0;
      int i = this.zzoc.getCount();
      while (b < i) {
        byte b1;
        if (bool) {
          b1 = ((EntityBuffer)this.zzoc).zzi(b);
        } else {
          b1 = b;
        } 
        paramString = dataHolder.getString(str, b1, dataHolder.getWindowIndex(b1));
        if (!TextUtils.isEmpty(paramString) && this.zzoq.matches(zzh(paramString), this.zzop))
          this.zzob.add(Integer.valueOf(b)); 
        b++;
      } 
      return;
    } 
    this.zzob.clear();
  }
  
  @VisibleForTesting
  public final void setFilterTerm(Context paramContext, String paramString) {
    setFilterTerm(paramContext, CONTAINS, paramString);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\TextFilteredDataBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */