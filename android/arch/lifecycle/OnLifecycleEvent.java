package android.arch.lifecycle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface OnLifecycleEvent {
  Lifecycle.Event value();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\arch\lifecycle\OnLifecycleEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */