package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.zzbh;
import com.google.android.gms.maps.internal.zzbj;
import com.google.android.gms.maps.internal.zzbl;
import com.google.android.gms.maps.internal.zzbn;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;

public class StreetViewPanorama {
  private final IStreetViewPanoramaDelegate zzbn;
  
  public StreetViewPanorama(@NonNull IStreetViewPanoramaDelegate paramIStreetViewPanoramaDelegate) {
    this.zzbn = (IStreetViewPanoramaDelegate)Preconditions.checkNotNull(paramIStreetViewPanoramaDelegate, "delegate");
  }
  
  public void animateTo(StreetViewPanoramaCamera paramStreetViewPanoramaCamera, long paramLong) {
    try {
      this.zzbn.animateTo(paramStreetViewPanoramaCamera, paramLong);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public StreetViewPanoramaLocation getLocation() {
    try {
      return this.zzbn.getStreetViewPanoramaLocation();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public StreetViewPanoramaCamera getPanoramaCamera() {
    try {
      return this.zzbn.getPanoramaCamera();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public boolean isPanningGesturesEnabled() {
    try {
      return this.zzbn.isPanningGesturesEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public boolean isStreetNamesEnabled() {
    try {
      return this.zzbn.isStreetNamesEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public boolean isUserNavigationEnabled() {
    try {
      return this.zzbn.isUserNavigationEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public boolean isZoomGesturesEnabled() {
    try {
      return this.zzbn.isZoomGesturesEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public Point orientationToPoint(StreetViewPanoramaOrientation paramStreetViewPanoramaOrientation) {
    try {
      IObjectWrapper iObjectWrapper = this.zzbn.orientationToPoint(paramStreetViewPanoramaOrientation);
      return (iObjectWrapper == null) ? null : (Point)ObjectWrapper.unwrap(iObjectWrapper);
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public StreetViewPanoramaOrientation pointToOrientation(Point paramPoint) {
    try {
      return this.zzbn.pointToOrientation(ObjectWrapper.wrap(paramPoint));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setOnStreetViewPanoramaCameraChangeListener(OnStreetViewPanoramaCameraChangeListener paramOnStreetViewPanoramaCameraChangeListener) {
    if (paramOnStreetViewPanoramaCameraChangeListener == null)
      try {
        this.zzbn.setOnStreetViewPanoramaCameraChangeListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate = this.zzbn;
    zzae zzae = new zzae();
    this(this, (OnStreetViewPanoramaCameraChangeListener)remoteException);
    iStreetViewPanoramaDelegate.setOnStreetViewPanoramaCameraChangeListener((zzbh)zzae);
  }
  
  public final void setOnStreetViewPanoramaChangeListener(OnStreetViewPanoramaChangeListener paramOnStreetViewPanoramaChangeListener) {
    if (paramOnStreetViewPanoramaChangeListener == null)
      try {
        this.zzbn.setOnStreetViewPanoramaChangeListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate = this.zzbn;
    zzad zzad = new zzad();
    this(this, (OnStreetViewPanoramaChangeListener)remoteException);
    iStreetViewPanoramaDelegate.setOnStreetViewPanoramaChangeListener((zzbj)zzad);
  }
  
  public final void setOnStreetViewPanoramaClickListener(OnStreetViewPanoramaClickListener paramOnStreetViewPanoramaClickListener) {
    if (paramOnStreetViewPanoramaClickListener == null)
      try {
        this.zzbn.setOnStreetViewPanoramaClickListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate = this.zzbn;
    zzaf zzaf = new zzaf();
    this(this, (OnStreetViewPanoramaClickListener)remoteException);
    iStreetViewPanoramaDelegate.setOnStreetViewPanoramaClickListener((zzbl)zzaf);
  }
  
  public final void setOnStreetViewPanoramaLongClickListener(OnStreetViewPanoramaLongClickListener paramOnStreetViewPanoramaLongClickListener) {
    if (paramOnStreetViewPanoramaLongClickListener == null)
      try {
        this.zzbn.setOnStreetViewPanoramaLongClickListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate = this.zzbn;
    zzag zzag = new zzag();
    this(this, (OnStreetViewPanoramaLongClickListener)remoteException);
    iStreetViewPanoramaDelegate.setOnStreetViewPanoramaLongClickListener((zzbn)zzag);
  }
  
  public void setPanningGesturesEnabled(boolean paramBoolean) {
    try {
      this.zzbn.enablePanning(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public void setPosition(LatLng paramLatLng) {
    try {
      this.zzbn.setPosition(paramLatLng);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public void setPosition(LatLng paramLatLng, int paramInt) {
    try {
      this.zzbn.setPositionWithRadius(paramLatLng, paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public void setPosition(LatLng paramLatLng, int paramInt, StreetViewSource paramStreetViewSource) {
    try {
      this.zzbn.setPositionWithRadiusAndSource(paramLatLng, paramInt, paramStreetViewSource);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public void setPosition(LatLng paramLatLng, StreetViewSource paramStreetViewSource) {
    try {
      this.zzbn.setPositionWithSource(paramLatLng, paramStreetViewSource);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public void setPosition(String paramString) {
    try {
      this.zzbn.setPositionWithID(paramString);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public void setStreetNamesEnabled(boolean paramBoolean) {
    try {
      this.zzbn.enableStreetNames(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public void setUserNavigationEnabled(boolean paramBoolean) {
    try {
      this.zzbn.enableUserNavigation(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public void setZoomGesturesEnabled(boolean paramBoolean) {
    try {
      this.zzbn.enableZoom(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static interface OnStreetViewPanoramaCameraChangeListener {
    void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera param1StreetViewPanoramaCamera);
  }
  
  public static interface OnStreetViewPanoramaChangeListener {
    void onStreetViewPanoramaChange(StreetViewPanoramaLocation param1StreetViewPanoramaLocation);
  }
  
  public static interface OnStreetViewPanoramaClickListener {
    void onStreetViewPanoramaClick(StreetViewPanoramaOrientation param1StreetViewPanoramaOrientation);
  }
  
  public static interface OnStreetViewPanoramaLongClickListener {
    void onStreetViewPanoramaLongClick(StreetViewPanoramaOrientation param1StreetViewPanoramaOrientation);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\StreetViewPanorama.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */