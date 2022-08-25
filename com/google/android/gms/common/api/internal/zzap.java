package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.Api;
import java.util.ArrayList;

final class zzap extends zzat {
  private final ArrayList<Api.Client> zzib;
  
  public zzap(zzaj paramzzaj, ArrayList<Api.Client> paramArrayList) {
    super(paramzzaj, null);
    this.zzib = paramArrayList;
  }
  
  @WorkerThread
  public final void zzaq() {
    (zzaj.zzd(this.zzhv)).zzfq.zzim = zzaj.zzg(this.zzhv);
    ArrayList<Api.Client> arrayList = this.zzib;
    int i = arrayList.size();
    byte b = 0;
    while (b < i) {
      Api.Client client = (Api.Client)arrayList.get(b);
      b++;
      ((Api.Client)client).getRemoteService(zzaj.zzh(this.zzhv), (zzaj.zzd(this.zzhv)).zzfq.zzim);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */