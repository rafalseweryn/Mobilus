package com.google.protobuf;

import java.util.Collection;
import java.util.List;

public interface LazyStringList extends ProtocolStringList {
  void add(ByteString paramByteString);
  
  void add(byte[] paramArrayOfbyte);
  
  boolean addAllByteArray(Collection<byte[]> paramCollection);
  
  boolean addAllByteString(Collection<? extends ByteString> paramCollection);
  
  List<byte[]> asByteArrayList();
  
  byte[] getByteArray(int paramInt);
  
  ByteString getByteString(int paramInt);
  
  Object getRaw(int paramInt);
  
  List<?> getUnderlyingElements();
  
  LazyStringList getUnmodifiableView();
  
  void mergeFrom(LazyStringList paramLazyStringList);
  
  void set(int paramInt, ByteString paramByteString);
  
  void set(int paramInt, byte[] paramArrayOfbyte);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\LazyStringList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */