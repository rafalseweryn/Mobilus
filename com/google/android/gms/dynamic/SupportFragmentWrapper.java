package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

public final class SupportFragmentWrapper extends IFragmentWrapper.Stub {
  private Fragment zzabq;
  
  private SupportFragmentWrapper(Fragment paramFragment) {
    this.zzabq = paramFragment;
  }
  
  public static SupportFragmentWrapper wrap(Fragment paramFragment) {
    return (paramFragment != null) ? new SupportFragmentWrapper(paramFragment) : null;
  }
  
  public final IObjectWrapper getActivity() {
    return ObjectWrapper.wrap(this.zzabq.getActivity());
  }
  
  public final Bundle getArguments() {
    return this.zzabq.getArguments();
  }
  
  public final int getId() {
    return this.zzabq.getId();
  }
  
  public final IFragmentWrapper getParentFragment() {
    return wrap(this.zzabq.getParentFragment());
  }
  
  public final IObjectWrapper getResources() {
    return ObjectWrapper.wrap(this.zzabq.getResources());
  }
  
  public final boolean getRetainInstance() {
    return this.zzabq.getRetainInstance();
  }
  
  public final String getTag() {
    return this.zzabq.getTag();
  }
  
  public final IFragmentWrapper getTargetFragment() {
    return wrap(this.zzabq.getTargetFragment());
  }
  
  public final int getTargetRequestCode() {
    return this.zzabq.getTargetRequestCode();
  }
  
  public final boolean getUserVisibleHint() {
    return this.zzabq.getUserVisibleHint();
  }
  
  public final IObjectWrapper getView() {
    return ObjectWrapper.wrap(this.zzabq.getView());
  }
  
  public final boolean isAdded() {
    return this.zzabq.isAdded();
  }
  
  public final boolean isDetached() {
    return this.zzabq.isDetached();
  }
  
  public final boolean isHidden() {
    return this.zzabq.isHidden();
  }
  
  public final boolean isInLayout() {
    return this.zzabq.isInLayout();
  }
  
  public final boolean isRemoving() {
    return this.zzabq.isRemoving();
  }
  
  public final boolean isResumed() {
    return this.zzabq.isResumed();
  }
  
  public final boolean isVisible() {
    return this.zzabq.isVisible();
  }
  
  public final void registerForContextMenu(IObjectWrapper paramIObjectWrapper) {
    View view = ObjectWrapper.<View>unwrap(paramIObjectWrapper);
    this.zzabq.registerForContextMenu(view);
  }
  
  public final void setHasOptionsMenu(boolean paramBoolean) {
    this.zzabq.setHasOptionsMenu(paramBoolean);
  }
  
  public final void setMenuVisibility(boolean paramBoolean) {
    this.zzabq.setMenuVisibility(paramBoolean);
  }
  
  public final void setRetainInstance(boolean paramBoolean) {
    this.zzabq.setRetainInstance(paramBoolean);
  }
  
  public final void setUserVisibleHint(boolean paramBoolean) {
    this.zzabq.setUserVisibleHint(paramBoolean);
  }
  
  public final void startActivity(Intent paramIntent) {
    this.zzabq.startActivity(paramIntent);
  }
  
  public final void startActivityForResult(Intent paramIntent, int paramInt) {
    this.zzabq.startActivityForResult(paramIntent, paramInt);
  }
  
  public final void unregisterForContextMenu(IObjectWrapper paramIObjectWrapper) {
    View view = ObjectWrapper.<View>unwrap(paramIObjectWrapper);
    this.zzabq.unregisterForContextMenu(view);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\dynamic\SupportFragmentWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */