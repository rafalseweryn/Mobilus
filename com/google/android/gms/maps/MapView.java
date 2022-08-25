package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzap;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class MapView extends FrameLayout {
  private final zzb zzbf;
  
  public MapView(Context paramContext) {
    super(paramContext);
    this.zzbf = new zzb((ViewGroup)this, paramContext, null);
    setClickable(true);
  }
  
  public MapView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    this.zzbf = new zzb((ViewGroup)this, paramContext, GoogleMapOptions.createFromAttributes(paramContext, paramAttributeSet));
    setClickable(true);
  }
  
  public MapView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    this.zzbf = new zzb((ViewGroup)this, paramContext, GoogleMapOptions.createFromAttributes(paramContext, paramAttributeSet));
    setClickable(true);
  }
  
  public MapView(Context paramContext, GoogleMapOptions paramGoogleMapOptions) {
    super(paramContext);
    this.zzbf = new zzb((ViewGroup)this, paramContext, paramGoogleMapOptions);
    setClickable(true);
  }
  
  public void getMapAsync(OnMapReadyCallback paramOnMapReadyCallback) {
    Preconditions.checkMainThread("getMapAsync() must be called on the main thread");
    this.zzbf.getMapAsync(paramOnMapReadyCallback);
  }
  
  public final void onCreate(Bundle paramBundle) {
    StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
    StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder(threadPolicy)).permitAll().build());
    try {
      this.zzbf.onCreate(paramBundle);
      if (this.zzbf.getDelegate() == null)
        DeferredLifecycleHelper.showGooglePlayUnavailableMessage(this); 
      return;
    } finally {
      StrictMode.setThreadPolicy(threadPolicy);
    } 
  }
  
  public final void onDestroy() {
    this.zzbf.onDestroy();
  }
  
  public final void onEnterAmbient(Bundle paramBundle) {
    Preconditions.checkMainThread("onEnterAmbient() must be called on the main thread");
    zzb zzb1 = this.zzbf;
    if (zzb1.getDelegate() != null)
      ((zza)zzb1.getDelegate()).onEnterAmbient(paramBundle); 
  }
  
  public final void onExitAmbient() {
    Preconditions.checkMainThread("onExitAmbient() must be called on the main thread");
    zzb zzb1 = this.zzbf;
    if (zzb1.getDelegate() != null)
      ((zza)zzb1.getDelegate()).onExitAmbient(); 
  }
  
  public final void onLowMemory() {
    this.zzbf.onLowMemory();
  }
  
  public final void onPause() {
    this.zzbf.onPause();
  }
  
  public final void onResume() {
    this.zzbf.onResume();
  }
  
  public final void onSaveInstanceState(Bundle paramBundle) {
    this.zzbf.onSaveInstanceState(paramBundle);
  }
  
  public final void onStart() {
    this.zzbf.onStart();
  }
  
  public final void onStop() {
    this.zzbf.onStop();
  }
  
  @VisibleForTesting
  static final class zza implements MapLifecycleDelegate {
    private final ViewGroup parent;
    
    private final IMapViewDelegate zzbg;
    
    private View zzbh;
    
    public zza(ViewGroup param1ViewGroup, IMapViewDelegate param1IMapViewDelegate) {
      this.zzbg = (IMapViewDelegate)Preconditions.checkNotNull(param1IMapViewDelegate);
      this.parent = (ViewGroup)Preconditions.checkNotNull(param1ViewGroup);
    }
    
    public final void getMapAsync(OnMapReadyCallback param1OnMapReadyCallback) {
      try {
        IMapViewDelegate iMapViewDelegate = this.zzbg;
        zzac zzac = new zzac();
        this(this, param1OnMapReadyCallback);
        iMapViewDelegate.getMapAsync((zzap)zzac);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onCreate(Bundle param1Bundle) {
      try {
        Bundle bundle = new Bundle();
        this();
        zzby.zza(param1Bundle, bundle);
        this.zzbg.onCreate(bundle);
        zzby.zza(bundle, param1Bundle);
        this.zzbh = (View)ObjectWrapper.unwrap(this.zzbg.getView());
        this.parent.removeAllViews();
        this.parent.addView(this.zzbh);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final View onCreateView(LayoutInflater param1LayoutInflater, ViewGroup param1ViewGroup, Bundle param1Bundle) {
      throw new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
    }
    
    public final void onDestroy() {
      try {
        this.zzbg.onDestroy();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onDestroyView() {
      throw new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
    }
    
    public final void onEnterAmbient(Bundle param1Bundle) {
      try {
        Bundle bundle = new Bundle();
        this();
        zzby.zza(param1Bundle, bundle);
        this.zzbg.onEnterAmbient(bundle);
        zzby.zza(bundle, param1Bundle);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onExitAmbient() {
      try {
        this.zzbg.onExitAmbient();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onInflate(Activity param1Activity, Bundle param1Bundle1, Bundle param1Bundle2) {
      throw new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
    }
    
    public final void onLowMemory() {
      try {
        this.zzbg.onLowMemory();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onPause() {
      try {
        this.zzbg.onPause();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onResume() {
      try {
        this.zzbg.onResume();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onSaveInstanceState(Bundle param1Bundle) {
      try {
        Bundle bundle = new Bundle();
        this();
        zzby.zza(param1Bundle, bundle);
        this.zzbg.onSaveInstanceState(bundle);
        zzby.zza(bundle, param1Bundle);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onStart() {
      try {
        this.zzbg.onStart();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onStop() {
      try {
        this.zzbg.onStop();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
  }
  
  @VisibleForTesting
  static final class zzb extends DeferredLifecycleHelper<zza> {
    private OnDelegateCreatedListener<MapView.zza> zzbc;
    
    private final List<OnMapReadyCallback> zzbe = new ArrayList<>();
    
    private final ViewGroup zzbi;
    
    private final Context zzbj;
    
    private final GoogleMapOptions zzbk;
    
    @VisibleForTesting
    zzb(ViewGroup param1ViewGroup, Context param1Context, GoogleMapOptions param1GoogleMapOptions) {
      this.zzbi = param1ViewGroup;
      this.zzbj = param1Context;
      this.zzbk = param1GoogleMapOptions;
    }
    
    protected final void createDelegate(OnDelegateCreatedListener<MapView.zza> param1OnDelegateCreatedListener) {
      this.zzbc = param1OnDelegateCreatedListener;
      if (this.zzbc != null && getDelegate() == null)
        try {
          MapsInitializer.initialize(this.zzbj);
          IMapViewDelegate iMapViewDelegate = zzbz.zza(this.zzbj).zza(ObjectWrapper.wrap(this.zzbj), this.zzbk);
          if (iMapViewDelegate == null)
            return; 
          OnDelegateCreatedListener<MapView.zza> onDelegateCreatedListener = this.zzbc;
          MapView.zza zza = new MapView.zza();
          this(this.zzbi, iMapViewDelegate);
          onDelegateCreatedListener.onDelegateCreated((LifecycleDelegate)zza);
          for (OnMapReadyCallback onMapReadyCallback : this.zzbe)
            ((MapView.zza)getDelegate()).getMapAsync(onMapReadyCallback); 
          this.zzbe.clear();
          return;
        } catch (RemoteException remoteException) {
          throw new RuntimeRemoteException(remoteException);
        } catch (GooglePlayServicesNotAvailableException googlePlayServicesNotAvailableException) {} 
    }
    
    public final void getMapAsync(OnMapReadyCallback param1OnMapReadyCallback) {
      if (getDelegate() != null) {
        ((MapView.zza)getDelegate()).getMapAsync(param1OnMapReadyCallback);
        return;
      } 
      this.zzbe.add(param1OnMapReadyCallback);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\MapView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */