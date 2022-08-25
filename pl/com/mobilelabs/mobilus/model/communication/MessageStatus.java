package pl.com.mobilelabs.mobilus.model.communication;

public enum MessageStatus {
  ERROR,
  OK(0),
  SESSION_NOT_VALID(1),
  TIMEOUT(1),
  WRONG_DATA(1);
  
  private int value;
  
  static {
    ERROR = new MessageStatus("ERROR", 2, 2);
    TIMEOUT = new MessageStatus("TIMEOUT", 3, -1);
    WRONG_DATA = new MessageStatus("WRONG_DATA", 4, 3);
    $VALUES = new MessageStatus[] { OK, SESSION_NOT_VALID, ERROR, TIMEOUT, WRONG_DATA };
  }
  
  MessageStatus(int paramInt1) {
    this.value = paramInt1;
  }
  
  public static MessageStatus fromValue(byte paramByte) {
    return (paramByte == 0) ? OK : ((paramByte == -1) ? TIMEOUT : ((paramByte == 1) ? SESSION_NOT_VALID : ((paramByte == 3) ? WRONG_DATA : ERROR)));
  }
  
  public byte getValue() {
    return (byte)this.value;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\communication\MessageStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */