package com.google.android.gms.common.util;

import java.util.ArrayList;

public abstract class ObjectPoolBase<T> {
  private final ArrayList<T> zzaag;
  
  private final int zzaah;
  
  public ObjectPoolBase(int paramInt) {
    this.zzaag = new ArrayList<>(paramInt);
    this.zzaah = paramInt;
  }
  
  public final T aquire() {
    synchronized (this.zzaag) {
      int i = this.zzaag.size();
      if (i > 0)
        return this.zzaag.remove(i - 1); 
      return newObject();
    } 
  }
  
  protected boolean cleanUpObject(T paramT) {
    return true;
  }
  
  protected abstract T newObject();
  
  public final boolean release(T paramT) {
    synchronized (this.zzaag) {
      String str;
      int i = this.zzaag.size();
      for (int j = 0; j < i; j++) {
        if (this.zzaag.get(j) == paramT) {
          IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
          str = String.valueOf(paramT);
          j = String.valueOf(str).length();
          StringBuilder stringBuilder = new StringBuilder();
          this(j + 25);
          stringBuilder.append("Object released already: ");
          stringBuilder.append(str);
          this(stringBuilder.toString());
          throw illegalArgumentException;
        } 
      } 
      if (i < this.zzaah && cleanUpObject((T)str)) {
        this.zzaag.add((T)str);
        return true;
      } 
      return false;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\ObjectPoolBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */