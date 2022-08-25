package com.google.android.gms.common.images.internal;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public final class ImageUtils {
  public static Bitmap frameBitmapInCircle(Bitmap paramBitmap) {
    if (paramBitmap == null)
      return null; 
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    int k = 0;
    if (i >= j) {
      k = (j - i) / 2;
      i = j;
      j = 0;
    } else {
      j = (i - j) / 2;
    } 
    Bitmap bitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    Paint paint = new Paint(1);
    paint.setColor(-16777216);
    float f = (i / 2);
    canvas.drawCircle(f, f, f, paint);
    paint.setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    canvas.drawBitmap(paramBitmap, k, j, paint);
    return bitmap;
  }
  
  public static Drawable frameDrawableInCircle(Resources paramResources, Drawable paramDrawable) {
    Bitmap bitmap;
    if (paramDrawable == null) {
      paramDrawable = null;
    } else if (paramDrawable instanceof BitmapDrawable) {
      bitmap = ((BitmapDrawable)paramDrawable).getBitmap();
    } else {
      Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getIntrinsicWidth(), bitmap.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
      Canvas canvas = new Canvas(bitmap1);
      bitmap.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
      bitmap.draw(canvas);
      bitmap = bitmap1;
    } 
    return (Drawable)new BitmapDrawable(paramResources, frameBitmapInCircle(bitmap));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\common\images\internal\ImageUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */