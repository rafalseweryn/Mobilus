package com.google.android.gms.maps.model;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzq;

public final class IndoorLevel {
  private final zzq zzdf;
  
  public IndoorLevel(zzq paramzzq) {
    this.zzdf = (zzq)Preconditions.checkNotNull(paramzzq);
  }
  
  public final void activate() {
    try {
      this.zzdf.activate();
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean equals(Object paramObject) {
    if (!(paramObject instanceof IndoorLevel))
      return false; 
    try {
      return this.zzdf.zzb(((IndoorLevel)paramObject).zzdf);
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  @NonNull
  public final String getName() {
    try {
      return this.zzdf.getName();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  @NonNull
  public final String getShortName() {
    try {
      return this.zzdf.getShortName();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final int hashCode() {
    try {
      return this.zzdf.zzi();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\IndoorLevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */