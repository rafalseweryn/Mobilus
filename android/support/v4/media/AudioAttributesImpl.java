package android.support.v4.media;

import android.os.Bundle;
import android.support.annotation.NonNull;
import androidx.versionedparcelable.VersionedParcelable;

interface AudioAttributesImpl extends VersionedParcelable {
  Object getAudioAttributes();
  
  int getContentType();
  
  int getFlags();
  
  int getLegacyStreamType();
  
  int getRawLegacyStreamType();
  
  int getUsage();
  
  int getVolumeControlStream();
  
  @NonNull
  Bundle toBundle();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\media\AudioAttributesImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */