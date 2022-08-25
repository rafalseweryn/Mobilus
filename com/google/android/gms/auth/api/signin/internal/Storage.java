package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;

public class Storage {
  private static final Lock zzaf = new ReentrantLock();
  
  @GuardedBy("sLk")
  private static Storage zzag;
  
  private final Lock zzah = new ReentrantLock();
  
  @GuardedBy("mLk")
  private final SharedPreferences zzai;
  
  @VisibleForTesting
  private Storage(Context paramContext) {
    this.zzai = paramContext.getSharedPreferences("com.google.android.gms.signin", 0);
  }
  
  public static Storage getInstance(Context paramContext) {
    Preconditions.checkNotNull(paramContext);
    zzaf.lock();
    try {
      if (zzag == null) {
        Storage storage = new Storage();
        this(paramContext.getApplicationContext());
        zzag = storage;
      } 
      return zzag;
    } finally {
      zzaf.unlock();
    } 
  }
  
  @VisibleForTesting
  public static void setInstance(@Nullable Storage paramStorage) {
    zzaf.lock();
    try {
      zzag = paramStorage;
      return;
    } finally {
      zzaf.unlock();
    } 
  }
  
  private static String zza(String paramString1, String paramString2) {
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString1).length() + 1 + String.valueOf(paramString2).length());
    stringBuilder.append(paramString1);
    stringBuilder.append(":");
    stringBuilder.append(paramString2);
    return stringBuilder.toString();
  }
  
  @Nullable
  @VisibleForTesting
  private final GoogleSignInAccount zzb(String paramString) {
    if (TextUtils.isEmpty(paramString))
      return null; 
    paramString = getFromStore(zza("googleSignInAccount", paramString));
    if (paramString != null)
      try {
        return GoogleSignInAccount.fromJsonString(paramString);
      } catch (JSONException jSONException) {} 
    return null;
  }
  
  @Nullable
  @VisibleForTesting
  private final GoogleSignInOptions zzc(String paramString) {
    if (TextUtils.isEmpty(paramString))
      return null; 
    paramString = getFromStore(zza("googleSignInOptions", paramString));
    if (paramString != null)
      try {
        return GoogleSignInOptions.fromJsonString(paramString);
      } catch (JSONException jSONException) {} 
    return null;
  }
  
  public void clear() {
    this.zzah.lock();
    try {
      this.zzai.edit().clear().apply();
      return;
    } finally {
      this.zzah.unlock();
    } 
  }
  
  @Nullable
  protected String getFromStore(String paramString) {
    this.zzah.lock();
    try {
      paramString = this.zzai.getString(paramString, null);
      return paramString;
    } finally {
      this.zzah.unlock();
    } 
  }
  
  @Nullable
  public GoogleSignInAccount getSavedDefaultGoogleSignInAccount() {
    return zzb(getFromStore("defaultGoogleSignInAccount"));
  }
  
  @Nullable
  public GoogleSignInOptions getSavedDefaultGoogleSignInOptions() {
    return zzc(getFromStore("defaultGoogleSignInAccount"));
  }
  
  @Nullable
  public String getSavedRefreshToken() {
    return getFromStore("refreshToken");
  }
  
  protected void removeFromStore(String paramString) {
    this.zzah.lock();
    try {
      this.zzai.edit().remove(paramString).apply();
      return;
    } finally {
      this.zzah.unlock();
    } 
  }
  
  public void removeSavedDefaultGoogleSignInAccount() {
    String str = getFromStore("defaultGoogleSignInAccount");
    removeFromStore("defaultGoogleSignInAccount");
    if (!TextUtils.isEmpty(str)) {
      removeFromStore(zza("googleSignInAccount", str));
      removeFromStore(zza("googleSignInOptions", str));
    } 
  }
  
  public void saveDefaultGoogleSignInAccount(GoogleSignInAccount paramGoogleSignInAccount, GoogleSignInOptions paramGoogleSignInOptions) {
    Preconditions.checkNotNull(paramGoogleSignInAccount);
    Preconditions.checkNotNull(paramGoogleSignInOptions);
    saveToStore("defaultGoogleSignInAccount", paramGoogleSignInAccount.getObfuscatedIdentifier());
    Preconditions.checkNotNull(paramGoogleSignInAccount);
    Preconditions.checkNotNull(paramGoogleSignInOptions);
    String str = paramGoogleSignInAccount.getObfuscatedIdentifier();
    saveToStore(zza("googleSignInAccount", str), paramGoogleSignInAccount.toJsonForStorage());
    saveToStore(zza("googleSignInOptions", str), paramGoogleSignInOptions.toJson());
  }
  
  public void saveRefreshToken(String paramString) {
    if (!TextUtils.isEmpty(paramString))
      saveToStore("refreshToken", paramString); 
  }
  
  protected void saveToStore(String paramString1, String paramString2) {
    this.zzah.lock();
    try {
      this.zzai.edit().putString(paramString1, paramString2).apply();
      return;
    } finally {
      this.zzah.unlock();
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\auth\api\signin\internal\Storage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */