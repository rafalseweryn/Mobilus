package com.google.protobuf;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class SmallSortedMap<K extends Comparable<K>, V> extends AbstractMap<K, V> {
  private List<Entry> entryList;
  
  private boolean isImmutable;
  
  private volatile DescendingEntrySet lazyDescendingEntrySet;
  
  private volatile EntrySet lazyEntrySet;
  
  private final int maxArraySize;
  
  private Map<K, V> overflowEntries;
  
  private Map<K, V> overflowEntriesDescending;
  
  private SmallSortedMap(int paramInt) {
    this.maxArraySize = paramInt;
    this.entryList = Collections.emptyList();
    this.overflowEntries = Collections.emptyMap();
    this.overflowEntriesDescending = Collections.emptyMap();
  }
  
  private int binarySearchInArray(K paramK) {
    int i = this.entryList.size() - 1;
    if (i >= 0) {
      int k = paramK.compareTo(((Entry)this.entryList.get(i)).getKey());
      if (k > 0)
        return -(i + 2); 
      if (k == 0)
        return i; 
    } 
    int j = 0;
    while (j <= i) {
      int k = (j + i) / 2;
      int m = paramK.compareTo(((Entry)this.entryList.get(k)).getKey());
      if (m < 0) {
        i = k - 1;
        continue;
      } 
      if (m > 0) {
        j = k + 1;
        continue;
      } 
      return k;
    } 
    return -(j + 1);
  }
  
  private void checkMutable() {
    if (this.isImmutable)
      throw new UnsupportedOperationException(); 
  }
  
  private void ensureEntryArrayMutable() {
    checkMutable();
    if (this.entryList.isEmpty() && !(this.entryList instanceof ArrayList))
      this.entryList = new ArrayList<>(this.maxArraySize); 
  }
  
  private SortedMap<K, V> getOverflowEntriesMutable() {
    checkMutable();
    if (this.overflowEntries.isEmpty() && !(this.overflowEntries instanceof TreeMap)) {
      this.overflowEntries = new TreeMap<>();
      this.overflowEntriesDescending = ((TreeMap<K, V>)this.overflowEntries).descendingMap();
    } 
    return (SortedMap<K, V>)this.overflowEntries;
  }
  
  static <FieldDescriptorType extends FieldSet.FieldDescriptorLite<FieldDescriptorType>> SmallSortedMap<FieldDescriptorType, Object> newFieldMap(int paramInt) {
    return new SmallSortedMap<FieldDescriptorType, Object>(paramInt) {
        public void makeImmutable() {
          if (!isImmutable()) {
            for (byte b = 0; b < getNumArrayEntries(); b++) {
              Map.Entry<FieldDescriptorType, Object> entry = getArrayEntryAt(b);
              if (((FieldSet.FieldDescriptorLite)entry.getKey()).isRepeated())
                entry.setValue(Collections.unmodifiableList((List)entry.getValue())); 
            } 
            for (Map.Entry<FieldDescriptorType, Object> entry : getOverflowEntries()) {
              if (((FieldSet.FieldDescriptorLite)entry.getKey()).isRepeated())
                entry.setValue(Collections.unmodifiableList((List)entry.getValue())); 
            } 
          } 
          super.makeImmutable();
        }
      };
  }
  
  static <K extends Comparable<K>, V> SmallSortedMap<K, V> newInstanceForTest(int paramInt) {
    return new SmallSortedMap<>(paramInt);
  }
  
  private V removeArrayEntryAt(int paramInt) {
    checkMutable();
    V v = ((Entry)this.entryList.remove(paramInt)).getValue();
    if (!this.overflowEntries.isEmpty()) {
      Iterator<Map.Entry<K, V>> iterator = getOverflowEntriesMutable().entrySet().iterator();
      this.entryList.add(new Entry(iterator.next()));
      iterator.remove();
    } 
    return v;
  }
  
  public void clear() {
    checkMutable();
    if (!this.entryList.isEmpty())
      this.entryList.clear(); 
    if (!this.overflowEntries.isEmpty())
      this.overflowEntries.clear(); 
  }
  
  public boolean containsKey(Object paramObject) {
    paramObject = paramObject;
    return (binarySearchInArray((K)paramObject) >= 0 || this.overflowEntries.containsKey(paramObject));
  }
  
  Set<Map.Entry<K, V>> descendingEntrySet() {
    if (this.lazyDescendingEntrySet == null)
      this.lazyDescendingEntrySet = new DescendingEntrySet(); 
    return this.lazyDescendingEntrySet;
  }
  
  public Set<Map.Entry<K, V>> entrySet() {
    if (this.lazyEntrySet == null)
      this.lazyEntrySet = new EntrySet(); 
    return this.lazyEntrySet;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof SmallSortedMap))
      return super.equals(paramObject); 
    paramObject = paramObject;
    int i = size();
    if (i != paramObject.size())
      return false; 
    int j = getNumArrayEntries();
    if (j != paramObject.getNumArrayEntries())
      return entrySet().equals(paramObject.entrySet()); 
    for (byte b = 0; b < j; b++) {
      if (!getArrayEntryAt(b).equals(paramObject.getArrayEntryAt(b)))
        return false; 
    } 
    return (j != i) ? this.overflowEntries.equals(((SmallSortedMap)paramObject).overflowEntries) : true;
  }
  
  public V get(Object paramObject) {
    paramObject = paramObject;
    int i = binarySearchInArray((K)paramObject);
    return (i >= 0) ? ((Entry)this.entryList.get(i)).getValue() : this.overflowEntries.get(paramObject);
  }
  
  public Map.Entry<K, V> getArrayEntryAt(int paramInt) {
    return this.entryList.get(paramInt);
  }
  
  public int getNumArrayEntries() {
    return this.entryList.size();
  }
  
  public int getNumOverflowEntries() {
    return this.overflowEntries.size();
  }
  
  public Iterable<Map.Entry<K, V>> getOverflowEntries() {
    Iterable<?> iterable;
    if (this.overflowEntries.isEmpty()) {
      iterable = EmptySet.iterable();
    } else {
      iterable = this.overflowEntries.entrySet();
    } 
    return (Iterable)iterable;
  }
  
  Iterable<Map.Entry<K, V>> getOverflowEntriesDescending() {
    Iterable<?> iterable;
    if (this.overflowEntriesDescending.isEmpty()) {
      iterable = EmptySet.iterable();
    } else {
      iterable = this.overflowEntriesDescending.entrySet();
    } 
    return (Iterable)iterable;
  }
  
  public int hashCode() {
    int i = getNumArrayEntries();
    int j = 0;
    int k = 0;
    while (j < i) {
      k += ((Entry)this.entryList.get(j)).hashCode();
      j++;
    } 
    j = k;
    if (getNumOverflowEntries() > 0)
      j = k + this.overflowEntries.hashCode(); 
    return j;
  }
  
  public boolean isImmutable() {
    return this.isImmutable;
  }
  
  public void makeImmutable() {
    if (!this.isImmutable) {
      Map<?, ?> map;
      if (this.overflowEntries.isEmpty()) {
        map = Collections.emptyMap();
      } else {
        map = Collections.unmodifiableMap(this.overflowEntries);
      } 
      this.overflowEntries = (Map)map;
      if (this.overflowEntriesDescending.isEmpty()) {
        map = Collections.emptyMap();
      } else {
        map = Collections.unmodifiableMap(this.overflowEntriesDescending);
      } 
      this.overflowEntriesDescending = (Map)map;
      this.isImmutable = true;
    } 
  }
  
  public V put(K paramK, V paramV) {
    checkMutable();
    int i = binarySearchInArray(paramK);
    if (i >= 0)
      return ((Entry)this.entryList.get(i)).setValue(paramV); 
    ensureEntryArrayMutable();
    i = -(i + 1);
    if (i >= this.maxArraySize)
      return getOverflowEntriesMutable().put(paramK, paramV); 
    if (this.entryList.size() == this.maxArraySize) {
      Entry entry = this.entryList.remove(this.maxArraySize - 1);
      getOverflowEntriesMutable().put(entry.getKey(), entry.getValue());
    } 
    this.entryList.add(i, new Entry(paramK, paramV));
    return null;
  }
  
  public V remove(Object paramObject) {
    checkMutable();
    paramObject = paramObject;
    int i = binarySearchInArray((K)paramObject);
    return (i >= 0) ? removeArrayEntryAt(i) : (this.overflowEntries.isEmpty() ? null : this.overflowEntries.remove(paramObject));
  }
  
  public int size() {
    return this.entryList.size() + this.overflowEntries.size();
  }
  
  private class DescendingEntryIterator implements Iterator<Map.Entry<K, V>> {
    private Iterator<Map.Entry<K, V>> lazyOverflowIterator;
    
    private int pos = SmallSortedMap.access$600(SmallSortedMap.this).size();
    
    private DescendingEntryIterator() {}
    
    private Iterator<Map.Entry<K, V>> getOverflowIterator() {
      if (this.lazyOverflowIterator == null)
        this.lazyOverflowIterator = SmallSortedMap.this.overflowEntriesDescending.entrySet().iterator(); 
      return this.lazyOverflowIterator;
    }
    
    public boolean hasNext() {
      boolean bool;
      if ((this.pos > 0 && this.pos <= SmallSortedMap.access$600(SmallSortedMap.this).size()) || getOverflowIterator().hasNext()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public Map.Entry<K, V> next() {
      if (getOverflowIterator().hasNext())
        return getOverflowIterator().next(); 
      List<Map.Entry<K, V>> list = SmallSortedMap.access$600(SmallSortedMap.this);
      int i = this.pos - 1;
      this.pos = i;
      return list.get(i);
    }
    
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
  
  private class DescendingEntrySet extends EntrySet {
    private DescendingEntrySet() {}
    
    public Iterator<Map.Entry<K, V>> iterator() {
      return new SmallSortedMap.DescendingEntryIterator();
    }
  }
  
  private static class EmptySet {
    private static final Iterable<Object> ITERABLE = new Iterable() {
        public Iterator<Object> iterator() {
          return SmallSortedMap.EmptySet.ITERATOR;
        }
      };
    
    private static final Iterator<Object> ITERATOR = new Iterator() {
        public boolean hasNext() {
          return false;
        }
        
        public Object next() {
          throw new NoSuchElementException();
        }
        
        public void remove() {
          throw new UnsupportedOperationException();
        }
      };
    
    static {
    
    }
    
    static <T> Iterable<T> iterable() {
      return (Iterable)ITERABLE;
    }
  }
  
  static final class null implements Iterator<Object> {
    public boolean hasNext() {
      return false;
    }
    
    public Object next() {
      throw new NoSuchElementException();
    }
    
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
  
  static final class null implements Iterable<Object> {
    public Iterator<Object> iterator() {
      return SmallSortedMap.EmptySet.ITERATOR;
    }
  }
  
  private class Entry implements Map.Entry<K, V>, Comparable<Entry> {
    private final K key;
    
    private V value;
    
    Entry(K param1K, V param1V) {
      this.key = param1K;
      this.value = param1V;
    }
    
    Entry(Map.Entry<K, V> param1Entry) {
      this(param1Entry.getKey(), param1Entry.getValue());
    }
    
    private boolean equals(Object param1Object1, Object param1Object2) {
      boolean bool;
      if (param1Object1 == null) {
        if (param1Object2 == null) {
          bool = true;
        } else {
          bool = false;
        } 
      } else {
        bool = param1Object1.equals(param1Object2);
      } 
      return bool;
    }
    
    public int compareTo(Entry param1Entry) {
      return getKey().compareTo(param1Entry.getKey());
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = true;
      if (param1Object == this)
        return true; 
      if (!(param1Object instanceof Map.Entry))
        return false; 
      param1Object = param1Object;
      if (!equals(this.key, param1Object.getKey()) || !equals(this.value, param1Object.getValue()))
        bool = false; 
      return bool;
    }
    
    public K getKey() {
      return this.key;
    }
    
    public V getValue() {
      return this.value;
    }
    
    public int hashCode() {
      int j;
      K k = this.key;
      int i = 0;
      if (k == null) {
        j = 0;
      } else {
        j = this.key.hashCode();
      } 
      if (this.value != null)
        i = this.value.hashCode(); 
      return j ^ i;
    }
    
    public V setValue(V param1V) {
      SmallSortedMap.this.checkMutable();
      V v = this.value;
      this.value = param1V;
      return v;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this.key);
      stringBuilder.append("=");
      stringBuilder.append(this.value);
      return stringBuilder.toString();
    }
  }
  
  private class EntryIterator implements Iterator<Map.Entry<K, V>> {
    private Iterator<Map.Entry<K, V>> lazyOverflowIterator;
    
    private boolean nextCalledBeforeRemove;
    
    private int pos = -1;
    
    private EntryIterator() {}
    
    private Iterator<Map.Entry<K, V>> getOverflowIterator() {
      if (this.lazyOverflowIterator == null)
        this.lazyOverflowIterator = SmallSortedMap.this.overflowEntries.entrySet().iterator(); 
      return this.lazyOverflowIterator;
    }
    
    public boolean hasNext() {
      int i = this.pos;
      boolean bool1 = true;
      boolean bool2 = bool1;
      if (i + 1 >= SmallSortedMap.access$600(SmallSortedMap.this).size())
        if (!SmallSortedMap.this.overflowEntries.isEmpty() && getOverflowIterator().hasNext()) {
          bool2 = bool1;
        } else {
          bool2 = false;
        }  
      return bool2;
    }
    
    public Map.Entry<K, V> next() {
      this.nextCalledBeforeRemove = true;
      int i = this.pos + 1;
      this.pos = i;
      return (i < SmallSortedMap.access$600(SmallSortedMap.this).size()) ? SmallSortedMap.access$600(SmallSortedMap.this).get(this.pos) : getOverflowIterator().next();
    }
    
    public void remove() {
      if (!this.nextCalledBeforeRemove)
        throw new IllegalStateException("remove() was called before next()"); 
      this.nextCalledBeforeRemove = false;
      SmallSortedMap.this.checkMutable();
      if (this.pos < SmallSortedMap.access$600(SmallSortedMap.this).size()) {
        SmallSortedMap smallSortedMap = SmallSortedMap.this;
        int i = this.pos;
        this.pos = i - 1;
        smallSortedMap.removeArrayEntryAt(i);
      } else {
        getOverflowIterator().remove();
      } 
    }
  }
  
  private class EntrySet extends AbstractSet<Map.Entry<K, V>> {
    private EntrySet() {}
    
    public boolean add(Map.Entry<K, V> param1Entry) {
      if (!contains(param1Entry)) {
        SmallSortedMap.this.put((Comparable)param1Entry.getKey(), param1Entry.getValue());
        return true;
      } 
      return false;
    }
    
    public void clear() {
      SmallSortedMap.this.clear();
    }
    
    public boolean contains(Object param1Object) {
      Map.Entry entry = (Map.Entry)param1Object;
      param1Object = SmallSortedMap.this.get(entry.getKey());
      entry = (Map.Entry)entry.getValue();
      return (param1Object == entry || (param1Object != null && param1Object.equals(entry)));
    }
    
    public Iterator<Map.Entry<K, V>> iterator() {
      return new SmallSortedMap.EntryIterator();
    }
    
    public boolean remove(Object param1Object) {
      param1Object = param1Object;
      if (contains(param1Object)) {
        SmallSortedMap.this.remove(param1Object.getKey());
        return true;
      } 
      return false;
    }
    
    public int size() {
      return SmallSortedMap.this.size();
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\SmallSortedMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */