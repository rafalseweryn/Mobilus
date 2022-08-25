package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;

public final class zzh<O extends Api.ApiOptions> {
  private final Api<O> mApi;
  
  private final O zzcl;
  
  private final boolean zzeb = true;
  
  private final int zzec;
  
  private zzh(Api<O> paramApi) {
    this.mApi = paramApi;
    this.zzcl = null;
    this.zzec = System.identityHashCode(this);
  }
  
  private zzh(Api<O> paramApi, O paramO) {
    this.mApi = paramApi;
    this.zzcl = paramO;
    this.zzec = Objects.hashCode(new Object[] { this.mApi, this.zzcl });
  }
  
  public static <O extends Api.ApiOptions> zzh<O> zza(Api<O> paramApi) {
    return new zzh<>(paramApi);
  }
  
  public static <O extends Api.ApiOptions> zzh<O> zza(Api<O> paramApi, O paramO) {
    return new zzh<>(paramApi, paramO);
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzh))
      return false; 
    paramObject = paramObject;
    return (!this.zzeb && !((zzh)paramObject).zzeb && Objects.equal(this.mApi, ((zzh)paramObject).mApi) && Objects.equal(this.zzcl, ((zzh)paramObject).zzcl));
  }
  
  public final int hashCode() {
    return this.zzec;
  }
  
  public final String zzq() {
    return this.mApi.getName();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzh.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */