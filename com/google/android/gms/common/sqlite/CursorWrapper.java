package com.google.android.gms.common.sqlite;

import android.database.AbstractWindowedCursor;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.CursorWrapper;

public class CursorWrapper extends CursorWrapper implements CrossProcessCursor {
  private AbstractWindowedCursor zzxu;
  
  public CursorWrapper(Cursor paramCursor) {
    super(paramCursor);
    String str;
    for (byte b = 0; b < 10 && paramCursor instanceof CursorWrapper; b++)
      paramCursor = ((CursorWrapper)paramCursor).getWrappedCursor(); 
    if (!(paramCursor instanceof AbstractWindowedCursor)) {
      str = String.valueOf(paramCursor.getClass().getName());
      if (str.length() != 0) {
        str = "Unknown type: ".concat(str);
      } else {
        str = new String("Unknown type: ");
      } 
      throw new IllegalArgumentException(str);
    } 
    this.zzxu = (AbstractWindowedCursor)str;
  }
  
  public void fillWindow(int paramInt, CursorWindow paramCursorWindow) {
    this.zzxu.fillWindow(paramInt, paramCursorWindow);
  }
  
  public CursorWindow getWindow() {
    return this.zzxu.getWindow();
  }
  
  public AbstractWindowedCursor getWrappedCursor() {
    return this.zzxu;
  }
  
  public boolean onMove(int paramInt1, int paramInt2) {
    return this.zzxu.onMove(paramInt1, paramInt2);
  }
  
  public void setWindow(CursorWindow paramCursorWindow) {
    this.zzxu.setWindow(paramCursorWindow);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\sqlite\CursorWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */