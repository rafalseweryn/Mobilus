package pl.com.mobilelabs.mobilus.view.fragment.editingdevice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.model.objects.Device;
import pl.com.mobilelabs.mobilus.view.activity.EditingDeviceChildActivity;

public class CountdownRemovingDeviceFragment extends EditingDeviceFragment {
  private Handler handler = new Handler();
  
  protected boolean hard = false;
  
  private Thread mThread;
  
  @BindView(2131296517)
  protected RelativeLayout nextButton;
  
  @BindView(2131296518)
  protected TextView nextButtonTitle;
  
  int pStatus = 0;
  
  @BindView(2131296501)
  protected ContentLoadingProgressBar progressBar;
  
  @BindView(2131296753)
  protected TextView secondsLeft;
  
  private void enableBackButton(boolean paramBoolean) {
    EditingDeviceChildActivity editingDeviceChildActivity = (EditingDeviceChildActivity)getActivity();
    if (editingDeviceChildActivity != null)
      editingDeviceChildActivity.backButton.setEnabled(paramBoolean); 
  }
  
  private ImageButton getBackButton() {
    EditingDeviceChildActivity editingDeviceChildActivity = (EditingDeviceChildActivity)getActivity();
    return (editingDeviceChildActivity != null && editingDeviceChildActivity.backButton != null) ? editingDeviceChildActivity.backButton : null;
  }
  
  private void startCountdown() {
    if (this.mThread != null && !this.mThread.isInterrupted()) {
      this.mThread.interrupt();
      this.mThread = null;
    } 
    this.pStatus = 100;
    this.progressBar.setProgress(100);
    this.progressBar.setSecondaryProgress(100);
    this.progressBar.setMax(100);
    this.secondsLeft.setText(getString(2131624047, new Object[] { Integer.valueOf(30) }));
    this.mThread = new Thread(new Runnable() {
          public void run() {
            while (CountdownRemovingDeviceFragment.this.pStatus >= 0) {
              CountdownRemovingDeviceFragment countdownRemovingDeviceFragment = CountdownRemovingDeviceFragment.this;
              countdownRemovingDeviceFragment.pStatus--;
              CountdownRemovingDeviceFragment.this.handler.post(new Runnable() {
                    public void run() {
                      if (CountdownRemovingDeviceFragment.this.pStatus >= 0) {
                        CountdownRemovingDeviceFragment.this.progressBar.setProgress(CountdownRemovingDeviceFragment.this.pStatus);
                        int i = (int)Math.ceil((CountdownRemovingDeviceFragment.this.pStatus * 30) / 100.0D);
                        CountdownRemovingDeviceFragment.this.secondsLeft.setText(CountdownRemovingDeviceFragment.this.getString(2131624047, new Object[] { Integer.valueOf(i) }));
                      } else {
                        EditingDeviceChildActivity editingDeviceChildActivity = (EditingDeviceChildActivity)CountdownRemovingDeviceFragment.this.getActivity();
                        if (editingDeviceChildActivity != null) {
                          editingDeviceChildActivity.changeView(2131492928, new FailureRemovingDeviceFragment());
                          editingDeviceChildActivity.changeBackButtonImage(2131558473);
                        } 
                      } 
                    }
                  });
              try {
                Thread.sleep(300L);
              } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
                return;
              } 
            } 
          }
        });
    this.mThread.start();
  }
  
  private void stopCountdown() {
    if (this.mThread != null && !this.mThread.isInterrupted()) {
      this.mThread.interrupt();
      this.mThread = null;
    } 
  }
  
  public void backSelected() {
    stopCountdown();
    EditingDeviceChildActivity editingDeviceChildActivity = (EditingDeviceChildActivity)getActivity();
    if (editingDeviceChildActivity != null)
      editingDeviceChildActivity.setResult(-1); 
  }
  
  protected void configureIntentFilter() {
    super.configureIntentFilter();
    this.intentFilter.addAction("ManagementService.Intents.updateDeviceResponseReceived");
  }
  
  public void managementServiceConnected() {}
  
  @OnClick({2131296517})
  protected void nextClicked() {
    if (this.nextButtonTitle.getText().toString().equalsIgnoreCase(getResources().getString(2131624057))) {
      Device device = extractDevice();
      if (this.managementServiceBinder != null && device != null && this.managementServiceBinder.removeDeviceSoft(device.getId()))
        this.nextButtonTitle.setText(2131624058); 
    } else if (this.nextButtonTitle.getText().toString().equalsIgnoreCase(getResources().getString(2131624058))) {
      Device device = extractDevice();
      if (this.managementServiceBinder != null && device != null && this.managementServiceBinder.removeDeviceHard(device.getId()))
        this.hard = true; 
    } 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492907, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      startCountdown();
    } 
    return view1;
  }
  
  public void onDestroyView() {
    stopCountdown();
    super.onDestroyView();
  }
  
  protected void somethingElseReceived(Context paramContext, Intent paramIntent) {
    super.somethingElseReceived(paramContext, paramIntent);
    if (paramIntent.getAction().equalsIgnoreCase("ManagementService.Intents.updateDeviceResponseReceived")) {
      boolean bool;
      switch (paramIntent.getIntExtra("ManagementService.Intents.updateDeviceResponseReceivedOperationStatus", 0)) {
        default:
          bool = false;
          break;
        case 2:
          Toast.makeText(paramContext, 2131624139, 0).show();
        case 1:
          Toast.makeText(paramContext, 2131624103, 0).show();
        case 0:
          Toast.makeText(paramContext, 2131624141, 0).show();
          bool = true;
          break;
      } 
      if (bool) {
        EditingDeviceChildActivity editingDeviceChildActivity = (EditingDeviceChildActivity)getActivity();
        if (editingDeviceChildActivity != null) {
          editingDeviceChildActivity.setResult(0);
          editingDeviceChildActivity.finish();
        } 
      } else if (!this.hard) {
        EditingDeviceChildActivity editingDeviceChildActivity = (EditingDeviceChildActivity)getActivity();
        if (editingDeviceChildActivity != null) {
          editingDeviceChildActivity.changeView(2131492928, new FailureRemovingDeviceFragment());
          editingDeviceChildActivity.changeBackButtonImage(2131558473);
        } 
      } 
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingdevice\CountdownRemovingDeviceFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */