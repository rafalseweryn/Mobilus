package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(19)
class ViewUtilsApi19 extends ViewUtilsBase {
  private static final String TAG = "ViewUtilsApi19";
  
  private static Method sGetTransitionAlphaMethod;
  
  private static boolean sGetTransitionAlphaMethodFetched;
  
  private static Method sSetTransitionAlphaMethod;
  
  private static boolean sSetTransitionAlphaMethodFetched;
  
  private void fetchGetTransitionAlphaMethod() {
    if (!sGetTransitionAlphaMethodFetched) {
      try {
        sGetTransitionAlphaMethod = View.class.getDeclaredMethod("getTransitionAlpha", new Class[0]);
        sGetTransitionAlphaMethod.setAccessible(true);
      } catch (NoSuchMethodException noSuchMethodException) {
        Log.i("ViewUtilsApi19", "Failed to retrieve getTransitionAlpha method", noSuchMethodException);
      } 
      sGetTransitionAlphaMethodFetched = true;
    } 
  }
  
  private void fetchSetTransitionAlphaMethod() {
    if (!sSetTransitionAlphaMethodFetched) {
      try {
        sSetTransitionAlphaMethod = View.class.getDeclaredMethod("setTransitionAlpha", new Class[] { float.class });
        sSetTransitionAlphaMethod.setAccessible(true);
      } catch (NoSuchMethodException noSuchMethodException) {
        Log.i("ViewUtilsApi19", "Failed to retrieve setTransitionAlpha method", noSuchMethodException);
      } 
      sSetTransitionAlphaMethodFetched = true;
    } 
  }
  
  public void clearNonTransitionAlpha(@NonNull View paramView) {}
  
  public float getTransitionAlpha(@NonNull View paramView) {
    fetchGetTransitionAlphaMethod();
    if (sGetTransitionAlphaMethod != null)
      try {
        return ((Float)sGetTransitionAlphaMethod.invoke(paramView, new Object[0])).floatValue();
      } catch (IllegalAccessException illegalAccessException) {
      
      } catch (InvocationTargetException invocationTargetException) {
        throw new RuntimeException(invocationTargetException.getCause());
      }  
    return super.getTransitionAlpha((View)invocationTargetException);
  }
  
  public void saveNonTransitionAlpha(@NonNull View paramView) {}
  
  public void setTransitionAlpha(@NonNull View paramView, float paramFloat) {
    fetchSetTransitionAlphaMethod();
    if (sSetTransitionAlphaMethod != null) {
      try {
        sSetTransitionAlphaMethod.invoke(paramView, new Object[] { Float.valueOf(paramFloat) });
      } catch (IllegalAccessException illegalAccessException) {
      
      } catch (InvocationTargetException invocationTargetException) {
        throw new RuntimeException(invocationTargetException.getCause());
      } 
    } else {
      invocationTargetException.setAlpha(paramFloat);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\transition\ViewUtilsApi19.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */