package com.google.android.gms.dynamic;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

final class zze implements View.OnClickListener {
  zze(Context paramContext) {}
  
  public final void onClick(View paramView) {
    try {
      context.startActivity(this.zzabl);
      return;
    } catch (ActivityNotFoundException activityNotFoundException) {
      Log.e("DeferredLifecycleHelper", "Failed to start resolution intent", (Throwable)activityNotFoundException);
      return;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\dynamic\zze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */