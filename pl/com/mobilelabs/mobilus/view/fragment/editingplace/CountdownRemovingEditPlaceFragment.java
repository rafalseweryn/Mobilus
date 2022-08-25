package pl.com.mobilelabs.mobilus.view.fragment.editingplace;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.model.objects.Place;
import pl.com.mobilelabs.mobilus.view.activity.EditingPlaceChildActivity;

public class CountdownRemovingEditPlaceFragment extends EditingPlaceFragment {
  private Handler handler = new Handler();
  
  private Thread mThread;
  
  @BindView(2131296526)
  protected TextView message;
  
  @BindView(2131296528)
  protected RelativeLayout nextButton;
  
  @BindView(2131296529)
  protected TextView nextButtonTitle;
  
  int pStatus = 0;
  
  @BindView(2131296525)
  protected ImageView placeIcon;
  
  @BindView(2131296527)
  protected TextView placeName;
  
  @BindView(2131296501)
  protected ContentLoadingProgressBar progressBar;
  
  @BindView(2131296753)
  protected TextView secondsLeft;
  
  private void enableBackButton(boolean paramBoolean) {
    EditingPlaceChildActivity editingPlaceChildActivity = (EditingPlaceChildActivity)getActivity();
    if (editingPlaceChildActivity != null)
      editingPlaceChildActivity.backButton.setEnabled(paramBoolean); 
  }
  
  private ImageButton getBackButton() {
    EditingPlaceChildActivity editingPlaceChildActivity = (EditingPlaceChildActivity)getActivity();
    return (editingPlaceChildActivity != null && editingPlaceChildActivity.backButton != null) ? editingPlaceChildActivity.backButton : null;
  }
  
  private void presentData() {
    Place place = extractPlace();
    if (place != null)
      showPlace(place); 
  }
  
  private void showPlace(Place paramPlace) {
    this.placeName.setText(paramPlace.getName());
    this.placeIcon.setImageResource(2131558655);
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
            while (CountdownRemovingEditPlaceFragment.this.pStatus >= 0) {
              CountdownRemovingEditPlaceFragment countdownRemovingEditPlaceFragment = CountdownRemovingEditPlaceFragment.this;
              countdownRemovingEditPlaceFragment.pStatus--;
              CountdownRemovingEditPlaceFragment.this.handler.post(new Runnable() {
                    public void run() {
                      if (CountdownRemovingEditPlaceFragment.this.pStatus >= 0) {
                        CountdownRemovingEditPlaceFragment.this.progressBar.setProgress(CountdownRemovingEditPlaceFragment.this.pStatus);
                        int i = (int)Math.ceil((CountdownRemovingEditPlaceFragment.this.pStatus * 30) / 100.0D);
                        CountdownRemovingEditPlaceFragment.this.secondsLeft.setText(CountdownRemovingEditPlaceFragment.this.getString(2131624047, new Object[] { Integer.valueOf(i) }));
                      } else {
                        EditingPlaceChildActivity editingPlaceChildActivity = (EditingPlaceChildActivity)CountdownRemovingEditPlaceFragment.this.getActivity();
                        if (editingPlaceChildActivity != null) {
                          editingPlaceChildActivity.setResult(-1);
                          editingPlaceChildActivity.finish();
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
    EditingPlaceChildActivity editingPlaceChildActivity = (EditingPlaceChildActivity)getActivity();
    if (editingPlaceChildActivity != null)
      editingPlaceChildActivity.setResult(-1); 
  }
  
  protected void configureIntentFilter() {
    super.configureIntentFilter();
    this.intentFilter.addAction("ManagementService.Intents.updateDevicePlaceResponseReceived");
  }
  
  public void managementServiceConnected() {
    presentData();
  }
  
  @OnClick({2131296528})
  protected void nextClicked() {
    Place place = extractPlace();
    if (this.managementServiceBinder != null && place != null)
      this.managementServiceBinder.removeDevicePlace(place.getId()); 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492908, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      this.message.setText(2131624164);
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
    if (paramIntent.getAction().equalsIgnoreCase("ManagementService.Intents.updateDevicePlaceResponseReceived")) {
      boolean bool;
      switch (paramIntent.getIntExtra("ManagementService.Intents.updateDevicePlaceResponseReceivedOperationStatus", 0)) {
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
        stopCountdown();
        EditingPlaceChildActivity editingPlaceChildActivity = (EditingPlaceChildActivity)getActivity();
        if (editingPlaceChildActivity != null) {
          editingPlaceChildActivity.setResult(0);
          editingPlaceChildActivity.finish();
        } 
      } 
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingplace\CountdownRemovingEditPlaceFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */