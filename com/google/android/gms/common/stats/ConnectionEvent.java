package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import javax.annotation.Nullable;

@Class(creator = "ConnectionEventCreator")
public final class ConnectionEvent extends StatsEvent {
  public static final Parcelable.Creator<ConnectionEvent> CREATOR = new ConnectionEventCreator();
  
  @VersionField(id = 1)
  private final int zzal;
  
  @Field(getter = "getTimeMillis", id = 2)
  private final long zzxv;
  
  @Field(getter = "getEventType", id = 12)
  private int zzxw;
  
  @Field(getter = "getCallingProcess", id = 4)
  private final String zzxx;
  
  @Field(getter = "getCallingService", id = 5)
  private final String zzxy;
  
  @Field(getter = "getTargetProcess", id = 6)
  private final String zzxz;
  
  @Field(getter = "getTargetService", id = 7)
  private final String zzya;
  
  @Field(getter = "getStackTrace", id = 8)
  private final String zzyb;
  
  @Field(getter = "getEventKey", id = 13)
  private final String zzyc;
  
  @Field(getter = "getElapsedRealtime", id = 10)
  private final long zzyd;
  
  @Field(getter = "getHeapAlloc", id = 11)
  private final long zzye;
  
  private long zzyf;
  
  @Constructor
  ConnectionEvent(@Param(id = 1) int paramInt1, @Param(id = 2) long paramLong1, @Param(id = 12) int paramInt2, @Param(id = 4) String paramString1, @Param(id = 5) String paramString2, @Param(id = 6) String paramString3, @Param(id = 7) String paramString4, @Param(id = 8) String paramString5, @Param(id = 13) String paramString6, @Param(id = 10) long paramLong2, @Param(id = 11) long paramLong3) {
    this.zzal = paramInt1;
    this.zzxv = paramLong1;
    this.zzxw = paramInt2;
    this.zzxx = paramString1;
    this.zzxy = paramString2;
    this.zzxz = paramString3;
    this.zzya = paramString4;
    this.zzyf = -1L;
    this.zzyb = paramString5;
    this.zzyc = paramString6;
    this.zzyd = paramLong2;
    this.zzye = paramLong3;
  }
  
  public ConnectionEvent(long paramLong1, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, long paramLong2, long paramLong3) {
    this(1, paramLong1, paramInt, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramLong2, paramLong3);
  }
  
  public ConnectionEvent(ConnectionEvent paramConnectionEvent) {
    this(paramConnectionEvent.zzal, paramConnectionEvent.getTimeMillis(), paramConnectionEvent.getEventType(), paramConnectionEvent.getCallingProcess(), paramConnectionEvent.getCallingService(), paramConnectionEvent.getTargetProcess(), paramConnectionEvent.getTargetService(), paramConnectionEvent.getStackTrace(), paramConnectionEvent.getEventKey(), paramConnectionEvent.getElapsedRealtime(), paramConnectionEvent.getHeapAlloc());
  }
  
  public static boolean checkEventType(StatsEvent paramStatsEvent) {
    return (2 != paramStatsEvent.getEventType() && 3 != paramStatsEvent.getEventType() && 4 != paramStatsEvent.getEventType() && 1 != paramStatsEvent.getEventType() && 6 != paramStatsEvent.getEventType() && 13 != paramStatsEvent.getEventType() && 14 != paramStatsEvent.getEventType()) ? ((15 == paramStatsEvent.getEventType())) : true;
  }
  
  public final StatsEvent ReconstructCloseEvent(StatsEvent paramStatsEvent) {
    if (paramStatsEvent instanceof ConnectionEvent) {
      paramStatsEvent = paramStatsEvent;
      return ((ConnectionEvent)(new ConnectionEvent((ConnectionEvent)paramStatsEvent)).setEventType(super.getEventType())).setDurationMillis(super.getElapsedRealtime() - paramStatsEvent.getElapsedRealtime());
    } 
    return paramStatsEvent;
  }
  
  public final String getCallingProcess() {
    return this.zzxx;
  }
  
  public final String getCallingService() {
    return this.zzxy;
  }
  
  public final long getDurationMillis() {
    return this.zzyf;
  }
  
  public final long getElapsedRealtime() {
    return this.zzyd;
  }
  
  public final String getEventKey() {
    return this.zzyc;
  }
  
  public final int getEventType() {
    return this.zzxw;
  }
  
  public final long getHeapAlloc() {
    return this.zzye;
  }
  
  public final String getSpecificString() {
    String str5;
    String str1 = getCallingProcess();
    String str2 = getCallingService();
    String str3 = getTargetProcess();
    String str4 = getTargetService();
    if (this.zzyb == null) {
      str5 = "";
    } else {
      str5 = this.zzyb;
    } 
    long l = getHeapAlloc();
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 26 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length() + String.valueOf(str5).length());
    stringBuilder.append("\t");
    stringBuilder.append(str1);
    stringBuilder.append("/");
    stringBuilder.append(str2);
    stringBuilder.append("\t");
    stringBuilder.append(str3);
    stringBuilder.append("/");
    stringBuilder.append(str4);
    stringBuilder.append("\t");
    stringBuilder.append(str5);
    stringBuilder.append("\t");
    stringBuilder.append(l);
    return stringBuilder.toString();
  }
  
  @Nullable
  public final String getStackTrace() {
    return this.zzyb;
  }
  
  public final String getTargetProcess() {
    return this.zzxz;
  }
  
  public final String getTargetService() {
    return this.zzya;
  }
  
  public final long getTimeMillis() {
    return this.zzxv;
  }
  
  public final long getTimeout() {
    return 0L;
  }
  
  public final ConnectionEvent markTimeOut() {
    this.zzxw = 6;
    return this;
  }
  
  public final ConnectionEvent setDurationMillis(long paramLong) {
    this.zzyf = paramLong;
    return this;
  }
  
  public final ConnectionEvent setEventType(int paramInt) {
    this.zzxw = paramInt;
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzal);
    SafeParcelWriter.writeLong(paramParcel, 2, super.getTimeMillis());
    SafeParcelWriter.writeString(paramParcel, 4, getCallingProcess(), false);
    SafeParcelWriter.writeString(paramParcel, 5, getCallingService(), false);
    SafeParcelWriter.writeString(paramParcel, 6, getTargetProcess(), false);
    SafeParcelWriter.writeString(paramParcel, 7, getTargetService(), false);
    SafeParcelWriter.writeString(paramParcel, 8, getStackTrace(), false);
    SafeParcelWriter.writeLong(paramParcel, 10, super.getElapsedRealtime());
    SafeParcelWriter.writeLong(paramParcel, 11, getHeapAlloc());
    SafeParcelWriter.writeInt(paramParcel, 12, super.getEventType());
    SafeParcelWriter.writeString(paramParcel, 13, super.getEventKey(), false);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\stats\ConnectionEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */