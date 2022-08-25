package com.google.android.gms.common.api.internal;

final class zzbh implements BackgroundDetector.BackgroundStateChangeListener {
  zzbh(GoogleApiManager paramGoogleApiManager) {}
  
  public final void onBackgroundStateChanged(boolean paramBoolean) {
    GoogleApiManager.zza(this.zzjy).sendMessage(GoogleApiManager.zza(this.zzjy).obtainMessage(1, Boolean.valueOf(paramBoolean)));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzbh.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */