package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.auth.api.signin.internal.HashAccumulator;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "GoogleSignInOptionsCreator")
public class GoogleSignInOptions extends AbstractSafeParcelable implements Api.ApiOptions.Optional, ReflectedParcelable {
  public static final Parcelable.Creator<GoogleSignInOptions> CREATOR;
  
  public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN;
  
  public static final GoogleSignInOptions DEFAULT_SIGN_IN;
  
  @VisibleForTesting
  public static final Scope SCOPE_EMAIL;
  
  @VisibleForTesting
  public static final Scope SCOPE_GAMES;
  
  @VisibleForTesting
  public static final Scope SCOPE_GAMES_LITE;
  
  @VisibleForTesting
  public static final Scope SCOPE_OPEN_ID;
  
  @VisibleForTesting
  public static final Scope SCOPE_PROFILE = new Scope("profile");
  
  private static Comparator<Scope> zzaa;
  
  @VersionField(id = 1)
  private final int versionCode;
  
  @Field(getter = "getScopes", id = 2)
  private final ArrayList<Scope> zzr;
  
  @Field(getter = "getAccount", id = 3)
  private Account zzs;
  
  @Field(getter = "isIdTokenRequested", id = 4)
  private boolean zzt;
  
  @Field(getter = "isServerAuthCodeRequested", id = 5)
  private final boolean zzu;
  
  @Field(getter = "isForceCodeForRefreshToken", id = 6)
  private final boolean zzv;
  
  @Field(getter = "getServerClientId", id = 7)
  private String zzw;
  
  @Field(getter = "getHostedDomain", id = 8)
  private String zzx;
  
  @Field(getter = "getExtensions", id = 9)
  private ArrayList<GoogleSignInOptionsExtensionParcelable> zzy;
  
  private Map<Integer, GoogleSignInOptionsExtensionParcelable> zzz;
  
  static {
    SCOPE_EMAIL = new Scope("email");
    SCOPE_OPEN_ID = new Scope("openid");
    SCOPE_GAMES_LITE = new Scope("https://www.googleapis.com/auth/games_lite");
    SCOPE_GAMES = new Scope("https://www.googleapis.com/auth/games");
    DEFAULT_SIGN_IN = (new Builder()).requestId().requestProfile().build();
    DEFAULT_GAMES_SIGN_IN = (new Builder()).requestScopes(SCOPE_GAMES_LITE, new Scope[0]).build();
    CREATOR = new GoogleSignInOptionsCreator();
    zzaa = new zzb();
  }
  
  @Constructor
  GoogleSignInOptions(@Param(id = 1) int paramInt, @Param(id = 2) ArrayList<Scope> paramArrayList, @Param(id = 3) Account paramAccount, @Param(id = 4) boolean paramBoolean1, @Param(id = 5) boolean paramBoolean2, @Param(id = 6) boolean paramBoolean3, @Param(id = 7) String paramString1, @Param(id = 8) String paramString2, @Param(id = 9) ArrayList<GoogleSignInOptionsExtensionParcelable> paramArrayList1) {
    this(paramInt, paramArrayList, paramAccount, paramBoolean1, paramBoolean2, paramBoolean3, paramString1, paramString2, zza(paramArrayList1));
  }
  
  private GoogleSignInOptions(int paramInt, ArrayList<Scope> paramArrayList, Account paramAccount, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString1, String paramString2, Map<Integer, GoogleSignInOptionsExtensionParcelable> paramMap) {
    this.versionCode = paramInt;
    this.zzr = paramArrayList;
    this.zzs = paramAccount;
    this.zzt = paramBoolean1;
    this.zzu = paramBoolean2;
    this.zzv = paramBoolean3;
    this.zzw = paramString1;
    this.zzx = paramString2;
    this.zzy = new ArrayList<>(paramMap.values());
    this.zzz = paramMap;
  }
  
  @Nullable
  public static GoogleSignInOptions fromJsonString(@Nullable String paramString) throws JSONException {
    if (TextUtils.isEmpty(paramString))
      return null; 
    JSONObject jSONObject = new JSONObject(paramString);
    HashSet<Scope> hashSet = new HashSet();
    JSONArray jSONArray = jSONObject.getJSONArray("scopes");
    int i = jSONArray.length();
    for (byte b = 0; b < i; b++)
      hashSet.add(new Scope(jSONArray.getString(b))); 
    String str = jSONObject.optString("accountName", null);
    if (!TextUtils.isEmpty(str)) {
      Account account = new Account(str, "com.google");
    } else {
      str = null;
    } 
    return new GoogleSignInOptions(3, new ArrayList<>(hashSet), (Account)str, jSONObject.getBoolean("idTokenRequested"), jSONObject.getBoolean("serverAuthRequested"), jSONObject.getBoolean("forceCodeForRefreshToken"), jSONObject.optString("serverClientId", null), jSONObject.optString("hostedDomain", null), new HashMap<>());
  }
  
  private static Map<Integer, GoogleSignInOptionsExtensionParcelable> zza(@Nullable List<GoogleSignInOptionsExtensionParcelable> paramList) {
    HashMap<Object, Object> hashMap = new HashMap<>();
    if (paramList == null)
      return (Map)hashMap; 
    for (GoogleSignInOptionsExtensionParcelable googleSignInOptionsExtensionParcelable : paramList)
      hashMap.put(Integer.valueOf(googleSignInOptionsExtensionParcelable.getType()), googleSignInOptionsExtensionParcelable); 
    return (Map)hashMap;
  }
  
  private final JSONObject zza() {
    JSONObject jSONObject = new JSONObject();
    try {
      JSONArray jSONArray = new JSONArray();
      this();
      Collections.sort(this.zzr, zzaa);
      ArrayList<Scope> arrayList = this.zzr;
      int i = arrayList.size();
      byte b = 0;
      while (b < i) {
        Scope scope = (Scope)arrayList.get(b);
        b++;
        jSONArray.put(((Scope)scope).getScopeUri());
      } 
      jSONObject.put("scopes", jSONArray);
      if (this.zzs != null)
        jSONObject.put("accountName", this.zzs.name); 
      jSONObject.put("idTokenRequested", this.zzt);
      jSONObject.put("forceCodeForRefreshToken", this.zzv);
      jSONObject.put("serverAuthRequested", this.zzu);
      if (!TextUtils.isEmpty(this.zzw))
        jSONObject.put("serverClientId", this.zzw); 
      if (!TextUtils.isEmpty(this.zzx))
        jSONObject.put("hostedDomain", this.zzx); 
      return jSONObject;
    } catch (JSONException jSONException) {
      throw new RuntimeException(jSONException);
    } 
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == null)
      return false; 
    try {
      paramObject = paramObject;
      if (this.zzy.size() <= 0) {
        if (((GoogleSignInOptions)paramObject).zzy.size() > 0)
          return false; 
        if (this.zzr.size() == paramObject.getScopes().size()) {
          if (!this.zzr.containsAll(paramObject.getScopes()))
            return false; 
          if (((this.zzs == null) ? (paramObject.getAccount() == null) : this.zzs.equals(paramObject.getAccount())) && (TextUtils.isEmpty(this.zzw) ? TextUtils.isEmpty(paramObject.getServerClientId()) : this.zzw.equals(paramObject.getServerClientId())) && this.zzv == paramObject.isForceCodeForRefreshToken() && this.zzt == paramObject.isIdTokenRequested()) {
            boolean bool1 = this.zzu;
            boolean bool2 = paramObject.isServerAuthCodeRequested();
            if (bool1 == bool2)
              return true; 
          } 
        } 
      } 
    } catch (ClassCastException classCastException) {}
    return false;
  }
  
  public Account getAccount() {
    return this.zzs;
  }
  
  public GoogleSignInOptionsExtensionParcelable getExtension(@TypeId int paramInt) {
    return this.zzz.get(Integer.valueOf(paramInt));
  }
  
  public ArrayList<GoogleSignInOptionsExtensionParcelable> getExtensions() {
    return this.zzy;
  }
  
  public String getHostedDomain() {
    return this.zzx;
  }
  
  public Scope[] getScopeArray() {
    return this.zzr.<Scope>toArray(new Scope[this.zzr.size()]);
  }
  
  public ArrayList<Scope> getScopes() {
    return new ArrayList<>(this.zzr);
  }
  
  public String getServerClientId() {
    return this.zzw;
  }
  
  public boolean hasExtension(@TypeId int paramInt) {
    return this.zzz.containsKey(Integer.valueOf(paramInt));
  }
  
  public int hashCode() {
    ArrayList<String> arrayList = new ArrayList();
    ArrayList<Scope> arrayList1 = this.zzr;
    int i = arrayList1.size();
    byte b = 0;
    while (b < i) {
      Scope scope = (Scope)arrayList1.get(b);
      b++;
      arrayList.add(((Scope)scope).getScopeUri());
    } 
    Collections.sort(arrayList);
    return (new HashAccumulator()).addObject(arrayList).addObject(this.zzs).addObject(this.zzw).addBoolean(this.zzv).addBoolean(this.zzt).addBoolean(this.zzu).hash();
  }
  
  public boolean isForceCodeForRefreshToken() {
    return this.zzv;
  }
  
  public boolean isIdTokenRequested() {
    return this.zzt;
  }
  
  public boolean isServerAuthCodeRequested() {
    return this.zzu;
  }
  
  public String toJson() {
    return zza().toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.versionCode);
    SafeParcelWriter.writeTypedList(paramParcel, 2, getScopes(), false);
    SafeParcelWriter.writeParcelable(paramParcel, 3, (Parcelable)getAccount(), paramInt, false);
    SafeParcelWriter.writeBoolean(paramParcel, 4, isIdTokenRequested());
    SafeParcelWriter.writeBoolean(paramParcel, 5, isServerAuthCodeRequested());
    SafeParcelWriter.writeBoolean(paramParcel, 6, isForceCodeForRefreshToken());
    SafeParcelWriter.writeString(paramParcel, 7, getServerClientId(), false);
    SafeParcelWriter.writeString(paramParcel, 8, getHostedDomain(), false);
    SafeParcelWriter.writeTypedList(paramParcel, 9, getExtensions(), false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
  
  public static final class Builder {
    private Set<Scope> mScopes = new HashSet<>();
    
    private Map<Integer, GoogleSignInOptionsExtensionParcelable> zzab = new HashMap<>();
    
    private Account zzs;
    
    private boolean zzt;
    
    private boolean zzu;
    
    private boolean zzv;
    
    private String zzw;
    
    private String zzx;
    
    public Builder() {}
    
    public Builder(@NonNull GoogleSignInOptions param1GoogleSignInOptions) {
      Preconditions.checkNotNull(param1GoogleSignInOptions);
      this.mScopes = new HashSet<>(GoogleSignInOptions.zza(param1GoogleSignInOptions));
      this.zzu = GoogleSignInOptions.zzb(param1GoogleSignInOptions);
      this.zzv = GoogleSignInOptions.zzc(param1GoogleSignInOptions);
      this.zzt = GoogleSignInOptions.zzd(param1GoogleSignInOptions);
      this.zzw = GoogleSignInOptions.zze(param1GoogleSignInOptions);
      this.zzs = GoogleSignInOptions.zzf(param1GoogleSignInOptions);
      this.zzx = GoogleSignInOptions.zzg(param1GoogleSignInOptions);
      this.zzab = GoogleSignInOptions.zzb(GoogleSignInOptions.zzh(param1GoogleSignInOptions));
    }
    
    private final String zza(String param1String) {
      Preconditions.checkNotEmpty(param1String);
      if (this.zzw == null || this.zzw.equals(param1String)) {
        boolean bool1 = true;
        Preconditions.checkArgument(bool1, "two different server client ids provided");
        return param1String;
      } 
      boolean bool = false;
      Preconditions.checkArgument(bool, "two different server client ids provided");
      return param1String;
    }
    
    public final Builder addExtension(GoogleSignInOptionsExtension param1GoogleSignInOptionsExtension) {
      if (this.zzab.containsKey(Integer.valueOf(param1GoogleSignInOptionsExtension.getExtensionType())))
        throw new IllegalStateException("Only one extension per type may be added"); 
      if (param1GoogleSignInOptionsExtension.getImpliedScopes() != null)
        this.mScopes.addAll(param1GoogleSignInOptionsExtension.getImpliedScopes()); 
      this.zzab.put(Integer.valueOf(param1GoogleSignInOptionsExtension.getExtensionType()), new GoogleSignInOptionsExtensionParcelable(param1GoogleSignInOptionsExtension));
      return this;
    }
    
    public final GoogleSignInOptions build() {
      if (this.mScopes.contains(GoogleSignInOptions.SCOPE_GAMES) && this.mScopes.contains(GoogleSignInOptions.SCOPE_GAMES_LITE))
        this.mScopes.remove(GoogleSignInOptions.SCOPE_GAMES_LITE); 
      if (this.zzt && (this.zzs == null || !this.mScopes.isEmpty()))
        requestId(); 
      return new GoogleSignInOptions(3, new ArrayList<>(this.mScopes), this.zzs, this.zzt, this.zzu, this.zzv, this.zzw, this.zzx, this.zzab, null);
    }
    
    public final Builder requestEmail() {
      this.mScopes.add(GoogleSignInOptions.SCOPE_EMAIL);
      return this;
    }
    
    public final Builder requestId() {
      this.mScopes.add(GoogleSignInOptions.SCOPE_OPEN_ID);
      return this;
    }
    
    public final Builder requestIdToken(String param1String) {
      this.zzt = true;
      this.zzw = zza(param1String);
      return this;
    }
    
    public final Builder requestPhatIdToken(String param1String) {
      return requestIdToken(param1String).requestProfile().requestEmail();
    }
    
    public final Builder requestProfile() {
      this.mScopes.add(GoogleSignInOptions.SCOPE_PROFILE);
      return this;
    }
    
    public final Builder requestScopes(Scope param1Scope, Scope... param1VarArgs) {
      this.mScopes.add(param1Scope);
      this.mScopes.addAll(Arrays.asList(param1VarArgs));
      return this;
    }
    
    public final Builder requestServerAuthCode(String param1String) {
      return requestServerAuthCode(param1String, false);
    }
    
    public final Builder requestServerAuthCode(String param1String, boolean param1Boolean) {
      this.zzu = true;
      this.zzw = zza(param1String);
      this.zzv = param1Boolean;
      return this;
    }
    
    public final Builder setAccount(Account param1Account) {
      this.zzs = (Account)Preconditions.checkNotNull(param1Account);
      return this;
    }
    
    public final Builder setAccountName(String param1String) {
      this.zzs = new Account(Preconditions.checkNotEmpty(param1String), "com.google");
      return this;
    }
    
    public final Builder setHostedDomain(String param1String) {
      this.zzx = Preconditions.checkNotEmpty(param1String);
      return this;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\auth\api\signin\GoogleSignInOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */