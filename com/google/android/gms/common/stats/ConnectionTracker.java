package com.google.android.gms.common.stats;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.List;

public class ConnectionTracker {
  private static final Object zztm = new Object();
  
  private static volatile ConnectionTracker zzyg;
  
  @VisibleForTesting
  private static boolean zzyh = false;
  
  private final List<String> zzyi = Collections.EMPTY_LIST;
  
  private final List<String> zzyj = Collections.EMPTY_LIST;
  
  private final List<String> zzyk = Collections.EMPTY_LIST;
  
  private final List<String> zzyl = Collections.EMPTY_LIST;
  
  public static ConnectionTracker getInstance() {
    if (zzyg == null)
      synchronized (zztm) {
        if (zzyg == null) {
          ConnectionTracker connectionTracker = new ConnectionTracker();
          this();
          zzyg = connectionTracker;
        } 
      }  
    return zzyg;
  }
  
  @SuppressLint({"UntrackedBindService"})
  private static boolean zza(Context paramContext, String paramString, Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt, boolean paramBoolean) {
    if (paramBoolean) {
      ComponentName componentName = paramIntent.getComponent();
      if (componentName == null) {
        paramBoolean = false;
      } else {
        paramBoolean = ClientLibraryUtils.isPackageStopped(paramContext, componentName.getPackageName());
      } 
      if (paramBoolean) {
        Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
        return false;
      } 
    } 
    return paramContext.bindService(paramIntent, paramServiceConnection, paramInt);
  }
  
  public boolean bindService(Context paramContext, Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt) {
    return bindService(paramContext, paramContext.getClass().getName(), paramIntent, paramServiceConnection, paramInt);
  }
  
  public boolean bindService(Context paramContext, String paramString, Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt) {
    return zza(paramContext, paramString, paramIntent, paramServiceConnection, paramInt, true);
  }
  
  public boolean bindServiceAllowStoppedPackages(Context paramContext, String paramString, Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt) {
    return zza(paramContext, paramString, paramIntent, paramServiceConnection, paramInt, false);
  }
  
  public void logConnectService(Context paramContext, ServiceConnection paramServiceConnection, String paramString, Intent paramIntent) {}
  
  public void logDisconnectService(Context paramContext, ServiceConnection paramServiceConnection) {}
  
  public void logStartService(Service paramService, int paramInt) {}
  
  public void logStopService(Service paramService, int paramInt) {}
  
  @SuppressLint({"UntrackedBindService"})
  public void unbindService(Context paramContext, ServiceConnection paramServiceConnection) {
    paramContext.unbindService(paramServiceConnection);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\stats\ConnectionTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */