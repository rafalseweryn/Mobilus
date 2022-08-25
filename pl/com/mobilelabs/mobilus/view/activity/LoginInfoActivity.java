package pl.com.mobilelabs.mobilus.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.model.ConnectionStatus;

public class LoginInfoActivity extends BaseActivity {
  public static final int REQUEST_CODE = 861;
  
  private static final String TAG = "LoginInfoActivity";
  
  @BindView(2131296381)
  TextInputLayout ipAddressLayout;
  
  @BindView(2131296393)
  ImageView mConnectionTypeImage;
  
  @BindView(2131296394)
  TextView mConnectionTypeText;
  
  @BindView(2131296380)
  EditText mIpAddressView;
  
  @BindView(2131296382)
  EditText mLoginView;
  
  @OnClick({2131296392})
  public void close() {
    finish();
  }
  
  @OnClick({2131296395})
  public void logout() {
    setResult(-1);
    finish();
  }
  
  protected void managementServiceConnected() {
    ConnectionStatus connectionStatus = this.managementBinder.getConnectionStatus();
    switch (connectionStatus) {
      default:
        return;
      case LOCAL_CONNECTION:
        this.mLoginView.setText(this.managementBinder.getUserLogin());
        this.mConnectionTypeImage.setImageResource(2131558470);
        this.mConnectionTypeText.setText(2131624043);
        if (this.managementBinder.getCentralLocalIpAddress() != null)
          this.mIpAddressView.setText(this.managementBinder.getCentralLocalIpAddress().getHostAddress()); 
      case REMOTE_CONNECTION:
        this.mLoginView.setText(this.managementBinder.getUserLogin());
        this.mConnectionTypeImage.setImageResource(2131558467);
        this.mConnectionTypeText.setText(2131624044);
        this.ipAddressLayout.setVisibility(4);
      case DEMO_MODE:
        break;
    } 
    this.mLoginView.setText(2131623975);
    this.mConnectionTypeImage.setImageResource(2131558506);
    this.mConnectionTypeText.setText(2131623975);
    this.ipAddressLayout.setVisibility(4);
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131492895);
    ButterKnife.bind((Activity)this);
    getWindow().setSoftInputMode(3);
  }
  
  protected void onDestroy() {
    super.onDestroy();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\LoginInfoActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */