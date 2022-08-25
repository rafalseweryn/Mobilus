package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzac implements OnCompleteListener<TResult> {
  zzac(zzaa paramzzaa, TaskCompletionSource paramTaskCompletionSource) {}
  
  public final void onComplete(@NonNull Task<TResult> paramTask) {
    zzaa.zzb(this.zzgz).remove(this.zzha);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzac.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */