package androidx.media;

import android.media.AudioAttributes;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.support.v4.media.AudioAttributesImplApi21;
import androidx.versionedparcelable.VersionedParcel;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public final class AudioAttributesImplApi21Parcelizer {
  public static AudioAttributesImplApi21 read(VersionedParcel paramVersionedParcel) {
    AudioAttributesImplApi21 audioAttributesImplApi21 = new AudioAttributesImplApi21();
    audioAttributesImplApi21.mAudioAttributes = (AudioAttributes)paramVersionedParcel.readParcelable((Parcelable)audioAttributesImplApi21.mAudioAttributes, 1);
    audioAttributesImplApi21.mLegacyStreamType = paramVersionedParcel.readInt(audioAttributesImplApi21.mLegacyStreamType, 2);
    return audioAttributesImplApi21;
  }
  
  public static void write(AudioAttributesImplApi21 paramAudioAttributesImplApi21, VersionedParcel paramVersionedParcel) {
    paramVersionedParcel.setSerializationFlags(false, false);
    paramVersionedParcel.writeParcelable((Parcelable)paramAudioAttributesImplApi21.mAudioAttributes, 1);
    paramVersionedParcel.writeInt(paramAudioAttributesImplApi21.mLegacyStreamType, 2);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\androidx\media\AudioAttributesImplApi21Parcelizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */