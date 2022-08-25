package com.google.android.gms.common.util;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.File;

public class SharedPreferencesUtils {
  @Deprecated
  public static void publishWorldReadableSharedPreferences(Context paramContext, SharedPreferences.Editor paramEditor, String paramString) {
    File file2 = new File((paramContext.getApplicationInfo()).dataDir, "shared_prefs");
    File file1 = file2.getParentFile();
    if (file1 != null)
      file1.setExecutable(true, false); 
    file2.setExecutable(true, false);
    paramEditor.commit();
    (new File(file2, String.valueOf(paramString).concat(".xml"))).setReadable(true, false);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\SharedPreferencesUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */