package com.google.android.gms.common.api.internal;

import android.support.annotation.BinderThread;
import com.google.android.gms.signin.internal.BaseSignInCallbacks;
import com.google.android.gms.signin.internal.SignInResponse;
import java.lang.ref.WeakReference;

final class zzaq extends BaseSignInCallbacks {
  private final WeakReference<zzaj> zzhw;
  
  zzaq(zzaj paramzzaj) {
    this.zzhw = new WeakReference<>(paramzzaj);
  }
  
  @BinderThread
  public final void onSignInComplete(SignInResponse paramSignInResponse) {
    zzaj zzaj = this.zzhw.get();
    if (zzaj == null)
      return; 
    zzaj.zzd(zzaj).zza(new zzar(this, zzaj, zzaj, paramSignInResponse));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzaq.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */