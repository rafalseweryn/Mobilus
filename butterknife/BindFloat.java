package butterknife;

import android.support.annotation.DimenRes;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD})
public @interface BindFloat {
  @DimenRes
  int value();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\butterknife\BindFloat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */