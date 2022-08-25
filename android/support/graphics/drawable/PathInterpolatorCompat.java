package android.support.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.animation.Interpolator;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class PathInterpolatorCompat implements Interpolator {
  public static final double EPSILON = 1.0E-5D;
  
  public static final int MAX_NUM_POINTS = 3000;
  
  private static final float PRECISION = 0.002F;
  
  private float[] mX;
  
  private float[] mY;
  
  public PathInterpolatorCompat(Context paramContext, AttributeSet paramAttributeSet, XmlPullParser paramXmlPullParser) {
    this(paramContext.getResources(), paramContext.getTheme(), paramAttributeSet, paramXmlPullParser);
  }
  
  public PathInterpolatorCompat(Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, XmlPullParser paramXmlPullParser) {
    TypedArray typedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_PATH_INTERPOLATOR);
    parseInterpolatorFromTypeArray(typedArray, paramXmlPullParser);
    typedArray.recycle();
  }
  
  private void initCubic(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    Path path = new Path();
    path.moveTo(0.0F, 0.0F);
    path.cubicTo(paramFloat1, paramFloat2, paramFloat3, paramFloat4, 1.0F, 1.0F);
    initPath(path);
  }
  
  private void initPath(Path paramPath) {
    StringBuilder stringBuilder1;
    byte b = 0;
    PathMeasure pathMeasure = new PathMeasure(paramPath, false);
    float f = pathMeasure.getLength();
    int i = Math.min(3000, (int)(f / 0.002F) + 1);
    if (i <= 0) {
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("The Path has a invalid length ");
      stringBuilder1.append(f);
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    this.mX = new float[i];
    this.mY = new float[i];
    float[] arrayOfFloat2 = new float[2];
    int j;
    for (j = 0; j < i; j++) {
      stringBuilder1.getPosTan(j * f / (i - 1), arrayOfFloat2, null);
      this.mX[j] = arrayOfFloat2[0];
      this.mY[j] = arrayOfFloat2[1];
    } 
    if (Math.abs(this.mX[0]) <= 1.0E-5D && Math.abs(this.mY[0]) <= 1.0E-5D) {
      arrayOfFloat2 = this.mX;
      j = i - 1;
      if (Math.abs(arrayOfFloat2[j] - 1.0F) <= 1.0E-5D && Math.abs(this.mY[j] - 1.0F) <= 1.0E-5D) {
        f = 0.0F;
        for (j = 0; b < i; j++) {
          float f1 = this.mX[j];
          if (f1 < f) {
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append("The Path cannot loop back on itself, x :");
            stringBuilder1.append(f1);
            throw new IllegalArgumentException(stringBuilder1.toString());
          } 
          this.mX[b] = f1;
          b++;
          f = f1;
        } 
        if (stringBuilder1.nextContour())
          throw new IllegalArgumentException("The Path should be continuous, can't have 2+ contours"); 
        return;
      } 
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("The Path must start at (0,0) and end at (1,1) start: ");
    stringBuilder2.append(this.mX[0]);
    stringBuilder2.append(",");
    stringBuilder2.append(this.mY[0]);
    stringBuilder2.append(" end:");
    float[] arrayOfFloat1 = this.mX;
    j = i - 1;
    stringBuilder2.append(arrayOfFloat1[j]);
    stringBuilder2.append(",");
    stringBuilder2.append(this.mY[j]);
    throw new IllegalArgumentException(stringBuilder2.toString());
  }
  
  private void initQuad(float paramFloat1, float paramFloat2) {
    Path path = new Path();
    path.moveTo(0.0F, 0.0F);
    path.quadTo(paramFloat1, paramFloat2, 1.0F, 1.0F);
    initPath(path);
  }
  
  private void parseInterpolatorFromTypeArray(TypedArray paramTypedArray, XmlPullParser paramXmlPullParser) {
    String str;
    StringBuilder stringBuilder;
    if (TypedArrayUtils.hasAttribute(paramXmlPullParser, "pathData")) {
      str = TypedArrayUtils.getNamedString(paramTypedArray, paramXmlPullParser, "pathData", 4);
      Path path = PathParser.createPathFromPathData(str);
      if (path == null) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("The path is null, which is created from ");
        stringBuilder.append(str);
        throw new InflateException(stringBuilder.toString());
      } 
      initPath((Path)stringBuilder);
    } else {
      if (!TypedArrayUtils.hasAttribute((XmlPullParser)stringBuilder, "controlX1"))
        throw new InflateException("pathInterpolator requires the controlX1 attribute"); 
      if (!TypedArrayUtils.hasAttribute((XmlPullParser)stringBuilder, "controlY1"))
        throw new InflateException("pathInterpolator requires the controlY1 attribute"); 
      float f1 = TypedArrayUtils.getNamedFloat((TypedArray)str, (XmlPullParser)stringBuilder, "controlX1", 0, 0.0F);
      float f2 = TypedArrayUtils.getNamedFloat((TypedArray)str, (XmlPullParser)stringBuilder, "controlY1", 1, 0.0F);
      boolean bool = TypedArrayUtils.hasAttribute((XmlPullParser)stringBuilder, "controlX2");
      if (bool != TypedArrayUtils.hasAttribute((XmlPullParser)stringBuilder, "controlY2"))
        throw new InflateException("pathInterpolator requires both controlX2 and controlY2 for cubic Beziers."); 
      if (!bool) {
        initQuad(f1, f2);
      } else {
        initCubic(f1, f2, TypedArrayUtils.getNamedFloat((TypedArray)str, (XmlPullParser)stringBuilder, "controlX2", 2, 0.0F), TypedArrayUtils.getNamedFloat((TypedArray)str, (XmlPullParser)stringBuilder, "controlY2", 3, 0.0F));
      } 
    } 
  }
  
  public float getInterpolation(float paramFloat) {
    if (paramFloat <= 0.0F)
      return 0.0F; 
    if (paramFloat >= 1.0F)
      return 1.0F; 
    int i = 0;
    int j = this.mX.length - 1;
    while (j - i > 1) {
      int k = (i + j) / 2;
      if (paramFloat < this.mX[k]) {
        j = k;
        continue;
      } 
      i = k;
    } 
    float f = this.mX[j] - this.mX[i];
    if (f == 0.0F)
      return this.mY[i]; 
    f = (paramFloat - this.mX[i]) / f;
    paramFloat = this.mY[i];
    return paramFloat + f * (this.mY[j] - paramFloat);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\graphics\drawable\PathInterpolatorCompat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */