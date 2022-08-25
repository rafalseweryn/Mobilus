package pl.com.mobilelabs.mobilus.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.Nullable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;

public class NetworkUtils {
  @Nullable
  public static String getCurrentWifiNetworkName(Context paramContext) {
    WifiManager wifiManager = (WifiManager)paramContext.getSystemService("wifi");
    if (wifiManager == null)
      return null; 
    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
    if (wifiInfo == null)
      return null; 
    String str = wifiInfo.getSSID();
    return (str != null) ? str : "";
  }
  
  @SuppressLint({"DefaultLocale"})
  @Nullable
  public static String getDeviceIpAddress() {
    try {
      ArrayList<NetworkInterface> arrayList = Collections.list(NetworkInterface.getNetworkInterfaces());
      for (NetworkInterface networkInterface : arrayList) {
        if (networkInterface.getName().contains("wlan"))
          for (InetAddress inetAddress : Collections.<InetAddress>list(networkInterface.getInetAddresses())) {
            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof java.net.Inet4Address)
              return inetAddress.getHostAddress().toUpperCase(); 
          }  
      } 
      return null;
    } catch (SocketException socketException) {
      return null;
    } 
  }
  
  @Nullable
  public static String getWifiMacAddress(Context paramContext) {
    WifiManager wifiManager = (WifiManager)paramContext.getSystemService("wifi");
    if (wifiManager == null)
      return null; 
    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
    return (wifiInfo == null) ? null : wifiInfo.getMacAddress();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilu\\utils\NetworkUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */