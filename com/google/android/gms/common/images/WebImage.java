package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "WebImageCreator")
public final class WebImage extends AbstractSafeParcelable {
  public static final Parcelable.Creator<WebImage> CREATOR = new WebImageCreator();
  
  @VersionField(id = 1)
  private final int zzal;
  
  @Field(getter = "getWidth", id = 3)
  private final int zzps;
  
  @Field(getter = "getHeight", id = 4)
  private final int zzpt;
  
  @Field(getter = "getUrl", id = 2)
  private final Uri zzpu;
  
  @Constructor
  WebImage(@Param(id = 1) int paramInt1, @Param(id = 2) Uri paramUri, @Param(id = 3) int paramInt2, @Param(id = 4) int paramInt3) {
    this.zzal = paramInt1;
    this.zzpu = paramUri;
    this.zzps = paramInt2;
    this.zzpt = paramInt3;
  }
  
  public WebImage(Uri paramUri) throws IllegalArgumentException {
    this(paramUri, 0, 0);
  }
  
  public WebImage(Uri paramUri, int paramInt1, int paramInt2) throws IllegalArgumentException {
    this(1, paramUri, paramInt1, paramInt2);
    if (paramUri == null)
      throw new IllegalArgumentException("url cannot be null"); 
    if (paramInt1 < 0 || paramInt2 < 0)
      throw new IllegalArgumentException("width and height must not be negative"); 
  }
  
  public WebImage(JSONObject paramJSONObject) throws IllegalArgumentException {
    this(zza(paramJSONObject), paramJSONObject.optInt("width", 0), paramJSONObject.optInt("height", 0));
  }
  
  private static Uri zza(JSONObject paramJSONObject) {
    if (paramJSONObject.has("url"))
      try {
        return Uri.parse(paramJSONObject.getString("url"));
      } catch (JSONException jSONException) {} 
    return null;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject != null) {
      if (!(paramObject instanceof WebImage))
        return false; 
      paramObject = paramObject;
      if (Objects.equal(this.zzpu, ((WebImage)paramObject).zzpu) && this.zzps == ((WebImage)paramObject).zzps && this.zzpt == ((WebImage)paramObject).zzpt)
        return true; 
    } 
    return false;
  }
  
  public final int getHeight() {
    return this.zzpt;
  }
  
  public final Uri getUrl() {
    return this.zzpu;
  }
  
  public final int getWidth() {
    return this.zzps;
  }
  
  public final int hashCode() {
    return Objects.hashCode(new Object[] { this.zzpu, Integer.valueOf(this.zzps), Integer.valueOf(this.zzpt) });
  }
  
  public final JSONObject toJson() {
    JSONObject jSONObject = new JSONObject();
    try {
      jSONObject.put("url", this.zzpu.toString());
      jSONObject.put("width", this.zzps);
      jSONObject.put("height", this.zzpt);
    } catch (JSONException jSONException) {}
    return jSONObject;
  }
  
  public final String toString() {
    return String.format(Locale.US, "Image %dx%d %s", new Object[] { Integer.valueOf(this.zzps), Integer.valueOf(this.zzpt), this.zzpu.toString() });
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzal);
    SafeParcelWriter.writeParcelable(paramParcel, 2, (Parcelable)getUrl(), paramInt, false);
    SafeParcelWriter.writeInt(paramParcel, 3, getWidth());
    SafeParcelWriter.writeInt(paramParcel, 4, getHeight());
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\images\WebImage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */