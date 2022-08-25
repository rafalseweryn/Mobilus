package pl.com.mobilelabs.mobilus.view.fragment.addingdevice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.view.activity.AddingDeviceActivity;
import pl.com.mobilelabs.mobilus.view.fragment.DoSthDeviceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.editingdevice.MenuEditDeviceFragment;

public class SuccessAddingDeviceFragment extends AddingDeviceFragment {
  public void backSelected() {}
  
  @OnClick({2131296787})
  protected void nextClicked() {
    AddingDeviceActivity addingDeviceActivity = (AddingDeviceActivity)getActivity();
    if (addingDeviceActivity != null) {
      addingDeviceActivity.changeView(2131492931, (DoSthDeviceFragment)new MenuEditDeviceFragment());
      addingDeviceActivity.changeTitleText(2131624049);
      addingDeviceActivity.changeIconText(2131624063);
      addingDeviceActivity.changeHeaderColor(2131099698);
      addingDeviceActivity.changeIconImage(2131558521);
      addingDeviceActivity.changeBackButtonImage(2131558473);
    } 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492961, paramViewGroup, false);
      ButterKnife.bind(this, view1);
    } 
    return view1;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addingdevice\SuccessAddingDeviceFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */