package butterknife.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.TypedValue;
import android.view.View;
import java.lang.reflect.Array;
import java.util.List;

public final class Utils {
  private static final TypedValue VALUE = new TypedValue();
  
  private Utils() {
    throw new AssertionError("No instances.");
  }
  
  @SafeVarargs
  public static <T> T[] arrayOf(T... paramVarArgs) {
    return filterNull(paramVarArgs);
  }
  
  public static <T> T castParam(Object paramObject, String paramString1, int paramInt1, String paramString2, int paramInt2, Class<T> paramClass) {
    try {
      return paramClass.cast(paramObject);
    } catch (ClassCastException classCastException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Parameter #");
      stringBuilder.append(paramInt1 + 1);
      stringBuilder.append(" of method '");
      stringBuilder.append(paramString1);
      stringBuilder.append("' was of the wrong type for parameter #");
      stringBuilder.append(paramInt2 + 1);
      stringBuilder.append(" of method '");
      stringBuilder.append(paramString2);
      stringBuilder.append("'. See cause for more info.");
      throw new IllegalStateException(stringBuilder.toString(), classCastException);
    } 
  }
  
  public static <T> T castView(View paramView, @IdRes int paramInt, String paramString, Class<T> paramClass) {
    try {
      return paramClass.cast(paramView);
    } catch (ClassCastException classCastException) {
      String str = getResourceEntryName(paramView, paramInt);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("View '");
      stringBuilder.append(str);
      stringBuilder.append("' with ID ");
      stringBuilder.append(paramInt);
      stringBuilder.append(" for ");
      stringBuilder.append(paramString);
      stringBuilder.append(" was of the wrong type. See cause for more info.");
      throw new IllegalStateException(stringBuilder.toString(), classCastException);
    } 
  }
  
  private static <T> T[] filterNull(T[] paramArrayOfT) {
    int i = paramArrayOfT.length;
    byte b = 0;
    int j;
    for (j = b; b < i; j = k) {
      T t = paramArrayOfT[b];
      int k = j;
      if (t != null) {
        paramArrayOfT[j] = t;
        k = j + 1;
      } 
      b++;
    } 
    if (j == i)
      return paramArrayOfT; 
    Object[] arrayOfObject = (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), j);
    System.arraycopy(paramArrayOfT, 0, arrayOfObject, 0, j);
    return (T[])arrayOfObject;
  }
  
  public static <T> T findOptionalViewAsType(View paramView, @IdRes int paramInt, String paramString, Class<T> paramClass) {
    return castView(paramView.findViewById(paramInt), paramInt, paramString, paramClass);
  }
  
  public static View findRequiredView(View paramView, @IdRes int paramInt, String paramString) {
    View view = paramView.findViewById(paramInt);
    if (view != null)
      return view; 
    String str = getResourceEntryName(paramView, paramInt);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Required view '");
    stringBuilder.append(str);
    stringBuilder.append("' with ID ");
    stringBuilder.append(paramInt);
    stringBuilder.append(" for ");
    stringBuilder.append(paramString);
    stringBuilder.append(" was not found. If this view is optional add '@Nullable' (fields) or '@Optional' (methods) annotation.");
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public static <T> T findRequiredViewAsType(View paramView, @IdRes int paramInt, String paramString, Class<T> paramClass) {
    return castView(findRequiredView(paramView, paramInt, paramString), paramInt, paramString, paramClass);
  }
  
  @UiThread
  public static float getFloat(Context paramContext, @DimenRes int paramInt) {
    TypedValue typedValue = VALUE;
    paramContext.getResources().getValue(paramInt, typedValue, true);
    if (typedValue.type == 4)
      return typedValue.getFloat(); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Resource ID #0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    stringBuilder.append(" type #0x");
    stringBuilder.append(Integer.toHexString(typedValue.type));
    stringBuilder.append(" is not valid");
    throw new Resources.NotFoundException(stringBuilder.toString());
  }
  
  private static String getResourceEntryName(View paramView, @IdRes int paramInt) {
    return paramView.isInEditMode() ? "<unavailable while editing>" : paramView.getContext().getResources().getResourceEntryName(paramInt);
  }
  
  @UiThread
  public static Drawable getTintedDrawable(Context paramContext, @DrawableRes int paramInt1, @AttrRes int paramInt2) {
    if (!paramContext.getTheme().resolveAttribute(paramInt2, VALUE, true)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Required tint color attribute with name ");
      stringBuilder.append(paramContext.getResources().getResourceEntryName(paramInt2));
      stringBuilder.append(" and attribute ID ");
      stringBuilder.append(paramInt2);
      stringBuilder.append(" was not found.");
      throw new Resources.NotFoundException(stringBuilder.toString());
    } 
    Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(paramContext, paramInt1).mutate());
    DrawableCompat.setTint(drawable, ContextCompat.getColor(paramContext, VALUE.resourceId));
    return drawable;
  }
  
  @SafeVarargs
  public static <T> List<T> listOf(T... paramVarArgs) {
    return new ImmutableList<>(filterNull(paramVarArgs));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\butterknife\internal\Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */