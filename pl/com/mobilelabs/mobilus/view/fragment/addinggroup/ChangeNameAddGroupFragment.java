package pl.com.mobilelabs.mobilus.view.fragment.addinggroup;

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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.model.objects.Group;
import pl.com.mobilelabs.mobilus.view.activity.AddingGroupActivity;
import pl.com.mobilelabs.mobilus.view.view.IconsGenerator;

public class ChangeNameAddGroupFragment extends AddingGroupFragment {
  @BindView(2131296481)
  protected ImageView groupIcon;
  
  @BindView(2131296483)
  protected TextView groupName;
  
  @BindView(2131296485)
  protected TextInputEditText nameEditText;
  
  @BindView(2131296487)
  protected TextView nextButtonTitle;
  
  private void presentData() {
    Group group = extractGroup();
    if (group != null)
      showGroup(group); 
  }
  
  private void showGroup(Group paramGroup) {
    if (!paramGroup.getName().isEmpty())
      this.groupName.setText(paramGroup.getName()); 
    this.nameEditText.setText(paramGroup.getName());
    int i = IconsGenerator.getTotallyClosedImageResource((BaseObject)paramGroup);
    this.groupIcon.setImageResource(i);
  }
  
  public void backSelected() {
    AddingGroupActivity addingGroupActivity = (AddingGroupActivity)getActivity();
    if (addingGroupActivity != null)
      addingGroupActivity.finish(); 
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
    this.nextButtonTitle.setEnabled(false);
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
      this.groupName.setText(2131624128);
      this.nextButtonTitle.setText(2131624133);
      this.nameEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable param1Editable) {
              ChangeNameAddGroupFragment.this.nextButtonTitle.setEnabled(ChangeNameAddGroupFragment.this.nameEditText.getText().toString().isEmpty() ^ true);
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
    AddingGroupActivity addingGroupActivity = (AddingGroupActivity)getActivity();
    if (group != null && this.nextButtonTitle.isEnabled() && addingGroupActivity != null) {
      group.setName(this.nameEditText.getText().toString());
      addingGroupActivity.changeBackButtonImage(2131558473);
      addingGroupActivity.changeView(2131492905, new ChangeIconAddGroupFragment());
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addinggroup\ChangeNameAddGroupFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */