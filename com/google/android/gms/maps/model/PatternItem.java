package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.ArrayList;
import java.util.List;

@Class(creator = "PatternItemCreator")
@Reserved({1})
public class PatternItem extends AbstractSafeParcelable {
  public static final Parcelable.Creator<PatternItem> CREATOR = new zzi();
  
  private static final String TAG = "PatternItem";
  
  @Field(getter = "getType", id = 2)
  private final int type;
  
  @Nullable
  @Field(getter = "getLength", id = 3)
  private final Float zzdu;
  
  @Constructor
  public PatternItem(@Param(id = 2) int paramInt, @Nullable @Param(id = 3) Float paramFloat) {
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (paramInt != 1)
      if (paramFloat != null && paramFloat.floatValue() >= 0.0F) {
        bool2 = bool1;
      } else {
        bool2 = false;
      }  
    String str = String.valueOf(paramFloat);
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 45);
    stringBuilder.append("Invalid PatternItem: type=");
    stringBuilder.append(paramInt);
    stringBuilder.append(" length=");
    stringBuilder.append(str);
    Preconditions.checkArgument(bool2, stringBuilder.toString());
    this.type = paramInt;
    this.zzdu = paramFloat;
  }
  
  @Nullable
  static List<PatternItem> zza(@Nullable List<PatternItem> paramList) {
    if (paramList == null)
      return null; 
    ArrayList<PatternItem> arrayList = new ArrayList(paramList.size());
    for (PatternItem patternItem : paramList) {
      if (patternItem == null) {
        patternItem = null;
      } else {
        String str;
        int i;
        StringBuilder stringBuilder;
        switch (patternItem.type) {
          default:
            str = TAG;
            i = patternItem.type;
            stringBuilder = new StringBuilder(37);
            stringBuilder.append("Unknown PatternItem type: ");
            stringBuilder.append(i);
            Log.w(str, stringBuilder.toString());
            break;
          case 2:
            patternItem = new Gap(patternItem.zzdu.floatValue());
            break;
          case 1:
            patternItem = new Dot();
            break;
          case 0:
            patternItem = new Dash(patternItem.zzdu.floatValue());
            break;
        } 
      } 
      arrayList.add(patternItem);
    } 
    return arrayList;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof PatternItem))
      return false; 
    paramObject = paramObject;
    return (this.type == ((PatternItem)paramObject).type && Objects.equal(this.zzdu, ((PatternItem)paramObject).zzdu));
  }
  
  public int hashCode() {
    return Objects.hashCode(new Object[] { Integer.valueOf(this.type), this.zzdu });
  }
  
  public String toString() {
    int i = this.type;
    String str = String.valueOf(this.zzdu);
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 39);
    stringBuilder.append("[PatternItem: type=");
    stringBuilder.append(i);
    stringBuilder.append(" length=");
    stringBuilder.append(str);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 2, this.type);
    SafeParcelWriter.writeFloatObject(paramParcel, 3, this.zzdu, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\PatternItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */