package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.util.concurrent.NumberedThreadFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public final class zzbg {
  private static final ExecutorService zzji = Executors.newFixedThreadPool(2, (ThreadFactory)new NumberedThreadFactory("GAC_Executor"));
  
  public static ExecutorService zzbe() {
    return zzji;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzbg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */