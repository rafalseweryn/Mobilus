package com.google.protobuf;

import java.lang.reflect.Field;

final class OneofInfo {
  private final Field caseField;
  
  private final int id;
  
  private final Field valueField;
  
  public OneofInfo(int paramInt, Field paramField1, Field paramField2) {
    this.id = paramInt;
    this.caseField = paramField1;
    this.valueField = paramField2;
  }
  
  public Field getCaseField() {
    return this.caseField;
  }
  
  public int getId() {
    return this.id;
  }
  
  public Field getValueField() {
    return this.valueField;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\OneofInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */