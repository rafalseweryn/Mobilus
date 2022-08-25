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
import com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzbp;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class StreetViewPanoramaView extends FrameLayout {
  private final zzb zzcc;
  
  public StreetViewPanoramaView(Context paramContext) {
    super(paramContext);
    this.zzcc = new zzb((ViewGroup)this, paramContext, null);
  }
  
  public StreetViewPanoramaView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    this.zzcc = new zzb((ViewGroup)this, paramContext, null);
  }
  
  public StreetViewPanoramaView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    this.zzcc = new zzb((ViewGroup)this, paramContext, null);
  }
  
  public StreetViewPanoramaView(Context paramContext, StreetViewPanoramaOptions paramStreetViewPanoramaOptions) {
    super(paramContext);
    this.zzcc = new zzb((ViewGroup)this, paramContext, paramStreetViewPanoramaOptions);
  }
  
  public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback paramOnStreetViewPanoramaReadyCallback) {
    Preconditions.checkMainThread("getStreetViewPanoramaAsync() must be called on the main thread");
    this.zzcc.getStreetViewPanoramaAsync(paramOnStreetViewPanoramaReadyCallback);
  }
  
  public final void onCreate(Bundle paramBundle) {
    StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
    StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder(threadPolicy)).permitAll().build());
    try {
      this.zzcc.onCreate(paramBundle);
      if (this.zzcc.getDelegate() == null)
        DeferredLifecycleHelper.showGooglePlayUnavailableMessage(this); 
      return;
    } finally {
      StrictMode.setThreadPolicy(threadPolicy);
    } 
  }
  
  public void onDestroy() {
    this.zzcc.onDestroy();
  }
  
  public final void onLowMemory() {
    this.zzcc.onLowMemory();
  }
  
  public final void onPause() {
    this.zzcc.onPause();
  }
  
  public void onResume() {
    this.zzcc.onResume();
  }
  
  public final void onSaveInstanceState(Bundle paramBundle) {
    this.zzcc.onSaveInstanceState(paramBundle);
  }
  
  public void onStart() {
    this.zzcc.onStart();
  }
  
  public void onStop() {
    this.zzcc.onStop();
  }
  
  @VisibleForTesting
  static final class zza implements StreetViewLifecycleDelegate {
    private final ViewGroup parent;
    
    private final IStreetViewPanoramaViewDelegate zzcd;
    
    private View zzce;
    
    public zza(ViewGroup param1ViewGroup, IStreetViewPanoramaViewDelegate param1IStreetViewPanoramaViewDelegate) {
      this.zzcd = (IStreetViewPanoramaViewDelegate)Preconditions.checkNotNull(param1IStreetViewPanoramaViewDelegate);
      this.parent = (ViewGroup)Preconditions.checkNotNull(param1ViewGroup);
    }
    
    public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback param1OnStreetViewPanoramaReadyCallback) {
      try {
        IStreetViewPanoramaViewDelegate iStreetViewPanoramaViewDelegate = this.zzcd;
        zzaj zzaj = new zzaj();
        this(this, param1OnStreetViewPanoramaReadyCallback);
        iStreetViewPanoramaViewDelegate.getStreetViewPanoramaAsync((zzbp)zzaj);
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
        this.zzcd.onCreate(bundle);
        zzby.zza(bundle, param1Bundle);
        this.zzce = (View)ObjectWrapper.unwrap(this.zzcd.getView());
        this.parent.removeAllViews();
        this.parent.addView(this.zzce);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final View onCreateView(LayoutInflater param1LayoutInflater, ViewGroup param1ViewGroup, Bundle param1Bundle) {
      throw new UnsupportedOperationException("onCreateView not allowed on StreetViewPanoramaViewDelegate");
    }
    
    public final void onDestroy() {
      try {
        this.zzcd.onDestroy();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onDestroyView() {
      throw new UnsupportedOperationException("onDestroyView not allowed on StreetViewPanoramaViewDelegate");
    }
    
    public final void onInflate(Activity param1Activity, Bundle param1Bundle1, Bundle param1Bundle2) {
      throw new UnsupportedOperationException("onInflate not allowed on StreetViewPanoramaViewDelegate");
    }
    
    public final void onLowMemory() {
      try {
        this.zzcd.onLowMemory();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onPause() {
      try {
        this.zzcd.onPause();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onResume() {
      try {
        this.zzcd.onResume();
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
        this.zzcd.onSaveInstanceState(bundle);
        zzby.zza(bundle, param1Bundle);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onStart() {
      try {
        this.zzcd.onStart();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
    
    public final void onStop() {
      try {
        this.zzcd.onStop();
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    }
  }
  
  @VisibleForTesting
  static final class zzb extends DeferredLifecycleHelper<zza> {
    private OnDelegateCreatedListener<StreetViewPanoramaView.zza> zzbc;
    
    private final ViewGroup zzbi;
    
    private final Context zzbj;
    
    private final List<OnStreetViewPanoramaReadyCallback> zzbv = new ArrayList<>();
    
    private final StreetViewPanoramaOptions zzcf;
    
    @VisibleForTesting
    zzb(ViewGroup param1ViewGroup, Context param1Context, StreetViewPanoramaOptions param1StreetViewPanoramaOptions) {
      this.zzbi = param1ViewGroup;
      this.zzbj = param1Context;
      this.zzcf = param1StreetViewPanoramaOptions;
    }
    
    protected final void createDelegate(OnDelegateCreatedListener<StreetViewPanoramaView.zza> param1OnDelegateCreatedListener) {
      this.zzbc = param1OnDelegateCreatedListener;
      if (this.zzbc != null && getDelegate() == null)
        try {
          MapsInitializer.initialize(this.zzbj);
          IStreetViewPanoramaViewDelegate iStreetViewPanoramaViewDelegate = zzbz.zza(this.zzbj).zza(ObjectWrapper.wrap(this.zzbj), this.zzcf);
          OnDelegateCreatedListener<StreetViewPanoramaView.zza> onDelegateCreatedListener = this.zzbc;
          StreetViewPanoramaView.zza zza = new StreetViewPanoramaView.zza();
          this(this.zzbi, iStreetViewPanoramaViewDelegate);
          onDelegateCreatedListener.onDelegateCreated((LifecycleDelegate)zza);
          for (OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback : this.zzbv)
            ((StreetViewPanoramaView.zza)getDelegate()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback); 
          this.zzbv.clear();
          return;
        } catch (RemoteException remoteException) {
          throw new RuntimeRemoteException(remoteException);
        } catch (GooglePlayServicesNotAvailableException googlePlayServicesNotAvailableException) {} 
    }
    
    public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback param1OnStreetViewPanoramaReadyCallback) {
      if (getDelegate() != null) {
        ((StreetViewPanoramaView.zza)getDelegate()).getStreetViewPanoramaAsync(param1OnStreetViewPanoramaReadyCallback);
        return;
      } 
      this.zzbv.add(param1OnStreetViewPanoramaReadyCallback);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\StreetViewPanoramaView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */