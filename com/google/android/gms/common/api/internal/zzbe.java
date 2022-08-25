package com.google.android.gms.common.api.internal;

abstract class zzbe {
  private final zzbc zzjg;
  
  protected zzbe(zzbc paramzzbc) {
    this.zzjg = paramzzbc;
  }
  
  protected abstract void zzaq();
  
  public final void zzc(zzbd paramzzbd) {
    zzbd.zza(paramzzbd).lock();
    try {
      zzbc zzbc1 = zzbd.zzb(paramzzbd);
      zzbc zzbc2 = this.zzjg;
      if (zzbc1 == zzbc2)
        zzaq(); 
      return;
    } finally {
      zzbd.zza(paramzzbd).unlock();
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\internal\zzbe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */