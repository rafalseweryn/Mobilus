package com.google.protobuf;

interface MutabilityOracle {
  public static final MutabilityOracle IMMUTABLE = new MutabilityOracle() {
      public void ensureMutable() {
        throw new UnsupportedOperationException();
      }
    };
  
  void ensureMutable();
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\MutabilityOracle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */