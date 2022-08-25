package butterknife;

import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD})
public @interface BindDrawable {
  @AttrRes
  int tint() default 0;
  
  @DrawableRes
  int value();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\butterknife\BindDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */