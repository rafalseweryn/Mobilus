package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

final class zzi implements ServiceConnection {
  private ComponentName mComponentName;
  
  private int mState;
  
  private IBinder zzry;
  
  private final Set<ServiceConnection> zztv;
  
  private boolean zztw;
  
  private final GmsClientSupervisor.ConnectionStatusConfig zztx;
  
  public zzi(zzh paramzzh, GmsClientSupervisor.ConnectionStatusConfig paramConnectionStatusConfig) {
    this.zztx = paramConnectionStatusConfig;
    this.zztv = new HashSet<>();
    this.mState = 2;
  }
  
  public final IBinder getBinder() {
    return this.zzry;
  }
  
  public final ComponentName getComponentName() {
    return this.mComponentName;
  }
  
  public final int getState() {
    return this.mState;
  }
  
  public final boolean isBound() {
    return this.zztw;
  }
  
  public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) {
    synchronized (zzh.zza(this.zzty)) {
      zzh.zzb(this.zzty).removeMessages(1, this.zztx);
      this.zzry = paramIBinder;
      this.mComponentName = paramComponentName;
      Iterator<ServiceConnection> iterator = this.zztv.iterator();
      while (iterator.hasNext())
        ((ServiceConnection)iterator.next()).onServiceConnected(paramComponentName, paramIBinder); 
      this.mState = 1;
      return;
    } 
  }
  
  public final void onServiceDisconnected(ComponentName paramComponentName) {
    synchronized (zzh.zza(this.zzty)) {
      zzh.zzb(this.zzty).removeMessages(1, this.zztx);
      this.zzry = null;
      this.mComponentName = paramComponentName;
      Iterator<ServiceConnection> iterator = this.zztv.iterator();
      while (iterator.hasNext())
        ((ServiceConnection)iterator.next()).onServiceDisconnected(paramComponentName); 
      this.mState = 2;
      return;
    } 
  }
  
  public final void zza(ServiceConnection paramServiceConnection, String paramString) {
    zzh.zzd(this.zzty).logConnectService(zzh.zzc(this.zzty), paramServiceConnection, paramString, this.zztx.getStartServiceIntent(zzh.zzc(this.zzty)));
    this.zztv.add(paramServiceConnection);
  }
  
  public final boolean zza(ServiceConnection paramServiceConnection) {
    return this.zztv.contains(paramServiceConnection);
  }
  
  public final void zzb(ServiceConnection paramServiceConnection, String paramString) {
    zzh.zzd(this.zzty).logDisconnectService(zzh.zzc(this.zzty), paramServiceConnection);
    this.zztv.remove(paramServiceConnection);
  }
  
  public final boolean zzcv() {
    return this.zztv.isEmpty();
  }
  
  public final void zzj(String paramString) {
    this.mState = 3;
    this.zztw = zzh.zzd(this.zzty).bindService(zzh.zzc(this.zzty), paramString, this.zztx.getStartServiceIntent(zzh.zzc(this.zzty)), this, this.zztx.getBindFlags());
    if (this.zztw) {
      Message message = zzh.zzb(this.zzty).obtainMessage(1, this.zztx);
      zzh.zzb(this.zzty).sendMessageDelayed(message, zzh.zze(this.zzty));
      return;
    } 
    this.mState = 2;
    try {
      zzh.zzd(this.zzty).unbindService(zzh.zzc(this.zzty), this);
    } catch (IllegalArgumentException illegalArgumentException) {}
  }
  
  public final void zzk(String paramString) {
    zzh.zzb(this.zzty).removeMessages(1, this.zztx);
    zzh.zzd(this.zzty).unbindService(zzh.zzc(this.zzty), this);
    this.zztw = false;
    this.mState = 2;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\zzi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */