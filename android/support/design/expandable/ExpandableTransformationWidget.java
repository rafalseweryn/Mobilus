package android.support.design.expandable;

import android.support.annotation.IdRes;

public interface ExpandableTransformationWidget extends ExpandableWidget {
  @IdRes
  int getExpandedComponentIdHint();
  
  void setExpandedComponentIdHint(@IdRes int paramInt);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\expandable\ExpandableTransformationWidget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */