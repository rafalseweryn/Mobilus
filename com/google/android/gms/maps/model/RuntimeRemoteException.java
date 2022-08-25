package com.google.android.gms.maps.model;

import android.os.RemoteException;

public final class RuntimeRemoteException extends RuntimeException {
  public RuntimeRemoteException(RemoteException paramRemoteException) {
    super((Throwable)paramRemoteException);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\RuntimeRemoteException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */