package com.google.android.gms.common.data;

import android.content.Context;

public interface TextFilterable {
  public static final StringFilter CONTAINS = new zzc();
  
  public static final StringFilter STARTS_WITH = new zzd();
  
  public static final StringFilter WORD_STARTS_WITH = new zze();
  
  void setFilterTerm(Context paramContext, StringFilter paramStringFilter, String paramString);
  
  void setFilterTerm(Context paramContext, String paramString);
  
  public static interface StringFilter {
    boolean matches(String param1String1, String param1String2);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\TextFilterable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */