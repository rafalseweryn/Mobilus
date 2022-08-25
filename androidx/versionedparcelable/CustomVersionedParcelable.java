package androidx.versionedparcelable;

import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class CustomVersionedParcelable implements VersionedParcelable {
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void onPostParceling() {}
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void onPreParceling(boolean paramBoolean) {}
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\androidx\versionedparcelable\CustomVersionedParcelable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */