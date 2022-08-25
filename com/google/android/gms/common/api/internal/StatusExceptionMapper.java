package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;

@KeepForSdk
public interface StatusExceptionMapper {
  Exception getException(Status paramStatus);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\StatusExceptionMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */