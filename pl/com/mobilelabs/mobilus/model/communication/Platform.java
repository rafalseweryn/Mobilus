package pl.com.mobilelabs.mobilus.model.communication;

public enum Platform {
  ANDROID,
  CONTROLLER,
  IOS,
  UNKNOWN(0),
  WWW_PAGE(0);
  
  private int value;
  
  static {
    CONTROLLER = new Platform("CONTROLLER", 1, 1);
    ANDROID = new Platform("ANDROID", 2, 2);
    IOS = new Platform("IOS", 3, 3);
    WWW_PAGE = new Platform("WWW_PAGE", 4, 4);
    $VALUES = new Platform[] { UNKNOWN, CONTROLLER, ANDROID, IOS, WWW_PAGE };
  }
  
  Platform(int paramInt1) {
    this.value = paramInt1;
  }
  
  public static Platform fromValue(byte paramByte) {
    switch (paramByte) {
      default:
        return UNKNOWN;
      case 4:
        return WWW_PAGE;
      case 3:
        return IOS;
      case 2:
        return ANDROID;
      case 1:
        break;
    } 
    return CONTROLLER;
  }
  
  public byte getValue() {
    return (byte)this.value;
  }
  
  public String toString() {
    switch (this) {
      default:
        return "Nieznany";
      case WWW_PAGE:
        return "Panel WWW";
      case IOS:
        return "iOS";
      case ANDROID:
        return "Android";
      case CONTROLLER:
        break;
    } 
    return "Modul";
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\communication\Platform.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */