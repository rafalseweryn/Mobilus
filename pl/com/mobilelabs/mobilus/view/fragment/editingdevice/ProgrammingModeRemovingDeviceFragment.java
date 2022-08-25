package pl.com.mobilelabs.mobilus.view.fragment.editingdevice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.view.activity.EditingDeviceChildActivity;

public class ProgrammingModeRemovingDeviceFragment extends EditingDeviceFragment {
  public void backSelected() {
    EditingDeviceChildActivity editingDeviceChildActivity = (EditingDeviceChildActivity)getActivity();
    if (editingDeviceChildActivity != null)
      editingDeviceChildActivity.setResult(-1); 
  }
  
  public void managementServiceConnected() {}
  
  @OnClick({2131296703})
  protected void nextClicked() {
    EditingDeviceChildActivity editingDeviceChildActivity = (EditingDeviceChildActivity)getActivity();
    if (editingDeviceChildActivity != null)
      editingDeviceChildActivity.changeView(2131492907, new CountdownRemovingDeviceFragment()); 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492953, paramViewGroup, false);
      ButterKnife.bind(this, view1);
    } 
    return view1;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingdevice\ProgrammingModeRemovingDeviceFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */