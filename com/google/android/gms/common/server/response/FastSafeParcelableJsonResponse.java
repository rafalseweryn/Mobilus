package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Iterator;

public abstract class FastSafeParcelableJsonResponse extends FastJsonResponse implements SafeParcelable {
  public final int describeContents() {
    return 0;
  }
  
  public boolean equals(FastJsonResponse.Field<?, ?> paramObject) {
    if (this == paramObject)
      return true; 
    if (!getClass().isInstance(paramObject))
      return false; 
    FastJsonResponse fastJsonResponse = (FastJsonResponse)paramObject;
    for (FastJsonResponse.Field<?, ?> paramObject : getFieldMappings().values()) {
      if (isFieldSet(paramObject)) {
        if (!fastJsonResponse.isFieldSet(paramObject) || !getFieldValue(paramObject).equals(fastJsonResponse.getFieldValue(paramObject)))
          return false; 
        continue;
      } 
      if (fastJsonResponse.isFieldSet(paramObject))
        return false; 
    } 
    return true;
  }
  
  @VisibleForTesting
  public Object getValueObject(String paramString) {
    return null;
  }
  
  public int hashCode() {
    Iterator<FastJsonResponse.Field> iterator = getFieldMappings().values().iterator();
    int i = 0;
    while (iterator.hasNext()) {
      FastJsonResponse.Field field = iterator.next();
      if (isFieldSet(field))
        i = i * 31 + getFieldValue(field).hashCode(); 
    } 
    return i;
  }
  
  @VisibleForTesting
  public boolean isPrimitiveFieldSet(String paramString) {
    return false;
  }
  
  public byte[] toByteArray() {
    Parcel parcel = Parcel.obtain();
    writeToParcel(parcel, 0);
    byte[] arrayOfByte = parcel.marshall();
    parcel.recycle();
    return arrayOfByte;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\server\response\FastSafeParcelableJsonResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */