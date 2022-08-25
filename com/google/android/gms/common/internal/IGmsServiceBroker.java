package com.google.android.gms.common.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IGmsServiceBroker extends IInterface {
  void getService(IGmsCallbacks paramIGmsCallbacks, GetServiceRequest paramGetServiceRequest) throws RemoteException;
  
  public static abstract class Stub extends Binder implements IGmsServiceBroker {
    public Stub() {
      attachInterface(this, "com.google.android.gms.common.internal.IGmsServiceBroker");
    }
    
    public static IGmsServiceBroker asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
      return (iInterface != null && iInterface instanceof IGmsServiceBroker) ? (IGmsServiceBroker)iInterface : new zza(param1IBinder);
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    protected void getLegacyService(int param1Int1, IGmsCallbacks param1IGmsCallbacks, int param1Int2, String param1String1, String param1String2, String[] param1ArrayOfString, Bundle param1Bundle, IBinder param1IBinder, String param1String3, String param1String4) throws RemoteException {
      throw new UnsupportedOperationException();
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 > 16777215)
        return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
      param1Parcel1.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
      IGmsCallbacks iGmsCallbacks = IGmsCallbacks.Stub.asInterface(param1Parcel1.readStrongBinder());
      GetServiceRequest getServiceRequest1 = null;
      GetServiceRequest getServiceRequest2 = null;
      if (param1Int1 == 46) {
        if (param1Parcel1.readInt() != 0)
          getServiceRequest2 = (GetServiceRequest)GetServiceRequest.CREATOR.createFromParcel(param1Parcel1); 
        getService(iGmsCallbacks, getServiceRequest2);
      } else if (param1Int1 == 47) {
        ValidateAccountRequest validateAccountRequest;
        getServiceRequest2 = getServiceRequest1;
        if (param1Parcel1.readInt() != 0)
          validateAccountRequest = (ValidateAccountRequest)ValidateAccountRequest.CREATOR.createFromParcel(param1Parcel1); 
        validateAccount(iGmsCallbacks, validateAccountRequest);
      } else {
        String str;
        param1Int2 = param1Parcel1.readInt();
        if (param1Int1 != 4) {
          str = param1Parcel1.readString();
        } else {
          str = null;
        } 
        if (param1Int1 != 23 && param1Int1 != 25 && param1Int1 != 27) {
          if (param1Int1 != 30) {
            Bundle bundle;
            if (param1Int1 != 34)
              if (param1Int1 != 41 && param1Int1 != 43) {
                GetServiceRequest getServiceRequest3;
                String[] arrayOfString1;
                String str2;
                Bundle bundle1;
                String str3;
                IBinder iBinder1;
                GetServiceRequest getServiceRequest5;
                IBinder iBinder3;
                GetServiceRequest getServiceRequest4;
                String str5;
                IBinder iBinder2;
                String str4;
                GetServiceRequest getServiceRequest6;
                String str6;
                String[] arrayOfString3;
                Bundle bundle2;
                String[] arrayOfString2;
                IBinder iBinder4;
                Bundle bundle4;
                String str7;
                Bundle bundle3;
                IBinder iBinder6;
                String str8;
                IBinder iBinder5;
                String str10;
                Bundle bundle5;
                String str9;
                Bundle bundle7;
                String str11;
                Bundle bundle6;
                switch (param1Int1) {
                  default:
                    switch (param1Int1) {
                      default:
                        switch (param1Int1) {
                          case 37:
                          case 38:
                            if (param1Parcel1.readInt() != 0) {
                              Bundle bundle8 = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
                              getServiceRequest2 = null;
                              getServiceRequest1 = getServiceRequest2;
                              getServiceRequest5 = getServiceRequest1;
                            } else {
                              break;
                            } 
                            getServiceRequest3 = getServiceRequest5;
                            break;
                        } 
                        getServiceRequest2 = null;
                        getServiceRequest1 = null;
                        getServiceRequest3 = null;
                      case 19:
                        iBinder3 = getServiceRequest3.readStrongBinder();
                        if (getServiceRequest3.readInt() != 0) {
                          Bundle bundle8 = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)getServiceRequest3);
                          getServiceRequest2 = null;
                          getServiceRequest1 = getServiceRequest2;
                          getServiceRequest3 = getServiceRequest1;
                          break;
                        } 
                        getServiceRequest2 = null;
                        getServiceRequest1 = getServiceRequest2;
                        getServiceRequest6 = getServiceRequest1;
                        getServiceRequest3 = getServiceRequest6;
                        break;
                      case 10:
                        str3 = getServiceRequest3.readString();
                        arrayOfString1 = getServiceRequest3.createStringArray();
                        getServiceRequest3 = null;
                        getServiceRequest4 = getServiceRequest3;
                        getServiceRequest6 = getServiceRequest3;
                        getServiceRequest3 = getServiceRequest4;
                        break;
                      case 9:
                        str3 = getServiceRequest3.readString();
                        arrayOfString3 = getServiceRequest3.createStringArray();
                        str6 = getServiceRequest3.readString();
                        iBinder4 = getServiceRequest3.readStrongBinder();
                        str2 = getServiceRequest3.readString();
                        if (getServiceRequest3.readInt() != 0) {
                          Bundle bundle8 = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)getServiceRequest3);
                          String str12 = str2;
                          bundle1 = bundle8;
                          String str13 = str3;
                          iBinder1 = iBinder4;
                        } else {
                          bundle = bundle1;
                          bundle1 = null;
                          IBinder iBinder = iBinder1;
                          iBinder1 = iBinder4;
                        } 
                        bundle4 = bundle1;
                        iBinder6 = iBinder1;
                        str10 = str6;
                        bundle7 = bundle;
                        break;
                      case 20:
                        arrayOfString3 = bundle.createStringArray();
                        str5 = bundle.readString();
                        if (bundle.readInt() != 0) {
                          bundle4 = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)bundle);
                        } else {
                          bundle4 = null;
                        } 
                        iBinder6 = null;
                        str10 = null;
                        str11 = str10;
                        break;
                      case 5:
                      case 6:
                      case 7:
                      case 8:
                      case 11:
                      case 12:
                      case 13:
                      case 14:
                      case 15:
                      case 16:
                      case 17:
                      case 18:
                      
                    } 
                    bundle6 = bundle;
                    bundle5 = bundle;
                    str8 = str5;
                    str7 = str6;
                    bundle2 = bundle1;
                    iBinder2 = iBinder1;
                    break;
                  case 1:
                    str6 = bundle.readString();
                    arrayOfString2 = bundle.createStringArray();
                    str4 = bundle.readString();
                    if (bundle.readInt() != 0) {
                      bundle1 = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)bundle);
                    } else {
                      bundle1 = null;
                    } 
                    iBinder1 = null;
                    bundle = null;
                    bundle3 = bundle1;
                    iBinder5 = iBinder1;
                    str9 = str6;
                    bundle6 = bundle;
                    break;
                  case 2:
                  
                } 
                getLegacyService(param1Int1, iGmsCallbacks, param1Int2, str, str4, arrayOfString2, bundle3, iBinder5, str9, (String)bundle6);
                param1Parcel2.writeNoException();
                return true;
              }  
            String str1 = bundle.readString();
          } else {
          
          } 
        } else {
        
        } 
        getServiceRequest1 = null;
        param1Parcel1 = null;
      } 
      param1Parcel2.writeNoException();
      return true;
    }
    
    protected void validateAccount(IGmsCallbacks param1IGmsCallbacks, ValidateAccountRequest param1ValidateAccountRequest) throws RemoteException {
      throw new UnsupportedOperationException();
    }
    
    private static final class zza implements IGmsServiceBroker {
      private final IBinder zza;
      
      zza(IBinder param2IBinder) {
        this.zza = param2IBinder;
      }
      
      public final IBinder asBinder() {
        return this.zza;
      }
      
      public final void getService(IGmsCallbacks param2IGmsCallbacks, GetServiceRequest param2GetServiceRequest) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
          if (param2IGmsCallbacks != null) {
            IBinder iBinder = param2IGmsCallbacks.asBinder();
          } else {
            param2IGmsCallbacks = null;
          } 
          parcel1.writeStrongBinder((IBinder)param2IGmsCallbacks);
          if (param2GetServiceRequest != null) {
            parcel1.writeInt(1);
            param2GetServiceRequest.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          this.zza.transact(46, parcel1, parcel2, 0);
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
    }
  }
  
  private static final class zza implements IGmsServiceBroker {
    private final IBinder zza;
    
    zza(IBinder param1IBinder) {
      this.zza = param1IBinder;
    }
    
    public final IBinder asBinder() {
      return this.zza;
    }
    
    public final void getService(IGmsCallbacks param1IGmsCallbacks, GetServiceRequest param1GetServiceRequest) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
        if (param1IGmsCallbacks != null) {
          IBinder iBinder = param1IGmsCallbacks.asBinder();
        } else {
          param1IGmsCallbacks = null;
        } 
        parcel1.writeStrongBinder((IBinder)param1IGmsCallbacks);
        if (param1GetServiceRequest != null) {
          parcel1.writeInt(1);
          param1GetServiceRequest.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        this.zza.transact(46, parcel1, parcel2, 0);
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\IGmsServiceBroker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */