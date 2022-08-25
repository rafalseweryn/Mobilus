package pl.com.mobilelabs.mobilus.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.EditText;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import pl.com.mobilelabs.mobilus.model.ConnectionStatus;
import pl.com.mobilelabs.mobilus.services.settingsservice.SettingsService;

public class LoginActivity extends BaseActivity {
  private static final String ACTIVITY_EXTRA_EXIT = "ACTIVITY_EXTRA_EXIT";
  
  private static final String TAG = "LoginActivity";
  
  @BindView(2131296384)
  EditText mIpAddressView;
  
  @BindView(2131296388)
  LinearLayout mLocalAccessButton;
  
  @BindView(2131296648)
  View mLoginFormView;
  
  @BindView(2131296390)
  EditText mLoginView;
  
  @BindView(2131296385)
  EditText mPasswordView;
  
  @BindView(2131296649)
  View mProgressView;
  
  @BindView(2131296389)
  LinearLayout mRemoteAccessButton;
  
  private boolean remoteAccessAvailable;
  
  private SettingsService settingsService;
  
  private void moveToDashboard() {
    startActivity(new Intent((Context)this, DashboardActivity.class));
    finish();
  }
  
  private void setRemoteAccessButtonState() {
    if (this.managementBinder != null) {
      this.remoteAccessAvailable = this.managementBinder.hasCentralSerialNumber();
      if (this.remoteAccessAvailable && this.mLoginView.getText().length() > 0 && this.mPasswordView.getText().length() > 0) {
        this.mRemoteAccessButton.setBackgroundResource(2131230868);
        this.mRemoteAccessButton.setEnabled(true);
      } 
    } 
  }
  
  @OnClick({2131296383})
  public void demoMode() {
    if (this.managementBinder == null) {
      showMessage(getResources().getString(2131624174));
    } else {
      this.managementBinder.setConnectionType(ConnectionStatus.DEMO_MODE);
      moveToDashboard();
    } 
  }
  
  @OnClick({2131296388})
  public void loginLocally() {
    if (this.managementBinder == null) {
      showMessage(getResources().getString(2131624174));
    } else {
      if (this.mLoginView.getText() == null || this.mLoginView.getText().toString().isEmpty()) {
        showMessage(getString(2131624076));
        return;
      } 
      if (this.mPasswordView.getText() == null || this.mPasswordView.getText().toString().isEmpty()) {
        showMessage(getString(2131624076));
        return;
      } 
      if (this.mIpAddressView.getText() == null || this.mIpAddressView.getText().toString().isEmpty()) {
        showMessage(getString(2131624174));
        return;
      } 
      try {
        InetAddress inetAddress = Inet4Address.getByName(this.mIpAddressView.getText().toString());
      } catch (UnknownHostException unknownHostException) {
        unknownHostException = null;
      } 
      if (unknownHostException != null) {
        this.managementBinder.setConnectionOptions((InetAddress)unknownHostException, this.mLoginView.getText().toString(), this.mPasswordView.getText().toString());
        this.managementBinder.setConnectionType(ConnectionStatus.LOCAL_CONNECTION);
        moveToDashboard();
      } else {
        showMessage(getString(2131624174));
      } 
    } 
  }
  
  @OnClick({2131296389})
  public void loginRemotely() {
    if (this.managementBinder == null || !this.managementBinder.hasCentralSerialNumber()) {
      showMessage(getResources().getString(2131624174));
      return;
    } 
    if (this.mLoginView.getText() == null || this.mLoginView.getText().toString().isEmpty()) {
      showMessage(getString(2131624076));
      return;
    } 
    if (this.mPasswordView.getText() == null || this.mPasswordView.getText().toString().isEmpty()) {
      showMessage(getString(2131624076));
      return;
    } 
    this.managementBinder.setConnectionOptions(this.mLoginView.getText().toString(), this.mPasswordView.getText().toString());
    this.managementBinder.setConnectionType(ConnectionStatus.REMOTE_CONNECTION);
    moveToDashboard();
  }
  
  protected void managementServiceConnected() {
    setRemoteAccessButtonState();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == -1 && paramIntent.hasExtra("pl.com.mobilelabs.mobilus.view.activity.FindDeviceLocalIpActivity.foundIpAddress"))
      this.mIpAddressView.setText(paramIntent.getStringExtra("pl.com.mobilelabs.mobilus.view.activity.FindDeviceLocalIpActivity.foundIpAddress")); 
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    if (getIntent().getBooleanExtra("ACTIVITY_EXTRA_EXIT", false)) {
      startActivity(new Intent((Context)this, DummyActivity.class));
      finish();
      return;
    } 
    this.settingsService = new SettingsService((Context)this);
    setContentView(2131492896);
    ButterKnife.bind((Activity)this);
    LocalRemoteButtonsBackgroundChanger localRemoteButtonsBackgroundChanger = new LocalRemoteButtonsBackgroundChanger();
    this.mLoginView.addTextChangedListener(localRemoteButtonsBackgroundChanger);
    this.mPasswordView.addTextChangedListener(localRemoteButtonsBackgroundChanger);
    this.mIpAddressView.addTextChangedListener(localRemoteButtonsBackgroundChanger);
  }
  
  public void onResume() {
    super.onResume();
    setRemoteAccessButtonState();
  }
  
  protected void onStart() {
    super.onStart();
    if (this.settingsService.getCentralIpAddress() != null)
      this.mIpAddressView.setText(this.settingsService.getCentralIpAddress().getHostAddress()); 
    if (this.settingsService.getUserLogin() != null)
      this.mLoginView.setText(this.settingsService.getUserLogin()); 
  }
  
  public void showMessage(String paramString) {
    AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
    builder.setMessage(paramString).setTitle(2131624218);
    builder.create().show();
  }
  
  @TargetApi(13)
  public void showProgress(final boolean show) {
    int i = Build.VERSION.SDK_INT;
    int j = 0;
    boolean bool = false;
    if (i >= 13) {
      float f2;
      j = getResources().getInteger(17694720);
      View view2 = this.mLoginFormView;
      if (show) {
        i = 8;
      } else {
        i = 0;
      } 
      view2.setVisibility(i);
      ViewPropertyAnimator viewPropertyAnimator2 = this.mLoginFormView.animate();
      long l = j;
      viewPropertyAnimator2 = viewPropertyAnimator2.setDuration(l);
      float f1 = 1.0F;
      if (show) {
        f2 = 0.0F;
      } else {
        f2 = 1.0F;
      } 
      viewPropertyAnimator2.alpha(f2).setListener((Animator.AnimatorListener)new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator param1Animator) {
              boolean bool;
              View view = LoginActivity.this.mLoginFormView;
              if (show) {
                bool = true;
              } else {
                bool = false;
              } 
              view.setVisibility(bool);
            }
          });
      View view1 = this.mProgressView;
      if (show) {
        i = bool;
      } else {
        i = 8;
      } 
      view1.setVisibility(i);
      ViewPropertyAnimator viewPropertyAnimator1 = this.mProgressView.animate().setDuration(l);
      if (show) {
        f2 = f1;
      } else {
        f2 = 0.0F;
      } 
      viewPropertyAnimator1.alpha(f2).setListener((Animator.AnimatorListener)new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator param1Animator) {
              byte b;
              View view = LoginActivity.this.mProgressView;
              if (show) {
                b = 0;
              } else {
                b = 8;
              } 
              view.setVisibility(b);
            }
          });
    } else {
      View view = this.mProgressView;
      if (show) {
        i = 0;
      } else {
        i = 8;
      } 
      view.setVisibility(i);
      view = this.mLoginFormView;
      i = j;
      if (show)
        i = 8; 
      view.setVisibility(i);
    } 
  }
  
  @OnClick({2131296386})
  public void startSearchDeviceLocalIp() {
    this.mIpAddressView.setText("");
    startActivityForResult(new Intent((Context)this, FindDeviceLocalIpActivity.class), 9827);
  }
  
  private class LocalRemoteButtonsBackgroundChanger implements TextWatcher {
    private LocalRemoteButtonsBackgroundChanger() {}
    
    public void afterTextChanged(Editable param1Editable) {}
    
    public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
    
    public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
      if (LoginActivity.this.mLoginView.getText().length() != 0) {
        param1Int1 = 1;
      } else {
        param1Int1 = 0;
      } 
      if (LoginActivity.this.mPasswordView.getText().length() != 0) {
        param1Int2 = 1;
      } else {
        param1Int2 = 0;
      } 
      if (LoginActivity.this.mIpAddressView.getText().length() != 0) {
        param1Int3 = 1;
      } else {
        param1Int3 = 0;
      } 
      if (param1Int1 == 0 || param1Int2 == 0 || param1Int3 == 0) {
        LoginActivity.this.mLocalAccessButton.setBackgroundResource(2131230871);
        LoginActivity.this.mLocalAccessButton.setEnabled(false);
      } else {
        LoginActivity.this.mLocalAccessButton.setBackgroundResource(2131230868);
        LoginActivity.this.mLocalAccessButton.setEnabled(true);
      } 
      if (!LoginActivity.this.remoteAccessAvailable || param1Int1 == 0 || param1Int2 == 0) {
        LoginActivity.this.mRemoteAccessButton.setBackgroundResource(2131230871);
        LoginActivity.this.mRemoteAccessButton.setEnabled(false);
        return;
      } 
      LoginActivity.this.mRemoteAccessButton.setBackgroundResource(2131230868);
      LoginActivity.this.mRemoteAccessButton.setEnabled(true);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\LoginActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */