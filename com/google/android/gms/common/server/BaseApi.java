package com.google.android.gms.common.server;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DeviceProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseApi {
  @Deprecated
  public static String append(String paramString1, String paramString2, String paramString3) {
    byte b;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString1);
    if (paramString1.indexOf("?") != -1) {
      byte b1 = 38;
      b = b1;
    } else {
      byte b1 = 63;
      b = b1;
    } 
    stringBuilder.append(b);
    stringBuilder.append(paramString2);
    stringBuilder.append('=');
    stringBuilder.append(paramString3);
    return stringBuilder.toString();
  }
  
  public static void append(StringBuilder paramStringBuilder, String paramString1, String paramString2) {
    byte b;
    if (paramStringBuilder.indexOf("?") != -1) {
      byte b1 = 38;
      b = b1;
    } else {
      byte b1 = 63;
      b = b1;
    } 
    paramStringBuilder.append(b);
    paramStringBuilder.append(paramString1);
    paramStringBuilder.append('=');
    paramStringBuilder.append(paramString2);
  }
  
  public static String enc(String paramString) {
    Preconditions.checkNotNull(paramString, "Encoding a null parameter!");
    return Uri.encode(paramString);
  }
  
  protected static List<String> enc(List<String> paramList) {
    int i = paramList.size();
    ArrayList<String> arrayList = new ArrayList(i);
    for (byte b = 0; b < i; b++)
      arrayList.add(enc(paramList.get(b))); 
    return arrayList;
  }
  
  public static class BaseApiaryOptions<DerivedClassType extends BaseApiaryOptions<DerivedClassType>> {
    private final ArrayList<String> zzvt = new ArrayList<>();
    
    private final HashMap<String, String> zzvu = new HashMap<>();
    
    private String zzvv;
    
    private final Collector zzvw = new Collector(this);
    
    private static String zzcy() {
      return String.valueOf(DeviceProperties.isUserBuild() ^ true);
    }
    
    public final DerivedClassType addField(String param1String) {
      this.zzvt.add(param1String);
      return (DerivedClassType)this;
    }
    
    @Deprecated
    public final String appendParametersToUrl(String param1String) {
      String str = BaseApi.append(param1String, "prettyPrint", zzcy());
      param1String = str;
      if (this.zzvv != null)
        param1String = BaseApi.append(str, "trace", getTrace()); 
      str = param1String;
      if (!this.zzvt.isEmpty())
        str = BaseApi.append(param1String, "fields", TextUtils.join(",", getFields().toArray())); 
      return str;
    }
    
    public void appendParametersToUrl(StringBuilder param1StringBuilder) {
      BaseApi.append(param1StringBuilder, "prettyPrint", zzcy());
      if (this.zzvv != null)
        BaseApi.append(param1StringBuilder, "trace", getTrace()); 
      if (!this.zzvt.isEmpty())
        BaseApi.append(param1StringBuilder, "fields", TextUtils.join(",", getFields().toArray())); 
    }
    
    public final DerivedClassType buildFrom(BaseApiaryOptions<?> param1BaseApiaryOptions) {
      if (param1BaseApiaryOptions.zzvv != null)
        this.zzvv = param1BaseApiaryOptions.zzvv; 
      if (!param1BaseApiaryOptions.zzvt.isEmpty()) {
        this.zzvt.clear();
        this.zzvt.addAll(param1BaseApiaryOptions.zzvt);
      } 
      return (DerivedClassType)this;
    }
    
    protected final Collector getCollector() {
      return this.zzvw;
    }
    
    public final List<String> getFields() {
      return this.zzvt;
    }
    
    public final Map<String, String> getHeaders() {
      return this.zzvu;
    }
    
    public final String getTrace() {
      return this.zzvv;
    }
    
    public final DerivedClassType setEtag(String param1String) {
      return setHeader("ETag", param1String);
    }
    
    public final DerivedClassType setHeader(String param1String1, String param1String2) {
      this.zzvu.put(param1String1, param1String2);
      return (DerivedClassType)this;
    }
    
    public final DerivedClassType setTraceByLdap(String param1String) {
      String str = String.valueOf("email:");
      param1String = String.valueOf(param1String);
      if (param1String.length() != 0) {
        param1String = str.concat(param1String);
      } else {
        param1String = new String(str);
      } 
      this.zzvv = param1String;
      return (DerivedClassType)this;
    }
    
    public final DerivedClassType setTraceByToken(String param1String) {
      String str = String.valueOf("token:");
      param1String = String.valueOf(param1String);
      if (param1String.length() != 0) {
        param1String = str.concat(param1String);
      } else {
        param1String = new String(str);
      } 
      this.zzvv = param1String;
      return (DerivedClassType)this;
    }
    
    public final class Collector {
      private boolean zzvx;
      
      private boolean zzvy;
      
      private int zzvz;
      
      private StringBuilder zzwa = new StringBuilder();
      
      public Collector(BaseApi.BaseApiaryOptions this$0) {}
      
      private final void append(String param2String) {
        // Byte code:
        //   0: aload_0
        //   1: getfield zzvx : Z
        //   4: ifeq -> 29
        //   7: aload_0
        //   8: iconst_0
        //   9: putfield zzvx : Z
        //   12: aload_0
        //   13: getfield zzwa : Ljava/lang/StringBuilder;
        //   16: astore_2
        //   17: ldc ','
        //   19: astore_3
        //   20: aload_2
        //   21: aload_3
        //   22: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   25: pop
        //   26: goto -> 52
        //   29: aload_0
        //   30: getfield zzvy : Z
        //   33: ifeq -> 52
        //   36: aload_0
        //   37: iconst_0
        //   38: putfield zzvy : Z
        //   41: aload_0
        //   42: getfield zzwa : Ljava/lang/StringBuilder;
        //   45: astore_2
        //   46: ldc '/'
        //   48: astore_3
        //   49: goto -> 20
        //   52: aload_0
        //   53: getfield zzwa : Ljava/lang/StringBuilder;
        //   56: aload_1
        //   57: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   60: pop
        //   61: return
      }
      
      public final void addPiece(String param2String) {
        append(param2String);
        this.zzvy = true;
      }
      
      public final void beginSubCollection(String param2String) {
        append(param2String);
        this.zzwa.append("(");
        this.zzvz++;
      }
      
      public final void endSubCollection() {
        this.zzwa.append(")");
        this.zzvz--;
        if (this.zzvz == 0) {
          this.zzwb.addField(this.zzwa.toString());
          this.zzwa.setLength(0);
          this.zzvx = false;
          this.zzvy = false;
          return;
        } 
        this.zzvx = true;
      }
      
      public final void finishPiece(String param2String) {
        append(param2String);
        if (this.zzvz == 0) {
          this.zzwb.addField(this.zzwa.toString());
          this.zzwa.setLength(0);
          return;
        } 
        this.zzvx = true;
      }
    }
  }
  
  public final class Collector {
    private boolean zzvx;
    
    private boolean zzvy;
    
    private int zzvz;
    
    private StringBuilder zzwa = new StringBuilder();
    
    public Collector(BaseApi this$0) {}
    
    private final void append(String param1String) {
      // Byte code:
      //   0: aload_0
      //   1: getfield zzvx : Z
      //   4: ifeq -> 29
      //   7: aload_0
      //   8: iconst_0
      //   9: putfield zzvx : Z
      //   12: aload_0
      //   13: getfield zzwa : Ljava/lang/StringBuilder;
      //   16: astore_2
      //   17: ldc ','
      //   19: astore_3
      //   20: aload_2
      //   21: aload_3
      //   22: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   25: pop
      //   26: goto -> 52
      //   29: aload_0
      //   30: getfield zzvy : Z
      //   33: ifeq -> 52
      //   36: aload_0
      //   37: iconst_0
      //   38: putfield zzvy : Z
      //   41: aload_0
      //   42: getfield zzwa : Ljava/lang/StringBuilder;
      //   45: astore_2
      //   46: ldc '/'
      //   48: astore_3
      //   49: goto -> 20
      //   52: aload_0
      //   53: getfield zzwa : Ljava/lang/StringBuilder;
      //   56: aload_1
      //   57: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   60: pop
      //   61: return
    }
    
    public final void addPiece(String param1String) {
      append(param1String);
      this.zzvy = true;
    }
    
    public final void beginSubCollection(String param1String) {
      append(param1String);
      this.zzwa.append("(");
      this.zzvz++;
    }
    
    public final void endSubCollection() {
      this.zzwa.append(")");
      this.zzvz--;
      if (this.zzvz == 0) {
        this.zzwb.addField(this.zzwa.toString());
        this.zzwa.setLength(0);
        this.zzvx = false;
        this.zzvy = false;
        return;
      } 
      this.zzvx = true;
    }
    
    public final void finishPiece(String param1String) {
      append(param1String);
      if (this.zzvz == 0) {
        this.zzwb.addField(this.zzwa.toString());
        this.zzwa.setLength(0);
        return;
      } 
      this.zzvx = true;
    }
  }
  
  public static class FieldCollection<Parent> {
    private final BaseApi.BaseApiaryOptions<?>.Collector zzvw;
    
    private final Parent zzwc;
    
    protected FieldCollection(Parent param1Parent, BaseApi.BaseApiaryOptions<?>.Collector param1Collector) {
      FieldCollection fieldCollection;
      Parent parent = param1Parent;
      if (param1Parent == null)
        fieldCollection = this; 
      this.zzwc = (Parent)fieldCollection;
      this.zzvw = param1Collector;
    }
    
    protected BaseApi.BaseApiaryOptions<?>.Collector getCollector() {
      return this.zzvw;
    }
    
    protected Parent getParent() {
      return this.zzwc;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\server\BaseApi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */