package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Picture;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

class TransitionUtils {
  private static final boolean HAS_IS_ATTACHED_TO_WINDOW;
  
  private static final boolean HAS_OVERLAY;
  
  private static final boolean HAS_PICTURE_BITMAP;
  
  private static final int MAX_IMAGE_SIZE = 1048576;
  
  static {
    int i = Build.VERSION.SDK_INT;
    boolean bool1 = false;
    if (i >= 19) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    HAS_IS_ATTACHED_TO_WINDOW = bool2;
    if (Build.VERSION.SDK_INT >= 18) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    HAS_OVERLAY = bool2;
    boolean bool2 = bool1;
    if (Build.VERSION.SDK_INT >= 28)
      bool2 = true; 
    HAS_PICTURE_BITMAP = bool2;
  }
  
  static View copyViewImage(ViewGroup paramViewGroup, View paramView1, View paramView2) {
    Matrix matrix = new Matrix();
    matrix.setTranslate(-paramView2.getScrollX(), -paramView2.getScrollY());
    ViewUtils.transformMatrixToGlobal(paramView1, matrix);
    ViewUtils.transformMatrixToLocal((View)paramViewGroup, matrix);
    RectF rectF = new RectF(0.0F, 0.0F, paramView1.getWidth(), paramView1.getHeight());
    matrix.mapRect(rectF);
    int i = Math.round(rectF.left);
    int j = Math.round(rectF.top);
    int k = Math.round(rectF.right);
    int m = Math.round(rectF.bottom);
    ImageView imageView = new ImageView(paramView1.getContext());
    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    Bitmap bitmap = createViewBitmap(paramView1, matrix, rectF, paramViewGroup);
    if (bitmap != null)
      imageView.setImageBitmap(bitmap); 
    imageView.measure(View.MeasureSpec.makeMeasureSpec(k - i, 1073741824), View.MeasureSpec.makeMeasureSpec(m - j, 1073741824));
    imageView.layout(i, j, k, m);
    return (View)imageView;
  }
  
  private static Bitmap createViewBitmap(View paramView, Matrix paramMatrix, RectF paramRectF, ViewGroup paramViewGroup) {
    boolean bool1;
    boolean bool2;
    ViewGroup viewGroup;
    boolean bool3;
    Bitmap bitmap;
    if (HAS_IS_ATTACHED_TO_WINDOW) {
      bool1 = paramView.isAttachedToWindow() ^ true;
      if (paramViewGroup == null) {
        bool2 = false;
      } else {
        bool2 = paramViewGroup.isAttachedToWindow();
      } 
    } else {
      bool1 = false;
      bool2 = bool1;
    } 
    boolean bool = HAS_OVERLAY;
    Canvas canvas1 = null;
    if (bool && bool1) {
      if (!bool2)
        return null; 
      viewGroup = (ViewGroup)paramView.getParent();
      bool3 = viewGroup.indexOfChild(paramView);
      paramViewGroup.getOverlay().add(paramView);
    } else {
      bool3 = false;
      viewGroup = null;
    } 
    int i = Math.round(paramRectF.width());
    int j = Math.round(paramRectF.height());
    Canvas canvas2 = canvas1;
    if (i > 0) {
      canvas2 = canvas1;
      if (j > 0) {
        float f = Math.min(1.0F, 1048576.0F / (i * j));
        i = Math.round(i * f);
        j = Math.round(j * f);
        paramMatrix.postTranslate(-paramRectF.left, -paramRectF.top);
        paramMatrix.postScale(f, f);
        if (HAS_PICTURE_BITMAP) {
          Picture picture = new Picture();
          canvas2 = picture.beginRecording(i, j);
          canvas2.concat(paramMatrix);
          paramView.draw(canvas2);
          picture.endRecording();
          bitmap = Bitmap.createBitmap(picture);
        } else {
          bitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
          Canvas canvas = new Canvas(bitmap);
          canvas.concat(paramMatrix);
          paramView.draw(canvas);
        } 
      } 
    } 
    if (HAS_OVERLAY && bool1) {
      paramViewGroup.getOverlay().remove(paramView);
      viewGroup.addView(paramView, bool3);
    } 
    return bitmap;
  }
  
  static Animator mergeAnimators(Animator paramAnimator1, Animator paramAnimator2) {
    if (paramAnimator1 == null)
      return paramAnimator2; 
    if (paramAnimator2 == null)
      return paramAnimator1; 
    AnimatorSet animatorSet = new AnimatorSet();
    animatorSet.playTogether(new Animator[] { paramAnimator1, paramAnimator2 });
    return (Animator)animatorSet;
  }
  
  static class MatrixEvaluator implements TypeEvaluator<Matrix> {
    final float[] mTempEndValues = new float[9];
    
    final Matrix mTempMatrix = new Matrix();
    
    final float[] mTempStartValues = new float[9];
    
    public Matrix evaluate(float param1Float, Matrix param1Matrix1, Matrix param1Matrix2) {
      param1Matrix1.getValues(this.mTempStartValues);
      param1Matrix2.getValues(this.mTempEndValues);
      for (byte b = 0; b < 9; b++) {
        float f1 = this.mTempEndValues[b];
        float f2 = this.mTempStartValues[b];
        this.mTempEndValues[b] = this.mTempStartValues[b] + (f1 - f2) * param1Float;
      } 
      this.mTempMatrix.setValues(this.mTempEndValues);
      return this.mTempMatrix;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\transition\TransitionUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */