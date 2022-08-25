package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.util.VisibleForTesting;

public class HashAccumulator {
  @VisibleForTesting
  private static int zzad = 31;
  
  private int zzae = 1;
  
  public HashAccumulator addBoolean(boolean paramBoolean) {
    this.zzae = zzad * this.zzae + paramBoolean;
    return this;
  }
  
  public HashAccumulator addObject(Object paramObject) {
    int k;
    int i = zzad;
    int j = this.zzae;
    if (paramObject == null) {
      k = 0;
    } else {
      k = paramObject.hashCode();
    } 
    this.zzae = i * j + k;
    return this;
  }
  
  public int hash() {
    return this.zzae;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\auth\api\signin\internal\HashAccumulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */