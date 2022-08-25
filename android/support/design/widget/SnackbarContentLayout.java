package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RestrictTo;
import android.support.design.R;
import android.support.design.snackbar.ContentViewCallback;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SnackbarContentLayout extends LinearLayout implements ContentViewCallback {
  private Button actionView;
  
  private int maxInlineActionWidth;
  
  private int maxWidth;
  
  private TextView messageView;
  
  public SnackbarContentLayout(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public SnackbarContentLayout(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SnackbarLayout);
    this.maxWidth = typedArray.getDimensionPixelSize(R.styleable.SnackbarLayout_android_maxWidth, -1);
    this.maxInlineActionWidth = typedArray.getDimensionPixelSize(R.styleable.SnackbarLayout_maxActionInlineWidth, -1);
    typedArray.recycle();
  }
  
  private static void updateTopBottomPadding(View paramView, int paramInt1, int paramInt2) {
    if (ViewCompat.isPaddingRelative(paramView)) {
      ViewCompat.setPaddingRelative(paramView, ViewCompat.getPaddingStart(paramView), paramInt1, ViewCompat.getPaddingEnd(paramView), paramInt2);
    } else {
      paramView.setPadding(paramView.getPaddingLeft(), paramInt1, paramView.getPaddingRight(), paramInt2);
    } 
  }
  
  private boolean updateViewsWithinLayout(int paramInt1, int paramInt2, int paramInt3) {
    boolean bool;
    if (paramInt1 != getOrientation()) {
      setOrientation(paramInt1);
      bool = true;
    } else {
      bool = false;
    } 
    if (this.messageView.getPaddingTop() != paramInt2 || this.messageView.getPaddingBottom() != paramInt3) {
      updateTopBottomPadding((View)this.messageView, paramInt2, paramInt3);
      bool = true;
    } 
    return bool;
  }
  
  public void animateContentIn(int paramInt1, int paramInt2) {
    this.messageView.setAlpha(0.0F);
    ViewPropertyAnimator viewPropertyAnimator = this.messageView.animate().alpha(1.0F);
    long l1 = paramInt2;
    viewPropertyAnimator = viewPropertyAnimator.setDuration(l1);
    long l2 = paramInt1;
    viewPropertyAnimator.setStartDelay(l2).start();
    if (this.actionView.getVisibility() == 0) {
      this.actionView.setAlpha(0.0F);
      this.actionView.animate().alpha(1.0F).setDuration(l1).setStartDelay(l2).start();
    } 
  }
  
  public void animateContentOut(int paramInt1, int paramInt2) {
    this.messageView.setAlpha(1.0F);
    ViewPropertyAnimator viewPropertyAnimator = this.messageView.animate().alpha(0.0F);
    long l1 = paramInt2;
    viewPropertyAnimator = viewPropertyAnimator.setDuration(l1);
    long l2 = paramInt1;
    viewPropertyAnimator.setStartDelay(l2).start();
    if (this.actionView.getVisibility() == 0) {
      this.actionView.setAlpha(1.0F);
      this.actionView.animate().alpha(0.0F).setDuration(l1).setStartDelay(l2).start();
    } 
  }
  
  public Button getActionView() {
    return this.actionView;
  }
  
  public TextView getMessageView() {
    return this.messageView;
  }
  
  protected void onFinishInflate() {
    super.onFinishInflate();
    this.messageView = (TextView)findViewById(R.id.snackbar_text);
    this.actionView = (Button)findViewById(R.id.snackbar_action);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: iload_1
    //   2: iload_2
    //   3: invokespecial onMeasure : (II)V
    //   6: iload_1
    //   7: istore_3
    //   8: aload_0
    //   9: getfield maxWidth : I
    //   12: ifle -> 44
    //   15: iload_1
    //   16: istore_3
    //   17: aload_0
    //   18: invokevirtual getMeasuredWidth : ()I
    //   21: aload_0
    //   22: getfield maxWidth : I
    //   25: if_icmple -> 44
    //   28: aload_0
    //   29: getfield maxWidth : I
    //   32: ldc 1073741824
    //   34: invokestatic makeMeasureSpec : (II)I
    //   37: istore_3
    //   38: aload_0
    //   39: iload_3
    //   40: iload_2
    //   41: invokespecial onMeasure : (II)V
    //   44: aload_0
    //   45: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   48: getstatic android/support/design/R$dimen.design_snackbar_padding_vertical_2lines : I
    //   51: invokevirtual getDimensionPixelSize : (I)I
    //   54: istore #4
    //   56: aload_0
    //   57: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   60: getstatic android/support/design/R$dimen.design_snackbar_padding_vertical : I
    //   63: invokevirtual getDimensionPixelSize : (I)I
    //   66: istore #5
    //   68: aload_0
    //   69: getfield messageView : Landroid/widget/TextView;
    //   72: invokevirtual getLayout : ()Landroid/text/Layout;
    //   75: invokevirtual getLineCount : ()I
    //   78: istore_1
    //   79: iconst_1
    //   80: istore #6
    //   82: iload_1
    //   83: iconst_1
    //   84: if_icmple -> 92
    //   87: iconst_1
    //   88: istore_1
    //   89: goto -> 94
    //   92: iconst_0
    //   93: istore_1
    //   94: iload_1
    //   95: ifeq -> 140
    //   98: aload_0
    //   99: getfield maxInlineActionWidth : I
    //   102: ifle -> 140
    //   105: aload_0
    //   106: getfield actionView : Landroid/widget/Button;
    //   109: invokevirtual getMeasuredWidth : ()I
    //   112: aload_0
    //   113: getfield maxInlineActionWidth : I
    //   116: if_icmple -> 140
    //   119: aload_0
    //   120: iconst_1
    //   121: iload #4
    //   123: iload #4
    //   125: iload #5
    //   127: isub
    //   128: invokespecial updateViewsWithinLayout : (III)Z
    //   131: ifeq -> 169
    //   134: iload #6
    //   136: istore_1
    //   137: goto -> 171
    //   140: iload_1
    //   141: ifeq -> 150
    //   144: iload #4
    //   146: istore_1
    //   147: goto -> 153
    //   150: iload #5
    //   152: istore_1
    //   153: aload_0
    //   154: iconst_0
    //   155: iload_1
    //   156: iload_1
    //   157: invokespecial updateViewsWithinLayout : (III)Z
    //   160: ifeq -> 169
    //   163: iload #6
    //   165: istore_1
    //   166: goto -> 171
    //   169: iconst_0
    //   170: istore_1
    //   171: iload_1
    //   172: ifeq -> 181
    //   175: aload_0
    //   176: iload_3
    //   177: iload_2
    //   178: invokespecial onMeasure : (II)V
    //   181: return
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\widget\SnackbarContentLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */