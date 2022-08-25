package com.google.android.gms.common.internal;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Objects {
  private Objects() {
    throw new AssertionError("Uninstantiable");
  }
  
  public static boolean equal(@Nullable Object paramObject1, @Nullable Object paramObject2) {
    return (paramObject1 == paramObject2 || (paramObject1 != null && paramObject1.equals(paramObject2)));
  }
  
  public static int hashCode(Object... paramVarArgs) {
    return Arrays.hashCode(paramVarArgs);
  }
  
  public static ToStringHelper toStringHelper(Object paramObject) {
    return new ToStringHelper(paramObject, null);
  }
  
  public static final class ToStringHelper {
    private final List<String> zzul;
    
    private final Object zzum;
    
    private ToStringHelper(Object param1Object) {
      this.zzum = Preconditions.checkNotNull(param1Object);
      this.zzul = new ArrayList<>();
    }
    
    public final ToStringHelper add(String param1String, @Nullable Object param1Object) {
      List<String> list = this.zzul;
      param1String = Preconditions.<String>checkNotNull(param1String);
      String str = String.valueOf(param1Object);
      param1Object = new StringBuilder(String.valueOf(param1String).length() + 1 + String.valueOf(str).length());
      param1Object.append(param1String);
      param1Object.append("=");
      param1Object.append(str);
      list.add(param1Object.toString());
      return this;
    }
    
    public final ToStringHelper addValue(@Nullable Object param1Object) {
      this.zzul.add(String.valueOf(param1Object));
      return this;
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder(100);
      stringBuilder.append(this.zzum.getClass().getSimpleName());
      stringBuilder.append('{');
      int i = this.zzul.size();
      for (byte b = 0; b < i; b++) {
        stringBuilder.append(this.zzul.get(b));
        if (b < i - 1)
          stringBuilder.append(", "); 
      } 
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\Objects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */