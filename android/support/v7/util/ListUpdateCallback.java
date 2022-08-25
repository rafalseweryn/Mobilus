package android.support.v7.util;

import android.support.annotation.Nullable;

public interface ListUpdateCallback {
  void onChanged(int paramInt1, int paramInt2, @Nullable Object paramObject);
  
  void onInserted(int paramInt1, int paramInt2);
  
  void onMoved(int paramInt1, int paramInt2);
  
  void onRemoved(int paramInt1, int paramInt2);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v\\util\ListUpdateCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */