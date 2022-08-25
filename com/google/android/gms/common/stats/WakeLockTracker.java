package com.google.android.gms.common.stats;

import android.content.AbstractThreadedSyncAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.DeviceStateUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Arrays;
import java.util.List;

public class WakeLockTracker {
  @VisibleForTesting
  private static boolean zzyh = false;
  
  private static WakeLockTracker zzyz = new WakeLockTracker();
  
  private static Boolean zzza;
  
  public static WakeLockTracker getInstance() {
    return zzyz;
  }
  
  public void registerAcquireEvent(Context paramContext, Intent paramIntent, String paramString1, String paramString2, String paramString3, int paramInt, String paramString4) {
    registerAcquireEvent(paramContext, paramIntent, paramString1, paramString2, paramString3, paramInt, Arrays.asList(new String[] { paramString4 }));
  }
  
  public void registerAcquireEvent(Context paramContext, Intent paramIntent, String paramString1, String paramString2, String paramString3, int paramInt, List<String> paramList) {
    registerEvent(paramContext, paramIntent.getStringExtra("WAKE_LOCK_KEY"), 7, paramString1, paramString2, paramString3, paramInt, paramList);
  }
  
  public void registerEvent(Context paramContext, String paramString1, int paramInt1, String paramString2, String paramString3, String paramString4, int paramInt2, List<String> paramList) {
    registerEvent(paramContext, paramString1, paramInt1, paramString2, paramString3, paramString4, paramInt2, paramList, 0L);
  }
  
  public void registerEvent(Context paramContext, String paramString1, int paramInt1, String paramString2, String paramString3, String paramString4, int paramInt2, List<String> paramList, long paramLong) {
    String str;
    List<String> list = paramList;
    if (zzza == null)
      zzza = Boolean.valueOf(false); 
    if (!zzza.booleanValue())
      return; 
    if (TextUtils.isEmpty(paramString1)) {
      str = String.valueOf(paramString1);
      if (str.length() != 0) {
        str = "missing wakeLock key. ".concat(str);
      } else {
        str = new String("missing wakeLock key. ");
      } 
      Log.e("WakeLockTracker", str);
      return;
    } 
    long l = System.currentTimeMillis();
    if (7 == paramInt1 || 8 == paramInt1 || 10 == paramInt1 || 11 == paramInt1) {
      List<String> list1 = list;
      if (list != null) {
        list1 = list;
        if (paramList.size() == 1) {
          list1 = list;
          if ("com.google.android.gms".equals(list.get(0)))
            list1 = null; 
        } 
      } 
      long l1 = SystemClock.elapsedRealtime();
      int i = DeviceStateUtils.getDeviceState((Context)str);
      String str1 = str.getPackageName();
      if ("com.google.android.gms".equals(str1))
        str1 = null; 
      WakeLockEvent wakeLockEvent = new WakeLockEvent(l, paramInt1, paramString2, paramInt2, list1, paramString1, l1, i, paramString3, str1, DeviceStateUtils.getPowerPercentage((Context)str), paramLong, paramString4);
      try {
        Intent intent = new Intent();
        this();
        str.startService(intent.setComponent(LoggingConstants.STATS_SERVICE_COMPONENT_NAME).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", (Parcelable)wakeLockEvent));
        return;
      } catch (Exception exception) {
        Log.wtf("WakeLockTracker", exception);
      } 
    } 
  }
  
  public void registerReleaseEvent(Context paramContext, Intent paramIntent) {
    registerEvent(paramContext, paramIntent.getStringExtra("WAKE_LOCK_KEY"), 8, null, null, null, 0, null);
  }
  
  public void registerSyncEnd(Context paramContext, AbstractThreadedSyncAdapter paramAbstractThreadedSyncAdapter, String paramString1, String paramString2, boolean paramBoolean) {
    registerEvent(paramContext, StatsUtils.getEventKey(paramAbstractThreadedSyncAdapter, paramString1), 11, paramString1, paramString2, null, 0, null);
  }
  
  public void registerSyncStart(Context paramContext, AbstractThreadedSyncAdapter paramAbstractThreadedSyncAdapter, String paramString1, String paramString2) {
    registerEvent(paramContext, StatsUtils.getEventKey(paramAbstractThreadedSyncAdapter, paramString1), 10, paramString1, paramString2, null, 0, null);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\stats\WakeLockTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */