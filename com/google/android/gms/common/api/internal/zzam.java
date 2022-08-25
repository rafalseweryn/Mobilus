package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import java.util.ArrayList;
import java.util.Map;

final class zzam extends zzat {
  private final Map<Api.Client, zzal> zzhx;
  
  public zzam(zzaj paramzzaj, Map<Api.Client, zzal> paramMap) {
    super(paramzzaj, null);
    this.zzhx = paramMap;
  }
  
  @WorkerThread
  public final void zzaq() {
    ConnectionResult connectionResult;
    Api.Client client;
    GoogleApiAvailabilityCache googleApiAvailabilityCache = new GoogleApiAvailabilityCache(zzaj.zzb(this.zzhv));
    ArrayList<Api.Client> arrayList1 = new ArrayList();
    ArrayList<Api.Client> arrayList2 = new ArrayList();
    for (Api.Client client1 : this.zzhx.keySet()) {
      if (client1.requiresGooglePlayServices() && !zzal.zza(this.zzhx.get(client1))) {
        arrayList1.add(client1);
        continue;
      } 
      arrayList2.add(client1);
    } 
    int i = -1;
    boolean bool = arrayList1.isEmpty();
    int j = 0;
    int k = 0;
    if (bool) {
      ArrayList<Api.Client> arrayList = arrayList2;
      int m = arrayList.size();
      while (k < m) {
        arrayList1 = (ArrayList<Api.Client>)arrayList.get(k);
        k++;
        client = (Api.Client)arrayList1;
        j = googleApiAvailabilityCache.getClientAvailability(zzaj.zza(this.zzhv), client);
        i = j;
        if (j == 0) {
          i = j;
          break;
        } 
      } 
    } else {
      ArrayList<Api.Client> arrayList = (ArrayList)client;
      int m = arrayList.size();
      k = j;
      while (k < m) {
        client = arrayList.get(k);
        k++;
        client = client;
        j = googleApiAvailabilityCache.getClientAvailability(zzaj.zza(this.zzhv), client);
        i = j;
        if (j != 0) {
          i = j;
          break;
        } 
      } 
    } 
    if (i != 0) {
      connectionResult = new ConnectionResult(i, null);
      zzaj.zzd(this.zzhv).zza(new zzan(this, this.zzhv, connectionResult));
      return;
    } 
    if (zzaj.zze(this.zzhv))
      zzaj.zzf(this.zzhv).connect(); 
    for (Api.Client client1 : this.zzhx.keySet()) {
      BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks = this.zzhx.get(client1);
      if (client1.requiresGooglePlayServices() && connectionResult.getClientAvailability(zzaj.zza(this.zzhv), client1) != 0) {
        zzaj.zzd(this.zzhv).zza(new zzao(this, this.zzhv, connectionProgressReportCallbacks));
        continue;
      } 
      client1.connect(connectionProgressReportCallbacks);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */