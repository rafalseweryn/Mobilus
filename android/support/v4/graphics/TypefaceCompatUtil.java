package android.support.v4.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class TypefaceCompatUtil {
  private static final String CACHE_FILE_PREFIX = ".font";
  
  private static final String TAG = "TypefaceCompatUtil";
  
  public static void closeQuietly(Closeable paramCloseable) {
    if (paramCloseable != null)
      try {
        paramCloseable.close();
      } catch (IOException iOException) {} 
  }
  
  @Nullable
  @RequiresApi(19)
  public static ByteBuffer copyToDirectBuffer(Context paramContext, Resources paramResources, int paramInt) {
    File file = getTempFile(paramContext);
    if (file == null)
      return null; 
    try {
      boolean bool = copyToFile(file, paramResources, paramInt);
      if (!bool)
        return null; 
      return mmap(file);
    } finally {
      file.delete();
    } 
  }
  
  public static boolean copyToFile(File paramFile, Resources paramResources, int paramInt) {
    try {
      InputStream inputStream = paramResources.openRawResource(paramInt);
    } finally {
      paramFile = null;
    } 
    closeQuietly((Closeable)paramResources);
    throw paramFile;
  }
  
  public static boolean copyToFile(File paramFile, InputStream paramInputStream) {
    StrictMode.ThreadPolicy threadPolicy = StrictMode.allowThreadDiskWrites();
    File file1 = null;
    FileOutputStream fileOutputStream1 = null;
    FileOutputStream fileOutputStream2 = fileOutputStream1;
    try {
      FileOutputStream fileOutputStream = new FileOutputStream();
      fileOutputStream2 = fileOutputStream1;
      this(paramFile, false);
      try {
        byte[] arrayOfByte = new byte[1024];
        while (true) {
          int i = paramInputStream.read(arrayOfByte);
          if (i != -1) {
            fileOutputStream.write(arrayOfByte, 0, i);
            continue;
          } 
          closeQuietly(fileOutputStream);
          return true;
        } 
      } catch (IOException null) {
      
      } finally {
        paramFile = null;
      } 
    } catch (IOException iOException) {
      paramFile = file1;
    } finally {}
    File file2 = paramFile;
    StringBuilder stringBuilder = new StringBuilder();
    file2 = paramFile;
    this();
    file2 = paramFile;
    stringBuilder.append("Error copying resource contents to temp file: ");
    file2 = paramFile;
    stringBuilder.append(iOException.getMessage());
    file2 = paramFile;
    Log.e("TypefaceCompatUtil", stringBuilder.toString());
    closeQuietly((Closeable)paramFile);
    StrictMode.setThreadPolicy(threadPolicy);
    return false;
  }
  
  @Nullable
  public static File getTempFile(Context paramContext) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(".font");
    stringBuilder.append(Process.myPid());
    stringBuilder.append("-");
    stringBuilder.append(Process.myTid());
    stringBuilder.append("-");
    String str = stringBuilder.toString();
    byte b = 0;
    while (true) {
      if (b < 100) {
        File file = paramContext.getCacheDir();
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(str);
        stringBuilder1.append(b);
        file = new File(file, stringBuilder1.toString());
        try {
          boolean bool = file.createNewFile();
          if (bool)
            return file; 
        } catch (IOException iOException) {}
        b++;
        continue;
      } 
      return null;
    } 
  }
  
  @Nullable
  @RequiresApi(19)
  public static ByteBuffer mmap(Context paramContext, CancellationSignal paramCancellationSignal, Uri paramUri) {
    ContentResolver contentResolver = paramContext.getContentResolver();
    try {
      ParcelFileDescriptor parcelFileDescriptor = contentResolver.openFileDescriptor(paramUri, "r", paramCancellationSignal);
      if (parcelFileDescriptor == null) {
        if (parcelFileDescriptor != null)
          parcelFileDescriptor.close(); 
        return null;
      } 
      try {
        FileInputStream fileInputStream = new FileInputStream();
        this(parcelFileDescriptor.getFileDescriptor());
        try {
          FileChannel fileChannel = fileInputStream.getChannel();
          long l = fileChannel.size();
          return fileChannel.map(FileChannel.MapMode.READ_ONLY, 0L, l);
        } catch (Throwable throwable) {
          try {
            throw throwable;
          } finally {}
        } finally {
          paramCancellationSignal = null;
        } 
        if (fileInputStream != null)
          if (contentResolver != null) {
            try {
              fileInputStream.close();
            } catch (Throwable throwable) {
              contentResolver.addSuppressed(throwable);
            } 
          } else {
            throwable.close();
          }  
        throw paramCancellationSignal;
      } catch (Throwable throwable) {
        try {
          throw throwable;
        } finally {}
      } finally {
        paramCancellationSignal = null;
      } 
      if (parcelFileDescriptor != null)
        if (contentResolver != null) {
          try {
            parcelFileDescriptor.close();
          } catch (Throwable throwable) {
            contentResolver.addSuppressed(throwable);
          } 
        } else {
          throwable.close();
        }  
      throw paramCancellationSignal;
    } catch (IOException iOException) {
      return null;
    } 
  }
  
  @Nullable
  @RequiresApi(19)
  private static ByteBuffer mmap(File paramFile) {
    try {
      Throwable throwable;
      FileInputStream fileInputStream = new FileInputStream();
      this(paramFile);
      try {
        FileChannel fileChannel = fileInputStream.getChannel();
        long l = fileChannel.size();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, 0L, l);
      } catch (Throwable null) {
        try {
          throw throwable;
        } finally {}
      } finally {
        paramFile = null;
      } 
      if (fileInputStream != null)
        if (throwable != null) {
          try {
            fileInputStream.close();
          } catch (Throwable throwable1) {
            throwable.addSuppressed(throwable1);
          } 
        } else {
          throwable1.close();
        }  
      throw paramFile;
    } catch (IOException iOException) {
      return null;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\graphics\TypefaceCompatUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */