package pl.com.mobilelabs.mobilus.services.communication.demoservice;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pl.com.mobilelabs.mobilus.model.communication.MobilusModel;

public class Configuration {
  private Context context;
  
  private HashMap<Long, String> state = new HashMap<>();
  
  public Configuration() {
    this.state.put(Long.valueOf(1L), "80%");
    this.state.put(Long.valueOf(2L), "70%:40$");
    this.state.put(Long.valueOf(3L), "UP");
    this.state.put(Long.valueOf(4L), "50%");
    this.state.put(Long.valueOf(5L), "ON");
    this.state.put(Long.valueOf(6L), "OFF");
    this.state.put(Long.valueOf(7L), "OFF");
    this.state.put(Long.valueOf(8L), "ON");
    this.state.put(Long.valueOf(9L), "OFF");
    this.state.put(Long.valueOf(10L), "ON");
  }
  
  public ArrayList<MobilusModel.Event> getCurrentState() {
    ArrayList<MobilusModel.Event> arrayList = new ArrayList();
    for (Map.Entry<Long, String> entry : this.state.entrySet())
      arrayList.add(MobilusModel.Event.newBuilder().setDeviceId(((Long)entry.getKey()).longValue()).setEventNumber(8).setValue((String)entry.getValue()).build()); 
    return arrayList;
  }
  
  public ArrayList<MobilusModel.Device> getDevices() {
    ArrayList<MobilusModel.Device> arrayList = new ArrayList();
    arrayList.add(MobilusModel.Device.newBuilder().setId(1L).setName(this.context.getString(2131624197)).setType(1).setIcon(1).setFavourite(false).build());
    arrayList.add(MobilusModel.Device.newBuilder().setId(2L).setName(this.context.getString(2131624110)).setType(7).setIcon(35).setFavourite(false).build());
    arrayList.add(MobilusModel.Device.newBuilder().setId(3L).setName(this.context.getString(2131624000)).setType(3).setIcon(1).setFavourite(false).build());
    arrayList.add(MobilusModel.Device.newBuilder().setId(4L).setName(this.context.getString(2131624019)).setType(1).setIcon(2).setFavourite(false).build());
    arrayList.add(MobilusModel.Device.newBuilder().setId(5L).setName(this.context.getString(2131624096)).setType(4).setIcon(4).setFavourite(false).build());
    arrayList.add(MobilusModel.Device.newBuilder().setId(6L).setName(this.context.getString(2131624098)).setType(5).setIcon(23).setFavourite(false).build());
    arrayList.add(MobilusModel.Device.newBuilder().setId(7L).setName(this.context.getString(2131624012)).setType(5).setIcon(5).setFavourite(false).build());
    arrayList.add(MobilusModel.Device.newBuilder().setId(8L).setName(this.context.getString(2131624099)).setType(5).setIcon(19).setFavourite(false).build());
    arrayList.add(MobilusModel.Device.newBuilder().setId(9L).setName(this.context.getString(2131624068)).setType(5).setIcon(9).setFavourite(false).build());
    arrayList.add(MobilusModel.Device.newBuilder().setId(10L).setName(this.context.getString(2131624001)).setType(5).setIcon(17).setFavourite(false).build());
    return arrayList;
  }
  
  public ArrayList<MobilusModel.DeviceGroup> getGroups() {
    ArrayList<MobilusModel.DeviceGroup> arrayList = new ArrayList();
    ArrayList<Long> arrayList1 = new ArrayList();
    arrayList1.add(Long.valueOf(1L));
    arrayList1.add(Long.valueOf(4L));
    arrayList.add(MobilusModel.DeviceGroup.newBuilder().setId(1L).setName(this.context.getString(2131624178)).setIcon(1).addAllAssignedDevicesIds(arrayList1).build());
    arrayList1 = new ArrayList<>();
    arrayList1.add(Long.valueOf(2L));
    arrayList.add(MobilusModel.DeviceGroup.newBuilder().setId(2L).setName(this.context.getString(2131624093)).setIcon(10).addAllAssignedDevicesIds(arrayList1).build());
    arrayList1 = new ArrayList<>();
    arrayList1.add(Long.valueOf(6L));
    arrayList1.add(Long.valueOf(7L));
    arrayList.add(MobilusModel.DeviceGroup.newBuilder().setId(3L).setName(this.context.getString(2131624107)).setIcon(6).addAllAssignedDevicesIds(arrayList1).build());
    return arrayList;
  }
  
  public ArrayList<MobilusModel.DevicePlace> getPlaces() {
    ArrayList<MobilusModel.DevicePlace> arrayList = new ArrayList();
    ArrayList<Long> arrayList1 = new ArrayList();
    arrayList1.add(Long.valueOf(2L));
    arrayList1.add(Long.valueOf(5L));
    arrayList.add(MobilusModel.DevicePlace.newBuilder().setId(1L).setName(this.context.getString(2131624092)).setIcon(2).addAllAssignedDevicesIds(arrayList1).build());
    arrayList1 = new ArrayList<>();
    arrayList1.add(Long.valueOf(10L));
    arrayList1.add(Long.valueOf(1L));
    arrayList1.add(Long.valueOf(4L));
    arrayList.add(MobilusModel.DevicePlace.newBuilder().setId(2L).setName(this.context.getString(2131624177)).setIcon(2).addAllAssignedDevicesIds(arrayList1).build());
    arrayList1 = new ArrayList<>();
    arrayList1.add(Long.valueOf(6L));
    arrayList1.add(Long.valueOf(8L));
    arrayList.add(MobilusModel.DevicePlace.newBuilder().setId(3L).setName(this.context.getString(2131624097)).setIcon(2).addAllAssignedDevicesIds(arrayList1).build());
    arrayList1 = new ArrayList<>();
    arrayList1.add(Long.valueOf(7L));
    arrayList1.add(Long.valueOf(9L));
    arrayList.add(MobilusModel.DevicePlace.newBuilder().setId(4L).setName(this.context.getString(2131624011)).setIcon(2).addAllAssignedDevicesIds(arrayList1).build());
    return arrayList;
  }
  
  public ArrayList<MobilusModel.Scene> getScenes() {
    ArrayList<MobilusModel.Scene> arrayList = new ArrayList();
    ArrayList<MobilusModel.SceneEvent> arrayList1 = new ArrayList();
    arrayList1.add(MobilusModel.SceneEvent.newBuilder().setId(1L).setEventNumber(6).setDeviceId(1L).setValue("100%").build());
    arrayList1.add(MobilusModel.SceneEvent.newBuilder().setId(2L).setEventNumber(6).setDeviceId(3L).setValue("100%").build());
    arrayList1.add(MobilusModel.SceneEvent.newBuilder().setId(3L).setEventNumber(6).setDeviceId(4L).setValue("100%").build());
    arrayList.add(MobilusModel.Scene.newBuilder().setId(1L).setName(this.context.getString(2131624003)).setIcon(2).setEnabled(false).addAllSceneEvents(arrayList1).build());
    arrayList1 = new ArrayList<>();
    arrayList1.add(MobilusModel.SceneEvent.newBuilder().setId(4L).setEventNumber(6).setDeviceId(1L).setValue("0%").build());
    arrayList1.add(MobilusModel.SceneEvent.newBuilder().setId(5L).setEventNumber(6).setDeviceId(3L).setValue("0%").build());
    arrayList1.add(MobilusModel.SceneEvent.newBuilder().setId(6L).setEventNumber(6).setDeviceId(4L).setValue("0%").build());
    arrayList.add(MobilusModel.Scene.newBuilder().setId(2L).setName(this.context.getString(2131624002)).setIcon(2).setEnabled(false).addAllSceneEvents(arrayList1).build());
    arrayList1 = new ArrayList<>();
    arrayList1.add(MobilusModel.SceneEvent.newBuilder().setId(7L).setEventNumber(6).setDeviceId(6L).setValue("ON").build());
    arrayList.add(MobilusModel.Scene.newBuilder().setId(3L).setName(this.context.getString(2131624109)).setIcon(2).setEnabled(false).addAllSceneEvents(arrayList1).build());
    arrayList1 = new ArrayList<>();
    arrayList1.add(MobilusModel.SceneEvent.newBuilder().setId(8L).setEventNumber(6).setDeviceId(6L).setValue("OFF").build());
    arrayList.add(MobilusModel.Scene.newBuilder().setId(4L).setName(this.context.getString(2131624108)).setIcon(2).setEnabled(false).addAllSceneEvents(arrayList1).build());
    arrayList1 = new ArrayList<>();
    arrayList1.add(MobilusModel.SceneEvent.newBuilder().setId(9L).setEventNumber(6).setDeviceId(8L).setValue("ON").build());
    arrayList.add(MobilusModel.Scene.newBuilder().setId(5L).setName(this.context.getString(2131624187)).setIcon(2).setEnabled(false).addAllSceneEvents(arrayList1).build());
    arrayList1 = new ArrayList<>();
    arrayList1.add(MobilusModel.SceneEvent.newBuilder().setId(10L).setEventNumber(6).setDeviceId(8L).setValue("OFF").build());
    arrayList.add(MobilusModel.Scene.newBuilder().setId(6L).setName(this.context.getString(2131624186)).setIcon(2).setEnabled(false).addAllSceneEvents(arrayList1).build());
    return arrayList;
  }
  
  public void setContext(Context paramContext) {
    this.context = paramContext;
  }
  
  public String setState(long paramLong, String paramString) {
    String str1;
    String str2;
    if (paramString.equalsIgnoreCase("DOWN")) {
      str2 = "0%";
    } else {
      str2 = paramString;
      if (paramString.equalsIgnoreCase("UP"))
        str2 = "100%"; 
    } 
    String str3 = this.state.get(Long.valueOf(paramLong));
    if (paramLong == 5L) {
      if (str3.equalsIgnoreCase("ON")) {
        paramString = "OFF";
      } else {
        paramString = "ON";
      } 
    } else {
      paramString = str2;
      if (str3.contains(":")) {
        String[] arrayOfString = str3.split(":");
        if (str2.contains("$")) {
          arrayOfString[1] = str2;
        } else {
          arrayOfString[0] = str2;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(arrayOfString[0]);
        stringBuilder.append(":");
        stringBuilder.append(arrayOfString[1]);
        str1 = stringBuilder.toString();
      } 
    } 
    this.state.put(Long.valueOf(paramLong), str1);
    return str1;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\services\communication\demoservice\Configuration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */