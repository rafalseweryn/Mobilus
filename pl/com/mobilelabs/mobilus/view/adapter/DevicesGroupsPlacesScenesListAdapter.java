package pl.com.mobilelabs.mobilus.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import pl.com.mobilelabs.mobilus.model.State;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.model.objects.Device;
import pl.com.mobilelabs.mobilus.model.objects.DeviceType;
import pl.com.mobilelabs.mobilus.model.objects.Group;
import pl.com.mobilelabs.mobilus.model.objects.Place;
import pl.com.mobilelabs.mobilus.model.objects.Scene;
import pl.com.mobilelabs.mobilus.view.view.CgrRollerGroupView;
import pl.com.mobilelabs.mobilus.view.view.CgrRollerView;
import pl.com.mobilelabs.mobilus.view.view.CosmoCmrRollerGroupView;
import pl.com.mobilelabs.mobilus.view.view.CosmoCmrRollerView;
import pl.com.mobilelabs.mobilus.view.view.CzrMzrRollerGroupView;
import pl.com.mobilelabs.mobilus.view.view.CzrMzrRollerView;
import pl.com.mobilelabs.mobilus.view.view.DeviceView;
import pl.com.mobilelabs.mobilus.view.view.IGetPresentedObjectIdInterface;
import pl.com.mobilelabs.mobilus.view.view.PlaceView;
import pl.com.mobilelabs.mobilus.view.view.SceneView;
import pl.com.mobilelabs.mobilus.view.view.SensoRollerGroupView;
import pl.com.mobilelabs.mobilus.view.view.SensoRollerView;
import pl.com.mobilelabs.mobilus.view.view.SensoZRollerGroupView;
import pl.com.mobilelabs.mobilus.view.view.SensoZRollerView;
import pl.com.mobilelabs.mobilus.view.view.switches.SwitchGroupView;
import pl.com.mobilelabs.mobilus.view.view.switches.SwitchView;

public class DevicesGroupsPlacesScenesListAdapter extends RecyclerView.Adapter<DeviceGroupPlaceSceneViewHolder> implements View.OnClickListener, View.OnLongClickListener {
  private static final int ITEM_TYPE_BULB = 5;
  
  private static final int ITEM_TYPE_GROUP_BULB = 11;
  
  private static final int ITEM_TYPE_PLACE = 12;
  
  private static final int ITEM_TYPE_ROLLER_CGR = 2;
  
  private static final int ITEM_TYPE_ROLLER_COSMO_CMR = 1;
  
  private static final int ITEM_TYPE_ROLLER_CZR_MZR = 3;
  
  private static final int ITEM_TYPE_ROLLER_GROUP_CGR = 8;
  
  private static final int ITEM_TYPE_ROLLER_GROUP_COSMO_CMR = 7;
  
  private static final int ITEM_TYPE_ROLLER_GROUP_CZR_MZR = 9;
  
  private static final int ITEM_TYPE_ROLLER_GROUP_SENSO = 6;
  
  private static final int ITEM_TYPE_ROLLER_GROUP_SENSO_Z = 10;
  
  private static final int ITEM_TYPE_ROLLER_SENSO = 0;
  
  private static final int ITEM_TYPE_ROLLER_SENSO_Z = 4;
  
  private static final int ITEM_TYPE_SCENE = 13;
  
  private IChangeViewOrderListener changeListener;
  
  protected List<BaseObject> items = new ArrayList<>();
  
  protected IDevicesGroupsPlacesScenesListAdapterClickListener listener;
  
  protected Map<Long, State> states = new HashMap<>();
  
  public DevicesGroupsPlacesScenesListAdapter(IDevicesGroupsPlacesScenesListAdapterClickListener paramIDevicesGroupsPlacesScenesListAdapterClickListener) {
    this.listener = paramIDevicesGroupsPlacesScenesListAdapterClickListener;
  }
  
  private boolean isTypeDevice(int paramInt) {
    return (paramInt >= 0 && paramInt < 6);
  }
  
  private String returnIdsOrder() {
    StringBuilder stringBuilder = new StringBuilder();
    Iterator<BaseObject> iterator = this.items.iterator();
    int i = 0;
    while (iterator.hasNext()) {
      stringBuilder.append(((BaseObject)iterator.next()).getId());
      int j = i + 1;
      i = j;
      if (j < this.items.size()) {
        stringBuilder.append(":");
        i = j;
      } 
    } 
    return stringBuilder.toString();
  }
  
  public int getItemCount() {
    return this.items.size();
  }
  
  public int getItemViewType(int paramInt) {
    if (this.items.get(paramInt) instanceof Group) {
      Group group = (Group)this.items.get(paramInt);
      switch (group.getType()) {
        default:
          return 11;
        case SENSO_Z:
          return 10;
        case CZR:
        case MZR:
          return 9;
        case CGR:
          return 8;
        case COSMO:
        case CMR:
          return 7;
        case SENSO:
          break;
      } 
      return 6;
    } 
    if (this.items.get(paramInt) instanceof Place)
      return 12; 
    if (this.items.get(paramInt) instanceof Scene)
      return 13; 
    Device device = (Device)this.items.get(paramInt);
    switch (device.getType()) {
      default:
        return 5;
      case SENSO_Z:
        return 4;
      case CZR:
      case MZR:
        return 3;
      case CGR:
        return 2;
      case COSMO:
      case CMR:
        return 1;
      case SENSO:
        break;
    } 
    return 0;
  }
  
  public boolean moveView(int paramInt1, int paramInt2) {
    if (paramInt1 < paramInt2) {
      for (int i = paramInt1; i < paramInt2; i = j) {
        List<BaseObject> list = this.items;
        int j = i + 1;
        Collections.swap(list, i, j);
      } 
    } else {
      for (int i = paramInt1; i > paramInt2; i--)
        Collections.swap(this.items, i, i - 1); 
    } 
    if (this.changeListener.devicesOrderChanged(paramInt1, paramInt2, returnIdsOrder()) == true) {
      notifyItemMoved(paramInt1, paramInt2);
      return true;
    } 
    return false;
  }
  
  public void onBindViewHolder(DeviceGroupPlaceSceneViewHolder paramDeviceGroupPlaceSceneViewHolder, int paramInt) {
    View view = paramDeviceGroupPlaceSceneViewHolder.getView();
    BaseObject baseObject = this.items.get(paramInt);
    State state = this.states.get(Long.valueOf(baseObject.getId()));
    view.setTag(paramDeviceGroupPlaceSceneViewHolder);
    if (baseObject instanceof Place) {
      ((PlaceView)view).bind((Place)baseObject);
    } else if (baseObject instanceof Scene) {
      ((SceneView)view).bind((Scene)baseObject);
    } else {
      DeviceView deviceView = (DeviceView)view;
      deviceView.bind(baseObject);
      deviceView.setState(state);
    } 
  }
  
  public void onClick(View paramView) {
    IGetPresentedObjectIdInterface iGetPresentedObjectIdInterface = (IGetPresentedObjectIdInterface)paramView;
    if (iGetPresentedObjectIdInterface.getPresentedObject() instanceof Group || iGetPresentedObjectIdInterface.getPresentedObject() instanceof Place) {
      this.listener.deviceGroupPlaceSceneSelected(iGetPresentedObjectIdInterface.getPresentedObject());
      return;
    } 
    if (iGetPresentedObjectIdInterface.getPresentedObject() instanceof Device && this.changeListener != null) {
      this.changeListener.clearDevicesOrder();
      notifyDataSetChanged();
    } 
  }
  
  public DeviceGroupPlaceSceneViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
    SensoRollerView sensoRollerView;
    boolean bool1 = false;
    boolean bool2 = true;
    if (paramInt == 0) {
      sensoRollerView = SensoRollerView.build(paramViewGroup.getContext());
    } else {
      CosmoCmrRollerView cosmoCmrRollerView;
      if (1 == paramInt) {
        cosmoCmrRollerView = CosmoCmrRollerView.build(sensoRollerView.getContext());
      } else {
        CgrRollerView cgrRollerView;
        if (2 == paramInt) {
          cgrRollerView = CgrRollerView.build(cosmoCmrRollerView.getContext());
        } else {
          CzrMzrRollerView czrMzrRollerView;
          if (3 == paramInt) {
            czrMzrRollerView = CzrMzrRollerView.build(cgrRollerView.getContext());
          } else {
            SensoRollerGroupView sensoRollerGroupView;
            SwitchView switchView;
            if (6 == paramInt) {
              sensoRollerGroupView = SensoRollerGroupView.build(czrMzrRollerView.getContext());
            } else {
              CosmoCmrRollerGroupView cosmoCmrRollerGroupView;
              if (7 == paramInt) {
                cosmoCmrRollerGroupView = CosmoCmrRollerGroupView.build(sensoRollerGroupView.getContext());
              } else {
                CgrRollerGroupView cgrRollerGroupView;
                if (8 == paramInt) {
                  cgrRollerGroupView = CgrRollerGroupView.build(cosmoCmrRollerGroupView.getContext());
                } else {
                  CzrMzrRollerGroupView czrMzrRollerGroupView;
                  if (9 == paramInt) {
                    czrMzrRollerGroupView = CzrMzrRollerGroupView.build(cgrRollerGroupView.getContext());
                  } else {
                    SensoZRollerGroupView sensoZRollerGroupView;
                    if (10 == paramInt) {
                      sensoZRollerGroupView = SensoZRollerGroupView.build(czrMzrRollerGroupView.getContext());
                    } else {
                      SwitchGroupView switchGroupView;
                      if (11 == paramInt) {
                        switchGroupView = SwitchGroupView.build(sensoZRollerGroupView.getContext());
                      } else {
                        SensoZRollerView sensoZRollerView;
                        if (4 == paramInt) {
                          sensoZRollerView = SensoZRollerView.build(switchGroupView.getContext());
                        } else {
                          PlaceView placeView;
                          if (12 == paramInt) {
                            placeView = PlaceView.build(sensoZRollerView.getContext());
                          } else {
                            SceneView sceneView;
                            if (13 == paramInt) {
                              sceneView = SceneView.build(placeView.getContext());
                              bool2 = false;
                            } else {
                              switchView = SwitchView.build(sceneView.getContext());
                              bool1 = true;
                            } 
                          } 
                          if (bool2)
                            switchView.setOnClickListener(this); 
                        } 
                        bool1 = true;
                      } 
                    } 
                  } 
                } 
              } 
            } 
            if (bool2)
              switchView.setOnClickListener(this); 
          } 
        } 
      } 
    } 
    bool1 = true;
  }
  
  public boolean onLongClick(View paramView) {
    if (this.changeListener != null) {
      this.changeListener.startChange((DeviceGroupPlaceSceneViewHolder)paramView.getTag());
      return true;
    } 
    return false;
  }
  
  public void setChangeListener(IChangeViewOrderListener paramIChangeViewOrderListener) {
    this.changeListener = paramIChangeViewOrderListener;
  }
  
  public void setItems(Collection<? extends BaseObject> paramCollection) {
    this.items.clear();
    if (paramCollection != null)
      this.items.addAll(paramCollection); 
    notifyDataSetChanged();
  }
  
  public void setStates(Map<Long, State> paramMap) {
    this.states.clear();
    if (paramMap != null)
      this.states.putAll(paramMap); 
    notifyDataSetChanged();
  }
  
  public void updateState(State paramState) {
    for (byte b = 0; b < this.items.size(); b++) {
      if (((BaseObject)this.items.get(b)).getId() == paramState.getId()) {
        this.states.put(Long.valueOf(paramState.getId()), paramState);
        notifyItemChanged(b);
        return;
      } 
    } 
  }
  
  public void updateStates(List<State> paramList) {
    for (State state : paramList) {
      for (byte b = 0; b < this.items.size(); b++) {
        if (((BaseObject)this.items.get(b)).getId() == state.getId()) {
          this.states.put(Long.valueOf(state.getId()), state);
          notifyItemChanged(b);
          break;
        } 
      } 
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\adapter\DevicesGroupsPlacesScenesListAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */