package android.support.transition;

import android.animation.TypeEvaluator;

class FloatArrayEvaluator implements TypeEvaluator<float[]> {
  private float[] mArray;
  
  FloatArrayEvaluator(float[] paramArrayOffloat) {
    this.mArray = paramArrayOffloat;
  }
  
  public float[] evaluate(float paramFloat, float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    float[] arrayOfFloat1 = this.mArray;
    float[] arrayOfFloat2 = arrayOfFloat1;
    if (arrayOfFloat1 == null)
      arrayOfFloat2 = new float[paramArrayOffloat1.length]; 
    for (byte b = 0; b < arrayOfFloat2.length; b++) {
      float f = paramArrayOffloat1[b];
      arrayOfFloat2[b] = f + (paramArrayOffloat2[b] - f) * paramFloat;
    } 
    return arrayOfFloat2;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\transition\FloatArrayEvaluator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */