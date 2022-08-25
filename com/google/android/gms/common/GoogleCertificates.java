package com.google.android.gms.common;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.ICertData;
import com.google.android.gms.common.internal.IGoogleCertificatesApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.CheckReturnValue;
import javax.annotation.concurrent.GuardedBy;

@CheckReturnValue
final class GoogleCertificates {
  private static volatile IGoogleCertificatesApi zzax;
  
  private static final Object zzay = new Object();
  
  private static Context zzaz;
  
  @GuardedBy("GoogleCertificates.class")
  private static Set<ICertData> zzba;
  
  @GuardedBy("GoogleCertificates.class")
  private static Set<ICertData> zzbb;
  
  static void init(Context paramContext) {
    // Byte code:
    //   0: ldc com/google/android/gms/common/GoogleCertificates
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/common/GoogleCertificates.zzaz : Landroid/content/Context;
    //   6: ifnonnull -> 24
    //   9: aload_0
    //   10: ifnull -> 32
    //   13: aload_0
    //   14: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   17: putstatic com/google/android/gms/common/GoogleCertificates.zzaz : Landroid/content/Context;
    //   20: ldc com/google/android/gms/common/GoogleCertificates
    //   22: monitorexit
    //   23: return
    //   24: ldc 'GoogleCertificates'
    //   26: ldc 'GoogleCertificates has been initialized already'
    //   28: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   31: pop
    //   32: ldc com/google/android/gms/common/GoogleCertificates
    //   34: monitorexit
    //   35: return
    //   36: astore_0
    //   37: ldc com/google/android/gms/common/GoogleCertificates
    //   39: monitorexit
    //   40: aload_0
    //   41: athrow
    // Exception table:
    //   from	to	target	type
    //   3	9	36	finally
    //   13	20	36	finally
    //   24	32	36	finally
  }
  
  static zzg zza(String paramString, CertData paramCertData, boolean paramBoolean) {
    try {
      zzc();
      Preconditions.checkNotNull(zzaz);
      GoogleCertificatesQuery googleCertificatesQuery = new GoogleCertificatesQuery(paramString, paramCertData, paramBoolean);
      try {
        boolean bool = zzax.isGoogleOrPlatformSigned(googleCertificatesQuery, ObjectWrapper.wrap(zzaz.getPackageManager()));
        if (bool)
          return zzg.zzg(); 
        bool = true;
        if (paramBoolean || !(zza(paramString, paramCertData, true)).zzbl)
          bool = false; 
        return zzg.zza(paramString, paramCertData, paramBoolean, bool);
      } catch (RemoteException remoteException) {
        Log.e("GoogleCertificates", "Failed to get Google certificates from remote", (Throwable)remoteException);
        paramString = "module call";
      } 
    } catch (com.google.android.gms.dynamite.DynamiteModule.LoadingException loadingException) {
      paramString = "module init";
    } 
    return zzg.zza(paramString, (Throwable)loadingException);
  }
  
  private static Set<ICertData> zza(IBinder[] paramArrayOfIBinder) throws RemoteException {
    int i = paramArrayOfIBinder.length;
    HashSet<ICertData> hashSet = new HashSet(i);
    for (byte b = 0; b < i; b++) {
      ICertData iCertData = ICertData.Stub.asInterface(paramArrayOfIBinder[b]);
      if (iCertData != null)
        hashSet.add(iCertData); 
    } 
    return hashSet;
  }
  
  private static void zzc() throws DynamiteModule.LoadingException {
    if (zzax != null)
      return; 
    Preconditions.checkNotNull(zzaz);
    synchronized (zzay) {
      if (zzax == null)
        zzax = IGoogleCertificatesApi.Stub.asInterface(DynamiteModule.load(zzaz, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.googlecertificates").instantiate("com.google.android.gms.common.GoogleCertificatesImpl")); 
      return;
    } 
  }
  
  static Set<ICertData> zzd() {
    // Byte code:
    //   0: ldc com/google/android/gms/common/GoogleCertificates
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/common/GoogleCertificates.zzba : Ljava/util/Set;
    //   6: ifnull -> 18
    //   9: getstatic com/google/android/gms/common/GoogleCertificates.zzba : Ljava/util/Set;
    //   12: astore_0
    //   13: ldc com/google/android/gms/common/GoogleCertificates
    //   15: monitorexit
    //   16: aload_0
    //   17: areturn
    //   18: invokestatic zzc : ()V
    //   21: getstatic com/google/android/gms/common/GoogleCertificates.zzax : Lcom/google/android/gms/common/internal/IGoogleCertificatesApi;
    //   24: invokeinterface getGoogleCertificates : ()Lcom/google/android/gms/dynamic/IObjectWrapper;
    //   29: astore_0
    //   30: aload_0
    //   31: ifnonnull -> 51
    //   34: ldc 'GoogleCertificates'
    //   36: ldc 'Failed to get Google certificates from remote'
    //   38: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   41: pop
    //   42: invokestatic emptySet : ()Ljava/util/Set;
    //   45: astore_0
    //   46: ldc com/google/android/gms/common/GoogleCertificates
    //   48: monitorexit
    //   49: aload_0
    //   50: areturn
    //   51: aload_0
    //   52: invokestatic unwrap : (Lcom/google/android/gms/dynamic/IObjectWrapper;)Ljava/lang/Object;
    //   55: checkcast [Landroid/os/IBinder;
    //   58: invokestatic zza : ([Landroid/os/IBinder;)Ljava/util/Set;
    //   61: putstatic com/google/android/gms/common/GoogleCertificates.zzba : Ljava/util/Set;
    //   64: getstatic com/google/android/gms/common/GoogleCertificates.zzba : Ljava/util/Set;
    //   67: astore_0
    //   68: ldc com/google/android/gms/common/GoogleCertificates
    //   70: monitorexit
    //   71: aload_0
    //   72: areturn
    //   73: astore_0
    //   74: ldc 'GoogleCertificates'
    //   76: ldc 'Failed to get Google certificates from remote'
    //   78: aload_0
    //   79: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   82: pop
    //   83: invokestatic emptySet : ()Ljava/util/Set;
    //   86: astore_0
    //   87: ldc com/google/android/gms/common/GoogleCertificates
    //   89: monitorexit
    //   90: aload_0
    //   91: areturn
    //   92: astore_0
    //   93: ldc 'GoogleCertificates'
    //   95: ldc 'Failed to load com.google.android.gms.googlecertificates'
    //   97: aload_0
    //   98: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   101: pop
    //   102: invokestatic emptySet : ()Ljava/util/Set;
    //   105: astore_0
    //   106: ldc com/google/android/gms/common/GoogleCertificates
    //   108: monitorexit
    //   109: aload_0
    //   110: areturn
    //   111: astore_0
    //   112: ldc com/google/android/gms/common/GoogleCertificates
    //   114: monitorexit
    //   115: aload_0
    //   116: athrow
    // Exception table:
    //   from	to	target	type
    //   3	13	111	finally
    //   18	21	92	com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   18	21	111	finally
    //   21	30	73	android/os/RemoteException
    //   21	30	111	finally
    //   34	46	73	android/os/RemoteException
    //   34	46	111	finally
    //   51	64	73	android/os/RemoteException
    //   51	64	111	finally
    //   64	68	111	finally
    //   74	87	111	finally
    //   93	106	111	finally
  }
  
  static Set<ICertData> zze() {
    // Byte code:
    //   0: ldc com/google/android/gms/common/GoogleCertificates
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/common/GoogleCertificates.zzbb : Ljava/util/Set;
    //   6: ifnull -> 18
    //   9: getstatic com/google/android/gms/common/GoogleCertificates.zzbb : Ljava/util/Set;
    //   12: astore_0
    //   13: ldc com/google/android/gms/common/GoogleCertificates
    //   15: monitorexit
    //   16: aload_0
    //   17: areturn
    //   18: invokestatic zzc : ()V
    //   21: getstatic com/google/android/gms/common/GoogleCertificates.zzax : Lcom/google/android/gms/common/internal/IGoogleCertificatesApi;
    //   24: invokeinterface getGoogleReleaseCertificates : ()Lcom/google/android/gms/dynamic/IObjectWrapper;
    //   29: astore_0
    //   30: aload_0
    //   31: ifnonnull -> 51
    //   34: ldc 'GoogleCertificates'
    //   36: ldc 'Failed to get Google certificates from remote'
    //   38: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   41: pop
    //   42: invokestatic emptySet : ()Ljava/util/Set;
    //   45: astore_0
    //   46: ldc com/google/android/gms/common/GoogleCertificates
    //   48: monitorexit
    //   49: aload_0
    //   50: areturn
    //   51: aload_0
    //   52: invokestatic unwrap : (Lcom/google/android/gms/dynamic/IObjectWrapper;)Ljava/lang/Object;
    //   55: checkcast [Landroid/os/IBinder;
    //   58: invokestatic zza : ([Landroid/os/IBinder;)Ljava/util/Set;
    //   61: putstatic com/google/android/gms/common/GoogleCertificates.zzbb : Ljava/util/Set;
    //   64: getstatic com/google/android/gms/common/GoogleCertificates.zzbb : Ljava/util/Set;
    //   67: astore_0
    //   68: ldc com/google/android/gms/common/GoogleCertificates
    //   70: monitorexit
    //   71: aload_0
    //   72: areturn
    //   73: astore_0
    //   74: ldc 'GoogleCertificates'
    //   76: ldc 'Failed to get Google certificates from remote'
    //   78: aload_0
    //   79: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   82: pop
    //   83: invokestatic emptySet : ()Ljava/util/Set;
    //   86: astore_0
    //   87: ldc com/google/android/gms/common/GoogleCertificates
    //   89: monitorexit
    //   90: aload_0
    //   91: areturn
    //   92: astore_0
    //   93: ldc 'GoogleCertificates'
    //   95: ldc 'Failed to load com.google.android.gms.googlecertificates'
    //   97: aload_0
    //   98: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   101: pop
    //   102: invokestatic emptySet : ()Ljava/util/Set;
    //   105: astore_0
    //   106: ldc com/google/android/gms/common/GoogleCertificates
    //   108: monitorexit
    //   109: aload_0
    //   110: areturn
    //   111: astore_0
    //   112: ldc com/google/android/gms/common/GoogleCertificates
    //   114: monitorexit
    //   115: aload_0
    //   116: athrow
    // Exception table:
    //   from	to	target	type
    //   3	13	111	finally
    //   18	21	92	com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   18	21	111	finally
    //   21	30	73	android/os/RemoteException
    //   21	30	111	finally
    //   34	46	73	android/os/RemoteException
    //   34	46	111	finally
    //   51	64	73	android/os/RemoteException
    //   51	64	111	finally
    //   64	68	111	finally
    //   74	87	111	finally
    //   93	106	111	finally
  }
  
  static abstract class CertData extends ICertData.Stub {
    private int zzbc;
    
    protected CertData(byte[] param1ArrayOfbyte) {
      boolean bool;
      if (param1ArrayOfbyte.length == 25) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkArgument(bool);
      this.zzbc = Arrays.hashCode(param1ArrayOfbyte);
    }
    
    protected static byte[] zzd(String param1String) {
      try {
        return param1String.getBytes("ISO-8859-1");
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        throw new AssertionError(unsupportedEncodingException);
      } 
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object != null) {
        if (!(param1Object instanceof ICertData))
          return false; 
        try {
          param1Object = param1Object;
          if (param1Object.getHashCode() != hashCode())
            return false; 
          param1Object = param1Object.getBytesWrapped();
          if (param1Object == null)
            return false; 
          param1Object = ObjectWrapper.unwrap((IObjectWrapper)param1Object);
          return Arrays.equals(getBytes(), (byte[])param1Object);
        } catch (RemoteException remoteException) {
          Log.e("GoogleCertificates", "Failed to get Google certificates from remote", (Throwable)remoteException);
        } 
      } 
      return false;
    }
    
    abstract byte[] getBytes();
    
    public IObjectWrapper getBytesWrapped() {
      return ObjectWrapper.wrap(getBytes());
    }
    
    public int getHashCode() {
      return hashCode();
    }
    
    public int hashCode() {
      return this.zzbc;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\GoogleCertificates.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */