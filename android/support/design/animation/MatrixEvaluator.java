package android.support.design.animation;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

public class MatrixEvaluator implements TypeEvaluator<Matrix> {
  private final float[] tempEndValues = new float[9];
  
  private final Matrix tempMatrix = new Matrix();
  
  private final float[] tempStartValues = new float[9];
  
  public Matrix evaluate(float paramFloat, Matrix paramMatrix1, Matrix paramMatrix2) {
    paramMatrix1.getValues(this.tempStartValues);
    paramMatrix2.getValues(this.tempEndValues);
    for (byte b = 0; b < 9; b++) {
      float f1 = this.tempEndValues[b];
      float f2 = this.tempStartValues[b];
      this.tempEndValues[b] = this.tempStartValues[b] + (f1 - f2) * paramFloat;
    } 
    this.tempMatrix.setValues(this.tempEndValues);
    return this.tempMatrix;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\animation\MatrixEvaluator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */