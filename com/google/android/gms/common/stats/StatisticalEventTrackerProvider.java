package com.google.android.gms.common.stats;

import android.os.Bundle;

public class StatisticalEventTrackerProvider {
  private static StatisticalEventTracker zzyp;
  
  public static StatisticalEventTracker getImpl() {
    return zzyp;
  }
  
  public static void setImpl(StatisticalEventTracker paramStatisticalEventTracker) {
    zzyp = paramStatisticalEventTracker;
  }
  
  public static interface StatisticalEventTracker {
    int getLogLevel(int param1Int);
    
    Bundle getOptions();
    
    boolean isEnabled();
    
    void registerEvent(ConnectionEvent param1ConnectionEvent);
    
    void registerEvent(StatsEvent param1StatsEvent);
    
    void registerEvent(WakeLockEvent param1WakeLockEvent);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\stats\StatisticalEventTrackerProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */