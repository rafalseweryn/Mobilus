package android.support.v4.media;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.util.ObjectsCompat;
import android.text.TextUtils;
import android.util.Log;

class MediaSessionManagerImplBase implements MediaSessionManager.MediaSessionManagerImpl {
  private static final boolean DEBUG = MediaSessionManager.DEBUG;
  
  private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
  
  private static final String PERMISSION_MEDIA_CONTENT_CONTROL = "android.permission.MEDIA_CONTENT_CONTROL";
  
  private static final String PERMISSION_STATUS_BAR_SERVICE = "android.permission.STATUS_BAR_SERVICE";
  
  private static final String TAG = "MediaSessionManager";
  
  ContentResolver mContentResolver;
  
  Context mContext;
  
  MediaSessionManagerImplBase(Context paramContext) {
    this.mContext = paramContext;
    this.mContentResolver = this.mContext.getContentResolver();
  }
  
  private boolean isPermissionGranted(MediaSessionManager.RemoteUserInfoImpl paramRemoteUserInfoImpl, String paramString) {
    int i = paramRemoteUserInfoImpl.getPid();
    boolean bool1 = false;
    boolean bool2 = false;
    if (i < 0) {
      if (this.mContext.getPackageManager().checkPermission(paramString, paramRemoteUserInfoImpl.getPackageName()) == 0)
        bool2 = true; 
      return bool2;
    } 
    bool2 = bool1;
    if (this.mContext.checkPermission(paramString, paramRemoteUserInfoImpl.getPid(), paramRemoteUserInfoImpl.getUid()) == 0)
      bool2 = true; 
    return bool2;
  }
  
  public Context getContext() {
    return this.mContext;
  }
  
  boolean isEnabledNotificationListener(@NonNull MediaSessionManager.RemoteUserInfoImpl paramRemoteUserInfoImpl) {
    String str = Settings.Secure.getString(this.mContentResolver, "enabled_notification_listeners");
    if (str != null) {
      String[] arrayOfString = str.split(":");
      for (byte b = 0; b < arrayOfString.length; b++) {
        ComponentName componentName = ComponentName.unflattenFromString(arrayOfString[b]);
        if (componentName != null && componentName.getPackageName().equals(paramRemoteUserInfoImpl.getPackageName()))
          return true; 
      } 
    } 
    return false;
  }
  
  public boolean isTrustedForMediaControl(@NonNull MediaSessionManager.RemoteUserInfoImpl paramRemoteUserInfoImpl) {
    boolean bool = false;
    try {
      ApplicationInfo applicationInfo = this.mContext.getPackageManager().getApplicationInfo(paramRemoteUserInfoImpl.getPackageName(), 0);
      if (applicationInfo.uid != paramRemoteUserInfoImpl.getUid()) {
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Package name ");
          stringBuilder.append(paramRemoteUserInfoImpl.getPackageName());
          stringBuilder.append(" doesn't match with the uid ");
          stringBuilder.append(paramRemoteUserInfoImpl.getUid());
          Log.d("MediaSessionManager", stringBuilder.toString());
        } 
        return false;
      } 
      if (isPermissionGranted(paramRemoteUserInfoImpl, "android.permission.STATUS_BAR_SERVICE") || isPermissionGranted(paramRemoteUserInfoImpl, "android.permission.MEDIA_CONTENT_CONTROL") || paramRemoteUserInfoImpl.getUid() == 1000 || isEnabledNotificationListener(paramRemoteUserInfoImpl))
        bool = true; 
      return bool;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      if (DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Package ");
        stringBuilder.append(paramRemoteUserInfoImpl.getPackageName());
        stringBuilder.append(" doesn't exist");
        Log.d("MediaSessionManager", stringBuilder.toString());
      } 
      return false;
    } 
  }
  
  static class RemoteUserInfoImplBase implements MediaSessionManager.RemoteUserInfoImpl {
    private String mPackageName;
    
    private int mPid;
    
    private int mUid;
    
    RemoteUserInfoImplBase(String param1String, int param1Int1, int param1Int2) {
      this.mPackageName = param1String;
      this.mPid = param1Int1;
      this.mUid = param1Int2;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = true;
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof RemoteUserInfoImplBase))
        return false; 
      param1Object = param1Object;
      if (!TextUtils.equals(this.mPackageName, ((RemoteUserInfoImplBase)param1Object).mPackageName) || this.mPid != ((RemoteUserInfoImplBase)param1Object).mPid || this.mUid != ((RemoteUserInfoImplBase)param1Object).mUid)
        bool = false; 
      return bool;
    }
    
    public String getPackageName() {
      return this.mPackageName;
    }
    
    public int getPid() {
      return this.mPid;
    }
    
    public int getUid() {
      return this.mUid;
    }
    
    public int hashCode() {
      return ObjectsCompat.hash(new Object[] { this.mPackageName, Integer.valueOf(this.mPid), Integer.valueOf(this.mUid) });
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\media\MediaSessionManagerImplBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */