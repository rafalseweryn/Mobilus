package pl.com.mobilelabs.mobilus.services.communication;

import pl.com.mobilelabs.mobilus.model.ConnectionStatus;
import pl.com.mobilelabs.mobilus.model.communication.ReceivedData;

public interface ICommunicationListener {
  void connectionStateChanged(ConnectionStatus paramConnectionStatus);
  
  void newMessageReceived(ReceivedData paramReceivedData);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\services\communication\ICommunicationListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */