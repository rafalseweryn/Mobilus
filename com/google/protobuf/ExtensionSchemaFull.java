package com.google.protobuf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class ExtensionSchemaFull extends ExtensionSchema<Descriptors.FieldDescriptor> {
  private static final long EXTENSION_FIELD_OFFSET = getExtensionsFieldOffset();
  
  private static <T> long getExtensionsFieldOffset() {
    try {
      return UnsafeUtil.objectFieldOffset(GeneratedMessageV3.ExtendableMessage.class.getDeclaredField("extensions"));
    } catch (Throwable throwable) {
      throw new IllegalStateException("Unable to lookup extension field offset");
    } 
  }
  
  int extensionNumber(Map.Entry<?, ?> paramEntry) {
    return ((Descriptors.FieldDescriptor)paramEntry.getKey()).getNumber();
  }
  
  Object findExtensionByNumber(ExtensionRegistryLite paramExtensionRegistryLite, MessageLite paramMessageLite, int paramInt) {
    return ((ExtensionRegistry)paramExtensionRegistryLite).findExtensionByNumber(((Message)paramMessageLite).getDescriptorForType(), paramInt);
  }
  
  public FieldSet<Descriptors.FieldDescriptor> getExtensions(Object paramObject) {
    return (FieldSet<Descriptors.FieldDescriptor>)UnsafeUtil.getObject(paramObject, EXTENSION_FIELD_OFFSET);
  }
  
  FieldSet<Descriptors.FieldDescriptor> getMutableExtensions(Object paramObject) {
    FieldSet<Descriptors.FieldDescriptor> fieldSet1 = getExtensions(paramObject);
    FieldSet<Descriptors.FieldDescriptor> fieldSet2 = fieldSet1;
    if (fieldSet1.isImmutable()) {
      fieldSet2 = fieldSet1.clone();
      setExtensions(paramObject, fieldSet2);
    } 
    return fieldSet2;
  }
  
  boolean hasExtensions(MessageLite paramMessageLite) {
    return paramMessageLite instanceof GeneratedMessageV3.ExtendableMessage;
  }
  
  void makeImmutable(Object paramObject) {
    getExtensions(paramObject).makeImmutable();
  }
  
  <UT, UB> UB parseExtension(Reader paramReader, Object paramObject, ExtensionRegistryLite paramExtensionRegistryLite, FieldSet<Descriptors.FieldDescriptor> paramFieldSet, UB paramUB, UnknownFieldSchema<UT, UB> paramUnknownFieldSchema) throws IOException {
    Object object;
    Descriptors.EnumValueDescriptor enumValueDescriptor;
    ExtensionRegistry.ExtensionInfo extensionInfo = (ExtensionRegistry.ExtensionInfo)paramObject;
    int i = extensionInfo.descriptor.getNumber();
    if (extensionInfo.descriptor.isRepeated() && extensionInfo.descriptor.isPacked()) {
      StringBuilder stringBuilder;
      ArrayList<Descriptors.EnumValueDescriptor> arrayList;
      switch (extensionInfo.descriptor.getLiteType()) {
        default:
          stringBuilder = new StringBuilder();
          stringBuilder.append("Type cannot be packed: ");
          stringBuilder.append(extensionInfo.descriptor.getLiteType());
          throw new IllegalStateException(stringBuilder.toString());
        case ENUM:
          paramObject = new ArrayList();
          stringBuilder.readEnumList((List<Integer>)paramObject);
          arrayList = new ArrayList();
          paramObject = paramObject.iterator();
          while (paramObject.hasNext()) {
            int j = ((Integer)paramObject.next()).intValue();
            enumValueDescriptor = extensionInfo.descriptor.getEnumType().findValueByNumber(j);
            if (enumValueDescriptor != null) {
              arrayList.add(enumValueDescriptor);
              continue;
            } 
            paramUB = SchemaUtil.storeUnknownEnum(i, j, paramUB, paramUnknownFieldSchema);
          } 
          break;
        case SINT64:
          paramObject = new ArrayList();
          arrayList.readSInt64List((List<Long>)paramObject);
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
      paramFieldSet.setField(extensionInfo.descriptor, object);
    } else {
      paramObject = null;
      if (extensionInfo.descriptor.getLiteType() == WireFormat.FieldType.ENUM) {
        int j = object.readInt32();
        paramObject = extensionInfo.descriptor.getEnumType().findValueByNumber(j);
        object = paramObject;
        if (paramObject == null)
          return SchemaUtil.storeUnknownEnum(i, j, paramUB, paramUnknownFieldSchema); 
      } else {
        switch (extensionInfo.descriptor.getLiteType()) {
          default:
            object = paramObject;
            break;
          case MESSAGE:
            object = object.readMessage(extensionInfo.defaultInstance.getClass(), (ExtensionRegistryLite)enumValueDescriptor);
            break;
          case GROUP:
            object = object.readGroup(extensionInfo.defaultInstance.getClass(), (ExtensionRegistryLite)enumValueDescriptor);
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
      if (extensionInfo.descriptor.isRepeated()) {
        paramFieldSet.addRepeatedField(extensionInfo.descriptor, object);
      } else {
        Object object1;
        switch (extensionInfo.descriptor.getLiteType()) {
          default:
            paramObject = object;
            break;
          case GROUP:
          case MESSAGE:
            object1 = paramFieldSet.getField(extensionInfo.descriptor);
            paramObject = object;
            if (object1 != null)
              paramObject = Internal.mergeMessage(object1, object); 
            break;
        } 
        paramFieldSet.setField(extensionInfo.descriptor, paramObject);
      } 
    } 
    return paramUB;
  }
  
  void parseLengthPrefixedMessageSetItem(Reader paramReader, Object paramObject, ExtensionRegistryLite paramExtensionRegistryLite, FieldSet<Descriptors.FieldDescriptor> paramFieldSet) throws IOException {
    paramObject = paramObject;
    if (ExtensionRegistryLite.isEagerlyParseMessageSets()) {
      paramReader = paramReader.readMessage(((ExtensionRegistry.ExtensionInfo)paramObject).defaultInstance.getClass(), paramExtensionRegistryLite);
      paramFieldSet.setField(((ExtensionRegistry.ExtensionInfo)paramObject).descriptor, paramReader);
    } else {
      paramFieldSet.setField(((ExtensionRegistry.ExtensionInfo)paramObject).descriptor, new LazyField(((ExtensionRegistry.ExtensionInfo)paramObject).defaultInstance, paramExtensionRegistryLite, paramReader.readBytes()));
    } 
  }
  
  void parseMessageSetItem(ByteString paramByteString, Object paramObject, ExtensionRegistryLite paramExtensionRegistryLite, FieldSet<Descriptors.FieldDescriptor> paramFieldSet) throws IOException {
    BinaryReader binaryReader;
    paramObject = paramObject;
    Message message = ((ExtensionRegistry.ExtensionInfo)paramObject).defaultInstance.newBuilderForType().buildPartial();
    if (ExtensionRegistryLite.isEagerlyParseMessageSets()) {
      binaryReader = BinaryReader.newInstance(ByteBuffer.wrap(paramByteString.toByteArray()), true);
      Protobuf.getInstance().mergeFrom(message, binaryReader, paramExtensionRegistryLite);
      paramFieldSet.setField(((ExtensionRegistry.ExtensionInfo)paramObject).descriptor, message);
      if (binaryReader.getFieldNumber() != Integer.MAX_VALUE)
        throw InvalidProtocolBufferException.invalidEndTag(); 
    } else {
      paramFieldSet.setField(((ExtensionRegistry.ExtensionInfo)paramObject).descriptor, new LazyField(((ExtensionRegistry.ExtensionInfo)paramObject).defaultInstance, paramExtensionRegistryLite, (ByteString)binaryReader));
    } 
  }
  
  void serializeExtension(Writer paramWriter, Map.Entry<?, ?> paramEntry) throws IOException {
    ArrayList<Integer> arrayList;
    Descriptors.FieldDescriptor fieldDescriptor = (Descriptors.FieldDescriptor)paramEntry.getKey();
    if (fieldDescriptor.isRepeated()) {
      List list;
      Iterator<Descriptors.EnumValueDescriptor> iterator;
      switch (fieldDescriptor.getLiteType()) {
        default:
          return;
        case MESSAGE:
          SchemaUtil.writeMessageList(fieldDescriptor.getNumber(), (List)paramEntry.getValue(), paramWriter);
        case GROUP:
          SchemaUtil.writeGroupList(fieldDescriptor.getNumber(), (List)paramEntry.getValue(), paramWriter);
        case STRING:
          SchemaUtil.writeStringList(fieldDescriptor.getNumber(), (List<String>)paramEntry.getValue(), paramWriter);
        case BYTES:
          SchemaUtil.writeBytesList(fieldDescriptor.getNumber(), (List<ByteString>)paramEntry.getValue(), paramWriter);
        case ENUM:
          list = (List)paramEntry.getValue();
          arrayList = new ArrayList();
          iterator = list.iterator();
          while (iterator.hasNext())
            arrayList.add(Integer.valueOf(((Descriptors.EnumValueDescriptor)iterator.next()).getNumber())); 
          SchemaUtil.writeInt32List(fieldDescriptor.getNumber(), arrayList, paramWriter, fieldDescriptor.isPacked());
        case SINT64:
          SchemaUtil.writeSInt64List(fieldDescriptor.getNumber(), arrayList.getValue(), paramWriter, fieldDescriptor.isPacked());
        case SINT32:
          SchemaUtil.writeSInt32List(fieldDescriptor.getNumber(), arrayList.getValue(), paramWriter, fieldDescriptor.isPacked());
        case SFIXED64:
          SchemaUtil.writeSFixed64List(fieldDescriptor.getNumber(), arrayList.getValue(), paramWriter, fieldDescriptor.isPacked());
        case SFIXED32:
          SchemaUtil.writeSFixed32List(fieldDescriptor.getNumber(), arrayList.getValue(), paramWriter, fieldDescriptor.isPacked());
        case UINT32:
          SchemaUtil.writeUInt32List(fieldDescriptor.getNumber(), arrayList.getValue(), paramWriter, fieldDescriptor.isPacked());
        case BOOL:
          SchemaUtil.writeBoolList(fieldDescriptor.getNumber(), arrayList.getValue(), paramWriter, fieldDescriptor.isPacked());
        case FIXED32:
          SchemaUtil.writeFixed32List(fieldDescriptor.getNumber(), arrayList.getValue(), paramWriter, fieldDescriptor.isPacked());
        case FIXED64:
          SchemaUtil.writeFixed64List(fieldDescriptor.getNumber(), arrayList.getValue(), paramWriter, fieldDescriptor.isPacked());
        case INT32:
          SchemaUtil.writeInt32List(fieldDescriptor.getNumber(), arrayList.getValue(), paramWriter, fieldDescriptor.isPacked());
        case UINT64:
          SchemaUtil.writeUInt64List(fieldDescriptor.getNumber(), arrayList.getValue(), paramWriter, fieldDescriptor.isPacked());
        case INT64:
          SchemaUtil.writeInt64List(fieldDescriptor.getNumber(), arrayList.getValue(), paramWriter, fieldDescriptor.isPacked());
        case FLOAT:
          SchemaUtil.writeFloatList(fieldDescriptor.getNumber(), arrayList.getValue(), paramWriter, fieldDescriptor.isPacked());
        case DOUBLE:
          break;
      } 
      SchemaUtil.writeDoubleList(fieldDescriptor.getNumber(), arrayList.getValue(), paramWriter, fieldDescriptor.isPacked());
    } 
    switch (fieldDescriptor.getLiteType()) {
      default:
      
      case MESSAGE:
        paramWriter.writeMessage(fieldDescriptor.getNumber(), arrayList.getValue());
      case GROUP:
        paramWriter.writeGroup(fieldDescriptor.getNumber(), arrayList.getValue());
      case STRING:
        paramWriter.writeString(fieldDescriptor.getNumber(), arrayList.getValue());
      case BYTES:
        paramWriter.writeBytes(fieldDescriptor.getNumber(), arrayList.getValue());
      case ENUM:
        paramWriter.writeInt32(fieldDescriptor.getNumber(), ((Descriptors.EnumValueDescriptor)arrayList.getValue()).getNumber());
      case SINT64:
        paramWriter.writeSInt64(fieldDescriptor.getNumber(), ((Long)arrayList.getValue()).longValue());
      case SINT32:
        paramWriter.writeSInt32(fieldDescriptor.getNumber(), ((Integer)arrayList.getValue()).intValue());
      case SFIXED64:
        paramWriter.writeSFixed64(fieldDescriptor.getNumber(), ((Long)arrayList.getValue()).longValue());
      case SFIXED32:
        paramWriter.writeSFixed32(fieldDescriptor.getNumber(), ((Integer)arrayList.getValue()).intValue());
      case UINT32:
        paramWriter.writeUInt32(fieldDescriptor.getNumber(), ((Integer)arrayList.getValue()).intValue());
      case BOOL:
        paramWriter.writeBool(fieldDescriptor.getNumber(), ((Boolean)arrayList.getValue()).booleanValue());
      case FIXED32:
        paramWriter.writeFixed32(fieldDescriptor.getNumber(), ((Integer)arrayList.getValue()).intValue());
      case FIXED64:
        paramWriter.writeFixed64(fieldDescriptor.getNumber(), ((Long)arrayList.getValue()).longValue());
      case INT32:
        paramWriter.writeInt32(fieldDescriptor.getNumber(), ((Integer)arrayList.getValue()).intValue());
      case UINT64:
        paramWriter.writeUInt64(fieldDescriptor.getNumber(), ((Long)arrayList.getValue()).longValue());
      case INT64:
        paramWriter.writeInt64(fieldDescriptor.getNumber(), ((Long)arrayList.getValue()).longValue());
      case FLOAT:
        paramWriter.writeFloat(fieldDescriptor.getNumber(), ((Float)arrayList.getValue()).floatValue());
      case DOUBLE:
        break;
    } 
    paramWriter.writeDouble(fieldDescriptor.getNumber(), ((Double)arrayList.getValue()).doubleValue());
  }
  
  void setExtensions(Object paramObject, FieldSet<Descriptors.FieldDescriptor> paramFieldSet) {
    UnsafeUtil.putObject(paramObject, EXTENSION_FIELD_OFFSET, paramFieldSet);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\ExtensionSchemaFull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */