package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(18)
class ViewGroupUtilsApi18 {
  private static final String TAG = "ViewUtilsApi18";
  
  private static Method sSuppressLayoutMethod;
  
  private static boolean sSuppressLayoutMethodFetched;
  
  private static void fetchSuppressLayoutMethod() {
    if (!sSuppressLayoutMethodFetched) {
      try {
        sSuppressLayoutMethod = ViewGroup.class.getDeclaredMethod("suppressLayout", new Class[] { boolean.class });
        sSuppressLayoutMethod.setAccessible(true);
      } catch (NoSuchMethodException noSuchMethodException) {
        Log.i("ViewUtilsApi18", "Failed to retrieve suppressLayout method", noSuchMethodException);
      } 
      sSuppressLayoutMethodFetched = true;
    } 
  }
  
  static void suppressLayout(@NonNull ViewGroup paramViewGroup, boolean paramBoolean) {
    fetchSuppressLayoutMethod();
    if (sSuppressLayoutMethod != null)
      try {
        sSuppressLayoutMethod.invoke(paramViewGroup, new Object[] { Boolean.valueOf(paramBoolean) });
      } catch (IllegalAccessException illegalAccessException) {
        Log.i("ViewUtilsApi18", "Failed to invoke suppressLayout method", illegalAccessException);
      } catch (InvocationTargetException invocationTargetException) {
        Log.i("ViewUtilsApi18", "Error invoking suppressLayout method", invocationTargetException);
      }  
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\transition\ViewGroupUtilsApi18.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */