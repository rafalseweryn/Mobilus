package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

interface zzq<TResult> {
  void cancel();
  
  void onComplete(@NonNull Task<TResult> paramTask);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\tasks\zzq.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */