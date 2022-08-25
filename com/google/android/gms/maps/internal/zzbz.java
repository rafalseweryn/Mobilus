package com.google.android.gms.maps.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class zzbz {
  private static final String TAG = "zzbz";
  
  @SuppressLint({"StaticFieldLeak"})
  @Nullable
  private static Context zzcj;
  
  private static zze zzck;
  
  public static zze zza(Context paramContext) throws GooglePlayServicesNotAvailableException {
    zze zze1;
    Preconditions.checkNotNull(paramContext);
    if (zzck != null)
      return zzck; 
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext, 12451000);
    if (i != 0)
      throw new GooglePlayServicesNotAvailableException(i); 
    Log.i(TAG, "Making Creator dynamically");
    IBinder iBinder = zza(zzb(paramContext).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl");
    if (iBinder == null) {
      iBinder = null;
    } else {
      IInterface iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
      if (iInterface instanceof zze) {
        zze1 = (zze)iInterface;
      } else {
        zze1 = new zzf((IBinder)zze1);
      } 
    } 
    zzck = zze1;
    try {
      zzck.zza(ObjectWrapper.wrap(zzb(paramContext).getResources()), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
      return zzck;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  private static <T> T zza(Class<?> paramClass) {
    String str;
    try {
      return (T)paramClass.newInstance();
    } catch (InstantiationException instantiationException) {
      str = String.valueOf(paramClass.getName());
      if (str.length() != 0) {
        str = "Unable to instantiate the dynamic class ".concat(str);
      } else {
        str = new String("Unable to instantiate the dynamic class ");
      } 
      throw new IllegalStateException(str);
    } catch (IllegalAccessException illegalAccessException) {
      str = String.valueOf(str.getName());
      if (str.length() != 0) {
        str = "Unable to call the default constructor of ".concat(str);
      } else {
        str = new String("Unable to call the default constructor of ");
      } 
      throw new IllegalStateException(str);
    } 
  }
  
  private static <T> T zza(ClassLoader paramClassLoader, String paramString) {
    try {
      return (T)zza(((ClassLoader)Preconditions.checkNotNull(paramClassLoader)).loadClass(paramString));
    } catch (ClassNotFoundException classNotFoundException) {
      String str = String.valueOf(paramString);
      if (str.length() != 0) {
        str = "Unable to find dynamic class ".concat(str);
      } else {
        str = new String("Unable to find dynamic class ");
      } 
      throw new IllegalStateException(str);
    } 
  }
  
  @Nullable
  private static Context zzb(Context paramContext) {
    if (zzcj != null)
      return zzcj; 
    paramContext = zzc(paramContext);
    zzcj = paramContext;
    return paramContext;
  }
  
  @Nullable
  private static Context zzc(Context paramContext) {
    try {
      return DynamiteModule.load(paramContext, DynamiteModule.PREFER_REMOTE, "com.google.android.gms.maps_dynamite").getModuleContext();
    } catch (Throwable throwable) {
      Log.e(TAG, "Failed to load maps module, use legacy", throwable);
      return GooglePlayServicesUtil.getRemoteContext(paramContext);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\internal\zzbz.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */