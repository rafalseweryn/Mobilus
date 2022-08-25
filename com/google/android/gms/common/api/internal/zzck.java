package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public final class zzck {
  public static final Status zzmm = new Status(8, "The connection to Google Play services was lost");
  
  private static final BasePendingResult<?>[] zzmn = (BasePendingResult<?>[])new BasePendingResult[0];
  
  private final Map<Api.AnyClientKey<?>, Api.Client> zzil;
  
  @VisibleForTesting
  final Set<BasePendingResult<?>> zzmo = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap<>()));
  
  private final zzcn zzmp = new zzcl(this);
  
  public zzck(Map<Api.AnyClientKey<?>, Api.Client> paramMap) {
    this.zzil = paramMap;
  }
  
  public final void release() {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzmo : Ljava/util/Set;
    //   4: getstatic com/google/android/gms/common/api/internal/zzck.zzmn : [Lcom/google/android/gms/common/api/internal/BasePendingResult;
    //   7: invokeinterface toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
    //   12: checkcast [Lcom/google/android/gms/common/api/internal/BasePendingResult;
    //   15: astore_1
    //   16: aload_1
    //   17: arraylength
    //   18: istore_2
    //   19: iconst_0
    //   20: istore_3
    //   21: iload_3
    //   22: iload_2
    //   23: if_icmpge -> 214
    //   26: aload_1
    //   27: iload_3
    //   28: aaload
    //   29: astore #4
    //   31: aload #4
    //   33: aconst_null
    //   34: invokevirtual zza : (Lcom/google/android/gms/common/api/internal/zzcn;)V
    //   37: aload #4
    //   39: invokevirtual zzo : ()Ljava/lang/Integer;
    //   42: ifnonnull -> 68
    //   45: aload #4
    //   47: invokevirtual zzw : ()Z
    //   50: ifeq -> 208
    //   53: aload_0
    //   54: getfield zzmo : Ljava/util/Set;
    //   57: aload #4
    //   59: invokeinterface remove : (Ljava/lang/Object;)Z
    //   64: pop
    //   65: goto -> 208
    //   68: aload #4
    //   70: aconst_null
    //   71: invokevirtual setResultCallback : (Lcom/google/android/gms/common/api/ResultCallback;)V
    //   74: aload_0
    //   75: getfield zzil : Ljava/util/Map;
    //   78: aload #4
    //   80: checkcast com/google/android/gms/common/api/internal/BaseImplementation$ApiMethodImpl
    //   83: invokevirtual getClientKey : ()Lcom/google/android/gms/common/api/Api$AnyClientKey;
    //   86: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   91: checkcast com/google/android/gms/common/api/Api$Client
    //   94: invokeinterface getServiceBrokerBinder : ()Landroid/os/IBinder;
    //   99: astore #5
    //   101: aload #4
    //   103: invokevirtual isReady : ()Z
    //   106: ifeq -> 130
    //   109: aload #4
    //   111: new com/google/android/gms/common/api/internal/zzcm
    //   114: dup
    //   115: aload #4
    //   117: aconst_null
    //   118: aload #5
    //   120: aconst_null
    //   121: invokespecial <init> : (Lcom/google/android/gms/common/api/internal/BasePendingResult;Lcom/google/android/gms/common/api/zzc;Landroid/os/IBinder;Lcom/google/android/gms/common/api/internal/zzcl;)V
    //   124: invokevirtual zza : (Lcom/google/android/gms/common/api/internal/zzcn;)V
    //   127: goto -> 53
    //   130: aload #5
    //   132: ifnull -> 180
    //   135: aload #5
    //   137: invokeinterface isBinderAlive : ()Z
    //   142: ifeq -> 180
    //   145: new com/google/android/gms/common/api/internal/zzcm
    //   148: dup
    //   149: aload #4
    //   151: aconst_null
    //   152: aload #5
    //   154: aconst_null
    //   155: invokespecial <init> : (Lcom/google/android/gms/common/api/internal/BasePendingResult;Lcom/google/android/gms/common/api/zzc;Landroid/os/IBinder;Lcom/google/android/gms/common/api/internal/zzcl;)V
    //   158: astore #6
    //   160: aload #4
    //   162: aload #6
    //   164: invokevirtual zza : (Lcom/google/android/gms/common/api/internal/zzcn;)V
    //   167: aload #5
    //   169: aload #6
    //   171: iconst_0
    //   172: invokeinterface linkToDeath : (Landroid/os/IBinder$DeathRecipient;I)V
    //   177: goto -> 53
    //   180: aload #4
    //   182: aconst_null
    //   183: invokevirtual zza : (Lcom/google/android/gms/common/api/internal/zzcn;)V
    //   186: aload #4
    //   188: invokevirtual cancel : ()V
    //   191: aload #4
    //   193: invokevirtual zzo : ()Ljava/lang/Integer;
    //   196: invokevirtual intValue : ()I
    //   199: pop
    //   200: new java/lang/NullPointerException
    //   203: dup
    //   204: invokespecial <init> : ()V
    //   207: athrow
    //   208: iinc #3, 1
    //   211: goto -> 21
    //   214: return
    //   215: astore #6
    //   217: goto -> 186
    // Exception table:
    //   from	to	target	type
    //   167	177	215	android/os/RemoteException
  }
  
  final void zzb(BasePendingResult<? extends Result> paramBasePendingResult) {
    this.zzmo.add(paramBasePendingResult);
    paramBasePendingResult.zza(this.zzmp);
  }
  
  public final void zzce() {
    BasePendingResult[] arrayOfBasePendingResult = this.zzmo.<BasePendingResult>toArray((BasePendingResult[])zzmn);
    int i = arrayOfBasePendingResult.length;
    for (byte b = 0; b < i; b++)
      arrayOfBasePendingResult[b].zzb(zzmm); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */