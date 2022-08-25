package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;

public interface zzbc {
  void begin();
  
  void connect();
  
  boolean disconnect();
  
  <A extends Api.AnyClient, R extends com.google.android.gms.common.api.Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T paramT);
  
  <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends com.google.android.gms.common.api.Result, A>> T execute(T paramT);
  
  void onConnected(Bundle paramBundle);
  
  void onConnectionSuspended(int paramInt);
  
  void zza(ConnectionResult paramConnectionResult, Api<?> paramApi, boolean paramBoolean);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzbc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */