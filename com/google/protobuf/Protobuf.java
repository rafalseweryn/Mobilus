package com.google.protobuf;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class Protobuf {
  private static final Protobuf INSTANCE = new Protobuf();
  
  private final ConcurrentMap<Class<?>, Schema<?>> schemaCache = new ConcurrentHashMap<>();
  
  private final SchemaFactory schemaFactory = new ManifestSchemaFactory();
  
  public static Protobuf getInstance() {
    return INSTANCE;
  }
  
  int getTotalSchemaSize() {
    Iterator<Schema> iterator = this.schemaCache.values().iterator();
    int i = 0;
    while (iterator.hasNext()) {
      Schema schema = iterator.next();
      if (schema instanceof MessageSchema)
        i += ((MessageSchema)schema).getSchemaSize(); 
    } 
    return i;
  }
  
  public <T> boolean isInitialized(T paramT) {
    return schemaFor(paramT).isInitialized(paramT);
  }
  
  public <T> void makeImmutable(T paramT) {
    schemaFor(paramT).makeImmutable(paramT);
  }
  
  public <T> void mergeFrom(T paramT, Reader paramReader) throws IOException {
    mergeFrom(paramT, paramReader, ExtensionRegistryLite.getEmptyRegistry());
  }
  
  public <T> void mergeFrom(T paramT, Reader paramReader, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    schemaFor(paramT).mergeFrom(paramT, paramReader, paramExtensionRegistryLite);
  }
  
  public Schema<?> registerSchema(Class<?> paramClass, Schema<?> paramSchema) {
    Internal.checkNotNull(paramClass, "messageType");
    Internal.checkNotNull(paramSchema, "schema");
    return this.schemaCache.putIfAbsent(paramClass, paramSchema);
  }
  
  public Schema<?> registerSchemaOverride(Class<?> paramClass, Schema<?> paramSchema) {
    Internal.checkNotNull(paramClass, "messageType");
    Internal.checkNotNull(paramSchema, "schema");
    return this.schemaCache.put(paramClass, paramSchema);
  }
  
  public <T> Schema<T> schemaFor(Class<T> paramClass) {
    Internal.checkNotNull(paramClass, "messageType");
    Schema<T> schema1 = (Schema)this.schemaCache.get(paramClass);
    Schema<T> schema2 = schema1;
    if (schema1 == null) {
      schema2 = this.schemaFactory.createSchema(paramClass);
      Schema<?> schema = registerSchema(paramClass, schema2);
      if (schema != null)
        schema2 = (Schema)schema; 
    } 
    return schema2;
  }
  
  public <T> Schema<T> schemaFor(T paramT) {
    return schemaFor((Class)paramT.getClass());
  }
  
  public <T> void writeTo(T paramT, Writer paramWriter) throws IOException {
    schemaFor(paramT).writeTo(paramT, paramWriter);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\Protobuf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */