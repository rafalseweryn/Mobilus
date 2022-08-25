package android.support.design.theme;

import android.content.Context;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatViewInflater;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

@Keep
public class MaterialComponentsViewInflater extends AppCompatViewInflater {
  @NonNull
  protected AppCompatButton createButton(Context paramContext, AttributeSet paramAttributeSet) {
    return (AppCompatButton)new MaterialButton(paramContext, paramAttributeSet);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\design\theme\MaterialComponentsViewInflater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */