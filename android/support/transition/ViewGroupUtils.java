package android.support.transition;

import android.os.Build;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

class ViewGroupUtils {
  static ViewGroupOverlayImpl getOverlay(@NonNull ViewGroup paramViewGroup) {
    return (ViewGroupOverlayImpl)((Build.VERSION.SDK_INT >= 18) ? new ViewGroupOverlayApi18(paramViewGroup) : ViewGroupOverlayApi14.createFrom(paramViewGroup));
  }
  
  static void suppressLayout(@NonNull ViewGroup paramViewGroup, boolean paramBoolean) {
    if (Build.VERSION.SDK_INT >= 18) {
      ViewGroupUtilsApi18.suppressLayout(paramViewGroup, paramBoolean);
    } else {
      ViewGroupUtilsApi14.suppressLayout(paramViewGroup, paramBoolean);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\transition\ViewGroupUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */