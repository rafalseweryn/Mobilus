package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzap;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class SupportMapFragment extends Fragment {
  private final zzb zzcg = new zzb(this);
  
  public static SupportMapFragment newInstance() {
    return new SupportMapFragment();
  }
  
  public static SupportMapFragment newInstance(GoogleMapOptions paramGoogleMapOptions) {
    SupportMapFragment supportMapFragment = new SupportMapFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelable("MapOptions", (Parcelable)paramGoogleMapOptions);
    supportMapFragment.setArguments(bundle);
    return supportMapFragment;
  }
  
  public void getMapAsync(OnMapReadyCallback paramOnMapReadyCallback) {
    Preconditions.checkMainThread("getMapAsync must be called on the main thread.");
    this.zzcg.getMapAsync(paramOnMapReadyCallback);
  }
  
  public void onActivityCreated(Bundle paramBundle) {
    if (paramBundle != null)
      paramBundle.setClassLoader(SupportMapFragment.class.getClassLoader()); 
    super.onActivityCreated(paramBundle);
  }
  
  public void onAttach(Activity paramActivity) {
    super.onAttach(paramActivity);
    zzb.zza(this.zzcg, paramActivity);
  }
  
  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    this.zzcg.onCreate(paramBundle);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view = this.zzcg.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    view.setClickable(true);
    return view;
  }
  
  public void onDestroy() {
    this.zzcg.onDestroy();
    super.onDestroy();
  }
  
  public void onDestroyView() {
    this.zzcg.onDestroyView();
    super.onDestroyView();
  }
  
  public final void onEnterAmbient(Bundle paramBundle) {
    Preconditions.checkMainThread("onEnterAmbient must be called on the main thread.");
    zzb zzb1 = this.zzcg;
    if (zzb1.getDelegate() != null)
      ((zza)zzb1.getDelegate()).onEnterAmbient(paramBundle); 
  }
  
  public final void onExitAmbient() {
    Preconditions.checkMainThread("onExitAmbient must be called on the main thread.");
    zzb zzb1 = this.zzcg;
    if (zzb1.getDelegate() != null)
      ((zza)zzb1.getDelegate()).onExitAmbient(); 
  }
  
  public void onInflate(Activity paramActivity, AttributeSet paramAttributeSet, Bundle paramBundle) {
    StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
    StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder(threadPolicy)).permitAll().build());
    try {
      super.onInflate(paramActivity, paramAttributeSet, paramBundle);
      zzb.zza(this.zzcg, paramActivity);
      GoogleMapOptions googleMapOptions = GoogleMapOptions.createFromAttributes((Context)paramActivity, paramAttributeSet);
      Bundle bundle = new Bundle();
      this();
      bundle.putParcelable("MapOptions", (Parcelable)googleMapOptions);
      this.zzcg.onInflate(paramActivity, bundle, paramBundle);
      return;
    } finally {
      StrictMode.setThreadPolicy(threadPolicy);
    } 
  }
  
  public void onLowMemory() {
    this.zzcg.onLowMemory();
    super.onLowMemory();
  }
  
  public void onPause() {
    this.zzcg.onPause();
    super.onPause();
  }
  
  public void onResume() {
    super.onResume();
    this.zzcg.onResume();
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {
    if (paramBundle != null)
      paramBundle.setClassLoader(SupportMapFragment.class.getClassLoader()); 
    super.onSaveInstanceState(paramBundle);
    this.zzcg.onSaveInstanceState(paramBundle);
  }
  
  public void onStart() {
    super.onStart();
    this.zzcg.onStart();
  }
  
  public void onStop() {
    this.zzcg.onStop();
    super.onStop();
  }
  
  public void setArguments(Bundle paramBundle) {
    super.setArguments(paramBundle);
  }
  
  @VisibleForTesting
  static final class zza implements MapLifecycleDelegate {
    private final Fragment fragment;
    
    private final IMapFragmentDelegate zzba;
    
    public zza(Fragment param1Fragment, IMapFragmentDelegate param1IMapFragmentDelegate) {
      this.zzba = (IMapFragmentDelegate)Preconditions.checkNotNull(param1IMapFragmentDelegate);
      this.fragment = (Fragment)Preconditions.checkNotNull(param1Fragment);
    }
    
    public final void getMapAsync(OnMapReadyCallback param1OnMapReadyCallback) {
      try {
        IMapFragmentDelegate iMapFragmentDelegate = this.zzba;
        zzak zzak = new zzak();
        this(this, param1OnMapReadyCallback);
        iMapFragmentDelegate.getMapAsync((zzap)zzak);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onCreate(Bundle param1Bundle) {
      try {
        Bundle bundle1 = new Bundle();
        this();
        zzby.zza(param1Bundle, bundle1);
        Bundle bundle2 = this.fragment.getArguments();
        if (bundle2 != null && bundle2.containsKey("MapOptions"))
          zzby.zza(bundle1, "MapOptions", bundle2.getParcelable("MapOptions")); 
        this.zzba.onCreate(bundle1);
        zzby.zza(bundle1, param1Bundle);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final View onCreateView(LayoutInflater param1LayoutInflater, ViewGroup param1ViewGroup, Bundle param1Bundle) {
      try {
        Bundle bundle = new Bundle();
        this();
        zzby.zza(param1Bundle, bundle);
        IObjectWrapper iObjectWrapper = this.zzba.onCreateView(ObjectWrapper.wrap(param1LayoutInflater), ObjectWrapper.wrap(param1ViewGroup), bundle);
        zzby.zza(bundle, param1Bundle);
        return (View)ObjectWrapper.unwrap(iObjectWrapper);
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onDestroy() {
      try {
        this.zzba.onDestroy();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onDestroyView() {
      try {
        this.zzba.onDestroyView();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onEnterAmbient(Bundle param1Bundle) {
      try {
        Bundle bundle = new Bundle();
        this();
        zzby.zza(param1Bundle, bundle);
        this.zzba.onEnterAmbient(bundle);
        zzby.zza(bundle, param1Bundle);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onExitAmbient() {
      try {
        this.zzba.onExitAmbient();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onInflate(Activity param1Activity, Bundle param1Bundle1, Bundle param1Bundle2) {
      GoogleMapOptions googleMapOptions = (GoogleMapOptions)param1Bundle1.getParcelable("MapOptions");
      try {
        Bundle bundle = new Bundle();
        this();
        zzby.zza(param1Bundle2, bundle);
        this.zzba.onInflate(ObjectWrapper.wrap(param1Activity), googleMapOptions, bundle);
        zzby.zza(bundle, param1Bundle2);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onLowMemory() {
      try {
        this.zzba.onLowMemory();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onPause() {
      try {
        this.zzba.onPause();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onResume() {
      try {
        this.zzba.onResume();
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
        this.zzba.onSaveInstanceState(bundle);
        zzby.zza(bundle, param1Bundle);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onStart() {
      try {
        this.zzba.onStart();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onStop() {
      try {
        this.zzba.onStop();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
  }
  
  @VisibleForTesting
  static final class zzb extends DeferredLifecycleHelper<zza> {
    private final Fragment fragment;
    
    private OnDelegateCreatedListener<SupportMapFragment.zza> zzbc;
    
    private Activity zzbd;
    
    private final List<OnMapReadyCallback> zzbe = new ArrayList<>();
    
    @VisibleForTesting
    zzb(Fragment param1Fragment) {
      this.fragment = param1Fragment;
    }
    
    private final void setActivity(Activity param1Activity) {
      this.zzbd = param1Activity;
      zzc();
    }
    
    private final void zzc() {
      if (this.zzbd != null && this.zzbc != null && getDelegate() == null)
        try {
          MapsInitializer.initialize((Context)this.zzbd);
          IMapFragmentDelegate iMapFragmentDelegate = zzbz.zza((Context)this.zzbd).zzc(ObjectWrapper.wrap(this.zzbd));
          if (iMapFragmentDelegate == null)
            return; 
          OnDelegateCreatedListener<SupportMapFragment.zza> onDelegateCreatedListener = this.zzbc;
          SupportMapFragment.zza zza = new SupportMapFragment.zza();
          this(this.fragment, iMapFragmentDelegate);
          onDelegateCreatedListener.onDelegateCreated((LifecycleDelegate)zza);
          for (OnMapReadyCallback onMapReadyCallback : this.zzbe)
            ((SupportMapFragment.zza)getDelegate()).getMapAsync(onMapReadyCallback); 
          this.zzbe.clear();
          return;
        } catch (RemoteException remoteException) {
          throw new RuntimeRemoteException(remoteException);
        } catch (GooglePlayServicesNotAvailableException googlePlayServicesNotAvailableException) {} 
    }
    
    protected final void createDelegate(OnDelegateCreatedListener<SupportMapFragment.zza> param1OnDelegateCreatedListener) {
      this.zzbc = param1OnDelegateCreatedListener;
      zzc();
    }
    
    public final void getMapAsync(OnMapReadyCallback param1OnMapReadyCallback) {
      if (getDelegate() != null) {
        ((SupportMapFragment.zza)getDelegate()).getMapAsync(param1OnMapReadyCallback);
        return;
      } 
      this.zzbe.add(param1OnMapReadyCallback);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\SupportMapFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */