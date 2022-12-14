package com.google.protobuf;

import java.util.Arrays;

public final class TextFormatParseLocation {
  public static final TextFormatParseLocation EMPTY = new TextFormatParseLocation(-1, -1);
  
  private final int column;
  
  private final int line;
  
  private TextFormatParseLocation(int paramInt1, int paramInt2) {
    this.line = paramInt1;
    this.column = paramInt2;
  }
  
  static TextFormatParseLocation create(int paramInt1, int paramInt2) {
    if (paramInt1 == -1 && paramInt2 == -1)
      return EMPTY; 
    if (paramInt1 < 0 || paramInt2 < 0)
      throw new IllegalArgumentException(String.format("line and column values must be >= 0: line %d, column: %d", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) })); 
    return new TextFormatParseLocation(paramInt1, paramInt2);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof TextFormatParseLocation))
      return false; 
    paramObject = paramObject;
    if (this.line != paramObject.getLine() || this.column != paramObject.getColumn())
      bool = false; 
    return bool;
  }
  
  public int getColumn() {
    return this.column;
  }
  
  public int getLine() {
    return this.line;
  }
  
  public int hashCode() {
    return Arrays.hashCode(new int[] { this.line, this.column });
  }
  
  public String toString() {
    return String.format("ParseLocation{line=%d, column=%d}", new Object[] { Integer.valueOf(this.line), Integer.valueOf(this.column) });
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\TextFormatParseLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */