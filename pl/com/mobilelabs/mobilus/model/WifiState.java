package pl.com.mobilelabs.mobilus.model;

public enum WifiState {
  CLIENT,
  ROUTER,
  TURNED_OFF(0);
  
  private int value;
  
  static {
    ROUTER = new WifiState("ROUTER", 1, 1);
    CLIENT = new WifiState("CLIENT", 2, 2);
    $VALUES = new WifiState[] { TURNED_OFF, ROUTER, CLIENT };
  }
  
  WifiState(int paramInt1) {
    this.value = paramInt1;
  }
  
  public static WifiState fromValue(int paramInt) {
    for (WifiState wifiState : values()) {
      if (wifiState.value == paramInt)
        return wifiState; 
    } 
    return null;
  }
  
  public int getValue() {
    return this.value;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\WifiState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */