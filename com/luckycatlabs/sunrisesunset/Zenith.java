package com.luckycatlabs.sunrisesunset;

import java.math.BigDecimal;

public class Zenith {
  public static final Zenith ASTRONOMICAL = new Zenith(108.0D);
  
  public static final Zenith CIVIL;
  
  public static final Zenith NAUTICAL = new Zenith(102.0D);
  
  public static final Zenith OFFICIAL;
  
  private final BigDecimal degrees;
  
  static {
    CIVIL = new Zenith(96.0D);
    OFFICIAL = new Zenith(90.8333D);
  }
  
  public Zenith(double paramDouble) {
    this.degrees = BigDecimal.valueOf(paramDouble);
  }
  
  public BigDecimal degrees() {
    return this.degrees;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\luckycatlabs\sunrisesunset\Zenith.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */