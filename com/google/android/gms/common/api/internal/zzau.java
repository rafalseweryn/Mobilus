package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import java.util.Collections;
import java.util.Iterator;

public final class zzau implements zzbc {
  private final zzbd zzhf;
  
  public zzau(zzbd paramzzbd) {
    this.zzhf = paramzzbd;
  }
  
  public final void begin() {
    Iterator<Api.Client> iterator = this.zzhf.zzil.values().iterator();
    while (iterator.hasNext())
      ((Api.Client)iterator.next()).disconnect(); 
    this.zzhf.zzfq.zzim = Collections.emptySet();
  }
  
  public final void connect() {
    this.zzhf.zzbc();
  }
  
  public final boolean disconnect() {
    return true;
  }
  
  public final <A extends Api.AnyClient, R extends com.google.android.gms.common.api.Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T paramT) {
    this.zzhf.zzfq.zzgo.add((BaseImplementation.ApiMethodImpl<?, ?>)paramT);
    return paramT;
  }
  
  public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends com.google.android.gms.common.api.Result, A>> T execute(T paramT) {
    throw new IllegalStateException("GoogleApiClient is not connected yet.");
  }
  
  public final void onConnected(Bundle paramBundle) {}
  
  public final void onConnectionSuspended(int paramInt) {}
  
  public final void zza(ConnectionResult paramConnectionResult, Api<?> paramApi, boolean paramBoolean) {}
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzau.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */