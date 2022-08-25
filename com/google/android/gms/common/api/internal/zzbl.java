package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.internal.BaseGmsClient;

final class zzbl implements BaseGmsClient.SignOutCallbacks {
  zzbl(GoogleApiManager.zza paramzza) {}
  
  public final void onSignOutComplete() {
    GoogleApiManager.zza(this.zzkk.zzjy).post(new zzbm(this));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzbl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */