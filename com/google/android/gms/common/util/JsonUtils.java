package com.google.android.gms.common.util;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@VisibleForTesting
public final class JsonUtils {
  private static final Pattern zzaae = Pattern.compile("\\\\.");
  
  private static final Pattern zzaaf = Pattern.compile("[\\\\\"/\b\f\n\r\t]");
  
  public static boolean areJsonStringsEquivalent(String paramString1, String paramString2) {
    if (paramString1 == null && paramString2 == null)
      return true; 
    if (paramString1 != null) {
      if (paramString2 == null)
        return false; 
      try {
        JSONObject jSONObject1 = new JSONObject();
        this(paramString1);
        JSONObject jSONObject2 = new JSONObject();
        this(paramString2);
        return areJsonValuesEquivalent(jSONObject1, jSONObject2);
      } catch (JSONException jSONException) {
        try {
          JSONArray jSONArray2 = new JSONArray();
          this(paramString1);
          JSONArray jSONArray1 = new JSONArray();
          this(paramString2);
          return areJsonValuesEquivalent(jSONArray2, jSONArray1);
        } catch (JSONException jSONException1) {}
      } 
    } 
    return false;
  }
  
  public static boolean areJsonValuesEquivalent(Object paramObject1, Object paramObject2) {
    if (paramObject1 == null && paramObject2 == null)
      return true; 
    if (paramObject1 != null) {
      if (paramObject2 == null)
        return false; 
      if (paramObject1 instanceof JSONObject && paramObject2 instanceof JSONObject) {
        paramObject1 = paramObject1;
        paramObject2 = paramObject2;
        if (paramObject1.length() != paramObject2.length())
          return false; 
        Iterator<String> iterator = paramObject1.keys();
        while (true) {
          if (iterator.hasNext()) {
            String str = iterator.next();
            if (!paramObject2.has(str))
              return false; 
            try {
              boolean bool = areJsonValuesEquivalent(paramObject1.get(str), paramObject2.get(str));
              if (!bool)
                return false; 
            } catch (JSONException null) {
              return false;
            } 
            continue;
          } 
          return true;
        } 
      } 
      if (jSONException instanceof JSONArray && paramObject2 instanceof JSONArray) {
        JSONArray jSONArray = (JSONArray)jSONException;
        paramObject2 = paramObject2;
        if (jSONArray.length() != paramObject2.length())
          return false; 
        byte b = 0;
        while (b < jSONArray.length()) {
          try {
            boolean bool = areJsonValuesEquivalent(jSONArray.get(b), paramObject2.get(b));
            if (!bool)
              return false; 
            b++;
          } catch (JSONException jSONException) {
            return false;
          } 
        } 
        return true;
      } 
      return jSONException.equals(paramObject2);
    } 
    return false;
  }
  
  public static String escapeString(String paramString) {
    String str = paramString;
    if (!TextUtils.isEmpty(paramString)) {
      StringBuffer stringBuffer;
      Matcher matcher = zzaaf.matcher(paramString);
      str = null;
      while (true) {
        StringBuffer stringBuffer1;
        if (matcher.find()) {
          String str1 = str;
          if (str == null)
            stringBuffer1 = new StringBuffer(); 
          char c = matcher.group().charAt(0);
          if (c != '"') {
            if (c != '/') {
              if (c != '\\') {
                StringBuffer stringBuffer5;
                String str5;
                StringBuffer stringBuffer4;
                String str4;
                StringBuffer stringBuffer3;
                String str3;
                StringBuffer stringBuffer2;
                switch (c) {
                  default:
                    switch (c) {
                      default:
                        stringBuffer5 = stringBuffer1;
                        continue;
                      case '\r':
                        str5 = "\\\\r";
                        break;
                      case '\f':
                        str5 = "\\\\f";
                        break;
                    } 
                    matcher.appendReplacement(stringBuffer1, str5);
                    stringBuffer4 = stringBuffer1;
                    continue;
                  case '\n':
                    str4 = "\\\\n";
                    matcher.appendReplacement(stringBuffer1, str4);
                    stringBuffer3 = stringBuffer1;
                    continue;
                  case '\t':
                    str3 = "\\\\t";
                    matcher.appendReplacement(stringBuffer1, str3);
                    stringBuffer2 = stringBuffer1;
                    continue;
                  case '\b':
                    break;
                } 
                String str2 = "\\\\b";
              } else {
                str = "\\\\\\\\";
              } 
            } else {
              str = "\\\\/";
            } 
          } else {
            str = "\\\\\\\"";
          } 
        } else {
          break;
        } 
        matcher.appendReplacement(stringBuffer1, str);
        stringBuffer = stringBuffer1;
      } 
      if (stringBuffer == null)
        return paramString; 
      matcher.appendTail(stringBuffer);
      str = stringBuffer.toString();
    } 
    return str;
  }
  
  public static String unescapeString(String paramString) {
    String str = paramString;
    if (!TextUtils.isEmpty(paramString)) {
      StringBuffer stringBuffer;
      String str1 = UnicodeUtils.unescape(paramString);
      Matcher matcher = zzaae.matcher(str1);
      paramString = null;
      while (matcher.find()) {
        StringBuffer stringBuffer1;
        str = paramString;
        if (paramString == null)
          stringBuffer1 = new StringBuffer(); 
        char c = matcher.group().charAt(1);
        if (c != '"') {
          if (c != '/') {
            if (c != '\\') {
              if (c != 'b') {
                if (c != 'f') {
                  if (c != 'n') {
                    if (c != 'r') {
                      if (c != 't')
                        throw new IllegalStateException("Found an escaped character that should never be."); 
                      paramString = "\t";
                    } else {
                      paramString = "\r";
                    } 
                  } else {
                    paramString = "\n";
                  } 
                } else {
                  paramString = "\f";
                } 
              } else {
                paramString = "\b";
              } 
            } else {
              paramString = "\\\\";
            } 
          } else {
            paramString = "/";
          } 
        } else {
          paramString = "\"";
        } 
        matcher.appendReplacement(stringBuffer1, paramString);
        stringBuffer = stringBuffer1;
      } 
      if (stringBuffer == null)
        return str1; 
      matcher.appendTail(stringBuffer);
      str = stringBuffer.toString();
    } 
    return str;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\JsonUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */