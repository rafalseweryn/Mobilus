package javax.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.PACKAGE, ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER})
public @interface Generated {
  String comments() default "";
  
  String date() default "";
  
  String[] value();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\javax\annotation\Generated.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */