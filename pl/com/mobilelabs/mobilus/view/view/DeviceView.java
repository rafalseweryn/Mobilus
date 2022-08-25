package pl.com.mobilelabs.mobilus.view.view;

import android.content.Context;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.model.Action;
import pl.com.mobilelabs.mobilus.model.Error;
import pl.com.mobilelabs.mobilus.model.ErrorType;
import pl.com.mobilelabs.mobilus.model.Reading;
import pl.com.mobilelabs.mobilus.model.State;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.model.objects.Device;
import pl.com.mobilelabs.mobilus.model.objects.Group;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.activity.EditingDeviceActivity;
import pl.com.mobilelabs.mobilus.view.activity.EditingGroupActivity;
import pl.com.mobilelabs.mobilus.view.fragment.IDevicesGroupsPlacesScenesListFragmentContext;

public abstract class DeviceView extends RelativeLayout implements IGetPresentedObjectIdInterface {
  @BindView(2131296546)
  CheckBox deviceFavouriteCheckBox;
  
  @BindView(2131296554)
  TextView deviceIdTextView;
  
  @BindView(2131296557)
  DeviceStateView deviceStateLayout;
  
  @BindView(2131296545)
  ImageButton editButton;
  
  private IFavouriteLongClickedListener listener;
  
  protected BaseObject presentedObject;
  
  public DeviceView(Context paramContext) {
    super(paramContext);
  }
  
  public void bind(BaseObject paramBaseObject) {
    this.presentedObject = paramBaseObject;
    this.deviceIdTextView.setText(this.presentedObject.getName());
    boolean bool = paramBaseObject instanceof Device;
    if (bool || paramBaseObject instanceof Group) {
      this.deviceFavouriteCheckBox.setVisibility(0);
      ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
      if (managementServiceBinder != null && managementServiceBinder.isUserAdmin()) {
        this.editButton.setVisibility(0);
      } else {
        this.editButton.setVisibility(8);
      } 
      if (bool) {
        this.deviceFavouriteCheckBox.setChecked(((Device)paramBaseObject).isFavourite());
      } else {
        this.deviceFavouriteCheckBox.setChecked(((Group)paramBaseObject).isFavourite());
      } 
      return;
    } 
    this.deviceFavouriteCheckBox.setVisibility(8);
    this.editButton.setVisibility(8);
  }
  
  @OnClick({2131296557})
  public void deviceIconClicked() {
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null && managementServiceBinder.sendControlDeviceMessage(this.presentedObject.getId(), Action.INFO) == true)
      this.deviceStateLayout.setWorking(true); 
  }
  
  @OnClick({2131296545})
  public void editButtonClicked() {
    if (this.presentedObject instanceof Device) {
      Intent intent = new Intent(getContext(), EditingDeviceActivity.class);
      intent.putExtra("EditingDeviceActivity.deviceId", this.presentedObject.getId());
      getContext().startActivity(intent);
    } else if (this.presentedObject instanceof Group) {
      Intent intent = new Intent(getContext(), EditingGroupActivity.class);
      intent.putExtra("EditingGroupActivity.somethingId", this.presentedObject.getId());
      getContext().startActivity(intent);
    } 
  }
  
  @OnClick({2131296546})
  public void favouriteCheckboxClicked() {
    if (this.presentedObject instanceof Device) {
      ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder().editDeviceFavourite(this.presentedObject.getId(), this.deviceFavouriteCheckBox.isChecked());
    } else if (this.presentedObject instanceof Group) {
      ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder().editDeviceGroupFavourite(this.presentedObject.getId(), this.deviceFavouriteCheckBox.isChecked());
    } 
  }
  
  public BaseObject getPresentedObject() {
    return this.presentedObject;
  }
  
  public void setListener(IFavouriteLongClickedListener paramIFavouriteLongClickedListener) {
    this.listener = paramIFavouriteLongClickedListener;
  }
  
  public void setState(State paramState) {
    int i;
    boolean bool = true;
    if (paramState == null) {
      this.deviceStateLayout.setWorking(false);
      i = IconsGenerator.getErrorImageResource(this.presentedObject, true);
    } else {
      this.deviceStateLayout.setWorking(paramState.isWorking());
      if (paramState instanceof Error) {
        Error error = (Error)paramState;
        BaseObject baseObject = this.presentedObject;
        if (error.getErrorType() != ErrorType.NO_CONNECTION)
          bool = false; 
        i = IconsGenerator.getErrorImageResource(baseObject, bool);
      } else {
        Reading reading = (Reading)paramState;
        if (reading.getAction() == Action.ON || reading.getAction() == Action.UP) {
          i = IconsGenerator.getTotallyOpenedImageResource(this.presentedObject);
        } else if (reading.getAction() == Action.OFF || reading.getAction() == Action.DOWN) {
          i = IconsGenerator.getTotallyClosedImageResource(this.presentedObject);
        } else {
          i = IconsGenerator.getPositionImageResource(this.presentedObject, reading.getPosition());
        } 
      } 
    } 
    this.deviceStateLayout.bind(i, paramState);
  }
  
  public static interface IFavouriteLongClickedListener {
    void favouriteLongClicked();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\DeviceView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */