package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.GoogleCertificatesQuery;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface IGoogleCertificatesApi extends IInterface {
  IObjectWrapper getGoogleCertificates() throws RemoteException;
  
  IObjectWrapper getGoogleReleaseCertificates() throws RemoteException;
  
  boolean isGoogleOrPlatformSigned(GoogleCertificatesQuery paramGoogleCertificatesQuery, IObjectWrapper paramIObjectWrapper) throws RemoteException;
  
  boolean isGoogleReleaseSigned(String paramString, IObjectWrapper paramIObjectWrapper) throws RemoteException;
  
  boolean isGoogleSigned(String paramString, IObjectWrapper paramIObjectWrapper) throws RemoteException;
  
  public static abstract class Stub extends zzb implements IGoogleCertificatesApi {
    public Stub() {
      super("com.google.android.gms.common.internal.IGoogleCertificatesApi");
    }
    
    public static IGoogleCertificatesApi asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("com.google.android.gms.common.internal.IGoogleCertificatesApi");
      return (iInterface instanceof IGoogleCertificatesApi) ? (IGoogleCertificatesApi)iInterface : new Proxy(param1IBinder);
    }
    
    protected boolean dispatchTransaction(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      IObjectWrapper iObjectWrapper;
      boolean bool;
      switch (param1Int1) {
        default:
          return false;
        case 5:
          bool = isGoogleOrPlatformSigned((GoogleCertificatesQuery)zzc.zza(param1Parcel1, GoogleCertificatesQuery.CREATOR), IObjectWrapper.Stub.asInterface(param1Parcel1.readStrongBinder()));
          param1Parcel2.writeNoException();
          zzc.zza(param1Parcel2, bool);
          return true;
        case 4:
          bool = isGoogleSigned(param1Parcel1.readString(), IObjectWrapper.Stub.asInterface(param1Parcel1.readStrongBinder()));
          param1Parcel2.writeNoException();
          zzc.zza(param1Parcel2, bool);
          return true;
        case 3:
          bool = isGoogleReleaseSigned(param1Parcel1.readString(), IObjectWrapper.Stub.asInterface(param1Parcel1.readStrongBinder()));
          param1Parcel2.writeNoException();
          zzc.zza(param1Parcel2, bool);
          return true;
        case 2:
          iObjectWrapper = getGoogleReleaseCertificates();
          break;
        case 1:
          iObjectWrapper = getGoogleCertificates();
          break;
      } 
      param1Parcel2.writeNoException();
      zzc.zza(param1Parcel2, (IInterface)iObjectWrapper);
      return true;
    }
    
    public static class Proxy extends zza implements IGoogleCertificatesApi {
      Proxy(IBinder param2IBinder) {
        super(param2IBinder, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
      }
      
      public IObjectWrapper getGoogleCertificates() throws RemoteException {
        Parcel parcel = transactAndReadException(1, obtainAndWriteInterfaceToken());
        IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
        parcel.recycle();
        return iObjectWrapper;
      }
      
      public IObjectWrapper getGoogleReleaseCertificates() throws RemoteException {
        Parcel parcel = transactAndReadException(2, obtainAndWriteInterfaceToken());
        IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
        parcel.recycle();
        return iObjectWrapper;
      }
      
      public boolean isGoogleOrPlatformSigned(GoogleCertificatesQuery param2GoogleCertificatesQuery, IObjectWrapper param2IObjectWrapper) throws RemoteException {
        Parcel parcel2 = obtainAndWriteInterfaceToken();
        zzc.zza(parcel2, (Parcelable)param2GoogleCertificatesQuery);
        zzc.zza(parcel2, (IInterface)param2IObjectWrapper);
        Parcel parcel1 = transactAndReadException(5, parcel2);
        boolean bool = zzc.zza(parcel1);
        parcel1.recycle();
        return bool;
      }
      
      public boolean isGoogleReleaseSigned(String param2String, IObjectWrapper param2IObjectWrapper) throws RemoteException {
        Parcel parcel2 = obtainAndWriteInterfaceToken();
        parcel2.writeString(param2String);
        zzc.zza(parcel2, (IInterface)param2IObjectWrapper);
        Parcel parcel1 = transactAndReadException(3, parcel2);
        boolean bool = zzc.zza(parcel1);
        parcel1.recycle();
        return bool;
      }
      
      public boolean isGoogleSigned(String param2String, IObjectWrapper param2IObjectWrapper) throws RemoteException {
        Parcel parcel2 = obtainAndWriteInterfaceToken();
        parcel2.writeString(param2String);
        zzc.zza(parcel2, (IInterface)param2IObjectWrapper);
        Parcel parcel1 = transactAndReadException(4, parcel2);
        boolean bool = zzc.zza(parcel1);
        parcel1.recycle();
        return bool;
      }
    }
  }
  
  public static class Proxy extends zza implements IGoogleCertificatesApi {
    Proxy(IBinder param1IBinder) {
      super(param1IBinder, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
    }
    
    public IObjectWrapper getGoogleCertificates() throws RemoteException {
      Parcel parcel = transactAndReadException(1, obtainAndWriteInterfaceToken());
      IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
      parcel.recycle();
      return iObjectWrapper;
    }
    
    public IObjectWrapper getGoogleReleaseCertificates() throws RemoteException {
      Parcel parcel = transactAndReadException(2, obtainAndWriteInterfaceToken());
      IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
      parcel.recycle();
      return iObjectWrapper;
    }
    
    public boolean isGoogleOrPlatformSigned(GoogleCertificatesQuery param1GoogleCertificatesQuery, IObjectWrapper param1IObjectWrapper) throws RemoteException {
      Parcel parcel2 = obtainAndWriteInterfaceToken();
      zzc.zza(parcel2, (Parcelable)param1GoogleCertificatesQuery);
      zzc.zza(parcel2, (IInterface)param1IObjectWrapper);
      Parcel parcel1 = transactAndReadException(5, parcel2);
      boolean bool = zzc.zza(parcel1);
      parcel1.recycle();
      return bool;
    }
    
    public boolean isGoogleReleaseSigned(String param1String, IObjectWrapper param1IObjectWrapper) throws RemoteException {
      Parcel parcel2 = obtainAndWriteInterfaceToken();
      parcel2.writeString(param1String);
      zzc.zza(parcel2, (IInterface)param1IObjectWrapper);
      Parcel parcel1 = transactAndReadException(3, parcel2);
      boolean bool = zzc.zza(parcel1);
      parcel1.recycle();
      return bool;
    }
    
    public boolean isGoogleSigned(String param1String, IObjectWrapper param1IObjectWrapper) throws RemoteException {
      Parcel parcel2 = obtainAndWriteInterfaceToken();
      parcel2.writeString(param1String);
      zzc.zza(parcel2, (IInterface)param1IObjectWrapper);
      Parcel parcel1 = transactAndReadException(4, parcel2);
      boolean bool = zzc.zza(parcel1);
      parcel1.recycle();
      return bool;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\IGoogleCertificatesApi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */