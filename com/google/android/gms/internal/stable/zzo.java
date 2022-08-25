package com.google.android.gms.internal.stable;

final class zzo extends zzl {
  private final zzm zzahm = new zzm();
  
  public final void zza(Throwable paramThrowable1, Throwable paramThrowable2) {
    if (paramThrowable2 == paramThrowable1)
      throw new IllegalArgumentException("Self suppression is not allowed.", paramThrowable2); 
    if (paramThrowable2 == null)
      throw new NullPointerException("The suppressed exception cannot be null."); 
    this.zzahm.zza(paramThrowable1, true).add(paramThrowable2);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\internal\stable\zzo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */