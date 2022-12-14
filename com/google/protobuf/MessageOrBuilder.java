package com.google.protobuf;

import java.util.List;
import java.util.Map;

public interface MessageOrBuilder extends MessageLiteOrBuilder {
  List<String> findInitializationErrors();
  
  Map<Descriptors.FieldDescriptor, Object> getAllFields();
  
  Message getDefaultInstanceForType();
  
  Descriptors.Descriptor getDescriptorForType();
  
  Object getField(Descriptors.FieldDescriptor paramFieldDescriptor);
  
  String getInitializationErrorString();
  
  Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor paramOneofDescriptor);
  
  Object getRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt);
  
  int getRepeatedFieldCount(Descriptors.FieldDescriptor paramFieldDescriptor);
  
  UnknownFieldSet getUnknownFields();
  
  boolean hasField(Descriptors.FieldDescriptor paramFieldDescriptor);
  
  boolean hasOneof(Descriptors.OneofDescriptor paramOneofDescriptor);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\MessageOrBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */