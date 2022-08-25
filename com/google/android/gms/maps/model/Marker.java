package com.google.android.gms.maps.model;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzt;

public final class Marker {
  private final zzt zzdl;
  
  public Marker(zzt paramzzt) {
    this.zzdl = (zzt)Preconditions.checkNotNull(paramzzt);
  }
  
  public final boolean equals(Object paramObject) {
    if (!(paramObject instanceof Marker))
      return false; 
    try {
      return this.zzdl.zzj(((Marker)paramObject).zzdl);
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final float getAlpha() {
    try {
      return this.zzdl.getAlpha();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final String getId() {
    try {
      return this.zzdl.getId();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final LatLng getPosition() {
    try {
      return this.zzdl.getPosition();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final float getRotation() {
    try {
      return this.zzdl.getRotation();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final String getSnippet() {
    try {
      return this.zzdl.getSnippet();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  @Nullable
  public final Object getTag() {
    try {
      return ObjectWrapper.unwrap(this.zzdl.zzj());
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final String getTitle() {
    try {
      return this.zzdl.getTitle();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final float getZIndex() {
    try {
      return this.zzdl.getZIndex();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final int hashCode() {
    try {
      return this.zzdl.zzi();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void hideInfoWindow() {
    try {
      this.zzdl.hideInfoWindow();
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isDraggable() {
    try {
      return this.zzdl.isDraggable();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isFlat() {
    try {
      return this.zzdl.isFlat();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isInfoWindowShown() {
    try {
      return this.zzdl.isInfoWindowShown();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isVisible() {
    try {
      return this.zzdl.isVisible();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void remove() {
    try {
      this.zzdl.remove();
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setAlpha(float paramFloat) {
    try {
      this.zzdl.setAlpha(paramFloat);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setAnchor(float paramFloat1, float paramFloat2) {
    try {
      this.zzdl.setAnchor(paramFloat1, paramFloat2);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setDraggable(boolean paramBoolean) {
    try {
      this.zzdl.setDraggable(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setFlat(boolean paramBoolean) {
    try {
      this.zzdl.setFlat(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setIcon(@Nullable BitmapDescriptor paramBitmapDescriptor) {
    if (paramBitmapDescriptor == null)
      try {
        this.zzdl.zzg(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IObjectWrapper iObjectWrapper = remoteException.zza();
    this.zzdl.zzg(iObjectWrapper);
  }
  
  public final void setInfoWindowAnchor(float paramFloat1, float paramFloat2) {
    try {
      this.zzdl.setInfoWindowAnchor(paramFloat1, paramFloat2);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setPosition(@NonNull LatLng paramLatLng) {
    if (paramLatLng == null)
      throw new IllegalArgumentException("latlng cannot be null - a position is required."); 
    try {
      this.zzdl.setPosition(paramLatLng);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setRotation(float paramFloat) {
    try {
      this.zzdl.setRotation(paramFloat);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setSnippet(@Nullable String paramString) {
    try {
      this.zzdl.setSnippet(paramString);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setTag(@Nullable Object paramObject) {
    try {
      this.zzdl.zze(ObjectWrapper.wrap(paramObject));
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setTitle(@Nullable String paramString) {
    try {
      this.zzdl.setTitle(paramString);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setVisible(boolean paramBoolean) {
    try {
      this.zzdl.setVisible(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setZIndex(float paramFloat) {
    try {
      this.zzdl.setZIndex(paramFloat);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void showInfoWindow() {
    try {
      this.zzdl.showInfoWindow();
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\Marker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */