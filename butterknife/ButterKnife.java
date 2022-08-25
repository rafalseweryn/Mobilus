package butterknife;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.CheckResult;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.util.Property;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ButterKnife {
  @VisibleForTesting
  static final Map<Class<?>, Constructor<? extends Unbinder>> BINDINGS = new LinkedHashMap<>();
  
  private static final String TAG = "ButterKnife";
  
  private static boolean debug = false;
  
  private ButterKnife() {
    throw new AssertionError("No instances.");
  }
  
  @TargetApi(14)
  @RequiresApi(14)
  @UiThread
  public static <T extends View, V> void apply(@NonNull T paramT, @NonNull Property<? super T, V> paramProperty, V paramV) {
    paramProperty.set(paramT, paramV);
  }
  
  @UiThread
  public static <T extends View> void apply(@NonNull T paramT, @NonNull Action<? super T> paramAction) {
    paramAction.apply(paramT, 0);
  }
  
  @UiThread
  public static <T extends View, V> void apply(@NonNull T paramT, @NonNull Setter<? super T, V> paramSetter, V paramV) {
    paramSetter.set(paramT, paramV, 0);
  }
  
  @SafeVarargs
  @UiThread
  public static <T extends View> void apply(@NonNull T paramT, @NonNull Action<? super T>... paramVarArgs) {
    int i = paramVarArgs.length;
    for (byte b = 0; b < i; b++)
      paramVarArgs[b].apply(paramT, 0); 
  }
  
  @TargetApi(14)
  @RequiresApi(14)
  @UiThread
  public static <T extends View, V> void apply(@NonNull List<T> paramList, @NonNull Property<? super T, V> paramProperty, V paramV) {
    int i = paramList.size();
    for (byte b = 0; b < i; b++)
      paramProperty.set(paramList.get(b), paramV); 
  }
  
  @UiThread
  public static <T extends View> void apply(@NonNull List<T> paramList, @NonNull Action<? super T> paramAction) {
    int i = paramList.size();
    for (byte b = 0; b < i; b++)
      paramAction.apply(paramList.get(b), b); 
  }
  
  @UiThread
  public static <T extends View, V> void apply(@NonNull List<T> paramList, @NonNull Setter<? super T, V> paramSetter, V paramV) {
    int i = paramList.size();
    for (byte b = 0; b < i; b++)
      paramSetter.set(paramList.get(b), paramV, b); 
  }
  
  @SafeVarargs
  @UiThread
  public static <T extends View> void apply(@NonNull List<T> paramList, @NonNull Action<? super T>... paramVarArgs) {
    int i = paramList.size();
    for (byte b = 0; b < i; b++) {
      int j = paramVarArgs.length;
      for (byte b1 = 0; b1 < j; b1++)
        paramVarArgs[b1].apply(paramList.get(b), b); 
    } 
  }
  
  @TargetApi(14)
  @RequiresApi(14)
  @UiThread
  public static <T extends View, V> void apply(@NonNull T[] paramArrayOfT, @NonNull Property<? super T, V> paramProperty, V paramV) {
    int i = paramArrayOfT.length;
    for (byte b = 0; b < i; b++)
      paramProperty.set(paramArrayOfT[b], paramV); 
  }
  
  @UiThread
  public static <T extends View> void apply(@NonNull T[] paramArrayOfT, @NonNull Action<? super T> paramAction) {
    int i = paramArrayOfT.length;
    for (byte b = 0; b < i; b++)
      paramAction.apply(paramArrayOfT[b], b); 
  }
  
  @UiThread
  public static <T extends View, V> void apply(@NonNull T[] paramArrayOfT, @NonNull Setter<? super T, V> paramSetter, V paramV) {
    int i = paramArrayOfT.length;
    for (byte b = 0; b < i; b++)
      paramSetter.set(paramArrayOfT[b], paramV, b); 
  }
  
  @SafeVarargs
  @UiThread
  public static <T extends View> void apply(@NonNull T[] paramArrayOfT, @NonNull Action<? super T>... paramVarArgs) {
    int i = paramArrayOfT.length;
    for (byte b = 0; b < i; b++) {
      int j = paramVarArgs.length;
      for (byte b1 = 0; b1 < j; b1++)
        paramVarArgs[b1].apply(paramArrayOfT[b], b); 
    } 
  }
  
  @NonNull
  @UiThread
  public static Unbinder bind(@NonNull Activity paramActivity) {
    return createBinding(paramActivity, paramActivity.getWindow().getDecorView());
  }
  
  @NonNull
  @UiThread
  public static Unbinder bind(@NonNull Dialog paramDialog) {
    return createBinding(paramDialog, paramDialog.getWindow().getDecorView());
  }
  
  @NonNull
  @UiThread
  public static Unbinder bind(@NonNull View paramView) {
    return createBinding(paramView, paramView);
  }
  
  @NonNull
  @UiThread
  public static Unbinder bind(@NonNull Object paramObject, @NonNull Activity paramActivity) {
    return createBinding(paramObject, paramActivity.getWindow().getDecorView());
  }
  
  @NonNull
  @UiThread
  public static Unbinder bind(@NonNull Object paramObject, @NonNull Dialog paramDialog) {
    return createBinding(paramObject, paramDialog.getWindow().getDecorView());
  }
  
  @NonNull
  @UiThread
  public static Unbinder bind(@NonNull Object paramObject, @NonNull View paramView) {
    return createBinding(paramObject, paramView);
  }
  
  private static Unbinder createBinding(@NonNull Object paramObject, @NonNull View paramView) {
    Class<?> clazz = paramObject.getClass();
    if (debug) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Looking up binding for ");
      stringBuilder.append(clazz.getName());
      Log.d("ButterKnife", stringBuilder.toString());
    } 
    Constructor<? extends Unbinder> constructor = findBindingConstructorForClass(clazz);
    if (constructor == null)
      return Unbinder.EMPTY; 
    try {
      return constructor.newInstance(new Object[] { paramObject, paramView });
    } catch (IllegalAccessException illegalAccessException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to invoke ");
      stringBuilder.append(constructor);
      throw new RuntimeException(stringBuilder.toString(), illegalAccessException);
    } catch (InstantiationException instantiationException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to invoke ");
      stringBuilder.append(constructor);
      throw new RuntimeException(stringBuilder.toString(), instantiationException);
    } catch (InvocationTargetException invocationTargetException) {
      Throwable throwable = invocationTargetException.getCause();
      if (throwable instanceof RuntimeException)
        throw (RuntimeException)throwable; 
      if (throwable instanceof Error)
        throw (Error)throwable; 
      throw new RuntimeException("Unable to create binding instance.", throwable);
    } 
  }
  
  @CheckResult
  @Nullable
  @UiThread
  private static Constructor<? extends Unbinder> findBindingConstructorForClass(Class<?> paramClass) {
    StringBuilder stringBuilder;
    Constructor<? extends Unbinder> constructor = BINDINGS.get(paramClass);
    if (constructor != null) {
      if (debug)
        Log.d("ButterKnife", "HIT: Cached in binding map."); 
      return constructor;
    } 
    String str = paramClass.getName();
    if (str.startsWith("android.") || str.startsWith("java.")) {
      if (debug)
        Log.d("ButterKnife", "MISS: Reached framework class. Abandoning search."); 
      return null;
    } 
    try {
      ClassLoader classLoader = paramClass.getClassLoader();
      StringBuilder stringBuilder1 = new StringBuilder();
      this();
      stringBuilder1.append(str);
      stringBuilder1.append("_ViewBinding");
      Constructor<?> constructor2 = classLoader.loadClass(stringBuilder1.toString()).getConstructor(new Class[] { paramClass, View.class });
      Constructor<?> constructor1 = constructor2;
      if (debug) {
        Log.d("ButterKnife", "HIT: Loaded binding class and constructor.");
        constructor1 = constructor2;
      } 
    } catch (ClassNotFoundException classNotFoundException) {
      if (debug) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Not found. Trying superclass ");
        stringBuilder1.append(paramClass.getSuperclass().getName());
        Log.d("ButterKnife", stringBuilder1.toString());
      } 
      Constructor<? extends Unbinder> constructor1 = findBindingConstructorForClass(paramClass.getSuperclass());
    } catch (NoSuchMethodException noSuchMethodException) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to find binding constructor for ");
      stringBuilder.append(str);
      throw new RuntimeException(stringBuilder.toString(), noSuchMethodException);
    } 
    BINDINGS.put(stringBuilder, noSuchMethodException);
    return (Constructor<? extends Unbinder>)noSuchMethodException;
  }
  
  @Deprecated
  @CheckResult
  public static <T extends View> T findById(@NonNull Activity paramActivity, @IdRes int paramInt) {
    return (T)paramActivity.findViewById(paramInt);
  }
  
  @Deprecated
  @CheckResult
  public static <T extends View> T findById(@NonNull Dialog paramDialog, @IdRes int paramInt) {
    return (T)paramDialog.findViewById(paramInt);
  }
  
  @Deprecated
  @CheckResult
  public static <T extends View> T findById(@NonNull View paramView, @IdRes int paramInt) {
    return (T)paramView.findViewById(paramInt);
  }
  
  public static void setDebug(boolean paramBoolean) {
    debug = paramBoolean;
  }
  
  public static interface Action<T extends View> {
    @UiThread
    void apply(@NonNull T param1T, int param1Int);
  }
  
  public static interface Setter<T extends View, V> {
    @UiThread
    void set(@NonNull T param1T, V param1V, int param1Int);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\butterknife\ButterKnife.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */