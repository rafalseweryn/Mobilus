package com.google.protobuf;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;

public final class Internal {
  private static final int DEFAULT_BUFFER_SIZE = 4096;
  
  public static final byte[] EMPTY_BYTE_ARRAY;
  
  public static final ByteBuffer EMPTY_BYTE_BUFFER;
  
  public static final CodedInputStream EMPTY_CODED_INPUT_STREAM;
  
  static final Charset ISO_8859_1;
  
  static final Charset UTF_8 = Charset.forName("UTF-8");
  
  static {
    ISO_8859_1 = Charset.forName("ISO-8859-1");
    EMPTY_BYTE_ARRAY = new byte[0];
    EMPTY_BYTE_BUFFER = ByteBuffer.wrap(EMPTY_BYTE_ARRAY);
    EMPTY_CODED_INPUT_STREAM = CodedInputStream.newInstance(EMPTY_BYTE_ARRAY);
  }
  
  public static byte[] byteArrayDefaultValue(String paramString) {
    return paramString.getBytes(ISO_8859_1);
  }
  
  public static ByteBuffer byteBufferDefaultValue(String paramString) {
    return ByteBuffer.wrap(byteArrayDefaultValue(paramString));
  }
  
  public static ByteString bytesDefaultValue(String paramString) {
    return ByteString.copyFrom(paramString.getBytes(ISO_8859_1));
  }
  
  static <T> T checkNotNull(T paramT) {
    if (paramT == null)
      throw new NullPointerException(); 
    return paramT;
  }
  
  static <T> T checkNotNull(T paramT, String paramString) {
    if (paramT == null)
      throw new NullPointerException(paramString); 
    return paramT;
  }
  
  public static ByteBuffer copyByteBuffer(ByteBuffer paramByteBuffer) {
    paramByteBuffer = paramByteBuffer.duplicate();
    paramByteBuffer.clear();
    ByteBuffer byteBuffer = ByteBuffer.allocate(paramByteBuffer.capacity());
    byteBuffer.put(paramByteBuffer);
    byteBuffer.clear();
    return byteBuffer;
  }
  
  public static boolean equals(List<byte[]> paramList1, List<byte[]> paramList2) {
    if (paramList1.size() != paramList2.size())
      return false; 
    for (byte b = 0; b < paramList1.size(); b++) {
      if (!Arrays.equals(paramList1.get(b), paramList2.get(b)))
        return false; 
    } 
    return true;
  }
  
  public static boolean equalsByteBuffer(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2) {
    return (paramByteBuffer1.capacity() != paramByteBuffer2.capacity()) ? false : paramByteBuffer1.duplicate().clear().equals(paramByteBuffer2.duplicate().clear());
  }
  
  public static boolean equalsByteBuffer(List<ByteBuffer> paramList1, List<ByteBuffer> paramList2) {
    if (paramList1.size() != paramList2.size())
      return false; 
    for (byte b = 0; b < paramList1.size(); b++) {
      if (!equalsByteBuffer(paramList1.get(b), paramList2.get(b)))
        return false; 
    } 
    return true;
  }
  
  public static <T extends MessageLite> T getDefaultInstance(Class<T> paramClass) {
    try {
      Method method = paramClass.getMethod("getDefaultInstance", new Class[0]);
      return (T)method.invoke(method, new Object[0]);
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Failed to get default instance for ");
      stringBuilder.append(paramClass);
      throw new RuntimeException(stringBuilder.toString(), exception);
    } 
  }
  
  public static int hashBoolean(boolean paramBoolean) {
    char c;
    if (paramBoolean) {
      c = 'ӏ';
    } else {
      c = 'ӕ';
    } 
    return c;
  }
  
  public static int hashCode(List<byte[]> paramList) {
    Iterator<byte> iterator = paramList.iterator();
    int i;
    for (i = 1; iterator.hasNext(); i = i * 31 + hashCode((byte[])iterator.next()));
    return i;
  }
  
  public static int hashCode(byte[] paramArrayOfbyte) {
    return hashCode(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  static int hashCode(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    paramInt2 = partialHash(paramInt2, paramArrayOfbyte, paramInt1, paramInt2);
    paramInt1 = paramInt2;
    if (paramInt2 == 0)
      paramInt1 = 1; 
    return paramInt1;
  }
  
  public static int hashCodeByteBuffer(ByteBuffer paramByteBuffer) {
    if (paramByteBuffer.hasArray()) {
      int k = partialHash(paramByteBuffer.capacity(), paramByteBuffer.array(), paramByteBuffer.arrayOffset(), paramByteBuffer.capacity());
      int m = k;
      if (k == 0)
        m = 1; 
      return m;
    } 
    int i = paramByteBuffer.capacity();
    int j = 4096;
    if (i <= 4096)
      j = paramByteBuffer.capacity(); 
    byte[] arrayOfByte = new byte[j];
    ByteBuffer byteBuffer = paramByteBuffer.duplicate();
    byteBuffer.clear();
    for (i = paramByteBuffer.capacity(); byteBuffer.remaining() > 0; i = partialHash(i, arrayOfByte, 0, k)) {
      int k;
      if (byteBuffer.remaining() <= j) {
        k = byteBuffer.remaining();
      } else {
        k = j;
      } 
      byteBuffer.get(arrayOfByte, 0, k);
    } 
    j = i;
    if (i == 0)
      j = 1; 
    return j;
  }
  
  public static int hashCodeByteBuffer(List<ByteBuffer> paramList) {
    Iterator<ByteBuffer> iterator = paramList.iterator();
    int i;
    for (i = 1; iterator.hasNext(); i = i * 31 + hashCodeByteBuffer(iterator.next()));
    return i;
  }
  
  public static int hashEnum(EnumLite paramEnumLite) {
    return paramEnumLite.getNumber();
  }
  
  public static int hashEnumList(List<? extends EnumLite> paramList) {
    Iterator<? extends EnumLite> iterator = paramList.iterator();
    int i;
    for (i = 1; iterator.hasNext(); i = i * 31 + hashEnum(iterator.next()));
    return i;
  }
  
  public static int hashLong(long paramLong) {
    return (int)(paramLong ^ paramLong >>> 32L);
  }
  
  public static boolean isValidUtf8(ByteString paramByteString) {
    return paramByteString.isValidUtf8();
  }
  
  public static boolean isValidUtf8(byte[] paramArrayOfbyte) {
    return Utf8.isValidUtf8(paramArrayOfbyte);
  }
  
  static Object mergeMessage(Object paramObject1, Object paramObject2) {
    return ((MessageLite)paramObject1).toBuilder().mergeFrom((MessageLite)paramObject2).buildPartial();
  }
  
  static int partialHash(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3) {
    int i = paramInt1;
    for (paramInt1 = paramInt2; paramInt1 < paramInt2 + paramInt3; paramInt1++)
      i = i * 31 + paramArrayOfbyte[paramInt1]; 
    return i;
  }
  
  public static String stringDefaultValue(String paramString) {
    return new String(paramString.getBytes(ISO_8859_1), UTF_8);
  }
  
  public static byte[] toByteArray(String paramString) {
    return paramString.getBytes(UTF_8);
  }
  
  public static String toStringUtf8(byte[] paramArrayOfbyte) {
    return new String(paramArrayOfbyte, UTF_8);
  }
  
  public static interface BooleanList extends ProtobufList<Boolean> {
    void addBoolean(boolean param1Boolean);
    
    boolean getBoolean(int param1Int);
    
    BooleanList mutableCopyWithCapacity(int param1Int);
    
    boolean setBoolean(int param1Int, boolean param1Boolean);
  }
  
  public static interface DoubleList extends ProtobufList<Double> {
    void addDouble(double param1Double);
    
    double getDouble(int param1Int);
    
    DoubleList mutableCopyWithCapacity(int param1Int);
    
    double setDouble(int param1Int, double param1Double);
  }
  
  public static interface EnumLite {
    int getNumber();
  }
  
  public static interface EnumLiteMap<T extends EnumLite> {
    T findValueByNumber(int param1Int);
  }
  
  public static interface EnumVerifier {
    boolean isInRange(int param1Int);
  }
  
  public static interface FloatList extends ProtobufList<Float> {
    void addFloat(float param1Float);
    
    float getFloat(int param1Int);
    
    FloatList mutableCopyWithCapacity(int param1Int);
    
    float setFloat(int param1Int, float param1Float);
  }
  
  public static interface IntList extends ProtobufList<Integer> {
    void addInt(int param1Int);
    
    int getInt(int param1Int);
    
    IntList mutableCopyWithCapacity(int param1Int);
    
    int setInt(int param1Int1, int param1Int2);
  }
  
  public static class ListAdapter<F, T> extends AbstractList<T> {
    private final Converter<F, T> converter;
    
    private final List<F> fromList;
    
    public ListAdapter(List<F> param1List, Converter<F, T> param1Converter) {
      this.fromList = param1List;
      this.converter = param1Converter;
    }
    
    public T get(int param1Int) {
      return this.converter.convert(this.fromList.get(param1Int));
    }
    
    public int size() {
      return this.fromList.size();
    }
    
    public static interface Converter<F, T> {
      T convert(F param2F);
    }
  }
  
  public static interface Converter<F, T> {
    T convert(F param1F);
  }
  
  public static interface LongList extends ProtobufList<Long> {
    void addLong(long param1Long);
    
    long getLong(int param1Int);
    
    LongList mutableCopyWithCapacity(int param1Int);
    
    long setLong(int param1Int, long param1Long);
  }
  
  public static class MapAdapter<K, V, RealValue> extends AbstractMap<K, V> {
    private final Map<K, RealValue> realMap;
    
    private final Converter<RealValue, V> valueConverter;
    
    public MapAdapter(Map<K, RealValue> param1Map, Converter<RealValue, V> param1Converter) {
      this.realMap = param1Map;
      this.valueConverter = param1Converter;
    }
    
    public static <T extends Internal.EnumLite> Converter<Integer, T> newEnumConverter(final Internal.EnumLiteMap<T> enumMap, final T unrecognizedValue) {
      return new Converter<Integer, T>() {
          public Integer doBackward(T param2T) {
            return Integer.valueOf(param2T.getNumber());
          }
          
          public T doForward(Integer param2Integer) {
            Internal.EnumLite enumLite;
            Integer integer = (Integer)enumMap.findValueByNumber(param2Integer.intValue());
            param2Integer = integer;
            if (integer == null)
              enumLite = unrecognizedValue; 
            return (T)enumLite;
          }
        };
    }
    
    public Set<Map.Entry<K, V>> entrySet() {
      return new SetAdapter(this.realMap.entrySet());
    }
    
    public V get(Object param1Object) {
      param1Object = this.realMap.get(param1Object);
      return (param1Object == null) ? null : this.valueConverter.doForward((RealValue)param1Object);
    }
    
    public V put(K param1K, V param1V) {
      param1K = (K)this.realMap.put(param1K, this.valueConverter.doBackward(param1V));
      return (param1K == null) ? null : this.valueConverter.doForward((RealValue)param1K);
    }
    
    public static interface Converter<A, B> {
      A doBackward(B param2B);
      
      B doForward(A param2A);
    }
    
    private class EntryAdapter implements Map.Entry<K, V> {
      private final Map.Entry<K, RealValue> realEntry;
      
      public EntryAdapter(Map.Entry<K, RealValue> param2Entry) {
        this.realEntry = param2Entry;
      }
      
      public boolean equals(Object param2Object) {
        boolean bool = true;
        if (param2Object == this)
          return true; 
        if (!(param2Object instanceof Map.Entry))
          return false; 
        param2Object = param2Object;
        if (!getKey().equals(param2Object.getKey()) || !getValue().equals(getValue()))
          bool = false; 
        return bool;
      }
      
      public K getKey() {
        return this.realEntry.getKey();
      }
      
      public V getValue() {
        return (V)Internal.MapAdapter.this.valueConverter.doForward(this.realEntry.getValue());
      }
      
      public int hashCode() {
        return this.realEntry.hashCode();
      }
      
      public V setValue(V param2V) {
        param2V = (V)this.realEntry.setValue((RealValue)Internal.MapAdapter.this.valueConverter.doBackward(param2V));
        return (param2V == null) ? null : (V)Internal.MapAdapter.this.valueConverter.doForward(param2V);
      }
    }
    
    private class IteratorAdapter implements Iterator<Map.Entry<K, V>> {
      private final Iterator<Map.Entry<K, RealValue>> realIterator;
      
      public IteratorAdapter(Iterator<Map.Entry<K, RealValue>> param2Iterator) {
        this.realIterator = param2Iterator;
      }
      
      public boolean hasNext() {
        return this.realIterator.hasNext();
      }
      
      public Map.Entry<K, V> next() {
        return new Internal.MapAdapter.EntryAdapter(this.realIterator.next());
      }
      
      public void remove() {
        this.realIterator.remove();
      }
    }
    
    private class SetAdapter extends AbstractSet<Map.Entry<K, V>> {
      private final Set<Map.Entry<K, RealValue>> realSet;
      
      public SetAdapter(Set<Map.Entry<K, RealValue>> param2Set) {
        this.realSet = param2Set;
      }
      
      public Iterator<Map.Entry<K, V>> iterator() {
        return new Internal.MapAdapter.IteratorAdapter(this.realSet.iterator());
      }
      
      public int size() {
        return this.realSet.size();
      }
    }
  }
  
  static final class null implements MapAdapter.Converter<Integer, T> {
    public Integer doBackward(T param1T) {
      return Integer.valueOf(param1T.getNumber());
    }
    
    public T doForward(Integer param1Integer) {
      Internal.EnumLite enumLite;
      Integer integer = (Integer)enumMap.findValueByNumber(param1Integer.intValue());
      param1Integer = integer;
      if (integer == null)
        enumLite = unrecognizedValue; 
      return (T)enumLite;
    }
  }
  
  public static interface Converter<A, B> {
    A doBackward(B param1B);
    
    B doForward(A param1A);
  }
  
  private class EntryAdapter implements Map.Entry<K, V> {
    private final Map.Entry<K, RealValue> realEntry;
    
    public EntryAdapter(Map.Entry<K, RealValue> param1Entry) {
      this.realEntry = param1Entry;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = true;
      if (param1Object == this)
        return true; 
      if (!(param1Object instanceof Map.Entry))
        return false; 
      param1Object = param1Object;
      if (!getKey().equals(param1Object.getKey()) || !getValue().equals(getValue()))
        bool = false; 
      return bool;
    }
    
    public K getKey() {
      return this.realEntry.getKey();
    }
    
    public V getValue() {
      return (V)this.this$0.valueConverter.doForward(this.realEntry.getValue());
    }
    
    public int hashCode() {
      return this.realEntry.hashCode();
    }
    
    public V setValue(V param1V) {
      param1V = (V)this.realEntry.setValue((RealValue)this.this$0.valueConverter.doBackward(param1V));
      return (param1V == null) ? null : (V)this.this$0.valueConverter.doForward(param1V);
    }
  }
  
  private class IteratorAdapter implements Iterator<Map.Entry<K, V>> {
    private final Iterator<Map.Entry<K, RealValue>> realIterator;
    
    public IteratorAdapter(Iterator<Map.Entry<K, RealValue>> param1Iterator) {
      this.realIterator = param1Iterator;
    }
    
    public boolean hasNext() {
      return this.realIterator.hasNext();
    }
    
    public Map.Entry<K, V> next() {
      return new Internal.MapAdapter.EntryAdapter(this.realIterator.next());
    }
    
    public void remove() {
      this.realIterator.remove();
    }
  }
  
  private class SetAdapter extends AbstractSet<Map.Entry<K, V>> {
    private final Set<Map.Entry<K, RealValue>> realSet;
    
    public SetAdapter(Set<Map.Entry<K, RealValue>> param1Set) {
      this.realSet = param1Set;
    }
    
    public Iterator<Map.Entry<K, V>> iterator() {
      return new Internal.MapAdapter.IteratorAdapter(this.realSet.iterator());
    }
    
    public int size() {
      return this.realSet.size();
    }
  }
  
  public static interface ProtobufList<E> extends List<E>, RandomAccess {
    boolean isModifiable();
    
    void makeImmutable();
    
    ProtobufList<E> mutableCopyWithCapacity(int param1Int);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\Internal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */