package android.support.v7.graphics.drawable;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.appcompat.R;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatedStateListDrawableCompat extends StateListDrawable {
  private static final String ELEMENT_ITEM = "item";
  
  private static final String ELEMENT_TRANSITION = "transition";
  
  private static final String ITEM_MISSING_DRAWABLE_ERROR = ": <item> tag requires a 'drawable' attribute or child tag defining a drawable";
  
  private static final String LOGTAG = "AnimatedStateListDrawableCompat";
  
  private static final String TRANSITION_MISSING_DRAWABLE_ERROR = ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable";
  
  private static final String TRANSITION_MISSING_FROM_TO_ID = ": <transition> tag requires 'fromId' & 'toId' attributes";
  
  private boolean mMutated;
  
  private AnimatedStateListState mState;
  
  private Transition mTransition;
  
  private int mTransitionFromIndex = -1;
  
  private int mTransitionToIndex = -1;
  
  public AnimatedStateListDrawableCompat() {
    this((AnimatedStateListState)null, (Resources)null);
  }
  
  AnimatedStateListDrawableCompat(@Nullable AnimatedStateListState paramAnimatedStateListState, @Nullable Resources paramResources) {
    super((StateListDrawable.StateListState)null);
    setConstantState(new AnimatedStateListState(paramAnimatedStateListState, this, paramResources));
    onStateChange(getState());
    jumpToCurrentState();
  }
  
  @Nullable
  public static AnimatedStateListDrawableCompat create(@NonNull Context paramContext, @DrawableRes int paramInt, @Nullable Resources.Theme paramTheme) {
    try {
      XmlPullParserException xmlPullParserException;
      Resources resources = paramContext.getResources();
      XmlResourceParser xmlResourceParser = resources.getXml(paramInt);
      AttributeSet attributeSet = Xml.asAttributeSet((XmlPullParser)xmlResourceParser);
      while (true) {
        paramInt = xmlResourceParser.next();
        if (paramInt != 2 && paramInt != 1)
          continue; 
        break;
      } 
      if (paramInt != 2) {
        xmlPullParserException = new XmlPullParserException();
        this("No start tag found");
        throw xmlPullParserException;
      } 
      return createFromXmlInner((Context)xmlPullParserException, resources, (XmlPullParser)xmlResourceParser, attributeSet, paramTheme);
    } catch (XmlPullParserException xmlPullParserException) {
      Log.e(LOGTAG, "parser error", (Throwable)xmlPullParserException);
    } catch (IOException iOException) {
      Log.e(LOGTAG, "parser error", iOException);
    } 
    return null;
  }
  
  public static AnimatedStateListDrawableCompat createFromXmlInner(@NonNull Context paramContext, @NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme) throws IOException, XmlPullParserException {
    StringBuilder stringBuilder;
    String str = paramXmlPullParser.getName();
    if (!str.equals("animated-selector")) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramXmlPullParser.getPositionDescription());
      stringBuilder.append(": invalid animated-selector tag ");
      stringBuilder.append(str);
      throw new XmlPullParserException(stringBuilder.toString());
    } 
    AnimatedStateListDrawableCompat animatedStateListDrawableCompat = new AnimatedStateListDrawableCompat();
    animatedStateListDrawableCompat.inflate((Context)stringBuilder, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    return animatedStateListDrawableCompat;
  }
  
  private void inflateChildElements(@NonNull Context paramContext, @NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    int i = paramXmlPullParser.getDepth() + 1;
    while (true) {
      int j = paramXmlPullParser.next();
      if (j != 1) {
        int k = paramXmlPullParser.getDepth();
        if (k >= i || j != 3) {
          if (j != 2 || k > i)
            continue; 
          if (paramXmlPullParser.getName().equals("item")) {
            parseItem(paramContext, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
            continue;
          } 
          if (paramXmlPullParser.getName().equals("transition"))
            parseTransition(paramContext, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme); 
          continue;
        } 
      } 
      break;
    } 
  }
  
  private void init() {
    onStateChange(getState());
  }
  
  private int parseItem(@NonNull Context paramContext, @NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    StringBuilder stringBuilder;
    Context context;
    TypedArray typedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.AnimatedStateListDrawableItem);
    int i = typedArray.getResourceId(R.styleable.AnimatedStateListDrawableItem_android_id, 0);
    int j = typedArray.getResourceId(R.styleable.AnimatedStateListDrawableItem_android_drawable, -1);
    if (j > 0) {
      context = (Context)AppCompatResources.getDrawable(paramContext, j);
    } else {
      context = null;
    } 
    typedArray.recycle();
    int[] arrayOfInt = extractStateSet(paramAttributeSet);
    paramContext = context;
    if (context == null)
      while (true) {
        Drawable drawable;
        j = paramXmlPullParser.next();
        if (j == 4)
          continue; 
        if (j != 2) {
          stringBuilder = new StringBuilder();
          stringBuilder.append(paramXmlPullParser.getPositionDescription());
          stringBuilder.append(": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
          throw new XmlPullParserException(stringBuilder.toString());
        } 
        if (paramXmlPullParser.getName().equals("vector")) {
          VectorDrawableCompat vectorDrawableCompat = VectorDrawableCompat.createFromXmlInner(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
          break;
        } 
        if (Build.VERSION.SDK_INT >= 21) {
          drawable = Drawable.createFromXmlInner(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
        } else {
          drawable = Drawable.createFromXmlInner(paramResources, paramXmlPullParser, paramAttributeSet);
        } 
        if (drawable == null) {
          stringBuilder = new StringBuilder();
          stringBuilder.append(paramXmlPullParser.getPositionDescription());
          stringBuilder.append(": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
          throw new XmlPullParserException(stringBuilder.toString());
        } 
        return this.mState.addStateSet(arrayOfInt, (Drawable)stringBuilder, i);
      }  
    if (stringBuilder == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramXmlPullParser.getPositionDescription());
      stringBuilder.append(": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
      throw new XmlPullParserException(stringBuilder.toString());
    } 
    return this.mState.addStateSet(arrayOfInt, (Drawable)stringBuilder, i);
  }
  
  private int parseTransition(@NonNull Context paramContext, @NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    // Byte code:
    //   0: aload_2
    //   1: aload #5
    //   3: aload #4
    //   5: getstatic android/support/v7/appcompat/R$styleable.AnimatedStateListDrawableTransition : [I
    //   8: invokestatic obtainAttributes : (Landroid/content/res/Resources;Landroid/content/res/Resources$Theme;Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;
    //   11: astore #6
    //   13: aload #6
    //   15: getstatic android/support/v7/appcompat/R$styleable.AnimatedStateListDrawableTransition_android_fromId : I
    //   18: iconst_m1
    //   19: invokevirtual getResourceId : (II)I
    //   22: istore #7
    //   24: aload #6
    //   26: getstatic android/support/v7/appcompat/R$styleable.AnimatedStateListDrawableTransition_android_toId : I
    //   29: iconst_m1
    //   30: invokevirtual getResourceId : (II)I
    //   33: istore #8
    //   35: aload #6
    //   37: getstatic android/support/v7/appcompat/R$styleable.AnimatedStateListDrawableTransition_android_drawable : I
    //   40: iconst_m1
    //   41: invokevirtual getResourceId : (II)I
    //   44: istore #9
    //   46: iload #9
    //   48: ifle -> 62
    //   51: aload_1
    //   52: iload #9
    //   54: invokestatic getDrawable : (Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;
    //   57: astore #10
    //   59: goto -> 65
    //   62: aconst_null
    //   63: astore #10
    //   65: aload #6
    //   67: getstatic android/support/v7/appcompat/R$styleable.AnimatedStateListDrawableTransition_android_reversible : I
    //   70: iconst_0
    //   71: invokevirtual getBoolean : (IZ)Z
    //   74: istore #11
    //   76: aload #6
    //   78: invokevirtual recycle : ()V
    //   81: aload #10
    //   83: astore #6
    //   85: aload #10
    //   87: ifnonnull -> 212
    //   90: aload_3
    //   91: invokeinterface next : ()I
    //   96: istore #9
    //   98: iload #9
    //   100: iconst_4
    //   101: if_icmpne -> 107
    //   104: goto -> 90
    //   107: iload #9
    //   109: iconst_2
    //   110: if_icmpeq -> 151
    //   113: new java/lang/StringBuilder
    //   116: dup
    //   117: invokespecial <init> : ()V
    //   120: astore_1
    //   121: aload_1
    //   122: aload_3
    //   123: invokeinterface getPositionDescription : ()Ljava/lang/String;
    //   128: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: pop
    //   132: aload_1
    //   133: ldc ': <transition> tag requires a 'drawable' attribute or child tag defining a drawable'
    //   135: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: pop
    //   139: new org/xmlpull/v1/XmlPullParserException
    //   142: dup
    //   143: aload_1
    //   144: invokevirtual toString : ()Ljava/lang/String;
    //   147: invokespecial <init> : (Ljava/lang/String;)V
    //   150: athrow
    //   151: aload_3
    //   152: invokeinterface getName : ()Ljava/lang/String;
    //   157: ldc_w 'animated-vector'
    //   160: invokevirtual equals : (Ljava/lang/Object;)Z
    //   163: ifeq -> 181
    //   166: aload_1
    //   167: aload_2
    //   168: aload_3
    //   169: aload #4
    //   171: aload #5
    //   173: invokestatic createFromXmlInner : (Landroid/content/Context;Landroid/content/res/Resources;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;Landroid/content/res/Resources$Theme;)Landroid/support/graphics/drawable/AnimatedVectorDrawableCompat;
    //   176: astore #6
    //   178: goto -> 212
    //   181: getstatic android/os/Build$VERSION.SDK_INT : I
    //   184: bipush #21
    //   186: if_icmplt -> 203
    //   189: aload_2
    //   190: aload_3
    //   191: aload #4
    //   193: aload #5
    //   195: invokestatic createFromXmlInner : (Landroid/content/res/Resources;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;Landroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;
    //   198: astore #6
    //   200: goto -> 212
    //   203: aload_2
    //   204: aload_3
    //   205: aload #4
    //   207: invokestatic createFromXmlInner : (Landroid/content/res/Resources;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;)Landroid/graphics/drawable/Drawable;
    //   210: astore #6
    //   212: aload #6
    //   214: ifnonnull -> 255
    //   217: new java/lang/StringBuilder
    //   220: dup
    //   221: invokespecial <init> : ()V
    //   224: astore_1
    //   225: aload_1
    //   226: aload_3
    //   227: invokeinterface getPositionDescription : ()Ljava/lang/String;
    //   232: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   235: pop
    //   236: aload_1
    //   237: ldc ': <transition> tag requires a 'drawable' attribute or child tag defining a drawable'
    //   239: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   242: pop
    //   243: new org/xmlpull/v1/XmlPullParserException
    //   246: dup
    //   247: aload_1
    //   248: invokevirtual toString : ()Ljava/lang/String;
    //   251: invokespecial <init> : (Ljava/lang/String;)V
    //   254: athrow
    //   255: iload #7
    //   257: iconst_m1
    //   258: if_icmpeq -> 286
    //   261: iload #8
    //   263: iconst_m1
    //   264: if_icmpne -> 270
    //   267: goto -> 286
    //   270: aload_0
    //   271: getfield mState : Landroid/support/v7/graphics/drawable/AnimatedStateListDrawableCompat$AnimatedStateListState;
    //   274: iload #7
    //   276: iload #8
    //   278: aload #6
    //   280: iload #11
    //   282: invokevirtual addTransition : (IILandroid/graphics/drawable/Drawable;Z)I
    //   285: ireturn
    //   286: new java/lang/StringBuilder
    //   289: dup
    //   290: invokespecial <init> : ()V
    //   293: astore_1
    //   294: aload_1
    //   295: aload_3
    //   296: invokeinterface getPositionDescription : ()Ljava/lang/String;
    //   301: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   304: pop
    //   305: aload_1
    //   306: ldc ': <transition> tag requires 'fromId' & 'toId' attributes'
    //   308: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   311: pop
    //   312: new org/xmlpull/v1/XmlPullParserException
    //   315: dup
    //   316: aload_1
    //   317: invokevirtual toString : ()Ljava/lang/String;
    //   320: invokespecial <init> : (Ljava/lang/String;)V
    //   323: athrow
  }
  
  private boolean selectTransition(int paramInt) {
    AnimatableTransition animatableTransition;
    int i;
    Transition transition = this.mTransition;
    if (transition != null) {
      if (paramInt == this.mTransitionToIndex)
        return true; 
      if (paramInt == this.mTransitionFromIndex && transition.canReverse()) {
        transition.reverse();
        this.mTransitionToIndex = this.mTransitionFromIndex;
        this.mTransitionFromIndex = paramInt;
        return true;
      } 
      i = this.mTransitionToIndex;
      transition.stop();
    } else {
      i = getCurrentIndex();
    } 
    this.mTransition = null;
    this.mTransitionFromIndex = -1;
    this.mTransitionToIndex = -1;
    AnimatedStateListState animatedStateListState = this.mState;
    int j = animatedStateListState.getKeyframeIdAt(i);
    int k = animatedStateListState.getKeyframeIdAt(paramInt);
    if (k == 0 || j == 0)
      return false; 
    int m = animatedStateListState.indexOfTransition(j, k);
    if (m < 0)
      return false; 
    boolean bool = animatedStateListState.transitionHasReversibleFlag(j, k);
    selectDrawable(m);
    Drawable drawable = getCurrent();
    if (drawable instanceof AnimationDrawable) {
      boolean bool1 = animatedStateListState.isTransitionReversed(j, k);
      AnimationDrawableTransition animationDrawableTransition = new AnimationDrawableTransition((AnimationDrawable)drawable, bool1, bool);
    } else if (drawable instanceof AnimatedVectorDrawableCompat) {
      AnimatedVectorDrawableTransition animatedVectorDrawableTransition = new AnimatedVectorDrawableTransition((AnimatedVectorDrawableCompat)drawable);
    } else {
      if (drawable instanceof Animatable) {
        animatableTransition = new AnimatableTransition((Animatable)drawable);
        animatableTransition.start();
        this.mTransition = animatableTransition;
        this.mTransitionFromIndex = i;
        this.mTransitionToIndex = paramInt;
        return true;
      } 
      return false;
    } 
    animatableTransition.start();
    this.mTransition = animatableTransition;
    this.mTransitionFromIndex = i;
    this.mTransitionToIndex = paramInt;
    return true;
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) {
    AnimatedStateListState animatedStateListState = this.mState;
    if (Build.VERSION.SDK_INT >= 21)
      animatedStateListState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations(); 
    animatedStateListState.setVariablePadding(paramTypedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_variablePadding, animatedStateListState.mVariablePadding));
    animatedStateListState.setConstantSize(paramTypedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_constantSize, animatedStateListState.mConstantSize));
    animatedStateListState.setEnterFadeDuration(paramTypedArray.getInt(R.styleable.AnimatedStateListDrawableCompat_android_enterFadeDuration, animatedStateListState.mEnterFadeDuration));
    animatedStateListState.setExitFadeDuration(paramTypedArray.getInt(R.styleable.AnimatedStateListDrawableCompat_android_exitFadeDuration, animatedStateListState.mExitFadeDuration));
    setDither(paramTypedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_dither, animatedStateListState.mDither));
  }
  
  public void addState(@NonNull int[] paramArrayOfint, @NonNull Drawable paramDrawable, int paramInt) {
    if (paramDrawable == null)
      throw new IllegalArgumentException("Drawable must not be null"); 
    this.mState.addStateSet(paramArrayOfint, paramDrawable, paramInt);
    onStateChange(getState());
  }
  
  public <T extends Drawable & Animatable> void addTransition(int paramInt1, int paramInt2, @NonNull T paramT, boolean paramBoolean) {
    if (paramT == null)
      throw new IllegalArgumentException("Transition drawable must not be null"); 
    this.mState.addTransition(paramInt1, paramInt2, (Drawable)paramT, paramBoolean);
  }
  
  void clearMutated() {
    super.clearMutated();
    this.mMutated = false;
  }
  
  AnimatedStateListState cloneConstantState() {
    return new AnimatedStateListState(this.mState, this, null);
  }
  
  public void inflate(@NonNull Context paramContext, @NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    TypedArray typedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.AnimatedStateListDrawableCompat);
    setVisible(typedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_visible, true), true);
    updateStateFromTypedArray(typedArray);
    updateDensity(paramResources);
    typedArray.recycle();
    inflateChildElements(paramContext, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    init();
  }
  
  public boolean isStateful() {
    return true;
  }
  
  public void jumpToCurrentState() {
    super.jumpToCurrentState();
    if (this.mTransition != null) {
      this.mTransition.stop();
      this.mTransition = null;
      selectDrawable(this.mTransitionToIndex);
      this.mTransitionToIndex = -1;
      this.mTransitionFromIndex = -1;
    } 
  }
  
  public Drawable mutate() {
    if (!this.mMutated && super.mutate() == this) {
      this.mState.mutate();
      this.mMutated = true;
    } 
    return this;
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    boolean bool1;
    int i = this.mState.indexOfKeyframe(paramArrayOfint);
    if (i != getCurrentIndex() && (selectTransition(i) || selectDrawable(i))) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    Drawable drawable = getCurrent();
    boolean bool2 = bool1;
    if (drawable != null)
      bool2 = bool1 | drawable.setState(paramArrayOfint); 
    return bool2;
  }
  
  protected void setConstantState(@NonNull DrawableContainer.DrawableContainerState paramDrawableContainerState) {
    super.setConstantState(paramDrawableContainerState);
    if (paramDrawableContainerState instanceof AnimatedStateListState)
      this.mState = (AnimatedStateListState)paramDrawableContainerState; 
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2) {
    boolean bool = super.setVisible(paramBoolean1, paramBoolean2);
    if (this.mTransition != null && (bool || paramBoolean2))
      if (paramBoolean1) {
        this.mTransition.start();
      } else {
        jumpToCurrentState();
      }  
    return bool;
  }
  
  private static class AnimatableTransition extends Transition {
    private final Animatable mA;
    
    AnimatableTransition(Animatable param1Animatable) {
      this.mA = param1Animatable;
    }
    
    public void start() {
      this.mA.start();
    }
    
    public void stop() {
      this.mA.stop();
    }
  }
  
  static class AnimatedStateListState extends StateListDrawable.StateListState {
    private static final long REVERSED_BIT = 4294967296L;
    
    private static final long REVERSIBLE_FLAG_BIT = 8589934592L;
    
    SparseArrayCompat<Integer> mStateIds;
    
    LongSparseArray<Long> mTransitions;
    
    AnimatedStateListState(@Nullable AnimatedStateListState param1AnimatedStateListState, @NonNull AnimatedStateListDrawableCompat param1AnimatedStateListDrawableCompat, @Nullable Resources param1Resources) {
      super(param1AnimatedStateListState, param1AnimatedStateListDrawableCompat, param1Resources);
      if (param1AnimatedStateListState != null) {
        this.mTransitions = param1AnimatedStateListState.mTransitions;
        this.mStateIds = param1AnimatedStateListState.mStateIds;
      } else {
        this.mTransitions = new LongSparseArray();
        this.mStateIds = new SparseArrayCompat();
      } 
    }
    
    private static long generateTransitionKey(int param1Int1, int param1Int2) {
      long l = param1Int1;
      return param1Int2 | l << 32L;
    }
    
    int addStateSet(@NonNull int[] param1ArrayOfint, @NonNull Drawable param1Drawable, int param1Int) {
      int i = addStateSet(param1ArrayOfint, param1Drawable);
      this.mStateIds.put(i, Integer.valueOf(param1Int));
      return i;
    }
    
    int addTransition(int param1Int1, int param1Int2, @NonNull Drawable param1Drawable, boolean param1Boolean) {
      long l2;
      int i = addChild(param1Drawable);
      long l1 = generateTransitionKey(param1Int1, param1Int2);
      if (param1Boolean) {
        l2 = 8589934592L;
      } else {
        l2 = 0L;
      } 
      LongSparseArray<Long> longSparseArray = this.mTransitions;
      long l3 = i;
      longSparseArray.append(l1, Long.valueOf(l3 | l2));
      if (param1Boolean) {
        l1 = generateTransitionKey(param1Int2, param1Int1);
        this.mTransitions.append(l1, Long.valueOf(0x100000000L | l3 | l2));
      } 
      return i;
    }
    
    int getKeyframeIdAt(int param1Int) {
      boolean bool = false;
      if (param1Int < 0) {
        param1Int = bool;
      } else {
        param1Int = ((Integer)this.mStateIds.get(param1Int, Integer.valueOf(0))).intValue();
      } 
      return param1Int;
    }
    
    int indexOfKeyframe(@NonNull int[] param1ArrayOfint) {
      int i = indexOfStateSet(param1ArrayOfint);
      return (i >= 0) ? i : indexOfStateSet(StateSet.WILD_CARD);
    }
    
    int indexOfTransition(int param1Int1, int param1Int2) {
      long l = generateTransitionKey(param1Int1, param1Int2);
      return (int)((Long)this.mTransitions.get(l, Long.valueOf(-1L))).longValue();
    }
    
    boolean isTransitionReversed(int param1Int1, int param1Int2) {
      boolean bool;
      long l = generateTransitionKey(param1Int1, param1Int2);
      if ((((Long)this.mTransitions.get(l, Long.valueOf(-1L))).longValue() & 0x100000000L) != 0L) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    void mutate() {
      this.mTransitions = this.mTransitions.clone();
      this.mStateIds = this.mStateIds.clone();
    }
    
    @NonNull
    public Drawable newDrawable() {
      return new AnimatedStateListDrawableCompat(this, null);
    }
    
    @NonNull
    public Drawable newDrawable(Resources param1Resources) {
      return new AnimatedStateListDrawableCompat(this, param1Resources);
    }
    
    boolean transitionHasReversibleFlag(int param1Int1, int param1Int2) {
      boolean bool;
      long l = generateTransitionKey(param1Int1, param1Int2);
      if ((((Long)this.mTransitions.get(l, Long.valueOf(-1L))).longValue() & 0x200000000L) != 0L) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
  }
  
  private static class AnimatedVectorDrawableTransition extends Transition {
    private final AnimatedVectorDrawableCompat mAvd;
    
    AnimatedVectorDrawableTransition(AnimatedVectorDrawableCompat param1AnimatedVectorDrawableCompat) {
      this.mAvd = param1AnimatedVectorDrawableCompat;
    }
    
    public void start() {
      this.mAvd.start();
    }
    
    public void stop() {
      this.mAvd.stop();
    }
  }
  
  private static class AnimationDrawableTransition extends Transition {
    private final ObjectAnimator mAnim;
    
    private final boolean mHasReversibleFlag;
    
    AnimationDrawableTransition(AnimationDrawable param1AnimationDrawable, boolean param1Boolean1, boolean param1Boolean2) {
      boolean bool;
      int i = param1AnimationDrawable.getNumberOfFrames();
      if (param1Boolean1) {
        bool = i - 1;
      } else {
        bool = false;
      } 
      if (param1Boolean1) {
        i = 0;
      } else {
        i--;
      } 
      AnimatedStateListDrawableCompat.FrameInterpolator frameInterpolator = new AnimatedStateListDrawableCompat.FrameInterpolator(param1AnimationDrawable, param1Boolean1);
      ObjectAnimator objectAnimator = ObjectAnimator.ofInt(param1AnimationDrawable, "currentIndex", new int[] { bool, i });
      if (Build.VERSION.SDK_INT >= 18)
        objectAnimator.setAutoCancel(true); 
      objectAnimator.setDuration(frameInterpolator.getTotalDuration());
      objectAnimator.setInterpolator(frameInterpolator);
      this.mHasReversibleFlag = param1Boolean2;
      this.mAnim = objectAnimator;
    }
    
    public boolean canReverse() {
      return this.mHasReversibleFlag;
    }
    
    public void reverse() {
      this.mAnim.reverse();
    }
    
    public void start() {
      this.mAnim.start();
    }
    
    public void stop() {
      this.mAnim.cancel();
    }
  }
  
  private static class FrameInterpolator implements TimeInterpolator {
    private int[] mFrameTimes;
    
    private int mFrames;
    
    private int mTotalDuration;
    
    FrameInterpolator(AnimationDrawable param1AnimationDrawable, boolean param1Boolean) {
      updateFrames(param1AnimationDrawable, param1Boolean);
    }
    
    public float getInterpolation(float param1Float) {
      int i = (int)(param1Float * this.mTotalDuration + 0.5F);
      int j = this.mFrames;
      int[] arrayOfInt = this.mFrameTimes;
      byte b;
      for (b = 0; b < j && i >= arrayOfInt[b]; b++)
        i -= arrayOfInt[b]; 
      if (b < j) {
        param1Float = i / this.mTotalDuration;
      } else {
        param1Float = 0.0F;
      } 
      return b / j + param1Float;
    }
    
    int getTotalDuration() {
      return this.mTotalDuration;
    }
    
    int updateFrames(AnimationDrawable param1AnimationDrawable, boolean param1Boolean) {
      int i = param1AnimationDrawable.getNumberOfFrames();
      this.mFrames = i;
      if (this.mFrameTimes == null || this.mFrameTimes.length < i)
        this.mFrameTimes = new int[i]; 
      int[] arrayOfInt = this.mFrameTimes;
      byte b = 0;
      int j = 0;
      while (b < i) {
        if (param1Boolean) {
          k = i - b - 1;
        } else {
          k = b;
        } 
        int k = param1AnimationDrawable.getDuration(k);
        arrayOfInt[b] = k;
        j += k;
        b++;
      } 
      this.mTotalDuration = j;
      return j;
    }
  }
  
  private static abstract class Transition {
    private Transition() {}
    
    public boolean canReverse() {
      return false;
    }
    
    public void reverse() {}
    
    public abstract void start();
    
    public abstract void stop();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v7\graphics\drawable\AnimatedStateListDrawableCompat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */