package android.support.v4.graphics.drawable;

import android.support.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompatParcelizer;
import androidx.versionedparcelable.VersionedParcel;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public final class IconCompatParcelizer extends IconCompatParcelizer {
  public static IconCompat read(VersionedParcel paramVersionedParcel) {
    return IconCompatParcelizer.read(paramVersionedParcel);
  }
  
  public static void write(IconCompat paramIconCompat, VersionedParcel paramVersionedParcel) {
    IconCompatParcelizer.write(paramIconCompat, paramVersionedParcel);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\graphics\drawable\IconCompatParcelizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */