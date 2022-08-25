package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepName;

@KeepName
public final class BinderWrapper implements Parcelable {
  public static final Parcelable.Creator<BinderWrapper> CREATOR = new zza();
  
  private IBinder zzry = null;
  
  public BinderWrapper() {}
  
  public BinderWrapper(IBinder paramIBinder) {
    this.zzry = paramIBinder;
  }
  
  private BinderWrapper(Parcel paramParcel) {
    this.zzry = paramParcel.readStrongBinder();
  }
  
  public final int describeContents() {
    return 0;
  }
  
  public final IBinder getBinder() {
    return this.zzry;
  }
  
  public final void setBinder(IBinder paramIBinder) {
    this.zzry = paramIBinder;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeStrongBinder(this.zzry);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\BinderWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */