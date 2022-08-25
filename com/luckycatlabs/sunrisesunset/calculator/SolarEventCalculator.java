package com.luckycatlabs.sunrisesunset.calculator;

import com.luckycatlabs.sunrisesunset.Zenith;
import com.luckycatlabs.sunrisesunset.dto.Location;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.TimeZone;

public class SolarEventCalculator {
  private final Location location;
  
  private final TimeZone timeZone;
  
  public SolarEventCalculator(Location paramLocation, String paramString) {
    this.location = paramLocation;
    this.timeZone = TimeZone.getTimeZone(paramString);
  }
  
  public SolarEventCalculator(Location paramLocation, TimeZone paramTimeZone) {
    this.location = paramLocation;
    this.timeZone = paramTimeZone;
  }
  
  private BigDecimal adjustForDST(BigDecimal paramBigDecimal, Calendar paramCalendar) {
    BigDecimal bigDecimal = paramBigDecimal;
    if (this.timeZone.inDaylightTime(paramCalendar.getTime()))
      bigDecimal = paramBigDecimal.add(BigDecimal.ONE); 
    paramBigDecimal = bigDecimal;
    if (bigDecimal.doubleValue() > 24.0D)
      paramBigDecimal = bigDecimal.subtract(BigDecimal.valueOf(24L)); 
    return paramBigDecimal;
  }
  
  private BigDecimal computeSolarEventTime(Zenith paramZenith, Calendar paramCalendar, boolean paramBoolean) {
    paramCalendar.setTimeZone(this.timeZone);
    BigDecimal bigDecimal2 = getLongitudeHour(paramCalendar, Boolean.valueOf(paramBoolean));
    BigDecimal bigDecimal3 = getSunTrueLongitude(getMeanAnomaly(bigDecimal2));
    BigDecimal bigDecimal1 = getCosineSunLocalHour(bigDecimal3, paramZenith);
    return (bigDecimal1.doubleValue() < -1.0D || bigDecimal1.doubleValue() > 1.0D) ? null : getLocalTime(getLocalMeanTime(bigDecimal3, bigDecimal2, getSunLocalHour(bigDecimal1, Boolean.valueOf(paramBoolean))), paramCalendar);
  }
  
  private BigDecimal convertDegreesToRadians(BigDecimal paramBigDecimal) {
    return multiplyBy(paramBigDecimal, BigDecimal.valueOf(0.017453292519943295D));
  }
  
  private BigDecimal convertRadiansToDegrees(BigDecimal paramBigDecimal) {
    return multiplyBy(paramBigDecimal, new BigDecimal(57.29577951308232D));
  }
  
  private BigDecimal divideBy(BigDecimal paramBigDecimal1, BigDecimal paramBigDecimal2) {
    return paramBigDecimal1.divide(paramBigDecimal2, 4, RoundingMode.HALF_EVEN);
  }
  
  private BigDecimal getArcCosineFor(BigDecimal paramBigDecimal) {
    return setScale(BigDecimal.valueOf(Math.acos(paramBigDecimal.doubleValue())));
  }
  
  private BigDecimal getBaseLongitudeHour() {
    return divideBy(this.location.getLongitude(), BigDecimal.valueOf(15L));
  }
  
  private BigDecimal getCosineOfSunDeclination(BigDecimal paramBigDecimal) {
    return setScale(BigDecimal.valueOf(Math.cos(BigDecimal.valueOf(Math.asin(paramBigDecimal.doubleValue())).doubleValue())));
  }
  
  private BigDecimal getCosineSunLocalHour(BigDecimal paramBigDecimal, Zenith paramZenith) {
    BigDecimal bigDecimal2 = getSinOfSunDeclination(paramBigDecimal);
    paramBigDecimal = getCosineOfSunDeclination(bigDecimal2);
    BigDecimal bigDecimal3 = BigDecimal.valueOf(Math.cos(convertDegreesToRadians(paramZenith.degrees()).doubleValue()));
    BigDecimal bigDecimal4 = BigDecimal.valueOf(Math.sin(convertDegreesToRadians(this.location.getLatitude()).doubleValue()));
    BigDecimal bigDecimal1 = BigDecimal.valueOf(Math.cos(convertDegreesToRadians(this.location.getLatitude()).doubleValue()));
    return setScale(divideBy(bigDecimal3.subtract(bigDecimal2.multiply(bigDecimal4)), paramBigDecimal.multiply(bigDecimal1)));
  }
  
  private BigDecimal getDayOfYear(Calendar paramCalendar) {
    return new BigDecimal(paramCalendar.get(6));
  }
  
  private BigDecimal getLocalMeanTime(BigDecimal paramBigDecimal1, BigDecimal paramBigDecimal2, BigDecimal paramBigDecimal3) {
    paramBigDecimal1 = getRightAscension(paramBigDecimal1);
    paramBigDecimal2 = paramBigDecimal2.multiply(new BigDecimal("0.06571"));
    paramBigDecimal2 = paramBigDecimal3.add(paramBigDecimal1).subtract(paramBigDecimal2).subtract(new BigDecimal("6.622"));
    if (paramBigDecimal2.doubleValue() < 0.0D) {
      paramBigDecimal1 = paramBigDecimal2.add(BigDecimal.valueOf(24L));
    } else {
      paramBigDecimal1 = paramBigDecimal2;
      if (paramBigDecimal2.doubleValue() > 24.0D)
        paramBigDecimal1 = paramBigDecimal2.subtract(BigDecimal.valueOf(24L)); 
    } 
    return setScale(paramBigDecimal1);
  }
  
  private BigDecimal getLocalTime(BigDecimal paramBigDecimal, Calendar paramCalendar) {
    return adjustForDST(paramBigDecimal.subtract(getBaseLongitudeHour()).add(getUTCOffSet(paramCalendar)), paramCalendar);
  }
  
  private String getLocalTimeAsString(BigDecimal paramBigDecimal) {
    String str1;
    String str2;
    if (paramBigDecimal == null)
      return "99:99"; 
    BigDecimal bigDecimal3 = paramBigDecimal;
    if (paramBigDecimal.compareTo(BigDecimal.ZERO) == -1)
      bigDecimal3 = paramBigDecimal.add(BigDecimal.valueOf(24.0D)); 
    String[] arrayOfString = bigDecimal3.toPlainString().split("\\.");
    boolean bool = false;
    int i = Integer.parseInt(arrayOfString[0]);
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("0.");
    stringBuilder1.append(arrayOfString[1]);
    BigDecimal bigDecimal2 = (new BigDecimal(stringBuilder1.toString())).multiply(BigDecimal.valueOf(60L)).setScale(0, RoundingMode.HALF_EVEN);
    int j = i;
    BigDecimal bigDecimal1 = bigDecimal2;
    if (bigDecimal2.intValue() == 60) {
      bigDecimal1 = BigDecimal.ZERO;
      j = i + 1;
    } 
    if (j == 24)
      j = bool; 
    if (bigDecimal1.intValue() < 10) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0");
      stringBuilder.append(bigDecimal1.toPlainString());
      str1 = stringBuilder.toString();
    } else {
      str1 = str1.toPlainString();
    } 
    if (j < 10) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0");
      stringBuilder.append(String.valueOf(j));
      str2 = stringBuilder.toString();
    } else {
      str2 = String.valueOf(j);
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(str2);
    stringBuilder2.append(":");
    stringBuilder2.append(str1);
    return stringBuilder2.toString();
  }
  
  private BigDecimal getLongitudeHour(Calendar paramCalendar, Boolean paramBoolean) {
    byte b;
    if (paramBoolean.booleanValue()) {
      b = 6;
    } else {
      b = 18;
    } 
    BigDecimal bigDecimal = divideBy(BigDecimal.valueOf(b).subtract(getBaseLongitudeHour()), BigDecimal.valueOf(24L));
    return setScale(getDayOfYear(paramCalendar).add(bigDecimal));
  }
  
  private BigDecimal getMeanAnomaly(BigDecimal paramBigDecimal) {
    return setScale(multiplyBy(new BigDecimal("0.9856"), paramBigDecimal).subtract(new BigDecimal("3.289")));
  }
  
  private BigDecimal getRightAscension(BigDecimal paramBigDecimal) {
    BigDecimal bigDecimal2;
    BigDecimal bigDecimal1 = setScale(convertRadiansToDegrees(new BigDecimal(Math.atan(convertDegreesToRadians(multiplyBy(convertRadiansToDegrees(new BigDecimal(Math.tan(convertDegreesToRadians(paramBigDecimal).doubleValue()))), new BigDecimal("0.91764"))).doubleValue()))));
    if (bigDecimal1.doubleValue() < 0.0D) {
      bigDecimal2 = bigDecimal1.add(BigDecimal.valueOf(360L));
    } else {
      bigDecimal2 = bigDecimal1;
      if (bigDecimal1.doubleValue() > 360.0D)
        bigDecimal2 = bigDecimal1.subtract(BigDecimal.valueOf(360L)); 
    } 
    bigDecimal1 = BigDecimal.valueOf(90L);
    return divideBy(bigDecimal2.add(paramBigDecimal.divide(bigDecimal1, 0, RoundingMode.FLOOR).multiply(bigDecimal1).subtract(bigDecimal2.divide(bigDecimal1, 0, RoundingMode.FLOOR).multiply(bigDecimal1))), BigDecimal.valueOf(15L));
  }
  
  private BigDecimal getSinOfSunDeclination(BigDecimal paramBigDecimal) {
    return setScale(BigDecimal.valueOf(Math.sin(convertDegreesToRadians(paramBigDecimal).doubleValue())).multiply(new BigDecimal("0.39782")));
  }
  
  private BigDecimal getSunLocalHour(BigDecimal paramBigDecimal, Boolean paramBoolean) {
    BigDecimal bigDecimal = convertRadiansToDegrees(getArcCosineFor(paramBigDecimal));
    paramBigDecimal = bigDecimal;
    if (paramBoolean.booleanValue())
      paramBigDecimal = BigDecimal.valueOf(360L).subtract(bigDecimal); 
    return divideBy(paramBigDecimal, BigDecimal.valueOf(15L));
  }
  
  private BigDecimal getSunTrueLongitude(BigDecimal paramBigDecimal) {
    BigDecimal bigDecimal1 = new BigDecimal(Math.sin(convertDegreesToRadians(paramBigDecimal).doubleValue()));
    BigDecimal bigDecimal2 = new BigDecimal(Math.sin(multiplyBy(convertDegreesToRadians(paramBigDecimal), BigDecimal.valueOf(2L)).doubleValue()));
    bigDecimal1 = paramBigDecimal.add(multiplyBy(bigDecimal1, new BigDecimal("1.916"))).add(multiplyBy(bigDecimal2, new BigDecimal("0.020")).add(new BigDecimal("282.634")));
    paramBigDecimal = bigDecimal1;
    if (bigDecimal1.doubleValue() > 360.0D)
      paramBigDecimal = bigDecimal1.subtract(BigDecimal.valueOf(360L)); 
    return setScale(paramBigDecimal);
  }
  
  private BigDecimal getUTCOffSet(Calendar paramCalendar) {
    return (new BigDecimal(paramCalendar.get(15))).divide(new BigDecimal(3600000), new MathContext(2));
  }
  
  private BigDecimal multiplyBy(BigDecimal paramBigDecimal1, BigDecimal paramBigDecimal2) {
    return setScale(paramBigDecimal1.multiply(paramBigDecimal2));
  }
  
  private BigDecimal setScale(BigDecimal paramBigDecimal) {
    return paramBigDecimal.setScale(4, RoundingMode.HALF_EVEN);
  }
  
  public Calendar computeSunriseCalendar(Zenith paramZenith, Calendar paramCalendar) {
    return getLocalTimeAsCalendar(computeSolarEventTime(paramZenith, paramCalendar, true), paramCalendar);
  }
  
  public String computeSunriseTime(Zenith paramZenith, Calendar paramCalendar) {
    return getLocalTimeAsString(computeSolarEventTime(paramZenith, paramCalendar, true));
  }
  
  public Calendar computeSunsetCalendar(Zenith paramZenith, Calendar paramCalendar) {
    return getLocalTimeAsCalendar(computeSolarEventTime(paramZenith, paramCalendar, false), paramCalendar);
  }
  
  public String computeSunsetTime(Zenith paramZenith, Calendar paramCalendar) {
    return getLocalTimeAsString(computeSolarEventTime(paramZenith, paramCalendar, false));
  }
  
  protected Calendar getLocalTimeAsCalendar(BigDecimal paramBigDecimal, Calendar paramCalendar) {
    if (paramBigDecimal == null)
      return null; 
    Calendar calendar = (Calendar)paramCalendar.clone();
    BigDecimal bigDecimal3 = paramBigDecimal;
    if (paramBigDecimal.compareTo(BigDecimal.ZERO) == -1) {
      bigDecimal3 = paramBigDecimal.add(BigDecimal.valueOf(24.0D));
      calendar.add(11, -24);
    } 
    String[] arrayOfString = bigDecimal3.toPlainString().split("\\.");
    int i = Integer.parseInt(arrayOfString[0]);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0.");
    stringBuilder.append(arrayOfString[1]);
    BigDecimal bigDecimal2 = (new BigDecimal(stringBuilder.toString())).multiply(BigDecimal.valueOf(60L)).setScale(0, RoundingMode.HALF_EVEN);
    int j = i;
    BigDecimal bigDecimal1 = bigDecimal2;
    if (bigDecimal2.intValue() == 60) {
      bigDecimal1 = BigDecimal.ZERO;
      j = i + 1;
    } 
    i = j;
    if (j == 24)
      i = 0; 
    calendar.set(11, i);
    calendar.set(12, bigDecimal1.intValue());
    calendar.set(13, 0);
    calendar.set(14, 0);
    calendar.setTimeZone(paramCalendar.getTimeZone());
    return calendar;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\com\luckycatlabs\sunrisesunset\calculator\SolarEventCalculator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */