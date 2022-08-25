package android.support.transition;

import android.animation.LayoutTransition;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ViewGroupUtilsApi14 {
  private static final int LAYOUT_TRANSITION_CHANGING = 4;
  
  private static final String TAG = "ViewGroupUtilsApi14";
  
  private static Method sCancelMethod;
  
  private static boolean sCancelMethodFetched;
  
  private static LayoutTransition sEmptyLayoutTransition;
  
  private static Field sLayoutSuppressedField;
  
  private static boolean sLayoutSuppressedFieldFetched;
  
  private static void cancelLayoutTransition(LayoutTransition paramLayoutTransition) {
    if (!sCancelMethodFetched) {
      try {
        sCancelMethod = LayoutTransition.class.getDeclaredMethod("cancel", new Class[0]);
        sCancelMethod.setAccessible(true);
      } catch (NoSuchMethodException noSuchMethodException) {
        Log.i("ViewGroupUtilsApi14", "Failed to access cancel method by reflection");
      } 
      sCancelMethodFetched = true;
    } 
    if (sCancelMethod != null)
      try {
        sCancelMethod.invoke(paramLayoutTransition, new Object[0]);
      } catch (IllegalAccessException illegalAccessException) {
        Log.i("ViewGroupUtilsApi14", "Failed to access cancel method by reflection");
      } catch (InvocationTargetException invocationTargetException) {
        Log.i("ViewGroupUtilsApi14", "Failed to invoke cancel method by reflection");
      }  
  }
  
  static void suppressLayout(@NonNull ViewGroup paramViewGroup, boolean paramBoolean) {
    // Byte code:
    //   0: getstatic android/support/transition/ViewGroupUtilsApi14.sEmptyLayoutTransition : Landroid/animation/LayoutTransition;
    //   3: astore_2
    //   4: iconst_0
    //   5: istore_3
    //   6: iconst_0
    //   7: istore #4
    //   9: aload_2
    //   10: ifnonnull -> 63
    //   13: new android/support/transition/ViewGroupUtilsApi14$1
    //   16: dup
    //   17: invokespecial <init> : ()V
    //   20: putstatic android/support/transition/ViewGroupUtilsApi14.sEmptyLayoutTransition : Landroid/animation/LayoutTransition;
    //   23: getstatic android/support/transition/ViewGroupUtilsApi14.sEmptyLayoutTransition : Landroid/animation/LayoutTransition;
    //   26: iconst_2
    //   27: aconst_null
    //   28: invokevirtual setAnimator : (ILandroid/animation/Animator;)V
    //   31: getstatic android/support/transition/ViewGroupUtilsApi14.sEmptyLayoutTransition : Landroid/animation/LayoutTransition;
    //   34: iconst_0
    //   35: aconst_null
    //   36: invokevirtual setAnimator : (ILandroid/animation/Animator;)V
    //   39: getstatic android/support/transition/ViewGroupUtilsApi14.sEmptyLayoutTransition : Landroid/animation/LayoutTransition;
    //   42: iconst_1
    //   43: aconst_null
    //   44: invokevirtual setAnimator : (ILandroid/animation/Animator;)V
    //   47: getstatic android/support/transition/ViewGroupUtilsApi14.sEmptyLayoutTransition : Landroid/animation/LayoutTransition;
    //   50: iconst_3
    //   51: aconst_null
    //   52: invokevirtual setAnimator : (ILandroid/animation/Animator;)V
    //   55: getstatic android/support/transition/ViewGroupUtilsApi14.sEmptyLayoutTransition : Landroid/animation/LayoutTransition;
    //   58: iconst_4
    //   59: aconst_null
    //   60: invokevirtual setAnimator : (ILandroid/animation/Animator;)V
    //   63: iload_1
    //   64: ifeq -> 112
    //   67: aload_0
    //   68: invokevirtual getLayoutTransition : ()Landroid/animation/LayoutTransition;
    //   71: astore_2
    //   72: aload_2
    //   73: ifnull -> 102
    //   76: aload_2
    //   77: invokevirtual isRunning : ()Z
    //   80: ifeq -> 87
    //   83: aload_2
    //   84: invokestatic cancelLayoutTransition : (Landroid/animation/LayoutTransition;)V
    //   87: aload_2
    //   88: getstatic android/support/transition/ViewGroupUtilsApi14.sEmptyLayoutTransition : Landroid/animation/LayoutTransition;
    //   91: if_acmpeq -> 102
    //   94: aload_0
    //   95: getstatic android/support/transition/R$id.transition_layout_save : I
    //   98: aload_2
    //   99: invokevirtual setTag : (ILjava/lang/Object;)V
    //   102: aload_0
    //   103: getstatic android/support/transition/ViewGroupUtilsApi14.sEmptyLayoutTransition : Landroid/animation/LayoutTransition;
    //   106: invokevirtual setLayoutTransition : (Landroid/animation/LayoutTransition;)V
    //   109: goto -> 238
    //   112: aload_0
    //   113: aconst_null
    //   114: invokevirtual setLayoutTransition : (Landroid/animation/LayoutTransition;)V
    //   117: getstatic android/support/transition/ViewGroupUtilsApi14.sLayoutSuppressedFieldFetched : Z
    //   120: ifne -> 156
    //   123: ldc android/view/ViewGroup
    //   125: ldc 'mLayoutSuppressed'
    //   127: invokevirtual getDeclaredField : (Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   130: putstatic android/support/transition/ViewGroupUtilsApi14.sLayoutSuppressedField : Ljava/lang/reflect/Field;
    //   133: getstatic android/support/transition/ViewGroupUtilsApi14.sLayoutSuppressedField : Ljava/lang/reflect/Field;
    //   136: iconst_1
    //   137: invokevirtual setAccessible : (Z)V
    //   140: goto -> 152
    //   143: astore_2
    //   144: ldc 'ViewGroupUtilsApi14'
    //   146: ldc 'Failed to access mLayoutSuppressed field by reflection'
    //   148: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   151: pop
    //   152: iconst_1
    //   153: putstatic android/support/transition/ViewGroupUtilsApi14.sLayoutSuppressedFieldFetched : Z
    //   156: iload_3
    //   157: istore_1
    //   158: getstatic android/support/transition/ViewGroupUtilsApi14.sLayoutSuppressedField : Ljava/lang/reflect/Field;
    //   161: ifnull -> 202
    //   164: getstatic android/support/transition/ViewGroupUtilsApi14.sLayoutSuppressedField : Ljava/lang/reflect/Field;
    //   167: aload_0
    //   168: invokevirtual getBoolean : (Ljava/lang/Object;)Z
    //   171: istore_1
    //   172: iload_1
    //   173: ifeq -> 191
    //   176: getstatic android/support/transition/ViewGroupUtilsApi14.sLayoutSuppressedField : Ljava/lang/reflect/Field;
    //   179: aload_0
    //   180: iconst_0
    //   181: invokevirtual setBoolean : (Ljava/lang/Object;Z)V
    //   184: goto -> 191
    //   187: astore_2
    //   188: goto -> 194
    //   191: goto -> 202
    //   194: ldc 'ViewGroupUtilsApi14'
    //   196: ldc 'Failed to get mLayoutSuppressed field by reflection'
    //   198: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   201: pop
    //   202: iload_1
    //   203: ifeq -> 210
    //   206: aload_0
    //   207: invokevirtual requestLayout : ()V
    //   210: aload_0
    //   211: getstatic android/support/transition/R$id.transition_layout_save : I
    //   214: invokevirtual getTag : (I)Ljava/lang/Object;
    //   217: checkcast android/animation/LayoutTransition
    //   220: astore_2
    //   221: aload_2
    //   222: ifnull -> 238
    //   225: aload_0
    //   226: getstatic android/support/transition/R$id.transition_layout_save : I
    //   229: aconst_null
    //   230: invokevirtual setTag : (ILjava/lang/Object;)V
    //   233: aload_0
    //   234: aload_2
    //   235: invokevirtual setLayoutTransition : (Landroid/animation/LayoutTransition;)V
    //   238: return
    //   239: astore_2
    //   240: iload #4
    //   242: istore_1
    //   243: goto -> 194
    // Exception table:
    //   from	to	target	type
    //   123	140	143	java/lang/NoSuchFieldException
    //   164	172	239	java/lang/IllegalAccessException
    //   176	184	187	java/lang/IllegalAccessException
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\transition\ViewGroupUtilsApi14.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */