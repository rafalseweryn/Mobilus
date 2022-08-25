package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.sqlite.CursorWrapper;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@KeepName
@Class(creator = "DataHolderCreator", validate = true)
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
  public static final Parcelable.Creator<DataHolder> CREATOR = new DataHolderCreator();
  
  private static final Builder zznt = new zza(new String[0], null);
  
  private boolean mClosed = false;
  
  @VersionField(id = 1000)
  private final int zzal;
  
  @Field(getter = "getStatusCode", id = 3)
  private final int zzam;
  
  @Field(getter = "getColumns", id = 1)
  private final String[] zznm;
  
  private Bundle zznn;
  
  @Field(getter = "getWindows", id = 2)
  private final CursorWindow[] zzno;
  
  @Field(getter = "getMetadata", id = 4)
  private final Bundle zznp;
  
  private int[] zznq;
  
  private int zznr;
  
  private boolean zzns = true;
  
  @Constructor
  DataHolder(@Param(id = 1000) int paramInt1, @Param(id = 1) String[] paramArrayOfString, @Param(id = 2) CursorWindow[] paramArrayOfCursorWindow, @Param(id = 3) int paramInt2, @Param(id = 4) Bundle paramBundle) {
    this.zzal = paramInt1;
    this.zznm = paramArrayOfString;
    this.zzno = paramArrayOfCursorWindow;
    this.zzam = paramInt2;
    this.zznp = paramBundle;
  }
  
  public DataHolder(Cursor paramCursor, int paramInt, Bundle paramBundle) {
    this(new CursorWrapper(paramCursor), paramInt, paramBundle);
  }
  
  private DataHolder(Builder paramBuilder, int paramInt, Bundle paramBundle) {
    this(Builder.zza(paramBuilder), zza(paramBuilder, -1), paramInt, paramBundle);
  }
  
  private DataHolder(Builder paramBuilder, int paramInt1, Bundle paramBundle, int paramInt2) {
    this(Builder.zza(paramBuilder), zza(paramBuilder, paramInt2), paramInt1, paramBundle);
  }
  
  public DataHolder(CursorWrapper paramCursorWrapper, int paramInt, Bundle paramBundle) {
    this(paramCursorWrapper.getColumnNames(), zza(paramCursorWrapper), paramInt, paramBundle);
  }
  
  public DataHolder(String[] paramArrayOfString, CursorWindow[] paramArrayOfCursorWindow, int paramInt, Bundle paramBundle) {
    this.zzal = 1;
    this.zznm = (String[])Preconditions.checkNotNull(paramArrayOfString);
    this.zzno = (CursorWindow[])Preconditions.checkNotNull(paramArrayOfCursorWindow);
    this.zzam = paramInt;
    this.zznp = paramBundle;
    validateContents();
  }
  
  public static Builder builder(String[] paramArrayOfString) {
    return new Builder(paramArrayOfString, null, null);
  }
  
  public static Builder builder(String[] paramArrayOfString, String paramString) {
    Preconditions.checkNotNull(paramString);
    return new Builder(paramArrayOfString, paramString, null);
  }
  
  public static DataHolder empty(int paramInt) {
    return empty(paramInt, null);
  }
  
  public static DataHolder empty(int paramInt, Bundle paramBundle) {
    return new DataHolder(zznt, paramInt, paramBundle);
  }
  
  private final void zza(String paramString, int paramInt) {
    if (this.zznn == null || !this.zznn.containsKey(paramString)) {
      paramString = String.valueOf(paramString);
      if (paramString.length() != 0) {
        paramString = "No such column: ".concat(paramString);
      } else {
        paramString = new String("No such column: ");
      } 
      throw new IllegalArgumentException(paramString);
    } 
    if (isClosed())
      throw new IllegalArgumentException("Buffer is closed."); 
    if (paramInt < 0 || paramInt >= this.zznr)
      throw new CursorIndexOutOfBoundsException(paramInt, this.zznr); 
  }
  
  private static CursorWindow[] zza(Builder paramBuilder, int paramInt) {
    List<Map> list;
    int i = (Builder.zza(paramBuilder)).length;
    boolean bool = false;
    if (i == 0)
      return new CursorWindow[0]; 
    if (paramInt < 0 || paramInt >= Builder.zzb(paramBuilder).size()) {
      list = Builder.zzb(paramBuilder);
    } else {
      list = Builder.zzb(paramBuilder).subList(0, paramInt);
    } 
    int j = list.size();
    CursorWindow cursorWindow = new CursorWindow(false);
    ArrayList<CursorWindow> arrayList = new ArrayList();
    arrayList.add(cursorWindow);
    cursorWindow.setNumColumns((Builder.zza(paramBuilder)).length);
    i = 0;
    paramInt = i;
    while (paramInt < j) {
      CursorWindow cursorWindow1 = cursorWindow;
      try {
        CursorWindow cursorWindow3;
        String str;
        CursorWindow cursorWindow2;
        if (!cursorWindow.allocRow()) {
          StringBuilder stringBuilder = new StringBuilder();
          this(72);
          stringBuilder.append("Allocating additional cursor window for large data set (row ");
          stringBuilder.append(paramInt);
          stringBuilder.append(")");
          Log.d("DataHolder", stringBuilder.toString());
          cursorWindow = new CursorWindow();
          this(false);
          cursorWindow.setStartPosition(paramInt);
          cursorWindow.setNumColumns((Builder.zza(paramBuilder)).length);
          arrayList.add(cursorWindow);
          cursorWindow3 = cursorWindow;
          if (!cursorWindow.allocRow()) {
            Log.e("DataHolder", "Unable to allocate row to hold data.");
            arrayList.remove(cursorWindow);
            return arrayList.<CursorWindow>toArray(new CursorWindow[arrayList.size()]);
          } 
        } 
        Map map = list.get(paramInt);
        byte b = 0;
        boolean bool1 = true;
        while (true) {
          if (b < (Builder.zza(paramBuilder)).length && bool1) {
            String str1 = Builder.zza(paramBuilder)[b];
            Object object = map.get(str1);
            if (object == null) {
              bool1 = cursorWindow3.putNull(paramInt, b);
            } else if (object instanceof String) {
              bool1 = cursorWindow3.putString((String)object, paramInt, b);
            } else {
              long l;
              if (object instanceof Long) {
                l = ((Long)object).longValue();
              } else {
                if (object instanceof Integer) {
                  bool1 = cursorWindow3.putLong(((Integer)object).intValue(), paramInt, b);
                } else {
                  if (object instanceof Boolean) {
                    if (((Boolean)object).booleanValue()) {
                      l = 1L;
                    } else {
                      l = 0L;
                    } 
                  } else {
                    if (object instanceof byte[]) {
                      bool1 = cursorWindow3.putBlob((byte[])object, paramInt, b);
                    } else if (object instanceof Double) {
                      bool1 = cursorWindow3.putDouble(((Double)object).doubleValue(), paramInt, b);
                    } else if (object instanceof Float) {
                      bool1 = cursorWindow3.putDouble(((Float)object).floatValue(), paramInt, b);
                    } else {
                      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
                      str = String.valueOf(object);
                      paramInt = String.valueOf(str1).length();
                      i = String.valueOf(str).length();
                      StringBuilder stringBuilder = new StringBuilder();
                      this(paramInt + 32 + i);
                      stringBuilder.append("Unsupported object for column ");
                      stringBuilder.append(str1);
                      stringBuilder.append(": ");
                      stringBuilder.append(str);
                      this(stringBuilder.toString());
                      throw illegalArgumentException;
                    } 
                    b++;
                  } 
                  bool1 = str.putLong(l, paramInt, b);
                } 
                b++;
              } 
              bool1 = str.putLong(l, paramInt, b);
            } 
          } else {
            break;
          } 
          b++;
        } 
        if (!bool1) {
          DataHolderException dataHolderException;
          if (i != 0) {
            dataHolderException = new DataHolderException();
            this("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
            throw dataHolderException;
          } 
          StringBuilder stringBuilder = new StringBuilder();
          this(74);
          stringBuilder.append("Couldn't populate window data for row ");
          stringBuilder.append(paramInt);
          stringBuilder.append(" - allocating new window.");
          Log.d("DataHolder", stringBuilder.toString());
          str.freeLastRow();
          cursorWindow2 = new CursorWindow();
          this(false);
          cursorWindow2.setStartPosition(paramInt);
          cursorWindow2.setNumColumns((Builder.zza((Builder)dataHolderException)).length);
          arrayList.add(cursorWindow2);
          paramInt--;
          i = 1;
        } else {
          i = 0;
        } 
        paramInt++;
        cursorWindow = cursorWindow2;
      } catch (RuntimeException runtimeException) {
        i = arrayList.size();
        for (paramInt = bool; paramInt < i; paramInt++)
          ((CursorWindow)arrayList.get(paramInt)).close(); 
        throw runtimeException;
      } 
    } 
    return arrayList.<CursorWindow>toArray(new CursorWindow[arrayList.size()]);
  }
  
  private static CursorWindow[] zza(CursorWrapper paramCursorWrapper) {
    ArrayList<CursorWindow> arrayList = new ArrayList();
    try {
      int j;
      int i = paramCursorWrapper.getCount();
      CursorWindow cursorWindow = paramCursorWrapper.getWindow();
      if (cursorWindow != null && cursorWindow.getStartPosition() == 0) {
        cursorWindow.acquireReference();
        paramCursorWrapper.setWindow(null);
        arrayList.add(cursorWindow);
        j = cursorWindow.getNumRows();
      } else {
        j = 0;
      } 
      while (j < i && paramCursorWrapper.moveToPosition(j)) {
        cursorWindow = paramCursorWrapper.getWindow();
        if (cursorWindow != null) {
          cursorWindow.acquireReference();
          paramCursorWrapper.setWindow(null);
        } else {
          cursorWindow = new CursorWindow();
          this(false);
          cursorWindow.setStartPosition(j);
          paramCursorWrapper.fillWindow(j, cursorWindow);
        } 
        if (cursorWindow.getNumRows() != 0) {
          arrayList.add(cursorWindow);
          int k = cursorWindow.getStartPosition();
          j = cursorWindow.getNumRows();
          j = k + j;
        } 
      } 
      return arrayList.<CursorWindow>toArray(new CursorWindow[arrayList.size()]);
    } finally {
      paramCursorWrapper.close();
    } 
  }
  
  public final void clearColumn(String paramString, int paramInt1, int paramInt2) {
    zza(paramString, paramInt1);
    this.zzno[paramInt2].putNull(paramInt1, this.zznn.getInt(paramString));
  }
  
  public final void close() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mClosed : Z
    //   6: ifne -> 40
    //   9: aload_0
    //   10: iconst_1
    //   11: putfield mClosed : Z
    //   14: iconst_0
    //   15: istore_1
    //   16: iload_1
    //   17: aload_0
    //   18: getfield zzno : [Landroid/database/CursorWindow;
    //   21: arraylength
    //   22: if_icmpge -> 40
    //   25: aload_0
    //   26: getfield zzno : [Landroid/database/CursorWindow;
    //   29: iload_1
    //   30: aaload
    //   31: invokevirtual close : ()V
    //   34: iinc #1, 1
    //   37: goto -> 16
    //   40: aload_0
    //   41: monitorexit
    //   42: return
    //   43: astore_2
    //   44: aload_0
    //   45: monitorexit
    //   46: aload_2
    //   47: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	43	finally
    //   16	34	43	finally
    //   40	42	43	finally
    //   44	46	43	finally
  }
  
  public final void copyToBuffer(String paramString, int paramInt1, int paramInt2, CharArrayBuffer paramCharArrayBuffer) {
    zza(paramString, paramInt1);
    this.zzno[paramInt2].copyStringToBuffer(paramInt1, this.zznn.getInt(paramString), paramCharArrayBuffer);
  }
  
  public final void disableLeakDetection() {
    this.zzns = false;
  }
  
  protected final void finalize() throws Throwable {
    try {
      if (this.zzns && this.zzno.length > 0 && !isClosed()) {
        close();
        String str = toString();
        int i = String.valueOf(str).length();
        StringBuilder stringBuilder = new StringBuilder();
        this(i + 178);
        stringBuilder.append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ");
        stringBuilder.append(str);
        stringBuilder.append(")");
        Log.e("DataBuffer", stringBuilder.toString());
      } 
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public final boolean getBoolean(String paramString, int paramInt1, int paramInt2) {
    zza(paramString, paramInt1);
    return (Long.valueOf(this.zzno[paramInt2].getLong(paramInt1, this.zznn.getInt(paramString))).longValue() == 1L);
  }
  
  public final byte[] getByteArray(String paramString, int paramInt1, int paramInt2) {
    zza(paramString, paramInt1);
    return this.zzno[paramInt2].getBlob(paramInt1, this.zznn.getInt(paramString));
  }
  
  public final int getCount() {
    return this.zznr;
  }
  
  public final double getDouble(String paramString, int paramInt1, int paramInt2) {
    zza(paramString, paramInt1);
    return this.zzno[paramInt2].getDouble(paramInt1, this.zznn.getInt(paramString));
  }
  
  public final float getFloat(String paramString, int paramInt1, int paramInt2) {
    zza(paramString, paramInt1);
    return this.zzno[paramInt2].getFloat(paramInt1, this.zznn.getInt(paramString));
  }
  
  public final int getInteger(String paramString, int paramInt1, int paramInt2) {
    zza(paramString, paramInt1);
    return this.zzno[paramInt2].getInt(paramInt1, this.zznn.getInt(paramString));
  }
  
  public final long getLong(String paramString, int paramInt1, int paramInt2) {
    zza(paramString, paramInt1);
    return this.zzno[paramInt2].getLong(paramInt1, this.zznn.getInt(paramString));
  }
  
  public final Bundle getMetadata() {
    return this.zznp;
  }
  
  public final int getStatusCode() {
    return this.zzam;
  }
  
  public final String getString(String paramString, int paramInt1, int paramInt2) {
    zza(paramString, paramInt1);
    return this.zzno[paramInt2].getString(paramInt1, this.zznn.getInt(paramString));
  }
  
  public final int getWindowIndex(int paramInt) {
    boolean bool;
    int i;
    byte b = 0;
    if (paramInt >= 0 && paramInt < this.zznr) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkState(bool);
    while (true) {
      i = b;
      if (b < this.zznq.length) {
        if (paramInt < this.zznq[b]) {
          i = b - 1;
          break;
        } 
        b++;
        continue;
      } 
      break;
    } 
    paramInt = i;
    if (i == this.zznq.length)
      paramInt = i - 1; 
    return paramInt;
  }
  
  public final boolean hasColumn(String paramString) {
    return this.zznn.containsKey(paramString);
  }
  
  public final boolean hasNull(String paramString, int paramInt1, int paramInt2) {
    zza(paramString, paramInt1);
    return this.zzno[paramInt2].isNull(paramInt1, this.zznn.getInt(paramString));
  }
  
  public final boolean isClosed() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mClosed : Z
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	11	finally
    //   12	14	11	finally
  }
  
  public final void logCursorMetadataForDebugging() {
    Log.d("DataHolder", "*******************************************");
    int i = this.zzno.length;
    StringBuilder stringBuilder = new StringBuilder(32);
    stringBuilder.append("num cursor windows : ");
    stringBuilder.append(i);
    Log.d("DataHolder", stringBuilder.toString());
    i = this.zznr;
    stringBuilder = new StringBuilder(46);
    stringBuilder.append("total number of objects in holder: ");
    stringBuilder.append(i);
    Log.d("DataHolder", stringBuilder.toString());
    i = this.zznq.length;
    stringBuilder = new StringBuilder(42);
    stringBuilder.append("total mumber of windowOffsets: ");
    stringBuilder.append(i);
    Log.d("DataHolder", stringBuilder.toString());
    for (i = 0; i < this.zznq.length; i++) {
      int j = this.zznq[i];
      stringBuilder = new StringBuilder(43);
      stringBuilder.append("offset for window ");
      stringBuilder.append(i);
      stringBuilder.append(" : ");
      stringBuilder.append(j);
      Log.d("DataHolder", stringBuilder.toString());
      j = this.zzno[i].getNumRows();
      stringBuilder = new StringBuilder(45);
      stringBuilder.append("num rows for window ");
      stringBuilder.append(i);
      stringBuilder.append(" : ");
      stringBuilder.append(j);
      Log.d("DataHolder", stringBuilder.toString());
      j = this.zzno[i].getStartPosition();
      stringBuilder = new StringBuilder(46);
      stringBuilder.append("start pos for window ");
      stringBuilder.append(i);
      stringBuilder.append(" : ");
      stringBuilder.append(j);
      Log.d("DataHolder", stringBuilder.toString());
    } 
    Log.d("DataHolder", "*******************************************");
  }
  
  public final Uri parseUri(String paramString, int paramInt1, int paramInt2) {
    paramString = getString(paramString, paramInt1, paramInt2);
    return (paramString == null) ? null : Uri.parse(paramString);
  }
  
  public final void replaceValue(String paramString, int paramInt1, int paramInt2, double paramDouble) {
    zza(paramString, paramInt1);
    this.zzno[paramInt2].putDouble(paramDouble, paramInt1, this.zznn.getInt(paramString));
  }
  
  public final void replaceValue(String paramString, int paramInt1, int paramInt2, long paramLong) {
    zza(paramString, paramInt1);
    this.zzno[paramInt2].putLong(paramLong, paramInt1, this.zznn.getInt(paramString));
  }
  
  public final void replaceValue(String paramString1, int paramInt1, int paramInt2, String paramString2) {
    zza(paramString1, paramInt1);
    this.zzno[paramInt2].putString(paramString2, paramInt1, this.zznn.getInt(paramString1));
  }
  
  public final void replaceValue(String paramString, int paramInt1, int paramInt2, byte[] paramArrayOfbyte) {
    zza(paramString, paramInt1);
    this.zzno[paramInt2].putBlob(paramArrayOfbyte, paramInt1, this.zznn.getInt(paramString));
  }
  
  public final void validateContents() {
    this.zznn = new Bundle();
    byte b = 0;
    int i;
    for (i = 0; i < this.zznm.length; i++)
      this.zznn.putInt(this.zznm[i], i); 
    this.zznq = new int[this.zzno.length];
    i = 0;
    while (b < this.zzno.length) {
      this.zznq[b] = i;
      int j = this.zzno[b].getStartPosition();
      i += this.zzno[b].getNumRows() - i - j;
      b++;
    } 
    this.zznr = i;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeStringArray(paramParcel, 1, this.zznm, false);
    SafeParcelWriter.writeTypedArray(paramParcel, 2, (Parcelable[])this.zzno, paramInt, false);
    SafeParcelWriter.writeInt(paramParcel, 3, getStatusCode());
    SafeParcelWriter.writeBundle(paramParcel, 4, getMetadata(), false);
    SafeParcelWriter.writeInt(paramParcel, 1000, this.zzal);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
    if ((paramInt & 0x1) != 0)
      close(); 
  }
  
  public static class Builder {
    private final String[] zznm;
    
    private final ArrayList<HashMap<String, Object>> zznu;
    
    private final String zznv;
    
    private final HashMap<Object, Integer> zznw;
    
    private boolean zznx;
    
    private String zzny;
    
    private Builder(String[] param1ArrayOfString, String param1String) {
      this.zznm = (String[])Preconditions.checkNotNull(param1ArrayOfString);
      this.zznu = new ArrayList<>();
      this.zznv = param1String;
      this.zznw = new HashMap<>();
      this.zznx = false;
      this.zzny = null;
    }
    
    private final boolean zzg(String param1String) {
      Asserts.checkNotNull(param1String);
      return (this.zznx && param1String.equals(this.zzny));
    }
    
    public DataHolder build(int param1Int) {
      return new DataHolder(this, param1Int, null, null);
    }
    
    public DataHolder build(int param1Int, Bundle param1Bundle) {
      return new DataHolder(this, param1Int, param1Bundle, -1, null);
    }
    
    public DataHolder build(int param1Int1, Bundle param1Bundle, int param1Int2) {
      return new DataHolder(this, param1Int1, param1Bundle, param1Int2, null);
    }
    
    public boolean containsRowWithValue(String param1String, Object param1Object) {
      int i = this.zznu.size();
      for (byte b = 0; b < i; b++) {
        if (Objects.equal(((HashMap)this.zznu.get(b)).get(param1String), param1Object))
          return true; 
      } 
      return false;
    }
    
    public Builder descendingSort(String param1String) {
      if (zzg(param1String))
        return this; 
      sort(param1String);
      Collections.reverse(this.zznu);
      return this;
    }
    
    public int getCount() {
      return this.zznu.size();
    }
    
    public void modifyUniqueRowValue(Object param1Object1, String param1String, Object param1Object2) {
      if (this.zznv == null)
        return; 
      param1Object1 = this.zznw.get(param1Object1);
      if (param1Object1 == null)
        return; 
      ((HashMap<String, Object>)this.zznu.get(param1Object1.intValue())).put(param1String, param1Object2);
    }
    
    public Builder removeRowsWithValue(String param1String, Object param1Object) {
      for (int i = this.zznu.size() - 1; i >= 0; i--) {
        if (Objects.equal(((HashMap)this.zznu.get(i)).get(param1String), param1Object))
          this.zznu.remove(i); 
      } 
      return this;
    }
    
    public Builder sort(String param1String) {
      if (zzg(param1String))
        return this; 
      Collections.sort(this.zznu, new DataHolder.zza(param1String));
      if (this.zznv != null) {
        this.zznw.clear();
        byte b = 0;
        int i = this.zznu.size();
        while (b < i) {
          Object object = ((HashMap)this.zznu.get(b)).get(this.zznv);
          if (object != null)
            this.zznw.put(object, Integer.valueOf(b)); 
          b++;
        } 
      } 
      this.zznx = true;
      this.zzny = param1String;
      return this;
    }
    
    public Builder withRow(ContentValues param1ContentValues) {
      Asserts.checkNotNull(param1ContentValues);
      HashMap<Object, Object> hashMap = new HashMap<>(param1ContentValues.size());
      for (Map.Entry entry : param1ContentValues.valueSet())
        hashMap.put(entry.getKey(), entry.getValue()); 
      return withRow((HashMap)hashMap);
    }
    
    public Builder withRow(HashMap<String, Object> param1HashMap) {
      // Byte code:
      //   0: aload_1
      //   1: invokestatic checkNotNull : (Ljava/lang/Object;)V
      //   4: aload_0
      //   5: getfield zznv : Ljava/lang/String;
      //   8: ifnonnull -> 16
      //   11: iconst_m1
      //   12: istore_2
      //   13: goto -> 78
      //   16: aload_1
      //   17: aload_0
      //   18: getfield zznv : Ljava/lang/String;
      //   21: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
      //   24: astore_3
      //   25: aload_3
      //   26: ifnonnull -> 32
      //   29: goto -> 11
      //   32: aload_0
      //   33: getfield zznw : Ljava/util/HashMap;
      //   36: aload_3
      //   37: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
      //   40: checkcast java/lang/Integer
      //   43: astore #4
      //   45: aload #4
      //   47: ifnonnull -> 72
      //   50: aload_0
      //   51: getfield zznw : Ljava/util/HashMap;
      //   54: aload_3
      //   55: aload_0
      //   56: getfield zznu : Ljava/util/ArrayList;
      //   59: invokevirtual size : ()I
      //   62: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   65: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   68: pop
      //   69: goto -> 11
      //   72: aload #4
      //   74: invokevirtual intValue : ()I
      //   77: istore_2
      //   78: iload_2
      //   79: iconst_m1
      //   80: if_icmpne -> 95
      //   83: aload_0
      //   84: getfield zznu : Ljava/util/ArrayList;
      //   87: aload_1
      //   88: invokevirtual add : (Ljava/lang/Object;)Z
      //   91: pop
      //   92: goto -> 113
      //   95: aload_0
      //   96: getfield zznu : Ljava/util/ArrayList;
      //   99: iload_2
      //   100: invokevirtual remove : (I)Ljava/lang/Object;
      //   103: pop
      //   104: aload_0
      //   105: getfield zznu : Ljava/util/ArrayList;
      //   108: iload_2
      //   109: aload_1
      //   110: invokevirtual add : (ILjava/lang/Object;)V
      //   113: aload_0
      //   114: iconst_0
      //   115: putfield zznx : Z
      //   118: aload_0
      //   119: areturn
    }
  }
  
  public static class DataHolderException extends RuntimeException {
    public DataHolderException(String param1String) {
      super(param1String);
    }
  }
  
  private static final class zza implements Comparator<HashMap<String, Object>> {
    private final String zznz;
    
    zza(String param1String) {
      this.zznz = (String)Preconditions.checkNotNull(param1String);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\DataHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */