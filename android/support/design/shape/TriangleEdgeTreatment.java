package android.support.design.shape;

import android.support.design.internal.Experimental;

@Experimental("The shapes API is currently experimental and subject to change")
public class TriangleEdgeTreatment extends EdgeTreatment {
  private final boolean inside;
  
  private final float size;
  
  public TriangleEdgeTreatment(float paramFloat, boolean paramBoolean) {
    this.size = paramFloat;
    this.inside = paramBoolean;
  }
  
  public void getEdgePath(float paramFloat1, float paramFloat2, ShapePath paramShapePath) {
    float f2;
    float f1 = paramFloat1 / 2.0F;
    paramShapePath.lineTo(f1 - this.size * paramFloat2, 0.0F);
    if (this.inside) {
      f2 = this.size;
    } else {
      f2 = -this.size;
    } 
    paramShapePath.lineTo(f1, f2 * paramFloat2);
    paramShapePath.lineTo(f1 + this.size * paramFloat2, 0.0F);
    paramShapePath.lineTo(paramFloat1, 0.0F);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\shape\TriangleEdgeTreatment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */