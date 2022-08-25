package pl.com.mobilelabs.mobilus.view.fragment.addingdevice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.activity.AddingDeviceActivity;

public class ProgrammingModeAddingDeviceFragment extends AddingDeviceFragment {
  public void backSelected() {}
  
  @OnClick({2131296699})
  protected void nextClicked() {
    AddingDeviceActivity addingDeviceActivity = (AddingDeviceActivity)getActivity();
    if (addingDeviceActivity != null) {
      ManagementService.ManagementServiceBinder managementServiceBinder = addingDeviceActivity.getManagementServiceBinder();
      if (managementServiceBinder != null && managementServiceBinder.startProgrammingNewDevice())
        addingDeviceActivity.changeView(2131492906, new CountdownAddingDeviceFragment()); 
    } 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492952, paramViewGroup, false);
      ButterKnife.bind(this, view1);
    } 
    return view1;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addingdevice\ProgrammingModeAddingDeviceFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */