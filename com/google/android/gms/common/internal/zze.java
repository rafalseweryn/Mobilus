package com.google.android.gms.common.internal;

import android.support.v4.util.LruCache;

final class zze extends LruCache<K, V> {
  zze(ExpirableLruCache paramExpirableLruCache, int paramInt) {
    super(paramInt);
  }
  
  protected final V create(K paramK) {
    return this.zzss.create(paramK);
  }
  
  protected final void entryRemoved(boolean paramBoolean, K paramK, V paramV1, V paramV2) {
    this.zzss.entryRemoved(paramBoolean, paramK, paramV1, paramV2);
    paramV1 = (V)ExpirableLruCache.zza(this.zzss);
    /* monitor enter ClassFileLocalVariableReferenceExpression{type=GenericType{V}, name=paramV1} */
    if (paramV2 == null)
      try {
        if (ExpirableLruCache.zzb(this.zzss))
          ExpirableLruCache.zzc(this.zzss).remove(paramK); 
      } finally {} 
    if (paramV2 == null && ExpirableLruCache.zzd(this.zzss))
      ExpirableLruCache.zze(this.zzss).remove(paramK); 
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=GenericType{V}, name=paramV1} */
  }
  
  protected final int sizeOf(K paramK, V paramV) {
    return this.zzss.sizeOf(paramK, paramV);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\zze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */