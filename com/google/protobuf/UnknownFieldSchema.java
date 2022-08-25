package com.google.protobuf;

import java.io.IOException;

abstract class UnknownFieldSchema<T, B> {
  abstract void addFixed32(B paramB, int paramInt1, int paramInt2);
  
  abstract void addFixed64(B paramB, int paramInt, long paramLong);
  
  abstract void addGroup(B paramB, int paramInt, T paramT);
  
  abstract void addLengthDelimited(B paramB, int paramInt, ByteString paramByteString);
  
  abstract void addVarint(B paramB, int paramInt, long paramLong);
  
  abstract B getBuilderFromMessage(Object paramObject);
  
  abstract T getFromMessage(Object paramObject);
  
  abstract int getSerializedSize(T paramT);
  
  abstract int getSerializedSizeAsMessageSet(T paramT);
  
  abstract void makeImmutable(Object paramObject);
  
  abstract T merge(T paramT1, T paramT2);
  
  final void mergeFrom(B paramB, Reader paramReader) throws IOException {
    do {
    
    } while (paramReader.getFieldNumber() != Integer.MAX_VALUE && mergeOneFieldFrom(paramB, paramReader));
  }
  
  final boolean mergeOneFieldFrom(B paramB, Reader paramReader) throws IOException {
    B b;
    int i = paramReader.getTag();
    int j = WireFormat.getTagFieldNumber(i);
    switch (WireFormat.getTagWireType(i)) {
      default:
        throw InvalidProtocolBufferException.invalidWireType();
      case 5:
        addFixed32(paramB, j, paramReader.readFixed32());
        return true;
      case 4:
        return false;
      case 3:
        b = newBuilder();
        i = WireFormat.makeTag(j, 4);
        mergeFrom(b, paramReader);
        if (i != paramReader.getTag())
          throw InvalidProtocolBufferException.invalidEndTag(); 
        addGroup(paramB, j, toImmutable(b));
        return true;
      case 2:
        addLengthDelimited(paramB, j, paramReader.readBytes());
        return true;
      case 1:
        addFixed64(paramB, j, paramReader.readFixed64());
        return true;
      case 0:
        break;
    } 
    addVarint(paramB, j, paramReader.readInt64());
    return true;
  }
  
  abstract B newBuilder();
  
  abstract void setBuilderToMessage(Object paramObject, B paramB);
  
  abstract void setToMessage(Object paramObject, T paramT);
  
  abstract boolean shouldDiscardUnknownFields(Reader paramReader);
  
  abstract T toImmutable(B paramB);
  
  abstract void writeAsMessageSetTo(T paramT, Writer paramWriter) throws IOException;
  
  abstract void writeTo(T paramT, Writer paramWriter) throws IOException;
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\UnknownFieldSchema.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */