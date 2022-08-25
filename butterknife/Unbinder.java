package butterknife;

import android.support.annotation.UiThread;

public interface Unbinder {
  public static final Unbinder EMPTY = new Unbinder() {
      public void unbind() {}
    };
  
  @UiThread
  void unbind();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\butterknife\Unbinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */