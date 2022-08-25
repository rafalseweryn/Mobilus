package com.google.android.gms.common.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.stable.zzk;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Set;
import javax.annotation.Nullable;

public final class DbUtils {
  public static void clearDatabase(SQLiteDatabase paramSQLiteDatabase) {
    zza(paramSQLiteDatabase, "table", new String[] { "sqlite_sequence", "android_metadata" });
    zza(paramSQLiteDatabase, "trigger", new String[0]);
    zza(paramSQLiteDatabase, "view", new String[0]);
  }
  
  public static long countCurrentRowBytes(Cursor paramCursor) {
    return countCurrentRowBytes(paramCursor, Charset.forName("UTF-8"));
  }
  
  public static long countCurrentRowBytes(Cursor paramCursor, Charset paramCharset) {
    // Byte code:
    //   0: lconst_0
    //   1: lstore_2
    //   2: iconst_0
    //   3: istore #4
    //   5: iload #4
    //   7: aload_0
    //   8: invokeinterface getColumnCount : ()I
    //   13: if_icmpge -> 116
    //   16: aload_0
    //   17: iload #4
    //   19: invokeinterface getType : (I)I
    //   24: tableswitch default -> 60, 0 -> 100, 1 -> 100, 2 -> 100, 3 -> 77, 4 -> 63
    //   60: goto -> 110
    //   63: aload_0
    //   64: iload #4
    //   66: invokeinterface getBlob : (I)[B
    //   71: arraylength
    //   72: istore #5
    //   74: goto -> 92
    //   77: aload_0
    //   78: iload #4
    //   80: invokeinterface getString : (I)Ljava/lang/String;
    //   85: aload_1
    //   86: invokevirtual getBytes : (Ljava/nio/charset/Charset;)[B
    //   89: arraylength
    //   90: istore #5
    //   92: iload #5
    //   94: i2l
    //   95: lstore #6
    //   97: goto -> 105
    //   100: ldc2_w 4
    //   103: lstore #6
    //   105: lload_2
    //   106: lload #6
    //   108: ladd
    //   109: lstore_2
    //   110: iinc #4, 1
    //   113: goto -> 5
    //   116: lload_2
    //   117: lreturn
  }
  
  public static long getDatabaseSize(Context paramContext, String paramString) {
    try {
      File file = paramContext.getDatabasePath(paramString);
      if (file != null)
        return file.length(); 
    } catch (SecurityException securityException) {
      String str = String.valueOf(paramString);
      if (str.length() != 0) {
        str = "Failed to get db size for ".concat(str);
      } else {
        str = new String("Failed to get db size for ");
      } 
      Log.w("DbUtils", str);
    } 
    return 0L;
  }
  
  @Nullable
  public static Integer getIntegerFromCursor(Cursor paramCursor, int paramInt) {
    return getIntegerFromCursor(paramCursor, paramInt, null);
  }
  
  @Nullable
  public static Integer getIntegerFromCursor(Cursor paramCursor, int paramInt, @Nullable Integer paramInteger) {
    return (paramInt >= 0) ? (paramCursor.isNull(paramInt) ? paramInteger : Integer.valueOf(paramCursor.getInt(paramInt))) : paramInteger;
  }
  
  @Nullable
  public static Long getLongFromCursor(Cursor paramCursor, int paramInt) {
    return getLongFromCursor(paramCursor, paramInt, null);
  }
  
  @Nullable
  public static Long getLongFromCursor(Cursor paramCursor, int paramInt, @Nullable Long paramLong) {
    return (paramInt >= 0) ? (paramCursor.isNull(paramInt) ? paramLong : Long.valueOf(paramCursor.getLong(paramInt))) : paramLong;
  }
  
  @Nullable
  public static String getStringFromCursor(Cursor paramCursor, int paramInt) {
    return getStringFromCursor(paramCursor, paramInt, null);
  }
  
  @Nullable
  public static String getStringFromCursor(Cursor paramCursor, int paramInt, @Nullable String paramString) {
    return (paramInt >= 0) ? (paramCursor.isNull(paramInt) ? paramString : paramCursor.getString(paramInt)) : paramString;
  }
  
  public static void putIntegerIntoContentValues(ContentValues paramContentValues, String paramString, @Nullable Integer paramInteger) {
    if (paramInteger != null) {
      paramContentValues.put(paramString, paramInteger);
      return;
    } 
    paramContentValues.putNull(paramString);
  }
  
  public static void putLongIntoContentValues(ContentValues paramContentValues, String paramString, @Nullable Long paramLong) {
    if (paramLong != null) {
      paramContentValues.put(paramString, paramLong);
      return;
    } 
    paramContentValues.putNull(paramString);
  }
  
  public static void putStringIntoContentValues(ContentValues paramContentValues, String paramString1, @Nullable String paramString2) {
    if (paramString2 != null) {
      paramContentValues.put(paramString1, paramString2);
      return;
    } 
    paramContentValues.putNull(paramString1);
  }
  
  private static void zza(SQLiteDatabase paramSQLiteDatabase, String paramString, String... paramVarArgs) {
    boolean bool;
    if ("table".equals(paramString) || "view".equals(paramString) || "trigger".equals(paramString)) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool);
    Cursor cursor = paramSQLiteDatabase.query("SQLITE_MASTER", new String[] { "name" }, "type == ?", new String[] { paramString }, null, null, null);
    Throwable throwable1 = null;
    Throwable throwable2 = throwable1;
    try {
      Set<String> set = CollectionUtils.setOf(paramVarArgs);
      while (true) {
        throwable2 = throwable1;
        if (cursor.moveToNext()) {
          throwable2 = throwable1;
          String str = cursor.getString(0);
          throwable2 = throwable1;
          if (!set.contains(str)) {
            throwable2 = throwable1;
            int i = String.valueOf(paramString).length();
            throwable2 = throwable1;
            int j = String.valueOf(str).length();
            throwable2 = throwable1;
            StringBuilder stringBuilder = new StringBuilder();
            throwable2 = throwable1;
            this(i + 8 + j);
            throwable2 = throwable1;
            stringBuilder.append("DROP ");
            throwable2 = throwable1;
            stringBuilder.append(paramString);
            throwable2 = throwable1;
            stringBuilder.append(" '");
            throwable2 = throwable1;
            stringBuilder.append(str);
            throwable2 = throwable1;
            stringBuilder.append("'");
            throwable2 = throwable1;
            paramSQLiteDatabase.execSQL(stringBuilder.toString());
          } 
          continue;
        } 
        if (cursor != null)
          cursor.close(); 
        return;
      } 
    } catch (Throwable throwable) {
      throwable2 = throwable;
      throw throwable;
    } finally {}
    if (cursor != null)
      if (throwable2 != null) {
        try {
          cursor.close();
        } catch (Throwable throwable) {
          zzk.zza(throwable2, throwable);
        } 
      } else {
        cursor.close();
      }  
    throw paramSQLiteDatabase;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\DbUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */