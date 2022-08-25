package pl.com.mobilelabs.mobilus.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.fragment.DoSthDeviceFragment;
import pl.com.mobilelabs.mobilus.view.fragment.IDevicesGroupsPlacesScenesListFragmentContext;

public abstract class UltimateHeaderActivity extends BackButtonHeaderActivity implements IDevicesGroupsPlacesScenesListFragmentContext {
  public ImageButton backButton;
  
  protected LinearLayout imageBackground;
  
  protected Bundle mBundle;
  
  protected DoSthDeviceFragment mFragment;
  
  protected void backSelected() {
    this.mFragment.backSelected();
    super.backSelected();
  }
  
  public void changeBackButtonImage(int paramInt) {
    this.backButton.setImageResource(paramInt);
  }
  
  public void changeHeaderColor(int paramInt) {
    this.imageBackground.setBackgroundColor(ContextCompat.getColor((Context)this, paramInt));
  }
  
  public void changeIconImage(int paramInt) {
    this.iconImage.setImageResource(paramInt);
  }
  
  public void changeIconText(int paramInt) {
    this.iconText.setText(paramInt);
  }
  
  public void changeTitleText(int paramInt) {
    this.titleText.setText(paramInt);
  }
  
  protected void changeView(int paramInt) {
    this.frame.removeView(this.settingsView);
    this.settingsView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(paramInt, null);
    this.frame.addView(this.settingsView);
  }
  
  public void changeView(int paramInt, DoSthDeviceFragment paramDoSthDeviceFragment) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
          public void onFragmentViewCreated(@NonNull FragmentManager param1FragmentManager, @NonNull Fragment param1Fragment, @NonNull View param1View, @Nullable Bundle param1Bundle) {
            super.onFragmentViewCreated(param1FragmentManager, param1Fragment, param1View, param1Bundle);
            UltimateHeaderActivity.this.settingsView = param1View;
          }
        }false);
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.remove((Fragment)this.mFragment);
    fragmentTransaction.replace(2131296462, (Fragment)paramDoSthDeviceFragment).commit();
    LayoutInflater layoutInflater = (LayoutInflater)getSystemService("layout_inflater");
    this.mFragment = paramDoSthDeviceFragment;
  }
  
  protected void configureView(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    configureView(paramInt1, paramInt2, paramInt3);
    this.imageBackground.setBackgroundColor(ContextCompat.getColor((Context)this, paramInt4));
    this.backButton.setImageResource(paramInt5);
  }
  
  public ManagementService.ManagementServiceBinder getManagementServiceBinder() {
    return this.managementBinder;
  }
  
  protected void initializeActivity(int paramInt, DoSthDeviceFragment paramDoSthDeviceFragment) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
          public void onFragmentViewCreated(@NonNull FragmentManager param1FragmentManager, @NonNull Fragment param1Fragment, @NonNull View param1View, @Nullable Bundle param1Bundle) {
            super.onFragmentViewCreated(param1FragmentManager, param1Fragment, param1View, param1Bundle);
            UltimateHeaderActivity.this.settingsView = param1View;
          }
        }false);
    fragmentManager.beginTransaction().replace(2131296462, (Fragment)paramDoSthDeviceFragment).commit();
    this.mFragment = paramDoSthDeviceFragment;
  }
  
  protected void managementServiceConnected() {
    super.managementServiceConnected();
    this.mFragment.managementServiceConnected();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    this.mBundle = paramBundle;
    this.backButton = (ImageButton)findViewById(2131296461);
    this.imageBackground = (LinearLayout)findViewById(2131296464);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\UltimateHeaderActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */