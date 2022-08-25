package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcf extends TaskApiCall<A, ResultT> {
  zzcf(TaskApiCall.Builder paramBuilder, Feature[] paramArrayOfFeature, boolean paramBoolean) {
    super(paramArrayOfFeature, paramBoolean, null);
  }
  
  protected final void doExecute(A paramA, TaskCompletionSource<ResultT> paramTaskCompletionSource) throws RemoteException {
    TaskApiCall.Builder.zza(this.zzmc).accept(paramA, paramTaskCompletionSource);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzcf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */