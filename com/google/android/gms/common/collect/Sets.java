package com.google.android.gms.common.collect;

import com.google.android.gms.common.internal.Preconditions;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Sets {
  public static <E> Set<E> difference(Set<? extends E> paramSet1, Set<? extends E> paramSet2) {
    Preconditions.checkNotNull(paramSet1);
    Preconditions.checkNotNull(paramSet2);
    HashSet<Set<? extends E>> hashSet = new HashSet();
    for (Set<? extends E> paramSet1 : paramSet1) {
      if (!paramSet2.contains(paramSet1))
        hashSet.add(paramSet1); 
    } 
    return (Set)hashSet;
  }
  
  public static <E> Set<E> union(Iterable<Set<E>> paramIterable) {
    Preconditions.checkNotNull(paramIterable);
    HashSet<E> hashSet = new HashSet();
    Iterator<Set<E>> iterator = paramIterable.iterator();
    while (iterator.hasNext())
      hashSet.addAll(iterator.next()); 
    return hashSet;
  }
  
  public static <E> Set<E> union(Set<? extends E> paramSet1, Set<? extends E> paramSet2) {
    Preconditions.checkNotNull(paramSet1);
    Preconditions.checkNotNull(paramSet2);
    paramSet1 = new HashSet<>(paramSet1);
    paramSet1.addAll(paramSet2);
    return (Set)paramSet1;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\collect\Sets.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */