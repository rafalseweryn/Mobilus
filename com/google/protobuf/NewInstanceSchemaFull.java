package com.google.protobuf;

final class NewInstanceSchemaFull implements NewInstanceSchema {
  public Object newInstance(Object paramObject) {
    return ((GeneratedMessageV3)paramObject).newInstance(GeneratedMessageV3.UnusedPrivateParameter.INSTANCE);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\NewInstanceSchemaFull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */