package pl.com.mobilelabs.mobilus.model.objects;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import pl.com.mobilelabs.mobilus.model.communication.MobilusModel;

public class Place extends BaseObject implements Comparable<Place> {
  private ArrayList<Device> devices = new ArrayList<>();
  
  private ArrayList<Group> groups = new ArrayList<>();
  
  Place(long paramLong, String paramString, int paramInt) {
    super(paramLong, paramString, paramInt);
  }
  
  public static Place createFrom(MobilusModel.DevicePlace paramDevicePlace) {
    return (!paramDevicePlace.hasId() || !paramDevicePlace.hasName() || !paramDevicePlace.hasIcon()) ? null : new Place(paramDevicePlace.getId(), paramDevicePlace.getName(), paramDevicePlace.getIcon());
  }
  
  public void addDevice(Device paramDevice) {
    for (byte b = 0; b < this.devices.size(); b++) {
      if (((Device)this.devices.get(b)).getId() == paramDevice.getId()) {
        this.devices.remove(b);
        break;
      } 
    } 
    this.devices.add(paramDevice);
    Collections.sort(this.devices);
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
  
  public void assignDevices(List<Device> paramList) {
    this.devices.clear();
    Iterator<Device> iterator = paramList.iterator();
    while (iterator.hasNext())
      addDevice(iterator.next()); 
  }
  
  public void assignGroup(Group paramGroup, boolean paramBoolean) {
    for (byte b = 0; b < this.groups.size(); b++) {
      if (((Group)this.groups.get(b)).getId() == paramGroup.getId())
        this.groups.remove(b); 
    } 
    if (paramBoolean)
      this.groups.add(paramGroup); 
  }
  
  public int compareTo(@NonNull Place paramPlace) {
    int i = getName().compareTo(paramPlace.getName());
    if (i != 0)
      return i; 
    i = getId() cmp paramPlace.getId();
    return (i < 0) ? -1 : ((i > 0) ? 1 : 0);
  }
  
  public boolean deviceBelongs(long paramLong) {
    return belongsTo((List)this.devices, paramLong);
  }
  
  public Device[] getDevices() {
    return this.devices.<Device>toArray(new Device[this.devices.size()]);
  }
  
  public Group[] getGroups() {
    return this.groups.<Group>toArray(new Group[this.groups.size()]);
  }
  
  public boolean groupBelongs(long paramLong) {
    return belongsTo((List)this.groups, paramLong);
  }
  
  public void setName(String paramString) {
    this.name = paramString;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\objects\Place.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */