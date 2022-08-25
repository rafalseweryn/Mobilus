package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzaa;
import com.google.android.gms.internal.maps.zzac;
import com.google.android.gms.internal.maps.zzad;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zzh;
import com.google.android.gms.internal.maps.zzi;
import com.google.android.gms.internal.maps.zzk;
import com.google.android.gms.internal.maps.zzl;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.internal.maps.zzo;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.internal.maps.zzu;
import com.google.android.gms.internal.maps.zzw;
import com.google.android.gms.internal.maps.zzx;
import com.google.android.gms.internal.maps.zzz;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

public final class zzg extends zza implements IGoogleMapDelegate {
  zzg(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.internal.IGoogleMapDelegate");
  }
  
  public final zzh addCircle(CircleOptions paramCircleOptions) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramCircleOptions);
    parcel = transactAndReadException(35, parcel);
    zzh zzh = zzi.zzc(parcel.readStrongBinder());
    parcel.recycle();
    return zzh;
  }
  
  public final zzk addGroundOverlay(GroundOverlayOptions paramGroundOverlayOptions) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzc.zza(parcel2, (Parcelable)paramGroundOverlayOptions);
    Parcel parcel1 = transactAndReadException(12, parcel2);
    zzk zzk = zzl.zzd(parcel1.readStrongBinder());
    parcel1.recycle();
    return zzk;
  }
  
  public final zzt addMarker(MarkerOptions paramMarkerOptions) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramMarkerOptions);
    parcel = transactAndReadException(11, parcel);
    zzt zzt = zzu.zzg(parcel.readStrongBinder());
    parcel.recycle();
    return zzt;
  }
  
  public final zzw addPolygon(PolygonOptions paramPolygonOptions) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzc.zza(parcel2, (Parcelable)paramPolygonOptions);
    Parcel parcel1 = transactAndReadException(10, parcel2);
    zzw zzw = zzx.zzh(parcel1.readStrongBinder());
    parcel1.recycle();
    return zzw;
  }
  
  public final zzz addPolyline(PolylineOptions paramPolylineOptions) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzc.zza(parcel2, (Parcelable)paramPolylineOptions);
    Parcel parcel1 = transactAndReadException(9, parcel2);
    zzz zzz = zzaa.zzi(parcel1.readStrongBinder());
    parcel1.recycle();
    return zzz;
  }
  
  public final zzac addTileOverlay(TileOverlayOptions paramTileOverlayOptions) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzc.zza(parcel2, (Parcelable)paramTileOverlayOptions);
    Parcel parcel1 = transactAndReadException(13, parcel2);
    zzac zzac = zzad.zzj(parcel1.readStrongBinder());
    parcel1.recycle();
    return zzac;
  }
  
  public final void animateCamera(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (IInterface)paramIObjectWrapper);
    transactAndReadExceptionReturnVoid(5, parcel);
  }
  
  public final void animateCameraWithCallback(IObjectWrapper paramIObjectWrapper, zzc paramzzc) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (IInterface)paramIObjectWrapper);
    zzc.zza(parcel, paramzzc);
    transactAndReadExceptionReturnVoid(6, parcel);
  }
  
  public final void animateCameraWithDurationAndCallback(IObjectWrapper paramIObjectWrapper, int paramInt, zzc paramzzc) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (IInterface)paramIObjectWrapper);
    parcel.writeInt(paramInt);
    zzc.zza(parcel, paramzzc);
    transactAndReadExceptionReturnVoid(7, parcel);
  }
  
  public final void clear() throws RemoteException {
    transactAndReadExceptionReturnVoid(14, obtainAndWriteInterfaceToken());
  }
  
  public final CameraPosition getCameraPosition() throws RemoteException {
    Parcel parcel = transactAndReadException(1, obtainAndWriteInterfaceToken());
    CameraPosition cameraPosition = (CameraPosition)zzc.zza(parcel, CameraPosition.CREATOR);
    parcel.recycle();
    return cameraPosition;
  }
  
  public final zzn getFocusedBuilding() throws RemoteException {
    Parcel parcel = transactAndReadException(44, obtainAndWriteInterfaceToken());
    zzn zzn = zzo.zze(parcel.readStrongBinder());
    parcel.recycle();
    return zzn;
  }
  
  public final void getMapAsync(zzap paramzzap) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzap);
    transactAndReadExceptionReturnVoid(53, parcel);
  }
  
  public final int getMapType() throws RemoteException {
    Parcel parcel = transactAndReadException(15, obtainAndWriteInterfaceToken());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final float getMaxZoomLevel() throws RemoteException {
    Parcel parcel = transactAndReadException(2, obtainAndWriteInterfaceToken());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final float getMinZoomLevel() throws RemoteException {
    Parcel parcel = transactAndReadException(3, obtainAndWriteInterfaceToken());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final Location getMyLocation() throws RemoteException {
    Parcel parcel = transactAndReadException(23, obtainAndWriteInterfaceToken());
    Location location = (Location)zzc.zza(parcel, Location.CREATOR);
    parcel.recycle();
    return location;
  }
  
  public final IProjectionDelegate getProjection() throws RemoteException {
    IProjectionDelegate iProjectionDelegate;
    Parcel parcel = transactAndReadException(26, obtainAndWriteInterfaceToken());
    IBinder iBinder = parcel.readStrongBinder();
    if (iBinder == null) {
      iBinder = null;
    } else {
      IInterface iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
      if (iInterface instanceof IProjectionDelegate) {
        iProjectionDelegate = (IProjectionDelegate)iInterface;
      } else {
        iProjectionDelegate = new zzbr((IBinder)iProjectionDelegate);
      } 
    } 
    parcel.recycle();
    return iProjectionDelegate;
  }
  
  public final IUiSettingsDelegate getUiSettings() throws RemoteException {
    IInterface iInterface;
    Parcel parcel = transactAndReadException(25, obtainAndWriteInterfaceToken());
    IBinder iBinder = parcel.readStrongBinder();
    if (iBinder == null) {
      iInterface = null;
    } else {
      iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
      if (iInterface instanceof IUiSettingsDelegate) {
        iInterface = iInterface;
      } else {
        iInterface = new zzbx(iBinder);
      } 
    } 
    parcel.recycle();
    return (IUiSettingsDelegate)iInterface;
  }
  
  public final boolean isBuildingsEnabled() throws RemoteException {
    Parcel parcel = transactAndReadException(40, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isIndoorEnabled() throws RemoteException {
    Parcel parcel = transactAndReadException(19, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isMyLocationEnabled() throws RemoteException {
    Parcel parcel = transactAndReadException(21, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isTrafficEnabled() throws RemoteException {
    Parcel parcel = transactAndReadException(17, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final void moveCamera(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (IInterface)paramIObjectWrapper);
    transactAndReadExceptionReturnVoid(4, parcel);
  }
  
  public final void onCreate(Bundle paramBundle) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramBundle);
    transactAndReadExceptionReturnVoid(54, parcel);
  }
  
  public final void onDestroy() throws RemoteException {
    transactAndReadExceptionReturnVoid(57, obtainAndWriteInterfaceToken());
  }
  
  public final void onEnterAmbient(Bundle paramBundle) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramBundle);
    transactAndReadExceptionReturnVoid(81, parcel);
  }
  
  public final void onExitAmbient() throws RemoteException {
    transactAndReadExceptionReturnVoid(82, obtainAndWriteInterfaceToken());
  }
  
  public final void onLowMemory() throws RemoteException {
    transactAndReadExceptionReturnVoid(58, obtainAndWriteInterfaceToken());
  }
  
  public final void onPause() throws RemoteException {
    transactAndReadExceptionReturnVoid(56, obtainAndWriteInterfaceToken());
  }
  
  public final void onResume() throws RemoteException {
    transactAndReadExceptionReturnVoid(55, obtainAndWriteInterfaceToken());
  }
  
  public final void onSaveInstanceState(Bundle paramBundle) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramBundle);
    parcel = transactAndReadException(60, parcel);
    if (parcel.readInt() != 0)
      paramBundle.readFromParcel(parcel); 
    parcel.recycle();
  }
  
  public final void onStart() throws RemoteException {
    transactAndReadExceptionReturnVoid(101, obtainAndWriteInterfaceToken());
  }
  
  public final void onStop() throws RemoteException {
    transactAndReadExceptionReturnVoid(102, obtainAndWriteInterfaceToken());
  }
  
  public final void resetMinMaxZoomPreference() throws RemoteException {
    transactAndReadExceptionReturnVoid(94, obtainAndWriteInterfaceToken());
  }
  
  public final void setBuildingsEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(41, parcel);
  }
  
  public final void setContentDescription(String paramString) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeString(paramString);
    transactAndReadExceptionReturnVoid(61, parcel);
  }
  
  public final boolean setIndoorEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    parcel = transactAndReadException(20, parcel);
    paramBoolean = zzc.zza(parcel);
    parcel.recycle();
    return paramBoolean;
  }
  
  public final void setInfoWindowAdapter(zzh paramzzh) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzh);
    transactAndReadExceptionReturnVoid(33, parcel);
  }
  
  public final void setLatLngBoundsForCameraTarget(LatLngBounds paramLatLngBounds) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramLatLngBounds);
    transactAndReadExceptionReturnVoid(95, parcel);
  }
  
  public final void setLocationSource(ILocationSourceDelegate paramILocationSourceDelegate) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramILocationSourceDelegate);
    transactAndReadExceptionReturnVoid(24, parcel);
  }
  
  public final boolean setMapStyle(MapStyleOptions paramMapStyleOptions) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzc.zza(parcel2, (Parcelable)paramMapStyleOptions);
    Parcel parcel1 = transactAndReadException(91, parcel2);
    boolean bool = zzc.zza(parcel1);
    parcel1.recycle();
    return bool;
  }
  
  public final void setMapType(int paramInt) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeInt(paramInt);
    transactAndReadExceptionReturnVoid(16, parcel);
  }
  
  public final void setMaxZoomPreference(float paramFloat) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeFloat(paramFloat);
    transactAndReadExceptionReturnVoid(93, parcel);
  }
  
  public final void setMinZoomPreference(float paramFloat) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeFloat(paramFloat);
    transactAndReadExceptionReturnVoid(92, parcel);
  }
  
  public final void setMyLocationEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(22, parcel);
  }
  
  public final void setOnCameraChangeListener(zzl paramzzl) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzl);
    transactAndReadExceptionReturnVoid(27, parcel);
  }
  
  public final void setOnCameraIdleListener(zzn paramzzn) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzn);
    transactAndReadExceptionReturnVoid(99, parcel);
  }
  
  public final void setOnCameraMoveCanceledListener(zzp paramzzp) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzp);
    transactAndReadExceptionReturnVoid(98, parcel);
  }
  
  public final void setOnCameraMoveListener(zzr paramzzr) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzr);
    transactAndReadExceptionReturnVoid(97, parcel);
  }
  
  public final void setOnCameraMoveStartedListener(zzt paramzzt) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzt);
    transactAndReadExceptionReturnVoid(96, parcel);
  }
  
  public final void setOnCircleClickListener(zzv paramzzv) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzv);
    transactAndReadExceptionReturnVoid(89, parcel);
  }
  
  public final void setOnGroundOverlayClickListener(zzx paramzzx) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzx);
    transactAndReadExceptionReturnVoid(83, parcel);
  }
  
  public final void setOnIndoorStateChangeListener(zzz paramzzz) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzz);
    transactAndReadExceptionReturnVoid(45, parcel);
  }
  
  public final void setOnInfoWindowClickListener(zzab paramzzab) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzab);
    transactAndReadExceptionReturnVoid(32, parcel);
  }
  
  public final void setOnInfoWindowCloseListener(zzad paramzzad) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzad);
    transactAndReadExceptionReturnVoid(86, parcel);
  }
  
  public final void setOnInfoWindowLongClickListener(zzaf paramzzaf) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzaf);
    transactAndReadExceptionReturnVoid(84, parcel);
  }
  
  public final void setOnMapClickListener(zzaj paramzzaj) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzaj);
    transactAndReadExceptionReturnVoid(28, parcel);
  }
  
  public final void setOnMapLoadedCallback(zzal paramzzal) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzal);
    transactAndReadExceptionReturnVoid(42, parcel);
  }
  
  public final void setOnMapLongClickListener(zzan paramzzan) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzan);
    transactAndReadExceptionReturnVoid(29, parcel);
  }
  
  public final void setOnMarkerClickListener(zzar paramzzar) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzar);
    transactAndReadExceptionReturnVoid(30, parcel);
  }
  
  public final void setOnMarkerDragListener(zzat paramzzat) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzat);
    transactAndReadExceptionReturnVoid(31, parcel);
  }
  
  public final void setOnMyLocationButtonClickListener(zzav paramzzav) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzav);
    transactAndReadExceptionReturnVoid(37, parcel);
  }
  
  public final void setOnMyLocationChangeListener(zzax paramzzax) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzax);
    transactAndReadExceptionReturnVoid(36, parcel);
  }
  
  public final void setOnMyLocationClickListener(zzaz paramzzaz) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzaz);
    transactAndReadExceptionReturnVoid(107, parcel);
  }
  
  public final void setOnPoiClickListener(zzbb paramzzbb) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzbb);
    transactAndReadExceptionReturnVoid(80, parcel);
  }
  
  public final void setOnPolygonClickListener(zzbd paramzzbd) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzbd);
    transactAndReadExceptionReturnVoid(85, parcel);
  }
  
  public final void setOnPolylineClickListener(zzbf paramzzbf) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzbf);
    transactAndReadExceptionReturnVoid(87, parcel);
  }
  
  public final void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeInt(paramInt1);
    parcel.writeInt(paramInt2);
    parcel.writeInt(paramInt3);
    parcel.writeInt(paramInt4);
    transactAndReadExceptionReturnVoid(39, parcel);
  }
  
  public final void setTrafficEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(18, parcel);
  }
  
  public final void setWatermarkEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(51, parcel);
  }
  
  public final void snapshot(zzbs paramzzbs, IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzbs);
    zzc.zza(parcel, (IInterface)paramIObjectWrapper);
    transactAndReadExceptionReturnVoid(38, parcel);
  }
  
  public final void snapshotForTest(zzbs paramzzbs) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramzzbs);
    transactAndReadExceptionReturnVoid(71, parcel);
  }
  
  public final void stopAnimation() throws RemoteException {
    transactAndReadExceptionReturnVoid(8, obtainAndWriteInterfaceToken());
  }
  
  public final boolean useViewLifecycleWhenInFragment() throws RemoteException {
    Parcel parcel = transactAndReadException(59, obtainAndWriteInterfaceToken());
    boolean bool = zzc.zza(parcel);
    parcel.recycle();
    return bool;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\internal\zzg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */