package android.support.transition;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.annotation.NonNull;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.ViewGroup;
import java.io.IOException;
import java.lang.reflect.Constructor;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class TransitionInflater {
  private static final ArrayMap<String, Constructor> CONSTRUCTORS;
  
  private static final Class<?>[] CONSTRUCTOR_SIGNATURE = new Class[] { Context.class, AttributeSet.class };
  
  private final Context mContext;
  
  static {
    CONSTRUCTORS = new ArrayMap();
  }
  
  private TransitionInflater(@NonNull Context paramContext) {
    this.mContext = paramContext;
  }
  
  private Object createCustom(AttributeSet paramAttributeSet, Class<?> paramClass, String paramString) {
    StringBuilder stringBuilder;
    String str = paramAttributeSet.getAttributeValue(null, "class");
    if (str == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(" tag must have a 'class' attribute");
      throw new InflateException(stringBuilder.toString());
    } 
    try {
      synchronized (CONSTRUCTORS) {
        Constructor<?> constructor2 = (Constructor)CONSTRUCTORS.get(str);
        Constructor<?> constructor1 = constructor2;
        if (constructor2 == null) {
          Class<?> clazz = this.mContext.getClassLoader().loadClass(str).asSubclass(paramClass);
          constructor1 = constructor2;
          if (clazz != null) {
            constructor1 = clazz.getConstructor(CONSTRUCTOR_SIGNATURE);
            constructor1.setAccessible(true);
            CONSTRUCTORS.put(str, constructor1);
          } 
        } 
        stringBuilder = (StringBuilder)constructor1.newInstance(new Object[] { this.mContext, stringBuilder });
        return stringBuilder;
      } 
    } catch (Exception exception) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Could not instantiate ");
      stringBuilder.append(paramClass);
      stringBuilder.append(" class ");
      stringBuilder.append(str);
      throw new InflateException(stringBuilder.toString(), exception);
    } 
  }
  
  private Transition createTransitionFromXml(XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Transition paramTransition) throws XmlPullParserException, IOException {
    TransitionSet transitionSet;
    Transition transition;
    int i = paramXmlPullParser.getDepth();
    if (paramTransition instanceof TransitionSet) {
      transitionSet = (TransitionSet)paramTransition;
    } else {
      transitionSet = null;
    } 
    label78: while (true) {
      transition = null;
      while (true) {
        int j = paramXmlPullParser.next();
        if ((j != 3 || paramXmlPullParser.getDepth() > i) && j != 1) {
          StringBuilder stringBuilder;
          Transition transition1;
          if (j != 2)
            continue; 
          String str = paramXmlPullParser.getName();
          if ("fade".equals(str)) {
            transition1 = new Fade(this.mContext, paramAttributeSet);
          } else {
            ChangeBounds changeBounds;
            if ("changeBounds".equals(transition1)) {
              changeBounds = new ChangeBounds(this.mContext, paramAttributeSet);
            } else {
              Slide slide;
              if ("slide".equals(changeBounds)) {
                slide = new Slide(this.mContext, paramAttributeSet);
              } else {
                Explode explode;
                if ("explode".equals(slide)) {
                  explode = new Explode(this.mContext, paramAttributeSet);
                } else {
                  ChangeImageTransform changeImageTransform;
                  if ("changeImageTransform".equals(explode)) {
                    changeImageTransform = new ChangeImageTransform(this.mContext, paramAttributeSet);
                  } else {
                    ChangeTransform changeTransform;
                    if ("changeTransform".equals(changeImageTransform)) {
                      changeTransform = new ChangeTransform(this.mContext, paramAttributeSet);
                    } else {
                      ChangeClipBounds changeClipBounds;
                      if ("changeClipBounds".equals(changeTransform)) {
                        changeClipBounds = new ChangeClipBounds(this.mContext, paramAttributeSet);
                      } else {
                        AutoTransition autoTransition;
                        if ("autoTransition".equals(changeClipBounds)) {
                          autoTransition = new AutoTransition(this.mContext, paramAttributeSet);
                        } else {
                          ChangeScroll changeScroll;
                          if ("changeScroll".equals(autoTransition)) {
                            changeScroll = new ChangeScroll(this.mContext, paramAttributeSet);
                          } else {
                            TransitionSet transitionSet1;
                            if ("transitionSet".equals(changeScroll)) {
                              transitionSet1 = new TransitionSet(this.mContext, paramAttributeSet);
                            } else if ("transition".equals(transitionSet1)) {
                              transition1 = (Transition)createCustom(paramAttributeSet, Transition.class, "transition");
                            } else if ("targets".equals(transition1)) {
                              getTargetIds(paramXmlPullParser, paramAttributeSet, paramTransition);
                              transition1 = transition;
                            } else if ("arcMotion".equals(transition1)) {
                              if (paramTransition == null)
                                throw new RuntimeException("Invalid use of arcMotion element"); 
                              paramTransition.setPathMotion(new ArcMotion(this.mContext, paramAttributeSet));
                              transition1 = transition;
                            } else if ("pathMotion".equals(transition1)) {
                              if (paramTransition == null)
                                throw new RuntimeException("Invalid use of pathMotion element"); 
                              paramTransition.setPathMotion((PathMotion)createCustom(paramAttributeSet, PathMotion.class, "pathMotion"));
                              transition1 = transition;
                            } else if ("patternPathMotion".equals(transition1)) {
                              if (paramTransition == null)
                                throw new RuntimeException("Invalid use of patternPathMotion element"); 
                              paramTransition.setPathMotion(new PatternPathMotion(this.mContext, paramAttributeSet));
                              transition1 = transition;
                            } else {
                              stringBuilder = new StringBuilder();
                              stringBuilder.append("Unknown scene name: ");
                              stringBuilder.append(paramXmlPullParser.getName());
                              throw new RuntimeException(stringBuilder.toString());
                            } 
                          } 
                        } 
                      } 
                    } 
                  } 
                } 
              } 
            } 
          } 
          transition = transition1;
          if (transition1 != null) {
            if (!paramXmlPullParser.isEmptyElementTag())
              createTransitionFromXml(paramXmlPullParser, (AttributeSet)stringBuilder, transition1); 
            if (transitionSet != null) {
              transitionSet.addTransition(transition1);
              continue label78;
            } 
            transition = transition1;
            if (paramTransition != null)
              throw new InflateException("Could not add transition to another transition."); 
          } 
          continue;
        } 
        break;
      } 
      break;
    } 
    return transition;
  }
  
  private TransitionManager createTransitionManagerFromXml(XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, ViewGroup paramViewGroup) throws XmlPullParserException, IOException {
    int i = paramXmlPullParser.getDepth();
    TransitionManager transitionManager = null;
    while (true) {
      int j = paramXmlPullParser.next();
      if ((j != 3 || paramXmlPullParser.getDepth() > i) && j != 1) {
        if (j != 2)
          continue; 
        String str = paramXmlPullParser.getName();
        if (str.equals("transitionManager")) {
          transitionManager = new TransitionManager();
          continue;
        } 
        if (str.equals("transition") && transitionManager != null) {
          loadTransition(paramAttributeSet, paramXmlPullParser, paramViewGroup, transitionManager);
          continue;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown scene name: ");
        stringBuilder.append(paramXmlPullParser.getName());
        throw new RuntimeException(stringBuilder.toString());
      } 
      break;
    } 
    return transitionManager;
  }
  
  public static TransitionInflater from(Context paramContext) {
    return new TransitionInflater(paramContext);
  }
  
  private void getTargetIds(XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Transition paramTransition) throws XmlPullParserException, IOException {
    int i = paramXmlPullParser.getDepth();
    while (true) {
      int j = paramXmlPullParser.next();
      if ((j != 3 || paramXmlPullParser.getDepth() > i) && j != 1) {
        String str;
        if (j != 2)
          continue; 
        if (paramXmlPullParser.getName().equals("target")) {
          TypedArray typedArray = this.mContext.obtainStyledAttributes(paramAttributeSet, Styleable.TRANSITION_TARGET);
          j = TypedArrayUtils.getNamedResourceId(typedArray, paramXmlPullParser, "targetId", 1, 0);
          if (j != 0) {
            paramTransition.addTarget(j);
          } else {
            j = TypedArrayUtils.getNamedResourceId(typedArray, paramXmlPullParser, "excludeId", 2, 0);
            if (j != 0) {
              paramTransition.excludeTarget(j, true);
            } else {
              String str1 = TypedArrayUtils.getNamedString(typedArray, paramXmlPullParser, "targetName", 4);
              if (str1 != null) {
                paramTransition.addTarget(str1);
              } else {
                str1 = TypedArrayUtils.getNamedString(typedArray, paramXmlPullParser, "excludeName", 5);
                if (str1 != null) {
                  paramTransition.excludeTarget(str1, true);
                } else {
                  String str2 = TypedArrayUtils.getNamedString(typedArray, paramXmlPullParser, "excludeClass", 3);
                  if (str2 != null) {
                    try {
                      paramTransition.excludeTarget(Class.forName(str2), true);
                    } catch (ClassNotFoundException classNotFoundException) {
                      str = str2;
                      continue;
                    } 
                  } else {
                    str1 = TypedArrayUtils.getNamedString(typedArray, (XmlPullParser)str, "targetClass", 0);
                    if (str1 != null)
                      try {
                        paramTransition.addTarget(Class.forName(str1));
                      } catch (ClassNotFoundException classNotFoundException) {
                        str = str1;
                      }  
                  } 
                } 
              } 
            } 
          } 
          typedArray.recycle();
          continue;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown scene name: ");
        stringBuilder.append(str.getName());
        throw new RuntimeException(stringBuilder.toString());
      } 
      break;
    } 
  }
  
  private void loadTransition(AttributeSet paramAttributeSet, XmlPullParser paramXmlPullParser, ViewGroup paramViewGroup, TransitionManager paramTransitionManager) throws Resources.NotFoundException {
    Scene scene;
    TypedArray typedArray = this.mContext.obtainStyledAttributes(paramAttributeSet, Styleable.TRANSITION_MANAGER);
    int i = TypedArrayUtils.getNamedResourceId(typedArray, paramXmlPullParser, "transition", 2, -1);
    int j = TypedArrayUtils.getNamedResourceId(typedArray, paramXmlPullParser, "fromScene", 0, -1);
    XmlPullParser xmlPullParser = null;
    if (j < 0) {
      paramAttributeSet = null;
    } else {
      Scene scene1 = Scene.getSceneForLayout(paramViewGroup, j, this.mContext);
    } 
    j = TypedArrayUtils.getNamedResourceId(typedArray, paramXmlPullParser, "toScene", 1, -1);
    if (j < 0) {
      paramXmlPullParser = xmlPullParser;
    } else {
      scene = Scene.getSceneForLayout(paramViewGroup, j, this.mContext);
    } 
    if (i >= 0) {
      Transition transition = inflateTransition(i);
      if (transition != null) {
        StringBuilder stringBuilder;
        if (scene == null) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("No toScene for transition ID ");
          stringBuilder.append(i);
          throw new RuntimeException(stringBuilder.toString());
        } 
        if (stringBuilder == null) {
          paramTransitionManager.setTransition(scene, transition);
        } else {
          paramTransitionManager.setTransition((Scene)stringBuilder, scene, transition);
        } 
      } 
    } 
    typedArray.recycle();
  }
  
  public Transition inflateTransition(int paramInt) {
    Exception exception;
    XmlResourceParser xmlResourceParser = this.mContext.getResources().getXml(paramInt);
    try {
      Transition transition = createTransitionFromXml((XmlPullParser)xmlResourceParser, Xml.asAttributeSet((XmlPullParser)xmlResourceParser), null);
      xmlResourceParser.close();
      return transition;
    } catch (XmlPullParserException xmlPullParserException) {
      InflateException inflateException = new InflateException();
      this(xmlPullParserException.getMessage(), (Throwable)xmlPullParserException);
      throw inflateException;
    } catch (IOException null) {
      InflateException inflateException = new InflateException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(xmlResourceParser.getPositionDescription());
      stringBuilder.append(": ");
      stringBuilder.append(exception.getMessage());
      this(stringBuilder.toString(), exception);
      throw inflateException;
    } finally {}
    xmlResourceParser.close();
    throw exception;
  }
  
  public TransitionManager inflateTransitionManager(int paramInt, ViewGroup paramViewGroup) {
    XmlResourceParser xmlResourceParser = this.mContext.getResources().getXml(paramInt);
    try {
      TransitionManager transitionManager = createTransitionManagerFromXml((XmlPullParser)xmlResourceParser, Xml.asAttributeSet((XmlPullParser)xmlResourceParser), paramViewGroup);
      xmlResourceParser.close();
      return transitionManager;
    } catch (XmlPullParserException xmlPullParserException) {
      InflateException inflateException = new InflateException();
      this(xmlPullParserException.getMessage());
      inflateException.initCause((Throwable)xmlPullParserException);
      throw inflateException;
    } catch (IOException iOException) {
      InflateException inflateException = new InflateException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(xmlResourceParser.getPositionDescription());
      stringBuilder.append(": ");
      stringBuilder.append(iOException.getMessage());
      this(stringBuilder.toString());
      inflateException.initCause(iOException);
      throw inflateException;
    } finally {}
    xmlResourceParser.close();
    throw paramViewGroup;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\transition\TransitionInflater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */