package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;

public abstract class RemoteCreator<T> {
  private final String zzabo;
  
  private T zzabp;
  
  protected RemoteCreator(String paramString) {
    this.zzabo = paramString;
  }
  
  protected abstract T getRemoteCreator(IBinder paramIBinder);
  
  protected final T getRemoteCreatorInstance(Context paramContext) throws RemoteCreatorException {
    if (this.zzabp == null) {
      Preconditions.checkNotNull(paramContext);
      paramContext = GooglePlayServicesUtilLight.getRemoteContext(paramContext);
      if (paramContext == null)
        throw new RemoteCreatorException("Could not get remote context."); 
      ClassLoader classLoader = paramContext.getClassLoader();
      try {
        this.zzabp = getRemoteCreator((IBinder)classLoader.loadClass(this.zzabo).newInstance());
      } catch (ClassNotFoundException classNotFoundException) {
        throw new RemoteCreatorException("Could not load creator class.", classNotFoundException);
      } catch (InstantiationException instantiationException) {
        throw new RemoteCreatorException("Could not instantiate creator.", instantiationException);
      } catch (IllegalAccessException illegalAccessException) {
        throw new RemoteCreatorException("Could not access creator.", illegalAccessException);
      } 
    } 
    return this.zzabp;
  }
  
  public static class RemoteCreatorException extends Exception {
    public RemoteCreatorException(String param1String) {
      super(param1String);
    }
    
    public RemoteCreatorException(String param1String, Throwable param1Throwable) {
      super(param1String, param1Throwable);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\dynamic\RemoteCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */