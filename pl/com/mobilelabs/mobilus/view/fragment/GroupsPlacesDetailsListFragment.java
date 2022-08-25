package pl.com.mobilelabs.mobilus.view.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import pl.com.mobilelabs.mobilus.model.State;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.model.objects.Device;
import pl.com.mobilelabs.mobilus.model.objects.Group;
import pl.com.mobilelabs.mobilus.model.objects.Place;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.adapter.DevicesGroupsPlacesScenesListAdapter;
import pl.com.mobilelabs.mobilus.view.adapter.GroupsPlacesDetailsListAdapter;

public class GroupsPlacesDetailsListFragment extends DevicesGroupsPlacesScenesListFragment {
  private BaseObject presentedObject;
  
  protected void initializeBroadcastReceiver() {
    this.receiver = new BroadcastReceiver() {
        public void onReceive(Context param1Context, Intent param1Intent) {
          State state;
          if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.newConfigurationReceived")) {
            String str;
            if (GroupsPlacesDetailsListFragment.this.presentedObject instanceof Group) {
              str = "ManagementService.Intents.newConfigurationReceivedGroups";
            } else {
              str = "ManagementService.Intents.newConfigurationReceivedPlaces";
            } 
            List list = (List)param1Intent.getSerializableExtra(str);
            if (list == null) {
              ((Activity)GroupsPlacesDetailsListFragment.this.getContext()).finish();
            } else {
              Iterator<BaseObject> iterator = list.iterator();
              while (iterator.hasNext()) {
                BaseObject baseObject = iterator.next();
                if (baseObject.getId() == GroupsPlacesDetailsListFragment.this.presentedObject.getId()) {
                  GroupsPlacesDetailsListFragment.access$002(GroupsPlacesDetailsListFragment.this, baseObject);
                  HashMap hashMap = (HashMap)param1Intent.getSerializableExtra("ManagementService.Intents.newConfigurationReceivedDevicesCurrentState");
                  boolean bool = GroupsPlacesDetailsListFragment.this.presentedObject instanceof Group;
                  int i = 0;
                  int j = 0;
                  if (bool) {
                    Group group = (Group)GroupsPlacesDetailsListFragment.this.presentedObject;
                    GroupsPlacesDetailsListFragment.this.listAdapter.setItems(Arrays.asList(group.getDevices()));
                    Device[] arrayOfDevice1 = group.getDevices();
                    i = arrayOfDevice1.length;
                    while (j < i) {
                      state = (State)hashMap.get(Long.valueOf(arrayOfDevice1[j].getId()));
                      if (state != null)
                        GroupsPlacesDetailsListFragment.this.listAdapter.updateState(state); 
                      j++;
                    } 
                    break;
                  } 
                  ArrayList arrayList = new ArrayList();
                  arrayList.addAll(Arrays.asList(((Place)GroupsPlacesDetailsListFragment.this.presentedObject).getGroups()));
                  arrayList.addAll(Arrays.asList(((Place)GroupsPlacesDetailsListFragment.this.presentedObject).getDevices()));
                  GroupsPlacesDetailsListFragment.this.listAdapter.setItems(arrayList);
                  Device[] arrayOfDevice = ((Place)GroupsPlacesDetailsListFragment.this.presentedObject).getDevices();
                  int k = arrayOfDevice.length;
                  for (j = 0; j < k; j++) {
                    State state1 = (State)hashMap.get(Long.valueOf(arrayOfDevice[j].getId()));
                    if (state1 != null)
                      GroupsPlacesDetailsListFragment.this.listAdapter.updateState(state1); 
                  } 
                  hashMap = (HashMap)state.getSerializableExtra("ManagementService.Intents.newConfigurationReceivedGroupsCurrentState");
                  Group[] arrayOfGroup = ((Place)GroupsPlacesDetailsListFragment.this.presentedObject).getGroups();
                  k = arrayOfGroup.length;
                  for (j = i; j < k; j++) {
                    state = (State)hashMap.get(Long.valueOf(arrayOfGroup[j].getId()));
                    if (state != null)
                      ((GroupsPlacesDetailsListAdapter)GroupsPlacesDetailsListFragment.this.listAdapter).updateState(state, true); 
                  } 
                  break;
                } 
              } 
            } 
          } else {
            String str1;
            String str2;
            if (state.getAction().equalsIgnoreCase("ManagementService.Intents.deviceActionStarted")) {
              str2 = "ManagementService.Intents.deviceActionStartedGroupsStates";
              str1 = "ManagementService.Intents.deviceActionStartedDeviceId";
            } else {
              str2 = "ManagementService.Intents.deviceActionFinishedResultGroups";
              str1 = "ManagementService.Intents.deviceActionFinishedResultDevice";
            } 
            if (GroupsPlacesDetailsListFragment.this.presentedObject instanceof Place) {
              HashMap hashMap = (HashMap)state.getSerializableExtra(str2);
              if (hashMap != null) {
                Place place = (Place)GroupsPlacesDetailsListFragment.this.presentedObject;
                for (Map.Entry entry : hashMap.entrySet()) {
                  if (place.groupBelongs(((Long)entry.getKey()).longValue()))
                    ((GroupsPlacesDetailsListAdapter)GroupsPlacesDetailsListFragment.this.listAdapter).updateState((State)entry.getValue(), true); 
                } 
              } 
            } 
            State state1 = (State)state.getSerializableExtra(str1);
            if (state1 != null)
              if (GroupsPlacesDetailsListFragment.this.presentedObject instanceof Place && ((Place)GroupsPlacesDetailsListFragment.this.presentedObject).deviceBelongs(state1.getId())) {
                GroupsPlacesDetailsListFragment.this.listAdapter.updateState(state1);
              } else if (GroupsPlacesDetailsListFragment.this.presentedObject instanceof Group && ((Group)GroupsPlacesDetailsListFragment.this.presentedObject).deviceBelongs(state1.getId())) {
                GroupsPlacesDetailsListFragment.this.listAdapter.updateState(state1);
              }  
          } 
        }
      };
  }
  
  protected void initializeListAdapter() {
    this.listAdapter = (DevicesGroupsPlacesScenesListAdapter)new GroupsPlacesDetailsListAdapter(this);
  }
  
  public void managementBinderBecameAvailable() {
    State state;
    ManagementService.ManagementServiceBinder managementServiceBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getActivity()).getManagementServiceBinder();
    HashMap hashMap = managementServiceBinder.getDeviceCurrentState();
    boolean bool = this.presentedObject instanceof Group;
    int i = 0;
    int j = 0;
    if (bool) {
      Group group = (Group)this.presentedObject;
      this.listAdapter.setItems(Arrays.asList(group.getDevices()));
      Device[] arrayOfDevice = group.getDevices();
      i = arrayOfDevice.length;
      while (j < i) {
        state = (State)hashMap.get(Long.valueOf(arrayOfDevice[j].getId()));
        if (state != null)
          this.listAdapter.updateState(state); 
        j++;
      } 
    } else {
      ArrayList arrayList = new ArrayList();
      arrayList.addAll(Arrays.asList(((Place)this.presentedObject).getGroups()));
      arrayList.addAll(Arrays.asList(((Place)this.presentedObject).getDevices()));
      this.listAdapter.setItems(arrayList);
      Device[] arrayOfDevice = ((Place)this.presentedObject).getDevices();
      int k = arrayOfDevice.length;
      for (j = 0; j < k; j++) {
        State state1 = (State)hashMap.get(Long.valueOf(arrayOfDevice[j].getId()));
        if (state1 != null)
          this.listAdapter.updateState(state1); 
      } 
      hashMap = state.getGroupCurrentState();
      Group[] arrayOfGroup = ((Place)this.presentedObject).getGroups();
      k = arrayOfGroup.length;
      for (j = i; j < k; j++) {
        state = (State)hashMap.get(Long.valueOf(arrayOfGroup[j].getId()));
        if (state != null)
          ((GroupsPlacesDetailsListAdapter)this.listAdapter).updateState(state, true); 
      } 
    } 
  }
  
  public void setPresentedDataType(int paramInt) {}
  
  public void setPresentedObject(BaseObject paramBaseObject) {
    this.presentedObject = paramBaseObject;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\GroupsPlacesDetailsListFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */