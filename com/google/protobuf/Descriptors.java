package com.google.protobuf;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.logging.Logger;

public final class Descriptors {
  private static final Logger logger = Logger.getLogger(Descriptors.class.getName());
  
  private static String computeFullName(FileDescriptor paramFileDescriptor, Descriptor paramDescriptor, String paramString) {
    StringBuilder stringBuilder;
    if (paramDescriptor != null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramDescriptor.getFullName());
      stringBuilder.append('.');
      stringBuilder.append(paramString);
      return stringBuilder.toString();
    } 
    String str = stringBuilder.getPackage();
    if (!str.isEmpty()) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(str);
      stringBuilder.append('.');
      stringBuilder.append(paramString);
      return stringBuilder.toString();
    } 
    return paramString;
  }
  
  public static final class Descriptor extends GenericDescriptor {
    private final Descriptor containingType;
    
    private final Descriptors.EnumDescriptor[] enumTypes;
    
    private final Descriptors.FieldDescriptor[] extensions;
    
    private final Descriptors.FieldDescriptor[] fields;
    
    private final Descriptors.FileDescriptor file;
    
    private final String fullName;
    
    private final int index;
    
    private final Descriptor[] nestedTypes;
    
    private final Descriptors.OneofDescriptor[] oneofs;
    
    private DescriptorProtos.DescriptorProto proto;
    
    private Descriptor(DescriptorProtos.DescriptorProto param1DescriptorProto, Descriptors.FileDescriptor param1FileDescriptor, Descriptor param1Descriptor, int param1Int) throws Descriptors.DescriptorValidationException {
      this.index = param1Int;
      this.proto = param1DescriptorProto;
      this.fullName = Descriptors.computeFullName(param1FileDescriptor, param1Descriptor, param1DescriptorProto.getName());
      this.file = param1FileDescriptor;
      this.containingType = param1Descriptor;
      this.oneofs = new Descriptors.OneofDescriptor[param1DescriptorProto.getOneofDeclCount()];
      boolean bool = false;
      for (param1Int = 0; param1Int < param1DescriptorProto.getOneofDeclCount(); param1Int++)
        this.oneofs[param1Int] = new Descriptors.OneofDescriptor(param1DescriptorProto.getOneofDecl(param1Int), param1FileDescriptor, this, param1Int); 
      this.nestedTypes = new Descriptor[param1DescriptorProto.getNestedTypeCount()];
      for (param1Int = 0; param1Int < param1DescriptorProto.getNestedTypeCount(); param1Int++)
        this.nestedTypes[param1Int] = new Descriptor(param1DescriptorProto.getNestedType(param1Int), param1FileDescriptor, this, param1Int); 
      this.enumTypes = new Descriptors.EnumDescriptor[param1DescriptorProto.getEnumTypeCount()];
      for (param1Int = 0; param1Int < param1DescriptorProto.getEnumTypeCount(); param1Int++)
        this.enumTypes[param1Int] = new Descriptors.EnumDescriptor(param1DescriptorProto.getEnumType(param1Int), param1FileDescriptor, this, param1Int); 
      this.fields = new Descriptors.FieldDescriptor[param1DescriptorProto.getFieldCount()];
      for (param1Int = 0; param1Int < param1DescriptorProto.getFieldCount(); param1Int++)
        this.fields[param1Int] = new Descriptors.FieldDescriptor(param1DescriptorProto.getField(param1Int), param1FileDescriptor, this, param1Int, false); 
      this.extensions = new Descriptors.FieldDescriptor[param1DescriptorProto.getExtensionCount()];
      for (param1Int = 0; param1Int < param1DescriptorProto.getExtensionCount(); param1Int++)
        this.extensions[param1Int] = new Descriptors.FieldDescriptor(param1DescriptorProto.getExtension(param1Int), param1FileDescriptor, this, param1Int, true); 
      byte b = 0;
      while (true) {
        param1Int = bool;
        if (b < param1DescriptorProto.getOneofDeclCount()) {
          Descriptors.OneofDescriptor.access$1902(this.oneofs[b], new Descriptors.FieldDescriptor[this.oneofs[b].getFieldCount()]);
          Descriptors.OneofDescriptor.access$2002(this.oneofs[b], 0);
          b++;
          continue;
        } 
        break;
      } 
      while (param1Int < param1DescriptorProto.getFieldCount()) {
        Descriptors.OneofDescriptor oneofDescriptor = this.fields[param1Int].getContainingOneof();
        if (oneofDescriptor != null)
          oneofDescriptor.fields[Descriptors.OneofDescriptor.access$2008(oneofDescriptor)] = this.fields[param1Int]; 
        param1Int++;
      } 
      param1FileDescriptor.pool.addSymbol(this);
    }
    
    Descriptor(String param1String) throws Descriptors.DescriptorValidationException {
      String str1;
      String str2;
      int i = param1String.lastIndexOf('.');
      if (i != -1) {
        str1 = param1String.substring(i + 1);
        str2 = param1String.substring(0, i);
      } else {
        str2 = "";
        str1 = param1String;
      } 
      this.index = 0;
      this.proto = DescriptorProtos.DescriptorProto.newBuilder().setName(str1).addExtensionRange(DescriptorProtos.DescriptorProto.ExtensionRange.newBuilder().setStart(1).setEnd(536870912).build()).build();
      this.fullName = param1String;
      this.containingType = null;
      this.nestedTypes = new Descriptor[0];
      this.enumTypes = new Descriptors.EnumDescriptor[0];
      this.fields = new Descriptors.FieldDescriptor[0];
      this.extensions = new Descriptors.FieldDescriptor[0];
      this.oneofs = new Descriptors.OneofDescriptor[0];
      this.file = new Descriptors.FileDescriptor(str2, this);
    }
    
    private void crossLink() throws Descriptors.DescriptorValidationException {
      Descriptor[] arrayOfDescriptor = this.nestedTypes;
      int i = arrayOfDescriptor.length;
      boolean bool = false;
      byte b;
      for (b = 0; b < i; b++)
        arrayOfDescriptor[b].crossLink(); 
      Descriptors.FieldDescriptor[] arrayOfFieldDescriptor = this.fields;
      i = arrayOfFieldDescriptor.length;
      for (b = 0; b < i; b++)
        arrayOfFieldDescriptor[b].crossLink(); 
      arrayOfFieldDescriptor = this.extensions;
      i = arrayOfFieldDescriptor.length;
      for (b = bool; b < i; b++)
        arrayOfFieldDescriptor[b].crossLink(); 
    }
    
    private void setProto(DescriptorProtos.DescriptorProto param1DescriptorProto) {
      byte b3;
      this.proto = param1DescriptorProto;
      byte b1 = 0;
      byte b2;
      for (b2 = 0; b2 < this.nestedTypes.length; b2++)
        this.nestedTypes[b2].setProto(param1DescriptorProto.getNestedType(b2)); 
      for (b2 = 0; b2 < this.oneofs.length; b2++)
        this.oneofs[b2].setProto(param1DescriptorProto.getOneofDecl(b2)); 
      for (b2 = 0; b2 < this.enumTypes.length; b2++)
        this.enumTypes[b2].setProto(param1DescriptorProto.getEnumType(b2)); 
      b2 = 0;
      while (true) {
        b3 = b1;
        if (b2 < this.fields.length) {
          this.fields[b2].setProto(param1DescriptorProto.getField(b2));
          b2++;
          continue;
        } 
        break;
      } 
      while (b3 < this.extensions.length) {
        this.extensions[b3].setProto(param1DescriptorProto.getExtension(b3));
        b3++;
      } 
    }
    
    public Descriptors.EnumDescriptor findEnumTypeByName(String param1String) {
      Descriptors.DescriptorPool descriptorPool = this.file.pool;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this.fullName);
      stringBuilder.append('.');
      stringBuilder.append(param1String);
      Descriptors.GenericDescriptor genericDescriptor = descriptorPool.findSymbol(stringBuilder.toString());
      return (genericDescriptor != null && genericDescriptor instanceof Descriptors.EnumDescriptor) ? (Descriptors.EnumDescriptor)genericDescriptor : null;
    }
    
    public Descriptors.FieldDescriptor findFieldByName(String param1String) {
      Descriptors.DescriptorPool descriptorPool = this.file.pool;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this.fullName);
      stringBuilder.append('.');
      stringBuilder.append(param1String);
      Descriptors.GenericDescriptor genericDescriptor = descriptorPool.findSymbol(stringBuilder.toString());
      return (genericDescriptor != null && genericDescriptor instanceof Descriptors.FieldDescriptor) ? (Descriptors.FieldDescriptor)genericDescriptor : null;
    }
    
    public Descriptors.FieldDescriptor findFieldByNumber(int param1Int) {
      return (Descriptors.FieldDescriptor)this.file.pool.fieldsByNumber.get(new Descriptors.DescriptorPool.DescriptorIntPair(this, param1Int));
    }
    
    public Descriptor findNestedTypeByName(String param1String) {
      Descriptors.DescriptorPool descriptorPool = this.file.pool;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this.fullName);
      stringBuilder.append('.');
      stringBuilder.append(param1String);
      Descriptors.GenericDescriptor genericDescriptor = descriptorPool.findSymbol(stringBuilder.toString());
      return (genericDescriptor != null && genericDescriptor instanceof Descriptor) ? (Descriptor)genericDescriptor : null;
    }
    
    public Descriptor getContainingType() {
      return this.containingType;
    }
    
    public List<Descriptors.EnumDescriptor> getEnumTypes() {
      return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
    }
    
    public List<Descriptors.FieldDescriptor> getExtensions() {
      return Collections.unmodifiableList(Arrays.asList(this.extensions));
    }
    
    public List<Descriptors.FieldDescriptor> getFields() {
      return Collections.unmodifiableList(Arrays.asList(this.fields));
    }
    
    public Descriptors.FileDescriptor getFile() {
      return this.file;
    }
    
    public String getFullName() {
      return this.fullName;
    }
    
    public int getIndex() {
      return this.index;
    }
    
    public String getName() {
      return this.proto.getName();
    }
    
    public List<Descriptor> getNestedTypes() {
      return Collections.unmodifiableList(Arrays.asList(this.nestedTypes));
    }
    
    public List<Descriptors.OneofDescriptor> getOneofs() {
      return Collections.unmodifiableList(Arrays.asList(this.oneofs));
    }
    
    public DescriptorProtos.MessageOptions getOptions() {
      return this.proto.getOptions();
    }
    
    public boolean isExtendable() {
      boolean bool;
      if (this.proto.getExtensionRangeList().size() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isExtensionNumber(int param1Int) {
      for (DescriptorProtos.DescriptorProto.ExtensionRange extensionRange : this.proto.getExtensionRangeList()) {
        if (extensionRange.getStart() <= param1Int && param1Int < extensionRange.getEnd())
          return true; 
      } 
      return false;
    }
    
    public boolean isReservedName(String param1String) {
      Internal.checkNotNull(param1String);
      Iterator<String> iterator = this.proto.getReservedNameList().iterator();
      while (iterator.hasNext()) {
        if (((String)iterator.next()).equals(param1String))
          return true; 
      } 
      return false;
    }
    
    public boolean isReservedNumber(int param1Int) {
      for (DescriptorProtos.DescriptorProto.ReservedRange reservedRange : this.proto.getReservedRangeList()) {
        if (reservedRange.getStart() <= param1Int && param1Int < reservedRange.getEnd())
          return true; 
      } 
      return false;
    }
    
    public DescriptorProtos.DescriptorProto toProto() {
      return this.proto;
    }
  }
  
  private static final class DescriptorPool {
    private boolean allowUnknownDependencies;
    
    private final Set<Descriptors.FileDescriptor> dependencies = new HashSet<>();
    
    private final Map<String, Descriptors.GenericDescriptor> descriptorsByName = new HashMap<>();
    
    private final Map<DescriptorIntPair, Descriptors.EnumValueDescriptor> enumValuesByNumber = new HashMap<>();
    
    private final Map<DescriptorIntPair, Descriptors.FieldDescriptor> fieldsByNumber = new HashMap<>();
    
    DescriptorPool(Descriptors.FileDescriptor[] param1ArrayOfFileDescriptor, boolean param1Boolean) {
      this.allowUnknownDependencies = param1Boolean;
      for (byte b = 0; b < param1ArrayOfFileDescriptor.length; b++) {
        this.dependencies.add(param1ArrayOfFileDescriptor[b]);
        importPublicDependencies(param1ArrayOfFileDescriptor[b]);
      } 
      Iterator<Descriptors.FileDescriptor> iterator = this.dependencies.iterator();
      while (iterator.hasNext()) {
        Descriptors.FileDescriptor fileDescriptor = iterator.next();
        try {
          addPackage(fileDescriptor.getPackage(), fileDescriptor);
        } catch (DescriptorValidationException descriptorValidationException) {
          throw new AssertionError(descriptorValidationException);
        } 
      } 
    }
    
    private void importPublicDependencies(Descriptors.FileDescriptor param1FileDescriptor) {
      for (Descriptors.FileDescriptor param1FileDescriptor : param1FileDescriptor.getPublicDependencies()) {
        if (this.dependencies.add(param1FileDescriptor))
          importPublicDependencies(param1FileDescriptor); 
      } 
    }
    
    static void validateSymbolName(Descriptors.GenericDescriptor param1GenericDescriptor) throws Descriptors.DescriptorValidationException {
      String str = param1GenericDescriptor.getName();
      if (str.length() == 0)
        throw new Descriptors.DescriptorValidationException(param1GenericDescriptor, "Missing name."); 
      byte b = 0;
      while (b < str.length()) {
        char c = str.charAt(b);
        if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || c == '_' || ('0' <= c && c <= '9' && b > 0)) {
          b++;
          continue;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('"');
        stringBuilder.append(str);
        stringBuilder.append("\" is not a valid identifier.");
        throw new Descriptors.DescriptorValidationException(param1GenericDescriptor, stringBuilder.toString());
      } 
    }
    
    void addEnumValueByNumber(Descriptors.EnumValueDescriptor param1EnumValueDescriptor) {
      DescriptorIntPair descriptorIntPair = new DescriptorIntPair(param1EnumValueDescriptor.getType(), param1EnumValueDescriptor.getNumber());
      param1EnumValueDescriptor = this.enumValuesByNumber.put(descriptorIntPair, param1EnumValueDescriptor);
      if (param1EnumValueDescriptor != null)
        this.enumValuesByNumber.put(descriptorIntPair, param1EnumValueDescriptor); 
    }
    
    void addFieldByNumber(Descriptors.FieldDescriptor param1FieldDescriptor) throws Descriptors.DescriptorValidationException {
      DescriptorIntPair descriptorIntPair = new DescriptorIntPair(param1FieldDescriptor.getContainingType(), param1FieldDescriptor.getNumber());
      Descriptors.FieldDescriptor fieldDescriptor = this.fieldsByNumber.put(descriptorIntPair, param1FieldDescriptor);
      if (fieldDescriptor != null) {
        this.fieldsByNumber.put(descriptorIntPair, fieldDescriptor);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Field number ");
        stringBuilder.append(param1FieldDescriptor.getNumber());
        stringBuilder.append(" has already been used in \"");
        stringBuilder.append(param1FieldDescriptor.getContainingType().getFullName());
        stringBuilder.append("\" by field \"");
        stringBuilder.append(fieldDescriptor.getName());
        stringBuilder.append("\".");
        throw new Descriptors.DescriptorValidationException(param1FieldDescriptor, stringBuilder.toString());
      } 
    }
    
    void addPackage(String param1String, Descriptors.FileDescriptor param1FileDescriptor) throws Descriptors.DescriptorValidationException {
      String str;
      int i = param1String.lastIndexOf('.');
      if (i == -1) {
        str = param1String;
      } else {
        addPackage(param1String.substring(0, i), param1FileDescriptor);
        str = param1String.substring(i + 1);
      } 
      Descriptors.GenericDescriptor genericDescriptor = this.descriptorsByName.put(param1String, new PackageDescriptor(str, param1String, param1FileDescriptor));
      if (genericDescriptor != null) {
        this.descriptorsByName.put(param1String, genericDescriptor);
        if (!(genericDescriptor instanceof PackageDescriptor)) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append('"');
          stringBuilder.append(str);
          stringBuilder.append("\" is already defined (as something other than a package) in file \"");
          stringBuilder.append(genericDescriptor.getFile().getName());
          stringBuilder.append("\".");
          throw new Descriptors.DescriptorValidationException(param1FileDescriptor, stringBuilder.toString());
        } 
      } 
    }
    
    void addSymbol(Descriptors.GenericDescriptor param1GenericDescriptor) throws Descriptors.DescriptorValidationException {
      validateSymbolName(param1GenericDescriptor);
      String str = param1GenericDescriptor.getFullName();
      Descriptors.GenericDescriptor genericDescriptor = this.descriptorsByName.put(str, param1GenericDescriptor);
      if (genericDescriptor != null) {
        this.descriptorsByName.put(str, genericDescriptor);
        if (param1GenericDescriptor.getFile() == genericDescriptor.getFile()) {
          int i = str.lastIndexOf('.');
          if (i == -1) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append('"');
            stringBuilder2.append(str);
            stringBuilder2.append("\" is already defined.");
            throw new Descriptors.DescriptorValidationException(param1GenericDescriptor, stringBuilder2.toString());
          } 
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append('"');
          stringBuilder1.append(str.substring(i + 1));
          stringBuilder1.append("\" is already defined in \"");
          stringBuilder1.append(str.substring(0, i));
          stringBuilder1.append("\".");
          throw new Descriptors.DescriptorValidationException(param1GenericDescriptor, stringBuilder1.toString());
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('"');
        stringBuilder.append(str);
        stringBuilder.append("\" is already defined in file \"");
        stringBuilder.append(genericDescriptor.getFile().getName());
        stringBuilder.append("\".");
        throw new Descriptors.DescriptorValidationException(param1GenericDescriptor, stringBuilder.toString());
      } 
    }
    
    Descriptors.GenericDescriptor findSymbol(String param1String) {
      return findSymbol(param1String, SearchFilter.ALL_SYMBOLS);
    }
    
    Descriptors.GenericDescriptor findSymbol(String param1String, SearchFilter param1SearchFilter) {
      Descriptors.GenericDescriptor genericDescriptor = this.descriptorsByName.get(param1String);
      if (genericDescriptor != null && (param1SearchFilter == SearchFilter.ALL_SYMBOLS || (param1SearchFilter == SearchFilter.TYPES_ONLY && isType(genericDescriptor)) || (param1SearchFilter == SearchFilter.AGGREGATES_ONLY && isAggregate(genericDescriptor))))
        return genericDescriptor; 
      Iterator<Descriptors.FileDescriptor> iterator = this.dependencies.iterator();
      while (iterator.hasNext()) {
        genericDescriptor = ((Descriptors.FileDescriptor)iterator.next()).pool.descriptorsByName.get(param1String);
        if (genericDescriptor != null && (param1SearchFilter == SearchFilter.ALL_SYMBOLS || (param1SearchFilter == SearchFilter.TYPES_ONLY && isType(genericDescriptor)) || (param1SearchFilter == SearchFilter.AGGREGATES_ONLY && isAggregate(genericDescriptor))))
          return genericDescriptor; 
      } 
      return null;
    }
    
    boolean isAggregate(Descriptors.GenericDescriptor param1GenericDescriptor) {
      return (param1GenericDescriptor instanceof Descriptors.Descriptor || param1GenericDescriptor instanceof Descriptors.EnumDescriptor || param1GenericDescriptor instanceof PackageDescriptor || param1GenericDescriptor instanceof Descriptors.ServiceDescriptor);
    }
    
    boolean isType(Descriptors.GenericDescriptor param1GenericDescriptor) {
      return (param1GenericDescriptor instanceof Descriptors.Descriptor || param1GenericDescriptor instanceof Descriptors.EnumDescriptor);
    }
    
    Descriptors.GenericDescriptor lookupSymbol(String param1String, Descriptors.GenericDescriptor param1GenericDescriptor, SearchFilter param1SearchFilter) throws Descriptors.DescriptorValidationException {
      // Byte code:
      //   0: aload_1
      //   1: ldc_w '.'
      //   4: invokevirtual startsWith : (Ljava/lang/String;)Z
      //   7: ifeq -> 29
      //   10: aload_1
      //   11: iconst_1
      //   12: invokevirtual substring : (I)Ljava/lang/String;
      //   15: astore #4
      //   17: aload_0
      //   18: aload #4
      //   20: aload_3
      //   21: invokevirtual findSymbol : (Ljava/lang/String;Lcom/google/protobuf/Descriptors$DescriptorPool$SearchFilter;)Lcom/google/protobuf/Descriptors$GenericDescriptor;
      //   24: astore #5
      //   26: goto -> 195
      //   29: aload_1
      //   30: bipush #46
      //   32: invokevirtual indexOf : (I)I
      //   35: istore #6
      //   37: iload #6
      //   39: iconst_m1
      //   40: if_icmpne -> 49
      //   43: aload_1
      //   44: astore #4
      //   46: goto -> 58
      //   49: aload_1
      //   50: iconst_0
      //   51: iload #6
      //   53: invokevirtual substring : (II)Ljava/lang/String;
      //   56: astore #4
      //   58: new java/lang/StringBuilder
      //   61: dup
      //   62: aload_2
      //   63: invokevirtual getFullName : ()Ljava/lang/String;
      //   66: invokespecial <init> : (Ljava/lang/String;)V
      //   69: astore #7
      //   71: aload #7
      //   73: ldc_w '.'
      //   76: invokevirtual lastIndexOf : (Ljava/lang/String;)I
      //   79: istore #8
      //   81: iload #8
      //   83: iconst_m1
      //   84: if_icmpne -> 101
      //   87: aload_0
      //   88: aload_1
      //   89: aload_3
      //   90: invokevirtual findSymbol : (Ljava/lang/String;Lcom/google/protobuf/Descriptors$DescriptorPool$SearchFilter;)Lcom/google/protobuf/Descriptors$GenericDescriptor;
      //   93: astore #5
      //   95: aload_1
      //   96: astore #4
      //   98: goto -> 195
      //   101: iload #8
      //   103: iconst_1
      //   104: iadd
      //   105: istore #9
      //   107: aload #7
      //   109: iload #9
      //   111: invokevirtual setLength : (I)V
      //   114: aload #7
      //   116: aload #4
      //   118: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   121: pop
      //   122: aload_0
      //   123: aload #7
      //   125: invokevirtual toString : ()Ljava/lang/String;
      //   128: getstatic com/google/protobuf/Descriptors$DescriptorPool$SearchFilter.AGGREGATES_ONLY : Lcom/google/protobuf/Descriptors$DescriptorPool$SearchFilter;
      //   131: invokevirtual findSymbol : (Ljava/lang/String;Lcom/google/protobuf/Descriptors$DescriptorPool$SearchFilter;)Lcom/google/protobuf/Descriptors$GenericDescriptor;
      //   134: astore #5
      //   136: aload #5
      //   138: ifnull -> 328
      //   141: iload #6
      //   143: iconst_m1
      //   144: if_icmpeq -> 176
      //   147: aload #7
      //   149: iload #9
      //   151: invokevirtual setLength : (I)V
      //   154: aload #7
      //   156: aload_1
      //   157: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   160: pop
      //   161: aload_0
      //   162: aload #7
      //   164: invokevirtual toString : ()Ljava/lang/String;
      //   167: aload_3
      //   168: invokevirtual findSymbol : (Ljava/lang/String;Lcom/google/protobuf/Descriptors$DescriptorPool$SearchFilter;)Lcom/google/protobuf/Descriptors$GenericDescriptor;
      //   171: astore #4
      //   173: goto -> 180
      //   176: aload #5
      //   178: astore #4
      //   180: aload #7
      //   182: invokevirtual toString : ()Ljava/lang/String;
      //   185: astore #7
      //   187: aload #4
      //   189: astore #5
      //   191: aload #7
      //   193: astore #4
      //   195: aload #5
      //   197: ifnonnull -> 325
      //   200: aload_0
      //   201: getfield allowUnknownDependencies : Z
      //   204: ifeq -> 282
      //   207: aload_3
      //   208: getstatic com/google/protobuf/Descriptors$DescriptorPool$SearchFilter.TYPES_ONLY : Lcom/google/protobuf/Descriptors$DescriptorPool$SearchFilter;
      //   211: if_acmpne -> 282
      //   214: invokestatic access$100 : ()Ljava/util/logging/Logger;
      //   217: astore_2
      //   218: new java/lang/StringBuilder
      //   221: dup
      //   222: invokespecial <init> : ()V
      //   225: astore_3
      //   226: aload_3
      //   227: ldc_w 'The descriptor for message type "'
      //   230: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   233: pop
      //   234: aload_3
      //   235: aload_1
      //   236: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   239: pop
      //   240: aload_3
      //   241: ldc_w '" can not be found and a placeholder is created for it'
      //   244: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   247: pop
      //   248: aload_2
      //   249: aload_3
      //   250: invokevirtual toString : ()Ljava/lang/String;
      //   253: invokevirtual warning : (Ljava/lang/String;)V
      //   256: new com/google/protobuf/Descriptors$Descriptor
      //   259: dup
      //   260: aload #4
      //   262: invokespecial <init> : (Ljava/lang/String;)V
      //   265: astore_1
      //   266: aload_0
      //   267: getfield dependencies : Ljava/util/Set;
      //   270: aload_1
      //   271: invokevirtual getFile : ()Lcom/google/protobuf/Descriptors$FileDescriptor;
      //   274: invokeinterface add : (Ljava/lang/Object;)Z
      //   279: pop
      //   280: aload_1
      //   281: areturn
      //   282: new java/lang/StringBuilder
      //   285: dup
      //   286: invokespecial <init> : ()V
      //   289: astore_3
      //   290: aload_3
      //   291: bipush #34
      //   293: invokevirtual append : (C)Ljava/lang/StringBuilder;
      //   296: pop
      //   297: aload_3
      //   298: aload_1
      //   299: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   302: pop
      //   303: aload_3
      //   304: ldc_w '" is not defined.'
      //   307: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   310: pop
      //   311: new com/google/protobuf/Descriptors$DescriptorValidationException
      //   314: dup
      //   315: aload_2
      //   316: aload_3
      //   317: invokevirtual toString : ()Ljava/lang/String;
      //   320: aconst_null
      //   321: invokespecial <init> : (Lcom/google/protobuf/Descriptors$GenericDescriptor;Ljava/lang/String;Lcom/google/protobuf/Descriptors$1;)V
      //   324: athrow
      //   325: aload #5
      //   327: areturn
      //   328: aload #7
      //   330: iload #8
      //   332: invokevirtual setLength : (I)V
      //   335: goto -> 71
    }
    
    private static final class DescriptorIntPair {
      private final Descriptors.GenericDescriptor descriptor;
      
      private final int number;
      
      DescriptorIntPair(Descriptors.GenericDescriptor param2GenericDescriptor, int param2Int) {
        this.descriptor = param2GenericDescriptor;
        this.number = param2Int;
      }
      
      public boolean equals(Object param2Object) {
        boolean bool = param2Object instanceof DescriptorIntPair;
        boolean bool1 = false;
        if (!bool)
          return false; 
        param2Object = param2Object;
        bool = bool1;
        if (this.descriptor == ((DescriptorIntPair)param2Object).descriptor) {
          bool = bool1;
          if (this.number == ((DescriptorIntPair)param2Object).number)
            bool = true; 
        } 
        return bool;
      }
      
      public int hashCode() {
        return this.descriptor.hashCode() * 65535 + this.number;
      }
    }
    
    private static final class PackageDescriptor extends Descriptors.GenericDescriptor {
      private final Descriptors.FileDescriptor file;
      
      private final String fullName;
      
      private final String name;
      
      PackageDescriptor(String param2String1, String param2String2, Descriptors.FileDescriptor param2FileDescriptor) {
        this.file = param2FileDescriptor;
        this.fullName = param2String2;
        this.name = param2String1;
      }
      
      public Descriptors.FileDescriptor getFile() {
        return this.file;
      }
      
      public String getFullName() {
        return this.fullName;
      }
      
      public String getName() {
        return this.name;
      }
      
      public Message toProto() {
        return this.file.toProto();
      }
    }
    
    enum SearchFilter {
      AGGREGATES_ONLY, ALL_SYMBOLS, TYPES_ONLY;
      
      static {
      
      }
    }
  }
  
  private static final class DescriptorIntPair {
    private final Descriptors.GenericDescriptor descriptor;
    
    private final int number;
    
    DescriptorIntPair(Descriptors.GenericDescriptor param1GenericDescriptor, int param1Int) {
      this.descriptor = param1GenericDescriptor;
      this.number = param1Int;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = param1Object instanceof DescriptorIntPair;
      boolean bool1 = false;
      if (!bool)
        return false; 
      param1Object = param1Object;
      bool = bool1;
      if (this.descriptor == ((DescriptorIntPair)param1Object).descriptor) {
        bool = bool1;
        if (this.number == ((DescriptorIntPair)param1Object).number)
          bool = true; 
      } 
      return bool;
    }
    
    public int hashCode() {
      return this.descriptor.hashCode() * 65535 + this.number;
    }
  }
  
  private static final class PackageDescriptor extends GenericDescriptor {
    private final Descriptors.FileDescriptor file;
    
    private final String fullName;
    
    private final String name;
    
    PackageDescriptor(String param1String1, String param1String2, Descriptors.FileDescriptor param1FileDescriptor) {
      this.file = param1FileDescriptor;
      this.fullName = param1String2;
      this.name = param1String1;
    }
    
    public Descriptors.FileDescriptor getFile() {
      return this.file;
    }
    
    public String getFullName() {
      return this.fullName;
    }
    
    public String getName() {
      return this.name;
    }
    
    public Message toProto() {
      return this.file.toProto();
    }
  }
  
  enum SearchFilter {
    AGGREGATES_ONLY, ALL_SYMBOLS, TYPES_ONLY;
    
    static {
      $VALUES = new SearchFilter[] { TYPES_ONLY, AGGREGATES_ONLY, ALL_SYMBOLS };
    }
  }
  
  public static class DescriptorValidationException extends Exception {
    private static final long serialVersionUID = 5750205775490483148L;
    
    private final String description;
    
    private final String name;
    
    private final Message proto;
    
    private DescriptorValidationException(Descriptors.FileDescriptor param1FileDescriptor, String param1String) {
      super(stringBuilder.toString());
      this.name = param1FileDescriptor.getName();
      this.proto = param1FileDescriptor.toProto();
      this.description = param1String;
    }
    
    private DescriptorValidationException(Descriptors.GenericDescriptor param1GenericDescriptor, String param1String) {
      super(stringBuilder.toString());
      this.name = param1GenericDescriptor.getFullName();
      this.proto = param1GenericDescriptor.toProto();
      this.description = param1String;
    }
    
    private DescriptorValidationException(Descriptors.GenericDescriptor param1GenericDescriptor, String param1String, Throwable param1Throwable) {
      this(param1GenericDescriptor, param1String);
      initCause(param1Throwable);
    }
    
    public String getDescription() {
      return this.description;
    }
    
    public Message getProblemProto() {
      return this.proto;
    }
    
    public String getProblemSymbolName() {
      return this.name;
    }
  }
  
  public static final class EnumDescriptor extends GenericDescriptor implements Internal.EnumLiteMap<EnumValueDescriptor> {
    private final Descriptors.Descriptor containingType;
    
    private final Descriptors.FileDescriptor file;
    
    private final String fullName;
    
    private final int index;
    
    private DescriptorProtos.EnumDescriptorProto proto;
    
    private final WeakHashMap<Integer, WeakReference<Descriptors.EnumValueDescriptor>> unknownValues = new WeakHashMap<>();
    
    private Descriptors.EnumValueDescriptor[] values;
    
    private EnumDescriptor(DescriptorProtos.EnumDescriptorProto param1EnumDescriptorProto, Descriptors.FileDescriptor param1FileDescriptor, Descriptors.Descriptor param1Descriptor, int param1Int) throws Descriptors.DescriptorValidationException {
      this.index = param1Int;
      this.proto = param1EnumDescriptorProto;
      this.fullName = Descriptors.computeFullName(param1FileDescriptor, param1Descriptor, param1EnumDescriptorProto.getName());
      this.file = param1FileDescriptor;
      this.containingType = param1Descriptor;
      if (param1EnumDescriptorProto.getValueCount() == 0)
        throw new Descriptors.DescriptorValidationException(this, "Enums must contain at least one value."); 
      this.values = new Descriptors.EnumValueDescriptor[param1EnumDescriptorProto.getValueCount()];
      for (param1Int = 0; param1Int < param1EnumDescriptorProto.getValueCount(); param1Int++)
        this.values[param1Int] = new Descriptors.EnumValueDescriptor(param1EnumDescriptorProto.getValue(param1Int), param1FileDescriptor, this, param1Int); 
      param1FileDescriptor.pool.addSymbol(this);
    }
    
    private void setProto(DescriptorProtos.EnumDescriptorProto param1EnumDescriptorProto) {
      this.proto = param1EnumDescriptorProto;
      for (byte b = 0; b < this.values.length; b++)
        this.values[b].setProto(param1EnumDescriptorProto.getValue(b)); 
    }
    
    public Descriptors.EnumValueDescriptor findValueByName(String param1String) {
      Descriptors.DescriptorPool descriptorPool = this.file.pool;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this.fullName);
      stringBuilder.append('.');
      stringBuilder.append(param1String);
      Descriptors.GenericDescriptor genericDescriptor = descriptorPool.findSymbol(stringBuilder.toString());
      return (genericDescriptor != null && genericDescriptor instanceof Descriptors.EnumValueDescriptor) ? (Descriptors.EnumValueDescriptor)genericDescriptor : null;
    }
    
    public Descriptors.EnumValueDescriptor findValueByNumber(int param1Int) {
      return (Descriptors.EnumValueDescriptor)this.file.pool.enumValuesByNumber.get(new Descriptors.DescriptorPool.DescriptorIntPair(this, param1Int));
    }
    
    public Descriptors.EnumValueDescriptor findValueByNumberCreatingIfUnknown(int param1Int) {
      // Byte code:
      //   0: aload_0
      //   1: iload_1
      //   2: invokevirtual findValueByNumber : (I)Lcom/google/protobuf/Descriptors$EnumValueDescriptor;
      //   5: astore_2
      //   6: aload_2
      //   7: ifnull -> 12
      //   10: aload_2
      //   11: areturn
      //   12: aload_0
      //   13: monitorenter
      //   14: new java/lang/Integer
      //   17: astore_3
      //   18: aload_3
      //   19: iload_1
      //   20: invokespecial <init> : (I)V
      //   23: aload_0
      //   24: getfield unknownValues : Ljava/util/WeakHashMap;
      //   27: aload_3
      //   28: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
      //   31: checkcast java/lang/ref/WeakReference
      //   34: astore #4
      //   36: aload #4
      //   38: ifnull -> 50
      //   41: aload #4
      //   43: invokevirtual get : ()Ljava/lang/Object;
      //   46: checkcast com/google/protobuf/Descriptors$EnumValueDescriptor
      //   49: astore_2
      //   50: aload_2
      //   51: astore #4
      //   53: aload_2
      //   54: ifnonnull -> 98
      //   57: new com/google/protobuf/Descriptors$EnumValueDescriptor
      //   60: astore #4
      //   62: aload #4
      //   64: aload_0
      //   65: getfield file : Lcom/google/protobuf/Descriptors$FileDescriptor;
      //   68: aload_0
      //   69: aload_3
      //   70: aconst_null
      //   71: invokespecial <init> : (Lcom/google/protobuf/Descriptors$FileDescriptor;Lcom/google/protobuf/Descriptors$EnumDescriptor;Ljava/lang/Integer;Lcom/google/protobuf/Descriptors$1;)V
      //   74: aload_0
      //   75: getfield unknownValues : Ljava/util/WeakHashMap;
      //   78: astore #5
      //   80: new java/lang/ref/WeakReference
      //   83: astore_2
      //   84: aload_2
      //   85: aload #4
      //   87: invokespecial <init> : (Ljava/lang/Object;)V
      //   90: aload #5
      //   92: aload_3
      //   93: aload_2
      //   94: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   97: pop
      //   98: aload_0
      //   99: monitorexit
      //   100: aload #4
      //   102: areturn
      //   103: astore_2
      //   104: aload_0
      //   105: monitorexit
      //   106: aload_2
      //   107: athrow
      // Exception table:
      //   from	to	target	type
      //   14	36	103	finally
      //   41	50	103	finally
      //   57	98	103	finally
      //   98	100	103	finally
      //   104	106	103	finally
    }
    
    public Descriptors.Descriptor getContainingType() {
      return this.containingType;
    }
    
    public Descriptors.FileDescriptor getFile() {
      return this.file;
    }
    
    public String getFullName() {
      return this.fullName;
    }
    
    public int getIndex() {
      return this.index;
    }
    
    public String getName() {
      return this.proto.getName();
    }
    
    public DescriptorProtos.EnumOptions getOptions() {
      return this.proto.getOptions();
    }
    
    int getUnknownEnumValueDescriptorCount() {
      return this.unknownValues.size();
    }
    
    public List<Descriptors.EnumValueDescriptor> getValues() {
      return Collections.unmodifiableList(Arrays.asList(this.values));
    }
    
    public DescriptorProtos.EnumDescriptorProto toProto() {
      return this.proto;
    }
  }
  
  public static final class EnumValueDescriptor extends GenericDescriptor implements Internal.EnumLite {
    private final Descriptors.FileDescriptor file;
    
    private final String fullName;
    
    private final int index;
    
    private DescriptorProtos.EnumValueDescriptorProto proto;
    
    private final Descriptors.EnumDescriptor type;
    
    private EnumValueDescriptor(DescriptorProtos.EnumValueDescriptorProto param1EnumValueDescriptorProto, Descriptors.FileDescriptor param1FileDescriptor, Descriptors.EnumDescriptor param1EnumDescriptor, int param1Int) throws Descriptors.DescriptorValidationException {
      this.index = param1Int;
      this.proto = param1EnumValueDescriptorProto;
      this.file = param1FileDescriptor;
      this.type = param1EnumDescriptor;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(param1EnumDescriptor.getFullName());
      stringBuilder.append('.');
      stringBuilder.append(param1EnumValueDescriptorProto.getName());
      this.fullName = stringBuilder.toString();
      param1FileDescriptor.pool.addSymbol(this);
      param1FileDescriptor.pool.addEnumValueByNumber(this);
    }
    
    private EnumValueDescriptor(Descriptors.FileDescriptor param1FileDescriptor, Descriptors.EnumDescriptor param1EnumDescriptor, Integer param1Integer) {
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("UNKNOWN_ENUM_VALUE_");
      stringBuilder2.append(param1EnumDescriptor.getName());
      stringBuilder2.append("_");
      stringBuilder2.append(param1Integer);
      String str = stringBuilder2.toString();
      DescriptorProtos.EnumValueDescriptorProto enumValueDescriptorProto = DescriptorProtos.EnumValueDescriptorProto.newBuilder().setName(str).setNumber(param1Integer.intValue()).build();
      this.index = -1;
      this.proto = enumValueDescriptorProto;
      this.file = param1FileDescriptor;
      this.type = param1EnumDescriptor;
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(param1EnumDescriptor.getFullName());
      stringBuilder1.append('.');
      stringBuilder1.append(enumValueDescriptorProto.getName());
      this.fullName = stringBuilder1.toString();
    }
    
    private void setProto(DescriptorProtos.EnumValueDescriptorProto param1EnumValueDescriptorProto) {
      this.proto = param1EnumValueDescriptorProto;
    }
    
    public Descriptors.FileDescriptor getFile() {
      return this.file;
    }
    
    public String getFullName() {
      return this.fullName;
    }
    
    public int getIndex() {
      return this.index;
    }
    
    public String getName() {
      return this.proto.getName();
    }
    
    public int getNumber() {
      return this.proto.getNumber();
    }
    
    public DescriptorProtos.EnumValueOptions getOptions() {
      return this.proto.getOptions();
    }
    
    public Descriptors.EnumDescriptor getType() {
      return this.type;
    }
    
    public DescriptorProtos.EnumValueDescriptorProto toProto() {
      return this.proto;
    }
    
    public String toString() {
      return this.proto.getName();
    }
  }
  
  public static final class FieldDescriptor extends GenericDescriptor implements Comparable<FieldDescriptor>, FieldSet.FieldDescriptorLite<FieldDescriptor> {
    private static final WireFormat.FieldType[] table = WireFormat.FieldType.values();
    
    private Descriptors.OneofDescriptor containingOneof;
    
    private Descriptors.Descriptor containingType;
    
    private Object defaultValue;
    
    private Descriptors.EnumDescriptor enumType;
    
    private final Descriptors.Descriptor extensionScope;
    
    private final Descriptors.FileDescriptor file;
    
    private final String fullName;
    
    private final int index;
    
    private final String jsonName;
    
    private Descriptors.Descriptor messageType;
    
    private DescriptorProtos.FieldDescriptorProto proto;
    
    private Type type;
    
    static {
      if ((Type.values()).length != (DescriptorProtos.FieldDescriptorProto.Type.values()).length)
        throw new RuntimeException("descriptor.proto has a new declared type but Descriptors.java wasn't updated."); 
    }
    
    private FieldDescriptor(DescriptorProtos.FieldDescriptorProto param1FieldDescriptorProto, Descriptors.FileDescriptor param1FileDescriptor, Descriptors.Descriptor param1Descriptor, int param1Int, boolean param1Boolean) throws Descriptors.DescriptorValidationException {
      this.index = param1Int;
      this.proto = param1FieldDescriptorProto;
      this.fullName = Descriptors.computeFullName(param1FileDescriptor, param1Descriptor, param1FieldDescriptorProto.getName());
      this.file = param1FileDescriptor;
      if (param1FieldDescriptorProto.hasJsonName()) {
        this.jsonName = param1FieldDescriptorProto.getJsonName();
      } else {
        this.jsonName = fieldNameToJsonName(param1FieldDescriptorProto.getName());
      } 
      if (param1FieldDescriptorProto.hasType())
        this.type = Type.valueOf(param1FieldDescriptorProto.getType()); 
      if (getNumber() <= 0)
        throw new Descriptors.DescriptorValidationException(this, "Field numbers must be positive integers."); 
      if (param1Boolean) {
        if (!param1FieldDescriptorProto.hasExtendee())
          throw new Descriptors.DescriptorValidationException(this, "FieldDescriptorProto.extendee not set for extension field."); 
        this.containingType = null;
        if (param1Descriptor != null) {
          this.extensionScope = param1Descriptor;
        } else {
          this.extensionScope = null;
        } 
        if (param1FieldDescriptorProto.hasOneofIndex())
          throw new Descriptors.DescriptorValidationException(this, "FieldDescriptorProto.oneof_index set for extension field."); 
        this.containingOneof = null;
      } else {
        if (param1FieldDescriptorProto.hasExtendee())
          throw new Descriptors.DescriptorValidationException(this, "FieldDescriptorProto.extendee set for non-extension field."); 
        this.containingType = param1Descriptor;
        if (param1FieldDescriptorProto.hasOneofIndex()) {
          StringBuilder stringBuilder;
          if (param1FieldDescriptorProto.getOneofIndex() < 0 || param1FieldDescriptorProto.getOneofIndex() >= param1Descriptor.toProto().getOneofDeclCount()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("FieldDescriptorProto.oneof_index is out of range for type ");
            stringBuilder.append(param1Descriptor.getName());
            throw new Descriptors.DescriptorValidationException(this, stringBuilder.toString());
          } 
          this.containingOneof = param1Descriptor.getOneofs().get(stringBuilder.getOneofIndex());
          Descriptors.OneofDescriptor.access$2008(this.containingOneof);
        } else {
          this.containingOneof = null;
        } 
        this.extensionScope = null;
      } 
      param1FileDescriptor.pool.addSymbol(this);
    }
    
    private void crossLink() throws Descriptors.DescriptorValidationException {
      if (this.proto.hasExtendee()) {
        StringBuilder stringBuilder;
        Descriptors.GenericDescriptor genericDescriptor = this.file.pool.lookupSymbol(this.proto.getExtendee(), this, Descriptors.DescriptorPool.SearchFilter.TYPES_ONLY);
        if (!(genericDescriptor instanceof Descriptors.Descriptor)) {
          stringBuilder = new StringBuilder();
          stringBuilder.append('"');
          stringBuilder.append(this.proto.getExtendee());
          stringBuilder.append("\" is not a message type.");
          throw new Descriptors.DescriptorValidationException(this, stringBuilder.toString());
        } 
        this.containingType = (Descriptors.Descriptor)stringBuilder;
        if (!getContainingType().isExtensionNumber(getNumber())) {
          stringBuilder = new StringBuilder();
          stringBuilder.append('"');
          stringBuilder.append(getContainingType().getFullName());
          stringBuilder.append("\" does not declare ");
          stringBuilder.append(getNumber());
          stringBuilder.append(" as an extension number.");
          throw new Descriptors.DescriptorValidationException(this, stringBuilder.toString());
        } 
      } 
      if (this.proto.hasTypeName()) {
        StringBuilder stringBuilder;
        Descriptors.GenericDescriptor genericDescriptor = this.file.pool.lookupSymbol(this.proto.getTypeName(), this, Descriptors.DescriptorPool.SearchFilter.TYPES_ONLY);
        if (!this.proto.hasType())
          if (genericDescriptor instanceof Descriptors.Descriptor) {
            this.type = Type.MESSAGE;
          } else if (genericDescriptor instanceof Descriptors.EnumDescriptor) {
            this.type = Type.ENUM;
          } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append('"');
            stringBuilder.append(this.proto.getTypeName());
            stringBuilder.append("\" is not a type.");
            throw new Descriptors.DescriptorValidationException(this, stringBuilder.toString());
          }  
        if (getJavaType() == JavaType.MESSAGE) {
          if (!(stringBuilder instanceof Descriptors.Descriptor)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append('"');
            stringBuilder.append(this.proto.getTypeName());
            stringBuilder.append("\" is not a message type.");
            throw new Descriptors.DescriptorValidationException(this, stringBuilder.toString());
          } 
          this.messageType = (Descriptors.Descriptor)stringBuilder;
          if (this.proto.hasDefaultValue())
            throw new Descriptors.DescriptorValidationException(this, "Messages can't have default values."); 
        } else if (getJavaType() == JavaType.ENUM) {
          if (!(stringBuilder instanceof Descriptors.EnumDescriptor)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append('"');
            stringBuilder.append(this.proto.getTypeName());
            stringBuilder.append("\" is not an enum type.");
            throw new Descriptors.DescriptorValidationException(this, stringBuilder.toString());
          } 
          this.enumType = (Descriptors.EnumDescriptor)stringBuilder;
        } else {
          throw new Descriptors.DescriptorValidationException(this, "Field with primitive type has type_name.");
        } 
      } else if (getJavaType() == JavaType.MESSAGE || getJavaType() == JavaType.ENUM) {
        throw new Descriptors.DescriptorValidationException(this, "Field with message or enum type missing type_name.");
      } 
      if (this.proto.getOptions().getPacked() && !isPackable())
        throw new Descriptors.DescriptorValidationException(this, "[packed = true] can only be specified for repeated primitive fields."); 
      if (this.proto.hasDefaultValue()) {
        if (isRepeated())
          throw new Descriptors.DescriptorValidationException(this, "Repeated fields cannot have default values."); 
        try {
          Descriptors.DescriptorValidationException descriptorValidationException;
          switch (getType()) {
            case null:
            case null:
              descriptorValidationException = new Descriptors.DescriptorValidationException();
              this(this, "Message type had default value.");
              throw descriptorValidationException;
            case null:
              this.defaultValue = this.enumType.findValueByName(this.proto.getDefaultValue());
              if (this.defaultValue == null) {
                Descriptors.DescriptorValidationException descriptorValidationException1 = new Descriptors.DescriptorValidationException();
                StringBuilder stringBuilder = new StringBuilder();
                this();
                stringBuilder.append("Unknown enum default value: \"");
                stringBuilder.append(this.proto.getDefaultValue());
                stringBuilder.append('"');
                this(this, stringBuilder.toString());
                throw descriptorValidationException1;
              } 
              break;
            case null:
              try {
                this.defaultValue = TextFormat.unescapeBytes(this.proto.getDefaultValue());
              } catch (InvalidEscapeSequenceException invalidEscapeSequenceException) {
                Descriptors.DescriptorValidationException descriptorValidationException1 = new Descriptors.DescriptorValidationException();
                StringBuilder stringBuilder = new StringBuilder();
                this();
                stringBuilder.append("Couldn't parse default value: ");
                stringBuilder.append(invalidEscapeSequenceException.getMessage());
                this(this, stringBuilder.toString(), invalidEscapeSequenceException);
                throw descriptorValidationException1;
              } 
              break;
            case null:
              this.defaultValue = this.proto.getDefaultValue();
              break;
            case null:
              this.defaultValue = Boolean.valueOf(this.proto.getDefaultValue());
              break;
            case null:
              if (this.proto.getDefaultValue().equals("inf")) {
                this.defaultValue = Double.valueOf(Double.POSITIVE_INFINITY);
                break;
              } 
              if (this.proto.getDefaultValue().equals("-inf")) {
                this.defaultValue = Double.valueOf(Double.NEGATIVE_INFINITY);
                break;
              } 
              if (this.proto.getDefaultValue().equals("nan")) {
                this.defaultValue = Double.valueOf(Double.NaN);
                break;
              } 
              this.defaultValue = Double.valueOf(this.proto.getDefaultValue());
              break;
            case null:
              if (this.proto.getDefaultValue().equals("inf")) {
                this.defaultValue = Float.valueOf(Float.POSITIVE_INFINITY);
                break;
              } 
              if (this.proto.getDefaultValue().equals("-inf")) {
                this.defaultValue = Float.valueOf(Float.NEGATIVE_INFINITY);
                break;
              } 
              if (this.proto.getDefaultValue().equals("nan")) {
                this.defaultValue = Float.valueOf(Float.NaN);
                break;
              } 
              this.defaultValue = Float.valueOf(this.proto.getDefaultValue());
              break;
            case null:
            case null:
              this.defaultValue = Long.valueOf(TextFormat.parseUInt64(this.proto.getDefaultValue()));
              break;
            case null:
            case null:
            case null:
              this.defaultValue = Long.valueOf(TextFormat.parseInt64(this.proto.getDefaultValue()));
              break;
            case null:
            case null:
              this.defaultValue = Integer.valueOf(TextFormat.parseUInt32(this.proto.getDefaultValue()));
              break;
            case ENUM:
            case MESSAGE:
            case null:
              this.defaultValue = Integer.valueOf(TextFormat.parseInt32(this.proto.getDefaultValue()));
              break;
          } 
        } catch (NumberFormatException numberFormatException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Could not parse default value: \"");
          stringBuilder.append(this.proto.getDefaultValue());
          stringBuilder.append('"');
          throw new Descriptors.DescriptorValidationException(this, stringBuilder.toString(), numberFormatException);
        } 
      } else if (isRepeated()) {
        this.defaultValue = Collections.emptyList();
      } else {
        switch (getJavaType()) {
          default:
            this.defaultValue = (getJavaType()).defaultDefault;
            break;
          case MESSAGE:
            this.defaultValue = null;
            break;
          case ENUM:
            this.defaultValue = this.enumType.getValues().get(0);
            break;
        } 
      } 
      if (!isExtension())
        this.file.pool.addFieldByNumber(this); 
      if (this.containingType != null && this.containingType.getOptions().getMessageSetWireFormat())
        if (isExtension()) {
          if (!isOptional() || getType() != Type.MESSAGE)
            throw new Descriptors.DescriptorValidationException(this, "Extensions of MessageSets must be optional messages."); 
        } else {
          throw new Descriptors.DescriptorValidationException(this, "MessageSets cannot have fields, only extensions.");
        }  
    }
    
    private static String fieldNameToJsonName(String param1String) {
      int i = param1String.length();
      StringBuilder stringBuilder = new StringBuilder(i);
      char c1 = Character.MIN_VALUE;
      char c2 = c1;
      while (c1 < i) {
        char c = param1String.charAt(c1);
        if (c == '_') {
          c2 = '\001';
        } else if (c2 != Character.MIN_VALUE) {
          char c3 = c;
          if ('a' <= c) {
            c3 = c;
            if (c <= 'z') {
              c2 = (char)(c - 97 + 65);
              c3 = c2;
            } 
          } 
          stringBuilder.append(c3);
          c2 = Character.MIN_VALUE;
        } else {
          stringBuilder.append(c);
        } 
        c1++;
      } 
      return stringBuilder.toString();
    }
    
    private void setProto(DescriptorProtos.FieldDescriptorProto param1FieldDescriptorProto) {
      this.proto = param1FieldDescriptorProto;
    }
    
    public int compareTo(FieldDescriptor param1FieldDescriptor) {
      if (param1FieldDescriptor.containingType != this.containingType)
        throw new IllegalArgumentException("FieldDescriptors can only be compared to other FieldDescriptors for fields of the same message type."); 
      return getNumber() - param1FieldDescriptor.getNumber();
    }
    
    public Descriptors.OneofDescriptor getContainingOneof() {
      return this.containingOneof;
    }
    
    public Descriptors.Descriptor getContainingType() {
      return this.containingType;
    }
    
    public Object getDefaultValue() {
      if (getJavaType() == JavaType.MESSAGE)
        throw new UnsupportedOperationException("FieldDescriptor.getDefaultValue() called on an embedded message field."); 
      return this.defaultValue;
    }
    
    public Descriptors.EnumDescriptor getEnumType() {
      if (getJavaType() != JavaType.ENUM)
        throw new UnsupportedOperationException(String.format("This field is not of enum type. (%s)", new Object[] { this.fullName })); 
      return this.enumType;
    }
    
    public Descriptors.Descriptor getExtensionScope() {
      if (!isExtension())
        throw new UnsupportedOperationException(String.format("This field is not an extension. (%s)", new Object[] { this.fullName })); 
      return this.extensionScope;
    }
    
    public Descriptors.FileDescriptor getFile() {
      return this.file;
    }
    
    public String getFullName() {
      return this.fullName;
    }
    
    public int getIndex() {
      return this.index;
    }
    
    public JavaType getJavaType() {
      return this.type.getJavaType();
    }
    
    public String getJsonName() {
      return this.jsonName;
    }
    
    public WireFormat.JavaType getLiteJavaType() {
      return getLiteType().getJavaType();
    }
    
    public WireFormat.FieldType getLiteType() {
      return table[this.type.ordinal()];
    }
    
    public Descriptors.Descriptor getMessageType() {
      if (getJavaType() != JavaType.MESSAGE)
        throw new UnsupportedOperationException(String.format("This field is not of message type. (%s)", new Object[] { this.fullName })); 
      return this.messageType;
    }
    
    public String getName() {
      return this.proto.getName();
    }
    
    public int getNumber() {
      return this.proto.getNumber();
    }
    
    public DescriptorProtos.FieldOptions getOptions() {
      return this.proto.getOptions();
    }
    
    public Type getType() {
      return this.type;
    }
    
    public boolean hasDefaultValue() {
      return this.proto.hasDefaultValue();
    }
    
    public MessageLite.Builder internalMergeFrom(MessageLite.Builder param1Builder, MessageLite param1MessageLite) {
      return ((Message.Builder)param1Builder).mergeFrom((Message)param1MessageLite);
    }
    
    public boolean isExtension() {
      return this.proto.hasExtendee();
    }
    
    public boolean isMapField() {
      boolean bool;
      if (getType() == Type.MESSAGE && isRepeated() && getMessageType().getOptions().getMapEntry()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isOptional() {
      boolean bool;
      if (this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_OPTIONAL) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isPackable() {
      boolean bool;
      if (isRepeated() && getLiteType().isPackable()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isPacked() {
      boolean bool = isPackable();
      boolean bool1 = false;
      if (!bool)
        return false; 
      if (getFile().getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO2)
        return getOptions().getPacked(); 
      if (!getOptions().hasPacked() || getOptions().getPacked())
        bool1 = true; 
      return bool1;
    }
    
    public boolean isRepeated() {
      boolean bool;
      if (this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REPEATED) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isRequired() {
      boolean bool;
      if (this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REQUIRED) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean needsUtf8Check() {
      return (this.type != Type.STRING) ? false : (getContainingType().getOptions().getMapEntry() ? true : ((getFile().getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO3) ? true : getFile().getOptions().getJavaStringCheckUtf8()));
    }
    
    public DescriptorProtos.FieldDescriptorProto toProto() {
      return this.proto;
    }
    
    public String toString() {
      return getFullName();
    }
    
    public enum JavaType {
      BOOLEAN,
      BYTE_STRING,
      DOUBLE,
      ENUM,
      FLOAT,
      INT((String)Integer.valueOf(0)),
      LONG((String)Long.valueOf(0L)),
      MESSAGE((String)Long.valueOf(0L)),
      STRING((String)Long.valueOf(0L));
      
      private final Object defaultDefault;
      
      static {
        BOOLEAN = new JavaType("BOOLEAN", 4, Boolean.valueOf(false));
        STRING = new JavaType("STRING", 5, "");
        BYTE_STRING = new JavaType("BYTE_STRING", 6, ByteString.EMPTY);
        ENUM = new JavaType("ENUM", 7, null);
        MESSAGE = new JavaType("MESSAGE", 8, null);
        $VALUES = new JavaType[] { INT, LONG, FLOAT, DOUBLE, BOOLEAN, STRING, BYTE_STRING, ENUM, MESSAGE };
      }
      
      JavaType(Object param2Object) {
        this.defaultDefault = param2Object;
      }
    }
    
    public enum Type {
      DOUBLE((String)Descriptors.FieldDescriptor.JavaType.DOUBLE),
      ENUM((String)Descriptors.FieldDescriptor.JavaType.DOUBLE),
      FIXED32((String)Descriptors.FieldDescriptor.JavaType.DOUBLE),
      FIXED64((String)Descriptors.FieldDescriptor.JavaType.DOUBLE),
      BOOL((String)Long.valueOf(0L)),
      BYTES((String)Long.valueOf(0L)),
      FLOAT((String)Descriptors.FieldDescriptor.JavaType.FLOAT),
      GROUP((String)Descriptors.FieldDescriptor.JavaType.FLOAT),
      INT32((String)Descriptors.FieldDescriptor.JavaType.FLOAT),
      INT64((String)Descriptors.FieldDescriptor.JavaType.LONG),
      MESSAGE((String)Descriptors.FieldDescriptor.JavaType.LONG),
      SFIXED32((String)Descriptors.FieldDescriptor.JavaType.LONG),
      SFIXED64((String)Descriptors.FieldDescriptor.JavaType.LONG),
      SINT32((String)Descriptors.FieldDescriptor.JavaType.LONG),
      SINT64((String)Descriptors.FieldDescriptor.JavaType.LONG),
      STRING((String)Descriptors.FieldDescriptor.JavaType.LONG),
      UINT32((String)Descriptors.FieldDescriptor.JavaType.LONG),
      UINT64((String)Descriptors.FieldDescriptor.JavaType.LONG);
      
      private Descriptors.FieldDescriptor.JavaType javaType;
      
      static {
        FIXED32 = new Type("FIXED32", 6, Descriptors.FieldDescriptor.JavaType.INT);
        BOOL = new Type("BOOL", 7, Descriptors.FieldDescriptor.JavaType.BOOLEAN);
        STRING = new Type("STRING", 8, Descriptors.FieldDescriptor.JavaType.STRING);
        GROUP = new Type("GROUP", 9, Descriptors.FieldDescriptor.JavaType.MESSAGE);
        MESSAGE = new Type("MESSAGE", 10, Descriptors.FieldDescriptor.JavaType.MESSAGE);
        BYTES = new Type("BYTES", 11, Descriptors.FieldDescriptor.JavaType.BYTE_STRING);
        UINT32 = new Type("UINT32", 12, Descriptors.FieldDescriptor.JavaType.INT);
        ENUM = new Type("ENUM", 13, Descriptors.FieldDescriptor.JavaType.ENUM);
        SFIXED32 = new Type("SFIXED32", 14, Descriptors.FieldDescriptor.JavaType.INT);
        SFIXED64 = new Type("SFIXED64", 15, Descriptors.FieldDescriptor.JavaType.LONG);
        SINT32 = new Type("SINT32", 16, Descriptors.FieldDescriptor.JavaType.INT);
        SINT64 = new Type("SINT64", 17, Descriptors.FieldDescriptor.JavaType.LONG);
        $VALUES = new Type[] { 
            DOUBLE, FLOAT, INT64, UINT64, INT32, FIXED64, FIXED32, BOOL, STRING, GROUP, 
            MESSAGE, BYTES, UINT32, ENUM, SFIXED32, SFIXED64, SINT32, SINT64 };
      }
      
      Type(Descriptors.FieldDescriptor.JavaType param2JavaType) {
        this.javaType = param2JavaType;
      }
      
      public Descriptors.FieldDescriptor.JavaType getJavaType() {
        return this.javaType;
      }
      
      public DescriptorProtos.FieldDescriptorProto.Type toProto() {
        return DescriptorProtos.FieldDescriptorProto.Type.forNumber(ordinal() + 1);
      }
    }
  }
  
  public enum JavaType {
    INT((String)Integer.valueOf(0)),
    LONG((String)Long.valueOf(0L)),
    MESSAGE((String)Long.valueOf(0L)),
    STRING((String)Long.valueOf(0L)),
    BOOLEAN((String)JavaType.LONG),
    BYTE_STRING((String)JavaType.LONG),
    DOUBLE((String)JavaType.LONG),
    ENUM((String)JavaType.LONG),
    FLOAT((String)JavaType.LONG);
    
    private final Object defaultDefault;
    
    static {
      DOUBLE = new JavaType("DOUBLE", 3, Double.valueOf(0.0D));
      BOOLEAN = new JavaType("BOOLEAN", 4, Boolean.valueOf(false));
      STRING = new JavaType("STRING", 5, "");
      BYTE_STRING = new JavaType("BYTE_STRING", 6, ByteString.EMPTY);
      ENUM = new JavaType("ENUM", 7, null);
      MESSAGE = new JavaType("MESSAGE", 8, null);
      $VALUES = new JavaType[] { INT, LONG, FLOAT, DOUBLE, BOOLEAN, STRING, BYTE_STRING, ENUM, MESSAGE };
    }
    
    JavaType(Object param1Object) {
      this.defaultDefault = param1Object;
    }
  }
  
  public enum Type {
    DOUBLE((String)Descriptors.FieldDescriptor.JavaType.DOUBLE),
    ENUM((String)Descriptors.FieldDescriptor.JavaType.DOUBLE),
    FIXED32((String)Descriptors.FieldDescriptor.JavaType.DOUBLE),
    FIXED64((String)Descriptors.FieldDescriptor.JavaType.DOUBLE),
    BOOL((String)Long.valueOf(0L)),
    BYTES((String)Long.valueOf(0L)),
    FLOAT((String)Descriptors.FieldDescriptor.JavaType.FLOAT),
    GROUP((String)Descriptors.FieldDescriptor.JavaType.FLOAT),
    INT32((String)Descriptors.FieldDescriptor.JavaType.FLOAT),
    INT64((String)Descriptors.FieldDescriptor.JavaType.LONG),
    MESSAGE((String)Descriptors.FieldDescriptor.JavaType.LONG),
    SFIXED32((String)Descriptors.FieldDescriptor.JavaType.LONG),
    SFIXED64((String)Descriptors.FieldDescriptor.JavaType.LONG),
    SINT32((String)Descriptors.FieldDescriptor.JavaType.LONG),
    SINT64((String)Descriptors.FieldDescriptor.JavaType.LONG),
    STRING((String)Descriptors.FieldDescriptor.JavaType.LONG),
    UINT32((String)Descriptors.FieldDescriptor.JavaType.LONG),
    UINT64((String)Descriptors.FieldDescriptor.JavaType.LONG);
    
    private Descriptors.FieldDescriptor.JavaType javaType;
    
    static {
      FIXED64 = new Type("FIXED64", 5, Descriptors.FieldDescriptor.JavaType.LONG);
      FIXED32 = new Type("FIXED32", 6, Descriptors.FieldDescriptor.JavaType.INT);
      BOOL = new Type("BOOL", 7, Descriptors.FieldDescriptor.JavaType.BOOLEAN);
      STRING = new Type("STRING", 8, Descriptors.FieldDescriptor.JavaType.STRING);
      GROUP = new Type("GROUP", 9, Descriptors.FieldDescriptor.JavaType.MESSAGE);
      MESSAGE = new Type("MESSAGE", 10, Descriptors.FieldDescriptor.JavaType.MESSAGE);
      BYTES = new Type("BYTES", 11, Descriptors.FieldDescriptor.JavaType.BYTE_STRING);
      UINT32 = new Type("UINT32", 12, Descriptors.FieldDescriptor.JavaType.INT);
      ENUM = new Type("ENUM", 13, Descriptors.FieldDescriptor.JavaType.ENUM);
      SFIXED32 = new Type("SFIXED32", 14, Descriptors.FieldDescriptor.JavaType.INT);
      SFIXED64 = new Type("SFIXED64", 15, Descriptors.FieldDescriptor.JavaType.LONG);
      SINT32 = new Type("SINT32", 16, Descriptors.FieldDescriptor.JavaType.INT);
      SINT64 = new Type("SINT64", 17, Descriptors.FieldDescriptor.JavaType.LONG);
      $VALUES = new Type[] { 
          DOUBLE, FLOAT, INT64, UINT64, INT32, FIXED64, FIXED32, BOOL, STRING, GROUP, 
          MESSAGE, BYTES, UINT32, ENUM, SFIXED32, SFIXED64, SINT32, SINT64 };
    }
    
    Type(Descriptors.FieldDescriptor.JavaType param1JavaType) {
      this.javaType = param1JavaType;
    }
    
    public Descriptors.FieldDescriptor.JavaType getJavaType() {
      return this.javaType;
    }
    
    public DescriptorProtos.FieldDescriptorProto.Type toProto() {
      return DescriptorProtos.FieldDescriptorProto.Type.forNumber(ordinal() + 1);
    }
  }
  
  public static final class FileDescriptor extends GenericDescriptor {
    private final FileDescriptor[] dependencies;
    
    private final Descriptors.EnumDescriptor[] enumTypes;
    
    private final Descriptors.FieldDescriptor[] extensions;
    
    private final Descriptors.Descriptor[] messageTypes;
    
    private final Descriptors.DescriptorPool pool;
    
    private DescriptorProtos.FileDescriptorProto proto;
    
    private final FileDescriptor[] publicDependencies;
    
    private final Descriptors.ServiceDescriptor[] services;
    
    private FileDescriptor(DescriptorProtos.FileDescriptorProto param1FileDescriptorProto, FileDescriptor[] param1ArrayOfFileDescriptor, Descriptors.DescriptorPool param1DescriptorPool, boolean param1Boolean) throws Descriptors.DescriptorValidationException {
      StringBuilder stringBuilder;
      this.pool = param1DescriptorPool;
      this.proto = param1FileDescriptorProto;
      this.dependencies = (FileDescriptor[])param1ArrayOfFileDescriptor.clone();
      HashMap<Object, Object> hashMap = new HashMap<>();
      int i = param1ArrayOfFileDescriptor.length;
      boolean bool = false;
      byte b;
      for (b = 0; b < i; b++) {
        FileDescriptor fileDescriptor = param1ArrayOfFileDescriptor[b];
        hashMap.put(fileDescriptor.getName(), fileDescriptor);
      } 
      ArrayList<FileDescriptor> arrayList = new ArrayList();
      for (b = 0; b < param1FileDescriptorProto.getPublicDependencyCount(); b++) {
        i = param1FileDescriptorProto.getPublicDependency(b);
        if (i < 0 || i >= param1FileDescriptorProto.getDependencyCount())
          throw new Descriptors.DescriptorValidationException(this, "Invalid public dependency index."); 
        String str = param1FileDescriptorProto.getDependency(i);
        FileDescriptor fileDescriptor = (FileDescriptor)hashMap.get(str);
        if (fileDescriptor == null) {
          if (!param1Boolean) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Invalid public dependency: ");
            stringBuilder.append(str);
            throw new Descriptors.DescriptorValidationException(this, stringBuilder.toString());
          } 
        } else {
          arrayList.add(fileDescriptor);
        } 
      } 
      this.publicDependencies = new FileDescriptor[arrayList.size()];
      arrayList.toArray(this.publicDependencies);
      param1DescriptorPool.addPackage(getPackage(), this);
      this.messageTypes = new Descriptors.Descriptor[stringBuilder.getMessageTypeCount()];
      for (b = 0; b < stringBuilder.getMessageTypeCount(); b++)
        this.messageTypes[b] = new Descriptors.Descriptor(stringBuilder.getMessageType(b), this, null, b); 
      this.enumTypes = new Descriptors.EnumDescriptor[stringBuilder.getEnumTypeCount()];
      for (b = 0; b < stringBuilder.getEnumTypeCount(); b++)
        this.enumTypes[b] = new Descriptors.EnumDescriptor(stringBuilder.getEnumType(b), this, null, b); 
      this.services = new Descriptors.ServiceDescriptor[stringBuilder.getServiceCount()];
      for (b = 0; b < stringBuilder.getServiceCount(); b++)
        this.services[b] = new Descriptors.ServiceDescriptor(stringBuilder.getService(b), this, b); 
      this.extensions = new Descriptors.FieldDescriptor[stringBuilder.getExtensionCount()];
      for (b = bool; b < stringBuilder.getExtensionCount(); b++)
        this.extensions[b] = new Descriptors.FieldDescriptor(stringBuilder.getExtension(b), this, null, b, true); 
    }
    
    FileDescriptor(String param1String, Descriptors.Descriptor param1Descriptor) throws Descriptors.DescriptorValidationException {
      this.pool = new Descriptors.DescriptorPool(new FileDescriptor[0], true);
      DescriptorProtos.FileDescriptorProto.Builder builder = DescriptorProtos.FileDescriptorProto.newBuilder();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(param1Descriptor.getFullName());
      stringBuilder.append(".placeholder.proto");
      this.proto = builder.setName(stringBuilder.toString()).setPackage(param1String).addMessageType(param1Descriptor.toProto()).build();
      this.dependencies = new FileDescriptor[0];
      this.publicDependencies = new FileDescriptor[0];
      this.messageTypes = new Descriptors.Descriptor[] { param1Descriptor };
      this.enumTypes = new Descriptors.EnumDescriptor[0];
      this.services = new Descriptors.ServiceDescriptor[0];
      this.extensions = new Descriptors.FieldDescriptor[0];
      this.pool.addPackage(param1String, this);
      this.pool.addSymbol(param1Descriptor);
    }
    
    public static FileDescriptor buildFrom(DescriptorProtos.FileDescriptorProto param1FileDescriptorProto, FileDescriptor[] param1ArrayOfFileDescriptor) throws Descriptors.DescriptorValidationException {
      return buildFrom(param1FileDescriptorProto, param1ArrayOfFileDescriptor, false);
    }
    
    public static FileDescriptor buildFrom(DescriptorProtos.FileDescriptorProto param1FileDescriptorProto, FileDescriptor[] param1ArrayOfFileDescriptor, boolean param1Boolean) throws Descriptors.DescriptorValidationException {
      FileDescriptor fileDescriptor = new FileDescriptor(param1FileDescriptorProto, param1ArrayOfFileDescriptor, new Descriptors.DescriptorPool(param1ArrayOfFileDescriptor, param1Boolean), param1Boolean);
      fileDescriptor.crossLink();
      return fileDescriptor;
    }
    
    private void crossLink() throws Descriptors.DescriptorValidationException {
      Descriptors.Descriptor[] arrayOfDescriptor = this.messageTypes;
      int i = arrayOfDescriptor.length;
      boolean bool = false;
      byte b;
      for (b = 0; b < i; b++)
        arrayOfDescriptor[b].crossLink(); 
      Descriptors.ServiceDescriptor[] arrayOfServiceDescriptor = this.services;
      i = arrayOfServiceDescriptor.length;
      for (b = 0; b < i; b++)
        arrayOfServiceDescriptor[b].crossLink(); 
      Descriptors.FieldDescriptor[] arrayOfFieldDescriptor = this.extensions;
      i = arrayOfFieldDescriptor.length;
      for (b = bool; b < i; b++)
        arrayOfFieldDescriptor[b].crossLink(); 
    }
    
    private static FileDescriptor[] findDescriptors(Class<?> param1Class, String[] param1ArrayOfString1, String[] param1ArrayOfString2) {
      ArrayList<FileDescriptor> arrayList = new ArrayList();
      for (byte b = 0; b < param1ArrayOfString1.length; b++) {
        try {
          arrayList.add((FileDescriptor)param1Class.getClassLoader().loadClass(param1ArrayOfString1[b]).getField("descriptor").get(null));
        } catch (Exception exception) {
          Logger logger = Descriptors.logger;
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Descriptors for \"");
          stringBuilder.append(param1ArrayOfString2[b]);
          stringBuilder.append("\" can not be found.");
          logger.warning(stringBuilder.toString());
        } 
      } 
      return arrayList.<FileDescriptor>toArray(new FileDescriptor[0]);
    }
    
    public static FileDescriptor internalBuildGeneratedFileFrom(String[] param1ArrayOfString1, Class<?> param1Class, String[] param1ArrayOfString2, String[] param1ArrayOfString3) {
      return internalBuildGeneratedFileFrom(param1ArrayOfString1, findDescriptors(param1Class, param1ArrayOfString2, param1ArrayOfString3));
    }
    
    public static FileDescriptor internalBuildGeneratedFileFrom(String[] param1ArrayOfString, FileDescriptor[] param1ArrayOfFileDescriptor) {
      byte[] arrayOfByte = latin1Cat(param1ArrayOfString);
      try {
        DescriptorProtos.FileDescriptorProto fileDescriptorProto = DescriptorProtos.FileDescriptorProto.parseFrom(arrayOfByte);
        try {
          return buildFrom(fileDescriptorProto, param1ArrayOfFileDescriptor, true);
        } catch (DescriptorValidationException descriptorValidationException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Invalid embedded descriptor for \"");
          stringBuilder.append(fileDescriptorProto.getName());
          stringBuilder.append("\".");
          throw new IllegalArgumentException(stringBuilder.toString(), descriptorValidationException);
        } 
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", invalidProtocolBufferException);
      } 
    }
    
    @Deprecated
    public static void internalBuildGeneratedFileFrom(String[] param1ArrayOfString1, Class<?> param1Class, String[] param1ArrayOfString2, String[] param1ArrayOfString3, InternalDescriptorAssigner param1InternalDescriptorAssigner) {
      internalBuildGeneratedFileFrom(param1ArrayOfString1, findDescriptors(param1Class, param1ArrayOfString2, param1ArrayOfString3), param1InternalDescriptorAssigner);
    }
    
    @Deprecated
    public static void internalBuildGeneratedFileFrom(String[] param1ArrayOfString, FileDescriptor[] param1ArrayOfFileDescriptor, InternalDescriptorAssigner param1InternalDescriptorAssigner) {
      byte[] arrayOfByte = latin1Cat(param1ArrayOfString);
      try {
        DescriptorProtos.FileDescriptorProto fileDescriptorProto = DescriptorProtos.FileDescriptorProto.parseFrom(arrayOfByte);
        try {
          FileDescriptor fileDescriptor = buildFrom(fileDescriptorProto, param1ArrayOfFileDescriptor, true);
          ExtensionRegistry extensionRegistry = param1InternalDescriptorAssigner.assignDescriptors(fileDescriptor);
          if (extensionRegistry != null)
            try {
              DescriptorProtos.FileDescriptorProto fileDescriptorProto1 = DescriptorProtos.FileDescriptorProto.parseFrom(arrayOfByte, extensionRegistry);
              fileDescriptor.setProto(fileDescriptorProto1);
            } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
              throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", invalidProtocolBufferException);
            }  
          return;
        } catch (DescriptorValidationException descriptorValidationException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Invalid embedded descriptor for \"");
          stringBuilder.append(invalidProtocolBufferException.getName());
          stringBuilder.append("\".");
          throw new IllegalArgumentException(stringBuilder.toString(), descriptorValidationException);
        } 
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", invalidProtocolBufferException);
      } 
    }
    
    public static void internalUpdateFileDescriptor(FileDescriptor param1FileDescriptor, ExtensionRegistry param1ExtensionRegistry) {
      ByteString byteString = param1FileDescriptor.proto.toByteString();
      try {
        DescriptorProtos.FileDescriptorProto fileDescriptorProto = DescriptorProtos.FileDescriptorProto.parseFrom(byteString, param1ExtensionRegistry);
        param1FileDescriptor.setProto(fileDescriptorProto);
        return;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", invalidProtocolBufferException);
      } 
    }
    
    private static byte[] latin1Cat(String[] param1ArrayOfString) {
      int i = param1ArrayOfString.length;
      byte b = 0;
      if (i == 1)
        return param1ArrayOfString[0].getBytes(Internal.ISO_8859_1); 
      StringBuilder stringBuilder = new StringBuilder();
      i = param1ArrayOfString.length;
      while (b < i) {
        stringBuilder.append(param1ArrayOfString[b]);
        b++;
      } 
      return stringBuilder.toString().getBytes(Internal.ISO_8859_1);
    }
    
    private void setProto(DescriptorProtos.FileDescriptorProto param1FileDescriptorProto) {
      byte b3;
      this.proto = param1FileDescriptorProto;
      byte b1 = 0;
      byte b2;
      for (b2 = 0; b2 < this.messageTypes.length; b2++)
        this.messageTypes[b2].setProto(param1FileDescriptorProto.getMessageType(b2)); 
      for (b2 = 0; b2 < this.enumTypes.length; b2++)
        this.enumTypes[b2].setProto(param1FileDescriptorProto.getEnumType(b2)); 
      b2 = 0;
      while (true) {
        b3 = b1;
        if (b2 < this.services.length) {
          this.services[b2].setProto(param1FileDescriptorProto.getService(b2));
          b2++;
          continue;
        } 
        break;
      } 
      while (b3 < this.extensions.length) {
        this.extensions[b3].setProto(param1FileDescriptorProto.getExtension(b3));
        b3++;
      } 
    }
    
    public Descriptors.EnumDescriptor findEnumTypeByName(String param1String) {
      if (param1String.indexOf('.') != -1)
        return null; 
      String str1 = getPackage();
      String str2 = param1String;
      if (!str1.isEmpty()) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str1);
        stringBuilder.append('.');
        stringBuilder.append(param1String);
        str2 = stringBuilder.toString();
      } 
      Descriptors.GenericDescriptor genericDescriptor = this.pool.findSymbol(str2);
      return (genericDescriptor != null && genericDescriptor instanceof Descriptors.EnumDescriptor && genericDescriptor.getFile() == this) ? (Descriptors.EnumDescriptor)genericDescriptor : null;
    }
    
    public Descriptors.FieldDescriptor findExtensionByName(String param1String) {
      if (param1String.indexOf('.') != -1)
        return null; 
      String str1 = getPackage();
      String str2 = param1String;
      if (!str1.isEmpty()) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str1);
        stringBuilder.append('.');
        stringBuilder.append(param1String);
        str2 = stringBuilder.toString();
      } 
      Descriptors.GenericDescriptor genericDescriptor = this.pool.findSymbol(str2);
      return (genericDescriptor != null && genericDescriptor instanceof Descriptors.FieldDescriptor && genericDescriptor.getFile() == this) ? (Descriptors.FieldDescriptor)genericDescriptor : null;
    }
    
    public Descriptors.Descriptor findMessageTypeByName(String param1String) {
      if (param1String.indexOf('.') != -1)
        return null; 
      String str1 = getPackage();
      String str2 = param1String;
      if (!str1.isEmpty()) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str1);
        stringBuilder.append('.');
        stringBuilder.append(param1String);
        str2 = stringBuilder.toString();
      } 
      Descriptors.GenericDescriptor genericDescriptor = this.pool.findSymbol(str2);
      return (genericDescriptor != null && genericDescriptor instanceof Descriptors.Descriptor && genericDescriptor.getFile() == this) ? (Descriptors.Descriptor)genericDescriptor : null;
    }
    
    public Descriptors.ServiceDescriptor findServiceByName(String param1String) {
      if (param1String.indexOf('.') != -1)
        return null; 
      String str1 = getPackage();
      String str2 = param1String;
      if (!str1.isEmpty()) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str1);
        stringBuilder.append('.');
        stringBuilder.append(param1String);
        str2 = stringBuilder.toString();
      } 
      Descriptors.GenericDescriptor genericDescriptor = this.pool.findSymbol(str2);
      return (genericDescriptor != null && genericDescriptor instanceof Descriptors.ServiceDescriptor && genericDescriptor.getFile() == this) ? (Descriptors.ServiceDescriptor)genericDescriptor : null;
    }
    
    public List<FileDescriptor> getDependencies() {
      return Collections.unmodifiableList(Arrays.asList(this.dependencies));
    }
    
    public List<Descriptors.EnumDescriptor> getEnumTypes() {
      return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
    }
    
    public List<Descriptors.FieldDescriptor> getExtensions() {
      return Collections.unmodifiableList(Arrays.asList(this.extensions));
    }
    
    public FileDescriptor getFile() {
      return this;
    }
    
    public String getFullName() {
      return this.proto.getName();
    }
    
    public List<Descriptors.Descriptor> getMessageTypes() {
      return Collections.unmodifiableList(Arrays.asList(this.messageTypes));
    }
    
    public String getName() {
      return this.proto.getName();
    }
    
    public DescriptorProtos.FileOptions getOptions() {
      return this.proto.getOptions();
    }
    
    public String getPackage() {
      return this.proto.getPackage();
    }
    
    public List<FileDescriptor> getPublicDependencies() {
      return Collections.unmodifiableList(Arrays.asList(this.publicDependencies));
    }
    
    public List<Descriptors.ServiceDescriptor> getServices() {
      return Collections.unmodifiableList(Arrays.asList(this.services));
    }
    
    public Syntax getSyntax() {
      return Syntax.PROTO3.name.equals(this.proto.getSyntax()) ? Syntax.PROTO3 : Syntax.PROTO2;
    }
    
    boolean supportsUnknownEnumValue() {
      boolean bool;
      if (getSyntax() == Syntax.PROTO3) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public DescriptorProtos.FileDescriptorProto toProto() {
      return this.proto;
    }
    
    @Deprecated
    public static interface InternalDescriptorAssigner {
      ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor param2FileDescriptor);
    }
    
    public enum Syntax {
      UNKNOWN("unknown"),
      PROTO2((String)Descriptors.FieldDescriptor.JavaType.LONG),
      PROTO3((String)Descriptors.FieldDescriptor.JavaType.LONG);
      
      private final String name;
      
      static {
      
      }
      
      Syntax(String param2String1) {
        this.name = param2String1;
      }
    }
  }
  
  @Deprecated
  public static interface InternalDescriptorAssigner {
    ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor param1FileDescriptor);
  }
  
  public enum Syntax {
    PROTO2("unknown"),
    PROTO3("unknown"),
    UNKNOWN("unknown");
    
    private final String name;
    
    static {
      $VALUES = new Syntax[] { UNKNOWN, PROTO2, PROTO3 };
    }
    
    Syntax(String param1String1) {
      this.name = param1String1;
    }
  }
  
  public static abstract class GenericDescriptor {
    private GenericDescriptor() {}
    
    public abstract Descriptors.FileDescriptor getFile();
    
    public abstract String getFullName();
    
    public abstract String getName();
    
    public abstract Message toProto();
  }
  
  public static final class MethodDescriptor extends GenericDescriptor {
    private final Descriptors.FileDescriptor file;
    
    private final String fullName;
    
    private final int index;
    
    private Descriptors.Descriptor inputType;
    
    private Descriptors.Descriptor outputType;
    
    private DescriptorProtos.MethodDescriptorProto proto;
    
    private final Descriptors.ServiceDescriptor service;
    
    private MethodDescriptor(DescriptorProtos.MethodDescriptorProto param1MethodDescriptorProto, Descriptors.FileDescriptor param1FileDescriptor, Descriptors.ServiceDescriptor param1ServiceDescriptor, int param1Int) throws Descriptors.DescriptorValidationException {
      this.index = param1Int;
      this.proto = param1MethodDescriptorProto;
      this.file = param1FileDescriptor;
      this.service = param1ServiceDescriptor;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(param1ServiceDescriptor.getFullName());
      stringBuilder.append('.');
      stringBuilder.append(param1MethodDescriptorProto.getName());
      this.fullName = stringBuilder.toString();
      param1FileDescriptor.pool.addSymbol(this);
    }
    
    private void crossLink() throws Descriptors.DescriptorValidationException {
      StringBuilder stringBuilder2;
      StringBuilder stringBuilder1;
      Descriptors.GenericDescriptor genericDescriptor2 = this.file.pool.lookupSymbol(this.proto.getInputType(), this, Descriptors.DescriptorPool.SearchFilter.TYPES_ONLY);
      if (!(genericDescriptor2 instanceof Descriptors.Descriptor)) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append('"');
        stringBuilder2.append(this.proto.getInputType());
        stringBuilder2.append("\" is not a message type.");
        throw new Descriptors.DescriptorValidationException(this, stringBuilder2.toString());
      } 
      this.inputType = (Descriptors.Descriptor)stringBuilder2;
      Descriptors.GenericDescriptor genericDescriptor1 = this.file.pool.lookupSymbol(this.proto.getOutputType(), this, Descriptors.DescriptorPool.SearchFilter.TYPES_ONLY);
      if (!(genericDescriptor1 instanceof Descriptors.Descriptor)) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append('"');
        stringBuilder1.append(this.proto.getOutputType());
        stringBuilder1.append("\" is not a message type.");
        throw new Descriptors.DescriptorValidationException(this, stringBuilder1.toString());
      } 
      this.outputType = (Descriptors.Descriptor)stringBuilder1;
    }
    
    private void setProto(DescriptorProtos.MethodDescriptorProto param1MethodDescriptorProto) {
      this.proto = param1MethodDescriptorProto;
    }
    
    public Descriptors.FileDescriptor getFile() {
      return this.file;
    }
    
    public String getFullName() {
      return this.fullName;
    }
    
    public int getIndex() {
      return this.index;
    }
    
    public Descriptors.Descriptor getInputType() {
      return this.inputType;
    }
    
    public String getName() {
      return this.proto.getName();
    }
    
    public DescriptorProtos.MethodOptions getOptions() {
      return this.proto.getOptions();
    }
    
    public Descriptors.Descriptor getOutputType() {
      return this.outputType;
    }
    
    public Descriptors.ServiceDescriptor getService() {
      return this.service;
    }
    
    public boolean isClientStreaming() {
      return this.proto.getClientStreaming();
    }
    
    public boolean isServerStreaming() {
      return this.proto.getServerStreaming();
    }
    
    public DescriptorProtos.MethodDescriptorProto toProto() {
      return this.proto;
    }
  }
  
  public static final class OneofDescriptor extends GenericDescriptor {
    private Descriptors.Descriptor containingType;
    
    private int fieldCount;
    
    private Descriptors.FieldDescriptor[] fields;
    
    private final Descriptors.FileDescriptor file;
    
    private final String fullName;
    
    private final int index;
    
    private DescriptorProtos.OneofDescriptorProto proto;
    
    private OneofDescriptor(DescriptorProtos.OneofDescriptorProto param1OneofDescriptorProto, Descriptors.FileDescriptor param1FileDescriptor, Descriptors.Descriptor param1Descriptor, int param1Int) throws Descriptors.DescriptorValidationException {
      this.proto = param1OneofDescriptorProto;
      this.fullName = Descriptors.computeFullName(param1FileDescriptor, param1Descriptor, param1OneofDescriptorProto.getName());
      this.file = param1FileDescriptor;
      this.index = param1Int;
      this.containingType = param1Descriptor;
      this.fieldCount = 0;
    }
    
    private void setProto(DescriptorProtos.OneofDescriptorProto param1OneofDescriptorProto) {
      this.proto = param1OneofDescriptorProto;
    }
    
    public Descriptors.Descriptor getContainingType() {
      return this.containingType;
    }
    
    public Descriptors.FieldDescriptor getField(int param1Int) {
      return this.fields[param1Int];
    }
    
    public int getFieldCount() {
      return this.fieldCount;
    }
    
    public List<Descriptors.FieldDescriptor> getFields() {
      return Collections.unmodifiableList(Arrays.asList(this.fields));
    }
    
    public Descriptors.FileDescriptor getFile() {
      return this.file;
    }
    
    public String getFullName() {
      return this.fullName;
    }
    
    public int getIndex() {
      return this.index;
    }
    
    public String getName() {
      return this.proto.getName();
    }
    
    public DescriptorProtos.OneofOptions getOptions() {
      return this.proto.getOptions();
    }
    
    public DescriptorProtos.OneofDescriptorProto toProto() {
      return this.proto;
    }
  }
  
  public static final class ServiceDescriptor extends GenericDescriptor {
    private final Descriptors.FileDescriptor file;
    
    private final String fullName;
    
    private final int index;
    
    private Descriptors.MethodDescriptor[] methods;
    
    private DescriptorProtos.ServiceDescriptorProto proto;
    
    private ServiceDescriptor(DescriptorProtos.ServiceDescriptorProto param1ServiceDescriptorProto, Descriptors.FileDescriptor param1FileDescriptor, int param1Int) throws Descriptors.DescriptorValidationException {
      this.index = param1Int;
      this.proto = param1ServiceDescriptorProto;
      this.fullName = Descriptors.computeFullName(param1FileDescriptor, null, param1ServiceDescriptorProto.getName());
      this.file = param1FileDescriptor;
      this.methods = new Descriptors.MethodDescriptor[param1ServiceDescriptorProto.getMethodCount()];
      for (param1Int = 0; param1Int < param1ServiceDescriptorProto.getMethodCount(); param1Int++)
        this.methods[param1Int] = new Descriptors.MethodDescriptor(param1ServiceDescriptorProto.getMethod(param1Int), param1FileDescriptor, this, param1Int); 
      param1FileDescriptor.pool.addSymbol(this);
    }
    
    private void crossLink() throws Descriptors.DescriptorValidationException {
      Descriptors.MethodDescriptor[] arrayOfMethodDescriptor = this.methods;
      int i = arrayOfMethodDescriptor.length;
      for (byte b = 0; b < i; b++)
        arrayOfMethodDescriptor[b].crossLink(); 
    }
    
    private void setProto(DescriptorProtos.ServiceDescriptorProto param1ServiceDescriptorProto) {
      this.proto = param1ServiceDescriptorProto;
      for (byte b = 0; b < this.methods.length; b++)
        this.methods[b].setProto(param1ServiceDescriptorProto.getMethod(b)); 
    }
    
    public Descriptors.MethodDescriptor findMethodByName(String param1String) {
      Descriptors.DescriptorPool descriptorPool = this.file.pool;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this.fullName);
      stringBuilder.append('.');
      stringBuilder.append(param1String);
      Descriptors.GenericDescriptor genericDescriptor = descriptorPool.findSymbol(stringBuilder.toString());
      return (genericDescriptor != null && genericDescriptor instanceof Descriptors.MethodDescriptor) ? (Descriptors.MethodDescriptor)genericDescriptor : null;
    }
    
    public Descriptors.FileDescriptor getFile() {
      return this.file;
    }
    
    public String getFullName() {
      return this.fullName;
    }
    
    public int getIndex() {
      return this.index;
    }
    
    public List<Descriptors.MethodDescriptor> getMethods() {
      return Collections.unmodifiableList(Arrays.asList(this.methods));
    }
    
    public String getName() {
      return this.proto.getName();
    }
    
    public DescriptorProtos.ServiceOptions getOptions() {
      return this.proto.getOptions();
    }
    
    public DescriptorProtos.ServiceDescriptorProto toProto() {
      return this.proto;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\Descriptors.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */