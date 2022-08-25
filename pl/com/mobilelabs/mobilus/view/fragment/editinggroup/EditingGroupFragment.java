package pl.com.mobilelabs.mobilus.view.fragment.editinggroup;

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
import pl.com.mobilelabs.mobilus.view.activity.UltimateHeaderActivity;
import pl.com.mobilelabs.mobilus.view.fragment.DoSthDeviceFragment;

public abstract class EditingGroupFragment extends DoSthDeviceFragment {
  protected BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        EditingGroupFragment.this.broadcastReceived(param1Context, param1Intent);
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
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
    //   4: checkcast pl/com/mobilelabs/mobilus/view/activity/UltimateHeaderActivity
    //   7: astore_1
    //   8: aload_1
    //   9: ifnull -> 117
    //   12: aload_0
    //   13: aload_1
    //   14: invokevirtual getManagementServiceBinder : ()Lpl/com/mobilelabs/mobilus/services/managementservice/ManagementService$ManagementServiceBinder;
    //   17: putfield managementServiceBinder : Lpl/com/mobilelabs/mobilus/services/managementservice/ManagementService$ManagementServiceBinder;
    //   20: aload_0
    //   21: getfield managementServiceBinder : Lpl/com/mobilelabs/mobilus/services/managementservice/ManagementService$ManagementServiceBinder;
    //   24: ifnull -> 115
    //   27: aload_1
    //   28: instanceof pl/com/mobilelabs/mobilus/view/activity/DoSthWithSthInterface
    //   31: ifeq -> 113
    //   34: aload_1
    //   35: checkcast pl/com/mobilelabs/mobilus/view/activity/DoSthWithSthInterface
    //   38: astore_1
    //   39: aload_1
    //   40: invokeinterface getType : ()I
    //   45: iconst_1
    //   46: if_icmpne -> 113
    //   49: aload_1
    //   50: invokeinterface getNewSomethingID : ()J
    //   55: lstore_2
    //   56: lload_2
    //   57: lconst_0
    //   58: lcmp
    //   59: iflt -> 111
    //   62: aload_0
    //   63: getfield managementServiceBinder : Lpl/com/mobilelabs/mobilus/services/managementservice/ManagementService$ManagementServiceBinder;
    //   66: invokevirtual getGroups : ()Ljava/util/ArrayList;
    //   69: invokevirtual iterator : ()Ljava/util/Iterator;
    //   72: astore #4
    //   74: aload #4
    //   76: invokeinterface hasNext : ()Z
    //   81: ifeq -> 107
    //   84: aload #4
    //   86: invokeinterface next : ()Ljava/lang/Object;
    //   91: checkcast pl/com/mobilelabs/mobilus/model/objects/Group
    //   94: astore_1
    //   95: aload_1
    //   96: invokevirtual getId : ()J
    //   99: lload_2
    //   100: lcmp
    //   101: ifne -> 74
    //   104: goto -> 109
    //   107: aconst_null
    //   108: astore_1
    //   109: aload_1
    //   110: areturn
    //   111: aconst_null
    //   112: areturn
    //   113: aconst_null
    //   114: areturn
    //   115: aconst_null
    //   116: areturn
    //   117: aconst_null
    //   118: areturn
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


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\editinggroup\EditingGroupFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */