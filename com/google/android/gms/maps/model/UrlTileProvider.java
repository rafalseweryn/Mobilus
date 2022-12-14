package com.google.android.gms.maps.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public abstract class UrlTileProvider implements TileProvider {
  private final int height;
  
  private final int width;
  
  public UrlTileProvider(int paramInt1, int paramInt2) {
    this.width = paramInt1;
    this.height = paramInt2;
  }
  
  public final Tile getTile(int paramInt1, int paramInt2, int paramInt3) {
    URL uRL = getTileUrl(paramInt1, paramInt2, paramInt3);
    if (uRL == null)
      return NO_TILE; 
    try {
      paramInt3 = this.width;
      paramInt2 = this.height;
      InputStream inputStream = uRL.openStream();
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      this();
      byte[] arrayOfByte = new byte[4096];
      while (true) {
        paramInt1 = inputStream.read(arrayOfByte);
        if (paramInt1 != -1) {
          byteArrayOutputStream.write(arrayOfByte, 0, paramInt1);
          continue;
        } 
        return new Tile(paramInt3, paramInt2, byteArrayOutputStream.toByteArray());
      } 
    } catch (IOException iOException) {
      return null;
    } 
  }
  
  public abstract URL getTileUrl(int paramInt1, int paramInt2, int paramInt3);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\google\android\gms\maps\model\UrlTileProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */