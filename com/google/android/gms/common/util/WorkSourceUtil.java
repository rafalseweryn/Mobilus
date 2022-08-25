package com.google.android.gms.common.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.os.WorkSource;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorkSourceUtil {
  public static final String TAG = "WorkSourceUtil";
  
  private static final int zzaam = Process.myUid();
  
  private static final Method zzaan = zzdf();
  
  private static final Method zzaao = zzdg();
  
  private static final Method zzaap = zzdh();
  
  private static final Method zzaaq = zzdi();
  
  private static final Method zzaar = zzdj();
  
  private static final Method zzaas = zzdk();
  
  private static final Method zzaat = zzdl();
  
  public static void add(WorkSource paramWorkSource, int paramInt, @Nullable String paramString) {
    if (zzaao != null) {
      String str = paramString;
      if (paramString == null)
        str = ""; 
      try {
        zzaao.invoke(paramWorkSource, new Object[] { Integer.valueOf(paramInt), str });
        return;
      } catch (Exception exception) {
        Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", exception);
        return;
      } 
    } 
    if (zzaan != null)
      try {
        zzaan.invoke(exception, new Object[] { Integer.valueOf(paramInt) });
        return;
      } catch (Exception exception1) {
        Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", exception1);
      }  
  }
  
  @Nullable
  public static WorkSource fromPackage(Context paramContext, @Nullable String paramString) {
    if (paramContext != null && paramContext.getPackageManager() != null) {
      if (paramString == null)
        return null; 
      try {
        String str;
        ApplicationInfo applicationInfo = Wrappers.packageManager(paramContext).getApplicationInfo(paramString, 0);
        if (applicationInfo == null) {
          str = String.valueOf(paramString);
          if (str.length() != 0) {
            str = "Could not get applicationInfo from package: ".concat(str);
          } else {
            str = new String("Could not get applicationInfo from package: ");
          } 
          Log.e("WorkSourceUtil", str);
          return null;
        } 
        return fromUidAndPackage(((ApplicationInfo)str).uid, paramString);
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        String str = String.valueOf(paramString);
        if (str.length() != 0) {
          str = "Could not find package: ".concat(str);
        } else {
          str = new String("Could not find package: ");
        } 
        Log.e("WorkSourceUtil", str);
      } 
    } 
    return null;
  }
  
  public static WorkSource fromPackageAndModuleExperimentalPi(Context paramContext, String paramString1, String paramString2) {
    if (paramContext == null || paramContext.getPackageManager() == null || paramString2 == null || paramString1 == null) {
      Log.w("WorkSourceUtil", "Unexpected null arguments");
      return null;
    } 
    int i = zzc(paramContext, paramString1);
    if (i < 0)
      return null; 
    WorkSource workSource = new WorkSource();
    if (zzaas == null || zzaat == null) {
      add(workSource, i, paramString1);
      return workSource;
    } 
    try {
      Object object = zzaas.invoke(workSource, new Object[0]);
      if (i != zzaam)
        zzaat.invoke(object, new Object[] { Integer.valueOf(i), paramString1 }); 
      zzaat.invoke(object, new Object[] { Integer.valueOf(zzaam), paramString2 });
      return workSource;
    } catch (Exception exception) {
      Log.w("WorkSourceUtil", "Unable to assign chained blame through WorkSource", exception);
      return workSource;
    } 
  }
  
  public static WorkSource fromUidAndPackage(int paramInt, String paramString) {
    WorkSource workSource = new WorkSource();
    add(workSource, paramInt, paramString);
    return workSource;
  }
  
  public static int get(WorkSource paramWorkSource, int paramInt) {
    if (zzaaq != null)
      try {
        return ((Integer)zzaaq.invoke(paramWorkSource, new Object[] { Integer.valueOf(paramInt) })).intValue();
      } catch (Exception exception) {
        Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", exception);
      }  
    return 0;
  }
  
  @Nullable
  public static String getName(WorkSource paramWorkSource, int paramInt) {
    if (zzaar != null)
      try {
        return (String)zzaar.invoke(paramWorkSource, new Object[] { Integer.valueOf(paramInt) });
      } catch (Exception exception) {
        Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", exception);
      }  
    return null;
  }
  
  public static List<String> getNames(@Nullable WorkSource paramWorkSource) {
    int i;
    byte b = 0;
    if (paramWorkSource == null) {
      i = 0;
    } else {
      i = size(paramWorkSource);
    } 
    if (i == 0)
      return Collections.emptyList(); 
    ArrayList<String> arrayList = new ArrayList();
    while (b < i) {
      String str = getName(paramWorkSource, b);
      if (!Strings.isEmptyOrWhitespace(str))
        arrayList.add(str); 
      b++;
    } 
    return arrayList;
  }
  
  public static boolean hasWorkSourcePermission(Context paramContext) {
    return (paramContext == null) ? false : ((paramContext.getPackageManager() == null) ? false : ((Wrappers.packageManager(paramContext).checkPermission("android.permission.UPDATE_DEVICE_STATS", paramContext.getPackageName()) == 0)));
  }
  
  public static int size(WorkSource paramWorkSource) {
    if (zzaap != null)
      try {
        return ((Integer)zzaap.invoke(paramWorkSource, new Object[0])).intValue();
      } catch (Exception exception) {
        Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", exception);
      }  
    return 0;
  }
  
  private static int zzc(Context paramContext, String paramString) {
    try {
      String str;
      ApplicationInfo applicationInfo = Wrappers.packageManager(paramContext).getApplicationInfo(paramString, 0);
      if (applicationInfo == null) {
        str = String.valueOf(paramString);
        if (str.length() != 0) {
          str = "Could not get applicationInfo from package: ".concat(str);
        } else {
          str = new String("Could not get applicationInfo from package: ");
        } 
        Log.e("WorkSourceUtil", str);
        return -1;
      } 
      return ((ApplicationInfo)str).uid;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      String str = String.valueOf(paramString);
      if (str.length() != 0) {
        str = "Could not find package: ".concat(str);
      } else {
        str = new String("Could not find package: ");
      } 
      Log.e("WorkSourceUtil", str);
      return -1;
    } 
  }
  
  private static Method zzdf() {
    try {
      return WorkSource.class.getMethod("add", new Class[] { int.class });
    } catch (Exception exception) {
      return null;
    } 
  }
  
  private static Method zzdg() {
    if (PlatformVersion.isAtLeastJellyBeanMR2())
      try {
        return WorkSource.class.getMethod("add", new Class[] { int.class, String.class });
      } catch (Exception exception) {} 
    return null;
  }
  
  private static Method zzdh() {
    try {
      return WorkSource.class.getMethod("size", new Class[0]);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  private static Method zzdi() {
    try {
      return WorkSource.class.getMethod("get", new Class[] { int.class });
    } catch (Exception exception) {
      return null;
    } 
  }
  
  private static Method zzdj() {
    if (PlatformVersion.isAtLeastJellyBeanMR2())
      try {
        return WorkSource.class.getMethod("getName", new Class[] { int.class });
      } catch (Exception exception) {} 
    return null;
  }
  
  private static final Method zzdk() {
    if (PlatformVersion.isAtLeastP())
      try {
        return WorkSource.class.getMethod("createWorkChain", new Class[0]);
      } catch (Exception exception) {
        Log.w("WorkSourceUtil", "Missing WorkChain API createWorkChain", exception);
      }  
    return null;
  }
  
  @SuppressLint({"PrivateApi"})
  private static final Method zzdl() {
    if (PlatformVersion.isAtLeastP())
      try {
        return Class.forName("android.os.WorkSource$WorkChain").getMethod("addNode", new Class[] { int.class, String.class });
      } catch (Exception exception) {
        Log.w("WorkSourceUtil", "Missing WorkChain class", exception);
      }  
    return null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\WorkSourceUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */