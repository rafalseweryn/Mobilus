package android.support.v4.graphics;

import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.Collection;

public final class PathUtils {
  @NonNull
  @RequiresApi(26)
  public static Collection<PathSegment> flatten(@NonNull Path paramPath) {
    return flatten(paramPath, 0.5F);
  }
  
  @NonNull
  @RequiresApi(26)
  public static Collection<PathSegment> flatten(@NonNull Path paramPath, @FloatRange(from = 0.0D) float paramFloat) {
    float[] arrayOfFloat = paramPath.approximate(paramFloat);
    int i = arrayOfFloat.length / 3;
    ArrayList<PathSegment> arrayList = new ArrayList(i);
    for (byte b = 1; b < i; b++) {
      int j = b * 3;
      int k = (b - 1) * 3;
      float f1 = arrayOfFloat[j];
      paramFloat = arrayOfFloat[j + 1];
      float f2 = arrayOfFloat[j + 2];
      float f3 = arrayOfFloat[k];
      float f4 = arrayOfFloat[k + 1];
      float f5 = arrayOfFloat[k + 2];
      if (f1 != f3 && (paramFloat != f4 || f2 != f5))
        arrayList.add(new PathSegment(new PointF(f4, f5), f3, new PointF(paramFloat, f2), f1)); 
    } 
    return arrayList;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\graphics\PathUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */