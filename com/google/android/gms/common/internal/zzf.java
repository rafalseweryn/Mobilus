package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzf implements BaseGmsClient.BaseConnectionCallbacks {
  zzf(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {}
  
  public final void onConnected(@Nullable Bundle paramBundle) {
    this.zztd.onConnected(paramBundle);
  }
  
  public final void onConnectionSuspended(int paramInt) {
    this.zztd.onConnectionSuspended(paramInt);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\zzf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */