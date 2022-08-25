package pl.com.mobilelabs.mobilus.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import pl.com.mobilelabs.mobilus.view.fragment.DevicesGroupsPlacesScenesListFragment;

public class DashboardPagerAdapter extends FragmentStatePagerAdapter {
  private int[] tabImages = new int[] { 2131230847, 2131230848, 2131230849, 2131230850 };
  
  private int[] tabTitles = new int[] { 2131624049, 2131624050, 2131624051, 2131624052 };
  
  private HashMap<Integer, Fragment> visibleFragments = new HashMap<>();
  
  public DashboardPagerAdapter(FragmentManager paramFragmentManager) {
    super(paramFragmentManager);
  }
  
  public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject) {
    this.visibleFragments.remove(Integer.valueOf(paramInt));
    super.destroyItem(paramViewGroup, paramInt, paramObject);
  }
  
  public int getCount() {
    return this.tabTitles.length;
  }
  
  public Fragment getItem(int paramInt) {
    DevicesGroupsPlacesScenesListFragment devicesGroupsPlacesScenesListFragment = new DevicesGroupsPlacesScenesListFragment();
    devicesGroupsPlacesScenesListFragment.setPresentedDataType(paramInt);
    this.visibleFragments.put(Integer.valueOf(paramInt), devicesGroupsPlacesScenesListFragment);
    return (Fragment)devicesGroupsPlacesScenesListFragment;
  }
  
  public int getTabTitleId(int paramInt) {
    return this.tabTitles[paramInt];
  }
  
  public View getTabView(int paramInt, Context paramContext) {
    View view = LayoutInflater.from(paramContext).inflate(2131492972, null);
    ((ImageView)view.findViewById(2131296631)).setImageResource(this.tabImages[paramInt]);
    return view;
  }
  
  public void managementBinderBecameAvailable() {
    Iterator<Map.Entry> iterator = this.visibleFragments.entrySet().iterator();
    while (iterator.hasNext())
      ((DevicesGroupsPlacesScenesListFragment)((Map.Entry)iterator.next()).getValue()).managementBinderBecameAvailable(); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\adapter\DashboardPagerAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */