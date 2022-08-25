package pl.com.mobilelabs.mobilus.view.fragment.editinggroup;

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
import pl.com.mobilelabs.mobilus.model.Action;
import pl.com.mobilelabs.mobilus.model.Error;
import pl.com.mobilelabs.mobilus.model.ErrorType;
import pl.com.mobilelabs.mobilus.model.Reading;
import pl.com.mobilelabs.mobilus.model.State;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.model.objects.Group;
import pl.com.mobilelabs.mobilus.view.activity.EditingGroupChildActivity;
import pl.com.mobilelabs.mobilus.view.view.IconsGenerator;

public class CountdownRemovingEditGroupFragment extends EditingGroupFragment {
  @BindView(2131296525)
  protected ImageView groupIcon;
  
  @BindView(2131296527)
  protected TextView groupName;
  
  private Handler handler = new Handler();
  
  private Thread mThread;
  
  @BindView(2131296526)
  protected TextView message;
  
  @BindView(2131296528)
  protected RelativeLayout nextButton;
  
  @BindView(2131296529)
  protected TextView nextButtonTitle;
  
  int pStatus = 0;
  
  @BindView(2131296501)
  protected ContentLoadingProgressBar progressBar;
  
  @BindView(2131296753)
  protected TextView secondsLeft;
  
  private void enableBackButton(boolean paramBoolean) {
    EditingGroupChildActivity editingGroupChildActivity = (EditingGroupChildActivity)getActivity();
    if (editingGroupChildActivity != null)
      editingGroupChildActivity.backButton.setEnabled(paramBoolean); 
  }
  
  private ImageButton getBackButton() {
    EditingGroupChildActivity editingGroupChildActivity = (EditingGroupChildActivity)getActivity();
    return (editingGroupChildActivity != null && editingGroupChildActivity.backButton != null) ? editingGroupChildActivity.backButton : null;
  }
  
  private void presentData() {
    Group group = extractGroup();
    if (group != null)
      showGroup(group); 
  }
  
  private void showGroup(Group paramGroup) {
    int i;
    this.groupName.setText(paramGroup.getName());
    State state = (State)this.managementServiceBinder.getGroupCurrentState().get(Long.valueOf(paramGroup.getId()));
    boolean bool = true;
    if (state == null) {
      i = IconsGenerator.getErrorImageResource((BaseObject)paramGroup, true);
    } else if (state instanceof Error) {
      if (((Error)state).getErrorType() != ErrorType.NO_CONNECTION)
        bool = false; 
      i = IconsGenerator.getErrorImageResource((BaseObject)paramGroup, bool);
    } else {
      Reading reading = (Reading)state;
      if (reading.getAction() == Action.ON || reading.getAction() == Action.UP) {
        i = IconsGenerator.getTotallyOpenedImageResource((BaseObject)paramGroup);
      } else if (reading.getAction() == Action.OFF || reading.getAction() == Action.DOWN) {
        i = IconsGenerator.getTotallyClosedImageResource((BaseObject)paramGroup);
      } else {
        i = IconsGenerator.getPositionImageResource((BaseObject)paramGroup, reading.getPosition());
      } 
    } 
    this.groupIcon.setImageResource(i);
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
            while (CountdownRemovingEditGroupFragment.this.pStatus >= 0) {
              CountdownRemovingEditGroupFragment countdownRemovingEditGroupFragment = CountdownRemovingEditGroupFragment.this;
              countdownRemovingEditGroupFragment.pStatus--;
              CountdownRemovingEditGroupFragment.this.handler.post(new Runnable() {
                    public void run() {
                      if (CountdownRemovingEditGroupFragment.this.pStatus >= 0) {
                        CountdownRemovingEditGroupFragment.this.progressBar.setProgress(CountdownRemovingEditGroupFragment.this.pStatus);
                        int i = (int)Math.ceil((CountdownRemovingEditGroupFragment.this.pStatus * 30) / 100.0D);
                        CountdownRemovingEditGroupFragment.this.secondsLeft.setText(CountdownRemovingEditGroupFragment.this.getString(2131624047, new Object[] { Integer.valueOf(i) }));
                      } else {
                        EditingGroupChildActivity editingGroupChildActivity = (EditingGroupChildActivity)CountdownRemovingEditGroupFragment.this.getActivity();
                        if (editingGroupChildActivity != null) {
                          editingGroupChildActivity.setResult(-1);
                          editingGroupChildActivity.finish();
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
    EditingGroupChildActivity editingGroupChildActivity = (EditingGroupChildActivity)getActivity();
    if (editingGroupChildActivity != null)
      editingGroupChildActivity.setResult(-1); 
  }
  
  protected void configureIntentFilter() {
    super.configureIntentFilter();
    this.intentFilter.addAction("ManagementService.Intents.updateDeviceGroupResponseReceived");
  }
  
  public void managementServiceConnected() {
    presentData();
  }
  
  @OnClick({2131296528})
  protected void nextClicked() {
    Group group = extractGroup();
    if (this.managementServiceBinder != null && group != null)
      this.managementServiceBinder.removeDeviceGroup(group.getId()); 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492908, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      this.message.setText(2131624162);
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
    if (paramIntent.getAction().equalsIgnoreCase("ManagementService.Intents.updateDeviceGroupResponseReceived")) {
      boolean bool;
      switch (paramIntent.getIntExtra("ManagementService.Intents.updateDeviceGroupResponseReceivedOperationStatus", 0)) {
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
        EditingGroupChildActivity editingGroupChildActivity = (EditingGroupChildActivity)getActivity();
        if (editingGroupChildActivity != null) {
          editingGroupChildActivity.setResult(0);
          editingGroupChildActivity.finish();
        } 
      } 
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editinggroup\CountdownRemovingEditGroupFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */