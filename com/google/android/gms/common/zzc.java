package com.google.android.gms.common;

import java.lang.ref.WeakReference;

abstract class zzc extends GoogleCertificates.CertData {
  private static final WeakReference<byte[]> zzbf = (WeakReference)new WeakReference<>(null);
  
  private WeakReference<byte[]> zzbe = zzbf;
  
  zzc(byte[] paramArrayOfbyte) {
    super(paramArrayOfbyte);
  }
  
  final byte[] getBytes() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzbe : Ljava/lang/ref/WeakReference;
    //   6: invokevirtual get : ()Ljava/lang/Object;
    //   9: checkcast [B
    //   12: astore_1
    //   13: aload_1
    //   14: astore_2
    //   15: aload_1
    //   16: ifnonnull -> 38
    //   19: aload_0
    //   20: invokevirtual zzf : ()[B
    //   23: astore_2
    //   24: new java/lang/ref/WeakReference
    //   27: astore_1
    //   28: aload_1
    //   29: aload_2
    //   30: invokespecial <init> : (Ljava/lang/Object;)V
    //   33: aload_0
    //   34: aload_1
    //   35: putfield zzbe : Ljava/lang/ref/WeakReference;
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_2
    //   41: areturn
    //   42: astore_2
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_2
    //   46: athrow
    // Exception table:
    //   from	to	target	type
    //   2	13	42	finally
    //   19	38	42	finally
    //   38	40	42	finally
    //   43	45	42	finally
  }
  
  protected abstract byte[] zzf();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\zzc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */