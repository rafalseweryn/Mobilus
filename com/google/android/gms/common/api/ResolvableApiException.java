package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender;
import android.support.annotation.NonNull;

public class ResolvableApiException extends ApiException {
  public ResolvableApiException(@NonNull Status paramStatus) {
    super(paramStatus);
  }
  
  public PendingIntent getResolution() {
    return this.mStatus.getResolution();
  }
  
  public void startResolutionForResult(Activity paramActivity, int paramInt) throws IntentSender.SendIntentException {
    this.mStatus.startResolutionForResult(paramActivity, paramInt);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\ResolvableApiException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */