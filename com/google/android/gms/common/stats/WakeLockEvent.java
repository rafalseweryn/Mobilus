package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.List;
import javax.annotation.Nullable;

@Class(creator = "WakeLockEventCreator")
public final class WakeLockEvent extends StatsEvent {
  public static final Parcelable.Creator<WakeLockEvent> CREATOR = new WakeLockEventCreator();
  
  @Field(getter = "getTimeout", id = 16)
  private final long mTimeout;
  
  @VersionField(id = 1)
  private final int zzal;
  
  @Field(getter = "getTimeMillis", id = 2)
  private final long zzxv;
  
  @Field(getter = "getEventType", id = 11)
  private int zzxw;
  
  @Field(getter = "getElapsedRealtime", id = 8)
  private final long zzyd;
  
  private long zzyf;
  
  @Field(getter = "getWakeLockName", id = 4)
  private final String zzyq;
  
  @Field(getter = "getSecondaryWakeLockName", id = 10)
  private final String zzyr;
  
  @Field(getter = "getCodePackage", id = 17)
  private final String zzys;
  
  @Field(getter = "getWakeLockType", id = 5)
  private final int zzyt;
  
  @Field(getter = "getCallingPackages", id = 6)
  private final List<String> zzyu;
  
  @Field(getter = "getEventKey", id = 12)
  private final String zzyv;
  
  @Field(getter = "getDeviceState", id = 14)
  private int zzyw;
  
  @Field(getter = "getHostPackage", id = 13)
  private final String zzyx;
  
  @Field(getter = "getBeginPowerPercentage", id = 15)
  private final float zzyy;
  
  @Constructor
  WakeLockEvent(@Param(id = 1) int paramInt1, @Param(id = 2) long paramLong1, @Param(id = 11) int paramInt2, @Param(id = 4) String paramString1, @Param(id = 5) int paramInt3, @Param(id = 6) List<String> paramList, @Param(id = 12) String paramString2, @Param(id = 8) long paramLong2, @Param(id = 14) int paramInt4, @Param(id = 10) String paramString3, @Param(id = 13) String paramString4, @Param(id = 15) float paramFloat, @Param(id = 16) long paramLong3, @Param(id = 17) String paramString5) {
    this.zzal = paramInt1;
    this.zzxv = paramLong1;
    this.zzxw = paramInt2;
    this.zzyq = paramString1;
    this.zzyr = paramString3;
    this.zzys = paramString5;
    this.zzyt = paramInt3;
    this.zzyf = -1L;
    this.zzyu = paramList;
    this.zzyv = paramString2;
    this.zzyd = paramLong2;
    this.zzyw = paramInt4;
    this.zzyx = paramString4;
    this.zzyy = paramFloat;
    this.mTimeout = paramLong3;
  }
  
  public WakeLockEvent(long paramLong1, int paramInt1, String paramString1, int paramInt2, List<String> paramList, String paramString2, long paramLong2, int paramInt3, String paramString3, String paramString4, float paramFloat, long paramLong3, String paramString5) {
    this(2, paramLong1, paramInt1, paramString1, paramInt2, paramList, paramString2, paramLong2, paramInt3, paramString3, paramString4, paramFloat, paramLong3, paramString5);
  }
  
  public WakeLockEvent(WakeLockEvent paramWakeLockEvent) {
    this(paramWakeLockEvent.zzal, paramWakeLockEvent.getTimeMillis(), paramWakeLockEvent.getEventType(), paramWakeLockEvent.getWakeLockName(), paramWakeLockEvent.getWakeLockType(), paramWakeLockEvent.getCallingPackages(), paramWakeLockEvent.getEventKey(), paramWakeLockEvent.getElapsedRealtime(), paramWakeLockEvent.getDeviceState(), paramWakeLockEvent.getSecondaryWakeLockName(), paramWakeLockEvent.getHostPackage(), paramWakeLockEvent.getBeginPowerPercentage(), paramWakeLockEvent.getTimeout(), paramWakeLockEvent.getCodePackage());
  }
  
  public static boolean checkEventType(StatsEvent paramStatsEvent) {
    return (7 == paramStatsEvent.getEventType() || 8 == paramStatsEvent.getEventType() || 9 == paramStatsEvent.getEventType() || 10 == paramStatsEvent.getEventType() || 11 == paramStatsEvent.getEventType() || 12 == paramStatsEvent.getEventType());
  }
  
  public final StatsEvent ReconstructCloseEvent(StatsEvent paramStatsEvent) {
    if (paramStatsEvent instanceof WakeLockEvent) {
      paramStatsEvent = paramStatsEvent;
      return (new WakeLockEvent((WakeLockEvent)paramStatsEvent)).setEventType(super.getEventType()).setDurationMillis(super.getElapsedRealtime() - paramStatsEvent.getElapsedRealtime());
    } 
    return paramStatsEvent;
  }
  
  public final float getBeginPowerPercentage() {
    return this.zzyy;
  }
  
  @Nullable
  public final List<String> getCallingPackages() {
    return this.zzyu;
  }
  
  public final String getCodePackage() {
    return this.zzys;
  }
  
  public final int getDeviceState() {
    return this.zzyw;
  }
  
  public final long getDurationMillis() {
    return this.zzyf;
  }
  
  public final long getElapsedRealtime() {
    return this.zzyd;
  }
  
  public final String getEventKey() {
    return this.zzyv;
  }
  
  public final int getEventType() {
    return this.zzxw;
  }
  
  public final String getHostPackage() {
    return this.zzyx;
  }
  
  public final String getSecondaryWakeLockName() {
    return this.zzyr;
  }
  
  public final String getSpecificString() {
    String str2;
    String str3;
    String str4;
    String str5;
    String str1 = getWakeLockName();
    int i = getWakeLockType();
    if (getCallingPackages() == null) {
      str2 = "";
    } else {
      str2 = TextUtils.join(",", getCallingPackages());
    } 
    int j = getDeviceState();
    if (getSecondaryWakeLockName() == null) {
      str3 = "";
    } else {
      str3 = getSecondaryWakeLockName();
    } 
    if (getHostPackage() == null) {
      str4 = "";
    } else {
      str4 = getHostPackage();
    } 
    float f = getBeginPowerPercentage();
    if (getCodePackage() == null) {
      str5 = "";
    } else {
      str5 = getCodePackage();
    } 
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 45 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length() + String.valueOf(str5).length());
    stringBuilder.append("\t");
    stringBuilder.append(str1);
    stringBuilder.append("\t");
    stringBuilder.append(i);
    stringBuilder.append("\t");
    stringBuilder.append(str2);
    stringBuilder.append("\t");
    stringBuilder.append(j);
    stringBuilder.append("\t");
    stringBuilder.append(str3);
    stringBuilder.append("\t");
    stringBuilder.append(str4);
    stringBuilder.append("\t");
    stringBuilder.append(f);
    stringBuilder.append("\t");
    stringBuilder.append(str5);
    return stringBuilder.toString();
  }
  
  public final long getTimeMillis() {
    return this.zzxv;
  }
  
  public final long getTimeout() {
    return this.mTimeout;
  }
  
  public final String getWakeLockName() {
    return this.zzyq;
  }
  
  public final int getWakeLockType() {
    return this.zzyt;
  }
  
  public final WakeLockEvent markTimeOut() {
    if (this.mTimeout != 0L)
      this.zzyf = this.mTimeout; 
    if (7 == this.zzxw) {
      byte b = 9;
      this.zzxw = b;
      return this;
    } 
    if (10 == this.zzxw) {
      byte b = 12;
      this.zzxw = b;
      return this;
    } 
    return this;
  }
  
  public final StatsEvent setDurationMillis(long paramLong) {
    this.zzyf = paramLong;
    return this;
  }
  
  public final StatsEvent setEventType(int paramInt) {
    this.zzxw = paramInt;
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzal);
    SafeParcelWriter.writeLong(paramParcel, 2, super.getTimeMillis());
    SafeParcelWriter.writeString(paramParcel, 4, getWakeLockName(), false);
    SafeParcelWriter.writeInt(paramParcel, 5, getWakeLockType());
    SafeParcelWriter.writeStringList(paramParcel, 6, getCallingPackages(), false);
    SafeParcelWriter.writeLong(paramParcel, 8, super.getElapsedRealtime());
    SafeParcelWriter.writeString(paramParcel, 10, getSecondaryWakeLockName(), false);
    SafeParcelWriter.writeInt(paramParcel, 11, super.getEventType());
    SafeParcelWriter.writeString(paramParcel, 12, super.getEventKey(), false);
    SafeParcelWriter.writeString(paramParcel, 13, getHostPackage(), false);
    SafeParcelWriter.writeInt(paramParcel, 14, getDeviceState());
    SafeParcelWriter.writeFloat(paramParcel, 15, getBeginPowerPercentage());
    SafeParcelWriter.writeLong(paramParcel, 16, super.getTimeout());
    SafeParcelWriter.writeString(paramParcel, 17, getCodePackage(), false);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\stats\WakeLockEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */