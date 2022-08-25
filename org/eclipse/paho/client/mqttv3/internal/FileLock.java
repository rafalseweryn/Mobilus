package org.eclipse.paho.client.mqttv3.internal;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileLock {
  private RandomAccessFile file;
  
  private Object fileLock;
  
  private File lockFile;
  
  public FileLock(File paramFile, String paramString) throws Exception {
    this.lockFile = new File(paramFile, paramString);
    if (ExceptionHelper.isClassAvailable("java.nio.channels.FileLock")) {
      try {
        RandomAccessFile randomAccessFile = new RandomAccessFile();
        this(this.lockFile, "rw");
        this.file = randomAccessFile;
        Object object = this.file.getClass().getMethod("getChannel", new Class[0]).invoke(this.file, new Object[0]);
        this.fileLock = object.getClass().getMethod("tryLock", new Class[0]).invoke(object, new Object[0]);
      } catch (NoSuchMethodException noSuchMethodException) {
        this.fileLock = null;
      } catch (IllegalArgumentException illegalArgumentException) {
        this.fileLock = null;
      } catch (IllegalAccessException illegalAccessException) {
        this.fileLock = null;
      } 
      if (this.fileLock == null) {
        release();
        throw new Exception("Problem obtaining file lock");
      } 
    } 
  }
  
  public void release() {
    try {
      if (this.fileLock != null) {
        this.fileLock.getClass().getMethod("release", new Class[0]).invoke(this.fileLock, new Object[0]);
        this.fileLock = null;
      } 
    } catch (Exception exception) {}
    if (this.file != null) {
      try {
        this.file.close();
      } catch (IOException iOException) {}
      this.file = null;
    } 
    if (this.lockFile != null && this.lockFile.exists())
      this.lockFile.delete(); 
    this.lockFile = null;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\FileLock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */