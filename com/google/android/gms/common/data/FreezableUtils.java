package com.google.android.gms.common.data;

import java.util.ArrayList;
import java.util.Iterator;

public final class FreezableUtils {
  public static <T, E extends Freezable<T>> ArrayList<T> freeze(ArrayList<E> paramArrayList) {
    ArrayList<T> arrayList = new ArrayList(paramArrayList.size());
    int i = paramArrayList.size();
    for (byte b = 0; b < i; b++)
      arrayList.add(((Freezable)paramArrayList.get(b)).freeze()); 
    return arrayList;
  }
  
  public static <T, E extends Freezable<T>> ArrayList<T> freeze(E[] paramArrayOfE) {
    ArrayList<T> arrayList = new ArrayList(paramArrayOfE.length);
    for (byte b = 0; b < paramArrayOfE.length; b++)
      arrayList.add(paramArrayOfE[b].freeze()); 
    return arrayList;
  }
  
  public static <T, E extends Freezable<T>> ArrayList<T> freezeIterable(Iterable<E> paramIterable) {
    ArrayList<T> arrayList = new ArrayList();
    Iterator<E> iterator = paramIterable.iterator();
    while (iterator.hasNext())
      arrayList.add(((Freezable)iterator.next()).freeze()); 
    return arrayList;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\data\FreezableUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */