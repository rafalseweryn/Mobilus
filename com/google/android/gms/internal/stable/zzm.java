package com.google.android.gms.internal.stable;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

final class zzm {
  private final ConcurrentHashMap<zzn, List<Throwable>> zzahj = new ConcurrentHashMap<>(16, 0.75F, 10);
  
  private final ReferenceQueue<Throwable> zzahk = new ReferenceQueue<>();
  
  public final List<Throwable> zza(Throwable paramThrowable, boolean paramBoolean) {
    while (true) {
      Reference<? extends Throwable> reference = this.zzahk.poll();
      if (reference != null) {
        this.zzahj.remove(reference);
        continue;
      } 
      reference = new zzn(paramThrowable, null);
      List<Throwable> list2 = this.zzahj.get(reference);
      if (list2 != null)
        return list2; 
      list2 = new Vector<>(2);
      List<Throwable> list1 = this.zzahj.putIfAbsent(new zzn(paramThrowable, this.zzahk), list2);
      return (list1 == null) ? list2 : list1;
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\internal\stable\zzm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */