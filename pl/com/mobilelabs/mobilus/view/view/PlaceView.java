package pl.com.mobilelabs.mobilus.view.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.model.objects.Place;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.activity.EditingPlaceActivity;
import pl.com.mobilelabs.mobilus.view.fragment.IDevicesGroupsPlacesScenesListFragmentContext;

public class PlaceView extends RelativeLayout implements IGetPresentedObjectIdInterface {
  private static final String TAG = "PlaceLayout";
  
  private boolean alreadyInflated = false;
  
  @BindView(2131296564)
  RelativeLayout editLayout;
  
  @BindView(2131296838)
  TextView nameTextView;
  
  private Place place;
  
  public PlaceView(Context paramContext) {
    super(paramContext);
  }
  
  public static PlaceView build(Context paramContext) {
    PlaceView placeView = new PlaceView(paramContext);
    placeView.onFinishInflate();
    return placeView;
  }
  
  public void bind(Place paramPlace) {
    this.place = paramPlace;
    this.nameTextView.setText(paramPlace.getName());
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getContext()).getManagementServiceBinder();
    if (managementServiceBinder != null && managementServiceBinder.isUserAdmin()) {
      this.editLayout.setVisibility(0);
    } else {
      this.editLayout.setVisibility(4);
    } 
  }
  
  @OnClick({2131296563})
  protected void editButtonClicked() {
    Intent intent = new Intent(getContext(), EditingPlaceActivity.class);
    intent.putExtra("EditingPlaceActivity.somethingId", this.place.getId());
    getContext().startActivity(intent);
  }
  
  public BaseObject getPresentedObject() {
    return (BaseObject)this.place;
  }
  
  public void onFinishInflate() {
    if (!this.alreadyInflated) {
      this.alreadyInflated = true;
      inflate(getContext(), 2131492974, (ViewGroup)this);
      ButterKnife.bind((View)this);
    } 
    super.onFinishInflate();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\view\PlaceView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */