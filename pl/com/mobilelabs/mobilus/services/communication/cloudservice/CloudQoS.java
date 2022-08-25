package pl.com.mobilelabs.mobilus.services.communication.cloudservice;

enum CloudQoS {
  AT_LEAST_ONCE,
  AT_MOST_ONCE(0),
  EXACTLY_ONCE(0);
  
  private int value;
  
  static {
    AT_LEAST_ONCE = new CloudQoS("AT_LEAST_ONCE", 1, 1);
    EXACTLY_ONCE = new CloudQoS("EXACTLY_ONCE", 2, 2);
    $VALUES = new CloudQoS[] { AT_MOST_ONCE, AT_LEAST_ONCE, EXACTLY_ONCE };
  }
  
  CloudQoS(int paramInt1) {
    this.value = paramInt1;
  }
  
  public int getValue() {
    return this.value;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\services\communication\cloudservice\CloudQoS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */