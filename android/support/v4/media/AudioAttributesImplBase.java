package android.support.v4.media;

import android.os.Bundle;
import android.support.annotation.NonNull;
import java.util.Arrays;

class AudioAttributesImplBase implements AudioAttributesImpl {
  int mContentType = 0;
  
  int mFlags = 0;
  
  int mLegacyStream = -1;
  
  int mUsage = 0;
  
  AudioAttributesImplBase() {}
  
  AudioAttributesImplBase(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.mContentType = paramInt1;
    this.mFlags = paramInt2;
    this.mUsage = paramInt3;
    this.mLegacyStream = paramInt4;
  }
  
  public static AudioAttributesImpl fromBundle(Bundle paramBundle) {
    if (paramBundle == null)
      return null; 
    int i = paramBundle.getInt("android.support.v4.media.audio_attrs.USAGE", 0);
    return new AudioAttributesImplBase(paramBundle.getInt("android.support.v4.media.audio_attrs.CONTENT_TYPE", 0), paramBundle.getInt("android.support.v4.media.audio_attrs.FLAGS", 0), i, paramBundle.getInt("android.support.v4.media.audio_attrs.LEGACY_STREAM_TYPE", -1));
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof AudioAttributesImplBase;
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    bool = bool1;
    if (this.mContentType == paramObject.getContentType()) {
      bool = bool1;
      if (this.mFlags == paramObject.getFlags()) {
        bool = bool1;
        if (this.mUsage == paramObject.getUsage()) {
          bool = bool1;
          if (this.mLegacyStream == ((AudioAttributesImplBase)paramObject).mLegacyStream)
            bool = true; 
        } 
      } 
    } 
    return bool;
  }
  
  public Object getAudioAttributes() {
    return null;
  }
  
  public int getContentType() {
    return this.mContentType;
  }
  
  public int getFlags() {
    int k;
    int i = this.mFlags;
    int j = getLegacyStreamType();
    if (j == 6) {
      k = i | 0x4;
    } else {
      k = i;
      if (j == 7)
        k = i | 0x1; 
    } 
    return k & 0x111;
  }
  
  public int getLegacyStreamType() {
    return (this.mLegacyStream != -1) ? this.mLegacyStream : AudioAttributesCompat.toVolumeStreamType(false, this.mFlags, this.mUsage);
  }
  
  public int getRawLegacyStreamType() {
    return this.mLegacyStream;
  }
  
  public int getUsage() {
    return this.mUsage;
  }
  
  public int getVolumeControlStream() {
    return AudioAttributesCompat.toVolumeStreamType(true, this.mFlags, this.mUsage);
  }
  
  public int hashCode() {
    return Arrays.hashCode(new Object[] { Integer.valueOf(this.mContentType), Integer.valueOf(this.mFlags), Integer.valueOf(this.mUsage), Integer.valueOf(this.mLegacyStream) });
  }
  
  @NonNull
  public Bundle toBundle() {
    Bundle bundle = new Bundle();
    bundle.putInt("android.support.v4.media.audio_attrs.USAGE", this.mUsage);
    bundle.putInt("android.support.v4.media.audio_attrs.CONTENT_TYPE", this.mContentType);
    bundle.putInt("android.support.v4.media.audio_attrs.FLAGS", this.mFlags);
    if (this.mLegacyStream != -1)
      bundle.putInt("android.support.v4.media.audio_attrs.LEGACY_STREAM_TYPE", this.mLegacyStream); 
    return bundle;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("AudioAttributesCompat:");
    if (this.mLegacyStream != -1) {
      stringBuilder.append(" stream=");
      stringBuilder.append(this.mLegacyStream);
      stringBuilder.append(" derived");
    } 
    stringBuilder.append(" usage=");
    stringBuilder.append(AudioAttributesCompat.usageToString(this.mUsage));
    stringBuilder.append(" content=");
    stringBuilder.append(this.mContentType);
    stringBuilder.append(" flags=0x");
    stringBuilder.append(Integer.toHexString(this.mFlags).toUpperCase());
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\media\AudioAttributesImplBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */