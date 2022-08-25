package com.google.android.gms.common.oob;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;

@VisibleForTesting
public class SignUp {
  public static final String ACTION_OOB_SIGN_UP = "com.google.android.gms.common.oob.OOB_SIGN_UP";
  
  public static final String EXTRAS_CALLING_APP_DESCRIPTION = "com.google.android.gms.common.oob.EXTRAS_APP_DESCRIPTION";
  
  public static final String EXTRAS_CLIENT_CALLING_APP_PACKAGE = "com.google.android.gms.common.oob.EXTRAS_CLIENT_CALLING_APP_PACKAGE";
  
  public static final String EXTRAS_PROMO_APP_PACKAGE = "com.google.android.gms.common.oob.EXTRAS_PROMO_APP_PACKAGE";
  
  public static final String EXTRAS_PROMO_APP_TEXT = "com.google.android.gms.common.oob.EXTRAS_PROMO_APP_TEXT";
  
  public static final String EXTRA_ACCOUNT_NAME = "com.google.android.gms.common.oob.EXTRA_ACCOUNT_NAME";
  
  public static final String EXTRA_BACK_BUTTON_NAME = "com.google.android.gms.common.oob.EXTRA_BACK_BUTTON_NAME";
  
  public static final String EXTRA_GPSRC = "com.google.android.gms.plus.GPSRC";
  
  public static final String EXTRA_OVERRIDE_THEME = "com.google.android.gms.plus.OVERRIDE_THEME";
  
  public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES = BaseGmsClient.GOOGLE_PLUS_REQUIRED_FEATURES;
  
  public static final int THEME_DEFAULT = 0;
  
  public static final int THEME_FULL = 1;
  
  public static final int THEME_SETUP_WIZARD = 2;
  
  public static AccountManagerFuture<Boolean> checkSignUpState(Context paramContext, String paramString, String[] paramArrayOfString, AccountManagerCallback<Boolean> paramAccountManagerCallback, Handler paramHandler) {
    boolean bool1;
    Preconditions.checkArgument(TextUtils.isEmpty(paramString) ^ true, "The accountName is required");
    byte b = 0;
    if (paramArrayOfString != null) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    Preconditions.checkArgument(bool1, "The requiredFeatures parameter is required");
    AccountManager accountManager = AccountManager.get(paramContext);
    Account[] arrayOfAccount = accountManager.getAccountsByType("com.google");
    int i = arrayOfAccount.length;
    boolean bool2 = false;
    while (b < i) {
      if (paramString.equals((arrayOfAccount[b]).name))
        bool2 = true; 
      b++;
    } 
    if (!bool2)
      throw new IllegalStateException("Given account does not exist on the device"); 
    return accountManager.hasFeatures(new Account(paramString, "com.google"), paramArrayOfString, paramAccountManagerCallback, paramHandler);
  }
  
  public static boolean isSignedUpBlocking(Context paramContext, String paramString, String[] paramArrayOfString) throws AuthenticatorException, OperationCanceledException, IOException {
    return ((Boolean)checkSignUpState(paramContext, paramString, paramArrayOfString, null, null).getResult()).booleanValue();
  }
  
  public static Intent newSignUpIntent(String paramString) {
    return newSignUpIntent(paramString, null);
  }
  
  public static Intent newSignUpIntent(String paramString1, String paramString2) {
    Intent intent = new Intent();
    intent.setPackage("com.google.android.gms");
    intent.setAction("com.google.android.gms.common.oob.OOB_SIGN_UP");
    intent.putExtra("com.google.android.gms.common.oob.EXTRA_ACCOUNT_NAME", paramString1);
    intent.putExtra("com.google.android.gms.common.oob.EXTRA_BACK_BUTTON_NAME", paramString2);
    return intent;
  }
  
  public static Intent newSignUpIntent(String paramString1, String paramString2, String paramString3, String paramString4) {
    Intent intent = new Intent();
    intent.setPackage("com.google.android.gms");
    intent.setAction("com.google.android.gms.common.oob.OOB_SIGN_UP");
    intent.putExtra("com.google.android.gms.common.oob.EXTRA_ACCOUNT_NAME", paramString1);
    intent.putExtra("com.google.android.gms.common.oob.EXTRA_BACK_BUTTON_NAME", paramString2);
    intent.putExtra("com.google.android.gms.common.oob.EXTRAS_PROMO_APP_PACKAGE", paramString3);
    intent.putExtra("com.google.android.gms.common.oob.EXTRAS_PROMO_APP_TEXT", paramString4);
    return intent;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\oob\SignUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */