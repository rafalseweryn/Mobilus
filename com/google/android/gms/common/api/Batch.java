package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.BasePendingResult;
import java.util.ArrayList;
import java.util.List;

public final class Batch extends BasePendingResult<BatchResult> {
  private final Object mLock = new Object();
  
  private int zzcd;
  
  private boolean zzce;
  
  private boolean zzcf;
  
  private final PendingResult<?>[] zzcg;
  
  private Batch(List<PendingResult<?>> paramList, GoogleApiClient paramGoogleApiClient) {
    super(paramGoogleApiClient);
    this.zzcd = paramList.size();
    this.zzcg = (PendingResult<?>[])new PendingResult[this.zzcd];
    if (paramList.isEmpty()) {
      setResult(new BatchResult(Status.RESULT_SUCCESS, this.zzcg));
      return;
    } 
    for (byte b = 0; b < paramList.size(); b++) {
      PendingResult<?> pendingResult = paramList.get(b);
      this.zzcg[b] = pendingResult;
      pendingResult.addStatusListener(new zza(this));
    } 
  }
  
  public final void cancel() {
    super.cancel();
    PendingResult<?>[] arrayOfPendingResult = this.zzcg;
    int i = arrayOfPendingResult.length;
    for (byte b = 0; b < i; b++)
      arrayOfPendingResult[b].cancel(); 
  }
  
  public final BatchResult createFailedResult(Status paramStatus) {
    return new BatchResult(paramStatus, this.zzcg);
  }
  
  public static final class Builder {
    private List<PendingResult<?>> zzci = new ArrayList<>();
    
    private GoogleApiClient zzcj;
    
    public Builder(GoogleApiClient param1GoogleApiClient) {
      this.zzcj = param1GoogleApiClient;
    }
    
    public final <R extends Result> BatchResultToken<R> add(PendingResult<R> param1PendingResult) {
      BatchResultToken<Result> batchResultToken = new BatchResultToken<>(this.zzci.size());
      this.zzci.add(param1PendingResult);
      return (BatchResultToken)batchResultToken;
    }
    
    public final Batch build() {
      return new Batch(this.zzci, this.zzcj, null);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\Batch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */