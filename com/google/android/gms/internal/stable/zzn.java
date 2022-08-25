package com.google.android.gms.internal.stable;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class zzn extends WeakReference<Throwable> {
  private final int zzahl;
  
  public zzn(Throwable paramThrowable, ReferenceQueue<Throwable> paramReferenceQueue) {
    super(paramThrowable, paramReferenceQueue);
    if (paramThrowable == null)
      throw new NullPointerException("The referent cannot be null"); 
    this.zzahl = System.identityHashCode(paramThrowable);
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject != null) {
      if (paramObject.getClass() != getClass())
        return false; 
      if (this == paramObject)
        return true; 
      paramObject = paramObject;
      if (this.zzahl == ((zzn)paramObject).zzahl && get() == paramObject.get())
        return true; 
    } 
    return false;
  }
  
  public final int hashCode() {
    return this.zzahl;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\internal\stable\zzn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */