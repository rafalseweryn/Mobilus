package android.support.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface VisibleForTesting {
  public static final int NONE = 5;
  
  public static final int PACKAGE_PRIVATE = 3;
  
  public static final int PRIVATE = 2;
  
  public static final int PROTECTED = 4;
  
  int otherwise() default 2;
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\annotation\VisibleForTesting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */