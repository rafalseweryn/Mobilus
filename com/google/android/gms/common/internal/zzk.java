package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;

final class zzk implements PendingResultUtil.StatusConverter {
  public final ApiException convert(Status paramStatus) {
    return ApiExceptionUtil.fromStatus(paramStatus);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\zzk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */