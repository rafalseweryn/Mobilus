package pl.com.mobilelabs.mobilus.services.communication;

import android.os.Binder;
import com.google.protobuf.GeneratedMessageV3;
import pl.com.mobilelabs.mobilus.model.ConnectionStatus;
import pl.com.mobilelabs.mobilus.model.communication.MessageType;

public abstract class CommunicationBinder extends Binder {
  public abstract void configureService(ICommunicationListener paramICommunicationListener, String paramString);
  
  public abstract void configureService(ICommunicationListener paramICommunicationListener, String paramString1, String paramString2);
  
  public abstract boolean connect(boolean paramBoolean);
  
  public abstract void disconnect();
  
  public abstract ConnectionStatus isConnected();
  
  public abstract boolean isWorking();
  
  public abstract boolean resendLastMessage();
  
  public abstract boolean sendMessage(MessageType paramMessageType, GeneratedMessageV3 paramGeneratedMessageV3);
  
  public abstract void setEncryptionKeys(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\services\communication\CommunicationBinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */