package com.google.protobuf;

final class ExtensionSchemas {
  private static final ExtensionSchema<?> FULL_SCHEMA;
  
  private static final ExtensionSchema<?> LITE_SCHEMA = new ExtensionSchemaLite();
  
  static {
    FULL_SCHEMA = loadSchemaForFullRuntime();
  }
  
  static ExtensionSchema<?> full() {
    if (FULL_SCHEMA == null)
      throw new IllegalStateException("Protobuf runtime is not correctly loaded."); 
    return FULL_SCHEMA;
  }
  
  static ExtensionSchema<?> lite() {
    return LITE_SCHEMA;
  }
  
  private static ExtensionSchema<?> loadSchemaForFullRuntime() {
    try {
      return Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
    } catch (Exception exception) {
      return null;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\ExtensionSchemas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */