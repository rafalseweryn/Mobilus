package com.google.protobuf;

public final class DiscardUnknownFieldsParser {
  public static final <T extends Message> Parser<T> wrap(final Parser<T> parser) {
    return new AbstractParser<T>() {
        public T parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
          try {
            param1CodedInputStream.discardUnknownFields();
            return (T)parser.parsePartialFrom(param1CodedInputStream, param1ExtensionRegistryLite);
          } finally {
            param1CodedInputStream.unsetDiscardUnknownFields();
          } 
        }
      };
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\DiscardUnknownFieldsParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */