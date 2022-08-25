package com.google.firebase;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;

public class FirebaseExceptionMapper implements StatusExceptionMapper {
  public Exception getException(Status paramStatus) {
    return (paramStatus.getStatusCode() == 8) ? new FirebaseException(paramStatus.zzp()) : new FirebaseApiNotAvailableException(paramStatus.zzp());
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\firebase\FirebaseExceptionMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */