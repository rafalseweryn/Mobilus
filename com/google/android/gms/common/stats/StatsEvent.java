package com.google.android.gms.common.stats;

import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public abstract class StatsEvent extends AbstractSafeParcelable implements ReflectedParcelable {
  public abstract StatsEvent ReconstructCloseEvent(StatsEvent paramStatsEvent);
  
  public abstract long getDurationMillis();
  
  public abstract long getElapsedRealtime();
  
  public abstract String getEventKey();
  
  public abstract int getEventType();
  
  public abstract String getSpecificString();
  
  public abstract long getTimeMillis();
  
  public abstract long getTimeout();
  
  public abstract StatsEvent markTimeOut();
  
  public abstract StatsEvent setDurationMillis(long paramLong);
  
  public abstract StatsEvent setEventType(int paramInt);
  
  public String toString() {
    long l1 = getTimeMillis();
    int i = getEventType();
    long l2 = getDurationMillis();
    String str = getSpecificString();
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 53);
    stringBuilder.append(l1);
    stringBuilder.append("\t");
    stringBuilder.append(i);
    stringBuilder.append("\t");
    stringBuilder.append(l2);
    stringBuilder.append(str);
    return stringBuilder.toString();
  }
  
  public static interface Types {
    public static final int EVENT_TYPE_ACQUIRE_WAKE_LOCK = 7;
    
    public static final int EVENT_TYPE_BIND = 2;
    
    public static final int EVENT_TYPE_CONNECT = 3;
    
    public static final int EVENT_TYPE_CONNECTION_TIME_OUT = 6;
    
    public static final int EVENT_TYPE_DISCONNECT = 4;
    
    public static final int EVENT_TYPE_RELEASE_WAKE_LOCK = 8;
    
    public static final int EVENT_TYPE_START_SERVICE = 13;
    
    public static final int EVENT_TYPE_STOP_ALL_SERVICE = 15;
    
    public static final int EVENT_TYPE_STOP_SERVICE = 14;
    
    public static final int EVENT_TYPE_SYNC_END = 11;
    
    public static final int EVENT_TYPE_SYNC_START = 10;
    
    public static final int EVENT_TYPE_SYNC_TIME_OUT = 12;
    
    public static final int EVENT_TYPE_UNBIND = 1;
    
    public static final int EVENT_TYPE_UNKNOWN = -1;
    
    public static final int EVENT_TYPE_WAKE_LOCK_TIME_OUT = 9;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\stats\StatsEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */