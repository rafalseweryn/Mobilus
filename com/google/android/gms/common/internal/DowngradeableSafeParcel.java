package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable implements ReflectedParcelable {
  private static final Object zzsj = new Object();
  
  private static ClassLoader zzsk;
  
  private static Integer zzsl;
  
  private boolean zzsm = false;
  
  protected static boolean canUnparcelSafely(String paramString) {
    ClassLoader classLoader = getUnparcelClassLoader();
    if (classLoader == null)
      return true; 
    try {
      return zza(classLoader.loadClass(paramString));
    } catch (Exception exception) {
      return false;
    } 
  }
  
  static Bundle getExtras(Intent paramIntent, Context paramContext, Integer paramInteger) {
    if (paramContext != null)
      try {
        zza(paramContext.getClassLoader(), paramInteger);
        if (paramIntent.getExtras() != null) {
          Bundle bundle = new Bundle();
          this();
          bundle.putAll(paramIntent.getExtras());
          return bundle;
        } 
      } finally {
        zza((ClassLoader)null, (Integer)null);
      }  
    paramIntent = null;
    zza((ClassLoader)null, (Integer)null);
    return (Bundle)paramIntent;
  }
  
  static <T extends Parcelable> T getParcelable(Intent paramIntent, String paramString, Context paramContext, Integer paramInteger) {
    if (paramContext != null) {
      try {
        zza(paramContext.getClassLoader(), paramInteger);
        Parcelable parcelable = paramIntent.getParcelableExtra(paramString);
      } finally {
        zza((ClassLoader)null, (Integer)null);
      } 
    } else {
      paramIntent = null;
    } 
    zza((ClassLoader)null, (Integer)null);
    return (T)paramIntent;
  }
  
  static <T extends Parcelable> T getParcelable(Bundle paramBundle, String paramString, Context paramContext, Integer paramInteger) {
    if (paramContext != null) {
      try {
        zza(paramContext.getClassLoader(), paramInteger);
        Parcelable parcelable = paramBundle.getParcelable(paramString);
      } finally {
        zza((ClassLoader)null, (Integer)null);
      } 
    } else {
      paramBundle = null;
    } 
    zza((ClassLoader)null, (Integer)null);
    return (T)paramBundle;
  }
  
  protected static ClassLoader getUnparcelClassLoader() {
    synchronized (zzsj) {
      return zzsk;
    } 
  }
  
  protected static Integer getUnparcelClientVersion() {
    synchronized (zzsj) {
      return zzsl;
    } 
  }
  
  static boolean putParcelable(Bundle paramBundle, String paramString, DowngradeableSafeParcel paramDowngradeableSafeParcel, Context paramContext, Integer paramInteger) {
    boolean bool = false;
    if (paramContext == null && paramInteger == null)
      return false; 
    if (paramDowngradeableSafeParcel.zza(paramContext, paramInteger)) {
      paramBundle.putParcelable(paramString, paramDowngradeableSafeParcel);
      bool = true;
    } 
    return bool;
  }
  
  private static void zza(ClassLoader paramClassLoader, Integer paramInteger) {
    synchronized (zzsj) {
      zzsk = paramClassLoader;
      zzsl = paramInteger;
      return;
    } 
  }
  
  private final boolean zza(Context paramContext, Integer paramInteger) {
    if (paramInteger != null)
      return prepareForClientVersion(paramInteger.intValue()); 
    try {
      setShouldDowngrade(zza(paramContext.getClassLoader().loadClass(getClass().getCanonicalName())) ^ true);
      return true;
    } catch (Exception exception) {
      return false;
    } 
  }
  
  private static boolean zza(Class<?> paramClass) {
    try {
      return "SAFE_PARCELABLE_NULL_STRING".equals(paramClass.getField("NULL").get(null));
    } catch (NoSuchFieldException|IllegalAccessException noSuchFieldException) {
      return false;
    } 
  }
  
  protected abstract boolean prepareForClientVersion(int paramInt);
  
  public void setShouldDowngrade(boolean paramBoolean) {
    this.zzsm = paramBoolean;
  }
  
  protected boolean shouldDowngrade() {
    return this.zzsm;
  }
  
  public static final class DowngradeableSafeParcelHelper {
    public static Bundle getExtras(Intent param1Intent, Context param1Context, Integer param1Integer) {
      synchronized (DowngradeableSafeParcel.zzcs()) {
        return DowngradeableSafeParcel.getExtras(param1Intent, param1Context, param1Integer);
      } 
    }
    
    public static <T extends Parcelable> T getParcelable(Intent param1Intent, String param1String, Context param1Context, Integer param1Integer) {
      synchronized (DowngradeableSafeParcel.zzcs()) {
        param1Intent = DowngradeableSafeParcel.getParcelable(param1Intent, param1String, param1Context, param1Integer);
        return (T)param1Intent;
      } 
    }
    
    public static <T extends Parcelable> T getParcelable(Bundle param1Bundle, String param1String, Context param1Context, Integer param1Integer) {
      synchronized (DowngradeableSafeParcel.zzcs()) {
        param1Bundle = DowngradeableSafeParcel.getParcelable(param1Bundle, param1String, param1Context, param1Integer);
        return (T)param1Bundle;
      } 
    }
    
    public static boolean putParcelable(Bundle param1Bundle, String param1String, DowngradeableSafeParcel param1DowngradeableSafeParcel, Context param1Context, Integer param1Integer) {
      return DowngradeableSafeParcel.putParcelable(param1Bundle, param1String, param1DowngradeableSafeParcel, param1Context, param1Integer);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\DowngradeableSafeParcel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */