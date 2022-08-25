package android.support.v4.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Shader;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class ComplexColorCompat {
  private static final String LOG_TAG = "ComplexColorCompat";
  
  private int mColor;
  
  private final ColorStateList mColorStateList;
  
  private final Shader mShader;
  
  private ComplexColorCompat(Shader paramShader, ColorStateList paramColorStateList, @ColorInt int paramInt) {
    this.mShader = paramShader;
    this.mColorStateList = paramColorStateList;
    this.mColor = paramInt;
  }
  
  @NonNull
  private static ComplexColorCompat createFromXml(@NonNull Resources paramResources, @ColorRes int paramInt, @Nullable Resources.Theme paramTheme) throws IOException, XmlPullParserException {
    // Byte code:
    //   0: aload_0
    //   1: iload_1
    //   2: invokevirtual getXml : (I)Landroid/content/res/XmlResourceParser;
    //   5: astore_3
    //   6: aload_3
    //   7: invokestatic asAttributeSet : (Lorg/xmlpull/v1/XmlPullParser;)Landroid/util/AttributeSet;
    //   10: astore #4
    //   12: aload_3
    //   13: invokeinterface next : ()I
    //   18: istore #5
    //   20: iconst_1
    //   21: istore_1
    //   22: iload #5
    //   24: iconst_2
    //   25: if_icmpeq -> 37
    //   28: iload #5
    //   30: iconst_1
    //   31: if_icmpeq -> 37
    //   34: goto -> 12
    //   37: iload #5
    //   39: iconst_2
    //   40: if_icmpeq -> 53
    //   43: new org/xmlpull/v1/XmlPullParserException
    //   46: dup
    //   47: ldc 'No start tag found'
    //   49: invokespecial <init> : (Ljava/lang/String;)V
    //   52: athrow
    //   53: aload_3
    //   54: invokeinterface getName : ()Ljava/lang/String;
    //   59: astore #6
    //   61: aload #6
    //   63: invokevirtual hashCode : ()I
    //   66: istore #5
    //   68: iload #5
    //   70: ldc 89650992
    //   72: if_icmpeq -> 100
    //   75: iload #5
    //   77: ldc 1191572447
    //   79: if_icmpeq -> 85
    //   82: goto -> 113
    //   85: aload #6
    //   87: ldc 'selector'
    //   89: invokevirtual equals : (Ljava/lang/Object;)Z
    //   92: ifeq -> 113
    //   95: iconst_0
    //   96: istore_1
    //   97: goto -> 115
    //   100: aload #6
    //   102: ldc 'gradient'
    //   104: invokevirtual equals : (Ljava/lang/Object;)Z
    //   107: ifeq -> 113
    //   110: goto -> 115
    //   113: iconst_m1
    //   114: istore_1
    //   115: iload_1
    //   116: tableswitch default -> 140, 0 -> 197, 1 -> 185
    //   140: new java/lang/StringBuilder
    //   143: dup
    //   144: invokespecial <init> : ()V
    //   147: astore_0
    //   148: aload_0
    //   149: aload_3
    //   150: invokeinterface getPositionDescription : ()Ljava/lang/String;
    //   155: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: pop
    //   159: aload_0
    //   160: ldc ': unsupported complex color tag '
    //   162: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: pop
    //   166: aload_0
    //   167: aload #6
    //   169: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: pop
    //   173: new org/xmlpull/v1/XmlPullParserException
    //   176: dup
    //   177: aload_0
    //   178: invokevirtual toString : ()Ljava/lang/String;
    //   181: invokespecial <init> : (Ljava/lang/String;)V
    //   184: athrow
    //   185: aload_0
    //   186: aload_3
    //   187: aload #4
    //   189: aload_2
    //   190: invokestatic createFromXmlInner : (Landroid/content/res/Resources;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;Landroid/content/res/Resources$Theme;)Landroid/graphics/Shader;
    //   193: invokestatic from : (Landroid/graphics/Shader;)Landroid/support/v4/content/res/ComplexColorCompat;
    //   196: areturn
    //   197: aload_0
    //   198: aload_3
    //   199: aload #4
    //   201: aload_2
    //   202: invokestatic createFromXmlInner : (Landroid/content/res/Resources;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;Landroid/content/res/Resources$Theme;)Landroid/content/res/ColorStateList;
    //   205: invokestatic from : (Landroid/content/res/ColorStateList;)Landroid/support/v4/content/res/ComplexColorCompat;
    //   208: areturn
  }
  
  static ComplexColorCompat from(@ColorInt int paramInt) {
    return new ComplexColorCompat(null, null, paramInt);
  }
  
  static ComplexColorCompat from(@NonNull ColorStateList paramColorStateList) {
    return new ComplexColorCompat(null, paramColorStateList, paramColorStateList.getDefaultColor());
  }
  
  static ComplexColorCompat from(@NonNull Shader paramShader) {
    return new ComplexColorCompat(paramShader, null, 0);
  }
  
  @Nullable
  public static ComplexColorCompat inflate(@NonNull Resources paramResources, @ColorRes int paramInt, @Nullable Resources.Theme paramTheme) {
    try {
      return createFromXml(paramResources, paramInt, paramTheme);
    } catch (Exception exception) {
      Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", exception);
      return null;
    } 
  }
  
  @ColorInt
  public int getColor() {
    return this.mColor;
  }
  
  @Nullable
  public Shader getShader() {
    return this.mShader;
  }
  
  public boolean isGradient() {
    boolean bool;
    if (this.mShader != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isStateful() {
    boolean bool;
    if (this.mShader == null && this.mColorStateList != null && this.mColorStateList.isStateful()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean onStateChanged(int[] paramArrayOfint) {
    if (isStateful()) {
      int i = this.mColorStateList.getColorForState(paramArrayOfint, this.mColorStateList.getDefaultColor());
      if (i != this.mColor) {
        boolean bool = true;
        this.mColor = i;
        return bool;
      } 
    } 
    return false;
  }
  
  public void setColor(@ColorInt int paramInt) {
    this.mColor = paramInt;
  }
  
  public boolean willDraw() {
    return (isGradient() || this.mColor != 0);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\content\res\ComplexColorCompat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */