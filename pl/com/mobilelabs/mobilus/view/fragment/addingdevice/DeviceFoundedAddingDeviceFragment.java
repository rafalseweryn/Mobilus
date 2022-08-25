package pl.com.mobilelabs.mobilus.view.fragment.addingdevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.Arrays;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.activity.AddingDeviceActivity;

public class DeviceFoundedAddingDeviceFragment extends AddingDeviceFragment {
  private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.updateDeviceResponseReceived")) {
          boolean bool = false;
          switch (param1Intent.getIntExtra("ManagementService.Intents.updateDeviceResponseReceivedOperationStatus", 0)) {
            case 2:
              Toast.makeText(param1Context, 2131624139, 0).show();
              break;
            case 1:
              Toast.makeText(param1Context, 2131624103, 0).show();
              break;
            case 0:
              Toast.makeText(param1Context, 2131624141, 0).show();
              bool = true;
              break;
          } 
          if (bool) {
            AddingDeviceActivity addingDeviceActivity = (AddingDeviceActivity)DeviceFoundedAddingDeviceFragment.this.getActivity();
            if (addingDeviceActivity != null) {
              addingDeviceActivity.changeView(2131492961, new SuccessAddingDeviceFragment());
              addingDeviceActivity.changeBackButtonImage(2131558473);
            } 
          } else {
            AddingDeviceActivity addingDeviceActivity = (AddingDeviceActivity)DeviceFoundedAddingDeviceFragment.this.getActivity();
            if (addingDeviceActivity != null) {
              addingDeviceActivity.changeView(2131492927, new FailureAddingDeviceFragment());
              addingDeviceActivity.changeBackButtonImage(2131558473);
            } 
          } 
        } 
      }
    };
  
  private long deviceId = -1L;
  
  private int deviceType = -1;
  
  @BindView(2131296553)
  protected TextView typeTextView;
  
  public void backSelected() {}
  
  @OnClick({2131296549})
  protected void nextClicked() {
    AddingDeviceActivity addingDeviceActivity = (AddingDeviceActivity)getActivity();
    if (addingDeviceActivity != null) {
      ManagementService.ManagementServiceBinder managementServiceBinder = addingDeviceActivity.getManagementServiceBinder();
      if (managementServiceBinder != null)
        managementServiceBinder.addNewDevice(this.deviceId, this.deviceType); 
    } 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      View view = paramLayoutInflater.inflate(2131492925, paramViewGroup, false);
      ButterKnife.bind(this, view);
      IntentFilter intentFilter = new IntentFilter("ManagementService.Intents.updateDeviceResponseReceived");
      LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.broadcastReceiver, intentFilter);
      AddingDeviceActivity addingDeviceActivity = (AddingDeviceActivity)getActivity();
      if (addingDeviceActivity != null) {
        this.deviceId = addingDeviceActivity.getNewDeviceID();
        this.deviceType = addingDeviceActivity.getNewDeviceType();
      } 
      view1 = view;
      if (this.deviceId >= 0L) {
        view1 = view;
        if (this.deviceType >= 0) {
          ArrayList<CharSequence> arrayList = new ArrayList(Arrays.asList((Object[])getResources().getStringArray(2130903040)));
          view1 = view;
          if (this.deviceType > 0) {
            view1 = view;
            if (this.deviceType < arrayList.size()) {
              this.typeTextView.setText(arrayList.get(this.deviceType));
              view1 = view;
            } 
          } 
        } 
      } 
    } 
    return view1;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addingdevice\DeviceFoundedAddingDeviceFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */