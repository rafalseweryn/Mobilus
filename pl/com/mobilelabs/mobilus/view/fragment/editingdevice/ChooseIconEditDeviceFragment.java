package pl.com.mobilelabs.mobilus.view.fragment.editingdevice;

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
import pl.com.mobilelabs.mobilus.model.objects.Device;
import pl.com.mobilelabs.mobilus.view.activity.EditingDeviceChildActivity;
import pl.com.mobilelabs.mobilus.view.view.IconsGenerator;

public class ChooseIconEditDeviceFragment extends EditingDeviceFragment {
  @BindView(2131296493)
  protected ImageView deviceIcon;
  
  @BindView(2131296495)
  protected TextView deviceName;
  
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
    byte b1 = 5;
    byte b2 = b1;
    if (paramInt != 5)
      if (paramInt == 6) {
        b2 = b1;
      } else {
        for (paramInt = 1; paramInt <= 4; paramInt++)
          this.iconList.add(Integer.valueOf(paramInt)); 
        for (paramInt = 32; paramInt <= 35; paramInt++)
          this.iconList.add(Integer.valueOf(paramInt)); 
        return;
      }  
    while (b2 <= 31) {
      this.iconList.add(Integer.valueOf(b2));
      b2++;
    } 
  }
  
  private void presentData() {
    Device device = extractDevice();
    if (device != null) {
      showDevice(device);
      populateIconList(device.getType().getValue());
      this.mAdapter.setPositionSelected(this.iconList.indexOf(Integer.valueOf(device.getIcon())));
    } 
  }
  
  private void showDevice(Device paramDevice) {
    int i;
    this.deviceName.setText(paramDevice.getName());
    State state = (State)this.managementServiceBinder.getDeviceCurrentState().get(Long.valueOf(paramDevice.getId()));
    boolean bool = true;
    if (state == null) {
      i = IconsGenerator.getErrorImageResource((BaseObject)paramDevice, true);
    } else if (state instanceof Error) {
      if (((Error)state).getErrorType() != ErrorType.NO_CONNECTION)
        bool = false; 
      i = IconsGenerator.getErrorImageResource((BaseObject)paramDevice, bool);
    } else {
      Reading reading = (Reading)state;
      if (reading.getAction() == Action.ON || reading.getAction() == Action.UP) {
        i = IconsGenerator.getTotallyOpenedImageResource((BaseObject)paramDevice);
      } else if (reading.getAction() == Action.OFF || reading.getAction() == Action.DOWN) {
        i = IconsGenerator.getTotallyClosedImageResource((BaseObject)paramDevice);
      } else {
        i = IconsGenerator.getPositionImageResource((BaseObject)paramDevice, reading.getPosition());
      } 
    } 
    this.deviceIcon.setImageResource(i);
  }
  
  public void backSelected() {}
  
  protected void configureIntentFilter() {
    super.configureIntentFilter();
    this.intentFilter.addAction("ManagementService.Intents.updateDeviceResponseReceived");
  }
  
  protected void deviceActionFinished(Context paramContext, Intent paramIntent) {
    if (extractDevice() != null)
      presentData(); 
  }
  
  protected void deviceActionStarted(Context paramContext, Intent paramIntent) {
    if (extractDevice() != null)
      presentData(); 
  }
  
  public void managementServiceConnected() {
    presentData();
  }
  
  protected void newConfigurationReceived(Context paramContext, Intent paramIntent) {
    super.newConfigurationReceived(paramContext, paramIntent);
    if (extractDevice() != null)
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
    Device device = extractDevice();
    if (device != null && this.saveButtonTitle.isEnabled())
      this.managementServiceBinder.editDeviceIcon(device.getId(), ((Integer)this.iconList.get(this.mAdapter.getPositionSelected())).intValue()); 
  }
  
  protected void somethingElseReceived(Context paramContext, Intent paramIntent) {
    super.somethingElseReceived(paramContext, paramIntent);
    if (paramIntent.getAction().equalsIgnoreCase("ManagementService.Intents.updateDeviceResponseReceived")) {
      boolean bool = false;
      switch (paramIntent.getIntExtra("ManagementService.Intents.updateDeviceResponseReceivedOperationStatus", 0)) {
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
        EditingDeviceChildActivity editingDeviceChildActivity = (EditingDeviceChildActivity)getActivity();
        if (editingDeviceChildActivity != null) {
          editingDeviceChildActivity.setResult(-1);
          editingDeviceChildActivity.finish();
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
        ChooseIconEditDeviceFragment.this.saveButtonTitle.setEnabled(true);
      } 
    }
    
    public int getItemCount() {
      return (ChooseIconEditDeviceFragment.this.iconList != null) ? ChooseIconEditDeviceFragment.this.iconList.size() : 0;
    }
    
    public int getPositionSelected() {
      return this.positionSelected;
    }
    
    public void onBindViewHolder(ChooseIconEditDeviceFragment.IconViewHolder param1IconViewHolder, final int position) {
      if (ChooseIconEditDeviceFragment.this.iconList != null) {
        boolean bool2;
        ImageButton imageButton = param1IconViewHolder.iconImageButton;
        int i = ((Integer)ChooseIconEditDeviceFragment.this.iconList.get(position)).intValue();
        int j = this.positionSelected;
        boolean bool1 = true;
        if (j == position) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        imageButton.setImageResource(IconsGenerator.getSelectionImageResource(i, bool2, false));
        imageButton = param1IconViewHolder.iconImageButton;
        if (this.positionSelected == position) {
          bool2 = bool1;
        } else {
          bool2 = false;
        } 
        imageButton.setSelected(bool2);
        param1IconViewHolder.iconImageButton.setOnClickListener(new View.OnClickListener() {
              public void onClick(View param2View) {
                ChooseIconEditDeviceFragment.IconAdapter.this.changeSelection(position);
              }
            });
      } 
    }
    
    public ChooseIconEditDeviceFragment.IconViewHolder onCreateViewHolder(ViewGroup param1ViewGroup, int param1Int) {
      View view = LayoutInflater.from(param1ViewGroup.getContext()).inflate(2131492930, param1ViewGroup, false);
      return new ChooseIconEditDeviceFragment.IconViewHolder(view);
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


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingdevice\ChooseIconEditDeviceFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */