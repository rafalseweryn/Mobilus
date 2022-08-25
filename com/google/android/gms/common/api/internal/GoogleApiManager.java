package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public class GoogleApiManager implements Handler.Callback {
  private static final Object lock;
  
  public static final Status zzjj = new Status(4, "Sign-out occurred while this API call was in progress.");
  
  private static final Status zzjk = new Status(4, "The user must be signed in to make this API call.");
  
  @GuardedBy("lock")
  private static GoogleApiManager zzjo;
  
  private final Handler handler;
  
  private long zzjl = 5000L;
  
  private long zzjm = 120000L;
  
  private long zzjn = 10000L;
  
  private final Context zzjp;
  
  private final GoogleApiAvailability zzjq;
  
  private final GoogleApiAvailabilityCache zzjr;
  
  private final AtomicInteger zzjs = new AtomicInteger(1);
  
  private final AtomicInteger zzjt = new AtomicInteger(0);
  
  private final Map<zzh<?>, zza<?>> zzju = new ConcurrentHashMap<>(5, 0.75F, 1);
  
  @GuardedBy("lock")
  private zzad zzjv = null;
  
  @GuardedBy("lock")
  private final Set<zzh<?>> zzjw = (Set<zzh<?>>)new ArraySet();
  
  private final Set<zzh<?>> zzjx = (Set<zzh<?>>)new ArraySet();
  
  static {
    lock = new Object();
  }
  
  @KeepForSdk
  private GoogleApiManager(Context paramContext, Looper paramLooper, GoogleApiAvailability paramGoogleApiAvailability) {
    this.zzjp = paramContext;
    this.handler = new Handler(paramLooper, this);
    this.zzjq = paramGoogleApiAvailability;
    this.zzjr = new GoogleApiAvailabilityCache((GoogleApiAvailabilityLight)paramGoogleApiAvailability);
    this.handler.sendMessage(this.handler.obtainMessage(6));
  }
  
  @KeepForSdk
  public static void reportSignOut() {
    synchronized (lock) {
      if (zzjo != null) {
        GoogleApiManager googleApiManager = zzjo;
        googleApiManager.zzjt.incrementAndGet();
        googleApiManager.handler.sendMessageAtFrontOfQueue(googleApiManager.handler.obtainMessage(10));
      } 
      return;
    } 
  }
  
  public static GoogleApiManager zzb(Context paramContext) {
    synchronized (lock) {
      if (zzjo == null) {
        HandlerThread handlerThread = new HandlerThread();
        this("GoogleApiHandler", 9);
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        GoogleApiManager googleApiManager = new GoogleApiManager();
        this(paramContext.getApplicationContext(), looper, GoogleApiAvailability.getInstance());
        zzjo = googleApiManager;
      } 
      return zzjo;
    } 
  }
  
  @WorkerThread
  private final void zzb(GoogleApi<?> paramGoogleApi) {
    zzh<?> zzh = paramGoogleApi.zzm();
    zza<?> zza1 = this.zzju.get(zzh);
    zza<?> zza2 = zza1;
    if (zza1 == null) {
      zza2 = new zza(this, paramGoogleApi);
      this.zzju.put(zzh, zza2);
    } 
    if (zza2.requiresSignIn())
      this.zzjx.add(zzh); 
    zza2.connect();
  }
  
  public static GoogleApiManager zzbf() {
    synchronized (lock) {
      Preconditions.checkNotNull(zzjo, "Must guarantee manager is non-null before using getInstance");
      return zzjo;
    } 
  }
  
  @WorkerThread
  public boolean handleMessage(Message paramMessage) {
    // Byte code:
    //   0: aload_1
    //   1: getfield what : I
    //   4: istore_2
    //   5: ldc2_w 300000
    //   8: lstore_3
    //   9: iload_2
    //   10: tableswitch default -> 88, 1 -> 1082, 2 -> 939, 3 -> 891, 4 -> 772, 5 -> 548, 6 -> 483, 7 -> 470, 8 -> 772, 9 -> 433, 10 -> 372, 11 -> 335, 12 -> 297, 13 -> 772, 14 -> 221, 15 -> 175, 16 -> 129
    //   88: aload_1
    //   89: getfield what : I
    //   92: istore_2
    //   93: new java/lang/StringBuilder
    //   96: dup
    //   97: bipush #31
    //   99: invokespecial <init> : (I)V
    //   102: astore_1
    //   103: aload_1
    //   104: ldc 'Unknown message id: '
    //   106: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: pop
    //   110: aload_1
    //   111: iload_2
    //   112: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   115: pop
    //   116: ldc_w 'GoogleApiManager'
    //   119: aload_1
    //   120: invokevirtual toString : ()Ljava/lang/String;
    //   123: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   126: pop
    //   127: iconst_0
    //   128: ireturn
    //   129: aload_1
    //   130: getfield obj : Ljava/lang/Object;
    //   133: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zzb
    //   136: astore_1
    //   137: aload_0
    //   138: getfield zzju : Ljava/util/Map;
    //   141: aload_1
    //   142: invokestatic zzc : (Lcom/google/android/gms/common/api/internal/GoogleApiManager$zzb;)Lcom/google/android/gms/common/api/internal/zzh;
    //   145: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   150: ifeq -> 1175
    //   153: aload_0
    //   154: getfield zzju : Ljava/util/Map;
    //   157: aload_1
    //   158: invokestatic zzc : (Lcom/google/android/gms/common/api/internal/GoogleApiManager$zzb;)Lcom/google/android/gms/common/api/internal/zzh;
    //   161: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   166: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zza
    //   169: aload_1
    //   170: invokestatic zzb : (Lcom/google/android/gms/common/api/internal/GoogleApiManager$zza;Lcom/google/android/gms/common/api/internal/GoogleApiManager$zzb;)V
    //   173: iconst_1
    //   174: ireturn
    //   175: aload_1
    //   176: getfield obj : Ljava/lang/Object;
    //   179: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zzb
    //   182: astore_1
    //   183: aload_0
    //   184: getfield zzju : Ljava/util/Map;
    //   187: aload_1
    //   188: invokestatic zzc : (Lcom/google/android/gms/common/api/internal/GoogleApiManager$zzb;)Lcom/google/android/gms/common/api/internal/zzh;
    //   191: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   196: ifeq -> 1175
    //   199: aload_0
    //   200: getfield zzju : Ljava/util/Map;
    //   203: aload_1
    //   204: invokestatic zzc : (Lcom/google/android/gms/common/api/internal/GoogleApiManager$zzb;)Lcom/google/android/gms/common/api/internal/zzh;
    //   207: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   212: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zza
    //   215: aload_1
    //   216: invokestatic zza : (Lcom/google/android/gms/common/api/internal/GoogleApiManager$zza;Lcom/google/android/gms/common/api/internal/GoogleApiManager$zzb;)V
    //   219: iconst_1
    //   220: ireturn
    //   221: aload_1
    //   222: getfield obj : Ljava/lang/Object;
    //   225: checkcast com/google/android/gms/common/api/internal/zzae
    //   228: astore #5
    //   230: aload #5
    //   232: invokevirtual zzm : ()Lcom/google/android/gms/common/api/internal/zzh;
    //   235: astore_1
    //   236: aload_0
    //   237: getfield zzju : Ljava/util/Map;
    //   240: aload_1
    //   241: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   246: ifne -> 263
    //   249: aload #5
    //   251: invokevirtual zzao : ()Lcom/google/android/gms/tasks/TaskCompletionSource;
    //   254: iconst_0
    //   255: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   258: invokevirtual setResult : (Ljava/lang/Object;)V
    //   261: iconst_1
    //   262: ireturn
    //   263: aload_0
    //   264: getfield zzju : Ljava/util/Map;
    //   267: aload_1
    //   268: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   273: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zza
    //   276: iconst_0
    //   277: invokestatic zza : (Lcom/google/android/gms/common/api/internal/GoogleApiManager$zza;Z)Z
    //   280: istore #6
    //   282: aload #5
    //   284: invokevirtual zzao : ()Lcom/google/android/gms/tasks/TaskCompletionSource;
    //   287: iload #6
    //   289: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   292: invokevirtual setResult : (Ljava/lang/Object;)V
    //   295: iconst_1
    //   296: ireturn
    //   297: aload_0
    //   298: getfield zzju : Ljava/util/Map;
    //   301: aload_1
    //   302: getfield obj : Ljava/lang/Object;
    //   305: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   310: ifeq -> 1175
    //   313: aload_0
    //   314: getfield zzju : Ljava/util/Map;
    //   317: aload_1
    //   318: getfield obj : Ljava/lang/Object;
    //   321: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   326: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zza
    //   329: invokevirtual zzbs : ()Z
    //   332: pop
    //   333: iconst_1
    //   334: ireturn
    //   335: aload_0
    //   336: getfield zzju : Ljava/util/Map;
    //   339: aload_1
    //   340: getfield obj : Ljava/lang/Object;
    //   343: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   348: ifeq -> 1175
    //   351: aload_0
    //   352: getfield zzju : Ljava/util/Map;
    //   355: aload_1
    //   356: getfield obj : Ljava/lang/Object;
    //   359: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   364: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zza
    //   367: invokevirtual zzay : ()V
    //   370: iconst_1
    //   371: ireturn
    //   372: aload_0
    //   373: getfield zzjx : Ljava/util/Set;
    //   376: invokeinterface iterator : ()Ljava/util/Iterator;
    //   381: astore_1
    //   382: aload_1
    //   383: invokeinterface hasNext : ()Z
    //   388: ifeq -> 422
    //   391: aload_1
    //   392: invokeinterface next : ()Ljava/lang/Object;
    //   397: checkcast com/google/android/gms/common/api/internal/zzh
    //   400: astore #5
    //   402: aload_0
    //   403: getfield zzju : Ljava/util/Map;
    //   406: aload #5
    //   408: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   413: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zza
    //   416: invokevirtual zzbm : ()V
    //   419: goto -> 382
    //   422: aload_0
    //   423: getfield zzjx : Ljava/util/Set;
    //   426: invokeinterface clear : ()V
    //   431: iconst_1
    //   432: ireturn
    //   433: aload_0
    //   434: getfield zzju : Ljava/util/Map;
    //   437: aload_1
    //   438: getfield obj : Ljava/lang/Object;
    //   441: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   446: ifeq -> 1175
    //   449: aload_0
    //   450: getfield zzju : Ljava/util/Map;
    //   453: aload_1
    //   454: getfield obj : Ljava/lang/Object;
    //   457: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   462: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zza
    //   465: invokevirtual resume : ()V
    //   468: iconst_1
    //   469: ireturn
    //   470: aload_0
    //   471: aload_1
    //   472: getfield obj : Ljava/lang/Object;
    //   475: checkcast com/google/android/gms/common/api/GoogleApi
    //   478: invokespecial zzb : (Lcom/google/android/gms/common/api/GoogleApi;)V
    //   481: iconst_1
    //   482: ireturn
    //   483: invokestatic isAtLeastIceCreamSandwich : ()Z
    //   486: ifeq -> 1175
    //   489: aload_0
    //   490: getfield zzjp : Landroid/content/Context;
    //   493: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   496: instanceof android/app/Application
    //   499: ifeq -> 1175
    //   502: aload_0
    //   503: getfield zzjp : Landroid/content/Context;
    //   506: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   509: checkcast android/app/Application
    //   512: invokestatic initialize : (Landroid/app/Application;)V
    //   515: invokestatic getInstance : ()Lcom/google/android/gms/common/api/internal/BackgroundDetector;
    //   518: new com/google/android/gms/common/api/internal/zzbh
    //   521: dup
    //   522: aload_0
    //   523: invokespecial <init> : (Lcom/google/android/gms/common/api/internal/GoogleApiManager;)V
    //   526: invokevirtual addListener : (Lcom/google/android/gms/common/api/internal/BackgroundDetector$BackgroundStateChangeListener;)V
    //   529: invokestatic getInstance : ()Lcom/google/android/gms/common/api/internal/BackgroundDetector;
    //   532: iconst_1
    //   533: invokevirtual readCurrentStateIfPossible : (Z)Z
    //   536: ifne -> 1175
    //   539: aload_0
    //   540: ldc2_w 300000
    //   543: putfield zzjn : J
    //   546: iconst_1
    //   547: ireturn
    //   548: aload_1
    //   549: getfield arg1 : I
    //   552: istore_2
    //   553: aload_1
    //   554: getfield obj : Ljava/lang/Object;
    //   557: checkcast com/google/android/gms/common/ConnectionResult
    //   560: astore #5
    //   562: aload_0
    //   563: getfield zzju : Ljava/util/Map;
    //   566: invokeinterface values : ()Ljava/util/Collection;
    //   571: invokeinterface iterator : ()Ljava/util/Iterator;
    //   576: astore #7
    //   578: aload #7
    //   580: invokeinterface hasNext : ()Z
    //   585: ifeq -> 610
    //   588: aload #7
    //   590: invokeinterface next : ()Ljava/lang/Object;
    //   595: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zza
    //   598: astore_1
    //   599: aload_1
    //   600: invokevirtual getInstanceId : ()I
    //   603: iload_2
    //   604: if_icmpne -> 578
    //   607: goto -> 612
    //   610: aconst_null
    //   611: astore_1
    //   612: aload_1
    //   613: ifnull -> 720
    //   616: aload_0
    //   617: getfield zzjq : Lcom/google/android/gms/common/GoogleApiAvailability;
    //   620: aload #5
    //   622: invokevirtual getErrorCode : ()I
    //   625: invokevirtual getErrorString : (I)Ljava/lang/String;
    //   628: astore #7
    //   630: aload #5
    //   632: invokevirtual getErrorMessage : ()Ljava/lang/String;
    //   635: astore #5
    //   637: new java/lang/StringBuilder
    //   640: dup
    //   641: aload #7
    //   643: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   646: invokevirtual length : ()I
    //   649: bipush #69
    //   651: iadd
    //   652: aload #5
    //   654: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   657: invokevirtual length : ()I
    //   660: iadd
    //   661: invokespecial <init> : (I)V
    //   664: astore #8
    //   666: aload #8
    //   668: ldc_w 'Error resolution was canceled by the user, original error message: '
    //   671: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   674: pop
    //   675: aload #8
    //   677: aload #7
    //   679: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   682: pop
    //   683: aload #8
    //   685: ldc_w ': '
    //   688: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   691: pop
    //   692: aload #8
    //   694: aload #5
    //   696: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   699: pop
    //   700: aload_1
    //   701: new com/google/android/gms/common/api/Status
    //   704: dup
    //   705: bipush #17
    //   707: aload #8
    //   709: invokevirtual toString : ()Ljava/lang/String;
    //   712: invokespecial <init> : (ILjava/lang/String;)V
    //   715: invokevirtual zzc : (Lcom/google/android/gms/common/api/Status;)V
    //   718: iconst_1
    //   719: ireturn
    //   720: new java/lang/StringBuilder
    //   723: dup
    //   724: bipush #76
    //   726: invokespecial <init> : (I)V
    //   729: astore_1
    //   730: aload_1
    //   731: ldc_w 'Could not find API instance '
    //   734: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   737: pop
    //   738: aload_1
    //   739: iload_2
    //   740: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   743: pop
    //   744: aload_1
    //   745: ldc_w ' while trying to fail enqueued calls.'
    //   748: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   751: pop
    //   752: ldc_w 'GoogleApiManager'
    //   755: aload_1
    //   756: invokevirtual toString : ()Ljava/lang/String;
    //   759: new java/lang/Exception
    //   762: dup
    //   763: invokespecial <init> : ()V
    //   766: invokestatic wtf : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   769: pop
    //   770: iconst_1
    //   771: ireturn
    //   772: aload_1
    //   773: getfield obj : Ljava/lang/Object;
    //   776: checkcast com/google/android/gms/common/api/internal/zzbu
    //   779: astore #7
    //   781: aload_0
    //   782: getfield zzju : Ljava/util/Map;
    //   785: aload #7
    //   787: getfield zzlr : Lcom/google/android/gms/common/api/GoogleApi;
    //   790: invokevirtual zzm : ()Lcom/google/android/gms/common/api/internal/zzh;
    //   793: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   798: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zza
    //   801: astore #5
    //   803: aload #5
    //   805: astore_1
    //   806: aload #5
    //   808: ifnonnull -> 841
    //   811: aload_0
    //   812: aload #7
    //   814: getfield zzlr : Lcom/google/android/gms/common/api/GoogleApi;
    //   817: invokespecial zzb : (Lcom/google/android/gms/common/api/GoogleApi;)V
    //   820: aload_0
    //   821: getfield zzju : Ljava/util/Map;
    //   824: aload #7
    //   826: getfield zzlr : Lcom/google/android/gms/common/api/GoogleApi;
    //   829: invokevirtual zzm : ()Lcom/google/android/gms/common/api/internal/zzh;
    //   832: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   837: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zza
    //   840: astore_1
    //   841: aload_1
    //   842: invokevirtual requiresSignIn : ()Z
    //   845: ifeq -> 880
    //   848: aload_0
    //   849: getfield zzjt : Ljava/util/concurrent/atomic/AtomicInteger;
    //   852: invokevirtual get : ()I
    //   855: aload #7
    //   857: getfield zzlq : I
    //   860: if_icmpeq -> 880
    //   863: aload #7
    //   865: getfield zzlp : Lcom/google/android/gms/common/api/internal/zzb;
    //   868: getstatic com/google/android/gms/common/api/internal/GoogleApiManager.zzjj : Lcom/google/android/gms/common/api/Status;
    //   871: invokevirtual zza : (Lcom/google/android/gms/common/api/Status;)V
    //   874: aload_1
    //   875: invokevirtual zzbm : ()V
    //   878: iconst_1
    //   879: ireturn
    //   880: aload_1
    //   881: aload #7
    //   883: getfield zzlp : Lcom/google/android/gms/common/api/internal/zzb;
    //   886: invokevirtual zza : (Lcom/google/android/gms/common/api/internal/zzb;)V
    //   889: iconst_1
    //   890: ireturn
    //   891: aload_0
    //   892: getfield zzju : Ljava/util/Map;
    //   895: invokeinterface values : ()Ljava/util/Collection;
    //   900: invokeinterface iterator : ()Ljava/util/Iterator;
    //   905: astore_1
    //   906: aload_1
    //   907: invokeinterface hasNext : ()Z
    //   912: ifeq -> 1175
    //   915: aload_1
    //   916: invokeinterface next : ()Ljava/lang/Object;
    //   921: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zza
    //   924: astore #5
    //   926: aload #5
    //   928: invokevirtual zzbo : ()V
    //   931: aload #5
    //   933: invokevirtual connect : ()V
    //   936: goto -> 906
    //   939: aload_1
    //   940: getfield obj : Ljava/lang/Object;
    //   943: checkcast com/google/android/gms/common/api/internal/zzj
    //   946: astore #8
    //   948: aload #8
    //   950: invokevirtual zzs : ()Ljava/util/Set;
    //   953: invokeinterface iterator : ()Ljava/util/Iterator;
    //   958: astore #5
    //   960: aload #5
    //   962: invokeinterface hasNext : ()Z
    //   967: ifeq -> 1175
    //   970: aload #5
    //   972: invokeinterface next : ()Ljava/lang/Object;
    //   977: checkcast com/google/android/gms/common/api/internal/zzh
    //   980: astore_1
    //   981: aload_0
    //   982: getfield zzju : Ljava/util/Map;
    //   985: aload_1
    //   986: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   991: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zza
    //   994: astore #7
    //   996: aload #7
    //   998: ifnonnull -> 1019
    //   1001: aload #8
    //   1003: aload_1
    //   1004: new com/google/android/gms/common/ConnectionResult
    //   1007: dup
    //   1008: bipush #13
    //   1010: invokespecial <init> : (I)V
    //   1013: aconst_null
    //   1014: invokevirtual zza : (Lcom/google/android/gms/common/api/internal/zzh;Lcom/google/android/gms/common/ConnectionResult;Ljava/lang/String;)V
    //   1017: iconst_1
    //   1018: ireturn
    //   1019: aload #7
    //   1021: invokevirtual isConnected : ()Z
    //   1024: ifeq -> 1049
    //   1027: aload #8
    //   1029: aload_1
    //   1030: getstatic com/google/android/gms/common/ConnectionResult.RESULT_SUCCESS : Lcom/google/android/gms/common/ConnectionResult;
    //   1033: aload #7
    //   1035: invokevirtual zzae : ()Lcom/google/android/gms/common/api/Api$Client;
    //   1038: invokeinterface getEndpointPackageName : ()Ljava/lang/String;
    //   1043: invokevirtual zza : (Lcom/google/android/gms/common/api/internal/zzh;Lcom/google/android/gms/common/ConnectionResult;Ljava/lang/String;)V
    //   1046: goto -> 960
    //   1049: aload #7
    //   1051: invokevirtual zzbp : ()Lcom/google/android/gms/common/ConnectionResult;
    //   1054: ifnull -> 1072
    //   1057: aload #8
    //   1059: aload_1
    //   1060: aload #7
    //   1062: invokevirtual zzbp : ()Lcom/google/android/gms/common/ConnectionResult;
    //   1065: aconst_null
    //   1066: invokevirtual zza : (Lcom/google/android/gms/common/api/internal/zzh;Lcom/google/android/gms/common/ConnectionResult;Ljava/lang/String;)V
    //   1069: goto -> 960
    //   1072: aload #7
    //   1074: aload #8
    //   1076: invokevirtual zza : (Lcom/google/android/gms/common/api/internal/zzj;)V
    //   1079: goto -> 960
    //   1082: aload_1
    //   1083: getfield obj : Ljava/lang/Object;
    //   1086: checkcast java/lang/Boolean
    //   1089: invokevirtual booleanValue : ()Z
    //   1092: ifeq -> 1099
    //   1095: ldc2_w 10000
    //   1098: lstore_3
    //   1099: aload_0
    //   1100: lload_3
    //   1101: putfield zzjn : J
    //   1104: aload_0
    //   1105: getfield handler : Landroid/os/Handler;
    //   1108: bipush #12
    //   1110: invokevirtual removeMessages : (I)V
    //   1113: aload_0
    //   1114: getfield zzju : Ljava/util/Map;
    //   1117: invokeinterface keySet : ()Ljava/util/Set;
    //   1122: invokeinterface iterator : ()Ljava/util/Iterator;
    //   1127: astore #5
    //   1129: aload #5
    //   1131: invokeinterface hasNext : ()Z
    //   1136: ifeq -> 1175
    //   1139: aload #5
    //   1141: invokeinterface next : ()Ljava/lang/Object;
    //   1146: checkcast com/google/android/gms/common/api/internal/zzh
    //   1149: astore_1
    //   1150: aload_0
    //   1151: getfield handler : Landroid/os/Handler;
    //   1154: aload_0
    //   1155: getfield handler : Landroid/os/Handler;
    //   1158: bipush #12
    //   1160: aload_1
    //   1161: invokevirtual obtainMessage : (ILjava/lang/Object;)Landroid/os/Message;
    //   1164: aload_0
    //   1165: getfield zzjn : J
    //   1168: invokevirtual sendMessageDelayed : (Landroid/os/Message;J)Z
    //   1171: pop
    //   1172: goto -> 1129
    //   1175: iconst_1
    //   1176: ireturn
  }
  
  final void maybeSignOut() {
    this.zzjt.incrementAndGet();
    this.handler.sendMessage(this.handler.obtainMessage(10));
  }
  
  final PendingIntent zza(zzh<?> paramzzh, int paramInt) {
    zza zza = this.zzju.get(paramzzh);
    if (zza == null)
      return null; 
    SignInClient signInClient = zza.zzbt();
    return (signInClient == null) ? null : PendingIntent.getActivity(this.zzjp, paramInt, signInClient.getSignInIntent(), 134217728);
  }
  
  public final <O extends Api.ApiOptions> Task<Boolean> zza(@NonNull GoogleApi<O> paramGoogleApi, @NonNull ListenerHolder.ListenerKey<?> paramListenerKey) {
    TaskCompletionSource<Boolean> taskCompletionSource = new TaskCompletionSource();
    zzg zzg = new zzg(paramListenerKey, taskCompletionSource);
    this.handler.sendMessage(this.handler.obtainMessage(13, new zzbu(zzg, this.zzjt.get(), paramGoogleApi)));
    return taskCompletionSource.getTask();
  }
  
  public final <O extends Api.ApiOptions> Task<Void> zza(@NonNull GoogleApi<O> paramGoogleApi, @NonNull RegisterListenerMethod<Api.AnyClient, ?> paramRegisterListenerMethod, @NonNull UnregisterListenerMethod<Api.AnyClient, ?> paramUnregisterListenerMethod) {
    TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource();
    zze zze = new zze(new zzbv(paramRegisterListenerMethod, paramUnregisterListenerMethod), taskCompletionSource);
    this.handler.sendMessage(this.handler.obtainMessage(8, new zzbu(zze, this.zzjt.get(), paramGoogleApi)));
    return taskCompletionSource.getTask();
  }
  
  public final Task<Map<zzh<?>, String>> zza(Iterable<? extends GoogleApi<?>> paramIterable) {
    zzj zzj = new zzj(paramIterable);
    this.handler.sendMessage(this.handler.obtainMessage(2, zzj));
    return zzj.getTask();
  }
  
  public final void zza(ConnectionResult paramConnectionResult, int paramInt) {
    if (!zzc(paramConnectionResult, paramInt))
      this.handler.sendMessage(this.handler.obtainMessage(5, paramInt, 0, paramConnectionResult)); 
  }
  
  public final void zza(GoogleApi<?> paramGoogleApi) {
    this.handler.sendMessage(this.handler.obtainMessage(7, paramGoogleApi));
  }
  
  public final <O extends Api.ApiOptions> void zza(GoogleApi<O> paramGoogleApi, int paramInt, BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient> paramApiMethodImpl) {
    zzd<BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient>> zzd = new zzd<>(paramInt, paramApiMethodImpl);
    this.handler.sendMessage(this.handler.obtainMessage(4, new zzbu(zzd, this.zzjt.get(), paramGoogleApi)));
  }
  
  public final <O extends Api.ApiOptions, ResultT> void zza(GoogleApi<O> paramGoogleApi, int paramInt, TaskApiCall<Api.AnyClient, ResultT> paramTaskApiCall, TaskCompletionSource<ResultT> paramTaskCompletionSource, StatusExceptionMapper paramStatusExceptionMapper) {
    zzf<ResultT> zzf = new zzf<>(paramInt, paramTaskApiCall, paramTaskCompletionSource, paramStatusExceptionMapper);
    this.handler.sendMessage(this.handler.obtainMessage(4, new zzbu(zzf, this.zzjt.get(), paramGoogleApi)));
  }
  
  public final void zza(@NonNull zzad paramzzad) {
    synchronized (lock) {
      if (this.zzjv != paramzzad) {
        this.zzjv = paramzzad;
        this.zzjw.clear();
      } 
      this.zzjw.addAll((Collection<? extends zzh<?>>)paramzzad.zzam());
      return;
    } 
  }
  
  final void zzb(@NonNull zzad paramzzad) {
    synchronized (lock) {
      if (this.zzjv == paramzzad) {
        this.zzjv = null;
        this.zzjw.clear();
      } 
      return;
    } 
  }
  
  public final int zzbg() {
    return this.zzjs.getAndIncrement();
  }
  
  public final Task<Boolean> zzc(GoogleApi<?> paramGoogleApi) {
    zzae zzae = new zzae(paramGoogleApi.zzm());
    this.handler.sendMessage(this.handler.obtainMessage(14, zzae));
    return zzae.zzao().getTask();
  }
  
  final boolean zzc(ConnectionResult paramConnectionResult, int paramInt) {
    return this.zzjq.showWrappedErrorNotification(this.zzjp, paramConnectionResult, paramInt);
  }
  
  public final void zzr() {
    this.handler.sendMessage(this.handler.obtainMessage(3));
  }
  
  public final class zza<O extends Api.ApiOptions> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, zzq {
    private final zzh<O> zzhc;
    
    private final Queue<zzb> zzjz;
    
    private final Api.Client zzka;
    
    private final Api.AnyClient zzkb;
    
    private final zzaa zzkc;
    
    private final Set<zzj> zzkd;
    
    private final Map<ListenerHolder.ListenerKey<?>, zzbv> zzke;
    
    private final int zzkf;
    
    private final zzby zzkg;
    
    private boolean zzkh;
    
    private final List<GoogleApiManager.zzb> zzki;
    
    private ConnectionResult zzkj;
    
    @WorkerThread
    public zza(GoogleApiManager this$0, GoogleApi<O> param1GoogleApi) {
      Api.Client client;
      this.zzjz = new LinkedList<>();
      this.zzkd = new HashSet<>();
      this.zzke = new HashMap<>();
      this.zzki = new ArrayList<>();
      this.zzkj = null;
      this.zzka = param1GoogleApi.zza(GoogleApiManager.zza(this$0).getLooper(), this);
      if (this.zzka instanceof SimpleClientAdapter) {
        Api.SimpleClient simpleClient = ((SimpleClientAdapter)this.zzka).getClient();
      } else {
        client = this.zzka;
      } 
      this.zzkb = (Api.AnyClient)client;
      this.zzhc = param1GoogleApi.zzm();
      this.zzkc = new zzaa();
      this.zzkf = param1GoogleApi.getInstanceId();
      if (this.zzka.requiresSignIn()) {
        this.zzkg = param1GoogleApi.zza(GoogleApiManager.zzb(this$0), GoogleApiManager.zza(this$0));
        return;
      } 
      this.zzkg = null;
    }
    
    @WorkerThread
    private final void zza(GoogleApiManager.zzb param1zzb) {
      if (!this.zzki.contains(param1zzb))
        return; 
      if (!this.zzkh) {
        if (!this.zzka.isConnected()) {
          connect();
          return;
        } 
        zzbl();
      } 
    }
    
    @WorkerThread
    private final void zzb(GoogleApiManager.zzb param1zzb) {
      if (this.zzki.remove(param1zzb)) {
        GoogleApiManager.zza(this.zzjy).removeMessages(15, param1zzb);
        GoogleApiManager.zza(this.zzjy).removeMessages(16, param1zzb);
        Feature feature = GoogleApiManager.zzb.zzd(param1zzb);
        ArrayList<zzb> arrayList1 = new ArrayList(this.zzjz.size());
        for (zzb zzb1 : this.zzjz) {
          if (zzb1 instanceof zzf) {
            Feature[] arrayOfFeature = ((zzf)zzb1).getRequiredFeatures();
            if (arrayOfFeature != null && ArrayUtils.contains((Object[])arrayOfFeature, feature))
              arrayList1.add(zzb1); 
          } 
        } 
        ArrayList<zzb> arrayList2 = arrayList1;
        int i = arrayList2.size();
        byte b = 0;
        while (b < i) {
          arrayList1 = (ArrayList<zzb>)arrayList2.get(b);
          b++;
          zzb zzb1 = (zzb)arrayList1;
          this.zzjz.remove(zzb1);
          zzb1.zza((RuntimeException)new UnsupportedApiCallException(feature));
        } 
      } 
    }
    
    @WorkerThread
    private final boolean zzb(zzb param1zzb) {
      ConnectionResult connectionResult;
      if (!(param1zzb instanceof zzf)) {
        zzc(param1zzb);
        return true;
      } 
      zzf zzf = (zzf)param1zzb;
      Feature[] arrayOfFeature1 = zzf.getRequiredFeatures();
      if (arrayOfFeature1 == null || arrayOfFeature1.length == 0) {
        zzc(param1zzb);
        return true;
      } 
      Feature[] arrayOfFeature2 = this.zzka.getAvailableFeatures();
      Feature[] arrayOfFeature3 = arrayOfFeature2;
      if (arrayOfFeature2 == null)
        arrayOfFeature3 = new Feature[0]; 
      ArrayMap<String, Long> arrayMap = new ArrayMap(arrayOfFeature3.length);
      int i = arrayOfFeature3.length;
      int j;
      for (j = 0; j < i; j++) {
        Feature feature = arrayOfFeature3[j];
        arrayMap.put(feature.getName(), Long.valueOf(feature.getVersion()));
      } 
      i = arrayOfFeature1.length;
      for (j = 0; j < i; j++) {
        Feature feature = arrayOfFeature1[j];
        if (!arrayMap.containsKey(feature.getName()) || ((Long)arrayMap.get(feature.getName())).longValue() < feature.getVersion()) {
          if (zzf.shouldAutoResolveMissingFeatures()) {
            GoogleApiManager.zzb zzb1 = new GoogleApiManager.zzb(this.zzhc, feature, null);
            j = this.zzki.indexOf(zzb1);
            if (j >= 0) {
              zzb1 = this.zzki.get(j);
              GoogleApiManager.zza(this.zzjy).removeMessages(15, zzb1);
              GoogleApiManager.zza(this.zzjy).sendMessageDelayed(Message.obtain(GoogleApiManager.zza(this.zzjy), 15, zzb1), GoogleApiManager.zzc(this.zzjy));
              return false;
            } 
            this.zzki.add(zzb1);
            GoogleApiManager.zza(this.zzjy).sendMessageDelayed(Message.obtain(GoogleApiManager.zza(this.zzjy), 15, zzb1), GoogleApiManager.zzc(this.zzjy));
            GoogleApiManager.zza(this.zzjy).sendMessageDelayed(Message.obtain(GoogleApiManager.zza(this.zzjy), 16, zzb1), GoogleApiManager.zzd(this.zzjy));
            connectionResult = new ConnectionResult(2, null);
            if (!zzh(connectionResult)) {
              this.zzjy.zzc(connectionResult, this.zzkf);
              return false;
            } 
          } else {
            zzf.zza((RuntimeException)new UnsupportedApiCallException(feature));
          } 
          return false;
        } 
        this.zzki.remove(new GoogleApiManager.zzb(this.zzhc, feature, null));
      } 
      zzc((zzb)connectionResult);
      return true;
    }
    
    @WorkerThread
    private final boolean zzb(boolean param1Boolean) {
      Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
      if (this.zzka.isConnected() && this.zzke.size() == 0) {
        if (this.zzkc.zzaj()) {
          if (param1Boolean)
            zzbr(); 
          return false;
        } 
        this.zzka.disconnect();
        return true;
      } 
      return false;
    }
    
    @WorkerThread
    private final void zzbj() {
      zzbo();
      zzi(ConnectionResult.RESULT_SUCCESS);
      zzbq();
      Iterator<zzbv> iterator = this.zzke.values().iterator();
      while (true) {
        if (iterator.hasNext()) {
          zzbv zzbv = iterator.next();
          try {
            RegisterListenerMethod<Api.AnyClient, ?> registerListenerMethod = zzbv.zzlt;
            Api.AnyClient anyClient = this.zzkb;
            TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource();
            this();
            registerListenerMethod.registerListener(anyClient, taskCompletionSource);
            continue;
          } catch (DeadObjectException deadObjectException) {
            onConnectionSuspended(1);
            this.zzka.disconnect();
            zzbl();
            zzbr();
            return;
          } catch (RemoteException remoteException) {
            continue;
          } 
        } 
        zzbl();
        zzbr();
        return;
      } 
    }
    
    @WorkerThread
    private final void zzbk() {
      zzbo();
      this.zzkh = true;
      this.zzkc.zzal();
      GoogleApiManager.zza(this.zzjy).sendMessageDelayed(Message.obtain(GoogleApiManager.zza(this.zzjy), 9, this.zzhc), GoogleApiManager.zzc(this.zzjy));
      GoogleApiManager.zza(this.zzjy).sendMessageDelayed(Message.obtain(GoogleApiManager.zza(this.zzjy), 11, this.zzhc), GoogleApiManager.zzd(this.zzjy));
      GoogleApiManager.zze(this.zzjy).flush();
    }
    
    @WorkerThread
    private final void zzbl() {
      ArrayList<zzb> arrayList = new ArrayList<>(this.zzjz);
      int i = arrayList.size();
      int j = 0;
      while (j < i) {
        zzb zzb = (zzb)arrayList.get(j);
        int k = j + 1;
        zzb = zzb;
        if (this.zzka.isConnected()) {
          j = k;
          if (zzb(zzb)) {
            this.zzjz.remove(zzb);
            j = k;
          } 
        } 
      } 
    }
    
    @WorkerThread
    private final void zzbq() {
      if (this.zzkh) {
        GoogleApiManager.zza(this.zzjy).removeMessages(11, this.zzhc);
        GoogleApiManager.zza(this.zzjy).removeMessages(9, this.zzhc);
        this.zzkh = false;
      } 
    }
    
    private final void zzbr() {
      GoogleApiManager.zza(this.zzjy).removeMessages(12, this.zzhc);
      GoogleApiManager.zza(this.zzjy).sendMessageDelayed(GoogleApiManager.zza(this.zzjy).obtainMessage(12, this.zzhc), GoogleApiManager.zzi(this.zzjy));
    }
    
    @WorkerThread
    private final void zzc(zzb param1zzb) {
      param1zzb.zza(this.zzkc, requiresSignIn());
      try {
        param1zzb.zza(this);
        return;
      } catch (DeadObjectException deadObjectException) {
        onConnectionSuspended(1);
        this.zzka.disconnect();
        return;
      } 
    }
    
    @WorkerThread
    private final boolean zzh(@NonNull ConnectionResult param1ConnectionResult) {
      synchronized (GoogleApiManager.zzbh()) {
        if (GoogleApiManager.zzf(this.zzjy) != null && GoogleApiManager.zzg(this.zzjy).contains(this.zzhc)) {
          GoogleApiManager.zzf(this.zzjy).zzb(param1ConnectionResult, this.zzkf);
          return true;
        } 
        return false;
      } 
    }
    
    @WorkerThread
    private final void zzi(ConnectionResult param1ConnectionResult) {
      for (zzj zzj : this.zzkd) {
        String str = null;
        if (Objects.equal(param1ConnectionResult, ConnectionResult.RESULT_SUCCESS))
          str = this.zzka.getEndpointPackageName(); 
        zzj.zza(this.zzhc, param1ConnectionResult, str);
      } 
      this.zzkd.clear();
    }
    
    @WorkerThread
    public final void connect() {
      Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
      if (!this.zzka.isConnected()) {
        if (this.zzka.isConnecting())
          return; 
        int i = GoogleApiManager.zze(this.zzjy).getClientAvailability(GoogleApiManager.zzb(this.zzjy), this.zzka);
        if (i != 0) {
          onConnectionFailed(new ConnectionResult(i, null));
          return;
        } 
        GoogleApiManager.zzc zzc = new GoogleApiManager.zzc(this.zzjy, this.zzka, this.zzhc);
        if (this.zzka.requiresSignIn())
          this.zzkg.zza(zzc); 
        this.zzka.connect(zzc);
      } 
    }
    
    public final int getInstanceId() {
      return this.zzkf;
    }
    
    final boolean isConnected() {
      return this.zzka.isConnected();
    }
    
    public final void onConnected(@Nullable Bundle param1Bundle) {
      if (Looper.myLooper() == GoogleApiManager.zza(this.zzjy).getLooper()) {
        zzbj();
        return;
      } 
      GoogleApiManager.zza(this.zzjy).post(new zzbi(this));
    }
    
    @WorkerThread
    public final void onConnectionFailed(@NonNull ConnectionResult param1ConnectionResult) {
      Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
      if (this.zzkg != null)
        this.zzkg.zzbz(); 
      zzbo();
      GoogleApiManager.zze(this.zzjy).flush();
      zzi(param1ConnectionResult);
      if (param1ConnectionResult.getErrorCode() == 4) {
        zzc(GoogleApiManager.zzbi());
        return;
      } 
      if (this.zzjz.isEmpty()) {
        this.zzkj = param1ConnectionResult;
        return;
      } 
      if (zzh(param1ConnectionResult))
        return; 
      if (!this.zzjy.zzc(param1ConnectionResult, this.zzkf)) {
        if (param1ConnectionResult.getErrorCode() == 18)
          this.zzkh = true; 
        if (this.zzkh) {
          GoogleApiManager.zza(this.zzjy).sendMessageDelayed(Message.obtain(GoogleApiManager.zza(this.zzjy), 9, this.zzhc), GoogleApiManager.zzc(this.zzjy));
          return;
        } 
        String str = this.zzhc.zzq();
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 38);
        stringBuilder.append("API: ");
        stringBuilder.append(str);
        stringBuilder.append(" is not available on this device.");
        zzc(new Status(17, stringBuilder.toString()));
      } 
    }
    
    public final void onConnectionSuspended(int param1Int) {
      if (Looper.myLooper() == GoogleApiManager.zza(this.zzjy).getLooper()) {
        zzbk();
        return;
      } 
      GoogleApiManager.zza(this.zzjy).post(new zzbj(this));
    }
    
    public final boolean requiresSignIn() {
      return this.zzka.requiresSignIn();
    }
    
    @WorkerThread
    public final void resume() {
      Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
      if (this.zzkh)
        connect(); 
    }
    
    public final void zza(ConnectionResult param1ConnectionResult, Api<?> param1Api, boolean param1Boolean) {
      if (Looper.myLooper() == GoogleApiManager.zza(this.zzjy).getLooper()) {
        onConnectionFailed(param1ConnectionResult);
        return;
      } 
      GoogleApiManager.zza(this.zzjy).post(new zzbk(this, param1ConnectionResult));
    }
    
    @WorkerThread
    public final void zza(zzb param1zzb) {
      Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
      if (this.zzka.isConnected()) {
        if (zzb(param1zzb)) {
          zzbr();
          return;
        } 
        this.zzjz.add(param1zzb);
        return;
      } 
      this.zzjz.add(param1zzb);
      if (this.zzkj != null && this.zzkj.hasResolution()) {
        onConnectionFailed(this.zzkj);
        return;
      } 
      connect();
    }
    
    @WorkerThread
    public final void zza(zzj param1zzj) {
      Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
      this.zzkd.add(param1zzj);
    }
    
    public final Api.Client zzae() {
      return this.zzka;
    }
    
    @WorkerThread
    public final void zzay() {
      Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
      if (this.zzkh) {
        Status status;
        zzbq();
        if (GoogleApiManager.zzh(this.zzjy).isGooglePlayServicesAvailable(GoogleApiManager.zzb(this.zzjy)) == 18) {
          status = new Status(8, "Connection timed out while waiting for Google Play services update to complete.");
        } else {
          status = new Status(8, "API failed to connect while resuming due to an unknown error.");
        } 
        zzc(status);
        this.zzka.disconnect();
      } 
    }
    
    @WorkerThread
    public final void zzbm() {
      Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
      zzc(GoogleApiManager.zzjj);
      this.zzkc.zzak();
      ListenerHolder.ListenerKey[] arrayOfListenerKey = (ListenerHolder.ListenerKey[])this.zzke.keySet().toArray((Object[])new ListenerHolder.ListenerKey[this.zzke.size()]);
      int i = arrayOfListenerKey.length;
      for (byte b = 0; b < i; b++)
        zza(new zzg(arrayOfListenerKey[b], new TaskCompletionSource())); 
      zzi(new ConnectionResult(4));
      if (this.zzka.isConnected())
        this.zzka.onUserSignOut(new zzbl(this)); 
    }
    
    public final Map<ListenerHolder.ListenerKey<?>, zzbv> zzbn() {
      return this.zzke;
    }
    
    @WorkerThread
    public final void zzbo() {
      Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
      this.zzkj = null;
    }
    
    @WorkerThread
    public final ConnectionResult zzbp() {
      Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
      return this.zzkj;
    }
    
    @WorkerThread
    public final boolean zzbs() {
      return zzb(true);
    }
    
    final SignInClient zzbt() {
      return (this.zzkg == null) ? null : this.zzkg.zzbt();
    }
    
    @WorkerThread
    public final void zzc(Status param1Status) {
      Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
      Iterator<zzb> iterator = this.zzjz.iterator();
      while (iterator.hasNext())
        ((zzb)iterator.next()).zza(param1Status); 
      this.zzjz.clear();
    }
    
    @WorkerThread
    public final void zzg(@NonNull ConnectionResult param1ConnectionResult) {
      Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
      this.zzka.disconnect();
      onConnectionFailed(param1ConnectionResult);
    }
  }
  
  private static final class zzb {
    private final Feature zzdr;
    
    private final zzh<?> zzkn;
    
    private zzb(zzh<?> param1zzh, Feature param1Feature) {
      this.zzkn = param1zzh;
      this.zzdr = param1Feature;
    }
    
    public final boolean equals(Object param1Object) {
      if (param1Object != null && param1Object instanceof zzb) {
        param1Object = param1Object;
        if (Objects.equal(this.zzkn, ((zzb)param1Object).zzkn) && Objects.equal(this.zzdr, ((zzb)param1Object).zzdr))
          return true; 
      } 
      return false;
    }
    
    public final int hashCode() {
      return Objects.hashCode(new Object[] { this.zzkn, this.zzdr });
    }
    
    public final String toString() {
      return Objects.toStringHelper(this).add("key", this.zzkn).add("feature", this.zzdr).toString();
    }
  }
  
  private final class zzc implements zzcb, BaseGmsClient.ConnectionProgressReportCallbacks {
    private final zzh<?> zzhc;
    
    private final Api.Client zzka;
    
    private IAccountAccessor zzko = null;
    
    private Set<Scope> zzkp = null;
    
    private boolean zzkq = false;
    
    public zzc(GoogleApiManager this$0, Api.Client param1Client, zzh<?> param1zzh) {
      this.zzka = param1Client;
      this.zzhc = param1zzh;
    }
    
    @WorkerThread
    private final void zzbu() {
      if (this.zzkq && this.zzko != null)
        this.zzka.getRemoteService(this.zzko, this.zzkp); 
    }
    
    public final void onReportServiceBinding(@NonNull ConnectionResult param1ConnectionResult) {
      GoogleApiManager.zza(this.zzjy).post(new zzbn(this, param1ConnectionResult));
    }
    
    @WorkerThread
    public final void zza(IAccountAccessor param1IAccountAccessor, Set<Scope> param1Set) {
      if (param1IAccountAccessor == null || param1Set == null) {
        Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
        zzg(new ConnectionResult(4));
        return;
      } 
      this.zzko = param1IAccountAccessor;
      this.zzkp = param1Set;
      zzbu();
    }
    
    @WorkerThread
    public final void zzg(ConnectionResult param1ConnectionResult) {
      ((GoogleApiManager.zza)GoogleApiManager.zzj(this.zzjy).get(this.zzhc)).zzg(param1ConnectionResult);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\GoogleApiManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */