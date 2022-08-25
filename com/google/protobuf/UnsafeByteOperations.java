package com.google.protobuf;

import java.io.IOException;
import java.nio.ByteBuffer;

public final class UnsafeByteOperations {
  public static ByteString unsafeWrap(ByteBuffer paramByteBuffer) {
    return ByteString.wrap(paramByteBuffer);
  }
  
  public static ByteString unsafeWrap(byte[] paramArrayOfbyte) {
    return ByteString.wrap(paramArrayOfbyte);
  }
  
  public static ByteString unsafeWrap(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return ByteString.wrap(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public static void unsafeWriteTo(ByteString paramByteString, ByteOutput paramByteOutput) throws IOException {
    paramByteString.writeTo(paramByteOutput);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\UnsafeByteOperations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */