package android.support.v4.view;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

public final class GestureDetectorCompat {
  private final GestureDetectorCompatImpl mImpl;
  
  public GestureDetectorCompat(Context paramContext, GestureDetector.OnGestureListener paramOnGestureListener) {
    this(paramContext, paramOnGestureListener, null);
  }
  
  public GestureDetectorCompat(Context paramContext, GestureDetector.OnGestureListener paramOnGestureListener, Handler paramHandler) {
    if (Build.VERSION.SDK_INT > 17) {
      this.mImpl = new GestureDetectorCompatImplJellybeanMr2(paramContext, paramOnGestureListener, paramHandler);
    } else {
      this.mImpl = new GestureDetectorCompatImplBase(paramContext, paramOnGestureListener, paramHandler);
    } 
  }
  
  public boolean isLongpressEnabled() {
    return this.mImpl.isLongpressEnabled();
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    return this.mImpl.onTouchEvent(paramMotionEvent);
  }
  
  public void setIsLongpressEnabled(boolean paramBoolean) {
    this.mImpl.setIsLongpressEnabled(paramBoolean);
  }
  
  public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener paramOnDoubleTapListener) {
    this.mImpl.setOnDoubleTapListener(paramOnDoubleTapListener);
  }
  
  static interface GestureDetectorCompatImpl {
    boolean isLongpressEnabled();
    
    boolean onTouchEvent(MotionEvent param1MotionEvent);
    
    void setIsLongpressEnabled(boolean param1Boolean);
    
    void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener param1OnDoubleTapListener);
  }
  
  static class GestureDetectorCompatImplBase implements GestureDetectorCompatImpl {
    private static final int DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();
    
    private static final int LONGPRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
    
    private static final int LONG_PRESS = 2;
    
    private static final int SHOW_PRESS = 1;
    
    private static final int TAP = 3;
    
    private static final int TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
    
    private boolean mAlwaysInBiggerTapRegion;
    
    private boolean mAlwaysInTapRegion;
    
    MotionEvent mCurrentDownEvent;
    
    boolean mDeferConfirmSingleTap;
    
    GestureDetector.OnDoubleTapListener mDoubleTapListener;
    
    private int mDoubleTapSlopSquare;
    
    private float mDownFocusX;
    
    private float mDownFocusY;
    
    private final Handler mHandler;
    
    private boolean mInLongPress;
    
    private boolean mIsDoubleTapping;
    
    private boolean mIsLongpressEnabled;
    
    private float mLastFocusX;
    
    private float mLastFocusY;
    
    final GestureDetector.OnGestureListener mListener;
    
    private int mMaximumFlingVelocity;
    
    private int mMinimumFlingVelocity;
    
    private MotionEvent mPreviousUpEvent;
    
    boolean mStillDown;
    
    private int mTouchSlopSquare;
    
    private VelocityTracker mVelocityTracker;
    
    static {
    
    }
    
    GestureDetectorCompatImplBase(Context param1Context, GestureDetector.OnGestureListener param1OnGestureListener, Handler param1Handler) {
      if (param1Handler != null) {
        this.mHandler = new GestureHandler(param1Handler);
      } else {
        this.mHandler = new GestureHandler();
      } 
      this.mListener = param1OnGestureListener;
      if (param1OnGestureListener instanceof GestureDetector.OnDoubleTapListener)
        setOnDoubleTapListener((GestureDetector.OnDoubleTapListener)param1OnGestureListener); 
      init(param1Context);
    }
    
    private void cancel() {
      this.mHandler.removeMessages(1);
      this.mHandler.removeMessages(2);
      this.mHandler.removeMessages(3);
      this.mVelocityTracker.recycle();
      this.mVelocityTracker = null;
      this.mIsDoubleTapping = false;
      this.mStillDown = false;
      this.mAlwaysInTapRegion = false;
      this.mAlwaysInBiggerTapRegion = false;
      this.mDeferConfirmSingleTap = false;
      if (this.mInLongPress)
        this.mInLongPress = false; 
    }
    
    private void cancelTaps() {
      this.mHandler.removeMessages(1);
      this.mHandler.removeMessages(2);
      this.mHandler.removeMessages(3);
      this.mIsDoubleTapping = false;
      this.mAlwaysInTapRegion = false;
      this.mAlwaysInBiggerTapRegion = false;
      this.mDeferConfirmSingleTap = false;
      if (this.mInLongPress)
        this.mInLongPress = false; 
    }
    
    private void init(Context param1Context) {
      if (param1Context == null)
        throw new IllegalArgumentException("Context must not be null"); 
      if (this.mListener == null)
        throw new IllegalArgumentException("OnGestureListener must not be null"); 
      this.mIsLongpressEnabled = true;
      ViewConfiguration viewConfiguration = ViewConfiguration.get(param1Context);
      int i = viewConfiguration.getScaledTouchSlop();
      int j = viewConfiguration.getScaledDoubleTapSlop();
      this.mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
      this.mMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
      this.mTouchSlopSquare = i * i;
      this.mDoubleTapSlopSquare = j * j;
    }
    
    private boolean isConsideredDoubleTap(MotionEvent param1MotionEvent1, MotionEvent param1MotionEvent2, MotionEvent param1MotionEvent3) {
      boolean bool = this.mAlwaysInBiggerTapRegion;
      boolean bool1 = false;
      if (!bool)
        return false; 
      if (param1MotionEvent3.getEventTime() - param1MotionEvent2.getEventTime() > DOUBLE_TAP_TIMEOUT)
        return false; 
      int i = (int)param1MotionEvent1.getX() - (int)param1MotionEvent3.getX();
      int j = (int)param1MotionEvent1.getY() - (int)param1MotionEvent3.getY();
      if (i * i + j * j < this.mDoubleTapSlopSquare)
        bool1 = true; 
      return bool1;
    }
    
    void dispatchLongPress() {
      this.mHandler.removeMessages(3);
      this.mDeferConfirmSingleTap = false;
      this.mInLongPress = true;
      this.mListener.onLongPress(this.mCurrentDownEvent);
    }
    
    public boolean isLongpressEnabled() {
      return this.mIsLongpressEnabled;
    }
    
    public boolean onTouchEvent(MotionEvent param1MotionEvent) {
      // Byte code:
      //   0: aload_1
      //   1: invokevirtual getAction : ()I
      //   4: istore_2
      //   5: aload_0
      //   6: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   9: ifnonnull -> 19
      //   12: aload_0
      //   13: invokestatic obtain : ()Landroid/view/VelocityTracker;
      //   16: putfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   19: aload_0
      //   20: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   23: aload_1
      //   24: invokevirtual addMovement : (Landroid/view/MotionEvent;)V
      //   27: iload_2
      //   28: sipush #255
      //   31: iand
      //   32: istore_3
      //   33: iconst_0
      //   34: istore #4
      //   36: iload_3
      //   37: bipush #6
      //   39: if_icmpne -> 47
      //   42: iconst_1
      //   43: istore_2
      //   44: goto -> 49
      //   47: iconst_0
      //   48: istore_2
      //   49: iload_2
      //   50: ifeq -> 62
      //   53: aload_1
      //   54: invokevirtual getActionIndex : ()I
      //   57: istore #5
      //   59: goto -> 65
      //   62: iconst_m1
      //   63: istore #5
      //   65: aload_1
      //   66: invokevirtual getPointerCount : ()I
      //   69: istore #6
      //   71: iconst_0
      //   72: istore #7
      //   74: fconst_0
      //   75: fstore #8
      //   77: fload #8
      //   79: fstore #9
      //   81: iload #7
      //   83: iload #6
      //   85: if_icmpge -> 126
      //   88: iload #5
      //   90: iload #7
      //   92: if_icmpne -> 98
      //   95: goto -> 120
      //   98: fload #8
      //   100: aload_1
      //   101: iload #7
      //   103: invokevirtual getX : (I)F
      //   106: fadd
      //   107: fstore #8
      //   109: fload #9
      //   111: aload_1
      //   112: iload #7
      //   114: invokevirtual getY : (I)F
      //   117: fadd
      //   118: fstore #9
      //   120: iinc #7, 1
      //   123: goto -> 81
      //   126: iload_2
      //   127: ifeq -> 138
      //   130: iload #6
      //   132: iconst_1
      //   133: isub
      //   134: istore_2
      //   135: goto -> 141
      //   138: iload #6
      //   140: istore_2
      //   141: iload_2
      //   142: i2f
      //   143: fstore #10
      //   145: fload #8
      //   147: fload #10
      //   149: fdiv
      //   150: fstore #8
      //   152: fload #9
      //   154: fload #10
      //   156: fdiv
      //   157: fstore #11
      //   159: iload_3
      //   160: tableswitch default -> 204, 0 -> 936, 1 -> 657, 2 -> 407, 3 -> 396, 4 -> 204, 5 -> 361, 6 -> 211
      //   204: iload #4
      //   206: istore #12
      //   208: goto -> 1198
      //   211: aload_0
      //   212: fload #8
      //   214: putfield mLastFocusX : F
      //   217: aload_0
      //   218: fload #8
      //   220: putfield mDownFocusX : F
      //   223: aload_0
      //   224: fload #11
      //   226: putfield mLastFocusY : F
      //   229: aload_0
      //   230: fload #11
      //   232: putfield mDownFocusY : F
      //   235: aload_0
      //   236: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   239: sipush #1000
      //   242: aload_0
      //   243: getfield mMaximumFlingVelocity : I
      //   246: i2f
      //   247: invokevirtual computeCurrentVelocity : (IF)V
      //   250: aload_1
      //   251: invokevirtual getActionIndex : ()I
      //   254: istore #5
      //   256: aload_1
      //   257: iload #5
      //   259: invokevirtual getPointerId : (I)I
      //   262: istore_2
      //   263: aload_0
      //   264: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   267: iload_2
      //   268: invokevirtual getXVelocity : (I)F
      //   271: fstore #9
      //   273: aload_0
      //   274: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   277: iload_2
      //   278: invokevirtual getYVelocity : (I)F
      //   281: fstore #8
      //   283: iconst_0
      //   284: istore_2
      //   285: iload #4
      //   287: istore #12
      //   289: iload_2
      //   290: iload #6
      //   292: if_icmpge -> 1198
      //   295: iload_2
      //   296: iload #5
      //   298: if_icmpne -> 304
      //   301: goto -> 355
      //   304: aload_1
      //   305: iload_2
      //   306: invokevirtual getPointerId : (I)I
      //   309: istore #7
      //   311: aload_0
      //   312: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   315: iload #7
      //   317: invokevirtual getXVelocity : (I)F
      //   320: fload #9
      //   322: fmul
      //   323: aload_0
      //   324: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   327: iload #7
      //   329: invokevirtual getYVelocity : (I)F
      //   332: fload #8
      //   334: fmul
      //   335: fadd
      //   336: fconst_0
      //   337: fcmpg
      //   338: ifge -> 355
      //   341: aload_0
      //   342: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   345: invokevirtual clear : ()V
      //   348: iload #4
      //   350: istore #12
      //   352: goto -> 1198
      //   355: iinc #2, 1
      //   358: goto -> 285
      //   361: aload_0
      //   362: fload #8
      //   364: putfield mLastFocusX : F
      //   367: aload_0
      //   368: fload #8
      //   370: putfield mDownFocusX : F
      //   373: aload_0
      //   374: fload #11
      //   376: putfield mLastFocusY : F
      //   379: aload_0
      //   380: fload #11
      //   382: putfield mDownFocusY : F
      //   385: aload_0
      //   386: invokespecial cancelTaps : ()V
      //   389: iload #4
      //   391: istore #12
      //   393: goto -> 1198
      //   396: aload_0
      //   397: invokespecial cancel : ()V
      //   400: iload #4
      //   402: istore #12
      //   404: goto -> 1198
      //   407: aload_0
      //   408: getfield mInLongPress : Z
      //   411: ifeq -> 421
      //   414: iload #4
      //   416: istore #12
      //   418: goto -> 1198
      //   421: aload_0
      //   422: getfield mLastFocusX : F
      //   425: fload #8
      //   427: fsub
      //   428: fstore #9
      //   430: aload_0
      //   431: getfield mLastFocusY : F
      //   434: fload #11
      //   436: fsub
      //   437: fstore #10
      //   439: aload_0
      //   440: getfield mIsDoubleTapping : Z
      //   443: ifeq -> 463
      //   446: iconst_0
      //   447: aload_0
      //   448: getfield mDoubleTapListener : Landroid/view/GestureDetector$OnDoubleTapListener;
      //   451: aload_1
      //   452: invokeinterface onDoubleTapEvent : (Landroid/view/MotionEvent;)Z
      //   457: ior
      //   458: istore #12
      //   460: goto -> 1198
      //   463: aload_0
      //   464: getfield mAlwaysInTapRegion : Z
      //   467: ifeq -> 598
      //   470: fload #8
      //   472: aload_0
      //   473: getfield mDownFocusX : F
      //   476: fsub
      //   477: f2i
      //   478: istore #5
      //   480: fload #11
      //   482: aload_0
      //   483: getfield mDownFocusY : F
      //   486: fsub
      //   487: f2i
      //   488: istore_2
      //   489: iload #5
      //   491: iload #5
      //   493: imul
      //   494: iload_2
      //   495: iload_2
      //   496: imul
      //   497: iadd
      //   498: istore_2
      //   499: iload_2
      //   500: aload_0
      //   501: getfield mTouchSlopSquare : I
      //   504: if_icmple -> 571
      //   507: aload_0
      //   508: getfield mListener : Landroid/view/GestureDetector$OnGestureListener;
      //   511: aload_0
      //   512: getfield mCurrentDownEvent : Landroid/view/MotionEvent;
      //   515: aload_1
      //   516: fload #9
      //   518: fload #10
      //   520: invokeinterface onScroll : (Landroid/view/MotionEvent;Landroid/view/MotionEvent;FF)Z
      //   525: istore #12
      //   527: aload_0
      //   528: fload #8
      //   530: putfield mLastFocusX : F
      //   533: aload_0
      //   534: fload #11
      //   536: putfield mLastFocusY : F
      //   539: aload_0
      //   540: iconst_0
      //   541: putfield mAlwaysInTapRegion : Z
      //   544: aload_0
      //   545: getfield mHandler : Landroid/os/Handler;
      //   548: iconst_3
      //   549: invokevirtual removeMessages : (I)V
      //   552: aload_0
      //   553: getfield mHandler : Landroid/os/Handler;
      //   556: iconst_1
      //   557: invokevirtual removeMessages : (I)V
      //   560: aload_0
      //   561: getfield mHandler : Landroid/os/Handler;
      //   564: iconst_2
      //   565: invokevirtual removeMessages : (I)V
      //   568: goto -> 574
      //   571: iconst_0
      //   572: istore #12
      //   574: iload #12
      //   576: istore #4
      //   578: iload_2
      //   579: aload_0
      //   580: getfield mTouchSlopSquare : I
      //   583: if_icmple -> 929
      //   586: aload_0
      //   587: iconst_0
      //   588: putfield mAlwaysInBiggerTapRegion : Z
      //   591: iload #12
      //   593: istore #4
      //   595: goto -> 929
      //   598: fload #9
      //   600: invokestatic abs : (F)F
      //   603: fconst_1
      //   604: fcmpl
      //   605: ifge -> 622
      //   608: iload #4
      //   610: istore #12
      //   612: fload #10
      //   614: invokestatic abs : (F)F
      //   617: fconst_1
      //   618: fcmpl
      //   619: iflt -> 1198
      //   622: aload_0
      //   623: getfield mListener : Landroid/view/GestureDetector$OnGestureListener;
      //   626: aload_0
      //   627: getfield mCurrentDownEvent : Landroid/view/MotionEvent;
      //   630: aload_1
      //   631: fload #9
      //   633: fload #10
      //   635: invokeinterface onScroll : (Landroid/view/MotionEvent;Landroid/view/MotionEvent;FF)Z
      //   640: istore #12
      //   642: aload_0
      //   643: fload #8
      //   645: putfield mLastFocusX : F
      //   648: aload_0
      //   649: fload #11
      //   651: putfield mLastFocusY : F
      //   654: goto -> 1198
      //   657: aload_0
      //   658: iconst_0
      //   659: putfield mStillDown : Z
      //   662: aload_1
      //   663: invokestatic obtain : (Landroid/view/MotionEvent;)Landroid/view/MotionEvent;
      //   666: astore #13
      //   668: aload_0
      //   669: getfield mIsDoubleTapping : Z
      //   672: ifeq -> 692
      //   675: aload_0
      //   676: getfield mDoubleTapListener : Landroid/view/GestureDetector$OnDoubleTapListener;
      //   679: aload_1
      //   680: invokeinterface onDoubleTapEvent : (Landroid/view/MotionEvent;)Z
      //   685: iconst_0
      //   686: ior
      //   687: istore #12
      //   689: goto -> 860
      //   692: aload_0
      //   693: getfield mInLongPress : Z
      //   696: ifeq -> 715
      //   699: aload_0
      //   700: getfield mHandler : Landroid/os/Handler;
      //   703: iconst_3
      //   704: invokevirtual removeMessages : (I)V
      //   707: aload_0
      //   708: iconst_0
      //   709: putfield mInLongPress : Z
      //   712: goto -> 834
      //   715: aload_0
      //   716: getfield mAlwaysInTapRegion : Z
      //   719: ifeq -> 762
      //   722: aload_0
      //   723: getfield mListener : Landroid/view/GestureDetector$OnGestureListener;
      //   726: aload_1
      //   727: invokeinterface onSingleTapUp : (Landroid/view/MotionEvent;)Z
      //   732: istore #12
      //   734: aload_0
      //   735: getfield mDeferConfirmSingleTap : Z
      //   738: ifeq -> 759
      //   741: aload_0
      //   742: getfield mDoubleTapListener : Landroid/view/GestureDetector$OnDoubleTapListener;
      //   745: ifnull -> 759
      //   748: aload_0
      //   749: getfield mDoubleTapListener : Landroid/view/GestureDetector$OnDoubleTapListener;
      //   752: aload_1
      //   753: invokeinterface onSingleTapConfirmed : (Landroid/view/MotionEvent;)Z
      //   758: pop
      //   759: goto -> 860
      //   762: aload_0
      //   763: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   766: astore #14
      //   768: aload_1
      //   769: iconst_0
      //   770: invokevirtual getPointerId : (I)I
      //   773: istore_2
      //   774: aload #14
      //   776: sipush #1000
      //   779: aload_0
      //   780: getfield mMaximumFlingVelocity : I
      //   783: i2f
      //   784: invokevirtual computeCurrentVelocity : (IF)V
      //   787: aload #14
      //   789: iload_2
      //   790: invokevirtual getYVelocity : (I)F
      //   793: fstore #9
      //   795: aload #14
      //   797: iload_2
      //   798: invokevirtual getXVelocity : (I)F
      //   801: fstore #8
      //   803: fload #9
      //   805: invokestatic abs : (F)F
      //   808: aload_0
      //   809: getfield mMinimumFlingVelocity : I
      //   812: i2f
      //   813: fcmpl
      //   814: ifgt -> 840
      //   817: fload #8
      //   819: invokestatic abs : (F)F
      //   822: aload_0
      //   823: getfield mMinimumFlingVelocity : I
      //   826: i2f
      //   827: fcmpl
      //   828: ifle -> 834
      //   831: goto -> 840
      //   834: iconst_0
      //   835: istore #12
      //   837: goto -> 860
      //   840: aload_0
      //   841: getfield mListener : Landroid/view/GestureDetector$OnGestureListener;
      //   844: aload_0
      //   845: getfield mCurrentDownEvent : Landroid/view/MotionEvent;
      //   848: aload_1
      //   849: fload #8
      //   851: fload #9
      //   853: invokeinterface onFling : (Landroid/view/MotionEvent;Landroid/view/MotionEvent;FF)Z
      //   858: istore #12
      //   860: aload_0
      //   861: getfield mPreviousUpEvent : Landroid/view/MotionEvent;
      //   864: ifnull -> 874
      //   867: aload_0
      //   868: getfield mPreviousUpEvent : Landroid/view/MotionEvent;
      //   871: invokevirtual recycle : ()V
      //   874: aload_0
      //   875: aload #13
      //   877: putfield mPreviousUpEvent : Landroid/view/MotionEvent;
      //   880: aload_0
      //   881: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   884: ifnull -> 899
      //   887: aload_0
      //   888: getfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   891: invokevirtual recycle : ()V
      //   894: aload_0
      //   895: aconst_null
      //   896: putfield mVelocityTracker : Landroid/view/VelocityTracker;
      //   899: aload_0
      //   900: iconst_0
      //   901: putfield mIsDoubleTapping : Z
      //   904: aload_0
      //   905: iconst_0
      //   906: putfield mDeferConfirmSingleTap : Z
      //   909: aload_0
      //   910: getfield mHandler : Landroid/os/Handler;
      //   913: iconst_1
      //   914: invokevirtual removeMessages : (I)V
      //   917: aload_0
      //   918: getfield mHandler : Landroid/os/Handler;
      //   921: iconst_2
      //   922: invokevirtual removeMessages : (I)V
      //   925: iload #12
      //   927: istore #4
      //   929: iload #4
      //   931: istore #12
      //   933: goto -> 1198
      //   936: aload_0
      //   937: getfield mDoubleTapListener : Landroid/view/GestureDetector$OnDoubleTapListener;
      //   940: ifnull -> 1049
      //   943: aload_0
      //   944: getfield mHandler : Landroid/os/Handler;
      //   947: iconst_3
      //   948: invokevirtual hasMessages : (I)Z
      //   951: istore #12
      //   953: iload #12
      //   955: ifeq -> 966
      //   958: aload_0
      //   959: getfield mHandler : Landroid/os/Handler;
      //   962: iconst_3
      //   963: invokevirtual removeMessages : (I)V
      //   966: aload_0
      //   967: getfield mCurrentDownEvent : Landroid/view/MotionEvent;
      //   970: ifnull -> 1036
      //   973: aload_0
      //   974: getfield mPreviousUpEvent : Landroid/view/MotionEvent;
      //   977: ifnull -> 1036
      //   980: iload #12
      //   982: ifeq -> 1036
      //   985: aload_0
      //   986: aload_0
      //   987: getfield mCurrentDownEvent : Landroid/view/MotionEvent;
      //   990: aload_0
      //   991: getfield mPreviousUpEvent : Landroid/view/MotionEvent;
      //   994: aload_1
      //   995: invokespecial isConsideredDoubleTap : (Landroid/view/MotionEvent;Landroid/view/MotionEvent;Landroid/view/MotionEvent;)Z
      //   998: ifeq -> 1036
      //   1001: aload_0
      //   1002: iconst_1
      //   1003: putfield mIsDoubleTapping : Z
      //   1006: aload_0
      //   1007: getfield mDoubleTapListener : Landroid/view/GestureDetector$OnDoubleTapListener;
      //   1010: aload_0
      //   1011: getfield mCurrentDownEvent : Landroid/view/MotionEvent;
      //   1014: invokeinterface onDoubleTap : (Landroid/view/MotionEvent;)Z
      //   1019: iconst_0
      //   1020: ior
      //   1021: aload_0
      //   1022: getfield mDoubleTapListener : Landroid/view/GestureDetector$OnDoubleTapListener;
      //   1025: aload_1
      //   1026: invokeinterface onDoubleTapEvent : (Landroid/view/MotionEvent;)Z
      //   1031: ior
      //   1032: istore_2
      //   1033: goto -> 1051
      //   1036: aload_0
      //   1037: getfield mHandler : Landroid/os/Handler;
      //   1040: iconst_3
      //   1041: getstatic android/support/v4/view/GestureDetectorCompat$GestureDetectorCompatImplBase.DOUBLE_TAP_TIMEOUT : I
      //   1044: i2l
      //   1045: invokevirtual sendEmptyMessageDelayed : (IJ)Z
      //   1048: pop
      //   1049: iconst_0
      //   1050: istore_2
      //   1051: aload_0
      //   1052: fload #8
      //   1054: putfield mLastFocusX : F
      //   1057: aload_0
      //   1058: fload #8
      //   1060: putfield mDownFocusX : F
      //   1063: aload_0
      //   1064: fload #11
      //   1066: putfield mLastFocusY : F
      //   1069: aload_0
      //   1070: fload #11
      //   1072: putfield mDownFocusY : F
      //   1075: aload_0
      //   1076: getfield mCurrentDownEvent : Landroid/view/MotionEvent;
      //   1079: ifnull -> 1089
      //   1082: aload_0
      //   1083: getfield mCurrentDownEvent : Landroid/view/MotionEvent;
      //   1086: invokevirtual recycle : ()V
      //   1089: aload_0
      //   1090: aload_1
      //   1091: invokestatic obtain : (Landroid/view/MotionEvent;)Landroid/view/MotionEvent;
      //   1094: putfield mCurrentDownEvent : Landroid/view/MotionEvent;
      //   1097: aload_0
      //   1098: iconst_1
      //   1099: putfield mAlwaysInTapRegion : Z
      //   1102: aload_0
      //   1103: iconst_1
      //   1104: putfield mAlwaysInBiggerTapRegion : Z
      //   1107: aload_0
      //   1108: iconst_1
      //   1109: putfield mStillDown : Z
      //   1112: aload_0
      //   1113: iconst_0
      //   1114: putfield mInLongPress : Z
      //   1117: aload_0
      //   1118: iconst_0
      //   1119: putfield mDeferConfirmSingleTap : Z
      //   1122: aload_0
      //   1123: getfield mIsLongpressEnabled : Z
      //   1126: ifeq -> 1163
      //   1129: aload_0
      //   1130: getfield mHandler : Landroid/os/Handler;
      //   1133: iconst_2
      //   1134: invokevirtual removeMessages : (I)V
      //   1137: aload_0
      //   1138: getfield mHandler : Landroid/os/Handler;
      //   1141: iconst_2
      //   1142: aload_0
      //   1143: getfield mCurrentDownEvent : Landroid/view/MotionEvent;
      //   1146: invokevirtual getDownTime : ()J
      //   1149: getstatic android/support/v4/view/GestureDetectorCompat$GestureDetectorCompatImplBase.TAP_TIMEOUT : I
      //   1152: i2l
      //   1153: ladd
      //   1154: getstatic android/support/v4/view/GestureDetectorCompat$GestureDetectorCompatImplBase.LONGPRESS_TIMEOUT : I
      //   1157: i2l
      //   1158: ladd
      //   1159: invokevirtual sendEmptyMessageAtTime : (IJ)Z
      //   1162: pop
      //   1163: aload_0
      //   1164: getfield mHandler : Landroid/os/Handler;
      //   1167: iconst_1
      //   1168: aload_0
      //   1169: getfield mCurrentDownEvent : Landroid/view/MotionEvent;
      //   1172: invokevirtual getDownTime : ()J
      //   1175: getstatic android/support/v4/view/GestureDetectorCompat$GestureDetectorCompatImplBase.TAP_TIMEOUT : I
      //   1178: i2l
      //   1179: ladd
      //   1180: invokevirtual sendEmptyMessageAtTime : (IJ)Z
      //   1183: pop
      //   1184: iload_2
      //   1185: aload_0
      //   1186: getfield mListener : Landroid/view/GestureDetector$OnGestureListener;
      //   1189: aload_1
      //   1190: invokeinterface onDown : (Landroid/view/MotionEvent;)Z
      //   1195: ior
      //   1196: istore #12
      //   1198: iload #12
      //   1200: ireturn
    }
    
    public void setIsLongpressEnabled(boolean param1Boolean) {
      this.mIsLongpressEnabled = param1Boolean;
    }
    
    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener param1OnDoubleTapListener) {
      this.mDoubleTapListener = param1OnDoubleTapListener;
    }
    
    private class GestureHandler extends Handler {
      GestureHandler() {}
      
      GestureHandler(Handler param2Handler) {
        super(param2Handler.getLooper());
      }
      
      public void handleMessage(Message param2Message) {
        StringBuilder stringBuilder;
        switch (param2Message.what) {
          default:
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unknown message ");
            stringBuilder.append(param2Message);
            throw new RuntimeException(stringBuilder.toString());
          case 3:
            if (GestureDetectorCompat.GestureDetectorCompatImplBase.this.mDoubleTapListener != null)
              if (!GestureDetectorCompat.GestureDetectorCompatImplBase.this.mStillDown) {
                GestureDetectorCompat.GestureDetectorCompatImplBase.this.mDoubleTapListener.onSingleTapConfirmed(GestureDetectorCompat.GestureDetectorCompatImplBase.this.mCurrentDownEvent);
              } else {
                GestureDetectorCompat.GestureDetectorCompatImplBase.this.mDeferConfirmSingleTap = true;
              }  
            return;
          case 2:
            GestureDetectorCompat.GestureDetectorCompatImplBase.this.dispatchLongPress();
            return;
          case 1:
            break;
        } 
        GestureDetectorCompat.GestureDetectorCompatImplBase.this.mListener.onShowPress(GestureDetectorCompat.GestureDetectorCompatImplBase.this.mCurrentDownEvent);
      }
    }
  }
  
  private class GestureHandler extends Handler {
    GestureHandler() {}
    
    GestureHandler(Handler param1Handler) {
      super(param1Handler.getLooper());
    }
    
    public void handleMessage(Message param1Message) {
      StringBuilder stringBuilder;
      switch (param1Message.what) {
        default:
          stringBuilder = new StringBuilder();
          stringBuilder.append("Unknown message ");
          stringBuilder.append(param1Message);
          throw new RuntimeException(stringBuilder.toString());
        case 3:
          if (this.this$0.mDoubleTapListener != null)
            if (!this.this$0.mStillDown) {
              this.this$0.mDoubleTapListener.onSingleTapConfirmed(this.this$0.mCurrentDownEvent);
            } else {
              this.this$0.mDeferConfirmSingleTap = true;
            }  
          return;
        case 2:
          this.this$0.dispatchLongPress();
          return;
        case 1:
          break;
      } 
      this.this$0.mListener.onShowPress(this.this$0.mCurrentDownEvent);
    }
  }
  
  static class GestureDetectorCompatImplJellybeanMr2 implements GestureDetectorCompatImpl {
    private final GestureDetector mDetector;
    
    GestureDetectorCompatImplJellybeanMr2(Context param1Context, GestureDetector.OnGestureListener param1OnGestureListener, Handler param1Handler) {
      this.mDetector = new GestureDetector(param1Context, param1OnGestureListener, param1Handler);
    }
    
    public boolean isLongpressEnabled() {
      return this.mDetector.isLongpressEnabled();
    }
    
    public boolean onTouchEvent(MotionEvent param1MotionEvent) {
      return this.mDetector.onTouchEvent(param1MotionEvent);
    }
    
    public void setIsLongpressEnabled(boolean param1Boolean) {
      this.mDetector.setIsLongpressEnabled(param1Boolean);
    }
    
    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener param1OnDoubleTapListener) {
      this.mDetector.setOnDoubleTapListener(param1OnDoubleTapListener);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\view\GestureDetectorCompat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */