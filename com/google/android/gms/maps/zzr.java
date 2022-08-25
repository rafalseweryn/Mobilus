package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.zzbt;

final class zzr extends zzbt {
  zzr(GoogleMap paramGoogleMap, GoogleMap.SnapshotReadyCallback paramSnapshotReadyCallback) {}
  
  public final void onSnapshotReady(Bitmap paramBitmap) throws RemoteException {
    this.zzz.onSnapshotReady(paramBitmap);
  }
  
  public final void zzb(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    this.zzz.onSnapshotReady((Bitmap)ObjectWrapper.unwrap(paramIObjectWrapper));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\zzr.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */