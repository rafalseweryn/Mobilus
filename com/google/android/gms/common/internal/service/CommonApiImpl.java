package com.google.android.gms.common.internal.service;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

public final class CommonApiImpl implements CommonApi {
  public final PendingResult<Status> clearDefaultAccount(GoogleApiClient paramGoogleApiClient) {
    return (PendingResult<Status>)paramGoogleApiClient.execute(new zzb(this, paramGoogleApiClient));
  }
  
  private static final class zza extends BaseCommonServiceCallbacks {
    private final BaseImplementation.ResultHolder<Status> mResultHolder;
    
    public zza(BaseImplementation.ResultHolder<Status> param1ResultHolder) {
      this.mResultHolder = param1ResultHolder;
    }
    
    public final void onDefaultAccountCleared(int param1Int) throws RemoteException {
      this.mResultHolder.setResult(new Status(param1Int));
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\service\CommonApiImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */