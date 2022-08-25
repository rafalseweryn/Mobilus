package pl.com.mobilelabs.mobilus.view.fragment.addinggroup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import pl.com.mobilelabs.mobilus.model.objects.Group;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.activity.AddingGroupActivity;
import pl.com.mobilelabs.mobilus.view.activity.UltimateHeaderActivity;
import pl.com.mobilelabs.mobilus.view.fragment.DoSthDeviceFragment;

public abstract class AddingGroupFragment extends DoSthDeviceFragment {
  protected BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        AddingGroupFragment.this.broadcastReceived(param1Context, param1Intent);
      }
    };
  
  protected IntentFilter intentFilter = new IntentFilter();
  
  protected ManagementService.ManagementServiceBinder managementServiceBinder;
  
  protected void broadcastReceived(Context paramContext, Intent paramIntent) {
    if (paramIntent.getAction().equalsIgnoreCase("ManagementService.Intents.newConfigurationReceived")) {
      newConfigurationReceived(paramContext, paramIntent);
    } else if (paramIntent.getAction().equalsIgnoreCase("ManagementService.Intents.deviceActionStarted")) {
      deviceActionStarted(paramContext, paramIntent);
    } else if (paramIntent.getAction().equalsIgnoreCase("ManagementService.Intents.deviceActionFinished")) {
      deviceActionFinished(paramContext, paramIntent);
    } else {
      somethingElseReceived(paramContext, paramIntent);
    } 
  }
  
  protected void configureIntentFilter() {
    this.intentFilter.addAction("ManagementService.Intents.newConfigurationReceived");
    this.intentFilter.addAction("ManagementService.Intents.deviceActionStarted");
    this.intentFilter.addAction("ManagementService.Intents.deviceActionFinished");
  }
  
  protected void deviceActionFinished(Context paramContext, Intent paramIntent) {}
  
  protected void deviceActionStarted(Context paramContext, Intent paramIntent) {}
  
  protected Group extractGroup() {
    AddingGroupActivity addingGroupActivity = (AddingGroupActivity)getActivity();
    return (addingGroupActivity != null) ? addingGroupActivity.getNewGroup() : null;
  }
  
  protected void newConfigurationReceived(Context paramContext, Intent paramIntent) {
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    if (extractGroup() == null && ultimateHeaderActivity != null)
      ultimateHeaderActivity.finish(); 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    if (view == null) {
      configureIntentFilter();
      LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.broadcastReceiver, this.intentFilter);
    } 
    return view;
  }
  
  protected void somethingElseReceived(Context paramContext, Intent paramIntent) {}
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addinggroup\AddingGroupFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */