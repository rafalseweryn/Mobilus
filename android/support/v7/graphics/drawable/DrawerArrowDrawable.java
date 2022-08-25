package android.support.v7.graphics.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class DrawerArrowDrawable extends Drawable {
  public static final int ARROW_DIRECTION_END = 3;
  
  public static final int ARROW_DIRECTION_LEFT = 0;
  
  public static final int ARROW_DIRECTION_RIGHT = 1;
  
  public static final int ARROW_DIRECTION_START = 2;
  
  private static final float ARROW_HEAD_ANGLE = (float)Math.toRadians(45.0D);
  
  private float mArrowHeadLength;
  
  private float mArrowShaftLength;
  
  private float mBarGap;
  
  private float mBarLength;
  
  private int mDirection = 2;
  
  private float mMaxCutForBarSize;
  
  private final Paint mPaint = new Paint();
  
  private final Path mPath = new Path();
  
  private float mProgress;
  
  private final int mSize;
  
  private boolean mSpin;
  
  private boolean mVerticalMirror = false;
  
  public DrawerArrowDrawable(Context paramContext) {
    this.mPaint.setStyle(Paint.Style.STROKE);
    this.mPaint.setStrokeJoin(Paint.Join.MITER);
    this.mPaint.setStrokeCap(Paint.Cap.BUTT);
    this.mPaint.setAntiAlias(true);
    TypedArray typedArray = paramContext.getTheme().obtainStyledAttributes(null, R.styleable.DrawerArrowToggle, R.attr.drawerArrowStyle, R.style.Base_Widget_AppCompat_DrawerArrowToggle);
    setColor(typedArray.getColor(R.styleable.DrawerArrowToggle_color, 0));
    setBarThickness(typedArray.getDimension(R.styleable.DrawerArrowToggle_thickness, 0.0F));
    setSpinEnabled(typedArray.getBoolean(R.styleable.DrawerArrowToggle_spinBars, true));
    setGapSize(Math.round(typedArray.getDimension(R.styleable.DrawerArrowToggle_gapBetweenBars, 0.0F)));
    this.mSize = typedArray.getDimensionPixelSize(R.styleable.DrawerArrowToggle_drawableSize, 0);
    this.mBarLength = Math.round(typedArray.getDimension(R.styleable.DrawerArrowToggle_barLength, 0.0F));
    this.mArrowHeadLength = Math.round(typedArray.getDimension(R.styleable.DrawerArrowToggle_arrowHeadLength, 0.0F));
    this.mArrowShaftLength = typedArray.getDimension(R.styleable.DrawerArrowToggle_arrowShaftLength, 0.0F);
    typedArray.recycle();
  }
  
  private static float lerp(float paramFloat1, float paramFloat2, float paramFloat3) {
    return paramFloat1 + (paramFloat2 - paramFloat1) * paramFloat3;
  }
  
  public void draw(Canvas paramCanvas) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getBounds : ()Landroid/graphics/Rect;
    //   4: astore_2
    //   5: aload_0
    //   6: getfield mDirection : I
    //   9: istore_3
    //   10: iconst_0
    //   11: istore #4
    //   13: iconst_1
    //   14: istore #5
    //   16: iload_3
    //   17: iconst_3
    //   18: if_icmpeq -> 66
    //   21: iload #4
    //   23: istore #6
    //   25: iload_3
    //   26: tableswitch default -> 48, 0 -> 80, 1 -> 60
    //   48: iload #4
    //   50: istore #6
    //   52: aload_0
    //   53: invokestatic getLayoutDirection : (Landroid/graphics/drawable/Drawable;)I
    //   56: iconst_1
    //   57: if_icmpne -> 80
    //   60: iconst_1
    //   61: istore #6
    //   63: goto -> 80
    //   66: iload #4
    //   68: istore #6
    //   70: aload_0
    //   71: invokestatic getLayoutDirection : (Landroid/graphics/drawable/Drawable;)I
    //   74: ifne -> 80
    //   77: goto -> 60
    //   80: aload_0
    //   81: getfield mArrowHeadLength : F
    //   84: aload_0
    //   85: getfield mArrowHeadLength : F
    //   88: fmul
    //   89: fconst_2
    //   90: fmul
    //   91: f2d
    //   92: invokestatic sqrt : (D)D
    //   95: d2f
    //   96: fstore #7
    //   98: aload_0
    //   99: getfield mBarLength : F
    //   102: fload #7
    //   104: aload_0
    //   105: getfield mProgress : F
    //   108: invokestatic lerp : (FFF)F
    //   111: fstore #8
    //   113: aload_0
    //   114: getfield mBarLength : F
    //   117: aload_0
    //   118: getfield mArrowShaftLength : F
    //   121: aload_0
    //   122: getfield mProgress : F
    //   125: invokestatic lerp : (FFF)F
    //   128: fstore #9
    //   130: fconst_0
    //   131: aload_0
    //   132: getfield mMaxCutForBarSize : F
    //   135: aload_0
    //   136: getfield mProgress : F
    //   139: invokestatic lerp : (FFF)F
    //   142: invokestatic round : (F)I
    //   145: i2f
    //   146: fstore #10
    //   148: fconst_0
    //   149: getstatic android/support/v7/graphics/drawable/DrawerArrowDrawable.ARROW_HEAD_ANGLE : F
    //   152: aload_0
    //   153: getfield mProgress : F
    //   156: invokestatic lerp : (FFF)F
    //   159: fstore #11
    //   161: iload #6
    //   163: ifeq -> 172
    //   166: fconst_0
    //   167: fstore #7
    //   169: goto -> 176
    //   172: ldc -180.0
    //   174: fstore #7
    //   176: iload #6
    //   178: ifeq -> 188
    //   181: ldc 180.0
    //   183: fstore #12
    //   185: goto -> 191
    //   188: fconst_0
    //   189: fstore #12
    //   191: fload #7
    //   193: fload #12
    //   195: aload_0
    //   196: getfield mProgress : F
    //   199: invokestatic lerp : (FFF)F
    //   202: fstore #7
    //   204: fload #8
    //   206: f2d
    //   207: dstore #13
    //   209: fload #11
    //   211: f2d
    //   212: dstore #15
    //   214: dload #13
    //   216: dload #15
    //   218: invokestatic cos : (D)D
    //   221: dmul
    //   222: invokestatic round : (D)J
    //   225: l2f
    //   226: fstore #12
    //   228: dload #13
    //   230: dload #15
    //   232: invokestatic sin : (D)D
    //   235: dmul
    //   236: invokestatic round : (D)J
    //   239: l2f
    //   240: fstore #11
    //   242: aload_0
    //   243: getfield mPath : Landroid/graphics/Path;
    //   246: invokevirtual rewind : ()V
    //   249: aload_0
    //   250: getfield mBarGap : F
    //   253: aload_0
    //   254: getfield mPaint : Landroid/graphics/Paint;
    //   257: invokevirtual getStrokeWidth : ()F
    //   260: fadd
    //   261: aload_0
    //   262: getfield mMaxCutForBarSize : F
    //   265: fneg
    //   266: aload_0
    //   267: getfield mProgress : F
    //   270: invokestatic lerp : (FFF)F
    //   273: fstore #8
    //   275: fload #9
    //   277: fneg
    //   278: fconst_2
    //   279: fdiv
    //   280: fstore #17
    //   282: aload_0
    //   283: getfield mPath : Landroid/graphics/Path;
    //   286: fload #17
    //   288: fload #10
    //   290: fadd
    //   291: fconst_0
    //   292: invokevirtual moveTo : (FF)V
    //   295: aload_0
    //   296: getfield mPath : Landroid/graphics/Path;
    //   299: fload #9
    //   301: fload #10
    //   303: fconst_2
    //   304: fmul
    //   305: fsub
    //   306: fconst_0
    //   307: invokevirtual rLineTo : (FF)V
    //   310: aload_0
    //   311: getfield mPath : Landroid/graphics/Path;
    //   314: fload #17
    //   316: fload #8
    //   318: invokevirtual moveTo : (FF)V
    //   321: aload_0
    //   322: getfield mPath : Landroid/graphics/Path;
    //   325: fload #12
    //   327: fload #11
    //   329: invokevirtual rLineTo : (FF)V
    //   332: aload_0
    //   333: getfield mPath : Landroid/graphics/Path;
    //   336: fload #17
    //   338: fload #8
    //   340: fneg
    //   341: invokevirtual moveTo : (FF)V
    //   344: aload_0
    //   345: getfield mPath : Landroid/graphics/Path;
    //   348: fload #12
    //   350: fload #11
    //   352: fneg
    //   353: invokevirtual rLineTo : (FF)V
    //   356: aload_0
    //   357: getfield mPath : Landroid/graphics/Path;
    //   360: invokevirtual close : ()V
    //   363: aload_1
    //   364: invokevirtual save : ()I
    //   367: pop
    //   368: aload_0
    //   369: getfield mPaint : Landroid/graphics/Paint;
    //   372: invokevirtual getStrokeWidth : ()F
    //   375: fstore #10
    //   377: aload_2
    //   378: invokevirtual height : ()I
    //   381: i2f
    //   382: ldc_w 3.0
    //   385: fload #10
    //   387: fmul
    //   388: fsub
    //   389: aload_0
    //   390: getfield mBarGap : F
    //   393: fconst_2
    //   394: fmul
    //   395: fsub
    //   396: f2i
    //   397: iconst_4
    //   398: idiv
    //   399: iconst_2
    //   400: imul
    //   401: i2f
    //   402: fstore #9
    //   404: aload_0
    //   405: getfield mBarGap : F
    //   408: fstore #12
    //   410: aload_1
    //   411: aload_2
    //   412: invokevirtual centerX : ()I
    //   415: i2f
    //   416: fload #9
    //   418: fload #10
    //   420: ldc_w 1.5
    //   423: fmul
    //   424: fload #12
    //   426: fadd
    //   427: fadd
    //   428: invokevirtual translate : (FF)V
    //   431: aload_0
    //   432: getfield mSpin : Z
    //   435: ifeq -> 464
    //   438: aload_0
    //   439: getfield mVerticalMirror : Z
    //   442: iload #6
    //   444: ixor
    //   445: ifeq -> 451
    //   448: iconst_m1
    //   449: istore #5
    //   451: aload_1
    //   452: fload #7
    //   454: iload #5
    //   456: i2f
    //   457: fmul
    //   458: invokevirtual rotate : (F)V
    //   461: goto -> 475
    //   464: iload #6
    //   466: ifeq -> 475
    //   469: aload_1
    //   470: ldc 180.0
    //   472: invokevirtual rotate : (F)V
    //   475: aload_1
    //   476: aload_0
    //   477: getfield mPath : Landroid/graphics/Path;
    //   480: aload_0
    //   481: getfield mPaint : Landroid/graphics/Paint;
    //   484: invokevirtual drawPath : (Landroid/graphics/Path;Landroid/graphics/Paint;)V
    //   487: aload_1
    //   488: invokevirtual restore : ()V
    //   491: return
  }
  
  public float getArrowHeadLength() {
    return this.mArrowHeadLength;
  }
  
  public float getArrowShaftLength() {
    return this.mArrowShaftLength;
  }
  
  public float getBarLength() {
    return this.mBarLength;
  }
  
  public float getBarThickness() {
    return this.mPaint.getStrokeWidth();
  }
  
  @ColorInt
  public int getColor() {
    return this.mPaint.getColor();
  }
  
  public int getDirection() {
    return this.mDirection;
  }
  
  public float getGapSize() {
    return this.mBarGap;
  }
  
  public int getIntrinsicHeight() {
    return this.mSize;
  }
  
  public int getIntrinsicWidth() {
    return this.mSize;
  }
  
  public int getOpacity() {
    return -3;
  }
  
  public final Paint getPaint() {
    return this.mPaint;
  }
  
  @FloatRange(from = 0.0D, to = 1.0D)
  public float getProgress() {
    return this.mProgress;
  }
  
  public boolean isSpinEnabled() {
    return this.mSpin;
  }
  
  public void setAlpha(int paramInt) {
    if (paramInt != this.mPaint.getAlpha()) {
      this.mPaint.setAlpha(paramInt);
      invalidateSelf();
    } 
  }
  
  public void setArrowHeadLength(float paramFloat) {
    if (this.mArrowHeadLength != paramFloat) {
      this.mArrowHeadLength = paramFloat;
      invalidateSelf();
    } 
  }
  
  public void setArrowShaftLength(float paramFloat) {
    if (this.mArrowShaftLength != paramFloat) {
      this.mArrowShaftLength = paramFloat;
      invalidateSelf();
    } 
  }
  
  public void setBarLength(float paramFloat) {
    if (this.mBarLength != paramFloat) {
      this.mBarLength = paramFloat;
      invalidateSelf();
    } 
  }
  
  public void setBarThickness(float paramFloat) {
    if (this.mPaint.getStrokeWidth() != paramFloat) {
      this.mPaint.setStrokeWidth(paramFloat);
      this.mMaxCutForBarSize = (float)((paramFloat / 2.0F) * Math.cos(ARROW_HEAD_ANGLE));
      invalidateSelf();
    } 
  }
  
  public void setColor(@ColorInt int paramInt) {
    if (paramInt != this.mPaint.getColor()) {
      this.mPaint.setColor(paramInt);
      invalidateSelf();
    } 
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    this.mPaint.setColorFilter(paramColorFilter);
    invalidateSelf();
  }
  
  public void setDirection(int paramInt) {
    if (paramInt != this.mDirection) {
      this.mDirection = paramInt;
      invalidateSelf();
    } 
  }
  
  public void setGapSize(float paramFloat) {
    if (paramFloat != this.mBarGap) {
      this.mBarGap = paramFloat;
      invalidateSelf();
    } 
  }
  
  public void setProgress(@FloatRange(from = 0.0D, to = 1.0D) float paramFloat) {
    if (this.mProgress != paramFloat) {
      this.mProgress = paramFloat;
      invalidateSelf();
    } 
  }
  
  public void setSpinEnabled(boolean paramBoolean) {
    if (this.mSpin != paramBoolean) {
      this.mSpin = paramBoolean;
      invalidateSelf();
    } 
  }
  
  public void setVerticalMirror(boolean paramBoolean) {
    if (this.mVerticalMirror != paramBoolean) {
      this.mVerticalMirror = paramBoolean;
      invalidateSelf();
    } 
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface ArrowDirection {}
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v7\graphics\drawable\DrawerArrowDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */