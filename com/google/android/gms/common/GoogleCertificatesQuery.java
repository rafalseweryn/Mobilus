package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.ICertData;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.Nullable;

@Class(creator = "GoogleCertificatesQueryCreator")
public class GoogleCertificatesQuery extends AbstractSafeParcelable {
  public static final Parcelable.Creator<GoogleCertificatesQuery> CREATOR = new GoogleCertificatesQueryCreator();
  
  @Field(getter = "getCallingPackage", id = 1)
  private final String zzbh;
  
  @Nullable
  @Field(getter = "getCallingCertificateBinder", id = 2, type = "android.os.IBinder")
  private final GoogleCertificates.CertData zzbi;
  
  @Field(getter = "getAllowTestKeys", id = 3)
  private final boolean zzbj;
  
  @Constructor
  GoogleCertificatesQuery(@Param(id = 1) String paramString, @Nullable @Param(id = 2) IBinder paramIBinder, @Param(id = 3) boolean paramBoolean) {
    this.zzbh = paramString;
    this.zzbi = zza(paramIBinder);
    this.zzbj = paramBoolean;
  }
  
  GoogleCertificatesQuery(String paramString, @Nullable GoogleCertificates.CertData paramCertData, boolean paramBoolean) {
    this.zzbh = paramString;
    this.zzbi = paramCertData;
    this.zzbj = paramBoolean;
  }
  
  @Nullable
  private static GoogleCertificates.CertData zza(@Nullable IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    try {
      byte[] arrayOfByte;
      IObjectWrapper iObjectWrapper = ICertData.Stub.asInterface(paramIBinder).getBytesWrapped();
      if (iObjectWrapper == null) {
        iObjectWrapper = null;
      } else {
        arrayOfByte = (byte[])ObjectWrapper.unwrap(iObjectWrapper);
      } 
      if (arrayOfByte != null)
        return new zzb(arrayOfByte); 
      Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
      return null;
    } catch (RemoteException remoteException) {
      Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", (Throwable)remoteException);
      return null;
    } 
  }
  
  public boolean getAllowTestKeys() {
    return this.zzbj;
  }
  
  @Nullable
  public IBinder getCallingCertificateBinder() {
    if (this.zzbi == null) {
      Log.w("GoogleCertificatesQuery", "certificate binder is null");
      return null;
    } 
    return this.zzbi.asBinder();
  }
  
  public String getCallingPackage() {
    return this.zzbh;
  }
  
  @Nullable
  public GoogleCertificates.CertData getCertificate() {
    return this.zzbi;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeString(paramParcel, 1, getCallingPackage(), false);
    SafeParcelWriter.writeIBinder(paramParcel, 2, getCallingCertificateBinder(), false);
    SafeParcelWriter.writeBoolean(paramParcel, 3, getAllowTestKeys());
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\GoogleCertificatesQuery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */