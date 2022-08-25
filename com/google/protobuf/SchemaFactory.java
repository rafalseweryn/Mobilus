package com.google.protobuf;

interface SchemaFactory {
  <T> Schema<T> createSchema(Class<T> paramClass);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\SchemaFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */