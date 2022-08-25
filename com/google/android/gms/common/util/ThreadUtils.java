package com.google.android.gms.common.util;

import android.os.Looper;

public class ThreadUtils {
  public static boolean isMainThread() {
    return (Looper.getMainLooper() == Looper.myLooper());
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\ThreadUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */