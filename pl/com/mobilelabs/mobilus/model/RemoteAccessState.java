package pl.com.mobilelabs.mobilus.model;

public enum RemoteAccessState {
  CONNECTED,
  DISCONNECTED,
  TURNED_OFF("OFF");
  
  private String value;
  
  static {
    CONNECTED = new RemoteAccessState("CONNECTED", 1, "ON_CONNECTED");
    DISCONNECTED = new RemoteAccessState("DISCONNECTED", 2, "ON_DISCONNECTED");
    $VALUES = new RemoteAccessState[] { TURNED_OFF, CONNECTED, DISCONNECTED };
  }
  
  RemoteAccessState(String paramString1) {
    this.value = paramString1;
  }
  
  public static RemoteAccessState fromString(String paramString) {
    for (RemoteAccessState remoteAccessState : values()) {
      if (remoteAccessState.value.equalsIgnoreCase(paramString))
        return remoteAccessState; 
    } 
    return null;
  }
  
  public String getValue() {
    return this.value;
  }
  
  public String toString() {
    return this.value;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\RemoteAccessState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */