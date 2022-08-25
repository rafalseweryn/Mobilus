package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.common.R;
import javax.annotation.Nullable;

public class StringResourceValueReader {
  private final Resources zzvb;
  
  private final String zzvc;
  
  public StringResourceValueReader(Context paramContext) {
    Preconditions.checkNotNull(paramContext);
    this.zzvb = paramContext.getResources();
    this.zzvc = this.zzvb.getResourcePackageName(R.string.common_google_play_services_unknown_issue);
  }
  
  @Nullable
  public String getString(String paramString) {
    int i = this.zzvb.getIdentifier(paramString, "string", this.zzvc);
    return (i == 0) ? null : this.zzvb.getString(i);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\StringResourceValueReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */