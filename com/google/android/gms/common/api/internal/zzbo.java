package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;

public final class zzbo<O extends Api.ApiOptions> extends zzaf {
  private final GoogleApi<O> zzks;
  
  public zzbo(GoogleApi<O> paramGoogleApi) {
    super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
    this.zzks = paramGoogleApi;
  }
  
  public final <A extends Api.AnyClient, R extends com.google.android.gms.common.api.Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T paramT) {
    return (T)this.zzks.doRead((BaseImplementation.ApiMethodImpl)paramT);
  }
  
  public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends com.google.android.gms.common.api.Result, A>> T execute(@NonNull T paramT) {
    return (T)this.zzks.doWrite((BaseImplementation.ApiMethodImpl)paramT);
  }
  
  public final Context getContext() {
    return this.zzks.getApplicationContext();
  }
  
  public final Looper getLooper() {
    return this.zzks.getLooper();
  }
  
  public final void zza(zzch paramzzch) {}
  
  public final void zzb(zzch paramzzch) {}
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzbo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */