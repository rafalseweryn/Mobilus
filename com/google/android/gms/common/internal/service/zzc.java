package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;

abstract class zzc<R extends Result> extends BaseImplementation.ApiMethodImpl<R, CommonClient> {
  public zzc(GoogleApiClient paramGoogleApiClient) {
    super(Common.API, paramGoogleApiClient);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\service\zzc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */