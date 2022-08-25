package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.util.BiConsumer;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
public abstract class TaskApiCall<A extends Api.AnyClient, ResultT> {
  private final Feature[] zzlz = null;
  
  private final boolean zzma = false;
  
  @Deprecated
  @KeepForSdk
  public TaskApiCall() {}
  
  @KeepForSdk
  private TaskApiCall(Feature[] paramArrayOfFeature, boolean paramBoolean) {}
  
  @KeepForSdk
  public static <A extends Api.AnyClient, ResultT> Builder<A, ResultT> builder() {
    return new Builder<>(null);
  }
  
  @KeepForSdk
  protected abstract void doExecute(A paramA, TaskCompletionSource<ResultT> paramTaskCompletionSource) throws RemoteException;
  
  @KeepForSdk
  public boolean shouldAutoResolveMissingFeatures() {
    return this.zzma;
  }
  
  @Nullable
  public final Feature[] zzca() {
    return this.zzlz;
  }
  
  @KeepForSdk
  public static class Builder<A extends Api.AnyClient, ResultT> {
    private Feature[] zzlz;
    
    private boolean zzma = true;
    
    private BiConsumer<A, TaskCompletionSource<ResultT>> zzmb;
    
    private Builder() {}
    
    @KeepForSdk
    public TaskApiCall<A, ResultT> build() {
      if (this.zzmb == null)
        throw new IllegalArgumentException("execute parameter required"); 
      return new zzcf(this, this.zzlz, this.zzma);
    }
    
    @KeepForSdk
    public Builder<A, ResultT> execute(BiConsumer<A, TaskCompletionSource<ResultT>> param1BiConsumer) {
      this.zzmb = param1BiConsumer;
      return this;
    }
    
    @KeepForSdk
    public Builder<A, ResultT> setAutoResolveMissingFeatures(boolean param1Boolean) {
      this.zzma = param1Boolean;
      return this;
    }
    
    @KeepForSdk
    public Builder<A, ResultT> setFeatures(Feature[] param1ArrayOfFeature) {
      this.zzlz = param1ArrayOfFeature;
      return this;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\TaskApiCall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */