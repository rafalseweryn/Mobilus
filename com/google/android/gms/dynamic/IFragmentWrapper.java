package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.stable.zza;
import com.google.android.gms.internal.stable.zzb;
import com.google.android.gms.internal.stable.zzc;

public interface IFragmentWrapper extends IInterface {
  IObjectWrapper getActivity() throws RemoteException;
  
  Bundle getArguments() throws RemoteException;
  
  int getId() throws RemoteException;
  
  IFragmentWrapper getParentFragment() throws RemoteException;
  
  IObjectWrapper getResources() throws RemoteException;
  
  boolean getRetainInstance() throws RemoteException;
  
  String getTag() throws RemoteException;
  
  IFragmentWrapper getTargetFragment() throws RemoteException;
  
  int getTargetRequestCode() throws RemoteException;
  
  boolean getUserVisibleHint() throws RemoteException;
  
  IObjectWrapper getView() throws RemoteException;
  
  boolean isAdded() throws RemoteException;
  
  boolean isDetached() throws RemoteException;
  
  boolean isHidden() throws RemoteException;
  
  boolean isInLayout() throws RemoteException;
  
  boolean isRemoving() throws RemoteException;
  
  boolean isResumed() throws RemoteException;
  
  boolean isVisible() throws RemoteException;
  
  void registerForContextMenu(IObjectWrapper paramIObjectWrapper) throws RemoteException;
  
  void setHasOptionsMenu(boolean paramBoolean) throws RemoteException;
  
  void setMenuVisibility(boolean paramBoolean) throws RemoteException;
  
  void setRetainInstance(boolean paramBoolean) throws RemoteException;
  
  void setUserVisibleHint(boolean paramBoolean) throws RemoteException;
  
  void startActivity(Intent paramIntent) throws RemoteException;
  
  void startActivityForResult(Intent paramIntent, int paramInt) throws RemoteException;
  
  void unregisterForContextMenu(IObjectWrapper paramIObjectWrapper) throws RemoteException;
  
  public static abstract class Stub extends zzb implements IFragmentWrapper {
    public Stub() {
      super("com.google.android.gms.dynamic.IFragmentWrapper");
    }
    
    public static IFragmentWrapper asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("com.google.android.gms.dynamic.IFragmentWrapper");
      return (iInterface instanceof IFragmentWrapper) ? (IFragmentWrapper)iInterface : new Proxy(param1IBinder);
    }
    
    protected boolean dispatchTransaction(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      IObjectWrapper iObjectWrapper3;
      IFragmentWrapper iFragmentWrapper2;
      String str;
      IObjectWrapper iObjectWrapper2;
      IFragmentWrapper iFragmentWrapper1;
      Bundle bundle;
      IObjectWrapper iObjectWrapper1;
      boolean bool;
      switch (param1Int1) {
        default:
          return false;
        case 27:
          unregisterForContextMenu(IObjectWrapper.Stub.asInterface(param1Parcel1.readStrongBinder()));
          param1Parcel2.writeNoException();
          return true;
        case 26:
          startActivityForResult((Intent)zzc.zza(param1Parcel1, Intent.CREATOR), param1Parcel1.readInt());
          param1Parcel2.writeNoException();
          return true;
        case 25:
          startActivity((Intent)zzc.zza(param1Parcel1, Intent.CREATOR));
          param1Parcel2.writeNoException();
          return true;
        case 24:
          setUserVisibleHint(zzc.zza(param1Parcel1));
          param1Parcel2.writeNoException();
          return true;
        case 23:
          setRetainInstance(zzc.zza(param1Parcel1));
          param1Parcel2.writeNoException();
          return true;
        case 22:
          setMenuVisibility(zzc.zza(param1Parcel1));
          param1Parcel2.writeNoException();
          return true;
        case 21:
          setHasOptionsMenu(zzc.zza(param1Parcel1));
          param1Parcel2.writeNoException();
          return true;
        case 20:
          registerForContextMenu(IObjectWrapper.Stub.asInterface(param1Parcel1.readStrongBinder()));
          param1Parcel2.writeNoException();
          return true;
        case 19:
          bool = isVisible();
          param1Parcel2.writeNoException();
          zzc.zza(param1Parcel2, bool);
          return true;
        case 18:
          bool = isResumed();
          param1Parcel2.writeNoException();
          zzc.zza(param1Parcel2, bool);
          return true;
        case 17:
          bool = isRemoving();
          param1Parcel2.writeNoException();
          zzc.zza(param1Parcel2, bool);
          return true;
        case 16:
          bool = isInLayout();
          param1Parcel2.writeNoException();
          zzc.zza(param1Parcel2, bool);
          return true;
        case 15:
          bool = isHidden();
          param1Parcel2.writeNoException();
          zzc.zza(param1Parcel2, bool);
          return true;
        case 14:
          bool = isDetached();
          param1Parcel2.writeNoException();
          zzc.zza(param1Parcel2, bool);
          return true;
        case 13:
          bool = isAdded();
          param1Parcel2.writeNoException();
          zzc.zza(param1Parcel2, bool);
          return true;
        case 12:
          iObjectWrapper3 = getView();
          break;
        case 11:
          bool = getUserVisibleHint();
          param1Parcel2.writeNoException();
          zzc.zza(param1Parcel2, bool);
          return true;
        case 10:
          param1Int1 = getTargetRequestCode();
          param1Parcel2.writeNoException();
          param1Parcel2.writeInt(param1Int1);
          return true;
        case 9:
          iFragmentWrapper2 = getTargetFragment();
          break;
        case 8:
          str = getTag();
          param1Parcel2.writeNoException();
          param1Parcel2.writeString(str);
          return true;
        case 7:
          bool = getRetainInstance();
          param1Parcel2.writeNoException();
          zzc.zza(param1Parcel2, bool);
          return true;
        case 6:
          iObjectWrapper2 = getResources();
          break;
        case 5:
          iFragmentWrapper1 = getParentFragment();
          break;
        case 4:
          param1Int1 = getId();
          param1Parcel2.writeNoException();
          param1Parcel2.writeInt(param1Int1);
          return true;
        case 3:
          bundle = getArguments();
          param1Parcel2.writeNoException();
          zzc.zzb(param1Parcel2, (Parcelable)bundle);
          return true;
        case 2:
          iObjectWrapper1 = getActivity();
          break;
      } 
      param1Parcel2.writeNoException();
      zzc.zza(param1Parcel2, iObjectWrapper1);
      return true;
    }
    
    public static class Proxy extends zza implements IFragmentWrapper {
      Proxy(IBinder param2IBinder) {
        super(param2IBinder, "com.google.android.gms.dynamic.IFragmentWrapper");
      }
      
      public IObjectWrapper getActivity() throws RemoteException {
        Parcel parcel = transactAndReadException(2, obtainAndWriteInterfaceToken());
        IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
        parcel.recycle();
        return iObjectWrapper;
      }
      
      public Bundle getArguments() throws RemoteException {
        Parcel parcel = transactAndReadException(3, obtainAndWriteInterfaceToken());
        Bundle bundle = (Bundle)zzc.zza(parcel, Bundle.CREATOR);
        parcel.recycle();
        return bundle;
      }
      
      public int getId() throws RemoteException {
        Parcel parcel = transactAndReadException(4, obtainAndWriteInterfaceToken());
        int i = parcel.readInt();
        parcel.recycle();
        return i;
      }
      
      public IFragmentWrapper getParentFragment() throws RemoteException {
        Parcel parcel = transactAndReadException(5, obtainAndWriteInterfaceToken());
        IFragmentWrapper iFragmentWrapper = IFragmentWrapper.Stub.asInterface(parcel.readStrongBinder());
        parcel.recycle();
        return iFragmentWrapper;
      }
      
      public IObjectWrapper getResources() throws RemoteException {
        Parcel parcel = transactAndReadException(6, obtainAndWriteInterfaceToken());
        IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
        parcel.recycle();
        return iObjectWrapper;
      }
      
      public boolean getRetainInstance() throws RemoteException {
        Parcel parcel = transactAndReadException(7, obtainAndWriteInterfaceToken());
        boolean bool = zzc.zza(parcel);
        parcel.recycle();
        return bool;
      }
      
      public String getTag() throws RemoteException {
        Parcel parcel = transactAndReadException(8, obtainAndWriteInterfaceToken());
        String str = parcel.readString();
        parcel.recycle();
        return str;
      }
      
      public IFragmentWrapper getTargetFragment() throws RemoteException {
        Parcel parcel = transactAndReadException(9, obtainAndWriteInterfaceToken());
        IFragmentWrapper iFragmentWrapper = IFragmentWrapper.Stub.asInterface(parcel.readStrongBinder());
        parcel.recycle();
        return iFragmentWrapper;
      }
      
      public int getTargetRequestCode() throws RemoteException {
        Parcel parcel = transactAndReadException(10, obtainAndWriteInterfaceToken());
        int i = parcel.readInt();
        parcel.recycle();
        return i;
      }
      
      public boolean getUserVisibleHint() throws RemoteException {
        Parcel parcel = transactAndReadException(11, obtainAndWriteInterfaceToken());
        boolean bool = zzc.zza(parcel);
        parcel.recycle();
        return bool;
      }
      
      public IObjectWrapper getView() throws RemoteException {
        Parcel parcel = transactAndReadException(12, obtainAndWriteInterfaceToken());
        IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
        parcel.recycle();
        return iObjectWrapper;
      }
      
      public boolean isAdded() throws RemoteException {
        Parcel parcel = transactAndReadException(13, obtainAndWriteInterfaceToken());
        boolean bool = zzc.zza(parcel);
        parcel.recycle();
        return bool;
      }
      
      public boolean isDetached() throws RemoteException {
        Parcel parcel = transactAndReadException(14, obtainAndWriteInterfaceToken());
        boolean bool = zzc.zza(parcel);
        parcel.recycle();
        return bool;
      }
      
      public boolean isHidden() throws RemoteException {
        Parcel parcel = transactAndReadException(15, obtainAndWriteInterfaceToken());
        boolean bool = zzc.zza(parcel);
        parcel.recycle();
        return bool;
      }
      
      public boolean isInLayout() throws RemoteException {
        Parcel parcel = transactAndReadException(16, obtainAndWriteInterfaceToken());
        boolean bool = zzc.zza(parcel);
        parcel.recycle();
        return bool;
      }
      
      public boolean isRemoving() throws RemoteException {
        Parcel parcel = transactAndReadException(17, obtainAndWriteInterfaceToken());
        boolean bool = zzc.zza(parcel);
        parcel.recycle();
        return bool;
      }
      
      public boolean isResumed() throws RemoteException {
        Parcel parcel = transactAndReadException(18, obtainAndWriteInterfaceToken());
        boolean bool = zzc.zza(parcel);
        parcel.recycle();
        return bool;
      }
      
      public boolean isVisible() throws RemoteException {
        Parcel parcel = transactAndReadException(19, obtainAndWriteInterfaceToken());
        boolean bool = zzc.zza(parcel);
        parcel.recycle();
        return bool;
      }
      
      public void registerForContextMenu(IObjectWrapper param2IObjectWrapper) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, param2IObjectWrapper);
        transactAndReadExceptionReturnVoid(20, parcel);
      }
      
      public void setHasOptionsMenu(boolean param2Boolean) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, param2Boolean);
        transactAndReadExceptionReturnVoid(21, parcel);
      }
      
      public void setMenuVisibility(boolean param2Boolean) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, param2Boolean);
        transactAndReadExceptionReturnVoid(22, parcel);
      }
      
      public void setRetainInstance(boolean param2Boolean) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, param2Boolean);
        transactAndReadExceptionReturnVoid(23, parcel);
      }
      
      public void setUserVisibleHint(boolean param2Boolean) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, param2Boolean);
        transactAndReadExceptionReturnVoid(24, parcel);
      }
      
      public void startActivity(Intent param2Intent) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, (Parcelable)param2Intent);
        transactAndReadExceptionReturnVoid(25, parcel);
      }
      
      public void startActivityForResult(Intent param2Intent, int param2Int) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, (Parcelable)param2Intent);
        parcel.writeInt(param2Int);
        transactAndReadExceptionReturnVoid(26, parcel);
      }
      
      public void unregisterForContextMenu(IObjectWrapper param2IObjectWrapper) throws RemoteException {
        Parcel parcel = obtainAndWriteInterfaceToken();
        zzc.zza(parcel, param2IObjectWrapper);
        transactAndReadExceptionReturnVoid(27, parcel);
      }
    }
  }
  
  public static class Proxy extends zza implements IFragmentWrapper {
    Proxy(IBinder param1IBinder) {
      super(param1IBinder, "com.google.android.gms.dynamic.IFragmentWrapper");
    }
    
    public IObjectWrapper getActivity() throws RemoteException {
      Parcel parcel = transactAndReadException(2, obtainAndWriteInterfaceToken());
      IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
      parcel.recycle();
      return iObjectWrapper;
    }
    
    public Bundle getArguments() throws RemoteException {
      Parcel parcel = transactAndReadException(3, obtainAndWriteInterfaceToken());
      Bundle bundle = (Bundle)zzc.zza(parcel, Bundle.CREATOR);
      parcel.recycle();
      return bundle;
    }
    
    public int getId() throws RemoteException {
      Parcel parcel = transactAndReadException(4, obtainAndWriteInterfaceToken());
      int i = parcel.readInt();
      parcel.recycle();
      return i;
    }
    
    public IFragmentWrapper getParentFragment() throws RemoteException {
      Parcel parcel = transactAndReadException(5, obtainAndWriteInterfaceToken());
      IFragmentWrapper iFragmentWrapper = IFragmentWrapper.Stub.asInterface(parcel.readStrongBinder());
      parcel.recycle();
      return iFragmentWrapper;
    }
    
    public IObjectWrapper getResources() throws RemoteException {
      Parcel parcel = transactAndReadException(6, obtainAndWriteInterfaceToken());
      IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
      parcel.recycle();
      return iObjectWrapper;
    }
    
    public boolean getRetainInstance() throws RemoteException {
      Parcel parcel = transactAndReadException(7, obtainAndWriteInterfaceToken());
      boolean bool = zzc.zza(parcel);
      parcel.recycle();
      return bool;
    }
    
    public String getTag() throws RemoteException {
      Parcel parcel = transactAndReadException(8, obtainAndWriteInterfaceToken());
      String str = parcel.readString();
      parcel.recycle();
      return str;
    }
    
    public IFragmentWrapper getTargetFragment() throws RemoteException {
      Parcel parcel = transactAndReadException(9, obtainAndWriteInterfaceToken());
      IFragmentWrapper iFragmentWrapper = IFragmentWrapper.Stub.asInterface(parcel.readStrongBinder());
      parcel.recycle();
      return iFragmentWrapper;
    }
    
    public int getTargetRequestCode() throws RemoteException {
      Parcel parcel = transactAndReadException(10, obtainAndWriteInterfaceToken());
      int i = parcel.readInt();
      parcel.recycle();
      return i;
    }
    
    public boolean getUserVisibleHint() throws RemoteException {
      Parcel parcel = transactAndReadException(11, obtainAndWriteInterfaceToken());
      boolean bool = zzc.zza(parcel);
      parcel.recycle();
      return bool;
    }
    
    public IObjectWrapper getView() throws RemoteException {
      Parcel parcel = transactAndReadException(12, obtainAndWriteInterfaceToken());
      IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
      parcel.recycle();
      return iObjectWrapper;
    }
    
    public boolean isAdded() throws RemoteException {
      Parcel parcel = transactAndReadException(13, obtainAndWriteInterfaceToken());
      boolean bool = zzc.zza(parcel);
      parcel.recycle();
      return bool;
    }
    
    public boolean isDetached() throws RemoteException {
      Parcel parcel = transactAndReadException(14, obtainAndWriteInterfaceToken());
      boolean bool = zzc.zza(parcel);
      parcel.recycle();
      return bool;
    }
    
    public boolean isHidden() throws RemoteException {
      Parcel parcel = transactAndReadException(15, obtainAndWriteInterfaceToken());
      boolean bool = zzc.zza(parcel);
      parcel.recycle();
      return bool;
    }
    
    public boolean isInLayout() throws RemoteException {
      Parcel parcel = transactAndReadException(16, obtainAndWriteInterfaceToken());
      boolean bool = zzc.zza(parcel);
      parcel.recycle();
      return bool;
    }
    
    public boolean isRemoving() throws RemoteException {
      Parcel parcel = transactAndReadException(17, obtainAndWriteInterfaceToken());
      boolean bool = zzc.zza(parcel);
      parcel.recycle();
      return bool;
    }
    
    public boolean isResumed() throws RemoteException {
      Parcel parcel = transactAndReadException(18, obtainAndWriteInterfaceToken());
      boolean bool = zzc.zza(parcel);
      parcel.recycle();
      return bool;
    }
    
    public boolean isVisible() throws RemoteException {
      Parcel parcel = transactAndReadException(19, obtainAndWriteInterfaceToken());
      boolean bool = zzc.zza(parcel);
      parcel.recycle();
      return bool;
    }
    
    public void registerForContextMenu(IObjectWrapper param1IObjectWrapper) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, param1IObjectWrapper);
      transactAndReadExceptionReturnVoid(20, parcel);
    }
    
    public void setHasOptionsMenu(boolean param1Boolean) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, param1Boolean);
      transactAndReadExceptionReturnVoid(21, parcel);
    }
    
    public void setMenuVisibility(boolean param1Boolean) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, param1Boolean);
      transactAndReadExceptionReturnVoid(22, parcel);
    }
    
    public void setRetainInstance(boolean param1Boolean) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, param1Boolean);
      transactAndReadExceptionReturnVoid(23, parcel);
    }
    
    public void setUserVisibleHint(boolean param1Boolean) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, param1Boolean);
      transactAndReadExceptionReturnVoid(24, parcel);
    }
    
    public void startActivity(Intent param1Intent) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, (Parcelable)param1Intent);
      transactAndReadExceptionReturnVoid(25, parcel);
    }
    
    public void startActivityForResult(Intent param1Intent, int param1Int) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, (Parcelable)param1Intent);
      parcel.writeInt(param1Int);
      transactAndReadExceptionReturnVoid(26, parcel);
    }
    
    public void unregisterForContextMenu(IObjectWrapper param1IObjectWrapper) throws RemoteException {
      Parcel parcel = obtainAndWriteInterfaceToken();
      zzc.zza(parcel, param1IObjectWrapper);
      transactAndReadExceptionReturnVoid(27, parcel);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\dynamic\IFragmentWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */