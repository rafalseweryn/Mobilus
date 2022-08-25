package com.google.protobuf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

abstract class ListFieldSchema {
  private static final ListFieldSchema FULL_INSTANCE = new ListFieldSchemaFull();
  
  private static final ListFieldSchema LITE_INSTANCE = new ListFieldSchemaLite();
  
  private ListFieldSchema() {}
  
  static ListFieldSchema full() {
    return FULL_INSTANCE;
  }
  
  static ListFieldSchema lite() {
    return LITE_INSTANCE;
  }
  
  abstract void makeImmutableListAt(Object paramObject, long paramLong);
  
  abstract <L> void mergeListsAt(Object paramObject1, Object paramObject2, long paramLong);
  
  abstract <L> List<L> mutableListAt(Object paramObject, long paramLong);
  
  private static final class ListFieldSchemaFull extends ListFieldSchema {
    private static final Class<?> UNMODIFIABLE_LIST_CLASS = Collections.unmodifiableList(Collections.emptyList()).getClass();
    
    private ListFieldSchemaFull() {}
    
    static <E> List<E> getList(Object param1Object, long param1Long) {
      return (List<E>)UnsafeUtil.getObject(param1Object, param1Long);
    }
    
    private static <L> List<L> mutableListAt(Object<?> param1Object, long param1Long, int param1Int) {
      Object<?> object;
      List<?> list = getList(param1Object, param1Long);
      if (list.isEmpty()) {
        ArrayList arrayList;
        if (list instanceof LazyStringList) {
          LazyStringArrayList lazyStringArrayList = new LazyStringArrayList(param1Int);
        } else if (list instanceof PrimitiveNonBoxingCollection && list instanceof Internal.ProtobufList) {
          Internal.ProtobufList protobufList = ((Internal.ProtobufList)list).mutableCopyWithCapacity(param1Int);
        } else {
          arrayList = new ArrayList(param1Int);
        } 
        UnsafeUtil.putObject(param1Object, param1Long, arrayList);
      } else {
        if (UNMODIFIABLE_LIST_CLASS.isAssignableFrom(list.getClass())) {
          ArrayList<?> arrayList = new ArrayList(list.size() + param1Int);
          arrayList.addAll(list);
          UnsafeUtil.putObject(param1Object, param1Long, arrayList);
          param1Object = (Object<?>)arrayList;
        } else if (list instanceof UnmodifiableLazyStringList) {
          LazyStringArrayList lazyStringArrayList = new LazyStringArrayList(list.size() + param1Int);
          lazyStringArrayList.addAll((UnmodifiableLazyStringList)list);
          UnsafeUtil.putObject(param1Object, param1Long, lazyStringArrayList);
          param1Object = (Object<?>)lazyStringArrayList;
        } else {
          List<?> list1 = list;
          if (list instanceof PrimitiveNonBoxingCollection) {
            list1 = list;
            if (list instanceof Internal.ProtobufList) {
              Internal.ProtobufList<?> protobufList = (Internal.ProtobufList)list;
              list1 = list;
              if (!protobufList.isModifiable()) {
                list1 = protobufList.mutableCopyWithCapacity(list.size() + param1Int);
                UnsafeUtil.putObject(param1Object, param1Long, list1);
              } 
            } 
          } 
          return (List)list1;
        } 
        object = param1Object;
      } 
      return (List)object;
    }
    
    void makeImmutableListAt(Object param1Object, long param1Long) {
      List<?> list = (List)UnsafeUtil.getObject(param1Object, param1Long);
      if (list instanceof LazyStringList) {
        list = ((LazyStringList)list).getUnmodifiableView();
      } else {
        if (UNMODIFIABLE_LIST_CLASS.isAssignableFrom(list.getClass()))
          return; 
        if (list instanceof PrimitiveNonBoxingCollection && list instanceof Internal.ProtobufList) {
          param1Object = list;
          if (param1Object.isModifiable())
            param1Object.makeImmutable(); 
          return;
        } 
        list = Collections.unmodifiableList(list);
      } 
      UnsafeUtil.putObject(param1Object, param1Long, list);
    }
    
    <E> void mergeListsAt(Object param1Object1, Object<?> param1Object2, long param1Long) {
      param1Object2 = getList(param1Object2, param1Long);
      List<?> list = mutableListAt(param1Object1, param1Long, param1Object2.size());
      int i = list.size();
      int j = param1Object2.size();
      if (i > 0 && j > 0)
        list.addAll((Collection<?>)param1Object2); 
      if (i > 0)
        param1Object2 = (Object<?>)list; 
      UnsafeUtil.putObject(param1Object1, param1Long, param1Object2);
    }
    
    <L> List<L> mutableListAt(Object param1Object, long param1Long) {
      return mutableListAt(param1Object, param1Long, 10);
    }
  }
  
  private static final class ListFieldSchemaLite extends ListFieldSchema {
    private ListFieldSchemaLite() {}
    
    static <E> Internal.ProtobufList<E> getProtobufList(Object param1Object, long param1Long) {
      return (Internal.ProtobufList<E>)UnsafeUtil.getObject(param1Object, param1Long);
    }
    
    void makeImmutableListAt(Object param1Object, long param1Long) {
      getProtobufList(param1Object, param1Long).makeImmutable();
    }
    
    <E> void mergeListsAt(Object param1Object1, Object<?> param1Object2, long param1Long) {
      Internal.ProtobufList<?> protobufList1 = getProtobufList(param1Object1, param1Long);
      Internal.ProtobufList<?> protobufList2 = getProtobufList(param1Object2, param1Long);
      int i = protobufList1.size();
      int j = protobufList2.size();
      param1Object2 = (Object<?>)protobufList1;
      if (i > 0) {
        param1Object2 = (Object<?>)protobufList1;
        if (j > 0) {
          param1Object2 = (Object<?>)protobufList1;
          if (!protobufList1.isModifiable())
            param1Object2 = (Object<?>)protobufList1.mutableCopyWithCapacity(j + i); 
          param1Object2.addAll(protobufList2);
        } 
      } 
      if (i <= 0)
        param1Object2 = (Object<?>)protobufList2; 
      UnsafeUtil.putObject(param1Object1, param1Long, param1Object2);
    }
    
    <L> List<L> mutableListAt(Object param1Object, long param1Long) {
      Internal.ProtobufList<?> protobufList1 = getProtobufList(param1Object, param1Long);
      Internal.ProtobufList<?> protobufList2 = protobufList1;
      if (!protobufList1.isModifiable()) {
        int i = protobufList1.size();
        if (i == 0) {
          i = 10;
        } else {
          i *= 2;
        } 
        protobufList2 = protobufList1.mutableCopyWithCapacity(i);
        UnsafeUtil.putObject(param1Object, param1Long, protobufList2);
      } 
      return (List)protobufList2;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\ListFieldSchema.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */