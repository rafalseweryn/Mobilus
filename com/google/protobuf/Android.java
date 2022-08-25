package com.google.protobuf;

final class Android {
  static {
    if (getClassForName("org.robolectric.Robolectric") != null) {
      bool = true;
    } else {
      bool = false;
    } 
    IS_ROBOLECTRIC = bool;
  }
  
  private static <T> Class<T> getClassForName(String paramString) {
    try {
      return (Class)Class.forName(paramString);
    } catch (Throwable throwable) {
      return null;
    } 
  }
  
  static Class<?> getMemoryClass() {
    return MEMORY_CLASS;
  }
  
  static boolean isOnAndroidDevice() {
    boolean bool;
    if (MEMORY_CLASS != null && !IS_ROBOLECTRIC) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  static {
    boolean bool;
  }
  
  private static final boolean IS_ROBOLECTRIC;
  
  private static final Class<?> MEMORY_CLASS = getClassForName("libcore.io.Memory");
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\Android.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */