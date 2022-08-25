package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Handler;
import android.os.IInterface;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Iterator;
import java.util.Set;

public abstract class GmsClient<T extends IInterface> extends BaseGmsClient<T> implements Api.Client, GmsClientEventManager.GmsClientEventState {
  private final Set<Scope> mScopes;
  
  private final ClientSettings zzgf;
  
  private final Account zzs;
  
  @VisibleForTesting
  protected GmsClient(Context paramContext, Handler paramHandler, int paramInt, ClientSettings paramClientSettings) {
    this(paramContext, paramHandler, GmsClientSupervisor.getInstance(paramContext), GoogleApiAvailability.getInstance(), paramInt, paramClientSettings, (GoogleApiClient.ConnectionCallbacks)null, (GoogleApiClient.OnConnectionFailedListener)null);
  }
  
  @VisibleForTesting
  protected GmsClient(Context paramContext, Handler paramHandler, GmsClientSupervisor paramGmsClientSupervisor, GoogleApiAvailability paramGoogleApiAvailability, int paramInt, ClientSettings paramClientSettings, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    super(paramContext, paramHandler, paramGmsClientSupervisor, (GoogleApiAvailabilityLight)paramGoogleApiAvailability, paramInt, zza(paramConnectionCallbacks), zza(paramOnConnectionFailedListener));
    this.zzgf = Preconditions.<ClientSettings>checkNotNull(paramClientSettings);
    this.zzs = paramClientSettings.getAccount();
    this.mScopes = zza(paramClientSettings.getAllRequestedScopes());
  }
  
  protected GmsClient(Context paramContext, Looper paramLooper, int paramInt, ClientSettings paramClientSettings) {
    this(paramContext, paramLooper, GmsClientSupervisor.getInstance(paramContext), GoogleApiAvailability.getInstance(), paramInt, paramClientSettings, (GoogleApiClient.ConnectionCallbacks)null, (GoogleApiClient.OnConnectionFailedListener)null);
  }
  
  protected GmsClient(Context paramContext, Looper paramLooper, int paramInt, ClientSettings paramClientSettings, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    this(paramContext, paramLooper, GmsClientSupervisor.getInstance(paramContext), GoogleApiAvailability.getInstance(), paramInt, paramClientSettings, Preconditions.<GoogleApiClient.ConnectionCallbacks>checkNotNull(paramConnectionCallbacks), Preconditions.<GoogleApiClient.OnConnectionFailedListener>checkNotNull(paramOnConnectionFailedListener));
  }
  
  @VisibleForTesting
  protected GmsClient(Context paramContext, Looper paramLooper, GmsClientSupervisor paramGmsClientSupervisor, GoogleApiAvailability paramGoogleApiAvailability, int paramInt, ClientSettings paramClientSettings, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    super(paramContext, paramLooper, paramGmsClientSupervisor, (GoogleApiAvailabilityLight)paramGoogleApiAvailability, paramInt, zza(paramConnectionCallbacks), zza(paramOnConnectionFailedListener), paramClientSettings.getRealClientClassName());
    this.zzgf = paramClientSettings;
    this.zzs = paramClientSettings.getAccount();
    this.mScopes = zza(paramClientSettings.getAllRequestedScopes());
  }
  
  @Nullable
  private static BaseGmsClient.BaseConnectionCallbacks zza(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    return (paramConnectionCallbacks == null) ? null : new zzf(paramConnectionCallbacks);
  }
  
  @Nullable
  private static BaseGmsClient.BaseOnConnectionFailedListener zza(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    return (paramOnConnectionFailedListener == null) ? null : new zzg(paramOnConnectionFailedListener);
  }
  
  private final Set<Scope> zza(@NonNull Set<Scope> paramSet) {
    Set<Scope> set = validateScopes(paramSet);
    Iterator<Scope> iterator = set.iterator();
    while (iterator.hasNext()) {
      if (!paramSet.contains(iterator.next()))
        throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead"); 
    } 
    return set;
  }
  
  public final Account getAccount() {
    return this.zzs;
  }
  
  protected final ClientSettings getClientSettings() {
    return this.zzgf;
  }
  
  public int getMinApkVersion() {
    return super.getMinApkVersion();
  }
  
  public Feature[] getRequiredFeatures() {
    return new Feature[0];
  }
  
  protected final Set<Scope> getScopes() {
    return this.mScopes;
  }
  
  @NonNull
  protected Set<Scope> validateScopes(@NonNull Set<Scope> paramSet) {
    return paramSet;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\GmsClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */