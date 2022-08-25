package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;

@KeepForSdk
@Class(creator = "StatusCreator")
public final class Status extends AbstractSafeParcelable implements Result, ReflectedParcelable {
  public static final Parcelable.Creator<Status> CREATOR;
  
  @KeepForSdk
  public static final Status RESULT_CANCELED;
  
  @KeepForSdk
  public static final Status RESULT_DEAD_CLIENT;
  
  @KeepForSdk
  public static final Status RESULT_INTERNAL_ERROR;
  
  @KeepForSdk
  public static final Status RESULT_INTERRUPTED;
  
  @KeepForSdk
  @VisibleForTesting
  public static final Status RESULT_SUCCESS = new Status(0);
  
  @KeepForSdk
  public static final Status RESULT_TIMEOUT;
  
  private static final Status zzdq;
  
  @VersionField(id = 1000)
  private final int zzal;
  
  @Field(getter = "getStatusCode", id = 1)
  private final int zzam;
  
  @Nullable
  @Field(getter = "getPendingIntent", id = 3)
  private final PendingIntent zzan;
  
  @Nullable
  @Field(getter = "getStatusMessage", id = 2)
  private final String zzao;
  
  static {
    RESULT_INTERRUPTED = new Status(14);
    RESULT_INTERNAL_ERROR = new Status(8);
    RESULT_TIMEOUT = new Status(15);
    RESULT_CANCELED = new Status(16);
    zzdq = new Status(17);
    RESULT_DEAD_CLIENT = new Status(18);
    CREATOR = new zze();
  }
  
  @KeepForSdk
  public Status(int paramInt) {
    this(paramInt, null);
  }
  
  @KeepForSdk
  @Constructor
  Status(@Param(id = 1000) int paramInt1, @Param(id = 1) int paramInt2, @Nullable @Param(id = 2) String paramString, @Nullable @Param(id = 3) PendingIntent paramPendingIntent) {
    this.zzal = paramInt1;
    this.zzam = paramInt2;
    this.zzao = paramString;
    this.zzan = paramPendingIntent;
  }
  
  @KeepForSdk
  public Status(int paramInt, @Nullable String paramString) {
    this(1, paramInt, paramString, null);
  }
  
  @KeepForSdk
  public Status(int paramInt, @Nullable String paramString, @Nullable PendingIntent paramPendingIntent) {
    this(1, paramInt, paramString, paramPendingIntent);
  }
  
  public final boolean equals(Object paramObject) {
    if (!(paramObject instanceof Status))
      return false; 
    paramObject = paramObject;
    return (this.zzal == ((Status)paramObject).zzal && this.zzam == ((Status)paramObject).zzam && Objects.equal(this.zzao, ((Status)paramObject).zzao) && Objects.equal(this.zzan, ((Status)paramObject).zzan));
  }
  
  public final PendingIntent getResolution() {
    return this.zzan;
  }
  
  @KeepForSdk
  public final Status getStatus() {
    return this;
  }
  
  public final int getStatusCode() {
    return this.zzam;
  }
  
  @Nullable
  public final String getStatusMessage() {
    return this.zzao;
  }
  
  @VisibleForTesting
  public final boolean hasResolution() {
    return (this.zzan != null);
  }
  
  public final int hashCode() {
    return Objects.hashCode(new Object[] { Integer.valueOf(this.zzal), Integer.valueOf(this.zzam), this.zzao, this.zzan });
  }
  
  public final boolean isCanceled() {
    return (this.zzam == 16);
  }
  
  public final boolean isInterrupted() {
    return (this.zzam == 14);
  }
  
  public final boolean isSuccess() {
    return (this.zzam <= 0);
  }
  
  public final void startResolutionForResult(Activity paramActivity, int paramInt) throws IntentSender.SendIntentException {
    if (!hasResolution())
      return; 
    paramActivity.startIntentSenderForResult(this.zzan.getIntentSender(), paramInt, null, 0, 0, 0);
  }
  
  public final String toString() {
    return Objects.toStringHelper(this).add("statusCode", zzp()).add("resolution", this.zzan).toString();
  }
  
  @KeepForSdk
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, getStatusCode());
    SafeParcelWriter.writeString(paramParcel, 2, getStatusMessage(), false);
    SafeParcelWriter.writeParcelable(paramParcel, 3, (Parcelable)this.zzan, paramInt, false);
    SafeParcelWriter.writeInt(paramParcel, 1000, this.zzal);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
  
  public final String zzp() {
    return (this.zzao != null) ? this.zzao : CommonStatusCodes.getStatusCodeString(this.zzam);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\Status.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */