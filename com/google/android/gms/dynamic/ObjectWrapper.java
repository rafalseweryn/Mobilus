package com.google.android.gms.dynamic;

import android.os.IBinder;
import java.lang.reflect.Field;

public final class ObjectWrapper<T> extends IObjectWrapper.Stub {
  private final T zzabn;
  
  private ObjectWrapper(T paramT) {
    this.zzabn = paramT;
  }
  
  public static <T> T unwrap(IObjectWrapper paramIObjectWrapper) {
    Field field;
    if (paramIObjectWrapper instanceof ObjectWrapper)
      return ((ObjectWrapper)paramIObjectWrapper).zzabn; 
    IBinder iBinder = paramIObjectWrapper.asBinder();
    Field[] arrayOfField = iBinder.getClass().getDeclaredFields();
    int i = arrayOfField.length;
    int j = 0;
    paramIObjectWrapper = null;
    int k;
    for (k = 0; j < i; k = m) {
      Field field1 = arrayOfField[j];
      int m = k;
      if (!field1.isSynthetic()) {
        m = k + 1;
        field = field1;
      } 
      j++;
    } 
    if (k == 1) {
      if (!field.isAccessible()) {
        field.setAccessible(true);
        try {
          return (T)field.get(iBinder);
        } catch (NullPointerException nullPointerException) {
          throw new IllegalArgumentException("Binder object is null.", nullPointerException);
        } catch (IllegalAccessException illegalAccessException) {
          throw new IllegalArgumentException("Could not access the field in remoteBinder.", illegalAccessException);
        } 
      } 
      throw new IllegalArgumentException("IObjectWrapper declared field not private!");
    } 
    j = arrayOfField.length;
    StringBuilder stringBuilder = new StringBuilder(64);
    stringBuilder.append("Unexpected number of IObjectWrapper declared fields: ");
    stringBuilder.append(j);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public static <T> IObjectWrapper wrap(T paramT) {
    return new ObjectWrapper<>(paramT);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\dynamic\ObjectWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */