package android.support.v4.content.pm;

import android.annotation.SuppressLint;
import android.content.pm.PermissionInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PermissionInfoCompat {
  @SuppressLint({"WrongConstant"})
  public static int getProtection(@NonNull PermissionInfo paramPermissionInfo) {
    return (Build.VERSION.SDK_INT >= 28) ? paramPermissionInfo.getProtection() : (paramPermissionInfo.protectionLevel & 0xF);
  }
  
  @SuppressLint({"WrongConstant"})
  public static int getProtectionFlags(@NonNull PermissionInfo paramPermissionInfo) {
    return (Build.VERSION.SDK_INT >= 28) ? paramPermissionInfo.getProtectionFlags() : (paramPermissionInfo.protectionLevel & 0xFFFFFFF0);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY})
  public static @interface Protection {}
  
  @Retention(RetentionPolicy.SOURCE)
  @SuppressLint({"UniqueConstants"})
  @RestrictTo({RestrictTo.Scope.LIBRARY})
  public static @interface ProtectionFlags {}
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\content\pm\PermissionInfoCompat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */