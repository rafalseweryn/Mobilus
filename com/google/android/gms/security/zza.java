package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

final class zza extends AsyncTask<Void, Void, Integer> {
  zza(Context paramContext) {}
  
  private final Integer zza(Void... paramVarArgs) {
    int i;
    try {
      ProviderInstaller.installIfNeeded(context);
      i = 0;
    } catch (GooglePlayServicesRepairableException googlePlayServicesRepairableException) {
      i = googlePlayServicesRepairableException.getConnectionStatusCode();
    } catch (GooglePlayServicesNotAvailableException googlePlayServicesNotAvailableException) {
      i = googlePlayServicesNotAvailableException.errorCode;
    } 
    return Integer.valueOf(i);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\security\zza.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */