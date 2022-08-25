package com.google.protobuf;

final class NewInstanceSchemas {
  private static final NewInstanceSchema FULL_SCHEMA = loadSchemaForFullRuntime();
  
  private static final NewInstanceSchema LITE_SCHEMA = new NewInstanceSchemaLite();
  
  static NewInstanceSchema full() {
    return FULL_SCHEMA;
  }
  
  static NewInstanceSchema lite() {
    return LITE_SCHEMA;
  }
  
  private static NewInstanceSchema loadSchemaForFullRuntime() {
    try {
      return Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
    } catch (Exception exception) {
      return null;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\NewInstanceSchemas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */