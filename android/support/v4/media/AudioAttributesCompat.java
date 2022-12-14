package android.support.v4.media;

import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.versionedparcelable.VersionedParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AudioAttributesCompat implements VersionedParcelable {
  static final String AUDIO_ATTRIBUTES_CONTENT_TYPE = "android.support.v4.media.audio_attrs.CONTENT_TYPE";
  
  static final String AUDIO_ATTRIBUTES_FLAGS = "android.support.v4.media.audio_attrs.FLAGS";
  
  static final String AUDIO_ATTRIBUTES_FRAMEWORKS = "android.support.v4.media.audio_attrs.FRAMEWORKS";
  
  static final String AUDIO_ATTRIBUTES_LEGACY_STREAM_TYPE = "android.support.v4.media.audio_attrs.LEGACY_STREAM_TYPE";
  
  static final String AUDIO_ATTRIBUTES_USAGE = "android.support.v4.media.audio_attrs.USAGE";
  
  public static final int CONTENT_TYPE_MOVIE = 3;
  
  public static final int CONTENT_TYPE_MUSIC = 2;
  
  public static final int CONTENT_TYPE_SONIFICATION = 4;
  
  public static final int CONTENT_TYPE_SPEECH = 1;
  
  public static final int CONTENT_TYPE_UNKNOWN = 0;
  
  static final int FLAG_ALL = 1023;
  
  static final int FLAG_ALL_PUBLIC = 273;
  
  public static final int FLAG_AUDIBILITY_ENFORCED = 1;
  
  static final int FLAG_BEACON = 8;
  
  static final int FLAG_BYPASS_INTERRUPTION_POLICY = 64;
  
  static final int FLAG_BYPASS_MUTE = 128;
  
  static final int FLAG_DEEP_BUFFER = 512;
  
  public static final int FLAG_HW_AV_SYNC = 16;
  
  static final int FLAG_HW_HOTWORD = 32;
  
  static final int FLAG_LOW_LATENCY = 256;
  
  static final int FLAG_SCO = 4;
  
  static final int FLAG_SECURE = 2;
  
  static final int INVALID_STREAM_TYPE = -1;
  
  private static final int[] SDK_USAGES;
  
  private static final int SUPPRESSIBLE_CALL = 2;
  
  private static final int SUPPRESSIBLE_NOTIFICATION = 1;
  
  private static final SparseIntArray SUPPRESSIBLE_USAGES = new SparseIntArray();
  
  private static final String TAG = "AudioAttributesCompat";
  
  public static final int USAGE_ALARM = 4;
  
  public static final int USAGE_ASSISTANCE_ACCESSIBILITY = 11;
  
  public static final int USAGE_ASSISTANCE_NAVIGATION_GUIDANCE = 12;
  
  public static final int USAGE_ASSISTANCE_SONIFICATION = 13;
  
  public static final int USAGE_ASSISTANT = 16;
  
  public static final int USAGE_GAME = 14;
  
  public static final int USAGE_MEDIA = 1;
  
  public static final int USAGE_NOTIFICATION = 5;
  
  public static final int USAGE_NOTIFICATION_COMMUNICATION_DELAYED = 9;
  
  public static final int USAGE_NOTIFICATION_COMMUNICATION_INSTANT = 8;
  
  public static final int USAGE_NOTIFICATION_COMMUNICATION_REQUEST = 7;
  
  public static final int USAGE_NOTIFICATION_EVENT = 10;
  
  public static final int USAGE_NOTIFICATION_RINGTONE = 6;
  
  public static final int USAGE_UNKNOWN = 0;
  
  private static final int USAGE_VIRTUAL_SOURCE = 15;
  
  public static final int USAGE_VOICE_COMMUNICATION = 2;
  
  public static final int USAGE_VOICE_COMMUNICATION_SIGNALLING = 3;
  
  static boolean sForceLegacyBehavior;
  
  AudioAttributesImpl mImpl;
  
  static {
    SUPPRESSIBLE_USAGES.put(5, 1);
    SUPPRESSIBLE_USAGES.put(6, 2);
    SUPPRESSIBLE_USAGES.put(7, 2);
    SUPPRESSIBLE_USAGES.put(8, 1);
    SUPPRESSIBLE_USAGES.put(9, 1);
    SUPPRESSIBLE_USAGES.put(10, 1);
    SDK_USAGES = new int[] { 
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
        10, 11, 12, 13, 14, 16 };
  }
  
  AudioAttributesCompat() {}
  
  AudioAttributesCompat(AudioAttributesImpl paramAudioAttributesImpl) {
    this.mImpl = paramAudioAttributesImpl;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static AudioAttributesCompat fromBundle(Bundle paramBundle) {
    AudioAttributesImpl audioAttributesImpl;
    AudioAttributesCompat audioAttributesCompat;
    if (Build.VERSION.SDK_INT >= 21) {
      audioAttributesImpl = AudioAttributesImplApi21.fromBundle(paramBundle);
    } else {
      audioAttributesImpl = AudioAttributesImplBase.fromBundle((Bundle)audioAttributesImpl);
    } 
    if (audioAttributesImpl == null) {
      audioAttributesImpl = null;
    } else {
      audioAttributesCompat = new AudioAttributesCompat(audioAttributesImpl);
    } 
    return audioAttributesCompat;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static void setForceLegacyBehavior(boolean paramBoolean) {
    sForceLegacyBehavior = paramBoolean;
  }
  
  static int toVolumeStreamType(boolean paramBoolean, int paramInt1, int paramInt2) {
    boolean bool1 = true;
    if ((paramInt1 & 0x1) == 1) {
      if (paramBoolean) {
        paramInt1 = bool1;
      } else {
        paramInt1 = 7;
      } 
      return paramInt1;
    } 
    bool1 = false;
    boolean bool2 = false;
    if ((paramInt1 & 0x4) == 4) {
      if (paramBoolean) {
        paramInt1 = bool2;
      } else {
        paramInt1 = 6;
      } 
      return paramInt1;
    } 
    paramInt1 = 3;
    switch (paramInt2) {
      default:
        if (paramBoolean) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Unknown usage value ");
          stringBuilder.append(paramInt2);
          stringBuilder.append(" in audio attributes");
          throw new IllegalArgumentException(stringBuilder.toString());
        } 
        return 3;
      case 13:
        return 1;
      case 11:
        return 10;
      case 6:
        return 2;
      case 5:
      case 7:
      case 8:
      case 9:
      case 10:
        return 5;
      case 4:
        return 4;
      case 3:
        if (paramBoolean) {
          paramInt1 = bool1;
        } else {
          paramInt1 = 8;
        } 
        return paramInt1;
      case 2:
        return 0;
      case 1:
      case 12:
      case 14:
      case 16:
        return 3;
      case 0:
        break;
    } 
    if (paramBoolean)
      paramInt1 = Integer.MIN_VALUE; 
    return paramInt1;
  }
  
  static int toVolumeStreamType(boolean paramBoolean, AudioAttributesCompat paramAudioAttributesCompat) {
    return toVolumeStreamType(paramBoolean, paramAudioAttributesCompat.getFlags(), paramAudioAttributesCompat.getUsage());
  }
  
  static int usageForStreamType(int paramInt) {
    switch (paramInt) {
      default:
        return 0;
      case 10:
        return 11;
      case 8:
        return 3;
      case 6:
        return 2;
      case 5:
        return 5;
      case 4:
        return 4;
      case 3:
        return 1;
      case 2:
        return 6;
      case 1:
      case 7:
        return 13;
      case 0:
        break;
    } 
    return 2;
  }
  
  static String usageToString(int paramInt) {
    StringBuilder stringBuilder;
    switch (paramInt) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("unknown usage ");
        stringBuilder.append(paramInt);
        return stringBuilder.toString();
      case 16:
        return "USAGE_ASSISTANT";
      case 14:
        return "USAGE_GAME";
      case 13:
        return "USAGE_ASSISTANCE_SONIFICATION";
      case 12:
        return "USAGE_ASSISTANCE_NAVIGATION_GUIDANCE";
      case 11:
        return "USAGE_ASSISTANCE_ACCESSIBILITY";
      case 10:
        return "USAGE_NOTIFICATION_EVENT";
      case 9:
        return "USAGE_NOTIFICATION_COMMUNICATION_DELAYED";
      case 8:
        return "USAGE_NOTIFICATION_COMMUNICATION_INSTANT";
      case 7:
        return "USAGE_NOTIFICATION_COMMUNICATION_REQUEST";
      case 6:
        return "USAGE_NOTIFICATION_RINGTONE";
      case 5:
        return "USAGE_NOTIFICATION";
      case 4:
        return "USAGE_ALARM";
      case 3:
        return "USAGE_VOICE_COMMUNICATION_SIGNALLING";
      case 2:
        return "USAGE_VOICE_COMMUNICATION";
      case 1:
        return "USAGE_MEDIA";
      case 0:
        break;
    } 
    return "USAGE_UNKNOWN";
  }
  
  @Nullable
  public static AudioAttributesCompat wrap(@NonNull Object paramObject) {
    if (Build.VERSION.SDK_INT >= 21 && !sForceLegacyBehavior) {
      AudioAttributesImplApi21 audioAttributesImplApi21 = new AudioAttributesImplApi21((AudioAttributes)paramObject);
      paramObject = new AudioAttributesCompat();
      ((AudioAttributesCompat)paramObject).mImpl = audioAttributesImplApi21;
      return (AudioAttributesCompat)paramObject;
    } 
    return null;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof AudioAttributesCompat;
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    if (this.mImpl == null) {
      if (((AudioAttributesCompat)paramObject).mImpl == null)
        bool1 = true; 
      return bool1;
    } 
    return this.mImpl.equals(((AudioAttributesCompat)paramObject).mImpl);
  }
  
  public int getContentType() {
    return this.mImpl.getContentType();
  }
  
  public int getFlags() {
    return this.mImpl.getFlags();
  }
  
  public int getLegacyStreamType() {
    return this.mImpl.getLegacyStreamType();
  }
  
  int getRawLegacyStreamType() {
    return this.mImpl.getRawLegacyStreamType();
  }
  
  public int getUsage() {
    return this.mImpl.getUsage();
  }
  
  public int getVolumeControlStream() {
    return this.mImpl.getVolumeControlStream();
  }
  
  public int hashCode() {
    return this.mImpl.hashCode();
  }
  
  @NonNull
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public Bundle toBundle() {
    return this.mImpl.toBundle();
  }
  
  public String toString() {
    return this.mImpl.toString();
  }
  
  @Nullable
  public Object unwrap() {
    return this.mImpl.getAudioAttributes();
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface AttributeContentType {}
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface AttributeUsage {}
  
  static abstract class AudioManagerHidden {
    public static final int STREAM_ACCESSIBILITY = 10;
    
    public static final int STREAM_BLUETOOTH_SCO = 6;
    
    public static final int STREAM_SYSTEM_ENFORCED = 7;
    
    public static final int STREAM_TTS = 9;
  }
  
  public static class Builder {
    private int mContentType = 0;
    
    private int mFlags = 0;
    
    private int mLegacyStream = -1;
    
    private int mUsage = 0;
    
    public Builder() {}
    
    public Builder(AudioAttributesCompat param1AudioAttributesCompat) {
      this.mUsage = param1AudioAttributesCompat.getUsage();
      this.mContentType = param1AudioAttributesCompat.getContentType();
      this.mFlags = param1AudioAttributesCompat.getFlags();
      this.mLegacyStream = param1AudioAttributesCompat.getRawLegacyStreamType();
    }
    
    public AudioAttributesCompat build() {
      AudioAttributesImplBase audioAttributesImplBase;
      if (!AudioAttributesCompat.sForceLegacyBehavior && Build.VERSION.SDK_INT >= 21) {
        AudioAttributes.Builder builder = (new AudioAttributes.Builder()).setContentType(this.mContentType).setFlags(this.mFlags).setUsage(this.mUsage);
        if (this.mLegacyStream != -1)
          builder.setLegacyStreamType(this.mLegacyStream); 
        AudioAttributesImplApi21 audioAttributesImplApi21 = new AudioAttributesImplApi21(builder.build(), this.mLegacyStream);
      } else {
        audioAttributesImplBase = new AudioAttributesImplBase(this.mContentType, this.mFlags, this.mUsage, this.mLegacyStream);
      } 
      return new AudioAttributesCompat(audioAttributesImplBase);
    }
    
    public Builder setContentType(int param1Int) {
      switch (param1Int) {
        default:
          this.mUsage = 0;
          return this;
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
          break;
      } 
      this.mContentType = param1Int;
      return this;
    }
    
    public Builder setFlags(int param1Int) {
      this.mFlags = param1Int & 0x3FF | this.mFlags;
      return this;
    }
    
    Builder setInternalLegacyStreamType(int param1Int) {
      StringBuilder stringBuilder;
      switch (param1Int) {
        default:
          stringBuilder = new StringBuilder();
          stringBuilder.append("Invalid stream type ");
          stringBuilder.append(param1Int);
          stringBuilder.append(" for AudioAttributesCompat");
          Log.e("AudioAttributesCompat", stringBuilder.toString());
          this.mUsage = AudioAttributesCompat.usageForStreamType(param1Int);
          return this;
        case 10:
          this.mContentType = 1;
          this.mUsage = AudioAttributesCompat.usageForStreamType(param1Int);
          return this;
        case 9:
          this.mContentType = 4;
          this.mUsage = AudioAttributesCompat.usageForStreamType(param1Int);
          return this;
        case 8:
          this.mContentType = 4;
          this.mUsage = AudioAttributesCompat.usageForStreamType(param1Int);
          return this;
        case 7:
          this.mFlags = 0x1 | this.mFlags;
        case 6:
          this.mContentType = 1;
          this.mFlags |= 0x4;
          this.mUsage = AudioAttributesCompat.usageForStreamType(param1Int);
          return this;
        case 5:
          this.mContentType = 4;
          this.mUsage = AudioAttributesCompat.usageForStreamType(param1Int);
          return this;
        case 4:
          this.mContentType = 4;
          this.mUsage = AudioAttributesCompat.usageForStreamType(param1Int);
          return this;
        case 3:
          this.mContentType = 2;
          this.mUsage = AudioAttributesCompat.usageForStreamType(param1Int);
          return this;
        case 2:
          this.mContentType = 4;
          this.mUsage = AudioAttributesCompat.usageForStreamType(param1Int);
          return this;
        case 1:
          this.mContentType = 4;
          this.mUsage = AudioAttributesCompat.usageForStreamType(param1Int);
          return this;
        case 0:
          break;
      } 
      this.mContentType = 1;
      this.mUsage = AudioAttributesCompat.usageForStreamType(param1Int);
      return this;
    }
    
    public Builder setLegacyStreamType(int param1Int) {
      if (param1Int == 10)
        throw new IllegalArgumentException("STREAM_ACCESSIBILITY is not a legacy stream type that was used for audio playback"); 
      this.mLegacyStream = param1Int;
      return (Build.VERSION.SDK_INT >= 21) ? setInternalLegacyStreamType(param1Int) : this;
    }
    
    public Builder setUsage(int param1Int) {
      switch (param1Int) {
        default:
          this.mUsage = 0;
          return this;
        case 16:
          if (!AudioAttributesCompat.sForceLegacyBehavior && Build.VERSION.SDK_INT > 25) {
            this.mUsage = param1Int;
          } else {
            this.mUsage = 12;
          } 
          return this;
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
        case 10:
        case 11:
        case 12:
        case 13:
        case 14:
        case 15:
          break;
      } 
      this.mUsage = param1Int;
      return this;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\media\AudioAttributesCompat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */