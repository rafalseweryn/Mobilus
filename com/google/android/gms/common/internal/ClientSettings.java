package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.support.v4.util.ArraySet;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInOptions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@VisibleForTesting
public final class ClientSettings {
  public static final String KEY_CLIENT_SESSION_ID = "com.google.android.gms.common.internal.ClientSettings.sessionId";
  
  private final Set<Scope> zzcv;
  
  private final int zzcx;
  
  private final View zzcy;
  
  private final String zzcz;
  
  private final String zzda;
  
  private final Set<Scope> zzrz;
  
  private final Account zzs;
  
  private final Map<Api<?>, OptionalApiSettings> zzsa;
  
  private final SignInOptions zzsb;
  
  private Integer zzsc;
  
  public ClientSettings(Account paramAccount, Set<Scope> paramSet, Map<Api<?>, OptionalApiSettings> paramMap, int paramInt, View paramView, String paramString1, String paramString2, SignInOptions paramSignInOptions) {
    Set<Scope> set;
    this.zzs = paramAccount;
    if (paramSet == null) {
      set = Collections.EMPTY_SET;
    } else {
      set = Collections.unmodifiableSet(paramSet);
    } 
    this.zzcv = set;
    Map<Api<?>, OptionalApiSettings> map = paramMap;
    if (paramMap == null)
      map = Collections.EMPTY_MAP; 
    this.zzsa = map;
    this.zzcy = paramView;
    this.zzcx = paramInt;
    this.zzcz = paramString1;
    this.zzda = paramString2;
    this.zzsb = paramSignInOptions;
    paramSet = new HashSet<>(this.zzcv);
    Iterator iterator = this.zzsa.values().iterator();
    while (iterator.hasNext())
      paramSet.addAll(((OptionalApiSettings)iterator.next()).mScopes); 
    this.zzrz = Collections.unmodifiableSet(paramSet);
  }
  
  public static ClientSettings createDefault(Context paramContext) {
    return (new GoogleApiClient.Builder(paramContext)).buildClientSettings();
  }
  
  @Nullable
  public final Account getAccount() {
    return this.zzs;
  }
  
  @Deprecated
  @Nullable
  public final String getAccountName() {
    return (this.zzs != null) ? this.zzs.name : null;
  }
  
  public final Account getAccountOrDefault() {
    return (this.zzs != null) ? this.zzs : new Account("<<default account>>", "com.google");
  }
  
  public final Set<Scope> getAllRequestedScopes() {
    return this.zzrz;
  }
  
  public final Set<Scope> getApplicableScopes(Api<?> paramApi) {
    OptionalApiSettings optionalApiSettings = this.zzsa.get(paramApi);
    if (optionalApiSettings == null || optionalApiSettings.mScopes.isEmpty())
      return this.zzcv; 
    HashSet<Scope> hashSet = new HashSet<>(this.zzcv);
    hashSet.addAll(optionalApiSettings.mScopes);
    return hashSet;
  }
  
  @Nullable
  public final Integer getClientSessionId() {
    return this.zzsc;
  }
  
  public final int getGravityForPopups() {
    return this.zzcx;
  }
  
  public final Map<Api<?>, OptionalApiSettings> getOptionalApiSettings() {
    return this.zzsa;
  }
  
  @Nullable
  public final String getRealClientClassName() {
    return this.zzda;
  }
  
  @Nullable
  public final String getRealClientPackageName() {
    return this.zzcz;
  }
  
  public final Set<Scope> getRequiredScopes() {
    return this.zzcv;
  }
  
  @Nullable
  public final SignInOptions getSignInOptions() {
    return this.zzsb;
  }
  
  @Nullable
  public final View getViewForPopups() {
    return this.zzcy;
  }
  
  public final void setClientSessionId(Integer paramInteger) {
    this.zzsc = paramInteger;
  }
  
  public static final class Builder {
    private int zzcx = 0;
    
    private View zzcy;
    
    private String zzcz;
    
    private String zzda;
    
    private Account zzs;
    
    private Map<Api<?>, ClientSettings.OptionalApiSettings> zzsa;
    
    private SignInOptions zzsb = SignInOptions.DEFAULT;
    
    private ArraySet<Scope> zzsd;
    
    public final Builder addAllRequiredScopes(Collection<Scope> param1Collection) {
      if (this.zzsd == null)
        this.zzsd = new ArraySet(); 
      this.zzsd.addAll(param1Collection);
      return this;
    }
    
    public final Builder addRequiredScope(Scope param1Scope) {
      if (this.zzsd == null)
        this.zzsd = new ArraySet(); 
      this.zzsd.add(param1Scope);
      return this;
    }
    
    public final ClientSettings build() {
      return new ClientSettings(this.zzs, (Set<Scope>)this.zzsd, this.zzsa, this.zzcx, this.zzcy, this.zzcz, this.zzda, this.zzsb);
    }
    
    public final Builder setAccount(Account param1Account) {
      this.zzs = param1Account;
      return this;
    }
    
    public final Builder setGravityForPopups(int param1Int) {
      this.zzcx = param1Int;
      return this;
    }
    
    public final Builder setOptionalApiSettingsMap(Map<Api<?>, ClientSettings.OptionalApiSettings> param1Map) {
      this.zzsa = param1Map;
      return this;
    }
    
    public final Builder setRealClientClassName(String param1String) {
      this.zzda = param1String;
      return this;
    }
    
    public final Builder setRealClientPackageName(String param1String) {
      this.zzcz = param1String;
      return this;
    }
    
    public final Builder setSignInOptions(SignInOptions param1SignInOptions) {
      this.zzsb = param1SignInOptions;
      return this;
    }
    
    public final Builder setViewForPopups(View param1View) {
      this.zzcy = param1View;
      return this;
    }
  }
  
  public static final class OptionalApiSettings {
    public final Set<Scope> mScopes;
    
    public OptionalApiSettings(Set<Scope> param1Set) {
      Preconditions.checkNotNull(param1Set);
      this.mScopes = Collections.unmodifiableSet(param1Set);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\ClientSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */