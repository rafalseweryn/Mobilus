package android.support.v4.media;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@TargetApi(21)
class AudioAttributesImplApi21 implements AudioAttributesImpl {
  private static final String TAG = "AudioAttributesCompat21";
  
  static Method sAudioAttributesToLegacyStreamType;
  
  AudioAttributes mAudioAttributes;
  
  int mLegacyStreamType = -1;
  
  AudioAttributesImplApi21() {}
  
  AudioAttributesImplApi21(AudioAttributes paramAudioAttributes) {
    this(paramAudioAttributes, -1);
  }
  
  AudioAttributesImplApi21(AudioAttributes paramAudioAttributes, int paramInt) {
    this.mAudioAttributes = paramAudioAttributes;
    this.mLegacyStreamType = paramInt;
  }
  
  public static AudioAttributesImpl fromBundle(Bundle paramBundle) {
    if (paramBundle == null)
      return null; 
    AudioAttributes audioAttributes = (AudioAttributes)paramBundle.getParcelable("android.support.v4.media.audio_attrs.FRAMEWORKS");
    return (audioAttributes == null) ? null : new AudioAttributesImplApi21(audioAttributes, paramBundle.getInt("android.support.v4.media.audio_attrs.LEGACY_STREAM_TYPE", -1));
  }
  
  static Method getAudioAttributesToLegacyStreamTypeMethod() {
    try {
      if (sAudioAttributesToLegacyStreamType == null)
        sAudioAttributesToLegacyStreamType = AudioAttributes.class.getMethod("toLegacyStreamType", new Class[] { AudioAttributes.class }); 
      return sAudioAttributesToLegacyStreamType;
    } catch (NoSuchMethodException noSuchMethodException) {
      return null;
    } 
  }
  
  public boolean equals(Object paramObject) {
    if (!(paramObject instanceof AudioAttributesImplApi21))
      return false; 
    paramObject = paramObject;
    return this.mAudioAttributes.equals(((AudioAttributesImplApi21)paramObject).mAudioAttributes);
  }
  
  public Object getAudioAttributes() {
    return this.mAudioAttributes;
  }
  
  public int getContentType() {
    return this.mAudioAttributes.getContentType();
  }
  
  public int getFlags() {
    return this.mAudioAttributes.getFlags();
  }
  
  public int getLegacyStreamType() {
    StringBuilder stringBuilder;
    if (this.mLegacyStreamType != -1)
      return this.mLegacyStreamType; 
    Method method = getAudioAttributesToLegacyStreamTypeMethod();
    if (method == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("No AudioAttributes#toLegacyStreamType() on API: ");
      stringBuilder.append(Build.VERSION.SDK_INT);
      Log.w("AudioAttributesCompat21", stringBuilder.toString());
      return -1;
    } 
    try {
      return ((Integer)stringBuilder.invoke(null, new Object[] { this.mAudioAttributes })).intValue();
    } catch (InvocationTargetException|IllegalAccessException invocationTargetException) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("getLegacyStreamType() failed on API: ");
      stringBuilder1.append(Build.VERSION.SDK_INT);
      Log.w("AudioAttributesCompat21", stringBuilder1.toString(), invocationTargetException);
      return -1;
    } 
  }
  
  public int getRawLegacyStreamType() {
    return this.mLegacyStreamType;
  }
  
  public int getUsage() {
    return this.mAudioAttributes.getUsage();
  }
  
  public int getVolumeControlStream() {
    return (Build.VERSION.SDK_INT >= 26) ? this.mAudioAttributes.getVolumeControlStream() : AudioAttributesCompat.toVolumeStreamType(true, getFlags(), getUsage());
  }
  
  public int hashCode() {
    return this.mAudioAttributes.hashCode();
  }
  
  @NonNull
  public Bundle toBundle() {
    Bundle bundle = new Bundle();
    bundle.putParcelable("android.support.v4.media.audio_attrs.FRAMEWORKS", (Parcelable)this.mAudioAttributes);
    if (this.mLegacyStreamType != -1)
      bundle.putInt("android.support.v4.media.audio_attrs.LEGACY_STREAM_TYPE", this.mLegacyStreamType); 
    return bundle;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("AudioAttributesCompat: audioattributes=");
    stringBuilder.append(this.mAudioAttributes);
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\media\AudioAttributesImplApi21.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */