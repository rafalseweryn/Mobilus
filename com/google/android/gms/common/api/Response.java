package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public class Response<T extends Result> {
  private T zzdm;
  
  public Response() {}
  
  protected Response(@NonNull T paramT) {
    this.zzdm = paramT;
  }
  
  @NonNull
  protected T getResult() {
    return this.zzdm;
  }
  
  public void setResult(@NonNull T paramT) {
    this.zzdm = paramT;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\api\Response.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */