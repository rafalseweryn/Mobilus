package pl.com.mobilelabs.mobilus.view.fragment.addingscene;

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
import pl.com.mobilelabs.mobilus.model.objects.Scene;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.activity.AddingSceneActivity;
import pl.com.mobilelabs.mobilus.view.activity.UltimateHeaderActivity;
import pl.com.mobilelabs.mobilus.view.fragment.DoSthDeviceFragment;

public abstract class AddingSceneFragment extends DoSthDeviceFragment {
  protected BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        AddingSceneFragment.this.broadcastReceived(param1Context, param1Intent);
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
  
  protected Device extractDevice(long paramLong) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getActivity : ()Landroid/support/v4/app/FragmentActivity;
    //   4: checkcast pl/com/mobilelabs/mobilus/view/activity/UltimateHeaderActivity
    //   7: astore_3
    //   8: aload_3
    //   9: ifnull -> 86
    //   12: aload_0
    //   13: aload_3
    //   14: invokevirtual getManagementServiceBinder : ()Lpl/com/mobilelabs/mobilus/services/managementservice/ManagementService$ManagementServiceBinder;
    //   17: putfield managementServiceBinder : Lpl/com/mobilelabs/mobilus/services/managementservice/ManagementService$ManagementServiceBinder;
    //   20: aload_0
    //   21: getfield managementServiceBinder : Lpl/com/mobilelabs/mobilus/services/managementservice/ManagementService$ManagementServiceBinder;
    //   24: ifnull -> 84
    //   27: lload_1
    //   28: lconst_0
    //   29: lcmp
    //   30: iflt -> 82
    //   33: aload_0
    //   34: getfield managementServiceBinder : Lpl/com/mobilelabs/mobilus/services/managementservice/ManagementService$ManagementServiceBinder;
    //   37: invokevirtual getDevices : ()Ljava/util/ArrayList;
    //   40: invokevirtual iterator : ()Ljava/util/Iterator;
    //   43: astore #4
    //   45: aload #4
    //   47: invokeinterface hasNext : ()Z
    //   52: ifeq -> 78
    //   55: aload #4
    //   57: invokeinterface next : ()Ljava/lang/Object;
    //   62: checkcast pl/com/mobilelabs/mobilus/model/objects/Device
    //   65: astore_3
    //   66: aload_3
    //   67: invokevirtual getId : ()J
    //   70: lload_1
    //   71: lcmp
    //   72: ifne -> 45
    //   75: goto -> 80
    //   78: aconst_null
    //   79: astore_3
    //   80: aload_3
    //   81: areturn
    //   82: aconst_null
    //   83: areturn
    //   84: aconst_null
    //   85: areturn
    //   86: aconst_null
    //   87: areturn
  }
  
  protected Scene extractScene() {
    AddingSceneActivity addingSceneActivity = (AddingSceneActivity)getActivity();
    return (addingSceneActivity != null) ? addingSceneActivity.getNewScene() : null;
  }
  
  protected void newConfigurationReceived(Context paramContext, Intent paramIntent) {
    UltimateHeaderActivity ultimateHeaderActivity = (UltimateHeaderActivity)getActivity();
    if (extractScene() == null && ultimateHeaderActivity != null)
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


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\addingscene\AddingSceneFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */