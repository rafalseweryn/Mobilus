package com.google.android.gms.common.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class InputMethodUtils {
  public static boolean hideSoftInput(Context paramContext, View paramView) {
    InputMethodManager inputMethodManager = zzf(paramContext);
    if (inputMethodManager != null) {
      inputMethodManager.hideSoftInputFromWindow(paramView.getWindowToken(), 0);
      return true;
    } 
    return false;
  }
  
  public static boolean isAcceptingText(Context paramContext) {
    InputMethodManager inputMethodManager = zzf(paramContext);
    return (inputMethodManager != null) ? inputMethodManager.isAcceptingText() : false;
  }
  
  public static void restart(Context paramContext, View paramView) {
    InputMethodManager inputMethodManager = zzf(paramContext);
    if (inputMethodManager != null)
      inputMethodManager.restartInput(paramView); 
  }
  
  public static boolean showSoftInput(Context paramContext, View paramView) {
    InputMethodManager inputMethodManager = zzf(paramContext);
    if (inputMethodManager != null) {
      inputMethodManager.showSoftInput(paramView, 0);
      return true;
    } 
    return false;
  }
  
  private static InputMethodManager zzf(Context paramContext) {
    return (InputMethodManager)paramContext.getSystemService("input_method");
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\InputMethodUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */