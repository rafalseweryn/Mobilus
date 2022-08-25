package pl.com.mobilelabs.mobilus.model.communication;

import com.google.protobuf.GeneratedMessageV3;

public class ReceivedData {
  private byte[] clientId;
  
  private GeneratedMessageV3 message;
  
  private MessageStatus messageStatus;
  
  private MessageType messageType;
  
  public ReceivedData(MessageType paramMessageType, byte[] paramArrayOfbyte, MessageStatus paramMessageStatus, GeneratedMessageV3 paramGeneratedMessageV3) {
    this.messageType = paramMessageType;
    this.clientId = paramArrayOfbyte;
    this.messageStatus = paramMessageStatus;
    this.message = paramGeneratedMessageV3;
  }
  
  public byte[] getClientId() {
    return this.clientId;
  }
  
  public GeneratedMessageV3 getMessage() {
    return this.message;
  }
  
  public MessageStatus getMessageStatus() {
    return this.messageStatus;
  }
  
  public MessageType getMessageType() {
    return this.messageType;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\communication\ReceivedData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */