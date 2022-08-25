package com.google.android.gms.common.internal;

import android.support.v4.util.LruCache;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ExpirableLruCache<K, V> {
  public static int TIME_UNSET = -1;
  
  private final Object mLock;
  
  private final LruCache<K, V> zzsn;
  
  private final long zzso;
  
  private final long zzsp;
  
  private HashMap<K, Long> zzsq;
  
  private HashMap<K, Long> zzsr;
  
  public ExpirableLruCache(int paramInt, long paramLong1, long paramLong2, TimeUnit paramTimeUnit) {
    boolean bool;
    this.mLock = new Object();
    this.zzso = TimeUnit.NANOSECONDS.convert(paramLong1, paramTimeUnit);
    this.zzsp = TimeUnit.NANOSECONDS.convert(paramLong2, paramTimeUnit);
    if (zzct() || zzcu()) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "ExpirableLruCache has both access and write expiration negative");
    this.zzsn = new zze(this, paramInt);
    if (zzct())
      this.zzsq = new HashMap<>(); 
    if (zzcu())
      this.zzsr = new HashMap<>(); 
  }
  
  private final boolean zza(K paramK) {
    long l = System.nanoTime();
    return (zzct() && this.zzsq.containsKey(paramK) && l - ((Long)this.zzsq.get(paramK)).longValue() > this.zzso) ? true : ((zzcu() && this.zzsr.containsKey(paramK) && l - ((Long)this.zzsr.get(paramK)).longValue() > this.zzsp));
  }
  
  private final boolean zzct() {
    return (this.zzso >= 0L);
  }
  
  private final boolean zzcu() {
    return (this.zzsp >= 0L);
  }
  
  protected V create(K paramK) {
    return null;
  }
  
  protected void entryRemoved(boolean paramBoolean, K paramK, V paramV1, V paramV2) {}
  
  public void evictAll() {
    this.zzsn.evictAll();
  }
  
  public V get(K paramK) {
    synchronized (this.mLock) {
      if (zza(paramK))
        this.zzsn.remove(paramK); 
      Object object = this.zzsn.get(paramK);
      if (object != null && this.zzso > 0L) {
        long l = System.nanoTime();
        this.zzsq.put(paramK, Long.valueOf(l));
      } 
      return (V)object;
    } 
  }
  
  public V put(K paramK, V paramV) {
    if (zzcu()) {
      long l = System.nanoTime();
      synchronized (this.mLock) {
        this.zzsr.put(paramK, Long.valueOf(l));
      } 
    } 
    return (V)this.zzsn.put(paramK, paramV);
  }
  
  public V remove(K paramK) {
    return (V)this.zzsn.remove(paramK);
  }
  
  public void removeExpired() {
    for (K k : this.zzsn.snapshot().keySet()) {
      synchronized (this.mLock) {
        if (zza(k))
          this.zzsn.remove(k); 
      } 
    } 
  }
  
  protected int sizeOf(K paramK, V paramV) {
    return 1;
  }
  
  public Map<K, V> snapshot() {
    removeExpired();
    return this.zzsn.snapshot();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\ExpirableLruCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */