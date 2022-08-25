package pl.com.mobilelabs.mobilus.view.fragment.editinggroup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class ChangeNameEditGroupFragment extends EditingGroupFragment {
  @BindView(2131296481)
  protected ImageView groupIcon;
  
  @BindView(2131296483)
  protected TextView groupName;
  
  @BindView(2131296485)
  protected TextInputEditText nameEditText;
  
  @BindView(2131296487)
  protected TextView saveButtonTitle;
  
  private void presentData() {
    Group group = extractGroup();
    if (group != null)
      showGroup(group); 
  }
  
  private void showGroup(Group paramGroup) {
    int i;
    this.groupName.setText(paramGroup.getName());
    this.nameEditText.setText(paramGroup.getName());
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
  
  public void backSelected() {}
  
  protected void configureIntentFilter() {
    super.configureIntentFilter();
    this.intentFilter.addAction("ManagementService.Intents.updateDeviceGroupResponseReceived");
  }
  
  protected void deviceActionFinished(Context paramContext, Intent paramIntent) {
    if (extractGroup() != null)
      presentData(); 
  }
  
  protected void deviceActionStarted(Context paramContext, Intent paramIntent) {
    if (extractGroup() != null)
      presentData(); 
  }
  
  public void managementServiceConnected() {
    presentData();
    this.saveButtonTitle.setEnabled(false);
  }
  
  protected void newConfigurationReceived(Context paramContext, Intent paramIntent) {
    super.newConfigurationReceived(paramContext, paramIntent);
    if (extractGroup() != null)
      presentData(); 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492904, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      this.nameEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable param1Editable) {
              ChangeNameEditGroupFragment.this.saveButtonTitle.setEnabled(ChangeNameEditGroupFragment.this.nameEditText.getText().toString().isEmpty() ^ true);
            }
            
            public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
            
            public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
          });
      presentData();
    } 
    return view1;
  }
  
  @OnClick({2131296486})
  protected void saveButtonClicked() {
    Group group = extractGroup();
    if (group != null && this.managementServiceBinder != null && this.saveButtonTitle.isEnabled())
      this.managementServiceBinder.editDeviceGroupName(group.getId(), this.nameEditText.getText().toString()); 
  }
  
  protected void somethingElseReceived(Context paramContext, Intent paramIntent) {
    super.somethingElseReceived(paramContext, paramIntent);
    if (paramIntent.getAction().equalsIgnoreCase("ManagementService.Intents.updateDeviceGroupResponseReceived")) {
      boolean bool = false;
      switch (paramIntent.getIntExtra("ManagementService.Intents.updateDeviceGroupResponseReceivedOperationStatus", 0)) {
        case 2:
          Toast.makeText(paramContext, 2131624139, 0).show();
          break;
        case 1:
          Toast.makeText(paramContext, 2131624103, 0).show();
          break;
        case 0:
          Toast.makeText(paramContext, 2131624141, 0).show();
          bool = true;
          break;
      } 
      if (bool) {
        EditingGroupChildActivity editingGroupChildActivity = (EditingGroupChildActivity)getActivity();
        if (editingGroupChildActivity != null) {
          editingGroupChildActivity.setResult(-1);
          editingGroupChildActivity.finish();
        } 
      } 
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editinggroup\ChangeNameEditGroupFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */