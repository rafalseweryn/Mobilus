package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class CameraUpdateFactory {
  private static ICameraUpdateFactoryDelegate zzf;
  
  public static CameraUpdate newCameraPosition(CameraPosition paramCameraPosition) {
    try {
      return new CameraUpdate(zzb().newCameraPosition(paramCameraPosition));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate newLatLng(LatLng paramLatLng) {
    try {
      return new CameraUpdate(zzb().newLatLng(paramLatLng));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate newLatLngBounds(LatLngBounds paramLatLngBounds, int paramInt) {
    try {
      return new CameraUpdate(zzb().newLatLngBounds(paramLatLngBounds, paramInt));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate newLatLngBounds(LatLngBounds paramLatLngBounds, int paramInt1, int paramInt2, int paramInt3) {
    try {
      return new CameraUpdate(zzb().newLatLngBoundsWithSize(paramLatLngBounds, paramInt1, paramInt2, paramInt3));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate newLatLngZoom(LatLng paramLatLng, float paramFloat) {
    try {
      return new CameraUpdate(zzb().newLatLngZoom(paramLatLng, paramFloat));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate scrollBy(float paramFloat1, float paramFloat2) {
    try {
      return new CameraUpdate(zzb().scrollBy(paramFloat1, paramFloat2));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate zoomBy(float paramFloat) {
    try {
      return new CameraUpdate(zzb().zoomBy(paramFloat));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate zoomBy(float paramFloat, Point paramPoint) {
    try {
      return new CameraUpdate(zzb().zoomByWithFocus(paramFloat, paramPoint.x, paramPoint.y));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate zoomIn() {
    try {
      return new CameraUpdate(zzb().zoomIn());
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate zoomOut() {
    try {
      return new CameraUpdate(zzb().zoomOut());
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate zoomTo(float paramFloat) {
    try {
      return new CameraUpdate(zzb().zoomTo(paramFloat));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static void zza(ICameraUpdateFactoryDelegate paramICameraUpdateFactoryDelegate) {
    zzf = (ICameraUpdateFactoryDelegate)Preconditions.checkNotNull(paramICameraUpdateFactoryDelegate);
  }
  
  private static ICameraUpdateFactoryDelegate zzb() {
    return (ICameraUpdateFactoryDelegate)Preconditions.checkNotNull(zzf, "CameraUpdateFactory is not initialized");
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\CameraUpdateFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */