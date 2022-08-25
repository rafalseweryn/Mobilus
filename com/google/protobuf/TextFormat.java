package com.google.protobuf;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TextFormat {
  private static final Parser PARSER;
  
  private static final Logger logger = Logger.getLogger(TextFormat.class.getName());
  
  static {
    PARSER = Parser.newBuilder().build();
  }
  
  private static int digitValue(byte paramByte) {
    return (48 <= paramByte && paramByte <= 57) ? (paramByte - 48) : ((97 <= paramByte && paramByte <= 122) ? (paramByte - 97 + 10) : (paramByte - 65 + 10));
  }
  
  public static String escapeBytes(ByteString paramByteString) {
    return TextFormatEscaper.escapeBytes(paramByteString);
  }
  
  public static String escapeBytes(byte[] paramArrayOfbyte) {
    return TextFormatEscaper.escapeBytes(paramArrayOfbyte);
  }
  
  public static String escapeDoubleQuotesAndBackslashes(String paramString) {
    return TextFormatEscaper.escapeDoubleQuotesAndBackslashes(paramString);
  }
  
  static String escapeText(String paramString) {
    return escapeBytes(ByteString.copyFromUtf8(paramString));
  }
  
  public static Parser getParser() {
    return PARSER;
  }
  
  private static boolean isHex(byte paramByte) {
    boolean bool;
    if ((48 <= paramByte && paramByte <= 57) || (97 <= paramByte && paramByte <= 102) || (65 <= paramByte && paramByte <= 70)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private static boolean isOctal(byte paramByte) {
    boolean bool;
    if (48 <= paramByte && paramByte <= 55) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static void merge(CharSequence paramCharSequence, ExtensionRegistry paramExtensionRegistry, Message.Builder paramBuilder) throws ParseException {
    PARSER.merge(paramCharSequence, paramExtensionRegistry, paramBuilder);
  }
  
  public static void merge(CharSequence paramCharSequence, Message.Builder paramBuilder) throws ParseException {
    PARSER.merge(paramCharSequence, paramBuilder);
  }
  
  public static void merge(Readable paramReadable, ExtensionRegistry paramExtensionRegistry, Message.Builder paramBuilder) throws IOException {
    PARSER.merge(paramReadable, paramExtensionRegistry, paramBuilder);
  }
  
  public static void merge(Readable paramReadable, Message.Builder paramBuilder) throws IOException {
    PARSER.merge(paramReadable, paramBuilder);
  }
  
  private static TextGenerator multiLineOutput(Appendable paramAppendable) {
    return new TextGenerator(paramAppendable, false);
  }
  
  public static <T extends Message> T parse(CharSequence paramCharSequence, ExtensionRegistry paramExtensionRegistry, Class<T> paramClass) throws ParseException {
    Message.Builder builder = ((Message)Internal.<Message>getDefaultInstance(paramClass)).newBuilderForType();
    merge(paramCharSequence, paramExtensionRegistry, builder);
    return (T)builder.build();
  }
  
  public static <T extends Message> T parse(CharSequence paramCharSequence, Class<T> paramClass) throws ParseException {
    Message.Builder builder = ((Message)Internal.<Message>getDefaultInstance(paramClass)).newBuilderForType();
    merge(paramCharSequence, builder);
    return (T)builder.build();
  }
  
  static int parseInt32(String paramString) throws NumberFormatException {
    return (int)parseInteger(paramString, true, false);
  }
  
  static long parseInt64(String paramString) throws NumberFormatException {
    return parseInteger(paramString, true, true);
  }
  
  private static long parseInteger(String paramString, boolean paramBoolean1, boolean paramBoolean2) throws NumberFormatException {
    StringBuilder stringBuilder;
    byte b3;
    long l;
    byte b1 = 0;
    boolean bool = paramString.startsWith("-", 0);
    boolean bool1 = true;
    if (bool) {
      if (!paramBoolean1) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Number must be positive: ");
        stringBuilder.append(paramString);
        throw new NumberFormatException(stringBuilder.toString());
      } 
      b1 = 1;
    } else {
      bool1 = false;
    } 
    byte b2 = 10;
    if (paramString.startsWith("0x", b1)) {
      b3 = b1 + 2;
      b2 = 16;
    } else {
      b3 = b1;
      if (paramString.startsWith("0", b1)) {
        b2 = 8;
        b3 = b1;
      } 
    } 
    String str = paramString.substring(b3);
    if (str.length() < 16) {
      l = Long.parseLong(str, b2);
      long l1 = l;
      if (bool1)
        l1 = -l; 
      l = l1;
      if (!paramBoolean2)
        if (paramBoolean1) {
          if (l1 <= 2147483647L) {
            l = l1;
            if (l1 < -2147483648L) {
              stringBuilder = new StringBuilder();
              stringBuilder.append("Number out of range for 32-bit signed integer: ");
              stringBuilder.append(paramString);
              throw new NumberFormatException(stringBuilder.toString());
            } 
          } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Number out of range for 32-bit signed integer: ");
            stringBuilder.append(paramString);
            throw new NumberFormatException(stringBuilder.toString());
          } 
        } else if (l1 < 4294967296L) {
          l = l1;
          if (l1 < 0L) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Number out of range for 32-bit unsigned integer: ");
            stringBuilder.append(paramString);
            throw new NumberFormatException(stringBuilder.toString());
          } 
        } else {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Number out of range for 32-bit unsigned integer: ");
          stringBuilder.append(paramString);
          throw new NumberFormatException(stringBuilder.toString());
        }  
    } else {
      StringBuilder stringBuilder1;
      BigInteger bigInteger2 = new BigInteger((String)stringBuilder, b2);
      BigInteger bigInteger1 = bigInteger2;
      if (bool1)
        bigInteger1 = bigInteger2.negate(); 
      if (!paramBoolean2) {
        if (paramBoolean1) {
          if (bigInteger1.bitLength() > 31) {
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Number out of range for 32-bit signed integer: ");
            stringBuilder1.append(paramString);
            throw new NumberFormatException(stringBuilder1.toString());
          } 
        } else if (stringBuilder1.bitLength() > 32) {
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Number out of range for 32-bit unsigned integer: ");
          stringBuilder1.append(paramString);
          throw new NumberFormatException(stringBuilder1.toString());
        } 
      } else if (paramBoolean1) {
        if (stringBuilder1.bitLength() > 63) {
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Number out of range for 64-bit signed integer: ");
          stringBuilder1.append(paramString);
          throw new NumberFormatException(stringBuilder1.toString());
        } 
      } else if (stringBuilder1.bitLength() > 64) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Number out of range for 64-bit unsigned integer: ");
        stringBuilder1.append(paramString);
        throw new NumberFormatException(stringBuilder1.toString());
      } 
      l = stringBuilder1.longValue();
    } 
    return l;
  }
  
  static int parseUInt32(String paramString) throws NumberFormatException {
    return (int)parseInteger(paramString, false, false);
  }
  
  static long parseUInt64(String paramString) throws NumberFormatException {
    return parseInteger(paramString, false, true);
  }
  
  @Deprecated
  public static void print(MessageOrBuilder paramMessageOrBuilder, Appendable paramAppendable) throws IOException {
    printer().print(paramMessageOrBuilder, paramAppendable);
  }
  
  @Deprecated
  public static void print(UnknownFieldSet paramUnknownFieldSet, Appendable paramAppendable) throws IOException {
    printer().print(paramUnknownFieldSet, paramAppendable);
  }
  
  @Deprecated
  public static void printField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject, Appendable paramAppendable) throws IOException {
    printer().printField(paramFieldDescriptor, paramObject, paramAppendable);
  }
  
  @Deprecated
  public static String printFieldToString(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject) {
    return printer().printFieldToString(paramFieldDescriptor, paramObject);
  }
  
  @Deprecated
  public static void printFieldValue(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject, Appendable paramAppendable) throws IOException {
    printer().printFieldValue(paramFieldDescriptor, paramObject, paramAppendable);
  }
  
  @Deprecated
  public static String printToString(MessageOrBuilder paramMessageOrBuilder) {
    return printer().printToString(paramMessageOrBuilder);
  }
  
  @Deprecated
  public static String printToString(UnknownFieldSet paramUnknownFieldSet) {
    return printer().printToString(paramUnknownFieldSet);
  }
  
  @Deprecated
  public static String printToUnicodeString(MessageOrBuilder paramMessageOrBuilder) {
    return printer().escapingNonAscii(false).printToString(paramMessageOrBuilder);
  }
  
  @Deprecated
  public static String printToUnicodeString(UnknownFieldSet paramUnknownFieldSet) {
    return printer().escapingNonAscii(false).printToString(paramUnknownFieldSet);
  }
  
  @Deprecated
  public static void printUnicode(MessageOrBuilder paramMessageOrBuilder, Appendable paramAppendable) throws IOException {
    printer().escapingNonAscii(false).print(paramMessageOrBuilder, paramAppendable);
  }
  
  @Deprecated
  public static void printUnicode(UnknownFieldSet paramUnknownFieldSet, Appendable paramAppendable) throws IOException {
    printer().escapingNonAscii(false).print(paramUnknownFieldSet, paramAppendable);
  }
  
  @Deprecated
  public static void printUnicodeFieldValue(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject, Appendable paramAppendable) throws IOException {
    printer().escapingNonAscii(false).printFieldValue(paramFieldDescriptor, paramObject, paramAppendable);
  }
  
  private static void printUnknownFieldValue(int paramInt, Object paramObject, TextGenerator paramTextGenerator) throws IOException {
    int i = WireFormat.getTagWireType(paramInt);
    if (i != 5) {
      switch (i) {
        default:
          paramObject = new StringBuilder();
          paramObject.append("Bad tag: ");
          paramObject.append(paramInt);
          throw new IllegalArgumentException(paramObject.toString());
        case 3:
          Printer.printUnknownFields((UnknownFieldSet)paramObject, paramTextGenerator);
          return;
        case 2:
          try {
            UnknownFieldSet unknownFieldSet = UnknownFieldSet.parseFrom((ByteString)paramObject);
            paramTextGenerator.print("{");
            paramTextGenerator.eol();
            paramTextGenerator.indent();
            Printer.printUnknownFields(unknownFieldSet, paramTextGenerator);
            paramTextGenerator.outdent();
            paramTextGenerator.print("}");
          } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
            paramTextGenerator.print("\"");
            paramTextGenerator.print(escapeBytes((ByteString)paramObject));
            paramTextGenerator.print("\"");
          } 
          return;
        case 1:
          paramTextGenerator.print(String.format((Locale)null, "0x%016x", new Object[] { paramObject }));
          return;
        case 0:
          break;
      } 
      paramTextGenerator.print(unsignedToString(((Long)paramObject).longValue()));
    } else {
      paramTextGenerator.print(String.format((Locale)null, "0x%08x", new Object[] { paramObject }));
    } 
  }
  
  public static void printUnknownFieldValue(int paramInt, Object paramObject, Appendable paramAppendable) throws IOException {
    printUnknownFieldValue(paramInt, paramObject, multiLineOutput(paramAppendable));
  }
  
  public static Printer printer() {
    return Printer.DEFAULT;
  }
  
  @Deprecated
  public static String shortDebugString(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject) {
    return printer().shortDebugString(paramFieldDescriptor, paramObject);
  }
  
  public static String shortDebugString(MessageOrBuilder paramMessageOrBuilder) {
    return printer().shortDebugString(paramMessageOrBuilder);
  }
  
  @Deprecated
  public static String shortDebugString(UnknownFieldSet paramUnknownFieldSet) {
    return printer().shortDebugString(paramUnknownFieldSet);
  }
  
  private static TextGenerator singleLineOutput(Appendable paramAppendable) {
    return new TextGenerator(paramAppendable, true);
  }
  
  public static ByteString unescapeBytes(CharSequence paramCharSequence) throws InvalidEscapeSequenceException {
    ByteString byteString = ByteString.copyFromUtf8(paramCharSequence.toString());
    byte[] arrayOfByte = new byte[byteString.size()];
    int i = 0;
    int j = i;
    while (true) {
      if (i < byteString.size()) {
        int m = byteString.byteAt(i);
        if (m == 92) {
          m = i + 1;
          if (m < byteString.size()) {
            byte b = byteString.byteAt(m);
            if (isOctal(b)) {
              int n = digitValue(b);
              int i1 = m + 1;
              int i2 = m;
              i = n;
              if (i1 < byteString.size()) {
                i2 = m;
                i = n;
                if (isOctal(byteString.byteAt(i1))) {
                  i = n * 8 + digitValue(byteString.byteAt(i1));
                  i2 = i1;
                } 
              } 
              n = i2 + 1;
              m = i2;
              i1 = i;
              if (n < byteString.size()) {
                m = i2;
                i1 = i;
                if (isOctal(byteString.byteAt(n))) {
                  i1 = i * 8 + digitValue(byteString.byteAt(n));
                  m = n;
                } 
              } 
              i2 = j + 1;
              arrayOfByte[j] = (byte)(byte)i1;
              i = m;
            } else {
              if (b != 34) {
                if (b != 39) {
                  if (b != 92) {
                    if (b != 102) {
                      if (b != 110) {
                        if (b != 114) {
                          if (b != 116) {
                            if (b != 118) {
                              StringBuilder stringBuilder;
                              if (b != 120) {
                                switch (b) {
                                  default:
                                    stringBuilder = new StringBuilder();
                                    stringBuilder.append("Invalid escape sequence: '\\");
                                    stringBuilder.append((char)b);
                                    stringBuilder.append('\'');
                                    throw new InvalidEscapeSequenceException(stringBuilder.toString());
                                  case 98:
                                    i = j + 1;
                                    arrayOfByte[j] = (byte)8;
                                    break;
                                  case 97:
                                    i = j + 1;
                                    arrayOfByte[j] = (byte)7;
                                    break;
                                } 
                              } else {
                                int i1 = m + 1;
                                if (i1 < stringBuilder.size() && isHex(stringBuilder.byteAt(i1))) {
                                  int i2 = digitValue(stringBuilder.byteAt(i1));
                                  m = i1 + 1;
                                  i = i1;
                                  int i3 = i2;
                                  if (m < stringBuilder.size()) {
                                    i = i1;
                                    i3 = i2;
                                    if (isHex(stringBuilder.byteAt(m))) {
                                      i3 = i2 * 16 + digitValue(stringBuilder.byteAt(m));
                                      i = m;
                                    } 
                                  } 
                                  m = j + 1;
                                  arrayOfByte[j] = (byte)(byte)i3;
                                  i3 = m;
                                } else {
                                  throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
                                } 
                                m = i;
                              } 
                            } else {
                              i = j + 1;
                              arrayOfByte[j] = (byte)11;
                            } 
                          } else {
                            i = j + 1;
                            arrayOfByte[j] = (byte)9;
                          } 
                        } else {
                          i = j + 1;
                          arrayOfByte[j] = (byte)13;
                        } 
                      } else {
                        i = j + 1;
                        arrayOfByte[j] = (byte)10;
                      } 
                    } else {
                      i = j + 1;
                      arrayOfByte[j] = (byte)12;
                    } 
                  } else {
                    i = j + 1;
                    arrayOfByte[j] = (byte)92;
                  } 
                } else {
                  i = j + 1;
                  arrayOfByte[j] = (byte)39;
                } 
              } else {
                i = j + 1;
                arrayOfByte[j] = (byte)34;
              } 
              int n = i;
              i = m + 1;
              j = n;
            } 
          } else {
            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
          } 
        } else {
          int n = j + 1;
          arrayOfByte[j] = (byte)m;
        } 
      } else {
        break;
      } 
      int k = i;
    } 
    if (arrayOfByte.length == j) {
      byteString = ByteString.wrap(arrayOfByte);
    } else {
      byteString = ByteString.copyFrom(arrayOfByte, 0, j);
    } 
    return byteString;
  }
  
  static String unescapeText(String paramString) throws InvalidEscapeSequenceException {
    return unescapeBytes(paramString).toStringUtf8();
  }
  
  public static String unsignedToString(int paramInt) {
    return (paramInt >= 0) ? Integer.toString(paramInt) : Long.toString(paramInt & 0xFFFFFFFFL);
  }
  
  public static String unsignedToString(long paramLong) {
    return (paramLong >= 0L) ? Long.toString(paramLong) : BigInteger.valueOf(paramLong & Long.MAX_VALUE).setBit(63).toString();
  }
  
  public static class InvalidEscapeSequenceException extends IOException {
    private static final long serialVersionUID = -8164033650142593304L;
    
    InvalidEscapeSequenceException(String param1String) {
      super(param1String);
    }
  }
  
  public static class ParseException extends IOException {
    private static final long serialVersionUID = 3196188060225107702L;
    
    private final int column;
    
    private final int line;
    
    public ParseException(int param1Int1, int param1Int2, String param1String) {
      super(stringBuilder.toString());
      this.line = param1Int1;
      this.column = param1Int2;
    }
    
    public ParseException(String param1String) {
      this(-1, -1, param1String);
    }
    
    public int getColumn() {
      return this.column;
    }
    
    public int getLine() {
      return this.line;
    }
  }
  
  public static class Parser {
    private static final int BUFFER_SIZE = 4096;
    
    private final boolean allowUnknownEnumValues;
    
    private final boolean allowUnknownExtensions;
    
    private final boolean allowUnknownFields;
    
    private TextFormatParseInfoTree.Builder parseInfoTreeBuilder;
    
    private final SingularOverwritePolicy singularOverwritePolicy;
    
    private Parser(boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, SingularOverwritePolicy param1SingularOverwritePolicy, TextFormatParseInfoTree.Builder param1Builder) {
      this.allowUnknownFields = param1Boolean1;
      this.allowUnknownEnumValues = param1Boolean2;
      this.allowUnknownExtensions = param1Boolean3;
      this.singularOverwritePolicy = param1SingularOverwritePolicy;
      this.parseInfoTreeBuilder = param1Builder;
    }
    
    private void checkUnknownFields(List<UnknownField> param1List) throws TextFormat.ParseException {
      boolean bool;
      if (param1List.isEmpty())
        return; 
      StringBuilder stringBuilder = new StringBuilder("Input contains unknown fields and/or extensions:");
      for (UnknownField unknownField : param1List) {
        stringBuilder.append('\n');
        stringBuilder.append(unknownField.message);
      } 
      if (this.allowUnknownFields) {
        TextFormat.logger.warning(stringBuilder.toString());
        return;
      } 
      if (this.allowUnknownExtensions) {
        boolean bool1;
        Iterator<UnknownField> iterator = param1List.iterator();
        bool = false;
        while (true) {
          if (iterator.hasNext()) {
            if (((UnknownField)iterator.next()).type == UnknownField.Type.FIELD) {
              boolean bool2 = false;
              break;
            } 
            bool++;
            continue;
          } 
          bool1 = true;
          break;
        } 
        if (bool1) {
          TextFormat.logger.warning(stringBuilder.toString());
          return;
        } 
      } else {
        bool = false;
      } 
      String[] arrayOfString = ((UnknownField)param1List.get(bool)).message.split(":");
      throw new TextFormat.ParseException(Integer.parseInt(arrayOfString[0]), Integer.parseInt(arrayOfString[1]), stringBuilder.toString());
    }
    
    private void consumeFieldValue(TextFormat.Tokenizer param1Tokenizer, ExtensionRegistry param1ExtensionRegistry, MessageReflection.MergeTarget param1MergeTarget, Descriptors.FieldDescriptor param1FieldDescriptor, ExtensionRegistry.ExtensionInfo param1ExtensionInfo, TextFormatParseInfoTree.Builder param1Builder, List<UnknownField> param1List) throws TextFormat.ParseException {
      Object object;
      StringBuilder stringBuilder1;
      if (this.singularOverwritePolicy == SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES && !param1FieldDescriptor.isRepeated()) {
        if (param1MergeTarget.hasField(param1FieldDescriptor)) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Non-repeated field \"");
          stringBuilder.append(param1FieldDescriptor.getFullName());
          stringBuilder.append("\" cannot be overwritten.");
          throw param1Tokenizer.parseExceptionPreviousToken(stringBuilder.toString());
        } 
        if (param1FieldDescriptor.getContainingOneof() != null && param1MergeTarget.hasOneof(param1FieldDescriptor.getContainingOneof())) {
          Descriptors.OneofDescriptor oneofDescriptor = param1FieldDescriptor.getContainingOneof();
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Field \"");
          stringBuilder1.append(param1FieldDescriptor.getFullName());
          stringBuilder1.append("\" is specified along with field \"");
          stringBuilder1.append(param1MergeTarget.getOneofFieldDescriptor(oneofDescriptor).getFullName());
          stringBuilder1.append("\", another member of oneof \"");
          stringBuilder1.append(oneofDescriptor.getName());
          stringBuilder1.append("\".");
          throw param1Tokenizer.parseExceptionPreviousToken(stringBuilder1.toString());
        } 
      } 
      Descriptors.FieldDescriptor.JavaType javaType1 = param1FieldDescriptor.getJavaType();
      Descriptors.FieldDescriptor.JavaType javaType2 = Descriptors.FieldDescriptor.JavaType.MESSAGE;
      String str = null;
      StringBuilder stringBuilder2 = null;
      if (javaType1 == javaType2) {
        Message message;
        if (param1Tokenizer.tryConsume("<")) {
          str = ">";
        } else {
          param1Tokenizer.consume("{");
          str = "}";
        } 
        if (stringBuilder1 == null) {
          stringBuilder1 = stringBuilder2;
        } else {
          message = ((ExtensionRegistry.ExtensionInfo)stringBuilder1).defaultInstance;
        } 
        MessageReflection.MergeTarget mergeTarget = param1MergeTarget.newMergeTargetForField(param1FieldDescriptor, message);
        while (!param1Tokenizer.tryConsume(str)) {
          StringBuilder stringBuilder;
          if (param1Tokenizer.atEnd()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Expected \"");
            stringBuilder.append(str);
            stringBuilder.append("\".");
            throw param1Tokenizer.parseException(stringBuilder.toString());
          } 
          mergeField(param1Tokenizer, (ExtensionRegistry)stringBuilder, mergeTarget, param1Builder, param1List);
        } 
        object = mergeTarget.finish();
      } else {
        String str2;
        Descriptors.EnumValueDescriptor enumValueDescriptor1;
        ByteString byteString;
        String str1;
        Long long_2;
        Integer integer;
        Double double_;
        Float float_;
        Boolean bool;
        Long long_1;
        Descriptors.EnumValueDescriptor enumValueDescriptor2;
        Descriptors.EnumDescriptor enumDescriptor;
        String str3;
        switch (param1FieldDescriptor.getType()) {
          default:
            str2 = str;
            break;
          case MESSAGE:
          case GROUP:
            throw new RuntimeException("Can't get here.");
          case ENUM:
            enumDescriptor = param1FieldDescriptor.getEnumType();
            if (param1Tokenizer.lookingAtInteger()) {
              int i = param1Tokenizer.consumeInt32();
              Descriptors.EnumValueDescriptor enumValueDescriptor4 = enumDescriptor.findValueByNumber(i);
              Descriptors.EnumValueDescriptor enumValueDescriptor3 = enumValueDescriptor4;
              if (enumValueDescriptor4 == null) {
                StringBuilder stringBuilder4 = new StringBuilder();
                stringBuilder4.append("Enum type \"");
                stringBuilder4.append(enumDescriptor.getFullName());
                stringBuilder4.append("\" has no value with number ");
                stringBuilder4.append(i);
                stringBuilder4.append('.');
                String str4 = stringBuilder4.toString();
                if (this.allowUnknownEnumValues) {
                  TextFormat.logger.warning(str4);
                  return;
                } 
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("Enum type \"");
                stringBuilder3.append(enumDescriptor.getFullName());
                stringBuilder3.append("\" has no value with number ");
                stringBuilder3.append(i);
                stringBuilder3.append('.');
                throw param1Tokenizer.parseExceptionPreviousToken(stringBuilder3.toString());
              } 
              break;
            } 
            str3 = param1Tokenizer.consumeIdentifier();
            enumValueDescriptor2 = enumDescriptor.findValueByName(str3);
            enumValueDescriptor1 = enumValueDescriptor2;
            if (enumValueDescriptor2 == null) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Enum type \"");
              stringBuilder.append(enumDescriptor.getFullName());
              stringBuilder.append("\" has no value named \"");
              stringBuilder.append(str3);
              stringBuilder.append("\".");
              String str4 = stringBuilder.toString();
              if (this.allowUnknownEnumValues) {
                TextFormat.logger.warning(str4);
                return;
              } 
              throw param1Tokenizer.parseExceptionPreviousToken(str4);
            } 
            break;
          case BYTES:
            byteString = param1Tokenizer.consumeByteString();
            break;
          case STRING:
            str1 = param1Tokenizer.consumeString();
            break;
          case UINT64:
          case FIXED64:
            long_2 = Long.valueOf(param1Tokenizer.consumeUInt64());
            break;
          case UINT32:
          case FIXED32:
            integer = Integer.valueOf(param1Tokenizer.consumeUInt32());
            break;
          case DOUBLE:
            double_ = Double.valueOf(param1Tokenizer.consumeDouble());
            break;
          case FLOAT:
            float_ = Float.valueOf(param1Tokenizer.consumeFloat());
            break;
          case BOOL:
            bool = Boolean.valueOf(param1Tokenizer.consumeBoolean());
            break;
          case INT64:
          case SINT64:
          case SFIXED64:
            long_1 = Long.valueOf(param1Tokenizer.consumeInt64());
            break;
          case INT32:
          case SINT32:
          case SFIXED32:
            object = Integer.valueOf(param1Tokenizer.consumeInt32());
            break;
        } 
      } 
      if (param1FieldDescriptor.isRepeated()) {
        param1MergeTarget.addRepeatedField(param1FieldDescriptor, object);
      } else {
        param1MergeTarget.setField(param1FieldDescriptor, object);
      } 
    }
    
    private void consumeFieldValues(TextFormat.Tokenizer param1Tokenizer, ExtensionRegistry param1ExtensionRegistry, MessageReflection.MergeTarget param1MergeTarget, Descriptors.FieldDescriptor param1FieldDescriptor, ExtensionRegistry.ExtensionInfo param1ExtensionInfo, TextFormatParseInfoTree.Builder param1Builder, List<UnknownField> param1List) throws TextFormat.ParseException {
      if (param1FieldDescriptor.isRepeated() && param1Tokenizer.tryConsume("[")) {
        if (!param1Tokenizer.tryConsume("]"))
          while (true) {
            consumeFieldValue(param1Tokenizer, param1ExtensionRegistry, param1MergeTarget, param1FieldDescriptor, param1ExtensionInfo, param1Builder, param1List);
            if (param1Tokenizer.tryConsume("]"))
              break; 
            param1Tokenizer.consume(",");
          }  
      } else {
        consumeFieldValue(param1Tokenizer, param1ExtensionRegistry, param1MergeTarget, param1FieldDescriptor, param1ExtensionInfo, param1Builder, param1List);
      } 
    }
    
    private void mergeField(TextFormat.Tokenizer param1Tokenizer, ExtensionRegistry param1ExtensionRegistry, MessageReflection.MergeTarget param1MergeTarget, TextFormatParseInfoTree.Builder param1Builder, List<UnknownField> param1List) throws TextFormat.ParseException {
      StringBuilder stringBuilder;
      Descriptors.Descriptor descriptor2;
      int i = param1Tokenizer.getLine();
      int j = param1Tokenizer.getColumn();
      Descriptors.Descriptor descriptor1 = param1MergeTarget.getDescriptorForType();
      boolean bool = param1Tokenizer.tryConsume("[");
      Descriptors.FieldDescriptor fieldDescriptor = null;
      if (bool) {
        StringBuilder stringBuilder1 = new StringBuilder(param1Tokenizer.consumeIdentifier());
        while (param1Tokenizer.tryConsume(".")) {
          stringBuilder1.append('.');
          stringBuilder1.append(param1Tokenizer.consumeIdentifier());
        } 
        ExtensionRegistry.ExtensionInfo extensionInfo = param1MergeTarget.findExtensionByName(param1ExtensionRegistry, stringBuilder1.toString());
        if (extensionInfo == null) {
          StringBuilder stringBuilder2 = new StringBuilder();
          stringBuilder2.append(param1Tokenizer.getPreviousLine() + 1);
          stringBuilder2.append(":");
          stringBuilder2.append(param1Tokenizer.getPreviousColumn() + 1);
          stringBuilder2.append(":\t");
          stringBuilder2.append(descriptor1.getFullName());
          stringBuilder2.append(".[");
          stringBuilder2.append(stringBuilder1);
          stringBuilder2.append("]");
          param1List.add(new UnknownField(stringBuilder2.toString(), UnknownField.Type.EXTENSION));
        } else {
          if (extensionInfo.descriptor.getContainingType() != descriptor1) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Extension \"");
            stringBuilder.append(stringBuilder1);
            stringBuilder.append("\" does not extend message type \"");
            stringBuilder.append(descriptor1.getFullName());
            stringBuilder.append("\".");
            throw param1Tokenizer.parseExceptionPreviousToken(stringBuilder.toString());
          } 
          fieldDescriptor = extensionInfo.descriptor;
        } 
        param1Tokenizer.consume("]");
      } else {
        String str = param1Tokenizer.consumeIdentifier();
        Descriptors.FieldDescriptor fieldDescriptor1 = descriptor1.findFieldByName(str);
        fieldDescriptor = fieldDescriptor1;
        if (fieldDescriptor1 == null) {
          fieldDescriptor1 = descriptor1.findFieldByName(str.toLowerCase(Locale.US));
          fieldDescriptor = fieldDescriptor1;
          if (fieldDescriptor1 != null) {
            fieldDescriptor = fieldDescriptor1;
            if (fieldDescriptor1.getType() != Descriptors.FieldDescriptor.Type.GROUP)
              fieldDescriptor = null; 
          } 
        } 
        fieldDescriptor1 = fieldDescriptor;
        if (fieldDescriptor != null) {
          fieldDescriptor1 = fieldDescriptor;
          if (fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
            fieldDescriptor1 = fieldDescriptor;
            if (!fieldDescriptor.getMessageType().getName().equals(str))
              fieldDescriptor1 = null; 
          } 
        } 
        if (fieldDescriptor1 == null) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append(param1Tokenizer.getPreviousLine() + 1);
          stringBuilder1.append(":");
          stringBuilder1.append(param1Tokenizer.getPreviousColumn() + 1);
          stringBuilder1.append(":\t");
          stringBuilder1.append(descriptor1.getFullName());
          stringBuilder1.append(".");
          stringBuilder1.append(str);
          param1List.add(new UnknownField(stringBuilder1.toString(), UnknownField.Type.FIELD));
        } 
        descriptor1 = null;
        fieldDescriptor = fieldDescriptor1;
        descriptor2 = descriptor1;
      } 
      if (fieldDescriptor == null) {
        if (param1Tokenizer.tryConsume(":") && !param1Tokenizer.lookingAt("{") && !param1Tokenizer.lookingAt("<")) {
          skipFieldValue(param1Tokenizer);
        } else {
          skipFieldMessage(param1Tokenizer);
        } 
        return;
      } 
      if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
        param1Tokenizer.tryConsume(":");
        if (param1Builder != null) {
          consumeFieldValues(param1Tokenizer, (ExtensionRegistry)stringBuilder, param1MergeTarget, fieldDescriptor, (ExtensionRegistry.ExtensionInfo)descriptor2, param1Builder.getBuilderForSubMessageField(fieldDescriptor), param1List);
        } else {
          consumeFieldValues(param1Tokenizer, (ExtensionRegistry)stringBuilder, param1MergeTarget, fieldDescriptor, (ExtensionRegistry.ExtensionInfo)descriptor2, param1Builder, param1List);
        } 
      } else {
        param1Tokenizer.consume(":");
        consumeFieldValues(param1Tokenizer, (ExtensionRegistry)stringBuilder, param1MergeTarget, fieldDescriptor, (ExtensionRegistry.ExtensionInfo)descriptor2, param1Builder, param1List);
      } 
      if (param1Builder != null)
        param1Builder.setLocation(fieldDescriptor, TextFormatParseLocation.create(i, j)); 
      if (!param1Tokenizer.tryConsume(";"))
        param1Tokenizer.tryConsume(","); 
    }
    
    private void mergeField(TextFormat.Tokenizer param1Tokenizer, ExtensionRegistry param1ExtensionRegistry, MessageReflection.MergeTarget param1MergeTarget, List<UnknownField> param1List) throws TextFormat.ParseException {
      mergeField(param1Tokenizer, param1ExtensionRegistry, param1MergeTarget, this.parseInfoTreeBuilder, param1List);
    }
    
    public static Builder newBuilder() {
      return new Builder();
    }
    
    private void skipField(TextFormat.Tokenizer param1Tokenizer) throws TextFormat.ParseException {
      // Byte code:
      //   0: aload_1
      //   1: ldc_w '['
      //   4: invokevirtual tryConsume : (Ljava/lang/String;)Z
      //   7: ifeq -> 35
      //   10: aload_1
      //   11: invokevirtual consumeIdentifier : ()Ljava/lang/String;
      //   14: pop
      //   15: aload_1
      //   16: ldc_w '.'
      //   19: invokevirtual tryConsume : (Ljava/lang/String;)Z
      //   22: ifne -> 10
      //   25: aload_1
      //   26: ldc_w ']'
      //   29: invokevirtual consume : (Ljava/lang/String;)V
      //   32: goto -> 40
      //   35: aload_1
      //   36: invokevirtual consumeIdentifier : ()Ljava/lang/String;
      //   39: pop
      //   40: aload_1
      //   41: ldc ':'
      //   43: invokevirtual tryConsume : (Ljava/lang/String;)Z
      //   46: ifeq -> 75
      //   49: aload_1
      //   50: ldc '<'
      //   52: invokevirtual lookingAt : (Ljava/lang/String;)Z
      //   55: ifne -> 75
      //   58: aload_1
      //   59: ldc '{'
      //   61: invokevirtual lookingAt : (Ljava/lang/String;)Z
      //   64: ifne -> 75
      //   67: aload_0
      //   68: aload_1
      //   69: invokespecial skipFieldValue : (Lcom/google/protobuf/TextFormat$Tokenizer;)V
      //   72: goto -> 80
      //   75: aload_0
      //   76: aload_1
      //   77: invokespecial skipFieldMessage : (Lcom/google/protobuf/TextFormat$Tokenizer;)V
      //   80: aload_1
      //   81: ldc_w ';'
      //   84: invokevirtual tryConsume : (Ljava/lang/String;)Z
      //   87: ifne -> 98
      //   90: aload_1
      //   91: ldc_w ','
      //   94: invokevirtual tryConsume : (Ljava/lang/String;)Z
      //   97: pop
      //   98: return
    }
    
    private void skipFieldMessage(TextFormat.Tokenizer param1Tokenizer) throws TextFormat.ParseException {
      String str;
      if (param1Tokenizer.tryConsume("<")) {
        str = ">";
      } else {
        param1Tokenizer.consume("{");
        str = "}";
      } 
      while (!param1Tokenizer.lookingAt(">") && !param1Tokenizer.lookingAt("}"))
        skipField(param1Tokenizer); 
      param1Tokenizer.consume(str);
    }
    
    private void skipFieldValue(TextFormat.Tokenizer param1Tokenizer) throws TextFormat.ParseException {
      if (param1Tokenizer.tryConsumeString()) {
        while (param1Tokenizer.tryConsumeString());
        return;
      } 
      if (!param1Tokenizer.tryConsumeIdentifier() && !param1Tokenizer.tryConsumeInt64() && !param1Tokenizer.tryConsumeUInt64() && !param1Tokenizer.tryConsumeDouble() && !param1Tokenizer.tryConsumeFloat()) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid field value: ");
        stringBuilder.append(param1Tokenizer.currentToken);
        throw param1Tokenizer.parseException(stringBuilder.toString());
      } 
    }
    
    private static StringBuilder toStringBuilder(Readable param1Readable) throws IOException {
      StringBuilder stringBuilder = new StringBuilder();
      CharBuffer charBuffer = CharBuffer.allocate(4096);
      while (true) {
        int i = param1Readable.read(charBuffer);
        if (i == -1)
          return stringBuilder; 
        charBuffer.flip();
        stringBuilder.append(charBuffer, 0, i);
      } 
    }
    
    public void merge(CharSequence param1CharSequence, ExtensionRegistry param1ExtensionRegistry, Message.Builder param1Builder) throws TextFormat.ParseException {
      TextFormat.Tokenizer tokenizer = new TextFormat.Tokenizer(param1CharSequence);
      MessageReflection.BuilderAdapter builderAdapter = new MessageReflection.BuilderAdapter(param1Builder);
      ArrayList<UnknownField> arrayList = new ArrayList();
      while (!tokenizer.atEnd())
        mergeField(tokenizer, param1ExtensionRegistry, builderAdapter, arrayList); 
      checkUnknownFields(arrayList);
    }
    
    public void merge(CharSequence param1CharSequence, Message.Builder param1Builder) throws TextFormat.ParseException {
      merge(param1CharSequence, ExtensionRegistry.getEmptyRegistry(), param1Builder);
    }
    
    public void merge(Readable param1Readable, ExtensionRegistry param1ExtensionRegistry, Message.Builder param1Builder) throws IOException {
      merge(toStringBuilder(param1Readable), param1ExtensionRegistry, param1Builder);
    }
    
    public void merge(Readable param1Readable, Message.Builder param1Builder) throws IOException {
      merge(param1Readable, ExtensionRegistry.getEmptyRegistry(), param1Builder);
    }
    
    public static class Builder {
      private boolean allowUnknownEnumValues = false;
      
      private boolean allowUnknownExtensions = false;
      
      private boolean allowUnknownFields = false;
      
      private TextFormatParseInfoTree.Builder parseInfoTreeBuilder = null;
      
      private TextFormat.Parser.SingularOverwritePolicy singularOverwritePolicy = TextFormat.Parser.SingularOverwritePolicy.ALLOW_SINGULAR_OVERWRITES;
      
      public TextFormat.Parser build() {
        return new TextFormat.Parser(this.allowUnknownFields, this.allowUnknownEnumValues, this.allowUnknownExtensions, this.singularOverwritePolicy, this.parseInfoTreeBuilder);
      }
      
      public Builder setAllowUnknownExtensions(boolean param2Boolean) {
        this.allowUnknownExtensions = param2Boolean;
        return this;
      }
      
      public Builder setAllowUnknownFields(boolean param2Boolean) {
        this.allowUnknownFields = param2Boolean;
        return this;
      }
      
      public Builder setParseInfoTreeBuilder(TextFormatParseInfoTree.Builder param2Builder) {
        this.parseInfoTreeBuilder = param2Builder;
        return this;
      }
      
      public Builder setSingularOverwritePolicy(TextFormat.Parser.SingularOverwritePolicy param2SingularOverwritePolicy) {
        this.singularOverwritePolicy = param2SingularOverwritePolicy;
        return this;
      }
    }
    
    public enum SingularOverwritePolicy {
      ALLOW_SINGULAR_OVERWRITES, FORBID_SINGULAR_OVERWRITES;
      
      static {
      
      }
    }
    
    static final class UnknownField {
      final String message;
      
      final Type type;
      
      UnknownField(String param2String, Type param2Type) {
        this.message = param2String;
        this.type = param2Type;
      }
      
      enum Type {
        FIELD, EXTENSION;
        
        static {
        
        }
      }
    }
    
    enum Type {
      EXTENSION, FIELD;
      
      static {
      
      }
    }
  }
  
  public static class Builder {
    private boolean allowUnknownEnumValues = false;
    
    private boolean allowUnknownExtensions = false;
    
    private boolean allowUnknownFields = false;
    
    private TextFormatParseInfoTree.Builder parseInfoTreeBuilder = null;
    
    private TextFormat.Parser.SingularOverwritePolicy singularOverwritePolicy = TextFormat.Parser.SingularOverwritePolicy.ALLOW_SINGULAR_OVERWRITES;
    
    public TextFormat.Parser build() {
      return new TextFormat.Parser(this.allowUnknownFields, this.allowUnknownEnumValues, this.allowUnknownExtensions, this.singularOverwritePolicy, this.parseInfoTreeBuilder);
    }
    
    public Builder setAllowUnknownExtensions(boolean param1Boolean) {
      this.allowUnknownExtensions = param1Boolean;
      return this;
    }
    
    public Builder setAllowUnknownFields(boolean param1Boolean) {
      this.allowUnknownFields = param1Boolean;
      return this;
    }
    
    public Builder setParseInfoTreeBuilder(TextFormatParseInfoTree.Builder param1Builder) {
      this.parseInfoTreeBuilder = param1Builder;
      return this;
    }
    
    public Builder setSingularOverwritePolicy(TextFormat.Parser.SingularOverwritePolicy param1SingularOverwritePolicy) {
      this.singularOverwritePolicy = param1SingularOverwritePolicy;
      return this;
    }
  }
  
  public enum SingularOverwritePolicy {
    ALLOW_SINGULAR_OVERWRITES, FORBID_SINGULAR_OVERWRITES;
    
    static {
    
    }
  }
  
  static final class UnknownField {
    final String message;
    
    final Type type;
    
    UnknownField(String param1String, Type param1Type) {
      this.message = param1String;
      this.type = param1Type;
    }
    
    enum Type {
      FIELD, EXTENSION;
      
      static {
      
      }
    }
  }
  
  enum Type {
    EXTENSION, FIELD;
    
    static {
      $VALUES = new Type[] { FIELD, EXTENSION };
    }
  }
  
  public static final class Printer {
    private static final Printer DEFAULT = new Printer(true);
    
    private final boolean escapeNonAscii;
    
    private Printer(boolean param1Boolean) {
      this.escapeNonAscii = param1Boolean;
    }
    
    private void print(MessageOrBuilder param1MessageOrBuilder, TextFormat.TextGenerator param1TextGenerator) throws IOException {
      printMessage(param1MessageOrBuilder, param1TextGenerator);
    }
    
    private void printField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object, TextFormat.TextGenerator param1TextGenerator) throws IOException {
      if (param1FieldDescriptor.isRepeated()) {
        param1Object = ((List)param1Object).iterator();
        while (param1Object.hasNext())
          printSingleField(param1FieldDescriptor, param1Object.next(), param1TextGenerator); 
      } else {
        printSingleField(param1FieldDescriptor, param1Object, param1TextGenerator);
      } 
    }
    
    private void printFieldValue(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object, TextFormat.TextGenerator param1TextGenerator) throws IOException {
      String str;
      switch (param1FieldDescriptor.getType()) {
        default:
          return;
        case MESSAGE:
        case GROUP:
          print((Message)param1Object, param1TextGenerator);
        case ENUM:
          param1TextGenerator.print(((Descriptors.EnumValueDescriptor)param1Object).getName());
        case BYTES:
          param1TextGenerator.print("\"");
          if (param1Object instanceof ByteString) {
            param1TextGenerator.print(TextFormat.escapeBytes((ByteString)param1Object));
          } else {
            param1TextGenerator.print(TextFormat.escapeBytes((byte[])param1Object));
          } 
          param1TextGenerator.print("\"");
        case STRING:
          param1TextGenerator.print("\"");
          if (this.escapeNonAscii) {
            str = TextFormatEscaper.escapeText((String)param1Object);
          } else {
            str = TextFormat.escapeDoubleQuotesAndBackslashes((String)param1Object).replace("\n", "\\n");
          } 
          param1TextGenerator.print(str);
          param1TextGenerator.print("\"");
        case UINT64:
        case FIXED64:
          param1TextGenerator.print(TextFormat.unsignedToString(((Long)param1Object).longValue()));
        case UINT32:
        case FIXED32:
          param1TextGenerator.print(TextFormat.unsignedToString(((Integer)param1Object).intValue()));
        case DOUBLE:
          param1TextGenerator.print(((Double)param1Object).toString());
        case FLOAT:
          param1TextGenerator.print(((Float)param1Object).toString());
        case BOOL:
          param1TextGenerator.print(((Boolean)param1Object).toString());
        case INT64:
        case SINT64:
        case SFIXED64:
          param1TextGenerator.print(((Long)param1Object).toString());
        case INT32:
        case SINT32:
        case SFIXED32:
          break;
      } 
      param1TextGenerator.print(((Integer)param1Object).toString());
    }
    
    private void printMessage(MessageOrBuilder param1MessageOrBuilder, TextFormat.TextGenerator param1TextGenerator) throws IOException {
      for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : param1MessageOrBuilder.getAllFields().entrySet())
        printField((Descriptors.FieldDescriptor)entry.getKey(), entry.getValue(), param1TextGenerator); 
      printUnknownFields(param1MessageOrBuilder.getUnknownFields(), param1TextGenerator);
    }
    
    private void printSingleField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object, TextFormat.TextGenerator param1TextGenerator) throws IOException {
      if (param1FieldDescriptor.isExtension()) {
        param1TextGenerator.print("[");
        if (param1FieldDescriptor.getContainingType().getOptions().getMessageSetWireFormat() && param1FieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && param1FieldDescriptor.isOptional() && param1FieldDescriptor.getExtensionScope() == param1FieldDescriptor.getMessageType()) {
          param1TextGenerator.print(param1FieldDescriptor.getMessageType().getFullName());
        } else {
          param1TextGenerator.print(param1FieldDescriptor.getFullName());
        } 
        param1TextGenerator.print("]");
      } else if (param1FieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
        param1TextGenerator.print(param1FieldDescriptor.getMessageType().getName());
      } else {
        param1TextGenerator.print(param1FieldDescriptor.getName());
      } 
      if (param1FieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
        param1TextGenerator.print(" {");
        param1TextGenerator.eol();
        param1TextGenerator.indent();
      } else {
        param1TextGenerator.print(": ");
      } 
      printFieldValue(param1FieldDescriptor, param1Object, param1TextGenerator);
      if (param1FieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
        param1TextGenerator.outdent();
        param1TextGenerator.print("}");
      } 
      param1TextGenerator.eol();
    }
    
    private static void printUnknownField(int param1Int1, int param1Int2, List<?> param1List, TextFormat.TextGenerator param1TextGenerator) throws IOException {
      for (List<?> param1List : param1List) {
        param1TextGenerator.print(String.valueOf(param1Int1));
        param1TextGenerator.print(": ");
        printUnknownFieldValue(param1Int2, param1List, param1TextGenerator);
        param1TextGenerator.eol();
      } 
    }
    
    private static void printUnknownFieldValue(int param1Int, Object param1Object, TextFormat.TextGenerator param1TextGenerator) throws IOException {
      int i = WireFormat.getTagWireType(param1Int);
      if (i != 5) {
        switch (i) {
          default:
            param1Object = new StringBuilder();
            param1Object.append("Bad tag: ");
            param1Object.append(param1Int);
            throw new IllegalArgumentException(param1Object.toString());
          case 3:
            printUnknownFields((UnknownFieldSet)param1Object, param1TextGenerator);
            return;
          case 2:
            try {
              UnknownFieldSet unknownFieldSet = UnknownFieldSet.parseFrom((ByteString)param1Object);
              param1TextGenerator.print("{");
              param1TextGenerator.eol();
              param1TextGenerator.indent();
              printUnknownFields(unknownFieldSet, param1TextGenerator);
              param1TextGenerator.outdent();
              param1TextGenerator.print("}");
            } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
              param1TextGenerator.print("\"");
              param1TextGenerator.print(TextFormat.escapeBytes((ByteString)param1Object));
              param1TextGenerator.print("\"");
            } 
            return;
          case 1:
            param1TextGenerator.print(String.format((Locale)null, "0x%016x", new Object[] { param1Object }));
            return;
          case 0:
            break;
        } 
        param1TextGenerator.print(TextFormat.unsignedToString(((Long)param1Object).longValue()));
      } else {
        param1TextGenerator.print(String.format((Locale)null, "0x%08x", new Object[] { param1Object }));
      } 
    }
    
    private static void printUnknownFields(UnknownFieldSet param1UnknownFieldSet, TextFormat.TextGenerator param1TextGenerator) throws IOException {
      for (Map.Entry<Integer, UnknownFieldSet.Field> entry : param1UnknownFieldSet.asMap().entrySet()) {
        int i = ((Integer)entry.getKey()).intValue();
        UnknownFieldSet.Field field = (UnknownFieldSet.Field)entry.getValue();
        printUnknownField(i, 0, field.getVarintList(), param1TextGenerator);
        printUnknownField(i, 5, field.getFixed32List(), param1TextGenerator);
        printUnknownField(i, 1, field.getFixed64List(), param1TextGenerator);
        printUnknownField(i, 2, field.getLengthDelimitedList(), param1TextGenerator);
        for (UnknownFieldSet unknownFieldSet : field.getGroupList()) {
          param1TextGenerator.print(((Integer)entry.getKey()).toString());
          param1TextGenerator.print(" {");
          param1TextGenerator.eol();
          param1TextGenerator.indent();
          printUnknownFields(unknownFieldSet, param1TextGenerator);
          param1TextGenerator.outdent();
          param1TextGenerator.print("}");
          param1TextGenerator.eol();
        } 
      } 
    }
    
    public Printer escapingNonAscii(boolean param1Boolean) {
      return new Printer(param1Boolean);
    }
    
    public void print(MessageOrBuilder param1MessageOrBuilder, Appendable param1Appendable) throws IOException {
      print(param1MessageOrBuilder, TextFormat.multiLineOutput(param1Appendable));
    }
    
    public void print(UnknownFieldSet param1UnknownFieldSet, Appendable param1Appendable) throws IOException {
      printUnknownFields(param1UnknownFieldSet, TextFormat.multiLineOutput(param1Appendable));
    }
    
    public void printField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object, Appendable param1Appendable) throws IOException {
      printField(param1FieldDescriptor, param1Object, TextFormat.multiLineOutput(param1Appendable));
    }
    
    public String printFieldToString(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      try {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        printField(param1FieldDescriptor, param1Object, stringBuilder);
        return stringBuilder.toString();
      } catch (IOException iOException) {
        throw new IllegalStateException(iOException);
      } 
    }
    
    public void printFieldValue(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object, Appendable param1Appendable) throws IOException {
      printFieldValue(param1FieldDescriptor, param1Object, TextFormat.multiLineOutput(param1Appendable));
    }
    
    public String printToString(MessageOrBuilder param1MessageOrBuilder) {
      try {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        print(param1MessageOrBuilder, stringBuilder);
        return stringBuilder.toString();
      } catch (IOException iOException) {
        throw new IllegalStateException(iOException);
      } 
    }
    
    public String printToString(UnknownFieldSet param1UnknownFieldSet) {
      try {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        print(param1UnknownFieldSet, stringBuilder);
        return stringBuilder.toString();
      } catch (IOException iOException) {
        throw new IllegalStateException(iOException);
      } 
    }
    
    public String shortDebugString(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      try {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        printField(param1FieldDescriptor, param1Object, TextFormat.singleLineOutput(stringBuilder));
        return stringBuilder.toString();
      } catch (IOException iOException) {
        throw new IllegalStateException(iOException);
      } 
    }
    
    public String shortDebugString(MessageOrBuilder param1MessageOrBuilder) {
      try {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        print(param1MessageOrBuilder, TextFormat.singleLineOutput(stringBuilder));
        return stringBuilder.toString();
      } catch (IOException iOException) {
        throw new IllegalStateException(iOException);
      } 
    }
    
    public String shortDebugString(UnknownFieldSet param1UnknownFieldSet) {
      try {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        printUnknownFields(param1UnknownFieldSet, TextFormat.singleLineOutput(stringBuilder));
        return stringBuilder.toString();
      } catch (IOException iOException) {
        throw new IllegalStateException(iOException);
      } 
    }
  }
  
  private static final class TextGenerator {
    private boolean atStartOfLine = false;
    
    private final StringBuilder indent = new StringBuilder();
    
    private final Appendable output;
    
    private final boolean singleLineMode;
    
    private TextGenerator(Appendable param1Appendable, boolean param1Boolean) {
      this.output = param1Appendable;
      this.singleLineMode = param1Boolean;
    }
    
    public void eol() throws IOException {
      if (!this.singleLineMode)
        this.output.append("\n"); 
      this.atStartOfLine = true;
    }
    
    public void indent() {
      this.indent.append("  ");
    }
    
    public void outdent() {
      int i = this.indent.length();
      if (i == 0)
        throw new IllegalArgumentException(" Outdent() without matching Indent()."); 
      this.indent.setLength(i - 2);
    }
    
    public void print(CharSequence param1CharSequence) throws IOException {
      if (this.atStartOfLine) {
        StringBuilder stringBuilder;
        this.atStartOfLine = false;
        Appendable appendable = this.output;
        if (this.singleLineMode) {
          String str = " ";
        } else {
          stringBuilder = this.indent;
        } 
        appendable.append(stringBuilder);
      } 
      this.output.append(param1CharSequence);
    }
  }
  
  private static final class Tokenizer {
    private static final Pattern DOUBLE_INFINITY;
    
    private static final Pattern FLOAT_INFINITY;
    
    private static final Pattern FLOAT_NAN;
    
    private static final Pattern TOKEN = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
    
    private static final Pattern WHITESPACE = Pattern.compile("(\\s|(#.*$))++", 8);
    
    private int column = 0;
    
    private String currentToken;
    
    private int line = 0;
    
    private final Matcher matcher;
    
    private int pos = 0;
    
    private int previousColumn = 0;
    
    private int previousLine = 0;
    
    private final CharSequence text;
    
    static {
      DOUBLE_INFINITY = Pattern.compile("-?inf(inity)?", 2);
      FLOAT_INFINITY = Pattern.compile("-?inf(inity)?f?", 2);
      FLOAT_NAN = Pattern.compile("nanf?", 2);
    }
    
    private Tokenizer(CharSequence param1CharSequence) {
      this.text = param1CharSequence;
      this.matcher = WHITESPACE.matcher(param1CharSequence);
      skipWhitespace();
      nextToken();
    }
    
    private void consumeByteString(List<ByteString> param1List) throws TextFormat.ParseException {
      int i = this.currentToken.length();
      char c = Character.MIN_VALUE;
      if (i > 0)
        c = this.currentToken.charAt(0); 
      if (c != '"' && c != '\'')
        throw parseException("Expected string."); 
      if (this.currentToken.length() < 2 || this.currentToken.charAt(this.currentToken.length() - 1) != c)
        throw parseException("String missing ending quote."); 
      try {
        ByteString byteString = TextFormat.unescapeBytes(this.currentToken.substring(1, this.currentToken.length() - 1));
        nextToken();
        param1List.add(byteString);
        return;
      } catch (InvalidEscapeSequenceException invalidEscapeSequenceException) {
        throw parseException(invalidEscapeSequenceException.getMessage());
      } 
    }
    
    private TextFormat.ParseException floatParseException(NumberFormatException param1NumberFormatException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Couldn't parse number: ");
      stringBuilder.append(param1NumberFormatException.getMessage());
      return parseException(stringBuilder.toString());
    }
    
    private TextFormat.ParseException integerParseException(NumberFormatException param1NumberFormatException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Couldn't parse integer: ");
      stringBuilder.append(param1NumberFormatException.getMessage());
      return parseException(stringBuilder.toString());
    }
    
    private void skipWhitespace() {
      this.matcher.usePattern(WHITESPACE);
      if (this.matcher.lookingAt())
        this.matcher.region(this.matcher.end(), this.matcher.regionEnd()); 
    }
    
    public boolean atEnd() {
      boolean bool;
      if (this.currentToken.length() == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void consume(String param1String) throws TextFormat.ParseException {
      if (!tryConsume(param1String)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected \"");
        stringBuilder.append(param1String);
        stringBuilder.append("\".");
        throw parseException(stringBuilder.toString());
      } 
    }
    
    public boolean consumeBoolean() throws TextFormat.ParseException {
      if (this.currentToken.equals("true") || this.currentToken.equals("True") || this.currentToken.equals("t") || this.currentToken.equals("1")) {
        nextToken();
        return true;
      } 
      if (this.currentToken.equals("false") || this.currentToken.equals("False") || this.currentToken.equals("f") || this.currentToken.equals("0")) {
        nextToken();
        return false;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Expected \"true\" or \"false\". Found \"");
      stringBuilder.append(this.currentToken);
      stringBuilder.append("\".");
      throw parseException(stringBuilder.toString());
    }
    
    public ByteString consumeByteString() throws TextFormat.ParseException {
      ArrayList<ByteString> arrayList = new ArrayList();
      consumeByteString(arrayList);
      while (true) {
        if (this.currentToken.startsWith("'") || this.currentToken.startsWith("\"")) {
          consumeByteString(arrayList);
          continue;
        } 
        return ByteString.copyFrom(arrayList);
      } 
    }
    
    public double consumeDouble() throws TextFormat.ParseException {
      if (DOUBLE_INFINITY.matcher(this.currentToken).matches()) {
        double d;
        boolean bool = this.currentToken.startsWith("-");
        nextToken();
        if (bool) {
          d = Double.NEGATIVE_INFINITY;
        } else {
          d = Double.POSITIVE_INFINITY;
        } 
        return d;
      } 
      if (this.currentToken.equalsIgnoreCase("nan")) {
        nextToken();
        return Double.NaN;
      } 
      try {
        double d = Double.parseDouble(this.currentToken);
        nextToken();
        return d;
      } catch (NumberFormatException numberFormatException) {
        throw floatParseException(numberFormatException);
      } 
    }
    
    public float consumeFloat() throws TextFormat.ParseException {
      if (FLOAT_INFINITY.matcher(this.currentToken).matches()) {
        float f;
        boolean bool = this.currentToken.startsWith("-");
        nextToken();
        if (bool) {
          f = Float.NEGATIVE_INFINITY;
        } else {
          f = Float.POSITIVE_INFINITY;
        } 
        return f;
      } 
      if (FLOAT_NAN.matcher(this.currentToken).matches()) {
        nextToken();
        return Float.NaN;
      } 
      try {
        float f = Float.parseFloat(this.currentToken);
        nextToken();
        return f;
      } catch (NumberFormatException numberFormatException) {
        throw floatParseException(numberFormatException);
      } 
    }
    
    public String consumeIdentifier() throws TextFormat.ParseException {
      byte b = 0;
      while (b < this.currentToken.length()) {
        char c = this.currentToken.charAt(b);
        if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9') || c == '_' || c == '.') {
          b++;
          continue;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected identifier. Found '");
        stringBuilder.append(this.currentToken);
        stringBuilder.append("'");
        throw parseException(stringBuilder.toString());
      } 
      String str = this.currentToken;
      nextToken();
      return str;
    }
    
    public int consumeInt32() throws TextFormat.ParseException {
      try {
        int i = TextFormat.parseInt32(this.currentToken);
        nextToken();
        return i;
      } catch (NumberFormatException numberFormatException) {
        throw integerParseException(numberFormatException);
      } 
    }
    
    public long consumeInt64() throws TextFormat.ParseException {
      try {
        long l = TextFormat.parseInt64(this.currentToken);
        nextToken();
        return l;
      } catch (NumberFormatException numberFormatException) {
        throw integerParseException(numberFormatException);
      } 
    }
    
    public String consumeString() throws TextFormat.ParseException {
      return consumeByteString().toStringUtf8();
    }
    
    public int consumeUInt32() throws TextFormat.ParseException {
      try {
        int i = TextFormat.parseUInt32(this.currentToken);
        nextToken();
        return i;
      } catch (NumberFormatException numberFormatException) {
        throw integerParseException(numberFormatException);
      } 
    }
    
    public long consumeUInt64() throws TextFormat.ParseException {
      try {
        long l = TextFormat.parseUInt64(this.currentToken);
        nextToken();
        return l;
      } catch (NumberFormatException numberFormatException) {
        throw integerParseException(numberFormatException);
      } 
    }
    
    int getColumn() {
      return this.column;
    }
    
    int getLine() {
      return this.line;
    }
    
    int getPreviousColumn() {
      return this.previousColumn;
    }
    
    int getPreviousLine() {
      return this.previousLine;
    }
    
    public boolean lookingAt(String param1String) {
      return this.currentToken.equals(param1String);
    }
    
    public boolean lookingAtInteger() {
      int i = this.currentToken.length();
      boolean bool = false;
      if (i == 0)
        return false; 
      i = this.currentToken.charAt(0);
      if ((48 <= i && i <= 57) || i == 45 || i == 43)
        bool = true; 
      return bool;
    }
    
    public void nextToken() {
      this.previousLine = this.line;
      this.previousColumn = this.column;
      while (this.pos < this.matcher.regionStart()) {
        if (this.text.charAt(this.pos) == '\n') {
          this.line++;
          this.column = 0;
        } else {
          this.column++;
        } 
        this.pos++;
      } 
      if (this.matcher.regionStart() == this.matcher.regionEnd()) {
        this.currentToken = "";
      } else {
        this.matcher.usePattern(TOKEN);
        if (this.matcher.lookingAt()) {
          this.currentToken = this.matcher.group();
          this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
        } else {
          this.currentToken = String.valueOf(this.text.charAt(this.pos));
          this.matcher.region(this.pos + 1, this.matcher.regionEnd());
        } 
        skipWhitespace();
      } 
    }
    
    public TextFormat.ParseException parseException(String param1String) {
      return new TextFormat.ParseException(this.line + 1, this.column + 1, param1String);
    }
    
    public TextFormat.ParseException parseExceptionPreviousToken(String param1String) {
      return new TextFormat.ParseException(this.previousLine + 1, this.previousColumn + 1, param1String);
    }
    
    public boolean tryConsume(String param1String) {
      if (this.currentToken.equals(param1String)) {
        nextToken();
        return true;
      } 
      return false;
    }
    
    public boolean tryConsumeDouble() {
      try {
        consumeDouble();
        return true;
      } catch (ParseException parseException) {
        return false;
      } 
    }
    
    public boolean tryConsumeFloat() {
      try {
        consumeFloat();
        return true;
      } catch (ParseException parseException) {
        return false;
      } 
    }
    
    public boolean tryConsumeIdentifier() {
      try {
        consumeIdentifier();
        return true;
      } catch (ParseException parseException) {
        return false;
      } 
    }
    
    public boolean tryConsumeInt64() {
      try {
        consumeInt64();
        return true;
      } catch (ParseException parseException) {
        return false;
      } 
    }
    
    public boolean tryConsumeString() {
      try {
        consumeString();
        return true;
      } catch (ParseException parseException) {
        return false;
      } 
    }
    
    public boolean tryConsumeUInt64() {
      try {
        consumeUInt64();
        return true;
      } catch (ParseException parseException) {
        return false;
      } 
    }
    
    public TextFormat.UnknownFieldParseException unknownFieldParseExceptionPreviousToken(String param1String1, String param1String2) {
      return new TextFormat.UnknownFieldParseException(this.previousLine + 1, this.previousColumn + 1, param1String1, param1String2);
    }
  }
  
  public static class UnknownFieldParseException extends ParseException {
    private final String unknownField;
    
    public UnknownFieldParseException(int param1Int1, int param1Int2, String param1String1, String param1String2) {
      super(param1Int1, param1Int2, param1String2);
      this.unknownField = param1String1;
    }
    
    public UnknownFieldParseException(String param1String) {
      this(-1, -1, "", param1String);
    }
    
    public String getUnknownField() {
      return this.unknownField;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\TextFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */