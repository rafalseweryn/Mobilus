package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class zzaf extends GoogleApiClient {
  private final String zzhe;
  
  public zzaf(String paramString) {
    this.zzhe = paramString;
  }
  
  public ConnectionResult blockingConnect() {
    throw new UnsupportedOperationException(this.zzhe);
  }
  
  public ConnectionResult blockingConnect(long paramLong, @NonNull TimeUnit paramTimeUnit) {
    throw new UnsupportedOperationException(this.zzhe);
  }
  
  public PendingResult<Status> clearDefaultAccountAndReconnect() {
    throw new UnsupportedOperationException(this.zzhe);
  }
  
  public void connect() {
    throw new UnsupportedOperationException(this.zzhe);
  }
  
  public void disconnect() {
    throw new UnsupportedOperationException(this.zzhe);
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    throw new UnsupportedOperationException(this.zzhe);
  }
  
  @NonNull
  public ConnectionResult getConnectionResult(@NonNull Api<?> paramApi) {
    throw new UnsupportedOperationException(this.zzhe);
  }
  
  public boolean hasConnectedApi(@NonNull Api<?> paramApi) {
    throw new UnsupportedOperationException(this.zzhe);
  }
  
  public boolean isConnected() {
    throw new UnsupportedOperationException(this.zzhe);
  }
  
  public boolean isConnecting() {
    throw new UnsupportedOperationException(this.zzhe);
  }
  
  public boolean isConnectionCallbacksRegistered(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    throw new UnsupportedOperationException(this.zzhe);
  }
  
  public boolean isConnectionFailedListenerRegistered(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    throw new UnsupportedOperationException(this.zzhe);
  }
  
  public void reconnect() {
    throw new UnsupportedOperationException(this.zzhe);
  }
  
  public void registerConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    throw new UnsupportedOperationException(this.zzhe);
  }
  
  public void registerConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    throw new UnsupportedOperationException(this.zzhe);
  }
  
  public void stopAutoManage(@NonNull FragmentActivity paramFragmentActivity) {
    throw new UnsupportedOperationException(this.zzhe);
  }
  
  public void unregisterConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    throw new UnsupportedOperationException(this.zzhe);
  }
  
  public void unregisterConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    throw new UnsupportedOperationException(this.zzhe);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzaf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */