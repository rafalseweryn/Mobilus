package com.google.android.gms.common.api;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.zzh;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;

public class AvailabilityException extends Exception {
  private final ArrayMap<zzh<?>, ConnectionResult> zzcc;
  
  public AvailabilityException(ArrayMap<zzh<?>, ConnectionResult> paramArrayMap) {
    this.zzcc = paramArrayMap;
  }
  
  public ConnectionResult getConnectionResult(GoogleApi<? extends Api.ApiOptions> paramGoogleApi) {
    boolean bool;
    zzh<? extends Api.ApiOptions> zzh = paramGoogleApi.zzm();
    if (this.zzcc.get(zzh) != null) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "The given API was not part of the availability request.");
    return (ConnectionResult)this.zzcc.get(zzh);
  }
  
  public String getMessage() {
    String str;
    ArrayList<String> arrayList = new ArrayList();
    Iterator<zzh> iterator = this.zzcc.keySet().iterator();
    boolean bool = true;
    while (iterator.hasNext()) {
      zzh zzh = iterator.next();
      ConnectionResult connectionResult = (ConnectionResult)this.zzcc.get(zzh);
      if (connectionResult.isSuccess())
        bool = false; 
      String str1 = zzh.zzq();
      String str2 = String.valueOf(connectionResult);
      StringBuilder stringBuilder1 = new StringBuilder(String.valueOf(str1).length() + 2 + String.valueOf(str2).length());
      stringBuilder1.append(str1);
      stringBuilder1.append(": ");
      stringBuilder1.append(str2);
      arrayList.add(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    if (bool) {
      str = "None of the queried APIs are available. ";
    } else {
      str = "Some of the queried APIs are unavailable. ";
    } 
    stringBuilder.append(str);
    stringBuilder.append(TextUtils.join("; ", arrayList));
    return stringBuilder.toString();
  }
  
  public final ArrayMap<zzh<?>, ConnectionResult> zzl() {
    return this.zzcc;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\AvailabilityException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */