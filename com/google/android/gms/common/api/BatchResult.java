package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.TimeUnit;

public final class BatchResult implements Result {
  private final Status mStatus;
  
  private final PendingResult<?>[] zzcg;
  
  BatchResult(Status paramStatus, PendingResult<?>[] paramArrayOfPendingResult) {
    this.mStatus = paramStatus;
    this.zzcg = paramArrayOfPendingResult;
  }
  
  public final Status getStatus() {
    return this.mStatus;
  }
  
  public final <R extends Result> R take(BatchResultToken<R> paramBatchResultToken) {
    boolean bool;
    if (paramBatchResultToken.mId < this.zzcg.length) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "The result token does not belong to this batch");
    return (R)this.zzcg[paramBatchResultToken.mId].await(0L, TimeUnit.MILLISECONDS);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\BatchResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */