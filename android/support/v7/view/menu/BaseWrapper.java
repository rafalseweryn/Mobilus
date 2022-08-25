package android.support.v7.view.menu;

class BaseWrapper<T> {
  final T mWrappedObject;
  
  BaseWrapper(T paramT) {
    if (paramT == null)
      throw new IllegalArgumentException("Wrapped Object can not be null."); 
    this.mWrappedObject = paramT;
  }
  
  public T getWrappedObject() {
    return this.mWrappedObject;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v7\view\menu\BaseWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */