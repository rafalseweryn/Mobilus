package com.google.android.gms.maps;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.StrictMode;
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
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzbp;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

@TargetApi(11)
public class StreetViewPanoramaFragment extends Fragment {
  private final zzb zzbs = new zzb(this);
  
  public static StreetViewPanoramaFragment newInstance() {
    return new StreetViewPanoramaFragment();
  }
  
  public static StreetViewPanoramaFragment newInstance(StreetViewPanoramaOptions paramStreetViewPanoramaOptions) {
    StreetViewPanoramaFragment streetViewPanoramaFragment = new StreetViewPanoramaFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelable("StreetViewPanoramaOptions", (Parcelable)paramStreetViewPanoramaOptions);
    streetViewPanoramaFragment.setArguments(bundle);
    return streetViewPanoramaFragment;
  }
  
  public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback paramOnStreetViewPanoramaReadyCallback) {
    Preconditions.checkMainThread("getStreetViewPanoramaAsync() must be called on the main thread");
    this.zzbs.getStreetViewPanoramaAsync(paramOnStreetViewPanoramaReadyCallback);
  }
  
  public void onActivityCreated(Bundle paramBundle) {
    if (paramBundle != null)
      paramBundle.setClassLoader(StreetViewPanoramaFragment.class.getClassLoader()); 
    super.onActivityCreated(paramBundle);
  }
  
  public void onAttach(Activity paramActivity) {
    super.onAttach(paramActivity);
    zzb.zza(this.zzbs, paramActivity);
  }
  
  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    this.zzbs.onCreate(paramBundle);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    return this.zzbs.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
  }
  
  public void onDestroy() {
    this.zzbs.onDestroy();
    super.onDestroy();
  }
  
  public void onDestroyView() {
    this.zzbs.onDestroyView();
    super.onDestroyView();
  }
  
  @SuppressLint({"NewApi"})
  public void onInflate(Activity paramActivity, AttributeSet paramAttributeSet, Bundle paramBundle) {
    StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
    StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder(threadPolicy)).permitAll().build());
    try {
      super.onInflate(paramActivity, paramAttributeSet, paramBundle);
      zzb.zza(this.zzbs, paramActivity);
      Bundle bundle = new Bundle();
      this();
      this.zzbs.onInflate(paramActivity, bundle, paramBundle);
      return;
    } finally {
      StrictMode.setThreadPolicy(threadPolicy);
    } 
  }
  
  public void onLowMemory() {
    this.zzbs.onLowMemory();
    super.onLowMemory();
  }
  
  public void onPause() {
    this.zzbs.onPause();
    super.onPause();
  }
  
  public void onResume() {
    super.onResume();
    this.zzbs.onResume();
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {
    if (paramBundle != null)
      paramBundle.setClassLoader(StreetViewPanoramaFragment.class.getClassLoader()); 
    super.onSaveInstanceState(paramBundle);
    this.zzbs.onSaveInstanceState(paramBundle);
  }
  
  public void onStart() {
    super.onStart();
    this.zzbs.onStart();
  }
  
  public void onStop() {
    this.zzbs.onStop();
    super.onStop();
  }
  
  public void setArguments(Bundle paramBundle) {
    super.setArguments(paramBundle);
  }
  
  @VisibleForTesting
  static final class zza implements StreetViewLifecycleDelegate {
    private final Fragment zzaz;
    
    private final IStreetViewPanoramaFragmentDelegate zzbt;
    
    public zza(Fragment param1Fragment, IStreetViewPanoramaFragmentDelegate param1IStreetViewPanoramaFragmentDelegate) {
      this.zzbt = (IStreetViewPanoramaFragmentDelegate)Preconditions.checkNotNull(param1IStreetViewPanoramaFragmentDelegate);
      this.zzaz = (Fragment)Preconditions.checkNotNull(param1Fragment);
    }
    
    public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback param1OnStreetViewPanoramaReadyCallback) {
      try {
        IStreetViewPanoramaFragmentDelegate iStreetViewPanoramaFragmentDelegate = this.zzbt;
        zzah zzah = new zzah();
        this(this, param1OnStreetViewPanoramaReadyCallback);
        iStreetViewPanoramaFragmentDelegate.getStreetViewPanoramaAsync((zzbp)zzah);
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
        Bundle bundle2 = this.zzaz.getArguments();
        if (bundle2 != null && bundle2.containsKey("StreetViewPanoramaOptions"))
          zzby.zza(bundle1, "StreetViewPanoramaOptions", bundle2.getParcelable("StreetViewPanoramaOptions")); 
        this.zzbt.onCreate(bundle1);
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
        IObjectWrapper iObjectWrapper = this.zzbt.onCreateView(ObjectWrapper.wrap(param1LayoutInflater), ObjectWrapper.wrap(param1ViewGroup), bundle);
        zzby.zza(bundle, param1Bundle);
        return (View)ObjectWrapper.unwrap(iObjectWrapper);
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onDestroy() {
      try {
        this.zzbt.onDestroy();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onDestroyView() {
      try {
        this.zzbt.onDestroyView();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onInflate(Activity param1Activity, Bundle param1Bundle1, Bundle param1Bundle2) {
      try {
        param1Bundle1 = new Bundle();
        this();
        zzby.zza(param1Bundle2, param1Bundle1);
        this.zzbt.onInflate(ObjectWrapper.wrap(param1Activity), null, param1Bundle1);
        zzby.zza(param1Bundle1, param1Bundle2);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onLowMemory() {
      try {
        this.zzbt.onLowMemory();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onPause() {
      try {
        this.zzbt.onPause();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onResume() {
      try {
        this.zzbt.onResume();
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
        this.zzbt.onSaveInstanceState(bundle);
        zzby.zza(bundle, param1Bundle);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onStart() {
      try {
        this.zzbt.onStart();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onStop() {
      try {
        this.zzbt.onStop();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
  }
  
  @VisibleForTesting
  static final class zzb extends DeferredLifecycleHelper<zza> {
    private final Fragment zzaz;
    
    private OnDelegateCreatedListener<StreetViewPanoramaFragment.zza> zzbc;
    
    private Activity zzbd;
    
    private final List<OnStreetViewPanoramaReadyCallback> zzbv = new ArrayList<>();
    
    @VisibleForTesting
    zzb(Fragment param1Fragment) {
      this.zzaz = param1Fragment;
    }
    
    private final void setActivity(Activity param1Activity) {
      this.zzbd = param1Activity;
      zzc();
    }
    
    private final void zzc() {
      if (this.zzbd != null && this.zzbc != null && getDelegate() == null)
        try {
          MapsInitializer.initialize((Context)this.zzbd);
          IStreetViewPanoramaFragmentDelegate iStreetViewPanoramaFragmentDelegate = zzbz.zza((Context)this.zzbd).zzd(ObjectWrapper.wrap(this.zzbd));
          OnDelegateCreatedListener<StreetViewPanoramaFragment.zza> onDelegateCreatedListener = this.zzbc;
          StreetViewPanoramaFragment.zza zza = new StreetViewPanoramaFragment.zza();
          this(this.zzaz, iStreetViewPanoramaFragmentDelegate);
          onDelegateCreatedListener.onDelegateCreated((LifecycleDelegate)zza);
          for (OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback : this.zzbv)
            ((StreetViewPanoramaFragment.zza)getDelegate()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback); 
          this.zzbv.clear();
          return;
        } catch (RemoteException remoteException) {
          throw new RuntimeRemoteException(remoteException);
        } catch (GooglePlayServicesNotAvailableException googlePlayServicesNotAvailableException) {} 
    }
    
    protected final void createDelegate(OnDelegateCreatedListener<StreetViewPanoramaFragment.zza> param1OnDelegateCreatedListener) {
      this.zzbc = param1OnDelegateCreatedListener;
      zzc();
    }
    
    public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback param1OnStreetViewPanoramaReadyCallback) {
      if (getDelegate() != null) {
        ((StreetViewPanoramaFragment.zza)getDelegate()).getStreetViewPanoramaAsync(param1OnStreetViewPanoramaReadyCallback);
        return;
      } 
      this.zzbv.add(param1OnStreetViewPanoramaReadyCallback);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\StreetViewPanoramaFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */