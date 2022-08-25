package com.google.protobuf;

import java.util.Map;

interface MapFieldSchema {
  Map<?, ?> forMapData(Object paramObject);
  
  MapEntryLite.Metadata<?, ?> forMapMetadata(Object paramObject);
  
  Map<?, ?> forMutableMapData(Object paramObject);
  
  int getSerializedSize(int paramInt, Object paramObject1, Object paramObject2);
  
  boolean isImmutable(Object paramObject);
  
  Object mergeFrom(Object paramObject1, Object paramObject2);
  
  Object newMapField(Object paramObject);
  
  Object toImmutable(Object paramObject);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\MapFieldSchema.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */