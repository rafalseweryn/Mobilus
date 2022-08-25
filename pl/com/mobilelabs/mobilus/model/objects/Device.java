package pl.com.mobilelabs.mobilus.model.objects;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pl.com.mobilelabs.mobilus.model.communication.MobilusModel;

public class Device extends BaseObject implements Comparable<Device> {
  private boolean favourite;
  
  private ArrayList<Group> groups;
  
  private ArrayList<Place> places;
  
  private DeviceType type;
  
  private Device(long paramLong, String paramString, int paramInt, DeviceType paramDeviceType, boolean paramBoolean) {
    super(paramLong, paramString, paramInt);
    this.type = paramDeviceType;
    this.favourite = paramBoolean;
    this.groups = new ArrayList<>();
    this.places = new ArrayList<>();
  }
  
  public static Device createFrom(MobilusModel.Device paramDevice) {
    if (!paramDevice.hasId() || !paramDevice.hasName() || !paramDevice.hasType() || !paramDevice.hasIcon())
      return null; 
    DeviceType deviceType = DeviceType.fromValue(paramDevice.getType());
    if (deviceType == null)
      return null; 
    boolean bool = false;
    if (paramDevice.hasFavourite())
      bool = paramDevice.getFavourite(); 
    return new Device(paramDevice.getId(), paramDevice.getName(), paramDevice.getIcon(), deviceType, bool);
  }
  
  public void addGroup(Group paramGroup) {
    for (byte b = 0; b < this.groups.size(); b++) {
      if (((Group)this.groups.get(b)).getId() == paramGroup.getId()) {
        this.groups.remove(b);
        break;
      } 
    } 
    this.groups.add(paramGroup);
    Collections.sort(this.groups);
  }
  
  public void addPlace(Place paramPlace) {
    for (byte b = 0; b < this.places.size(); b++) {
      if (((Place)this.places.get(b)).getId() == paramPlace.getId()) {
        this.places.remove(b);
        break;
      } 
    } 
    this.places.add(paramPlace);
    Collections.sort(this.places);
  }
  
  public boolean belongsToGroup(long paramLong) {
    return belongsTo((List)this.groups, paramLong);
  }
  
  public boolean belongsToPlace(long paramLong) {
    return belongsTo((List)this.places, paramLong);
  }
  
  public int compareTo(@NonNull Device paramDevice) {
    if (this.favourite) {
      if (!paramDevice.favourite)
        return -1; 
      int j = getName().compareTo(paramDevice.getName());
      if (j != 0)
        return j; 
      j = getId() cmp paramDevice.getId();
      return (j < 0) ? -1 : ((j > 0) ? 1 : 0);
    } 
    if (paramDevice.favourite)
      return 1; 
    int i = getName().compareTo(paramDevice.getName());
    if (i != 0)
      return i; 
    i = getId() cmp paramDevice.getId();
    return (i < 0) ? -1 : ((i > 0) ? 1 : 0);
  }
  
  public Group[] getGroups() {
    return this.groups.<Group>toArray(new Group[this.groups.size()]);
  }
  
  public Place[] getPlaces() {
    return this.places.<Place>toArray(new Place[this.places.size()]);
  }
  
  public DeviceType getType() {
    return this.type;
  }
  
  public boolean isFavourite() {
    return this.favourite;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\objects\Device.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */