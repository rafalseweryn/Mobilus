package pl.com.mobilelabs.mobilus.model.communication;

public enum EventType {
  ACTION_ON_SESSION,
  CONTROL_DEVICE,
  DEVICE,
  DEVICE_ACTION_STARTED,
  DEVICE_ACTION_STOPPED_ERROR,
  DEVICE_ACTION_STOPPED_OK,
  FIRMWARE_UPDATE_PROCESS_STARTED_ENDED,
  GROUP,
  PLACE,
  REMOTE_CONNECTION_STATE_CHANGED,
  SCENE,
  UNKNOWN(0),
  USER(1);
  
  private int value;
  
  static {
    DEVICE = new EventType("DEVICE", 2, 2);
    GROUP = new EventType("GROUP", 3, 3);
    PLACE = new EventType("PLACE", 4, 4);
    SCENE = new EventType("SCENE", 5, 5);
    CONTROL_DEVICE = new EventType("CONTROL_DEVICE", 6, 6);
    DEVICE_ACTION_STARTED = new EventType("DEVICE_ACTION_STARTED", 7, 7);
    DEVICE_ACTION_STOPPED_OK = new EventType("DEVICE_ACTION_STOPPED_OK", 8, 8);
    DEVICE_ACTION_STOPPED_ERROR = new EventType("DEVICE_ACTION_STOPPED_ERROR", 9, 9);
    ACTION_ON_SESSION = new EventType("ACTION_ON_SESSION", 10, 10);
    REMOTE_CONNECTION_STATE_CHANGED = new EventType("REMOTE_CONNECTION_STATE_CHANGED", 11, 11);
    FIRMWARE_UPDATE_PROCESS_STARTED_ENDED = new EventType("FIRMWARE_UPDATE_PROCESS_STARTED_ENDED", 12, 12);
    $VALUES = new EventType[] { 
        UNKNOWN, USER, DEVICE, GROUP, PLACE, SCENE, CONTROL_DEVICE, DEVICE_ACTION_STARTED, DEVICE_ACTION_STOPPED_OK, DEVICE_ACTION_STOPPED_ERROR, 
        ACTION_ON_SESSION, REMOTE_CONNECTION_STATE_CHANGED, FIRMWARE_UPDATE_PROCESS_STARTED_ENDED };
  }
  
  EventType(int paramInt1) {
    this.value = paramInt1;
  }
  
  public static EventType fromValue(int paramInt) {
    switch (paramInt) {
      default:
        return UNKNOWN;
      case 12:
        return FIRMWARE_UPDATE_PROCESS_STARTED_ENDED;
      case 11:
        return REMOTE_CONNECTION_STATE_CHANGED;
      case 10:
        return ACTION_ON_SESSION;
      case 9:
        return DEVICE_ACTION_STOPPED_ERROR;
      case 8:
        return DEVICE_ACTION_STOPPED_OK;
      case 7:
        return DEVICE_ACTION_STARTED;
      case 6:
        return CONTROL_DEVICE;
      case 5:
        return SCENE;
      case 4:
        return PLACE;
      case 3:
        return GROUP;
      case 2:
        return DEVICE;
      case 1:
        break;
    } 
    return USER;
  }
  
  public int getValue() {
    return this.value;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\communication\EventType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */