package com.google.android.gms.common.net;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class SocketFactoryCreator extends RemoteCreator<ISocketFactoryCreator> {
  private static SocketFactoryCreator zzvr;
  
  protected SocketFactoryCreator() {
    super("com.google.android.gms.common.net.SocketFactoryCreatorImpl");
  }
  
  public static SocketFactoryCreator getInstance() {
    if (zzvr == null)
      zzvr = new SocketFactoryCreator(); 
    return zzvr;
  }
  
  protected ISocketFactoryCreator getRemoteCreator(IBinder paramIBinder) {
    return ISocketFactoryCreator.Stub.asInterface(paramIBinder);
  }
  
  public SSLSocketFactory makeSocketFactory(Context paramContext, KeyManager[] paramArrayOfKeyManager, TrustManager[] paramArrayOfTrustManager, boolean paramBoolean) {
    try {
      return (SSLSocketFactory)ObjectWrapper.unwrap(((ISocketFactoryCreator)getRemoteCreatorInstance(paramContext)).newSocketFactory(ObjectWrapper.wrap(paramContext), ObjectWrapper.wrap(paramArrayOfKeyManager), ObjectWrapper.wrap(paramArrayOfTrustManager), paramBoolean));
    } catch (RemoteException|com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException remoteException) {
      throw new RuntimeException(remoteException);
    } 
  }
  
  public SSLSocketFactory makeSocketFactoryWithCacheDir(Context paramContext, KeyManager[] paramArrayOfKeyManager, TrustManager[] paramArrayOfTrustManager, String paramString) {
    try {
      return (SSLSocketFactory)ObjectWrapper.unwrap(((ISocketFactoryCreator)getRemoteCreatorInstance(paramContext)).newSocketFactoryWithCacheDir(ObjectWrapper.wrap(paramContext), ObjectWrapper.wrap(paramArrayOfKeyManager), ObjectWrapper.wrap(paramArrayOfTrustManager), paramString));
    } catch (RemoteException|com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException remoteException) {
      throw new RuntimeException(remoteException);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\net\SocketFactoryCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */