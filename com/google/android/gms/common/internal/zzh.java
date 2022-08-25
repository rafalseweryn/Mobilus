package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import javax.annotation.concurrent.GuardedBy;

final class zzh extends GmsClientSupervisor implements Handler.Callback {
  private final Handler mHandler;
  
  private final Context zzau;
  
  @GuardedBy("mConnectionStatus")
  private final HashMap<GmsClientSupervisor.ConnectionStatusConfig, zzi> zztr = new HashMap<>();
  
  private final ConnectionTracker zzts;
  
  private final long zztt;
  
  private final long zztu;
  
  zzh(Context paramContext) {
    this.zzau = paramContext.getApplicationContext();
    this.mHandler = new Handler(paramContext.getMainLooper(), this);
    this.zzts = ConnectionTracker.getInstance();
    this.zztt = 5000L;
    this.zztu = 300000L;
  }
  
  protected final boolean bindService(GmsClientSupervisor.ConnectionStatusConfig paramConnectionStatusConfig, ServiceConnection paramServiceConnection, String paramString) {
    Preconditions.checkNotNull(paramServiceConnection, "ServiceConnection must not be null");
    synchronized (this.zztr) {
      zzi zzi1;
      zzi zzi2 = this.zztr.get(paramConnectionStatusConfig);
      if (zzi2 == null) {
        zzi2 = new zzi();
        this(this, paramConnectionStatusConfig);
        zzi2.zza(paramServiceConnection, paramString);
        zzi2.zzj(paramString);
        this.zztr.put(paramConnectionStatusConfig, zzi2);
        zzi1 = zzi2;
      } else {
        IllegalStateException illegalStateException;
        StringBuilder stringBuilder;
        boolean bool;
        this.mHandler.removeMessages(0, zzi1);
        if (zzi2.zza(paramServiceConnection)) {
          illegalStateException = new IllegalStateException();
          String str = String.valueOf(zzi1);
          int i = String.valueOf(str).length();
          stringBuilder = new StringBuilder();
          this(i + 81);
          stringBuilder.append("Trying to bind a GmsServiceConnection that was already connected before.  config=");
          stringBuilder.append(str);
          this(stringBuilder.toString());
          throw illegalStateException;
        } 
        zzi2.zza((ServiceConnection)illegalStateException, (String)stringBuilder);
        switch (zzi2.getState()) {
          default:
            zzi1 = zzi2;
            bool = zzi1.isBound();
            return bool;
          case 2:
            zzi2.zzj((String)stringBuilder);
            zzi1 = zzi2;
            bool = zzi1.isBound();
            return bool;
          case 1:
            break;
        } 
        illegalStateException.onServiceConnected(zzi2.getComponentName(), zzi2.getBinder());
        zzi1 = zzi2;
      } 
      return zzi1.isBound();
    } 
  }
  
  public final boolean handleMessage(Message paramMessage) {
    switch (paramMessage.what) {
      default:
        return false;
      case 1:
        synchronized (this.zztr) {
          GmsClientSupervisor.ConnectionStatusConfig connectionStatusConfig = (GmsClientSupervisor.ConnectionStatusConfig)paramMessage.obj;
          zzi zzi = this.zztr.get(connectionStatusConfig);
          if (zzi != null && zzi.getState() == 3) {
            String str1 = String.valueOf(connectionStatusConfig);
            int i = String.valueOf(str1).length();
            StringBuilder stringBuilder = new StringBuilder();
            this(i + 47);
            stringBuilder.append("Timeout waiting for ServiceConnection callback ");
            stringBuilder.append(str1);
            String str2 = stringBuilder.toString();
            Exception exception = new Exception();
            this();
            Log.wtf("GmsClientSupervisor", str2, exception);
            ComponentName componentName2 = zzi.getComponentName();
            ComponentName componentName1 = componentName2;
            if (componentName2 == null)
              componentName1 = connectionStatusConfig.getComponentName(); 
            componentName2 = componentName1;
            if (componentName1 == null) {
              componentName2 = new ComponentName();
              this(connectionStatusConfig.getPackage(), "unknown");
            } 
            zzi.onServiceDisconnected(componentName2);
          } 
          return true;
        } 
      case 0:
        break;
    } 
    synchronized (this.zztr) {
      GmsClientSupervisor.ConnectionStatusConfig connectionStatusConfig = (GmsClientSupervisor.ConnectionStatusConfig)paramMessage.obj;
      zzi zzi = this.zztr.get(connectionStatusConfig);
      if (zzi != null && zzi.zzcv()) {
        if (zzi.isBound())
          zzi.zzk("GmsClientSupervisor"); 
        this.zztr.remove(connectionStatusConfig);
      } 
      return true;
    } 
  }
  
  @VisibleForTesting
  public final void resetForTesting() {
    synchronized (this.zztr) {
      for (zzi zzi : this.zztr.values()) {
        this.mHandler.removeMessages(0, zzi.zza(zzi));
        if (zzi.isBound())
          zzi.zzk("GmsClientSupervisor"); 
      } 
      this.zztr.clear();
      return;
    } 
  }
  
  protected final void unbindService(GmsClientSupervisor.ConnectionStatusConfig paramConnectionStatusConfig, ServiceConnection paramServiceConnection, String paramString) {
    Preconditions.checkNotNull(paramServiceConnection, "ServiceConnection must not be null");
    synchronized (this.zztr) {
      String str;
      StringBuilder stringBuilder;
      IllegalStateException illegalStateException;
      zzi zzi = this.zztr.get(paramConnectionStatusConfig);
      if (zzi == null) {
        illegalStateException = new IllegalStateException();
        str = String.valueOf(paramConnectionStatusConfig);
        int i = String.valueOf(str).length();
        StringBuilder stringBuilder1 = new StringBuilder();
        this(i + 50);
        stringBuilder1.append("Nonexistent connection status for service config: ");
        stringBuilder1.append(str);
        this(stringBuilder1.toString());
        throw illegalStateException;
      } 
      if (!zzi.zza((ServiceConnection)illegalStateException)) {
        illegalStateException = new IllegalStateException();
        paramString = String.valueOf(str);
        int i = String.valueOf(paramString).length();
        stringBuilder = new StringBuilder();
        this(i + 76);
        stringBuilder.append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=");
        stringBuilder.append(paramString);
        this(stringBuilder.toString());
        throw illegalStateException;
      } 
      zzi.zzb((ServiceConnection)illegalStateException, paramString);
      if (zzi.zzcv()) {
        Message message = this.mHandler.obtainMessage(0, stringBuilder);
        this.mHandler.sendMessageDelayed(message, this.zztt);
      } 
      return;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\zzh.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */