package pl.com.mobilelabs.mobilus.view.fragment.addinggroup;

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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.model.objects.Group;
import pl.com.mobilelabs.mobilus.view.activity.AddingGroupActivity;
import pl.com.mobilelabs.mobilus.view.view.IconsGenerator;

public class ChangeIconAddGroupFragment extends AddingGroupFragment {
  @BindView(2131296493)
  protected ImageView groupIcon;
  
  @BindView(2131296495)
  protected TextView groupName;
  
  protected ArrayList<Integer> iconList = new ArrayList<>();
  
  protected IconAdapter mAdapter = new IconAdapter();
  
  @BindView(2131296497)
  protected TextView nextButtonTitle;
  
  @BindView(2131296711)
  protected RecyclerView recyclerView;
  
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
      populateIconList(0);
      this.mAdapter.setPositionSelected(this.iconList.indexOf(Integer.valueOf(group.getIcon())));
    } 
  }
  
  private void showGroup(Group paramGroup) {
    this.groupName.setText(paramGroup.getName());
    int i = IconsGenerator.getTotallyClosedImageResource((BaseObject)paramGroup);
    this.groupIcon.setImageResource(i);
  }
  
  public void backSelected() {
    AddingGroupActivity addingGroupActivity = (AddingGroupActivity)getActivity();
    if (addingGroupActivity != null) {
      addingGroupActivity.changeBackButtonImage(2131558462);
      addingGroupActivity.changeView(2131492904, new ChangeNameAddGroupFragment());
    } 
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
      this.nextButtonTitle.setText(2131624133);
      this.recyclerView.setAdapter(this.mAdapter);
      presentData();
    } 
    return view1;
  }
  
  @OnClick({2131296496})
  protected void saveButtonClicked() {
    Group group = extractGroup();
    AddingGroupActivity addingGroupActivity = (AddingGroupActivity)getActivity();
    if (group != null && addingGroupActivity != null) {
      group.setIcon(((Integer)this.iconList.get(this.mAdapter.getPositionSelected())).intValue());
      addingGroupActivity.changeView(2131492901, new AssignDeviceAddGroupFragment());
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
      } 
    }
    
    public int getItemCount() {
      return (ChangeIconAddGroupFragment.this.iconList != null) ? ChangeIconAddGroupFragment.this.iconList.size() : 0;
    }
    
    public int getPositionSelected() {
      return this.positionSelected;
    }
    
    public void onBindViewHolder(ChangeIconAddGroupFragment.IconViewHolder param1IconViewHolder, final int position) {
      if (ChangeIconAddGroupFragment.this.iconList != null) {
        ImageButton imageButton = param1IconViewHolder.iconImageButton;
        int i = ((Integer)ChangeIconAddGroupFragment.this.iconList.get(position)).intValue();
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
                ChangeIconAddGroupFragment.IconAdapter.this.changeSelection(position);
              }
            });
      } 
    }
    
    public ChangeIconAddGroupFragment.IconViewHolder onCreateViewHolder(ViewGroup param1ViewGroup, int param1Int) {
      View view = LayoutInflater.from(param1ViewGroup.getContext()).inflate(2131492930, param1ViewGroup, false);
      return new ChangeIconAddGroupFragment.IconViewHolder(view);
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


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addinggroup\ChangeIconAddGroupFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */