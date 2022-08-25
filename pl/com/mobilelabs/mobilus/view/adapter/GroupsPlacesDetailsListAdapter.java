package pl.com.mobilelabs.mobilus.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.com.mobilelabs.mobilus.model.State;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.view.view.DeviceView;

public class GroupsPlacesDetailsListAdapter extends DevicesGroupsPlacesScenesListAdapter {
  protected Map<Long, State> groupsStates = new HashMap<>();
  
  public GroupsPlacesDetailsListAdapter(IDevicesGroupsPlacesScenesListAdapterClickListener paramIDevicesGroupsPlacesScenesListAdapterClickListener) {
    super(paramIDevicesGroupsPlacesScenesListAdapterClickListener);
  }
  
  public void onBindViewHolder(DeviceGroupPlaceSceneViewHolder paramDeviceGroupPlaceSceneViewHolder, int paramInt) {
    State state;
    View view = paramDeviceGroupPlaceSceneViewHolder.getView();
    BaseObject baseObject = this.items.get(paramInt);
    if (baseObject instanceof pl.com.mobilelabs.mobilus.model.objects.Group) {
      state = this.groupsStates.get(Long.valueOf(baseObject.getId()));
    } else {
      state = this.states.get(Long.valueOf(baseObject.getId()));
    } 
    DeviceView deviceView = (DeviceView)view;
    deviceView.bind(baseObject);
    deviceView.setState(state);
  }
  
  public void setStates(Map<Long, State> paramMap, boolean paramBoolean) {
    if (paramBoolean) {
      this.groupsStates.clear();
      if (paramMap != null)
        this.groupsStates.putAll(paramMap); 
    } else {
      this.states.clear();
      if (paramMap != null)
        this.states.putAll(paramMap); 
    } 
    notifyDataSetChanged();
  }
  
  public void updateState(State paramState, boolean paramBoolean) {
    byte b1 = 0;
    byte b2 = 0;
    if (paramBoolean) {
      for (b1 = b2; b1 < this.items.size(); b1++) {
        if (this.items.get(b1) instanceof pl.com.mobilelabs.mobilus.model.objects.Group && ((BaseObject)this.items.get(b1)).getId() == paramState.getId()) {
          this.groupsStates.put(Long.valueOf(paramState.getId()), paramState);
          notifyItemChanged(b1);
          return;
        } 
      } 
    } else {
      while (b1 < this.items.size()) {
        if (this.items.get(b1) instanceof pl.com.mobilelabs.mobilus.model.objects.Device && ((BaseObject)this.items.get(b1)).getId() == paramState.getId()) {
          this.states.put(Long.valueOf(paramState.getId()), paramState);
          notifyItemChanged(b1);
          return;
        } 
        b1++;
      } 
    } 
  }
  
  public void updateStates(List<State> paramList, boolean paramBoolean) {
    if (paramBoolean) {
      for (State state : paramList) {
        for (byte b = 0; b < this.items.size(); b++) {
          if (this.items.get(b) instanceof pl.com.mobilelabs.mobilus.model.objects.Group && ((BaseObject)this.items.get(b)).getId() == state.getId()) {
            this.groupsStates.put(Long.valueOf(state.getId()), state);
            notifyItemChanged(b);
            break;
          } 
        } 
      } 
    } else {
      for (State state : paramList) {
        for (byte b = 0; b < this.items.size(); b++) {
          if (this.items.get(b) instanceof pl.com.mobilelabs.mobilus.model.objects.Device && ((BaseObject)this.items.get(b)).getId() == state.getId()) {
            this.states.put(Long.valueOf(state.getId()), state);
            notifyItemChanged(b);
            break;
          } 
        } 
      } 
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\adapter\GroupsPlacesDetailsListAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */