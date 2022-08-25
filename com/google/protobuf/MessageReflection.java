package com.google.protobuf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class MessageReflection {
  static String delimitWithCommas(List<String> paramList) {
    StringBuilder stringBuilder = new StringBuilder();
    for (String str : paramList) {
      if (stringBuilder.length() > 0)
        stringBuilder.append(", "); 
      stringBuilder.append(str);
    } 
    return stringBuilder.toString();
  }
  
  private static void eagerlyMergeMessageSetExtension(CodedInputStream paramCodedInputStream, ExtensionRegistry.ExtensionInfo paramExtensionInfo, ExtensionRegistryLite paramExtensionRegistryLite, MergeTarget paramMergeTarget) throws IOException {
    Descriptors.FieldDescriptor fieldDescriptor = paramExtensionInfo.descriptor;
    paramMergeTarget.setField(fieldDescriptor, paramMergeTarget.parseMessage(paramCodedInputStream, paramExtensionRegistryLite, fieldDescriptor, paramExtensionInfo.defaultInstance));
  }
  
  static List<String> findMissingFields(MessageOrBuilder paramMessageOrBuilder) {
    ArrayList<String> arrayList = new ArrayList();
    findMissingFields(paramMessageOrBuilder, "", arrayList);
    return arrayList;
  }
  
  private static void findMissingFields(MessageOrBuilder paramMessageOrBuilder, String paramString, List<String> paramList) {
    for (Descriptors.FieldDescriptor fieldDescriptor : paramMessageOrBuilder.getDescriptorForType().getFields()) {
      if (fieldDescriptor.isRequired() && !paramMessageOrBuilder.hasField(fieldDescriptor)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append(fieldDescriptor.getName());
        paramList.add(stringBuilder.toString());
      } 
    } 
    for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : paramMessageOrBuilder.getAllFields().entrySet()) {
      Descriptors.FieldDescriptor fieldDescriptor = (Descriptors.FieldDescriptor)entry.getKey();
      entry = (Map.Entry<Descriptors.FieldDescriptor, Object>)entry.getValue();
      if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
        Iterator<MessageOrBuilder> iterator;
        if (fieldDescriptor.isRepeated()) {
          byte b = 0;
          iterator = ((List)entry).iterator();
          while (iterator.hasNext()) {
            findMissingFields(iterator.next(), subMessagePrefix(paramString, fieldDescriptor, b), paramList);
            b++;
          } 
          continue;
        } 
        if (paramMessageOrBuilder.hasField(fieldDescriptor))
          findMissingFields((MessageOrBuilder)iterator, subMessagePrefix(paramString, fieldDescriptor, -1), paramList); 
      } 
    } 
  }
  
  static int getSerializedSize(Message paramMessage, Map<Descriptors.FieldDescriptor, Object> paramMap) {
    boolean bool = paramMessage.getDescriptorForType().getOptions().getMessageSetWireFormat();
    Iterator<Map.Entry> iterator = paramMap.entrySet().iterator();
    int i;
    for (i = 0; iterator.hasNext(); i += FieldSet.computeFieldSize(fieldDescriptor, entry)) {
      Map.Entry entry = iterator.next();
      Descriptors.FieldDescriptor fieldDescriptor = (Descriptors.FieldDescriptor)entry.getKey();
      entry = (Map.Entry)entry.getValue();
      if (bool && fieldDescriptor.isExtension() && fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && !fieldDescriptor.isRepeated()) {
        i += CodedOutputStream.computeMessageSetExtensionSize(fieldDescriptor.getNumber(), (Message)entry);
        continue;
      } 
    } 
    UnknownFieldSet unknownFieldSet = paramMessage.getUnknownFields();
    if (bool) {
      i += unknownFieldSet.getSerializedSizeAsMessageSet();
    } else {
      i += unknownFieldSet.getSerializedSize();
    } 
    return i;
  }
  
  static boolean isInitialized(MessageOrBuilder paramMessageOrBuilder) {
    for (Descriptors.FieldDescriptor fieldDescriptor : paramMessageOrBuilder.getDescriptorForType().getFields()) {
      if (fieldDescriptor.isRequired() && !paramMessageOrBuilder.hasField(fieldDescriptor))
        return false; 
    } 
    for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : paramMessageOrBuilder.getAllFields().entrySet()) {
      Descriptors.FieldDescriptor fieldDescriptor = (Descriptors.FieldDescriptor)entry.getKey();
      if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
        Iterator<Message> iterator;
        if (fieldDescriptor.isRepeated()) {
          iterator = ((List)entry.getValue()).iterator();
          while (iterator.hasNext()) {
            if (!((Message)iterator.next()).isInitialized())
              return false; 
          } 
          continue;
        } 
        if (!((Message)iterator.getValue()).isInitialized())
          return false; 
      } 
    } 
    return true;
  }
  
  static boolean mergeFieldFrom(CodedInputStream paramCodedInputStream, UnknownFieldSet.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite, Descriptors.Descriptor paramDescriptor, MergeTarget paramMergeTarget, int paramInt) throws IOException {
    // Byte code:
    //   0: aload_3
    //   1: invokevirtual getOptions : ()Lcom/google/protobuf/DescriptorProtos$MessageOptions;
    //   4: invokevirtual getMessageSetWireFormat : ()Z
    //   7: ifeq -> 29
    //   10: iload #5
    //   12: getstatic com/google/protobuf/WireFormat.MESSAGE_SET_ITEM_TAG : I
    //   15: if_icmpne -> 29
    //   18: aload_0
    //   19: aload_1
    //   20: aload_2
    //   21: aload_3
    //   22: aload #4
    //   24: invokestatic mergeMessageSetExtensionFromCodedStream : (Lcom/google/protobuf/CodedInputStream;Lcom/google/protobuf/UnknownFieldSet$Builder;Lcom/google/protobuf/ExtensionRegistryLite;Lcom/google/protobuf/Descriptors$Descriptor;Lcom/google/protobuf/MessageReflection$MergeTarget;)V
    //   27: iconst_1
    //   28: ireturn
    //   29: iload #5
    //   31: invokestatic getTagWireType : (I)I
    //   34: istore #6
    //   36: iload #5
    //   38: invokestatic getTagFieldNumber : (I)I
    //   41: istore #7
    //   43: aload_3
    //   44: iload #7
    //   46: invokevirtual isExtensionNumber : (I)Z
    //   49: istore #8
    //   51: aconst_null
    //   52: astore #9
    //   54: iload #8
    //   56: ifeq -> 180
    //   59: aload_2
    //   60: instanceof com/google/protobuf/ExtensionRegistry
    //   63: ifeq -> 167
    //   66: aload #4
    //   68: aload_2
    //   69: checkcast com/google/protobuf/ExtensionRegistry
    //   72: aload_3
    //   73: iload #7
    //   75: invokeinterface findExtensionByNumber : (Lcom/google/protobuf/ExtensionRegistry;Lcom/google/protobuf/Descriptors$Descriptor;I)Lcom/google/protobuf/ExtensionRegistry$ExtensionInfo;
    //   80: astore_3
    //   81: aload_3
    //   82: ifnonnull -> 88
    //   85: goto -> 167
    //   88: aload_3
    //   89: getfield descriptor : Lcom/google/protobuf/Descriptors$FieldDescriptor;
    //   92: astore #10
    //   94: aload_3
    //   95: getfield defaultInstance : Lcom/google/protobuf/Message;
    //   98: astore #11
    //   100: aload #10
    //   102: astore_3
    //   103: aload #11
    //   105: astore #9
    //   107: aload #11
    //   109: ifnonnull -> 203
    //   112: aload #10
    //   114: astore_3
    //   115: aload #11
    //   117: astore #9
    //   119: aload #10
    //   121: invokevirtual getJavaType : ()Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
    //   124: getstatic com/google/protobuf/Descriptors$FieldDescriptor$JavaType.MESSAGE : Lcom/google/protobuf/Descriptors$FieldDescriptor$JavaType;
    //   127: if_acmpne -> 203
    //   130: new java/lang/StringBuilder
    //   133: dup
    //   134: invokespecial <init> : ()V
    //   137: astore_0
    //   138: aload_0
    //   139: ldc 'Message-typed extension lacked default instance: '
    //   141: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: pop
    //   145: aload_0
    //   146: aload #10
    //   148: invokevirtual getFullName : ()Ljava/lang/String;
    //   151: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: pop
    //   155: new java/lang/IllegalStateException
    //   158: dup
    //   159: aload_0
    //   160: invokevirtual toString : ()Ljava/lang/String;
    //   163: invokespecial <init> : (Ljava/lang/String;)V
    //   166: athrow
    //   167: aconst_null
    //   168: astore #10
    //   170: aload #9
    //   172: astore_3
    //   173: aload #10
    //   175: astore #9
    //   177: goto -> 203
    //   180: aload #4
    //   182: invokeinterface getContainerType : ()Lcom/google/protobuf/MessageReflection$MergeTarget$ContainerType;
    //   187: getstatic com/google/protobuf/MessageReflection$MergeTarget$ContainerType.MESSAGE : Lcom/google/protobuf/MessageReflection$MergeTarget$ContainerType;
    //   190: if_acmpne -> 167
    //   193: aload_3
    //   194: iload #7
    //   196: invokevirtual findFieldByNumber : (I)Lcom/google/protobuf/Descriptors$FieldDescriptor;
    //   199: astore_3
    //   200: aconst_null
    //   201: astore #9
    //   203: iconst_0
    //   204: istore #12
    //   206: aload_3
    //   207: ifnonnull -> 219
    //   210: iconst_0
    //   211: istore #6
    //   213: iconst_1
    //   214: istore #12
    //   216: goto -> 261
    //   219: iload #6
    //   221: aload_3
    //   222: invokevirtual getLiteType : ()Lcom/google/protobuf/WireFormat$FieldType;
    //   225: iconst_0
    //   226: invokestatic getWireFormatForFieldType : (Lcom/google/protobuf/WireFormat$FieldType;Z)I
    //   229: if_icmpne -> 238
    //   232: iconst_0
    //   233: istore #6
    //   235: goto -> 261
    //   238: aload_3
    //   239: invokevirtual isPackable : ()Z
    //   242: ifeq -> 210
    //   245: iload #6
    //   247: aload_3
    //   248: invokevirtual getLiteType : ()Lcom/google/protobuf/WireFormat$FieldType;
    //   251: iconst_1
    //   252: invokestatic getWireFormatForFieldType : (Lcom/google/protobuf/WireFormat$FieldType;Z)I
    //   255: if_icmpne -> 210
    //   258: iconst_1
    //   259: istore #6
    //   261: iload #12
    //   263: ifeq -> 285
    //   266: aload_1
    //   267: ifnull -> 278
    //   270: aload_1
    //   271: iload #5
    //   273: aload_0
    //   274: invokevirtual mergeFieldFrom : (ILcom/google/protobuf/CodedInputStream;)Z
    //   277: ireturn
    //   278: aload_0
    //   279: iload #5
    //   281: invokevirtual skipField : (I)Z
    //   284: ireturn
    //   285: iload #6
    //   287: ifeq -> 441
    //   290: aload_0
    //   291: aload_0
    //   292: invokevirtual readRawVarint32 : ()I
    //   295: invokevirtual pushLimit : (I)I
    //   298: istore #5
    //   300: aload_3
    //   301: invokevirtual getLiteType : ()Lcom/google/protobuf/WireFormat$FieldType;
    //   304: getstatic com/google/protobuf/WireFormat$FieldType.ENUM : Lcom/google/protobuf/WireFormat$FieldType;
    //   307: if_acmpne -> 397
    //   310: aload_0
    //   311: invokevirtual getBytesUntilLimit : ()I
    //   314: ifle -> 432
    //   317: aload_0
    //   318: invokevirtual readEnum : ()I
    //   321: istore #6
    //   323: aload_3
    //   324: invokevirtual getFile : ()Lcom/google/protobuf/Descriptors$FileDescriptor;
    //   327: invokevirtual supportsUnknownEnumValue : ()Z
    //   330: ifeq -> 354
    //   333: aload #4
    //   335: aload_3
    //   336: aload_3
    //   337: invokevirtual getEnumType : ()Lcom/google/protobuf/Descriptors$EnumDescriptor;
    //   340: iload #6
    //   342: invokevirtual findValueByNumberCreatingIfUnknown : (I)Lcom/google/protobuf/Descriptors$EnumValueDescriptor;
    //   345: invokeinterface addRepeatedField : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/Object;)Lcom/google/protobuf/MessageReflection$MergeTarget;
    //   350: pop
    //   351: goto -> 310
    //   354: aload_3
    //   355: invokevirtual getEnumType : ()Lcom/google/protobuf/Descriptors$EnumDescriptor;
    //   358: iload #6
    //   360: invokevirtual findValueByNumber : (I)Lcom/google/protobuf/Descriptors$EnumValueDescriptor;
    //   363: astore_2
    //   364: aload_2
    //   365: ifnonnull -> 384
    //   368: aload_1
    //   369: ifnull -> 310
    //   372: aload_1
    //   373: iload #7
    //   375: iload #6
    //   377: invokevirtual mergeVarintField : (II)Lcom/google/protobuf/UnknownFieldSet$Builder;
    //   380: pop
    //   381: goto -> 310
    //   384: aload #4
    //   386: aload_3
    //   387: aload_2
    //   388: invokeinterface addRepeatedField : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/Object;)Lcom/google/protobuf/MessageReflection$MergeTarget;
    //   393: pop
    //   394: goto -> 310
    //   397: aload_0
    //   398: invokevirtual getBytesUntilLimit : ()I
    //   401: ifle -> 432
    //   404: aload #4
    //   406: aload_3
    //   407: aload_0
    //   408: aload_3
    //   409: invokevirtual getLiteType : ()Lcom/google/protobuf/WireFormat$FieldType;
    //   412: aload #4
    //   414: aload_3
    //   415: invokeinterface getUtf8Validation : (Lcom/google/protobuf/Descriptors$FieldDescriptor;)Lcom/google/protobuf/WireFormat$Utf8Validation;
    //   420: invokestatic readPrimitiveField : (Lcom/google/protobuf/CodedInputStream;Lcom/google/protobuf/WireFormat$FieldType;Lcom/google/protobuf/WireFormat$Utf8Validation;)Ljava/lang/Object;
    //   423: invokeinterface addRepeatedField : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/Object;)Lcom/google/protobuf/MessageReflection$MergeTarget;
    //   428: pop
    //   429: goto -> 397
    //   432: aload_0
    //   433: iload #5
    //   435: invokevirtual popLimit : (I)V
    //   438: goto -> 620
    //   441: getstatic com/google/protobuf/MessageReflection$1.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type : [I
    //   444: aload_3
    //   445: invokevirtual getType : ()Lcom/google/protobuf/Descriptors$FieldDescriptor$Type;
    //   448: invokevirtual ordinal : ()I
    //   451: iaload
    //   452: tableswitch default -> 480, 1 -> 577, 2 -> 561, 3 -> 500
    //   480: aload_0
    //   481: aload_3
    //   482: invokevirtual getLiteType : ()Lcom/google/protobuf/WireFormat$FieldType;
    //   485: aload #4
    //   487: aload_3
    //   488: invokeinterface getUtf8Validation : (Lcom/google/protobuf/Descriptors$FieldDescriptor;)Lcom/google/protobuf/WireFormat$Utf8Validation;
    //   493: invokestatic readPrimitiveField : (Lcom/google/protobuf/CodedInputStream;Lcom/google/protobuf/WireFormat$FieldType;Lcom/google/protobuf/WireFormat$Utf8Validation;)Ljava/lang/Object;
    //   496: astore_0
    //   497: goto -> 590
    //   500: aload_0
    //   501: invokevirtual readEnum : ()I
    //   504: istore #5
    //   506: aload_3
    //   507: invokevirtual getFile : ()Lcom/google/protobuf/Descriptors$FileDescriptor;
    //   510: invokevirtual supportsUnknownEnumValue : ()Z
    //   513: ifeq -> 529
    //   516: aload_3
    //   517: invokevirtual getEnumType : ()Lcom/google/protobuf/Descriptors$EnumDescriptor;
    //   520: iload #5
    //   522: invokevirtual findValueByNumberCreatingIfUnknown : (I)Lcom/google/protobuf/Descriptors$EnumValueDescriptor;
    //   525: astore_0
    //   526: goto -> 590
    //   529: aload_3
    //   530: invokevirtual getEnumType : ()Lcom/google/protobuf/Descriptors$EnumDescriptor;
    //   533: iload #5
    //   535: invokevirtual findValueByNumber : (I)Lcom/google/protobuf/Descriptors$EnumValueDescriptor;
    //   538: astore_0
    //   539: aload_0
    //   540: ifnonnull -> 558
    //   543: aload_1
    //   544: ifnull -> 556
    //   547: aload_1
    //   548: iload #7
    //   550: iload #5
    //   552: invokevirtual mergeVarintField : (II)Lcom/google/protobuf/UnknownFieldSet$Builder;
    //   555: pop
    //   556: iconst_1
    //   557: ireturn
    //   558: goto -> 590
    //   561: aload #4
    //   563: aload_0
    //   564: aload_2
    //   565: aload_3
    //   566: aload #9
    //   568: invokeinterface parseMessage : (Lcom/google/protobuf/CodedInputStream;Lcom/google/protobuf/ExtensionRegistryLite;Lcom/google/protobuf/Descriptors$FieldDescriptor;Lcom/google/protobuf/Message;)Ljava/lang/Object;
    //   573: astore_0
    //   574: goto -> 590
    //   577: aload #4
    //   579: aload_0
    //   580: aload_2
    //   581: aload_3
    //   582: aload #9
    //   584: invokeinterface parseGroup : (Lcom/google/protobuf/CodedInputStream;Lcom/google/protobuf/ExtensionRegistryLite;Lcom/google/protobuf/Descriptors$FieldDescriptor;Lcom/google/protobuf/Message;)Ljava/lang/Object;
    //   589: astore_0
    //   590: aload_3
    //   591: invokevirtual isRepeated : ()Z
    //   594: ifeq -> 610
    //   597: aload #4
    //   599: aload_3
    //   600: aload_0
    //   601: invokeinterface addRepeatedField : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/Object;)Lcom/google/protobuf/MessageReflection$MergeTarget;
    //   606: pop
    //   607: goto -> 620
    //   610: aload #4
    //   612: aload_3
    //   613: aload_0
    //   614: invokeinterface setField : (Lcom/google/protobuf/Descriptors$FieldDescriptor;Ljava/lang/Object;)Lcom/google/protobuf/MessageReflection$MergeTarget;
    //   619: pop
    //   620: iconst_1
    //   621: ireturn
  }
  
  private static void mergeMessageSetExtensionFromBytes(ByteString paramByteString, ExtensionRegistry.ExtensionInfo paramExtensionInfo, ExtensionRegistryLite paramExtensionRegistryLite, MergeTarget paramMergeTarget) throws IOException {
    Descriptors.FieldDescriptor fieldDescriptor = paramExtensionInfo.descriptor;
    if (paramMergeTarget.hasField(fieldDescriptor) || ExtensionRegistryLite.isEagerlyParseMessageSets()) {
      paramMergeTarget.setField(fieldDescriptor, paramMergeTarget.parseMessageFromBytes(paramByteString, paramExtensionRegistryLite, fieldDescriptor, paramExtensionInfo.defaultInstance));
      return;
    } 
    paramMergeTarget.setField(fieldDescriptor, new LazyField(paramExtensionInfo.defaultInstance, paramExtensionRegistryLite, paramByteString));
  }
  
  private static void mergeMessageSetExtensionFromCodedStream(CodedInputStream paramCodedInputStream, UnknownFieldSet.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite, Descriptors.Descriptor paramDescriptor, MergeTarget paramMergeTarget) throws IOException {
    ByteString byteString;
    int i = 0;
    ExtensionRegistry.ExtensionInfo extensionInfo1 = null;
    ExtensionRegistry.ExtensionInfo extensionInfo2 = extensionInfo1;
    while (true) {
      int j = paramCodedInputStream.readTag();
      if (j == 0)
        break; 
      if (j == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
        j = paramCodedInputStream.readUInt32();
        i = j;
        if (j != 0) {
          i = j;
          if (paramExtensionRegistryLite instanceof ExtensionRegistry) {
            extensionInfo1 = paramMergeTarget.findExtensionByNumber((ExtensionRegistry)paramExtensionRegistryLite, paramDescriptor, j);
            i = j;
          } 
        } 
        continue;
      } 
      if (j == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
        if (i != 0 && extensionInfo1 != null && ExtensionRegistryLite.isEagerlyParseMessageSets()) {
          eagerlyMergeMessageSetExtension(paramCodedInputStream, extensionInfo1, paramExtensionRegistryLite, paramMergeTarget);
          extensionInfo2 = null;
          continue;
        } 
        byteString = paramCodedInputStream.readBytes();
        continue;
      } 
      if (!paramCodedInputStream.skipField(j))
        break; 
    } 
    paramCodedInputStream.checkLastTagWas(WireFormat.MESSAGE_SET_ITEM_END_TAG);
    if (byteString != null && i != 0)
      if (extensionInfo1 != null) {
        mergeMessageSetExtensionFromBytes(byteString, extensionInfo1, paramExtensionRegistryLite, paramMergeTarget);
      } else if (byteString != null && paramBuilder != null) {
        paramBuilder.mergeField(i, UnknownFieldSet.Field.newBuilder().addLengthDelimited(byteString).build());
      }  
  }
  
  private static String subMessagePrefix(String paramString, Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder(paramString);
    if (paramFieldDescriptor.isExtension()) {
      stringBuilder.append('(');
      stringBuilder.append(paramFieldDescriptor.getFullName());
      stringBuilder.append(')');
    } else {
      stringBuilder.append(paramFieldDescriptor.getName());
    } 
    if (paramInt != -1) {
      stringBuilder.append('[');
      stringBuilder.append(paramInt);
      stringBuilder.append(']');
    } 
    stringBuilder.append('.');
    return stringBuilder.toString();
  }
  
  static void writeMessageTo(Message paramMessage, Map<Descriptors.FieldDescriptor, Object> paramMap, CodedOutputStream paramCodedOutputStream, boolean paramBoolean) throws IOException {
    boolean bool = paramMessage.getDescriptorForType().getOptions().getMessageSetWireFormat();
    Map<Descriptors.FieldDescriptor, Object> map = paramMap;
    if (paramBoolean) {
      map = new TreeMap<>(paramMap);
      for (Descriptors.FieldDescriptor fieldDescriptor : paramMessage.getDescriptorForType().getFields()) {
        if (fieldDescriptor.isRequired() && !map.containsKey(fieldDescriptor))
          map.put(fieldDescriptor, paramMessage.getField(fieldDescriptor)); 
      } 
    } 
    for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : map.entrySet()) {
      Descriptors.FieldDescriptor fieldDescriptor = (Descriptors.FieldDescriptor)entry.getKey();
      entry = (Map.Entry<Descriptors.FieldDescriptor, Object>)entry.getValue();
      if (bool && fieldDescriptor.isExtension() && fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && !fieldDescriptor.isRepeated()) {
        paramCodedOutputStream.writeMessageSetExtension(fieldDescriptor.getNumber(), (Message)entry);
        continue;
      } 
      FieldSet.writeField(fieldDescriptor, entry, paramCodedOutputStream);
    } 
    UnknownFieldSet unknownFieldSet = paramMessage.getUnknownFields();
    if (bool) {
      unknownFieldSet.writeAsMessageSetTo(paramCodedOutputStream);
    } else {
      unknownFieldSet.writeTo(paramCodedOutputStream);
    } 
  }
  
  static class BuilderAdapter implements MergeTarget {
    private final Message.Builder builder;
    
    public BuilderAdapter(Message.Builder param1Builder) {
      this.builder = param1Builder;
    }
    
    public MessageReflection.MergeTarget addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      this.builder.addRepeatedField(param1FieldDescriptor, param1Object);
      return this;
    }
    
    public MessageReflection.MergeTarget clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      this.builder.clearField(param1FieldDescriptor);
      return this;
    }
    
    public MessageReflection.MergeTarget clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      this.builder.clearOneof(param1OneofDescriptor);
      return this;
    }
    
    public ExtensionRegistry.ExtensionInfo findExtensionByName(ExtensionRegistry param1ExtensionRegistry, String param1String) {
      return param1ExtensionRegistry.findImmutableExtensionByName(param1String);
    }
    
    public ExtensionRegistry.ExtensionInfo findExtensionByNumber(ExtensionRegistry param1ExtensionRegistry, Descriptors.Descriptor param1Descriptor, int param1Int) {
      return param1ExtensionRegistry.findImmutableExtensionByNumber(param1Descriptor, param1Int);
    }
    
    public Object finish() {
      return this.builder.buildPartial();
    }
    
    public MessageReflection.MergeTarget.ContainerType getContainerType() {
      return MessageReflection.MergeTarget.ContainerType.MESSAGE;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return this.builder.getDescriptorForType();
    }
    
    public Object getField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return this.builder.getField(param1FieldDescriptor);
    }
    
    public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return this.builder.getOneofFieldDescriptor(param1OneofDescriptor);
    }
    
    public WireFormat.Utf8Validation getUtf8Validation(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return param1FieldDescriptor.needsUtf8Check() ? WireFormat.Utf8Validation.STRICT : ((!param1FieldDescriptor.isRepeated() && this.builder instanceof GeneratedMessage.Builder) ? WireFormat.Utf8Validation.LAZY : WireFormat.Utf8Validation.LOOSE);
    }
    
    public boolean hasField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return this.builder.hasField(param1FieldDescriptor);
    }
    
    public boolean hasOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return this.builder.hasOneof(param1OneofDescriptor);
    }
    
    public MessageReflection.MergeTarget newEmptyTargetForField(Descriptors.FieldDescriptor param1FieldDescriptor, Message param1Message) {
      Message.Builder builder;
      if (param1Message != null) {
        builder = param1Message.newBuilderForType();
      } else {
        builder = this.builder.newBuilderForField((Descriptors.FieldDescriptor)builder);
      } 
      return new BuilderAdapter(builder);
    }
    
    public MessageReflection.MergeTarget newMergeTargetForField(Descriptors.FieldDescriptor param1FieldDescriptor, Message param1Message) {
      Message.Builder builder;
      if (param1Message != null) {
        builder = param1Message.newBuilderForType();
      } else {
        builder = this.builder.newBuilderForField(param1FieldDescriptor);
      } 
      if (!param1FieldDescriptor.isRepeated()) {
        Message message = (Message)getField(param1FieldDescriptor);
        if (message != null)
          builder.mergeFrom(message); 
      } 
      return new BuilderAdapter(builder);
    }
    
    public Object parseGroup(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite, Descriptors.FieldDescriptor param1FieldDescriptor, Message param1Message) throws IOException {
      Message.Builder builder;
      if (param1Message != null) {
        builder = param1Message.newBuilderForType();
      } else {
        builder = this.builder.newBuilderForField(param1FieldDescriptor);
      } 
      if (!param1FieldDescriptor.isRepeated()) {
        Message message = (Message)getField(param1FieldDescriptor);
        if (message != null)
          builder.mergeFrom(message); 
      } 
      param1CodedInputStream.readGroup(param1FieldDescriptor.getNumber(), builder, param1ExtensionRegistryLite);
      return builder.buildPartial();
    }
    
    public Object parseMessage(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite, Descriptors.FieldDescriptor param1FieldDescriptor, Message param1Message) throws IOException {
      Message.Builder builder;
      if (param1Message != null) {
        builder = param1Message.newBuilderForType();
      } else {
        builder = this.builder.newBuilderForField(param1FieldDescriptor);
      } 
      if (!param1FieldDescriptor.isRepeated()) {
        Message message = (Message)getField(param1FieldDescriptor);
        if (message != null)
          builder.mergeFrom(message); 
      } 
      param1CodedInputStream.readMessage(builder, param1ExtensionRegistryLite);
      return builder.buildPartial();
    }
    
    public Object parseMessageFromBytes(ByteString param1ByteString, ExtensionRegistryLite param1ExtensionRegistryLite, Descriptors.FieldDescriptor param1FieldDescriptor, Message param1Message) throws IOException {
      Message.Builder builder;
      if (param1Message != null) {
        builder = param1Message.newBuilderForType();
      } else {
        builder = this.builder.newBuilderForField(param1FieldDescriptor);
      } 
      if (!param1FieldDescriptor.isRepeated()) {
        Message message = (Message)getField(param1FieldDescriptor);
        if (message != null)
          builder.mergeFrom(message); 
      } 
      builder.mergeFrom(param1ByteString, param1ExtensionRegistryLite);
      return builder.buildPartial();
    }
    
    public MessageReflection.MergeTarget setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      this.builder.setField(param1FieldDescriptor, param1Object);
      return this;
    }
    
    public MessageReflection.MergeTarget setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object) {
      this.builder.setRepeatedField(param1FieldDescriptor, param1Int, param1Object);
      return this;
    }
  }
  
  static class ExtensionAdapter implements MergeTarget {
    private final FieldSet<Descriptors.FieldDescriptor> extensions;
    
    ExtensionAdapter(FieldSet<Descriptors.FieldDescriptor> param1FieldSet) {
      this.extensions = param1FieldSet;
    }
    
    public MessageReflection.MergeTarget addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      this.extensions.addRepeatedField(param1FieldDescriptor, param1Object);
      return this;
    }
    
    public MessageReflection.MergeTarget clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      this.extensions.clearField(param1FieldDescriptor);
      return this;
    }
    
    public MessageReflection.MergeTarget clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return this;
    }
    
    public ExtensionRegistry.ExtensionInfo findExtensionByName(ExtensionRegistry param1ExtensionRegistry, String param1String) {
      return param1ExtensionRegistry.findImmutableExtensionByName(param1String);
    }
    
    public ExtensionRegistry.ExtensionInfo findExtensionByNumber(ExtensionRegistry param1ExtensionRegistry, Descriptors.Descriptor param1Descriptor, int param1Int) {
      return param1ExtensionRegistry.findImmutableExtensionByNumber(param1Descriptor, param1Int);
    }
    
    public Object finish() {
      throw new UnsupportedOperationException("finish() called on FieldSet object");
    }
    
    public MessageReflection.MergeTarget.ContainerType getContainerType() {
      return MessageReflection.MergeTarget.ContainerType.EXTENSION_SET;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      throw new UnsupportedOperationException("getDescriptorForType() called on FieldSet object");
    }
    
    public Object getField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return this.extensions.getField(param1FieldDescriptor);
    }
    
    public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return null;
    }
    
    public WireFormat.Utf8Validation getUtf8Validation(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return param1FieldDescriptor.needsUtf8Check() ? WireFormat.Utf8Validation.STRICT : WireFormat.Utf8Validation.LOOSE;
    }
    
    public boolean hasField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return this.extensions.hasField(param1FieldDescriptor);
    }
    
    public boolean hasOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return false;
    }
    
    public MessageReflection.MergeTarget newEmptyTargetForField(Descriptors.FieldDescriptor param1FieldDescriptor, Message param1Message) {
      throw new UnsupportedOperationException("newEmptyTargetForField() called on FieldSet object");
    }
    
    public MessageReflection.MergeTarget newMergeTargetForField(Descriptors.FieldDescriptor param1FieldDescriptor, Message param1Message) {
      throw new UnsupportedOperationException("newMergeTargetForField() called on FieldSet object");
    }
    
    public Object parseGroup(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite, Descriptors.FieldDescriptor param1FieldDescriptor, Message param1Message) throws IOException {
      Message.Builder builder = param1Message.newBuilderForType();
      if (!param1FieldDescriptor.isRepeated()) {
        param1Message = (Message)getField(param1FieldDescriptor);
        if (param1Message != null)
          builder.mergeFrom(param1Message); 
      } 
      param1CodedInputStream.readGroup(param1FieldDescriptor.getNumber(), builder, param1ExtensionRegistryLite);
      return builder.buildPartial();
    }
    
    public Object parseMessage(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite, Descriptors.FieldDescriptor param1FieldDescriptor, Message param1Message) throws IOException {
      Message.Builder builder = param1Message.newBuilderForType();
      if (!param1FieldDescriptor.isRepeated()) {
        Message message = (Message)getField(param1FieldDescriptor);
        if (message != null)
          builder.mergeFrom(message); 
      } 
      param1CodedInputStream.readMessage(builder, param1ExtensionRegistryLite);
      return builder.buildPartial();
    }
    
    public Object parseMessageFromBytes(ByteString param1ByteString, ExtensionRegistryLite param1ExtensionRegistryLite, Descriptors.FieldDescriptor param1FieldDescriptor, Message param1Message) throws IOException {
      Message.Builder builder = param1Message.newBuilderForType();
      if (!param1FieldDescriptor.isRepeated()) {
        Message message = (Message)getField(param1FieldDescriptor);
        if (message != null)
          builder.mergeFrom(message); 
      } 
      builder.mergeFrom(param1ByteString, param1ExtensionRegistryLite);
      return builder.buildPartial();
    }
    
    public MessageReflection.MergeTarget setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      this.extensions.setField(param1FieldDescriptor, param1Object);
      return this;
    }
    
    public MessageReflection.MergeTarget setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object) {
      this.extensions.setRepeatedField(param1FieldDescriptor, param1Int, param1Object);
      return this;
    }
  }
  
  static interface MergeTarget {
    MergeTarget addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object);
    
    MergeTarget clearField(Descriptors.FieldDescriptor param1FieldDescriptor);
    
    MergeTarget clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor);
    
    ExtensionRegistry.ExtensionInfo findExtensionByName(ExtensionRegistry param1ExtensionRegistry, String param1String);
    
    ExtensionRegistry.ExtensionInfo findExtensionByNumber(ExtensionRegistry param1ExtensionRegistry, Descriptors.Descriptor param1Descriptor, int param1Int);
    
    Object finish();
    
    ContainerType getContainerType();
    
    Descriptors.Descriptor getDescriptorForType();
    
    Object getField(Descriptors.FieldDescriptor param1FieldDescriptor);
    
    Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor param1OneofDescriptor);
    
    WireFormat.Utf8Validation getUtf8Validation(Descriptors.FieldDescriptor param1FieldDescriptor);
    
    boolean hasField(Descriptors.FieldDescriptor param1FieldDescriptor);
    
    boolean hasOneof(Descriptors.OneofDescriptor param1OneofDescriptor);
    
    MergeTarget newEmptyTargetForField(Descriptors.FieldDescriptor param1FieldDescriptor, Message param1Message);
    
    MergeTarget newMergeTargetForField(Descriptors.FieldDescriptor param1FieldDescriptor, Message param1Message);
    
    Object parseGroup(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite, Descriptors.FieldDescriptor param1FieldDescriptor, Message param1Message) throws IOException;
    
    Object parseMessage(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite, Descriptors.FieldDescriptor param1FieldDescriptor, Message param1Message) throws IOException;
    
    Object parseMessageFromBytes(ByteString param1ByteString, ExtensionRegistryLite param1ExtensionRegistryLite, Descriptors.FieldDescriptor param1FieldDescriptor, Message param1Message) throws IOException;
    
    MergeTarget setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object);
    
    MergeTarget setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object);
    
    public enum ContainerType {
      EXTENSION_SET, MESSAGE;
      
      static {
      
      }
    }
  }
  
  public enum ContainerType {
    EXTENSION_SET, MESSAGE;
    
    static {
      $VALUES = new ContainerType[] { MESSAGE, EXTENSION_SET };
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\MessageReflection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */