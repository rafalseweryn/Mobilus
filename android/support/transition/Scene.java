package android.support.transition;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Scene {
  private Context mContext;
  
  private Runnable mEnterAction;
  
  private Runnable mExitAction;
  
  private View mLayout;
  
  private int mLayoutId = -1;
  
  private ViewGroup mSceneRoot;
  
  public Scene(@NonNull ViewGroup paramViewGroup) {
    this.mSceneRoot = paramViewGroup;
  }
  
  private Scene(ViewGroup paramViewGroup, int paramInt, Context paramContext) {
    this.mContext = paramContext;
    this.mSceneRoot = paramViewGroup;
    this.mLayoutId = paramInt;
  }
  
  public Scene(@NonNull ViewGroup paramViewGroup, @NonNull View paramView) {
    this.mSceneRoot = paramViewGroup;
    this.mLayout = paramView;
  }
  
  static Scene getCurrentScene(View paramView) {
    return (Scene)paramView.getTag(R.id.transition_current_scene);
  }
  
  @NonNull
  public static Scene getSceneForLayout(@NonNull ViewGroup paramViewGroup, @LayoutRes int paramInt, @NonNull Context paramContext) {
    SparseArray sparseArray1 = (SparseArray)paramViewGroup.getTag(R.id.transition_scene_layoutid_cache);
    SparseArray sparseArray2 = sparseArray1;
    if (sparseArray1 == null) {
      sparseArray2 = new SparseArray();
      paramViewGroup.setTag(R.id.transition_scene_layoutid_cache, sparseArray2);
    } 
    Scene scene2 = (Scene)sparseArray2.get(paramInt);
    if (scene2 != null)
      return scene2; 
    Scene scene1 = new Scene(paramViewGroup, paramInt, paramContext);
    sparseArray2.put(paramInt, scene1);
    return scene1;
  }
  
  static void setCurrentScene(View paramView, Scene paramScene) {
    paramView.setTag(R.id.transition_current_scene, paramScene);
  }
  
  public void enter() {
    if (this.mLayoutId > 0 || this.mLayout != null) {
      getSceneRoot().removeAllViews();
      if (this.mLayoutId > 0) {
        LayoutInflater.from(this.mContext).inflate(this.mLayoutId, this.mSceneRoot);
      } else {
        this.mSceneRoot.addView(this.mLayout);
      } 
    } 
    if (this.mEnterAction != null)
      this.mEnterAction.run(); 
    setCurrentScene((View)this.mSceneRoot, this);
  }
  
  public void exit() {
    if (getCurrentScene((View)this.mSceneRoot) == this && this.mExitAction != null)
      this.mExitAction.run(); 
  }
  
  @NonNull
  public ViewGroup getSceneRoot() {
    return this.mSceneRoot;
  }
  
  boolean isCreatedFromLayoutResource() {
    boolean bool;
    if (this.mLayoutId > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void setEnterAction(@Nullable Runnable paramRunnable) {
    this.mEnterAction = paramRunnable;
  }
  
  public void setExitAction(@Nullable Runnable paramRunnable) {
    this.mExitAction = paramRunnable;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\transition\Scene.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */