package com.google.android.gms.common.util;

import android.os.StrictMode;

public class StrictModeUtils {
  public static StrictMode.ThreadPolicy setDynamiteThreadPolicy() {
    StrictMode.noteSlowCall("gcore.dynamite");
    StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
    StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
    return threadPolicy;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\StrictModeUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */