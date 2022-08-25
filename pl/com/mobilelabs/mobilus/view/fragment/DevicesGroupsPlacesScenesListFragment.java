package pl.com.mobilelabs.mobilus.view.fragment;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import pl.com.mobilelabs.mobilus.model.State;
import pl.com.mobilelabs.mobilus.model.objects.BaseObject;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.activity.AddingDeviceActivity;
import pl.com.mobilelabs.mobilus.view.activity.AddingGroupActivity;
import pl.com.mobilelabs.mobilus.view.activity.AddingPlaceActivity;
import pl.com.mobilelabs.mobilus.view.activity.AddingSceneActivity;
import pl.com.mobilelabs.mobilus.view.activity.DevicesListActivity;
import pl.com.mobilelabs.mobilus.view.adapter.DevicesGroupsPlacesScenesListAdapter;
import pl.com.mobilelabs.mobilus.view.adapter.GridSpacingItemDecorator;
import pl.com.mobilelabs.mobilus.view.adapter.IChangeViewOrderListener;
import pl.com.mobilelabs.mobilus.view.adapter.IDevicesGroupsPlacesScenesListAdapterClickListener;

public class DevicesGroupsPlacesScenesListFragment extends Fragment implements IDevicesGroupsPlacesScenesListAdapterClickListener, IChangeViewOrderListener {
  private static final int DEVICES = 0;
  
  private static final int GROUPS = 1;
  
  private static final int PLACES = 2;
  
  private static final int SCENES = 3;
  
  protected boolean adapterInitialized;
  
  @BindView(2131296577)
  FloatingActionButton floatingActionButton;
  
  protected DevicesGroupsPlacesScenesListAdapter listAdapter;
  
  @BindView(2131296812)
  RecyclerView mRecyclerView;
  
  private ManagementService.ManagementServiceBinder managementBinder;
  
  private int presentedDataType;
  
  protected BroadcastReceiver receiver;
  
  private ItemTouchHelper touchHelper;
  
  private void fabVisibility(boolean paramBoolean) {
    if (this.managementBinder == null || !this.managementBinder.isUserAdmin())
      paramBoolean = false; 
    if (paramBoolean) {
      if (!isFABVisible())
        this.floatingActionButton.show(); 
    } else if (isFABVisible()) {
      this.floatingActionButton.hide();
    } 
  }
  
  private void initializeAdapter(ManagementService.ManagementServiceBinder paramManagementServiceBinder) {
    if (paramManagementServiceBinder != null && !this.adapterInitialized) {
      ArrayList arrayList;
      HashMap hashMap = null;
      if (this.presentedDataType == 1) {
        ArrayList arrayList1 = paramManagementServiceBinder.getGroups();
        hashMap = paramManagementServiceBinder.getGroupCurrentState();
        arrayList = arrayList1;
      } else if (this.presentedDataType == 2) {
        arrayList = arrayList.getPlaces();
      } else if (this.presentedDataType == 3) {
        arrayList = arrayList.getScenes();
      } else {
        ArrayList arrayList1 = arrayList.getDevices();
        hashMap = arrayList.getDeviceCurrentState();
        arrayList = arrayList1;
      } 
      this.listAdapter.setItems(arrayList);
      if (hashMap != null)
        this.listAdapter.setStates(hashMap); 
      this.adapterInitialized = true;
    } 
  }
  
  private boolean isFABVisible() {
    boolean bool;
    if (this.floatingActionButton.getVisibility() == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void clearDevicesOrder() {
    AlertDialog.Builder builder = new AlertDialog.Builder((Context)getActivity());
    builder.setTitle(2131624023);
    builder.setMessage(2131624022);
    builder.setPositiveButton(2131624227, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            if (DevicesGroupsPlacesScenesListFragment.this.managementBinder != null)
              DevicesGroupsPlacesScenesListFragment.this.managementBinder.clearDevicesOrder(); 
          }
        });
    builder.setNegativeButton(2131624135, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {}
        });
    final AlertDialog alertDialog = builder.create();
    alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
          public void onShow(DialogInterface param1DialogInterface) {
            alertDialog.getButton(-1).setTextColor(DevicesGroupsPlacesScenesListFragment.this.getResources().getColor(2131099704, null));
            alertDialog.getButton(-2).setTextColor(DevicesGroupsPlacesScenesListFragment.this.getResources().getColor(2131099697, null));
          }
        });
    alertDialog.show();
  }
  
  public void deviceGroupPlaceSceneSelected(BaseObject paramBaseObject) {
    startActivity(DevicesListActivity.createStartIntent(getContext(), paramBaseObject));
  }
  
  public boolean devicesOrderChanged(int paramInt1, int paramInt2, String paramString) {
    return (this.managementBinder != null) ? this.managementBinder.changeDevicesOrder(paramInt1, paramInt2, paramString) : false;
  }
  
  @OnClick({2131296577})
  protected void floatingActionButtonClicked() {
    switch (this.presentedDataType) {
      default:
        return;
      case 3:
        startActivity(new Intent((Context)getActivity(), AddingSceneActivity.class));
      case 2:
        startActivity(new Intent((Context)getActivity(), AddingPlaceActivity.class));
      case 1:
        startActivity(new Intent((Context)getActivity(), AddingGroupActivity.class));
      case 0:
        break;
    } 
    startActivity(new Intent((Context)getActivity(), AddingDeviceActivity.class));
  }
  
  protected void initializeBroadcastReceiver() {
    this.receiver = new BroadcastReceiver() {
        public void onReceive(Context param1Context, Intent param1Intent) {
          State state;
          HashMap hashMap;
          if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.newConfigurationReceived")) {
            if (DevicesGroupsPlacesScenesListFragment.this.presentedDataType == 1) {
              List list = (List)param1Intent.getSerializableExtra("ManagementService.Intents.newConfigurationReceivedGroups");
              hashMap = (HashMap)param1Intent.getSerializableExtra("ManagementService.Intents.newConfigurationReceivedGroupsCurrentState");
            } else {
              if (DevicesGroupsPlacesScenesListFragment.this.presentedDataType == 2) {
                List list = (List)hashMap.getSerializableExtra("ManagementService.Intents.newConfigurationReceivedPlaces");
              } else if (DevicesGroupsPlacesScenesListFragment.this.presentedDataType == 3) {
                List list = (List)hashMap.getSerializableExtra("ManagementService.Intents.newConfigurationReceivedScenes");
              } else {
                List list = (List)hashMap.getSerializableExtra("ManagementService.Intents.newConfigurationReceivedDevices");
                hashMap = (HashMap)hashMap.getSerializableExtra("ManagementService.Intents.newConfigurationReceivedDevicesCurrentState");
                DevicesGroupsPlacesScenesListFragment.this.listAdapter.setItems(list);
              } 
              hashMap = null;
            } 
          } else {
            if (hashMap.getAction().equalsIgnoreCase("ManagementService.Intents.deviceActionStarted")) {
              if (DevicesGroupsPlacesScenesListFragment.this.presentedDataType == 1) {
                HashMap hashMap1 = (HashMap)hashMap.getSerializableExtra("ManagementService.Intents.deviceActionStartedGroupsStates");
                if (hashMap1 != null) {
                  ArrayList arrayList = new ArrayList(hashMap1.values());
                  DevicesGroupsPlacesScenesListFragment.this.listAdapter.updateStates(arrayList);
                } 
              } else {
                state = (State)hashMap.getSerializableExtra("ManagementService.Intents.deviceActionStartedDeviceId");
                if (state != null)
                  DevicesGroupsPlacesScenesListFragment.this.listAdapter.updateState(state); 
              } 
            } else if (DevicesGroupsPlacesScenesListFragment.this.presentedDataType == 1) {
              HashMap hashMap1 = (HashMap)hashMap.getSerializableExtra("ManagementService.Intents.deviceActionFinishedResultGroups");
              if (hashMap1 != null) {
                ArrayList arrayList = new ArrayList(hashMap1.values());
                DevicesGroupsPlacesScenesListFragment.this.listAdapter.updateStates(arrayList);
              } 
            } else {
              state = (State)hashMap.getSerializableExtra("ManagementService.Intents.deviceActionFinishedResultDevice");
              DevicesGroupsPlacesScenesListFragment.this.listAdapter.updateState(state);
            } 
            return;
          } 
          DevicesGroupsPlacesScenesListFragment.this.listAdapter.setItems((Collection)state);
        }
      };
  }
  
  protected void initializeListAdapter() {
    this.listAdapter = new DevicesGroupsPlacesScenesListAdapter(this);
    if (this.presentedDataType == 0)
      this.listAdapter.setChangeListener(this); 
  }
  
  public void managementBinderBecameAvailable() {
    this.managementBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getActivity()).getManagementServiceBinder();
    initializeAdapter(this.managementBinder);
    fabVisibility(true);
  }
  
  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    initializeBroadcastReceiver();
    registerBroadcastReceivedIfNeeded();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view2 = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    View view1 = view2;
    if (view2 == null) {
      View view = paramLayoutInflater.inflate(2131492892, paramViewGroup, false);
      ButterKnife.bind(this, view);
      int i = getResources().getInteger(2131361806);
      initializeListAdapter();
      this.mRecyclerView.setLayoutManager((RecyclerView.LayoutManager)new GridLayoutManager((Context)getActivity(), i));
      this.mRecyclerView.addItemDecoration((RecyclerView.ItemDecoration)new GridSpacingItemDecorator(i, 10, true));
      this.mRecyclerView.setAdapter((RecyclerView.Adapter)this.listAdapter);
      this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView param1RecyclerView, int param1Int1, int param1Int2) {
              super.onScrolled(param1RecyclerView, param1Int1, param1Int2);
              if (param1Int2 > 0 && DevicesGroupsPlacesScenesListFragment.this.isFABVisible()) {
                DevicesGroupsPlacesScenesListFragment.this.fabVisibility(false);
              } else if (param1Int2 < 0 && !DevicesGroupsPlacesScenesListFragment.this.isFABVisible()) {
                DevicesGroupsPlacesScenesListFragment.this.fabVisibility(true);
              } 
            }
          });
      fabVisibility(false);
      if (this.touchHelper != null)
        this.touchHelper.attachToRecyclerView(this.mRecyclerView); 
      view1 = view;
      if (this.managementBinder == null) {
        this.managementBinder = ((IDevicesGroupsPlacesScenesListFragmentContext)getActivity()).getManagementServiceBinder();
        view1 = view;
        if (this.managementBinder != null) {
          initializeAdapter(this.managementBinder);
          fabVisibility(true);
          view1 = view;
        } 
      } 
    } 
    return view1;
  }
  
  public void onDestroy() {
    LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.receiver);
    super.onDestroy();
  }
  
  public void onDestroyView() {
    this.mRecyclerView.setAdapter(null);
    super.onDestroyView();
  }
  
  protected void registerBroadcastReceivedIfNeeded() {
    IntentFilter intentFilter = new IntentFilter("ManagementService.Intents.newConfigurationReceived");
    if (this.presentedDataType == 0 || this.presentedDataType == 1) {
      intentFilter.addAction("ManagementService.Intents.deviceActionStarted");
      intentFilter.addAction("ManagementService.Intents.deviceActionFinished");
    } 
    LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.receiver, intentFilter);
  }
  
  public void setPresentedDataType(int paramInt) {
    this.presentedDataType = paramInt;
    if (paramInt == 0) {
      this.touchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            public int getMovementFlags(RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder) {
              return makeMovementFlags(15, 0);
            }
            
            public boolean isItemViewSwipeEnabled() {
              return false;
            }
            
            public boolean isLongPressDragEnabled() {
              return false;
            }
            
            public boolean onMove(RecyclerView param1RecyclerView, RecyclerView.ViewHolder param1ViewHolder1, RecyclerView.ViewHolder param1ViewHolder2) {
              int i = param1ViewHolder1.getAdapterPosition();
              int j = param1ViewHolder2.getAdapterPosition();
              return DevicesGroupsPlacesScenesListFragment.this.listAdapter.moveView(i, j);
            }
            
            public void onSwiped(RecyclerView.ViewHolder param1ViewHolder, int param1Int) {}
          });
      if (this.mRecyclerView != null)
        this.touchHelper.attachToRecyclerView(this.mRecyclerView); 
    } 
  }
  
  public void startChange(RecyclerView.ViewHolder paramViewHolder) {
    if (this.touchHelper != null && paramViewHolder != null)
      this.touchHelper.startDrag(paramViewHolder); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\DevicesGroupsPlacesScenesListFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */