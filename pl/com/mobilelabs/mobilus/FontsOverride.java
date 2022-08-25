package pl.com.mobilelabs.mobilus;

import android.content.Context;
import android.graphics.Typeface;
import java.lang.reflect.Field;

public class FontsOverride {
  protected static void replaceFont(String paramString, Typeface paramTypeface) {
    try {
      Field field = Typeface.class.getDeclaredField(paramString);
      field.setAccessible(true);
      field.set((Object)null, paramTypeface);
    } catch (NoSuchFieldException noSuchFieldException) {
      noSuchFieldException.printStackTrace();
    } catch (IllegalAccessException illegalAccessException) {
      illegalAccessException.printStackTrace();
    } 
  }
  
  public static void setDefaultFont(Context paramContext, String paramString1, String paramString2) {
    replaceFont(paramString1, Typeface.createFromAsset(paramContext.getAssets(), paramString2));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\FontsOverride.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */