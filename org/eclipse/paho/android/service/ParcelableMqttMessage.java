package org.eclipse.paho.android.service;

import android.os.Parcel;
import android.os.Parcelable;
import org.eclipse.paho.client.mqttv3.MqttMessage;

class ParcelableMqttMessage extends MqttMessage implements Parcelable {
  public static final Parcelable.Creator<ParcelableMqttMessage> CREATOR = new Parcelable.Creator<ParcelableMqttMessage>() {
      public ParcelableMqttMessage createFromParcel(Parcel param1Parcel) {
        return new ParcelableMqttMessage(param1Parcel);
      }
      
      public ParcelableMqttMessage[] newArray(int param1Int) {
        return new ParcelableMqttMessage[param1Int];
      }
    };
  
  String messageId = null;
  
  ParcelableMqttMessage(Parcel paramParcel) {
    super(paramParcel.createByteArray());
    setQos(paramParcel.readInt());
    boolean[] arrayOfBoolean = paramParcel.createBooleanArray();
    setRetained(arrayOfBoolean[0]);
    setDuplicate(arrayOfBoolean[1]);
    this.messageId = paramParcel.readString();
  }
  
  ParcelableMqttMessage(MqttMessage paramMqttMessage) {
    super(paramMqttMessage.getPayload());
    setQos(paramMqttMessage.getQos());
    setRetained(paramMqttMessage.isRetained());
    setDuplicate(paramMqttMessage.isDuplicate());
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getMessageId() {
    return this.messageId;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeByteArray(getPayload());
    paramParcel.writeInt(getQos());
    paramParcel.writeBooleanArray(new boolean[] { isRetained(), isDuplicate() });
    paramParcel.writeString(this.messageId);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\android\service\ParcelableMqttMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */