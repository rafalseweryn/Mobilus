package butterknife;

import android.support.annotation.ColorRes;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD})
public @interface BindColor {
  @ColorRes
  int value();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\butterknife\BindColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */