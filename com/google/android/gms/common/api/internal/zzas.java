package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.signin.internal.ISignInCallbacks;

final class zzas implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
  private zzas(zzaj paramzzaj) {}
  
  public final void onConnected(Bundle paramBundle) {
    zzaj.zzf(this.zzhv).signIn((ISignInCallbacks)new zzaq(this.zzhv));
  }
  
  public final void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult) {
    zzaj.zzc(this.zzhv).lock();
    try {
      if (zzaj.zzb(this.zzhv, paramConnectionResult)) {
        zzaj.zzi(this.zzhv);
        zzaj.zzj(this.zzhv);
      } else {
        zzaj.zza(this.zzhv, paramConnectionResult);
      } 
      return;
    } finally {
      zzaj.zzc(this.zzhv).unlock();
    } 
  }
  
  public final void onConnectionSuspended(int paramInt) {}
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */