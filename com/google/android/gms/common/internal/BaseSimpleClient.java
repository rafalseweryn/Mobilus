package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IInterface;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;

public abstract class BaseSimpleClient<T extends IInterface> implements Api.SimpleClient<T> {
  private final Context mContext;
  
  private final Object mLock = new Object();
  
  private int mState = 1;
  
  private T zzrj = null;
  
  public BaseSimpleClient(Context paramContext) {
    this.mContext = paramContext;
  }
  
  public Context getContext() {
    return this.mContext;
  }
  
  public Feature[] getRequiredFeatures() {
    return new Feature[0];
  }
  
  public T getService() throws DeadObjectException {
    synchronized (this.mLock) {
      boolean bool;
      if (this.mState == 5) {
        DeadObjectException deadObjectException = new DeadObjectException();
        this();
        throw deadObjectException;
      } 
      if (this.mState != 4) {
        IllegalStateException illegalStateException = new IllegalStateException();
        this("Not connected. Call connect() and wait for onConnected() to be called.");
        throw illegalStateException;
      } 
      if (this.zzrj != null) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkState(bool, "Client is connected but service is null");
      return this.zzrj;
    } 
  }
  
  public void setState(int paramInt, T paramT) {
    synchronized (this.mLock) {
      this.mState = paramInt;
      this.zzrj = paramT;
      return;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\BaseSimpleClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */