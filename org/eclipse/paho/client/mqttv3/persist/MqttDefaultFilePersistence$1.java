package org.eclipse.paho.client.mqttv3.persist;

import java.io.File;
import java.io.FilenameFilter;

class MqttDefaultFilePersistence$1 implements FilenameFilter {
  public boolean accept(File paramFile, String paramString) {
    return paramString.endsWith(".msg");
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\persist\MqttDefaultFilePersistence$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */