package pl.com.mobilelabs.mobilus.view.fragment.editinggroup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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
import pl.com.mobilelabs.mobilus.view.activity.DoSthWithSthInterface;
import pl.com.mobilelabs.mobilus.view.activity.EditingGroupChildActivity;
import pl.com.mobilelabs.mobilus.view.activity.UltimateHeaderActivity;
import pl.com.mobilelabs.mobilus.view.view.IconsGenerator;

public class MenuEditGroupFragment extends EditingGroupFragment {
  @BindView(2131296665)
  protected ImageView groupIcon;
  
  @BindView(2131296666)
  protected TextView groupName;
  
  protected boolean runForYourLife = true;
  
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
  
  @OnClick({2131296661})
  protected void assignDeviceToGroupClicked() {
    this.runForYourLife = true;
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    Intent intent = new Intent(getContext(), EditingGroupChildActivity.class);
    intent.putExtra("EditingGroupChildActivity.groupId", ((DoSthWithSthInterface)ultimateHeaderActivity).getNewSomethingID());
    intent.putExtra("EditingGroupChildActivity.fragmentResource", 2131492901);
    intent.putExtra("EditingGroupChildActivity.deviceFragment", true);
    startActivityForResult(intent, 8);
  }
  
  @OnClick({2131296662})
  protected void assignGroupToPlaceClicked() {
    this.runForYourLife = true;
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    Intent intent = new Intent(getContext(), EditingGroupChildActivity.class);
    intent.putExtra("EditingGroupChildActivity.groupId", ((DoSthWithSthInterface)ultimateHeaderActivity).getNewSomethingID());
    intent.putExtra("EditingGroupChildActivity.fragmentResource", 2131492901);
    intent.putExtra("EditingGroupChildActivity.deviceFragment", false);
    startActivityForResult(intent, 8);
  }
  
  public void backSelected() {}
  
  @OnClick({2131296663})
  protected void changeNameClicked() {
    this.runForYourLife = true;
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    Intent intent = new Intent(getContext(), EditingGroupChildActivity.class);
    intent.putExtra("EditingGroupChildActivity.groupId", ((DoSthWithSthInterface)ultimateHeaderActivity).getNewSomethingID());
    intent.putExtra("EditingGroupChildActivity.fragmentResource", 2131492904);
    startActivityForResult(intent, 8);
  }
  
  @OnClick({2131296664})
  protected void chooseIconClicked() {
    this.runForYourLife = true;
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    Intent intent = new Intent(getContext(), EditingGroupChildActivity.class);
    intent.putExtra("EditingGroupChildActivity.groupId", ((DoSthWithSthInterface)ultimateHeaderActivity).getNewSomethingID());
    intent.putExtra("EditingGroupChildActivity.fragmentResource", 2131492905);
    startActivityForResult(intent, 8);
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
  }
  
  protected void newConfigurationReceived(Context paramContext, Intent paramIntent) {
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    if (extractGroup() == null && ultimateHeaderActivity != null && this.runForYourLife)
      ultimateHeaderActivity.finish(); 
    if (extractGroup() != null)
      presentData(); 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492932, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      presentData();
    } 
    return view1;
  }
  
  @OnClick({2131296667})
  protected void removeGroupClicked() {
    this.runForYourLife = true;
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    Intent intent = new Intent(getContext(), EditingGroupChildActivity.class);
    intent.putExtra("EditingGroupChildActivity.groupId", ((DoSthWithSthInterface)ultimateHeaderActivity).getNewSomethingID());
    intent.putExtra("EditingGroupChildActivity.fragmentResource", 2131492907);
    startActivityForResult(intent, 8);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editinggroup\MenuEditGroupFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */