package android.arch.lifecycle;

import android.support.annotation.NonNull;

@Deprecated
public interface LifecycleRegistryOwner extends LifecycleOwner {
  @NonNull
  LifecycleRegistry getLifecycle();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\arch\lifecycle\LifecycleRegistryOwner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */