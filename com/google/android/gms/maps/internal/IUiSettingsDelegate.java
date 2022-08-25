package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;

public interface IUiSettingsDelegate extends IInterface {
  boolean isCompassEnabled() throws RemoteException;
  
  boolean isIndoorLevelPickerEnabled() throws RemoteException;
  
  boolean isMapToolbarEnabled() throws RemoteException;
  
  boolean isMyLocationButtonEnabled() throws RemoteException;
  
  boolean isRotateGesturesEnabled() throws RemoteException;
  
  boolean isScrollGesturesEnabled() throws RemoteException;
  
  boolean isTiltGesturesEnabled() throws RemoteException;
  
  boolean isZoomControlsEnabled() throws RemoteException;
  
  boolean isZoomGesturesEnabled() throws RemoteException;
  
  void setAllGesturesEnabled(boolean paramBoolean) throws RemoteException;
  
  void setCompassEnabled(boolean paramBoolean) throws RemoteException;
  
  void setIndoorLevelPickerEnabled(boolean paramBoolean) throws RemoteException;
  
  void setMapToolbarEnabled(boolean paramBoolean) throws RemoteException;
  
  void setMyLocationButtonEnabled(boolean paramBoolean) throws RemoteException;
  
  void setRotateGesturesEnabled(boolean paramBoolean) throws RemoteException;
  
  void setScrollGesturesEnabled(boolean paramBoolean) throws RemoteException;
  
  void setTiltGesturesEnabled(boolean paramBoolean) throws RemoteException;
  
  void setZoomControlsEnabled(boolean paramBoolean) throws RemoteException;
  
  void setZoomGesturesEnabled(boolean paramBoolean) throws RemoteException;
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\internal\IUiSettingsDelegate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */