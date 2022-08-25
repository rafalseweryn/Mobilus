package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.ConnectionErrorMessages;
import java.util.LinkedList;

public abstract class DeferredLifecycleHelper<T extends LifecycleDelegate> {
  private T zzabc;
  
  private Bundle zzabd;
  
  private LinkedList<zza> zzabe;
  
  private final OnDelegateCreatedListener<T> zzabf = new zza(this);
  
  public static void showGooglePlayUnavailableMessage(FrameLayout paramFrameLayout) {
    GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
    Context context = paramFrameLayout.getContext();
    int i = googleApiAvailability.isGooglePlayServicesAvailable(context);
    String str1 = ConnectionErrorMessages.getErrorMessage(context, i);
    String str2 = ConnectionErrorMessages.getErrorDialogButtonMessage(context, i);
    LinearLayout linearLayout = new LinearLayout(paramFrameLayout.getContext());
    linearLayout.setOrientation(1);
    linearLayout.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2));
    paramFrameLayout.addView((View)linearLayout);
    TextView textView = new TextView(paramFrameLayout.getContext());
    textView.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2));
    textView.setText(str1);
    linearLayout.addView((View)textView);
    Intent intent = googleApiAvailability.getErrorResolutionIntent(context, i, null);
    if (intent != null) {
      Button button = new Button(context);
      button.setId(16908313);
      button.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2));
      button.setText(str2);
      linearLayout.addView((View)button);
      button.setOnClickListener(new zze(context, intent));
    } 
  }
  
  private final void zza(Bundle paramBundle, zza paramzza) {
    if (this.zzabc != null) {
      paramzza.zza((LifecycleDelegate)this.zzabc);
      return;
    } 
    if (this.zzabe == null)
      this.zzabe = new LinkedList<>(); 
    this.zzabe.add(paramzza);
    if (paramBundle != null)
      if (this.zzabd == null) {
        this.zzabd = (Bundle)paramBundle.clone();
      } else {
        this.zzabd.putAll(paramBundle);
      }  
    createDelegate(this.zzabf);
  }
  
  private final void zzm(int paramInt) {
    while (!this.zzabe.isEmpty() && ((zza)this.zzabe.getLast()).getState() >= paramInt)
      this.zzabe.removeLast(); 
  }
  
  protected abstract void createDelegate(OnDelegateCreatedListener<T> paramOnDelegateCreatedListener);
  
  public T getDelegate() {
    return this.zzabc;
  }
  
  protected void handleGooglePlayUnavailable(FrameLayout paramFrameLayout) {
    showGooglePlayUnavailableMessage(paramFrameLayout);
  }
  
  public void onCreate(Bundle paramBundle) {
    zza(paramBundle, new zzc(this, paramBundle));
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    FrameLayout frameLayout = new FrameLayout(paramLayoutInflater.getContext());
    zza(paramBundle, new zzd(this, frameLayout, paramLayoutInflater, paramViewGroup, paramBundle));
    if (this.zzabc == null)
      handleGooglePlayUnavailable(frameLayout); 
    return (View)frameLayout;
  }
  
  public void onDestroy() {
    if (this.zzabc != null) {
      this.zzabc.onDestroy();
      return;
    } 
    zzm(1);
  }
  
  public void onDestroyView() {
    if (this.zzabc != null) {
      this.zzabc.onDestroyView();
      return;
    } 
    zzm(2);
  }
  
  public void onInflate(Activity paramActivity, Bundle paramBundle1, Bundle paramBundle2) {
    zza(paramBundle2, new zzb(this, paramActivity, paramBundle1, paramBundle2));
  }
  
  public void onLowMemory() {
    if (this.zzabc != null)
      this.zzabc.onLowMemory(); 
  }
  
  public void onPause() {
    if (this.zzabc != null) {
      this.zzabc.onPause();
      return;
    } 
    zzm(5);
  }
  
  public void onResume() {
    zza((Bundle)null, new zzg(this));
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {
    if (this.zzabc != null) {
      this.zzabc.onSaveInstanceState(paramBundle);
      return;
    } 
    if (this.zzabd != null)
      paramBundle.putAll(this.zzabd); 
  }
  
  public void onStart() {
    zza((Bundle)null, new zzf(this));
  }
  
  public void onStop() {
    if (this.zzabc != null) {
      this.zzabc.onStop();
      return;
    } 
    zzm(4);
  }
  
  private static interface zza {
    int getState();
    
    void zza(LifecycleDelegate param1LifecycleDelegate);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\dynamic\DeferredLifecycleHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */