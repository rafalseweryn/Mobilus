package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.PlatformVersion;

public abstract class zzb {
  private final int type;
  
  public zzb(int paramInt) {
    this.type = paramInt;
  }
  
  private static Status zza(RemoteException paramRemoteException) {
    StringBuilder stringBuilder = new StringBuilder();
    if (PlatformVersion.isAtLeastIceCreamSandwichMR1() && paramRemoteException instanceof android.os.TransactionTooLargeException)
      stringBuilder.append("TransactionTooLargeException: "); 
    stringBuilder.append(paramRemoteException.getLocalizedMessage());
    return new Status(8, stringBuilder.toString());
  }
  
  public abstract void zza(@NonNull Status paramStatus);
  
  public abstract void zza(GoogleApiManager.zza<?> paramzza) throws DeadObjectException;
  
  public abstract void zza(@NonNull zzaa paramzzaa, boolean paramBoolean);
  
  public abstract void zza(@NonNull RuntimeException paramRuntimeException);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */