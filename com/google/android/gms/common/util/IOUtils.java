package com.google.android.gms.common.util;

import android.os.ParcelFileDescriptor;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.Arrays;
import javax.annotation.Nullable;

public final class IOUtils {
  public static void close(@Nullable Closeable paramCloseable, String paramString1, String paramString2) {
    if (paramCloseable != null)
      try {
        paramCloseable.close();
        return;
      } catch (IOException iOException) {
        Log.d(paramString1, paramString2, iOException);
      }  
  }
  
  public static void closeQuietly(@Nullable ParcelFileDescriptor paramParcelFileDescriptor) {
    if (paramParcelFileDescriptor != null)
      try {
        paramParcelFileDescriptor.close();
      } catch (IOException iOException) {} 
  }
  
  public static void closeQuietly(@Nullable Closeable paramCloseable) {
    if (paramCloseable != null)
      try {
        paramCloseable.close();
      } catch (IOException iOException) {} 
  }
  
  public static void closeQuietly(@Nullable ServerSocket paramServerSocket) {
    if (paramServerSocket != null)
      try {
        paramServerSocket.close();
      } catch (IOException iOException) {} 
  }
  
  public static void closeQuietly(@Nullable Socket paramSocket) {
    if (paramSocket != null)
      try {
        paramSocket.close();
      } catch (IOException iOException) {} 
  }
  
  public static long copyStream(InputStream paramInputStream, OutputStream paramOutputStream) throws IOException {
    return copyStream(paramInputStream, paramOutputStream, false);
  }
  
  public static long copyStream(InputStream paramInputStream, OutputStream paramOutputStream, boolean paramBoolean) throws IOException {
    return copyStream(paramInputStream, paramOutputStream, paramBoolean, 1024);
  }
  
  public static long copyStream(InputStream paramInputStream, OutputStream paramOutputStream, boolean paramBoolean, int paramInt) throws IOException {
    null = new byte[paramInt];
    long l = 0L;
    try {
      while (true) {
        int i = paramInputStream.read(null, 0, paramInt);
        if (i != -1) {
          l += i;
          paramOutputStream.write(null, 0, i);
          continue;
        } 
        return l;
      } 
    } finally {
      if (paramBoolean) {
        closeQuietly(paramInputStream);
        closeQuietly(paramOutputStream);
      } 
    } 
  }
  
  public static boolean isGzipByteBuffer(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte.length > 1) {
      byte b = paramArrayOfbyte[0];
      if (((paramArrayOfbyte[1] & 0xFF) << 8 | b & 0xFF) == 35615)
        return true; 
    } 
    return false;
  }
  
  @WorkerThread
  public static void lockAndTruncateFile(File paramFile) throws IOException, OverlappingFileLockException {
    Closeable closeable;
    if (!paramFile.exists())
      throw new FileNotFoundException(); 
    FileLock fileLock = null;
    try {
      closeable = new RandomAccessFile();
      this(paramFile, "rw");
    } finally {
      paramFile = null;
    } 
    if (fileLock != null && fileLock.isValid())
      try {
        fileLock.release();
      } catch (IOException iOException) {} 
    if (closeable != null)
      closeQuietly(closeable); 
    throw paramFile;
  }
  
  public static byte[] readInputStreamFully(InputStream paramInputStream) throws IOException {
    return readInputStreamFully(paramInputStream, true);
  }
  
  public static byte[] readInputStreamFully(InputStream paramInputStream, boolean paramBoolean) throws IOException {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    copyStream(paramInputStream, byteArrayOutputStream, paramBoolean);
    return byteArrayOutputStream.toByteArray();
  }
  
  public static byte[] toByteArray(File paramFile) throws IOException {
    return (new zzb(paramFile, null)).zzdd();
  }
  
  public static byte[] toByteArray(InputStream paramInputStream) throws IOException {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    zza(paramInputStream, byteArrayOutputStream);
    return byteArrayOutputStream.toByteArray();
  }
  
  private static long zza(InputStream paramInputStream, OutputStream paramOutputStream) throws IOException {
    Preconditions.checkNotNull(paramInputStream);
    Preconditions.checkNotNull(paramOutputStream);
    byte[] arrayOfByte = new byte[4096];
    long l = 0L;
    while (true) {
      int i = paramInputStream.read(arrayOfByte);
      if (i != -1) {
        paramOutputStream.write(arrayOfByte, 0, i);
        l += i;
        continue;
      } 
      return l;
    } 
  }
  
  private static byte[] zza(InputStream paramInputStream, long paramLong) throws IOException {
    StringBuilder stringBuilder;
    if (paramLong > 2147483647L) {
      stringBuilder = new StringBuilder(68);
      stringBuilder.append("file is too large to fit in a byte array: ");
      stringBuilder.append(paramLong);
      stringBuilder.append(" bytes");
      throw new OutOfMemoryError(stringBuilder.toString());
    } 
    if (paramLong == 0L)
      return toByteArray((InputStream)stringBuilder); 
    int i = (int)paramLong;
    byte[] arrayOfByte2 = new byte[i];
    int j;
    for (j = i; j > 0; j -= m) {
      int k = i - j;
      int m = stringBuilder.read(arrayOfByte2, k, j);
      if (m == -1)
        return Arrays.copyOf(arrayOfByte2, k); 
    } 
    j = stringBuilder.read();
    if (j == -1)
      return arrayOfByte2; 
    zza zza = new zza(null);
    zza.write(j);
    zza((InputStream)stringBuilder, zza);
    byte[] arrayOfByte1 = Arrays.copyOf(arrayOfByte2, arrayOfByte2.length + zza.size());
    zza.zza(arrayOfByte1, arrayOfByte2.length);
    return arrayOfByte1;
  }
  
  private static final class zza extends ByteArrayOutputStream {
    private zza() {}
    
    final void zza(byte[] param1ArrayOfbyte, int param1Int) {
      System.arraycopy(this.buf, 0, param1ArrayOfbyte, param1Int, this.count);
    }
  }
  
  private static final class zzb {
    private final File file;
    
    private zzb(File param1File) {
      this.file = (File)Preconditions.checkNotNull(param1File);
    }
    
    public final byte[] zzdd() throws IOException {
      Exception exception;
      FileInputStream fileInputStream = null;
      try {
        FileInputStream fileInputStream1 = new FileInputStream();
        this(this.file);
        try {
          return IOUtils.zzb(fileInputStream1, fileInputStream1.getChannel().size());
        } finally {
          exception = null;
        } 
      } finally {}
      IOUtils.closeQuietly(fileInputStream);
      throw exception;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\IOUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */