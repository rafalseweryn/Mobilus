package com.google.android.gms.common.internal.service;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;

public class CommonClient extends GmsClient<ICommonService> {
  public static final String START_SERVICE_ACTION = "com.google.android.gms.common.service.START";
  
  public CommonClient(Context paramContext, Looper paramLooper, ClientSettings paramClientSettings, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    super(paramContext, paramLooper, 39, paramClientSettings, paramConnectionCallbacks, paramOnConnectionFailedListener);
  }
  
  protected ICommonService createServiceInterface(IBinder paramIBinder) {
    return ICommonService.Stub.asInterface(paramIBinder);
  }
  
  public int getMinApkVersion() {
    return super.getMinApkVersion();
  }
  
  protected String getServiceDescriptor() {
    return "com.google.android.gms.common.internal.service.ICommonService";
  }
  
  public String getStartServiceAction() {
    return "com.google.android.gms.common.service.START";
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\service\CommonClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */