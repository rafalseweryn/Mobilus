package com.google.android.gms.common.api.internal;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiActivity;

final class zzm implements Runnable {
  private final zzl zzev;
  
  zzm(zzk paramzzk, zzl paramzzl) {
    this.zzev = paramzzl;
  }
  
  @MainThread
  public final void run() {
    Dialog dialog;
    if (!this.zzew.mStarted)
      return; 
    ConnectionResult connectionResult = this.zzev.getConnectionResult();
    if (connectionResult.hasResolution()) {
      this.zzew.mLifecycleFragment.startActivityForResult(GoogleApiActivity.zza((Context)this.zzew.getActivity(), connectionResult.getResolution(), this.zzev.zzu(), false), 1);
      return;
    } 
    if (this.zzew.zzdg.isUserResolvableError(connectionResult.getErrorCode())) {
      this.zzew.zzdg.showErrorDialogFragment(this.zzew.getActivity(), this.zzew.mLifecycleFragment, connectionResult.getErrorCode(), 2, this.zzew);
      return;
    } 
    if (connectionResult.getErrorCode() == 18) {
      dialog = this.zzew.zzdg.showUpdatingDialog(this.zzew.getActivity(), this.zzew);
      this.zzew.zzdg.registerCallbackOnUpdate(this.zzew.getActivity().getApplicationContext(), new zzn(this, dialog));
      return;
    } 
    this.zzew.zza((ConnectionResult)dialog, this.zzev.zzu());
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */