package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

final class zzcj extends Handler {
  public zzcj(zzch paramzzch, Looper paramLooper) {
    super(paramLooper);
  }
  
  public final void handleMessage(Message paramMessage) {
    StringBuilder stringBuilder;
    String str;
    int i;
    RuntimeException runtimeException;
    switch (paramMessage.what) {
      default:
        i = paramMessage.what;
        stringBuilder = new StringBuilder(70);
        stringBuilder.append("TransformationResultHandler received unknown message type: ");
        stringBuilder.append(i);
        Log.e("TransformedResultImpl", stringBuilder.toString());
        return;
      case 1:
        runtimeException = (RuntimeException)((Message)stringBuilder).obj;
        str = String.valueOf(runtimeException.getMessage());
        if (str.length() != 0) {
          str = "Runtime exception on the transformation worker thread: ".concat(str);
        } else {
          str = new String("Runtime exception on the transformation worker thread: ");
        } 
        Log.e("TransformedResultImpl", str);
        throw runtimeException;
      case 0:
        break;
    } 
    PendingResult pendingResult = (PendingResult)((Message)str).obj;
    Object object = zzch.zzf(this.zzml);
    /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
    if (pendingResult == null) {
      try {
        zzch zzch1 = zzch.zzg(this.zzml);
        Status status = new Status();
        this(13, "Transform returned null");
        zzch.zza(zzch1, status);
      } finally {}
    } else {
      Status status;
      zzch zzch1;
      if (pendingResult instanceof zzbx) {
        zzch1 = zzch.zzg(this.zzml);
        status = ((zzbx)pendingResult).getStatus();
      } else {
        zzch.zzg(this.zzml).zza((PendingResult<?>)status);
        /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
      } 
      zzch.zza(zzch1, status);
    } 
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzcj.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */