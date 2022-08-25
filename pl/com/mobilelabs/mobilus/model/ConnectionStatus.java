package pl.com.mobilelabs.mobilus.model;

public enum ConnectionStatus {
  DEMO_MODE, LOCAL_CONNECTION, NO_CONNECTION, REMOTE_CONNECTION;
  
  static {
    LOCAL_CONNECTION = new ConnectionStatus("LOCAL_CONNECTION", 1);
    REMOTE_CONNECTION = new ConnectionStatus("REMOTE_CONNECTION", 2);
    DEMO_MODE = new ConnectionStatus("DEMO_MODE", 3);
    $VALUES = new ConnectionStatus[] { NO_CONNECTION, LOCAL_CONNECTION, REMOTE_CONNECTION, DEMO_MODE };
  }
  
  public boolean isConnected() {
    return !(this == NO_CONNECTION);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\ConnectionStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */