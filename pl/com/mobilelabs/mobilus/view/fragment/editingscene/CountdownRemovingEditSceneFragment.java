package pl.com.mobilelabs.mobilus.view.fragment.editingscene;

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
import pl.com.mobilelabs.mobilus.model.objects.Scene;
import pl.com.mobilelabs.mobilus.view.activity.EditingPlaceChildActivity;
import pl.com.mobilelabs.mobilus.view.activity.EditingSceneChildActivity;

public class CountdownRemovingEditSceneFragment extends EditingSceneFragment {
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
  
  @BindView(2131296525)
  protected ImageView sceneIcon;
  
  @BindView(2131296527)
  protected TextView sceneName;
  
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
    Scene scene = extractScene();
    if (scene != null)
      showScene(scene); 
  }
  
  private void showScene(Scene paramScene) {
    this.sceneName.setText(paramScene.getName());
    this.sceneIcon.setImageResource(2131558655);
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
            while (CountdownRemovingEditSceneFragment.this.pStatus >= 0) {
              CountdownRemovingEditSceneFragment countdownRemovingEditSceneFragment = CountdownRemovingEditSceneFragment.this;
              countdownRemovingEditSceneFragment.pStatus--;
              CountdownRemovingEditSceneFragment.this.handler.post(new Runnable() {
                    public void run() {
                      if (CountdownRemovingEditSceneFragment.this.pStatus >= 0) {
                        CountdownRemovingEditSceneFragment.this.progressBar.setProgress(CountdownRemovingEditSceneFragment.this.pStatus);
                        int i = (int)Math.ceil((CountdownRemovingEditSceneFragment.this.pStatus * 30) / 100.0D);
                        CountdownRemovingEditSceneFragment.this.secondsLeft.setText(CountdownRemovingEditSceneFragment.this.getString(2131624047, new Object[] { Integer.valueOf(i) }));
                      } else {
                        EditingSceneChildActivity editingSceneChildActivity = (EditingSceneChildActivity)CountdownRemovingEditSceneFragment.this.getActivity();
                        if (editingSceneChildActivity != null) {
                          editingSceneChildActivity.setResult(-1);
                          editingSceneChildActivity.finish();
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
    EditingSceneChildActivity editingSceneChildActivity = (EditingSceneChildActivity)getActivity();
    if (editingSceneChildActivity != null) {
      editingSceneChildActivity.setResult(-1);
      editingSceneChildActivity.finish();
    } 
  }
  
  protected void configureIntentFilter() {
    super.configureIntentFilter();
    this.intentFilter.addAction("ManagementService.Intents.updateSceneResponseReceived");
  }
  
  public void managementServiceConnected() {
    presentData();
  }
  
  @OnClick({2131296528})
  protected void nextClicked() {
    Scene scene = extractScene();
    if (this.managementServiceBinder != null && scene != null)
      this.managementServiceBinder.removeScene(scene.getId()); 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492908, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      this.message.setText(2131624166);
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
    if (paramIntent.getAction().equalsIgnoreCase("ManagementService.Intents.updateSceneResponseReceived")) {
      boolean bool;
      switch (paramIntent.getIntExtra("ManagementService.Intents.updateSceneResponseReceivedOperationStatus", 0)) {
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
        EditingSceneChildActivity editingSceneChildActivity = (EditingSceneChildActivity)getActivity();
        if (editingSceneChildActivity != null) {
          editingSceneChildActivity.setResult(0);
          editingSceneChildActivity.finish();
        } 
      } 
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingscene\CountdownRemovingEditSceneFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */