package com.google.android.gms.common.util;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@VisibleForTesting
public class Strings {
  private static final Pattern zzaak = Pattern.compile("\\$\\{(.*?)\\}");
  
  public static String capitalize(@NonNull String paramString) {
    if (paramString.length() == 0)
      return paramString; 
    char c1 = paramString.charAt(0);
    char c2 = Character.toUpperCase(c1);
    if (c1 == c2)
      return paramString; 
    paramString = paramString.substring(1);
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString).length() + 1);
    stringBuilder.append(c2);
    stringBuilder.append(paramString);
    return stringBuilder.toString();
  }
  
  @Nullable
  public static String emptyToNull(@Nullable String paramString) {
    String str = paramString;
    if (TextUtils.isEmpty(paramString))
      str = null; 
    return str;
  }
  
  public static String format(@NonNull String paramString, @NonNull Bundle paramBundle) {
    Matcher matcher = zzaak.matcher(paramString);
    if (matcher.find()) {
      StringBuffer stringBuffer = new StringBuffer();
      while (true) {
        paramString = matcher.group(1);
        Object object = paramBundle.get(paramString);
        if (object != null) {
          paramString = object.toString();
        } else if (paramBundle.containsKey(paramString)) {
          paramString = "null";
        } else {
          paramString = "";
        } 
        matcher.appendReplacement(stringBuffer, paramString);
        if (!matcher.find()) {
          matcher.appendTail(stringBuffer);
          paramString = stringBuffer.toString();
          break;
        } 
      } 
    } 
    return paramString;
  }
  
  public static boolean isEmptyOrWhitespace(@Nullable String paramString) {
    return (paramString == null || paramString.trim().isEmpty());
  }
  
  public static String nullToEmpty(@Nullable String paramString) {
    String str = paramString;
    if (paramString == null)
      str = ""; 
    return str;
  }
  
  public static String padEnd(@NonNull String paramString, int paramInt, char paramChar) {
    Preconditions.checkNotNull(paramString);
    if (paramString.length() >= paramInt)
      return paramString; 
    StringBuilder stringBuilder = new StringBuilder(paramInt);
    stringBuilder.append(paramString);
    for (int i = paramString.length(); i < paramInt; i++)
      stringBuilder.append(paramChar); 
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\Strings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */