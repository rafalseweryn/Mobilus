package android.support.design.canvas;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class CanvasCompat {
  public static int saveLayerAlpha(Canvas paramCanvas, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt) {
    return (Build.VERSION.SDK_INT > 21) ? paramCanvas.saveLayerAlpha(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramInt) : paramCanvas.saveLayerAlpha(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramInt, 31);
  }
  
  public static int saveLayerAlpha(Canvas paramCanvas, RectF paramRectF, int paramInt) {
    return (Build.VERSION.SDK_INT > 21) ? paramCanvas.saveLayerAlpha(paramRectF, paramInt) : paramCanvas.saveLayerAlpha(paramRectF, paramInt, 31);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\canvas\CanvasCompat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */