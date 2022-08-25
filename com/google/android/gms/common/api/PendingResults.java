package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.api.internal.OptionalPendingResultImpl;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
public final class PendingResults {
  public static PendingResult<Status> canceledPendingResult() {
    StatusPendingResult statusPendingResult = new StatusPendingResult(Looper.getMainLooper());
    statusPendingResult.cancel();
    return (PendingResult<Status>)statusPendingResult;
  }
  
  public static <R extends Result> PendingResult<R> canceledPendingResult(R paramR) {
    boolean bool;
    Preconditions.checkNotNull(paramR, "Result must not be null");
    if (paramR.getStatus().getStatusCode() == 16) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "Status code must be CommonStatusCodes.CANCELED");
    zza<R> zza = new zza<>(paramR);
    zza.cancel();
    return (PendingResult<R>)zza;
  }
  
  @KeepForSdk
  public static <R extends Result> PendingResult<R> immediateFailedResult(R paramR, GoogleApiClient paramGoogleApiClient) {
    Preconditions.checkNotNull(paramR, "Result must not be null");
    Preconditions.checkArgument(paramR.getStatus().isSuccess() ^ true, "Status code must not be SUCCESS");
    zzb<R> zzb = new zzb<>(paramGoogleApiClient, paramR);
    zzb.setResult((Result)paramR);
    return (PendingResult<R>)zzb;
  }
  
  @KeepForSdk
  public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R paramR) {
    Preconditions.checkNotNull(paramR, "Result must not be null");
    zzc<Result> zzc = new zzc<>(null);
    zzc.setResult((Result)paramR);
    return (OptionalPendingResult<R>)new OptionalPendingResultImpl((PendingResult)zzc);
  }
  
  @KeepForSdk
  public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R paramR, GoogleApiClient paramGoogleApiClient) {
    Preconditions.checkNotNull(paramR, "Result must not be null");
    zzc<Result> zzc = new zzc<>(paramGoogleApiClient);
    zzc.setResult((Result)paramR);
    return (OptionalPendingResult<R>)new OptionalPendingResultImpl((PendingResult)zzc);
  }
  
  @KeepForSdk
  public static PendingResult<Status> immediatePendingResult(Status paramStatus) {
    Preconditions.checkNotNull(paramStatus, "Result must not be null");
    StatusPendingResult statusPendingResult = new StatusPendingResult(Looper.getMainLooper());
    statusPendingResult.setResult(paramStatus);
    return (PendingResult<Status>)statusPendingResult;
  }
  
  @KeepForSdk
  public static PendingResult<Status> immediatePendingResult(Status paramStatus, GoogleApiClient paramGoogleApiClient) {
    Preconditions.checkNotNull(paramStatus, "Result must not be null");
    StatusPendingResult statusPendingResult = new StatusPendingResult(paramGoogleApiClient);
    statusPendingResult.setResult(paramStatus);
    return (PendingResult<Status>)statusPendingResult;
  }
  
  private static final class zza<R extends Result> extends BasePendingResult<R> {
    private final R zzdl;
    
    public zza(R param1R) {
      super(Looper.getMainLooper());
      this.zzdl = param1R;
    }
    
    protected final R createFailedResult(Status param1Status) {
      if (param1Status.getStatusCode() != this.zzdl.getStatus().getStatusCode())
        throw new UnsupportedOperationException("Creating failed results is not supported"); 
      return this.zzdl;
    }
  }
  
  private static final class zzb<R extends Result> extends BasePendingResult<R> {
    private final R zzdm;
    
    public zzb(GoogleApiClient param1GoogleApiClient, R param1R) {
      super(param1GoogleApiClient);
      this.zzdm = param1R;
    }
    
    protected final R createFailedResult(Status param1Status) {
      return this.zzdm;
    }
  }
  
  private static final class zzc<R extends Result> extends BasePendingResult<R> {
    public zzc(GoogleApiClient param1GoogleApiClient) {
      super(param1GoogleApiClient);
    }
    
    protected final R createFailedResult(Status param1Status) {
      throw new UnsupportedOperationException("Creating failed results is not supported");
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\PendingResults.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */