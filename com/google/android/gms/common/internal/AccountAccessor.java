package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtilLight;

public class AccountAccessor extends IAccountAccessor.Stub {
  private Context mContext;
  
  private int zzqu = -1;
  
  private Account zzs;
  
  public AccountAccessor(Context paramContext, Account paramAccount) {
    this.mContext = paramContext.getApplicationContext();
    this.zzs = paramAccount;
  }
  
  public static AccountAccessor fromGoogleAccountName(Context paramContext, String paramString) {
    Account account;
    if (TextUtils.isEmpty(paramString)) {
      paramString = null;
    } else {
      account = new Account(paramString, "com.google");
    } 
    return new AccountAccessor(paramContext, account);
  }
  
  public static Account getAccountBinderSafe(IAccountAccessor paramIAccountAccessor) {
    if (paramIAccountAccessor != null) {
      long l = Binder.clearCallingIdentity();
      try {
        Account account = paramIAccountAccessor.getAccount();
        Binder.restoreCallingIdentity(l);
        return account;
      } catch (RemoteException remoteException) {
        Log.w("AccountAccessor", "Remote account accessor probably died");
        Binder.restoreCallingIdentity(l);
      } finally {}
    } 
    return null;
  }
  
  public boolean equals(Object paramObject) {
    return (this == paramObject) ? true : (!(paramObject instanceof AccountAccessor) ? false : this.zzs.equals(((AccountAccessor)paramObject).zzs));
  }
  
  public Account getAccount() {
    int i = Binder.getCallingUid();
    if (i == this.zzqu)
      return this.zzs; 
    if (GooglePlayServicesUtilLight.isGooglePlayServicesUid(this.mContext, i)) {
      this.zzqu = i;
      return this.zzs;
    } 
    throw new SecurityException("Caller is not GooglePlayServices");
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\AccountAccessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */