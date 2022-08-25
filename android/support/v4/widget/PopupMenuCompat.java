package android.support.v4.widget;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.PopupMenu;

public final class PopupMenuCompat {
  @Nullable
  public static View.OnTouchListener getDragToOpenListener(@NonNull Object paramObject) {
    return (Build.VERSION.SDK_INT >= 19) ? ((PopupMenu)paramObject).getDragToOpenListener() : null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\widget\PopupMenuCompat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */