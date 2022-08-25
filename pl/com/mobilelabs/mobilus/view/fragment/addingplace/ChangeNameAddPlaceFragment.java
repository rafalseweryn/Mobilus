package pl.com.mobilelabs.mobilus.view.fragment.addingplace;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.model.objects.Place;
import pl.com.mobilelabs.mobilus.view.activity.AddingPlaceActivity;

public class ChangeNameAddPlaceFragment extends AddingPlaceFragment {
  @BindView(2131296485)
  protected TextInputEditText nameEditText;
  
  @BindView(2131296487)
  protected TextView nextButtonTitle;
  
  @BindView(2131296481)
  protected ImageView placeIcon;
  
  @BindView(2131296483)
  protected TextView placeName;
  
  private void presentData() {
    Place place = extractPlace();
    if (place != null)
      showPlace(place); 
  }
  
  private void showPlace(Place paramPlace) {
    if (!paramPlace.getName().isEmpty())
      this.placeName.setText(paramPlace.getName()); 
    this.nameEditText.setText(paramPlace.getName());
    this.placeIcon.setImageResource(2131558655);
  }
  
  public void backSelected() {
    AddingPlaceActivity addingPlaceActivity = (AddingPlaceActivity)getActivity();
    if (addingPlaceActivity != null)
      addingPlaceActivity.finish(); 
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
    this.nextButtonTitle.setEnabled(false);
  }
  
  protected void newConfigurationReceived(Context paramContext, Intent paramIntent) {
    super.newConfigurationReceived(paramContext, paramIntent);
    if (extractPlace() != null)
      presentData(); 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      view1 = paramLayoutInflater.inflate(2131492904, paramViewGroup, false);
      ButterKnife.bind(this, view1);
      this.placeName.setText(2131624130);
      this.nextButtonTitle.setText(2131624133);
      this.managementServiceBinder = ((AddingPlaceActivity)getActivity()).getManagementServiceBinder();
      this.nameEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable param1Editable) {
              ChangeNameAddPlaceFragment.this.nextButtonTitle.setEnabled(ChangeNameAddPlaceFragment.this.nameEditText.getText().toString().isEmpty() ^ true);
            }
            
            public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
            
            public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
          });
      presentData();
    } 
    return view1;
  }
  
  @OnClick({2131296486})
  protected void saveButtonClicked() {
    Place place = extractPlace();
    AddingPlaceActivity addingPlaceActivity = (AddingPlaceActivity)getActivity();
    if (place != null && this.nextButtonTitle.isEnabled() && addingPlaceActivity != null) {
      place.setName(this.nameEditText.getText().toString());
      addingPlaceActivity.changeBackButtonImage(2131558473);
      addingPlaceActivity.changeView(2131492901, new AssignDeviceAddPlaceFragment());
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addingplace\ChangeNameAddPlaceFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */