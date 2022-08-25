package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.activity.BackButtonHeaderActivity;

public class UserPasswordSettingsActivity extends BackButtonHeaderActivity {
  private static final String TAG = "ChangePassword";
  
  public static final String USERNAME = "UserPasswordSettingsActivity.username";
  
  @BindView(2131296318)
  protected LinearLayout applyButton;
  
  private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.updateUserResponseReceived") && param1Intent.hasExtra("ManagementService.Intents.updateUserResponseReceivedOperationStatus")) {
          boolean bool = false;
          switch (param1Intent.getIntExtra("ManagementService.Intents.updateUserResponseReceivedOperationStatus", 0)) {
            case 2:
              Toast.makeText(param1Context, 2131624139, 0).show();
              break;
            case 1:
              Toast.makeText(param1Context, 2131624103, 0).show();
              break;
            case 0:
              Toast.makeText(param1Context, 2131624141, 0).show();
              bool = true;
              break;
          } 
          if (bool)
            UserPasswordSettingsActivity.this.finish(); 
        } 
      }
    };
  
  @BindView(2131296321)
  protected TextInputEditText newPasswordEditText;
  
  @BindView(2131296320)
  protected TextInputLayout newPasswordTextInput;
  
  @BindView(2131296323)
  protected TextInputEditText oldPasswordEditText;
  
  @BindView(2131296322)
  protected TextInputLayout oldPasswordTextInput;
  
  @BindView(2131296325)
  protected TextInputEditText repeatPasswordEditText;
  
  @BindView(2131296324)
  protected TextInputLayout repeatPasswordTextInput;
  
  @BindView(2131296326)
  protected TextView usernameTextView;
  
  private void changePassword() {
    this.managementBinder.sendChangePasswordRequest(this.oldPasswordEditText.getText().toString(), this.newPasswordEditText.getText().toString());
  }
  
  private boolean checkValidity() {
    if (!this.managementBinder.isPasswordValid(this.oldPasswordEditText.getText().toString())) {
      this.oldPasswordTextInput.setError(getString(2131624226));
      return false;
    } 
    if (!this.newPasswordEditText.getText().toString().contentEquals((CharSequence)this.repeatPasswordEditText.getText())) {
      this.newPasswordTextInput.setError(getString(2131624144));
      this.repeatPasswordTextInput.setError(getString(2131624144));
    } 
    return true;
  }
  
  private void clearPasswordValidityError() {
    if (this.newPasswordEditText.getText().toString().contentEquals((CharSequence)this.repeatPasswordEditText.getText())) {
      this.newPasswordTextInput.setError(null);
      this.repeatPasswordTextInput.setError(null);
    } 
  }
  
  @OnClick({2131296318})
  protected void applyButtonClicked() {
    if (checkValidity())
      if (this.newPasswordEditText.getText().toString().isEmpty()) {
        Snackbar.make(getSettingsView(), 2131624070, 0).setAction(getString(2131624061).toUpperCase(), new View.OnClickListener() {
              public void onClick(View param1View) {
                UserPasswordSettingsActivity.this.changePassword();
              }
            }).show();
      } else {
        changePassword();
      }  
  }
  
  @OnClick({2131296319})
  protected void cancelButtonClicked() {
    finish();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    initializeActivity(2131492969);
    configureView(2131558767, 2131624185, 2131624046);
    ButterKnife.bind((Activity)this);
    IntentFilter intentFilter = new IntentFilter("ManagementService.Intents.updateUserResponseReceived");
    LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.broadcastReceiver, intentFilter);
    if (getIntent().hasExtra("UserPasswordSettingsActivity.username"))
      this.usernameTextView.setText(getIntent().getStringExtra("UserPasswordSettingsActivity.username")); 
    this.oldPasswordEditText.addTextChangedListener(new TextWatcher() {
          public void afterTextChanged(Editable param1Editable) {}
          
          public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
          
          public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            if (UserPasswordSettingsActivity.this.oldPasswordTextInput.getError() != null && UserPasswordSettingsActivity.this.managementBinder.isPasswordValid(UserPasswordSettingsActivity.this.oldPasswordEditText.getText().toString()))
              UserPasswordSettingsActivity.this.oldPasswordTextInput.setError(null); 
          }
        });
    this.newPasswordEditText.addTextChangedListener(new TextWatcher() {
          public void afterTextChanged(Editable param1Editable) {}
          
          public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
          
          public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            if (UserPasswordSettingsActivity.this.newPasswordTextInput.getError() != null)
              UserPasswordSettingsActivity.this.clearPasswordValidityError(); 
          }
        });
    this.repeatPasswordEditText.addTextChangedListener(new TextWatcher() {
          public void afterTextChanged(Editable param1Editable) {}
          
          public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
          
          public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            if (UserPasswordSettingsActivity.this.repeatPasswordTextInput.getError() != null)
              UserPasswordSettingsActivity.this.clearPasswordValidityError(); 
          }
        });
  }
  
  protected void onDestroy() {
    LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(this.broadcastReceiver);
    super.onDestroy();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\UserPasswordSettingsActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */