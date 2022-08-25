package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.widget.Button;
import com.google.android.gms.base.R;
import com.google.android.gms.common.util.DeviceProperties;

public final class SignInButtonImpl extends Button {
  public SignInButtonImpl(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public SignInButtonImpl(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet, 16842824);
  }
  
  private static int zza(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    StringBuilder stringBuilder;
    switch (paramInt1) {
      default:
        stringBuilder = new StringBuilder(33);
        stringBuilder.append("Unknown color scheme: ");
        stringBuilder.append(paramInt1);
        throw new IllegalStateException(stringBuilder.toString());
      case 2:
        return paramInt4;
      case 1:
        return paramInt3;
      case 0:
        break;
    } 
    return paramInt2;
  }
  
  public final void configure(Resources paramResources, int paramInt1, int paramInt2) {
    StringBuilder stringBuilder;
    setTypeface(Typeface.DEFAULT_BOLD);
    setTextSize(14.0F);
    int i = (int)((paramResources.getDisplayMetrics()).density * 48.0F + 0.5F);
    setMinHeight(i);
    setMinWidth(i);
    i = zza(paramInt2, R.drawable.common_google_signin_btn_icon_dark, R.drawable.common_google_signin_btn_icon_light, R.drawable.common_google_signin_btn_icon_light);
    int j = zza(paramInt2, R.drawable.common_google_signin_btn_text_dark, R.drawable.common_google_signin_btn_text_light, R.drawable.common_google_signin_btn_text_light);
    switch (paramInt1) {
      default:
        stringBuilder = new StringBuilder(32);
        stringBuilder.append("Unknown button size: ");
        stringBuilder.append(paramInt1);
        throw new IllegalStateException(stringBuilder.toString());
      case 0:
      case 1:
        i = j;
        break;
      case 2:
        break;
    } 
    Drawable drawable = DrawableCompat.wrap(stringBuilder.getDrawable(i));
    DrawableCompat.setTintList(drawable, stringBuilder.getColorStateList(R.color.common_google_signin_btn_tint));
    DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_ATOP);
    setBackgroundDrawable(drawable);
    setTextColor(Preconditions.<ColorStateList>checkNotNull(stringBuilder.getColorStateList(zza(paramInt2, R.color.common_google_signin_btn_text_dark, R.color.common_google_signin_btn_text_light, R.color.common_google_signin_btn_text_light))));
    switch (paramInt1) {
      default:
        stringBuilder = new StringBuilder(32);
        stringBuilder.append("Unknown button size: ");
        stringBuilder.append(paramInt1);
        throw new IllegalStateException(stringBuilder.toString());
      case 2:
        setText(null);
        break;
      case 1:
        paramInt1 = R.string.common_signin_button_text_long;
        setText(stringBuilder.getString(paramInt1));
        break;
      case 0:
        paramInt1 = R.string.common_signin_button_text;
        setText(stringBuilder.getString(paramInt1));
        break;
    } 
    setTransformationMethod(null);
    if (DeviceProperties.isWearable(getContext()))
      setGravity(19); 
  }
  
  public final void configure(Resources paramResources, SignInButtonConfig paramSignInButtonConfig) {
    configure(paramResources, paramSignInButtonConfig.getButtonSize(), paramSignInButtonConfig.getColorScheme());
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\internal\SignInButtonImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */