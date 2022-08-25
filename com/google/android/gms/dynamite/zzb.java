package com.google.android.gms.dynamite;

import android.content.Context;

final class zzb implements DynamiteModule.VersionPolicy {
  public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context paramContext, String paramString, DynamiteModule.VersionPolicy.IVersions paramIVersions) throws DynamiteModule.LoadingException {
    DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
    selectionResult.remoteVersion = paramIVersions.getRemoteVersion(paramContext, paramString, true);
    if (selectionResult.remoteVersion != 0) {
      selectionResult.selection = 1;
      return selectionResult;
    } 
    selectionResult.localVersion = paramIVersions.getLocalVersion(paramContext, paramString);
    if (selectionResult.localVersion != 0)
      selectionResult.selection = -1; 
    return selectionResult;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\dynamite\zzb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */