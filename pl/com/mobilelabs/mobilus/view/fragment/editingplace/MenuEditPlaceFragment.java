package pl.com.mobilelabs.mobilus.view.fragment.editingplace;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.model.objects.Place;
import pl.com.mobilelabs.mobilus.view.activity.DoSthWithSthInterface;
import pl.com.mobilelabs.mobilus.view.activity.EditingPlaceChildActivity;
import pl.com.mobilelabs.mobilus.view.activity.UltimateHeaderActivity;

public class MenuEditPlaceFragment extends EditingPlaceFragment {
  @BindView(2131296672)
  protected TextView placeName;
  
  protected boolean runForYourLife = true;
  
  private void presentData() {
    Place place = extractPlace();
    if (place != null)
      showPlace(place); 
  }
  
  private void showPlace(Place paramPlace) {
    this.placeName.setText(paramPlace.getName());
  }
  
  @OnClick({2131296668})
  protected void assignDeviceToPlaceClicked() {
    this.runForYourLife = true;
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    Intent intent = new Intent(getContext(), EditingPlaceChildActivity.class);
    intent.putExtra("EditingPlaceChildActivity.placeId", ((DoSthWithSthInterface)ultimateHeaderActivity).getNewSomethingID());
    intent.putExtra("EditingPlaceChildActivity.fragmentResource", 2131492901);
    intent.putExtra("EditingPlaceChildActivity.deviceFragment", true);
    startActivityForResult(intent, 9);
  }
  
  @OnClick({2131296669})
  protected void assignGroupToPlaceClicked() {
    this.runForYourLife = true;
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    Intent intent = new Intent(getContext(), EditingPlaceChildActivity.class);
    intent.putExtra("EditingPlaceChildActivity.placeId", ((DoSthWithSthInterface)ultimateHeaderActivity).getNewSomethingID());
    intent.putExtra("EditingPlaceChildActivity.fragmentResource", 2131492901);
    intent.putExtra("EditingPlaceChildActivity.deviceFragment", false);
    startActivityForResult(intent, 9);
  }
  
  public void backSelected() {}
  
  @OnClick({2131296670})
  protected void changeNameClicked() {
    this.runForYourLife = true;
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    Intent intent = new Intent(getContext(), EditingPlaceChildActivity.class);
    intent.putExtra("EditingPlaceChildActivity.placeId", ((DoSthWithSthInterface)ultimateHeaderActivity).getNewSomethingID());
    intent.putExtra("EditingPlaceChildActivity.fragmentResource", 2131492904);
    startActivityForResult(intent, 9);
  }
  
  protected void deviceActionFinished(Context paramContext, Intent paramIntent) {
    if (extractPlace() != null)
      presentData(); 
  }
  
  protected void deviceActionStarted(Context paramContext, Intent paramIntent) {
    if (extractPlace() != null)
      presentData(); 
  }
  
  public void managementServiceConnected() {
    presentData();
  }
  
  protected void newConfigurationReceived(Context paramContext, Intent paramIntent) {
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    if (extractPlace() == null && ultimateHeaderActivity != null && this.runForYourLife)
      ultimateHeaderActivity.finish(); 
    if (extractPlace() != null)
      presentData(); 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492933, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      presentData();
    } 
    return view1;
  }
  
  @OnClick({2131296673})
  protected void removePlaceClicked() {
    this.runForYourLife = true;
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    Intent intent = new Intent(getContext(), EditingPlaceChildActivity.class);
    intent.putExtra("EditingPlaceChildActivity.placeId", ((DoSthWithSthInterface)ultimateHeaderActivity).getNewSomethingID());
    intent.putExtra("EditingPlaceChildActivity.fragmentResource", 2131492907);
    startActivityForResult(intent, 9);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingplace\MenuEditPlaceFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */