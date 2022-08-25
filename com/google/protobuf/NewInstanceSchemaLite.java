package com.google.protobuf;

final class NewInstanceSchemaLite implements NewInstanceSchema {
  public Object newInstance(Object paramObject) {
    return ((GeneratedMessageLite)paramObject).dynamicMethod(GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\NewInstanceSchemaLite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */