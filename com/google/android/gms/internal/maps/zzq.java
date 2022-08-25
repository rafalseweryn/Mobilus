package com.google.android.gms.internal.maps;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzq extends IInterface {
  void activate() throws RemoteException;
  
  String getName() throws RemoteException;
  
  String getShortName() throws RemoteException;
  
  boolean zzb(zzq paramzzq) throws RemoteException;
  
  int zzi() throws RemoteException;
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\internal\maps\zzq.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */