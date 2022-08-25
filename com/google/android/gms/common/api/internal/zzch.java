package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import javax.annotation.concurrent.GuardedBy;

public final class zzch<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
  private final Object zzfa;
  
  private final WeakReference<GoogleApiClient> zzfc;
  
  private ResultTransform<? super R, ? extends Result> zzmd;
  
  private zzch<? extends Result> zzme;
  
  private volatile ResultCallbacks<? super R> zzmf;
  
  private PendingResult<R> zzmg;
  
  private Status zzmh;
  
  private final zzcj zzmi;
  
  private boolean zzmj;
  
  public zzch(WeakReference<GoogleApiClient> paramWeakReference) {
    Looper looper;
    this.zzmd = null;
    this.zzme = null;
    this.zzmf = null;
    this.zzmg = null;
    this.zzfa = new Object();
    this.zzmh = null;
    this.zzmj = false;
    Preconditions.checkNotNull(paramWeakReference, "GoogleApiClient reference must not be null");
    this.zzfc = paramWeakReference;
    GoogleApiClient googleApiClient = this.zzfc.get();
    if (googleApiClient != null) {
      looper = googleApiClient.getLooper();
    } else {
      looper = Looper.getMainLooper();
    } 
    this.zzmi = new zzcj(this, looper);
  }
  
  private static void zzb(Result paramResult) {
    if (paramResult instanceof Releasable)
      try {
        ((Releasable)paramResult).release();
        return;
      } catch (RuntimeException runtimeException) {
        String str = String.valueOf(paramResult);
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 18);
        stringBuilder.append("Unable to release ");
        stringBuilder.append(str);
        Log.w("TransformedResultImpl", stringBuilder.toString(), runtimeException);
      }  
  }
  
  @GuardedBy("mSyncToken")
  private final void zzcb() {
    if (this.zzmd == null && this.zzmf == null)
      return; 
    GoogleApiClient googleApiClient = this.zzfc.get();
    if (!this.zzmj && this.zzmd != null && googleApiClient != null) {
      googleApiClient.zza(this);
      this.zzmj = true;
    } 
    if (this.zzmh != null) {
      zze(this.zzmh);
      return;
    } 
    if (this.zzmg != null)
      this.zzmg.setResultCallback(this); 
  }
  
  @GuardedBy("mSyncToken")
  private final boolean zzcd() {
    GoogleApiClient googleApiClient = this.zzfc.get();
    return (this.zzmf != null && googleApiClient != null);
  }
  
  private final void zzd(Status paramStatus) {
    synchronized (this.zzfa) {
      this.zzmh = paramStatus;
      zze(this.zzmh);
      return;
    } 
  }
  
  private final void zze(Status paramStatus) {
    synchronized (this.zzfa) {
      if (this.zzmd != null) {
        paramStatus = this.zzmd.onFailure(paramStatus);
        Preconditions.checkNotNull(paramStatus, "onFailure must not return null");
        this.zzme.zzd(paramStatus);
      } else if (zzcd()) {
        this.zzmf.onFailure(paramStatus);
      } 
      return;
    } 
  }
  
  public final void andFinally(@NonNull ResultCallbacks<? super R> paramResultCallbacks) {
    synchronized (this.zzfa) {
      ResultCallbacks<? super R> resultCallbacks = this.zzmf;
      boolean bool1 = false;
      if (resultCallbacks == null) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      Preconditions.checkState(bool2, "Cannot call andFinally() twice.");
      boolean bool2 = bool1;
      if (this.zzmd == null)
        bool2 = true; 
      Preconditions.checkState(bool2, "Cannot call then() and andFinally() on the same TransformedResult.");
      this.zzmf = paramResultCallbacks;
      zzcb();
      return;
    } 
  }
  
  public final void onResult(R paramR) {
    synchronized (this.zzfa) {
      if (paramR.getStatus().isSuccess()) {
        if (this.zzmd != null) {
          ExecutorService executorService = zzbw.zzbe();
          zzci zzci = new zzci();
          this(this, (Result)paramR);
          executorService.submit(zzci);
        } else if (zzcd()) {
          this.zzmf.onSuccess((Result)paramR);
        } 
      } else {
        zzd(paramR.getStatus());
        zzb((Result)paramR);
      } 
      return;
    } 
  }
  
  @NonNull
  public final <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> paramResultTransform) {
    synchronized (this.zzfa) {
      ResultTransform<? super R, ? extends Result> resultTransform = this.zzmd;
      boolean bool1 = false;
      if (resultTransform == null) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      Preconditions.checkState(bool2, "Cannot call then() twice.");
      boolean bool2 = bool1;
      if (this.zzmf == null)
        bool2 = true; 
      Preconditions.checkState(bool2, "Cannot call then() and andFinally() on the same TransformedResult.");
      this.zzmd = paramResultTransform;
      zzch<? extends Result> zzch1 = new zzch();
      this(this.zzfc);
      this.zzme = zzch1;
      zzcb();
      return (TransformedResult)zzch1;
    } 
  }
  
  public final void zza(PendingResult<?> paramPendingResult) {
    synchronized (this.zzfa) {
      this.zzmg = (PendingResult)paramPendingResult;
      zzcb();
      return;
    } 
  }
  
  final void zzcc() {
    this.zzmf = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */