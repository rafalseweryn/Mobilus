package android.support.v4.media;

import android.os.Bundle;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class MediaBrowserCompatUtils {
  public static boolean areSameOptions(Bundle paramBundle1, Bundle paramBundle2) {
    boolean bool1 = true;
    boolean bool2 = true;
    boolean bool3 = true;
    if (paramBundle1 == paramBundle2)
      return true; 
    if (paramBundle1 == null) {
      if (paramBundle2.getInt("android.media.browse.extra.PAGE", -1) != -1 || paramBundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1) != -1)
        bool3 = false; 
      return bool3;
    } 
    if (paramBundle2 == null) {
      if (paramBundle1.getInt("android.media.browse.extra.PAGE", -1) == -1 && paramBundle1.getInt("android.media.browse.extra.PAGE_SIZE", -1) == -1) {
        bool3 = bool1;
      } else {
        bool3 = false;
      } 
      return bool3;
    } 
    if (paramBundle1.getInt("android.media.browse.extra.PAGE", -1) == paramBundle2.getInt("android.media.browse.extra.PAGE", -1) && paramBundle1.getInt("android.media.browse.extra.PAGE_SIZE", -1) == paramBundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1)) {
      bool3 = bool2;
    } else {
      bool3 = false;
    } 
    return bool3;
  }
  
  public static boolean hasDuplicatedItems(Bundle paramBundle1, Bundle paramBundle2) {
    int i;
    int j;
    int k;
    int m;
    if (paramBundle1 == null) {
      i = -1;
    } else {
      i = paramBundle1.getInt("android.media.browse.extra.PAGE", -1);
    } 
    if (paramBundle2 == null) {
      j = -1;
    } else {
      j = paramBundle2.getInt("android.media.browse.extra.PAGE", -1);
    } 
    if (paramBundle1 == null) {
      k = -1;
    } else {
      k = paramBundle1.getInt("android.media.browse.extra.PAGE_SIZE", -1);
    } 
    if (paramBundle2 == null) {
      m = -1;
    } else {
      m = paramBundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1);
    } 
    int n = Integer.MAX_VALUE;
    boolean bool1 = false;
    if (i == -1 || k == -1) {
      k = Integer.MAX_VALUE;
      i = 0;
    } else {
      i *= k;
      k = k + i - 1;
    } 
    if (j == -1 || m == -1) {
      m = 0;
      j = n;
    } else {
      n = m * j;
      j = m + n - 1;
      m = n;
    } 
    boolean bool2 = bool1;
    if (k >= m) {
      bool2 = bool1;
      if (j >= i)
        bool2 = true; 
    } 
    return bool2;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\media\MediaBrowserCompatUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */