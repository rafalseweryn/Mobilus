package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Class(creator = "FieldMappingDictionaryCreator")
public class FieldMappingDictionary extends AbstractSafeParcelable {
  public static final Parcelable.Creator<FieldMappingDictionary> CREATOR = new FieldMappingDictionaryCreator();
  
  @VersionField(id = 1)
  private final int zzal;
  
  private final HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> zzxk;
  
  @Field(getter = "getSerializedDictionary", id = 2)
  private final ArrayList<Entry> zzxl;
  
  @Field(getter = "getRootClassName", id = 3)
  private final String zzxm;
  
  @Constructor
  FieldMappingDictionary(@Param(id = 1) int paramInt, @Param(id = 2) ArrayList<Entry> paramArrayList, @Param(id = 3) String paramString) {
    this.zzal = paramInt;
    this.zzxl = null;
    HashMap<Object, Object> hashMap = new HashMap<>();
    int i = paramArrayList.size();
    for (paramInt = 0; paramInt < i; paramInt++) {
      Entry entry = paramArrayList.get(paramInt);
      String str = entry.className;
      HashMap<Object, Object> hashMap1 = new HashMap<>();
      int j = entry.zzxn.size();
      for (byte b = 0; b < j; b++) {
        FieldMapPair fieldMapPair = entry.zzxn.get(b);
        hashMap1.put(fieldMapPair.zzxo, fieldMapPair.zzxp);
      } 
      hashMap.put(str, hashMap1);
    } 
    this.zzxk = (HashMap)hashMap;
    this.zzxm = (String)Preconditions.checkNotNull(paramString);
    linkFields();
  }
  
  public FieldMappingDictionary(Class<? extends FastJsonResponse> paramClass) {
    this.zzal = 1;
    this.zzxl = null;
    this.zzxk = new HashMap<>();
    this.zzxm = paramClass.getCanonicalName();
  }
  
  public void copyInternalFieldMappings() {
    for (String str : this.zzxk.keySet()) {
      Map map = this.zzxk.get(str);
      HashMap<Object, Object> hashMap = new HashMap<>();
      for (String str1 : map.keySet())
        hashMap.put(str1, ((FastJsonResponse.Field)map.get(str1)).copyForDictionary()); 
      this.zzxk.put(str, hashMap);
    } 
  }
  
  @VisibleForTesting
  public Map<String, FastJsonResponse.Field<?, ?>> getFieldMapping(Class<? extends FastJsonResponse> paramClass) {
    return this.zzxk.get(paramClass.getCanonicalName());
  }
  
  public Map<String, FastJsonResponse.Field<?, ?>> getFieldMapping(String paramString) {
    return this.zzxk.get(paramString);
  }
  
  public String getRootClassName() {
    return this.zzxm;
  }
  
  public boolean hasFieldMappingForClass(Class<? extends FastJsonResponse> paramClass) {
    return this.zzxk.containsKey(paramClass.getCanonicalName());
  }
  
  public void linkFields() {
    for (String str : this.zzxk.keySet()) {
      Map map = this.zzxk.get(str);
      Iterator<String> iterator = map.keySet().iterator();
      while (iterator.hasNext())
        ((FastJsonResponse.Field)map.get(iterator.next())).setFieldMappingDictionary(this); 
    } 
  }
  
  public void put(Class<? extends FastJsonResponse> paramClass, Map<String, FastJsonResponse.Field<?, ?>> paramMap) {
    this.zzxk.put(paramClass.getCanonicalName(), paramMap);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (String str : this.zzxk.keySet()) {
      stringBuilder.append(str);
      stringBuilder.append(":\n");
      Map map = this.zzxk.get(str);
      for (String str1 : map.keySet()) {
        stringBuilder.append("  ");
        stringBuilder.append(str1);
        stringBuilder.append(": ");
        stringBuilder.append(map.get(str1));
      } 
    } 
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzal);
    ArrayList<Entry> arrayList = new ArrayList();
    for (String str : this.zzxk.keySet())
      arrayList.add(new Entry(str, this.zzxk.get(str))); 
    SafeParcelWriter.writeTypedList(paramParcel, 2, arrayList, false);
    SafeParcelWriter.writeString(paramParcel, 3, getRootClassName(), false);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
  
  @Class(creator = "FieldMappingDictionaryEntryCreator")
  public static class Entry extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Entry> CREATOR = new FieldMappingDictionaryEntryCreator();
    
    @Field(id = 2)
    final String className;
    
    @VersionField(id = 1)
    private final int versionCode;
    
    @Field(id = 3)
    final ArrayList<FieldMappingDictionary.FieldMapPair> zzxn;
    
    @Constructor
    Entry(@Param(id = 1) int param1Int, @Param(id = 2) String param1String, @Param(id = 3) ArrayList<FieldMappingDictionary.FieldMapPair> param1ArrayList) {
      this.versionCode = param1Int;
      this.className = param1String;
      this.zzxn = param1ArrayList;
    }
    
    Entry(String param1String, Map<String, FastJsonResponse.Field<?, ?>> param1Map) {
      String str;
      this.versionCode = 1;
      this.className = param1String;
      if (param1Map == null) {
        param1String = null;
      } else {
        ArrayList<FieldMappingDictionary.FieldMapPair> arrayList = new ArrayList();
        Iterator<String> iterator = param1Map.keySet().iterator();
        while (true) {
          ArrayList<FieldMappingDictionary.FieldMapPair> arrayList1 = arrayList;
          if (iterator.hasNext()) {
            str = iterator.next();
            arrayList.add(new FieldMappingDictionary.FieldMapPair(str, param1Map.get(str)));
            continue;
          } 
          break;
        } 
      } 
      this.zzxn = (ArrayList<FieldMappingDictionary.FieldMapPair>)str;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Int = SafeParcelWriter.beginObjectHeader(param1Parcel);
      SafeParcelWriter.writeInt(param1Parcel, 1, this.versionCode);
      SafeParcelWriter.writeString(param1Parcel, 2, this.className, false);
      SafeParcelWriter.writeTypedList(param1Parcel, 3, this.zzxn, false);
      SafeParcelWriter.finishObjectHeader(param1Parcel, param1Int);
    }
  }
  
  @Class(creator = "FieldMapPairCreator")
  public static class FieldMapPair extends AbstractSafeParcelable {
    public static final Parcelable.Creator<FieldMapPair> CREATOR = new FieldMapPairCreator();
    
    @VersionField(id = 1)
    private final int versionCode;
    
    @Field(id = 2)
    final String zzxo;
    
    @Field(id = 3)
    final FastJsonResponse.Field<?, ?> zzxp;
    
    @Constructor
    FieldMapPair(@Param(id = 1) int param1Int, @Param(id = 2) String param1String, @Param(id = 3) FastJsonResponse.Field<?, ?> param1Field) {
      this.versionCode = param1Int;
      this.zzxo = param1String;
      this.zzxp = param1Field;
    }
    
    FieldMapPair(String param1String, FastJsonResponse.Field<?, ?> param1Field) {
      this.versionCode = 1;
      this.zzxo = param1String;
      this.zzxp = param1Field;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      int i = SafeParcelWriter.beginObjectHeader(param1Parcel);
      SafeParcelWriter.writeInt(param1Parcel, 1, this.versionCode);
      SafeParcelWriter.writeString(param1Parcel, 2, this.zzxo, false);
      SafeParcelWriter.writeParcelable(param1Parcel, 3, (Parcelable)this.zzxp, param1Int, false);
      SafeParcelWriter.finishObjectHeader(param1Parcel, i);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\server\response\FieldMappingDictionary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */