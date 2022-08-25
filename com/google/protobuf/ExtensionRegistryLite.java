package com.google.protobuf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExtensionRegistryLite {
  static final ExtensionRegistryLite EMPTY_REGISTRY_LITE;
  
  static final String EXTENSION_CLASS_NAME = "com.google.protobuf.Extension";
  
  private static boolean doFullRuntimeInheritanceCheck = true;
  
  private static volatile boolean eagerlyParseMessageSets = false;
  
  private static volatile ExtensionRegistryLite emptyRegistry;
  
  private static final Class<?> extensionClass = resolveExtensionClass();
  
  private final Map<ObjectIntPair, GeneratedMessageLite.GeneratedExtension<?, ?>> extensionsByNumber;
  
  static {
    EMPTY_REGISTRY_LITE = new ExtensionRegistryLite(true);
  }
  
  ExtensionRegistryLite() {
    this.extensionsByNumber = new HashMap<>();
  }
  
  ExtensionRegistryLite(ExtensionRegistryLite paramExtensionRegistryLite) {
    if (paramExtensionRegistryLite == EMPTY_REGISTRY_LITE) {
      this.extensionsByNumber = Collections.emptyMap();
    } else {
      this.extensionsByNumber = Collections.unmodifiableMap(paramExtensionRegistryLite.extensionsByNumber);
    } 
  }
  
  ExtensionRegistryLite(boolean paramBoolean) {
    this.extensionsByNumber = Collections.emptyMap();
  }
  
  public static ExtensionRegistryLite getEmptyRegistry() {
    // Byte code:
    //   0: getstatic com/google/protobuf/ExtensionRegistryLite.emptyRegistry : Lcom/google/protobuf/ExtensionRegistryLite;
    //   3: astore_0
    //   4: aload_0
    //   5: astore_1
    //   6: aload_0
    //   7: ifnonnull -> 56
    //   10: ldc com/google/protobuf/ExtensionRegistryLite
    //   12: monitorenter
    //   13: getstatic com/google/protobuf/ExtensionRegistryLite.emptyRegistry : Lcom/google/protobuf/ExtensionRegistryLite;
    //   16: astore_0
    //   17: aload_0
    //   18: astore_1
    //   19: aload_0
    //   20: ifnonnull -> 44
    //   23: getstatic com/google/protobuf/ExtensionRegistryLite.doFullRuntimeInheritanceCheck : Z
    //   26: ifeq -> 36
    //   29: invokestatic createEmpty : ()Lcom/google/protobuf/ExtensionRegistryLite;
    //   32: astore_1
    //   33: goto -> 40
    //   36: getstatic com/google/protobuf/ExtensionRegistryLite.EMPTY_REGISTRY_LITE : Lcom/google/protobuf/ExtensionRegistryLite;
    //   39: astore_1
    //   40: aload_1
    //   41: putstatic com/google/protobuf/ExtensionRegistryLite.emptyRegistry : Lcom/google/protobuf/ExtensionRegistryLite;
    //   44: ldc com/google/protobuf/ExtensionRegistryLite
    //   46: monitorexit
    //   47: goto -> 56
    //   50: astore_1
    //   51: ldc com/google/protobuf/ExtensionRegistryLite
    //   53: monitorexit
    //   54: aload_1
    //   55: athrow
    //   56: aload_1
    //   57: areturn
    // Exception table:
    //   from	to	target	type
    //   13	17	50	finally
    //   23	33	50	finally
    //   36	40	50	finally
    //   40	44	50	finally
    //   44	47	50	finally
    //   51	54	50	finally
  }
  
  public static boolean isEagerlyParseMessageSets() {
    return eagerlyParseMessageSets;
  }
  
  public static ExtensionRegistryLite newInstance() {
    ExtensionRegistryLite extensionRegistryLite;
    if (doFullRuntimeInheritanceCheck) {
      extensionRegistryLite = ExtensionRegistryFactory.create();
    } else {
      extensionRegistryLite = new ExtensionRegistryLite();
    } 
    return extensionRegistryLite;
  }
  
  static Class<?> resolveExtensionClass() {
    try {
      return Class.forName("com.google.protobuf.Extension");
    } catch (ClassNotFoundException classNotFoundException) {
      return null;
    } 
  }
  
  public static void setEagerlyParseMessageSets(boolean paramBoolean) {
    eagerlyParseMessageSets = paramBoolean;
  }
  
  public final void add(ExtensionLite<?, ?> paramExtensionLite) {
    if (GeneratedMessageLite.GeneratedExtension.class.isAssignableFrom(paramExtensionLite.getClass()))
      add((GeneratedMessageLite.GeneratedExtension<?, ?>)paramExtensionLite); 
    if (doFullRuntimeInheritanceCheck && ExtensionRegistryFactory.isFullRegistry(this))
      try {
        getClass().getMethod("add", new Class[] { extensionClass }).invoke(this, new Object[] { paramExtensionLite });
      } catch (Exception exception) {
        throw new IllegalArgumentException(String.format("Could not invoke ExtensionRegistry#add for %s", new Object[] { paramExtensionLite }), exception);
      }  
  }
  
  public final void add(GeneratedMessageLite.GeneratedExtension<?, ?> paramGeneratedExtension) {
    this.extensionsByNumber.put(new ObjectIntPair(paramGeneratedExtension.getContainingTypeDefaultInstance(), paramGeneratedExtension.getNumber()), paramGeneratedExtension);
  }
  
  public <ContainingType extends MessageLite> GeneratedMessageLite.GeneratedExtension<ContainingType, ?> findLiteExtensionByNumber(ContainingType paramContainingType, int paramInt) {
    return (GeneratedMessageLite.GeneratedExtension<ContainingType, ?>)this.extensionsByNumber.get(new ObjectIntPair(paramContainingType, paramInt));
  }
  
  public ExtensionRegistryLite getUnmodifiable() {
    return new ExtensionRegistryLite(this);
  }
  
  private static final class ObjectIntPair {
    private final int number;
    
    private final Object object;
    
    ObjectIntPair(Object param1Object, int param1Int) {
      this.object = param1Object;
      this.number = param1Int;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = param1Object instanceof ObjectIntPair;
      boolean bool1 = false;
      if (!bool)
        return false; 
      param1Object = param1Object;
      bool = bool1;
      if (this.object == ((ObjectIntPair)param1Object).object) {
        bool = bool1;
        if (this.number == ((ObjectIntPair)param1Object).number)
          bool = true; 
      } 
      return bool;
    }
    
    public int hashCode() {
      return System.identityHashCode(this.object) * 65535 + this.number;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\ExtensionRegistryLite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */