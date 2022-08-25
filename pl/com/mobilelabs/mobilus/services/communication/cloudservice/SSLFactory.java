package pl.com.mobilelabs.mobilus.services.communication.cloudservice;

import android.content.Context;
import javax.net.ssl.SSLSocketFactory;

class SSLFactory {
  private static final String SSL_CERTIFICATE_FILE_NAME = "mobilelabs_mobilus_ca.crt";
  
  public static SSLSocketFactory getFactory(Context paramContext) {
    // Byte code:
    //   0: iconst_1
    //   1: istore_1
    //   2: aconst_null
    //   3: astore_2
    //   4: ldc 'X.509'
    //   6: invokestatic getInstance : (Ljava/lang/String;)Ljava/security/cert/CertificateFactory;
    //   9: astore_3
    //   10: aload_0
    //   11: invokevirtual getAssets : ()Landroid/content/res/AssetManager;
    //   14: ldc 'mobilelabs_mobilus_ca.crt'
    //   16: invokevirtual open : (Ljava/lang/String;)Ljava/io/InputStream;
    //   19: astore_0
    //   20: aload_3
    //   21: aload_0
    //   22: invokevirtual generateCertificate : (Ljava/io/InputStream;)Ljava/security/cert/Certificate;
    //   25: astore_2
    //   26: aload_0
    //   27: ifnull -> 34
    //   30: aload_0
    //   31: invokevirtual close : ()V
    //   34: iconst_0
    //   35: istore #4
    //   37: aload_2
    //   38: astore_0
    //   39: goto -> 89
    //   42: astore_2
    //   43: aload_0
    //   44: astore_3
    //   45: aload_2
    //   46: astore_0
    //   47: aload_3
    //   48: astore_2
    //   49: goto -> 53
    //   52: astore_0
    //   53: aload_2
    //   54: ifnull -> 61
    //   57: aload_2
    //   58: invokevirtual close : ()V
    //   61: aload_0
    //   62: athrow
    //   63: astore_0
    //   64: aconst_null
    //   65: astore_0
    //   66: aload_0
    //   67: ifnull -> 84
    //   70: goto -> 80
    //   73: astore_0
    //   74: aconst_null
    //   75: astore_0
    //   76: aload_0
    //   77: ifnull -> 84
    //   80: aload_0
    //   81: invokevirtual close : ()V
    //   84: iconst_1
    //   85: istore #4
    //   87: aconst_null
    //   88: astore_0
    //   89: iload #4
    //   91: ifne -> 150
    //   94: invokestatic getDefaultType : ()Ljava/lang/String;
    //   97: invokestatic getInstance : (Ljava/lang/String;)Ljava/security/KeyStore;
    //   100: astore_2
    //   101: aload_2
    //   102: aconst_null
    //   103: aconst_null
    //   104: invokevirtual load : (Ljava/io/InputStream;[C)V
    //   107: aload_2
    //   108: ldc 'ca'
    //   110: aload_0
    //   111: invokevirtual setCertificateEntry : (Ljava/lang/String;Ljava/security/cert/Certificate;)V
    //   114: invokestatic getDefaultAlgorithm : ()Ljava/lang/String;
    //   117: invokestatic getInstance : (Ljava/lang/String;)Ljavax/net/ssl/TrustManagerFactory;
    //   120: astore_0
    //   121: aload_0
    //   122: aload_2
    //   123: invokevirtual init : (Ljava/security/KeyStore;)V
    //   126: ldc 'TLS'
    //   128: invokestatic getInstance : (Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
    //   131: astore_2
    //   132: aload_2
    //   133: aconst_null
    //   134: aload_0
    //   135: invokevirtual getTrustManagers : ()[Ljavax/net/ssl/TrustManager;
    //   138: aconst_null
    //   139: invokevirtual init : ([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
    //   142: aload_2
    //   143: invokevirtual getSocketFactory : ()Ljavax/net/ssl/SSLSocketFactory;
    //   146: astore_0
    //   147: goto -> 152
    //   150: aconst_null
    //   151: astore_0
    //   152: iload #4
    //   154: ifeq -> 159
    //   157: aconst_null
    //   158: areturn
    //   159: aload_0
    //   160: areturn
    //   161: astore_2
    //   162: goto -> 76
    //   165: astore_2
    //   166: goto -> 66
    //   169: astore_0
    //   170: goto -> 34
    //   173: astore_2
    //   174: goto -> 61
    //   177: astore_0
    //   178: goto -> 84
    //   181: astore_0
    //   182: iload_1
    //   183: istore #4
    //   185: goto -> 150
    // Exception table:
    //   from	to	target	type
    //   4	20	73	java/security/cert/CertificateException
    //   4	20	63	java/io/IOException
    //   4	20	52	finally
    //   20	26	161	java/security/cert/CertificateException
    //   20	26	165	java/io/IOException
    //   20	26	42	finally
    //   30	34	169	java/io/IOException
    //   57	61	173	java/io/IOException
    //   80	84	177	java/io/IOException
    //   94	147	181	java/security/KeyStoreException
    //   94	147	181	java/security/cert/CertificateException
    //   94	147	181	java/security/NoSuchAlgorithmException
    //   94	147	181	java/security/KeyManagementException
    //   94	147	181	java/io/IOException
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\services\communication\cloudservice\SSLFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */