package pl.com.mobilelabs.mobilus.model;

public enum ErrorType {
  NO_CONNECTION,
  OBSTACLE,
  OVERLOAD,
  UNKNOWN("UNKNOWN");
  
  private String value;
  
  static {
    OVERLOAD = new ErrorType("OVERLOAD", 1, "OVERLOAD");
    OBSTACLE = new ErrorType("OBSTACLE", 2, "OBSTACLE");
    NO_CONNECTION = new ErrorType("NO_CONNECTION", 3, "NO_CONNECTION");
    $VALUES = new ErrorType[] { UNKNOWN, OVERLOAD, OBSTACLE, NO_CONNECTION };
  }
  
  ErrorType(String paramString1) {
    this.value = paramString1;
  }
  
  public static ErrorType fromString(String paramString) {
    return (paramString == null) ? UNKNOWN : (paramString.equalsIgnoreCase(OVERLOAD.value) ? OVERLOAD : (paramString.equalsIgnoreCase(OBSTACLE.value) ? OBSTACLE : (paramString.equalsIgnoreCase(NO_CONNECTION.value) ? NO_CONNECTION : UNKNOWN)));
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\ErrorType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */