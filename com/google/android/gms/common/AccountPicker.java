package com.google.android.gms.common;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;

public final class AccountPicker {
  public static final int CUSTOM_THEME_ACCOUNT_CHIPS = 2;
  
  public static final int CUSTOM_THEME_GAMES = 1;
  
  public static final int CUSTOM_THEME_NONE = 0;
  
  public static final String EXTRA_ADD_ACCOUNT_AUTH_TOKEN_TYPE_STRING = "authTokenType";
  
  public static final String EXTRA_ADD_ACCOUNT_OPTIONS_BUNDLE = "addAccountOptions";
  
  public static final String EXTRA_ADD_ACCOUNT_REQUIRED_FEATURES_STRING_ARRAY = "addAccountRequiredFeatures";
  
  public static final String EXTRA_ALLOWABLE_ACCOUNTS_ARRAYLIST = "allowableAccounts";
  
  public static final String EXTRA_ALLOWABLE_ACCOUNT_TYPES_STRING_ARRAY = "allowableAccountTypes";
  
  public static final String EXTRA_ALWAYS_PROMPT_FOR_ACCOUNT = "alwaysPromptForAccount";
  
  public static final String EXTRA_DESCRIPTION_TEXT_OVERRIDE = "descriptionTextOverride";
  
  public static final String EXTRA_HOSTED_DOMAIN_FILTER = "hostedDomainFilter";
  
  public static final String EXTRA_IS_ACCOUNT_CHIPS_ACCOUNT_PICKER = "pickedFromAccountChips";
  
  public static final String EXTRA_OVERRIDE_CUSTOM_THEME = "overrideCustomTheme";
  
  public static final String EXTRA_OVERRIDE_THEME = "overrideTheme";
  
  public static final String EXTRA_REAL_CLIENT_PACKAGE = "realClientPackage";
  
  public static final String EXTRA_SELECTED_ACCOUNT = "selectedAccount";
  
  public static final String EXTRA_SET_GMS_CORE_ACCOUNT = "setGmsCoreAccount";
  
  public static final int THEME_DEFAULT = 0;
  
  public static final int THEME_LIGHT = 1;
  
  public static Intent newChooseAccountIntent(Account paramAccount, ArrayList<Account> paramArrayList, String[] paramArrayOfString1, boolean paramBoolean, String paramString1, String paramString2, String[] paramArrayOfString2, Bundle paramBundle) {
    return newChooseAccountIntent(paramAccount, paramArrayList, paramArrayOfString1, paramBoolean, paramString1, paramString2, paramArrayOfString2, paramBundle, false);
  }
  
  public static Intent newChooseAccountIntent(Account paramAccount, ArrayList<Account> paramArrayList, String[] paramArrayOfString1, boolean paramBoolean1, String paramString1, String paramString2, String[] paramArrayOfString2, Bundle paramBundle, boolean paramBoolean2) {
    return newChooseAccountIntent(paramAccount, paramArrayList, paramArrayOfString1, paramBoolean1, paramString1, paramString2, paramArrayOfString2, paramBundle, paramBoolean2, 0, 0);
  }
  
  public static Intent newChooseAccountIntent(Account paramAccount, ArrayList<Account> paramArrayList, String[] paramArrayOfString1, boolean paramBoolean1, String paramString1, String paramString2, String[] paramArrayOfString2, Bundle paramBundle, boolean paramBoolean2, int paramInt1, int paramInt2) {
    return newChooseAccountIntent(paramAccount, paramArrayList, paramArrayOfString1, paramBoolean1, paramString1, paramString2, paramArrayOfString2, paramBundle, paramBoolean2, paramInt1, paramInt2, null, false);
  }
  
  public static Intent newChooseAccountIntent(Account paramAccount, ArrayList<Account> paramArrayList, String[] paramArrayOfString1, boolean paramBoolean1, String paramString1, String paramString2, String[] paramArrayOfString2, Bundle paramBundle, boolean paramBoolean2, int paramInt1, int paramInt2, String paramString3, boolean paramBoolean3) {
    String str;
    Intent intent = new Intent();
    if (!paramBoolean3) {
      boolean bool;
      if (paramString3 == null) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkArgument(bool, "We only support hostedDomain filter for account chip styled account picker");
    } 
    if (paramBoolean3) {
      str = "com.google.android.gms.common.account.CHOOSE_ACCOUNT_USERTILE";
    } else {
      str = "com.google.android.gms.common.account.CHOOSE_ACCOUNT";
    } 
    intent.setAction(str);
    intent.setPackage("com.google.android.gms");
    intent.putExtra("allowableAccounts", paramArrayList);
    intent.putExtra("allowableAccountTypes", paramArrayOfString1);
    intent.putExtra("addAccountOptions", paramBundle);
    intent.putExtra("selectedAccount", (Parcelable)paramAccount);
    intent.putExtra("alwaysPromptForAccount", paramBoolean1);
    intent.putExtra("descriptionTextOverride", paramString1);
    intent.putExtra("authTokenType", paramString2);
    intent.putExtra("addAccountRequiredFeatures", paramArrayOfString2);
    intent.putExtra("setGmsCoreAccount", paramBoolean2);
    intent.putExtra("overrideTheme", paramInt1);
    intent.putExtra("overrideCustomTheme", paramInt2);
    intent.putExtra("hostedDomainFilter", paramString3);
    return intent;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\AccountPicker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */