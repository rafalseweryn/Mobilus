package butterknife.internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
public @interface ListenerClass {
  Class<? extends Enum<?>> callbacks() default NONE.class;
  
  ListenerMethod[] method() default {};
  
  String remover() default "";
  
  String setter();
  
  String targetType();
  
  String type();
  
  public enum NONE {
  
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\butterknife\internal\ListenerClass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */