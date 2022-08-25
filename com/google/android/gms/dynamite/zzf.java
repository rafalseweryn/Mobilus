package com.google.android.gms.dynamite;

import android.content.Context;

final class zzf implements DynamiteModule.VersionPolicy {
  public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context paramContext, String paramString, DynamiteModule.VersionPolicy.IVersions paramIVersions) throws DynamiteModule.LoadingException {
    DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
    selectionResult.localVersion = paramIVersions.getLocalVersion(paramContext, paramString);
    selectionResult.remoteVersion = paramIVersions.getRemoteVersion(paramContext, paramString, true);
    if (selectionResult.localVersion == 0 && selectionResult.remoteVersion == 0) {
      boolean bool = false;
      selectionResult.selection = bool;
      return selectionResult;
    } 
    if (selectionResult.remoteVersion >= selectionResult.localVersion) {
      selectionResult.selection = 1;
      return selectionResult;
    } 
    byte b = -1;
    selectionResult.selection = b;
    return selectionResult;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\dynamite\zzf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */