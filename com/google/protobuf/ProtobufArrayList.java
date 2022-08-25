package com.google.protobuf;

import java.util.ArrayList;
import java.util.List;

final class ProtobufArrayList<E> extends AbstractProtobufList<E> {
  private static final ProtobufArrayList<Object> EMPTY_LIST = new ProtobufArrayList(new ArrayList<>(0));
  
  private final List<E> list;
  
  static {
    EMPTY_LIST.makeImmutable();
  }
  
  ProtobufArrayList() {
    this(new ArrayList<>(10));
  }
  
  private ProtobufArrayList(List<E> paramList) {
    this.list = paramList;
  }
  
  public static <E> ProtobufArrayList<E> emptyList() {
    return (ProtobufArrayList)EMPTY_LIST;
  }
  
  public void add(int paramInt, E paramE) {
    ensureIsMutable();
    this.list.add(paramInt, paramE);
    this.modCount++;
  }
  
  public E get(int paramInt) {
    return this.list.get(paramInt);
  }
  
  public ProtobufArrayList<E> mutableCopyWithCapacity(int paramInt) {
    if (paramInt < size())
      throw new IllegalArgumentException(); 
    ArrayList<E> arrayList = new ArrayList(paramInt);
    arrayList.addAll(this.list);
    return new ProtobufArrayList(arrayList);
  }
  
  public E remove(int paramInt) {
    ensureIsMutable();
    E e = this.list.remove(paramInt);
    this.modCount++;
    return e;
  }
  
  public E set(int paramInt, E paramE) {
    ensureIsMutable();
    paramE = this.list.set(paramInt, paramE);
    this.modCount++;
    return paramE;
  }
  
  public int size() {
    return this.list.size();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\ProtobufArrayList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */