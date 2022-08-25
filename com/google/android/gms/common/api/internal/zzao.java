package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import javax.annotation.concurrent.GuardedBy;

final class zzao extends zzbe {
  zzao(zzam paramzzam, zzbc paramzzbc, BaseGmsClient.ConnectionProgressReportCallbacks paramConnectionProgressReportCallbacks) {
    super(paramzzbc);
  }
  
  @GuardedBy("mLock")
  public final void zzaq() {
    this.zzia.onReportServiceBinding(new ConnectionResult(16, null));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */