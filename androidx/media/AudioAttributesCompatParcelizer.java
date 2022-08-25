package androidx.media;

import android.support.annotation.RestrictTo;
import android.support.v4.media.AudioAttributesCompat;
import android.support.v4.media.AudioAttributesImpl;
import androidx.versionedparcelable.VersionedParcel;
import androidx.versionedparcelable.VersionedParcelable;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public final class AudioAttributesCompatParcelizer {
  public static AudioAttributesCompat read(VersionedParcel paramVersionedParcel) {
    AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
    audioAttributesCompat.mImpl = (AudioAttributesImpl)paramVersionedParcel.readVersionedParcelable((VersionedParcelable)audioAttributesCompat.mImpl, 1);
    return audioAttributesCompat;
  }
  
  public static void write(AudioAttributesCompat paramAudioAttributesCompat, VersionedParcel paramVersionedParcel) {
    paramVersionedParcel.setSerializationFlags(false, false);
    paramVersionedParcel.writeVersionedParcelable((VersionedParcelable)paramAudioAttributesCompat.mImpl, 1);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\androidx\media\AudioAttributesCompatParcelizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */