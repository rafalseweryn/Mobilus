package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IUiSettingsDelegate;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class UiSettings {
  private final IUiSettingsDelegate zzci;
  
  UiSettings(IUiSettingsDelegate paramIUiSettingsDelegate) {
    this.zzci = paramIUiSettingsDelegate;
  }
  
  public final boolean isCompassEnabled() {
    try {
      return this.zzci.isCompassEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isIndoorLevelPickerEnabled() {
    try {
      return this.zzci.isIndoorLevelPickerEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isMapToolbarEnabled() {
    try {
      return this.zzci.isMapToolbarEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isMyLocationButtonEnabled() {
    try {
      return this.zzci.isMyLocationButtonEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isRotateGesturesEnabled() {
    try {
      return this.zzci.isRotateGesturesEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isScrollGesturesEnabled() {
    try {
      return this.zzci.isScrollGesturesEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isTiltGesturesEnabled() {
    try {
      return this.zzci.isTiltGesturesEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isZoomControlsEnabled() {
    try {
      return this.zzci.isZoomControlsEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isZoomGesturesEnabled() {
    try {
      return this.zzci.isZoomGesturesEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setAllGesturesEnabled(boolean paramBoolean) {
    try {
      this.zzci.setAllGesturesEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setCompassEnabled(boolean paramBoolean) {
    try {
      this.zzci.setCompassEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setIndoorLevelPickerEnabled(boolean paramBoolean) {
    try {
      this.zzci.setIndoorLevelPickerEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setMapToolbarEnabled(boolean paramBoolean) {
    try {
      this.zzci.setMapToolbarEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setMyLocationButtonEnabled(boolean paramBoolean) {
    try {
      this.zzci.setMyLocationButtonEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setRotateGesturesEnabled(boolean paramBoolean) {
    try {
      this.zzci.setRotateGesturesEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setScrollGesturesEnabled(boolean paramBoolean) {
    try {
      this.zzci.setScrollGesturesEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setTiltGesturesEnabled(boolean paramBoolean) {
    try {
      this.zzci.setTiltGesturesEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setZoomControlsEnabled(boolean paramBoolean) {
    try {
      this.zzci.setZoomControlsEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setZoomGesturesEnabled(boolean paramBoolean) {
    try {
      this.zzci.setZoomGesturesEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\UiSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */