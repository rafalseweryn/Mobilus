package pl.com.mobilelabs.mobilus.view.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import pl.com.mobilelabs.mobilus.model.objects.Scene;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.activity.EditingSceneActivity;
import pl.com.mobilelabs.mobilus.view.fragment.IDevicesGroupsPlacesScenesListFragmentContext;

public class SceneView extends RelativeLayout {
  private boolean alreadyInflated = false;
  
  @BindView(2131296563)
  ImageView editButton;
  
  @BindView(2131296569)
  SwitchCompat enabledSwitch;
  
  @BindView(2131296624)
  ImageView iconBlue;
  
  @BindView(2131296625)
  ImageView iconGray;
  
  @BindView(2131296838)
  TextView nameTextView;
  
  @BindView(2131296837)
  TextView nextRunTextView;
  
  private Scene scene;
  
  @BindView(2131296733)
  TableRow sceneNextRunTableRow;
  
  @BindView(2131296734)
  ImageView scenePlayBlueDotsImageView;
  
  @BindView(2131296735)
  TableRow scenePlayButtonTableRow;
  
  @BindView(2131296736)
  ImageView scenePlayGreyDotsImageView;
  
  @BindView(2131296756)
  LinearLayout separator1;
  
  @BindView(2131296757)
  LinearLayout separator2;
  
  public SceneView(Context paramContext) {
    super(paramContext);
  }
  
  public static SceneView build(Context paramContext) {
    SceneView sceneView = new SceneView(paramContext);
    sceneView.onFinishInflate();
    return sceneView;
  }
  
  @RequiresApi(api = 23)
  private void sceneEnabled(boolean paramBoolean) {
    if (paramBoolean) {
      this.iconBlue.setVisibility(0);
      this.iconGray.setVisibility(8);
      this.nextRunTextView.setTextColor(getResources().getColor(2131099697, getContext().getTheme()));
      this.enabledSwitch.setChecked(true);
    } else {
      this.iconBlue.setVisibility(8);
      this.iconGray.setVisibility(0);
      this.nextRunTextView.setTextColor(getResources().getColor(2131099700, getContext().getTheme()));
      this.enabledSwitch.setChecked(false);
    } 
  }
  
  private void setEditElements(boolean paramBoolean) {
    if (paramBoolean) {
      this.separator1.setVisibility(0);
      this.editButton.setVisibility(0);
      this.separator2.setVisibility(0);
      this.enabledSwitch.setVisibility(0);
    } else {
      this.separator1.setVisibility(8);
      this.editButton.setVisibility(8);
      this.separator2.setVisibility(8);
      this.enabledSwitch.setVisibility(8);
    } 
  }
  
  private void setScenePlayButtonVisibility(boolean paramBoolean) {
    if (paramBoolean) {
      this.sceneNextRunTableRow.setVisibility(0);
      this.scenePlayButtonTableRow.setVisibility(0);
      ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
      if (managementServiceBinder != null && managementServiceBinder.isUserAdmin()) {
        setEditElements(true);
      } else {
        setEditElements(false);
      } 
      this.scenePlayGreyDotsImageView.setVisibility(8);
      this.scenePlayBlueDotsImageView.setVisibility(0);
    } else {
      this.sceneNextRunTableRow.setVisibility(8);
      this.scenePlayButtonTableRow.setVisibility(8);
      setEditElements(false);
      this.scenePlayGreyDotsImageView.setVisibility(0);
      this.scenePlayBlueDotsImageView.setVisibility(8);
    } 
  }
  
  private void toggleScenePlayButtonVisibility() {
    if (this.scenePlayButtonTableRow.getVisibility() == 8) {
      setScenePlayButtonVisibility(true);
    } else {
      setScenePlayButtonVisibility(false);
    } 
  }
  
  @RequiresApi(api = 23)
  public void bind(Scene paramScene) {
    this.scene = paramScene;
    this.nameTextView.setText(paramScene.getName());
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null) {
      paramScene.setLocation(managementServiceBinder.getLocation());
      paramScene.refreshDates();
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EE HH:mm", Locale.getDefault());
      simpleDateFormat.setTimeZone(TimeZone.getDefault());
      Date date = paramScene.getNextRun();
      if (date != null) {
        this.nextRunTextView.setText(String.format("%s - %s", new Object[] { simpleDateFormat.format(date), getResources().getString(2131624134) }));
        this.nextRunTextView.setVisibility(0);
      } else {
        this.nextRunTextView.setVisibility(4);
      } 
    } 
    sceneEnabled(paramScene.isEnabled());
    setScenePlayButtonVisibility(false);
  }
  
  @OnClick({2131296563})
  protected void editButtonClicked() {
    Intent intent = new Intent(getContext(), EditingSceneActivity.class);
    intent.putExtra("EditingSceneActivity.somethingId", this.scene.getId());
    getContext().startActivity(intent);
  }
  
  @OnClick({2131296569})
  protected void enabledSwitchClicked() {
    ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder().editSceneEnabled(this.scene.getId(), this.enabledSwitch.isChecked());
  }
  
  public Scene getScene() {
    return this.scene;
  }
  
  public void onFinishInflate() {
    if (!this.alreadyInflated) {
      this.alreadyInflated = true;
      inflate(getContext(), 2131492976, (ViewGroup)this);
      ButterKnife.bind((View)this);
    } 
    setScenePlayButtonVisibility(false);
    super.onFinishInflate();
    setOnClickListener(new View.OnClickListener() {
          public void onClick(View param1View) {
            SceneView.this.toggleScenePlayButtonVisibility();
          }
        });
  }
  
  @OnClick({2131296732})
  public void onSceneButtonClicked() {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null)
      managementServiceBinder.runSceneActions(this.scene.getId()); 
    setScenePlayButtonVisibility(false);
    Toast.makeText(getContext(), 2131624173, 0).show();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\SceneView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */