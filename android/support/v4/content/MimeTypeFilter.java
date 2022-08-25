package android.support.v4.content;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;

public final class MimeTypeFilter {
  @Nullable
  public static String matches(@Nullable String paramString, @NonNull String[] paramArrayOfString) {
    if (paramString == null)
      return null; 
    String[] arrayOfString = paramString.split("/");
    int i = paramArrayOfString.length;
    for (byte b = 0; b < i; b++) {
      paramString = paramArrayOfString[b];
      if (mimeTypeAgainstFilter(arrayOfString, paramString.split("/")))
        return paramString; 
    } 
    return null;
  }
  
  @Nullable
  public static String matches(@Nullable String[] paramArrayOfString, @NonNull String paramString) {
    if (paramArrayOfString == null)
      return null; 
    String[] arrayOfString = paramString.split("/");
    int i = paramArrayOfString.length;
    for (byte b = 0; b < i; b++) {
      String str = paramArrayOfString[b];
      if (mimeTypeAgainstFilter(str.split("/"), arrayOfString))
        return str; 
    } 
    return null;
  }
  
  public static boolean matches(@Nullable String paramString1, @NonNull String paramString2) {
    return (paramString1 == null) ? false : mimeTypeAgainstFilter(paramString1.split("/"), paramString2.split("/"));
  }
  
  @NonNull
  public static String[] matchesMany(@Nullable String[] paramArrayOfString, @NonNull String paramString) {
    byte b = 0;
    if (paramArrayOfString == null)
      return new String[0]; 
    ArrayList<String> arrayList = new ArrayList();
    String[] arrayOfString = paramString.split("/");
    int i = paramArrayOfString.length;
    while (b < i) {
      String str = paramArrayOfString[b];
      if (mimeTypeAgainstFilter(str.split("/"), arrayOfString))
        arrayList.add(str); 
      b++;
    } 
    return arrayList.<String>toArray(new String[arrayList.size()]);
  }
  
  private static boolean mimeTypeAgainstFilter(@NonNull String[] paramArrayOfString1, @NonNull String[] paramArrayOfString2) {
    if (paramArrayOfString2.length != 2)
      throw new IllegalArgumentException("Ill-formatted MIME type filter. Must be type/subtype."); 
    if (paramArrayOfString2[0].isEmpty() || paramArrayOfString2[1].isEmpty())
      throw new IllegalArgumentException("Ill-formatted MIME type filter. Type or subtype empty."); 
    return (paramArrayOfString1.length != 2) ? false : ((!"*".equals(paramArrayOfString2[0]) && !paramArrayOfString2[0].equals(paramArrayOfString1[0])) ? false : (!(!"*".equals(paramArrayOfString2[1]) && !paramArrayOfString2[1].equals(paramArrayOfString1[1]))));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\content\MimeTypeFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */