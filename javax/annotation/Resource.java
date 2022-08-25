package javax.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
public @interface Resource {
  AuthenticationType authenticationType() default AuthenticationType.CONTAINER;
  
  String description() default "";
  
  String mappedName() default "";
  
  String name() default "";
  
  boolean shareable() default true;
  
  Class type() default Object.class;
  
  public enum AuthenticationType {
    APPLICATION, CONTAINER;
    
    static {
      $VALUES = new AuthenticationType[] { CONTAINER, APPLICATION };
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\javax\annotation\Resource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */