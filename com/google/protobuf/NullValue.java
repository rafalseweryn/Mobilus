package com.google.protobuf;

public enum NullValue implements ProtocolMessageEnum {
  NULL_VALUE(0),
  UNRECOGNIZED(-1);
  
  public static final int NULL_VALUE_VALUE = 0;
  
  private static final NullValue[] VALUES;
  
  private static final Internal.EnumLiteMap<NullValue> internalValueMap;
  
  private final int value;
  
  static {
    $VALUES = new NullValue[] { NULL_VALUE, UNRECOGNIZED };
    internalValueMap = new Internal.EnumLiteMap<NullValue>() {
        public NullValue findValueByNumber(int param1Int) {
          return NullValue.forNumber(param1Int);
        }
      };
    VALUES = values();
  }
  
  NullValue(int paramInt1) {
    this.value = paramInt1;
  }
  
  public static NullValue forNumber(int paramInt) {
    return (paramInt != 0) ? null : NULL_VALUE;
  }
  
  public static final Descriptors.EnumDescriptor getDescriptor() {
    return StructProto.getDescriptor().getEnumTypes().get(0);
  }
  
  public static Internal.EnumLiteMap<NullValue> internalGetValueMap() {
    return internalValueMap;
  }
  
  public final Descriptors.EnumDescriptor getDescriptorForType() {
    return getDescriptor();
  }
  
  public final int getNumber() {
    if (this == UNRECOGNIZED)
      throw new IllegalArgumentException("Can't get the number of an unknown enum value."); 
    return this.value;
  }
  
  public final Descriptors.EnumValueDescriptor getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\NullValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */