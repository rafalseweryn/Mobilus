package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;

@VisibleForTesting(otherwise = 3)
@KeepForSdk
public class BaseImplementation {
  @KeepForSdk
  public static abstract class ApiMethodImpl<R extends Result, A extends Api.AnyClient> extends BasePendingResult<R> implements ResultHolder<R> {
    @KeepForSdk
    private final Api<?> mApi;
    
    @KeepForSdk
    private final Api.AnyClientKey<A> mClientKey;
    
    @Deprecated
    @KeepForSdk
    protected ApiMethodImpl(@NonNull Api.AnyClientKey<A> param1AnyClientKey, @NonNull GoogleApiClient param1GoogleApiClient) {
      super((GoogleApiClient)Preconditions.checkNotNull(param1GoogleApiClient, "GoogleApiClient must not be null"));
      this.mClientKey = (Api.AnyClientKey<A>)Preconditions.checkNotNull(param1AnyClientKey);
      this.mApi = null;
    }
    
    @KeepForSdk
    protected ApiMethodImpl(@NonNull Api<?> param1Api, @NonNull GoogleApiClient param1GoogleApiClient) {
      super((GoogleApiClient)Preconditions.checkNotNull(param1GoogleApiClient, "GoogleApiClient must not be null"));
      Preconditions.checkNotNull(param1Api, "Api must not be null");
      this.mClientKey = param1Api.getClientKey();
      this.mApi = param1Api;
    }
    
    @VisibleForTesting
    @KeepForSdk
    protected ApiMethodImpl(@NonNull BasePendingResult.CallbackHandler<R> param1CallbackHandler) {
      super(param1CallbackHandler);
      this.mClientKey = null;
      this.mApi = null;
    }
    
    @KeepForSdk
    private void setFailedResult(@NonNull RemoteException param1RemoteException) {
      setFailedResult(new Status(8, param1RemoteException.getLocalizedMessage(), null));
    }
    
    @KeepForSdk
    protected abstract void doExecute(@NonNull A param1A) throws RemoteException;
    
    @KeepForSdk
    public final Api<?> getApi() {
      return this.mApi;
    }
    
    @KeepForSdk
    public final Api.AnyClientKey<A> getClientKey() {
      return this.mClientKey;
    }
    
    @KeepForSdk
    protected void onSetFailedResult(@NonNull R param1R) {}
    
    @KeepForSdk
    public final void run(@NonNull A param1A) throws DeadObjectException {
      Api.SimpleClient simpleClient;
      A a = param1A;
      if (param1A instanceof SimpleClientAdapter)
        simpleClient = ((SimpleClientAdapter)param1A).getClient(); 
      try {
        doExecute((A)simpleClient);
        return;
      } catch (DeadObjectException deadObjectException) {
        setFailedResult((RemoteException)deadObjectException);
        throw deadObjectException;
      } catch (RemoteException remoteException) {
        setFailedResult(remoteException);
        return;
      } 
    }
    
    @KeepForSdk
    public final void setFailedResult(@NonNull Status param1Status) {
      Preconditions.checkArgument(param1Status.isSuccess() ^ true, "Failed result must not be success");
      param1Status = (Status)createFailedResult(param1Status);
      setResult((R)param1Status);
      onSetFailedResult((R)param1Status);
    }
  }
  
  @KeepForSdk
  public static interface ResultHolder<R> {
    @KeepForSdk
    void setFailedResult(Status param1Status);
    
    @KeepForSdk
    void setResult(R param1R);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\BaseImplementation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */