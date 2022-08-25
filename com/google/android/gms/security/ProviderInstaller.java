package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.reflect.Method;

public class ProviderInstaller {
  public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
  
  private static final Object sLock;
  
  private static final GoogleApiAvailabilityLight zzacw = GoogleApiAvailabilityLight.getInstance();
  
  private static Method zzacx;
  
  static {
    sLock = new Object();
  }
  
  public static void installIfNeeded(Context paramContext) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
    Preconditions.checkNotNull(paramContext, "Context must not be null");
    zzacw.verifyGooglePlayServicesIsAvailable(paramContext, 11925000);
    try {
      paramContext = GooglePlayServicesUtilLight.getRemoteContext(paramContext);
      if (paramContext == null) {
        if (Log.isLoggable("ProviderInstaller", 6))
          Log.e("ProviderInstaller", "Failed to get remote context"); 
        throw new GooglePlayServicesNotAvailableException(8);
      } 
      Object object = sLock;
      /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
      try {
        if (zzacx == null)
          zzacx = paramContext.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("insertProvider", new Class[] { Context.class }); 
        zzacx.invoke(null, new Object[] { paramContext });
        /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
        return;
      } catch (Exception exception) {
        Throwable throwable = exception.getCause();
        if (Log.isLoggable("ProviderInstaller", 6)) {
          if (throwable == null) {
            str = exception.getMessage();
          } else {
            str = throwable.getMessage();
          } 
          String str = String.valueOf(str);
          if (str.length() != 0) {
            str = "Failed to install provider: ".concat(str);
          } else {
            str = new String("Failed to install provider: ");
          } 
          Log.e("ProviderInstaller", str);
        } 
        GooglePlayServicesNotAvailableException googlePlayServicesNotAvailableException = new GooglePlayServicesNotAvailableException();
        this(8);
        throw googlePlayServicesNotAvailableException;
      } finally {}
      /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
      throw paramContext;
    } catch (android.content.res.Resources.NotFoundException notFoundException) {
      if (Log.isLoggable("ProviderInstaller", 6))
        Log.e("ProviderInstaller", "Failed to get remote context - resource not found"); 
      throw new GooglePlayServicesNotAvailableException(8);
    } 
  }
  
  public static void installIfNeededAsync(Context paramContext, ProviderInstallListener paramProviderInstallListener) {
    Preconditions.checkNotNull(paramContext, "Context must not be null");
    Preconditions.checkNotNull(paramProviderInstallListener, "Listener must not be null");
    Preconditions.checkMainThread("Must be called on the UI thread");
    (new zza(paramContext, paramProviderInstallListener)).execute((Object[])new Void[0]);
  }
  
  public static interface ProviderInstallListener {
    void onProviderInstallFailed(int param1Int, Intent param1Intent);
    
    void onProviderInstalled();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\security\ProviderInstaller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */