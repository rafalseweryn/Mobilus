package pl.com.mobilelabs.mobilus.view.fragment.addingdevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
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
import pl.com.mobilelabs.mobilus.view.activity.AddingDeviceActivity;

public class CountdownAddingDeviceFragment extends AddingDeviceFragment {
  private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.programNewDeviceResponseReceived")) {
          if (param1Intent.hasExtra("ManagementService.Intents.programNewDeviceResponseReceivedDeviceId"))
            CountdownAddingDeviceFragment.access$002(CountdownAddingDeviceFragment.this, param1Intent.getLongExtra("ManagementService.Intents.programNewDeviceResponseReceivedDeviceId", -1L)); 
          if (param1Intent.hasExtra("ManagementService.Intents.programNewDeviceResponseReceivedDeviceType"))
            CountdownAddingDeviceFragment.access$102(CountdownAddingDeviceFragment.this, param1Intent.getIntExtra("ManagementService.Intents.programNewDeviceResponseReceivedDeviceType", -1)); 
          if (CountdownAddingDeviceFragment.this.deviceID >= 0L && CountdownAddingDeviceFragment.this.deviceType >= 0) {
            CountdownAddingDeviceFragment.this.stopCountdown();
            AddingDeviceActivity addingDeviceActivity = (AddingDeviceActivity)CountdownAddingDeviceFragment.this.getActivity();
            if (addingDeviceActivity != null) {
              addingDeviceActivity.setNewDeviceID(CountdownAddingDeviceFragment.this.deviceID);
              addingDeviceActivity.setNewDeviceType(CountdownAddingDeviceFragment.this.deviceType);
              addingDeviceActivity.changeView(2131492925, new DeviceFoundedAddingDeviceFragment());
            } 
          } 
        } 
      }
    };
  
  private long deviceID = -1L;
  
  private int deviceType = -1;
  
  private Handler handler = new Handler();
  
  private Thread mThread;
  
  @BindView(2131296510)
  protected RelativeLayout nextButton;
  
  @BindView(2131296511)
  protected TextView nextButtonTitle;
  
  int pStatus = 0;
  
  @BindView(2131296501)
  protected ContentLoadingProgressBar progressBar;
  
  @BindView(2131296753)
  protected TextView secondsLeft;
  
  private void enableBackButton(boolean paramBoolean) {
    AddingDeviceActivity addingDeviceActivity = (AddingDeviceActivity)getActivity();
    if (addingDeviceActivity != null && addingDeviceActivity.backButton != null)
      addingDeviceActivity.backButton.setEnabled(paramBoolean); 
  }
  
  private ImageButton getBackButton() {
    AddingDeviceActivity addingDeviceActivity = (AddingDeviceActivity)getActivity();
    return (addingDeviceActivity != null && addingDeviceActivity.backButton != null) ? addingDeviceActivity.backButton : null;
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
            while (CountdownAddingDeviceFragment.this.pStatus >= 0) {
              CountdownAddingDeviceFragment countdownAddingDeviceFragment = CountdownAddingDeviceFragment.this;
              countdownAddingDeviceFragment.pStatus--;
              CountdownAddingDeviceFragment.this.handler.post(new Runnable() {
                    public void run() {
                      if (CountdownAddingDeviceFragment.this.pStatus >= 0) {
                        CountdownAddingDeviceFragment.this.progressBar.setProgress(CountdownAddingDeviceFragment.this.pStatus);
                        int i = (int)Math.ceil((CountdownAddingDeviceFragment.this.pStatus * 30) / 100.0D);
                        CountdownAddingDeviceFragment.this.secondsLeft.setText(CountdownAddingDeviceFragment.this.getString(2131624047, new Object[] { Integer.valueOf(i) }));
                      } else {
                        AddingDeviceActivity addingDeviceActivity = (AddingDeviceActivity)CountdownAddingDeviceFragment.this.getActivity();
                        if (addingDeviceActivity != null) {
                          addingDeviceActivity.changeView(2131492927, new FailureAddingDeviceFragment());
                          addingDeviceActivity.changeBackButtonImage(2131558473);
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
  }
  
  @OnClick({2131296510})
  protected void nextClicked() {
    Toast.makeText((Context)getActivity(), "Rent?", 0).show();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492906, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      IntentFilter intentFilter = new IntentFilter("ManagementService.Intents.programNewDeviceResponseReceived");
      LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.broadcastReceiver, intentFilter);
      this.nextButton.setEnabled(false);
      this.nextButtonTitle.setEnabled(false);
      startCountdown();
    } 
    return view1;
  }
  
  public void onDestroyView() {
    stopCountdown();
    super.onDestroyView();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addingdevice\CountdownAddingDeviceFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */