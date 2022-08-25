package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.internal.maps.zzq;
import com.google.android.gms.internal.maps.zzr;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class IndoorBuilding {
  @NonNull
  private final zzn zzdc;
  
  @NonNull
  private final zza zzdd;
  
  public IndoorBuilding(@NonNull zzn paramzzn) {
    this(paramzzn, zza.zzde);
  }
  
  @VisibleForTesting
  private IndoorBuilding(@NonNull zzn paramzzn, @NonNull zza paramzza) {
    this.zzdc = (zzn)Preconditions.checkNotNull(paramzzn, "delegate");
    this.zzdd = (zza)Preconditions.checkNotNull(paramzza, "shim");
  }
  
  public final boolean equals(Object paramObject) {
    if (!(paramObject instanceof IndoorBuilding))
      return false; 
    try {
      return this.zzdc.zzb(((IndoorBuilding)paramObject).zzdc);
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final int getActiveLevelIndex() {
    try {
      return this.zzdc.getActiveLevelIndex();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final int getDefaultLevelIndex() {
    try {
      return this.zzdc.getDefaultLevelIndex();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final List<IndoorLevel> getLevels() {
    try {
      List list = this.zzdc.getLevels();
      ArrayList<IndoorLevel> arrayList = new ArrayList();
      this(list.size());
      Iterator<IBinder> iterator = list.iterator();
      while (iterator.hasNext())
        arrayList.add(zza.zza(zza.zza(iterator.next()))); 
      return arrayList;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final int hashCode() {
    try {
      return this.zzdc.zzi();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isUnderground() {
    try {
      return this.zzdc.isUnderground();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  @VisibleForTesting
  static final class zza {
    public static final zza zzde = new zza();
    
    @NonNull
    public static zzq zza(IBinder param1IBinder) {
      return zzr.zzf(param1IBinder);
    }
    
    @NonNull
    public static IndoorLevel zza(@NonNull zzq param1zzq) {
      return new IndoorLevel(param1zzq);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\IndoorBuilding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */