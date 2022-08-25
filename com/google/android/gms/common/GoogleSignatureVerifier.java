package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.ICertData;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.CheckReturnValue;

@CheckReturnValue
public class GoogleSignatureVerifier {
  private static GoogleSignatureVerifier zzbv;
  
  private final Context mContext;
  
  private GoogleSignatureVerifier(Context paramContext) {
    this.mContext = paramContext.getApplicationContext();
  }
  
  public static GoogleSignatureVerifier getInstance(Context paramContext) {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic checkNotNull : (Ljava/lang/Object;)Ljava/lang/Object;
    //   4: pop
    //   5: ldc com/google/android/gms/common/GoogleSignatureVerifier
    //   7: monitorenter
    //   8: getstatic com/google/android/gms/common/GoogleSignatureVerifier.zzbv : Lcom/google/android/gms/common/GoogleSignatureVerifier;
    //   11: ifnonnull -> 31
    //   14: aload_0
    //   15: invokestatic init : (Landroid/content/Context;)V
    //   18: new com/google/android/gms/common/GoogleSignatureVerifier
    //   21: astore_1
    //   22: aload_1
    //   23: aload_0
    //   24: invokespecial <init> : (Landroid/content/Context;)V
    //   27: aload_1
    //   28: putstatic com/google/android/gms/common/GoogleSignatureVerifier.zzbv : Lcom/google/android/gms/common/GoogleSignatureVerifier;
    //   31: ldc com/google/android/gms/common/GoogleSignatureVerifier
    //   33: monitorexit
    //   34: getstatic com/google/android/gms/common/GoogleSignatureVerifier.zzbv : Lcom/google/android/gms/common/GoogleSignatureVerifier;
    //   37: areturn
    //   38: astore_0
    //   39: ldc com/google/android/gms/common/GoogleSignatureVerifier
    //   41: monitorexit
    //   42: aload_0
    //   43: athrow
    // Exception table:
    //   from	to	target	type
    //   8	31	38	finally
    //   31	34	38	finally
    //   39	42	38	finally
  }
  
  @VisibleForTesting
  public static void resetForTests() {
    // Byte code:
    //   0: ldc com/google/android/gms/common/GoogleSignatureVerifier
    //   2: monitorenter
    //   3: aconst_null
    //   4: putstatic com/google/android/gms/common/GoogleSignatureVerifier.zzbv : Lcom/google/android/gms/common/GoogleSignatureVerifier;
    //   7: ldc com/google/android/gms/common/GoogleSignatureVerifier
    //   9: monitorexit
    //   10: return
    //   11: astore_0
    //   12: ldc com/google/android/gms/common/GoogleSignatureVerifier
    //   14: monitorexit
    //   15: aload_0
    //   16: athrow
    // Exception table:
    //   from	to	target	type
    //   3	7	11	finally
  }
  
  private static GoogleCertificates.CertData zza(PackageInfo paramPackageInfo, GoogleCertificates.CertData... paramVarArgs) {
    if (paramPackageInfo.signatures == null)
      return null; 
    if (paramPackageInfo.signatures.length != 1) {
      Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
      return null;
    } 
    Signature[] arrayOfSignature = paramPackageInfo.signatures;
    byte b = 0;
    zzb zzb = new zzb(arrayOfSignature[0].toByteArray());
    while (b < paramVarArgs.length) {
      if (paramVarArgs[b].equals(zzb))
        return paramVarArgs[b]; 
      b++;
    } 
    return null;
  }
  
  private final zzg zza(PackageInfo paramPackageInfo) {
    String str1;
    boolean bool = GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext);
    if (paramPackageInfo == null) {
      str1 = "null pkg";
      return zzg.zze(str1);
    } 
    if (((PackageInfo)str1).signatures.length != 1) {
      str1 = "single cert required";
      return zzg.zze(str1);
    } 
    zzb zzb = new zzb(((PackageInfo)str1).signatures[0].toByteArray());
    String str2 = ((PackageInfo)str1).packageName;
    zzg zzg = GoogleCertificates.zza(str2, zzb, bool);
    if (zzg.zzbl && ((PackageInfo)str1).applicationInfo != null && (((PackageInfo)str1).applicationInfo.flags & 0x2) != 0 && (!bool || (GoogleCertificates.zza(str2, zzb, false)).zzbl)) {
      str1 = "debuggable release cert app rejected";
      return zzg.zze(str1);
    } 
    return zzg;
  }
  
  private final zzg zzb(int paramInt) {
    String[] arrayOfString = Wrappers.packageManager(this.mContext).getPackagesForUid(paramInt);
    if (arrayOfString == null || arrayOfString.length == 0)
      return zzg.zze("no pkgs"); 
    zzg zzg = null;
    int i = arrayOfString.length;
    for (paramInt = 0; paramInt < i; paramInt++) {
      zzg = zzf(arrayOfString[paramInt]);
      if (zzg.zzbl)
        return zzg; 
    } 
    return zzg;
  }
  
  private final zzg zzf(String paramString) {
    try {
      PackageInfo packageInfo = Wrappers.packageManager(this.mContext).getPackageInfo(paramString, 64);
      return zza(packageInfo);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      paramString = String.valueOf(paramString);
      if (paramString.length() != 0) {
        paramString = "no pkg ".concat(paramString);
      } else {
        paramString = new String("no pkg ");
      } 
      return zzg.zze(paramString);
    } 
  }
  
  @Deprecated
  public Set<byte[]> getAllGoogleSignatures(boolean paramBoolean) {
    Set<ICertData> set;
    if (paramBoolean) {
      set = GoogleCertificates.zzd();
    } else {
      set = GoogleCertificates.zze();
    } 
    HashSet<byte[]> hashSet = new HashSet(set.size());
    try {
      Iterator<ICertData> iterator = set.iterator();
      while (iterator.hasNext())
        hashSet.add((byte[])ObjectWrapper.unwrap(((ICertData)iterator.next()).getBytesWrapped())); 
    } catch (RemoteException remoteException) {
      Log.e("GoogleSignatureVerifier", "Failed to get Google certificates from remote", (Throwable)remoteException);
    } 
    return (Set<byte[]>)hashSet;
  }
  
  public boolean isChimeraSigned(PackageManager paramPackageManager, PackageInfo paramPackageInfo) {
    String str = paramPackageInfo.packageName;
    paramPackageInfo.packageName = "com.google.android.gms.chimera";
    boolean bool = isPackageGoogleSigned(paramPackageInfo);
    paramPackageInfo.packageName = str;
    return bool;
  }
  
  public boolean isGooglePublicSignedPackage(PackageInfo paramPackageInfo) {
    if (paramPackageInfo == null)
      return false; 
    if (isGooglePublicSignedPackage(paramPackageInfo, false))
      return true; 
    if (isGooglePublicSignedPackage(paramPackageInfo, true)) {
      if (GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext))
        return true; 
      Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
    } 
    return false;
  }
  
  public boolean isGooglePublicSignedPackage(PackageInfo paramPackageInfo, boolean paramBoolean) {
    if (paramPackageInfo != null && paramPackageInfo.signatures != null) {
      GoogleCertificates.CertData[] arrayOfCertData;
      if (paramBoolean) {
        arrayOfCertData = zzd.zzbg;
      } else {
        arrayOfCertData = new GoogleCertificates.CertData[1];
        arrayOfCertData[0] = zzd.zzbg[0];
      } 
      GoogleCertificates.CertData certData = zza(paramPackageInfo, arrayOfCertData);
      if (certData != null)
        return true; 
    } 
    return false;
  }
  
  @Deprecated
  public boolean isGooglePublicSignedPackage(PackageManager paramPackageManager, PackageInfo paramPackageInfo) {
    return isGooglePublicSignedPackage(paramPackageInfo);
  }
  
  public boolean isPackageGoogleSigned(PackageInfo paramPackageInfo) {
    zzg zzg = zza(paramPackageInfo);
    zzg.zzi();
    return zzg.zzbl;
  }
  
  @Deprecated
  public boolean isPackageGoogleSigned(PackageManager paramPackageManager, PackageInfo paramPackageInfo) {
    return isPackageGoogleSigned(paramPackageInfo);
  }
  
  @Deprecated
  public boolean isPackageGoogleSigned(PackageManager paramPackageManager, String paramString) {
    return isPackageGoogleSigned(paramString);
  }
  
  public boolean isPackageGoogleSigned(String paramString) {
    zzg zzg = zzf(paramString);
    zzg.zzi();
    return zzg.zzbl;
  }
  
  public boolean isUidGoogleSigned(int paramInt) {
    zzg zzg = zzb(paramInt);
    zzg.zzi();
    return zzg.zzbl;
  }
  
  @Deprecated
  public boolean isUidGoogleSigned(PackageManager paramPackageManager, int paramInt) {
    return isUidGoogleSigned(paramInt);
  }
  
  @Deprecated
  public void verifyPackageIsGoogleSigned(PackageManager paramPackageManager, String paramString) throws SecurityException {
    verifyPackageIsGoogleSigned(paramString);
  }
  
  public void verifyPackageIsGoogleSigned(String paramString) throws SecurityException {
    zzf(paramString).zzh();
  }
  
  public void verifyUidIsGoogleSigned(int paramInt) throws SecurityException {
    zzb(paramInt).zzh();
  }
  
  @Deprecated
  public void verifyUidIsGoogleSigned(PackageManager paramPackageManager, int paramInt) throws SecurityException {
    verifyUidIsGoogleSigned(paramInt);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\GoogleSignatureVerifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */