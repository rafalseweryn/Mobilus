package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@Class(creator = "BitmapTeleporterCreator")
public class BitmapTeleporter extends AbstractSafeParcelable implements ReflectedParcelable {
  public static final Parcelable.Creator<BitmapTeleporter> CREATOR = new BitmapTeleporterCreator();
  
  @Field(id = 3)
  private final int zzac;
  
  @VersionField(id = 1)
  private final int zzal;
  
  @Field(id = 2)
  private ParcelFileDescriptor zznb;
  
  private Bitmap zznc;
  
  private boolean zznd;
  
  private File zzne;
  
  @Constructor
  BitmapTeleporter(@Param(id = 1) int paramInt1, @Param(id = 2) ParcelFileDescriptor paramParcelFileDescriptor, @Param(id = 3) int paramInt2) {
    this.zzal = paramInt1;
    this.zznb = paramParcelFileDescriptor;
    this.zzac = paramInt2;
    this.zznc = null;
    this.zznd = false;
  }
  
  public BitmapTeleporter(Bitmap paramBitmap) {
    this.zzal = 1;
    this.zznb = null;
    this.zzac = 0;
    this.zznc = paramBitmap;
    this.zznd = true;
  }
  
  private static void zza(Closeable paramCloseable) {
    try {
      paramCloseable.close();
      return;
    } catch (IOException iOException) {
      Log.w("BitmapTeleporter", "Could not close stream", iOException);
      return;
    } 
  }
  
  private final FileOutputStream zzcj() {
    if (this.zzne == null)
      throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel"); 
    try {
      File file = File.createTempFile("teleporter", ".tmp", this.zzne);
      try {
        FileOutputStream fileOutputStream = new FileOutputStream();
        this(file);
        this.zznb = ParcelFileDescriptor.open(file, 268435456);
        file.delete();
        return fileOutputStream;
      } catch (FileNotFoundException fileNotFoundException) {
        throw new IllegalStateException("Temporary file is somehow already deleted");
      } 
    } catch (IOException iOException) {
      throw new IllegalStateException("Could not create temporary file", iOException);
    } 
  }
  
  public Bitmap get() {
    if (!this.zznd) {
      DataInputStream dataInputStream = new DataInputStream((InputStream)new ParcelFileDescriptor.AutoCloseInputStream(this.zznb));
      try {
        byte[] arrayOfByte = new byte[dataInputStream.readInt()];
        int i = dataInputStream.readInt();
        int j = dataInputStream.readInt();
        Bitmap.Config config = Bitmap.Config.valueOf(dataInputStream.readUTF());
        dataInputStream.read(arrayOfByte);
        zza(dataInputStream);
        ByteBuffer byteBuffer = ByteBuffer.wrap(arrayOfByte);
        Bitmap bitmap = Bitmap.createBitmap(i, j, config);
        bitmap.copyPixelsFromBuffer(byteBuffer);
        this.zznc = bitmap;
        this.zznd = true;
      } catch (IOException iOException) {
        IllegalStateException illegalStateException = new IllegalStateException();
        this("Could not read from parcel file descriptor", iOException);
        throw illegalStateException;
      } finally {
        Exception exception;
      } 
    } 
    return this.zznc;
  }
  
  public void release() {
    if (!this.zznd)
      try {
        this.zznb.close();
        return;
      } catch (IOException iOException) {
        Log.w("BitmapTeleporter", "Could not close PFD", iOException);
      }  
  }
  
  public void setTempDir(File paramFile) {
    if (paramFile == null)
      throw new NullPointerException("Cannot set null temp directory"); 
    this.zzne = paramFile;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (this.zznb == null) {
      Bitmap bitmap = this.zznc;
      ByteBuffer byteBuffer = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap.getHeight());
      bitmap.copyPixelsToBuffer(byteBuffer);
      byte[] arrayOfByte = byteBuffer.array();
      DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(zzcj()));
      try {
        dataOutputStream.writeInt(arrayOfByte.length);
        dataOutputStream.writeInt(bitmap.getWidth());
        dataOutputStream.writeInt(bitmap.getHeight());
        dataOutputStream.writeUTF(bitmap.getConfig().toString());
        dataOutputStream.write(arrayOfByte);
        zza(dataOutputStream);
      } catch (IOException iOException) {
        IllegalStateException illegalStateException = new IllegalStateException();
        this("Could not write into unlinked file", iOException);
        throw illegalStateException;
      } finally {}
    } 
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzal);
    SafeParcelWriter.writeParcelable(paramParcel, 2, (Parcelable)this.zznb, paramInt | 0x1, false);
    SafeParcelWriter.writeInt(paramParcel, 3, this.zzac);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
    this.zznb = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\BitmapTeleporter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */