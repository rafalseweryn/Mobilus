package android.support.design.animation;

import android.support.design.R;
import android.util.Property;
import android.view.ViewGroup;

public class ChildrenAlphaProperty extends Property<ViewGroup, Float> {
  public static final Property<ViewGroup, Float> CHILDREN_ALPHA = new ChildrenAlphaProperty("childrenAlpha");
  
  private ChildrenAlphaProperty(String paramString) {
    super(Float.class, paramString);
  }
  
  public Float get(ViewGroup paramViewGroup) {
    Float float_ = (Float)paramViewGroup.getTag(R.id.mtrl_internal_children_alpha_tag);
    return (float_ != null) ? float_ : Float.valueOf(1.0F);
  }
  
  public void set(ViewGroup paramViewGroup, Float paramFloat) {
    float f = paramFloat.floatValue();
    paramViewGroup.setTag(R.id.mtrl_internal_children_alpha_tag, Float.valueOf(f));
    int i = paramViewGroup.getChildCount();
    for (byte b = 0; b < i; b++)
      paramViewGroup.getChildAt(b).setAlpha(f); 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\animation\ChildrenAlphaProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */