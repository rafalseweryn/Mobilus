package com.google.protobuf;

final class ExtensionRegistryFactory {
  static final Class<?> EXTENSION_REGISTRY_CLASS = reflectExtensionRegistry();
  
  static final String FULL_REGISTRY_CLASS_NAME = "com.google.protobuf.ExtensionRegistry";
  
  public static ExtensionRegistryLite create() {
    if (EXTENSION_REGISTRY_CLASS != null)
      try {
        return invokeSubclassFactory("newInstance");
      } catch (Exception exception) {} 
    return new ExtensionRegistryLite();
  }
  
  public static ExtensionRegistryLite createEmpty() {
    if (EXTENSION_REGISTRY_CLASS != null)
      try {
        return invokeSubclassFactory("getEmptyRegistry");
      } catch (Exception exception) {} 
    return ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
  }
  
  private static final ExtensionRegistryLite invokeSubclassFactory(String paramString) throws Exception {
    return (ExtensionRegistryLite)EXTENSION_REGISTRY_CLASS.getDeclaredMethod(paramString, new Class[0]).invoke(null, new Object[0]);
  }
  
  static boolean isFullRegistry(ExtensionRegistryLite paramExtensionRegistryLite) {
    boolean bool;
    if (EXTENSION_REGISTRY_CLASS != null && EXTENSION_REGISTRY_CLASS.isAssignableFrom(paramExtensionRegistryLite.getClass())) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  static Class<?> reflectExtensionRegistry() {
    try {
      return Class.forName("com.google.protobuf.ExtensionRegistry");
    } catch (ClassNotFoundException classNotFoundException) {
      return null;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\ExtensionRegistryFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */