package com.google.android.gms.signin;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Preconditions;

public final class SignInOptions implements Api.ApiOptions.Optional {
  public static final SignInOptions DEFAULT = (new Builder()).build();
  
  private final boolean zzadb;
  
  private final boolean zzadc;
  
  private final Long zzadd;
  
  private final Long zzade;
  
  private final boolean zzt;
  
  private final boolean zzv;
  
  private final String zzw;
  
  private final String zzx;
  
  private SignInOptions(boolean paramBoolean1, boolean paramBoolean2, String paramString1, boolean paramBoolean3, String paramString2, boolean paramBoolean4, Long paramLong1, Long paramLong2) {
    this.zzadb = paramBoolean1;
    this.zzt = paramBoolean2;
    this.zzw = paramString1;
    this.zzv = paramBoolean3;
    this.zzadc = paramBoolean4;
    this.zzx = paramString2;
    this.zzadd = paramLong1;
    this.zzade = paramLong2;
  }
  
  @Nullable
  public final Long getAuthApiSignInModuleVersion() {
    return this.zzadd;
  }
  
  @Nullable
  public final String getHostedDomain() {
    return this.zzx;
  }
  
  @Nullable
  public final Long getRealClientLibraryVersion() {
    return this.zzade;
  }
  
  public final String getServerClientId() {
    return this.zzw;
  }
  
  public final boolean isForceCodeForRefreshToken() {
    return this.zzv;
  }
  
  public final boolean isIdTokenRequested() {
    return this.zzt;
  }
  
  public final boolean isOfflineAccessRequested() {
    return this.zzadb;
  }
  
  public final boolean waitForAccessTokenRefresh() {
    return this.zzadc;
  }
  
  public static final class Builder {
    private boolean zzadf;
    
    private boolean zzadg;
    
    private String zzadh;
    
    private boolean zzadi;
    
    private String zzadj;
    
    private boolean zzadk;
    
    private Long zzadl;
    
    private Long zzadm;
    
    private final String zza(String param1String) {
      Preconditions.checkNotNull(param1String);
      if (this.zzadh == null || this.zzadh.equals(param1String)) {
        boolean bool1 = true;
        Preconditions.checkArgument(bool1, "two different server client ids provided");
        return param1String;
      } 
      boolean bool = false;
      Preconditions.checkArgument(bool, "two different server client ids provided");
      return param1String;
    }
    
    public final SignInOptions build() {
      return new SignInOptions(this.zzadf, this.zzadg, this.zzadh, this.zzadi, this.zzadj, this.zzadk, this.zzadl, this.zzadm, null);
    }
    
    public final Builder requestIdToken(String param1String) {
      this.zzadg = true;
      this.zzadh = zza(param1String);
      return this;
    }
    
    public final Builder requestServerAuthCode(String param1String, boolean param1Boolean) {
      this.zzadi = param1Boolean;
      this.zzadf = true;
      this.zzadh = zza(param1String);
      return this;
    }
    
    public final Builder setAuthApiSignInModuleVersion(long param1Long) {
      this.zzadl = Long.valueOf(param1Long);
      return this;
    }
    
    public final Builder setHostedDomain(@Nullable String param1String) {
      this.zzadj = param1String;
      return this;
    }
    
    public final Builder setRealClientLibraryVersion(long param1Long) {
      this.zzadm = Long.valueOf(param1Long);
      return this;
    }
    
    public final Builder setWaitForAccessTokenRefresh(boolean param1Boolean) {
      this.zzadk = param1Boolean;
      return this;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\signin\SignInOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */