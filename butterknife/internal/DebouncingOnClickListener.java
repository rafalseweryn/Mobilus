package butterknife.internal;

import android.view.View;

public abstract class DebouncingOnClickListener implements View.OnClickListener {
  private static final Runnable ENABLE_AGAIN = new Runnable() {
      public void run() {
        DebouncingOnClickListener.enabled = true;
      }
    };
  
  static boolean enabled = true;
  
  public abstract void doClick(View paramView);
  
  public final void onClick(View paramView) {
    if (enabled) {
      enabled = false;
      paramView.post(ENABLE_AGAIN);
      doClick(paramView);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\butterknife\internal\DebouncingOnClickListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */