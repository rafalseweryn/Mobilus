package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.Api;

public final class Common {
  public static final Api<Api.ApiOptions.NoOptions> API;
  
  private static final Api.AbstractClientBuilder<CommonClient, Api.ApiOptions.NoOptions> CLIENT_BUILDER;
  
  public static final Api.ClientKey<CommonClient> CLIENT_KEY = new Api.ClientKey();
  
  public static final CommonApi CommonApi;
  
  static {
    CLIENT_BUILDER = new zza();
    API = new Api("Common.API", CLIENT_BUILDER, CLIENT_KEY);
    CommonApi = new CommonApiImpl();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\service\Common.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */