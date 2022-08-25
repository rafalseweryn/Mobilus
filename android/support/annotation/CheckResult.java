package android.support.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD})
public @interface CheckResult {
  String suggest() default "";
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\annotation\CheckResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */