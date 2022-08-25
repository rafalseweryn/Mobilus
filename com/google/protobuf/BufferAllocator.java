package com.google.protobuf;

import java.nio.ByteBuffer;

abstract class BufferAllocator {
  private static final BufferAllocator UNPOOLED = new BufferAllocator() {
      public AllocatedBuffer allocateDirectBuffer(int param1Int) {
        return AllocatedBuffer.wrap(ByteBuffer.allocateDirect(param1Int));
      }
      
      public AllocatedBuffer allocateHeapBuffer(int param1Int) {
        return AllocatedBuffer.wrap(new byte[param1Int]);
      }
    };
  
  public static BufferAllocator unpooled() {
    return UNPOOLED;
  }
  
  public abstract AllocatedBuffer allocateDirectBuffer(int paramInt);
  
  public abstract AllocatedBuffer allocateHeapBuffer(int paramInt);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\BufferAllocator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */