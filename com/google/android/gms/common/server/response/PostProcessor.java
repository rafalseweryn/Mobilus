package com.google.android.gms.common.server.response;

public interface PostProcessor<T extends FastJsonResponse> {
  T postProcess(T paramT);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\server\response\PostProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */