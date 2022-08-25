package android.support.v4.widget;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class FocusStrategy {
  private static boolean beamBeats(int paramInt, @NonNull Rect paramRect1, @NonNull Rect paramRect2, @NonNull Rect paramRect3) {
    boolean bool1 = beamsOverlap(paramInt, paramRect1, paramRect2);
    if (beamsOverlap(paramInt, paramRect1, paramRect3) || !bool1)
      return false; 
    boolean bool2 = isToDirectionOf(paramInt, paramRect1, paramRect3);
    bool1 = true;
    if (!bool2)
      return true; 
    if (paramInt == 17 || paramInt == 66)
      return true; 
    if (majorAxisDistance(paramInt, paramRect1, paramRect2) >= majorAxisDistanceToFarEdge(paramInt, paramRect1, paramRect3))
      bool1 = false; 
    return bool1;
  }
  
  private static boolean beamsOverlap(int paramInt, @NonNull Rect paramRect1, @NonNull Rect paramRect2) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: iconst_0
    //   3: istore #4
    //   5: iload_0
    //   6: bipush #17
    //   8: if_icmpeq -> 76
    //   11: iload_0
    //   12: bipush #33
    //   14: if_icmpeq -> 40
    //   17: iload_0
    //   18: bipush #66
    //   20: if_icmpeq -> 76
    //   23: iload_0
    //   24: sipush #130
    //   27: if_icmpeq -> 40
    //   30: new java/lang/IllegalArgumentException
    //   33: dup
    //   34: ldc 'direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.'
    //   36: invokespecial <init> : (Ljava/lang/String;)V
    //   39: athrow
    //   40: iload #4
    //   42: istore #5
    //   44: aload_2
    //   45: getfield right : I
    //   48: aload_1
    //   49: getfield left : I
    //   52: if_icmplt -> 73
    //   55: iload #4
    //   57: istore #5
    //   59: aload_2
    //   60: getfield left : I
    //   63: aload_1
    //   64: getfield right : I
    //   67: if_icmpgt -> 73
    //   70: iconst_1
    //   71: istore #5
    //   73: iload #5
    //   75: ireturn
    //   76: iload_3
    //   77: istore #5
    //   79: aload_2
    //   80: getfield bottom : I
    //   83: aload_1
    //   84: getfield top : I
    //   87: if_icmplt -> 107
    //   90: iload_3
    //   91: istore #5
    //   93: aload_2
    //   94: getfield top : I
    //   97: aload_1
    //   98: getfield bottom : I
    //   101: if_icmpgt -> 107
    //   104: iconst_1
    //   105: istore #5
    //   107: iload #5
    //   109: ireturn
  }
  
  public static <L, T> T findNextFocusInAbsoluteDirection(@NonNull L paramL, @NonNull CollectionAdapter<L, T> paramCollectionAdapter, @NonNull BoundsAdapter<T> paramBoundsAdapter, @Nullable T paramT, @NonNull Rect paramRect, int paramInt) {
    T t;
    Rect rect1 = new Rect(paramRect);
    byte b = 0;
    if (paramInt != 17) {
      if (paramInt != 33) {
        if (paramInt != 66) {
          if (paramInt != 130)
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}."); 
          rect1.offset(0, -(paramRect.height() + 1));
        } else {
          rect1.offset(-(paramRect.width() + 1), 0);
        } 
      } else {
        rect1.offset(0, paramRect.height() + 1);
      } 
    } else {
      rect1.offset(paramRect.width() + 1, 0);
    } 
    Object object = null;
    int i = paramCollectionAdapter.size(paramL);
    Rect rect2 = new Rect();
    while (b < i) {
      T t1 = paramCollectionAdapter.get(paramL, b);
      if (t1 != paramT) {
        paramBoundsAdapter.obtainBounds(t1, rect2);
        if (isBetterCandidate(paramInt, paramRect, rect2, rect1)) {
          rect1.set(rect2);
          t = t1;
        } 
      } 
      b++;
    } 
    return t;
  }
  
  public static <L, T> T findNextFocusInRelativeDirection(@NonNull L paramL, @NonNull CollectionAdapter<L, T> paramCollectionAdapter, @NonNull BoundsAdapter<T> paramBoundsAdapter, @Nullable T paramT, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    int i = paramCollectionAdapter.size(paramL);
    ArrayList<?> arrayList = new ArrayList(i);
    for (byte b = 0; b < i; b++)
      arrayList.add(paramCollectionAdapter.get(paramL, b)); 
    Collections.sort(arrayList, new SequentialComparator(paramBoolean1, paramBoundsAdapter));
    switch (paramInt) {
      default:
        throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD}.");
      case 2:
        return getNextFocusable(paramT, (ArrayList)arrayList, paramBoolean2);
      case 1:
        break;
    } 
    return getPreviousFocusable(paramT, (ArrayList)arrayList, paramBoolean2);
  }
  
  private static <T> T getNextFocusable(T paramT, ArrayList<T> paramArrayList, boolean paramBoolean) {
    int j;
    int i = paramArrayList.size();
    if (paramT == null) {
      j = -1;
    } else {
      j = paramArrayList.lastIndexOf(paramT);
    } 
    return (++j < i) ? paramArrayList.get(j) : ((paramBoolean && i > 0) ? paramArrayList.get(0) : null);
  }
  
  private static <T> T getPreviousFocusable(T paramT, ArrayList<T> paramArrayList, boolean paramBoolean) {
    int j;
    int i = paramArrayList.size();
    if (paramT == null) {
      j = i;
    } else {
      j = paramArrayList.indexOf(paramT);
    } 
    return (--j >= 0) ? paramArrayList.get(j) : ((paramBoolean && i > 0) ? paramArrayList.get(i - 1) : null);
  }
  
  private static int getWeightedDistanceFor(int paramInt1, int paramInt2) {
    return paramInt1 * 13 * paramInt1 + paramInt2 * paramInt2;
  }
  
  private static boolean isBetterCandidate(int paramInt, @NonNull Rect paramRect1, @NonNull Rect paramRect2, @NonNull Rect paramRect3) {
    boolean bool = isCandidate(paramRect1, paramRect2, paramInt);
    boolean bool1 = false;
    if (!bool)
      return false; 
    if (!isCandidate(paramRect1, paramRect3, paramInt))
      return true; 
    if (beamBeats(paramInt, paramRect1, paramRect2, paramRect3))
      return true; 
    if (beamBeats(paramInt, paramRect1, paramRect3, paramRect2))
      return false; 
    if (getWeightedDistanceFor(majorAxisDistance(paramInt, paramRect1, paramRect2), minorAxisDistance(paramInt, paramRect1, paramRect2)) < getWeightedDistanceFor(majorAxisDistance(paramInt, paramRect1, paramRect3), minorAxisDistance(paramInt, paramRect1, paramRect3)))
      bool1 = true; 
    return bool1;
  }
  
  private static boolean isCandidate(@NonNull Rect paramRect1, @NonNull Rect paramRect2, int paramInt) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: iconst_0
    //   3: istore #4
    //   5: iconst_0
    //   6: istore #5
    //   8: iconst_0
    //   9: istore #6
    //   11: iload_2
    //   12: bipush #17
    //   14: if_icmpeq -> 185
    //   17: iload_2
    //   18: bipush #33
    //   20: if_icmpeq -> 138
    //   23: iload_2
    //   24: bipush #66
    //   26: if_icmpeq -> 93
    //   29: iload_2
    //   30: sipush #130
    //   33: if_icmpeq -> 46
    //   36: new java/lang/IllegalArgumentException
    //   39: dup
    //   40: ldc 'direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.'
    //   42: invokespecial <init> : (Ljava/lang/String;)V
    //   45: athrow
    //   46: aload_0
    //   47: getfield top : I
    //   50: aload_1
    //   51: getfield top : I
    //   54: if_icmplt -> 72
    //   57: iload #6
    //   59: istore #7
    //   61: aload_0
    //   62: getfield bottom : I
    //   65: aload_1
    //   66: getfield top : I
    //   69: if_icmpgt -> 90
    //   72: iload #6
    //   74: istore #7
    //   76: aload_0
    //   77: getfield bottom : I
    //   80: aload_1
    //   81: getfield bottom : I
    //   84: if_icmpge -> 90
    //   87: iconst_1
    //   88: istore #7
    //   90: iload #7
    //   92: ireturn
    //   93: aload_0
    //   94: getfield left : I
    //   97: aload_1
    //   98: getfield left : I
    //   101: if_icmplt -> 118
    //   104: iload_3
    //   105: istore #7
    //   107: aload_0
    //   108: getfield right : I
    //   111: aload_1
    //   112: getfield left : I
    //   115: if_icmpgt -> 135
    //   118: iload_3
    //   119: istore #7
    //   121: aload_0
    //   122: getfield right : I
    //   125: aload_1
    //   126: getfield right : I
    //   129: if_icmpge -> 135
    //   132: iconst_1
    //   133: istore #7
    //   135: iload #7
    //   137: ireturn
    //   138: aload_0
    //   139: getfield bottom : I
    //   142: aload_1
    //   143: getfield bottom : I
    //   146: if_icmpgt -> 164
    //   149: iload #4
    //   151: istore #7
    //   153: aload_0
    //   154: getfield top : I
    //   157: aload_1
    //   158: getfield bottom : I
    //   161: if_icmplt -> 182
    //   164: iload #4
    //   166: istore #7
    //   168: aload_0
    //   169: getfield top : I
    //   172: aload_1
    //   173: getfield top : I
    //   176: if_icmple -> 182
    //   179: iconst_1
    //   180: istore #7
    //   182: iload #7
    //   184: ireturn
    //   185: aload_0
    //   186: getfield right : I
    //   189: aload_1
    //   190: getfield right : I
    //   193: if_icmpgt -> 211
    //   196: iload #5
    //   198: istore #7
    //   200: aload_0
    //   201: getfield left : I
    //   204: aload_1
    //   205: getfield right : I
    //   208: if_icmplt -> 229
    //   211: iload #5
    //   213: istore #7
    //   215: aload_0
    //   216: getfield left : I
    //   219: aload_1
    //   220: getfield left : I
    //   223: if_icmple -> 229
    //   226: iconst_1
    //   227: istore #7
    //   229: iload #7
    //   231: ireturn
  }
  
  private static boolean isToDirectionOf(int paramInt, @NonNull Rect paramRect1, @NonNull Rect paramRect2) {
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    if (paramInt != 17) {
      if (paramInt != 33) {
        if (paramInt != 66) {
          if (paramInt != 130)
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}."); 
          if (paramRect1.bottom <= paramRect2.top)
            bool4 = true; 
          return bool4;
        } 
        bool4 = bool1;
        if (paramRect1.right <= paramRect2.left)
          bool4 = true; 
        return bool4;
      } 
      bool4 = bool2;
      if (paramRect1.top >= paramRect2.bottom)
        bool4 = true; 
      return bool4;
    } 
    bool4 = bool3;
    if (paramRect1.left >= paramRect2.right)
      bool4 = true; 
    return bool4;
  }
  
  private static int majorAxisDistance(int paramInt, @NonNull Rect paramRect1, @NonNull Rect paramRect2) {
    return Math.max(0, majorAxisDistanceRaw(paramInt, paramRect1, paramRect2));
  }
  
  private static int majorAxisDistanceRaw(int paramInt, @NonNull Rect paramRect1, @NonNull Rect paramRect2) {
    if (paramInt != 17) {
      if (paramInt != 33) {
        if (paramInt != 66) {
          if (paramInt != 130)
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}."); 
          return paramRect2.top - paramRect1.bottom;
        } 
        return paramRect2.left - paramRect1.right;
      } 
      return paramRect1.top - paramRect2.bottom;
    } 
    return paramRect1.left - paramRect2.right;
  }
  
  private static int majorAxisDistanceToFarEdge(int paramInt, @NonNull Rect paramRect1, @NonNull Rect paramRect2) {
    return Math.max(1, majorAxisDistanceToFarEdgeRaw(paramInt, paramRect1, paramRect2));
  }
  
  private static int majorAxisDistanceToFarEdgeRaw(int paramInt, @NonNull Rect paramRect1, @NonNull Rect paramRect2) {
    if (paramInt != 17) {
      if (paramInt != 33) {
        if (paramInt != 66) {
          if (paramInt != 130)
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}."); 
          return paramRect2.bottom - paramRect1.bottom;
        } 
        return paramRect2.right - paramRect1.right;
      } 
      return paramRect1.top - paramRect2.top;
    } 
    return paramRect1.left - paramRect2.left;
  }
  
  private static int minorAxisDistance(int paramInt, @NonNull Rect paramRect1, @NonNull Rect paramRect2) {
    if (paramInt != 17)
      if (paramInt != 33) {
        if (paramInt != 66) {
          if (paramInt != 130)
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}."); 
          return Math.abs(paramRect1.left + paramRect1.width() / 2 - paramRect2.left + paramRect2.width() / 2);
        } 
      } else {
        return Math.abs(paramRect1.left + paramRect1.width() / 2 - paramRect2.left + paramRect2.width() / 2);
      }  
    return Math.abs(paramRect1.top + paramRect1.height() / 2 - paramRect2.top + paramRect2.height() / 2);
  }
  
  public static interface BoundsAdapter<T> {
    void obtainBounds(T param1T, Rect param1Rect);
  }
  
  public static interface CollectionAdapter<T, V> {
    V get(T param1T, int param1Int);
    
    int size(T param1T);
  }
  
  private static class SequentialComparator<T> implements Comparator<T> {
    private final FocusStrategy.BoundsAdapter<T> mAdapter;
    
    private final boolean mIsLayoutRtl;
    
    private final Rect mTemp1 = new Rect();
    
    private final Rect mTemp2 = new Rect();
    
    SequentialComparator(boolean param1Boolean, FocusStrategy.BoundsAdapter<T> param1BoundsAdapter) {
      this.mIsLayoutRtl = param1Boolean;
      this.mAdapter = param1BoundsAdapter;
    }
    
    public int compare(T param1T1, T param1T2) {
      Rect rect1 = this.mTemp1;
      Rect rect2 = this.mTemp2;
      this.mAdapter.obtainBounds(param1T1, rect1);
      this.mAdapter.obtainBounds(param1T2, rect2);
      int i = rect1.top;
      int j = rect2.top;
      byte b = -1;
      if (i < j)
        return -1; 
      if (rect1.top > rect2.top)
        return 1; 
      if (rect1.left < rect2.left) {
        if (this.mIsLayoutRtl)
          b = 1; 
        return b;
      } 
      if (rect1.left > rect2.left) {
        if (!this.mIsLayoutRtl)
          b = 1; 
        return b;
      } 
      if (rect1.bottom < rect2.bottom)
        return -1; 
      if (rect1.bottom > rect2.bottom)
        return 1; 
      if (rect1.right < rect2.right) {
        if (this.mIsLayoutRtl)
          b = 1; 
        return b;
      } 
      if (rect1.right > rect2.right) {
        if (!this.mIsLayoutRtl)
          b = 1; 
        return b;
      } 
      return 0;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\widget\FocusStrategy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */