package com.google.android.gms.common.config;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Process;
import android.os.StrictMode;
import android.os.UserManager;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.stable.zzg;
import com.google.android.gms.internal.stable.zzi;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

public abstract class GservicesValue<T> {
  private static final Object sLock = new Object();
  
  private static zza zzmu;
  
  private static int zzmv = 0;
  
  private static Context zzmw;
  
  private static String zzmx = "com.google.android.providers.gsf.permission.READ_GSERVICES";
  
  @GuardedBy("sLock")
  private static HashSet<String> zzmy;
  
  protected final T mDefaultValue;
  
  protected final String mKey;
  
  private T zzmz = null;
  
  protected GservicesValue(String paramString, T paramT) {
    this.mKey = paramString;
    this.mDefaultValue = paramT;
  }
  
  @Deprecated
  @VisibleForTesting
  public static void forceInit(Context paramContext) {
    forceInit(paramContext, new HashSet<>());
  }
  
  @VisibleForTesting
  public static void forceInit(Context paramContext, @Nullable HashSet<String> paramHashSet) {
    zza(paramContext, new zzd(paramContext.getContentResolver()), paramHashSet);
  }
  
  @TargetApi(24)
  public static SharedPreferences getDirectBootCache(Context paramContext) {
    return paramContext.getApplicationContext().createDeviceProtectedStorageContext().getSharedPreferences("gservices-direboot-cache", 0);
  }
  
  public static int getSharedUserId() {
    return zzmv;
  }
  
  @Deprecated
  public static void init(Context paramContext) {
    HashSet hashSet;
    if (zzd(paramContext)) {
      hashSet = new HashSet();
    } else {
      hashSet = null;
    } 
    init(paramContext, hashSet);
  }
  
  public static void init(Context paramContext, @Nullable HashSet<String> paramHashSet) {
    synchronized (sLock) {
      if (zzmu == null) {
        zzd zzd = new zzd();
        this(paramContext.getContentResolver());
        zza(paramContext, zzd, paramHashSet);
      } 
      int i = zzmv;
      if (i == 0)
        try {
          zzmv = (paramContext.getPackageManager().getApplicationInfo("com.google.android.gms", 0)).uid;
        } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
          Log.e("GservicesValue", nameNotFoundException.toString());
        }  
      return;
    } 
  }
  
  @Deprecated
  @VisibleForTesting
  public static void initForTests() {
    zza(null, new zzb(null), new HashSet<>());
  }
  
  @VisibleForTesting
  public static void initForTests(Context paramContext, @Nullable HashSet<String> paramHashSet) {
    zza(paramContext, new zzb(null), paramHashSet);
  }
  
  @Deprecated
  @VisibleForTesting
  public static void initForTests(String paramString, Object paramObject) {
    HashMap<Object, Object> hashMap = new HashMap<>(1);
    hashMap.put(paramString, paramObject);
    initForTests((Map)hashMap);
  }
  
  @Deprecated
  @VisibleForTesting
  public static void initForTests(Map<String, ?> paramMap) {
    synchronized (sLock) {
      zzc zzc = new zzc();
      this(paramMap);
      zzmu = zzc;
      return;
    } 
  }
  
  public static boolean isInitialized() {
    synchronized (sLock) {
      boolean bool;
      if (zzmu != null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
  }
  
  public static GservicesValue<String> partnerSetting(String paramString1, String paramString2) {
    return new zzg(paramString1, paramString2);
  }
  
  @VisibleForTesting
  public static void resetAllOverrides() {
    synchronized (sLock) {
      if (zzcg()) {
        Iterator<GservicesValue> iterator = zzb.zzci().iterator();
        while (iterator.hasNext())
          ((GservicesValue)iterator.next()).resetOverride(); 
        zzb.zzci().clear();
      } 
      return;
    } 
  }
  
  public static GservicesValue<Double> value(String paramString, Double paramDouble) {
    return new zzd(paramString, paramDouble);
  }
  
  public static GservicesValue<Float> value(String paramString, Float paramFloat) {
    return new zze(paramString, paramFloat);
  }
  
  public static GservicesValue<Integer> value(String paramString, Integer paramInteger) {
    return new zzc(paramString, paramInteger);
  }
  
  public static GservicesValue<Long> value(String paramString, Long paramLong) {
    return new zzb(paramString, paramLong);
  }
  
  public static GservicesValue<String> value(String paramString1, String paramString2) {
    return new zzf(paramString1, paramString2);
  }
  
  public static GservicesValue<Boolean> value(String paramString, boolean paramBoolean) {
    return new zza(paramString, Boolean.valueOf(paramBoolean));
  }
  
  @TargetApi(24)
  private static void zza(@Nullable Context paramContext, zza paramzza, @Nullable HashSet<String> paramHashSet) {
    synchronized (sLock) {
      zzmu = paramzza;
      zzmy = null;
      zzmw = null;
      if (paramContext != null && zzd(paramContext)) {
        zzmy = paramHashSet;
        zzmw = paramContext.getApplicationContext().createDeviceProtectedStorageContext();
      } 
      return;
    } 
  }
  
  private static boolean zzcg() {
    synchronized (sLock) {
      if (zzmu instanceof zzb || zzmu instanceof zzc)
        return true; 
      return false;
    } 
  }
  
  @TargetApi(24)
  private static boolean zzd(Context paramContext) {
    if (!PlatformVersion.isAtLeastN())
      return false; 
    UserManager userManager = (UserManager)paramContext.getSystemService(UserManager.class);
    return userManager.isUserUnlocked() ? false : (!userManager.isUserRunning(Process.myUserHandle()));
  }
  
  public final T get() {
    if (this.zzmz != null)
      return this.zzmz; 
    null = StrictMode.allowThreadDiskReads();
    synchronized (sLock) {
      boolean bool;
      if (zzmw != null && zzd(zzmw)) {
        bool = true;
      } else {
        bool = false;
      } 
      HashSet<String> hashSet = zzmy;
      Context context = zzmw;
      if (bool) {
        if (Log.isLoggable("GservicesValue", 3)) {
          null = String.valueOf(this.mKey);
          if (null.length() != 0) {
            null = "Gservice value accessed during directboot: ".concat(null);
          } else {
            null = new String("Gservice value accessed during directboot: ");
          } 
          Log.d("GservicesValue", null);
        } 
        if (hashSet != null && !hashSet.contains(this.mKey)) {
          null = String.valueOf(this.mKey);
          if (null.length() != 0) {
            null = "Gservices key not whitelisted for directboot access: ".concat(null);
          } else {
            null = new String("Gservices key not whitelisted for directboot access: ");
          } 
          Log.e("GservicesValue", null);
          return this.mDefaultValue;
        } 
        return retrieveFromDirectBootCache(context, this.mKey, this.mDefaultValue);
      } 
      synchronized (sLock) {
        zzmy = null;
        zzmw = null;
        try {
          null = retrieve(this.mKey);
          StrictMode.setThreadPolicy((StrictMode.ThreadPolicy)null);
          return (T)null;
        } catch (SecurityException null) {
          long l = Binder.clearCallingIdentity();
          try {
            null = (SecurityException)retrieve(this.mKey);
            Binder.restoreCallingIdentity(l);
            return (T)null;
          } finally {
            Binder.restoreCallingIdentity(l);
          } 
        } finally {}
        StrictMode.setThreadPolicy((StrictMode.ThreadPolicy)null);
        throw null;
      } 
    } 
  }
  
  @Deprecated
  public final T getBinderSafe() {
    return get();
  }
  
  public String getKey() {
    return this.mKey;
  }
  
  @VisibleForTesting
  public void override(T paramT) {
    if (!(zzmu instanceof zzb))
      Log.w("GservicesValue", "GservicesValue.override(): test should probably call initForTests() first"); 
    this.zzmz = paramT;
    synchronized (sLock) {
      if (zzcg())
        zzb.zzci().add(this); 
      return;
    } 
  }
  
  @VisibleForTesting
  public void resetOverride() {
    this.zzmz = null;
  }
  
  protected abstract T retrieve(String paramString);
  
  @TargetApi(24)
  protected T retrieveFromDirectBootCache(Context paramContext, String paramString, T paramT) {
    throw new UnsupportedOperationException("The Gservices classes used does not support direct-boot");
  }
  
  private static interface zza {
    Long getLong(String param1String, Long param1Long);
    
    String getString(String param1String1, String param1String2);
    
    Boolean zza(String param1String, Boolean param1Boolean);
    
    Double zza(String param1String, Double param1Double);
    
    Float zza(String param1String, Float param1Float);
    
    Integer zza(String param1String, Integer param1Integer);
    
    String zzb(String param1String1, String param1String2);
  }
  
  private static final class zzb implements zza {
    private static final Collection<GservicesValue<?>> zzna = new HashSet<>();
    
    private zzb() {}
    
    public final Long getLong(String param1String, Long param1Long) {
      return param1Long;
    }
    
    public final String getString(String param1String1, String param1String2) {
      return param1String2;
    }
    
    public final Boolean zza(String param1String, Boolean param1Boolean) {
      return param1Boolean;
    }
    
    public final Double zza(String param1String, Double param1Double) {
      return param1Double;
    }
    
    public final Float zza(String param1String, Float param1Float) {
      return param1Float;
    }
    
    public final Integer zza(String param1String, Integer param1Integer) {
      return param1Integer;
    }
    
    public final String zzb(String param1String1, String param1String2) {
      return param1String2;
    }
  }
  
  @Deprecated
  private static final class zzc implements zza {
    private final Map<String, ?> values;
    
    public zzc(Map<String, ?> param1Map) {
      this.values = param1Map;
    }
    
    private final <T> T zza(String param1String, T param1T) {
      return (T)(this.values.containsKey(param1String) ? this.values.get(param1String) : (Object)param1T);
    }
    
    public final Long getLong(String param1String, Long param1Long) {
      return zza(param1String, param1Long);
    }
    
    public final String getString(String param1String1, String param1String2) {
      return zza(param1String1, param1String2);
    }
    
    public final Boolean zza(String param1String, Boolean param1Boolean) {
      return zza(param1String, param1Boolean);
    }
    
    public final Double zza(String param1String, Double param1Double) {
      return zza(param1String, param1Double);
    }
    
    public final Float zza(String param1String, Float param1Float) {
      return zza(param1String, param1Float);
    }
    
    public final Integer zza(String param1String, Integer param1Integer) {
      return zza(param1String, param1Integer);
    }
    
    public final String zzb(String param1String1, String param1String2) {
      return zza(param1String1, param1String2);
    }
  }
  
  private static final class zzd implements zza {
    private final ContentResolver mContentResolver;
    
    public zzd(ContentResolver param1ContentResolver) {
      this.mContentResolver = param1ContentResolver;
    }
    
    public final Long getLong(String param1String, Long param1Long) {
      return Long.valueOf(zzi.getLong(this.mContentResolver, param1String, param1Long.longValue()));
    }
    
    public final String getString(String param1String1, String param1String2) {
      return zzi.zza(this.mContentResolver, param1String1, param1String2);
    }
    
    public final Boolean zza(String param1String, Boolean param1Boolean) {
      return Boolean.valueOf(zzi.zza(this.mContentResolver, param1String, param1Boolean.booleanValue()));
    }
    
    public final Double zza(String param1String, Double param1Double) {
      param1String = zzi.zza(this.mContentResolver, param1String, null);
      if (param1String != null)
        try {
          double d = Double.parseDouble(param1String);
          return Double.valueOf(d);
        } catch (NumberFormatException numberFormatException) {} 
      return param1Double;
    }
    
    public final Float zza(String param1String, Float param1Float) {
      param1String = zzi.zza(this.mContentResolver, param1String, null);
      if (param1String != null)
        try {
          float f = Float.parseFloat(param1String);
          return Float.valueOf(f);
        } catch (NumberFormatException numberFormatException) {} 
      return param1Float;
    }
    
    public final Integer zza(String param1String, Integer param1Integer) {
      return Integer.valueOf(zzi.getInt(this.mContentResolver, param1String, param1Integer.intValue()));
    }
    
    public final String zzb(String param1String1, String param1String2) {
      return zzg.zza(this.mContentResolver, param1String1, param1String2);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\config\GservicesValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */