package com.google.android.gms.common.util;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.CharArrayBuffer;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class DataUtils {
  public static void copyStringToBuffer(String paramString, CharArrayBuffer paramCharArrayBuffer) {
    if (TextUtils.isEmpty(paramString)) {
      paramCharArrayBuffer.sizeCopied = 0;
    } else if (paramCharArrayBuffer.data == null || paramCharArrayBuffer.data.length < paramString.length()) {
      paramCharArrayBuffer.data = paramString.toCharArray();
    } else {
      paramString.getChars(0, paramString.length(), paramCharArrayBuffer.data, 0);
    } 
    paramCharArrayBuffer.sizeCopied = paramString.length();
  }
  
  public static byte[] loadImageBytes(AssetManager paramAssetManager, String paramString) {
    try {
      return IOUtils.readInputStreamFully(paramAssetManager.open(paramString));
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } 
  }
  
  @VisibleForTesting
  public static byte[] loadImageBytes(Resources paramResources, int paramInt) {
    try {
      return IOUtils.readInputStreamFully(paramResources.openRawResource(paramInt));
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } 
  }
  
  public static byte[] loadImageBytes(Bitmap paramBitmap) {
    return loadImageBytes(paramBitmap, 100);
  }
  
  public static byte[] loadImageBytes(Bitmap paramBitmap, int paramInt) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.JPEG, paramInt, byteArrayOutputStream);
    return byteArrayOutputStream.toByteArray();
  }
  
  public static byte[] loadImageBytes(BitmapDrawable paramBitmapDrawable) {
    return loadImageBytes(paramBitmapDrawable.getBitmap());
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\commo\\util\DataUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */