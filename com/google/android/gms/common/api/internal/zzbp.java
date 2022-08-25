package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public interface zzbp {
  ConnectionResult blockingConnect();
  
  ConnectionResult blockingConnect(long paramLong, TimeUnit paramTimeUnit);
  
  void connect();
  
  void disconnect();
  
  void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
  
  <A extends Api.AnyClient, R extends com.google.android.gms.common.api.Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T paramT);
  
  <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends com.google.android.gms.common.api.Result, A>> T execute(@NonNull T paramT);
  
  @Nullable
  ConnectionResult getConnectionResult(@NonNull Api<?> paramApi);
  
  boolean isConnected();
  
  boolean isConnecting();
  
  boolean maybeSignIn(SignInConnectionListener paramSignInConnectionListener);
  
  void maybeSignOut();
  
  void zzz();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzbp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */