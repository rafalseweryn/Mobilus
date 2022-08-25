package android.support.transition;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

public class SidePropagation extends VisibilityPropagation {
  private float mPropagationSpeed = 3.0F;
  
  private int mSide = 80;
  
  private int distance(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mSide : I
    //   4: istore #10
    //   6: iconst_0
    //   7: istore #11
    //   9: iconst_1
    //   10: istore #12
    //   12: iconst_1
    //   13: istore #13
    //   15: iload #10
    //   17: ldc 8388611
    //   19: if_icmpne -> 53
    //   22: aload_1
    //   23: invokestatic getLayoutDirection : (Landroid/view/View;)I
    //   26: iconst_1
    //   27: if_icmpne -> 33
    //   30: goto -> 36
    //   33: iconst_0
    //   34: istore #13
    //   36: iload #13
    //   38: ifeq -> 47
    //   41: iconst_5
    //   42: istore #13
    //   44: goto -> 94
    //   47: iconst_3
    //   48: istore #13
    //   50: goto -> 94
    //   53: aload_0
    //   54: getfield mSide : I
    //   57: ldc 8388613
    //   59: if_icmpne -> 88
    //   62: aload_1
    //   63: invokestatic getLayoutDirection : (Landroid/view/View;)I
    //   66: iconst_1
    //   67: if_icmpne -> 77
    //   70: iload #12
    //   72: istore #13
    //   74: goto -> 80
    //   77: iconst_0
    //   78: istore #13
    //   80: iload #13
    //   82: ifeq -> 41
    //   85: goto -> 47
    //   88: aload_0
    //   89: getfield mSide : I
    //   92: istore #13
    //   94: iload #13
    //   96: iconst_3
    //   97: if_icmpeq -> 174
    //   100: iload #13
    //   102: iconst_5
    //   103: if_icmpeq -> 158
    //   106: iload #13
    //   108: bipush #48
    //   110: if_icmpeq -> 142
    //   113: iload #13
    //   115: bipush #80
    //   117: if_icmpeq -> 126
    //   120: iload #11
    //   122: istore_2
    //   123: goto -> 187
    //   126: iload_3
    //   127: iload #7
    //   129: isub
    //   130: iload #4
    //   132: iload_2
    //   133: isub
    //   134: invokestatic abs : (I)I
    //   137: iadd
    //   138: istore_2
    //   139: goto -> 187
    //   142: iload #9
    //   144: iload_3
    //   145: isub
    //   146: iload #4
    //   148: iload_2
    //   149: isub
    //   150: invokestatic abs : (I)I
    //   153: iadd
    //   154: istore_2
    //   155: goto -> 187
    //   158: iload_2
    //   159: iload #6
    //   161: isub
    //   162: iload #5
    //   164: iload_3
    //   165: isub
    //   166: invokestatic abs : (I)I
    //   169: iadd
    //   170: istore_2
    //   171: goto -> 187
    //   174: iload #8
    //   176: iload_2
    //   177: isub
    //   178: iload #5
    //   180: iload_3
    //   181: isub
    //   182: invokestatic abs : (I)I
    //   185: iadd
    //   186: istore_2
    //   187: iload_2
    //   188: ireturn
  }
  
  private int getMaxDistance(ViewGroup paramViewGroup) {
    int i = this.mSide;
    return (i != 3 && i != 5 && i != 8388611 && i != 8388613) ? paramViewGroup.getHeight() : paramViewGroup.getWidth();
  }
  
  public long getStartDelay(ViewGroup paramViewGroup, Transition paramTransition, TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2) {
    boolean bool;
    int i2;
    int i3;
    if (paramTransitionValues1 == null && paramTransitionValues2 == null)
      return 0L; 
    Rect rect = paramTransition.getEpicenter();
    if (paramTransitionValues2 == null || getViewVisibility(paramTransitionValues1) == 0) {
      bool = true;
    } else {
      bool = true;
      paramTransitionValues1 = paramTransitionValues2;
    } 
    int i = getViewX(paramTransitionValues1);
    int j = getViewY(paramTransitionValues1);
    int[] arrayOfInt = new int[2];
    paramViewGroup.getLocationOnScreen(arrayOfInt);
    int k = arrayOfInt[0] + Math.round(paramViewGroup.getTranslationX());
    int m = arrayOfInt[1] + Math.round(paramViewGroup.getTranslationY());
    int n = k + paramViewGroup.getWidth();
    int i1 = m + paramViewGroup.getHeight();
    if (rect != null) {
      i2 = rect.centerX();
      int i4 = rect.centerY();
      i3 = i2;
      i2 = i4;
    } else {
      int i4 = (k + n) / 2;
      i2 = (m + i1) / 2;
      i3 = i4;
    } 
    float f = distance((View)paramViewGroup, i, j, i3, i2, k, m, n, i1) / getMaxDistance(paramViewGroup);
    long l1 = paramTransition.getDuration();
    long l2 = l1;
    if (l1 < 0L)
      l2 = 300L; 
    return Math.round((float)(l2 * bool) / this.mPropagationSpeed * f);
  }
  
  public void setPropagationSpeed(float paramFloat) {
    if (paramFloat == 0.0F)
      throw new IllegalArgumentException("propagationSpeed may not be 0"); 
    this.mPropagationSpeed = paramFloat;
  }
  
  public void setSide(int paramInt) {
    this.mSide = paramInt;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\transition\SidePropagation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */