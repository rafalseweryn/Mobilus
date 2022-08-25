package android.support.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.LOCAL_VARIABLE, ElementType.FIELD})
public @interface ColorInt {}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\annotation\ColorInt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */