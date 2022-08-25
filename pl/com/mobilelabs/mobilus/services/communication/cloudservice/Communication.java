package pl.com.mobilelabs.mobilus.services.communication.cloudservice;

import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.Arrays;
import java.util.Date;
import pl.com.mobilelabs.mobilus.model.communication.MessageHeader;
import pl.com.mobilelabs.mobilus.model.communication.MessageStatus;
import pl.com.mobilelabs.mobilus.model.communication.MessageType;
import pl.com.mobilelabs.mobilus.model.communication.MobilusProtocol;
import pl.com.mobilelabs.mobilus.model.communication.Platform;
import pl.com.mobilelabs.mobilus.model.communication.ReceivedData;
import pl.com.mobilelabs.mobilus.utils.Conversions;
import pl.com.mobilelabs.mobilus.utils.Encryption;

class Communication {
  public static ReceivedData decodeMessage(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3) {
    byte[] arrayOfByte1;
    int i = paramArrayOfbyte1.length;
    int j = MessageHeader.getBytesLength();
    byte[] arrayOfByte2 = null;
    if (i < j || (paramArrayOfbyte2 != null && paramArrayOfbyte2.length != 32) || (paramArrayOfbyte3 != null && paramArrayOfbyte3.length != 32))
      return new ReceivedData(MessageType.UNKNOWN, null, MessageStatus.WRONG_DATA, null); 
    MessageHeader messageHeader = MessageHeader.createFromBytes(paramArrayOfbyte1);
    if (messageHeader == null)
      return new ReceivedData(MessageType.UNKNOWN, null, MessageStatus.WRONG_DATA, null); 
    if (messageHeader.getMessageStatus() != MessageStatus.OK)
      return new ReceivedData(MessageType.UNKNOWN, messageHeader.getClientId(), messageHeader.getMessageStatus(), null); 
    byte[] arrayOfByte3 = Arrays.copyOfRange(paramArrayOfbyte1, MessageHeader.getBytesLength(), paramArrayOfbyte1.length);
    if (arrayOfByte3 == null || arrayOfByte3.length <= 0)
      return new ReceivedData(messageHeader.getMessageType(), messageHeader.getClientId(), messageHeader.getMessageStatus(), null); 
    if (messageHeader.getMessageType() == MessageType.CALL_EVENTS)
      paramArrayOfbyte2 = paramArrayOfbyte3; 
    paramArrayOfbyte1 = arrayOfByte3;
    if (paramArrayOfbyte2 != null)
      paramArrayOfbyte1 = Encryption.decodeUsingAes(arrayOfByte3, paramArrayOfbyte2, Conversions.getBytesForValue(messageHeader.getTimestamp(), 16)); 
    j = 0;
    if (paramArrayOfbyte1 == null)
      return new ReceivedData(MessageType.UNKNOWN, messageHeader.getClientId(), messageHeader.getMessageStatus(), null); 
    try {
      MobilusProtocol.ProgramNewDeviceResponse programNewDeviceResponse;
      MobilusProtocol.FirmwareUpdateResponse firmwareUpdateResponse;
      MobilusProtocol.UserListResponse userListResponse;
      MobilusProtocol.WifiNetworkListResponse wifiNetworkListResponse;
      MobilusProtocol.NetworkSettingsResponse networkSettingsResponse;
      MobilusProtocol.DeviceSettingsResponse deviceSettingsResponse;
      MobilusProtocol.CurrentStateResponse currentStateResponse;
      MobilusProtocol.UpdateDeviceResponse updateDeviceResponse;
      MobilusProtocol.UpdateDeviceGroupResponse updateDeviceGroupResponse;
      MobilusProtocol.UpdateDevicePlaceResponse updateDevicePlaceResponse;
      MobilusProtocol.UpdateSceneResponse updateSceneResponse;
      MobilusProtocol.UpdateUserResponse updateUserResponse;
      MobilusProtocol.CallEvents callEvents;
      MobilusProtocol.HistoricalEventsResponse historicalEventsResponse;
      MobilusProtocol.ScenesResponse scenesResponse;
      MobilusProtocol.DeviceGroupsResponse deviceGroupsResponse;
      MobilusProtocol.DevicePlacesResponse devicePlacesResponse;
      MobilusProtocol.DevicesListResponse devicesListResponse;
      MobilusProtocol.LoginResponse loginResponse;
      switch (messageHeader.getMessageType()) {
        default:
          paramArrayOfbyte1 = arrayOfByte2;
          break;
        case PROGRAM_NEW_DEVICE_RESPONSE:
          programNewDeviceResponse = MobilusProtocol.ProgramNewDeviceResponse.parseFrom(paramArrayOfbyte1);
          break;
        case FIRMWARE_UPDATE_RESPONSE:
          firmwareUpdateResponse = MobilusProtocol.FirmwareUpdateResponse.parseFrom((byte[])programNewDeviceResponse);
          break;
        case USER_LIST_RESPONSE:
          userListResponse = MobilusProtocol.UserListResponse.parseFrom((byte[])firmwareUpdateResponse);
          break;
        case WIFI_NETWORK_LIST_RESPONSE:
          wifiNetworkListResponse = MobilusProtocol.WifiNetworkListResponse.parseFrom((byte[])userListResponse);
          break;
        case NETWORK_SETTINGS_RESPONSE:
          networkSettingsResponse = MobilusProtocol.NetworkSettingsResponse.parseFrom((byte[])wifiNetworkListResponse);
          break;
        case DEVICE_SETTINGS_RESPONSE:
          deviceSettingsResponse = MobilusProtocol.DeviceSettingsResponse.parseFrom((byte[])networkSettingsResponse);
          break;
        case CURRENT_STATE_RESPONSE:
          currentStateResponse = MobilusProtocol.CurrentStateResponse.parseFrom((byte[])deviceSettingsResponse);
          break;
        case UPDATE_DEVICE_RESPONSE:
          updateDeviceResponse = MobilusProtocol.UpdateDeviceResponse.parseFrom((byte[])currentStateResponse);
          break;
        case UPDATE_DEVICE_GROUP_RESPONSE:
          updateDeviceGroupResponse = MobilusProtocol.UpdateDeviceGroupResponse.parseFrom((byte[])updateDeviceResponse);
          break;
        case UPDATE_DEVICE_PLACE_RESPONSE:
          updateDevicePlaceResponse = MobilusProtocol.UpdateDevicePlaceResponse.parseFrom((byte[])updateDeviceGroupResponse);
          break;
        case UPDATE_SCENE_RESPONSE:
          updateSceneResponse = MobilusProtocol.UpdateSceneResponse.parseFrom((byte[])updateDevicePlaceResponse);
          break;
        case UPDATE_USER_RESPONSE:
          updateUserResponse = MobilusProtocol.UpdateUserResponse.parseFrom((byte[])updateSceneResponse);
          break;
        case CALL_EVENTS:
          callEvents = MobilusProtocol.CallEvents.parseFrom((byte[])updateUserResponse);
          break;
        case HISTORICAL_EVENTS_RESPONSE:
          historicalEventsResponse = MobilusProtocol.HistoricalEventsResponse.parseFrom((byte[])callEvents);
          break;
        case SCENES_RESPONSE:
          scenesResponse = MobilusProtocol.ScenesResponse.parseFrom((byte[])historicalEventsResponse);
          break;
        case DEVICE_GROUPS_RESPONSE:
          deviceGroupsResponse = MobilusProtocol.DeviceGroupsResponse.parseFrom((byte[])scenesResponse);
          break;
        case DEVICE_PLACES_RESPONSE:
          devicePlacesResponse = MobilusProtocol.DevicePlacesResponse.parseFrom((byte[])deviceGroupsResponse);
          break;
        case DEVICES_LIST_RESPONSE:
          devicesListResponse = MobilusProtocol.DevicesListResponse.parseFrom((byte[])devicePlacesResponse);
          break;
        case LOGIN_RESPONSE:
          loginResponse = MobilusProtocol.LoginResponse.parseFrom((byte[])devicesListResponse);
          break;
      } 
    } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
      j = 1;
      arrayOfByte1 = arrayOfByte2;
    } 
    return (j != 0) ? new ReceivedData(messageHeader.getMessageType(), messageHeader.getClientId(), MessageStatus.ERROR, (GeneratedMessageV3)arrayOfByte1) : new ReceivedData(messageHeader.getMessageType(), messageHeader.getClientId(), messageHeader.getMessageStatus(), (GeneratedMessageV3)arrayOfByte1);
  }
  
  public static byte[] encodeAndAddHeader(MessageType paramMessageType, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3) {
    if (paramMessageType == MessageType.UNKNOWN || (paramArrayOfbyte2 != null && paramArrayOfbyte2.length != 32))
      return null; 
    long l = (new Date()).getTime() / 1000L;
    if (paramArrayOfbyte2 != null)
      paramArrayOfbyte1 = Encryption.encodeUsingAes(paramArrayOfbyte1, paramArrayOfbyte2, Conversions.getBytesForValue(l, 16)); 
    if (paramArrayOfbyte1 == null)
      return null; 
    paramArrayOfbyte2 = (new MessageHeader(paramArrayOfbyte1.length, paramMessageType, l, paramArrayOfbyte3, Platform.ANDROID, MessageStatus.OK)).getBytes();
    byte[] arrayOfByte = new byte[paramArrayOfbyte2.length + paramArrayOfbyte1.length];
    System.arraycopy(paramArrayOfbyte2, 0, arrayOfByte, 0, paramArrayOfbyte2.length);
    System.arraycopy(paramArrayOfbyte1, 0, arrayOfByte, paramArrayOfbyte2.length, paramArrayOfbyte1.length);
    return arrayOfByte;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\services\communication\cloudservice\Communication.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */