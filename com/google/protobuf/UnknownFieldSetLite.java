package com.google.protobuf;

import java.io.IOException;
import java.util.Arrays;

public final class UnknownFieldSetLite {
  private static final UnknownFieldSetLite DEFAULT_INSTANCE = new UnknownFieldSetLite(0, new int[0], new Object[0], false);
  
  private static final int MIN_CAPACITY = 8;
  
  private int count;
  
  private boolean isMutable;
  
  private int memoizedSerializedSize = -1;
  
  private Object[] objects;
  
  private int[] tags;
  
  private UnknownFieldSetLite() {
    this(0, new int[8], new Object[8], true);
  }
  
  private UnknownFieldSetLite(int paramInt, int[] paramArrayOfint, Object[] paramArrayOfObject, boolean paramBoolean) {
    this.count = paramInt;
    this.tags = paramArrayOfint;
    this.objects = paramArrayOfObject;
    this.isMutable = paramBoolean;
  }
  
  private void ensureCapacity() {
    if (this.count == this.tags.length) {
      if (this.count < 4) {
        i = 8;
      } else {
        i = this.count >> 1;
      } 
      int i = this.count + i;
      this.tags = Arrays.copyOf(this.tags, i);
      this.objects = Arrays.copyOf(this.objects, i);
    } 
  }
  
  private static boolean equals(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt) {
    for (byte b = 0; b < paramInt; b++) {
      if (paramArrayOfint1[b] != paramArrayOfint2[b])
        return false; 
    } 
    return true;
  }
  
  private static boolean equals(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt) {
    for (byte b = 0; b < paramInt; b++) {
      if (!paramArrayOfObject1[b].equals(paramArrayOfObject2[b]))
        return false; 
    } 
    return true;
  }
  
  public static UnknownFieldSetLite getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static int hashCode(int[] paramArrayOfint, int paramInt) {
    int i = 17;
    for (byte b = 0; b < paramInt; b++)
      i = i * 31 + paramArrayOfint[b]; 
    return i;
  }
  
  private static int hashCode(Object[] paramArrayOfObject, int paramInt) {
    int i = 17;
    for (byte b = 0; b < paramInt; b++)
      i = i * 31 + paramArrayOfObject[b].hashCode(); 
    return i;
  }
  
  private UnknownFieldSetLite mergeFrom(CodedInputStream paramCodedInputStream) throws IOException {
    int i;
    do {
      i = paramCodedInputStream.readTag();
    } while (i != 0 && mergeFieldFrom(i, paramCodedInputStream));
    return this;
  }
  
  static UnknownFieldSetLite mutableCopyOf(UnknownFieldSetLite paramUnknownFieldSetLite1, UnknownFieldSetLite paramUnknownFieldSetLite2) {
    int i = paramUnknownFieldSetLite1.count + paramUnknownFieldSetLite2.count;
    int[] arrayOfInt = Arrays.copyOf(paramUnknownFieldSetLite1.tags, i);
    System.arraycopy(paramUnknownFieldSetLite2.tags, 0, arrayOfInt, paramUnknownFieldSetLite1.count, paramUnknownFieldSetLite2.count);
    Object[] arrayOfObject = Arrays.copyOf(paramUnknownFieldSetLite1.objects, i);
    System.arraycopy(paramUnknownFieldSetLite2.objects, 0, arrayOfObject, paramUnknownFieldSetLite1.count, paramUnknownFieldSetLite2.count);
    return new UnknownFieldSetLite(i, arrayOfInt, arrayOfObject, true);
  }
  
  static UnknownFieldSetLite newInstance() {
    return new UnknownFieldSetLite();
  }
  
  private static void writeField(int paramInt, Object paramObject, Writer paramWriter) throws IOException {
    int i = WireFormat.getTagFieldNumber(paramInt);
    paramInt = WireFormat.getTagWireType(paramInt);
    if (paramInt != 5) {
      switch (paramInt) {
        default:
          throw new RuntimeException(InvalidProtocolBufferException.invalidWireType());
        case 3:
          if (paramWriter.fieldOrder() == Writer.FieldOrder.ASCENDING) {
            paramWriter.writeStartGroup(i);
            ((UnknownFieldSetLite)paramObject).writeTo(paramWriter);
            paramWriter.writeEndGroup(i);
          } else {
            paramWriter.writeEndGroup(i);
            ((UnknownFieldSetLite)paramObject).writeTo(paramWriter);
            paramWriter.writeStartGroup(i);
          } 
          return;
        case 2:
          paramWriter.writeBytes(i, (ByteString)paramObject);
          return;
        case 1:
          paramWriter.writeFixed64(i, ((Long)paramObject).longValue());
          return;
        case 0:
          break;
      } 
      paramWriter.writeInt64(i, ((Long)paramObject).longValue());
    } else {
      paramWriter.writeFixed32(i, ((Integer)paramObject).intValue());
    } 
  }
  
  void checkMutable() {
    if (!this.isMutable)
      throw new UnsupportedOperationException(); 
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (!(paramObject instanceof UnknownFieldSetLite))
      return false; 
    paramObject = paramObject;
    return !(this.count != ((UnknownFieldSetLite)paramObject).count || !equals(this.tags, ((UnknownFieldSetLite)paramObject).tags, this.count) || !equals(this.objects, ((UnknownFieldSetLite)paramObject).objects, this.count));
  }
  
  public int getSerializedSize() {
    int i = this.memoizedSerializedSize;
    if (i != -1)
      return i; 
    byte b = 0;
    i = 0;
    while (b < this.count) {
      int j = this.tags[b];
      int k = WireFormat.getTagFieldNumber(j);
      j = WireFormat.getTagWireType(j);
      if (j != 5) {
        switch (j) {
          default:
            throw new IllegalStateException(InvalidProtocolBufferException.invalidWireType());
          case 3:
            i += CodedOutputStream.computeTagSize(k) * 2 + ((UnknownFieldSetLite)this.objects[b]).getSerializedSize();
            break;
          case 2:
            i += CodedOutputStream.computeBytesSize(k, (ByteString)this.objects[b]);
            break;
          case 1:
            i += CodedOutputStream.computeFixed64Size(k, ((Long)this.objects[b]).longValue());
            break;
          case 0:
            i += CodedOutputStream.computeUInt64Size(k, ((Long)this.objects[b]).longValue());
            break;
        } 
      } else {
        i += CodedOutputStream.computeFixed32Size(k, ((Integer)this.objects[b]).intValue());
      } 
      b++;
    } 
    this.memoizedSerializedSize = i;
    return i;
  }
  
  public int getSerializedSizeAsMessageSet() {
    int i = this.memoizedSerializedSize;
    if (i != -1)
      return i; 
    byte b = 0;
    i = 0;
    while (b < this.count) {
      i += CodedOutputStream.computeRawMessageSetExtensionSize(WireFormat.getTagFieldNumber(this.tags[b]), (ByteString)this.objects[b]);
      b++;
    } 
    this.memoizedSerializedSize = i;
    return i;
  }
  
  public int hashCode() {
    return ((527 + this.count) * 31 + hashCode(this.tags, this.count)) * 31 + hashCode(this.objects, this.count);
  }
  
  public void makeImmutable() {
    this.isMutable = false;
  }
  
  boolean mergeFieldFrom(int paramInt, CodedInputStream paramCodedInputStream) throws IOException {
    UnknownFieldSetLite unknownFieldSetLite;
    checkMutable();
    int i = WireFormat.getTagFieldNumber(paramInt);
    switch (WireFormat.getTagWireType(paramInt)) {
      default:
        throw InvalidProtocolBufferException.invalidWireType();
      case 5:
        storeField(paramInt, Integer.valueOf(paramCodedInputStream.readFixed32()));
        return true;
      case 4:
        return false;
      case 3:
        unknownFieldSetLite = new UnknownFieldSetLite();
        unknownFieldSetLite.mergeFrom(paramCodedInputStream);
        paramCodedInputStream.checkLastTagWas(WireFormat.makeTag(i, 4));
        storeField(paramInt, unknownFieldSetLite);
        return true;
      case 2:
        storeField(paramInt, paramCodedInputStream.readBytes());
        return true;
      case 1:
        storeField(paramInt, Long.valueOf(paramCodedInputStream.readFixed64()));
        return true;
      case 0:
        break;
    } 
    storeField(paramInt, Long.valueOf(paramCodedInputStream.readInt64()));
    return true;
  }
  
  UnknownFieldSetLite mergeLengthDelimitedField(int paramInt, ByteString paramByteString) {
    checkMutable();
    if (paramInt == 0)
      throw new IllegalArgumentException("Zero is not a valid field number."); 
    storeField(WireFormat.makeTag(paramInt, 2), paramByteString);
    return this;
  }
  
  UnknownFieldSetLite mergeVarintField(int paramInt1, int paramInt2) {
    checkMutable();
    if (paramInt1 == 0)
      throw new IllegalArgumentException("Zero is not a valid field number."); 
    storeField(WireFormat.makeTag(paramInt1, 0), Long.valueOf(paramInt2));
    return this;
  }
  
  final void printWithIndent(StringBuilder paramStringBuilder, int paramInt) {
    for (byte b = 0; b < this.count; b++)
      MessageLiteToString.printField(paramStringBuilder, paramInt, String.valueOf(WireFormat.getTagFieldNumber(this.tags[b])), this.objects[b]); 
  }
  
  void storeField(int paramInt, Object paramObject) {
    checkMutable();
    ensureCapacity();
    this.tags[this.count] = paramInt;
    this.objects[this.count] = paramObject;
    this.count++;
  }
  
  public void writeAsMessageSetTo(CodedOutputStream paramCodedOutputStream) throws IOException {
    for (byte b = 0; b < this.count; b++)
      paramCodedOutputStream.writeRawMessageSetExtension(WireFormat.getTagFieldNumber(this.tags[b]), (ByteString)this.objects[b]); 
  }
  
  void writeAsMessageSetTo(Writer paramWriter) throws IOException {
    if (paramWriter.fieldOrder() == Writer.FieldOrder.DESCENDING) {
      for (int i = this.count - 1; i >= 0; i--)
        paramWriter.writeMessageSetItem(WireFormat.getTagFieldNumber(this.tags[i]), this.objects[i]); 
    } else {
      for (byte b = 0; b < this.count; b++)
        paramWriter.writeMessageSetItem(WireFormat.getTagFieldNumber(this.tags[b]), this.objects[b]); 
    } 
  }
  
  public void writeTo(CodedOutputStream paramCodedOutputStream) throws IOException {
    for (byte b = 0; b < this.count; b++) {
      int i = this.tags[b];
      int j = WireFormat.getTagFieldNumber(i);
      i = WireFormat.getTagWireType(i);
      if (i != 5) {
        switch (i) {
          default:
            throw InvalidProtocolBufferException.invalidWireType();
          case 3:
            paramCodedOutputStream.writeTag(j, 3);
            ((UnknownFieldSetLite)this.objects[b]).writeTo(paramCodedOutputStream);
            paramCodedOutputStream.writeTag(j, 4);
            break;
          case 2:
            paramCodedOutputStream.writeBytes(j, (ByteString)this.objects[b]);
            break;
          case 1:
            paramCodedOutputStream.writeFixed64(j, ((Long)this.objects[b]).longValue());
            break;
          case 0:
            paramCodedOutputStream.writeUInt64(j, ((Long)this.objects[b]).longValue());
            break;
        } 
      } else {
        paramCodedOutputStream.writeFixed32(j, ((Integer)this.objects[b]).intValue());
      } 
    } 
  }
  
  public void writeTo(Writer paramWriter) throws IOException {
    if (this.count == 0)
      return; 
    if (paramWriter.fieldOrder() == Writer.FieldOrder.ASCENDING) {
      for (byte b = 0; b < this.count; b++)
        writeField(this.tags[b], this.objects[b], paramWriter); 
    } else {
      for (int i = this.count - 1; i >= 0; i--)
        writeField(this.tags[i], this.objects[i], paramWriter); 
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\UnknownFieldSetLite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */