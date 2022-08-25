package android.support.v7.view.menu;

import android.support.annotation.RestrictTo;
import android.widget.ListView;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface ShowableListMenu {
  void dismiss();
  
  ListView getListView();
  
  boolean isShowing();
  
  void show();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v7\view\menu\ShowableListMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */