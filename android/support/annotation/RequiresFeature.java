package android.support.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface RequiresFeature {
  String enforcement();
  
  String name();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\annotation\RequiresFeature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */