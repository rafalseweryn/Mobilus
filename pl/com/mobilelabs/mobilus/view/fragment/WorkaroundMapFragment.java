package pl.com.mobilelabs.mobilus.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.maps.SupportMapFragment;

public class WorkaroundMapFragment extends SupportMapFragment {
  private OnTouchListener mListener;
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    TouchableWrapper touchableWrapper = new TouchableWrapper((Context)getActivity());
    touchableWrapper.setBackgroundColor(getResources().getColor(17170445));
    ((ViewGroup)view).addView((View)touchableWrapper, new ViewGroup.LayoutParams(-1, -1));
    return view;
  }
  
  public void setListener(OnTouchListener paramOnTouchListener) {
    this.mListener = paramOnTouchListener;
  }
  
  public static interface OnTouchListener {
    void onTouch();
  }
  
  public class TouchableWrapper extends FrameLayout {
    public TouchableWrapper(Context param1Context) {
      super(param1Context);
    }
    
    public boolean dispatchTouchEvent(MotionEvent param1MotionEvent) {
      switch (param1MotionEvent.getAction()) {
        default:
          return super.dispatchTouchEvent(param1MotionEvent);
        case 1:
          WorkaroundMapFragment.this.mListener.onTouch();
        case 0:
          break;
      } 
      WorkaroundMapFragment.this.mListener.onTouch();
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\fragment\WorkaroundMapFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */