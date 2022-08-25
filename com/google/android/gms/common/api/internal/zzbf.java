package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

final class zzbf extends Handler {
  zzbf(zzbd paramzzbd, Looper paramLooper) {
    super(paramLooper);
  }
  
  public final void handleMessage(Message paramMessage) {
    StringBuilder stringBuilder;
    int i;
    switch (paramMessage.what) {
      default:
        i = paramMessage.what;
        stringBuilder = new StringBuilder(31);
        stringBuilder.append("Unknown message id: ");
        stringBuilder.append(i);
        Log.w("GACStateManager", stringBuilder.toString());
        return;
      case 2:
        throw (RuntimeException)stringBuilder.obj;
      case 1:
        break;
    } 
    ((zzbe)((Message)stringBuilder).obj).zzc(this.zzjh);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzbf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */