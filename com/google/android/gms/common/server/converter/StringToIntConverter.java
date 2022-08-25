package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.HashMap;

@Class(creator = "StringToIntConverterCreator")
public final class StringToIntConverter extends AbstractSafeParcelable implements FastJsonResponse.FieldConverter<String, Integer> {
  public static final Parcelable.Creator<StringToIntConverter> CREATOR = new StringToIntConverterCreator();
  
  @VersionField(id = 1)
  private final int zzal = 1;
  
  private final HashMap<String, Integer> zzwe = new HashMap<>();
  
  private final SparseArray<String> zzwf = new SparseArray();
  
  @Field(getter = "getSerializedMap", id = 2)
  private final ArrayList<Entry> zzwg = null;
  
  public StringToIntConverter() {}
  
  @Constructor
  StringToIntConverter(@Param(id = 1) int paramInt, @Param(id = 2) ArrayList<Entry> paramArrayList) {
    paramArrayList = paramArrayList;
    int i = paramArrayList.size();
    paramInt = 0;
    while (paramInt < i) {
      Entry entry = (Entry)paramArrayList.get(paramInt);
      paramInt++;
      entry = entry;
      add(entry.zzwh, entry.zzwi);
    } 
  }
  
  public final StringToIntConverter add(String paramString, int paramInt) {
    this.zzwe.put(paramString, Integer.valueOf(paramInt));
    this.zzwf.put(paramInt, paramString);
    return this;
  }
  
  public final Integer convert(String paramString) {
    Integer integer2 = this.zzwe.get(paramString);
    Integer integer1 = integer2;
    if (integer2 == null)
      integer1 = this.zzwe.get("gms_unknown"); 
    return integer1;
  }
  
  public final String convertBack(Integer paramInteger) {
    String str = (String)this.zzwf.get(paramInteger.intValue());
    return (str == null && this.zzwe.containsKey("gms_unknown")) ? "gms_unknown" : str;
  }
  
  public final int getTypeIn() {
    return 7;
  }
  
  public final int getTypeOut() {
    return 0;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzal);
    ArrayList<Entry> arrayList = new ArrayList();
    for (String str : this.zzwe.keySet())
      arrayList.add(new Entry(str, ((Integer)this.zzwe.get(str)).intValue())); 
    SafeParcelWriter.writeTypedList(paramParcel, 2, arrayList, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
  
  @Class(creator = "StringToIntConverterEntryCreator")
  public static final class Entry extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Entry> CREATOR = new StringToIntConverterEntryCreator();
    
    @VersionField(id = 1)
    private final int versionCode;
    
    @Field(id = 2)
    final String zzwh;
    
    @Field(id = 3)
    final int zzwi;
    
    @Constructor
    Entry(@Param(id = 1) int param1Int1, @Param(id = 2) String param1String, @Param(id = 3) int param1Int2) {
      this.versionCode = param1Int1;
      this.zzwh = param1String;
      this.zzwi = param1Int2;
    }
    
    Entry(String param1String, int param1Int) {
      this.versionCode = 1;
      this.zzwh = param1String;
      this.zzwi = param1Int;
    }
    
    public final void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Int = SafeParcelWriter.beginObjectHeader(param1Parcel);
      SafeParcelWriter.writeInt(param1Parcel, 1, this.versionCode);
      SafeParcelWriter.writeString(param1Parcel, 2, this.zzwh, false);
      SafeParcelWriter.writeInt(param1Parcel, 3, this.zzwi);
      SafeParcelWriter.finishObjectHeader(param1Parcel, param1Int);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\server\converter\StringToIntConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */