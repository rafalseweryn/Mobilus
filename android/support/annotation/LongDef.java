package android.support.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.ANNOTATION_TYPE})
public @interface LongDef {
  boolean flag() default false;
  
  long[] value() default {};
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\annotation\LongDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */