package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

final class zzal implements BaseGmsClient.ConnectionProgressReportCallbacks {
  private final Api<?> mApi;
  
  private final boolean zzfo;
  
  private final WeakReference<zzaj> zzhw;
  
  public zzal(zzaj paramzzaj, Api<?> paramApi, boolean paramBoolean) {
    this.zzhw = new WeakReference<>(paramzzaj);
    this.mApi = paramApi;
    this.zzfo = paramBoolean;
  }
  
  public final void onReportServiceBinding(@NonNull ConnectionResult paramConnectionResult) {
    boolean bool;
    zzaj zzaj = this.zzhw.get();
    if (zzaj == null)
      return; 
    if (Looper.myLooper() == (zzaj.zzd(zzaj)).zzfq.getLooper()) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkState(bool, "onReportServiceBinding must be called on the GoogleApiClient handler thread");
    zzaj.zzc(zzaj).lock();
    try {
      bool = zzaj.zza(zzaj, 0);
      if (bool) {
        if (!paramConnectionResult.isSuccess())
          zzaj.zza(zzaj, paramConnectionResult, this.mApi, this.zzfo); 
        if (zzaj.zzk(zzaj))
          zzaj.zzj(zzaj); 
      } 
      return;
    } finally {
      zzaj.zzc(zzaj).unlock();
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */