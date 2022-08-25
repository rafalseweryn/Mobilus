package pl.com.mobilelabs.mobilus.services.communication.cloudservice;

enum ConnectionState {
  CONNECTED, DISCONNECTED, ERROR_CANNOT_CONNECT, ERROR_CANNOT_SUBSCRIBE, ERROR_SSL;
  
  static {
    CONNECTED = new ConnectionState("CONNECTED", 1);
    ERROR_CANNOT_CONNECT = new ConnectionState("ERROR_CANNOT_CONNECT", 2);
    ERROR_CANNOT_SUBSCRIBE = new ConnectionState("ERROR_CANNOT_SUBSCRIBE", 3);
    ERROR_SSL = new ConnectionState("ERROR_SSL", 4);
    $VALUES = new ConnectionState[] { DISCONNECTED, CONNECTED, ERROR_CANNOT_CONNECT, ERROR_CANNOT_SUBSCRIBE, ERROR_SSL };
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\services\communication\cloudservice\ConnectionState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */