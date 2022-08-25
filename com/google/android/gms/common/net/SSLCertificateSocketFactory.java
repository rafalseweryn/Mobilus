package com.google.android.gms.common.net;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.security.PrivateKey;
import javax.net.SocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class SSLCertificateSocketFactory extends SSLSocketFactory {
  private static final TrustManager[] zzvf = new TrustManager[] { new zza() };
  
  @VisibleForTesting
  private final Context mContext;
  
  @VisibleForTesting
  private SSLSocketFactory zzvg = null;
  
  @VisibleForTesting
  private SSLSocketFactory zzvh = null;
  
  @VisibleForTesting
  private TrustManager[] zzvi = null;
  
  @VisibleForTesting
  private KeyManager[] zzvj = null;
  
  @VisibleForTesting
  private byte[] zzvk = null;
  
  @VisibleForTesting
  private byte[] zzvl = null;
  
  @VisibleForTesting
  private PrivateKey zzvm = null;
  
  @VisibleForTesting
  private final int zzvn;
  
  @VisibleForTesting
  private final boolean zzvo;
  
  @VisibleForTesting
  private final boolean zzvp;
  
  @VisibleForTesting
  private final String zzvq;
  
  private SSLCertificateSocketFactory(Context paramContext, int paramInt, boolean paramBoolean1, boolean paramBoolean2, String paramString) {
    this.mContext = paramContext.getApplicationContext();
    this.zzvn = paramInt;
    this.zzvo = paramBoolean1;
    this.zzvp = paramBoolean2;
    this.zzvq = paramString;
  }
  
  public static SocketFactory getDefault(Context paramContext, int paramInt) {
    return new SSLCertificateSocketFactory(paramContext, paramInt, false, true, null);
  }
  
  public static SSLSocketFactory getDefaultWithCacheDir(int paramInt, Context paramContext, String paramString) {
    return new SSLCertificateSocketFactory(paramContext, paramInt, true, true, paramString);
  }
  
  public static SSLSocketFactory getDefaultWithSessionCache(int paramInt, Context paramContext) {
    return new SSLCertificateSocketFactory(paramContext, paramInt, true, true, null);
  }
  
  public static SSLSocketFactory getInsecure(Context paramContext, int paramInt, boolean paramBoolean) {
    return new SSLCertificateSocketFactory(paramContext, paramInt, paramBoolean, false, null);
  }
  
  public static void verifyHostname(Socket paramSocket, String paramString) throws IOException {
    if (!(paramSocket instanceof javax.net.ssl.SSLSocket))
      throw new IllegalArgumentException("Attempt to verify non-SSL socket"); 
    paramSocket = paramSocket;
    paramSocket.startHandshake();
    SSLSession sSLSession = paramSocket.getSession();
    if (sSLSession == null)
      throw new SSLException("Cannot verify SSL socket without session"); 
    if (!HttpsURLConnection.getDefaultHostnameVerifier().verify(paramString, sSLSession)) {
      String str = String.valueOf(paramString);
      if (str.length() != 0) {
        str = "Cannot verify hostname: ".concat(str);
      } else {
        str = new String("Cannot verify hostname: ");
      } 
      throw new SSLPeerUnverifiedException(str);
    } 
  }
  
  @VisibleForTesting
  private static void zza(Socket paramSocket, int paramInt) {
    if (paramSocket != null) {
      String str;
      try {
        paramSocket.getClass().getMethod("setHandshakeTimeout", new Class[] { int.class }).invoke(paramSocket, new Object[] { Integer.valueOf(paramInt) });
        return;
      } catch (InvocationTargetException invocationTargetException) {
        Throwable throwable = invocationTargetException.getCause();
        if (throwable instanceof RuntimeException)
          throw (RuntimeException)throwable; 
        str = String.valueOf(paramSocket.getClass());
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 46);
        stringBuilder.append("Failed to invoke setSocketHandshakeTimeout on ");
        stringBuilder.append(str);
        throw new RuntimeException(stringBuilder.toString(), invocationTargetException);
      } catch (NoSuchMethodException|IllegalAccessException noSuchMethodException) {
        str = String.valueOf(str.getClass());
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 45);
        stringBuilder.append(str);
        stringBuilder.append(" does not implement setSocketHandshakeTimeout");
        throw new IllegalArgumentException(stringBuilder.toString(), noSuchMethodException);
      } 
    } 
  }
  
  @VisibleForTesting
  private static void zza(Socket paramSocket, PrivateKey paramPrivateKey) {
    if (paramSocket != null) {
      String str;
      try {
        paramSocket.getClass().getMethod("setChannelIdPrivateKey", new Class[] { PrivateKey.class }).invoke(paramSocket, new Object[] { paramPrivateKey });
        return;
      } catch (InvocationTargetException invocationTargetException) {
        Throwable throwable = invocationTargetException.getCause();
        if (throwable instanceof RuntimeException)
          throw (RuntimeException)throwable; 
        str = String.valueOf(paramSocket.getClass());
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 43);
        stringBuilder.append("Failed to invoke setChannelIdPrivateKey on ");
        stringBuilder.append(str);
        throw new RuntimeException(stringBuilder.toString(), invocationTargetException);
      } catch (NoSuchMethodException|IllegalAccessException noSuchMethodException) {
        String str1 = String.valueOf(str.getClass());
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 42);
        stringBuilder.append(str1);
        stringBuilder.append(" does not implement setChannelIdPrivateKey");
        throw new IllegalArgumentException(stringBuilder.toString(), noSuchMethodException);
      } 
    } 
  }
  
  @VisibleForTesting
  private static void zza(Socket paramSocket, byte[] paramArrayOfbyte) {
    if (paramSocket != null) {
      StringBuilder stringBuilder;
      try {
        paramSocket.getClass().getMethod("setNpnProtocols", new Class[] { byte[].class }).invoke(paramSocket, new Object[] { paramArrayOfbyte });
        return;
      } catch (InvocationTargetException invocationTargetException) {
        Throwable throwable = invocationTargetException.getCause();
        if (throwable instanceof RuntimeException)
          throw (RuntimeException)throwable; 
        String str = String.valueOf(paramSocket.getClass());
        stringBuilder = new StringBuilder(String.valueOf(str).length() + 36);
        stringBuilder.append("Failed to invoke setNpnProtocols on ");
        stringBuilder.append(str);
        throw new RuntimeException(stringBuilder.toString(), invocationTargetException);
      } catch (NoSuchMethodException|IllegalAccessException noSuchMethodException) {
        String str = String.valueOf(stringBuilder.getClass());
        StringBuilder stringBuilder1 = new StringBuilder(String.valueOf(str).length() + 35);
        stringBuilder1.append(str);
        stringBuilder1.append(" does not implement setNpnProtocols");
        throw new IllegalArgumentException(stringBuilder1.toString(), noSuchMethodException);
      } 
    } 
  }
  
  private static byte[] zza(byte[]... paramVarArgs) {
    StringBuilder stringBuilder;
    if (paramVarArgs.length == 0)
      throw new IllegalArgumentException("items.length == 0"); 
    int i = paramVarArgs.length;
    int j = 0;
    int k = j;
    while (j < i) {
      byte[] arrayOfByte1 = paramVarArgs[j];
      if (arrayOfByte1.length == 0 || arrayOfByte1.length > 255) {
        j = arrayOfByte1.length;
        stringBuilder = new StringBuilder(44);
        stringBuilder.append("s.length == 0 || s.length > 255: ");
        stringBuilder.append(j);
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      k += arrayOfByte1.length + 1;
      j++;
    } 
    byte[] arrayOfByte = new byte[k];
    int m = stringBuilder.length;
    k = 0;
    j = k;
    while (k < m) {
      StringBuilder stringBuilder1 = stringBuilder[k];
      arrayOfByte[j] = (byte)(byte)stringBuilder1.length;
      int n = stringBuilder1.length;
      j++;
      i = 0;
      while (i < n) {
        arrayOfByte[j] = (byte)stringBuilder1[i];
        i++;
        j++;
      } 
      k++;
    } 
    return arrayOfByte;
  }
  
  @VisibleForTesting
  private static void zzb(Socket paramSocket, byte[] paramArrayOfbyte) {
    if (paramSocket != null) {
      String str;
      try {
        paramSocket.getClass().getMethod("setAlpnProtocols", new Class[] { byte[].class }).invoke(paramSocket, new Object[] { paramArrayOfbyte });
        return;
      } catch (InvocationTargetException invocationTargetException) {
        Throwable throwable = invocationTargetException.getCause();
        if (throwable instanceof RuntimeException)
          throw (RuntimeException)throwable; 
        str = String.valueOf(paramSocket.getClass());
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 37);
        stringBuilder.append("Failed to invoke setAlpnProtocols on ");
        stringBuilder.append(str);
        throw new RuntimeException(stringBuilder.toString(), invocationTargetException);
      } catch (NoSuchMethodException|IllegalAccessException noSuchMethodException) {
        String str1 = String.valueOf(str.getClass());
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 36);
        stringBuilder.append(str1);
        stringBuilder.append(" does not implement setAlpnProtocols");
        throw new IllegalArgumentException(stringBuilder.toString(), noSuchMethodException);
      } 
    } 
  }
  
  @VisibleForTesting
  private final SSLSocketFactory zzcx() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzvp : Z
    //   6: ifne -> 58
    //   9: aload_0
    //   10: getfield zzvg : Ljavax/net/ssl/SSLSocketFactory;
    //   13: ifnonnull -> 49
    //   16: ldc 'SSLCertificateSocketFactory'
    //   18: ldc 'Bypassing SSL security checks at caller's request'
    //   20: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   23: pop
    //   24: aload_0
    //   25: invokestatic getInstance : ()Lcom/google/android/gms/common/net/SocketFactoryCreator;
    //   28: aload_0
    //   29: getfield mContext : Landroid/content/Context;
    //   32: aload_0
    //   33: getfield zzvj : [Ljavax/net/ssl/KeyManager;
    //   36: getstatic com/google/android/gms/common/net/SSLCertificateSocketFactory.zzvf : [Ljavax/net/ssl/TrustManager;
    //   39: aload_0
    //   40: getfield zzvo : Z
    //   43: invokevirtual makeSocketFactory : (Landroid/content/Context;[Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Z)Ljavax/net/ssl/SSLSocketFactory;
    //   46: putfield zzvg : Ljavax/net/ssl/SSLSocketFactory;
    //   49: aload_0
    //   50: getfield zzvg : Ljavax/net/ssl/SSLSocketFactory;
    //   53: astore_1
    //   54: aload_0
    //   55: monitorexit
    //   56: aload_1
    //   57: areturn
    //   58: aload_0
    //   59: getfield zzvq : Ljava/lang/String;
    //   62: ifnull -> 103
    //   65: aload_0
    //   66: getfield zzvh : Ljavax/net/ssl/SSLSocketFactory;
    //   69: ifnonnull -> 136
    //   72: invokestatic getInstance : ()Lcom/google/android/gms/common/net/SocketFactoryCreator;
    //   75: aload_0
    //   76: getfield mContext : Landroid/content/Context;
    //   79: aload_0
    //   80: getfield zzvj : [Ljavax/net/ssl/KeyManager;
    //   83: aload_0
    //   84: getfield zzvi : [Ljavax/net/ssl/TrustManager;
    //   87: aload_0
    //   88: getfield zzvq : Ljava/lang/String;
    //   91: invokevirtual makeSocketFactoryWithCacheDir : (Landroid/content/Context;[Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/lang/String;)Ljavax/net/ssl/SSLSocketFactory;
    //   94: astore_1
    //   95: aload_0
    //   96: aload_1
    //   97: putfield zzvh : Ljavax/net/ssl/SSLSocketFactory;
    //   100: goto -> 136
    //   103: aload_0
    //   104: getfield zzvh : Ljavax/net/ssl/SSLSocketFactory;
    //   107: ifnonnull -> 136
    //   110: invokestatic getInstance : ()Lcom/google/android/gms/common/net/SocketFactoryCreator;
    //   113: aload_0
    //   114: getfield mContext : Landroid/content/Context;
    //   117: aload_0
    //   118: getfield zzvj : [Ljavax/net/ssl/KeyManager;
    //   121: aload_0
    //   122: getfield zzvi : [Ljavax/net/ssl/TrustManager;
    //   125: aload_0
    //   126: getfield zzvo : Z
    //   129: invokevirtual makeSocketFactory : (Landroid/content/Context;[Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Z)Ljavax/net/ssl/SSLSocketFactory;
    //   132: astore_1
    //   133: goto -> 95
    //   136: aload_0
    //   137: getfield zzvh : Ljavax/net/ssl/SSLSocketFactory;
    //   140: astore_1
    //   141: aload_0
    //   142: monitorexit
    //   143: aload_1
    //   144: areturn
    //   145: astore_1
    //   146: aload_0
    //   147: monitorexit
    //   148: aload_1
    //   149: athrow
    // Exception table:
    //   from	to	target	type
    //   2	49	145	finally
    //   49	54	145	finally
    //   58	95	145	finally
    //   95	100	145	finally
    //   103	133	145	finally
    //   136	141	145	finally
  }
  
  public Socket createSocket() throws IOException {
    Socket socket = zzcx().createSocket();
    zza(socket, this.zzvk);
    zzb(socket, this.zzvl);
    zza(socket, this.zzvn);
    zza(socket, this.zzvm);
    return socket;
  }
  
  public Socket createSocket(String paramString, int paramInt) throws IOException {
    Socket socket = zzcx().createSocket(paramString, paramInt);
    zza(socket, this.zzvk);
    zzb(socket, this.zzvl);
    zza(socket, this.zzvn);
    zza(socket, this.zzvm);
    if (this.zzvp)
      verifyHostname(socket, paramString); 
    return socket;
  }
  
  public Socket createSocket(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2) throws IOException {
    Socket socket = zzcx().createSocket(paramString, paramInt1, paramInetAddress, paramInt2);
    zza(socket, this.zzvk);
    zzb(socket, this.zzvl);
    zza(socket, this.zzvn);
    zza(socket, this.zzvm);
    if (this.zzvp)
      verifyHostname(socket, paramString); 
    return socket;
  }
  
  public Socket createSocket(InetAddress paramInetAddress, int paramInt) throws IOException {
    Socket socket = zzcx().createSocket(paramInetAddress, paramInt);
    zza(socket, this.zzvk);
    zzb(socket, this.zzvl);
    zza(socket, this.zzvn);
    zza(socket, this.zzvm);
    return socket;
  }
  
  public Socket createSocket(InetAddress paramInetAddress1, int paramInt1, InetAddress paramInetAddress2, int paramInt2) throws IOException {
    Socket socket = zzcx().createSocket(paramInetAddress1, paramInt1, paramInetAddress2, paramInt2);
    zza(socket, this.zzvk);
    zzb(socket, this.zzvl);
    zza(socket, this.zzvn);
    zza(socket, this.zzvm);
    return socket;
  }
  
  public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean) throws IOException {
    paramSocket = zzcx().createSocket(paramSocket, paramString, paramInt, paramBoolean);
    zza(paramSocket, this.zzvk);
    zzb(paramSocket, this.zzvl);
    zza(paramSocket, this.zzvn);
    zza(paramSocket, this.zzvm);
    if (this.zzvp)
      verifyHostname(paramSocket, paramString); 
    return paramSocket;
  }
  
  public byte[] getAlpnSelectedProtocol(Socket paramSocket) {
    String str;
    try {
      return (byte[])paramSocket.getClass().getMethod("getAlpnSelectedProtocol", new Class[0]).invoke(paramSocket, new Object[0]);
    } catch (InvocationTargetException invocationTargetException) {
      Throwable throwable = invocationTargetException.getCause();
      if (throwable instanceof RuntimeException)
        throw (RuntimeException)throwable; 
      str = String.valueOf(paramSocket.getClass());
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 44);
      stringBuilder.append("Failed to invoke getAlpnSelectedProtocol on ");
      stringBuilder.append(str);
      throw new RuntimeException(stringBuilder.toString(), invocationTargetException);
    } catch (NoSuchMethodException|IllegalAccessException noSuchMethodException) {
      String str1 = String.valueOf(str.getClass());
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 43);
      stringBuilder.append(str1);
      stringBuilder.append(" does not implement getAlpnSelectedProtocol");
      throw new IllegalArgumentException(stringBuilder.toString(), noSuchMethodException);
    } 
  }
  
  public String[] getDefaultCipherSuites() {
    return zzcx().getDefaultCipherSuites();
  }
  
  public byte[] getNpnSelectedProtocol(Socket paramSocket) {
    StringBuilder stringBuilder;
    try {
      return (byte[])paramSocket.getClass().getMethod("getNpnSelectedProtocol", new Class[0]).invoke(paramSocket, new Object[0]);
    } catch (InvocationTargetException invocationTargetException) {
      Throwable throwable = invocationTargetException.getCause();
      if (throwable instanceof RuntimeException)
        throw (RuntimeException)throwable; 
      String str = String.valueOf(paramSocket.getClass());
      stringBuilder = new StringBuilder(String.valueOf(str).length() + 43);
      stringBuilder.append("Failed to invoke getNpnSelectedProtocol on ");
      stringBuilder.append(str);
      throw new RuntimeException(stringBuilder.toString(), invocationTargetException);
    } catch (NoSuchMethodException|IllegalAccessException noSuchMethodException) {
      String str = String.valueOf(stringBuilder.getClass());
      stringBuilder = new StringBuilder(String.valueOf(str).length() + 42);
      stringBuilder.append(str);
      stringBuilder.append(" does not implement getNpnSelectedProtocol");
      throw new IllegalArgumentException(stringBuilder.toString(), noSuchMethodException);
    } 
  }
  
  public String[] getSupportedCipherSuites() {
    return zzcx().getSupportedCipherSuites();
  }
  
  public void setAlpnProtocols(byte[][] paramArrayOfbyte) {
    this.zzvl = zza(paramArrayOfbyte);
  }
  
  public void setChannelIdPrivateKey(PrivateKey paramPrivateKey) {
    this.zzvm = paramPrivateKey;
  }
  
  public void setHostname(Socket paramSocket, String paramString) {
    StringBuilder stringBuilder;
    try {
      paramSocket.getClass().getMethod("setHostname", new Class[] { String.class }).invoke(paramSocket, new Object[] { paramString });
      return;
    } catch (InvocationTargetException invocationTargetException) {
      Throwable throwable = invocationTargetException.getCause();
      if (throwable instanceof RuntimeException)
        throw (RuntimeException)throwable; 
      String str = String.valueOf(paramSocket.getClass());
      stringBuilder = new StringBuilder(String.valueOf(str).length() + 32);
      stringBuilder.append("Failed to invoke setHostname on ");
      stringBuilder.append(str);
      throw new RuntimeException(stringBuilder.toString(), invocationTargetException);
    } catch (NoSuchMethodException|IllegalAccessException noSuchMethodException) {
      String str = String.valueOf(stringBuilder.getClass());
      StringBuilder stringBuilder1 = new StringBuilder(String.valueOf(str).length() + 31);
      stringBuilder1.append(str);
      stringBuilder1.append(" does not implement setHostname");
      throw new IllegalArgumentException(stringBuilder1.toString(), noSuchMethodException);
    } 
  }
  
  public void setKeyManagers(KeyManager[] paramArrayOfKeyManager) {
    this.zzvj = paramArrayOfKeyManager;
    this.zzvh = null;
    this.zzvg = null;
  }
  
  public void setNpnProtocols(byte[][] paramArrayOfbyte) {
    this.zzvk = zza(paramArrayOfbyte);
  }
  
  public void setSoWriteTimeout(Socket paramSocket, int paramInt) throws SocketException {
    String str;
    try {
      paramSocket.getClass().getMethod("setSoWriteTimeout", new Class[] { int.class }).invoke(paramSocket, new Object[] { Integer.valueOf(paramInt) });
      return;
    } catch (InvocationTargetException invocationTargetException) {
      Throwable throwable = invocationTargetException.getCause();
      if (throwable instanceof SocketException)
        throw (SocketException)throwable; 
      if (throwable instanceof RuntimeException)
        throw (RuntimeException)throwable; 
      str = String.valueOf(paramSocket.getClass());
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 38);
      stringBuilder.append("Failed to invoke setSoWriteTimeout on ");
      stringBuilder.append(str);
      throw new RuntimeException(stringBuilder.toString(), invocationTargetException);
    } catch (NoSuchMethodException|IllegalAccessException noSuchMethodException) {
      str = String.valueOf(str.getClass());
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 37);
      stringBuilder.append(str);
      stringBuilder.append(" does not implement setSoWriteTimeout");
      throw new IllegalArgumentException(stringBuilder.toString(), noSuchMethodException);
    } 
  }
  
  public void setTrustManagers(TrustManager[] paramArrayOfTrustManager) {
    this.zzvi = paramArrayOfTrustManager;
    this.zzvh = null;
  }
  
  public void setUseSessionTickets(Socket paramSocket, boolean paramBoolean) {
    String str;
    try {
      paramSocket.getClass().getMethod("setUseSessionTickets", new Class[] { boolean.class }).invoke(paramSocket, new Object[] { Boolean.valueOf(paramBoolean) });
      return;
    } catch (InvocationTargetException invocationTargetException) {
      Throwable throwable = invocationTargetException.getCause();
      if (throwable instanceof RuntimeException)
        throw (RuntimeException)throwable; 
      str = String.valueOf(paramSocket.getClass());
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 41);
      stringBuilder.append("Failed to invoke setUseSessionTickets on ");
      stringBuilder.append(str);
      throw new RuntimeException(stringBuilder.toString(), invocationTargetException);
    } catch (NoSuchMethodException|IllegalAccessException noSuchMethodException) {
      str = String.valueOf(str.getClass());
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 40);
      stringBuilder.append(str);
      stringBuilder.append(" does not implement setUseSessionTickets");
      throw new IllegalArgumentException(stringBuilder.toString(), noSuchMethodException);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\net\SSLCertificateSocketFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */