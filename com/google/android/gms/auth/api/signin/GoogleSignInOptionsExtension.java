package com.google.android.gms.auth.api.signin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Scope;
import java.util.List;

public interface GoogleSignInOptionsExtension {
  public static final int FALLBACK_SIGN_IN = 2;
  
  public static final int FITNESS = 3;
  
  public static final int GAMES = 1;
  
  @TypeId
  int getExtensionType();
  
  @Nullable
  List<Scope> getImpliedScopes();
  
  Bundle toBundle();
  
  public static @interface TypeId {}
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\auth\api\signin\GoogleSignInOptionsExtension.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */