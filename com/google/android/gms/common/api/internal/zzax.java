package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.concurrent.atomic.AtomicReference;

final class zzax implements GoogleApiClient.ConnectionCallbacks {
  zzax(zzav paramzzav, AtomicReference paramAtomicReference, StatusPendingResult paramStatusPendingResult) {}
  
  public final void onConnected(Bundle paramBundle) {
    zzav.zza(this.zzit, this.zziu.get(), this.zziv, true);
  }
  
  public final void onConnectionSuspended(int paramInt) {}
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzax.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */