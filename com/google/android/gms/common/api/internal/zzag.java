package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import java.util.Iterator;

public final class zzag implements zzbc {
  private final zzbd zzhf;
  
  private boolean zzhg = false;
  
  public zzag(zzbd paramzzbd) {
    this.zzhf = paramzzbd;
  }
  
  public final void begin() {}
  
  public final void connect() {
    if (this.zzhg) {
      this.zzhg = false;
      this.zzhf.zza(new zzai(this, this));
    } 
  }
  
  public final boolean disconnect() {
    if (this.zzhg)
      return false; 
    if (this.zzhf.zzfq.zzba()) {
      this.zzhg = true;
      Iterator<zzch> iterator = this.zzhf.zzfq.zziq.iterator();
      while (iterator.hasNext())
        ((zzch)iterator.next()).zzcc(); 
      return false;
    } 
    this.zzhf.zzf(null);
    return true;
  }
  
  public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T paramT) {
    return (T)execute((BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient>)paramT);
  }
  
  public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T paramT) {
    try {
      Api.SimpleClient simpleClient;
      this.zzhf.zzfq.zzir.zzb((BasePendingResult<? extends Result>)paramT);
      zzav zzav = this.zzhf.zzfq;
      Api.AnyClientKey anyClientKey = paramT.getClientKey();
      Api.Client client2 = zzav.zzil.get(anyClientKey);
      Preconditions.checkNotNull(client2, "Appropriate Api was not requested.");
      if (!client2.isConnected() && this.zzhf.zzjb.containsKey(paramT.getClientKey())) {
        Status status = new Status();
        this(17);
        paramT.setFailedResult(status);
        return paramT;
      } 
      Api.Client client1 = client2;
      if (client2 instanceof SimpleClientAdapter)
        simpleClient = ((SimpleClientAdapter)client2).getClient(); 
      paramT.run(simpleClient);
      return paramT;
    } catch (DeadObjectException deadObjectException) {
      this.zzhf.zza(new zzah(this, this));
      return paramT;
    } 
  }
  
  public final void onConnected(Bundle paramBundle) {}
  
  public final void onConnectionSuspended(int paramInt) {
    this.zzhf.zzf(null);
    this.zzhf.zzjf.zzb(paramInt, this.zzhg);
  }
  
  public final void zza(ConnectionResult paramConnectionResult, Api<?> paramApi, boolean paramBoolean) {}
  
  final void zzap() {
    if (this.zzhg) {
      this.zzhg = false;
      this.zzhf.zzfq.zzir.release();
      disconnect();
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */