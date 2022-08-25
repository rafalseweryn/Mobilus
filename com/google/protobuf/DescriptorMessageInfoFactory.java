package com.google.protobuf;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

final class DescriptorMessageInfoFactory implements MessageInfoFactory {
  private static final String GET_DEFAULT_INSTANCE_METHOD_NAME = "getDefaultInstance";
  
  private static final DescriptorMessageInfoFactory instance = new DescriptorMessageInfoFactory();
  
  private static IsInitializedCheckAnalyzer isInitializedCheckAnalyzer;
  
  private static final Set<String> specialFieldNames = new HashSet<>(Arrays.asList(new String[] { "cached_size", "serialized_size", "class" }));
  
  static {
    isInitializedCheckAnalyzer = new IsInitializedCheckAnalyzer();
  }
  
  private static Field bitField(Class<?> paramClass, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("bitField");
    stringBuilder.append(paramInt);
    stringBuilder.append("_");
    return field(paramClass, stringBuilder.toString());
  }
  
  private static FieldInfo buildOneofMember(Class<?> paramClass, Descriptors.FieldDescriptor paramFieldDescriptor, OneofState paramOneofState, boolean paramBoolean, Internal.EnumVerifier paramEnumVerifier) {
    OneofInfo oneofInfo = paramOneofState.getOneof(paramClass, paramFieldDescriptor.getContainingOneof());
    FieldType fieldType = getFieldType(paramFieldDescriptor);
    paramClass = getOneofStoredType(paramClass, paramFieldDescriptor, fieldType);
    return FieldInfo.forOneofMemberField(paramFieldDescriptor.getNumber(), fieldType, oneofInfo, paramClass, paramBoolean, paramEnumVerifier);
  }
  
  private static Field cachedSizeField(Class<?> paramClass, Descriptors.FieldDescriptor paramFieldDescriptor) {
    return field(paramClass, getCachedSizeFieldName(paramFieldDescriptor));
  }
  
  private static MessageInfo convert(Class<?> paramClass, Descriptors.Descriptor paramDescriptor) {
    StringBuilder stringBuilder;
    switch (paramDescriptor.getFile().getSyntax()) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unsupported syntax: ");
        stringBuilder.append(paramDescriptor.getFile().getSyntax());
        throw new IllegalArgumentException(stringBuilder.toString());
      case BYTES:
        return convertProto3((Class<?>)stringBuilder, paramDescriptor);
      case BOOL:
        break;
    } 
    return convertProto2((Class<?>)stringBuilder, paramDescriptor);
  }
  
  private static StructuralMessageInfo convertProto2(Class<?> paramClass, Descriptors.Descriptor paramDescriptor) {
    List<Descriptors.FieldDescriptor> list = paramDescriptor.getFields();
    StructuralMessageInfo.Builder builder = StructuralMessageInfo.newBuilder(list.size());
    builder.withDefaultInstance(getDefaultInstance(paramClass));
    builder.withSyntax(ProtoSyntax.PROTO2);
    builder.withMessageSetWireFormat(paramDescriptor.getOptions().getMessageSetWireFormat());
    OneofState oneofState = new OneofState();
    boolean bool = false;
    paramDescriptor = null;
    byte b1 = 0;
    byte b2 = b1;
    int i;
    for (i = 1;; i <<= 1) {
      if (b1 < list.size()) {
        Internal.EnumVerifier enumVerifier;
        final Descriptors.FieldDescriptor fd = list.get(b1);
        boolean bool1 = fieldDescriptor.getFile().getOptions().getJavaStringCheckUtf8();
        if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
          enumVerifier = new Internal.EnumVerifier() {
              public boolean isInRange(int param1Int) {
                boolean bool;
                if (fd.getEnumType().findValueByNumber(param1Int) != null) {
                  bool = true;
                } else {
                  bool = false;
                } 
                return bool;
              }
            };
        } else {
          enumVerifier = null;
        } 
        if (fieldDescriptor.getContainingOneof() != null) {
          builder.withField(buildOneofMember(paramClass, fieldDescriptor, oneofState, bool1, enumVerifier));
        } else {
          Field field = field(paramClass, fieldDescriptor);
          int j = fieldDescriptor.getNumber();
          FieldType fieldType = getFieldType(fieldDescriptor);
          if (fieldDescriptor.isMapField()) {
            final Descriptors.FieldDescriptor valueField = fieldDescriptor.getMessageType().findFieldByNumber(2);
            if (fieldDescriptor1.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM)
              enumVerifier = new Internal.EnumVerifier() {
                  public boolean isInRange(int param1Int) {
                    boolean bool;
                    if (valueField.getEnumType().findValueByNumber(param1Int) != null) {
                      bool = true;
                    } else {
                      bool = false;
                    } 
                    return bool;
                  }
                }; 
            builder.withField(FieldInfo.forMapField(field, j, SchemaUtil.getMapDefaultEntry(paramClass, fieldDescriptor.getName()), enumVerifier));
          } else if (fieldDescriptor.isRepeated()) {
            if (enumVerifier != null) {
              if (fieldDescriptor.isPacked()) {
                builder.withField(FieldInfo.forPackedFieldWithEnumVerifier(field, j, fieldType, enumVerifier, cachedSizeField(paramClass, fieldDescriptor)));
              } else {
                builder.withField(FieldInfo.forFieldWithEnumVerifier(field, j, fieldType, enumVerifier));
              } 
            } else if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
              builder.withField(FieldInfo.forRepeatedMessageField(field, j, fieldType, getTypeForRepeatedMessageField(paramClass, fieldDescriptor)));
            } else if (fieldDescriptor.isPacked()) {
              builder.withField(FieldInfo.forPackedField(field, j, fieldType, cachedSizeField(paramClass, fieldDescriptor)));
            } else {
              builder.withField(FieldInfo.forField(field, j, fieldType, bool1));
            } 
          } else {
            Field field1;
            Descriptors.Descriptor descriptor = paramDescriptor;
            if (paramDescriptor == null)
              field1 = bitField(paramClass, b2); 
            if (fieldDescriptor.isRequired()) {
              builder.withField(FieldInfo.forProto2RequiredField(field, j, fieldType, field1, i, bool1, enumVerifier));
              Field field2 = field1;
            } else {
              builder.withField(FieldInfo.forProto2OptionalField(field, j, fieldType, field1, i, bool1, enumVerifier));
              Field field2 = field1;
            } 
            i <<= 1;
          } 
          b1++;
        } 
      } else {
        break;
      } 
    } 
    ArrayList<Integer> arrayList = new ArrayList();
    for (i = 0; i < list.size(); i++) {
      final Descriptors.FieldDescriptor fd = list.get(i);
      if (fieldDescriptor.isRequired() || (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE && needsIsInitializedCheck(fieldDescriptor.getMessageType())))
        arrayList.add(Integer.valueOf(fieldDescriptor.getNumber())); 
    } 
    int[] arrayOfInt = new int[arrayList.size()];
    for (i = bool; i < arrayList.size(); i++)
      arrayOfInt[i] = ((Integer)arrayList.get(i)).intValue(); 
    builder.withCheckInitialized(arrayOfInt);
    return builder.build();
  }
  
  private static StructuralMessageInfo convertProto3(Class<?> paramClass, Descriptors.Descriptor paramDescriptor) {
    List<Descriptors.FieldDescriptor> list = paramDescriptor.getFields();
    StructuralMessageInfo.Builder builder = StructuralMessageInfo.newBuilder(list.size());
    builder.withDefaultInstance(getDefaultInstance(paramClass));
    builder.withSyntax(ProtoSyntax.PROTO3);
    OneofState oneofState = new OneofState();
    for (byte b = 0; b < list.size(); b++) {
      Descriptors.FieldDescriptor fieldDescriptor = list.get(b);
      if (fieldDescriptor.getContainingOneof() != null) {
        builder.withField(buildOneofMember(paramClass, fieldDescriptor, oneofState, true, null));
      } else if (fieldDescriptor.isMapField()) {
        builder.withField(FieldInfo.forMapField(field(paramClass, fieldDescriptor), fieldDescriptor.getNumber(), SchemaUtil.getMapDefaultEntry(paramClass, fieldDescriptor.getName()), null));
      } else if (fieldDescriptor.isRepeated() && fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
        builder.withField(FieldInfo.forRepeatedMessageField(field(paramClass, fieldDescriptor), fieldDescriptor.getNumber(), getFieldType(fieldDescriptor), getTypeForRepeatedMessageField(paramClass, fieldDescriptor)));
      } else if (fieldDescriptor.isPacked()) {
        builder.withField(FieldInfo.forPackedField(field(paramClass, fieldDescriptor), fieldDescriptor.getNumber(), getFieldType(fieldDescriptor), cachedSizeField(paramClass, fieldDescriptor)));
      } else {
        builder.withField(FieldInfo.forField(field(paramClass, fieldDescriptor), fieldDescriptor.getNumber(), getFieldType(fieldDescriptor), true));
      } 
    } 
    return builder.build();
  }
  
  private static Descriptors.Descriptor descriptorForType(Class<?> paramClass) {
    return getDefaultInstance(paramClass).getDescriptorForType();
  }
  
  private static Field field(Class<?> paramClass, Descriptors.FieldDescriptor paramFieldDescriptor) {
    return field(paramClass, getFieldName(paramFieldDescriptor));
  }
  
  private static Field field(Class<?> paramClass, String paramString) {
    try {
      return paramClass.getDeclaredField(paramString);
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to find field ");
      stringBuilder.append(paramString);
      stringBuilder.append(" in message class ");
      stringBuilder.append(paramClass.getName());
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
  }
  
  private static String getCachedSizeFieldName(Descriptors.FieldDescriptor paramFieldDescriptor) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(snakeCaseToCamelCase(paramFieldDescriptor.getName()));
    stringBuilder.append("MemoizedSerializedSize");
    return stringBuilder.toString();
  }
  
  private static Message getDefaultInstance(Class<?> paramClass) {
    try {
      return (Message)paramClass.getDeclaredMethod("getDefaultInstance", new Class[0]).invoke(null, new Object[0]);
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to get default instance for message class ");
      stringBuilder.append(paramClass.getName());
      throw new IllegalArgumentException(stringBuilder.toString(), exception);
    } 
  }
  
  static String getFieldName(Descriptors.FieldDescriptor paramFieldDescriptor) {
    String str1;
    String str2;
    if (paramFieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
      str1 = paramFieldDescriptor.getMessageType().getName();
    } else {
      str1 = str1.getName();
    } 
    if (specialFieldNames.contains(str1)) {
      str2 = "__";
    } else {
      str2 = "_";
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(snakeCaseToCamelCase(str1));
    stringBuilder.append(str2);
    return stringBuilder.toString();
  }
  
  private static FieldType getFieldType(Descriptors.FieldDescriptor paramFieldDescriptor) {
    FieldType fieldType;
    StringBuilder stringBuilder;
    switch (paramFieldDescriptor.getType()) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unsupported field type: ");
        stringBuilder.append(paramFieldDescriptor.getType());
        throw new IllegalArgumentException(stringBuilder.toString());
      case UINT64:
        if (!paramFieldDescriptor.isRepeated())
          return FieldType.UINT64; 
        if (paramFieldDescriptor.isPacked()) {
          fieldType = FieldType.UINT64_LIST_PACKED;
        } else {
          fieldType = FieldType.UINT64_LIST;
        } 
        return fieldType;
      case UINT32:
        if (!fieldType.isRepeated())
          return FieldType.UINT32; 
        if (fieldType.isPacked()) {
          fieldType = FieldType.UINT32_LIST_PACKED;
        } else {
          fieldType = FieldType.UINT32_LIST;
        } 
        return fieldType;
      case STRING:
        if (fieldType.isRepeated()) {
          fieldType = FieldType.STRING_LIST;
        } else {
          fieldType = FieldType.STRING;
        } 
        return fieldType;
      case SINT64:
        if (!fieldType.isRepeated())
          return FieldType.SINT64; 
        if (fieldType.isPacked()) {
          fieldType = FieldType.SINT64_LIST_PACKED;
        } else {
          fieldType = FieldType.SINT64_LIST;
        } 
        return fieldType;
      case SINT32:
        if (!fieldType.isRepeated())
          return FieldType.SINT32; 
        if (fieldType.isPacked()) {
          fieldType = FieldType.SINT32_LIST_PACKED;
        } else {
          fieldType = FieldType.SINT32_LIST;
        } 
        return fieldType;
      case SFIXED64:
        if (!fieldType.isRepeated())
          return FieldType.SFIXED64; 
        if (fieldType.isPacked()) {
          fieldType = FieldType.SFIXED64_LIST_PACKED;
        } else {
          fieldType = FieldType.SFIXED64_LIST;
        } 
        return fieldType;
      case SFIXED32:
        if (!fieldType.isRepeated())
          return FieldType.SFIXED32; 
        if (fieldType.isPacked()) {
          fieldType = FieldType.SFIXED32_LIST_PACKED;
        } else {
          fieldType = FieldType.SFIXED32_LIST;
        } 
        return fieldType;
      case MESSAGE:
        if (fieldType.isMapField())
          return FieldType.MAP; 
        if (fieldType.isRepeated()) {
          fieldType = FieldType.MESSAGE_LIST;
        } else {
          fieldType = FieldType.MESSAGE;
        } 
        return fieldType;
      case INT64:
        if (!fieldType.isRepeated())
          return FieldType.INT64; 
        if (fieldType.isPacked()) {
          fieldType = FieldType.INT64_LIST_PACKED;
        } else {
          fieldType = FieldType.INT64_LIST;
        } 
        return fieldType;
      case INT32:
        if (!fieldType.isRepeated())
          return FieldType.INT32; 
        if (fieldType.isPacked()) {
          fieldType = FieldType.INT32_LIST_PACKED;
        } else {
          fieldType = FieldType.INT32_LIST;
        } 
        return fieldType;
      case GROUP:
        if (fieldType.isRepeated()) {
          fieldType = FieldType.GROUP_LIST;
        } else {
          fieldType = FieldType.GROUP;
        } 
        return fieldType;
      case FLOAT:
        if (!fieldType.isRepeated())
          return FieldType.FLOAT; 
        if (fieldType.isPacked()) {
          fieldType = FieldType.FLOAT_LIST_PACKED;
        } else {
          fieldType = FieldType.FLOAT_LIST;
        } 
        return fieldType;
      case FIXED64:
        if (!fieldType.isRepeated())
          return FieldType.FIXED64; 
        if (fieldType.isPacked()) {
          fieldType = FieldType.FIXED64_LIST_PACKED;
        } else {
          fieldType = FieldType.FIXED64_LIST;
        } 
        return fieldType;
      case FIXED32:
        if (!fieldType.isRepeated())
          return FieldType.FIXED32; 
        if (fieldType.isPacked()) {
          fieldType = FieldType.FIXED32_LIST_PACKED;
        } else {
          fieldType = FieldType.FIXED32_LIST;
        } 
        return fieldType;
      case ENUM:
        if (!fieldType.isRepeated())
          return FieldType.ENUM; 
        if (fieldType.isPacked()) {
          fieldType = FieldType.ENUM_LIST_PACKED;
        } else {
          fieldType = FieldType.ENUM_LIST;
        } 
        return fieldType;
      case DOUBLE:
        if (!fieldType.isRepeated())
          return FieldType.DOUBLE; 
        if (fieldType.isPacked()) {
          fieldType = FieldType.DOUBLE_LIST_PACKED;
        } else {
          fieldType = FieldType.DOUBLE_LIST;
        } 
        return fieldType;
      case BYTES:
        if (fieldType.isRepeated()) {
          fieldType = FieldType.BYTES_LIST;
        } else {
          fieldType = FieldType.BYTES;
        } 
        return fieldType;
      case BOOL:
        break;
    } 
    if (!fieldType.isRepeated())
      return FieldType.BOOL; 
    if (fieldType.isPacked()) {
      fieldType = FieldType.BOOL_LIST_PACKED;
    } else {
      fieldType = FieldType.BOOL_LIST;
    } 
    return fieldType;
  }
  
  public static DescriptorMessageInfoFactory getInstance() {
    return instance;
  }
  
  private static Class<?> getOneofStoredType(Class<?> paramClass, Descriptors.FieldDescriptor paramFieldDescriptor, FieldType paramFieldType) {
    StringBuilder stringBuilder;
    switch (paramFieldType.getJavaType()) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid type for oneof: ");
        stringBuilder.append(paramFieldType);
        throw new IllegalArgumentException(stringBuilder.toString());
      case INT32:
        return getOneofStoredTypeForMessage((Class<?>)stringBuilder, paramFieldDescriptor);
      case GROUP:
        return String.class;
      case FLOAT:
        return Long.class;
      case FIXED32:
      case FIXED64:
        return Integer.class;
      case ENUM:
        return Float.class;
      case DOUBLE:
        return Double.class;
      case BYTES:
        return ByteString.class;
      case BOOL:
        break;
    } 
    return Boolean.class;
  }
  
  private static Class<?> getOneofStoredTypeForMessage(Class<?> paramClass, Descriptors.FieldDescriptor paramFieldDescriptor) {
    try {
      String str;
      if (paramFieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
        str = paramFieldDescriptor.getMessageType().getName();
      } else {
        str = str.getName();
      } 
      return paramClass.getDeclaredMethod(getterForField(str), new Class[0]).getReturnType();
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    } 
  }
  
  private static Class<?> getTypeForRepeatedMessageField(Class<?> paramClass, Descriptors.FieldDescriptor paramFieldDescriptor) {
    try {
      String str;
      if (paramFieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
        str = paramFieldDescriptor.getMessageType().getName();
      } else {
        str = str.getName();
      } 
      return paramClass.getDeclaredMethod(getterForField(str), new Class[] { int.class }).getReturnType();
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    } 
  }
  
  private static String getterForField(String paramString) {
    String str = snakeCaseToCamelCase(paramString);
    StringBuilder stringBuilder = new StringBuilder("get");
    stringBuilder.append(Character.toUpperCase(str.charAt(0)));
    stringBuilder.append(str.substring(1, str.length()));
    return stringBuilder.toString();
  }
  
  private static boolean needsIsInitializedCheck(Descriptors.Descriptor paramDescriptor) {
    return isInitializedCheckAnalyzer.needsIsInitializedCheck(paramDescriptor);
  }
  
  private static String snakeCaseToCamelCase(String paramString) {
    StringBuilder stringBuilder = new StringBuilder(paramString.length() + 1);
    byte b1 = 0;
    byte b2 = b1;
    while (true) {
      if (b1 < paramString.length()) {
        char c = paramString.charAt(b1);
        if (c != '_')
          if (Character.isDigit(c)) {
            stringBuilder.append(c);
          } else {
            if (b2 != 0) {
              stringBuilder.append(Character.toUpperCase(c));
              b2 = 0;
            } else if (b1 == 0) {
              stringBuilder.append(Character.toLowerCase(c));
            } else {
              stringBuilder.append(c);
            } 
            b1++;
          }  
        b2 = 1;
      } else {
        break;
      } 
      b1++;
    } 
    return stringBuilder.toString();
  }
  
  public boolean isSupported(Class<?> paramClass) {
    return GeneratedMessageV3.class.isAssignableFrom(paramClass);
  }
  
  public MessageInfo messageInfoFor(Class<?> paramClass) {
    if (!GeneratedMessageV3.class.isAssignableFrom(paramClass)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unsupported message type: ");
      stringBuilder.append(paramClass.getName());
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    return convert(paramClass, descriptorForType(paramClass));
  }
  
  static class IsInitializedCheckAnalyzer {
    private int index = 0;
    
    private final Map<Descriptors.Descriptor, Node> nodeCache = new HashMap<>();
    
    private final Map<Descriptors.Descriptor, Boolean> resultCache = new ConcurrentHashMap<>();
    
    private final Stack<Node> stack = new Stack<>();
    
    private void analyze(StronglyConnectedComponent param1StronglyConnectedComponent) {
      boolean bool;
      null = param1StronglyConnectedComponent.messages.iterator();
      label27: while (true) {
        boolean bool1 = null.hasNext();
        bool = true;
        if (bool1) {
          Descriptors.Descriptor descriptor = null.next();
          if (descriptor.isExtendable())
            break; 
          for (Descriptors.FieldDescriptor fieldDescriptor : descriptor.getFields()) {
            if (fieldDescriptor.isRequired())
              break label27; 
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
              Node node = this.nodeCache.get(fieldDescriptor.getMessageType());
              if (node.component != param1StronglyConnectedComponent && node.component.needsIsInitializedCheck)
                break label27; 
            } 
          } 
          continue;
        } 
        bool = false;
        break;
      } 
      param1StronglyConnectedComponent.needsIsInitializedCheck = bool;
      for (Descriptors.Descriptor descriptor : param1StronglyConnectedComponent.messages)
        this.resultCache.put(descriptor, Boolean.valueOf(param1StronglyConnectedComponent.needsIsInitializedCheck)); 
    }
    
    private Node dfs(Descriptors.Descriptor param1Descriptor) {
      int i = this.index;
      this.index = i + 1;
      Node node = new Node(param1Descriptor, i);
      this.stack.push(node);
      this.nodeCache.put(param1Descriptor, node);
      for (Descriptors.FieldDescriptor fieldDescriptor : param1Descriptor.getFields()) {
        if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
          Node node1 = this.nodeCache.get(fieldDescriptor.getMessageType());
          if (node1 == null) {
            node1 = dfs(fieldDescriptor.getMessageType());
            node.lowLink = Math.min(node.lowLink, node1.lowLink);
            continue;
          } 
          if (node1.component == null)
            node.lowLink = Math.min(node.lowLink, node1.lowLink); 
        } 
      } 
      if (node.index == node.lowLink) {
        StronglyConnectedComponent stronglyConnectedComponent = new StronglyConnectedComponent();
        while (true) {
          Node node1 = this.stack.pop();
          node1.component = stronglyConnectedComponent;
          stronglyConnectedComponent.messages.add(node1.descriptor);
          if (node1 == node) {
            analyze(stronglyConnectedComponent);
            break;
          } 
        } 
      } 
      return node;
    }
    
    public boolean needsIsInitializedCheck(Descriptors.Descriptor param1Descriptor) {
      // Byte code:
      //   0: aload_0
      //   1: getfield resultCache : Ljava/util/Map;
      //   4: aload_1
      //   5: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
      //   10: checkcast java/lang/Boolean
      //   13: astore_2
      //   14: aload_2
      //   15: ifnull -> 23
      //   18: aload_2
      //   19: invokevirtual booleanValue : ()Z
      //   22: ireturn
      //   23: aload_0
      //   24: monitorenter
      //   25: aload_0
      //   26: getfield resultCache : Ljava/util/Map;
      //   29: aload_1
      //   30: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
      //   35: checkcast java/lang/Boolean
      //   38: astore_2
      //   39: aload_2
      //   40: ifnull -> 52
      //   43: aload_2
      //   44: invokevirtual booleanValue : ()Z
      //   47: istore_3
      //   48: aload_0
      //   49: monitorexit
      //   50: iload_3
      //   51: ireturn
      //   52: aload_0
      //   53: aload_1
      //   54: invokespecial dfs : (Lcom/google/protobuf/Descriptors$Descriptor;)Lcom/google/protobuf/DescriptorMessageInfoFactory$IsInitializedCheckAnalyzer$Node;
      //   57: getfield component : Lcom/google/protobuf/DescriptorMessageInfoFactory$IsInitializedCheckAnalyzer$StronglyConnectedComponent;
      //   60: getfield needsIsInitializedCheck : Z
      //   63: istore_3
      //   64: aload_0
      //   65: monitorexit
      //   66: iload_3
      //   67: ireturn
      //   68: astore_1
      //   69: aload_0
      //   70: monitorexit
      //   71: aload_1
      //   72: athrow
      // Exception table:
      //   from	to	target	type
      //   25	39	68	finally
      //   43	50	68	finally
      //   52	66	68	finally
      //   69	71	68	finally
    }
    
    private static class Node {
      DescriptorMessageInfoFactory.IsInitializedCheckAnalyzer.StronglyConnectedComponent component;
      
      final Descriptors.Descriptor descriptor;
      
      final int index;
      
      int lowLink;
      
      Node(Descriptors.Descriptor param2Descriptor, int param2Int) {
        this.descriptor = param2Descriptor;
        this.index = param2Int;
        this.lowLink = param2Int;
        this.component = null;
      }
    }
    
    private static class StronglyConnectedComponent {
      final List<Descriptors.Descriptor> messages = new ArrayList<>();
      
      boolean needsIsInitializedCheck = false;
      
      private StronglyConnectedComponent() {}
    }
  }
  
  private static class Node {
    DescriptorMessageInfoFactory.IsInitializedCheckAnalyzer.StronglyConnectedComponent component;
    
    final Descriptors.Descriptor descriptor;
    
    final int index;
    
    int lowLink;
    
    Node(Descriptors.Descriptor param1Descriptor, int param1Int) {
      this.descriptor = param1Descriptor;
      this.index = param1Int;
      this.lowLink = param1Int;
      this.component = null;
    }
  }
  
  private static class StronglyConnectedComponent {
    final List<Descriptors.Descriptor> messages = new ArrayList<>();
    
    boolean needsIsInitializedCheck = false;
    
    private StronglyConnectedComponent() {}
  }
  
  private static final class OneofState {
    private OneofInfo[] oneofs = new OneofInfo[2];
    
    private OneofState() {}
    
    private static OneofInfo newInfo(Class<?> param1Class, Descriptors.OneofDescriptor param1OneofDescriptor) {
      String str1 = DescriptorMessageInfoFactory.snakeCaseToCamelCase(param1OneofDescriptor.getName());
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(str1);
      stringBuilder1.append("_");
      String str2 = stringBuilder1.toString();
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append(str1);
      stringBuilder2.append("Case_");
      str1 = stringBuilder2.toString();
      return new OneofInfo(param1OneofDescriptor.getIndex(), DescriptorMessageInfoFactory.field(param1Class, str1), DescriptorMessageInfoFactory.field(param1Class, str2));
    }
    
    OneofInfo getOneof(Class<?> param1Class, Descriptors.OneofDescriptor param1OneofDescriptor) {
      int i = param1OneofDescriptor.getIndex();
      if (i >= this.oneofs.length)
        this.oneofs = Arrays.<OneofInfo>copyOf(this.oneofs, i * 2); 
      OneofInfo oneofInfo1 = this.oneofs[i];
      OneofInfo oneofInfo2 = oneofInfo1;
      if (oneofInfo1 == null) {
        oneofInfo2 = newInfo(param1Class, param1OneofDescriptor);
        this.oneofs[i] = oneofInfo2;
      } 
      return oneofInfo2;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\DescriptorMessageInfoFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */