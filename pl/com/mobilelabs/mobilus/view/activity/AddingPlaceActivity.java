package pl.com.mobilelabs.mobilus.view.activity;

import android.os.Bundle;
import pl.com.mobilelabs.mobilus.model.communication.MobilusModel;
import pl.com.mobilelabs.mobilus.model.objects.Place;
import pl.com.mobilelabs.mobilus.view.fragment.DoSthDeviceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.addingplace.ChangeNameAddPlaceFragment;

public class AddingPlaceActivity extends UltimateHeaderActivity implements DoSthWithSthInterface {
  protected Place newPlace;
  
  protected void backSelected() {
    this.mFragment.backSelected();
  }
  
  public Place getNewPlace() {
    return this.newPlace;
  }
  
  public long getNewSomethingID() {
    return 0L;
  }
  
  public int getType() {
    return 2;
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    MobilusModel.DevicePlace.Builder builder = MobilusModel.DevicePlace.newBuilder();
    builder.setId(-1L);
    builder.setName("");
    builder.setIcon(1);
    this.newPlace = Place.createFrom(builder.build());
    initializeActivity(2131492904, (DoSthDeviceFragment)new ChangeNameAddPlaceFragment());
    configureView(2131558402, 2131623984, 2131624051, 2131099699, 2131558462);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\AddingPlaceActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */