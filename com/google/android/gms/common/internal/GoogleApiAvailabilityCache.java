package com.google.android.gms.common.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.SparseIntArray;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;

public class GoogleApiAvailabilityCache {
  private final SparseIntArray zzug = new SparseIntArray();
  
  private GoogleApiAvailabilityLight zzuh;
  
  public GoogleApiAvailabilityCache() {
    this((GoogleApiAvailabilityLight)GoogleApiAvailability.getInstance());
  }
  
  public GoogleApiAvailabilityCache(@NonNull GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight) {
    Preconditions.checkNotNull(paramGoogleApiAvailabilityLight);
    this.zzuh = paramGoogleApiAvailabilityLight;
  }
  
  public void flush() {
    this.zzug.clear();
  }
  
  public int getClientAvailability(@NonNull Context paramContext, @NonNull Api.Client paramClient) {
    int m;
    Preconditions.checkNotNull(paramContext);
    Preconditions.checkNotNull(paramClient);
    if (!paramClient.requiresGooglePlayServices())
      return 0; 
    int i = paramClient.getMinApkVersion();
    int j = this.zzug.get(i, -1);
    if (j != -1)
      return j; 
    int k = 0;
    while (true) {
      m = j;
      if (k < this.zzug.size()) {
        m = this.zzug.keyAt(k);
        if (m > i && this.zzug.get(m) == 0) {
          m = 0;
          break;
        } 
        k++;
        continue;
      } 
      break;
    } 
    k = m;
    if (m == -1)
      k = this.zzuh.isGooglePlayServicesAvailable(paramContext, i); 
    this.zzug.put(i, k);
    return k;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\GoogleApiAvailabilityCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */