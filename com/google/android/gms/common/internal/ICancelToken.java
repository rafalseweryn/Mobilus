package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;

public interface ICancelToken extends IInterface {
  void cancel() throws RemoteException;
  
  public static abstract class Stub extends zzb implements ICancelToken {
    public Stub() {
      super("com.google.android.gms.common.internal.ICancelToken");
    }
    
    public static ICancelToken asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("com.google.android.gms.common.internal.ICancelToken");
      return (iInterface instanceof ICancelToken) ? (ICancelToken)iInterface : new Proxy(param1IBinder);
    }
    
    protected boolean dispatchTransaction(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 == 2) {
        cancel();
        return true;
      } 
      return false;
    }
    
    public static class Proxy extends zza implements ICancelToken {
      Proxy(IBinder param2IBinder) {
        super(param2IBinder, "com.google.android.gms.common.internal.ICancelToken");
      }
      
      public void cancel() throws RemoteException {
        transactOneway(2, obtainAndWriteInterfaceToken());
      }
    }
  }
  
  public static class Proxy extends zza implements ICancelToken {
    Proxy(IBinder param1IBinder) {
      super(param1IBinder, "com.google.android.gms.common.internal.ICancelToken");
    }
    
    public void cancel() throws RemoteException {
      transactOneway(2, obtainAndWriteInterfaceToken());
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\ICancelToken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */