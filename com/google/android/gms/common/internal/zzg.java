package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzg implements BaseGmsClient.BaseOnConnectionFailedListener {
  zzg(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {}
  
  public final void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult) {
    this.zzte.onConnectionFailed(paramConnectionResult);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\zzg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */