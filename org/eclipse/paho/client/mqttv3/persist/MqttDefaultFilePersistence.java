package org.eclipse.paho.client.mqttv3.persist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.internal.FileLock;
import org.eclipse.paho.client.mqttv3.internal.MqttPersistentData;

public class MqttDefaultFilePersistence implements MqttClientPersistence {
  private static final FilenameFilter FILE_FILTER = new MqttDefaultFilePersistence$1();
  
  private static final String LOCK_FILENAME = ".lck";
  
  private static final String MESSAGE_BACKUP_FILE_EXTENSION = ".bup";
  
  private static final String MESSAGE_FILE_EXTENSION = ".msg";
  
  private File clientDir = null;
  
  private File dataDir;
  
  private FileLock fileLock = null;
  
  public MqttDefaultFilePersistence() {
    this(System.getProperty("user.dir"));
  }
  
  public MqttDefaultFilePersistence(String paramString) {
    this.dataDir = new File(paramString);
  }
  
  private void checkIsOpen() throws MqttPersistenceException {
    if (this.clientDir == null)
      throw new MqttPersistenceException(); 
  }
  
  private File[] getFiles() throws MqttPersistenceException {
    checkIsOpen();
    File[] arrayOfFile = this.clientDir.listFiles(FILE_FILTER);
    if (arrayOfFile == null)
      throw new MqttPersistenceException(); 
    return arrayOfFile;
  }
  
  private boolean isSafeChar(char paramChar) {
    return !(!Character.isJavaIdentifierPart(paramChar) && paramChar != '-');
  }
  
  private void restoreBackups(File paramFile) throws MqttPersistenceException {
    File[] arrayOfFile = paramFile.listFiles(new MqttDefaultFilePersistence$2(this));
    if (arrayOfFile == null)
      throw new MqttPersistenceException(); 
    for (byte b = 0;; b++) {
      if (b >= arrayOfFile.length)
        return; 
      File file = new File(paramFile, arrayOfFile[b].getName().substring(0, arrayOfFile[b].getName().length() - ".bup".length()));
      if (!arrayOfFile[b].renameTo(file)) {
        file.delete();
        arrayOfFile[b].renameTo(file);
      } 
    } 
  }
  
  public void clear() throws MqttPersistenceException {
    checkIsOpen();
    File[] arrayOfFile = getFiles();
    for (byte b = 0;; b++) {
      if (b >= arrayOfFile.length)
        return; 
      arrayOfFile[b].delete();
    } 
  }
  
  public void close() throws MqttPersistenceException {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield fileLock : Lorg/eclipse/paho/client/mqttv3/internal/FileLock;
    //   6: ifnull -> 16
    //   9: aload_0
    //   10: getfield fileLock : Lorg/eclipse/paho/client/mqttv3/internal/FileLock;
    //   13: invokevirtual release : ()V
    //   16: aload_0
    //   17: invokespecial getFiles : ()[Ljava/io/File;
    //   20: arraylength
    //   21: ifne -> 32
    //   24: aload_0
    //   25: getfield clientDir : Ljava/io/File;
    //   28: invokevirtual delete : ()Z
    //   31: pop
    //   32: aload_0
    //   33: aconst_null
    //   34: putfield clientDir : Ljava/io/File;
    //   37: aload_0
    //   38: monitorexit
    //   39: return
    //   40: astore_1
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_1
    //   44: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	40	finally
    //   16	32	40	finally
    //   32	39	40	finally
    //   41	43	40	finally
  }
  
  public boolean containsKey(String paramString) throws MqttPersistenceException {
    checkIsOpen();
    File file = this.clientDir;
    StringBuffer stringBuffer = new StringBuffer(String.valueOf(paramString));
    stringBuffer.append(".msg");
    return (new File(file, stringBuffer.toString())).exists();
  }
  
  public MqttPersistable get(String paramString) throws MqttPersistenceException {
    checkIsOpen();
    try {
      File file1 = new File();
      File file2 = this.clientDir;
      StringBuffer stringBuffer = new StringBuffer();
      this(String.valueOf(paramString));
      stringBuffer.append(".msg");
      this(file2, stringBuffer.toString());
      FileInputStream fileInputStream = new FileInputStream();
      this(file1);
      int i = fileInputStream.available();
      byte[] arrayOfByte = new byte[i];
      int j;
      for (j = 0;; j += k) {
        if (j >= i) {
          fileInputStream.close();
          return (MqttPersistable)new MqttPersistentData(paramString, arrayOfByte, 0, arrayOfByte.length, null, 0, 0);
        } 
        int k = fileInputStream.read(arrayOfByte, j, i - j);
      } 
    } catch (IOException iOException) {
      throw new MqttPersistenceException(iOException);
    } 
  }
  
  public Enumeration keys() throws MqttPersistenceException {
    checkIsOpen();
    File[] arrayOfFile = getFiles();
    Vector<String> vector = new Vector(arrayOfFile.length);
    for (byte b = 0;; b++) {
      if (b >= arrayOfFile.length)
        return vector.elements(); 
      String str = arrayOfFile[b].getName();
      vector.addElement(str.substring(0, str.length() - ".msg".length()));
    } 
  }
  
  public void open(String paramString1, String paramString2) throws MqttPersistenceException {
    // Byte code:
    //   0: aload_0
    //   1: getfield dataDir : Ljava/io/File;
    //   4: invokevirtual exists : ()Z
    //   7: ifeq -> 28
    //   10: aload_0
    //   11: getfield dataDir : Ljava/io/File;
    //   14: invokevirtual isDirectory : ()Z
    //   17: ifne -> 28
    //   20: new org/eclipse/paho/client/mqttv3/MqttPersistenceException
    //   23: dup
    //   24: invokespecial <init> : ()V
    //   27: athrow
    //   28: aload_0
    //   29: getfield dataDir : Ljava/io/File;
    //   32: invokevirtual exists : ()Z
    //   35: ifne -> 56
    //   38: aload_0
    //   39: getfield dataDir : Ljava/io/File;
    //   42: invokevirtual mkdirs : ()Z
    //   45: ifne -> 56
    //   48: new org/eclipse/paho/client/mqttv3/MqttPersistenceException
    //   51: dup
    //   52: invokespecial <init> : ()V
    //   55: athrow
    //   56: aload_0
    //   57: getfield dataDir : Ljava/io/File;
    //   60: invokevirtual canWrite : ()Z
    //   63: ifne -> 74
    //   66: new org/eclipse/paho/client/mqttv3/MqttPersistenceException
    //   69: dup
    //   70: invokespecial <init> : ()V
    //   73: athrow
    //   74: new java/lang/StringBuffer
    //   77: dup
    //   78: invokespecial <init> : ()V
    //   81: astore_3
    //   82: iconst_0
    //   83: istore #4
    //   85: iconst_0
    //   86: istore #5
    //   88: iload #5
    //   90: aload_1
    //   91: invokevirtual length : ()I
    //   94: if_icmplt -> 246
    //   97: aload_3
    //   98: ldc '-'
    //   100: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   103: pop
    //   104: iload #4
    //   106: istore #5
    //   108: iload #5
    //   110: aload_2
    //   111: invokevirtual length : ()I
    //   114: if_icmplt -> 216
    //   117: aload_0
    //   118: monitorenter
    //   119: aload_0
    //   120: getfield clientDir : Ljava/io/File;
    //   123: ifnonnull -> 167
    //   126: aload_3
    //   127: invokevirtual toString : ()Ljava/lang/String;
    //   130: astore_2
    //   131: new java/io/File
    //   134: astore_1
    //   135: aload_1
    //   136: aload_0
    //   137: getfield dataDir : Ljava/io/File;
    //   140: aload_2
    //   141: invokespecial <init> : (Ljava/io/File;Ljava/lang/String;)V
    //   144: aload_0
    //   145: aload_1
    //   146: putfield clientDir : Ljava/io/File;
    //   149: aload_0
    //   150: getfield clientDir : Ljava/io/File;
    //   153: invokevirtual exists : ()Z
    //   156: ifne -> 167
    //   159: aload_0
    //   160: getfield clientDir : Ljava/io/File;
    //   163: invokevirtual mkdir : ()Z
    //   166: pop
    //   167: new org/eclipse/paho/client/mqttv3/internal/FileLock
    //   170: astore_1
    //   171: aload_1
    //   172: aload_0
    //   173: getfield clientDir : Ljava/io/File;
    //   176: ldc '.lck'
    //   178: invokespecial <init> : (Ljava/io/File;Ljava/lang/String;)V
    //   181: aload_0
    //   182: aload_1
    //   183: putfield fileLock : Lorg/eclipse/paho/client/mqttv3/internal/FileLock;
    //   186: aload_0
    //   187: aload_0
    //   188: getfield clientDir : Ljava/io/File;
    //   191: invokespecial restoreBackups : (Ljava/io/File;)V
    //   194: aload_0
    //   195: monitorexit
    //   196: return
    //   197: astore_1
    //   198: new org/eclipse/paho/client/mqttv3/MqttPersistenceException
    //   201: astore_1
    //   202: aload_1
    //   203: sipush #32200
    //   206: invokespecial <init> : (I)V
    //   209: aload_1
    //   210: athrow
    //   211: astore_1
    //   212: aload_0
    //   213: monitorexit
    //   214: aload_1
    //   215: athrow
    //   216: aload_2
    //   217: iload #5
    //   219: invokevirtual charAt : (I)C
    //   222: istore #6
    //   224: aload_0
    //   225: iload #6
    //   227: invokespecial isSafeChar : (C)Z
    //   230: ifeq -> 240
    //   233: aload_3
    //   234: iload #6
    //   236: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   239: pop
    //   240: iinc #5, 1
    //   243: goto -> 108
    //   246: aload_1
    //   247: iload #5
    //   249: invokevirtual charAt : (I)C
    //   252: istore #6
    //   254: aload_0
    //   255: iload #6
    //   257: invokespecial isSafeChar : (C)Z
    //   260: ifeq -> 270
    //   263: aload_3
    //   264: iload #6
    //   266: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   269: pop
    //   270: iinc #5, 1
    //   273: goto -> 88
    // Exception table:
    //   from	to	target	type
    //   119	167	211	finally
    //   167	186	197	java/lang/Exception
    //   167	186	211	finally
    //   186	196	211	finally
    //   198	211	211	finally
    //   212	214	211	finally
  }
  
  public void put(String paramString, MqttPersistable paramMqttPersistable) throws MqttPersistenceException {
    checkIsOpen();
    File file2 = this.clientDir;
    StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(paramString));
    stringBuffer2.append(".msg");
    file2 = new File(file2, stringBuffer2.toString());
    File file3 = this.clientDir;
    StringBuffer stringBuffer1 = new StringBuffer(String.valueOf(paramString));
    stringBuffer1.append(".msg");
    stringBuffer1.append(".bup");
    File file1 = new File(file3, stringBuffer1.toString());
    if (file2.exists() && !file2.renameTo(file1)) {
      file1.delete();
      file2.renameTo(file1);
    } 
    try {
      FileOutputStream fileOutputStream = new FileOutputStream();
      this(file2);
      fileOutputStream.write(paramMqttPersistable.getHeaderBytes(), paramMqttPersistable.getHeaderOffset(), paramMqttPersistable.getHeaderLength());
      if (paramMqttPersistable.getPayloadBytes() != null)
        fileOutputStream.write(paramMqttPersistable.getPayloadBytes(), paramMqttPersistable.getPayloadOffset(), paramMqttPersistable.getPayloadLength()); 
      fileOutputStream.getFD().sync();
      fileOutputStream.close();
      if (file1.exists())
        file1.delete(); 
      if (file1.exists() && !file1.renameTo(file2)) {
        file2.delete();
        file1.renameTo(file2);
      } 
      return;
    } catch (IOException iOException) {
      MqttPersistenceException mqttPersistenceException = new MqttPersistenceException();
      this(iOException);
      throw mqttPersistenceException;
    } finally {}
    if (file1.exists() && !file1.renameTo(file2)) {
      file2.delete();
      file1.renameTo(file2);
    } 
    throw paramMqttPersistable;
  }
  
  public void remove(String paramString) throws MqttPersistenceException {
    checkIsOpen();
    File file2 = this.clientDir;
    StringBuffer stringBuffer = new StringBuffer(String.valueOf(paramString));
    stringBuffer.append(".msg");
    File file1 = new File(file2, stringBuffer.toString());
    if (file1.exists())
      file1.delete(); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\persist\MqttDefaultFilePersistence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */