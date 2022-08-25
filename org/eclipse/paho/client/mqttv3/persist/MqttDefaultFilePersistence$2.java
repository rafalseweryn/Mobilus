package org.eclipse.paho.client.mqttv3.persist;

import java.io.File;
import java.io.FileFilter;

class MqttDefaultFilePersistence$2 implements FileFilter {
  public boolean accept(File paramFile) {
    return paramFile.getName().endsWith(".bup");
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv3\persist\MqttDefaultFilePersistence$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */