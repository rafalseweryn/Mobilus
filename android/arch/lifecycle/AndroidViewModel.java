package android.arch.lifecycle;

import android.annotation.SuppressLint;
import android.app.Application;
import android.support.annotation.NonNull;

public class AndroidViewModel extends ViewModel {
  @SuppressLint({"StaticFieldLeak"})
  private Application mApplication;
  
  public AndroidViewModel(@NonNull Application paramApplication) {
    this.mApplication = paramApplication;
  }
  
  @NonNull
  public <T extends Application> T getApplication() {
    return (T)this.mApplication;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\arch\lifecycle\AndroidViewModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */