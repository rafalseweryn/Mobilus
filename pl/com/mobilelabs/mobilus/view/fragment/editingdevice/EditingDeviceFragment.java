package pl.com.mobilelabs.mobilus.view.fragment.editingdevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import pl.com.mobilelabs.mobilus.model.objects.Device;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.activity.UltimateHeaderActivity;
import pl.com.mobilelabs.mobilus.view.fragment.DoSthDeviceFragment;

public abstract class EditingDeviceFragment extends DoSthDeviceFragment {
  protected BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        EditingDeviceFragment.this.broadcastReceived(param1Context, param1Intent);
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
  
  protected Device extractDevice() {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
    //   4: checkcast pl/com/mobilelabs/mobilus/view/activity/UltimateHeaderActivity
    //   7: astore_1
    //   8: aload_1
    //   9: ifnull -> 105
    //   12: aload_0
    //   13: aload_1
    //   14: invokevirtual getManagementServiceBinder : ()Lpl/com/mobilelabs/mobilus/services/managementservice/ManagementService$ManagementServiceBinder;
    //   17: putfield managementServiceBinder : Lpl/com/mobilelabs/mobilus/services/managementservice/ManagementService$ManagementServiceBinder;
    //   20: aload_0
    //   21: getfield managementServiceBinder : Lpl/com/mobilelabs/mobilus/services/managementservice/ManagementService$ManagementServiceBinder;
    //   24: ifnull -> 103
    //   27: aload_1
    //   28: instanceof pl/com/mobilelabs/mobilus/view/activity/DoSthWithDeviceInterface
    //   31: ifeq -> 101
    //   34: aload_1
    //   35: checkcast pl/com/mobilelabs/mobilus/view/activity/DoSthWithDeviceInterface
    //   38: invokeinterface getNewDeviceID : ()J
    //   43: lstore_2
    //   44: lload_2
    //   45: lconst_0
    //   46: lcmp
    //   47: iflt -> 99
    //   50: aload_0
    //   51: getfield managementServiceBinder : Lpl/com/mobilelabs/mobilus/services/managementservice/ManagementService$ManagementServiceBinder;
    //   54: invokevirtual getDevices : ()Ljava/util/ArrayList;
    //   57: invokevirtual iterator : ()Ljava/util/Iterator;
    //   60: astore #4
    //   62: aload #4
    //   64: invokeinterface hasNext : ()Z
    //   69: ifeq -> 95
    //   72: aload #4
    //   74: invokeinterface next : ()Ljava/lang/Object;
    //   79: checkcast pl/com/mobilelabs/mobilus/model/objects/Device
    //   82: astore_1
    //   83: aload_1
    //   84: invokevirtual getId : ()J
    //   87: lload_2
    //   88: lcmp
    //   89: ifne -> 62
    //   92: goto -> 97
    //   95: aconst_null
    //   96: astore_1
    //   97: aload_1
    //   98: areturn
    //   99: aconst_null
    //   100: areturn
    //   101: aconst_null
    //   102: areturn
    //   103: aconst_null
    //   104: areturn
    //   105: aconst_null
    //   106: areturn
  }
  
  protected void newConfigurationReceived(Context paramContext, Intent paramIntent) {
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    if (extractDevice() == null && ultimateHeaderActivity != null)
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


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editingdevice\EditingDeviceFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */