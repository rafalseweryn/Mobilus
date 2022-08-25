package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "GoogleSignInAccountCreator")
public class GoogleSignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {
  public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new GoogleSignInAccountCreator();
  
  @VisibleForTesting
  public static Clock sClock = DefaultClock.getInstance();
  
  @VersionField(id = 1)
  private final int versionCode;
  
  @Field(getter = "getId", id = 2)
  private String zze;
  
  @Field(getter = "getIdToken", id = 3)
  private String zzf;
  
  @Field(getter = "getEmail", id = 4)
  private String zzg;
  
  @Field(getter = "getDisplayName", id = 5)
  private String zzh;
  
  @Field(getter = "getPhotoUrl", id = 6)
  private Uri zzi;
  
  @Field(getter = "getServerAuthCode", id = 7)
  private String zzj;
  
  @Field(getter = "getExpirationTimeSecs", id = 8)
  private long zzk;
  
  @Field(getter = "getObfuscatedIdentifier", id = 9)
  private String zzl;
  
  @Field(id = 10)
  private List<Scope> zzm;
  
  @Field(getter = "getGivenName", id = 11)
  private String zzn;
  
  @Field(getter = "getFamilyName", id = 12)
  private String zzo;
  
  private Set<Scope> zzp = new HashSet<>();
  
  @Constructor
  GoogleSignInAccount(@Param(id = 1) int paramInt, @Param(id = 2) String paramString1, @Param(id = 3) String paramString2, @Param(id = 4) String paramString3, @Param(id = 5) String paramString4, @Param(id = 6) Uri paramUri, @Param(id = 7) String paramString5, @Param(id = 8) long paramLong, @Param(id = 9) String paramString6, @Param(id = 10) List<Scope> paramList, @Param(id = 11) String paramString7, @Param(id = 12) String paramString8) {
    this.versionCode = paramInt;
    this.zze = paramString1;
    this.zzf = paramString2;
    this.zzg = paramString3;
    this.zzh = paramString4;
    this.zzi = paramUri;
    this.zzj = paramString5;
    this.zzk = paramLong;
    this.zzl = paramString6;
    this.zzm = paramList;
    this.zzn = paramString7;
    this.zzo = paramString8;
  }
  
  public static GoogleSignInAccount create(@Nullable String paramString1, @Nullable String paramString2, @Nullable String paramString3, @Nullable String paramString4, @Nullable Uri paramUri, @Nullable Long paramLong, @NonNull String paramString5, @NonNull Set<Scope> paramSet) {
    return create(paramString1, paramString2, paramString3, paramString4, null, null, paramUri, paramLong, paramString5, paramSet);
  }
  
  public static GoogleSignInAccount create(@Nullable String paramString1, @Nullable String paramString2, @Nullable String paramString3, @Nullable String paramString4, @Nullable String paramString5, @Nullable String paramString6, @Nullable Uri paramUri, @Nullable Long paramLong, @NonNull String paramString7, @NonNull Set<Scope> paramSet) {
    if (paramLong == null)
      paramLong = Long.valueOf(sClock.currentTimeMillis() / 1000L); 
    return new GoogleSignInAccount(3, paramString1, paramString2, paramString3, paramString4, paramUri, null, paramLong.longValue(), Preconditions.checkNotEmpty(paramString7), new ArrayList<>((Collection<? extends Scope>)Preconditions.checkNotNull(paramSet)), paramString5, paramString6);
  }
  
  public static GoogleSignInAccount createDefault() {
    return zza(new Account("<<default account>>", "com.google"), new HashSet<>());
  }
  
  public static GoogleSignInAccount fromAccountAndScopes(@NonNull Account paramAccount, @NonNull Scope paramScope, Scope... paramVarArgs) {
    Preconditions.checkNotNull(paramAccount);
    Preconditions.checkNotNull(paramScope);
    HashSet<Scope> hashSet = new HashSet();
    hashSet.add(paramScope);
    hashSet.addAll(Arrays.asList(paramVarArgs));
    return zza(paramAccount, hashSet);
  }
  
  @Nullable
  public static GoogleSignInAccount fromJsonString(@Nullable String paramString) throws JSONException {
    if (TextUtils.isEmpty(paramString))
      return null; 
    JSONObject jSONObject = new JSONObject(paramString);
    paramString = jSONObject.optString("photoUrl", null);
    if (!TextUtils.isEmpty(paramString)) {
      Uri uri = Uri.parse(paramString);
    } else {
      paramString = null;
    } 
    long l = Long.parseLong(jSONObject.getString("expirationTime"));
    HashSet<Scope> hashSet = new HashSet();
    JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
    int i = jSONArray.length();
    for (byte b = 0; b < i; b++)
      hashSet.add(new Scope(jSONArray.getString(b))); 
    return create(jSONObject.optString("id"), jSONObject.optString("tokenId", null), jSONObject.optString("email", null), jSONObject.optString("displayName", null), jSONObject.optString("givenName", null), jSONObject.optString("familyName", null), (Uri)paramString, Long.valueOf(l), jSONObject.getString("obfuscatedIdentifier"), hashSet).setServerAuthCode(jSONObject.optString("serverAuthCode", null));
  }
  
  private static GoogleSignInAccount zza(Account paramAccount, Set<Scope> paramSet) {
    return create(null, null, paramAccount.name, null, null, null, null, Long.valueOf(0L), paramAccount.name, paramSet);
  }
  
  private final JSONObject zza() {
    JSONObject jSONObject = new JSONObject();
    try {
      if (getId() != null)
        jSONObject.put("id", getId()); 
      if (getIdToken() != null)
        jSONObject.put("tokenId", getIdToken()); 
      if (getEmail() != null)
        jSONObject.put("email", getEmail()); 
      if (getDisplayName() != null)
        jSONObject.put("displayName", getDisplayName()); 
      if (getGivenName() != null)
        jSONObject.put("givenName", getGivenName()); 
      if (getFamilyName() != null)
        jSONObject.put("familyName", getFamilyName()); 
      if (getPhotoUrl() != null)
        jSONObject.put("photoUrl", getPhotoUrl().toString()); 
      if (getServerAuthCode() != null)
        jSONObject.put("serverAuthCode", getServerAuthCode()); 
      jSONObject.put("expirationTime", this.zzk);
      jSONObject.put("obfuscatedIdentifier", getObfuscatedIdentifier());
      JSONArray jSONArray = new JSONArray();
      this();
      Scope[] arrayOfScope = this.zzm.<Scope>toArray(new Scope[this.zzm.size()]);
      Arrays.sort(arrayOfScope, zza.zzq);
      int i = arrayOfScope.length;
      for (byte b = 0; b < i; b++)
        jSONArray.put(arrayOfScope[b].getScopeUri()); 
      jSONObject.put("grantedScopes", jSONArray);
      return jSONObject;
    } catch (JSONException jSONException) {
      throw new RuntimeException(jSONException);
    } 
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof GoogleSignInAccount))
      return false; 
    paramObject = paramObject;
    return (paramObject.getObfuscatedIdentifier().equals(getObfuscatedIdentifier()) && paramObject.getRequestedScopes().equals(getRequestedScopes()));
  }
  
  @Nullable
  public Account getAccount() {
    return (this.zzg == null) ? null : new Account(this.zzg, "com.google");
  }
  
  @Nullable
  public String getDisplayName() {
    return this.zzh;
  }
  
  @Nullable
  public String getEmail() {
    return this.zzg;
  }
  
  public long getExpirationTimeSecs() {
    return this.zzk;
  }
  
  @Nullable
  public String getFamilyName() {
    return this.zzo;
  }
  
  @Nullable
  public String getGivenName() {
    return this.zzn;
  }
  
  @NonNull
  public Set<Scope> getGrantedScopes() {
    return new HashSet<>(this.zzm);
  }
  
  @Nullable
  public String getId() {
    return this.zze;
  }
  
  @Nullable
  public String getIdToken() {
    return this.zzf;
  }
  
  @NonNull
  public String getObfuscatedIdentifier() {
    return this.zzl;
  }
  
  @Nullable
  public Uri getPhotoUrl() {
    return this.zzi;
  }
  
  @NonNull
  public Set<Scope> getRequestedScopes() {
    HashSet<Scope> hashSet = new HashSet<>(this.zzm);
    hashSet.addAll(this.zzp);
    return hashSet;
  }
  
  @Nullable
  public String getServerAuthCode() {
    return this.zzj;
  }
  
  public int hashCode() {
    return (getObfuscatedIdentifier().hashCode() + 527) * 31 + getRequestedScopes().hashCode();
  }
  
  public boolean isExpired() {
    return (sClock.currentTimeMillis() / 1000L >= this.zzk - 300L);
  }
  
  public GoogleSignInAccount requestExtraScopes(Scope... paramVarArgs) {
    if (paramVarArgs != null)
      Collections.addAll(this.zzp, paramVarArgs); 
    return this;
  }
  
  public GoogleSignInAccount setServerAuthCode(String paramString) {
    this.zzj = paramString;
    return this;
  }
  
  public String toJson() {
    return zza().toString();
  }
  
  public String toJsonForStorage() {
    JSONObject jSONObject = zza();
    jSONObject.remove("serverAuthCode");
    return jSONObject.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.versionCode);
    SafeParcelWriter.writeString(paramParcel, 2, getId(), false);
    SafeParcelWriter.writeString(paramParcel, 3, getIdToken(), false);
    SafeParcelWriter.writeString(paramParcel, 4, getEmail(), false);
    SafeParcelWriter.writeString(paramParcel, 5, getDisplayName(), false);
    SafeParcelWriter.writeParcelable(paramParcel, 6, (Parcelable)getPhotoUrl(), paramInt, false);
    SafeParcelWriter.writeString(paramParcel, 7, getServerAuthCode(), false);
    SafeParcelWriter.writeLong(paramParcel, 8, getExpirationTimeSecs());
    SafeParcelWriter.writeString(paramParcel, 9, getObfuscatedIdentifier(), false);
    SafeParcelWriter.writeTypedList(paramParcel, 10, this.zzm, false);
    SafeParcelWriter.writeString(paramParcel, 11, getGivenName(), false);
    SafeParcelWriter.writeString(paramParcel, 12, getFamilyName(), false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\auth\api\signin\GoogleSignInAccount.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */