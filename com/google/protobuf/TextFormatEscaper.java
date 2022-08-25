package com.google.protobuf;

final class TextFormatEscaper {
  static String escapeBytes(final ByteString input) {
    return escapeBytes(new ByteSequence() {
          public byte byteAt(int param1Int) {
            return input.byteAt(param1Int);
          }
          
          public int size() {
            return input.size();
          }
        });
  }
  
  static String escapeBytes(ByteSequence paramByteSequence) {
    StringBuilder stringBuilder = new StringBuilder(paramByteSequence.size());
    for (byte b = 0; b < paramByteSequence.size(); b++) {
      byte b1 = paramByteSequence.byteAt(b);
      if (b1 != 34) {
        if (b1 != 39) {
          if (b1 != 92) {
            switch (b1) {
              default:
                if (b1 >= 32 && b1 <= 126) {
                  stringBuilder.append((char)b1);
                  break;
                } 
                stringBuilder.append('\\');
                stringBuilder.append((char)((b1 >>> 6 & 0x3) + 48));
                stringBuilder.append((char)((b1 >>> 3 & 0x7) + 48));
                stringBuilder.append((char)((b1 & 0x7) + 48));
                break;
              case 13:
                stringBuilder.append("\\r");
                break;
              case 12:
                stringBuilder.append("\\f");
                break;
              case 11:
                stringBuilder.append("\\v");
                break;
              case 10:
                stringBuilder.append("\\n");
                break;
              case 9:
                stringBuilder.append("\\t");
                break;
              case 8:
                stringBuilder.append("\\b");
                break;
              case 7:
                stringBuilder.append("\\a");
                break;
            } 
          } else {
            stringBuilder.append("\\\\");
          } 
        } else {
          stringBuilder.append("\\'");
        } 
      } else {
        stringBuilder.append("\\\"");
      } 
    } 
    return stringBuilder.toString();
  }
  
  static String escapeBytes(final byte[] input) {
    return escapeBytes(new ByteSequence() {
          public byte byteAt(int param1Int) {
            return input[param1Int];
          }
          
          public int size() {
            return input.length;
          }
        });
  }
  
  static String escapeDoubleQuotesAndBackslashes(String paramString) {
    return paramString.replace("\\", "\\\\").replace("\"", "\\\"");
  }
  
  static String escapeText(String paramString) {
    return escapeBytes(ByteString.copyFromUtf8(paramString));
  }
  
  private static interface ByteSequence {
    byte byteAt(int param1Int);
    
    int size();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\protobuf\TextFormatEscaper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */