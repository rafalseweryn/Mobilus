package pl.com.mobilelabs.mobilus;

import android.app.Application;
import android.content.Context;

public class MobilusApplication extends Application {
  public void onCreate() {
    super.onCreate();
    FontsOverride.setDefaultFont((Context)this, "DEFAULT", "Titillium-Regular.otf");
    FontsOverride.setDefaultFont((Context)this, "MONOSPACE", "Titillium-Regular.otf");
    FontsOverride.setDefaultFont((Context)this, "SERIF", "Titillium-Regular.otf");
    FontsOverride.setDefaultFont((Context)this, "SANS_SERIF", "Titillium-Regular.otf");
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\MobilusApplication.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */