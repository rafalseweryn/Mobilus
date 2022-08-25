package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.internal.SignInResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public final class zzaj implements zzbc {
  private final Context mContext;
  
  private final Api.AbstractClientBuilder<? extends SignInClient, SignInOptions> zzdh;
  
  private final Lock zzga;
  
  private final ClientSettings zzgf;
  
  private final Map<Api<?>, Boolean> zzgi;
  
  private final GoogleApiAvailabilityLight zzgk;
  
  private ConnectionResult zzgt;
  
  private final zzbd zzhf;
  
  private int zzhi;
  
  private int zzhj = 0;
  
  private int zzhk;
  
  private final Bundle zzhl = new Bundle();
  
  private final Set<Api.AnyClientKey> zzhm = new HashSet<>();
  
  private SignInClient zzhn;
  
  private boolean zzho;
  
  private boolean zzhp;
  
  private boolean zzhq;
  
  private IAccountAccessor zzhr;
  
  private boolean zzhs;
  
  private boolean zzht;
  
  private ArrayList<Future<?>> zzhu = new ArrayList<>();
  
  public zzaj(zzbd paramzzbd, ClientSettings paramClientSettings, Map<Api<?>, Boolean> paramMap, GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight, Api.AbstractClientBuilder<? extends SignInClient, SignInOptions> paramAbstractClientBuilder, Lock paramLock, Context paramContext) {
    this.zzhf = paramzzbd;
    this.zzgf = paramClientSettings;
    this.zzgi = paramMap;
    this.zzgk = paramGoogleApiAvailabilityLight;
    this.zzdh = paramAbstractClientBuilder;
    this.zzga = paramLock;
    this.mContext = paramContext;
  }
  
  @GuardedBy("mLock")
  private final void zza(SignInResponse paramSignInResponse) {
    String str;
    if (!zze(0))
      return; 
    ConnectionResult connectionResult = paramSignInResponse.getConnectionResult();
    if (connectionResult.isSuccess()) {
      ResolveAccountResponse resolveAccountResponse = paramSignInResponse.getResolveAccountResponse();
      ConnectionResult connectionResult1 = resolveAccountResponse.getConnectionResult();
      if (!connectionResult1.isSuccess()) {
        str = String.valueOf(connectionResult1);
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 48);
        stringBuilder.append("Sign-in succeeded with resolve account failure: ");
        stringBuilder.append(str);
        Log.wtf("GoogleApiClientConnecting", stringBuilder.toString(), new Exception());
        zze(connectionResult1);
        return;
      } 
      this.zzhq = true;
      this.zzhr = str.getAccountAccessor();
      this.zzhs = str.getSaveDefaultAccount();
      this.zzht = str.isFromCrossClientAuth();
      zzas();
      return;
    } 
    if (zzd((ConnectionResult)str)) {
      zzau();
      zzas();
      return;
    } 
    zze((ConnectionResult)str);
  }
  
  private final void zza(boolean paramBoolean) {
    if (this.zzhn != null) {
      if (this.zzhn.isConnected() && paramBoolean)
        this.zzhn.clearAccountFromSessionStore(); 
      this.zzhn.disconnect();
      this.zzhr = null;
    } 
  }
  
  @GuardedBy("mLock")
  private final boolean zzar() {
    this.zzhk--;
    if (this.zzhk > 0)
      return false; 
    if (this.zzhk < 0) {
      Log.w("GoogleApiClientConnecting", this.zzhf.zzfq.zzbb());
      Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
      ConnectionResult connectionResult = new ConnectionResult(8, null);
      zze(connectionResult);
      return false;
    } 
    if (this.zzgt != null) {
      this.zzhf.zzje = this.zzhi;
      ConnectionResult connectionResult = this.zzgt;
      zze(connectionResult);
      return false;
    } 
    return true;
  }
  
  @GuardedBy("mLock")
  private final void zzas() {
    if (this.zzhk != 0)
      return; 
    if (!this.zzhp || this.zzhq) {
      ArrayList<Api.Client> arrayList = new ArrayList();
      this.zzhj = 1;
      this.zzhk = this.zzhf.zzil.size();
      for (Api.AnyClientKey<?> anyClientKey : this.zzhf.zzil.keySet()) {
        if (this.zzhf.zzjb.containsKey(anyClientKey)) {
          if (zzar())
            zzat(); 
          continue;
        } 
        arrayList.add(this.zzhf.zzil.get(anyClientKey));
      } 
      if (!arrayList.isEmpty())
        this.zzhu.add(zzbg.zzbe().submit(new zzap(this, arrayList))); 
    } 
  }
  
  @GuardedBy("mLock")
  private final void zzat() {
    Bundle bundle;
    this.zzhf.zzbd();
    zzbg.zzbe().execute(new zzak(this));
    if (this.zzhn != null) {
      if (this.zzhs)
        this.zzhn.saveDefaultAccount(this.zzhr, this.zzht); 
      zza(false);
    } 
    for (Api.AnyClientKey<?> anyClientKey : this.zzhf.zzjb.keySet())
      ((Api.Client)this.zzhf.zzil.get(anyClientKey)).disconnect(); 
    if (this.zzhl.isEmpty()) {
      bundle = null;
    } else {
      bundle = this.zzhl;
    } 
    this.zzhf.zzjf.zzb(bundle);
  }
  
  @GuardedBy("mLock")
  private final void zzau() {
    this.zzhp = false;
    this.zzhf.zzfq.zzim = Collections.emptySet();
    for (Api.AnyClientKey<?> anyClientKey : this.zzhm) {
      if (!this.zzhf.zzjb.containsKey(anyClientKey))
        this.zzhf.zzjb.put(anyClientKey, new ConnectionResult(17, null)); 
    } 
  }
  
  private final void zzav() {
    ArrayList<Future<?>> arrayList = this.zzhu;
    int i = arrayList.size();
    byte b = 0;
    while (b < i) {
      Future future = (Future)arrayList.get(b);
      b++;
      ((Future)future).cancel(true);
    } 
    this.zzhu.clear();
  }
  
  private final Set<Scope> zzaw() {
    if (this.zzgf == null)
      return Collections.emptySet(); 
    HashSet<Scope> hashSet = new HashSet(this.zzgf.getRequiredScopes());
    Map map = this.zzgf.getOptionalApiSettings();
    for (Api api : map.keySet()) {
      if (!this.zzhf.zzjb.containsKey(api.getClientKey()))
        hashSet.addAll(((ClientSettings.OptionalApiSettings)map.get(api)).mScopes); 
    } 
    return hashSet;
  }
  
  @GuardedBy("mLock")
  private final void zzb(ConnectionResult paramConnectionResult, Api<?> paramApi, boolean paramBoolean) {
    // Byte code:
    //   0: aload_2
    //   1: invokevirtual zzj : ()Lcom/google/android/gms/common/api/Api$BaseClientBuilder;
    //   4: invokevirtual getPriority : ()I
    //   7: istore #4
    //   9: iconst_0
    //   10: istore #5
    //   12: iload_3
    //   13: ifeq -> 58
    //   16: aload_1
    //   17: invokevirtual hasResolution : ()Z
    //   20: ifeq -> 29
    //   23: iconst_1
    //   24: istore #6
    //   26: goto -> 49
    //   29: aload_0
    //   30: getfield zzgk : Lcom/google/android/gms/common/GoogleApiAvailabilityLight;
    //   33: aload_1
    //   34: invokevirtual getErrorCode : ()I
    //   37: invokevirtual getErrorResolutionIntent : (I)Landroid/content/Intent;
    //   40: ifnull -> 46
    //   43: goto -> 23
    //   46: iconst_0
    //   47: istore #6
    //   49: iload #5
    //   51: istore #7
    //   53: iload #6
    //   55: ifeq -> 81
    //   58: aload_0
    //   59: getfield zzgt : Lcom/google/android/gms/common/ConnectionResult;
    //   62: ifnull -> 78
    //   65: iload #5
    //   67: istore #7
    //   69: iload #4
    //   71: aload_0
    //   72: getfield zzhi : I
    //   75: if_icmpge -> 81
    //   78: iconst_1
    //   79: istore #7
    //   81: iload #7
    //   83: ifeq -> 97
    //   86: aload_0
    //   87: aload_1
    //   88: putfield zzgt : Lcom/google/android/gms/common/ConnectionResult;
    //   91: aload_0
    //   92: iload #4
    //   94: putfield zzhi : I
    //   97: aload_0
    //   98: getfield zzhf : Lcom/google/android/gms/common/api/internal/zzbd;
    //   101: getfield zzjb : Ljava/util/Map;
    //   104: aload_2
    //   105: invokevirtual getClientKey : ()Lcom/google/android/gms/common/api/Api$AnyClientKey;
    //   108: aload_1
    //   109: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   114: pop
    //   115: return
  }
  
  @GuardedBy("mLock")
  private final boolean zzd(ConnectionResult paramConnectionResult) {
    return (this.zzho && !paramConnectionResult.hasResolution());
  }
  
  @GuardedBy("mLock")
  private final void zze(ConnectionResult paramConnectionResult) {
    zzav();
    zza(paramConnectionResult.hasResolution() ^ true);
    this.zzhf.zzf(paramConnectionResult);
    this.zzhf.zzjf.zzc(paramConnectionResult);
  }
  
  @GuardedBy("mLock")
  private final boolean zze(int paramInt) {
    if (this.zzhj != paramInt) {
      Log.w("GoogleApiClientConnecting", this.zzhf.zzfq.zzbb());
      String str1 = String.valueOf(this);
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 23);
      stringBuilder.append("Unexpected callback in ");
      stringBuilder.append(str1);
      Log.w("GoogleApiClientConnecting", stringBuilder.toString());
      int i = this.zzhk;
      stringBuilder = new StringBuilder(33);
      stringBuilder.append("mRemainingConnections=");
      stringBuilder.append(i);
      Log.w("GoogleApiClientConnecting", stringBuilder.toString());
      str1 = zzf(this.zzhj);
      String str2 = zzf(paramInt);
      stringBuilder = new StringBuilder(String.valueOf(str1).length() + 70 + String.valueOf(str2).length());
      stringBuilder.append("GoogleApiClient connecting is in step ");
      stringBuilder.append(str1);
      stringBuilder.append(" but received callback for step ");
      stringBuilder.append(str2);
      Log.wtf("GoogleApiClientConnecting", stringBuilder.toString(), new Exception());
      zze(new ConnectionResult(8, null));
      return false;
    } 
    return true;
  }
  
  private static String zzf(int paramInt) {
    switch (paramInt) {
      default:
        return "UNKNOWN";
      case 1:
        return "STEP_GETTING_REMOTE_SERVICE";
      case 0:
        break;
    } 
    return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
  }
  
  public final void begin() {
    this.zzhf.zzjb.clear();
    this.zzhp = false;
    this.zzgt = null;
    this.zzhj = 0;
    this.zzho = true;
    this.zzhq = false;
    this.zzhs = false;
    HashMap<Object, Object> hashMap = new HashMap<>();
    Iterator<Api> iterator = this.zzgi.keySet().iterator();
    int i = 0;
    while (iterator.hasNext()) {
      byte b;
      Api<?> api = iterator.next();
      Api.Client client = this.zzhf.zzil.get(api.getClientKey());
      if (api.zzj().getPriority() == 1) {
        b = 1;
      } else {
        b = 0;
      } 
      i |= b;
      boolean bool = ((Boolean)this.zzgi.get(api)).booleanValue();
      if (client.requiresSignIn()) {
        this.zzhp = true;
        if (bool) {
          this.zzhm.add(api.getClientKey());
        } else {
          this.zzho = false;
        } 
      } 
      hashMap.put(client, new zzal(this, api, bool));
    } 
    if (i != 0)
      this.zzhp = false; 
    if (this.zzhp) {
      this.zzgf.setClientSessionId(Integer.valueOf(System.identityHashCode(this.zzhf.zzfq)));
      zzas zzas = new zzas(this, null);
      this.zzhn = (SignInClient)this.zzdh.buildClient(this.mContext, this.zzhf.zzfq.getLooper(), this.zzgf, this.zzgf.getSignInOptions(), zzas, zzas);
    } 
    this.zzhk = this.zzhf.zzil.size();
    this.zzhu.add(zzbg.zzbe().submit(new zzam(this, (Map)hashMap)));
  }
  
  public final void connect() {}
  
  public final boolean disconnect() {
    zzav();
    zza(true);
    this.zzhf.zzf(null);
    return true;
  }
  
  public final <A extends Api.AnyClient, R extends com.google.android.gms.common.api.Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T paramT) {
    this.zzhf.zzfq.zzgo.add((BaseImplementation.ApiMethodImpl<?, ?>)paramT);
    return paramT;
  }
  
  public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends com.google.android.gms.common.api.Result, A>> T execute(T paramT) {
    throw new IllegalStateException("GoogleApiClient is not connected yet.");
  }
  
  @GuardedBy("mLock")
  public final void onConnected(Bundle paramBundle) {
    if (!zze(1))
      return; 
    if (paramBundle != null)
      this.zzhl.putAll(paramBundle); 
    if (zzar())
      zzat(); 
  }
  
  @GuardedBy("mLock")
  public final void onConnectionSuspended(int paramInt) {
    zze(new ConnectionResult(8, null));
  }
  
  @GuardedBy("mLock")
  public final void zza(ConnectionResult paramConnectionResult, Api<?> paramApi, boolean paramBoolean) {
    if (!zze(1))
      return; 
    zzb(paramConnectionResult, paramApi, paramBoolean);
    if (zzar())
      zzat(); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzaj.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */