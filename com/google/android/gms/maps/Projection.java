package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.IProjectionDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.VisibleRegion;

public final class Projection {
  private final IProjectionDelegate zzbm;
  
  Projection(IProjectionDelegate paramIProjectionDelegate) {
    this.zzbm = paramIProjectionDelegate;
  }
  
  public final LatLng fromScreenLocation(Point paramPoint) {
    try {
      return this.zzbm.fromScreenLocation(ObjectWrapper.wrap(paramPoint));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final VisibleRegion getVisibleRegion() {
    try {
      return this.zzbm.getVisibleRegion();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final Point toScreenLocation(LatLng paramLatLng) {
    try {
      return (Point)ObjectWrapper.unwrap(this.zzbm.toScreenLocation(paramLatLng));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\Projection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */