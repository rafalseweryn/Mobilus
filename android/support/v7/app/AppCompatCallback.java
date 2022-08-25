package android.support.v7.app;

import android.support.annotation.Nullable;
import android.support.v7.view.ActionMode;

public interface AppCompatCallback {
  void onSupportActionModeFinished(ActionMode paramActionMode);
  
  void onSupportActionModeStarted(ActionMode paramActionMode);
  
  @Nullable
  ActionMode onWindowStartingSupportActionMode(ActionMode.Callback paramCallback);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v7\app\AppCompatCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */