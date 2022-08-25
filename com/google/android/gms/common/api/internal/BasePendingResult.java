package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@KeepForSdk
@KeepName
public abstract class BasePendingResult<R extends Result> extends PendingResult<R> {
  static final ThreadLocal<Boolean> zzez = new zzo();
  
  @KeepName
  private zza mResultGuardian;
  
  private Status mStatus;
  
  private R zzdm;
  
  private final Object zzfa;
  
  private final CallbackHandler<R> zzfb;
  
  private final WeakReference<GoogleApiClient> zzfc;
  
  private final CountDownLatch zzfd;
  
  private final ArrayList<PendingResult.StatusListener> zzfe;
  
  private ResultCallback<? super R> zzff;
  
  private final AtomicReference<zzcn> zzfg;
  
  private volatile boolean zzfh;
  
  private boolean zzfi;
  
  private boolean zzfj;
  
  private ICancelToken zzfk;
  
  private volatile zzch<R> zzfl;
  
  private boolean zzfm;
  
  @Deprecated
  BasePendingResult() {
    this.zzfa = new Object();
    this.zzfd = new CountDownLatch(1);
    this.zzfe = new ArrayList<>();
    this.zzfg = new AtomicReference<>();
    this.zzfm = false;
    this.zzfb = new CallbackHandler<>(Looper.getMainLooper());
    this.zzfc = new WeakReference<>(null);
  }
  
  @Deprecated
  @KeepForSdk
  protected BasePendingResult(Looper paramLooper) {
    this.zzfa = new Object();
    this.zzfd = new CountDownLatch(1);
    this.zzfe = new ArrayList<>();
    this.zzfg = new AtomicReference<>();
    this.zzfm = false;
    this.zzfb = new CallbackHandler<>(paramLooper);
    this.zzfc = new WeakReference<>(null);
  }
  
  @KeepForSdk
  protected BasePendingResult(GoogleApiClient paramGoogleApiClient) {
    Looper looper;
    this.zzfa = new Object();
    this.zzfd = new CountDownLatch(1);
    this.zzfe = new ArrayList<>();
    this.zzfg = new AtomicReference<>();
    this.zzfm = false;
    if (paramGoogleApiClient != null) {
      looper = paramGoogleApiClient.getLooper();
    } else {
      looper = Looper.getMainLooper();
    } 
    this.zzfb = new CallbackHandler<>(looper);
    this.zzfc = new WeakReference<>(paramGoogleApiClient);
  }
  
  @KeepForSdk
  @VisibleForTesting
  protected BasePendingResult(@NonNull CallbackHandler<R> paramCallbackHandler) {
    this.zzfa = new Object();
    this.zzfd = new CountDownLatch(1);
    this.zzfe = new ArrayList<>();
    this.zzfg = new AtomicReference<>();
    this.zzfm = false;
    this.zzfb = (CallbackHandler<R>)Preconditions.checkNotNull(paramCallbackHandler, "CallbackHandler must not be null");
    this.zzfc = new WeakReference<>(null);
  }
  
  private final R get() {
    synchronized (this.zzfa) {
      Preconditions.checkState(this.zzfh ^ true, "Result has already been consumed.");
      Preconditions.checkState(isReady(), "Result is not ready.");
      R r = this.zzdm;
      this.zzdm = null;
      this.zzff = null;
      this.zzfh = true;
      null = this.zzfg.getAndSet(null);
      if (null != null)
        null.zzc(this); 
      return r;
    } 
  }
  
  private final void zza(R paramR) {
    this.zzdm = paramR;
    this.zzfk = null;
    this.zzfd.countDown();
    this.mStatus = this.zzdm.getStatus();
    if (this.zzfi) {
      this.zzff = null;
    } else if (this.zzff == null) {
      if (this.zzdm instanceof Releasable)
        this.mResultGuardian = new zza(null); 
    } else {
      this.zzfb.removeMessages(2);
      this.zzfb.zza(this.zzff, get());
    } 
    ArrayList<PendingResult.StatusListener> arrayList = this.zzfe;
    int i = arrayList.size();
    byte b = 0;
    while (b < i) {
      PendingResult.StatusListener statusListener = (PendingResult.StatusListener)arrayList.get(b);
      b++;
      ((PendingResult.StatusListener)statusListener).onComplete(this.mStatus);
    } 
    this.zzfe.clear();
  }
  
  public static void zzb(Result paramResult) {
    if (paramResult instanceof Releasable)
      try {
        ((Releasable)paramResult).release();
        return;
      } catch (RuntimeException runtimeException) {
        String str = String.valueOf(paramResult);
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 18);
        stringBuilder.append("Unable to release ");
        stringBuilder.append(str);
        Log.w("BasePendingResult", stringBuilder.toString(), runtimeException);
      }  
  }
  
  public final void addStatusListener(PendingResult.StatusListener paramStatusListener) {
    boolean bool;
    if (paramStatusListener != null) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "Callback cannot be null.");
    synchronized (this.zzfa) {
      if (isReady()) {
        paramStatusListener.onComplete(this.mStatus);
      } else {
        this.zzfe.add(paramStatusListener);
      } 
      return;
    } 
  }
  
  public final R await() {
    Preconditions.checkNotMainThread("await must not be called on the UI thread");
    boolean bool = this.zzfh;
    boolean bool1 = true;
    Preconditions.checkState(bool ^ true, "Result has already been consumed");
    if (this.zzfl != null)
      bool1 = false; 
    Preconditions.checkState(bool1, "Cannot await if then() has been called.");
    try {
      this.zzfd.await();
    } catch (InterruptedException interruptedException) {
      zzb(Status.RESULT_INTERRUPTED);
    } 
    Preconditions.checkState(isReady(), "Result is not ready.");
    return get();
  }
  
  public final R await(long paramLong, TimeUnit paramTimeUnit) {
    if (paramLong > 0L)
      Preconditions.checkNotMainThread("await must not be called on the UI thread when time is greater than zero."); 
    boolean bool = this.zzfh;
    boolean bool1 = true;
    Preconditions.checkState(bool ^ true, "Result has already been consumed.");
    if (this.zzfl != null)
      bool1 = false; 
    Preconditions.checkState(bool1, "Cannot await if then() has been called.");
    try {
      if (!this.zzfd.await(paramLong, paramTimeUnit))
        zzb(Status.RESULT_TIMEOUT); 
    } catch (InterruptedException interruptedException) {
      zzb(Status.RESULT_INTERRUPTED);
    } 
    Preconditions.checkState(isReady(), "Result is not ready.");
    return get();
  }
  
  @KeepForSdk
  public void cancel() {
    synchronized (this.zzfa) {
      if (this.zzfi || this.zzfh)
        return; 
      ICancelToken iCancelToken = this.zzfk;
      if (iCancelToken != null)
        try {
          this.zzfk.cancel();
        } catch (RemoteException remoteException) {} 
      zzb((Result)this.zzdm);
      this.zzfi = true;
      zza(createFailedResult(Status.RESULT_CANCELED));
      return;
    } 
  }
  
  @NonNull
  @KeepForSdk
  protected abstract R createFailedResult(Status paramStatus);
  
  public boolean isCanceled() {
    synchronized (this.zzfa) {
      return this.zzfi;
    } 
  }
  
  @KeepForSdk
  public final boolean isReady() {
    return (this.zzfd.getCount() == 0L);
  }
  
  @KeepForSdk
  protected final void setCancelToken(ICancelToken paramICancelToken) {
    synchronized (this.zzfa) {
      this.zzfk = paramICancelToken;
      return;
    } 
  }
  
  @KeepForSdk
  public final void setResult(R paramR) {
    synchronized (this.zzfa) {
      if (!this.zzfj && !this.zzfi) {
        isReady();
        Preconditions.checkState(isReady() ^ true, "Results have already been set");
        Preconditions.checkState(this.zzfh ^ true, "Result has already been consumed");
        zza(paramR);
        return;
      } 
      zzb((Result)paramR);
      return;
    } 
  }
  
  @KeepForSdk
  public final void setResultCallback(ResultCallback<? super R> paramResultCallback) {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzfa : Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_1
    //   8: ifnonnull -> 23
    //   11: aload_0
    //   12: aconst_null
    //   13: putfield zzff : Lcom/google/android/gms/common/api/ResultCallback;
    //   16: aload_2
    //   17: monitorexit
    //   18: return
    //   19: astore_1
    //   20: goto -> 100
    //   23: aload_0
    //   24: getfield zzfh : Z
    //   27: istore_3
    //   28: iconst_1
    //   29: istore #4
    //   31: iload_3
    //   32: iconst_1
    //   33: ixor
    //   34: ldc 'Result has already been consumed.'
    //   36: invokestatic checkState : (ZLjava/lang/Object;)V
    //   39: aload_0
    //   40: getfield zzfl : Lcom/google/android/gms/common/api/internal/zzch;
    //   43: ifnonnull -> 49
    //   46: goto -> 52
    //   49: iconst_0
    //   50: istore #4
    //   52: iload #4
    //   54: ldc_w 'Cannot set callbacks if then() has been called.'
    //   57: invokestatic checkState : (ZLjava/lang/Object;)V
    //   60: aload_0
    //   61: invokevirtual isCanceled : ()Z
    //   64: ifeq -> 70
    //   67: aload_2
    //   68: monitorexit
    //   69: return
    //   70: aload_0
    //   71: invokevirtual isReady : ()Z
    //   74: ifeq -> 92
    //   77: aload_0
    //   78: getfield zzfb : Lcom/google/android/gms/common/api/internal/BasePendingResult$CallbackHandler;
    //   81: aload_1
    //   82: aload_0
    //   83: invokespecial get : ()Lcom/google/android/gms/common/api/Result;
    //   86: invokevirtual zza : (Lcom/google/android/gms/common/api/ResultCallback;Lcom/google/android/gms/common/api/Result;)V
    //   89: goto -> 97
    //   92: aload_0
    //   93: aload_1
    //   94: putfield zzff : Lcom/google/android/gms/common/api/ResultCallback;
    //   97: aload_2
    //   98: monitorexit
    //   99: return
    //   100: aload_2
    //   101: monitorexit
    //   102: aload_1
    //   103: athrow
    // Exception table:
    //   from	to	target	type
    //   11	18	19	finally
    //   23	28	19	finally
    //   31	46	19	finally
    //   52	69	19	finally
    //   70	89	19	finally
    //   92	97	19	finally
    //   97	99	19	finally
    //   100	102	19	finally
  }
  
  @KeepForSdk
  public final void setResultCallback(ResultCallback<? super R> paramResultCallback, long paramLong, TimeUnit paramTimeUnit) {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzfa : Ljava/lang/Object;
    //   4: astore #5
    //   6: aload #5
    //   8: monitorenter
    //   9: aload_1
    //   10: ifnonnull -> 26
    //   13: aload_0
    //   14: aconst_null
    //   15: putfield zzff : Lcom/google/android/gms/common/api/ResultCallback;
    //   18: aload #5
    //   20: monitorexit
    //   21: return
    //   22: astore_1
    //   23: goto -> 131
    //   26: aload_0
    //   27: getfield zzfh : Z
    //   30: istore #6
    //   32: iconst_1
    //   33: istore #7
    //   35: iload #6
    //   37: iconst_1
    //   38: ixor
    //   39: ldc 'Result has already been consumed.'
    //   41: invokestatic checkState : (ZLjava/lang/Object;)V
    //   44: aload_0
    //   45: getfield zzfl : Lcom/google/android/gms/common/api/internal/zzch;
    //   48: ifnonnull -> 54
    //   51: goto -> 57
    //   54: iconst_0
    //   55: istore #7
    //   57: iload #7
    //   59: ldc_w 'Cannot set callbacks if then() has been called.'
    //   62: invokestatic checkState : (ZLjava/lang/Object;)V
    //   65: aload_0
    //   66: invokevirtual isCanceled : ()Z
    //   69: ifeq -> 76
    //   72: aload #5
    //   74: monitorexit
    //   75: return
    //   76: aload_0
    //   77: invokevirtual isReady : ()Z
    //   80: ifeq -> 98
    //   83: aload_0
    //   84: getfield zzfb : Lcom/google/android/gms/common/api/internal/BasePendingResult$CallbackHandler;
    //   87: aload_1
    //   88: aload_0
    //   89: invokespecial get : ()Lcom/google/android/gms/common/api/Result;
    //   92: invokevirtual zza : (Lcom/google/android/gms/common/api/ResultCallback;Lcom/google/android/gms/common/api/Result;)V
    //   95: goto -> 127
    //   98: aload_0
    //   99: aload_1
    //   100: putfield zzff : Lcom/google/android/gms/common/api/ResultCallback;
    //   103: aload_0
    //   104: getfield zzfb : Lcom/google/android/gms/common/api/internal/BasePendingResult$CallbackHandler;
    //   107: astore_1
    //   108: aload #4
    //   110: lload_2
    //   111: invokevirtual toMillis : (J)J
    //   114: lstore_2
    //   115: aload_1
    //   116: aload_1
    //   117: iconst_2
    //   118: aload_0
    //   119: invokevirtual obtainMessage : (ILjava/lang/Object;)Landroid/os/Message;
    //   122: lload_2
    //   123: invokevirtual sendMessageDelayed : (Landroid/os/Message;J)Z
    //   126: pop
    //   127: aload #5
    //   129: monitorexit
    //   130: return
    //   131: aload #5
    //   133: monitorexit
    //   134: aload_1
    //   135: athrow
    // Exception table:
    //   from	to	target	type
    //   13	21	22	finally
    //   26	32	22	finally
    //   35	51	22	finally
    //   57	75	22	finally
    //   76	95	22	finally
    //   98	127	22	finally
    //   127	130	22	finally
    //   131	134	22	finally
  }
  
  public <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> paramResultTransform) {
    Preconditions.checkState(this.zzfh ^ true, "Result has already been consumed.");
    synchronized (this.zzfa) {
      zzch<R> zzch1 = this.zzfl;
      boolean bool1 = false;
      if (zzch1 == null) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      Preconditions.checkState(bool2, "Cannot call then() twice.");
      boolean bool2 = bool1;
      if (this.zzff == null)
        bool2 = true; 
      Preconditions.checkState(bool2, "Cannot call then() if callbacks are set.");
      Preconditions.checkState(this.zzfi ^ true, "Cannot call then() if result was canceled.");
      this.zzfm = true;
      zzch1 = new zzch<>();
      this(this.zzfc);
      this.zzfl = zzch1;
      TransformedResult<S> transformedResult = this.zzfl.then(paramResultTransform);
      if (isReady()) {
        this.zzfb.zza(this.zzfl, get());
      } else {
        this.zzff = this.zzfl;
      } 
      return transformedResult;
    } 
  }
  
  public final void zza(zzcn paramzzcn) {
    this.zzfg.set(paramzzcn);
  }
  
  public final void zzb(Status paramStatus) {
    synchronized (this.zzfa) {
      if (!isReady()) {
        setResult(createFailedResult(paramStatus));
        this.zzfj = true;
      } 
      return;
    } 
  }
  
  public final Integer zzo() {
    return null;
  }
  
  public final boolean zzw() {
    synchronized (this.zzfa) {
      if ((GoogleApiClient)this.zzfc.get() == null || !this.zzfm)
        super.cancel(); 
      return super.isCanceled();
    } 
  }
  
  public final void zzx() {
    boolean bool;
    if (this.zzfm || ((Boolean)zzez.get()).booleanValue()) {
      bool = true;
    } else {
      bool = false;
    } 
    this.zzfm = bool;
  }
  
  @VisibleForTesting
  public static class CallbackHandler<R extends Result> extends Handler {
    public CallbackHandler() {
      this(Looper.getMainLooper());
    }
    
    public CallbackHandler(Looper param1Looper) {
      super(param1Looper);
    }
    
    public void handleMessage(Message param1Message) {
      StringBuilder stringBuilder;
      int i;
      switch (param1Message.what) {
        default:
          i = param1Message.what;
          stringBuilder = new StringBuilder(45);
          stringBuilder.append("Don't know how to handle message: ");
          stringBuilder.append(i);
          Log.wtf("BasePendingResult", stringBuilder.toString(), new Exception());
          return;
        case 2:
          ((BasePendingResult)((Message)stringBuilder).obj).zzb(Status.RESULT_TIMEOUT);
          return;
        case 1:
          break;
      } 
      Pair pair = (Pair)((Message)stringBuilder).obj;
      ResultCallback resultCallback = (ResultCallback)pair.first;
      Result result = (Result)pair.second;
      try {
        resultCallback.onResult(result);
        return;
      } catch (RuntimeException runtimeException) {
        BasePendingResult.zzb(result);
        throw runtimeException;
      } 
    }
    
    public final void zza(ResultCallback<? super R> param1ResultCallback, R param1R) {
      sendMessage(obtainMessage(1, new Pair(param1ResultCallback, param1R)));
    }
  }
  
  private final class zza {
    private zza(BasePendingResult this$0) {}
    
    protected final void finalize() throws Throwable {
      BasePendingResult.zzb(BasePendingResult.zza(this.zzfn));
      super.finalize();
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\BasePendingResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */