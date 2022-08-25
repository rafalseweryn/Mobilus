package javax.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface PostConstruct {}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\javax\annotation\PostConstruct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */