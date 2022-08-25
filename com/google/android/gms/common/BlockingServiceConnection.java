package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BlockingServiceConnection implements ServiceConnection {
  private boolean zzaj = false;
  
  private final BlockingQueue<IBinder> zzak = new LinkedBlockingQueue<>();
  
  public IBinder getService() throws InterruptedException {
    Preconditions.checkNotMainThread("BlockingServiceConnection.getService() called on main thread");
    if (this.zzaj)
      throw new IllegalStateException("Cannot call get on this connection more than once"); 
    this.zzaj = true;
    return this.zzak.take();
  }
  
  public IBinder getServiceWithTimeout(long paramLong, TimeUnit paramTimeUnit) throws InterruptedException, TimeoutException {
    Preconditions.checkNotMainThread("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
    if (this.zzaj)
      throw new IllegalStateException("Cannot call get on this connection more than once"); 
    this.zzaj = true;
    IBinder iBinder = this.zzak.poll(paramLong, paramTimeUnit);
    if (iBinder == null)
      throw new TimeoutException("Timed out waiting for the service connection"); 
    return iBinder;
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) {
    this.zzak.add(paramIBinder);
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName) {}
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\BlockingServiceConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */