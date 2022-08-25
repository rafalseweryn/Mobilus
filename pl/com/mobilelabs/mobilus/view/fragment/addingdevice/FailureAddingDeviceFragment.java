package pl.com.mobilelabs.mobilus.view.fragment.addingdevice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.view.activity.AddingDeviceActivity;

public class FailureAddingDeviceFragment extends AddingDeviceFragment {
  public void backSelected() {}
  
  @OnClick({2131296579})
  protected void nextClicked() {
    AddingDeviceActivity addingDeviceActivity = (AddingDeviceActivity)getActivity();
    if (addingDeviceActivity != null)
      addingDeviceActivity.finish(); 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492927, paramViewGroup, false);
      ButterKnife.bind(this, view1);
    } 
    return view1;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addingdevice\FailureAddingDeviceFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */