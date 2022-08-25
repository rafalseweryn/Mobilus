package pl.com.mobilelabs.mobilus.model.objects;

public enum DeviceType {
  CGR,
  CMR,
  COSMO,
  CZR,
  MZR,
  ON_OFF,
  ON_OFF_POTENTIAL_FREE,
  SENSO(1),
  SENSO_Z(1);
  
  private int value;
  
  static {
    COSMO = new DeviceType("COSMO", 1, 2);
    CMR = new DeviceType("CMR", 2, 3);
    CGR = new DeviceType("CGR", 3, 4);
    ON_OFF = new DeviceType("ON_OFF", 4, 5);
    ON_OFF_POTENTIAL_FREE = new DeviceType("ON_OFF_POTENTIAL_FREE", 5, 6);
    CZR = new DeviceType("CZR", 6, 7);
    MZR = new DeviceType("MZR", 7, 8);
    SENSO_Z = new DeviceType("SENSO_Z", 8, 9);
    $VALUES = new DeviceType[] { SENSO, COSMO, CMR, CGR, ON_OFF, ON_OFF_POTENTIAL_FREE, CZR, MZR, SENSO_Z };
  }
  
  DeviceType(int paramInt1) {
    this.value = paramInt1;
  }
  
  public static DeviceType fromValue(int paramInt) {
    for (DeviceType deviceType : values()) {
      if (deviceType.value == paramInt)
        return deviceType; 
    } 
    return null;
  }
  
  public int getValue() {
    return this.value;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\objects\DeviceType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */