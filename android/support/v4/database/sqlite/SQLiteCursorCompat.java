package android.support.v4.database.sqlite;

import android.database.sqlite.SQLiteCursor;
import android.os.Build;
import android.support.annotation.NonNull;

public final class SQLiteCursorCompat {
  public static void setFillWindowForwardOnly(@NonNull SQLiteCursor paramSQLiteCursor, boolean paramBoolean) {
    if (Build.VERSION.SDK_INT >= 28)
      paramSQLiteCursor.setFillWindowForwardOnly(paramBoolean); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\database\sqlite\SQLiteCursorCompat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */