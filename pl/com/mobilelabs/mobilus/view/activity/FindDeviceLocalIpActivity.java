package pl.com.mobilelabs.mobilus.view.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.services.searchcentralservice.ISearchCentralServiceListener;
import pl.com.mobilelabs.mobilus.services.searchcentralservice.SearchCentralService;

public class FindDeviceLocalIpActivity extends Activity implements ISearchCentralServiceListener {
  public static final String FOUND_IP_ADDRESS = "pl.com.mobilelabs.mobilus.view.activity.FindDeviceLocalIpActivity.foundIpAddress";
  
  public static final int REQUEST_CODE = 9827;
  
  @BindView(2131296377)
  Button cancelCloseButton;
  
  private ServiceConnection connection = new ServiceConnection() {
      public void onServiceConnected(ComponentName param1ComponentName, IBinder param1IBinder) {
        FindDeviceLocalIpActivity.access$002(FindDeviceLocalIpActivity.this, (SearchCentralService.SearchCentralServiceBinder)param1IBinder);
        FindDeviceLocalIpActivity.this.searchBinder.setListener(FindDeviceLocalIpActivity.this);
        FindDeviceLocalIpActivity.access$102(FindDeviceLocalIpActivity.this, FindDeviceLocalIpActivity.this.searchBinder.startSearch());
        if (!FindDeviceLocalIpActivity.this.searchInProgress) {
          FindDeviceLocalIpActivity.this.searchTextView.setText(2131624007);
          FindDeviceLocalIpActivity.this.cancelCloseButton.setText(2131624024);
        } 
      }
      
      public void onServiceDisconnected(ComponentName param1ComponentName) {
        FindDeviceLocalIpActivity.access$002(FindDeviceLocalIpActivity.this, (SearchCentralService.SearchCentralServiceBinder)null);
      }
    };
  
  private String foundIpAddress;
  
  private SearchCentralService.SearchCentralServiceBinder searchBinder;
  
  private boolean searchInProgress;
  
  @BindView(2131296378)
  ProgressBar searchProgressBar;
  
  @BindView(2131296379)
  TextView searchTextView;
  
  @OnClick({2131296377})
  public void closeActivity() {
    if (this.searchBinder == null) {
      finish();
    } else {
      if (this.searchInProgress)
        this.searchBinder.cancelSearch(); 
      if (this.searchInProgress == true) {
        setResult(0);
      } else {
        Intent intent = new Intent();
        if (this.foundIpAddress != null)
          intent.putExtra("pl.com.mobilelabs.mobilus.view.activity.FindDeviceLocalIpActivity.foundIpAddress", this.foundIpAddress); 
        setResult(-1, intent);
      } 
      finish();
    } 
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131492894);
    ButterKnife.bind(this);
    bindService(new Intent((Context)this, SearchCentralService.class), this.connection, 1);
  }
  
  protected void onDestroy() {
    if (this.connection != null)
      unbindService(this.connection); 
    super.onDestroy();
  }
  
  public void searchCentralIpAddressFinished(String paramString) {
    this.foundIpAddress = paramString;
    this.searchInProgress = false;
    this.searchProgressBar.setProgress(this.searchProgressBar.getMax());
    if (paramString == null) {
      this.searchTextView.setText(2131624174);
    } else {
      TextView textView = this.searchTextView;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(getResources().getString(2131624059));
      stringBuilder.append(": ");
      stringBuilder.append(paramString);
      textView.setText(stringBuilder.toString());
    } 
    this.cancelCloseButton.setText(2131624024);
  }
  
  public void searchCentralIpAddressProgress(int paramInt) {
    this.searchProgressBar.setProgress(paramInt);
    String str = String.format("%d/255", new Object[] { Integer.valueOf(paramInt) });
    this.searchTextView.setText(str);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\FindDeviceLocalIpActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */