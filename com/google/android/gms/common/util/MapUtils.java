package com.google.android.gms.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapUtils {
  public static <K, V> K getKeyFromMap(Map<K, V> paramMap, K paramK) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   7: ifeq -> 48
    //   10: aload_0
    //   11: invokeinterface keySet : ()Ljava/util/Set;
    //   16: invokeinterface iterator : ()Ljava/util/Iterator;
    //   21: astore_0
    //   22: aload_0
    //   23: invokeinterface hasNext : ()Z
    //   28: ifeq -> 48
    //   31: aload_0
    //   32: invokeinterface next : ()Ljava/lang/Object;
    //   37: astore_2
    //   38: aload_2
    //   39: aload_1
    //   40: invokevirtual equals : (Ljava/lang/Object;)Z
    //   43: ifeq -> 22
    //   46: aload_2
    //   47: areturn
    //   48: aconst_null
    //   49: areturn
  }
  
  public static void writeStringMapToJson(StringBuilder paramStringBuilder, HashMap<String, String> paramHashMap) {
    paramStringBuilder.append("{");
    Iterator<String> iterator = paramHashMap.keySet().iterator();
    boolean bool = true;
    while (iterator.hasNext()) {
      String str1 = iterator.next();
      if (!bool) {
        paramStringBuilder.append(",");
      } else {
        bool = false;
      } 
      String str2 = paramHashMap.get(str1);
      paramStringBuilder.append("\"");
      paramStringBuilder.append(str1);
      paramStringBuilder.append("\":");
      if (str2 == null) {
        str1 = "null";
      } else {
        paramStringBuilder.append("\"");
        paramStringBuilder.append(str2);
        str1 = "\"";
      } 
      paramStringBuilder.append(str1);
    } 
    paramStringBuilder.append("}");
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\MapUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */