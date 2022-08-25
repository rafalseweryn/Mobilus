package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface IStatusCallback extends IInterface {
  void onResult(Status paramStatus) throws RemoteException;
  
  public static abstract class Stub extends zzb implements IStatusCallback {
    public Stub() {
      super("com.google.android.gms.common.api.internal.IStatusCallback");
    }
    
    public static IStatusCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("com.google.android.gms.common.api.internal.IStatusCallback");
      return (iInterface instanceof IStatusCallback) ? (IStatusCallback)iInterface : new zza(param1IBinder);
    }
    
    protected boolean dispatchTransaction(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 == 1) {
        onResult((Status)zzc.zza(param1Parcel1, Status.CREATOR));
        return true;
      } 
      return false;
    }
    
    public static final class zza extends com.google.android.gms.internal.stable.zza implements IStatusCallback {
      zza(IBinder param2IBinder) {
        super(param2IBinder, "com.google.android.gms.common.api.internal.IStatusCallback");
      }
      
      public final void onResult(Status param2Status) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, (Parcelable)param2Status);
        transactOneway(1, parcel);
      }
    }
  }
  
  public static final class zza extends com.google.android.gms.internal.stable.zza implements IStatusCallback {
    zza(IBinder param1IBinder) {
      super(param1IBinder, "com.google.android.gms.common.api.internal.IStatusCallback");
    }
    
    public final void onResult(Status param1Status) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, (Parcelable)param1Status);
      transactOneway(1, parcel);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\IStatusCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */