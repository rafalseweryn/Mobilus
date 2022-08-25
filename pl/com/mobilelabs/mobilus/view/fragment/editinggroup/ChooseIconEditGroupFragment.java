package pl.com.mobilelabs.mobilus.view.fragment.editinggroup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import pl.com.mobilelabs.mobilus.model.Action;
import pl.com.mobilelabs.mobilus.model.Error;
import pl.com.mobilelabs.mobilus.model.ErrorType;
import pl.com.mobilelabs.mobilus.model.Reading;
import pl.com.mobilelabs.mobilus.model.State;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.model.objects.Group;
import pl.com.mobilelabs.mobilus.view.activity.EditingGroupChildActivity;
import pl.com.mobilelabs.mobilus.view.view.IconsGenerator;

public class ChooseIconEditGroupFragment extends EditingGroupFragment {
  @BindView(2131296493)
  protected ImageView groupIcon;
  
  @BindView(2131296495)
  protected TextView groupName;
  
  protected ArrayList<Integer> iconList = new ArrayList<>();
  
  protected IconAdapter mAdapter = new IconAdapter();
  
  @BindView(2131296711)
  protected RecyclerView recyclerView;
  
  @BindView(2131296497)
  protected TextView saveButtonTitle;
  
  private void populateIconList(int paramInt) {
    if (this.iconList == null)
      this.iconList = new ArrayList<>(); 
    this.iconList.clear();
    for (paramInt = 1; paramInt <= 10; paramInt++)
      this.iconList.add(Integer.valueOf(paramInt)); 
  }
  
  private void presentData() {
    Group group = extractGroup();
    if (group != null) {
      showGroup(group);
      populateIconList(group.getType().getValue());
      this.mAdapter.setPositionSelected(this.iconList.indexOf(Integer.valueOf(group.getIcon())));
    } 
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
      view1 = paramLayoutInflater.inflate(2131492905, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      this.recyclerView.setAdapter(this.mAdapter);
      presentData();
      this.saveButtonTitle.setEnabled(false);
    } 
    return view1;
  }
  
  @OnClick({2131296496})
  protected void saveButtonClicked() {
    Group group = extractGroup();
    if (group != null && this.saveButtonTitle.isEnabled())
      this.managementServiceBinder.editDeviceGroupIcon(group.getId(), ((Integer)this.iconList.get(this.mAdapter.getPositionSelected())).intValue()); 
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
  
  public class IconAdapter extends RecyclerView.Adapter<IconViewHolder> {
    private int positionSelected;
    
    private void changeSelection(int param1Int) {
      if (param1Int != this.positionSelected) {
        int i = this.positionSelected;
        this.positionSelected = param1Int;
        notifyItemChanged(i);
        notifyItemChanged(this.positionSelected);
        ChooseIconEditGroupFragment.this.saveButtonTitle.setEnabled(true);
      } 
    }
    
    public int getItemCount() {
      return (ChooseIconEditGroupFragment.this.iconList != null) ? ChooseIconEditGroupFragment.this.iconList.size() : 0;
    }
    
    public int getPositionSelected() {
      return this.positionSelected;
    }
    
    public void onBindViewHolder(ChooseIconEditGroupFragment.IconViewHolder param1IconViewHolder, final int position) {
      if (ChooseIconEditGroupFragment.this.iconList != null) {
        ImageButton imageButton = param1IconViewHolder.iconImageButton;
        int i = ((Integer)ChooseIconEditGroupFragment.this.iconList.get(position)).intValue();
        int j = this.positionSelected;
        boolean bool1 = false;
        if (j == position) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        imageButton.setImageResource(IconsGenerator.getSelectionImageResource(i, bool2, true));
        imageButton = param1IconViewHolder.iconImageButton;
        boolean bool2 = bool1;
        if (this.positionSelected == position)
          bool2 = true; 
        imageButton.setSelected(bool2);
        param1IconViewHolder.iconImageButton.setOnClickListener(new View.OnClickListener() {
              public void onClick(View param2View) {
                ChooseIconEditGroupFragment.IconAdapter.this.changeSelection(position);
              }
            });
      } 
    }
    
    public ChooseIconEditGroupFragment.IconViewHolder onCreateViewHolder(ViewGroup param1ViewGroup, int param1Int) {
      View view = LayoutInflater.from(param1ViewGroup.getContext()).inflate(2131492930, param1ViewGroup, false);
      return new ChooseIconEditGroupFragment.IconViewHolder(view);
    }
    
    public void setPositionSelected(int param1Int) {
      this.positionSelected = param1Int;
      notifyDataSetChanged();
    }
  }
  
  class null implements View.OnClickListener {
    public void onClick(View param1View) {
      this.this$1.changeSelection(position);
    }
  }
  
  private class IconViewHolder extends RecyclerView.ViewHolder {
    public ImageButton iconImageButton;
    
    public IconViewHolder(View param1View) {
      super(param1View);
      param1View.setTag(this);
      this.iconImageButton = (ImageButton)param1View.findViewById(2131296626);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editinggroup\ChooseIconEditGroupFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */