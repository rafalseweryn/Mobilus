package com.google.protobuf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class ExtensionSchemaLite extends ExtensionSchema<GeneratedMessageLite.ExtensionDescriptor> {
  int extensionNumber(Map.Entry<?, ?> paramEntry) {
    return ((GeneratedMessageLite.ExtensionDescriptor)paramEntry.getKey()).getNumber();
  }
  
  Object findExtensionByNumber(ExtensionRegistryLite paramExtensionRegistryLite, MessageLite paramMessageLite, int paramInt) {
    return paramExtensionRegistryLite.findLiteExtensionByNumber(paramMessageLite, paramInt);
  }
  
  FieldSet<GeneratedMessageLite.ExtensionDescriptor> getExtensions(Object paramObject) {
    return ((GeneratedMessageLite.ExtendableMessage)paramObject).extensions;
  }
  
  FieldSet<GeneratedMessageLite.ExtensionDescriptor> getMutableExtensions(Object paramObject) {
    return ((GeneratedMessageLite.ExtendableMessage)paramObject).ensureExtensionsAreMutable();
  }
  
  boolean hasExtensions(MessageLite paramMessageLite) {
    return paramMessageLite instanceof GeneratedMessageLite.ExtendableMessage;
  }
  
  void makeImmutable(Object paramObject) {
    getExtensions(paramObject).makeImmutable();
  }
  
  <UT, UB> UB parseExtension(Reader paramReader, Object paramObject, ExtensionRegistryLite paramExtensionRegistryLite, FieldSet<GeneratedMessageLite.ExtensionDescriptor> paramFieldSet, UB paramUB, UnknownFieldSchema<UT, UB> paramUnknownFieldSchema) throws IOException {
    Object object;
    GeneratedMessageLite.GeneratedExtension generatedExtension = (GeneratedMessageLite.GeneratedExtension)paramObject;
    int i = generatedExtension.getNumber();
    if (generatedExtension.descriptor.isRepeated() && generatedExtension.descriptor.isPacked()) {
      StringBuilder stringBuilder;
      switch (generatedExtension.getLiteType()) {
        default:
          stringBuilder = new StringBuilder();
          stringBuilder.append("Type cannot be packed: ");
          stringBuilder.append(generatedExtension.descriptor.getLiteType());
          throw new IllegalStateException(stringBuilder.toString());
        case ENUM:
          paramObject = new ArrayList();
          stringBuilder.readEnumList((List<Integer>)paramObject);
          paramUB = SchemaUtil.filterUnknownEnumList(i, (List<Integer>)paramObject, generatedExtension.descriptor.getEnumType(), paramUB, paramUnknownFieldSchema);
          object = paramObject;
          break;
        case SINT64:
          paramObject = new ArrayList();
          object.readSInt64List((List<Long>)paramObject);
          object = paramObject;
          break;
        case SINT32:
          paramObject = new ArrayList();
          object.readSInt32List((List<Integer>)paramObject);
          object = paramObject;
          break;
        case SFIXED64:
          paramObject = new ArrayList();
          object.readSFixed64List((List<Long>)paramObject);
          object = paramObject;
          break;
        case SFIXED32:
          paramObject = new ArrayList();
          object.readSFixed32List((List<Integer>)paramObject);
          object = paramObject;
          break;
        case UINT32:
          paramObject = new ArrayList();
          object.readUInt32List((List<Integer>)paramObject);
          object = paramObject;
          break;
        case BOOL:
          paramObject = new ArrayList();
          object.readBoolList((List<Boolean>)paramObject);
          object = paramObject;
          break;
        case FIXED32:
          paramObject = new ArrayList();
          object.readFixed32List((List<Integer>)paramObject);
          object = paramObject;
          break;
        case FIXED64:
          paramObject = new ArrayList();
          object.readFixed64List((List<Long>)paramObject);
          object = paramObject;
          break;
        case INT32:
          paramObject = new ArrayList();
          object.readInt32List((List<Integer>)paramObject);
          object = paramObject;
          break;
        case UINT64:
          paramObject = new ArrayList();
          object.readUInt64List((List<Long>)paramObject);
          object = paramObject;
          break;
        case INT64:
          paramObject = new ArrayList();
          object.readInt64List((List<Long>)paramObject);
          object = paramObject;
          break;
        case FLOAT:
          paramObject = new ArrayList();
          object.readFloatList((List<Float>)paramObject);
          object = paramObject;
          break;
        case DOUBLE:
          paramObject = new ArrayList();
          object.readDoubleList((List<Double>)paramObject);
          object = paramObject;
          break;
      } 
      paramFieldSet.setField(generatedExtension.descriptor, object);
    } else {
      paramObject = null;
      if (generatedExtension.getLiteType() == WireFormat.FieldType.ENUM) {
        int j = object.readInt32();
        if (generatedExtension.descriptor.getEnumType().findValueByNumber(j) == null)
          return SchemaUtil.storeUnknownEnum(i, j, paramUB, paramUnknownFieldSchema); 
        object = Integer.valueOf(j);
      } else {
        switch (generatedExtension.getLiteType()) {
          default:
            object = paramObject;
            break;
          case MESSAGE:
            object = object.readMessage(generatedExtension.getMessageDefaultInstance().getClass(), paramExtensionRegistryLite);
            break;
          case GROUP:
            object = object.readGroup(generatedExtension.getMessageDefaultInstance().getClass(), paramExtensionRegistryLite);
            break;
          case STRING:
            object = object.readString();
            break;
          case BYTES:
            object = object.readBytes();
            break;
          case ENUM:
            throw new IllegalStateException("Shouldn't reach here.");
          case SINT64:
            object = Long.valueOf(object.readSInt64());
            break;
          case SINT32:
            object = Integer.valueOf(object.readSInt32());
            break;
          case SFIXED64:
            object = Long.valueOf(object.readSFixed64());
            break;
          case SFIXED32:
            object = Integer.valueOf(object.readSFixed32());
            break;
          case UINT32:
            object = Integer.valueOf(object.readUInt32());
            break;
          case BOOL:
            object = Boolean.valueOf(object.readBool());
            break;
          case FIXED32:
            object = Integer.valueOf(object.readFixed32());
            break;
          case FIXED64:
            object = Long.valueOf(object.readFixed64());
            break;
          case INT32:
            object = Integer.valueOf(object.readInt32());
            break;
          case UINT64:
            object = Long.valueOf(object.readUInt64());
            break;
          case INT64:
            object = Long.valueOf(object.readInt64());
            break;
          case FLOAT:
            object = Float.valueOf(object.readFloat());
            break;
          case DOUBLE:
            object = Double.valueOf(object.readDouble());
            break;
        } 
      } 
      if (generatedExtension.isRepeated()) {
        paramFieldSet.addRepeatedField(generatedExtension.descriptor, object);
      } else {
        Object object1;
        switch (generatedExtension.getLiteType()) {
          default:
            paramObject = object;
            break;
          case GROUP:
          case MESSAGE:
            object1 = paramFieldSet.getField(generatedExtension.descriptor);
            paramObject = object;
            if (object1 != null)
              paramObject = Internal.mergeMessage(object1, object); 
            break;
        } 
        paramFieldSet.setField(generatedExtension.descriptor, paramObject);
      } 
    } 
    return paramUB;
  }
  
  void parseLengthPrefixedMessageSetItem(Reader paramReader, Object paramObject, ExtensionRegistryLite paramExtensionRegistryLite, FieldSet<GeneratedMessageLite.ExtensionDescriptor> paramFieldSet) throws IOException {
    paramObject = paramObject;
    paramReader = paramReader.readMessage(paramObject.getMessageDefaultInstance().getClass(), paramExtensionRegistryLite);
    paramFieldSet.setField(((GeneratedMessageLite.GeneratedExtension)paramObject).descriptor, paramReader);
  }
  
  void parseMessageSetItem(ByteString paramByteString, Object paramObject, ExtensionRegistryLite paramExtensionRegistryLite, FieldSet<GeneratedMessageLite.ExtensionDescriptor> paramFieldSet) throws IOException {
    paramObject = paramObject;
    MessageLite messageLite = paramObject.getMessageDefaultInstance().newBuilderForType().buildPartial();
    BinaryReader binaryReader = BinaryReader.newInstance(ByteBuffer.wrap(paramByteString.toByteArray()), true);
    Protobuf.getInstance().mergeFrom(messageLite, binaryReader, paramExtensionRegistryLite);
    paramFieldSet.setField(((GeneratedMessageLite.GeneratedExtension)paramObject).descriptor, messageLite);
    if (binaryReader.getFieldNumber() != Integer.MAX_VALUE)
      throw InvalidProtocolBufferException.invalidEndTag(); 
  }
  
  void serializeExtension(Writer paramWriter, Map.Entry<?, ?> paramEntry) throws IOException {
    GeneratedMessageLite.ExtensionDescriptor extensionDescriptor = (GeneratedMessageLite.ExtensionDescriptor)paramEntry.getKey();
    if (extensionDescriptor.isRepeated()) {
      List<E> list;
      switch (extensionDescriptor.getLiteType()) {
        default:
          return;
        case MESSAGE:
          list = (List)paramEntry.getValue();
          if (list != null && !list.isEmpty())
            SchemaUtil.writeMessageList(extensionDescriptor.getNumber(), (List)paramEntry.getValue(), paramWriter, Protobuf.getInstance().schemaFor(list.get(0).getClass())); 
        case GROUP:
          list = (List<E>)paramEntry.getValue();
          if (list != null && !list.isEmpty())
            SchemaUtil.writeGroupList(extensionDescriptor.getNumber(), (List)paramEntry.getValue(), paramWriter, Protobuf.getInstance().schemaFor(list.get(0).getClass())); 
        case STRING:
          SchemaUtil.writeStringList(extensionDescriptor.getNumber(), (List<String>)paramEntry.getValue(), paramWriter);
        case BYTES:
          SchemaUtil.writeBytesList(extensionDescriptor.getNumber(), (List<ByteString>)paramEntry.getValue(), paramWriter);
        case ENUM:
          SchemaUtil.writeInt32List(extensionDescriptor.getNumber(), (List<Integer>)paramEntry.getValue(), paramWriter, extensionDescriptor.isPacked());
        case SINT64:
          SchemaUtil.writeSInt64List(extensionDescriptor.getNumber(), (List<Long>)paramEntry.getValue(), paramWriter, extensionDescriptor.isPacked());
        case SINT32:
          SchemaUtil.writeSInt32List(extensionDescriptor.getNumber(), (List<Integer>)paramEntry.getValue(), paramWriter, extensionDescriptor.isPacked());
        case SFIXED64:
          SchemaUtil.writeSFixed64List(extensionDescriptor.getNumber(), (List<Long>)paramEntry.getValue(), paramWriter, extensionDescriptor.isPacked());
        case SFIXED32:
          SchemaUtil.writeSFixed32List(extensionDescriptor.getNumber(), (List<Integer>)paramEntry.getValue(), paramWriter, extensionDescriptor.isPacked());
        case UINT32:
          SchemaUtil.writeUInt32List(extensionDescriptor.getNumber(), (List<Integer>)paramEntry.getValue(), paramWriter, extensionDescriptor.isPacked());
        case BOOL:
          SchemaUtil.writeBoolList(extensionDescriptor.getNumber(), (List<Boolean>)paramEntry.getValue(), paramWriter, extensionDescriptor.isPacked());
        case FIXED32:
          SchemaUtil.writeFixed32List(extensionDescriptor.getNumber(), (List<Integer>)paramEntry.getValue(), paramWriter, extensionDescriptor.isPacked());
        case FIXED64:
          SchemaUtil.writeFixed64List(extensionDescriptor.getNumber(), (List<Long>)paramEntry.getValue(), paramWriter, extensionDescriptor.isPacked());
        case INT32:
          SchemaUtil.writeInt32List(extensionDescriptor.getNumber(), (List<Integer>)paramEntry.getValue(), paramWriter, extensionDescriptor.isPacked());
        case UINT64:
          SchemaUtil.writeUInt64List(extensionDescriptor.getNumber(), (List<Long>)paramEntry.getValue(), paramWriter, extensionDescriptor.isPacked());
        case INT64:
          SchemaUtil.writeInt64List(extensionDescriptor.getNumber(), (List<Long>)paramEntry.getValue(), paramWriter, extensionDescriptor.isPacked());
        case FLOAT:
          SchemaUtil.writeFloatList(extensionDescriptor.getNumber(), (List<Float>)paramEntry.getValue(), paramWriter, extensionDescriptor.isPacked());
        case DOUBLE:
          break;
      } 
      SchemaUtil.writeDoubleList(extensionDescriptor.getNumber(), (List<Double>)paramEntry.getValue(), paramWriter, extensionDescriptor.isPacked());
    } 
    switch (extensionDescriptor.getLiteType()) {
      default:
      
      case MESSAGE:
        paramWriter.writeMessage(extensionDescriptor.getNumber(), paramEntry.getValue(), Protobuf.getInstance().schemaFor(paramEntry.getValue().getClass()));
      case GROUP:
        paramWriter.writeGroup(extensionDescriptor.getNumber(), paramEntry.getValue(), Protobuf.getInstance().schemaFor(paramEntry.getValue().getClass()));
      case STRING:
        paramWriter.writeString(extensionDescriptor.getNumber(), (String)paramEntry.getValue());
      case BYTES:
        paramWriter.writeBytes(extensionDescriptor.getNumber(), (ByteString)paramEntry.getValue());
      case ENUM:
        paramWriter.writeInt32(extensionDescriptor.getNumber(), ((Integer)paramEntry.getValue()).intValue());
      case SINT64:
        paramWriter.writeSInt64(extensionDescriptor.getNumber(), ((Long)paramEntry.getValue()).longValue());
      case SINT32:
        paramWriter.writeSInt32(extensionDescriptor.getNumber(), ((Integer)paramEntry.getValue()).intValue());
      case SFIXED64:
        paramWriter.writeSFixed64(extensionDescriptor.getNumber(), ((Long)paramEntry.getValue()).longValue());
      case SFIXED32:
        paramWriter.writeSFixed32(extensionDescriptor.getNumber(), ((Integer)paramEntry.getValue()).intValue());
      case UINT32:
        paramWriter.writeUInt32(extensionDescriptor.getNumber(), ((Integer)paramEntry.getValue()).intValue());
      case BOOL:
        paramWriter.writeBool(extensionDescriptor.getNumber(), ((Boolean)paramEntry.getValue()).booleanValue());
      case FIXED32:
        paramWriter.writeFixed32(extensionDescriptor.getNumber(), ((Integer)paramEntry.getValue()).intValue());
      case FIXED64:
        paramWriter.writeFixed64(extensionDescriptor.getNumber(), ((Long)paramEntry.getValue()).longValue());
      case INT32:
        paramWriter.writeInt32(extensionDescriptor.getNumber(), ((Integer)paramEntry.getValue()).intValue());
      case UINT64:
        paramWriter.writeUInt64(extensionDescriptor.getNumber(), ((Long)paramEntry.getValue()).longValue());
      case INT64:
        paramWriter.writeInt64(extensionDescriptor.getNumber(), ((Long)paramEntry.getValue()).longValue());
      case FLOAT:
        paramWriter.writeFloat(extensionDescriptor.getNumber(), ((Float)paramEntry.getValue()).floatValue());
      case DOUBLE:
        break;
    } 
    paramWriter.writeDouble(extensionDescriptor.getNumber(), ((Double)paramEntry.getValue()).doubleValue());
  }
  
  void setExtensions(Object paramObject, FieldSet<GeneratedMessageLite.ExtensionDescriptor> paramFieldSet) {
    ((GeneratedMessageLite.ExtendableMessage)paramObject).extensions = paramFieldSet;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\ExtensionSchemaLite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */