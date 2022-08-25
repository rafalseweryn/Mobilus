package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public final class GmsClientEventManager implements Handler.Callback {
  private final Handler mHandler;
  
  private final Object mLock = new Object();
  
  private final GmsClientEventState zztf;
  
  private final ArrayList<GoogleApiClient.ConnectionCallbacks> zztg = new ArrayList<>();
  
  @VisibleForTesting
  private final ArrayList<GoogleApiClient.ConnectionCallbacks> zzth = new ArrayList<>();
  
  private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zzti = new ArrayList<>();
  
  private volatile boolean zztj = false;
  
  private final AtomicInteger zztk = new AtomicInteger(0);
  
  private boolean zztl = false;
  
  public GmsClientEventManager(Looper paramLooper, GmsClientEventState paramGmsClientEventState) {
    this.zztf = paramGmsClientEventState;
    this.mHandler = new Handler(paramLooper, this);
  }
  
  public final boolean areCallbacksEnabled() {
    return this.zztj;
  }
  
  public final void disableCallbacks() {
    this.zztj = false;
    this.zztk.incrementAndGet();
  }
  
  public final void enableCallbacks() {
    this.zztj = true;
  }
  
  public final boolean handleMessage(Message paramMessage) {
    if (paramMessage.what == 1) {
      null = (GoogleApiClient.ConnectionCallbacks)paramMessage.obj;
      synchronized (this.mLock) {
        if (this.zztj && this.zztf.isConnected() && this.zztg.contains(null))
          null.onConnected(this.zztf.getConnectionHint()); 
        return true;
      } 
    } 
    int i = paramMessage.what;
    StringBuilder stringBuilder = new StringBuilder(45);
    stringBuilder.append("Don't know how to handle message: ");
    stringBuilder.append(i);
    Log.wtf("GmsClientEvents", stringBuilder.toString(), new Exception());
    return false;
  }
  
  public final boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    Preconditions.checkNotNull(paramConnectionCallbacks);
    synchronized (this.mLock) {
      return this.zztg.contains(paramConnectionCallbacks);
    } 
  }
  
  public final boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    Preconditions.checkNotNull(paramOnConnectionFailedListener);
    synchronized (this.mLock) {
      return this.zzti.contains(paramOnConnectionFailedListener);
    } 
  }
  
  @VisibleForTesting
  public final void onConnectionFailure(ConnectionResult paramConnectionResult) {
    boolean bool;
    Looper looper1 = Looper.myLooper();
    Looper looper2 = this.mHandler.getLooper();
    int i = 0;
    if (looper1 == looper2) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkState(bool, "onConnectionFailure must only be called on the Handler thread");
    this.mHandler.removeMessages(1);
    synchronized (this.mLock) {
      ArrayList<Object> arrayList = new ArrayList();
      this((Collection)this.zzti);
      int j = this.zztk.get();
      arrayList = arrayList;
      int k = arrayList.size();
      while (i < k) {
        GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = (GoogleApiClient.OnConnectionFailedListener)arrayList.get(i);
        int m = i + 1;
        onConnectionFailedListener = onConnectionFailedListener;
        if (!this.zztj || this.zztk.get() != j)
          return; 
        i = m;
        if (this.zzti.contains(onConnectionFailedListener)) {
          onConnectionFailedListener.onConnectionFailed(paramConnectionResult);
          i = m;
        } 
      } 
      return;
    } 
  }
  
  @VisibleForTesting
  protected final void onConnectionSuccess() {
    synchronized (this.mLock) {
      onConnectionSuccess(this.zztf.getConnectionHint());
      return;
    } 
  }
  
  @VisibleForTesting
  public final void onConnectionSuccess(Bundle paramBundle) {
    boolean bool2;
    Looper looper1 = Looper.myLooper();
    Looper looper2 = this.mHandler.getLooper();
    boolean bool1 = true;
    if (looper1 == looper2) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    Preconditions.checkState(bool2, "onConnectionSuccess must only be called on the Handler thread");
    synchronized (this.mLock) {
      Preconditions.checkState(this.zztl ^ true);
      this.mHandler.removeMessages(1);
      this.zztl = true;
      if (this.zzth.size() == 0) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      Preconditions.checkState(bool2);
      ArrayList<Object> arrayList = new ArrayList();
      this((Collection)this.zztg);
      int i = this.zztk.get();
      arrayList = arrayList;
      int j = arrayList.size();
      int k = 0;
      while (k < j) {
        GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks)arrayList.get(k);
        int m = k + 1;
        connectionCallbacks = connectionCallbacks;
        if (this.zztj && this.zztf.isConnected() && this.zztk.get() == i) {
          k = m;
          if (!this.zzth.contains(connectionCallbacks)) {
            connectionCallbacks.onConnected(paramBundle);
            k = m;
          } 
        } 
      } 
      this.zzth.clear();
      this.zztl = false;
      return;
    } 
  }
  
  @VisibleForTesting
  public final void onUnintentionalDisconnection(int paramInt) {
    boolean bool;
    if (Looper.myLooper() == this.mHandler.getLooper()) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkState(bool, "onUnintentionalDisconnection must only be called on the Handler thread");
    this.mHandler.removeMessages(1);
    synchronized (this.mLock) {
      this.zztl = true;
      ArrayList<Object> arrayList = new ArrayList();
      this((Collection)this.zztg);
      int i = this.zztk.get();
      arrayList = arrayList;
      int j = arrayList.size();
      int k = 0;
      while (k < j) {
        GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks)arrayList.get(k);
        int m = k + 1;
        connectionCallbacks = connectionCallbacks;
        if (this.zztj && this.zztk.get() == i) {
          k = m;
          if (this.zztg.contains(connectionCallbacks)) {
            connectionCallbacks.onConnectionSuspended(paramInt);
            k = m;
          } 
        } 
      } 
      this.zzth.clear();
      this.zztl = false;
      return;
    } 
  }
  
  public final void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    Preconditions.checkNotNull(paramConnectionCallbacks);
    synchronized (this.mLock) {
      if (this.zztg.contains(paramConnectionCallbacks)) {
        String str = String.valueOf(paramConnectionCallbacks);
        int i = String.valueOf(str).length();
        StringBuilder stringBuilder = new StringBuilder();
        this(i + 62);
        stringBuilder.append("registerConnectionCallbacks(): listener ");
        stringBuilder.append(str);
        stringBuilder.append(" is already registered");
        Log.w("GmsClientEvents", stringBuilder.toString());
      } else {
        this.zztg.add(paramConnectionCallbacks);
      } 
      if (this.zztf.isConnected())
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, paramConnectionCallbacks)); 
      return;
    } 
  }
  
  public final void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    Preconditions.checkNotNull(paramOnConnectionFailedListener);
    synchronized (this.mLock) {
      String str;
      if (this.zzti.contains(paramOnConnectionFailedListener)) {
        str = String.valueOf(paramOnConnectionFailedListener);
        int i = String.valueOf(str).length();
        StringBuilder stringBuilder = new StringBuilder();
        this(i + 67);
        stringBuilder.append("registerConnectionFailedListener(): listener ");
        stringBuilder.append(str);
        stringBuilder.append(" is already registered");
        Log.w("GmsClientEvents", stringBuilder.toString());
      } else {
        this.zzti.add(str);
      } 
      return;
    } 
  }
  
  public final void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    Preconditions.checkNotNull(paramConnectionCallbacks);
    synchronized (this.mLock) {
      StringBuilder stringBuilder;
      if (!this.zztg.remove(paramConnectionCallbacks)) {
        String str = String.valueOf(paramConnectionCallbacks);
        int i = String.valueOf(str).length();
        stringBuilder = new StringBuilder();
        this(i + 52);
        stringBuilder.append("unregisterConnectionCallbacks(): listener ");
        stringBuilder.append(str);
        stringBuilder.append(" not found");
        Log.w("GmsClientEvents", stringBuilder.toString());
      } else if (this.zztl) {
        this.zzth.add(stringBuilder);
      } 
      return;
    } 
  }
  
  public final void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    Preconditions.checkNotNull(paramOnConnectionFailedListener);
    synchronized (this.mLock) {
      if (!this.zzti.remove(paramOnConnectionFailedListener)) {
        String str = String.valueOf(paramOnConnectionFailedListener);
        int i = String.valueOf(str).length();
        StringBuilder stringBuilder = new StringBuilder();
        this(i + 57);
        stringBuilder.append("unregisterConnectionFailedListener(): listener ");
        stringBuilder.append(str);
        stringBuilder.append(" not found");
        Log.w("GmsClientEvents", stringBuilder.toString());
      } 
      return;
    } 
  }
  
  @VisibleForTesting
  public static interface GmsClientEventState {
    Bundle getConnectionHint();
    
    boolean isConnected();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\GmsClientEventManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */