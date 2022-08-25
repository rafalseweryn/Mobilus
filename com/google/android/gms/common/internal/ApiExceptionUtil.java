package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;

public class ApiExceptionUtil {
  @NonNull
  public static ApiException fromConnectionResult(@NonNull ConnectionResult paramConnectionResult) {
    return fromStatus(new Status(paramConnectionResult.getErrorCode(), paramConnectionResult.getErrorMessage(), paramConnectionResult.getResolution()));
  }
  
  @NonNull
  public static ApiException fromStatus(@NonNull Status paramStatus) {
    return (ApiException)(paramStatus.hasResolution() ? new ResolvableApiException(paramStatus) : new ApiException(paramStatus));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\ApiExceptionUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */