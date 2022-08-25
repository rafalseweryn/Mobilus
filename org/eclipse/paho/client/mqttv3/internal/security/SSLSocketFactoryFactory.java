package org.eclipse.paho.client.mqttv3.internal.security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.logging.Logger;

public class SSLSocketFactoryFactory {
  public static final String CIPHERSUITES = "com.ibm.ssl.enabledCipherSuites";
  
  private static final String CLASS_NAME = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
  
  public static final String CLIENTAUTH = "com.ibm.ssl.clientAuthentication";
  
  public static final String DEFAULT_PROTOCOL = "TLS";
  
  public static final String JSSEPROVIDER = "com.ibm.ssl.contextProvider";
  
  public static final String KEYSTORE = "com.ibm.ssl.keyStore";
  
  public static final String KEYSTOREMGR = "com.ibm.ssl.keyManager";
  
  public static final String KEYSTOREPROVIDER = "com.ibm.ssl.keyStoreProvider";
  
  public static final String KEYSTOREPWD = "com.ibm.ssl.keyStorePassword";
  
  public static final String KEYSTORETYPE = "com.ibm.ssl.keyStoreType";
  
  public static final String SSLPROTOCOL = "com.ibm.ssl.protocol";
  
  public static final String SYSKEYMGRALGO = "ssl.KeyManagerFactory.algorithm";
  
  public static final String SYSKEYSTORE = "javax.net.ssl.keyStore";
  
  public static final String SYSKEYSTOREPWD = "javax.net.ssl.keyStorePassword";
  
  public static final String SYSKEYSTORETYPE = "javax.net.ssl.keyStoreType";
  
  public static final String SYSTRUSTMGRALGO = "ssl.TrustManagerFactory.algorithm";
  
  public static final String SYSTRUSTSTORE = "javax.net.ssl.trustStore";
  
  public static final String SYSTRUSTSTOREPWD = "javax.net.ssl.trustStorePassword";
  
  public static final String SYSTRUSTSTORETYPE = "javax.net.ssl.trustStoreType";
  
  public static final String TRUSTSTORE = "com.ibm.ssl.trustStore";
  
  public static final String TRUSTSTOREMGR = "com.ibm.ssl.trustManager";
  
  public static final String TRUSTSTOREPROVIDER = "com.ibm.ssl.trustStoreProvider";
  
  public static final String TRUSTSTOREPWD = "com.ibm.ssl.trustStorePassword";
  
  public static final String TRUSTSTORETYPE = "com.ibm.ssl.trustStoreType";
  
  private static final byte[] key;
  
  private static final String[] propertyKeys = new String[] { 
      "com.ibm.ssl.protocol", "com.ibm.ssl.contextProvider", "com.ibm.ssl.keyStore", "com.ibm.ssl.keyStorePassword", "com.ibm.ssl.keyStoreType", "com.ibm.ssl.keyStoreProvider", "com.ibm.ssl.keyManager", "com.ibm.ssl.trustStore", "com.ibm.ssl.trustStorePassword", "com.ibm.ssl.trustStoreType", 
      "com.ibm.ssl.trustStoreProvider", "com.ibm.ssl.trustManager", "com.ibm.ssl.enabledCipherSuites", "com.ibm.ssl.clientAuthentication" };
  
  private static final String xorTag = "{xor}";
  
  private Hashtable configs = new Hashtable<>();
  
  private Properties defaultProperties;
  
  private Logger logger = null;
  
  static {
    key = new byte[] { -99, -89, -39, Byte.MIN_VALUE, 5, -72, -119, -100 };
  }
  
  public SSLSocketFactoryFactory() {}
  
  public SSLSocketFactoryFactory(Logger paramLogger) {
    this();
    this.logger = paramLogger;
  }
  
  private void checkPropertyKeys(Properties paramProperties) throws IllegalArgumentException {
    Iterator<String> iterator = paramProperties.keySet().iterator();
    while (true) {
      if (!iterator.hasNext())
        return; 
      String str = iterator.next();
      if (!keyValid(str)) {
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(str));
        stringBuffer.append(" is not a valid IBM SSL property key.");
        throw new IllegalArgumentException(stringBuffer.toString());
      } 
    } 
  }
  
  private void convertPassword(Properties paramProperties) {
    String str = paramProperties.getProperty("com.ibm.ssl.keyStorePassword");
    if (str != null && !str.startsWith("{xor}"))
      paramProperties.put("com.ibm.ssl.keyStorePassword", obfuscate(str.toCharArray())); 
    str = paramProperties.getProperty("com.ibm.ssl.trustStorePassword");
    if (str != null && !str.startsWith("{xor}"))
      paramProperties.put("com.ibm.ssl.trustStorePassword", obfuscate(str.toCharArray())); 
  }
  
  public static char[] deObfuscate(String paramString) {
    if (paramString == null)
      return null; 
    try {
      byte[] arrayOfByte = SimpleBase64Encoder.decode(paramString.substring("{xor}".length()));
      for (byte b = 0;; b++) {
        if (b >= arrayOfByte.length)
          return toChar(arrayOfByte); 
        arrayOfByte[b] = (byte)(byte)((arrayOfByte[b] ^ key[b % key.length]) & 0xFF);
      } 
    } catch (Exception exception) {
      return null;
    } 
  }
  
  private String getProperty(String paramString1, String paramString2, String paramString3) {
    paramString1 = getPropertyFromConfig(paramString1, paramString2);
    if (paramString1 != null)
      return paramString1; 
    if (paramString3 != null)
      paramString1 = System.getProperty(paramString3); 
    return paramString1;
  }
  
  private String getPropertyFromConfig(String paramString1, String paramString2) {
    String str = null;
    if (paramString1 != null) {
      properties = (Properties)this.configs.get(paramString1);
    } else {
      properties = null;
    } 
    paramString1 = str;
    if (properties != null) {
      String str1 = properties.getProperty(paramString2);
      paramString1 = str1;
      if (str1 != null)
        return str1; 
    } 
    Properties properties = this.defaultProperties;
    if (properties != null) {
      paramString2 = properties.getProperty(paramString2);
      paramString1 = paramString2;
      if (paramString2 != null)
        return paramString2; 
    } 
    return paramString1;
  }
  
  private SSLContext getSSLContext(String paramString) throws MqttSecurityException {
    MqttSecurityException mqttSecurityException;
    SSLContext sSLContext;
    String str2 = paramString;
    String str3 = getSSLProtocol(paramString);
    String str4 = str3;
    if (str3 == null)
      str4 = "TLS"; 
    if (this.logger != null) {
      Logger logger = this.logger;
      if (str2 != null) {
        str3 = str2;
      } else {
        str3 = "null (broker defaults)";
      } 
      logger.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12000", new Object[] { str3, str4 });
    } 
    str3 = getJSSEProvider(paramString);
    if (str3 == null) {
      try {
        sSLContext = SSLContext.getInstance(str4);
      } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
      
      } catch (NoSuchProviderException noSuchProviderException) {
      
      } catch (KeyManagementException keyManagementException) {}
    } else {
      sSLContext = SSLContext.getInstance(str4, str3);
    } 
    if (this.logger != null) {
      Logger logger = this.logger;
      if (str2 != null) {
        str4 = str2;
      } else {
        str4 = "null (broker defaults)";
      } 
      logger.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12001", new Object[] { str4, sSLContext.getProvider().getName() });
    } 
    str3 = getProperty(str2, "com.ibm.ssl.keyStore", null);
    str4 = str3;
    if (str3 == null)
      str4 = getProperty(str2, "com.ibm.ssl.keyStore", "javax.net.ssl.keyStore"); 
    if (this.logger != null) {
      String str;
      Logger logger = this.logger;
      if (str2 != null) {
        str3 = str2;
      } else {
        str3 = "null (broker defaults)";
      } 
      if (str4 != null) {
        str = str4;
      } else {
        str = "null";
      } 
      logger.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12004", new Object[] { str3, str });
    } 
    char[] arrayOfChar1 = getKeyStorePassword((String)keyManagementException);
    if (this.logger != null) {
      String str;
      Logger logger = this.logger;
      if (str2 != null) {
        str3 = str2;
      } else {
        str3 = "null (broker defaults)";
      } 
      if (arrayOfChar1 != null) {
        str = obfuscate(arrayOfChar1);
      } else {
        str = "null";
      } 
      logger.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12005", new Object[] { str3, str });
    } 
    String str5 = getKeyStoreType((String)keyManagementException);
    str3 = str5;
    if (str5 == null)
      str3 = KeyStore.getDefaultType(); 
    if (this.logger != null) {
      String str;
      Logger logger = this.logger;
      if (str2 != null) {
        str5 = str2;
      } else {
        str5 = "null (broker defaults)";
      } 
      if (str3 != null) {
        str = str3;
      } else {
        str = "null";
      } 
      logger.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12006", new Object[] { str5, str });
    } 
    String str6 = KeyManagerFactory.getDefaultAlgorithm();
    String str8 = getKeyStoreProvider((String)keyManagementException);
    str5 = getKeyManager((String)keyManagementException);
    if (str5 == null)
      str5 = str6; 
    if (str4 != null && str3 != null && str5 != null) {
      try {
        KeyManagerFactory keyManagerFactory;
        KeyStore keyStore = KeyStore.getInstance(str3);
        FileInputStream fileInputStream = new FileInputStream();
        this(str4);
        keyStore.load(fileInputStream, arrayOfChar1);
        if (str8 != null) {
          keyManagerFactory = KeyManagerFactory.getInstance(str5, str8);
        } else {
          keyManagerFactory = KeyManagerFactory.getInstance(str5);
        } 
        if (this.logger != null) {
          String str;
          Logger logger2 = this.logger;
          if (str2 != null) {
            str = str2;
          } else {
            str = "null (broker defaults)";
          } 
          if (str5 == null)
            str5 = "null"; 
          logger2.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12010", new Object[] { str, str5 });
          Logger logger1 = this.logger;
          if (str2 != null) {
            str = str2;
          } else {
            str = "null (broker defaults)";
          } 
          logger1.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12009", new Object[] { str, keyManagerFactory.getProvider().getName() });
        } 
        keyManagerFactory.init(keyStore, arrayOfChar1);
        KeyManager[] arrayOfKeyManager = keyManagerFactory.getKeyManagers();
      } catch (KeyStoreException keyStoreException) {
        mqttSecurityException = new MqttSecurityException();
        this(keyStoreException);
        throw mqttSecurityException;
      } catch (CertificateException certificateException) {
        mqttSecurityException = new MqttSecurityException();
        this(certificateException);
        throw mqttSecurityException;
      } catch (FileNotFoundException fileNotFoundException) {
        mqttSecurityException = new MqttSecurityException();
        this(fileNotFoundException);
        throw mqttSecurityException;
      } catch (IOException iOException) {
        MqttSecurityException mqttSecurityException1 = new MqttSecurityException();
        this(iOException);
        throw mqttSecurityException1;
      } catch (UnrecoverableKeyException unrecoverableKeyException) {
        mqttSecurityException = new MqttSecurityException();
        this(unrecoverableKeyException);
        throw mqttSecurityException;
      } 
    } else {
      str3 = null;
    } 
    String str7 = getTrustStore((String)mqttSecurityException);
    if (this.logger != null) {
      Logger logger = this.logger;
      if (unrecoverableKeyException != null) {
        UnrecoverableKeyException unrecoverableKeyException1 = unrecoverableKeyException;
      } else {
        str4 = "null (broker defaults)";
      } 
      if (str7 != null) {
        str5 = str7;
      } else {
        str5 = "null";
      } 
      logger.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12011", new Object[] { str4, str5 });
    } 
    char[] arrayOfChar2 = getTrustStorePassword((String)mqttSecurityException);
    if (this.logger != null) {
      Logger logger = this.logger;
      if (unrecoverableKeyException != null) {
        UnrecoverableKeyException unrecoverableKeyException1 = unrecoverableKeyException;
      } else {
        str4 = "null (broker defaults)";
      } 
      if (arrayOfChar2 != null) {
        str5 = obfuscate(arrayOfChar2);
      } else {
        str5 = "null";
      } 
      logger.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12012", new Object[] { str4, str5 });
    } 
    str5 = getTrustStoreType((String)mqttSecurityException);
    str4 = str5;
    if (str5 == null)
      str4 = KeyStore.getDefaultType(); 
    if (this.logger != null) {
      Logger logger = this.logger;
      if (unrecoverableKeyException != null) {
        UnrecoverableKeyException unrecoverableKeyException1 = unrecoverableKeyException;
      } else {
        str5 = "null (broker defaults)";
      } 
      if (str4 != null) {
        str6 = str4;
      } else {
        str6 = "null";
      } 
      logger.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12013", new Object[] { str5, str6 });
    } 
    str5 = TrustManagerFactory.getDefaultAlgorithm();
    String str9 = getTrustStoreProvider((String)mqttSecurityException);
    String str1 = getTrustManager((String)mqttSecurityException);
    if (str1 == null)
      str1 = str5; 
    if (str7 != null && str4 != null && str1 != null) {
      try {
        TrustManagerFactory trustManagerFactory;
        KeyStore keyStore = KeyStore.getInstance(str4);
        FileInputStream fileInputStream = new FileInputStream();
        this(str7);
        keyStore.load(fileInputStream, arrayOfChar2);
        if (str9 != null) {
          trustManagerFactory = TrustManagerFactory.getInstance(str1, str9);
        } else {
          trustManagerFactory = TrustManagerFactory.getInstance(str1);
        } 
        if (this.logger != null) {
          String str;
          Logger logger2 = this.logger;
          if (unrecoverableKeyException != null) {
            UnrecoverableKeyException unrecoverableKeyException1 = unrecoverableKeyException;
          } else {
            str5 = "null (broker defaults)";
          } 
          if (str1 == null)
            str1 = "null"; 
          logger2.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12017", new Object[] { str5, str1 });
          Logger logger1 = this.logger;
          if (unrecoverableKeyException == null)
            str = "null (broker defaults)"; 
          logger1.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12016", new Object[] { str, trustManagerFactory.getProvider().getName() });
        } 
        trustManagerFactory.init(keyStore);
        TrustManager[] arrayOfTrustManager = trustManagerFactory.getTrustManagers();
      } catch (KeyStoreException keyStoreException) {
        MqttSecurityException mqttSecurityException1 = new MqttSecurityException();
        this(keyStoreException);
        throw mqttSecurityException1;
      } catch (CertificateException certificateException) {
        MqttSecurityException mqttSecurityException1 = new MqttSecurityException();
        this(certificateException);
        throw mqttSecurityException1;
      } catch (FileNotFoundException fileNotFoundException) {
        MqttSecurityException mqttSecurityException1 = new MqttSecurityException();
        this(fileNotFoundException);
        throw mqttSecurityException1;
      } catch (IOException iOException) {
        MqttSecurityException mqttSecurityException1 = new MqttSecurityException();
        this(iOException);
        throw mqttSecurityException1;
      } 
    } else {
      str1 = null;
    } 
    sSLContext.init((KeyManager[])str3, (TrustManager[])str1, null);
    return sSLContext;
  }
  
  public static boolean isSupportedOnJVM() throws LinkageError, ExceptionInInitializerError {
    try {
      Class.forName("javax.net.ssl.SSLServerSocketFactory");
      return true;
    } catch (ClassNotFoundException classNotFoundException) {
      return false;
    } 
  }
  
  private boolean keyValid(String paramString) {
    boolean bool = false;
    for (byte b = 0;; b++) {
      if (b >= propertyKeys.length || propertyKeys[b].equals(paramString)) {
        if (b < propertyKeys.length)
          bool = true; 
        return bool;
      } 
    } 
  }
  
  public static String obfuscate(char[] paramArrayOfchar) {
    if (paramArrayOfchar == null)
      return null; 
    byte[] arrayOfByte = toByte(paramArrayOfchar);
    for (byte b = 0;; b++) {
      if (b >= arrayOfByte.length) {
        StringBuffer stringBuffer = new StringBuffer("{xor}");
        stringBuffer.append(new String(SimpleBase64Encoder.encode(arrayOfByte)));
        return stringBuffer.toString();
      } 
      arrayOfByte[b] = (byte)(byte)((arrayOfByte[b] ^ key[b % key.length]) & 0xFF);
    } 
  }
  
  public static String packCipherSuites(String[] paramArrayOfString) {
    if (paramArrayOfString != null) {
      StringBuffer stringBuffer = new StringBuffer();
      for (byte b = 0;; b++) {
        String str;
        if (b >= paramArrayOfString.length) {
          str = stringBuffer.toString();
          break;
        } 
        stringBuffer.append(str[b]);
        if (b < str.length - 1)
          stringBuffer.append(','); 
      } 
    } else {
      paramArrayOfString = null;
    } 
    return (String)paramArrayOfString;
  }
  
  public static byte[] toByte(char[] paramArrayOfchar) {
    if (paramArrayOfchar == null)
      return null; 
    byte[] arrayOfByte = new byte[paramArrayOfchar.length * 2];
    byte b = 0;
    int i = 0;
    while (true) {
      if (b >= paramArrayOfchar.length)
        return arrayOfByte; 
      int j = i + 1;
      arrayOfByte[i] = (byte)(byte)(paramArrayOfchar[b] & 0xFF);
      i = j + 1;
      arrayOfByte[j] = (byte)(byte)(paramArrayOfchar[b] >> 8 & 0xFF);
      b++;
    } 
  }
  
  public static char[] toChar(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte == null)
      return null; 
    char[] arrayOfChar = new char[paramArrayOfbyte.length / 2];
    int i = 0;
    byte b = 0;
    while (true) {
      if (i >= paramArrayOfbyte.length)
        return arrayOfChar; 
      int j = i + 1;
      arrayOfChar[b] = (char)(char)((paramArrayOfbyte[i] & 0xFF) + ((paramArrayOfbyte[j] & 0xFF) << 8));
      b++;
      i = j + 1;
    } 
  }
  
  public static String[] unpackCipherSuites(String paramString) {
    if (paramString == null)
      return null; 
    Vector<String> vector = new Vector();
    int i = paramString.indexOf(',');
    int j = 0;
    while (true) {
      String[] arrayOfString;
      if (i <= -1) {
        vector.add(paramString.substring(j));
        arrayOfString = new String[vector.size()];
        vector.toArray(arrayOfString);
        return arrayOfString;
      } 
      vector.add(arrayOfString.substring(j, i));
      j = i + 1;
      i = arrayOfString.indexOf(',', j);
    } 
  }
  
  public SSLSocketFactory createSocketFactory(String paramString) throws MqttSecurityException {
    SSLContext sSLContext = getSSLContext(paramString);
    if (this.logger != null) {
      String str;
      Logger logger = this.logger;
      if (paramString != null) {
        str = paramString;
      } else {
        str = "null (broker defaults)";
      } 
      if (getEnabledCipherSuites(paramString) != null) {
        paramString = getProperty(paramString, "com.ibm.ssl.enabledCipherSuites", null);
      } else {
        paramString = "null (using platform-enabled cipher suites)";
      } 
      logger.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "createSocketFactory", "12020", new Object[] { str, paramString });
    } 
    return sSLContext.getSocketFactory();
  }
  
  public boolean getClientAuthentication(String paramString) {
    boolean bool;
    paramString = getProperty(paramString, "com.ibm.ssl.clientAuthentication", null);
    if (paramString != null) {
      bool = Boolean.valueOf(paramString).booleanValue();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Properties getConfiguration(String paramString) {
    Properties properties;
    if (paramString == null) {
      properties = this.defaultProperties;
    } else {
      properties = (Properties)this.configs.get(properties);
    } 
    return properties;
  }
  
  public String[] getEnabledCipherSuites(String paramString) {
    return unpackCipherSuites(getProperty(paramString, "com.ibm.ssl.enabledCipherSuites", null));
  }
  
  public String getJSSEProvider(String paramString) {
    return getProperty(paramString, "com.ibm.ssl.contextProvider", null);
  }
  
  public String getKeyManager(String paramString) {
    return getProperty(paramString, "com.ibm.ssl.keyManager", "ssl.KeyManagerFactory.algorithm");
  }
  
  public String getKeyStore(String paramString) {
    paramString = getPropertyFromConfig(paramString, "com.ibm.ssl.keyStore");
    if (paramString != null)
      return paramString; 
    if ("javax.net.ssl.keyStore" != null)
      paramString = System.getProperty("javax.net.ssl.keyStore"); 
    return paramString;
  }
  
  public char[] getKeyStorePassword(String paramString) {
    paramString = getProperty(paramString, "com.ibm.ssl.keyStorePassword", "javax.net.ssl.keyStorePassword");
    if (paramString != null) {
      char[] arrayOfChar;
      if (paramString.startsWith("{xor}")) {
        arrayOfChar = deObfuscate(paramString);
      } else {
        arrayOfChar = arrayOfChar.toCharArray();
      } 
    } else {
      paramString = null;
    } 
    return (char[])paramString;
  }
  
  public String getKeyStoreProvider(String paramString) {
    return getProperty(paramString, "com.ibm.ssl.keyStoreProvider", null);
  }
  
  public String getKeyStoreType(String paramString) {
    return getProperty(paramString, "com.ibm.ssl.keyStoreType", "javax.net.ssl.keyStoreType");
  }
  
  public String getSSLProtocol(String paramString) {
    return getProperty(paramString, "com.ibm.ssl.protocol", null);
  }
  
  public String getTrustManager(String paramString) {
    return getProperty(paramString, "com.ibm.ssl.trustManager", "ssl.TrustManagerFactory.algorithm");
  }
  
  public String getTrustStore(String paramString) {
    return getProperty(paramString, "com.ibm.ssl.trustStore", "javax.net.ssl.trustStore");
  }
  
  public char[] getTrustStorePassword(String paramString) {
    paramString = getProperty(paramString, "com.ibm.ssl.trustStorePassword", "javax.net.ssl.trustStorePassword");
    if (paramString != null) {
      char[] arrayOfChar;
      if (paramString.startsWith("{xor}")) {
        arrayOfChar = deObfuscate(paramString);
      } else {
        arrayOfChar = arrayOfChar.toCharArray();
      } 
    } else {
      paramString = null;
    } 
    return (char[])paramString;
  }
  
  public String getTrustStoreProvider(String paramString) {
    return getProperty(paramString, "com.ibm.ssl.trustStoreProvider", null);
  }
  
  public String getTrustStoreType(String paramString) {
    return getProperty(paramString, "com.ibm.ssl.trustStoreType", null);
  }
  
  public void initialize(Properties paramProperties, String paramString) throws IllegalArgumentException {
    checkPropertyKeys(paramProperties);
    Properties properties = new Properties();
    properties.putAll(paramProperties);
    convertPassword(properties);
    if (paramString != null) {
      this.configs.put(paramString, properties);
    } else {
      this.defaultProperties = properties;
    } 
  }
  
  public void merge(Properties paramProperties, String paramString) throws IllegalArgumentException {
    checkPropertyKeys(paramProperties);
    Properties properties1 = this.defaultProperties;
    if (paramString != null)
      properties1 = (Properties)this.configs.get(paramString); 
    Properties properties2 = properties1;
    if (properties1 == null)
      properties2 = new Properties(); 
    convertPassword(paramProperties);
    properties2.putAll(paramProperties);
    if (paramString != null) {
      this.configs.put(paramString, properties2);
    } else {
      this.defaultProperties = properties2;
    } 
  }
  
  public boolean remove(String paramString) {
    null = true;
    if (paramString != null) {
      if (this.configs.remove(paramString) != null)
        return null; 
    } else if (this.defaultProperties != null) {
      this.defaultProperties = null;
      return null;
    } 
    return false;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\internal\security\SSLSocketFactoryFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */