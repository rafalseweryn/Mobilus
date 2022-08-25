package pl.com.mobilelabs.mobilus.model.objects;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import pl.com.mobilelabs.mobilus.model.communication.MobilusModel;

public class Group extends BaseObject implements Comparable<Group> {
  private ArrayList<Device> devices;
  
  private boolean favourite;
  
  private ArrayList<Place> places;
  
  private DeviceType type;
  
  Group(long paramLong, String paramString, int paramInt, boolean paramBoolean) {
    super(paramLong, paramString, paramInt);
    this.favourite = paramBoolean;
    this.devices = new ArrayList<>();
    this.places = new ArrayList<>();
  }
  
  public static Group createFrom(MobilusModel.DeviceGroup paramDeviceGroup) {
    if (!paramDeviceGroup.hasId() || !paramDeviceGroup.hasName() || !paramDeviceGroup.hasIcon())
      return null; 
    boolean bool = false;
    if (paramDeviceGroup.hasFavourite())
      bool = paramDeviceGroup.getFavourite(); 
    return new Group(paramDeviceGroup.getId(), paramDeviceGroup.getName(), paramDeviceGroup.getIcon(), bool);
  }
  
  private DeviceType updateGroupType(DeviceType paramDeviceType, Device paramDevice) {
    // Byte code:
    //   0: aload_2
    //   1: invokevirtual getType : ()Lpl/com/mobilelabs/mobilus/model/objects/DeviceType;
    //   4: astore_3
    //   5: aload_3
    //   6: getstatic pl/com/mobilelabs/mobilus/model/objects/DeviceType.SENSO_Z : Lpl/com/mobilelabs/mobilus/model/objects/DeviceType;
    //   9: if_acmpne -> 29
    //   12: aload_2
    //   13: invokevirtual getIcon : ()I
    //   16: bipush #35
    //   18: if_icmpeq -> 29
    //   21: getstatic pl/com/mobilelabs/mobilus/model/objects/DeviceType.SENSO : Lpl/com/mobilelabs/mobilus/model/objects/DeviceType;
    //   24: astore #4
    //   26: goto -> 63
    //   29: aload_3
    //   30: getstatic pl/com/mobilelabs/mobilus/model/objects/DeviceType.CZR : Lpl/com/mobilelabs/mobilus/model/objects/DeviceType;
    //   33: if_acmpeq -> 46
    //   36: aload_3
    //   37: astore #4
    //   39: aload_3
    //   40: getstatic pl/com/mobilelabs/mobilus/model/objects/DeviceType.MZR : Lpl/com/mobilelabs/mobilus/model/objects/DeviceType;
    //   43: if_acmpne -> 63
    //   46: aload_3
    //   47: astore #4
    //   49: aload_2
    //   50: invokevirtual getIcon : ()I
    //   53: bipush #35
    //   55: if_icmpeq -> 63
    //   58: getstatic pl/com/mobilelabs/mobilus/model/objects/DeviceType.COSMO : Lpl/com/mobilelabs/mobilus/model/objects/DeviceType;
    //   61: astore #4
    //   63: aload_1
    //   64: ifnonnull -> 79
    //   67: aload #4
    //   69: invokevirtual getValue : ()I
    //   72: invokestatic fromValue : (I)Lpl/com/mobilelabs/mobilus/model/objects/DeviceType;
    //   75: astore_2
    //   76: goto -> 173
    //   79: aload_1
    //   80: astore_2
    //   81: aload_1
    //   82: aload #4
    //   84: if_acmpeq -> 173
    //   87: aload_1
    //   88: getstatic pl/com/mobilelabs/mobilus/model/objects/DeviceType.SENSO_Z : Lpl/com/mobilelabs/mobilus/model/objects/DeviceType;
    //   91: if_acmpne -> 105
    //   94: aload #4
    //   96: getstatic pl/com/mobilelabs/mobilus/model/objects/DeviceType.SENSO_Z : Lpl/com/mobilelabs/mobilus/model/objects/DeviceType;
    //   99: if_acmpeq -> 105
    //   102: goto -> 170
    //   105: aload_1
    //   106: getstatic pl/com/mobilelabs/mobilus/model/objects/DeviceType.SENSO : Lpl/com/mobilelabs/mobilus/model/objects/DeviceType;
    //   109: if_acmpne -> 129
    //   112: aload_1
    //   113: astore_2
    //   114: aload #4
    //   116: getstatic pl/com/mobilelabs/mobilus/model/objects/DeviceType.SENSO_Z : Lpl/com/mobilelabs/mobilus/model/objects/DeviceType;
    //   119: if_acmpeq -> 173
    //   122: getstatic pl/com/mobilelabs/mobilus/model/objects/DeviceType.COSMO : Lpl/com/mobilelabs/mobilus/model/objects/DeviceType;
    //   125: astore_2
    //   126: goto -> 173
    //   129: aload_1
    //   130: getstatic pl/com/mobilelabs/mobilus/model/objects/DeviceType.CZR : Lpl/com/mobilelabs/mobilus/model/objects/DeviceType;
    //   133: if_acmpeq -> 145
    //   136: aload_1
    //   137: astore_2
    //   138: aload_1
    //   139: getstatic pl/com/mobilelabs/mobilus/model/objects/DeviceType.MZR : Lpl/com/mobilelabs/mobilus/model/objects/DeviceType;
    //   142: if_acmpne -> 173
    //   145: aload #4
    //   147: getstatic pl/com/mobilelabs/mobilus/model/objects/DeviceType.SENSO : Lpl/com/mobilelabs/mobilus/model/objects/DeviceType;
    //   150: if_acmpne -> 160
    //   153: getstatic pl/com/mobilelabs/mobilus/model/objects/DeviceType.COSMO : Lpl/com/mobilelabs/mobilus/model/objects/DeviceType;
    //   156: astore_2
    //   157: goto -> 173
    //   160: aload_1
    //   161: astore_2
    //   162: aload #4
    //   164: getstatic pl/com/mobilelabs/mobilus/model/objects/DeviceType.SENSO_Z : Lpl/com/mobilelabs/mobilus/model/objects/DeviceType;
    //   167: if_acmpeq -> 173
    //   170: aload #4
    //   172: astore_2
    //   173: aload_2
    //   174: areturn
  }
  
  public void addDevice(Device paramDevice) {
    this.type = updateGroupType(this.type, paramDevice);
    for (byte b = 0; b < this.devices.size(); b++) {
      if (((Device)this.devices.get(b)).getId() == paramDevice.getId()) {
        this.devices.remove(b);
        break;
      } 
    } 
    this.devices.add(paramDevice);
    Collections.sort(this.devices);
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
  
  public void assignDevices(List<Device> paramList) {
    this.devices.clear();
    this.type = null;
    Iterator<Device> iterator = paramList.iterator();
    while (iterator.hasNext())
      addDevice(iterator.next()); 
  }
  
  public void assignPlace(Place paramPlace, boolean paramBoolean) {
    for (byte b = 0; b < this.places.size(); b++) {
      if (((Place)this.places.get(b)).getId() == paramPlace.getId())
        this.places.remove(b); 
    } 
    if (paramBoolean)
      this.places.add(paramPlace); 
  }
  
  public boolean belongsToPlace(long paramLong) {
    return belongsTo((List)this.places, paramLong);
  }
  
  public DeviceType calculateGroupType(List<Device> paramList) {
    DeviceType deviceType;
    Iterator<Device> iterator = paramList.iterator();
    paramList = null;
    while (iterator.hasNext())
      deviceType = updateGroupType((DeviceType)paramList, iterator.next()); 
    return deviceType;
  }
  
  public int compareTo(@NonNull Group paramGroup) {
    if (this.favourite) {
      if (!paramGroup.favourite)
        return -1; 
      int j = getName().compareTo(paramGroup.getName());
      if (j != 0)
        return j; 
      j = getId() cmp paramGroup.getId();
      return (j < 0) ? -1 : ((j > 0) ? 1 : 0);
    } 
    if (paramGroup.favourite)
      return 1; 
    int i = getName().compareTo(paramGroup.getName());
    if (i != 0)
      return i; 
    i = getId() cmp paramGroup.getId();
    return (i < 0) ? -1 : ((i > 0) ? 1 : 0);
  }
  
  public boolean deviceBelongs(long paramLong) {
    return belongsTo((List)this.devices, paramLong);
  }
  
  public Device[] getDevices() {
    return this.devices.<Device>toArray(new Device[this.devices.size()]);
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
  
  public void setIcon(int paramInt) {
    this.icon = paramInt;
  }
  
  public void setName(String paramString) {
    this.name = paramString;
  }
  
  public DeviceType setType(DeviceType paramDeviceType) {
    DeviceType deviceType = this.type;
    this.type = paramDeviceType;
    return deviceType;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\objects\Group.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */