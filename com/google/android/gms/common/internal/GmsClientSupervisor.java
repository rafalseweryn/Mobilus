package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.google.android.gms.common.util.VisibleForTesting;

public abstract class GmsClientSupervisor {
  public static final int DEFAULT_BIND_FLAGS = 129;
  
  private static final Object zztm = new Object();
  
  private static GmsClientSupervisor zztn;
  
  public static GmsClientSupervisor getInstance(Context paramContext) {
    synchronized (zztm) {
      if (zztn == null) {
        zzh zzh = new zzh();
        this(paramContext.getApplicationContext());
        zztn = zzh;
      } 
      return zztn;
    } 
  }
  
  public boolean bindService(ComponentName paramComponentName, ServiceConnection paramServiceConnection, String paramString) {
    return bindService(new ConnectionStatusConfig(paramComponentName, 129), paramServiceConnection, paramString);
  }
  
  protected abstract boolean bindService(ConnectionStatusConfig paramConnectionStatusConfig, ServiceConnection paramServiceConnection, String paramString);
  
  public boolean bindService(String paramString1, ServiceConnection paramServiceConnection, String paramString2) {
    return bindService(new ConnectionStatusConfig(paramString1, 129), paramServiceConnection, paramString2);
  }
  
  public boolean bindService(String paramString1, String paramString2, int paramInt, ServiceConnection paramServiceConnection, String paramString3) {
    return bindService(new ConnectionStatusConfig(paramString1, paramString2, paramInt), paramServiceConnection, paramString3);
  }
  
  public boolean bindService(String paramString1, String paramString2, ServiceConnection paramServiceConnection, String paramString3) {
    return bindService(paramString1, paramString2, 129, paramServiceConnection, paramString3);
  }
  
  @VisibleForTesting
  public abstract void resetForTesting();
  
  public void unbindService(ComponentName paramComponentName, ServiceConnection paramServiceConnection, String paramString) {
    unbindService(new ConnectionStatusConfig(paramComponentName, 129), paramServiceConnection, paramString);
  }
  
  protected abstract void unbindService(ConnectionStatusConfig paramConnectionStatusConfig, ServiceConnection paramServiceConnection, String paramString);
  
  public void unbindService(String paramString1, ServiceConnection paramServiceConnection, String paramString2) {
    unbindService(new ConnectionStatusConfig(paramString1, 129), paramServiceConnection, paramString2);
  }
  
  public void unbindService(String paramString1, String paramString2, int paramInt, ServiceConnection paramServiceConnection, String paramString3) {
    unbindService(new ConnectionStatusConfig(paramString1, paramString2, paramInt), paramServiceConnection, paramString3);
  }
  
  public void unbindService(String paramString1, String paramString2, ServiceConnection paramServiceConnection, String paramString3) {
    unbindService(paramString1, paramString2, 129, paramServiceConnection, paramString3);
  }
  
  protected static final class ConnectionStatusConfig {
    private final ComponentName mComponentName;
    
    private final String zzto = null;
    
    private final String zztp = null;
    
    private final int zztq;
    
    public ConnectionStatusConfig(ComponentName param1ComponentName, int param1Int) {
      this.mComponentName = Preconditions.<ComponentName>checkNotNull(param1ComponentName);
      this.zztq = param1Int;
    }
    
    public ConnectionStatusConfig(String param1String, int param1Int) {
      this.mComponentName = null;
      this.zztq = param1Int;
    }
    
    public ConnectionStatusConfig(String param1String1, String param1String2, int param1Int) {
      this.mComponentName = null;
      this.zztq = param1Int;
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof ConnectionStatusConfig))
        return false; 
      param1Object = param1Object;
      return (Objects.equal(this.zzto, ((ConnectionStatusConfig)param1Object).zzto) && Objects.equal(this.zztp, ((ConnectionStatusConfig)param1Object).zztp) && Objects.equal(this.mComponentName, ((ConnectionStatusConfig)param1Object).mComponentName) && this.zztq == ((ConnectionStatusConfig)param1Object).zztq);
    }
    
    public final String getAction() {
      return this.zzto;
    }
    
    public final int getBindFlags() {
      return this.zztq;
    }
    
    public final ComponentName getComponentName() {
      return this.mComponentName;
    }
    
    public final String getPackage() {
      return this.zztp;
    }
    
    public final Intent getStartServiceIntent(Context param1Context) {
      return (this.zzto != null) ? (new Intent(this.zzto)).setPackage(this.zztp) : (new Intent()).setComponent(this.mComponentName);
    }
    
    public final int hashCode() {
      return Objects.hashCode(new Object[] { this.zzto, this.zztp, this.mComponentName, Integer.valueOf(this.zztq) });
    }
    
    public final String toString() {
      return (this.zzto == null) ? this.mComponentName.flattenToString() : this.zzto;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\GmsClientSupervisor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */