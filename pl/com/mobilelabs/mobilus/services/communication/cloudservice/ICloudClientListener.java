package pl.com.mobilelabs.mobilus.services.communication.cloudservice;

interface ICloudClientListener {
  void connectionStateChanged(boolean paramBoolean);
  
  void newBytesReceived(byte[] paramArrayOfbyte);
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\services\communication\cloudservice\ICloudClientListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */