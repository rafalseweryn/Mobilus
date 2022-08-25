package androidx.versionedparcelable;

import android.support.annotation.RestrictTo;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD})
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public @interface NonParcelField {}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\androidx\versionedparcelable\NonParcelField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */