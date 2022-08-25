package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

public class SimpleClientAdapter<T extends IInterface> extends GmsClient<T> {
  private final Api.SimpleClient<T> zzva;
  
  public SimpleClientAdapter(Context paramContext, Looper paramLooper, int paramInt, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, ClientSettings paramClientSettings, Api.SimpleClient<T> paramSimpleClient) {
    super(paramContext, paramLooper, paramInt, paramClientSettings, paramConnectionCallbacks, paramOnConnectionFailedListener);
    this.zzva = paramSimpleClient;
  }
  
  protected T createServiceInterface(IBinder paramIBinder) {
    return (T)this.zzva.createServiceInterface(paramIBinder);
  }
  
  public Api.SimpleClient<T> getClient() {
    return this.zzva;
  }
  
  public int getMinApkVersion() {
    return super.getMinApkVersion();
  }
  
  protected String getServiceDescriptor() {
    return this.zzva.getServiceDescriptor();
  }
  
  protected String getStartServiceAction() {
    return this.zzva.getStartServiceAction();
  }
  
  protected void onSetConnectState(int paramInt, T paramT) {
    this.zzva.setState(paramInt, (IInterface)paramT);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\SimpleClientAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */