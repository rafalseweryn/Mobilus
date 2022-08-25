package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class ListUtils {
  public static <T> ArrayList<T> copyAndRemoveElementFromListIfPresent(List<T> paramList, T paramT) {
    int i = paramList.size();
    ArrayList<T> arrayList = new ArrayList(i);
    for (byte b = 0; b < i; b++) {
      T t = paramList.get(b);
      if (paramT == null || !paramT.equals(t))
        arrayList.add(t); 
    } 
    return arrayList;
  }
  
  public static <T> ArrayList<T> copyAndRemoveElementsFromListIfPresent(List<T> paramList, Collection<T> paramCollection) {
    Preconditions.checkNotNull(paramCollection);
    ArrayList<List<T>> arrayList = new ArrayList(paramList.size());
    for (List<T> paramList : paramList) {
      if (!paramCollection.contains(paramList))
        arrayList.add(paramList); 
    } 
    return (ArrayList)arrayList;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\ListUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */