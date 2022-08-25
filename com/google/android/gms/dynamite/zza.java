package com.google.android.gms.dynamite;

import android.content.Context;

final class zza implements DynamiteModule.VersionPolicy.IVersions {
  public final int getLocalVersion(Context paramContext, String paramString) {
    return DynamiteModule.getLocalVersion(paramContext, paramString);
  }
  
  public final int getRemoteVersion(Context paramContext, String paramString, boolean paramBoolean) throws DynamiteModule.LoadingException {
    return DynamiteModule.getRemoteVersion(paramContext, paramString, paramBoolean);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\dynamite\zza.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */