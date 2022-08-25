package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.LifecycleActivity;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import com.google.android.gms.common.api.internal.zzav;
import com.google.android.gms.common.api.internal.zzch;
import com.google.android.gms.common.api.internal.zzi;
import com.google.android.gms.common.api.internal.zzp;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignIn;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.SignInOptions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public abstract class GoogleApiClient {
  @KeepForSdk
  public static final String DEFAULT_ACCOUNT = "<<default account>>";
  
  public static final int SIGN_IN_MODE_OPTIONAL = 2;
  
  public static final int SIGN_IN_MODE_REQUIRED = 1;
  
  @GuardedBy("sAllClients")
  private static final Set<GoogleApiClient> zzcu = Collections.newSetFromMap(new WeakHashMap<>());
  
  public static void dumpAll(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    // Byte code:
    //   0: getstatic com/google/android/gms/common/api/GoogleApiClient.zzcu : Ljava/util/Set;
    //   3: astore #4
    //   5: aload #4
    //   7: monitorenter
    //   8: iconst_0
    //   9: istore #5
    //   11: aload_0
    //   12: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   15: ldc '  '
    //   17: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   20: astore #6
    //   22: getstatic com/google/android/gms/common/api/GoogleApiClient.zzcu : Ljava/util/Set;
    //   25: invokeinterface iterator : ()Ljava/util/Iterator;
    //   30: astore #7
    //   32: aload #7
    //   34: invokeinterface hasNext : ()Z
    //   39: ifeq -> 85
    //   42: aload #7
    //   44: invokeinterface next : ()Ljava/lang/Object;
    //   49: checkcast com/google/android/gms/common/api/GoogleApiClient
    //   52: astore #8
    //   54: aload_2
    //   55: aload_0
    //   56: invokevirtual append : (Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
    //   59: ldc 'GoogleApiClient#'
    //   61: invokevirtual append : (Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
    //   64: iload #5
    //   66: invokevirtual println : (I)V
    //   69: aload #8
    //   71: aload #6
    //   73: aload_1
    //   74: aload_2
    //   75: aload_3
    //   76: invokevirtual dump : (Ljava/lang/String;Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V
    //   79: iinc #5, 1
    //   82: goto -> 32
    //   85: aload #4
    //   87: monitorexit
    //   88: return
    //   89: astore_0
    //   90: aload #4
    //   92: monitorexit
    //   93: aload_0
    //   94: athrow
    // Exception table:
    //   from	to	target	type
    //   11	32	89	finally
    //   32	79	89	finally
    //   85	88	89	finally
    //   90	93	89	finally
  }
  
  @KeepForSdk
  public static Set<GoogleApiClient> getAllClients() {
    synchronized (zzcu) {
      return zzcu;
    } 
  }
  
  public abstract ConnectionResult blockingConnect();
  
  public abstract ConnectionResult blockingConnect(long paramLong, @NonNull TimeUnit paramTimeUnit);
  
  public abstract PendingResult<Status> clearDefaultAccountAndReconnect();
  
  public abstract void connect();
  
  public void connect(int paramInt) {
    throw new UnsupportedOperationException();
  }
  
  public abstract void disconnect();
  
  public abstract void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
  
  @KeepForSdk
  public <A extends Api.AnyClient, R extends Result, T extends com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T paramT) {
    throw new UnsupportedOperationException();
  }
  
  @KeepForSdk
  public <A extends Api.AnyClient, T extends com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T paramT) {
    throw new UnsupportedOperationException();
  }
  
  @NonNull
  @KeepForSdk
  public <C extends Api.Client> C getClient(@NonNull Api.AnyClientKey<C> paramAnyClientKey) {
    throw new UnsupportedOperationException();
  }
  
  @NonNull
  public abstract ConnectionResult getConnectionResult(@NonNull Api<?> paramApi);
  
  @KeepForSdk
  public Context getContext() {
    throw new UnsupportedOperationException();
  }
  
  @KeepForSdk
  public Looper getLooper() {
    throw new UnsupportedOperationException();
  }
  
  @KeepForSdk
  public boolean hasApi(@NonNull Api<?> paramApi) {
    throw new UnsupportedOperationException();
  }
  
  public abstract boolean hasConnectedApi(@NonNull Api<?> paramApi);
  
  public abstract boolean isConnected();
  
  public abstract boolean isConnecting();
  
  public abstract boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener paramOnConnectionFailedListener);
  
  @KeepForSdk
  public boolean maybeSignIn(SignInConnectionListener paramSignInConnectionListener) {
    throw new UnsupportedOperationException();
  }
  
  @KeepForSdk
  public void maybeSignOut() {
    throw new UnsupportedOperationException();
  }
  
  public abstract void reconnect();
  
  public abstract void registerConnectionCallbacks(@NonNull ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract void registerConnectionFailedListener(@NonNull OnConnectionFailedListener paramOnConnectionFailedListener);
  
  @KeepForSdk
  public <L> ListenerHolder<L> registerListener(@NonNull L paramL) {
    throw new UnsupportedOperationException();
  }
  
  public abstract void stopAutoManage(@NonNull FragmentActivity paramFragmentActivity);
  
  public abstract void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener paramOnConnectionFailedListener);
  
  public void zza(zzch paramzzch) {
    throw new UnsupportedOperationException();
  }
  
  public void zzb(zzch paramzzch) {
    throw new UnsupportedOperationException();
  }
  
  @KeepForSdk
  public static final class Builder {
    private final Context mContext;
    
    private Looper zzcn;
    
    private final Set<Scope> zzcv = new HashSet<>();
    
    private final Set<Scope> zzcw = new HashSet<>();
    
    private int zzcx;
    
    private View zzcy;
    
    private String zzcz;
    
    private String zzda;
    
    private final Map<Api<?>, ClientSettings.OptionalApiSettings> zzdb = (Map<Api<?>, ClientSettings.OptionalApiSettings>)new ArrayMap();
    
    private final Map<Api<?>, Api.ApiOptions> zzdc = (Map<Api<?>, Api.ApiOptions>)new ArrayMap();
    
    private LifecycleActivity zzdd;
    
    private int zzde = -1;
    
    private GoogleApiClient.OnConnectionFailedListener zzdf;
    
    private GoogleApiAvailability zzdg = GoogleApiAvailability.getInstance();
    
    private Api.AbstractClientBuilder<? extends SignInClient, SignInOptions> zzdh = SignIn.CLIENT_BUILDER;
    
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> zzdi = new ArrayList<>();
    
    private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zzdj = new ArrayList<>();
    
    private boolean zzdk = false;
    
    private Account zzs;
    
    @KeepForSdk
    public Builder(@NonNull Context param1Context) {
      this.mContext = param1Context;
      this.zzcn = param1Context.getMainLooper();
      this.zzcz = param1Context.getPackageName();
      this.zzda = param1Context.getClass().getName();
    }
    
    @KeepForSdk
    public Builder(@NonNull Context param1Context, @NonNull GoogleApiClient.ConnectionCallbacks param1ConnectionCallbacks, @NonNull GoogleApiClient.OnConnectionFailedListener param1OnConnectionFailedListener) {
      this(param1Context);
      Preconditions.checkNotNull(param1ConnectionCallbacks, "Must provide a connected listener");
      this.zzdi.add(param1ConnectionCallbacks);
      Preconditions.checkNotNull(param1OnConnectionFailedListener, "Must provide a connection failed listener");
      this.zzdj.add(param1OnConnectionFailedListener);
    }
    
    private final <O extends Api.ApiOptions> void zza(Api<O> param1Api, O param1O, Scope... param1VarArgs) {
      HashSet<Scope> hashSet = new HashSet<>(param1Api.zzj().getImpliedScopes(param1O));
      int i = param1VarArgs.length;
      for (byte b = 0; b < i; b++)
        hashSet.add(param1VarArgs[b]); 
      this.zzdb.put(param1Api, new ClientSettings.OptionalApiSettings(hashSet));
    }
    
    public final Builder addApi(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> param1Api) {
      Preconditions.checkNotNull(param1Api, "Api must not be null");
      this.zzdc.put(param1Api, null);
      List<Scope> list = param1Api.zzj().getImpliedScopes(null);
      this.zzcw.addAll(list);
      this.zzcv.addAll(list);
      return this;
    }
    
    public final <O extends Api.ApiOptions.HasOptions> Builder addApi(@NonNull Api<O> param1Api, @NonNull O param1O) {
      Preconditions.checkNotNull(param1Api, "Api must not be null");
      Preconditions.checkNotNull(param1O, "Null options are not permitted for this Api");
      this.zzdc.put(param1Api, (Api.ApiOptions)param1O);
      List<Scope> list = param1Api.zzj().getImpliedScopes(param1O);
      this.zzcw.addAll(list);
      this.zzcv.addAll(list);
      return this;
    }
    
    public final <O extends Api.ApiOptions.HasOptions> Builder addApiIfAvailable(@NonNull Api<O> param1Api, @NonNull O param1O, Scope... param1VarArgs) {
      Preconditions.checkNotNull(param1Api, "Api must not be null");
      Preconditions.checkNotNull(param1O, "Null options are not permitted for this Api");
      this.zzdc.put(param1Api, (Api.ApiOptions)param1O);
      zza((Api)param1Api, (Api.ApiOptions)param1O, param1VarArgs);
      return this;
    }
    
    public final Builder addApiIfAvailable(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> param1Api, Scope... param1VarArgs) {
      Preconditions.checkNotNull(param1Api, "Api must not be null");
      this.zzdc.put(param1Api, null);
      zza(param1Api, null, param1VarArgs);
      return this;
    }
    
    public final Builder addConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks param1ConnectionCallbacks) {
      Preconditions.checkNotNull(param1ConnectionCallbacks, "Listener must not be null");
      this.zzdi.add(param1ConnectionCallbacks);
      return this;
    }
    
    public final Builder addOnConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener param1OnConnectionFailedListener) {
      Preconditions.checkNotNull(param1OnConnectionFailedListener, "Listener must not be null");
      this.zzdj.add(param1OnConnectionFailedListener);
      return this;
    }
    
    public final Builder addScope(@NonNull Scope param1Scope) {
      Preconditions.checkNotNull(param1Scope, "Scope must not be null");
      this.zzcv.add(param1Scope);
      return this;
    }
    
    @KeepForSdk
    public final Builder addScopeNames(String[] param1ArrayOfString) {
      for (byte b = 0; b < param1ArrayOfString.length; b++)
        this.zzcv.add(new Scope(param1ArrayOfString[b])); 
      return this;
    }
    
    public final GoogleApiClient build() {
      Api api;
      Preconditions.checkArgument(this.zzdc.isEmpty() ^ true, "must call addApi() to add at least one API");
      ClientSettings clientSettings = buildClientSettings();
      Api.AbstractClientBuilder abstractClientBuilder = null;
      Map map = clientSettings.getOptionalApiSettings();
      ArrayMap<Api, Boolean> arrayMap = new ArrayMap();
      ArrayMap<Api.AnyClientKey<?>, zzp> arrayMap1 = new ArrayMap();
      ArrayList<zzp> arrayList = new ArrayList();
      Iterator<Api> iterator = this.zzdc.keySet().iterator();
      int i = 0;
      while (iterator.hasNext()) {
        boolean bool;
        Api api2;
        Api api1 = iterator.next();
        Object object = this.zzdc.get(api1);
        if (map.get(api1) != null) {
          bool = true;
        } else {
          bool = false;
        } 
        arrayMap.put(api1, Boolean.valueOf(bool));
        zzp zzp = new zzp(api1, bool);
        arrayList.add(zzp);
        Api.AbstractClientBuilder abstractClientBuilder1 = api1.zzk();
        zzp = (zzp)abstractClientBuilder1.buildClient(this.mContext, this.zzcn, clientSettings, object, (GoogleApiClient.ConnectionCallbacks)zzp, (GoogleApiClient.OnConnectionFailedListener)zzp);
        arrayMap1.put(api1.getClientKey(), zzp);
        if (abstractClientBuilder1.getPriority() == 1)
          if (object != null) {
            i = 1;
          } else {
            i = 0;
          }  
        abstractClientBuilder1 = abstractClientBuilder;
        if (zzp.providesSignIn()) {
          if (abstractClientBuilder != null) {
            String str1 = api1.getName();
            String str2 = abstractClientBuilder.getName();
            StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 21 + String.valueOf(str2).length());
            stringBuilder.append(str1);
            stringBuilder.append(" cannot be used with ");
            stringBuilder.append(str2);
            throw new IllegalStateException(stringBuilder.toString());
          } 
          api2 = api1;
        } 
        api = api2;
      } 
      if (api != null) {
        StringBuilder stringBuilder;
        boolean bool;
        if (i) {
          String str = api.getName();
          stringBuilder = new StringBuilder(String.valueOf(str).length() + 82);
          stringBuilder.append("With using ");
          stringBuilder.append(str);
          stringBuilder.append(", GamesOptions can only be specified within GoogleSignInOptions.Builder");
          throw new IllegalStateException(stringBuilder.toString());
        } 
        if (this.zzs == null) {
          bool = true;
        } else {
          bool = false;
        } 
        Preconditions.checkState(bool, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", new Object[] { stringBuilder.getName() });
        Preconditions.checkState(this.zzcv.equals(this.zzcw), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", new Object[] { stringBuilder.getName() });
      } 
      i = zzav.zza(arrayMap1.values(), true);
      null = new zzav(this.mContext, new ReentrantLock(), this.zzcn, clientSettings, this.zzdg, this.zzdh, (Map)arrayMap, this.zzdi, this.zzdj, (Map)arrayMap1, this.zzde, i, arrayList, false);
      synchronized (GoogleApiClient.zzn()) {
        GoogleApiClient.zzn().add(null);
        if (this.zzde >= 0)
          zzi.zza(this.zzdd).zza(this.zzde, (GoogleApiClient)null, this.zzdf); 
        return (GoogleApiClient)null;
      } 
    }
    
    @KeepForSdk
    @VisibleForTesting
    public final ClientSettings buildClientSettings() {
      SignInOptions signInOptions = SignInOptions.DEFAULT;
      if (this.zzdc.containsKey(SignIn.API))
        signInOptions = (SignInOptions)this.zzdc.get(SignIn.API); 
      return new ClientSettings(this.zzs, this.zzcv, this.zzdb, this.zzcx, this.zzcy, this.zzcz, this.zzda, signInOptions);
    }
    
    public final Builder enableAutoManage(@NonNull FragmentActivity param1FragmentActivity, int param1Int, @Nullable GoogleApiClient.OnConnectionFailedListener param1OnConnectionFailedListener) {
      boolean bool;
      LifecycleActivity lifecycleActivity = new LifecycleActivity((Activity)param1FragmentActivity);
      if (param1Int >= 0) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkArgument(bool, "clientId must be non-negative");
      this.zzde = param1Int;
      this.zzdf = param1OnConnectionFailedListener;
      this.zzdd = lifecycleActivity;
      return this;
    }
    
    public final Builder enableAutoManage(@NonNull FragmentActivity param1FragmentActivity, @Nullable GoogleApiClient.OnConnectionFailedListener param1OnConnectionFailedListener) {
      return enableAutoManage(param1FragmentActivity, 0, param1OnConnectionFailedListener);
    }
    
    public final Builder setAccountName(String param1String) {
      Account account;
      if (param1String == null) {
        param1String = null;
      } else {
        account = new Account(param1String, "com.google");
      } 
      this.zzs = account;
      return this;
    }
    
    public final Builder setGravityForPopups(int param1Int) {
      this.zzcx = param1Int;
      return this;
    }
    
    public final Builder setHandler(@NonNull Handler param1Handler) {
      Preconditions.checkNotNull(param1Handler, "Handler must not be null");
      this.zzcn = param1Handler.getLooper();
      return this;
    }
    
    public final Builder setViewForPopups(@NonNull View param1View) {
      Preconditions.checkNotNull(param1View, "View must not be null");
      this.zzcy = param1View;
      return this;
    }
    
    public final Builder useDefaultAccount() {
      return setAccountName("<<default account>>");
    }
  }
  
  public static interface ConnectionCallbacks {
    public static final int CAUSE_NETWORK_LOST = 2;
    
    public static final int CAUSE_SERVICE_DISCONNECTED = 1;
    
    void onConnected(@Nullable Bundle param1Bundle);
    
    void onConnectionSuspended(int param1Int);
  }
  
  public static interface OnConnectionFailedListener {
    void onConnectionFailed(@NonNull ConnectionResult param1ConnectionResult);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\GoogleApiClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */