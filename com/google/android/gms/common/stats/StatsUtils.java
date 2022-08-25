package com.google.android.gms.common.stats;

import android.content.AbstractThreadedSyncAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;

public class StatsUtils {
  public static String getEventKey(AbstractThreadedSyncAdapter paramAbstractThreadedSyncAdapter, String paramString) {
    String str2 = String.valueOf(String.valueOf(Process.myPid() << 32L | System.identityHashCode(paramAbstractThreadedSyncAdapter)));
    String str1 = paramString;
    if (TextUtils.isEmpty(paramString))
      str1 = ""; 
    str1 = String.valueOf(str1);
    return (str1.length() != 0) ? str2.concat(str1) : new String(str2);
  }
  
  public static String getEventKey(Context paramContext, Intent paramIntent) {
    long l = System.identityHashCode(paramContext);
    return String.valueOf(System.identityHashCode(paramIntent) | l << 32L);
  }
  
  public static String getEventKey(PowerManager.WakeLock paramWakeLock, String paramString) {
    String str2 = String.valueOf(String.valueOf(Process.myPid() << 32L | System.identityHashCode(paramWakeLock)));
    String str1 = paramString;
    if (TextUtils.isEmpty(paramString))
      str1 = ""; 
    str1 = String.valueOf(str1);
    return (str1.length() != 0) ? str2.concat(str1) : new String(str2);
  }
  
  public static boolean isLoggingEnabled() {
    StatisticalEventTrackerProvider.StatisticalEventTracker statisticalEventTracker = StatisticalEventTrackerProvider.getImpl();
    return (statisticalEventTracker != null && statisticalEventTracker.isEnabled() && (zza(Integer.valueOf(statisticalEventTracker.getLogLevel(3))) || zza(Integer.valueOf(statisticalEventTracker.getLogLevel(2))) || zza(Integer.valueOf(statisticalEventTracker.getLogLevel(1)))));
  }
  
  public static boolean isTimeOutEvent(StatsEvent paramStatsEvent) {
    int i = paramStatsEvent.getEventType();
    return !(i != 6 && i != 9 && i != 12);
  }
  
  private static boolean zza(Integer paramInteger) {
    return !paramInteger.equals(Integer.valueOf(LoggingConstants.LOG_LEVEL_OFF));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\stats\StatsUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */