package org.eclipse.paho.client.mqttv3.util;

public final class Strings {
  private static final int INDEX_NOT_FOUND = -1;
  
  public static boolean containsAny(CharSequence paramCharSequence1, CharSequence paramCharSequence2) {
    return (paramCharSequence2 == null) ? false : containsAny(paramCharSequence1, toCharArray(paramCharSequence2));
  }
  
  public static boolean containsAny(CharSequence paramCharSequence, char[] paramArrayOfchar) {
    if (isEmpty(paramCharSequence) || isEmpty(paramArrayOfchar))
      return false; 
    int i = paramCharSequence.length();
    int j = paramArrayOfchar.length;
    byte b = 0;
    label24: while (true) {
      if (b >= i)
        return false; 
      char c = paramCharSequence.charAt(b);
      for (byte b1 = 0;; b1++) {
        if (b1 >= j) {
          b++;
          continue label24;
        } 
        if (paramArrayOfchar[b1] == c)
          if (Character.isHighSurrogate(c)) {
            if (b1 == j - 1)
              return true; 
            if (b < i - 1 && paramArrayOfchar[b1 + 1] == paramCharSequence.charAt(b + 1))
              return true; 
          } else {
            return true;
          }  
      } 
      break;
    } 
  }
  
  public static int countMatches(CharSequence paramCharSequence1, CharSequence paramCharSequence2) {
    boolean bool = isEmpty(paramCharSequence1);
    int i = 0;
    if (bool || isEmpty(paramCharSequence2))
      return 0; 
    byte b = 0;
    while (true) {
      i = indexOf(paramCharSequence1, paramCharSequence2, i);
      if (i == -1)
        return b; 
      b++;
      i += paramCharSequence2.length();
    } 
  }
  
  public static boolean equalsAny(CharSequence paramCharSequence, CharSequence[] paramArrayOfCharSequence) {
    boolean bool1;
    if (paramCharSequence == null && paramArrayOfCharSequence == null) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    boolean bool2 = bool1;
    if (paramArrayOfCharSequence != null)
      for (byte b = 0;; b++) {
        if (b >= paramArrayOfCharSequence.length) {
          bool2 = bool1;
          break;
        } 
        if (!bool1 && !paramArrayOfCharSequence[b].equals(paramCharSequence)) {
          bool1 = false;
        } else {
          bool1 = true;
        } 
      }  
    return bool2;
  }
  
  private static int indexOf(CharSequence paramCharSequence1, CharSequence paramCharSequence2, int paramInt) {
    return paramCharSequence1.toString().indexOf(paramCharSequence2.toString(), paramInt);
  }
  
  public static boolean isEmpty(CharSequence paramCharSequence) {
    return !(paramCharSequence != null && paramCharSequence.length() != 0);
  }
  
  private static boolean isEmpty(char[] paramArrayOfchar) {
    return !(paramArrayOfchar != null && paramArrayOfchar.length != 0);
  }
  
  private static char[] toCharArray(CharSequence paramCharSequence) {
    if (paramCharSequence instanceof String)
      return ((String)paramCharSequence).toCharArray(); 
    int i = paramCharSequence.length();
    char[] arrayOfChar = new char[paramCharSequence.length()];
    for (byte b = 0;; b++) {
      if (b >= i)
        return arrayOfChar; 
      arrayOfChar[b] = paramCharSequence.charAt(b);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\client\mqttv\\util\Strings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */