package com.google.android.gms.dynamite;

import android.content.Context;

final class zzg implements DynamiteModule.VersionPolicy {
  public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context paramContext, String paramString, DynamiteModule.VersionPolicy.IVersions paramIVersions) throws DynamiteModule.LoadingException {
    int i;
    DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
    selectionResult.localVersion = paramIVersions.getLocalVersion(paramContext, paramString);
    if (selectionResult.localVersion != 0) {
      i = paramIVersions.getRemoteVersion(paramContext, paramString, false);
    } else {
      i = paramIVersions.getRemoteVersion(paramContext, paramString, true);
    } 
    selectionResult.remoteVersion = i;
    if (selectionResult.localVersion == 0 && selectionResult.remoteVersion == 0) {
      selectionResult.selection = 0;
      return selectionResult;
    } 
    if (selectionResult.remoteVersion >= selectionResult.localVersion) {
      selectionResult.selection = 1;
      return selectionResult;
    } 
    selectionResult.selection = -1;
    return selectionResult;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\dynamite\zzg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */