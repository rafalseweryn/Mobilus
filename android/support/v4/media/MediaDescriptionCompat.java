package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.text.TextUtils;

public final class MediaDescriptionCompat implements Parcelable {
  public static final long BT_FOLDER_TYPE_ALBUMS = 2L;
  
  public static final long BT_FOLDER_TYPE_ARTISTS = 3L;
  
  public static final long BT_FOLDER_TYPE_GENRES = 4L;
  
  public static final long BT_FOLDER_TYPE_MIXED = 0L;
  
  public static final long BT_FOLDER_TYPE_PLAYLISTS = 5L;
  
  public static final long BT_FOLDER_TYPE_TITLES = 1L;
  
  public static final long BT_FOLDER_TYPE_YEARS = 6L;
  
  public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new Parcelable.Creator<MediaDescriptionCompat>() {
      public MediaDescriptionCompat createFromParcel(Parcel param1Parcel) {
        return (Build.VERSION.SDK_INT < 21) ? new MediaDescriptionCompat(param1Parcel) : MediaDescriptionCompat.fromMediaDescription(MediaDescriptionCompatApi21.fromParcel(param1Parcel));
      }
      
      public MediaDescriptionCompat[] newArray(int param1Int) {
        return new MediaDescriptionCompat[param1Int];
      }
    };
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static final String DESCRIPTION_KEY_MEDIA_URI = "android.support.v4.media.description.MEDIA_URI";
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static final String DESCRIPTION_KEY_NULL_BUNDLE_FLAG = "android.support.v4.media.description.NULL_BUNDLE_FLAG";
  
  public static final String EXTRA_BT_FOLDER_TYPE = "android.media.extra.BT_FOLDER_TYPE";
  
  public static final String EXTRA_DOWNLOAD_STATUS = "android.media.extra.DOWNLOAD_STATUS";
  
  public static final long STATUS_DOWNLOADED = 2L;
  
  public static final long STATUS_DOWNLOADING = 1L;
  
  public static final long STATUS_NOT_DOWNLOADED = 0L;
  
  private final CharSequence mDescription;
  
  private Object mDescriptionObj;
  
  private final Bundle mExtras;
  
  private final Bitmap mIcon;
  
  private final Uri mIconUri;
  
  private final String mMediaId;
  
  private final Uri mMediaUri;
  
  private final CharSequence mSubtitle;
  
  private final CharSequence mTitle;
  
  MediaDescriptionCompat(Parcel paramParcel) {
    this.mMediaId = paramParcel.readString();
    this.mTitle = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
    this.mSubtitle = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
    this.mDescription = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
    ClassLoader classLoader = getClass().getClassLoader();
    this.mIcon = (Bitmap)paramParcel.readParcelable(classLoader);
    this.mIconUri = (Uri)paramParcel.readParcelable(classLoader);
    this.mExtras = paramParcel.readBundle(classLoader);
    this.mMediaUri = (Uri)paramParcel.readParcelable(classLoader);
  }
  
  MediaDescriptionCompat(String paramString, CharSequence paramCharSequence1, CharSequence paramCharSequence2, CharSequence paramCharSequence3, Bitmap paramBitmap, Uri paramUri1, Bundle paramBundle, Uri paramUri2) {
    this.mMediaId = paramString;
    this.mTitle = paramCharSequence1;
    this.mSubtitle = paramCharSequence2;
    this.mDescription = paramCharSequence3;
    this.mIcon = paramBitmap;
    this.mIconUri = paramUri1;
    this.mExtras = paramBundle;
    this.mMediaUri = paramUri2;
  }
  
  public static MediaDescriptionCompat fromMediaDescription(Object paramObject) {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: ifnull -> 198
    //   6: getstatic android/os/Build$VERSION.SDK_INT : I
    //   9: bipush #21
    //   11: if_icmplt -> 198
    //   14: new android/support/v4/media/MediaDescriptionCompat$Builder
    //   17: dup
    //   18: invokespecial <init> : ()V
    //   21: astore_2
    //   22: aload_2
    //   23: aload_0
    //   24: invokestatic getMediaId : (Ljava/lang/Object;)Ljava/lang/String;
    //   27: invokevirtual setMediaId : (Ljava/lang/String;)Landroid/support/v4/media/MediaDescriptionCompat$Builder;
    //   30: pop
    //   31: aload_2
    //   32: aload_0
    //   33: invokestatic getTitle : (Ljava/lang/Object;)Ljava/lang/CharSequence;
    //   36: invokevirtual setTitle : (Ljava/lang/CharSequence;)Landroid/support/v4/media/MediaDescriptionCompat$Builder;
    //   39: pop
    //   40: aload_2
    //   41: aload_0
    //   42: invokestatic getSubtitle : (Ljava/lang/Object;)Ljava/lang/CharSequence;
    //   45: invokevirtual setSubtitle : (Ljava/lang/CharSequence;)Landroid/support/v4/media/MediaDescriptionCompat$Builder;
    //   48: pop
    //   49: aload_2
    //   50: aload_0
    //   51: invokestatic getDescription : (Ljava/lang/Object;)Ljava/lang/CharSequence;
    //   54: invokevirtual setDescription : (Ljava/lang/CharSequence;)Landroid/support/v4/media/MediaDescriptionCompat$Builder;
    //   57: pop
    //   58: aload_2
    //   59: aload_0
    //   60: invokestatic getIconBitmap : (Ljava/lang/Object;)Landroid/graphics/Bitmap;
    //   63: invokevirtual setIconBitmap : (Landroid/graphics/Bitmap;)Landroid/support/v4/media/MediaDescriptionCompat$Builder;
    //   66: pop
    //   67: aload_2
    //   68: aload_0
    //   69: invokestatic getIconUri : (Ljava/lang/Object;)Landroid/net/Uri;
    //   72: invokevirtual setIconUri : (Landroid/net/Uri;)Landroid/support/v4/media/MediaDescriptionCompat$Builder;
    //   75: pop
    //   76: aload_0
    //   77: invokestatic getExtras : (Ljava/lang/Object;)Landroid/os/Bundle;
    //   80: astore_3
    //   81: aload_3
    //   82: ifnull -> 103
    //   85: aload_3
    //   86: invokestatic ensureClassLoader : (Landroid/os/Bundle;)V
    //   89: aload_3
    //   90: ldc 'android.support.v4.media.description.MEDIA_URI'
    //   92: invokevirtual getParcelable : (Ljava/lang/String;)Landroid/os/Parcelable;
    //   95: checkcast android/net/Uri
    //   98: astore #4
    //   100: goto -> 106
    //   103: aconst_null
    //   104: astore #4
    //   106: aload #4
    //   108: ifnull -> 143
    //   111: aload_3
    //   112: ldc 'android.support.v4.media.description.NULL_BUNDLE_FLAG'
    //   114: invokevirtual containsKey : (Ljava/lang/String;)Z
    //   117: ifeq -> 131
    //   120: aload_3
    //   121: invokevirtual size : ()I
    //   124: iconst_2
    //   125: if_icmpne -> 131
    //   128: goto -> 145
    //   131: aload_3
    //   132: ldc 'android.support.v4.media.description.MEDIA_URI'
    //   134: invokevirtual remove : (Ljava/lang/String;)V
    //   137: aload_3
    //   138: ldc 'android.support.v4.media.description.NULL_BUNDLE_FLAG'
    //   140: invokevirtual remove : (Ljava/lang/String;)V
    //   143: aload_3
    //   144: astore_1
    //   145: aload_2
    //   146: aload_1
    //   147: invokevirtual setExtras : (Landroid/os/Bundle;)Landroid/support/v4/media/MediaDescriptionCompat$Builder;
    //   150: pop
    //   151: aload #4
    //   153: ifnull -> 166
    //   156: aload_2
    //   157: aload #4
    //   159: invokevirtual setMediaUri : (Landroid/net/Uri;)Landroid/support/v4/media/MediaDescriptionCompat$Builder;
    //   162: pop
    //   163: goto -> 183
    //   166: getstatic android/os/Build$VERSION.SDK_INT : I
    //   169: bipush #23
    //   171: if_icmplt -> 183
    //   174: aload_2
    //   175: aload_0
    //   176: invokestatic getMediaUri : (Ljava/lang/Object;)Landroid/net/Uri;
    //   179: invokevirtual setMediaUri : (Landroid/net/Uri;)Landroid/support/v4/media/MediaDescriptionCompat$Builder;
    //   182: pop
    //   183: aload_2
    //   184: invokevirtual build : ()Landroid/support/v4/media/MediaDescriptionCompat;
    //   187: astore #4
    //   189: aload #4
    //   191: aload_0
    //   192: putfield mDescriptionObj : Ljava/lang/Object;
    //   195: aload #4
    //   197: areturn
    //   198: aconst_null
    //   199: areturn
  }
  
  public int describeContents() {
    return 0;
  }
  
  @Nullable
  public CharSequence getDescription() {
    return this.mDescription;
  }
  
  @Nullable
  public Bundle getExtras() {
    return this.mExtras;
  }
  
  @Nullable
  public Bitmap getIconBitmap() {
    return this.mIcon;
  }
  
  @Nullable
  public Uri getIconUri() {
    return this.mIconUri;
  }
  
  public Object getMediaDescription() {
    if (this.mDescriptionObj != null || Build.VERSION.SDK_INT < 21)
      return this.mDescriptionObj; 
    Object object = MediaDescriptionCompatApi21.Builder.newInstance();
    MediaDescriptionCompatApi21.Builder.setMediaId(object, this.mMediaId);
    MediaDescriptionCompatApi21.Builder.setTitle(object, this.mTitle);
    MediaDescriptionCompatApi21.Builder.setSubtitle(object, this.mSubtitle);
    MediaDescriptionCompatApi21.Builder.setDescription(object, this.mDescription);
    MediaDescriptionCompatApi21.Builder.setIconBitmap(object, this.mIcon);
    MediaDescriptionCompatApi21.Builder.setIconUri(object, this.mIconUri);
    Bundle bundle1 = this.mExtras;
    Bundle bundle2 = bundle1;
    if (Build.VERSION.SDK_INT < 23) {
      bundle2 = bundle1;
      if (this.mMediaUri != null) {
        bundle2 = bundle1;
        if (bundle1 == null) {
          bundle2 = new Bundle();
          bundle2.putBoolean("android.support.v4.media.description.NULL_BUNDLE_FLAG", true);
        } 
        bundle2.putParcelable("android.support.v4.media.description.MEDIA_URI", (Parcelable)this.mMediaUri);
      } 
    } 
    MediaDescriptionCompatApi21.Builder.setExtras(object, bundle2);
    if (Build.VERSION.SDK_INT >= 23)
      MediaDescriptionCompatApi23.Builder.setMediaUri(object, this.mMediaUri); 
    this.mDescriptionObj = MediaDescriptionCompatApi21.Builder.build(object);
    return this.mDescriptionObj;
  }
  
  @Nullable
  public String getMediaId() {
    return this.mMediaId;
  }
  
  @Nullable
  public Uri getMediaUri() {
    return this.mMediaUri;
  }
  
  @Nullable
  public CharSequence getSubtitle() {
    return this.mSubtitle;
  }
  
  @Nullable
  public CharSequence getTitle() {
    return this.mTitle;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.mTitle);
    stringBuilder.append(", ");
    stringBuilder.append(this.mSubtitle);
    stringBuilder.append(", ");
    stringBuilder.append(this.mDescription);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (Build.VERSION.SDK_INT < 21) {
      paramParcel.writeString(this.mMediaId);
      TextUtils.writeToParcel(this.mTitle, paramParcel, paramInt);
      TextUtils.writeToParcel(this.mSubtitle, paramParcel, paramInt);
      TextUtils.writeToParcel(this.mDescription, paramParcel, paramInt);
      paramParcel.writeParcelable((Parcelable)this.mIcon, paramInt);
      paramParcel.writeParcelable((Parcelable)this.mIconUri, paramInt);
      paramParcel.writeBundle(this.mExtras);
      paramParcel.writeParcelable((Parcelable)this.mMediaUri, paramInt);
    } else {
      MediaDescriptionCompatApi21.writeToParcel(getMediaDescription(), paramParcel, paramInt);
    } 
  }
  
  public static final class Builder {
    private CharSequence mDescription;
    
    private Bundle mExtras;
    
    private Bitmap mIcon;
    
    private Uri mIconUri;
    
    private String mMediaId;
    
    private Uri mMediaUri;
    
    private CharSequence mSubtitle;
    
    private CharSequence mTitle;
    
    public MediaDescriptionCompat build() {
      return new MediaDescriptionCompat(this.mMediaId, this.mTitle, this.mSubtitle, this.mDescription, this.mIcon, this.mIconUri, this.mExtras, this.mMediaUri);
    }
    
    public Builder setDescription(@Nullable CharSequence param1CharSequence) {
      this.mDescription = param1CharSequence;
      return this;
    }
    
    public Builder setExtras(@Nullable Bundle param1Bundle) {
      this.mExtras = param1Bundle;
      return this;
    }
    
    public Builder setIconBitmap(@Nullable Bitmap param1Bitmap) {
      this.mIcon = param1Bitmap;
      return this;
    }
    
    public Builder setIconUri(@Nullable Uri param1Uri) {
      this.mIconUri = param1Uri;
      return this;
    }
    
    public Builder setMediaId(@Nullable String param1String) {
      this.mMediaId = param1String;
      return this;
    }
    
    public Builder setMediaUri(@Nullable Uri param1Uri) {
      this.mMediaUri = param1Uri;
      return this;
    }
    
    public Builder setSubtitle(@Nullable CharSequence param1CharSequence) {
      this.mSubtitle = param1CharSequence;
      return this;
    }
    
    public Builder setTitle(@Nullable CharSequence param1CharSequence) {
      this.mTitle = param1CharSequence;
      return this;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\media\MediaDescriptionCompat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */