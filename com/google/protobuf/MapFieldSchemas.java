package com.google.protobuf;

final class MapFieldSchemas {
  private static final MapFieldSchema FULL_SCHEMA = loadSchemaForFullRuntime();
  
  private static final MapFieldSchema LITE_SCHEMA = new MapFieldSchemaLite();
  
  static MapFieldSchema full() {
    return FULL_SCHEMA;
  }
  
  static MapFieldSchema lite() {
    return LITE_SCHEMA;
  }
  
  private static MapFieldSchema loadSchemaForFullRuntime() {
    try {
      return Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
    } catch (Exception exception) {
      return null;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\MapFieldSchemas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */