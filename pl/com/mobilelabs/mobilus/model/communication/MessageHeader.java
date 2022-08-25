package pl.com.mobilelabs.mobilus.model.communication;

import java.util.Arrays;
import pl.com.mobilelabs.mobilus.utils.Conversions;

public class MessageHeader {
  private static final int CLIENT_ID_LENGTH = 6;
  
  private byte[] clientId;
  
  private int length;
  
  private MessageStatus messageStatus;
  
  private MessageType messageType;
  
  private Platform platform;
  
  private long timestamp;
  
  public MessageHeader(int paramInt, MessageType paramMessageType, long paramLong, byte[] paramArrayOfbyte, Platform paramPlatform, MessageStatus paramMessageStatus) {
    this.length = paramInt;
    this.messageType = paramMessageType;
    this.platform = paramPlatform;
    this.timestamp = paramLong;
    this.clientId = paramArrayOfbyte;
    this.messageStatus = paramMessageStatus;
  }
  
  public static MessageHeader createFromBytes(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte != null && paramArrayOfbyte.length >= getBytesLength()) {
      int i = (int)Conversions.getLongFromArray(paramArrayOfbyte, 0, 4);
      if (i < getBytesLength() - 4)
        return null; 
      MessageType messageType = MessageType.fromValue(paramArrayOfbyte[4]);
      long l = Conversions.getLongFromArray(paramArrayOfbyte, 5, 4);
      return (l < 0L) ? null : new MessageHeader(i, messageType, l, Arrays.copyOfRange(paramArrayOfbyte, 9, getBytesLength()), Platform.fromValue(paramArrayOfbyte[15]), MessageStatus.fromValue(paramArrayOfbyte[16]));
    } 
    return null;
  }
  
  public static int getBytesLength() {
    return 17;
  }
  
  public byte[] getBytes() {
    byte[] arrayOfByte1 = Conversions.getBytesForValue((this.length + 1 + 4 + this.clientId.length + 1 + 1), 4);
    byte[] arrayOfByte2 = Conversions.getBytesForValue(this.timestamp, 4);
    byte[] arrayOfByte3 = new byte[getBytesLength()];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte3, 0, arrayOfByte1.length);
    arrayOfByte3[arrayOfByte1.length] = this.messageType.getValue();
    System.arraycopy(arrayOfByte2, 0, arrayOfByte3, arrayOfByte1.length + 1, arrayOfByte2.length);
    System.arraycopy(this.clientId, 0, arrayOfByte3, arrayOfByte1.length + 1 + arrayOfByte2.length, this.clientId.length);
    arrayOfByte3[arrayOfByte1.length + 1 + arrayOfByte2.length + this.clientId.length] = this.platform.getValue();
    arrayOfByte3[arrayOfByte1.length + 1 + arrayOfByte2.length + this.clientId.length + 1] = this.messageStatus.getValue();
    return arrayOfByte3;
  }
  
  public byte[] getClientId() {
    return this.clientId;
  }
  
  public int getMessageLength() {
    return this.length;
  }
  
  public MessageStatus getMessageStatus() {
    return this.messageStatus;
  }
  
  public MessageType getMessageType() {
    return this.messageType;
  }
  
  public Platform getPlatform() {
    return this.platform;
  }
  
  public long getTimestamp() {
    return this.timestamp;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\communication\MessageHeader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */