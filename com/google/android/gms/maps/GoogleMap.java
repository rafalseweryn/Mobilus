package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.view.View;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzac;
import com.google.android.gms.internal.maps.zzk;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.ILocationSourceDelegate;
import com.google.android.gms.maps.internal.zzab;
import com.google.android.gms.maps.internal.zzad;
import com.google.android.gms.maps.internal.zzaf;
import com.google.android.gms.maps.internal.zzaj;
import com.google.android.gms.maps.internal.zzal;
import com.google.android.gms.maps.internal.zzan;
import com.google.android.gms.maps.internal.zzar;
import com.google.android.gms.maps.internal.zzat;
import com.google.android.gms.maps.internal.zzav;
import com.google.android.gms.maps.internal.zzax;
import com.google.android.gms.maps.internal.zzaz;
import com.google.android.gms.maps.internal.zzbb;
import com.google.android.gms.maps.internal.zzbd;
import com.google.android.gms.maps.internal.zzbf;
import com.google.android.gms.maps.internal.zzbs;
import com.google.android.gms.maps.internal.zzc;
import com.google.android.gms.maps.internal.zzd;
import com.google.android.gms.maps.internal.zzh;
import com.google.android.gms.maps.internal.zzl;
import com.google.android.gms.maps.internal.zzn;
import com.google.android.gms.maps.internal.zzp;
import com.google.android.gms.maps.internal.zzr;
import com.google.android.gms.maps.internal.zzt;
import com.google.android.gms.maps.internal.zzv;
import com.google.android.gms.maps.internal.zzx;
import com.google.android.gms.maps.internal.zzz;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;

public final class GoogleMap {
  public static final int MAP_TYPE_HYBRID = 4;
  
  public static final int MAP_TYPE_NONE = 0;
  
  public static final int MAP_TYPE_NORMAL = 1;
  
  public static final int MAP_TYPE_SATELLITE = 2;
  
  public static final int MAP_TYPE_TERRAIN = 3;
  
  private final IGoogleMapDelegate zzg;
  
  private UiSettings zzh;
  
  public GoogleMap(IGoogleMapDelegate paramIGoogleMapDelegate) {
    this.zzg = (IGoogleMapDelegate)Preconditions.checkNotNull(paramIGoogleMapDelegate);
  }
  
  public final Circle addCircle(CircleOptions paramCircleOptions) {
    try {
      return new Circle(this.zzg.addCircle(paramCircleOptions));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final GroundOverlay addGroundOverlay(GroundOverlayOptions paramGroundOverlayOptions) {
    try {
      zzk zzk = this.zzg.addGroundOverlay(paramGroundOverlayOptions);
      return (zzk != null) ? new GroundOverlay(zzk) : null;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final Marker addMarker(MarkerOptions paramMarkerOptions) {
    try {
      zzt zzt = this.zzg.addMarker(paramMarkerOptions);
      return (zzt != null) ? new Marker(zzt) : null;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final Polygon addPolygon(PolygonOptions paramPolygonOptions) {
    try {
      return new Polygon(this.zzg.addPolygon(paramPolygonOptions));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final Polyline addPolyline(PolylineOptions paramPolylineOptions) {
    try {
      return new Polyline(this.zzg.addPolyline(paramPolylineOptions));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final TileOverlay addTileOverlay(TileOverlayOptions paramTileOverlayOptions) {
    try {
      zzac zzac = this.zzg.addTileOverlay(paramTileOverlayOptions);
      return (zzac != null) ? new TileOverlay(zzac) : null;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void animateCamera(CameraUpdate paramCameraUpdate) {
    try {
      this.zzg.animateCamera(paramCameraUpdate.zza());
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void animateCamera(CameraUpdate paramCameraUpdate, int paramInt, CancelableCallback paramCancelableCallback) {
    try {
      zza zza;
      IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
      IObjectWrapper iObjectWrapper = paramCameraUpdate.zza();
      if (paramCancelableCallback == null) {
        paramCameraUpdate = null;
      } else {
        zza = new zza(paramCancelableCallback);
      } 
      iGoogleMapDelegate.animateCameraWithDurationAndCallback(iObjectWrapper, paramInt, (zzc)zza);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void animateCamera(CameraUpdate paramCameraUpdate, CancelableCallback paramCancelableCallback) {
    try {
      zza zza;
      IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
      IObjectWrapper iObjectWrapper = paramCameraUpdate.zza();
      if (paramCancelableCallback == null) {
        paramCameraUpdate = null;
      } else {
        zza = new zza(paramCancelableCallback);
      } 
      iGoogleMapDelegate.animateCameraWithCallback(iObjectWrapper, (zzc)zza);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void clear() {
    try {
      this.zzg.clear();
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final CameraPosition getCameraPosition() {
    try {
      return this.zzg.getCameraPosition();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final IndoorBuilding getFocusedBuilding() {
    try {
      zzn zzn = this.zzg.getFocusedBuilding();
      return (zzn != null) ? new IndoorBuilding(zzn) : null;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final int getMapType() {
    try {
      return this.zzg.getMapType();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final float getMaxZoomLevel() {
    try {
      return this.zzg.getMaxZoomLevel();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final float getMinZoomLevel() {
    try {
      return this.zzg.getMinZoomLevel();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  @Deprecated
  public final Location getMyLocation() {
    try {
      return this.zzg.getMyLocation();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final Projection getProjection() {
    try {
      return new Projection(this.zzg.getProjection());
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final UiSettings getUiSettings() {
    try {
      if (this.zzh == null) {
        UiSettings uiSettings = new UiSettings();
        this(this.zzg.getUiSettings());
        this.zzh = uiSettings;
      } 
      return this.zzh;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isBuildingsEnabled() {
    try {
      return this.zzg.isBuildingsEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isIndoorEnabled() {
    try {
      return this.zzg.isIndoorEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isMyLocationEnabled() {
    try {
      return this.zzg.isMyLocationEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isTrafficEnabled() {
    try {
      return this.zzg.isTrafficEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void moveCamera(CameraUpdate paramCameraUpdate) {
    try {
      this.zzg.moveCamera(paramCameraUpdate.zza());
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void resetMinMaxZoomPreference() {
    try {
      this.zzg.resetMinMaxZoomPreference();
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setBuildingsEnabled(boolean paramBoolean) {
    try {
      this.zzg.setBuildingsEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setContentDescription(String paramString) {
    try {
      this.zzg.setContentDescription(paramString);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean setIndoorEnabled(boolean paramBoolean) {
    try {
      return this.zzg.setIndoorEnabled(paramBoolean);
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setInfoWindowAdapter(InfoWindowAdapter paramInfoWindowAdapter) {
    if (paramInfoWindowAdapter == null)
      try {
        this.zzg.setInfoWindowAdapter(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzg zzg = new zzg();
    this(this, (InfoWindowAdapter)remoteException);
    iGoogleMapDelegate.setInfoWindowAdapter((zzh)zzg);
  }
  
  public final void setLatLngBoundsForCameraTarget(LatLngBounds paramLatLngBounds) {
    try {
      this.zzg.setLatLngBoundsForCameraTarget(paramLatLngBounds);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setLocationSource(LocationSource paramLocationSource) {
    if (paramLocationSource == null)
      try {
        this.zzg.setLocationSource(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzl zzl = new zzl();
    this(this, (LocationSource)remoteException);
    iGoogleMapDelegate.setLocationSource((ILocationSourceDelegate)zzl);
  }
  
  public final boolean setMapStyle(@Nullable MapStyleOptions paramMapStyleOptions) {
    try {
      return this.zzg.setMapStyle(paramMapStyleOptions);
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setMapType(int paramInt) {
    try {
      this.zzg.setMapType(paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setMaxZoomPreference(float paramFloat) {
    try {
      this.zzg.setMaxZoomPreference(paramFloat);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setMinZoomPreference(float paramFloat) {
    try {
      this.zzg.setMinZoomPreference(paramFloat);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public final void setMyLocationEnabled(boolean paramBoolean) {
    try {
      this.zzg.setMyLocationEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  @Deprecated
  public final void setOnCameraChangeListener(@Nullable OnCameraChangeListener paramOnCameraChangeListener) {
    if (paramOnCameraChangeListener == null)
      try {
        this.zzg.setOnCameraChangeListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzt zzt = new zzt();
    this(this, (OnCameraChangeListener)remoteException);
    iGoogleMapDelegate.setOnCameraChangeListener((zzl)zzt);
  }
  
  public final void setOnCameraIdleListener(@Nullable OnCameraIdleListener paramOnCameraIdleListener) {
    if (paramOnCameraIdleListener == null)
      try {
        this.zzg.setOnCameraIdleListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzx zzx = new zzx();
    this(this, (OnCameraIdleListener)remoteException);
    iGoogleMapDelegate.setOnCameraIdleListener((zzn)zzx);
  }
  
  public final void setOnCameraMoveCanceledListener(@Nullable OnCameraMoveCanceledListener paramOnCameraMoveCanceledListener) {
    if (paramOnCameraMoveCanceledListener == null)
      try {
        this.zzg.setOnCameraMoveCanceledListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzw zzw = new zzw();
    this(this, (OnCameraMoveCanceledListener)remoteException);
    iGoogleMapDelegate.setOnCameraMoveCanceledListener((zzp)zzw);
  }
  
  public final void setOnCameraMoveListener(@Nullable OnCameraMoveListener paramOnCameraMoveListener) {
    if (paramOnCameraMoveListener == null)
      try {
        this.zzg.setOnCameraMoveListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzv zzv = new zzv();
    this(this, (OnCameraMoveListener)remoteException);
    iGoogleMapDelegate.setOnCameraMoveListener((zzr)zzv);
  }
  
  public final void setOnCameraMoveStartedListener(@Nullable OnCameraMoveStartedListener paramOnCameraMoveStartedListener) {
    if (paramOnCameraMoveStartedListener == null)
      try {
        this.zzg.setOnCameraMoveStartedListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzu zzu = new zzu();
    this(this, (OnCameraMoveStartedListener)remoteException);
    iGoogleMapDelegate.setOnCameraMoveStartedListener((zzt)zzu);
  }
  
  public final void setOnCircleClickListener(OnCircleClickListener paramOnCircleClickListener) {
    if (paramOnCircleClickListener == null)
      try {
        this.zzg.setOnCircleClickListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzo zzo = new zzo();
    this(this, (OnCircleClickListener)remoteException);
    iGoogleMapDelegate.setOnCircleClickListener((zzv)zzo);
  }
  
  public final void setOnGroundOverlayClickListener(OnGroundOverlayClickListener paramOnGroundOverlayClickListener) {
    if (paramOnGroundOverlayClickListener == null)
      try {
        this.zzg.setOnGroundOverlayClickListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzn zzn = new zzn();
    this(this, (OnGroundOverlayClickListener)remoteException);
    iGoogleMapDelegate.setOnGroundOverlayClickListener((zzx)zzn);
  }
  
  public final void setOnIndoorStateChangeListener(OnIndoorStateChangeListener paramOnIndoorStateChangeListener) {
    if (paramOnIndoorStateChangeListener == null)
      try {
        this.zzg.setOnIndoorStateChangeListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zza zza = new zza();
    this(this, (OnIndoorStateChangeListener)remoteException);
    iGoogleMapDelegate.setOnIndoorStateChangeListener((zzz)zza);
  }
  
  public final void setOnInfoWindowClickListener(@Nullable OnInfoWindowClickListener paramOnInfoWindowClickListener) {
    if (paramOnInfoWindowClickListener == null)
      try {
        this.zzg.setOnInfoWindowClickListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzd zzd = new zzd();
    this(this, (OnInfoWindowClickListener)remoteException);
    iGoogleMapDelegate.setOnInfoWindowClickListener((zzab)zzd);
  }
  
  public final void setOnInfoWindowCloseListener(@Nullable OnInfoWindowCloseListener paramOnInfoWindowCloseListener) {
    if (paramOnInfoWindowCloseListener == null)
      try {
        this.zzg.setOnInfoWindowCloseListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzf zzf = new zzf();
    this(this, (OnInfoWindowCloseListener)remoteException);
    iGoogleMapDelegate.setOnInfoWindowCloseListener((zzad)zzf);
  }
  
  public final void setOnInfoWindowLongClickListener(@Nullable OnInfoWindowLongClickListener paramOnInfoWindowLongClickListener) {
    if (paramOnInfoWindowLongClickListener == null)
      try {
        this.zzg.setOnInfoWindowLongClickListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zze zze = new zze();
    this(this, (OnInfoWindowLongClickListener)remoteException);
    iGoogleMapDelegate.setOnInfoWindowLongClickListener((zzaf)zze);
  }
  
  public final void setOnMapClickListener(@Nullable OnMapClickListener paramOnMapClickListener) {
    if (paramOnMapClickListener == null)
      try {
        this.zzg.setOnMapClickListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzy zzy = new zzy();
    this(this, (OnMapClickListener)remoteException);
    iGoogleMapDelegate.setOnMapClickListener((zzaj)zzy);
  }
  
  public final void setOnMapLoadedCallback(OnMapLoadedCallback paramOnMapLoadedCallback) {
    if (paramOnMapLoadedCallback == null)
      try {
        this.zzg.setOnMapLoadedCallback(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzk zzk = new zzk();
    this(this, (OnMapLoadedCallback)remoteException);
    iGoogleMapDelegate.setOnMapLoadedCallback((zzal)zzk);
  }
  
  public final void setOnMapLongClickListener(@Nullable OnMapLongClickListener paramOnMapLongClickListener) {
    if (paramOnMapLongClickListener == null)
      try {
        this.zzg.setOnMapLongClickListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzz zzz = new zzz();
    this(this, (OnMapLongClickListener)remoteException);
    iGoogleMapDelegate.setOnMapLongClickListener((zzan)zzz);
  }
  
  public final void setOnMarkerClickListener(@Nullable OnMarkerClickListener paramOnMarkerClickListener) {
    if (paramOnMarkerClickListener == null)
      try {
        this.zzg.setOnMarkerClickListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzb zzb = new zzb();
    this(this, (OnMarkerClickListener)remoteException);
    iGoogleMapDelegate.setOnMarkerClickListener((zzar)zzb);
  }
  
  public final void setOnMarkerDragListener(@Nullable OnMarkerDragListener paramOnMarkerDragListener) {
    if (paramOnMarkerDragListener == null)
      try {
        this.zzg.setOnMarkerDragListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzc zzc = new zzc();
    this(this, (OnMarkerDragListener)remoteException);
    iGoogleMapDelegate.setOnMarkerDragListener((zzat)zzc);
  }
  
  public final void setOnMyLocationButtonClickListener(@Nullable OnMyLocationButtonClickListener paramOnMyLocationButtonClickListener) {
    if (paramOnMyLocationButtonClickListener == null)
      try {
        this.zzg.setOnMyLocationButtonClickListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzi zzi = new zzi();
    this(this, (OnMyLocationButtonClickListener)remoteException);
    iGoogleMapDelegate.setOnMyLocationButtonClickListener((zzav)zzi);
  }
  
  @Deprecated
  public final void setOnMyLocationChangeListener(@Nullable OnMyLocationChangeListener paramOnMyLocationChangeListener) {
    if (paramOnMyLocationChangeListener == null)
      try {
        this.zzg.setOnMyLocationChangeListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzh zzh = new zzh();
    this(this, (OnMyLocationChangeListener)remoteException);
    iGoogleMapDelegate.setOnMyLocationChangeListener((zzax)zzh);
  }
  
  public final void setOnMyLocationClickListener(@Nullable OnMyLocationClickListener paramOnMyLocationClickListener) {
    if (paramOnMyLocationClickListener == null)
      try {
        this.zzg.setOnMyLocationClickListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzj zzj = new zzj();
    this(this, (OnMyLocationClickListener)remoteException);
    iGoogleMapDelegate.setOnMyLocationClickListener((zzaz)zzj);
  }
  
  public final void setOnPoiClickListener(OnPoiClickListener paramOnPoiClickListener) {
    if (paramOnPoiClickListener == null)
      try {
        this.zzg.setOnPoiClickListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzs zzs = new zzs();
    this(this, (OnPoiClickListener)remoteException);
    iGoogleMapDelegate.setOnPoiClickListener((zzbb)zzs);
  }
  
  public final void setOnPolygonClickListener(OnPolygonClickListener paramOnPolygonClickListener) {
    if (paramOnPolygonClickListener == null)
      try {
        this.zzg.setOnPolygonClickListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzp zzp = new zzp();
    this(this, (OnPolygonClickListener)remoteException);
    iGoogleMapDelegate.setOnPolygonClickListener((zzbd)zzp);
  }
  
  public final void setOnPolylineClickListener(OnPolylineClickListener paramOnPolylineClickListener) {
    if (paramOnPolylineClickListener == null)
      try {
        this.zzg.setOnPolylineClickListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzq zzq = new zzq();
    this(this, (OnPolylineClickListener)remoteException);
    iGoogleMapDelegate.setOnPolylineClickListener((zzbf)zzq);
  }
  
  public final void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    try {
      this.zzg.setPadding(paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setTrafficEnabled(boolean paramBoolean) {
    try {
      this.zzg.setTrafficEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void snapshot(SnapshotReadyCallback paramSnapshotReadyCallback) {
    snapshot(paramSnapshotReadyCallback, null);
  }
  
  public final void snapshot(SnapshotReadyCallback paramSnapshotReadyCallback, Bitmap paramBitmap) {
    if (paramBitmap != null) {
      IObjectWrapper iObjectWrapper = ObjectWrapper.wrap(paramBitmap);
    } else {
      paramBitmap = null;
    } 
    ObjectWrapper objectWrapper = (ObjectWrapper)paramBitmap;
    try {
      IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
      zzr zzr = new zzr();
      this(this, paramSnapshotReadyCallback);
      iGoogleMapDelegate.snapshot((zzbs)zzr, (IObjectWrapper)objectWrapper);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void stopAnimation() {
    try {
      this.zzg.stopAnimation();
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static interface CancelableCallback {
    void onCancel();
    
    void onFinish();
  }
  
  public static interface InfoWindowAdapter {
    View getInfoContents(Marker param1Marker);
    
    View getInfoWindow(Marker param1Marker);
  }
  
  @Deprecated
  public static interface OnCameraChangeListener {
    void onCameraChange(CameraPosition param1CameraPosition);
  }
  
  public static interface OnCameraIdleListener {
    void onCameraIdle();
  }
  
  public static interface OnCameraMoveCanceledListener {
    void onCameraMoveCanceled();
  }
  
  public static interface OnCameraMoveListener {
    void onCameraMove();
  }
  
  public static interface OnCameraMoveStartedListener {
    public static final int REASON_API_ANIMATION = 2;
    
    public static final int REASON_DEVELOPER_ANIMATION = 3;
    
    public static final int REASON_GESTURE = 1;
    
    void onCameraMoveStarted(int param1Int);
  }
  
  public static interface OnCircleClickListener {
    void onCircleClick(Circle param1Circle);
  }
  
  public static interface OnGroundOverlayClickListener {
    void onGroundOverlayClick(GroundOverlay param1GroundOverlay);
  }
  
  public static interface OnIndoorStateChangeListener {
    void onIndoorBuildingFocused();
    
    void onIndoorLevelActivated(IndoorBuilding param1IndoorBuilding);
  }
  
  public static interface OnInfoWindowClickListener {
    void onInfoWindowClick(Marker param1Marker);
  }
  
  public static interface OnInfoWindowCloseListener {
    void onInfoWindowClose(Marker param1Marker);
  }
  
  public static interface OnInfoWindowLongClickListener {
    void onInfoWindowLongClick(Marker param1Marker);
  }
  
  public static interface OnMapClickListener {
    void onMapClick(LatLng param1LatLng);
  }
  
  public static interface OnMapLoadedCallback {
    void onMapLoaded();
  }
  
  public static interface OnMapLongClickListener {
    void onMapLongClick(LatLng param1LatLng);
  }
  
  public static interface OnMarkerClickListener {
    boolean onMarkerClick(Marker param1Marker);
  }
  
  public static interface OnMarkerDragListener {
    void onMarkerDrag(Marker param1Marker);
    
    void onMarkerDragEnd(Marker param1Marker);
    
    void onMarkerDragStart(Marker param1Marker);
  }
  
  public static interface OnMyLocationButtonClickListener {
    boolean onMyLocationButtonClick();
  }
  
  @Deprecated
  public static interface OnMyLocationChangeListener {
    void onMyLocationChange(Location param1Location);
  }
  
  public static interface OnMyLocationClickListener {
    void onMyLocationClick(@NonNull Location param1Location);
  }
  
  public static interface OnPoiClickListener {
    void onPoiClick(PointOfInterest param1PointOfInterest);
  }
  
  public static interface OnPolygonClickListener {
    void onPolygonClick(Polygon param1Polygon);
  }
  
  public static interface OnPolylineClickListener {
    void onPolylineClick(Polyline param1Polyline);
  }
  
  public static interface SnapshotReadyCallback {
    void onSnapshotReady(Bitmap param1Bitmap);
  }
  
  private static final class zza extends zzd {
    private final GoogleMap.CancelableCallback zzai;
    
    zza(GoogleMap.CancelableCallback param1CancelableCallback) {
      this.zzai = param1CancelableCallback;
    }
    
    public final void onCancel() {
      this.zzai.onCancel();
    }
    
    public final void onFinish() {
      this.zzai.onFinish();
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\GoogleMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */