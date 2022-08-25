package com.google.android.gms.dynamic;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;

public interface IObjectWrapper extends IInterface {
  public static class Stub extends zzb implements IObjectWrapper {
    public Stub() {
      super("com.google.android.gms.dynamic.IObjectWrapper");
    }
    
    public static IObjectWrapper asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
      return (iInterface instanceof IObjectWrapper) ? (IObjectWrapper)iInterface : new Proxy(param1IBinder);
    }
    
    public static class Proxy extends zza implements IObjectWrapper {
      Proxy(IBinder param2IBinder) {
        super(param2IBinder, "com.google.android.gms.dynamic.IObjectWrapper");
      }
    }
  }
  
  public static class Proxy extends zza implements IObjectWrapper {
    Proxy(IBinder param1IBinder) {
      super(param1IBinder, "com.google.android.gms.dynamic.IObjectWrapper");
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\dynamic\IObjectWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */