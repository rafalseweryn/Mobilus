package pl.com.mobilelabs.mobilus.model.communication;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class MobilusModel {
  private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[] { "\n\023mobilus_model.proto\"`\n\004User\022\n\n\002id\030\001 \001(\003\022\r\n\005login\030\002 \001(\t\022\020\n\bpassword\030\003 \001(\f\022\r\n\005admin\030\004 \001(\b\022\034\n\024assigned_devices_ids\030\005 \003(\003\"\001\n\006Device\022\n\n\002id\030\001 \001(\003\022\f\n\004name\030\002 \001(\t\022\f\n\004type\030\003 \001(\005\022\f\n\004icon\030\004 \001(\005\022\022\n\ninserttime\030\005 \001(\003\022\021\n\tfavourite\030\006 \001(\b\022\032\n\022assigned_place_ids\030\007 \003(\003\022\032\n\022assigned_group_ids\030\b \003(\003\"o\n\013DevicePlace\022\n\n\002id\030\001 \001(\003\022\f\n\004name\030\002 \001(\t\022\f\n\004icon\030\003 \001(\005\022\034\n\024assigned_devices_ids\030\004 \003(\003\022\032\n\022assigned_group_ids\030\005 \003(\003\"\001\n\013DeviceGroup\022\n\n\002id\030\001 \001(\003\022\f\n\004name\030\002 \001(\t\022\f\n\004icon\030\003 \001(\005\022\034\n\024assigned_devices_ids\030\004 \003(\003\022\032\n\022assigned_place_ids\030\005 \003(\003\022\021\n\tfavourite\030\006 \001(\b\"P\n\nSceneEvent\022\n\n\002id\030\001 \001(\003\022\021\n\tdevice_id\030\002 \001(\003\022\024\n\fevent_number\030\003 \001(\005\022\r\n\005value\030\004 \001(\t\"?\n\021SceneWeekSchedule\022\n\n\002id\030\001 \001(\003\022\020\n\bweek_day\030\002 \001(\005\022\f\n\004hour\030\003 \001(\005\"U\n\023SceneAstralSchedule\022\n\n\002id\030\001 \001(\003\022\020\n\bweek_day\030\002 \001(\005\022\020\n\binterval\030\003 \001(\005\022\016\n\006astral\030\004 \001(\005\"Ë\001\n\005Scene\022\n\n\002id\030\001 \001(\003\022\f\n\004name\030\002 \001(\t\022\f\n\004icon\030\003 \001(\005\022!\n\fscene_events\030\004 \003(\0132\013.SceneEvent\0220\n\024scene_week_schedules\030\005 \003(\0132\022.SceneWeekSchedule\0224\n\026scene_astral_schedules\030\006 \003(\0132\024.SceneAstralSchedule\022\017\n\007enabled\030\007 \001(\b\"\n\005Event\022\n\n\002id\030\001 \001(\003\022\021\n\tdevice_id\030\002 \001(\003\022\024\n\fevent_number\030\003 \001(\005\022\r\n\005value\030\004 \001(\t\022\020\n\bplatform\030\005 \001(\005\022\f\n\004user\030\006 \001(\003\022\022\n\ninserttime\030\007 \001(\003\".\n\013WifiNetwork\022\f\n\004ssid\030\001 \001(\t\022\021\n\tencrypted\030\002 \001(\b\"%\n\006IdPair\022\f\n\004left\030\001 \001(\003\022\r\n\005right\030\002 \001(\003B=\n-pl.com.mobilelabs.mobilus.model.communicationB\fMobilusModel" }, new Descriptors.FileDescriptor[0]);
  
  private static final Descriptors.Descriptor internal_static_DeviceGroup_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_DeviceGroup_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_DevicePlace_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_DevicePlace_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Device_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Device_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Event_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Event_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_IdPair_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_IdPair_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_SceneAstralSchedule_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_SceneAstralSchedule_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_SceneEvent_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_SceneEvent_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_SceneWeekSchedule_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_SceneWeekSchedule_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_Scene_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_Scene_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_User_descriptor = getDescriptor().getMessageTypes().get(0);
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_User_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_User_descriptor, new String[] { "Id", "Login", "Password", "Admin", "AssignedDevicesIds" });
  
  private static final Descriptors.Descriptor internal_static_WifiNetwork_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_WifiNetwork_fieldAccessorTable;
  
  static {
    internal_static_Device_descriptor = getDescriptor().getMessageTypes().get(1);
    internal_static_Device_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Device_descriptor, new String[] { "Id", "Name", "Type", "Icon", "Inserttime", "Favourite", "AssignedPlaceIds", "AssignedGroupIds" });
    internal_static_DevicePlace_descriptor = getDescriptor().getMessageTypes().get(2);
    internal_static_DevicePlace_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_DevicePlace_descriptor, new String[] { "Id", "Name", "Icon", "AssignedDevicesIds", "AssignedGroupIds" });
    internal_static_DeviceGroup_descriptor = getDescriptor().getMessageTypes().get(3);
    internal_static_DeviceGroup_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_DeviceGroup_descriptor, new String[] { "Id", "Name", "Icon", "AssignedDevicesIds", "AssignedPlaceIds", "Favourite" });
    internal_static_SceneEvent_descriptor = getDescriptor().getMessageTypes().get(4);
    internal_static_SceneEvent_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SceneEvent_descriptor, new String[] { "Id", "DeviceId", "EventNumber", "Value" });
    internal_static_SceneWeekSchedule_descriptor = getDescriptor().getMessageTypes().get(5);
    internal_static_SceneWeekSchedule_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SceneWeekSchedule_descriptor, new String[] { "Id", "WeekDay", "Hour" });
    internal_static_SceneAstralSchedule_descriptor = getDescriptor().getMessageTypes().get(6);
    internal_static_SceneAstralSchedule_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SceneAstralSchedule_descriptor, new String[] { "Id", "WeekDay", "Interval", "Astral" });
    internal_static_Scene_descriptor = getDescriptor().getMessageTypes().get(7);
    internal_static_Scene_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Scene_descriptor, new String[] { "Id", "Name", "Icon", "SceneEvents", "SceneWeekSchedules", "SceneAstralSchedules", "Enabled" });
    internal_static_Event_descriptor = getDescriptor().getMessageTypes().get(8);
    internal_static_Event_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Event_descriptor, new String[] { "Id", "DeviceId", "EventNumber", "Value", "Platform", "User", "Inserttime" });
    internal_static_WifiNetwork_descriptor = getDescriptor().getMessageTypes().get(9);
    internal_static_WifiNetwork_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_WifiNetwork_descriptor, new String[] { "Ssid", "Encrypted" });
    internal_static_IdPair_descriptor = getDescriptor().getMessageTypes().get(10);
    internal_static_IdPair_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_IdPair_descriptor, new String[] { "Left", "Right" });
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  public static void registerAllExtensions(ExtensionRegistry paramExtensionRegistry) {
    registerAllExtensions((ExtensionRegistryLite)paramExtensionRegistry);
  }
  
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite) {}
  
  public static final class Device extends GeneratedMessageV3 implements DeviceOrBuilder {
    public static final int ASSIGNED_GROUP_IDS_FIELD_NUMBER = 8;
    
    public static final int ASSIGNED_PLACE_IDS_FIELD_NUMBER = 7;
    
    private static final Device DEFAULT_INSTANCE = new Device();
    
    public static final int FAVOURITE_FIELD_NUMBER = 6;
    
    public static final int ICON_FIELD_NUMBER = 4;
    
    public static final int ID_FIELD_NUMBER = 1;
    
    public static final int INSERTTIME_FIELD_NUMBER = 5;
    
    public static final int NAME_FIELD_NUMBER = 2;
    
    @Deprecated
    public static final Parser<Device> PARSER = (Parser<Device>)new AbstractParser<Device>() {
        public MobilusModel.Device parsePartialFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws InvalidProtocolBufferException {
          return new MobilusModel.Device(param2CodedInputStream, param2ExtensionRegistryLite);
        }
      };
    
    public static final int TYPE_FIELD_NUMBER = 3;
    
    private static final long serialVersionUID = 0L;
    
    private Internal.LongList assignedGroupIds_;
    
    private Internal.LongList assignedPlaceIds_;
    
    private int bitField0_;
    
    private boolean favourite_;
    
    private int icon_;
    
    private long id_;
    
    private long inserttime_;
    
    private byte memoizedIsInitialized = (byte)-1;
    
    private volatile Object name_;
    
    private int type_;
    
    private Device() {
      this.name_ = "";
      this.assignedPlaceIds_ = emptyLongList();
      this.assignedGroupIds_ = emptyLongList();
    }
    
    private Device(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      this();
      if (param1ExtensionRegistryLite == null)
        throw new NullPointerException(); 
      UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
      boolean bool = false;
      int i = 0;
      while (!bool) {
        int j = i;
        int k = i;
        int m = i;
        try {
          boolean bool1;
          int i1;
          ByteString byteString;
          int n = param1CodedInputStream.readTag();
          switch (n) {
            default:
              j = i;
              k = i;
              m = i;
              bool1 = parseUnknownField(param1CodedInputStream, builder, param1ExtensionRegistryLite, n);
              break;
            case 66:
              j = i;
              k = i;
              m = i;
              i1 = param1CodedInputStream.pushLimit(param1CodedInputStream.readRawVarint32());
              n = i;
              if ((i & 0x80) == 0) {
                n = i;
                j = i;
                k = i;
                m = i;
                if (param1CodedInputStream.getBytesUntilLimit() > 0) {
                  j = i;
                  k = i;
                  m = i;
                  this.assignedGroupIds_ = newLongList();
                  n = i | 0x80;
                } 
              } 
              while (true) {
                j = n;
                k = n;
                m = n;
                if (param1CodedInputStream.getBytesUntilLimit() > 0) {
                  j = n;
                  k = n;
                  m = n;
                  this.assignedGroupIds_.addLong(param1CodedInputStream.readInt64());
                  continue;
                } 
                j = n;
                k = n;
                m = n;
                break;
              } 
              param1CodedInputStream.popLimit(i1);
              i = n;
              continue;
            case 64:
              n = i;
              if ((i & 0x80) == 0) {
                j = i;
                k = i;
                m = i;
                this.assignedGroupIds_ = newLongList();
                n = i | 0x80;
              } 
              j = n;
              k = n;
              m = n;
              this.assignedGroupIds_.addLong(param1CodedInputStream.readInt64());
              i = n;
              continue;
            case 58:
              j = i;
              k = i;
              m = i;
              i1 = param1CodedInputStream.pushLimit(param1CodedInputStream.readRawVarint32());
              n = i;
              if ((i & 0x40) == 0) {
                n = i;
                j = i;
                k = i;
                m = i;
                if (param1CodedInputStream.getBytesUntilLimit() > 0) {
                  j = i;
                  k = i;
                  m = i;
                  this.assignedPlaceIds_ = newLongList();
                  n = i | 0x40;
                } 
              } 
              while (true) {
                j = n;
                k = n;
                m = n;
                if (param1CodedInputStream.getBytesUntilLimit() > 0) {
                  j = n;
                  k = n;
                  m = n;
                  this.assignedPlaceIds_.addLong(param1CodedInputStream.readInt64());
                  continue;
                } 
                j = n;
                k = n;
                m = n;
                break;
              } 
              param1CodedInputStream.popLimit(i1);
              i = n;
              continue;
            case 56:
              n = i;
              if ((i & 0x40) == 0) {
                j = i;
                k = i;
                m = i;
                this.assignedPlaceIds_ = newLongList();
                n = i | 0x40;
              } 
              j = n;
              k = n;
              m = n;
              this.assignedPlaceIds_.addLong(param1CodedInputStream.readInt64());
              i = n;
              continue;
            case 48:
              j = i;
              k = i;
              m = i;
              this.bitField0_ |= 0x20;
              j = i;
              k = i;
              m = i;
              this.favourite_ = param1CodedInputStream.readBool();
              continue;
            case 40:
              j = i;
              k = i;
              m = i;
              this.bitField0_ |= 0x10;
              j = i;
              k = i;
              m = i;
              this.inserttime_ = param1CodedInputStream.readInt64();
              continue;
            case 32:
              j = i;
              k = i;
              m = i;
              this.bitField0_ |= 0x8;
              j = i;
              k = i;
              m = i;
              this.icon_ = param1CodedInputStream.readInt32();
              continue;
            case 24:
              j = i;
              k = i;
              m = i;
              this.bitField0_ |= 0x4;
              j = i;
              k = i;
              m = i;
              this.type_ = param1CodedInputStream.readInt32();
              continue;
            case 18:
              j = i;
              k = i;
              m = i;
              byteString = param1CodedInputStream.readBytes();
              j = i;
              k = i;
              m = i;
              this.bitField0_ |= 0x2;
              j = i;
              k = i;
              m = i;
              this.name_ = byteString;
              continue;
            case 8:
              j = i;
              k = i;
              m = i;
              this.bitField0_ |= 0x1;
              j = i;
              k = i;
              m = i;
              this.id_ = param1CodedInputStream.readInt64();
              continue;
            case 0:
              bool = true;
              continue;
          } 
          if (!bool1);
          continue;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          j = m;
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } catch (IOException iOException) {
          j = k;
          InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException();
          j = k;
          this(iOException);
          j = k;
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } finally {}
        if ((j & 0x40) != 0)
          this.assignedPlaceIds_.makeImmutable(); 
        if ((j & 0x80) != 0)
          this.assignedGroupIds_.makeImmutable(); 
        this.unknownFields = builder.build();
        makeExtensionsImmutable();
        throw param1CodedInputStream;
      } 
      if ((i & 0x40) != 0)
        this.assignedPlaceIds_.makeImmutable(); 
      if ((i & 0x80) != 0)
        this.assignedGroupIds_.makeImmutable(); 
      this.unknownFields = builder.build();
      makeExtensionsImmutable();
    }
    
    private Device(GeneratedMessageV3.Builder<?> param1Builder) {
      super(param1Builder);
    }
    
    public static Device getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_Device_descriptor;
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(Device param1Device) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(param1Device);
    }
    
    public static Device parseDelimitedFrom(InputStream param1InputStream) throws IOException {
      return (Device)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream);
    }
    
    public static Device parseDelimitedFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (Device)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static Device parseFrom(ByteString param1ByteString) throws InvalidProtocolBufferException {
      return (Device)PARSER.parseFrom(param1ByteString);
    }
    
    public static Device parseFrom(ByteString param1ByteString, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (Device)PARSER.parseFrom(param1ByteString, param1ExtensionRegistryLite);
    }
    
    public static Device parseFrom(CodedInputStream param1CodedInputStream) throws IOException {
      return (Device)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream);
    }
    
    public static Device parseFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (Device)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream, param1ExtensionRegistryLite);
    }
    
    public static Device parseFrom(InputStream param1InputStream) throws IOException {
      return (Device)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream);
    }
    
    public static Device parseFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (Device)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static Device parseFrom(ByteBuffer param1ByteBuffer) throws InvalidProtocolBufferException {
      return (Device)PARSER.parseFrom(param1ByteBuffer);
    }
    
    public static Device parseFrom(ByteBuffer param1ByteBuffer, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (Device)PARSER.parseFrom(param1ByteBuffer, param1ExtensionRegistryLite);
    }
    
    public static Device parseFrom(byte[] param1ArrayOfbyte) throws InvalidProtocolBufferException {
      return (Device)PARSER.parseFrom(param1ArrayOfbyte);
    }
    
    public static Device parseFrom(byte[] param1ArrayOfbyte, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (Device)PARSER.parseFrom(param1ArrayOfbyte, param1ExtensionRegistryLite);
    }
    
    public static Parser<Device> parser() {
      return PARSER;
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object == this)
        return true; 
      if (!(param1Object instanceof Device))
        return super.equals(param1Object); 
      param1Object = param1Object;
      return (hasId() != param1Object.hasId()) ? false : ((hasId() && getId() != param1Object.getId()) ? false : ((hasName() != param1Object.hasName()) ? false : ((hasName() && !getName().equals(param1Object.getName())) ? false : ((hasType() != param1Object.hasType()) ? false : ((hasType() && getType() != param1Object.getType()) ? false : ((hasIcon() != param1Object.hasIcon()) ? false : ((hasIcon() && getIcon() != param1Object.getIcon()) ? false : ((hasInserttime() != param1Object.hasInserttime()) ? false : ((hasInserttime() && getInserttime() != param1Object.getInserttime()) ? false : ((hasFavourite() != param1Object.hasFavourite()) ? false : ((hasFavourite() && getFavourite() != param1Object.getFavourite()) ? false : (!getAssignedPlaceIdsList().equals(param1Object.getAssignedPlaceIdsList()) ? false : (!getAssignedGroupIdsList().equals(param1Object.getAssignedGroupIdsList()) ? false : (!!this.unknownFields.equals(((Device)param1Object).unknownFields)))))))))))))));
    }
    
    public long getAssignedGroupIds(int param1Int) {
      return this.assignedGroupIds_.getLong(param1Int);
    }
    
    public int getAssignedGroupIdsCount() {
      return this.assignedGroupIds_.size();
    }
    
    public List<Long> getAssignedGroupIdsList() {
      return (List<Long>)this.assignedGroupIds_;
    }
    
    public long getAssignedPlaceIds(int param1Int) {
      return this.assignedPlaceIds_.getLong(param1Int);
    }
    
    public int getAssignedPlaceIdsCount() {
      return this.assignedPlaceIds_.size();
    }
    
    public List<Long> getAssignedPlaceIdsList() {
      return (List<Long>)this.assignedPlaceIds_;
    }
    
    public Device getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
    
    public boolean getFavourite() {
      return this.favourite_;
    }
    
    public int getIcon() {
      return this.icon_;
    }
    
    public long getId() {
      return this.id_;
    }
    
    public long getInserttime() {
      return this.inserttime_;
    }
    
    public String getName() {
      Object object = this.name_;
      if (object instanceof String)
        return (String)object; 
      object = object;
      String str = object.toStringUtf8();
      if (object.isValidUtf8())
        this.name_ = str; 
      return str;
    }
    
    public ByteString getNameBytes() {
      Object object = this.name_;
      if (object instanceof String) {
        object = ByteString.copyFromUtf8((String)object);
        this.name_ = object;
        return (ByteString)object;
      } 
      return (ByteString)object;
    }
    
    public Parser<Device> getParserForType() {
      return PARSER;
    }
    
    public int getSerializedSize() {
      int i = this.memoizedSize;
      if (i != -1)
        return i; 
      i = this.bitField0_;
      byte b = 0;
      if ((i & 0x1) != 0) {
        i = CodedOutputStream.computeInt64Size(1, this.id_) + 0;
      } else {
        i = 0;
      } 
      int j = i;
      if ((this.bitField0_ & 0x2) != 0)
        j = i + GeneratedMessageV3.computeStringSize(2, this.name_); 
      i = j;
      if ((this.bitField0_ & 0x4) != 0)
        i = j + CodedOutputStream.computeInt32Size(3, this.type_); 
      j = i;
      if ((this.bitField0_ & 0x8) != 0)
        j = i + CodedOutputStream.computeInt32Size(4, this.icon_); 
      i = j;
      if ((this.bitField0_ & 0x10) != 0)
        i = j + CodedOutputStream.computeInt64Size(5, this.inserttime_); 
      j = i;
      if ((this.bitField0_ & 0x20) != 0)
        j = i + CodedOutputStream.computeBoolSize(6, this.favourite_); 
      int k = 0;
      i = k;
      while (k < this.assignedPlaceIds_.size()) {
        i += CodedOutputStream.computeInt64SizeNoTag(this.assignedPlaceIds_.getLong(k));
        k++;
      } 
      int m = getAssignedPlaceIdsList().size();
      k = 0;
      while (b < this.assignedGroupIds_.size()) {
        k += CodedOutputStream.computeInt64SizeNoTag(this.assignedGroupIds_.getLong(b));
        b++;
      } 
      i = j + i + m * 1 + k + getAssignedGroupIdsList().size() * 1 + this.unknownFields.getSerializedSize();
      this.memoizedSize = i;
      return i;
    }
    
    public int getType() {
      return this.type_;
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    public boolean hasFavourite() {
      boolean bool;
      if ((this.bitField0_ & 0x20) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasIcon() {
      boolean bool;
      if ((this.bitField0_ & 0x8) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasId() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean hasInserttime() {
      boolean bool;
      if ((this.bitField0_ & 0x10) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasName() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasType() {
      boolean bool;
      if ((this.bitField0_ & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int hashCode() {
      if (this.memoizedHashCode != 0)
        return this.memoizedHashCode; 
      int i = 779 + getDescriptor().hashCode();
      int j = i;
      if (hasId())
        j = (i * 37 + 1) * 53 + Internal.hashLong(getId()); 
      i = j;
      if (hasName())
        i = (j * 37 + 2) * 53 + getName().hashCode(); 
      int k = i;
      if (hasType())
        k = (i * 37 + 3) * 53 + getType(); 
      j = k;
      if (hasIcon())
        j = (k * 37 + 4) * 53 + getIcon(); 
      k = j;
      if (hasInserttime())
        k = (j * 37 + 5) * 53 + Internal.hashLong(getInserttime()); 
      i = k;
      if (hasFavourite())
        i = (k * 37 + 6) * 53 + Internal.hashBoolean(getFavourite()); 
      j = i;
      if (getAssignedPlaceIdsCount() > 0)
        j = (i * 37 + 7) * 53 + getAssignedPlaceIdsList().hashCode(); 
      i = j;
      if (getAssignedGroupIdsCount() > 0)
        i = (j * 37 + 8) * 53 + getAssignedGroupIdsList().hashCode(); 
      j = i * 29 + this.unknownFields.hashCode();
      this.memoizedHashCode = j;
      return j;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_Device_fieldAccessorTable.ensureFieldAccessorsInitialized(Device.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      byte b = this.memoizedIsInitialized;
      if (b == 1)
        return true; 
      if (b == 0)
        return false; 
      this.memoizedIsInitialized = (byte)1;
      return true;
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      return new Builder(param1BuilderParent);
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter param1UnusedPrivateParameter) {
      return new Device();
    }
    
    public Builder toBuilder() {
      Builder builder;
      if (this == DEFAULT_INSTANCE) {
        builder = new Builder();
      } else {
        builder = (new Builder()).mergeFrom(this);
      } 
      return builder;
    }
    
    public void writeTo(CodedOutputStream param1CodedOutputStream) throws IOException {
      byte b3;
      if ((this.bitField0_ & 0x1) != 0)
        param1CodedOutputStream.writeInt64(1, this.id_); 
      if ((this.bitField0_ & 0x2) != 0)
        GeneratedMessageV3.writeString(param1CodedOutputStream, 2, this.name_); 
      if ((this.bitField0_ & 0x4) != 0)
        param1CodedOutputStream.writeInt32(3, this.type_); 
      if ((this.bitField0_ & 0x8) != 0)
        param1CodedOutputStream.writeInt32(4, this.icon_); 
      if ((this.bitField0_ & 0x10) != 0)
        param1CodedOutputStream.writeInt64(5, this.inserttime_); 
      if ((this.bitField0_ & 0x20) != 0)
        param1CodedOutputStream.writeBool(6, this.favourite_); 
      byte b1 = 0;
      byte b2 = 0;
      while (true) {
        b3 = b1;
        if (b2 < this.assignedPlaceIds_.size()) {
          param1CodedOutputStream.writeInt64(7, this.assignedPlaceIds_.getLong(b2));
          b2++;
          continue;
        } 
        break;
      } 
      while (b3 < this.assignedGroupIds_.size()) {
        param1CodedOutputStream.writeInt64(8, this.assignedGroupIds_.getLong(b3));
        b3++;
      } 
      this.unknownFields.writeTo(param1CodedOutputStream);
    }
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MobilusModel.DeviceOrBuilder {
      private Internal.LongList assignedGroupIds_ = MobilusModel.Device.emptyLongList();
      
      private Internal.LongList assignedPlaceIds_ = MobilusModel.Device.emptyLongList();
      
      private int bitField0_;
      
      private boolean favourite_;
      
      private int icon_;
      
      private long id_;
      
      private long inserttime_;
      
      private Object name_ = "";
      
      private int type_;
      
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent param2BuilderParent) {
        super(param2BuilderParent);
        maybeForceBuilderInitialization();
      }
      
      private void ensureAssignedGroupIdsIsMutable() {
        if ((this.bitField0_ & 0x80) == 0) {
          this.assignedGroupIds_ = MobilusModel.Device.mutableCopy(this.assignedGroupIds_);
          this.bitField0_ |= 0x80;
        } 
      }
      
      private void ensureAssignedPlaceIdsIsMutable() {
        if ((this.bitField0_ & 0x40) == 0) {
          this.assignedPlaceIds_ = MobilusModel.Device.mutableCopy(this.assignedPlaceIds_);
          this.bitField0_ |= 0x40;
        } 
      }
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MobilusModel.internal_static_Device_descriptor;
      }
      
      private void maybeForceBuilderInitialization() {
        MobilusModel.Device.alwaysUseFieldBuilders;
      }
      
      public Builder addAllAssignedGroupIds(Iterable<? extends Long> param2Iterable) {
        ensureAssignedGroupIdsIsMutable();
        AbstractMessageLite.Builder.addAll(param2Iterable, (List)this.assignedGroupIds_);
        onChanged();
        return this;
      }
      
      public Builder addAllAssignedPlaceIds(Iterable<? extends Long> param2Iterable) {
        ensureAssignedPlaceIdsIsMutable();
        AbstractMessageLite.Builder.addAll(param2Iterable, (List)this.assignedPlaceIds_);
        onChanged();
        return this;
      }
      
      public Builder addAssignedGroupIds(long param2Long) {
        ensureAssignedGroupIdsIsMutable();
        this.assignedGroupIds_.addLong(param2Long);
        onChanged();
        return this;
      }
      
      public Builder addAssignedPlaceIds(long param2Long) {
        ensureAssignedPlaceIdsIsMutable();
        this.assignedPlaceIds_.addLong(param2Long);
        onChanged();
        return this;
      }
      
      public Builder addRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.addRepeatedField(param2FieldDescriptor, param2Object);
      }
      
      public MobilusModel.Device build() {
        MobilusModel.Device device = buildPartial();
        if (!device.isInitialized())
          throw newUninitializedMessageException(device); 
        return device;
      }
      
      public MobilusModel.Device buildPartial() {
        MobilusModel.Device device = new MobilusModel.Device(this);
        int i = this.bitField0_;
        if ((i & 0x1) != 0) {
          MobilusModel.Device.access$2602(device, this.id_);
          j = 1;
        } else {
          j = 0;
        } 
        int k = j;
        if ((i & 0x2) != 0)
          k = j | 0x2; 
        MobilusModel.Device.access$2702(device, this.name_);
        int j = k;
        if ((i & 0x4) != 0) {
          MobilusModel.Device.access$2802(device, this.type_);
          j = k | 0x4;
        } 
        k = j;
        if ((i & 0x8) != 0) {
          MobilusModel.Device.access$2902(device, this.icon_);
          k = j | 0x8;
        } 
        j = k;
        if ((i & 0x10) != 0) {
          MobilusModel.Device.access$3002(device, this.inserttime_);
          j = k | 0x10;
        } 
        k = j;
        if ((i & 0x20) != 0) {
          MobilusModel.Device.access$3102(device, this.favourite_);
          k = j | 0x20;
        } 
        if ((this.bitField0_ & 0x40) != 0) {
          this.assignedPlaceIds_.makeImmutable();
          this.bitField0_ &= 0xFFFFFFBF;
        } 
        MobilusModel.Device.access$3202(device, this.assignedPlaceIds_);
        if ((this.bitField0_ & 0x80) != 0) {
          this.assignedGroupIds_.makeImmutable();
          this.bitField0_ &= 0xFFFFFF7F;
        } 
        MobilusModel.Device.access$3302(device, this.assignedGroupIds_);
        MobilusModel.Device.access$3402(device, k);
        onBuilt();
        return device;
      }
      
      public Builder clear() {
        super.clear();
        this.id_ = 0L;
        this.bitField0_ &= 0xFFFFFFFE;
        this.name_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        this.type_ = 0;
        this.bitField0_ &= 0xFFFFFFFB;
        this.icon_ = 0;
        this.bitField0_ &= 0xFFFFFFF7;
        this.inserttime_ = 0L;
        this.bitField0_ &= 0xFFFFFFEF;
        this.favourite_ = false;
        this.bitField0_ &= 0xFFFFFFDF;
        this.assignedPlaceIds_ = MobilusModel.Device.emptyLongList();
        this.bitField0_ &= 0xFFFFFFBF;
        this.assignedGroupIds_ = MobilusModel.Device.emptyLongList();
        this.bitField0_ &= 0xFFFFFF7F;
        return this;
      }
      
      public Builder clearAssignedGroupIds() {
        this.assignedGroupIds_ = MobilusModel.Device.emptyLongList();
        this.bitField0_ &= 0xFFFFFF7F;
        onChanged();
        return this;
      }
      
      public Builder clearAssignedPlaceIds() {
        this.assignedPlaceIds_ = MobilusModel.Device.emptyLongList();
        this.bitField0_ &= 0xFFFFFFBF;
        onChanged();
        return this;
      }
      
      public Builder clearFavourite() {
        this.bitField0_ &= 0xFFFFFFDF;
        this.favourite_ = false;
        onChanged();
        return this;
      }
      
      public Builder clearField(Descriptors.FieldDescriptor param2FieldDescriptor) {
        return (Builder)super.clearField(param2FieldDescriptor);
      }
      
      public Builder clearIcon() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.icon_ = 0;
        onChanged();
        return this;
      }
      
      public Builder clearId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.id_ = 0L;
        onChanged();
        return this;
      }
      
      public Builder clearInserttime() {
        this.bitField0_ &= 0xFFFFFFEF;
        this.inserttime_ = 0L;
        onChanged();
        return this;
      }
      
      public Builder clearName() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.name_ = MobilusModel.Device.getDefaultInstance().getName();
        onChanged();
        return this;
      }
      
      public Builder clearOneof(Descriptors.OneofDescriptor param2OneofDescriptor) {
        return (Builder)super.clearOneof(param2OneofDescriptor);
      }
      
      public Builder clearType() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.type_ = 0;
        onChanged();
        return this;
      }
      
      public Builder clone() {
        return (Builder)super.clone();
      }
      
      public long getAssignedGroupIds(int param2Int) {
        return this.assignedGroupIds_.getLong(param2Int);
      }
      
      public int getAssignedGroupIdsCount() {
        return this.assignedGroupIds_.size();
      }
      
      public List<Long> getAssignedGroupIdsList() {
        Internal.LongList longList;
        if ((this.bitField0_ & 0x80) != 0) {
          List<?> list = Collections.unmodifiableList((List<?>)this.assignedGroupIds_);
        } else {
          longList = this.assignedGroupIds_;
        } 
        return (List<Long>)longList;
      }
      
      public long getAssignedPlaceIds(int param2Int) {
        return this.assignedPlaceIds_.getLong(param2Int);
      }
      
      public int getAssignedPlaceIdsCount() {
        return this.assignedPlaceIds_.size();
      }
      
      public List<Long> getAssignedPlaceIdsList() {
        Internal.LongList longList;
        if ((this.bitField0_ & 0x40) != 0) {
          List<?> list = Collections.unmodifiableList((List<?>)this.assignedPlaceIds_);
        } else {
          longList = this.assignedPlaceIds_;
        } 
        return (List<Long>)longList;
      }
      
      public MobilusModel.Device getDefaultInstanceForType() {
        return MobilusModel.Device.getDefaultInstance();
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MobilusModel.internal_static_Device_descriptor;
      }
      
      public boolean getFavourite() {
        return this.favourite_;
      }
      
      public int getIcon() {
        return this.icon_;
      }
      
      public long getId() {
        return this.id_;
      }
      
      public long getInserttime() {
        return this.inserttime_;
      }
      
      public String getName() {
        Object object = this.name_;
        if (!(object instanceof String)) {
          ByteString byteString = (ByteString)object;
          object = byteString.toStringUtf8();
          if (byteString.isValidUtf8())
            this.name_ = object; 
          return (String)object;
        } 
        return (String)object;
      }
      
      public ByteString getNameBytes() {
        Object object = this.name_;
        if (object instanceof String) {
          object = ByteString.copyFromUtf8((String)object);
          this.name_ = object;
          return (ByteString)object;
        } 
        return (ByteString)object;
      }
      
      public int getType() {
        return this.type_;
      }
      
      public boolean hasFavourite() {
        boolean bool;
        if ((this.bitField0_ & 0x20) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasIcon() {
        boolean bool;
        if ((this.bitField0_ & 0x8) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasId() {
        int i = this.bitField0_;
        boolean bool = true;
        if ((i & 0x1) == 0)
          bool = false; 
        return bool;
      }
      
      public boolean hasInserttime() {
        boolean bool;
        if ((this.bitField0_ & 0x10) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasName() {
        boolean bool;
        if ((this.bitField0_ & 0x2) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasType() {
        boolean bool;
        if ((this.bitField0_ & 0x4) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MobilusModel.internal_static_Device_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.Device.class, Builder.class);
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws IOException {
        ExtensionRegistryLite extensionRegistryLite = null;
        try {
          MobilusModel.Device device = (MobilusModel.Device)MobilusModel.Device.PARSER.parsePartialFrom(param2CodedInputStream, param2ExtensionRegistryLite);
          return this;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          MobilusModel.Device device = (MobilusModel.Device)invalidProtocolBufferException.getUnfinishedMessage();
        } finally {
          param2CodedInputStream = null;
        } 
        if (param2ExtensionRegistryLite != null)
          mergeFrom((MobilusModel.Device)param2ExtensionRegistryLite); 
        throw param2CodedInputStream;
      }
      
      public Builder mergeFrom(Message param2Message) {
        if (param2Message instanceof MobilusModel.Device)
          return mergeFrom((MobilusModel.Device)param2Message); 
        super.mergeFrom(param2Message);
        return this;
      }
      
      public Builder mergeFrom(MobilusModel.Device param2Device) {
        if (param2Device == MobilusModel.Device.getDefaultInstance())
          return this; 
        if (param2Device.hasId())
          setId(param2Device.getId()); 
        if (param2Device.hasName()) {
          this.bitField0_ |= 0x2;
          this.name_ = param2Device.name_;
          onChanged();
        } 
        if (param2Device.hasType())
          setType(param2Device.getType()); 
        if (param2Device.hasIcon())
          setIcon(param2Device.getIcon()); 
        if (param2Device.hasInserttime())
          setInserttime(param2Device.getInserttime()); 
        if (param2Device.hasFavourite())
          setFavourite(param2Device.getFavourite()); 
        if (!param2Device.assignedPlaceIds_.isEmpty()) {
          if (this.assignedPlaceIds_.isEmpty()) {
            this.assignedPlaceIds_ = param2Device.assignedPlaceIds_;
            this.bitField0_ &= 0xFFFFFFBF;
          } else {
            ensureAssignedPlaceIdsIsMutable();
            this.assignedPlaceIds_.addAll((Collection)param2Device.assignedPlaceIds_);
          } 
          onChanged();
        } 
        if (!param2Device.assignedGroupIds_.isEmpty()) {
          if (this.assignedGroupIds_.isEmpty()) {
            this.assignedGroupIds_ = param2Device.assignedGroupIds_;
            this.bitField0_ &= 0xFFFFFF7F;
          } else {
            ensureAssignedGroupIdsIsMutable();
            this.assignedGroupIds_.addAll((Collection)param2Device.assignedGroupIds_);
          } 
          onChanged();
        } 
        mergeUnknownFields(param2Device.unknownFields);
        onChanged();
        return this;
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.mergeUnknownFields(param2UnknownFieldSet);
      }
      
      public Builder setAssignedGroupIds(int param2Int, long param2Long) {
        ensureAssignedGroupIdsIsMutable();
        this.assignedGroupIds_.setLong(param2Int, param2Long);
        onChanged();
        return this;
      }
      
      public Builder setAssignedPlaceIds(int param2Int, long param2Long) {
        ensureAssignedPlaceIdsIsMutable();
        this.assignedPlaceIds_.setLong(param2Int, param2Long);
        onChanged();
        return this;
      }
      
      public Builder setFavourite(boolean param2Boolean) {
        this.bitField0_ |= 0x20;
        this.favourite_ = param2Boolean;
        onChanged();
        return this;
      }
      
      public Builder setField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.setField(param2FieldDescriptor, param2Object);
      }
      
      public Builder setIcon(int param2Int) {
        this.bitField0_ |= 0x8;
        this.icon_ = param2Int;
        onChanged();
        return this;
      }
      
      public Builder setId(long param2Long) {
        this.bitField0_ |= 0x1;
        this.id_ = param2Long;
        onChanged();
        return this;
      }
      
      public Builder setInserttime(long param2Long) {
        this.bitField0_ |= 0x10;
        this.inserttime_ = param2Long;
        onChanged();
        return this;
      }
      
      public Builder setName(String param2String) {
        if (param2String == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.name_ = param2String;
        onChanged();
        return this;
      }
      
      public Builder setNameBytes(ByteString param2ByteString) {
        if (param2ByteString == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.name_ = param2ByteString;
        onChanged();
        return this;
      }
      
      public Builder setRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, int param2Int, Object param2Object) {
        return (Builder)super.setRepeatedField(param2FieldDescriptor, param2Int, param2Object);
      }
      
      public Builder setType(int param2Int) {
        this.bitField0_ |= 0x4;
        this.type_ = param2Int;
        onChanged();
        return this;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.setUnknownFields(param2UnknownFieldSet);
      }
    }
  }
  
  static final class null extends AbstractParser<Device> {
    public MobilusModel.Device parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return new MobilusModel.Device(param1CodedInputStream, param1ExtensionRegistryLite);
    }
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<Device.Builder> implements DeviceOrBuilder {
    private Internal.LongList assignedGroupIds_ = MobilusModel.Device.emptyLongList();
    
    private Internal.LongList assignedPlaceIds_ = MobilusModel.Device.emptyLongList();
    
    private int bitField0_;
    
    private boolean favourite_;
    
    private int icon_;
    
    private long id_;
    
    private long inserttime_;
    
    private Object name_ = "";
    
    private int type_;
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      super(param1BuilderParent);
      maybeForceBuilderInitialization();
    }
    
    private void ensureAssignedGroupIdsIsMutable() {
      if ((this.bitField0_ & 0x80) == 0) {
        this.assignedGroupIds_ = MobilusModel.Device.mutableCopy(this.assignedGroupIds_);
        this.bitField0_ |= 0x80;
      } 
    }
    
    private void ensureAssignedPlaceIdsIsMutable() {
      if ((this.bitField0_ & 0x40) == 0) {
        this.assignedPlaceIds_ = MobilusModel.Device.mutableCopy(this.assignedPlaceIds_);
        this.bitField0_ |= 0x40;
      } 
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_Device_descriptor;
    }
    
    private void maybeForceBuilderInitialization() {
      MobilusModel.Device.alwaysUseFieldBuilders;
    }
    
    public Builder addAllAssignedGroupIds(Iterable<? extends Long> param1Iterable) {
      ensureAssignedGroupIdsIsMutable();
      AbstractMessageLite.Builder.addAll(param1Iterable, (List)this.assignedGroupIds_);
      onChanged();
      return this;
    }
    
    public Builder addAllAssignedPlaceIds(Iterable<? extends Long> param1Iterable) {
      ensureAssignedPlaceIdsIsMutable();
      AbstractMessageLite.Builder.addAll(param1Iterable, (List)this.assignedPlaceIds_);
      onChanged();
      return this;
    }
    
    public Builder addAssignedGroupIds(long param1Long) {
      ensureAssignedGroupIdsIsMutable();
      this.assignedGroupIds_.addLong(param1Long);
      onChanged();
      return this;
    }
    
    public Builder addAssignedPlaceIds(long param1Long) {
      ensureAssignedPlaceIdsIsMutable();
      this.assignedPlaceIds_.addLong(param1Long);
      onChanged();
      return this;
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.addRepeatedField(param1FieldDescriptor, param1Object);
    }
    
    public MobilusModel.Device build() {
      MobilusModel.Device device = buildPartial();
      if (!device.isInitialized())
        throw newUninitializedMessageException(device); 
      return device;
    }
    
    public MobilusModel.Device buildPartial() {
      MobilusModel.Device device = new MobilusModel.Device(this);
      int i = this.bitField0_;
      if ((i & 0x1) != 0) {
        MobilusModel.Device.access$2602(device, this.id_);
        j = 1;
      } else {
        j = 0;
      } 
      int k = j;
      if ((i & 0x2) != 0)
        k = j | 0x2; 
      MobilusModel.Device.access$2702(device, this.name_);
      int j = k;
      if ((i & 0x4) != 0) {
        MobilusModel.Device.access$2802(device, this.type_);
        j = k | 0x4;
      } 
      k = j;
      if ((i & 0x8) != 0) {
        MobilusModel.Device.access$2902(device, this.icon_);
        k = j | 0x8;
      } 
      j = k;
      if ((i & 0x10) != 0) {
        MobilusModel.Device.access$3002(device, this.inserttime_);
        j = k | 0x10;
      } 
      k = j;
      if ((i & 0x20) != 0) {
        MobilusModel.Device.access$3102(device, this.favourite_);
        k = j | 0x20;
      } 
      if ((this.bitField0_ & 0x40) != 0) {
        this.assignedPlaceIds_.makeImmutable();
        this.bitField0_ &= 0xFFFFFFBF;
      } 
      MobilusModel.Device.access$3202(device, this.assignedPlaceIds_);
      if ((this.bitField0_ & 0x80) != 0) {
        this.assignedGroupIds_.makeImmutable();
        this.bitField0_ &= 0xFFFFFF7F;
      } 
      MobilusModel.Device.access$3302(device, this.assignedGroupIds_);
      MobilusModel.Device.access$3402(device, k);
      onBuilt();
      return device;
    }
    
    public Builder clear() {
      super.clear();
      this.id_ = 0L;
      this.bitField0_ &= 0xFFFFFFFE;
      this.name_ = "";
      this.bitField0_ &= 0xFFFFFFFD;
      this.type_ = 0;
      this.bitField0_ &= 0xFFFFFFFB;
      this.icon_ = 0;
      this.bitField0_ &= 0xFFFFFFF7;
      this.inserttime_ = 0L;
      this.bitField0_ &= 0xFFFFFFEF;
      this.favourite_ = false;
      this.bitField0_ &= 0xFFFFFFDF;
      this.assignedPlaceIds_ = MobilusModel.Device.emptyLongList();
      this.bitField0_ &= 0xFFFFFFBF;
      this.assignedGroupIds_ = MobilusModel.Device.emptyLongList();
      this.bitField0_ &= 0xFFFFFF7F;
      return this;
    }
    
    public Builder clearAssignedGroupIds() {
      this.assignedGroupIds_ = MobilusModel.Device.emptyLongList();
      this.bitField0_ &= 0xFFFFFF7F;
      onChanged();
      return this;
    }
    
    public Builder clearAssignedPlaceIds() {
      this.assignedPlaceIds_ = MobilusModel.Device.emptyLongList();
      this.bitField0_ &= 0xFFFFFFBF;
      onChanged();
      return this;
    }
    
    public Builder clearFavourite() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.favourite_ = false;
      onChanged();
      return this;
    }
    
    public Builder clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return (Builder)super.clearField(param1FieldDescriptor);
    }
    
    public Builder clearIcon() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.icon_ = 0;
      onChanged();
      return this;
    }
    
    public Builder clearId() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.id_ = 0L;
      onChanged();
      return this;
    }
    
    public Builder clearInserttime() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.inserttime_ = 0L;
      onChanged();
      return this;
    }
    
    public Builder clearName() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.name_ = MobilusModel.Device.getDefaultInstance().getName();
      onChanged();
      return this;
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return (Builder)super.clearOneof(param1OneofDescriptor);
    }
    
    public Builder clearType() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.type_ = 0;
      onChanged();
      return this;
    }
    
    public Builder clone() {
      return (Builder)super.clone();
    }
    
    public long getAssignedGroupIds(int param1Int) {
      return this.assignedGroupIds_.getLong(param1Int);
    }
    
    public int getAssignedGroupIdsCount() {
      return this.assignedGroupIds_.size();
    }
    
    public List<Long> getAssignedGroupIdsList() {
      Internal.LongList longList;
      if ((this.bitField0_ & 0x80) != 0) {
        List<?> list = Collections.unmodifiableList((List<?>)this.assignedGroupIds_);
      } else {
        longList = this.assignedGroupIds_;
      } 
      return (List<Long>)longList;
    }
    
    public long getAssignedPlaceIds(int param1Int) {
      return this.assignedPlaceIds_.getLong(param1Int);
    }
    
    public int getAssignedPlaceIdsCount() {
      return this.assignedPlaceIds_.size();
    }
    
    public List<Long> getAssignedPlaceIdsList() {
      Internal.LongList longList;
      if ((this.bitField0_ & 0x40) != 0) {
        List<?> list = Collections.unmodifiableList((List<?>)this.assignedPlaceIds_);
      } else {
        longList = this.assignedPlaceIds_;
      } 
      return (List<Long>)longList;
    }
    
    public MobilusModel.Device getDefaultInstanceForType() {
      return MobilusModel.Device.getDefaultInstance();
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return MobilusModel.internal_static_Device_descriptor;
    }
    
    public boolean getFavourite() {
      return this.favourite_;
    }
    
    public int getIcon() {
      return this.icon_;
    }
    
    public long getId() {
      return this.id_;
    }
    
    public long getInserttime() {
      return this.inserttime_;
    }
    
    public String getName() {
      Object object = this.name_;
      if (!(object instanceof String)) {
        ByteString byteString = (ByteString)object;
        object = byteString.toStringUtf8();
        if (byteString.isValidUtf8())
          this.name_ = object; 
        return (String)object;
      } 
      return (String)object;
    }
    
    public ByteString getNameBytes() {
      Object object = this.name_;
      if (object instanceof String) {
        object = ByteString.copyFromUtf8((String)object);
        this.name_ = object;
        return (ByteString)object;
      } 
      return (ByteString)object;
    }
    
    public int getType() {
      return this.type_;
    }
    
    public boolean hasFavourite() {
      boolean bool;
      if ((this.bitField0_ & 0x20) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasIcon() {
      boolean bool;
      if ((this.bitField0_ & 0x8) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasId() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean hasInserttime() {
      boolean bool;
      if ((this.bitField0_ & 0x10) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasName() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasType() {
      boolean bool;
      if ((this.bitField0_ & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_Device_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.Device.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      ExtensionRegistryLite extensionRegistryLite = null;
      try {
        MobilusModel.Device device = (MobilusModel.Device)MobilusModel.Device.PARSER.parsePartialFrom(param1CodedInputStream, param1ExtensionRegistryLite);
        return this;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        MobilusModel.Device device = (MobilusModel.Device)invalidProtocolBufferException.getUnfinishedMessage();
      } finally {
        param1CodedInputStream = null;
      } 
      if (param1ExtensionRegistryLite != null)
        mergeFrom((MobilusModel.Device)param1ExtensionRegistryLite); 
      throw param1CodedInputStream;
    }
    
    public Builder mergeFrom(Message param1Message) {
      if (param1Message instanceof MobilusModel.Device)
        return mergeFrom((MobilusModel.Device)param1Message); 
      super.mergeFrom(param1Message);
      return this;
    }
    
    public Builder mergeFrom(MobilusModel.Device param1Device) {
      if (param1Device == MobilusModel.Device.getDefaultInstance())
        return this; 
      if (param1Device.hasId())
        setId(param1Device.getId()); 
      if (param1Device.hasName()) {
        this.bitField0_ |= 0x2;
        this.name_ = param1Device.name_;
        onChanged();
      } 
      if (param1Device.hasType())
        setType(param1Device.getType()); 
      if (param1Device.hasIcon())
        setIcon(param1Device.getIcon()); 
      if (param1Device.hasInserttime())
        setInserttime(param1Device.getInserttime()); 
      if (param1Device.hasFavourite())
        setFavourite(param1Device.getFavourite()); 
      if (!param1Device.assignedPlaceIds_.isEmpty()) {
        if (this.assignedPlaceIds_.isEmpty()) {
          this.assignedPlaceIds_ = param1Device.assignedPlaceIds_;
          this.bitField0_ &= 0xFFFFFFBF;
        } else {
          ensureAssignedPlaceIdsIsMutable();
          this.assignedPlaceIds_.addAll((Collection)param1Device.assignedPlaceIds_);
        } 
        onChanged();
      } 
      if (!param1Device.assignedGroupIds_.isEmpty()) {
        if (this.assignedGroupIds_.isEmpty()) {
          this.assignedGroupIds_ = param1Device.assignedGroupIds_;
          this.bitField0_ &= 0xFFFFFF7F;
        } else {
          ensureAssignedGroupIdsIsMutable();
          this.assignedGroupIds_.addAll((Collection)param1Device.assignedGroupIds_);
        } 
        onChanged();
      } 
      mergeUnknownFields(param1Device.unknownFields);
      onChanged();
      return this;
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.mergeUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder setAssignedGroupIds(int param1Int, long param1Long) {
      ensureAssignedGroupIdsIsMutable();
      this.assignedGroupIds_.setLong(param1Int, param1Long);
      onChanged();
      return this;
    }
    
    public Builder setAssignedPlaceIds(int param1Int, long param1Long) {
      ensureAssignedPlaceIdsIsMutable();
      this.assignedPlaceIds_.setLong(param1Int, param1Long);
      onChanged();
      return this;
    }
    
    public Builder setFavourite(boolean param1Boolean) {
      this.bitField0_ |= 0x20;
      this.favourite_ = param1Boolean;
      onChanged();
      return this;
    }
    
    public Builder setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.setField(param1FieldDescriptor, param1Object);
    }
    
    public Builder setIcon(int param1Int) {
      this.bitField0_ |= 0x8;
      this.icon_ = param1Int;
      onChanged();
      return this;
    }
    
    public Builder setId(long param1Long) {
      this.bitField0_ |= 0x1;
      this.id_ = param1Long;
      onChanged();
      return this;
    }
    
    public Builder setInserttime(long param1Long) {
      this.bitField0_ |= 0x10;
      this.inserttime_ = param1Long;
      onChanged();
      return this;
    }
    
    public Builder setName(String param1String) {
      if (param1String == null)
        throw new NullPointerException(); 
      this.bitField0_ |= 0x2;
      this.name_ = param1String;
      onChanged();
      return this;
    }
    
    public Builder setNameBytes(ByteString param1ByteString) {
      if (param1ByteString == null)
        throw new NullPointerException(); 
      this.bitField0_ |= 0x2;
      this.name_ = param1ByteString;
      onChanged();
      return this;
    }
    
    public Builder setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object) {
      return (Builder)super.setRepeatedField(param1FieldDescriptor, param1Int, param1Object);
    }
    
    public Builder setType(int param1Int) {
      this.bitField0_ |= 0x4;
      this.type_ = param1Int;
      onChanged();
      return this;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.setUnknownFields(param1UnknownFieldSet);
    }
  }
  
  public static final class DeviceGroup extends GeneratedMessageV3 implements DeviceGroupOrBuilder {
    public static final int ASSIGNED_DEVICES_IDS_FIELD_NUMBER = 4;
    
    public static final int ASSIGNED_PLACE_IDS_FIELD_NUMBER = 5;
    
    private static final DeviceGroup DEFAULT_INSTANCE = new DeviceGroup();
    
    public static final int FAVOURITE_FIELD_NUMBER = 6;
    
    public static final int ICON_FIELD_NUMBER = 3;
    
    public static final int ID_FIELD_NUMBER = 1;
    
    public static final int NAME_FIELD_NUMBER = 2;
    
    @Deprecated
    public static final Parser<DeviceGroup> PARSER = (Parser<DeviceGroup>)new AbstractParser<DeviceGroup>() {
        public MobilusModel.DeviceGroup parsePartialFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws InvalidProtocolBufferException {
          return new MobilusModel.DeviceGroup(param2CodedInputStream, param2ExtensionRegistryLite);
        }
      };
    
    private static final long serialVersionUID = 0L;
    
    private Internal.LongList assignedDevicesIds_;
    
    private Internal.LongList assignedPlaceIds_;
    
    private int bitField0_;
    
    private boolean favourite_;
    
    private int icon_;
    
    private long id_;
    
    private byte memoizedIsInitialized = (byte)-1;
    
    private volatile Object name_;
    
    private DeviceGroup() {
      this.name_ = "";
      this.assignedDevicesIds_ = emptyLongList();
      this.assignedPlaceIds_ = emptyLongList();
    }
    
    private DeviceGroup(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      this();
      if (param1ExtensionRegistryLite == null)
        throw new NullPointerException(); 
      UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
      boolean bool = false;
      int i = 0;
      while (!bool) {
        int j = i;
        int k = i;
        int m = i;
        try {
          int n = param1CodedInputStream.readTag();
          if (n != 0) {
            if (n != 8) {
              if (n != 18) {
                if (n != 24) {
                  if (n != 32) {
                    if (n != 34) {
                      if (n != 40) {
                        if (n != 42) {
                          if (n != 48) {
                            j = i;
                            k = i;
                            m = i;
                            if (!parseUnknownField(param1CodedInputStream, builder, param1ExtensionRegistryLite, n))
                              continue; 
                            continue;
                          } 
                          j = i;
                          k = i;
                          m = i;
                          this.bitField0_ |= 0x8;
                          j = i;
                          k = i;
                          m = i;
                          this.favourite_ = param1CodedInputStream.readBool();
                          continue;
                        } 
                        j = i;
                        k = i;
                        m = i;
                        int i2 = param1CodedInputStream.pushLimit(param1CodedInputStream.readRawVarint32());
                        n = i;
                        if ((i & 0x10) == 0) {
                          n = i;
                          j = i;
                          k = i;
                          m = i;
                          if (param1CodedInputStream.getBytesUntilLimit() > 0) {
                            j = i;
                            k = i;
                            m = i;
                            this.assignedPlaceIds_ = newLongList();
                            n = i | 0x10;
                          } 
                        } 
                        while (true) {
                          j = n;
                          k = n;
                          m = n;
                          if (param1CodedInputStream.getBytesUntilLimit() > 0) {
                            j = n;
                            k = n;
                            m = n;
                            this.assignedPlaceIds_.addLong(param1CodedInputStream.readInt64());
                            continue;
                          } 
                          j = n;
                          k = n;
                          m = n;
                          break;
                        } 
                        param1CodedInputStream.popLimit(i2);
                        i = n;
                        continue;
                      } 
                      n = i;
                      if ((i & 0x10) == 0) {
                        j = i;
                        k = i;
                        m = i;
                        this.assignedPlaceIds_ = newLongList();
                        n = i | 0x10;
                      } 
                      j = n;
                      k = n;
                      m = n;
                      this.assignedPlaceIds_.addLong(param1CodedInputStream.readInt64());
                      i = n;
                      continue;
                    } 
                    j = i;
                    k = i;
                    m = i;
                    int i1 = param1CodedInputStream.pushLimit(param1CodedInputStream.readRawVarint32());
                    n = i;
                    if ((i & 0x8) == 0) {
                      n = i;
                      j = i;
                      k = i;
                      m = i;
                      if (param1CodedInputStream.getBytesUntilLimit() > 0) {
                        j = i;
                        k = i;
                        m = i;
                        this.assignedDevicesIds_ = newLongList();
                        n = i | 0x8;
                      } 
                    } 
                    while (true) {
                      j = n;
                      k = n;
                      m = n;
                      if (param1CodedInputStream.getBytesUntilLimit() > 0) {
                        j = n;
                        k = n;
                        m = n;
                        this.assignedDevicesIds_.addLong(param1CodedInputStream.readInt64());
                        continue;
                      } 
                      j = n;
                      k = n;
                      m = n;
                      break;
                    } 
                    param1CodedInputStream.popLimit(i1);
                    i = n;
                    continue;
                  } 
                  n = i;
                  if ((i & 0x8) == 0) {
                    j = i;
                    k = i;
                    m = i;
                    this.assignedDevicesIds_ = newLongList();
                    n = i | 0x8;
                  } 
                  j = n;
                  k = n;
                  m = n;
                  this.assignedDevicesIds_.addLong(param1CodedInputStream.readInt64());
                  i = n;
                  continue;
                } 
                j = i;
                k = i;
                m = i;
                this.bitField0_ |= 0x4;
                j = i;
                k = i;
                m = i;
                this.icon_ = param1CodedInputStream.readInt32();
                continue;
              } 
              j = i;
              k = i;
              m = i;
              ByteString byteString = param1CodedInputStream.readBytes();
              j = i;
              k = i;
              m = i;
              this.bitField0_ |= 0x2;
              j = i;
              k = i;
              m = i;
              this.name_ = byteString;
              continue;
            } 
            j = i;
            k = i;
            m = i;
            this.bitField0_ |= 0x1;
            j = i;
            k = i;
            m = i;
            this.id_ = param1CodedInputStream.readInt64();
            continue;
          } 
          continue;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          j = m;
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } catch (IOException iOException) {
          j = k;
          InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException();
          j = k;
          this(iOException);
          j = k;
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } finally {}
        if ((j & 0x8) != 0)
          this.assignedDevicesIds_.makeImmutable(); 
        if ((j & 0x10) != 0)
          this.assignedPlaceIds_.makeImmutable(); 
        this.unknownFields = builder.build();
        makeExtensionsImmutable();
        throw param1CodedInputStream;
        bool = true;
      } 
      if ((i & 0x8) != 0)
        this.assignedDevicesIds_.makeImmutable(); 
      if ((i & 0x10) != 0)
        this.assignedPlaceIds_.makeImmutable(); 
      this.unknownFields = builder.build();
      makeExtensionsImmutable();
    }
    
    private DeviceGroup(GeneratedMessageV3.Builder<?> param1Builder) {
      super(param1Builder);
    }
    
    public static DeviceGroup getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_DeviceGroup_descriptor;
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(DeviceGroup param1DeviceGroup) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(param1DeviceGroup);
    }
    
    public static DeviceGroup parseDelimitedFrom(InputStream param1InputStream) throws IOException {
      return (DeviceGroup)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream);
    }
    
    public static DeviceGroup parseDelimitedFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (DeviceGroup)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static DeviceGroup parseFrom(ByteString param1ByteString) throws InvalidProtocolBufferException {
      return (DeviceGroup)PARSER.parseFrom(param1ByteString);
    }
    
    public static DeviceGroup parseFrom(ByteString param1ByteString, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (DeviceGroup)PARSER.parseFrom(param1ByteString, param1ExtensionRegistryLite);
    }
    
    public static DeviceGroup parseFrom(CodedInputStream param1CodedInputStream) throws IOException {
      return (DeviceGroup)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream);
    }
    
    public static DeviceGroup parseFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (DeviceGroup)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream, param1ExtensionRegistryLite);
    }
    
    public static DeviceGroup parseFrom(InputStream param1InputStream) throws IOException {
      return (DeviceGroup)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream);
    }
    
    public static DeviceGroup parseFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (DeviceGroup)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static DeviceGroup parseFrom(ByteBuffer param1ByteBuffer) throws InvalidProtocolBufferException {
      return (DeviceGroup)PARSER.parseFrom(param1ByteBuffer);
    }
    
    public static DeviceGroup parseFrom(ByteBuffer param1ByteBuffer, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (DeviceGroup)PARSER.parseFrom(param1ByteBuffer, param1ExtensionRegistryLite);
    }
    
    public static DeviceGroup parseFrom(byte[] param1ArrayOfbyte) throws InvalidProtocolBufferException {
      return (DeviceGroup)PARSER.parseFrom(param1ArrayOfbyte);
    }
    
    public static DeviceGroup parseFrom(byte[] param1ArrayOfbyte, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (DeviceGroup)PARSER.parseFrom(param1ArrayOfbyte, param1ExtensionRegistryLite);
    }
    
    public static Parser<DeviceGroup> parser() {
      return PARSER;
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object == this)
        return true; 
      if (!(param1Object instanceof DeviceGroup))
        return super.equals(param1Object); 
      param1Object = param1Object;
      return (hasId() != param1Object.hasId()) ? false : ((hasId() && getId() != param1Object.getId()) ? false : ((hasName() != param1Object.hasName()) ? false : ((hasName() && !getName().equals(param1Object.getName())) ? false : ((hasIcon() != param1Object.hasIcon()) ? false : ((hasIcon() && getIcon() != param1Object.getIcon()) ? false : (!getAssignedDevicesIdsList().equals(param1Object.getAssignedDevicesIdsList()) ? false : (!getAssignedPlaceIdsList().equals(param1Object.getAssignedPlaceIdsList()) ? false : ((hasFavourite() != param1Object.hasFavourite()) ? false : ((hasFavourite() && getFavourite() != param1Object.getFavourite()) ? false : (!!this.unknownFields.equals(((DeviceGroup)param1Object).unknownFields)))))))))));
    }
    
    public long getAssignedDevicesIds(int param1Int) {
      return this.assignedDevicesIds_.getLong(param1Int);
    }
    
    public int getAssignedDevicesIdsCount() {
      return this.assignedDevicesIds_.size();
    }
    
    public List<Long> getAssignedDevicesIdsList() {
      return (List<Long>)this.assignedDevicesIds_;
    }
    
    public long getAssignedPlaceIds(int param1Int) {
      return this.assignedPlaceIds_.getLong(param1Int);
    }
    
    public int getAssignedPlaceIdsCount() {
      return this.assignedPlaceIds_.size();
    }
    
    public List<Long> getAssignedPlaceIdsList() {
      return (List<Long>)this.assignedPlaceIds_;
    }
    
    public DeviceGroup getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
    
    public boolean getFavourite() {
      return this.favourite_;
    }
    
    public int getIcon() {
      return this.icon_;
    }
    
    public long getId() {
      return this.id_;
    }
    
    public String getName() {
      Object object = this.name_;
      if (object instanceof String)
        return (String)object; 
      object = object;
      String str = object.toStringUtf8();
      if (object.isValidUtf8())
        this.name_ = str; 
      return str;
    }
    
    public ByteString getNameBytes() {
      Object object = this.name_;
      if (object instanceof String) {
        object = ByteString.copyFromUtf8((String)object);
        this.name_ = object;
        return (ByteString)object;
      } 
      return (ByteString)object;
    }
    
    public Parser<DeviceGroup> getParserForType() {
      return PARSER;
    }
    
    public int getSerializedSize() {
      int i = this.memoizedSize;
      if (i != -1)
        return i; 
      i = this.bitField0_;
      boolean bool = false;
      if ((i & 0x1) != 0) {
        j = CodedOutputStream.computeInt64Size(1, this.id_) + 0;
      } else {
        j = 0;
      } 
      i = j;
      if ((this.bitField0_ & 0x2) != 0)
        i = j + GeneratedMessageV3.computeStringSize(2, this.name_); 
      int j = i;
      if ((this.bitField0_ & 0x4) != 0)
        j = i + CodedOutputStream.computeInt32Size(3, this.icon_); 
      byte b = 0;
      i = b;
      while (b < this.assignedDevicesIds_.size()) {
        i += CodedOutputStream.computeInt64SizeNoTag(this.assignedDevicesIds_.getLong(b));
        b++;
      } 
      int k = getAssignedDevicesIdsList().size();
      int m = 0;
      for (b = bool; b < this.assignedPlaceIds_.size(); b++)
        m += CodedOutputStream.computeInt64SizeNoTag(this.assignedPlaceIds_.getLong(b)); 
      j = j + i + k * 1 + m + getAssignedPlaceIdsList().size() * 1;
      i = j;
      if ((this.bitField0_ & 0x8) != 0)
        i = j + CodedOutputStream.computeBoolSize(6, this.favourite_); 
      i += this.unknownFields.getSerializedSize();
      this.memoizedSize = i;
      return i;
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    public boolean hasFavourite() {
      boolean bool;
      if ((this.bitField0_ & 0x8) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasIcon() {
      boolean bool;
      if ((this.bitField0_ & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasId() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean hasName() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int hashCode() {
      if (this.memoizedHashCode != 0)
        return this.memoizedHashCode; 
      int i = 779 + getDescriptor().hashCode();
      int j = i;
      if (hasId())
        j = (i * 37 + 1) * 53 + Internal.hashLong(getId()); 
      i = j;
      if (hasName())
        i = (j * 37 + 2) * 53 + getName().hashCode(); 
      int k = i;
      if (hasIcon())
        k = (i * 37 + 3) * 53 + getIcon(); 
      j = k;
      if (getAssignedDevicesIdsCount() > 0)
        j = (k * 37 + 4) * 53 + getAssignedDevicesIdsList().hashCode(); 
      i = j;
      if (getAssignedPlaceIdsCount() > 0)
        i = (j * 37 + 5) * 53 + getAssignedPlaceIdsList().hashCode(); 
      j = i;
      if (hasFavourite())
        j = (i * 37 + 6) * 53 + Internal.hashBoolean(getFavourite()); 
      j = j * 29 + this.unknownFields.hashCode();
      this.memoizedHashCode = j;
      return j;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_DeviceGroup_fieldAccessorTable.ensureFieldAccessorsInitialized(DeviceGroup.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      byte b = this.memoizedIsInitialized;
      if (b == 1)
        return true; 
      if (b == 0)
        return false; 
      this.memoizedIsInitialized = (byte)1;
      return true;
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      return new Builder(param1BuilderParent);
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter param1UnusedPrivateParameter) {
      return new DeviceGroup();
    }
    
    public Builder toBuilder() {
      Builder builder;
      if (this == DEFAULT_INSTANCE) {
        builder = new Builder();
      } else {
        builder = (new Builder()).mergeFrom(this);
      } 
      return builder;
    }
    
    public void writeTo(CodedOutputStream param1CodedOutputStream) throws IOException {
      byte b3;
      if ((this.bitField0_ & 0x1) != 0)
        param1CodedOutputStream.writeInt64(1, this.id_); 
      if ((this.bitField0_ & 0x2) != 0)
        GeneratedMessageV3.writeString(param1CodedOutputStream, 2, this.name_); 
      if ((this.bitField0_ & 0x4) != 0)
        param1CodedOutputStream.writeInt32(3, this.icon_); 
      byte b1 = 0;
      byte b2 = 0;
      while (true) {
        b3 = b1;
        if (b2 < this.assignedDevicesIds_.size()) {
          param1CodedOutputStream.writeInt64(4, this.assignedDevicesIds_.getLong(b2));
          b2++;
          continue;
        } 
        break;
      } 
      while (b3 < this.assignedPlaceIds_.size()) {
        param1CodedOutputStream.writeInt64(5, this.assignedPlaceIds_.getLong(b3));
        b3++;
      } 
      if ((this.bitField0_ & 0x8) != 0)
        param1CodedOutputStream.writeBool(6, this.favourite_); 
      this.unknownFields.writeTo(param1CodedOutputStream);
    }
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MobilusModel.DeviceGroupOrBuilder {
      private Internal.LongList assignedDevicesIds_ = MobilusModel.DeviceGroup.emptyLongList();
      
      private Internal.LongList assignedPlaceIds_ = MobilusModel.DeviceGroup.emptyLongList();
      
      private int bitField0_;
      
      private boolean favourite_;
      
      private int icon_;
      
      private long id_;
      
      private Object name_ = "";
      
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent param2BuilderParent) {
        super(param2BuilderParent);
        maybeForceBuilderInitialization();
      }
      
      private void ensureAssignedDevicesIdsIsMutable() {
        if ((this.bitField0_ & 0x8) == 0) {
          this.assignedDevicesIds_ = MobilusModel.DeviceGroup.mutableCopy(this.assignedDevicesIds_);
          this.bitField0_ |= 0x8;
        } 
      }
      
      private void ensureAssignedPlaceIdsIsMutable() {
        if ((this.bitField0_ & 0x10) == 0) {
          this.assignedPlaceIds_ = MobilusModel.DeviceGroup.mutableCopy(this.assignedPlaceIds_);
          this.bitField0_ |= 0x10;
        } 
      }
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MobilusModel.internal_static_DeviceGroup_descriptor;
      }
      
      private void maybeForceBuilderInitialization() {
        MobilusModel.DeviceGroup.alwaysUseFieldBuilders;
      }
      
      public Builder addAllAssignedDevicesIds(Iterable<? extends Long> param2Iterable) {
        ensureAssignedDevicesIdsIsMutable();
        AbstractMessageLite.Builder.addAll(param2Iterable, (List)this.assignedDevicesIds_);
        onChanged();
        return this;
      }
      
      public Builder addAllAssignedPlaceIds(Iterable<? extends Long> param2Iterable) {
        ensureAssignedPlaceIdsIsMutable();
        AbstractMessageLite.Builder.addAll(param2Iterable, (List)this.assignedPlaceIds_);
        onChanged();
        return this;
      }
      
      public Builder addAssignedDevicesIds(long param2Long) {
        ensureAssignedDevicesIdsIsMutable();
        this.assignedDevicesIds_.addLong(param2Long);
        onChanged();
        return this;
      }
      
      public Builder addAssignedPlaceIds(long param2Long) {
        ensureAssignedPlaceIdsIsMutable();
        this.assignedPlaceIds_.addLong(param2Long);
        onChanged();
        return this;
      }
      
      public Builder addRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.addRepeatedField(param2FieldDescriptor, param2Object);
      }
      
      public MobilusModel.DeviceGroup build() {
        MobilusModel.DeviceGroup deviceGroup = buildPartial();
        if (!deviceGroup.isInitialized())
          throw newUninitializedMessageException(deviceGroup); 
        return deviceGroup;
      }
      
      public MobilusModel.DeviceGroup buildPartial() {
        MobilusModel.DeviceGroup deviceGroup = new MobilusModel.DeviceGroup(this);
        int i = this.bitField0_;
        if ((i & 0x1) != 0) {
          MobilusModel.DeviceGroup.access$7302(deviceGroup, this.id_);
          j = 1;
        } else {
          j = 0;
        } 
        int k = j;
        if ((i & 0x2) != 0)
          k = j | 0x2; 
        MobilusModel.DeviceGroup.access$7402(deviceGroup, this.name_);
        int j = k;
        if ((i & 0x4) != 0) {
          MobilusModel.DeviceGroup.access$7502(deviceGroup, this.icon_);
          j = k | 0x4;
        } 
        if ((this.bitField0_ & 0x8) != 0) {
          this.assignedDevicesIds_.makeImmutable();
          this.bitField0_ &= 0xFFFFFFF7;
        } 
        MobilusModel.DeviceGroup.access$7602(deviceGroup, this.assignedDevicesIds_);
        if ((this.bitField0_ & 0x10) != 0) {
          this.assignedPlaceIds_.makeImmutable();
          this.bitField0_ &= 0xFFFFFFEF;
        } 
        MobilusModel.DeviceGroup.access$7702(deviceGroup, this.assignedPlaceIds_);
        k = j;
        if ((i & 0x20) != 0) {
          MobilusModel.DeviceGroup.access$7802(deviceGroup, this.favourite_);
          k = j | 0x8;
        } 
        MobilusModel.DeviceGroup.access$7902(deviceGroup, k);
        onBuilt();
        return deviceGroup;
      }
      
      public Builder clear() {
        super.clear();
        this.id_ = 0L;
        this.bitField0_ &= 0xFFFFFFFE;
        this.name_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        this.icon_ = 0;
        this.bitField0_ &= 0xFFFFFFFB;
        this.assignedDevicesIds_ = MobilusModel.DeviceGroup.emptyLongList();
        this.bitField0_ &= 0xFFFFFFF7;
        this.assignedPlaceIds_ = MobilusModel.DeviceGroup.emptyLongList();
        this.bitField0_ &= 0xFFFFFFEF;
        this.favourite_ = false;
        this.bitField0_ &= 0xFFFFFFDF;
        return this;
      }
      
      public Builder clearAssignedDevicesIds() {
        this.assignedDevicesIds_ = MobilusModel.DeviceGroup.emptyLongList();
        this.bitField0_ &= 0xFFFFFFF7;
        onChanged();
        return this;
      }
      
      public Builder clearAssignedPlaceIds() {
        this.assignedPlaceIds_ = MobilusModel.DeviceGroup.emptyLongList();
        this.bitField0_ &= 0xFFFFFFEF;
        onChanged();
        return this;
      }
      
      public Builder clearFavourite() {
        this.bitField0_ &= 0xFFFFFFDF;
        this.favourite_ = false;
        onChanged();
        return this;
      }
      
      public Builder clearField(Descriptors.FieldDescriptor param2FieldDescriptor) {
        return (Builder)super.clearField(param2FieldDescriptor);
      }
      
      public Builder clearIcon() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.icon_ = 0;
        onChanged();
        return this;
      }
      
      public Builder clearId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.id_ = 0L;
        onChanged();
        return this;
      }
      
      public Builder clearName() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.name_ = MobilusModel.DeviceGroup.getDefaultInstance().getName();
        onChanged();
        return this;
      }
      
      public Builder clearOneof(Descriptors.OneofDescriptor param2OneofDescriptor) {
        return (Builder)super.clearOneof(param2OneofDescriptor);
      }
      
      public Builder clone() {
        return (Builder)super.clone();
      }
      
      public long getAssignedDevicesIds(int param2Int) {
        return this.assignedDevicesIds_.getLong(param2Int);
      }
      
      public int getAssignedDevicesIdsCount() {
        return this.assignedDevicesIds_.size();
      }
      
      public List<Long> getAssignedDevicesIdsList() {
        Internal.LongList longList;
        if ((this.bitField0_ & 0x8) != 0) {
          List<?> list = Collections.unmodifiableList((List<?>)this.assignedDevicesIds_);
        } else {
          longList = this.assignedDevicesIds_;
        } 
        return (List<Long>)longList;
      }
      
      public long getAssignedPlaceIds(int param2Int) {
        return this.assignedPlaceIds_.getLong(param2Int);
      }
      
      public int getAssignedPlaceIdsCount() {
        return this.assignedPlaceIds_.size();
      }
      
      public List<Long> getAssignedPlaceIdsList() {
        Internal.LongList longList;
        if ((this.bitField0_ & 0x10) != 0) {
          List<?> list = Collections.unmodifiableList((List<?>)this.assignedPlaceIds_);
        } else {
          longList = this.assignedPlaceIds_;
        } 
        return (List<Long>)longList;
      }
      
      public MobilusModel.DeviceGroup getDefaultInstanceForType() {
        return MobilusModel.DeviceGroup.getDefaultInstance();
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MobilusModel.internal_static_DeviceGroup_descriptor;
      }
      
      public boolean getFavourite() {
        return this.favourite_;
      }
      
      public int getIcon() {
        return this.icon_;
      }
      
      public long getId() {
        return this.id_;
      }
      
      public String getName() {
        Object object = this.name_;
        if (!(object instanceof String)) {
          object = object;
          String str = object.toStringUtf8();
          if (object.isValidUtf8())
            this.name_ = str; 
          return str;
        } 
        return (String)object;
      }
      
      public ByteString getNameBytes() {
        Object object = this.name_;
        if (object instanceof String) {
          object = ByteString.copyFromUtf8((String)object);
          this.name_ = object;
          return (ByteString)object;
        } 
        return (ByteString)object;
      }
      
      public boolean hasFavourite() {
        boolean bool;
        if ((this.bitField0_ & 0x20) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasIcon() {
        boolean bool;
        if ((this.bitField0_ & 0x4) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasId() {
        int i = this.bitField0_;
        boolean bool = true;
        if ((i & 0x1) == 0)
          bool = false; 
        return bool;
      }
      
      public boolean hasName() {
        boolean bool;
        if ((this.bitField0_ & 0x2) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MobilusModel.internal_static_DeviceGroup_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.DeviceGroup.class, Builder.class);
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws IOException {
        ExtensionRegistryLite extensionRegistryLite = null;
        try {
          MobilusModel.DeviceGroup deviceGroup = (MobilusModel.DeviceGroup)MobilusModel.DeviceGroup.PARSER.parsePartialFrom(param2CodedInputStream, param2ExtensionRegistryLite);
          return this;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          MobilusModel.DeviceGroup deviceGroup = (MobilusModel.DeviceGroup)invalidProtocolBufferException.getUnfinishedMessage();
        } finally {
          param2CodedInputStream = null;
        } 
        if (param2ExtensionRegistryLite != null)
          mergeFrom((MobilusModel.DeviceGroup)param2ExtensionRegistryLite); 
        throw param2CodedInputStream;
      }
      
      public Builder mergeFrom(Message param2Message) {
        if (param2Message instanceof MobilusModel.DeviceGroup)
          return mergeFrom((MobilusModel.DeviceGroup)param2Message); 
        super.mergeFrom(param2Message);
        return this;
      }
      
      public Builder mergeFrom(MobilusModel.DeviceGroup param2DeviceGroup) {
        if (param2DeviceGroup == MobilusModel.DeviceGroup.getDefaultInstance())
          return this; 
        if (param2DeviceGroup.hasId())
          setId(param2DeviceGroup.getId()); 
        if (param2DeviceGroup.hasName()) {
          this.bitField0_ |= 0x2;
          this.name_ = param2DeviceGroup.name_;
          onChanged();
        } 
        if (param2DeviceGroup.hasIcon())
          setIcon(param2DeviceGroup.getIcon()); 
        if (!param2DeviceGroup.assignedDevicesIds_.isEmpty()) {
          if (this.assignedDevicesIds_.isEmpty()) {
            this.assignedDevicesIds_ = param2DeviceGroup.assignedDevicesIds_;
            this.bitField0_ &= 0xFFFFFFF7;
          } else {
            ensureAssignedDevicesIdsIsMutable();
            this.assignedDevicesIds_.addAll((Collection)param2DeviceGroup.assignedDevicesIds_);
          } 
          onChanged();
        } 
        if (!param2DeviceGroup.assignedPlaceIds_.isEmpty()) {
          if (this.assignedPlaceIds_.isEmpty()) {
            this.assignedPlaceIds_ = param2DeviceGroup.assignedPlaceIds_;
            this.bitField0_ &= 0xFFFFFFEF;
          } else {
            ensureAssignedPlaceIdsIsMutable();
            this.assignedPlaceIds_.addAll((Collection)param2DeviceGroup.assignedPlaceIds_);
          } 
          onChanged();
        } 
        if (param2DeviceGroup.hasFavourite())
          setFavourite(param2DeviceGroup.getFavourite()); 
        mergeUnknownFields(param2DeviceGroup.unknownFields);
        onChanged();
        return this;
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.mergeUnknownFields(param2UnknownFieldSet);
      }
      
      public Builder setAssignedDevicesIds(int param2Int, long param2Long) {
        ensureAssignedDevicesIdsIsMutable();
        this.assignedDevicesIds_.setLong(param2Int, param2Long);
        onChanged();
        return this;
      }
      
      public Builder setAssignedPlaceIds(int param2Int, long param2Long) {
        ensureAssignedPlaceIdsIsMutable();
        this.assignedPlaceIds_.setLong(param2Int, param2Long);
        onChanged();
        return this;
      }
      
      public Builder setFavourite(boolean param2Boolean) {
        this.bitField0_ |= 0x20;
        this.favourite_ = param2Boolean;
        onChanged();
        return this;
      }
      
      public Builder setField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.setField(param2FieldDescriptor, param2Object);
      }
      
      public Builder setIcon(int param2Int) {
        this.bitField0_ |= 0x4;
        this.icon_ = param2Int;
        onChanged();
        return this;
      }
      
      public Builder setId(long param2Long) {
        this.bitField0_ |= 0x1;
        this.id_ = param2Long;
        onChanged();
        return this;
      }
      
      public Builder setName(String param2String) {
        if (param2String == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.name_ = param2String;
        onChanged();
        return this;
      }
      
      public Builder setNameBytes(ByteString param2ByteString) {
        if (param2ByteString == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.name_ = param2ByteString;
        onChanged();
        return this;
      }
      
      public Builder setRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, int param2Int, Object param2Object) {
        return (Builder)super.setRepeatedField(param2FieldDescriptor, param2Int, param2Object);
      }
      
      public final Builder setUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.setUnknownFields(param2UnknownFieldSet);
      }
    }
  }
  
  static final class null extends AbstractParser<DeviceGroup> {
    public MobilusModel.DeviceGroup parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return new MobilusModel.DeviceGroup(param1CodedInputStream, param1ExtensionRegistryLite);
    }
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<DeviceGroup.Builder> implements DeviceGroupOrBuilder {
    private Internal.LongList assignedDevicesIds_ = MobilusModel.DeviceGroup.emptyLongList();
    
    private Internal.LongList assignedPlaceIds_ = MobilusModel.DeviceGroup.emptyLongList();
    
    private int bitField0_;
    
    private boolean favourite_;
    
    private int icon_;
    
    private long id_;
    
    private Object name_ = "";
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      super(param1BuilderParent);
      maybeForceBuilderInitialization();
    }
    
    private void ensureAssignedDevicesIdsIsMutable() {
      if ((this.bitField0_ & 0x8) == 0) {
        this.assignedDevicesIds_ = MobilusModel.DeviceGroup.mutableCopy(this.assignedDevicesIds_);
        this.bitField0_ |= 0x8;
      } 
    }
    
    private void ensureAssignedPlaceIdsIsMutable() {
      if ((this.bitField0_ & 0x10) == 0) {
        this.assignedPlaceIds_ = MobilusModel.DeviceGroup.mutableCopy(this.assignedPlaceIds_);
        this.bitField0_ |= 0x10;
      } 
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_DeviceGroup_descriptor;
    }
    
    private void maybeForceBuilderInitialization() {
      MobilusModel.DeviceGroup.alwaysUseFieldBuilders;
    }
    
    public Builder addAllAssignedDevicesIds(Iterable<? extends Long> param1Iterable) {
      ensureAssignedDevicesIdsIsMutable();
      AbstractMessageLite.Builder.addAll(param1Iterable, (List)this.assignedDevicesIds_);
      onChanged();
      return this;
    }
    
    public Builder addAllAssignedPlaceIds(Iterable<? extends Long> param1Iterable) {
      ensureAssignedPlaceIdsIsMutable();
      AbstractMessageLite.Builder.addAll(param1Iterable, (List)this.assignedPlaceIds_);
      onChanged();
      return this;
    }
    
    public Builder addAssignedDevicesIds(long param1Long) {
      ensureAssignedDevicesIdsIsMutable();
      this.assignedDevicesIds_.addLong(param1Long);
      onChanged();
      return this;
    }
    
    public Builder addAssignedPlaceIds(long param1Long) {
      ensureAssignedPlaceIdsIsMutable();
      this.assignedPlaceIds_.addLong(param1Long);
      onChanged();
      return this;
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.addRepeatedField(param1FieldDescriptor, param1Object);
    }
    
    public MobilusModel.DeviceGroup build() {
      MobilusModel.DeviceGroup deviceGroup = buildPartial();
      if (!deviceGroup.isInitialized())
        throw newUninitializedMessageException(deviceGroup); 
      return deviceGroup;
    }
    
    public MobilusModel.DeviceGroup buildPartial() {
      MobilusModel.DeviceGroup deviceGroup = new MobilusModel.DeviceGroup(this);
      int i = this.bitField0_;
      if ((i & 0x1) != 0) {
        MobilusModel.DeviceGroup.access$7302(deviceGroup, this.id_);
        j = 1;
      } else {
        j = 0;
      } 
      int k = j;
      if ((i & 0x2) != 0)
        k = j | 0x2; 
      MobilusModel.DeviceGroup.access$7402(deviceGroup, this.name_);
      int j = k;
      if ((i & 0x4) != 0) {
        MobilusModel.DeviceGroup.access$7502(deviceGroup, this.icon_);
        j = k | 0x4;
      } 
      if ((this.bitField0_ & 0x8) != 0) {
        this.assignedDevicesIds_.makeImmutable();
        this.bitField0_ &= 0xFFFFFFF7;
      } 
      MobilusModel.DeviceGroup.access$7602(deviceGroup, this.assignedDevicesIds_);
      if ((this.bitField0_ & 0x10) != 0) {
        this.assignedPlaceIds_.makeImmutable();
        this.bitField0_ &= 0xFFFFFFEF;
      } 
      MobilusModel.DeviceGroup.access$7702(deviceGroup, this.assignedPlaceIds_);
      k = j;
      if ((i & 0x20) != 0) {
        MobilusModel.DeviceGroup.access$7802(deviceGroup, this.favourite_);
        k = j | 0x8;
      } 
      MobilusModel.DeviceGroup.access$7902(deviceGroup, k);
      onBuilt();
      return deviceGroup;
    }
    
    public Builder clear() {
      super.clear();
      this.id_ = 0L;
      this.bitField0_ &= 0xFFFFFFFE;
      this.name_ = "";
      this.bitField0_ &= 0xFFFFFFFD;
      this.icon_ = 0;
      this.bitField0_ &= 0xFFFFFFFB;
      this.assignedDevicesIds_ = MobilusModel.DeviceGroup.emptyLongList();
      this.bitField0_ &= 0xFFFFFFF7;
      this.assignedPlaceIds_ = MobilusModel.DeviceGroup.emptyLongList();
      this.bitField0_ &= 0xFFFFFFEF;
      this.favourite_ = false;
      this.bitField0_ &= 0xFFFFFFDF;
      return this;
    }
    
    public Builder clearAssignedDevicesIds() {
      this.assignedDevicesIds_ = MobilusModel.DeviceGroup.emptyLongList();
      this.bitField0_ &= 0xFFFFFFF7;
      onChanged();
      return this;
    }
    
    public Builder clearAssignedPlaceIds() {
      this.assignedPlaceIds_ = MobilusModel.DeviceGroup.emptyLongList();
      this.bitField0_ &= 0xFFFFFFEF;
      onChanged();
      return this;
    }
    
    public Builder clearFavourite() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.favourite_ = false;
      onChanged();
      return this;
    }
    
    public Builder clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return (Builder)super.clearField(param1FieldDescriptor);
    }
    
    public Builder clearIcon() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.icon_ = 0;
      onChanged();
      return this;
    }
    
    public Builder clearId() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.id_ = 0L;
      onChanged();
      return this;
    }
    
    public Builder clearName() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.name_ = MobilusModel.DeviceGroup.getDefaultInstance().getName();
      onChanged();
      return this;
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return (Builder)super.clearOneof(param1OneofDescriptor);
    }
    
    public Builder clone() {
      return (Builder)super.clone();
    }
    
    public long getAssignedDevicesIds(int param1Int) {
      return this.assignedDevicesIds_.getLong(param1Int);
    }
    
    public int getAssignedDevicesIdsCount() {
      return this.assignedDevicesIds_.size();
    }
    
    public List<Long> getAssignedDevicesIdsList() {
      Internal.LongList longList;
      if ((this.bitField0_ & 0x8) != 0) {
        List<?> list = Collections.unmodifiableList((List<?>)this.assignedDevicesIds_);
      } else {
        longList = this.assignedDevicesIds_;
      } 
      return (List<Long>)longList;
    }
    
    public long getAssignedPlaceIds(int param1Int) {
      return this.assignedPlaceIds_.getLong(param1Int);
    }
    
    public int getAssignedPlaceIdsCount() {
      return this.assignedPlaceIds_.size();
    }
    
    public List<Long> getAssignedPlaceIdsList() {
      Internal.LongList longList;
      if ((this.bitField0_ & 0x10) != 0) {
        List<?> list = Collections.unmodifiableList((List<?>)this.assignedPlaceIds_);
      } else {
        longList = this.assignedPlaceIds_;
      } 
      return (List<Long>)longList;
    }
    
    public MobilusModel.DeviceGroup getDefaultInstanceForType() {
      return MobilusModel.DeviceGroup.getDefaultInstance();
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return MobilusModel.internal_static_DeviceGroup_descriptor;
    }
    
    public boolean getFavourite() {
      return this.favourite_;
    }
    
    public int getIcon() {
      return this.icon_;
    }
    
    public long getId() {
      return this.id_;
    }
    
    public String getName() {
      Object object = this.name_;
      if (!(object instanceof String)) {
        object = object;
        String str = object.toStringUtf8();
        if (object.isValidUtf8())
          this.name_ = str; 
        return str;
      } 
      return (String)object;
    }
    
    public ByteString getNameBytes() {
      Object object = this.name_;
      if (object instanceof String) {
        object = ByteString.copyFromUtf8((String)object);
        this.name_ = object;
        return (ByteString)object;
      } 
      return (ByteString)object;
    }
    
    public boolean hasFavourite() {
      boolean bool;
      if ((this.bitField0_ & 0x20) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasIcon() {
      boolean bool;
      if ((this.bitField0_ & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasId() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean hasName() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_DeviceGroup_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.DeviceGroup.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      ExtensionRegistryLite extensionRegistryLite = null;
      try {
        MobilusModel.DeviceGroup deviceGroup = (MobilusModel.DeviceGroup)MobilusModel.DeviceGroup.PARSER.parsePartialFrom(param1CodedInputStream, param1ExtensionRegistryLite);
        return this;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        MobilusModel.DeviceGroup deviceGroup = (MobilusModel.DeviceGroup)invalidProtocolBufferException.getUnfinishedMessage();
      } finally {
        param1CodedInputStream = null;
      } 
      if (param1ExtensionRegistryLite != null)
        mergeFrom((MobilusModel.DeviceGroup)param1ExtensionRegistryLite); 
      throw param1CodedInputStream;
    }
    
    public Builder mergeFrom(Message param1Message) {
      if (param1Message instanceof MobilusModel.DeviceGroup)
        return mergeFrom((MobilusModel.DeviceGroup)param1Message); 
      super.mergeFrom(param1Message);
      return this;
    }
    
    public Builder mergeFrom(MobilusModel.DeviceGroup param1DeviceGroup) {
      if (param1DeviceGroup == MobilusModel.DeviceGroup.getDefaultInstance())
        return this; 
      if (param1DeviceGroup.hasId())
        setId(param1DeviceGroup.getId()); 
      if (param1DeviceGroup.hasName()) {
        this.bitField0_ |= 0x2;
        this.name_ = param1DeviceGroup.name_;
        onChanged();
      } 
      if (param1DeviceGroup.hasIcon())
        setIcon(param1DeviceGroup.getIcon()); 
      if (!param1DeviceGroup.assignedDevicesIds_.isEmpty()) {
        if (this.assignedDevicesIds_.isEmpty()) {
          this.assignedDevicesIds_ = param1DeviceGroup.assignedDevicesIds_;
          this.bitField0_ &= 0xFFFFFFF7;
        } else {
          ensureAssignedDevicesIdsIsMutable();
          this.assignedDevicesIds_.addAll((Collection)param1DeviceGroup.assignedDevicesIds_);
        } 
        onChanged();
      } 
      if (!param1DeviceGroup.assignedPlaceIds_.isEmpty()) {
        if (this.assignedPlaceIds_.isEmpty()) {
          this.assignedPlaceIds_ = param1DeviceGroup.assignedPlaceIds_;
          this.bitField0_ &= 0xFFFFFFEF;
        } else {
          ensureAssignedPlaceIdsIsMutable();
          this.assignedPlaceIds_.addAll((Collection)param1DeviceGroup.assignedPlaceIds_);
        } 
        onChanged();
      } 
      if (param1DeviceGroup.hasFavourite())
        setFavourite(param1DeviceGroup.getFavourite()); 
      mergeUnknownFields(param1DeviceGroup.unknownFields);
      onChanged();
      return this;
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.mergeUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder setAssignedDevicesIds(int param1Int, long param1Long) {
      ensureAssignedDevicesIdsIsMutable();
      this.assignedDevicesIds_.setLong(param1Int, param1Long);
      onChanged();
      return this;
    }
    
    public Builder setAssignedPlaceIds(int param1Int, long param1Long) {
      ensureAssignedPlaceIdsIsMutable();
      this.assignedPlaceIds_.setLong(param1Int, param1Long);
      onChanged();
      return this;
    }
    
    public Builder setFavourite(boolean param1Boolean) {
      this.bitField0_ |= 0x20;
      this.favourite_ = param1Boolean;
      onChanged();
      return this;
    }
    
    public Builder setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.setField(param1FieldDescriptor, param1Object);
    }
    
    public Builder setIcon(int param1Int) {
      this.bitField0_ |= 0x4;
      this.icon_ = param1Int;
      onChanged();
      return this;
    }
    
    public Builder setId(long param1Long) {
      this.bitField0_ |= 0x1;
      this.id_ = param1Long;
      onChanged();
      return this;
    }
    
    public Builder setName(String param1String) {
      if (param1String == null)
        throw new NullPointerException(); 
      this.bitField0_ |= 0x2;
      this.name_ = param1String;
      onChanged();
      return this;
    }
    
    public Builder setNameBytes(ByteString param1ByteString) {
      if (param1ByteString == null)
        throw new NullPointerException(); 
      this.bitField0_ |= 0x2;
      this.name_ = param1ByteString;
      onChanged();
      return this;
    }
    
    public Builder setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object) {
      return (Builder)super.setRepeatedField(param1FieldDescriptor, param1Int, param1Object);
    }
    
    public final Builder setUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.setUnknownFields(param1UnknownFieldSet);
    }
  }
  
  public static interface DeviceGroupOrBuilder extends MessageOrBuilder {
    long getAssignedDevicesIds(int param1Int);
    
    int getAssignedDevicesIdsCount();
    
    List<Long> getAssignedDevicesIdsList();
    
    long getAssignedPlaceIds(int param1Int);
    
    int getAssignedPlaceIdsCount();
    
    List<Long> getAssignedPlaceIdsList();
    
    boolean getFavourite();
    
    int getIcon();
    
    long getId();
    
    String getName();
    
    ByteString getNameBytes();
    
    boolean hasFavourite();
    
    boolean hasIcon();
    
    boolean hasId();
    
    boolean hasName();
  }
  
  public static interface DeviceOrBuilder extends MessageOrBuilder {
    long getAssignedGroupIds(int param1Int);
    
    int getAssignedGroupIdsCount();
    
    List<Long> getAssignedGroupIdsList();
    
    long getAssignedPlaceIds(int param1Int);
    
    int getAssignedPlaceIdsCount();
    
    List<Long> getAssignedPlaceIdsList();
    
    boolean getFavourite();
    
    int getIcon();
    
    long getId();
    
    long getInserttime();
    
    String getName();
    
    ByteString getNameBytes();
    
    int getType();
    
    boolean hasFavourite();
    
    boolean hasIcon();
    
    boolean hasId();
    
    boolean hasInserttime();
    
    boolean hasName();
    
    boolean hasType();
  }
  
  public static final class DevicePlace extends GeneratedMessageV3 implements DevicePlaceOrBuilder {
    public static final int ASSIGNED_DEVICES_IDS_FIELD_NUMBER = 4;
    
    public static final int ASSIGNED_GROUP_IDS_FIELD_NUMBER = 5;
    
    private static final DevicePlace DEFAULT_INSTANCE = new DevicePlace();
    
    public static final int ICON_FIELD_NUMBER = 3;
    
    public static final int ID_FIELD_NUMBER = 1;
    
    public static final int NAME_FIELD_NUMBER = 2;
    
    @Deprecated
    public static final Parser<DevicePlace> PARSER = (Parser<DevicePlace>)new AbstractParser<DevicePlace>() {
        public MobilusModel.DevicePlace parsePartialFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws InvalidProtocolBufferException {
          return new MobilusModel.DevicePlace(param2CodedInputStream, param2ExtensionRegistryLite);
        }
      };
    
    private static final long serialVersionUID = 0L;
    
    private Internal.LongList assignedDevicesIds_;
    
    private Internal.LongList assignedGroupIds_;
    
    private int bitField0_;
    
    private int icon_;
    
    private long id_;
    
    private byte memoizedIsInitialized = (byte)-1;
    
    private volatile Object name_;
    
    private DevicePlace() {
      this.name_ = "";
      this.assignedDevicesIds_ = emptyLongList();
      this.assignedGroupIds_ = emptyLongList();
    }
    
    private DevicePlace(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      this();
      if (param1ExtensionRegistryLite == null)
        throw new NullPointerException(); 
      UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
      boolean bool = false;
      int i = 0;
      while (!bool) {
        int j = i;
        int k = i;
        int m = i;
        try {
          int n = param1CodedInputStream.readTag();
          if (n != 0) {
            if (n != 8) {
              if (n != 18) {
                if (n != 24) {
                  if (n != 32) {
                    if (n != 34) {
                      if (n != 40) {
                        if (n != 42) {
                          j = i;
                          k = i;
                          m = i;
                          if (!parseUnknownField(param1CodedInputStream, builder, param1ExtensionRegistryLite, n))
                            continue; 
                          continue;
                        } 
                        j = i;
                        k = i;
                        m = i;
                        int i2 = param1CodedInputStream.pushLimit(param1CodedInputStream.readRawVarint32());
                        n = i;
                        if ((i & 0x10) == 0) {
                          n = i;
                          j = i;
                          k = i;
                          m = i;
                          if (param1CodedInputStream.getBytesUntilLimit() > 0) {
                            j = i;
                            k = i;
                            m = i;
                            this.assignedGroupIds_ = newLongList();
                            n = i | 0x10;
                          } 
                        } 
                        while (true) {
                          j = n;
                          k = n;
                          m = n;
                          if (param1CodedInputStream.getBytesUntilLimit() > 0) {
                            j = n;
                            k = n;
                            m = n;
                            this.assignedGroupIds_.addLong(param1CodedInputStream.readInt64());
                            continue;
                          } 
                          j = n;
                          k = n;
                          m = n;
                          break;
                        } 
                        param1CodedInputStream.popLimit(i2);
                        i = n;
                        continue;
                      } 
                      n = i;
                      if ((i & 0x10) == 0) {
                        j = i;
                        k = i;
                        m = i;
                        this.assignedGroupIds_ = newLongList();
                        n = i | 0x10;
                      } 
                      j = n;
                      k = n;
                      m = n;
                      this.assignedGroupIds_.addLong(param1CodedInputStream.readInt64());
                      i = n;
                      continue;
                    } 
                    j = i;
                    k = i;
                    m = i;
                    int i1 = param1CodedInputStream.pushLimit(param1CodedInputStream.readRawVarint32());
                    n = i;
                    if ((i & 0x8) == 0) {
                      n = i;
                      j = i;
                      k = i;
                      m = i;
                      if (param1CodedInputStream.getBytesUntilLimit() > 0) {
                        j = i;
                        k = i;
                        m = i;
                        this.assignedDevicesIds_ = newLongList();
                        n = i | 0x8;
                      } 
                    } 
                    while (true) {
                      j = n;
                      k = n;
                      m = n;
                      if (param1CodedInputStream.getBytesUntilLimit() > 0) {
                        j = n;
                        k = n;
                        m = n;
                        this.assignedDevicesIds_.addLong(param1CodedInputStream.readInt64());
                        continue;
                      } 
                      j = n;
                      k = n;
                      m = n;
                      break;
                    } 
                    param1CodedInputStream.popLimit(i1);
                    i = n;
                    continue;
                  } 
                  n = i;
                  if ((i & 0x8) == 0) {
                    j = i;
                    k = i;
                    m = i;
                    this.assignedDevicesIds_ = newLongList();
                    n = i | 0x8;
                  } 
                  j = n;
                  k = n;
                  m = n;
                  this.assignedDevicesIds_.addLong(param1CodedInputStream.readInt64());
                  i = n;
                  continue;
                } 
                j = i;
                k = i;
                m = i;
                this.bitField0_ |= 0x4;
                j = i;
                k = i;
                m = i;
                this.icon_ = param1CodedInputStream.readInt32();
                continue;
              } 
              j = i;
              k = i;
              m = i;
              ByteString byteString = param1CodedInputStream.readBytes();
              j = i;
              k = i;
              m = i;
              this.bitField0_ |= 0x2;
              j = i;
              k = i;
              m = i;
              this.name_ = byteString;
              continue;
            } 
            j = i;
            k = i;
            m = i;
            this.bitField0_ |= 0x1;
            j = i;
            k = i;
            m = i;
            this.id_ = param1CodedInputStream.readInt64();
            continue;
          } 
          continue;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          j = m;
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } catch (IOException iOException) {
          j = k;
          InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException();
          j = k;
          this(iOException);
          j = k;
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } finally {}
        if ((j & 0x8) != 0)
          this.assignedDevicesIds_.makeImmutable(); 
        if ((j & 0x10) != 0)
          this.assignedGroupIds_.makeImmutable(); 
        this.unknownFields = builder.build();
        makeExtensionsImmutable();
        throw param1CodedInputStream;
        bool = true;
      } 
      if ((i & 0x8) != 0)
        this.assignedDevicesIds_.makeImmutable(); 
      if ((i & 0x10) != 0)
        this.assignedGroupIds_.makeImmutable(); 
      this.unknownFields = builder.build();
      makeExtensionsImmutable();
    }
    
    private DevicePlace(GeneratedMessageV3.Builder<?> param1Builder) {
      super(param1Builder);
    }
    
    public static DevicePlace getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_DevicePlace_descriptor;
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(DevicePlace param1DevicePlace) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(param1DevicePlace);
    }
    
    public static DevicePlace parseDelimitedFrom(InputStream param1InputStream) throws IOException {
      return (DevicePlace)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream);
    }
    
    public static DevicePlace parseDelimitedFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (DevicePlace)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static DevicePlace parseFrom(ByteString param1ByteString) throws InvalidProtocolBufferException {
      return (DevicePlace)PARSER.parseFrom(param1ByteString);
    }
    
    public static DevicePlace parseFrom(ByteString param1ByteString, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (DevicePlace)PARSER.parseFrom(param1ByteString, param1ExtensionRegistryLite);
    }
    
    public static DevicePlace parseFrom(CodedInputStream param1CodedInputStream) throws IOException {
      return (DevicePlace)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream);
    }
    
    public static DevicePlace parseFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (DevicePlace)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream, param1ExtensionRegistryLite);
    }
    
    public static DevicePlace parseFrom(InputStream param1InputStream) throws IOException {
      return (DevicePlace)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream);
    }
    
    public static DevicePlace parseFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (DevicePlace)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static DevicePlace parseFrom(ByteBuffer param1ByteBuffer) throws InvalidProtocolBufferException {
      return (DevicePlace)PARSER.parseFrom(param1ByteBuffer);
    }
    
    public static DevicePlace parseFrom(ByteBuffer param1ByteBuffer, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (DevicePlace)PARSER.parseFrom(param1ByteBuffer, param1ExtensionRegistryLite);
    }
    
    public static DevicePlace parseFrom(byte[] param1ArrayOfbyte) throws InvalidProtocolBufferException {
      return (DevicePlace)PARSER.parseFrom(param1ArrayOfbyte);
    }
    
    public static DevicePlace parseFrom(byte[] param1ArrayOfbyte, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (DevicePlace)PARSER.parseFrom(param1ArrayOfbyte, param1ExtensionRegistryLite);
    }
    
    public static Parser<DevicePlace> parser() {
      return PARSER;
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object == this)
        return true; 
      if (!(param1Object instanceof DevicePlace))
        return super.equals(param1Object); 
      param1Object = param1Object;
      return (hasId() != param1Object.hasId()) ? false : ((hasId() && getId() != param1Object.getId()) ? false : ((hasName() != param1Object.hasName()) ? false : ((hasName() && !getName().equals(param1Object.getName())) ? false : ((hasIcon() != param1Object.hasIcon()) ? false : ((hasIcon() && getIcon() != param1Object.getIcon()) ? false : (!getAssignedDevicesIdsList().equals(param1Object.getAssignedDevicesIdsList()) ? false : (!getAssignedGroupIdsList().equals(param1Object.getAssignedGroupIdsList()) ? false : (!!this.unknownFields.equals(((DevicePlace)param1Object).unknownFields)))))))));
    }
    
    public long getAssignedDevicesIds(int param1Int) {
      return this.assignedDevicesIds_.getLong(param1Int);
    }
    
    public int getAssignedDevicesIdsCount() {
      return this.assignedDevicesIds_.size();
    }
    
    public List<Long> getAssignedDevicesIdsList() {
      return (List<Long>)this.assignedDevicesIds_;
    }
    
    public long getAssignedGroupIds(int param1Int) {
      return this.assignedGroupIds_.getLong(param1Int);
    }
    
    public int getAssignedGroupIdsCount() {
      return this.assignedGroupIds_.size();
    }
    
    public List<Long> getAssignedGroupIdsList() {
      return (List<Long>)this.assignedGroupIds_;
    }
    
    public DevicePlace getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
    
    public int getIcon() {
      return this.icon_;
    }
    
    public long getId() {
      return this.id_;
    }
    
    public String getName() {
      Object object = this.name_;
      if (object instanceof String)
        return (String)object; 
      ByteString byteString = (ByteString)object;
      object = byteString.toStringUtf8();
      if (byteString.isValidUtf8())
        this.name_ = object; 
      return (String)object;
    }
    
    public ByteString getNameBytes() {
      Object object = this.name_;
      if (object instanceof String) {
        object = ByteString.copyFromUtf8((String)object);
        this.name_ = object;
        return (ByteString)object;
      } 
      return (ByteString)object;
    }
    
    public Parser<DevicePlace> getParserForType() {
      return PARSER;
    }
    
    public int getSerializedSize() {
      int i = this.memoizedSize;
      if (i != -1)
        return i; 
      i = this.bitField0_;
      boolean bool = false;
      if ((i & 0x1) != 0) {
        j = CodedOutputStream.computeInt64Size(1, this.id_) + 0;
      } else {
        j = 0;
      } 
      i = j;
      if ((this.bitField0_ & 0x2) != 0)
        i = j + GeneratedMessageV3.computeStringSize(2, this.name_); 
      int j = i;
      if ((this.bitField0_ & 0x4) != 0)
        j = i + CodedOutputStream.computeInt32Size(3, this.icon_); 
      byte b = 0;
      i = b;
      while (b < this.assignedDevicesIds_.size()) {
        i += CodedOutputStream.computeInt64SizeNoTag(this.assignedDevicesIds_.getLong(b));
        b++;
      } 
      int k = getAssignedDevicesIdsList().size();
      int m = 0;
      for (b = bool; b < this.assignedGroupIds_.size(); b++)
        m += CodedOutputStream.computeInt64SizeNoTag(this.assignedGroupIds_.getLong(b)); 
      i = j + i + k * 1 + m + getAssignedGroupIdsList().size() * 1 + this.unknownFields.getSerializedSize();
      this.memoizedSize = i;
      return i;
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    public boolean hasIcon() {
      boolean bool;
      if ((this.bitField0_ & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasId() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean hasName() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int hashCode() {
      if (this.memoizedHashCode != 0)
        return this.memoizedHashCode; 
      int i = 779 + getDescriptor().hashCode();
      int j = i;
      if (hasId())
        j = (i * 37 + 1) * 53 + Internal.hashLong(getId()); 
      i = j;
      if (hasName())
        i = (j * 37 + 2) * 53 + getName().hashCode(); 
      j = i;
      if (hasIcon())
        j = (i * 37 + 3) * 53 + getIcon(); 
      i = j;
      if (getAssignedDevicesIdsCount() > 0)
        i = (j * 37 + 4) * 53 + getAssignedDevicesIdsList().hashCode(); 
      j = i;
      if (getAssignedGroupIdsCount() > 0)
        j = (i * 37 + 5) * 53 + getAssignedGroupIdsList().hashCode(); 
      i = j * 29 + this.unknownFields.hashCode();
      this.memoizedHashCode = i;
      return i;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_DevicePlace_fieldAccessorTable.ensureFieldAccessorsInitialized(DevicePlace.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      byte b = this.memoizedIsInitialized;
      if (b == 1)
        return true; 
      if (b == 0)
        return false; 
      this.memoizedIsInitialized = (byte)1;
      return true;
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      return new Builder(param1BuilderParent);
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter param1UnusedPrivateParameter) {
      return new DevicePlace();
    }
    
    public Builder toBuilder() {
      Builder builder;
      if (this == DEFAULT_INSTANCE) {
        builder = new Builder();
      } else {
        builder = (new Builder()).mergeFrom(this);
      } 
      return builder;
    }
    
    public void writeTo(CodedOutputStream param1CodedOutputStream) throws IOException {
      byte b3;
      if ((this.bitField0_ & 0x1) != 0)
        param1CodedOutputStream.writeInt64(1, this.id_); 
      if ((this.bitField0_ & 0x2) != 0)
        GeneratedMessageV3.writeString(param1CodedOutputStream, 2, this.name_); 
      if ((this.bitField0_ & 0x4) != 0)
        param1CodedOutputStream.writeInt32(3, this.icon_); 
      byte b1 = 0;
      byte b2 = 0;
      while (true) {
        b3 = b1;
        if (b2 < this.assignedDevicesIds_.size()) {
          param1CodedOutputStream.writeInt64(4, this.assignedDevicesIds_.getLong(b2));
          b2++;
          continue;
        } 
        break;
      } 
      while (b3 < this.assignedGroupIds_.size()) {
        param1CodedOutputStream.writeInt64(5, this.assignedGroupIds_.getLong(b3));
        b3++;
      } 
      this.unknownFields.writeTo(param1CodedOutputStream);
    }
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MobilusModel.DevicePlaceOrBuilder {
      private Internal.LongList assignedDevicesIds_ = MobilusModel.DevicePlace.emptyLongList();
      
      private Internal.LongList assignedGroupIds_ = MobilusModel.DevicePlace.emptyLongList();
      
      private int bitField0_;
      
      private int icon_;
      
      private long id_;
      
      private Object name_ = "";
      
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent param2BuilderParent) {
        super(param2BuilderParent);
        maybeForceBuilderInitialization();
      }
      
      private void ensureAssignedDevicesIdsIsMutable() {
        if ((this.bitField0_ & 0x8) == 0) {
          this.assignedDevicesIds_ = MobilusModel.DevicePlace.mutableCopy(this.assignedDevicesIds_);
          this.bitField0_ |= 0x8;
        } 
      }
      
      private void ensureAssignedGroupIdsIsMutable() {
        if ((this.bitField0_ & 0x10) == 0) {
          this.assignedGroupIds_ = MobilusModel.DevicePlace.mutableCopy(this.assignedGroupIds_);
          this.bitField0_ |= 0x10;
        } 
      }
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MobilusModel.internal_static_DevicePlace_descriptor;
      }
      
      private void maybeForceBuilderInitialization() {
        MobilusModel.DevicePlace.alwaysUseFieldBuilders;
      }
      
      public Builder addAllAssignedDevicesIds(Iterable<? extends Long> param2Iterable) {
        ensureAssignedDevicesIdsIsMutable();
        AbstractMessageLite.Builder.addAll(param2Iterable, (List)this.assignedDevicesIds_);
        onChanged();
        return this;
      }
      
      public Builder addAllAssignedGroupIds(Iterable<? extends Long> param2Iterable) {
        ensureAssignedGroupIdsIsMutable();
        AbstractMessageLite.Builder.addAll(param2Iterable, (List)this.assignedGroupIds_);
        onChanged();
        return this;
      }
      
      public Builder addAssignedDevicesIds(long param2Long) {
        ensureAssignedDevicesIdsIsMutable();
        this.assignedDevicesIds_.addLong(param2Long);
        onChanged();
        return this;
      }
      
      public Builder addAssignedGroupIds(long param2Long) {
        ensureAssignedGroupIdsIsMutable();
        this.assignedGroupIds_.addLong(param2Long);
        onChanged();
        return this;
      }
      
      public Builder addRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.addRepeatedField(param2FieldDescriptor, param2Object);
      }
      
      public MobilusModel.DevicePlace build() {
        MobilusModel.DevicePlace devicePlace = buildPartial();
        if (!devicePlace.isInitialized())
          throw newUninitializedMessageException(devicePlace); 
        return devicePlace;
      }
      
      public MobilusModel.DevicePlace buildPartial() {
        MobilusModel.DevicePlace devicePlace = new MobilusModel.DevicePlace(this);
        int i = this.bitField0_;
        if ((i & 0x1) != 0) {
          MobilusModel.DevicePlace.access$5102(devicePlace, this.id_);
          j = 1;
        } else {
          j = 0;
        } 
        int k = j;
        if ((i & 0x2) != 0)
          k = j | 0x2; 
        MobilusModel.DevicePlace.access$5202(devicePlace, this.name_);
        int j = k;
        if ((i & 0x4) != 0) {
          MobilusModel.DevicePlace.access$5302(devicePlace, this.icon_);
          j = k | 0x4;
        } 
        if ((this.bitField0_ & 0x8) != 0) {
          this.assignedDevicesIds_.makeImmutable();
          this.bitField0_ &= 0xFFFFFFF7;
        } 
        MobilusModel.DevicePlace.access$5402(devicePlace, this.assignedDevicesIds_);
        if ((this.bitField0_ & 0x10) != 0) {
          this.assignedGroupIds_.makeImmutable();
          this.bitField0_ &= 0xFFFFFFEF;
        } 
        MobilusModel.DevicePlace.access$5502(devicePlace, this.assignedGroupIds_);
        MobilusModel.DevicePlace.access$5602(devicePlace, j);
        onBuilt();
        return devicePlace;
      }
      
      public Builder clear() {
        super.clear();
        this.id_ = 0L;
        this.bitField0_ &= 0xFFFFFFFE;
        this.name_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        this.icon_ = 0;
        this.bitField0_ &= 0xFFFFFFFB;
        this.assignedDevicesIds_ = MobilusModel.DevicePlace.emptyLongList();
        this.bitField0_ &= 0xFFFFFFF7;
        this.assignedGroupIds_ = MobilusModel.DevicePlace.emptyLongList();
        this.bitField0_ &= 0xFFFFFFEF;
        return this;
      }
      
      public Builder clearAssignedDevicesIds() {
        this.assignedDevicesIds_ = MobilusModel.DevicePlace.emptyLongList();
        this.bitField0_ &= 0xFFFFFFF7;
        onChanged();
        return this;
      }
      
      public Builder clearAssignedGroupIds() {
        this.assignedGroupIds_ = MobilusModel.DevicePlace.emptyLongList();
        this.bitField0_ &= 0xFFFFFFEF;
        onChanged();
        return this;
      }
      
      public Builder clearField(Descriptors.FieldDescriptor param2FieldDescriptor) {
        return (Builder)super.clearField(param2FieldDescriptor);
      }
      
      public Builder clearIcon() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.icon_ = 0;
        onChanged();
        return this;
      }
      
      public Builder clearId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.id_ = 0L;
        onChanged();
        return this;
      }
      
      public Builder clearName() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.name_ = MobilusModel.DevicePlace.getDefaultInstance().getName();
        onChanged();
        return this;
      }
      
      public Builder clearOneof(Descriptors.OneofDescriptor param2OneofDescriptor) {
        return (Builder)super.clearOneof(param2OneofDescriptor);
      }
      
      public Builder clone() {
        return (Builder)super.clone();
      }
      
      public long getAssignedDevicesIds(int param2Int) {
        return this.assignedDevicesIds_.getLong(param2Int);
      }
      
      public int getAssignedDevicesIdsCount() {
        return this.assignedDevicesIds_.size();
      }
      
      public List<Long> getAssignedDevicesIdsList() {
        Internal.LongList longList;
        if ((this.bitField0_ & 0x8) != 0) {
          List<?> list = Collections.unmodifiableList((List<?>)this.assignedDevicesIds_);
        } else {
          longList = this.assignedDevicesIds_;
        } 
        return (List<Long>)longList;
      }
      
      public long getAssignedGroupIds(int param2Int) {
        return this.assignedGroupIds_.getLong(param2Int);
      }
      
      public int getAssignedGroupIdsCount() {
        return this.assignedGroupIds_.size();
      }
      
      public List<Long> getAssignedGroupIdsList() {
        Internal.LongList longList;
        if ((this.bitField0_ & 0x10) != 0) {
          List<?> list = Collections.unmodifiableList((List<?>)this.assignedGroupIds_);
        } else {
          longList = this.assignedGroupIds_;
        } 
        return (List<Long>)longList;
      }
      
      public MobilusModel.DevicePlace getDefaultInstanceForType() {
        return MobilusModel.DevicePlace.getDefaultInstance();
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MobilusModel.internal_static_DevicePlace_descriptor;
      }
      
      public int getIcon() {
        return this.icon_;
      }
      
      public long getId() {
        return this.id_;
      }
      
      public String getName() {
        Object object = this.name_;
        if (!(object instanceof String)) {
          ByteString byteString = (ByteString)object;
          object = byteString.toStringUtf8();
          if (byteString.isValidUtf8())
            this.name_ = object; 
          return (String)object;
        } 
        return (String)object;
      }
      
      public ByteString getNameBytes() {
        Object object = this.name_;
        if (object instanceof String) {
          object = ByteString.copyFromUtf8((String)object);
          this.name_ = object;
          return (ByteString)object;
        } 
        return (ByteString)object;
      }
      
      public boolean hasIcon() {
        boolean bool;
        if ((this.bitField0_ & 0x4) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasId() {
        int i = this.bitField0_;
        boolean bool = true;
        if ((i & 0x1) == 0)
          bool = false; 
        return bool;
      }
      
      public boolean hasName() {
        boolean bool;
        if ((this.bitField0_ & 0x2) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MobilusModel.internal_static_DevicePlace_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.DevicePlace.class, Builder.class);
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws IOException {
        ExtensionRegistryLite extensionRegistryLite = null;
        try {
          MobilusModel.DevicePlace devicePlace = (MobilusModel.DevicePlace)MobilusModel.DevicePlace.PARSER.parsePartialFrom(param2CodedInputStream, param2ExtensionRegistryLite);
          return this;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          MobilusModel.DevicePlace devicePlace = (MobilusModel.DevicePlace)invalidProtocolBufferException.getUnfinishedMessage();
        } finally {
          param2CodedInputStream = null;
        } 
        if (param2ExtensionRegistryLite != null)
          mergeFrom((MobilusModel.DevicePlace)param2ExtensionRegistryLite); 
        throw param2CodedInputStream;
      }
      
      public Builder mergeFrom(Message param2Message) {
        if (param2Message instanceof MobilusModel.DevicePlace)
          return mergeFrom((MobilusModel.DevicePlace)param2Message); 
        super.mergeFrom(param2Message);
        return this;
      }
      
      public Builder mergeFrom(MobilusModel.DevicePlace param2DevicePlace) {
        if (param2DevicePlace == MobilusModel.DevicePlace.getDefaultInstance())
          return this; 
        if (param2DevicePlace.hasId())
          setId(param2DevicePlace.getId()); 
        if (param2DevicePlace.hasName()) {
          this.bitField0_ |= 0x2;
          this.name_ = param2DevicePlace.name_;
          onChanged();
        } 
        if (param2DevicePlace.hasIcon())
          setIcon(param2DevicePlace.getIcon()); 
        if (!param2DevicePlace.assignedDevicesIds_.isEmpty()) {
          if (this.assignedDevicesIds_.isEmpty()) {
            this.assignedDevicesIds_ = param2DevicePlace.assignedDevicesIds_;
            this.bitField0_ &= 0xFFFFFFF7;
          } else {
            ensureAssignedDevicesIdsIsMutable();
            this.assignedDevicesIds_.addAll((Collection)param2DevicePlace.assignedDevicesIds_);
          } 
          onChanged();
        } 
        if (!param2DevicePlace.assignedGroupIds_.isEmpty()) {
          if (this.assignedGroupIds_.isEmpty()) {
            this.assignedGroupIds_ = param2DevicePlace.assignedGroupIds_;
            this.bitField0_ &= 0xFFFFFFEF;
          } else {
            ensureAssignedGroupIdsIsMutable();
            this.assignedGroupIds_.addAll((Collection)param2DevicePlace.assignedGroupIds_);
          } 
          onChanged();
        } 
        mergeUnknownFields(param2DevicePlace.unknownFields);
        onChanged();
        return this;
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.mergeUnknownFields(param2UnknownFieldSet);
      }
      
      public Builder setAssignedDevicesIds(int param2Int, long param2Long) {
        ensureAssignedDevicesIdsIsMutable();
        this.assignedDevicesIds_.setLong(param2Int, param2Long);
        onChanged();
        return this;
      }
      
      public Builder setAssignedGroupIds(int param2Int, long param2Long) {
        ensureAssignedGroupIdsIsMutable();
        this.assignedGroupIds_.setLong(param2Int, param2Long);
        onChanged();
        return this;
      }
      
      public Builder setField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.setField(param2FieldDescriptor, param2Object);
      }
      
      public Builder setIcon(int param2Int) {
        this.bitField0_ |= 0x4;
        this.icon_ = param2Int;
        onChanged();
        return this;
      }
      
      public Builder setId(long param2Long) {
        this.bitField0_ |= 0x1;
        this.id_ = param2Long;
        onChanged();
        return this;
      }
      
      public Builder setName(String param2String) {
        if (param2String == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.name_ = param2String;
        onChanged();
        return this;
      }
      
      public Builder setNameBytes(ByteString param2ByteString) {
        if (param2ByteString == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.name_ = param2ByteString;
        onChanged();
        return this;
      }
      
      public Builder setRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, int param2Int, Object param2Object) {
        return (Builder)super.setRepeatedField(param2FieldDescriptor, param2Int, param2Object);
      }
      
      public final Builder setUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.setUnknownFields(param2UnknownFieldSet);
      }
    }
  }
  
  static final class null extends AbstractParser<DevicePlace> {
    public MobilusModel.DevicePlace parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return new MobilusModel.DevicePlace(param1CodedInputStream, param1ExtensionRegistryLite);
    }
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<DevicePlace.Builder> implements DevicePlaceOrBuilder {
    private Internal.LongList assignedDevicesIds_ = MobilusModel.DevicePlace.emptyLongList();
    
    private Internal.LongList assignedGroupIds_ = MobilusModel.DevicePlace.emptyLongList();
    
    private int bitField0_;
    
    private int icon_;
    
    private long id_;
    
    private Object name_ = "";
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      super(param1BuilderParent);
      maybeForceBuilderInitialization();
    }
    
    private void ensureAssignedDevicesIdsIsMutable() {
      if ((this.bitField0_ & 0x8) == 0) {
        this.assignedDevicesIds_ = MobilusModel.DevicePlace.mutableCopy(this.assignedDevicesIds_);
        this.bitField0_ |= 0x8;
      } 
    }
    
    private void ensureAssignedGroupIdsIsMutable() {
      if ((this.bitField0_ & 0x10) == 0) {
        this.assignedGroupIds_ = MobilusModel.DevicePlace.mutableCopy(this.assignedGroupIds_);
        this.bitField0_ |= 0x10;
      } 
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_DevicePlace_descriptor;
    }
    
    private void maybeForceBuilderInitialization() {
      MobilusModel.DevicePlace.alwaysUseFieldBuilders;
    }
    
    public Builder addAllAssignedDevicesIds(Iterable<? extends Long> param1Iterable) {
      ensureAssignedDevicesIdsIsMutable();
      AbstractMessageLite.Builder.addAll(param1Iterable, (List)this.assignedDevicesIds_);
      onChanged();
      return this;
    }
    
    public Builder addAllAssignedGroupIds(Iterable<? extends Long> param1Iterable) {
      ensureAssignedGroupIdsIsMutable();
      AbstractMessageLite.Builder.addAll(param1Iterable, (List)this.assignedGroupIds_);
      onChanged();
      return this;
    }
    
    public Builder addAssignedDevicesIds(long param1Long) {
      ensureAssignedDevicesIdsIsMutable();
      this.assignedDevicesIds_.addLong(param1Long);
      onChanged();
      return this;
    }
    
    public Builder addAssignedGroupIds(long param1Long) {
      ensureAssignedGroupIdsIsMutable();
      this.assignedGroupIds_.addLong(param1Long);
      onChanged();
      return this;
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.addRepeatedField(param1FieldDescriptor, param1Object);
    }
    
    public MobilusModel.DevicePlace build() {
      MobilusModel.DevicePlace devicePlace = buildPartial();
      if (!devicePlace.isInitialized())
        throw newUninitializedMessageException(devicePlace); 
      return devicePlace;
    }
    
    public MobilusModel.DevicePlace buildPartial() {
      MobilusModel.DevicePlace devicePlace = new MobilusModel.DevicePlace(this);
      int i = this.bitField0_;
      if ((i & 0x1) != 0) {
        MobilusModel.DevicePlace.access$5102(devicePlace, this.id_);
        j = 1;
      } else {
        j = 0;
      } 
      int k = j;
      if ((i & 0x2) != 0)
        k = j | 0x2; 
      MobilusModel.DevicePlace.access$5202(devicePlace, this.name_);
      int j = k;
      if ((i & 0x4) != 0) {
        MobilusModel.DevicePlace.access$5302(devicePlace, this.icon_);
        j = k | 0x4;
      } 
      if ((this.bitField0_ & 0x8) != 0) {
        this.assignedDevicesIds_.makeImmutable();
        this.bitField0_ &= 0xFFFFFFF7;
      } 
      MobilusModel.DevicePlace.access$5402(devicePlace, this.assignedDevicesIds_);
      if ((this.bitField0_ & 0x10) != 0) {
        this.assignedGroupIds_.makeImmutable();
        this.bitField0_ &= 0xFFFFFFEF;
      } 
      MobilusModel.DevicePlace.access$5502(devicePlace, this.assignedGroupIds_);
      MobilusModel.DevicePlace.access$5602(devicePlace, j);
      onBuilt();
      return devicePlace;
    }
    
    public Builder clear() {
      super.clear();
      this.id_ = 0L;
      this.bitField0_ &= 0xFFFFFFFE;
      this.name_ = "";
      this.bitField0_ &= 0xFFFFFFFD;
      this.icon_ = 0;
      this.bitField0_ &= 0xFFFFFFFB;
      this.assignedDevicesIds_ = MobilusModel.DevicePlace.emptyLongList();
      this.bitField0_ &= 0xFFFFFFF7;
      this.assignedGroupIds_ = MobilusModel.DevicePlace.emptyLongList();
      this.bitField0_ &= 0xFFFFFFEF;
      return this;
    }
    
    public Builder clearAssignedDevicesIds() {
      this.assignedDevicesIds_ = MobilusModel.DevicePlace.emptyLongList();
      this.bitField0_ &= 0xFFFFFFF7;
      onChanged();
      return this;
    }
    
    public Builder clearAssignedGroupIds() {
      this.assignedGroupIds_ = MobilusModel.DevicePlace.emptyLongList();
      this.bitField0_ &= 0xFFFFFFEF;
      onChanged();
      return this;
    }
    
    public Builder clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return (Builder)super.clearField(param1FieldDescriptor);
    }
    
    public Builder clearIcon() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.icon_ = 0;
      onChanged();
      return this;
    }
    
    public Builder clearId() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.id_ = 0L;
      onChanged();
      return this;
    }
    
    public Builder clearName() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.name_ = MobilusModel.DevicePlace.getDefaultInstance().getName();
      onChanged();
      return this;
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return (Builder)super.clearOneof(param1OneofDescriptor);
    }
    
    public Builder clone() {
      return (Builder)super.clone();
    }
    
    public long getAssignedDevicesIds(int param1Int) {
      return this.assignedDevicesIds_.getLong(param1Int);
    }
    
    public int getAssignedDevicesIdsCount() {
      return this.assignedDevicesIds_.size();
    }
    
    public List<Long> getAssignedDevicesIdsList() {
      Internal.LongList longList;
      if ((this.bitField0_ & 0x8) != 0) {
        List<?> list = Collections.unmodifiableList((List<?>)this.assignedDevicesIds_);
      } else {
        longList = this.assignedDevicesIds_;
      } 
      return (List<Long>)longList;
    }
    
    public long getAssignedGroupIds(int param1Int) {
      return this.assignedGroupIds_.getLong(param1Int);
    }
    
    public int getAssignedGroupIdsCount() {
      return this.assignedGroupIds_.size();
    }
    
    public List<Long> getAssignedGroupIdsList() {
      Internal.LongList longList;
      if ((this.bitField0_ & 0x10) != 0) {
        List<?> list = Collections.unmodifiableList((List<?>)this.assignedGroupIds_);
      } else {
        longList = this.assignedGroupIds_;
      } 
      return (List<Long>)longList;
    }
    
    public MobilusModel.DevicePlace getDefaultInstanceForType() {
      return MobilusModel.DevicePlace.getDefaultInstance();
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return MobilusModel.internal_static_DevicePlace_descriptor;
    }
    
    public int getIcon() {
      return this.icon_;
    }
    
    public long getId() {
      return this.id_;
    }
    
    public String getName() {
      Object object = this.name_;
      if (!(object instanceof String)) {
        ByteString byteString = (ByteString)object;
        object = byteString.toStringUtf8();
        if (byteString.isValidUtf8())
          this.name_ = object; 
        return (String)object;
      } 
      return (String)object;
    }
    
    public ByteString getNameBytes() {
      Object object = this.name_;
      if (object instanceof String) {
        object = ByteString.copyFromUtf8((String)object);
        this.name_ = object;
        return (ByteString)object;
      } 
      return (ByteString)object;
    }
    
    public boolean hasIcon() {
      boolean bool;
      if ((this.bitField0_ & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasId() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean hasName() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_DevicePlace_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.DevicePlace.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      ExtensionRegistryLite extensionRegistryLite = null;
      try {
        MobilusModel.DevicePlace devicePlace = (MobilusModel.DevicePlace)MobilusModel.DevicePlace.PARSER.parsePartialFrom(param1CodedInputStream, param1ExtensionRegistryLite);
        return this;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        MobilusModel.DevicePlace devicePlace = (MobilusModel.DevicePlace)invalidProtocolBufferException.getUnfinishedMessage();
      } finally {
        param1CodedInputStream = null;
      } 
      if (param1ExtensionRegistryLite != null)
        mergeFrom((MobilusModel.DevicePlace)param1ExtensionRegistryLite); 
      throw param1CodedInputStream;
    }
    
    public Builder mergeFrom(Message param1Message) {
      if (param1Message instanceof MobilusModel.DevicePlace)
        return mergeFrom((MobilusModel.DevicePlace)param1Message); 
      super.mergeFrom(param1Message);
      return this;
    }
    
    public Builder mergeFrom(MobilusModel.DevicePlace param1DevicePlace) {
      if (param1DevicePlace == MobilusModel.DevicePlace.getDefaultInstance())
        return this; 
      if (param1DevicePlace.hasId())
        setId(param1DevicePlace.getId()); 
      if (param1DevicePlace.hasName()) {
        this.bitField0_ |= 0x2;
        this.name_ = param1DevicePlace.name_;
        onChanged();
      } 
      if (param1DevicePlace.hasIcon())
        setIcon(param1DevicePlace.getIcon()); 
      if (!param1DevicePlace.assignedDevicesIds_.isEmpty()) {
        if (this.assignedDevicesIds_.isEmpty()) {
          this.assignedDevicesIds_ = param1DevicePlace.assignedDevicesIds_;
          this.bitField0_ &= 0xFFFFFFF7;
        } else {
          ensureAssignedDevicesIdsIsMutable();
          this.assignedDevicesIds_.addAll((Collection)param1DevicePlace.assignedDevicesIds_);
        } 
        onChanged();
      } 
      if (!param1DevicePlace.assignedGroupIds_.isEmpty()) {
        if (this.assignedGroupIds_.isEmpty()) {
          this.assignedGroupIds_ = param1DevicePlace.assignedGroupIds_;
          this.bitField0_ &= 0xFFFFFFEF;
        } else {
          ensureAssignedGroupIdsIsMutable();
          this.assignedGroupIds_.addAll((Collection)param1DevicePlace.assignedGroupIds_);
        } 
        onChanged();
      } 
      mergeUnknownFields(param1DevicePlace.unknownFields);
      onChanged();
      return this;
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.mergeUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder setAssignedDevicesIds(int param1Int, long param1Long) {
      ensureAssignedDevicesIdsIsMutable();
      this.assignedDevicesIds_.setLong(param1Int, param1Long);
      onChanged();
      return this;
    }
    
    public Builder setAssignedGroupIds(int param1Int, long param1Long) {
      ensureAssignedGroupIdsIsMutable();
      this.assignedGroupIds_.setLong(param1Int, param1Long);
      onChanged();
      return this;
    }
    
    public Builder setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.setField(param1FieldDescriptor, param1Object);
    }
    
    public Builder setIcon(int param1Int) {
      this.bitField0_ |= 0x4;
      this.icon_ = param1Int;
      onChanged();
      return this;
    }
    
    public Builder setId(long param1Long) {
      this.bitField0_ |= 0x1;
      this.id_ = param1Long;
      onChanged();
      return this;
    }
    
    public Builder setName(String param1String) {
      if (param1String == null)
        throw new NullPointerException(); 
      this.bitField0_ |= 0x2;
      this.name_ = param1String;
      onChanged();
      return this;
    }
    
    public Builder setNameBytes(ByteString param1ByteString) {
      if (param1ByteString == null)
        throw new NullPointerException(); 
      this.bitField0_ |= 0x2;
      this.name_ = param1ByteString;
      onChanged();
      return this;
    }
    
    public Builder setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object) {
      return (Builder)super.setRepeatedField(param1FieldDescriptor, param1Int, param1Object);
    }
    
    public final Builder setUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.setUnknownFields(param1UnknownFieldSet);
    }
  }
  
  public static interface DevicePlaceOrBuilder extends MessageOrBuilder {
    long getAssignedDevicesIds(int param1Int);
    
    int getAssignedDevicesIdsCount();
    
    List<Long> getAssignedDevicesIdsList();
    
    long getAssignedGroupIds(int param1Int);
    
    int getAssignedGroupIdsCount();
    
    List<Long> getAssignedGroupIdsList();
    
    int getIcon();
    
    long getId();
    
    String getName();
    
    ByteString getNameBytes();
    
    boolean hasIcon();
    
    boolean hasId();
    
    boolean hasName();
  }
  
  public static final class Event extends GeneratedMessageV3 implements EventOrBuilder {
    private static final Event DEFAULT_INSTANCE = new Event();
    
    public static final int DEVICE_ID_FIELD_NUMBER = 2;
    
    public static final int EVENT_NUMBER_FIELD_NUMBER = 3;
    
    public static final int ID_FIELD_NUMBER = 1;
    
    public static final int INSERTTIME_FIELD_NUMBER = 7;
    
    @Deprecated
    public static final Parser<Event> PARSER = (Parser<Event>)new AbstractParser<Event>() {
        public MobilusModel.Event parsePartialFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws InvalidProtocolBufferException {
          return new MobilusModel.Event(param2CodedInputStream, param2ExtensionRegistryLite);
        }
      };
    
    public static final int PLATFORM_FIELD_NUMBER = 5;
    
    public static final int USER_FIELD_NUMBER = 6;
    
    public static final int VALUE_FIELD_NUMBER = 4;
    
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    private long deviceId_;
    
    private int eventNumber_;
    
    private long id_;
    
    private long inserttime_;
    
    private byte memoizedIsInitialized = (byte)-1;
    
    private int platform_;
    
    private long user_;
    
    private volatile Object value_;
    
    private Event() {
      this.value_ = "";
    }
    
    private Event(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      this();
      if (param1ExtensionRegistryLite == null)
        throw new NullPointerException(); 
      UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
      for (boolean bool = false; !bool; bool = true) {
        try {
          int i = param1CodedInputStream.readTag();
          if (i != 0) {
            if (i != 8) {
              if (i != 16) {
                if (i != 24) {
                  if (i != 34) {
                    if (i != 40) {
                      if (i != 48) {
                        if (i != 56) {
                          if (!parseUnknownField(param1CodedInputStream, builder, param1ExtensionRegistryLite, i))
                            continue; 
                          continue;
                        } 
                        this.bitField0_ |= 0x40;
                        this.inserttime_ = param1CodedInputStream.readInt64();
                        continue;
                      } 
                      this.bitField0_ |= 0x20;
                      this.user_ = param1CodedInputStream.readInt64();
                      continue;
                    } 
                    this.bitField0_ |= 0x10;
                    this.platform_ = param1CodedInputStream.readInt32();
                    continue;
                  } 
                  ByteString byteString = param1CodedInputStream.readBytes();
                  this.bitField0_ |= 0x8;
                  this.value_ = byteString;
                  continue;
                } 
                this.bitField0_ |= 0x4;
                this.eventNumber_ = param1CodedInputStream.readInt32();
                continue;
              } 
              this.bitField0_ |= 0x2;
              this.deviceId_ = param1CodedInputStream.readInt64();
              continue;
            } 
            this.bitField0_ |= 0x1;
            this.id_ = param1CodedInputStream.readInt64();
            continue;
          } 
          continue;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } catch (IOException iOException) {
          InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException();
          this(iOException);
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } finally {}
        this.unknownFields = builder.build();
        makeExtensionsImmutable();
        throw param1CodedInputStream;
      } 
      this.unknownFields = builder.build();
      makeExtensionsImmutable();
    }
    
    private Event(GeneratedMessageV3.Builder<?> param1Builder) {
      super(param1Builder);
    }
    
    public static Event getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_Event_descriptor;
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(Event param1Event) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(param1Event);
    }
    
    public static Event parseDelimitedFrom(InputStream param1InputStream) throws IOException {
      return (Event)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream);
    }
    
    public static Event parseDelimitedFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (Event)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static Event parseFrom(ByteString param1ByteString) throws InvalidProtocolBufferException {
      return (Event)PARSER.parseFrom(param1ByteString);
    }
    
    public static Event parseFrom(ByteString param1ByteString, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (Event)PARSER.parseFrom(param1ByteString, param1ExtensionRegistryLite);
    }
    
    public static Event parseFrom(CodedInputStream param1CodedInputStream) throws IOException {
      return (Event)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream);
    }
    
    public static Event parseFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (Event)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream, param1ExtensionRegistryLite);
    }
    
    public static Event parseFrom(InputStream param1InputStream) throws IOException {
      return (Event)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream);
    }
    
    public static Event parseFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (Event)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static Event parseFrom(ByteBuffer param1ByteBuffer) throws InvalidProtocolBufferException {
      return (Event)PARSER.parseFrom(param1ByteBuffer);
    }
    
    public static Event parseFrom(ByteBuffer param1ByteBuffer, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (Event)PARSER.parseFrom(param1ByteBuffer, param1ExtensionRegistryLite);
    }
    
    public static Event parseFrom(byte[] param1ArrayOfbyte) throws InvalidProtocolBufferException {
      return (Event)PARSER.parseFrom(param1ArrayOfbyte);
    }
    
    public static Event parseFrom(byte[] param1ArrayOfbyte, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (Event)PARSER.parseFrom(param1ArrayOfbyte, param1ExtensionRegistryLite);
    }
    
    public static Parser<Event> parser() {
      return PARSER;
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object == this)
        return true; 
      if (!(param1Object instanceof Event))
        return super.equals(param1Object); 
      param1Object = param1Object;
      return (hasId() != param1Object.hasId()) ? false : ((hasId() && getId() != param1Object.getId()) ? false : ((hasDeviceId() != param1Object.hasDeviceId()) ? false : ((hasDeviceId() && getDeviceId() != param1Object.getDeviceId()) ? false : ((hasEventNumber() != param1Object.hasEventNumber()) ? false : ((hasEventNumber() && getEventNumber() != param1Object.getEventNumber()) ? false : ((hasValue() != param1Object.hasValue()) ? false : ((hasValue() && !getValue().equals(param1Object.getValue())) ? false : ((hasPlatform() != param1Object.hasPlatform()) ? false : ((hasPlatform() && getPlatform() != param1Object.getPlatform()) ? false : ((hasUser() != param1Object.hasUser()) ? false : ((hasUser() && getUser() != param1Object.getUser()) ? false : ((hasInserttime() != param1Object.hasInserttime()) ? false : ((hasInserttime() && getInserttime() != param1Object.getInserttime()) ? false : (!!this.unknownFields.equals(((Event)param1Object).unknownFields)))))))))))))));
    }
    
    public Event getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
    
    public long getDeviceId() {
      return this.deviceId_;
    }
    
    public int getEventNumber() {
      return this.eventNumber_;
    }
    
    public long getId() {
      return this.id_;
    }
    
    public long getInserttime() {
      return this.inserttime_;
    }
    
    public Parser<Event> getParserForType() {
      return PARSER;
    }
    
    public int getPlatform() {
      return this.platform_;
    }
    
    public int getSerializedSize() {
      int i = this.memoizedSize;
      if (i != -1)
        return i; 
      i = 0;
      if ((this.bitField0_ & 0x1) != 0)
        i = 0 + CodedOutputStream.computeInt64Size(1, this.id_); 
      int j = i;
      if ((this.bitField0_ & 0x2) != 0)
        j = i + CodedOutputStream.computeInt64Size(2, this.deviceId_); 
      i = j;
      if ((this.bitField0_ & 0x4) != 0)
        i = j + CodedOutputStream.computeInt32Size(3, this.eventNumber_); 
      j = i;
      if ((this.bitField0_ & 0x8) != 0)
        j = i + GeneratedMessageV3.computeStringSize(4, this.value_); 
      i = j;
      if ((this.bitField0_ & 0x10) != 0)
        i = j + CodedOutputStream.computeInt32Size(5, this.platform_); 
      j = i;
      if ((this.bitField0_ & 0x20) != 0)
        j = i + CodedOutputStream.computeInt64Size(6, this.user_); 
      i = j;
      if ((this.bitField0_ & 0x40) != 0)
        i = j + CodedOutputStream.computeInt64Size(7, this.inserttime_); 
      i += this.unknownFields.getSerializedSize();
      this.memoizedSize = i;
      return i;
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    public long getUser() {
      return this.user_;
    }
    
    public String getValue() {
      Object object = this.value_;
      if (object instanceof String)
        return (String)object; 
      ByteString byteString = (ByteString)object;
      object = byteString.toStringUtf8();
      if (byteString.isValidUtf8())
        this.value_ = object; 
      return (String)object;
    }
    
    public ByteString getValueBytes() {
      Object object = this.value_;
      if (object instanceof String) {
        object = ByteString.copyFromUtf8((String)object);
        this.value_ = object;
        return (ByteString)object;
      } 
      return (ByteString)object;
    }
    
    public boolean hasDeviceId() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasEventNumber() {
      boolean bool;
      if ((this.bitField0_ & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasId() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean hasInserttime() {
      boolean bool;
      if ((this.bitField0_ & 0x40) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasPlatform() {
      boolean bool;
      if ((this.bitField0_ & 0x10) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasUser() {
      boolean bool;
      if ((this.bitField0_ & 0x20) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasValue() {
      boolean bool;
      if ((this.bitField0_ & 0x8) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int hashCode() {
      if (this.memoizedHashCode != 0)
        return this.memoizedHashCode; 
      int i = 779 + getDescriptor().hashCode();
      int j = i;
      if (hasId())
        j = (i * 37 + 1) * 53 + Internal.hashLong(getId()); 
      i = j;
      if (hasDeviceId())
        i = (j * 37 + 2) * 53 + Internal.hashLong(getDeviceId()); 
      j = i;
      if (hasEventNumber())
        j = (i * 37 + 3) * 53 + getEventNumber(); 
      i = j;
      if (hasValue())
        i = (j * 37 + 4) * 53 + getValue().hashCode(); 
      j = i;
      if (hasPlatform())
        j = (i * 37 + 5) * 53 + getPlatform(); 
      i = j;
      if (hasUser())
        i = (j * 37 + 6) * 53 + Internal.hashLong(getUser()); 
      j = i;
      if (hasInserttime())
        j = (i * 37 + 7) * 53 + Internal.hashLong(getInserttime()); 
      j = j * 29 + this.unknownFields.hashCode();
      this.memoizedHashCode = j;
      return j;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_Event_fieldAccessorTable.ensureFieldAccessorsInitialized(Event.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      byte b = this.memoizedIsInitialized;
      if (b == 1)
        return true; 
      if (b == 0)
        return false; 
      this.memoizedIsInitialized = (byte)1;
      return true;
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      return new Builder(param1BuilderParent);
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter param1UnusedPrivateParameter) {
      return new Event();
    }
    
    public Builder toBuilder() {
      Builder builder;
      if (this == DEFAULT_INSTANCE) {
        builder = new Builder();
      } else {
        builder = (new Builder()).mergeFrom(this);
      } 
      return builder;
    }
    
    public void writeTo(CodedOutputStream param1CodedOutputStream) throws IOException {
      if ((this.bitField0_ & 0x1) != 0)
        param1CodedOutputStream.writeInt64(1, this.id_); 
      if ((this.bitField0_ & 0x2) != 0)
        param1CodedOutputStream.writeInt64(2, this.deviceId_); 
      if ((this.bitField0_ & 0x4) != 0)
        param1CodedOutputStream.writeInt32(3, this.eventNumber_); 
      if ((this.bitField0_ & 0x8) != 0)
        GeneratedMessageV3.writeString(param1CodedOutputStream, 4, this.value_); 
      if ((this.bitField0_ & 0x10) != 0)
        param1CodedOutputStream.writeInt32(5, this.platform_); 
      if ((this.bitField0_ & 0x20) != 0)
        param1CodedOutputStream.writeInt64(6, this.user_); 
      if ((this.bitField0_ & 0x40) != 0)
        param1CodedOutputStream.writeInt64(7, this.inserttime_); 
      this.unknownFields.writeTo(param1CodedOutputStream);
    }
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MobilusModel.EventOrBuilder {
      private int bitField0_;
      
      private long deviceId_;
      
      private int eventNumber_;
      
      private long id_;
      
      private long inserttime_;
      
      private int platform_;
      
      private long user_;
      
      private Object value_ = "";
      
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent param2BuilderParent) {
        super(param2BuilderParent);
        maybeForceBuilderInitialization();
      }
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MobilusModel.internal_static_Event_descriptor;
      }
      
      private void maybeForceBuilderInitialization() {
        MobilusModel.Event.alwaysUseFieldBuilders;
      }
      
      public Builder addRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.addRepeatedField(param2FieldDescriptor, param2Object);
      }
      
      public MobilusModel.Event build() {
        MobilusModel.Event event = buildPartial();
        if (!event.isInitialized())
          throw newUninitializedMessageException(event); 
        return event;
      }
      
      public MobilusModel.Event buildPartial() {
        MobilusModel.Event event = new MobilusModel.Event(this);
        int i = this.bitField0_;
        if ((i & 0x1) != 0) {
          MobilusModel.Event.access$15102(event, this.id_);
          j = 1;
        } else {
          j = 0;
        } 
        int k = j;
        if ((i & 0x2) != 0) {
          MobilusModel.Event.access$15202(event, this.deviceId_);
          k = j | 0x2;
        } 
        int j = k;
        if ((i & 0x4) != 0) {
          MobilusModel.Event.access$15302(event, this.eventNumber_);
          j = k | 0x4;
        } 
        k = j;
        if ((i & 0x8) != 0)
          k = j | 0x8; 
        MobilusModel.Event.access$15402(event, this.value_);
        j = k;
        if ((i & 0x10) != 0) {
          MobilusModel.Event.access$15502(event, this.platform_);
          j = k | 0x10;
        } 
        k = j;
        if ((i & 0x20) != 0) {
          MobilusModel.Event.access$15602(event, this.user_);
          k = j | 0x20;
        } 
        j = k;
        if ((i & 0x40) != 0) {
          MobilusModel.Event.access$15702(event, this.inserttime_);
          j = k | 0x40;
        } 
        MobilusModel.Event.access$15802(event, j);
        onBuilt();
        return event;
      }
      
      public Builder clear() {
        super.clear();
        this.id_ = 0L;
        this.bitField0_ &= 0xFFFFFFFE;
        this.deviceId_ = 0L;
        this.bitField0_ &= 0xFFFFFFFD;
        this.eventNumber_ = 0;
        this.bitField0_ &= 0xFFFFFFFB;
        this.value_ = "";
        this.bitField0_ &= 0xFFFFFFF7;
        this.platform_ = 0;
        this.bitField0_ &= 0xFFFFFFEF;
        this.user_ = 0L;
        this.bitField0_ &= 0xFFFFFFDF;
        this.inserttime_ = 0L;
        this.bitField0_ &= 0xFFFFFFBF;
        return this;
      }
      
      public Builder clearDeviceId() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.deviceId_ = 0L;
        onChanged();
        return this;
      }
      
      public Builder clearEventNumber() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.eventNumber_ = 0;
        onChanged();
        return this;
      }
      
      public Builder clearField(Descriptors.FieldDescriptor param2FieldDescriptor) {
        return (Builder)super.clearField(param2FieldDescriptor);
      }
      
      public Builder clearId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.id_ = 0L;
        onChanged();
        return this;
      }
      
      public Builder clearInserttime() {
        this.bitField0_ &= 0xFFFFFFBF;
        this.inserttime_ = 0L;
        onChanged();
        return this;
      }
      
      public Builder clearOneof(Descriptors.OneofDescriptor param2OneofDescriptor) {
        return (Builder)super.clearOneof(param2OneofDescriptor);
      }
      
      public Builder clearPlatform() {
        this.bitField0_ &= 0xFFFFFFEF;
        this.platform_ = 0;
        onChanged();
        return this;
      }
      
      public Builder clearUser() {
        this.bitField0_ &= 0xFFFFFFDF;
        this.user_ = 0L;
        onChanged();
        return this;
      }
      
      public Builder clearValue() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.value_ = MobilusModel.Event.getDefaultInstance().getValue();
        onChanged();
        return this;
      }
      
      public Builder clone() {
        return (Builder)super.clone();
      }
      
      public MobilusModel.Event getDefaultInstanceForType() {
        return MobilusModel.Event.getDefaultInstance();
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MobilusModel.internal_static_Event_descriptor;
      }
      
      public long getDeviceId() {
        return this.deviceId_;
      }
      
      public int getEventNumber() {
        return this.eventNumber_;
      }
      
      public long getId() {
        return this.id_;
      }
      
      public long getInserttime() {
        return this.inserttime_;
      }
      
      public int getPlatform() {
        return this.platform_;
      }
      
      public long getUser() {
        return this.user_;
      }
      
      public String getValue() {
        Object object = this.value_;
        if (!(object instanceof String)) {
          ByteString byteString = (ByteString)object;
          object = byteString.toStringUtf8();
          if (byteString.isValidUtf8())
            this.value_ = object; 
          return (String)object;
        } 
        return (String)object;
      }
      
      public ByteString getValueBytes() {
        Object object = this.value_;
        if (object instanceof String) {
          object = ByteString.copyFromUtf8((String)object);
          this.value_ = object;
          return (ByteString)object;
        } 
        return (ByteString)object;
      }
      
      public boolean hasDeviceId() {
        boolean bool;
        if ((this.bitField0_ & 0x2) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasEventNumber() {
        boolean bool;
        if ((this.bitField0_ & 0x4) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasId() {
        int i = this.bitField0_;
        boolean bool = true;
        if ((i & 0x1) == 0)
          bool = false; 
        return bool;
      }
      
      public boolean hasInserttime() {
        boolean bool;
        if ((this.bitField0_ & 0x40) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasPlatform() {
        boolean bool;
        if ((this.bitField0_ & 0x10) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasUser() {
        boolean bool;
        if ((this.bitField0_ & 0x20) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasValue() {
        boolean bool;
        if ((this.bitField0_ & 0x8) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MobilusModel.internal_static_Event_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.Event.class, Builder.class);
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws IOException {
        CodedInputStream codedInputStream = null;
        try {
          MobilusModel.Event event = (MobilusModel.Event)MobilusModel.Event.PARSER.parsePartialFrom(param2CodedInputStream, param2ExtensionRegistryLite);
          return this;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          MobilusModel.Event event = (MobilusModel.Event)invalidProtocolBufferException.getUnfinishedMessage();
        } finally {
          param2ExtensionRegistryLite = null;
        } 
        if (param2CodedInputStream != null)
          mergeFrom((MobilusModel.Event)param2CodedInputStream); 
        throw param2ExtensionRegistryLite;
      }
      
      public Builder mergeFrom(Message param2Message) {
        if (param2Message instanceof MobilusModel.Event)
          return mergeFrom((MobilusModel.Event)param2Message); 
        super.mergeFrom(param2Message);
        return this;
      }
      
      public Builder mergeFrom(MobilusModel.Event param2Event) {
        if (param2Event == MobilusModel.Event.getDefaultInstance())
          return this; 
        if (param2Event.hasId())
          setId(param2Event.getId()); 
        if (param2Event.hasDeviceId())
          setDeviceId(param2Event.getDeviceId()); 
        if (param2Event.hasEventNumber())
          setEventNumber(param2Event.getEventNumber()); 
        if (param2Event.hasValue()) {
          this.bitField0_ |= 0x8;
          this.value_ = param2Event.value_;
          onChanged();
        } 
        if (param2Event.hasPlatform())
          setPlatform(param2Event.getPlatform()); 
        if (param2Event.hasUser())
          setUser(param2Event.getUser()); 
        if (param2Event.hasInserttime())
          setInserttime(param2Event.getInserttime()); 
        mergeUnknownFields(param2Event.unknownFields);
        onChanged();
        return this;
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.mergeUnknownFields(param2UnknownFieldSet);
      }
      
      public Builder setDeviceId(long param2Long) {
        this.bitField0_ |= 0x2;
        this.deviceId_ = param2Long;
        onChanged();
        return this;
      }
      
      public Builder setEventNumber(int param2Int) {
        this.bitField0_ |= 0x4;
        this.eventNumber_ = param2Int;
        onChanged();
        return this;
      }
      
      public Builder setField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.setField(param2FieldDescriptor, param2Object);
      }
      
      public Builder setId(long param2Long) {
        this.bitField0_ |= 0x1;
        this.id_ = param2Long;
        onChanged();
        return this;
      }
      
      public Builder setInserttime(long param2Long) {
        this.bitField0_ |= 0x40;
        this.inserttime_ = param2Long;
        onChanged();
        return this;
      }
      
      public Builder setPlatform(int param2Int) {
        this.bitField0_ |= 0x10;
        this.platform_ = param2Int;
        onChanged();
        return this;
      }
      
      public Builder setRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, int param2Int, Object param2Object) {
        return (Builder)super.setRepeatedField(param2FieldDescriptor, param2Int, param2Object);
      }
      
      public final Builder setUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.setUnknownFields(param2UnknownFieldSet);
      }
      
      public Builder setUser(long param2Long) {
        this.bitField0_ |= 0x20;
        this.user_ = param2Long;
        onChanged();
        return this;
      }
      
      public Builder setValue(String param2String) {
        if (param2String == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x8;
        this.value_ = param2String;
        onChanged();
        return this;
      }
      
      public Builder setValueBytes(ByteString param2ByteString) {
        if (param2ByteString == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x8;
        this.value_ = param2ByteString;
        onChanged();
        return this;
      }
    }
  }
  
  static final class null extends AbstractParser<Event> {
    public MobilusModel.Event parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return new MobilusModel.Event(param1CodedInputStream, param1ExtensionRegistryLite);
    }
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<Event.Builder> implements EventOrBuilder {
    private int bitField0_;
    
    private long deviceId_;
    
    private int eventNumber_;
    
    private long id_;
    
    private long inserttime_;
    
    private int platform_;
    
    private long user_;
    
    private Object value_ = "";
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      super(param1BuilderParent);
      maybeForceBuilderInitialization();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_Event_descriptor;
    }
    
    private void maybeForceBuilderInitialization() {
      MobilusModel.Event.alwaysUseFieldBuilders;
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.addRepeatedField(param1FieldDescriptor, param1Object);
    }
    
    public MobilusModel.Event build() {
      MobilusModel.Event event = buildPartial();
      if (!event.isInitialized())
        throw newUninitializedMessageException(event); 
      return event;
    }
    
    public MobilusModel.Event buildPartial() {
      MobilusModel.Event event = new MobilusModel.Event(this);
      int i = this.bitField0_;
      if ((i & 0x1) != 0) {
        MobilusModel.Event.access$15102(event, this.id_);
        j = 1;
      } else {
        j = 0;
      } 
      int k = j;
      if ((i & 0x2) != 0) {
        MobilusModel.Event.access$15202(event, this.deviceId_);
        k = j | 0x2;
      } 
      int j = k;
      if ((i & 0x4) != 0) {
        MobilusModel.Event.access$15302(event, this.eventNumber_);
        j = k | 0x4;
      } 
      k = j;
      if ((i & 0x8) != 0)
        k = j | 0x8; 
      MobilusModel.Event.access$15402(event, this.value_);
      j = k;
      if ((i & 0x10) != 0) {
        MobilusModel.Event.access$15502(event, this.platform_);
        j = k | 0x10;
      } 
      k = j;
      if ((i & 0x20) != 0) {
        MobilusModel.Event.access$15602(event, this.user_);
        k = j | 0x20;
      } 
      j = k;
      if ((i & 0x40) != 0) {
        MobilusModel.Event.access$15702(event, this.inserttime_);
        j = k | 0x40;
      } 
      MobilusModel.Event.access$15802(event, j);
      onBuilt();
      return event;
    }
    
    public Builder clear() {
      super.clear();
      this.id_ = 0L;
      this.bitField0_ &= 0xFFFFFFFE;
      this.deviceId_ = 0L;
      this.bitField0_ &= 0xFFFFFFFD;
      this.eventNumber_ = 0;
      this.bitField0_ &= 0xFFFFFFFB;
      this.value_ = "";
      this.bitField0_ &= 0xFFFFFFF7;
      this.platform_ = 0;
      this.bitField0_ &= 0xFFFFFFEF;
      this.user_ = 0L;
      this.bitField0_ &= 0xFFFFFFDF;
      this.inserttime_ = 0L;
      this.bitField0_ &= 0xFFFFFFBF;
      return this;
    }
    
    public Builder clearDeviceId() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.deviceId_ = 0L;
      onChanged();
      return this;
    }
    
    public Builder clearEventNumber() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.eventNumber_ = 0;
      onChanged();
      return this;
    }
    
    public Builder clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return (Builder)super.clearField(param1FieldDescriptor);
    }
    
    public Builder clearId() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.id_ = 0L;
      onChanged();
      return this;
    }
    
    public Builder clearInserttime() {
      this.bitField0_ &= 0xFFFFFFBF;
      this.inserttime_ = 0L;
      onChanged();
      return this;
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return (Builder)super.clearOneof(param1OneofDescriptor);
    }
    
    public Builder clearPlatform() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.platform_ = 0;
      onChanged();
      return this;
    }
    
    public Builder clearUser() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.user_ = 0L;
      onChanged();
      return this;
    }
    
    public Builder clearValue() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.value_ = MobilusModel.Event.getDefaultInstance().getValue();
      onChanged();
      return this;
    }
    
    public Builder clone() {
      return (Builder)super.clone();
    }
    
    public MobilusModel.Event getDefaultInstanceForType() {
      return MobilusModel.Event.getDefaultInstance();
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return MobilusModel.internal_static_Event_descriptor;
    }
    
    public long getDeviceId() {
      return this.deviceId_;
    }
    
    public int getEventNumber() {
      return this.eventNumber_;
    }
    
    public long getId() {
      return this.id_;
    }
    
    public long getInserttime() {
      return this.inserttime_;
    }
    
    public int getPlatform() {
      return this.platform_;
    }
    
    public long getUser() {
      return this.user_;
    }
    
    public String getValue() {
      Object object = this.value_;
      if (!(object instanceof String)) {
        ByteString byteString = (ByteString)object;
        object = byteString.toStringUtf8();
        if (byteString.isValidUtf8())
          this.value_ = object; 
        return (String)object;
      } 
      return (String)object;
    }
    
    public ByteString getValueBytes() {
      Object object = this.value_;
      if (object instanceof String) {
        object = ByteString.copyFromUtf8((String)object);
        this.value_ = object;
        return (ByteString)object;
      } 
      return (ByteString)object;
    }
    
    public boolean hasDeviceId() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasEventNumber() {
      boolean bool;
      if ((this.bitField0_ & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasId() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean hasInserttime() {
      boolean bool;
      if ((this.bitField0_ & 0x40) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasPlatform() {
      boolean bool;
      if ((this.bitField0_ & 0x10) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasUser() {
      boolean bool;
      if ((this.bitField0_ & 0x20) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasValue() {
      boolean bool;
      if ((this.bitField0_ & 0x8) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_Event_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.Event.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      CodedInputStream codedInputStream = null;
      try {
        MobilusModel.Event event = (MobilusModel.Event)MobilusModel.Event.PARSER.parsePartialFrom(param1CodedInputStream, param1ExtensionRegistryLite);
        return this;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        MobilusModel.Event event = (MobilusModel.Event)invalidProtocolBufferException.getUnfinishedMessage();
      } finally {
        param1ExtensionRegistryLite = null;
      } 
      if (param1CodedInputStream != null)
        mergeFrom((MobilusModel.Event)param1CodedInputStream); 
      throw param1ExtensionRegistryLite;
    }
    
    public Builder mergeFrom(Message param1Message) {
      if (param1Message instanceof MobilusModel.Event)
        return mergeFrom((MobilusModel.Event)param1Message); 
      super.mergeFrom(param1Message);
      return this;
    }
    
    public Builder mergeFrom(MobilusModel.Event param1Event) {
      if (param1Event == MobilusModel.Event.getDefaultInstance())
        return this; 
      if (param1Event.hasId())
        setId(param1Event.getId()); 
      if (param1Event.hasDeviceId())
        setDeviceId(param1Event.getDeviceId()); 
      if (param1Event.hasEventNumber())
        setEventNumber(param1Event.getEventNumber()); 
      if (param1Event.hasValue()) {
        this.bitField0_ |= 0x8;
        this.value_ = param1Event.value_;
        onChanged();
      } 
      if (param1Event.hasPlatform())
        setPlatform(param1Event.getPlatform()); 
      if (param1Event.hasUser())
        setUser(param1Event.getUser()); 
      if (param1Event.hasInserttime())
        setInserttime(param1Event.getInserttime()); 
      mergeUnknownFields(param1Event.unknownFields);
      onChanged();
      return this;
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.mergeUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder setDeviceId(long param1Long) {
      this.bitField0_ |= 0x2;
      this.deviceId_ = param1Long;
      onChanged();
      return this;
    }
    
    public Builder setEventNumber(int param1Int) {
      this.bitField0_ |= 0x4;
      this.eventNumber_ = param1Int;
      onChanged();
      return this;
    }
    
    public Builder setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.setField(param1FieldDescriptor, param1Object);
    }
    
    public Builder setId(long param1Long) {
      this.bitField0_ |= 0x1;
      this.id_ = param1Long;
      onChanged();
      return this;
    }
    
    public Builder setInserttime(long param1Long) {
      this.bitField0_ |= 0x40;
      this.inserttime_ = param1Long;
      onChanged();
      return this;
    }
    
    public Builder setPlatform(int param1Int) {
      this.bitField0_ |= 0x10;
      this.platform_ = param1Int;
      onChanged();
      return this;
    }
    
    public Builder setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object) {
      return (Builder)super.setRepeatedField(param1FieldDescriptor, param1Int, param1Object);
    }
    
    public final Builder setUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.setUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder setUser(long param1Long) {
      this.bitField0_ |= 0x20;
      this.user_ = param1Long;
      onChanged();
      return this;
    }
    
    public Builder setValue(String param1String) {
      if (param1String == null)
        throw new NullPointerException(); 
      this.bitField0_ |= 0x8;
      this.value_ = param1String;
      onChanged();
      return this;
    }
    
    public Builder setValueBytes(ByteString param1ByteString) {
      if (param1ByteString == null)
        throw new NullPointerException(); 
      this.bitField0_ |= 0x8;
      this.value_ = param1ByteString;
      onChanged();
      return this;
    }
  }
  
  public static interface EventOrBuilder extends MessageOrBuilder {
    long getDeviceId();
    
    int getEventNumber();
    
    long getId();
    
    long getInserttime();
    
    int getPlatform();
    
    long getUser();
    
    String getValue();
    
    ByteString getValueBytes();
    
    boolean hasDeviceId();
    
    boolean hasEventNumber();
    
    boolean hasId();
    
    boolean hasInserttime();
    
    boolean hasPlatform();
    
    boolean hasUser();
    
    boolean hasValue();
  }
  
  public static final class IdPair extends GeneratedMessageV3 implements IdPairOrBuilder {
    private static final IdPair DEFAULT_INSTANCE = new IdPair();
    
    public static final int LEFT_FIELD_NUMBER = 1;
    
    @Deprecated
    public static final Parser<IdPair> PARSER = (Parser<IdPair>)new AbstractParser<IdPair>() {
        public MobilusModel.IdPair parsePartialFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws InvalidProtocolBufferException {
          return new MobilusModel.IdPair(param2CodedInputStream, param2ExtensionRegistryLite);
        }
      };
    
    public static final int RIGHT_FIELD_NUMBER = 2;
    
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    private long left_;
    
    private byte memoizedIsInitialized = (byte)-1;
    
    private long right_;
    
    private IdPair() {}
    
    private IdPair(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      this();
      if (param1ExtensionRegistryLite == null)
        throw new NullPointerException(); 
      UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
      for (boolean bool = false; !bool; bool = true) {
        try {
          int i = param1CodedInputStream.readTag();
          if (i != 0) {
            if (i != 8) {
              if (i != 16) {
                if (!parseUnknownField(param1CodedInputStream, builder, param1ExtensionRegistryLite, i))
                  continue; 
                continue;
              } 
              this.bitField0_ |= 0x2;
              this.right_ = param1CodedInputStream.readInt64();
              continue;
            } 
            this.bitField0_ |= 0x1;
            this.left_ = param1CodedInputStream.readInt64();
            continue;
          } 
          continue;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } catch (IOException iOException) {
          InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException();
          this(iOException);
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } finally {}
        this.unknownFields = builder.build();
        makeExtensionsImmutable();
        throw param1CodedInputStream;
      } 
      this.unknownFields = builder.build();
      makeExtensionsImmutable();
    }
    
    private IdPair(GeneratedMessageV3.Builder<?> param1Builder) {
      super(param1Builder);
    }
    
    public static IdPair getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_IdPair_descriptor;
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(IdPair param1IdPair) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(param1IdPair);
    }
    
    public static IdPair parseDelimitedFrom(InputStream param1InputStream) throws IOException {
      return (IdPair)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream);
    }
    
    public static IdPair parseDelimitedFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (IdPair)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static IdPair parseFrom(ByteString param1ByteString) throws InvalidProtocolBufferException {
      return (IdPair)PARSER.parseFrom(param1ByteString);
    }
    
    public static IdPair parseFrom(ByteString param1ByteString, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (IdPair)PARSER.parseFrom(param1ByteString, param1ExtensionRegistryLite);
    }
    
    public static IdPair parseFrom(CodedInputStream param1CodedInputStream) throws IOException {
      return (IdPair)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream);
    }
    
    public static IdPair parseFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (IdPair)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream, param1ExtensionRegistryLite);
    }
    
    public static IdPair parseFrom(InputStream param1InputStream) throws IOException {
      return (IdPair)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream);
    }
    
    public static IdPair parseFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (IdPair)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static IdPair parseFrom(ByteBuffer param1ByteBuffer) throws InvalidProtocolBufferException {
      return (IdPair)PARSER.parseFrom(param1ByteBuffer);
    }
    
    public static IdPair parseFrom(ByteBuffer param1ByteBuffer, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (IdPair)PARSER.parseFrom(param1ByteBuffer, param1ExtensionRegistryLite);
    }
    
    public static IdPair parseFrom(byte[] param1ArrayOfbyte) throws InvalidProtocolBufferException {
      return (IdPair)PARSER.parseFrom(param1ArrayOfbyte);
    }
    
    public static IdPair parseFrom(byte[] param1ArrayOfbyte, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (IdPair)PARSER.parseFrom(param1ArrayOfbyte, param1ExtensionRegistryLite);
    }
    
    public static Parser<IdPair> parser() {
      return PARSER;
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object == this)
        return true; 
      if (!(param1Object instanceof IdPair))
        return super.equals(param1Object); 
      param1Object = param1Object;
      return (hasLeft() != param1Object.hasLeft()) ? false : ((hasLeft() && getLeft() != param1Object.getLeft()) ? false : ((hasRight() != param1Object.hasRight()) ? false : ((hasRight() && getRight() != param1Object.getRight()) ? false : (!!this.unknownFields.equals(((IdPair)param1Object).unknownFields)))));
    }
    
    public IdPair getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
    
    public long getLeft() {
      return this.left_;
    }
    
    public Parser<IdPair> getParserForType() {
      return PARSER;
    }
    
    public long getRight() {
      return this.right_;
    }
    
    public int getSerializedSize() {
      int i = this.memoizedSize;
      if (i != -1)
        return i; 
      i = 0;
      if ((this.bitField0_ & 0x1) != 0)
        i = 0 + CodedOutputStream.computeInt64Size(1, this.left_); 
      int j = i;
      if ((this.bitField0_ & 0x2) != 0)
        j = i + CodedOutputStream.computeInt64Size(2, this.right_); 
      i = j + this.unknownFields.getSerializedSize();
      this.memoizedSize = i;
      return i;
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    public boolean hasLeft() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean hasRight() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int hashCode() {
      if (this.memoizedHashCode != 0)
        return this.memoizedHashCode; 
      int i = 779 + getDescriptor().hashCode();
      int j = i;
      if (hasLeft())
        j = (i * 37 + 1) * 53 + Internal.hashLong(getLeft()); 
      i = j;
      if (hasRight())
        i = (j * 37 + 2) * 53 + Internal.hashLong(getRight()); 
      j = i * 29 + this.unknownFields.hashCode();
      this.memoizedHashCode = j;
      return j;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_IdPair_fieldAccessorTable.ensureFieldAccessorsInitialized(IdPair.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      byte b = this.memoizedIsInitialized;
      if (b == 1)
        return true; 
      if (b == 0)
        return false; 
      this.memoizedIsInitialized = (byte)1;
      return true;
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      return new Builder(param1BuilderParent);
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter param1UnusedPrivateParameter) {
      return new IdPair();
    }
    
    public Builder toBuilder() {
      Builder builder;
      if (this == DEFAULT_INSTANCE) {
        builder = new Builder();
      } else {
        builder = (new Builder()).mergeFrom(this);
      } 
      return builder;
    }
    
    public void writeTo(CodedOutputStream param1CodedOutputStream) throws IOException {
      if ((this.bitField0_ & 0x1) != 0)
        param1CodedOutputStream.writeInt64(1, this.left_); 
      if ((this.bitField0_ & 0x2) != 0)
        param1CodedOutputStream.writeInt64(2, this.right_); 
      this.unknownFields.writeTo(param1CodedOutputStream);
    }
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MobilusModel.IdPairOrBuilder {
      private int bitField0_;
      
      private long left_;
      
      private long right_;
      
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent param2BuilderParent) {
        super(param2BuilderParent);
        maybeForceBuilderInitialization();
      }
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MobilusModel.internal_static_IdPair_descriptor;
      }
      
      private void maybeForceBuilderInitialization() {
        MobilusModel.IdPair.alwaysUseFieldBuilders;
      }
      
      public Builder addRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.addRepeatedField(param2FieldDescriptor, param2Object);
      }
      
      public MobilusModel.IdPair build() {
        MobilusModel.IdPair idPair = buildPartial();
        if (!idPair.isInitialized())
          throw newUninitializedMessageException(idPair); 
        return idPair;
      }
      
      public MobilusModel.IdPair buildPartial() {
        byte b;
        MobilusModel.IdPair idPair = new MobilusModel.IdPair(this);
        int i = this.bitField0_;
        if ((i & 0x1) != 0) {
          MobilusModel.IdPair.access$17802(idPair, this.left_);
          b = 1;
        } else {
          b = 0;
        } 
        int j = b;
        if ((i & 0x2) != 0) {
          MobilusModel.IdPair.access$17902(idPair, this.right_);
          j = b | 0x2;
        } 
        MobilusModel.IdPair.access$18002(idPair, j);
        onBuilt();
        return idPair;
      }
      
      public Builder clear() {
        super.clear();
        this.left_ = 0L;
        this.bitField0_ &= 0xFFFFFFFE;
        this.right_ = 0L;
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
      }
      
      public Builder clearField(Descriptors.FieldDescriptor param2FieldDescriptor) {
        return (Builder)super.clearField(param2FieldDescriptor);
      }
      
      public Builder clearLeft() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.left_ = 0L;
        onChanged();
        return this;
      }
      
      public Builder clearOneof(Descriptors.OneofDescriptor param2OneofDescriptor) {
        return (Builder)super.clearOneof(param2OneofDescriptor);
      }
      
      public Builder clearRight() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.right_ = 0L;
        onChanged();
        return this;
      }
      
      public Builder clone() {
        return (Builder)super.clone();
      }
      
      public MobilusModel.IdPair getDefaultInstanceForType() {
        return MobilusModel.IdPair.getDefaultInstance();
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MobilusModel.internal_static_IdPair_descriptor;
      }
      
      public long getLeft() {
        return this.left_;
      }
      
      public long getRight() {
        return this.right_;
      }
      
      public boolean hasLeft() {
        int i = this.bitField0_;
        boolean bool = true;
        if ((i & 0x1) == 0)
          bool = false; 
        return bool;
      }
      
      public boolean hasRight() {
        boolean bool;
        if ((this.bitField0_ & 0x2) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MobilusModel.internal_static_IdPair_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.IdPair.class, Builder.class);
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws IOException {
        ExtensionRegistryLite extensionRegistryLite = null;
        try {
          MobilusModel.IdPair idPair = (MobilusModel.IdPair)MobilusModel.IdPair.PARSER.parsePartialFrom(param2CodedInputStream, param2ExtensionRegistryLite);
          return this;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          MobilusModel.IdPair idPair = (MobilusModel.IdPair)invalidProtocolBufferException.getUnfinishedMessage();
        } finally {
          param2CodedInputStream = null;
        } 
        if (param2ExtensionRegistryLite != null)
          mergeFrom((MobilusModel.IdPair)param2ExtensionRegistryLite); 
        throw param2CodedInputStream;
      }
      
      public Builder mergeFrom(Message param2Message) {
        if (param2Message instanceof MobilusModel.IdPair)
          return mergeFrom((MobilusModel.IdPair)param2Message); 
        super.mergeFrom(param2Message);
        return this;
      }
      
      public Builder mergeFrom(MobilusModel.IdPair param2IdPair) {
        if (param2IdPair == MobilusModel.IdPair.getDefaultInstance())
          return this; 
        if (param2IdPair.hasLeft())
          setLeft(param2IdPair.getLeft()); 
        if (param2IdPair.hasRight())
          setRight(param2IdPair.getRight()); 
        mergeUnknownFields(param2IdPair.unknownFields);
        onChanged();
        return this;
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.mergeUnknownFields(param2UnknownFieldSet);
      }
      
      public Builder setField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.setField(param2FieldDescriptor, param2Object);
      }
      
      public Builder setLeft(long param2Long) {
        this.bitField0_ |= 0x1;
        this.left_ = param2Long;
        onChanged();
        return this;
      }
      
      public Builder setRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, int param2Int, Object param2Object) {
        return (Builder)super.setRepeatedField(param2FieldDescriptor, param2Int, param2Object);
      }
      
      public Builder setRight(long param2Long) {
        this.bitField0_ |= 0x2;
        this.right_ = param2Long;
        onChanged();
        return this;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.setUnknownFields(param2UnknownFieldSet);
      }
    }
  }
  
  static final class null extends AbstractParser<IdPair> {
    public MobilusModel.IdPair parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return new MobilusModel.IdPair(param1CodedInputStream, param1ExtensionRegistryLite);
    }
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<IdPair.Builder> implements IdPairOrBuilder {
    private int bitField0_;
    
    private long left_;
    
    private long right_;
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      super(param1BuilderParent);
      maybeForceBuilderInitialization();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_IdPair_descriptor;
    }
    
    private void maybeForceBuilderInitialization() {
      MobilusModel.IdPair.alwaysUseFieldBuilders;
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.addRepeatedField(param1FieldDescriptor, param1Object);
    }
    
    public MobilusModel.IdPair build() {
      MobilusModel.IdPair idPair = buildPartial();
      if (!idPair.isInitialized())
        throw newUninitializedMessageException(idPair); 
      return idPair;
    }
    
    public MobilusModel.IdPair buildPartial() {
      byte b;
      MobilusModel.IdPair idPair = new MobilusModel.IdPair(this);
      int i = this.bitField0_;
      if ((i & 0x1) != 0) {
        MobilusModel.IdPair.access$17802(idPair, this.left_);
        b = 1;
      } else {
        b = 0;
      } 
      int j = b;
      if ((i & 0x2) != 0) {
        MobilusModel.IdPair.access$17902(idPair, this.right_);
        j = b | 0x2;
      } 
      MobilusModel.IdPair.access$18002(idPair, j);
      onBuilt();
      return idPair;
    }
    
    public Builder clear() {
      super.clear();
      this.left_ = 0L;
      this.bitField0_ &= 0xFFFFFFFE;
      this.right_ = 0L;
      this.bitField0_ &= 0xFFFFFFFD;
      return this;
    }
    
    public Builder clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return (Builder)super.clearField(param1FieldDescriptor);
    }
    
    public Builder clearLeft() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.left_ = 0L;
      onChanged();
      return this;
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return (Builder)super.clearOneof(param1OneofDescriptor);
    }
    
    public Builder clearRight() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.right_ = 0L;
      onChanged();
      return this;
    }
    
    public Builder clone() {
      return (Builder)super.clone();
    }
    
    public MobilusModel.IdPair getDefaultInstanceForType() {
      return MobilusModel.IdPair.getDefaultInstance();
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return MobilusModel.internal_static_IdPair_descriptor;
    }
    
    public long getLeft() {
      return this.left_;
    }
    
    public long getRight() {
      return this.right_;
    }
    
    public boolean hasLeft() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean hasRight() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_IdPair_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.IdPair.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      ExtensionRegistryLite extensionRegistryLite = null;
      try {
        MobilusModel.IdPair idPair = (MobilusModel.IdPair)MobilusModel.IdPair.PARSER.parsePartialFrom(param1CodedInputStream, param1ExtensionRegistryLite);
        return this;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        MobilusModel.IdPair idPair = (MobilusModel.IdPair)invalidProtocolBufferException.getUnfinishedMessage();
      } finally {
        param1CodedInputStream = null;
      } 
      if (param1ExtensionRegistryLite != null)
        mergeFrom((MobilusModel.IdPair)param1ExtensionRegistryLite); 
      throw param1CodedInputStream;
    }
    
    public Builder mergeFrom(Message param1Message) {
      if (param1Message instanceof MobilusModel.IdPair)
        return mergeFrom((MobilusModel.IdPair)param1Message); 
      super.mergeFrom(param1Message);
      return this;
    }
    
    public Builder mergeFrom(MobilusModel.IdPair param1IdPair) {
      if (param1IdPair == MobilusModel.IdPair.getDefaultInstance())
        return this; 
      if (param1IdPair.hasLeft())
        setLeft(param1IdPair.getLeft()); 
      if (param1IdPair.hasRight())
        setRight(param1IdPair.getRight()); 
      mergeUnknownFields(param1IdPair.unknownFields);
      onChanged();
      return this;
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.mergeUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.setField(param1FieldDescriptor, param1Object);
    }
    
    public Builder setLeft(long param1Long) {
      this.bitField0_ |= 0x1;
      this.left_ = param1Long;
      onChanged();
      return this;
    }
    
    public Builder setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object) {
      return (Builder)super.setRepeatedField(param1FieldDescriptor, param1Int, param1Object);
    }
    
    public Builder setRight(long param1Long) {
      this.bitField0_ |= 0x2;
      this.right_ = param1Long;
      onChanged();
      return this;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.setUnknownFields(param1UnknownFieldSet);
    }
  }
  
  public static interface IdPairOrBuilder extends MessageOrBuilder {
    long getLeft();
    
    long getRight();
    
    boolean hasLeft();
    
    boolean hasRight();
  }
  
  public static final class Scene extends GeneratedMessageV3 implements SceneOrBuilder {
    private static final Scene DEFAULT_INSTANCE = new Scene();
    
    public static final int ENABLED_FIELD_NUMBER = 7;
    
    public static final int ICON_FIELD_NUMBER = 3;
    
    public static final int ID_FIELD_NUMBER = 1;
    
    public static final int NAME_FIELD_NUMBER = 2;
    
    @Deprecated
    public static final Parser<Scene> PARSER = (Parser<Scene>)new AbstractParser<Scene>() {
        public MobilusModel.Scene parsePartialFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws InvalidProtocolBufferException {
          return new MobilusModel.Scene(param2CodedInputStream, param2ExtensionRegistryLite);
        }
      };
    
    public static final int SCENE_ASTRAL_SCHEDULES_FIELD_NUMBER = 6;
    
    public static final int SCENE_EVENTS_FIELD_NUMBER = 4;
    
    public static final int SCENE_WEEK_SCHEDULES_FIELD_NUMBER = 5;
    
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    private boolean enabled_;
    
    private int icon_;
    
    private long id_;
    
    private byte memoizedIsInitialized = (byte)-1;
    
    private volatile Object name_;
    
    private List<MobilusModel.SceneAstralSchedule> sceneAstralSchedules_;
    
    private List<MobilusModel.SceneEvent> sceneEvents_;
    
    private List<MobilusModel.SceneWeekSchedule> sceneWeekSchedules_;
    
    private Scene() {
      this.name_ = "";
      this.sceneEvents_ = Collections.emptyList();
      this.sceneWeekSchedules_ = Collections.emptyList();
      this.sceneAstralSchedules_ = Collections.emptyList();
    }
    
    private Scene(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      this();
      if (param1ExtensionRegistryLite == null)
        throw new NullPointerException(); 
      UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
      boolean bool = false;
      int i = 0;
      while (!bool) {
        int j = i;
        int k = i;
        int m = i;
        try {
          int n = param1CodedInputStream.readTag();
          if (n != 0) {
            if (n != 8) {
              if (n != 18) {
                if (n != 24) {
                  if (n != 34) {
                    if (n != 42) {
                      if (n != 50) {
                        if (n != 56) {
                          j = i;
                          k = i;
                          m = i;
                          if (!parseUnknownField(param1CodedInputStream, builder, param1ExtensionRegistryLite, n))
                            continue; 
                          continue;
                        } 
                        j = i;
                        k = i;
                        m = i;
                        this.bitField0_ |= 0x8;
                        j = i;
                        k = i;
                        m = i;
                        this.enabled_ = param1CodedInputStream.readBool();
                        continue;
                      } 
                      n = i;
                      if ((i & 0x20) == 0) {
                        j = i;
                        k = i;
                        m = i;
                        ArrayList<MobilusModel.SceneAstralSchedule> arrayList = new ArrayList();
                        j = i;
                        k = i;
                        m = i;
                        this();
                        j = i;
                        k = i;
                        m = i;
                        this.sceneAstralSchedules_ = arrayList;
                        n = i | 0x20;
                      } 
                      j = n;
                      k = n;
                      m = n;
                      this.sceneAstralSchedules_.add(param1CodedInputStream.readMessage(MobilusModel.SceneAstralSchedule.PARSER, param1ExtensionRegistryLite));
                      i = n;
                      continue;
                    } 
                    n = i;
                    if ((i & 0x10) == 0) {
                      j = i;
                      k = i;
                      m = i;
                      ArrayList<MobilusModel.SceneWeekSchedule> arrayList = new ArrayList();
                      j = i;
                      k = i;
                      m = i;
                      this();
                      j = i;
                      k = i;
                      m = i;
                      this.sceneWeekSchedules_ = arrayList;
                      n = i | 0x10;
                    } 
                    j = n;
                    k = n;
                    m = n;
                    this.sceneWeekSchedules_.add(param1CodedInputStream.readMessage(MobilusModel.SceneWeekSchedule.PARSER, param1ExtensionRegistryLite));
                    i = n;
                    continue;
                  } 
                  n = i;
                  if ((i & 0x8) == 0) {
                    j = i;
                    k = i;
                    m = i;
                    ArrayList<MobilusModel.SceneEvent> arrayList = new ArrayList();
                    j = i;
                    k = i;
                    m = i;
                    this();
                    j = i;
                    k = i;
                    m = i;
                    this.sceneEvents_ = arrayList;
                    n = i | 0x8;
                  } 
                  j = n;
                  k = n;
                  m = n;
                  this.sceneEvents_.add(param1CodedInputStream.readMessage(MobilusModel.SceneEvent.PARSER, param1ExtensionRegistryLite));
                  i = n;
                  continue;
                } 
                j = i;
                k = i;
                m = i;
                this.bitField0_ |= 0x4;
                j = i;
                k = i;
                m = i;
                this.icon_ = param1CodedInputStream.readInt32();
                continue;
              } 
              j = i;
              k = i;
              m = i;
              ByteString byteString = param1CodedInputStream.readBytes();
              j = i;
              k = i;
              m = i;
              this.bitField0_ |= 0x2;
              j = i;
              k = i;
              m = i;
              this.name_ = byteString;
              continue;
            } 
            j = i;
            k = i;
            m = i;
            this.bitField0_ |= 0x1;
            j = i;
            k = i;
            m = i;
            this.id_ = param1CodedInputStream.readInt64();
            continue;
          } 
          continue;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          j = m;
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } catch (IOException iOException) {
          j = k;
          InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException();
          j = k;
          this(iOException);
          j = k;
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } finally {}
        if ((j & 0x8) != 0)
          this.sceneEvents_ = Collections.unmodifiableList(this.sceneEvents_); 
        if ((j & 0x10) != 0)
          this.sceneWeekSchedules_ = Collections.unmodifiableList(this.sceneWeekSchedules_); 
        if ((j & 0x20) != 0)
          this.sceneAstralSchedules_ = Collections.unmodifiableList(this.sceneAstralSchedules_); 
        this.unknownFields = builder.build();
        makeExtensionsImmutable();
        throw param1CodedInputStream;
        bool = true;
      } 
      if ((i & 0x8) != 0)
        this.sceneEvents_ = Collections.unmodifiableList(this.sceneEvents_); 
      if ((i & 0x10) != 0)
        this.sceneWeekSchedules_ = Collections.unmodifiableList(this.sceneWeekSchedules_); 
      if ((i & 0x20) != 0)
        this.sceneAstralSchedules_ = Collections.unmodifiableList(this.sceneAstralSchedules_); 
      this.unknownFields = builder.build();
      makeExtensionsImmutable();
    }
    
    private Scene(GeneratedMessageV3.Builder<?> param1Builder) {
      super(param1Builder);
    }
    
    public static Scene getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_Scene_descriptor;
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(Scene param1Scene) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(param1Scene);
    }
    
    public static Scene parseDelimitedFrom(InputStream param1InputStream) throws IOException {
      return (Scene)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream);
    }
    
    public static Scene parseDelimitedFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (Scene)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static Scene parseFrom(ByteString param1ByteString) throws InvalidProtocolBufferException {
      return (Scene)PARSER.parseFrom(param1ByteString);
    }
    
    public static Scene parseFrom(ByteString param1ByteString, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (Scene)PARSER.parseFrom(param1ByteString, param1ExtensionRegistryLite);
    }
    
    public static Scene parseFrom(CodedInputStream param1CodedInputStream) throws IOException {
      return (Scene)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream);
    }
    
    public static Scene parseFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (Scene)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream, param1ExtensionRegistryLite);
    }
    
    public static Scene parseFrom(InputStream param1InputStream) throws IOException {
      return (Scene)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream);
    }
    
    public static Scene parseFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (Scene)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static Scene parseFrom(ByteBuffer param1ByteBuffer) throws InvalidProtocolBufferException {
      return (Scene)PARSER.parseFrom(param1ByteBuffer);
    }
    
    public static Scene parseFrom(ByteBuffer param1ByteBuffer, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (Scene)PARSER.parseFrom(param1ByteBuffer, param1ExtensionRegistryLite);
    }
    
    public static Scene parseFrom(byte[] param1ArrayOfbyte) throws InvalidProtocolBufferException {
      return (Scene)PARSER.parseFrom(param1ArrayOfbyte);
    }
    
    public static Scene parseFrom(byte[] param1ArrayOfbyte, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (Scene)PARSER.parseFrom(param1ArrayOfbyte, param1ExtensionRegistryLite);
    }
    
    public static Parser<Scene> parser() {
      return PARSER;
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object == this)
        return true; 
      if (!(param1Object instanceof Scene))
        return super.equals(param1Object); 
      param1Object = param1Object;
      return (hasId() != param1Object.hasId()) ? false : ((hasId() && getId() != param1Object.getId()) ? false : ((hasName() != param1Object.hasName()) ? false : ((hasName() && !getName().equals(param1Object.getName())) ? false : ((hasIcon() != param1Object.hasIcon()) ? false : ((hasIcon() && getIcon() != param1Object.getIcon()) ? false : (!getSceneEventsList().equals(param1Object.getSceneEventsList()) ? false : (!getSceneWeekSchedulesList().equals(param1Object.getSceneWeekSchedulesList()) ? false : (!getSceneAstralSchedulesList().equals(param1Object.getSceneAstralSchedulesList()) ? false : ((hasEnabled() != param1Object.hasEnabled()) ? false : ((hasEnabled() && getEnabled() != param1Object.getEnabled()) ? false : (!!this.unknownFields.equals(((Scene)param1Object).unknownFields))))))))))));
    }
    
    public Scene getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
    
    public boolean getEnabled() {
      return this.enabled_;
    }
    
    public int getIcon() {
      return this.icon_;
    }
    
    public long getId() {
      return this.id_;
    }
    
    public String getName() {
      Object object = this.name_;
      if (object instanceof String)
        return (String)object; 
      object = object;
      String str = object.toStringUtf8();
      if (object.isValidUtf8())
        this.name_ = str; 
      return str;
    }
    
    public ByteString getNameBytes() {
      Object object = this.name_;
      if (object instanceof String) {
        object = ByteString.copyFromUtf8((String)object);
        this.name_ = object;
        return (ByteString)object;
      } 
      return (ByteString)object;
    }
    
    public Parser<Scene> getParserForType() {
      return PARSER;
    }
    
    public MobilusModel.SceneAstralSchedule getSceneAstralSchedules(int param1Int) {
      return this.sceneAstralSchedules_.get(param1Int);
    }
    
    public int getSceneAstralSchedulesCount() {
      return this.sceneAstralSchedules_.size();
    }
    
    public List<MobilusModel.SceneAstralSchedule> getSceneAstralSchedulesList() {
      return this.sceneAstralSchedules_;
    }
    
    public MobilusModel.SceneAstralScheduleOrBuilder getSceneAstralSchedulesOrBuilder(int param1Int) {
      return this.sceneAstralSchedules_.get(param1Int);
    }
    
    public List<? extends MobilusModel.SceneAstralScheduleOrBuilder> getSceneAstralSchedulesOrBuilderList() {
      return (List)this.sceneAstralSchedules_;
    }
    
    public MobilusModel.SceneEvent getSceneEvents(int param1Int) {
      return this.sceneEvents_.get(param1Int);
    }
    
    public int getSceneEventsCount() {
      return this.sceneEvents_.size();
    }
    
    public List<MobilusModel.SceneEvent> getSceneEventsList() {
      return this.sceneEvents_;
    }
    
    public MobilusModel.SceneEventOrBuilder getSceneEventsOrBuilder(int param1Int) {
      return this.sceneEvents_.get(param1Int);
    }
    
    public List<? extends MobilusModel.SceneEventOrBuilder> getSceneEventsOrBuilderList() {
      return (List)this.sceneEvents_;
    }
    
    public MobilusModel.SceneWeekSchedule getSceneWeekSchedules(int param1Int) {
      return this.sceneWeekSchedules_.get(param1Int);
    }
    
    public int getSceneWeekSchedulesCount() {
      return this.sceneWeekSchedules_.size();
    }
    
    public List<MobilusModel.SceneWeekSchedule> getSceneWeekSchedulesList() {
      return this.sceneWeekSchedules_;
    }
    
    public MobilusModel.SceneWeekScheduleOrBuilder getSceneWeekSchedulesOrBuilder(int param1Int) {
      return this.sceneWeekSchedules_.get(param1Int);
    }
    
    public List<? extends MobilusModel.SceneWeekScheduleOrBuilder> getSceneWeekSchedulesOrBuilderList() {
      return (List)this.sceneWeekSchedules_;
    }
    
    public int getSerializedSize() {
      byte b3;
      int i = this.memoizedSize;
      if (i != -1)
        return i; 
      i = this.bitField0_;
      byte b1 = 0;
      if ((i & 0x1) != 0) {
        j = CodedOutputStream.computeInt64Size(1, this.id_) + 0;
      } else {
        j = 0;
      } 
      i = j;
      if ((this.bitField0_ & 0x2) != 0)
        i = j + GeneratedMessageV3.computeStringSize(2, this.name_); 
      int j = i;
      if ((this.bitField0_ & 0x4) != 0)
        j = i + CodedOutputStream.computeInt32Size(3, this.icon_); 
      for (i = 0; i < this.sceneEvents_.size(); i++)
        j += CodedOutputStream.computeMessageSize(4, (MessageLite)this.sceneEvents_.get(i)); 
      byte b2 = 0;
      while (true) {
        i = j;
        b3 = b1;
        if (b2 < this.sceneWeekSchedules_.size()) {
          j += CodedOutputStream.computeMessageSize(5, (MessageLite)this.sceneWeekSchedules_.get(b2));
          b2++;
          continue;
        } 
        break;
      } 
      while (b3 < this.sceneAstralSchedules_.size()) {
        i += CodedOutputStream.computeMessageSize(6, (MessageLite)this.sceneAstralSchedules_.get(b3));
        b3++;
      } 
      j = i;
      if ((this.bitField0_ & 0x8) != 0)
        j = i + CodedOutputStream.computeBoolSize(7, this.enabled_); 
      i = j + this.unknownFields.getSerializedSize();
      this.memoizedSize = i;
      return i;
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    public boolean hasEnabled() {
      boolean bool;
      if ((this.bitField0_ & 0x8) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasIcon() {
      boolean bool;
      if ((this.bitField0_ & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasId() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean hasName() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int hashCode() {
      if (this.memoizedHashCode != 0)
        return this.memoizedHashCode; 
      int i = 779 + getDescriptor().hashCode();
      int j = i;
      if (hasId())
        j = (i * 37 + 1) * 53 + Internal.hashLong(getId()); 
      i = j;
      if (hasName())
        i = (j * 37 + 2) * 53 + getName().hashCode(); 
      j = i;
      if (hasIcon())
        j = (i * 37 + 3) * 53 + getIcon(); 
      i = j;
      if (getSceneEventsCount() > 0)
        i = (j * 37 + 4) * 53 + getSceneEventsList().hashCode(); 
      j = i;
      if (getSceneWeekSchedulesCount() > 0)
        j = (i * 37 + 5) * 53 + getSceneWeekSchedulesList().hashCode(); 
      i = j;
      if (getSceneAstralSchedulesCount() > 0)
        i = (j * 37 + 6) * 53 + getSceneAstralSchedulesList().hashCode(); 
      j = i;
      if (hasEnabled())
        j = (i * 37 + 7) * 53 + Internal.hashBoolean(getEnabled()); 
      i = j * 29 + this.unknownFields.hashCode();
      this.memoizedHashCode = i;
      return i;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_Scene_fieldAccessorTable.ensureFieldAccessorsInitialized(Scene.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      byte b = this.memoizedIsInitialized;
      if (b == 1)
        return true; 
      if (b == 0)
        return false; 
      this.memoizedIsInitialized = (byte)1;
      return true;
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      return new Builder(param1BuilderParent);
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter param1UnusedPrivateParameter) {
      return new Scene();
    }
    
    public Builder toBuilder() {
      Builder builder;
      if (this == DEFAULT_INSTANCE) {
        builder = new Builder();
      } else {
        builder = (new Builder()).mergeFrom(this);
      } 
      return builder;
    }
    
    public void writeTo(CodedOutputStream param1CodedOutputStream) throws IOException {
      byte b3;
      if ((this.bitField0_ & 0x1) != 0)
        param1CodedOutputStream.writeInt64(1, this.id_); 
      if ((this.bitField0_ & 0x2) != 0)
        GeneratedMessageV3.writeString(param1CodedOutputStream, 2, this.name_); 
      if ((this.bitField0_ & 0x4) != 0)
        param1CodedOutputStream.writeInt32(3, this.icon_); 
      byte b1 = 0;
      byte b2;
      for (b2 = 0; b2 < this.sceneEvents_.size(); b2++)
        param1CodedOutputStream.writeMessage(4, (MessageLite)this.sceneEvents_.get(b2)); 
      b2 = 0;
      while (true) {
        b3 = b1;
        if (b2 < this.sceneWeekSchedules_.size()) {
          param1CodedOutputStream.writeMessage(5, (MessageLite)this.sceneWeekSchedules_.get(b2));
          b2++;
          continue;
        } 
        break;
      } 
      while (b3 < this.sceneAstralSchedules_.size()) {
        param1CodedOutputStream.writeMessage(6, (MessageLite)this.sceneAstralSchedules_.get(b3));
        b3++;
      } 
      if ((this.bitField0_ & 0x8) != 0)
        param1CodedOutputStream.writeBool(7, this.enabled_); 
      this.unknownFields.writeTo(param1CodedOutputStream);
    }
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MobilusModel.SceneOrBuilder {
      private int bitField0_;
      
      private boolean enabled_;
      
      private int icon_;
      
      private long id_;
      
      private Object name_ = "";
      
      private RepeatedFieldBuilderV3<MobilusModel.SceneAstralSchedule, MobilusModel.SceneAstralSchedule.Builder, MobilusModel.SceneAstralScheduleOrBuilder> sceneAstralSchedulesBuilder_;
      
      private List<MobilusModel.SceneAstralSchedule> sceneAstralSchedules_ = Collections.emptyList();
      
      private RepeatedFieldBuilderV3<MobilusModel.SceneEvent, MobilusModel.SceneEvent.Builder, MobilusModel.SceneEventOrBuilder> sceneEventsBuilder_;
      
      private List<MobilusModel.SceneEvent> sceneEvents_ = Collections.emptyList();
      
      private RepeatedFieldBuilderV3<MobilusModel.SceneWeekSchedule, MobilusModel.SceneWeekSchedule.Builder, MobilusModel.SceneWeekScheduleOrBuilder> sceneWeekSchedulesBuilder_;
      
      private List<MobilusModel.SceneWeekSchedule> sceneWeekSchedules_ = Collections.emptyList();
      
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent param2BuilderParent) {
        super(param2BuilderParent);
        maybeForceBuilderInitialization();
      }
      
      private void ensureSceneAstralSchedulesIsMutable() {
        if ((this.bitField0_ & 0x20) == 0) {
          this.sceneAstralSchedules_ = new ArrayList<>(this.sceneAstralSchedules_);
          this.bitField0_ |= 0x20;
        } 
      }
      
      private void ensureSceneEventsIsMutable() {
        if ((this.bitField0_ & 0x8) == 0) {
          this.sceneEvents_ = new ArrayList<>(this.sceneEvents_);
          this.bitField0_ |= 0x8;
        } 
      }
      
      private void ensureSceneWeekSchedulesIsMutable() {
        if ((this.bitField0_ & 0x10) == 0) {
          this.sceneWeekSchedules_ = new ArrayList<>(this.sceneWeekSchedules_);
          this.bitField0_ |= 0x10;
        } 
      }
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MobilusModel.internal_static_Scene_descriptor;
      }
      
      private RepeatedFieldBuilderV3<MobilusModel.SceneAstralSchedule, MobilusModel.SceneAstralSchedule.Builder, MobilusModel.SceneAstralScheduleOrBuilder> getSceneAstralSchedulesFieldBuilder() {
        if (this.sceneAstralSchedulesBuilder_ == null) {
          boolean bool;
          List<MobilusModel.SceneAstralSchedule> list = this.sceneAstralSchedules_;
          if ((this.bitField0_ & 0x20) != 0) {
            bool = true;
          } else {
            bool = false;
          } 
          this.sceneAstralSchedulesBuilder_ = new RepeatedFieldBuilderV3(list, bool, (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.sceneAstralSchedules_ = null;
        } 
        return this.sceneAstralSchedulesBuilder_;
      }
      
      private RepeatedFieldBuilderV3<MobilusModel.SceneEvent, MobilusModel.SceneEvent.Builder, MobilusModel.SceneEventOrBuilder> getSceneEventsFieldBuilder() {
        if (this.sceneEventsBuilder_ == null) {
          boolean bool;
          List<MobilusModel.SceneEvent> list = this.sceneEvents_;
          if ((this.bitField0_ & 0x8) != 0) {
            bool = true;
          } else {
            bool = false;
          } 
          this.sceneEventsBuilder_ = new RepeatedFieldBuilderV3(list, bool, (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.sceneEvents_ = null;
        } 
        return this.sceneEventsBuilder_;
      }
      
      private RepeatedFieldBuilderV3<MobilusModel.SceneWeekSchedule, MobilusModel.SceneWeekSchedule.Builder, MobilusModel.SceneWeekScheduleOrBuilder> getSceneWeekSchedulesFieldBuilder() {
        if (this.sceneWeekSchedulesBuilder_ == null) {
          boolean bool;
          List<MobilusModel.SceneWeekSchedule> list = this.sceneWeekSchedules_;
          if ((this.bitField0_ & 0x10) != 0) {
            bool = true;
          } else {
            bool = false;
          } 
          this.sceneWeekSchedulesBuilder_ = new RepeatedFieldBuilderV3(list, bool, (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.sceneWeekSchedules_ = null;
        } 
        return this.sceneWeekSchedulesBuilder_;
      }
      
      private void maybeForceBuilderInitialization() {
        if (MobilusModel.Scene.alwaysUseFieldBuilders) {
          getSceneEventsFieldBuilder();
          getSceneWeekSchedulesFieldBuilder();
          getSceneAstralSchedulesFieldBuilder();
        } 
      }
      
      public Builder addAllSceneAstralSchedules(Iterable<? extends MobilusModel.SceneAstralSchedule> param2Iterable) {
        if (this.sceneAstralSchedulesBuilder_ == null) {
          ensureSceneAstralSchedulesIsMutable();
          AbstractMessageLite.Builder.addAll(param2Iterable, this.sceneAstralSchedules_);
          onChanged();
        } else {
          this.sceneAstralSchedulesBuilder_.addAllMessages(param2Iterable);
        } 
        return this;
      }
      
      public Builder addAllSceneEvents(Iterable<? extends MobilusModel.SceneEvent> param2Iterable) {
        if (this.sceneEventsBuilder_ == null) {
          ensureSceneEventsIsMutable();
          AbstractMessageLite.Builder.addAll(param2Iterable, this.sceneEvents_);
          onChanged();
        } else {
          this.sceneEventsBuilder_.addAllMessages(param2Iterable);
        } 
        return this;
      }
      
      public Builder addAllSceneWeekSchedules(Iterable<? extends MobilusModel.SceneWeekSchedule> param2Iterable) {
        if (this.sceneWeekSchedulesBuilder_ == null) {
          ensureSceneWeekSchedulesIsMutable();
          AbstractMessageLite.Builder.addAll(param2Iterable, this.sceneWeekSchedules_);
          onChanged();
        } else {
          this.sceneWeekSchedulesBuilder_.addAllMessages(param2Iterable);
        } 
        return this;
      }
      
      public Builder addRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.addRepeatedField(param2FieldDescriptor, param2Object);
      }
      
      public Builder addSceneAstralSchedules(int param2Int, MobilusModel.SceneAstralSchedule.Builder param2Builder) {
        if (this.sceneAstralSchedulesBuilder_ == null) {
          ensureSceneAstralSchedulesIsMutable();
          this.sceneAstralSchedules_.add(param2Int, param2Builder.build());
          onChanged();
        } else {
          this.sceneAstralSchedulesBuilder_.addMessage(param2Int, (AbstractMessage)param2Builder.build());
        } 
        return this;
      }
      
      public Builder addSceneAstralSchedules(int param2Int, MobilusModel.SceneAstralSchedule param2SceneAstralSchedule) {
        if (this.sceneAstralSchedulesBuilder_ == null) {
          if (param2SceneAstralSchedule == null)
            throw new NullPointerException(); 
          ensureSceneAstralSchedulesIsMutable();
          this.sceneAstralSchedules_.add(param2Int, param2SceneAstralSchedule);
          onChanged();
        } else {
          this.sceneAstralSchedulesBuilder_.addMessage(param2Int, (AbstractMessage)param2SceneAstralSchedule);
        } 
        return this;
      }
      
      public Builder addSceneAstralSchedules(MobilusModel.SceneAstralSchedule.Builder param2Builder) {
        if (this.sceneAstralSchedulesBuilder_ == null) {
          ensureSceneAstralSchedulesIsMutable();
          this.sceneAstralSchedules_.add(param2Builder.build());
          onChanged();
        } else {
          this.sceneAstralSchedulesBuilder_.addMessage((AbstractMessage)param2Builder.build());
        } 
        return this;
      }
      
      public Builder addSceneAstralSchedules(MobilusModel.SceneAstralSchedule param2SceneAstralSchedule) {
        if (this.sceneAstralSchedulesBuilder_ == null) {
          if (param2SceneAstralSchedule == null)
            throw new NullPointerException(); 
          ensureSceneAstralSchedulesIsMutable();
          this.sceneAstralSchedules_.add(param2SceneAstralSchedule);
          onChanged();
        } else {
          this.sceneAstralSchedulesBuilder_.addMessage((AbstractMessage)param2SceneAstralSchedule);
        } 
        return this;
      }
      
      public MobilusModel.SceneAstralSchedule.Builder addSceneAstralSchedulesBuilder() {
        return (MobilusModel.SceneAstralSchedule.Builder)getSceneAstralSchedulesFieldBuilder().addBuilder((AbstractMessage)MobilusModel.SceneAstralSchedule.getDefaultInstance());
      }
      
      public MobilusModel.SceneAstralSchedule.Builder addSceneAstralSchedulesBuilder(int param2Int) {
        return (MobilusModel.SceneAstralSchedule.Builder)getSceneAstralSchedulesFieldBuilder().addBuilder(param2Int, (AbstractMessage)MobilusModel.SceneAstralSchedule.getDefaultInstance());
      }
      
      public Builder addSceneEvents(int param2Int, MobilusModel.SceneEvent.Builder param2Builder) {
        if (this.sceneEventsBuilder_ == null) {
          ensureSceneEventsIsMutable();
          this.sceneEvents_.add(param2Int, param2Builder.build());
          onChanged();
        } else {
          this.sceneEventsBuilder_.addMessage(param2Int, (AbstractMessage)param2Builder.build());
        } 
        return this;
      }
      
      public Builder addSceneEvents(int param2Int, MobilusModel.SceneEvent param2SceneEvent) {
        if (this.sceneEventsBuilder_ == null) {
          if (param2SceneEvent == null)
            throw new NullPointerException(); 
          ensureSceneEventsIsMutable();
          this.sceneEvents_.add(param2Int, param2SceneEvent);
          onChanged();
        } else {
          this.sceneEventsBuilder_.addMessage(param2Int, (AbstractMessage)param2SceneEvent);
        } 
        return this;
      }
      
      public Builder addSceneEvents(MobilusModel.SceneEvent.Builder param2Builder) {
        if (this.sceneEventsBuilder_ == null) {
          ensureSceneEventsIsMutable();
          this.sceneEvents_.add(param2Builder.build());
          onChanged();
        } else {
          this.sceneEventsBuilder_.addMessage((AbstractMessage)param2Builder.build());
        } 
        return this;
      }
      
      public Builder addSceneEvents(MobilusModel.SceneEvent param2SceneEvent) {
        if (this.sceneEventsBuilder_ == null) {
          if (param2SceneEvent == null)
            throw new NullPointerException(); 
          ensureSceneEventsIsMutable();
          this.sceneEvents_.add(param2SceneEvent);
          onChanged();
        } else {
          this.sceneEventsBuilder_.addMessage((AbstractMessage)param2SceneEvent);
        } 
        return this;
      }
      
      public MobilusModel.SceneEvent.Builder addSceneEventsBuilder() {
        return (MobilusModel.SceneEvent.Builder)getSceneEventsFieldBuilder().addBuilder((AbstractMessage)MobilusModel.SceneEvent.getDefaultInstance());
      }
      
      public MobilusModel.SceneEvent.Builder addSceneEventsBuilder(int param2Int) {
        return (MobilusModel.SceneEvent.Builder)getSceneEventsFieldBuilder().addBuilder(param2Int, (AbstractMessage)MobilusModel.SceneEvent.getDefaultInstance());
      }
      
      public Builder addSceneWeekSchedules(int param2Int, MobilusModel.SceneWeekSchedule.Builder param2Builder) {
        if (this.sceneWeekSchedulesBuilder_ == null) {
          ensureSceneWeekSchedulesIsMutable();
          this.sceneWeekSchedules_.add(param2Int, param2Builder.build());
          onChanged();
        } else {
          this.sceneWeekSchedulesBuilder_.addMessage(param2Int, (AbstractMessage)param2Builder.build());
        } 
        return this;
      }
      
      public Builder addSceneWeekSchedules(int param2Int, MobilusModel.SceneWeekSchedule param2SceneWeekSchedule) {
        if (this.sceneWeekSchedulesBuilder_ == null) {
          if (param2SceneWeekSchedule == null)
            throw new NullPointerException(); 
          ensureSceneWeekSchedulesIsMutable();
          this.sceneWeekSchedules_.add(param2Int, param2SceneWeekSchedule);
          onChanged();
        } else {
          this.sceneWeekSchedulesBuilder_.addMessage(param2Int, (AbstractMessage)param2SceneWeekSchedule);
        } 
        return this;
      }
      
      public Builder addSceneWeekSchedules(MobilusModel.SceneWeekSchedule.Builder param2Builder) {
        if (this.sceneWeekSchedulesBuilder_ == null) {
          ensureSceneWeekSchedulesIsMutable();
          this.sceneWeekSchedules_.add(param2Builder.build());
          onChanged();
        } else {
          this.sceneWeekSchedulesBuilder_.addMessage((AbstractMessage)param2Builder.build());
        } 
        return this;
      }
      
      public Builder addSceneWeekSchedules(MobilusModel.SceneWeekSchedule param2SceneWeekSchedule) {
        if (this.sceneWeekSchedulesBuilder_ == null) {
          if (param2SceneWeekSchedule == null)
            throw new NullPointerException(); 
          ensureSceneWeekSchedulesIsMutable();
          this.sceneWeekSchedules_.add(param2SceneWeekSchedule);
          onChanged();
        } else {
          this.sceneWeekSchedulesBuilder_.addMessage((AbstractMessage)param2SceneWeekSchedule);
        } 
        return this;
      }
      
      public MobilusModel.SceneWeekSchedule.Builder addSceneWeekSchedulesBuilder() {
        return (MobilusModel.SceneWeekSchedule.Builder)getSceneWeekSchedulesFieldBuilder().addBuilder((AbstractMessage)MobilusModel.SceneWeekSchedule.getDefaultInstance());
      }
      
      public MobilusModel.SceneWeekSchedule.Builder addSceneWeekSchedulesBuilder(int param2Int) {
        return (MobilusModel.SceneWeekSchedule.Builder)getSceneWeekSchedulesFieldBuilder().addBuilder(param2Int, (AbstractMessage)MobilusModel.SceneWeekSchedule.getDefaultInstance());
      }
      
      public MobilusModel.Scene build() {
        MobilusModel.Scene scene = buildPartial();
        if (!scene.isInitialized())
          throw newUninitializedMessageException(scene); 
        return scene;
      }
      
      public MobilusModel.Scene buildPartial() {
        MobilusModel.Scene scene = new MobilusModel.Scene(this);
        int i = this.bitField0_;
        if ((i & 0x1) != 0) {
          MobilusModel.Scene.access$13202(scene, this.id_);
          j = 1;
        } else {
          j = 0;
        } 
        int k = j;
        if ((i & 0x2) != 0)
          k = j | 0x2; 
        MobilusModel.Scene.access$13302(scene, this.name_);
        int j = k;
        if ((i & 0x4) != 0) {
          MobilusModel.Scene.access$13402(scene, this.icon_);
          j = k | 0x4;
        } 
        if (this.sceneEventsBuilder_ == null) {
          if ((this.bitField0_ & 0x8) != 0) {
            this.sceneEvents_ = Collections.unmodifiableList(this.sceneEvents_);
            this.bitField0_ &= 0xFFFFFFF7;
          } 
          MobilusModel.Scene.access$13502(scene, this.sceneEvents_);
        } else {
          MobilusModel.Scene.access$13502(scene, this.sceneEventsBuilder_.build());
        } 
        if (this.sceneWeekSchedulesBuilder_ == null) {
          if ((this.bitField0_ & 0x10) != 0) {
            this.sceneWeekSchedules_ = Collections.unmodifiableList(this.sceneWeekSchedules_);
            this.bitField0_ &= 0xFFFFFFEF;
          } 
          MobilusModel.Scene.access$13602(scene, this.sceneWeekSchedules_);
        } else {
          MobilusModel.Scene.access$13602(scene, this.sceneWeekSchedulesBuilder_.build());
        } 
        if (this.sceneAstralSchedulesBuilder_ == null) {
          if ((this.bitField0_ & 0x20) != 0) {
            this.sceneAstralSchedules_ = Collections.unmodifiableList(this.sceneAstralSchedules_);
            this.bitField0_ &= 0xFFFFFFDF;
          } 
          MobilusModel.Scene.access$13702(scene, this.sceneAstralSchedules_);
        } else {
          MobilusModel.Scene.access$13702(scene, this.sceneAstralSchedulesBuilder_.build());
        } 
        k = j;
        if ((i & 0x40) != 0) {
          MobilusModel.Scene.access$13802(scene, this.enabled_);
          k = j | 0x8;
        } 
        MobilusModel.Scene.access$13902(scene, k);
        onBuilt();
        return scene;
      }
      
      public Builder clear() {
        super.clear();
        this.id_ = 0L;
        this.bitField0_ &= 0xFFFFFFFE;
        this.name_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        this.icon_ = 0;
        this.bitField0_ &= 0xFFFFFFFB;
        if (this.sceneEventsBuilder_ == null) {
          this.sceneEvents_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFF7;
        } else {
          this.sceneEventsBuilder_.clear();
        } 
        if (this.sceneWeekSchedulesBuilder_ == null) {
          this.sceneWeekSchedules_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFEF;
        } else {
          this.sceneWeekSchedulesBuilder_.clear();
        } 
        if (this.sceneAstralSchedulesBuilder_ == null) {
          this.sceneAstralSchedules_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFDF;
        } else {
          this.sceneAstralSchedulesBuilder_.clear();
        } 
        this.enabled_ = false;
        this.bitField0_ &= 0xFFFFFFBF;
        return this;
      }
      
      public Builder clearEnabled() {
        this.bitField0_ &= 0xFFFFFFBF;
        this.enabled_ = false;
        onChanged();
        return this;
      }
      
      public Builder clearField(Descriptors.FieldDescriptor param2FieldDescriptor) {
        return (Builder)super.clearField(param2FieldDescriptor);
      }
      
      public Builder clearIcon() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.icon_ = 0;
        onChanged();
        return this;
      }
      
      public Builder clearId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.id_ = 0L;
        onChanged();
        return this;
      }
      
      public Builder clearName() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.name_ = MobilusModel.Scene.getDefaultInstance().getName();
        onChanged();
        return this;
      }
      
      public Builder clearOneof(Descriptors.OneofDescriptor param2OneofDescriptor) {
        return (Builder)super.clearOneof(param2OneofDescriptor);
      }
      
      public Builder clearSceneAstralSchedules() {
        if (this.sceneAstralSchedulesBuilder_ == null) {
          this.sceneAstralSchedules_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFDF;
          onChanged();
        } else {
          this.sceneAstralSchedulesBuilder_.clear();
        } 
        return this;
      }
      
      public Builder clearSceneEvents() {
        if (this.sceneEventsBuilder_ == null) {
          this.sceneEvents_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFF7;
          onChanged();
        } else {
          this.sceneEventsBuilder_.clear();
        } 
        return this;
      }
      
      public Builder clearSceneWeekSchedules() {
        if (this.sceneWeekSchedulesBuilder_ == null) {
          this.sceneWeekSchedules_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFEF;
          onChanged();
        } else {
          this.sceneWeekSchedulesBuilder_.clear();
        } 
        return this;
      }
      
      public Builder clone() {
        return (Builder)super.clone();
      }
      
      public MobilusModel.Scene getDefaultInstanceForType() {
        return MobilusModel.Scene.getDefaultInstance();
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MobilusModel.internal_static_Scene_descriptor;
      }
      
      public boolean getEnabled() {
        return this.enabled_;
      }
      
      public int getIcon() {
        return this.icon_;
      }
      
      public long getId() {
        return this.id_;
      }
      
      public String getName() {
        Object object = this.name_;
        if (!(object instanceof String)) {
          object = object;
          String str = object.toStringUtf8();
          if (object.isValidUtf8())
            this.name_ = str; 
          return str;
        } 
        return (String)object;
      }
      
      public ByteString getNameBytes() {
        Object object = this.name_;
        if (object instanceof String) {
          object = ByteString.copyFromUtf8((String)object);
          this.name_ = object;
          return (ByteString)object;
        } 
        return (ByteString)object;
      }
      
      public MobilusModel.SceneAstralSchedule getSceneAstralSchedules(int param2Int) {
        return (this.sceneAstralSchedulesBuilder_ == null) ? this.sceneAstralSchedules_.get(param2Int) : (MobilusModel.SceneAstralSchedule)this.sceneAstralSchedulesBuilder_.getMessage(param2Int);
      }
      
      public MobilusModel.SceneAstralSchedule.Builder getSceneAstralSchedulesBuilder(int param2Int) {
        return (MobilusModel.SceneAstralSchedule.Builder)getSceneAstralSchedulesFieldBuilder().getBuilder(param2Int);
      }
      
      public List<MobilusModel.SceneAstralSchedule.Builder> getSceneAstralSchedulesBuilderList() {
        return getSceneAstralSchedulesFieldBuilder().getBuilderList();
      }
      
      public int getSceneAstralSchedulesCount() {
        return (this.sceneAstralSchedulesBuilder_ == null) ? this.sceneAstralSchedules_.size() : this.sceneAstralSchedulesBuilder_.getCount();
      }
      
      public List<MobilusModel.SceneAstralSchedule> getSceneAstralSchedulesList() {
        return (this.sceneAstralSchedulesBuilder_ == null) ? Collections.unmodifiableList(this.sceneAstralSchedules_) : this.sceneAstralSchedulesBuilder_.getMessageList();
      }
      
      public MobilusModel.SceneAstralScheduleOrBuilder getSceneAstralSchedulesOrBuilder(int param2Int) {
        return (this.sceneAstralSchedulesBuilder_ == null) ? this.sceneAstralSchedules_.get(param2Int) : (MobilusModel.SceneAstralScheduleOrBuilder)this.sceneAstralSchedulesBuilder_.getMessageOrBuilder(param2Int);
      }
      
      public List<? extends MobilusModel.SceneAstralScheduleOrBuilder> getSceneAstralSchedulesOrBuilderList() {
        return (this.sceneAstralSchedulesBuilder_ != null) ? this.sceneAstralSchedulesBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList((List)this.sceneAstralSchedules_);
      }
      
      public MobilusModel.SceneEvent getSceneEvents(int param2Int) {
        return (this.sceneEventsBuilder_ == null) ? this.sceneEvents_.get(param2Int) : (MobilusModel.SceneEvent)this.sceneEventsBuilder_.getMessage(param2Int);
      }
      
      public MobilusModel.SceneEvent.Builder getSceneEventsBuilder(int param2Int) {
        return (MobilusModel.SceneEvent.Builder)getSceneEventsFieldBuilder().getBuilder(param2Int);
      }
      
      public List<MobilusModel.SceneEvent.Builder> getSceneEventsBuilderList() {
        return getSceneEventsFieldBuilder().getBuilderList();
      }
      
      public int getSceneEventsCount() {
        return (this.sceneEventsBuilder_ == null) ? this.sceneEvents_.size() : this.sceneEventsBuilder_.getCount();
      }
      
      public List<MobilusModel.SceneEvent> getSceneEventsList() {
        return (this.sceneEventsBuilder_ == null) ? Collections.unmodifiableList(this.sceneEvents_) : this.sceneEventsBuilder_.getMessageList();
      }
      
      public MobilusModel.SceneEventOrBuilder getSceneEventsOrBuilder(int param2Int) {
        return (this.sceneEventsBuilder_ == null) ? this.sceneEvents_.get(param2Int) : (MobilusModel.SceneEventOrBuilder)this.sceneEventsBuilder_.getMessageOrBuilder(param2Int);
      }
      
      public List<? extends MobilusModel.SceneEventOrBuilder> getSceneEventsOrBuilderList() {
        return (this.sceneEventsBuilder_ != null) ? this.sceneEventsBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList((List)this.sceneEvents_);
      }
      
      public MobilusModel.SceneWeekSchedule getSceneWeekSchedules(int param2Int) {
        return (this.sceneWeekSchedulesBuilder_ == null) ? this.sceneWeekSchedules_.get(param2Int) : (MobilusModel.SceneWeekSchedule)this.sceneWeekSchedulesBuilder_.getMessage(param2Int);
      }
      
      public MobilusModel.SceneWeekSchedule.Builder getSceneWeekSchedulesBuilder(int param2Int) {
        return (MobilusModel.SceneWeekSchedule.Builder)getSceneWeekSchedulesFieldBuilder().getBuilder(param2Int);
      }
      
      public List<MobilusModel.SceneWeekSchedule.Builder> getSceneWeekSchedulesBuilderList() {
        return getSceneWeekSchedulesFieldBuilder().getBuilderList();
      }
      
      public int getSceneWeekSchedulesCount() {
        return (this.sceneWeekSchedulesBuilder_ == null) ? this.sceneWeekSchedules_.size() : this.sceneWeekSchedulesBuilder_.getCount();
      }
      
      public List<MobilusModel.SceneWeekSchedule> getSceneWeekSchedulesList() {
        return (this.sceneWeekSchedulesBuilder_ == null) ? Collections.unmodifiableList(this.sceneWeekSchedules_) : this.sceneWeekSchedulesBuilder_.getMessageList();
      }
      
      public MobilusModel.SceneWeekScheduleOrBuilder getSceneWeekSchedulesOrBuilder(int param2Int) {
        return (this.sceneWeekSchedulesBuilder_ == null) ? this.sceneWeekSchedules_.get(param2Int) : (MobilusModel.SceneWeekScheduleOrBuilder)this.sceneWeekSchedulesBuilder_.getMessageOrBuilder(param2Int);
      }
      
      public List<? extends MobilusModel.SceneWeekScheduleOrBuilder> getSceneWeekSchedulesOrBuilderList() {
        return (this.sceneWeekSchedulesBuilder_ != null) ? this.sceneWeekSchedulesBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList((List)this.sceneWeekSchedules_);
      }
      
      public boolean hasEnabled() {
        boolean bool;
        if ((this.bitField0_ & 0x40) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasIcon() {
        boolean bool;
        if ((this.bitField0_ & 0x4) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasId() {
        int i = this.bitField0_;
        boolean bool = true;
        if ((i & 0x1) == 0)
          bool = false; 
        return bool;
      }
      
      public boolean hasName() {
        boolean bool;
        if ((this.bitField0_ & 0x2) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MobilusModel.internal_static_Scene_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.Scene.class, Builder.class);
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws IOException {
        MobilusModel.Scene scene1;
        MobilusModel.Scene scene2 = null;
        try {
          scene1 = (MobilusModel.Scene)MobilusModel.Scene.PARSER.parsePartialFrom(param2CodedInputStream, param2ExtensionRegistryLite);
          return this;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          MobilusModel.Scene scene = (MobilusModel.Scene)invalidProtocolBufferException.getUnfinishedMessage();
        } finally {
          param2ExtensionRegistryLite = null;
        } 
        if (scene1 != null)
          mergeFrom(scene1); 
        throw param2ExtensionRegistryLite;
      }
      
      public Builder mergeFrom(Message param2Message) {
        if (param2Message instanceof MobilusModel.Scene)
          return mergeFrom((MobilusModel.Scene)param2Message); 
        super.mergeFrom(param2Message);
        return this;
      }
      
      public Builder mergeFrom(MobilusModel.Scene param2Scene) {
        if (param2Scene == MobilusModel.Scene.getDefaultInstance())
          return this; 
        if (param2Scene.hasId())
          setId(param2Scene.getId()); 
        if (param2Scene.hasName()) {
          this.bitField0_ |= 0x2;
          this.name_ = param2Scene.name_;
          onChanged();
        } 
        if (param2Scene.hasIcon())
          setIcon(param2Scene.getIcon()); 
        RepeatedFieldBuilderV3<MobilusModel.SceneEvent, MobilusModel.SceneEvent.Builder, MobilusModel.SceneEventOrBuilder> repeatedFieldBuilderV3 = this.sceneEventsBuilder_;
        RepeatedFieldBuilderV3 repeatedFieldBuilderV31 = null;
        if (repeatedFieldBuilderV3 == null) {
          if (!param2Scene.sceneEvents_.isEmpty()) {
            if (this.sceneEvents_.isEmpty()) {
              this.sceneEvents_ = param2Scene.sceneEvents_;
              this.bitField0_ &= 0xFFFFFFF7;
            } else {
              ensureSceneEventsIsMutable();
              this.sceneEvents_.addAll(param2Scene.sceneEvents_);
            } 
            onChanged();
          } 
        } else if (!param2Scene.sceneEvents_.isEmpty()) {
          if (this.sceneEventsBuilder_.isEmpty()) {
            this.sceneEventsBuilder_.dispose();
            this.sceneEventsBuilder_ = null;
            this.sceneEvents_ = param2Scene.sceneEvents_;
            this.bitField0_ &= 0xFFFFFFF7;
            if (MobilusModel.Scene.alwaysUseFieldBuilders) {
              repeatedFieldBuilderV3 = getSceneEventsFieldBuilder();
            } else {
              repeatedFieldBuilderV3 = null;
            } 
            this.sceneEventsBuilder_ = repeatedFieldBuilderV3;
          } else {
            this.sceneEventsBuilder_.addAllMessages(param2Scene.sceneEvents_);
          } 
        } 
        if (this.sceneWeekSchedulesBuilder_ == null) {
          if (!param2Scene.sceneWeekSchedules_.isEmpty()) {
            if (this.sceneWeekSchedules_.isEmpty()) {
              this.sceneWeekSchedules_ = param2Scene.sceneWeekSchedules_;
              this.bitField0_ &= 0xFFFFFFEF;
            } else {
              ensureSceneWeekSchedulesIsMutable();
              this.sceneWeekSchedules_.addAll(param2Scene.sceneWeekSchedules_);
            } 
            onChanged();
          } 
        } else if (!param2Scene.sceneWeekSchedules_.isEmpty()) {
          if (this.sceneWeekSchedulesBuilder_.isEmpty()) {
            this.sceneWeekSchedulesBuilder_.dispose();
            this.sceneWeekSchedulesBuilder_ = null;
            this.sceneWeekSchedules_ = param2Scene.sceneWeekSchedules_;
            this.bitField0_ &= 0xFFFFFFEF;
            if (MobilusModel.Scene.alwaysUseFieldBuilders) {
              repeatedFieldBuilderV3 = (RepeatedFieldBuilderV3)getSceneWeekSchedulesFieldBuilder();
            } else {
              repeatedFieldBuilderV3 = null;
            } 
            this.sceneWeekSchedulesBuilder_ = (RepeatedFieldBuilderV3)repeatedFieldBuilderV3;
          } else {
            this.sceneWeekSchedulesBuilder_.addAllMessages(param2Scene.sceneWeekSchedules_);
          } 
        } 
        if (this.sceneAstralSchedulesBuilder_ == null) {
          if (!param2Scene.sceneAstralSchedules_.isEmpty()) {
            if (this.sceneAstralSchedules_.isEmpty()) {
              this.sceneAstralSchedules_ = param2Scene.sceneAstralSchedules_;
              this.bitField0_ &= 0xFFFFFFDF;
            } else {
              ensureSceneAstralSchedulesIsMutable();
              this.sceneAstralSchedules_.addAll(param2Scene.sceneAstralSchedules_);
            } 
            onChanged();
          } 
        } else if (!param2Scene.sceneAstralSchedules_.isEmpty()) {
          if (this.sceneAstralSchedulesBuilder_.isEmpty()) {
            this.sceneAstralSchedulesBuilder_.dispose();
            this.sceneAstralSchedulesBuilder_ = null;
            this.sceneAstralSchedules_ = param2Scene.sceneAstralSchedules_;
            this.bitField0_ &= 0xFFFFFFDF;
            repeatedFieldBuilderV3 = repeatedFieldBuilderV31;
            if (MobilusModel.Scene.alwaysUseFieldBuilders)
              repeatedFieldBuilderV3 = (RepeatedFieldBuilderV3)getSceneAstralSchedulesFieldBuilder(); 
            this.sceneAstralSchedulesBuilder_ = (RepeatedFieldBuilderV3)repeatedFieldBuilderV3;
          } else {
            this.sceneAstralSchedulesBuilder_.addAllMessages(param2Scene.sceneAstralSchedules_);
          } 
        } 
        if (param2Scene.hasEnabled())
          setEnabled(param2Scene.getEnabled()); 
        mergeUnknownFields(param2Scene.unknownFields);
        onChanged();
        return this;
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.mergeUnknownFields(param2UnknownFieldSet);
      }
      
      public Builder removeSceneAstralSchedules(int param2Int) {
        if (this.sceneAstralSchedulesBuilder_ == null) {
          ensureSceneAstralSchedulesIsMutable();
          this.sceneAstralSchedules_.remove(param2Int);
          onChanged();
        } else {
          this.sceneAstralSchedulesBuilder_.remove(param2Int);
        } 
        return this;
      }
      
      public Builder removeSceneEvents(int param2Int) {
        if (this.sceneEventsBuilder_ == null) {
          ensureSceneEventsIsMutable();
          this.sceneEvents_.remove(param2Int);
          onChanged();
        } else {
          this.sceneEventsBuilder_.remove(param2Int);
        } 
        return this;
      }
      
      public Builder removeSceneWeekSchedules(int param2Int) {
        if (this.sceneWeekSchedulesBuilder_ == null) {
          ensureSceneWeekSchedulesIsMutable();
          this.sceneWeekSchedules_.remove(param2Int);
          onChanged();
        } else {
          this.sceneWeekSchedulesBuilder_.remove(param2Int);
        } 
        return this;
      }
      
      public Builder setEnabled(boolean param2Boolean) {
        this.bitField0_ |= 0x40;
        this.enabled_ = param2Boolean;
        onChanged();
        return this;
      }
      
      public Builder setField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.setField(param2FieldDescriptor, param2Object);
      }
      
      public Builder setIcon(int param2Int) {
        this.bitField0_ |= 0x4;
        this.icon_ = param2Int;
        onChanged();
        return this;
      }
      
      public Builder setId(long param2Long) {
        this.bitField0_ |= 0x1;
        this.id_ = param2Long;
        onChanged();
        return this;
      }
      
      public Builder setName(String param2String) {
        if (param2String == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.name_ = param2String;
        onChanged();
        return this;
      }
      
      public Builder setNameBytes(ByteString param2ByteString) {
        if (param2ByteString == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.name_ = param2ByteString;
        onChanged();
        return this;
      }
      
      public Builder setRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, int param2Int, Object param2Object) {
        return (Builder)super.setRepeatedField(param2FieldDescriptor, param2Int, param2Object);
      }
      
      public Builder setSceneAstralSchedules(int param2Int, MobilusModel.SceneAstralSchedule.Builder param2Builder) {
        if (this.sceneAstralSchedulesBuilder_ == null) {
          ensureSceneAstralSchedulesIsMutable();
          this.sceneAstralSchedules_.set(param2Int, param2Builder.build());
          onChanged();
        } else {
          this.sceneAstralSchedulesBuilder_.setMessage(param2Int, (AbstractMessage)param2Builder.build());
        } 
        return this;
      }
      
      public Builder setSceneAstralSchedules(int param2Int, MobilusModel.SceneAstralSchedule param2SceneAstralSchedule) {
        if (this.sceneAstralSchedulesBuilder_ == null) {
          if (param2SceneAstralSchedule == null)
            throw new NullPointerException(); 
          ensureSceneAstralSchedulesIsMutable();
          this.sceneAstralSchedules_.set(param2Int, param2SceneAstralSchedule);
          onChanged();
        } else {
          this.sceneAstralSchedulesBuilder_.setMessage(param2Int, (AbstractMessage)param2SceneAstralSchedule);
        } 
        return this;
      }
      
      public Builder setSceneEvents(int param2Int, MobilusModel.SceneEvent.Builder param2Builder) {
        if (this.sceneEventsBuilder_ == null) {
          ensureSceneEventsIsMutable();
          this.sceneEvents_.set(param2Int, param2Builder.build());
          onChanged();
        } else {
          this.sceneEventsBuilder_.setMessage(param2Int, (AbstractMessage)param2Builder.build());
        } 
        return this;
      }
      
      public Builder setSceneEvents(int param2Int, MobilusModel.SceneEvent param2SceneEvent) {
        if (this.sceneEventsBuilder_ == null) {
          if (param2SceneEvent == null)
            throw new NullPointerException(); 
          ensureSceneEventsIsMutable();
          this.sceneEvents_.set(param2Int, param2SceneEvent);
          onChanged();
        } else {
          this.sceneEventsBuilder_.setMessage(param2Int, (AbstractMessage)param2SceneEvent);
        } 
        return this;
      }
      
      public Builder setSceneWeekSchedules(int param2Int, MobilusModel.SceneWeekSchedule.Builder param2Builder) {
        if (this.sceneWeekSchedulesBuilder_ == null) {
          ensureSceneWeekSchedulesIsMutable();
          this.sceneWeekSchedules_.set(param2Int, param2Builder.build());
          onChanged();
        } else {
          this.sceneWeekSchedulesBuilder_.setMessage(param2Int, (AbstractMessage)param2Builder.build());
        } 
        return this;
      }
      
      public Builder setSceneWeekSchedules(int param2Int, MobilusModel.SceneWeekSchedule param2SceneWeekSchedule) {
        if (this.sceneWeekSchedulesBuilder_ == null) {
          if (param2SceneWeekSchedule == null)
            throw new NullPointerException(); 
          ensureSceneWeekSchedulesIsMutable();
          this.sceneWeekSchedules_.set(param2Int, param2SceneWeekSchedule);
          onChanged();
        } else {
          this.sceneWeekSchedulesBuilder_.setMessage(param2Int, (AbstractMessage)param2SceneWeekSchedule);
        } 
        return this;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.setUnknownFields(param2UnknownFieldSet);
      }
    }
  }
  
  static final class null extends AbstractParser<Scene> {
    public MobilusModel.Scene parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return new MobilusModel.Scene(param1CodedInputStream, param1ExtensionRegistryLite);
    }
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<Scene.Builder> implements SceneOrBuilder {
    private int bitField0_;
    
    private boolean enabled_;
    
    private int icon_;
    
    private long id_;
    
    private Object name_ = "";
    
    private RepeatedFieldBuilderV3<MobilusModel.SceneAstralSchedule, MobilusModel.SceneAstralSchedule.Builder, MobilusModel.SceneAstralScheduleOrBuilder> sceneAstralSchedulesBuilder_;
    
    private List<MobilusModel.SceneAstralSchedule> sceneAstralSchedules_ = Collections.emptyList();
    
    private RepeatedFieldBuilderV3<MobilusModel.SceneEvent, MobilusModel.SceneEvent.Builder, MobilusModel.SceneEventOrBuilder> sceneEventsBuilder_;
    
    private List<MobilusModel.SceneEvent> sceneEvents_ = Collections.emptyList();
    
    private RepeatedFieldBuilderV3<MobilusModel.SceneWeekSchedule, MobilusModel.SceneWeekSchedule.Builder, MobilusModel.SceneWeekScheduleOrBuilder> sceneWeekSchedulesBuilder_;
    
    private List<MobilusModel.SceneWeekSchedule> sceneWeekSchedules_ = Collections.emptyList();
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      super(param1BuilderParent);
      maybeForceBuilderInitialization();
    }
    
    private void ensureSceneAstralSchedulesIsMutable() {
      if ((this.bitField0_ & 0x20) == 0) {
        this.sceneAstralSchedules_ = new ArrayList<>(this.sceneAstralSchedules_);
        this.bitField0_ |= 0x20;
      } 
    }
    
    private void ensureSceneEventsIsMutable() {
      if ((this.bitField0_ & 0x8) == 0) {
        this.sceneEvents_ = new ArrayList<>(this.sceneEvents_);
        this.bitField0_ |= 0x8;
      } 
    }
    
    private void ensureSceneWeekSchedulesIsMutable() {
      if ((this.bitField0_ & 0x10) == 0) {
        this.sceneWeekSchedules_ = new ArrayList<>(this.sceneWeekSchedules_);
        this.bitField0_ |= 0x10;
      } 
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_Scene_descriptor;
    }
    
    private RepeatedFieldBuilderV3<MobilusModel.SceneAstralSchedule, MobilusModel.SceneAstralSchedule.Builder, MobilusModel.SceneAstralScheduleOrBuilder> getSceneAstralSchedulesFieldBuilder() {
      if (this.sceneAstralSchedulesBuilder_ == null) {
        boolean bool;
        List<MobilusModel.SceneAstralSchedule> list = this.sceneAstralSchedules_;
        if ((this.bitField0_ & 0x20) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        this.sceneAstralSchedulesBuilder_ = new RepeatedFieldBuilderV3(list, bool, (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.sceneAstralSchedules_ = null;
      } 
      return this.sceneAstralSchedulesBuilder_;
    }
    
    private RepeatedFieldBuilderV3<MobilusModel.SceneEvent, MobilusModel.SceneEvent.Builder, MobilusModel.SceneEventOrBuilder> getSceneEventsFieldBuilder() {
      if (this.sceneEventsBuilder_ == null) {
        boolean bool;
        List<MobilusModel.SceneEvent> list = this.sceneEvents_;
        if ((this.bitField0_ & 0x8) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        this.sceneEventsBuilder_ = new RepeatedFieldBuilderV3(list, bool, (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.sceneEvents_ = null;
      } 
      return this.sceneEventsBuilder_;
    }
    
    private RepeatedFieldBuilderV3<MobilusModel.SceneWeekSchedule, MobilusModel.SceneWeekSchedule.Builder, MobilusModel.SceneWeekScheduleOrBuilder> getSceneWeekSchedulesFieldBuilder() {
      if (this.sceneWeekSchedulesBuilder_ == null) {
        boolean bool;
        List<MobilusModel.SceneWeekSchedule> list = this.sceneWeekSchedules_;
        if ((this.bitField0_ & 0x10) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        this.sceneWeekSchedulesBuilder_ = new RepeatedFieldBuilderV3(list, bool, (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.sceneWeekSchedules_ = null;
      } 
      return this.sceneWeekSchedulesBuilder_;
    }
    
    private void maybeForceBuilderInitialization() {
      if (MobilusModel.Scene.alwaysUseFieldBuilders) {
        getSceneEventsFieldBuilder();
        getSceneWeekSchedulesFieldBuilder();
        getSceneAstralSchedulesFieldBuilder();
      } 
    }
    
    public Builder addAllSceneAstralSchedules(Iterable<? extends MobilusModel.SceneAstralSchedule> param1Iterable) {
      if (this.sceneAstralSchedulesBuilder_ == null) {
        ensureSceneAstralSchedulesIsMutable();
        AbstractMessageLite.Builder.addAll(param1Iterable, this.sceneAstralSchedules_);
        onChanged();
      } else {
        this.sceneAstralSchedulesBuilder_.addAllMessages(param1Iterable);
      } 
      return this;
    }
    
    public Builder addAllSceneEvents(Iterable<? extends MobilusModel.SceneEvent> param1Iterable) {
      if (this.sceneEventsBuilder_ == null) {
        ensureSceneEventsIsMutable();
        AbstractMessageLite.Builder.addAll(param1Iterable, this.sceneEvents_);
        onChanged();
      } else {
        this.sceneEventsBuilder_.addAllMessages(param1Iterable);
      } 
      return this;
    }
    
    public Builder addAllSceneWeekSchedules(Iterable<? extends MobilusModel.SceneWeekSchedule> param1Iterable) {
      if (this.sceneWeekSchedulesBuilder_ == null) {
        ensureSceneWeekSchedulesIsMutable();
        AbstractMessageLite.Builder.addAll(param1Iterable, this.sceneWeekSchedules_);
        onChanged();
      } else {
        this.sceneWeekSchedulesBuilder_.addAllMessages(param1Iterable);
      } 
      return this;
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.addRepeatedField(param1FieldDescriptor, param1Object);
    }
    
    public Builder addSceneAstralSchedules(int param1Int, MobilusModel.SceneAstralSchedule.Builder param1Builder) {
      if (this.sceneAstralSchedulesBuilder_ == null) {
        ensureSceneAstralSchedulesIsMutable();
        this.sceneAstralSchedules_.add(param1Int, param1Builder.build());
        onChanged();
      } else {
        this.sceneAstralSchedulesBuilder_.addMessage(param1Int, (AbstractMessage)param1Builder.build());
      } 
      return this;
    }
    
    public Builder addSceneAstralSchedules(int param1Int, MobilusModel.SceneAstralSchedule param1SceneAstralSchedule) {
      if (this.sceneAstralSchedulesBuilder_ == null) {
        if (param1SceneAstralSchedule == null)
          throw new NullPointerException(); 
        ensureSceneAstralSchedulesIsMutable();
        this.sceneAstralSchedules_.add(param1Int, param1SceneAstralSchedule);
        onChanged();
      } else {
        this.sceneAstralSchedulesBuilder_.addMessage(param1Int, (AbstractMessage)param1SceneAstralSchedule);
      } 
      return this;
    }
    
    public Builder addSceneAstralSchedules(MobilusModel.SceneAstralSchedule.Builder param1Builder) {
      if (this.sceneAstralSchedulesBuilder_ == null) {
        ensureSceneAstralSchedulesIsMutable();
        this.sceneAstralSchedules_.add(param1Builder.build());
        onChanged();
      } else {
        this.sceneAstralSchedulesBuilder_.addMessage((AbstractMessage)param1Builder.build());
      } 
      return this;
    }
    
    public Builder addSceneAstralSchedules(MobilusModel.SceneAstralSchedule param1SceneAstralSchedule) {
      if (this.sceneAstralSchedulesBuilder_ == null) {
        if (param1SceneAstralSchedule == null)
          throw new NullPointerException(); 
        ensureSceneAstralSchedulesIsMutable();
        this.sceneAstralSchedules_.add(param1SceneAstralSchedule);
        onChanged();
      } else {
        this.sceneAstralSchedulesBuilder_.addMessage((AbstractMessage)param1SceneAstralSchedule);
      } 
      return this;
    }
    
    public MobilusModel.SceneAstralSchedule.Builder addSceneAstralSchedulesBuilder() {
      return (MobilusModel.SceneAstralSchedule.Builder)getSceneAstralSchedulesFieldBuilder().addBuilder((AbstractMessage)MobilusModel.SceneAstralSchedule.getDefaultInstance());
    }
    
    public MobilusModel.SceneAstralSchedule.Builder addSceneAstralSchedulesBuilder(int param1Int) {
      return (MobilusModel.SceneAstralSchedule.Builder)getSceneAstralSchedulesFieldBuilder().addBuilder(param1Int, (AbstractMessage)MobilusModel.SceneAstralSchedule.getDefaultInstance());
    }
    
    public Builder addSceneEvents(int param1Int, MobilusModel.SceneEvent.Builder param1Builder) {
      if (this.sceneEventsBuilder_ == null) {
        ensureSceneEventsIsMutable();
        this.sceneEvents_.add(param1Int, param1Builder.build());
        onChanged();
      } else {
        this.sceneEventsBuilder_.addMessage(param1Int, (AbstractMessage)param1Builder.build());
      } 
      return this;
    }
    
    public Builder addSceneEvents(int param1Int, MobilusModel.SceneEvent param1SceneEvent) {
      if (this.sceneEventsBuilder_ == null) {
        if (param1SceneEvent == null)
          throw new NullPointerException(); 
        ensureSceneEventsIsMutable();
        this.sceneEvents_.add(param1Int, param1SceneEvent);
        onChanged();
      } else {
        this.sceneEventsBuilder_.addMessage(param1Int, (AbstractMessage)param1SceneEvent);
      } 
      return this;
    }
    
    public Builder addSceneEvents(MobilusModel.SceneEvent.Builder param1Builder) {
      if (this.sceneEventsBuilder_ == null) {
        ensureSceneEventsIsMutable();
        this.sceneEvents_.add(param1Builder.build());
        onChanged();
      } else {
        this.sceneEventsBuilder_.addMessage((AbstractMessage)param1Builder.build());
      } 
      return this;
    }
    
    public Builder addSceneEvents(MobilusModel.SceneEvent param1SceneEvent) {
      if (this.sceneEventsBuilder_ == null) {
        if (param1SceneEvent == null)
          throw new NullPointerException(); 
        ensureSceneEventsIsMutable();
        this.sceneEvents_.add(param1SceneEvent);
        onChanged();
      } else {
        this.sceneEventsBuilder_.addMessage((AbstractMessage)param1SceneEvent);
      } 
      return this;
    }
    
    public MobilusModel.SceneEvent.Builder addSceneEventsBuilder() {
      return (MobilusModel.SceneEvent.Builder)getSceneEventsFieldBuilder().addBuilder((AbstractMessage)MobilusModel.SceneEvent.getDefaultInstance());
    }
    
    public MobilusModel.SceneEvent.Builder addSceneEventsBuilder(int param1Int) {
      return (MobilusModel.SceneEvent.Builder)getSceneEventsFieldBuilder().addBuilder(param1Int, (AbstractMessage)MobilusModel.SceneEvent.getDefaultInstance());
    }
    
    public Builder addSceneWeekSchedules(int param1Int, MobilusModel.SceneWeekSchedule.Builder param1Builder) {
      if (this.sceneWeekSchedulesBuilder_ == null) {
        ensureSceneWeekSchedulesIsMutable();
        this.sceneWeekSchedules_.add(param1Int, param1Builder.build());
        onChanged();
      } else {
        this.sceneWeekSchedulesBuilder_.addMessage(param1Int, (AbstractMessage)param1Builder.build());
      } 
      return this;
    }
    
    public Builder addSceneWeekSchedules(int param1Int, MobilusModel.SceneWeekSchedule param1SceneWeekSchedule) {
      if (this.sceneWeekSchedulesBuilder_ == null) {
        if (param1SceneWeekSchedule == null)
          throw new NullPointerException(); 
        ensureSceneWeekSchedulesIsMutable();
        this.sceneWeekSchedules_.add(param1Int, param1SceneWeekSchedule);
        onChanged();
      } else {
        this.sceneWeekSchedulesBuilder_.addMessage(param1Int, (AbstractMessage)param1SceneWeekSchedule);
      } 
      return this;
    }
    
    public Builder addSceneWeekSchedules(MobilusModel.SceneWeekSchedule.Builder param1Builder) {
      if (this.sceneWeekSchedulesBuilder_ == null) {
        ensureSceneWeekSchedulesIsMutable();
        this.sceneWeekSchedules_.add(param1Builder.build());
        onChanged();
      } else {
        this.sceneWeekSchedulesBuilder_.addMessage((AbstractMessage)param1Builder.build());
      } 
      return this;
    }
    
    public Builder addSceneWeekSchedules(MobilusModel.SceneWeekSchedule param1SceneWeekSchedule) {
      if (this.sceneWeekSchedulesBuilder_ == null) {
        if (param1SceneWeekSchedule == null)
          throw new NullPointerException(); 
        ensureSceneWeekSchedulesIsMutable();
        this.sceneWeekSchedules_.add(param1SceneWeekSchedule);
        onChanged();
      } else {
        this.sceneWeekSchedulesBuilder_.addMessage((AbstractMessage)param1SceneWeekSchedule);
      } 
      return this;
    }
    
    public MobilusModel.SceneWeekSchedule.Builder addSceneWeekSchedulesBuilder() {
      return (MobilusModel.SceneWeekSchedule.Builder)getSceneWeekSchedulesFieldBuilder().addBuilder((AbstractMessage)MobilusModel.SceneWeekSchedule.getDefaultInstance());
    }
    
    public MobilusModel.SceneWeekSchedule.Builder addSceneWeekSchedulesBuilder(int param1Int) {
      return (MobilusModel.SceneWeekSchedule.Builder)getSceneWeekSchedulesFieldBuilder().addBuilder(param1Int, (AbstractMessage)MobilusModel.SceneWeekSchedule.getDefaultInstance());
    }
    
    public MobilusModel.Scene build() {
      MobilusModel.Scene scene = buildPartial();
      if (!scene.isInitialized())
        throw newUninitializedMessageException(scene); 
      return scene;
    }
    
    public MobilusModel.Scene buildPartial() {
      MobilusModel.Scene scene = new MobilusModel.Scene(this);
      int i = this.bitField0_;
      if ((i & 0x1) != 0) {
        MobilusModel.Scene.access$13202(scene, this.id_);
        j = 1;
      } else {
        j = 0;
      } 
      int k = j;
      if ((i & 0x2) != 0)
        k = j | 0x2; 
      MobilusModel.Scene.access$13302(scene, this.name_);
      int j = k;
      if ((i & 0x4) != 0) {
        MobilusModel.Scene.access$13402(scene, this.icon_);
        j = k | 0x4;
      } 
      if (this.sceneEventsBuilder_ == null) {
        if ((this.bitField0_ & 0x8) != 0) {
          this.sceneEvents_ = Collections.unmodifiableList(this.sceneEvents_);
          this.bitField0_ &= 0xFFFFFFF7;
        } 
        MobilusModel.Scene.access$13502(scene, this.sceneEvents_);
      } else {
        MobilusModel.Scene.access$13502(scene, this.sceneEventsBuilder_.build());
      } 
      if (this.sceneWeekSchedulesBuilder_ == null) {
        if ((this.bitField0_ & 0x10) != 0) {
          this.sceneWeekSchedules_ = Collections.unmodifiableList(this.sceneWeekSchedules_);
          this.bitField0_ &= 0xFFFFFFEF;
        } 
        MobilusModel.Scene.access$13602(scene, this.sceneWeekSchedules_);
      } else {
        MobilusModel.Scene.access$13602(scene, this.sceneWeekSchedulesBuilder_.build());
      } 
      if (this.sceneAstralSchedulesBuilder_ == null) {
        if ((this.bitField0_ & 0x20) != 0) {
          this.sceneAstralSchedules_ = Collections.unmodifiableList(this.sceneAstralSchedules_);
          this.bitField0_ &= 0xFFFFFFDF;
        } 
        MobilusModel.Scene.access$13702(scene, this.sceneAstralSchedules_);
      } else {
        MobilusModel.Scene.access$13702(scene, this.sceneAstralSchedulesBuilder_.build());
      } 
      k = j;
      if ((i & 0x40) != 0) {
        MobilusModel.Scene.access$13802(scene, this.enabled_);
        k = j | 0x8;
      } 
      MobilusModel.Scene.access$13902(scene, k);
      onBuilt();
      return scene;
    }
    
    public Builder clear() {
      super.clear();
      this.id_ = 0L;
      this.bitField0_ &= 0xFFFFFFFE;
      this.name_ = "";
      this.bitField0_ &= 0xFFFFFFFD;
      this.icon_ = 0;
      this.bitField0_ &= 0xFFFFFFFB;
      if (this.sceneEventsBuilder_ == null) {
        this.sceneEvents_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFF7;
      } else {
        this.sceneEventsBuilder_.clear();
      } 
      if (this.sceneWeekSchedulesBuilder_ == null) {
        this.sceneWeekSchedules_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFEF;
      } else {
        this.sceneWeekSchedulesBuilder_.clear();
      } 
      if (this.sceneAstralSchedulesBuilder_ == null) {
        this.sceneAstralSchedules_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFDF;
      } else {
        this.sceneAstralSchedulesBuilder_.clear();
      } 
      this.enabled_ = false;
      this.bitField0_ &= 0xFFFFFFBF;
      return this;
    }
    
    public Builder clearEnabled() {
      this.bitField0_ &= 0xFFFFFFBF;
      this.enabled_ = false;
      onChanged();
      return this;
    }
    
    public Builder clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return (Builder)super.clearField(param1FieldDescriptor);
    }
    
    public Builder clearIcon() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.icon_ = 0;
      onChanged();
      return this;
    }
    
    public Builder clearId() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.id_ = 0L;
      onChanged();
      return this;
    }
    
    public Builder clearName() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.name_ = MobilusModel.Scene.getDefaultInstance().getName();
      onChanged();
      return this;
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return (Builder)super.clearOneof(param1OneofDescriptor);
    }
    
    public Builder clearSceneAstralSchedules() {
      if (this.sceneAstralSchedulesBuilder_ == null) {
        this.sceneAstralSchedules_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFDF;
        onChanged();
      } else {
        this.sceneAstralSchedulesBuilder_.clear();
      } 
      return this;
    }
    
    public Builder clearSceneEvents() {
      if (this.sceneEventsBuilder_ == null) {
        this.sceneEvents_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFF7;
        onChanged();
      } else {
        this.sceneEventsBuilder_.clear();
      } 
      return this;
    }
    
    public Builder clearSceneWeekSchedules() {
      if (this.sceneWeekSchedulesBuilder_ == null) {
        this.sceneWeekSchedules_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFEF;
        onChanged();
      } else {
        this.sceneWeekSchedulesBuilder_.clear();
      } 
      return this;
    }
    
    public Builder clone() {
      return (Builder)super.clone();
    }
    
    public MobilusModel.Scene getDefaultInstanceForType() {
      return MobilusModel.Scene.getDefaultInstance();
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return MobilusModel.internal_static_Scene_descriptor;
    }
    
    public boolean getEnabled() {
      return this.enabled_;
    }
    
    public int getIcon() {
      return this.icon_;
    }
    
    public long getId() {
      return this.id_;
    }
    
    public String getName() {
      Object object = this.name_;
      if (!(object instanceof String)) {
        object = object;
        String str = object.toStringUtf8();
        if (object.isValidUtf8())
          this.name_ = str; 
        return str;
      } 
      return (String)object;
    }
    
    public ByteString getNameBytes() {
      Object object = this.name_;
      if (object instanceof String) {
        object = ByteString.copyFromUtf8((String)object);
        this.name_ = object;
        return (ByteString)object;
      } 
      return (ByteString)object;
    }
    
    public MobilusModel.SceneAstralSchedule getSceneAstralSchedules(int param1Int) {
      return (this.sceneAstralSchedulesBuilder_ == null) ? this.sceneAstralSchedules_.get(param1Int) : (MobilusModel.SceneAstralSchedule)this.sceneAstralSchedulesBuilder_.getMessage(param1Int);
    }
    
    public MobilusModel.SceneAstralSchedule.Builder getSceneAstralSchedulesBuilder(int param1Int) {
      return (MobilusModel.SceneAstralSchedule.Builder)getSceneAstralSchedulesFieldBuilder().getBuilder(param1Int);
    }
    
    public List<MobilusModel.SceneAstralSchedule.Builder> getSceneAstralSchedulesBuilderList() {
      return getSceneAstralSchedulesFieldBuilder().getBuilderList();
    }
    
    public int getSceneAstralSchedulesCount() {
      return (this.sceneAstralSchedulesBuilder_ == null) ? this.sceneAstralSchedules_.size() : this.sceneAstralSchedulesBuilder_.getCount();
    }
    
    public List<MobilusModel.SceneAstralSchedule> getSceneAstralSchedulesList() {
      return (this.sceneAstralSchedulesBuilder_ == null) ? Collections.unmodifiableList(this.sceneAstralSchedules_) : this.sceneAstralSchedulesBuilder_.getMessageList();
    }
    
    public MobilusModel.SceneAstralScheduleOrBuilder getSceneAstralSchedulesOrBuilder(int param1Int) {
      return (this.sceneAstralSchedulesBuilder_ == null) ? this.sceneAstralSchedules_.get(param1Int) : (MobilusModel.SceneAstralScheduleOrBuilder)this.sceneAstralSchedulesBuilder_.getMessageOrBuilder(param1Int);
    }
    
    public List<? extends MobilusModel.SceneAstralScheduleOrBuilder> getSceneAstralSchedulesOrBuilderList() {
      return (this.sceneAstralSchedulesBuilder_ != null) ? this.sceneAstralSchedulesBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList((List)this.sceneAstralSchedules_);
    }
    
    public MobilusModel.SceneEvent getSceneEvents(int param1Int) {
      return (this.sceneEventsBuilder_ == null) ? this.sceneEvents_.get(param1Int) : (MobilusModel.SceneEvent)this.sceneEventsBuilder_.getMessage(param1Int);
    }
    
    public MobilusModel.SceneEvent.Builder getSceneEventsBuilder(int param1Int) {
      return (MobilusModel.SceneEvent.Builder)getSceneEventsFieldBuilder().getBuilder(param1Int);
    }
    
    public List<MobilusModel.SceneEvent.Builder> getSceneEventsBuilderList() {
      return getSceneEventsFieldBuilder().getBuilderList();
    }
    
    public int getSceneEventsCount() {
      return (this.sceneEventsBuilder_ == null) ? this.sceneEvents_.size() : this.sceneEventsBuilder_.getCount();
    }
    
    public List<MobilusModel.SceneEvent> getSceneEventsList() {
      return (this.sceneEventsBuilder_ == null) ? Collections.unmodifiableList(this.sceneEvents_) : this.sceneEventsBuilder_.getMessageList();
    }
    
    public MobilusModel.SceneEventOrBuilder getSceneEventsOrBuilder(int param1Int) {
      return (this.sceneEventsBuilder_ == null) ? this.sceneEvents_.get(param1Int) : (MobilusModel.SceneEventOrBuilder)this.sceneEventsBuilder_.getMessageOrBuilder(param1Int);
    }
    
    public List<? extends MobilusModel.SceneEventOrBuilder> getSceneEventsOrBuilderList() {
      return (this.sceneEventsBuilder_ != null) ? this.sceneEventsBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList((List)this.sceneEvents_);
    }
    
    public MobilusModel.SceneWeekSchedule getSceneWeekSchedules(int param1Int) {
      return (this.sceneWeekSchedulesBuilder_ == null) ? this.sceneWeekSchedules_.get(param1Int) : (MobilusModel.SceneWeekSchedule)this.sceneWeekSchedulesBuilder_.getMessage(param1Int);
    }
    
    public MobilusModel.SceneWeekSchedule.Builder getSceneWeekSchedulesBuilder(int param1Int) {
      return (MobilusModel.SceneWeekSchedule.Builder)getSceneWeekSchedulesFieldBuilder().getBuilder(param1Int);
    }
    
    public List<MobilusModel.SceneWeekSchedule.Builder> getSceneWeekSchedulesBuilderList() {
      return getSceneWeekSchedulesFieldBuilder().getBuilderList();
    }
    
    public int getSceneWeekSchedulesCount() {
      return (this.sceneWeekSchedulesBuilder_ == null) ? this.sceneWeekSchedules_.size() : this.sceneWeekSchedulesBuilder_.getCount();
    }
    
    public List<MobilusModel.SceneWeekSchedule> getSceneWeekSchedulesList() {
      return (this.sceneWeekSchedulesBuilder_ == null) ? Collections.unmodifiableList(this.sceneWeekSchedules_) : this.sceneWeekSchedulesBuilder_.getMessageList();
    }
    
    public MobilusModel.SceneWeekScheduleOrBuilder getSceneWeekSchedulesOrBuilder(int param1Int) {
      return (this.sceneWeekSchedulesBuilder_ == null) ? this.sceneWeekSchedules_.get(param1Int) : (MobilusModel.SceneWeekScheduleOrBuilder)this.sceneWeekSchedulesBuilder_.getMessageOrBuilder(param1Int);
    }
    
    public List<? extends MobilusModel.SceneWeekScheduleOrBuilder> getSceneWeekSchedulesOrBuilderList() {
      return (this.sceneWeekSchedulesBuilder_ != null) ? this.sceneWeekSchedulesBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList((List)this.sceneWeekSchedules_);
    }
    
    public boolean hasEnabled() {
      boolean bool;
      if ((this.bitField0_ & 0x40) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasIcon() {
      boolean bool;
      if ((this.bitField0_ & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasId() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean hasName() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_Scene_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.Scene.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      MobilusModel.Scene scene1;
      MobilusModel.Scene scene2 = null;
      try {
        scene1 = (MobilusModel.Scene)MobilusModel.Scene.PARSER.parsePartialFrom(param1CodedInputStream, param1ExtensionRegistryLite);
        return this;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        MobilusModel.Scene scene = (MobilusModel.Scene)invalidProtocolBufferException.getUnfinishedMessage();
      } finally {
        param1ExtensionRegistryLite = null;
      } 
      if (scene1 != null)
        mergeFrom(scene1); 
      throw param1ExtensionRegistryLite;
    }
    
    public Builder mergeFrom(Message param1Message) {
      if (param1Message instanceof MobilusModel.Scene)
        return mergeFrom((MobilusModel.Scene)param1Message); 
      super.mergeFrom(param1Message);
      return this;
    }
    
    public Builder mergeFrom(MobilusModel.Scene param1Scene) {
      if (param1Scene == MobilusModel.Scene.getDefaultInstance())
        return this; 
      if (param1Scene.hasId())
        setId(param1Scene.getId()); 
      if (param1Scene.hasName()) {
        this.bitField0_ |= 0x2;
        this.name_ = param1Scene.name_;
        onChanged();
      } 
      if (param1Scene.hasIcon())
        setIcon(param1Scene.getIcon()); 
      RepeatedFieldBuilderV3<MobilusModel.SceneEvent, MobilusModel.SceneEvent.Builder, MobilusModel.SceneEventOrBuilder> repeatedFieldBuilderV3 = this.sceneEventsBuilder_;
      RepeatedFieldBuilderV3 repeatedFieldBuilderV31 = null;
      if (repeatedFieldBuilderV3 == null) {
        if (!param1Scene.sceneEvents_.isEmpty()) {
          if (this.sceneEvents_.isEmpty()) {
            this.sceneEvents_ = param1Scene.sceneEvents_;
            this.bitField0_ &= 0xFFFFFFF7;
          } else {
            ensureSceneEventsIsMutable();
            this.sceneEvents_.addAll(param1Scene.sceneEvents_);
          } 
          onChanged();
        } 
      } else if (!param1Scene.sceneEvents_.isEmpty()) {
        if (this.sceneEventsBuilder_.isEmpty()) {
          this.sceneEventsBuilder_.dispose();
          this.sceneEventsBuilder_ = null;
          this.sceneEvents_ = param1Scene.sceneEvents_;
          this.bitField0_ &= 0xFFFFFFF7;
          if (MobilusModel.Scene.alwaysUseFieldBuilders) {
            repeatedFieldBuilderV3 = getSceneEventsFieldBuilder();
          } else {
            repeatedFieldBuilderV3 = null;
          } 
          this.sceneEventsBuilder_ = repeatedFieldBuilderV3;
        } else {
          this.sceneEventsBuilder_.addAllMessages(param1Scene.sceneEvents_);
        } 
      } 
      if (this.sceneWeekSchedulesBuilder_ == null) {
        if (!param1Scene.sceneWeekSchedules_.isEmpty()) {
          if (this.sceneWeekSchedules_.isEmpty()) {
            this.sceneWeekSchedules_ = param1Scene.sceneWeekSchedules_;
            this.bitField0_ &= 0xFFFFFFEF;
          } else {
            ensureSceneWeekSchedulesIsMutable();
            this.sceneWeekSchedules_.addAll(param1Scene.sceneWeekSchedules_);
          } 
          onChanged();
        } 
      } else if (!param1Scene.sceneWeekSchedules_.isEmpty()) {
        if (this.sceneWeekSchedulesBuilder_.isEmpty()) {
          this.sceneWeekSchedulesBuilder_.dispose();
          this.sceneWeekSchedulesBuilder_ = null;
          this.sceneWeekSchedules_ = param1Scene.sceneWeekSchedules_;
          this.bitField0_ &= 0xFFFFFFEF;
          if (MobilusModel.Scene.alwaysUseFieldBuilders) {
            repeatedFieldBuilderV3 = (RepeatedFieldBuilderV3)getSceneWeekSchedulesFieldBuilder();
          } else {
            repeatedFieldBuilderV3 = null;
          } 
          this.sceneWeekSchedulesBuilder_ = (RepeatedFieldBuilderV3)repeatedFieldBuilderV3;
        } else {
          this.sceneWeekSchedulesBuilder_.addAllMessages(param1Scene.sceneWeekSchedules_);
        } 
      } 
      if (this.sceneAstralSchedulesBuilder_ == null) {
        if (!param1Scene.sceneAstralSchedules_.isEmpty()) {
          if (this.sceneAstralSchedules_.isEmpty()) {
            this.sceneAstralSchedules_ = param1Scene.sceneAstralSchedules_;
            this.bitField0_ &= 0xFFFFFFDF;
          } else {
            ensureSceneAstralSchedulesIsMutable();
            this.sceneAstralSchedules_.addAll(param1Scene.sceneAstralSchedules_);
          } 
          onChanged();
        } 
      } else if (!param1Scene.sceneAstralSchedules_.isEmpty()) {
        if (this.sceneAstralSchedulesBuilder_.isEmpty()) {
          this.sceneAstralSchedulesBuilder_.dispose();
          this.sceneAstralSchedulesBuilder_ = null;
          this.sceneAstralSchedules_ = param1Scene.sceneAstralSchedules_;
          this.bitField0_ &= 0xFFFFFFDF;
          repeatedFieldBuilderV3 = repeatedFieldBuilderV31;
          if (MobilusModel.Scene.alwaysUseFieldBuilders)
            repeatedFieldBuilderV3 = (RepeatedFieldBuilderV3)getSceneAstralSchedulesFieldBuilder(); 
          this.sceneAstralSchedulesBuilder_ = (RepeatedFieldBuilderV3)repeatedFieldBuilderV3;
        } else {
          this.sceneAstralSchedulesBuilder_.addAllMessages(param1Scene.sceneAstralSchedules_);
        } 
      } 
      if (param1Scene.hasEnabled())
        setEnabled(param1Scene.getEnabled()); 
      mergeUnknownFields(param1Scene.unknownFields);
      onChanged();
      return this;
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.mergeUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder removeSceneAstralSchedules(int param1Int) {
      if (this.sceneAstralSchedulesBuilder_ == null) {
        ensureSceneAstralSchedulesIsMutable();
        this.sceneAstralSchedules_.remove(param1Int);
        onChanged();
      } else {
        this.sceneAstralSchedulesBuilder_.remove(param1Int);
      } 
      return this;
    }
    
    public Builder removeSceneEvents(int param1Int) {
      if (this.sceneEventsBuilder_ == null) {
        ensureSceneEventsIsMutable();
        this.sceneEvents_.remove(param1Int);
        onChanged();
      } else {
        this.sceneEventsBuilder_.remove(param1Int);
      } 
      return this;
    }
    
    public Builder removeSceneWeekSchedules(int param1Int) {
      if (this.sceneWeekSchedulesBuilder_ == null) {
        ensureSceneWeekSchedulesIsMutable();
        this.sceneWeekSchedules_.remove(param1Int);
        onChanged();
      } else {
        this.sceneWeekSchedulesBuilder_.remove(param1Int);
      } 
      return this;
    }
    
    public Builder setEnabled(boolean param1Boolean) {
      this.bitField0_ |= 0x40;
      this.enabled_ = param1Boolean;
      onChanged();
      return this;
    }
    
    public Builder setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.setField(param1FieldDescriptor, param1Object);
    }
    
    public Builder setIcon(int param1Int) {
      this.bitField0_ |= 0x4;
      this.icon_ = param1Int;
      onChanged();
      return this;
    }
    
    public Builder setId(long param1Long) {
      this.bitField0_ |= 0x1;
      this.id_ = param1Long;
      onChanged();
      return this;
    }
    
    public Builder setName(String param1String) {
      if (param1String == null)
        throw new NullPointerException(); 
      this.bitField0_ |= 0x2;
      this.name_ = param1String;
      onChanged();
      return this;
    }
    
    public Builder setNameBytes(ByteString param1ByteString) {
      if (param1ByteString == null)
        throw new NullPointerException(); 
      this.bitField0_ |= 0x2;
      this.name_ = param1ByteString;
      onChanged();
      return this;
    }
    
    public Builder setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object) {
      return (Builder)super.setRepeatedField(param1FieldDescriptor, param1Int, param1Object);
    }
    
    public Builder setSceneAstralSchedules(int param1Int, MobilusModel.SceneAstralSchedule.Builder param1Builder) {
      if (this.sceneAstralSchedulesBuilder_ == null) {
        ensureSceneAstralSchedulesIsMutable();
        this.sceneAstralSchedules_.set(param1Int, param1Builder.build());
        onChanged();
      } else {
        this.sceneAstralSchedulesBuilder_.setMessage(param1Int, (AbstractMessage)param1Builder.build());
      } 
      return this;
    }
    
    public Builder setSceneAstralSchedules(int param1Int, MobilusModel.SceneAstralSchedule param1SceneAstralSchedule) {
      if (this.sceneAstralSchedulesBuilder_ == null) {
        if (param1SceneAstralSchedule == null)
          throw new NullPointerException(); 
        ensureSceneAstralSchedulesIsMutable();
        this.sceneAstralSchedules_.set(param1Int, param1SceneAstralSchedule);
        onChanged();
      } else {
        this.sceneAstralSchedulesBuilder_.setMessage(param1Int, (AbstractMessage)param1SceneAstralSchedule);
      } 
      return this;
    }
    
    public Builder setSceneEvents(int param1Int, MobilusModel.SceneEvent.Builder param1Builder) {
      if (this.sceneEventsBuilder_ == null) {
        ensureSceneEventsIsMutable();
        this.sceneEvents_.set(param1Int, param1Builder.build());
        onChanged();
      } else {
        this.sceneEventsBuilder_.setMessage(param1Int, (AbstractMessage)param1Builder.build());
      } 
      return this;
    }
    
    public Builder setSceneEvents(int param1Int, MobilusModel.SceneEvent param1SceneEvent) {
      if (this.sceneEventsBuilder_ == null) {
        if (param1SceneEvent == null)
          throw new NullPointerException(); 
        ensureSceneEventsIsMutable();
        this.sceneEvents_.set(param1Int, param1SceneEvent);
        onChanged();
      } else {
        this.sceneEventsBuilder_.setMessage(param1Int, (AbstractMessage)param1SceneEvent);
      } 
      return this;
    }
    
    public Builder setSceneWeekSchedules(int param1Int, MobilusModel.SceneWeekSchedule.Builder param1Builder) {
      if (this.sceneWeekSchedulesBuilder_ == null) {
        ensureSceneWeekSchedulesIsMutable();
        this.sceneWeekSchedules_.set(param1Int, param1Builder.build());
        onChanged();
      } else {
        this.sceneWeekSchedulesBuilder_.setMessage(param1Int, (AbstractMessage)param1Builder.build());
      } 
      return this;
    }
    
    public Builder setSceneWeekSchedules(int param1Int, MobilusModel.SceneWeekSchedule param1SceneWeekSchedule) {
      if (this.sceneWeekSchedulesBuilder_ == null) {
        if (param1SceneWeekSchedule == null)
          throw new NullPointerException(); 
        ensureSceneWeekSchedulesIsMutable();
        this.sceneWeekSchedules_.set(param1Int, param1SceneWeekSchedule);
        onChanged();
      } else {
        this.sceneWeekSchedulesBuilder_.setMessage(param1Int, (AbstractMessage)param1SceneWeekSchedule);
      } 
      return this;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.setUnknownFields(param1UnknownFieldSet);
    }
  }
  
  public static final class SceneAstralSchedule extends GeneratedMessageV3 implements SceneAstralScheduleOrBuilder {
    public static final int ASTRAL_FIELD_NUMBER = 4;
    
    private static final SceneAstralSchedule DEFAULT_INSTANCE = new SceneAstralSchedule();
    
    public static final int ID_FIELD_NUMBER = 1;
    
    public static final int INTERVAL_FIELD_NUMBER = 3;
    
    @Deprecated
    public static final Parser<SceneAstralSchedule> PARSER = (Parser<SceneAstralSchedule>)new AbstractParser<SceneAstralSchedule>() {
        public MobilusModel.SceneAstralSchedule parsePartialFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws InvalidProtocolBufferException {
          return new MobilusModel.SceneAstralSchedule(param2CodedInputStream, param2ExtensionRegistryLite);
        }
      };
    
    public static final int WEEK_DAY_FIELD_NUMBER = 2;
    
    private static final long serialVersionUID = 0L;
    
    private int astral_;
    
    private int bitField0_;
    
    private long id_;
    
    private int interval_;
    
    private byte memoizedIsInitialized = (byte)-1;
    
    private int weekDay_;
    
    private SceneAstralSchedule() {}
    
    private SceneAstralSchedule(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      this();
      if (param1ExtensionRegistryLite == null)
        throw new NullPointerException(); 
      UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
      for (boolean bool = false; !bool; bool = true) {
        try {
          int i = param1CodedInputStream.readTag();
          if (i != 0) {
            if (i != 8) {
              if (i != 16) {
                if (i != 24) {
                  if (i != 32) {
                    if (!parseUnknownField(param1CodedInputStream, builder, param1ExtensionRegistryLite, i))
                      continue; 
                    continue;
                  } 
                  this.bitField0_ |= 0x8;
                  this.astral_ = param1CodedInputStream.readInt32();
                  continue;
                } 
                this.bitField0_ |= 0x4;
                this.interval_ = param1CodedInputStream.readInt32();
                continue;
              } 
              this.bitField0_ |= 0x2;
              this.weekDay_ = param1CodedInputStream.readInt32();
              continue;
            } 
            this.bitField0_ |= 0x1;
            this.id_ = param1CodedInputStream.readInt64();
            continue;
          } 
          continue;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } catch (IOException iOException) {
          InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException();
          this(iOException);
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } finally {}
        this.unknownFields = builder.build();
        makeExtensionsImmutable();
        throw param1CodedInputStream;
      } 
      this.unknownFields = builder.build();
      makeExtensionsImmutable();
    }
    
    private SceneAstralSchedule(GeneratedMessageV3.Builder<?> param1Builder) {
      super(param1Builder);
    }
    
    public static SceneAstralSchedule getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_SceneAstralSchedule_descriptor;
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(SceneAstralSchedule param1SceneAstralSchedule) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(param1SceneAstralSchedule);
    }
    
    public static SceneAstralSchedule parseDelimitedFrom(InputStream param1InputStream) throws IOException {
      return (SceneAstralSchedule)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream);
    }
    
    public static SceneAstralSchedule parseDelimitedFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (SceneAstralSchedule)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static SceneAstralSchedule parseFrom(ByteString param1ByteString) throws InvalidProtocolBufferException {
      return (SceneAstralSchedule)PARSER.parseFrom(param1ByteString);
    }
    
    public static SceneAstralSchedule parseFrom(ByteString param1ByteString, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (SceneAstralSchedule)PARSER.parseFrom(param1ByteString, param1ExtensionRegistryLite);
    }
    
    public static SceneAstralSchedule parseFrom(CodedInputStream param1CodedInputStream) throws IOException {
      return (SceneAstralSchedule)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream);
    }
    
    public static SceneAstralSchedule parseFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (SceneAstralSchedule)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream, param1ExtensionRegistryLite);
    }
    
    public static SceneAstralSchedule parseFrom(InputStream param1InputStream) throws IOException {
      return (SceneAstralSchedule)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream);
    }
    
    public static SceneAstralSchedule parseFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (SceneAstralSchedule)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static SceneAstralSchedule parseFrom(ByteBuffer param1ByteBuffer) throws InvalidProtocolBufferException {
      return (SceneAstralSchedule)PARSER.parseFrom(param1ByteBuffer);
    }
    
    public static SceneAstralSchedule parseFrom(ByteBuffer param1ByteBuffer, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (SceneAstralSchedule)PARSER.parseFrom(param1ByteBuffer, param1ExtensionRegistryLite);
    }
    
    public static SceneAstralSchedule parseFrom(byte[] param1ArrayOfbyte) throws InvalidProtocolBufferException {
      return (SceneAstralSchedule)PARSER.parseFrom(param1ArrayOfbyte);
    }
    
    public static SceneAstralSchedule parseFrom(byte[] param1ArrayOfbyte, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (SceneAstralSchedule)PARSER.parseFrom(param1ArrayOfbyte, param1ExtensionRegistryLite);
    }
    
    public static Parser<SceneAstralSchedule> parser() {
      return PARSER;
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object == this)
        return true; 
      if (!(param1Object instanceof SceneAstralSchedule))
        return super.equals(param1Object); 
      param1Object = param1Object;
      return (hasId() != param1Object.hasId()) ? false : ((hasId() && getId() != param1Object.getId()) ? false : ((hasWeekDay() != param1Object.hasWeekDay()) ? false : ((hasWeekDay() && getWeekDay() != param1Object.getWeekDay()) ? false : ((hasInterval() != param1Object.hasInterval()) ? false : ((hasInterval() && getInterval() != param1Object.getInterval()) ? false : ((hasAstral() != param1Object.hasAstral()) ? false : ((hasAstral() && getAstral() != param1Object.getAstral()) ? false : (!!this.unknownFields.equals(((SceneAstralSchedule)param1Object).unknownFields)))))))));
    }
    
    public int getAstral() {
      return this.astral_;
    }
    
    public SceneAstralSchedule getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
    
    public long getId() {
      return this.id_;
    }
    
    public int getInterval() {
      return this.interval_;
    }
    
    public Parser<SceneAstralSchedule> getParserForType() {
      return PARSER;
    }
    
    public int getSerializedSize() {
      int i = this.memoizedSize;
      if (i != -1)
        return i; 
      i = 0;
      if ((this.bitField0_ & 0x1) != 0)
        i = 0 + CodedOutputStream.computeInt64Size(1, this.id_); 
      int j = i;
      if ((this.bitField0_ & 0x2) != 0)
        j = i + CodedOutputStream.computeInt32Size(2, this.weekDay_); 
      i = j;
      if ((this.bitField0_ & 0x4) != 0)
        i = j + CodedOutputStream.computeInt32Size(3, this.interval_); 
      j = i;
      if ((this.bitField0_ & 0x8) != 0)
        j = i + CodedOutputStream.computeInt32Size(4, this.astral_); 
      i = j + this.unknownFields.getSerializedSize();
      this.memoizedSize = i;
      return i;
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    public int getWeekDay() {
      return this.weekDay_;
    }
    
    public boolean hasAstral() {
      boolean bool;
      if ((this.bitField0_ & 0x8) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasId() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean hasInterval() {
      boolean bool;
      if ((this.bitField0_ & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasWeekDay() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int hashCode() {
      if (this.memoizedHashCode != 0)
        return this.memoizedHashCode; 
      int i = 779 + getDescriptor().hashCode();
      int j = i;
      if (hasId())
        j = (i * 37 + 1) * 53 + Internal.hashLong(getId()); 
      i = j;
      if (hasWeekDay())
        i = (j * 37 + 2) * 53 + getWeekDay(); 
      j = i;
      if (hasInterval())
        j = (i * 37 + 3) * 53 + getInterval(); 
      i = j;
      if (hasAstral())
        i = (j * 37 + 4) * 53 + getAstral(); 
      j = i * 29 + this.unknownFields.hashCode();
      this.memoizedHashCode = j;
      return j;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_SceneAstralSchedule_fieldAccessorTable.ensureFieldAccessorsInitialized(SceneAstralSchedule.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      byte b = this.memoizedIsInitialized;
      if (b == 1)
        return true; 
      if (b == 0)
        return false; 
      this.memoizedIsInitialized = (byte)1;
      return true;
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      return new Builder(param1BuilderParent);
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter param1UnusedPrivateParameter) {
      return new SceneAstralSchedule();
    }
    
    public Builder toBuilder() {
      Builder builder;
      if (this == DEFAULT_INSTANCE) {
        builder = new Builder();
      } else {
        builder = (new Builder()).mergeFrom(this);
      } 
      return builder;
    }
    
    public void writeTo(CodedOutputStream param1CodedOutputStream) throws IOException {
      if ((this.bitField0_ & 0x1) != 0)
        param1CodedOutputStream.writeInt64(1, this.id_); 
      if ((this.bitField0_ & 0x2) != 0)
        param1CodedOutputStream.writeInt32(2, this.weekDay_); 
      if ((this.bitField0_ & 0x4) != 0)
        param1CodedOutputStream.writeInt32(3, this.interval_); 
      if ((this.bitField0_ & 0x8) != 0)
        param1CodedOutputStream.writeInt32(4, this.astral_); 
      this.unknownFields.writeTo(param1CodedOutputStream);
    }
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MobilusModel.SceneAstralScheduleOrBuilder {
      private int astral_;
      
      private int bitField0_;
      
      private long id_;
      
      private int interval_;
      
      private int weekDay_;
      
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent param2BuilderParent) {
        super(param2BuilderParent);
        maybeForceBuilderInitialization();
      }
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MobilusModel.internal_static_SceneAstralSchedule_descriptor;
      }
      
      private void maybeForceBuilderInitialization() {
        MobilusModel.SceneAstralSchedule.alwaysUseFieldBuilders;
      }
      
      public Builder addRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.addRepeatedField(param2FieldDescriptor, param2Object);
      }
      
      public MobilusModel.SceneAstralSchedule build() {
        MobilusModel.SceneAstralSchedule sceneAstralSchedule = buildPartial();
        if (!sceneAstralSchedule.isInitialized())
          throw newUninitializedMessageException(sceneAstralSchedule); 
        return sceneAstralSchedule;
      }
      
      public MobilusModel.SceneAstralSchedule buildPartial() {
        MobilusModel.SceneAstralSchedule sceneAstralSchedule = new MobilusModel.SceneAstralSchedule(this);
        int i = this.bitField0_;
        if ((i & 0x1) != 0) {
          MobilusModel.SceneAstralSchedule.access$11902(sceneAstralSchedule, this.id_);
          j = 1;
        } else {
          j = 0;
        } 
        int k = j;
        if ((i & 0x2) != 0) {
          MobilusModel.SceneAstralSchedule.access$12002(sceneAstralSchedule, this.weekDay_);
          k = j | 0x2;
        } 
        int j = k;
        if ((i & 0x4) != 0) {
          MobilusModel.SceneAstralSchedule.access$12102(sceneAstralSchedule, this.interval_);
          j = k | 0x4;
        } 
        k = j;
        if ((i & 0x8) != 0) {
          MobilusModel.SceneAstralSchedule.access$12202(sceneAstralSchedule, this.astral_);
          k = j | 0x8;
        } 
        MobilusModel.SceneAstralSchedule.access$12302(sceneAstralSchedule, k);
        onBuilt();
        return sceneAstralSchedule;
      }
      
      public Builder clear() {
        super.clear();
        this.id_ = 0L;
        this.bitField0_ &= 0xFFFFFFFE;
        this.weekDay_ = 0;
        this.bitField0_ &= 0xFFFFFFFD;
        this.interval_ = 0;
        this.bitField0_ &= 0xFFFFFFFB;
        this.astral_ = 0;
        this.bitField0_ &= 0xFFFFFFF7;
        return this;
      }
      
      public Builder clearAstral() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.astral_ = 0;
        onChanged();
        return this;
      }
      
      public Builder clearField(Descriptors.FieldDescriptor param2FieldDescriptor) {
        return (Builder)super.clearField(param2FieldDescriptor);
      }
      
      public Builder clearId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.id_ = 0L;
        onChanged();
        return this;
      }
      
      public Builder clearInterval() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.interval_ = 0;
        onChanged();
        return this;
      }
      
      public Builder clearOneof(Descriptors.OneofDescriptor param2OneofDescriptor) {
        return (Builder)super.clearOneof(param2OneofDescriptor);
      }
      
      public Builder clearWeekDay() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.weekDay_ = 0;
        onChanged();
        return this;
      }
      
      public Builder clone() {
        return (Builder)super.clone();
      }
      
      public int getAstral() {
        return this.astral_;
      }
      
      public MobilusModel.SceneAstralSchedule getDefaultInstanceForType() {
        return MobilusModel.SceneAstralSchedule.getDefaultInstance();
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MobilusModel.internal_static_SceneAstralSchedule_descriptor;
      }
      
      public long getId() {
        return this.id_;
      }
      
      public int getInterval() {
        return this.interval_;
      }
      
      public int getWeekDay() {
        return this.weekDay_;
      }
      
      public boolean hasAstral() {
        boolean bool;
        if ((this.bitField0_ & 0x8) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasId() {
        int i = this.bitField0_;
        boolean bool = true;
        if ((i & 0x1) == 0)
          bool = false; 
        return bool;
      }
      
      public boolean hasInterval() {
        boolean bool;
        if ((this.bitField0_ & 0x4) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasWeekDay() {
        boolean bool;
        if ((this.bitField0_ & 0x2) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MobilusModel.internal_static_SceneAstralSchedule_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.SceneAstralSchedule.class, Builder.class);
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws IOException {
        Exception exception;
        CodedInputStream codedInputStream = null;
        try {
          MobilusModel.SceneAstralSchedule sceneAstralSchedule = (MobilusModel.SceneAstralSchedule)MobilusModel.SceneAstralSchedule.PARSER.parsePartialFrom(param2CodedInputStream, param2ExtensionRegistryLite);
          return this;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          MobilusModel.SceneAstralSchedule sceneAstralSchedule = (MobilusModel.SceneAstralSchedule)invalidProtocolBufferException.getUnfinishedMessage();
        } finally {
          exception = null;
        } 
        if (param2CodedInputStream != null)
          mergeFrom((MobilusModel.SceneAstralSchedule)param2CodedInputStream); 
        throw exception;
      }
      
      public Builder mergeFrom(Message param2Message) {
        if (param2Message instanceof MobilusModel.SceneAstralSchedule)
          return mergeFrom((MobilusModel.SceneAstralSchedule)param2Message); 
        super.mergeFrom(param2Message);
        return this;
      }
      
      public Builder mergeFrom(MobilusModel.SceneAstralSchedule param2SceneAstralSchedule) {
        if (param2SceneAstralSchedule == MobilusModel.SceneAstralSchedule.getDefaultInstance())
          return this; 
        if (param2SceneAstralSchedule.hasId())
          setId(param2SceneAstralSchedule.getId()); 
        if (param2SceneAstralSchedule.hasWeekDay())
          setWeekDay(param2SceneAstralSchedule.getWeekDay()); 
        if (param2SceneAstralSchedule.hasInterval())
          setInterval(param2SceneAstralSchedule.getInterval()); 
        if (param2SceneAstralSchedule.hasAstral())
          setAstral(param2SceneAstralSchedule.getAstral()); 
        mergeUnknownFields(param2SceneAstralSchedule.unknownFields);
        onChanged();
        return this;
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.mergeUnknownFields(param2UnknownFieldSet);
      }
      
      public Builder setAstral(int param2Int) {
        this.bitField0_ |= 0x8;
        this.astral_ = param2Int;
        onChanged();
        return this;
      }
      
      public Builder setField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.setField(param2FieldDescriptor, param2Object);
      }
      
      public Builder setId(long param2Long) {
        this.bitField0_ |= 0x1;
        this.id_ = param2Long;
        onChanged();
        return this;
      }
      
      public Builder setInterval(int param2Int) {
        this.bitField0_ |= 0x4;
        this.interval_ = param2Int;
        onChanged();
        return this;
      }
      
      public Builder setRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, int param2Int, Object param2Object) {
        return (Builder)super.setRepeatedField(param2FieldDescriptor, param2Int, param2Object);
      }
      
      public final Builder setUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.setUnknownFields(param2UnknownFieldSet);
      }
      
      public Builder setWeekDay(int param2Int) {
        this.bitField0_ |= 0x2;
        this.weekDay_ = param2Int;
        onChanged();
        return this;
      }
    }
  }
  
  static final class null extends AbstractParser<SceneAstralSchedule> {
    public MobilusModel.SceneAstralSchedule parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return new MobilusModel.SceneAstralSchedule(param1CodedInputStream, param1ExtensionRegistryLite);
    }
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<SceneAstralSchedule.Builder> implements SceneAstralScheduleOrBuilder {
    private int astral_;
    
    private int bitField0_;
    
    private long id_;
    
    private int interval_;
    
    private int weekDay_;
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      super(param1BuilderParent);
      maybeForceBuilderInitialization();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_SceneAstralSchedule_descriptor;
    }
    
    private void maybeForceBuilderInitialization() {
      MobilusModel.SceneAstralSchedule.alwaysUseFieldBuilders;
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.addRepeatedField(param1FieldDescriptor, param1Object);
    }
    
    public MobilusModel.SceneAstralSchedule build() {
      MobilusModel.SceneAstralSchedule sceneAstralSchedule = buildPartial();
      if (!sceneAstralSchedule.isInitialized())
        throw newUninitializedMessageException(sceneAstralSchedule); 
      return sceneAstralSchedule;
    }
    
    public MobilusModel.SceneAstralSchedule buildPartial() {
      MobilusModel.SceneAstralSchedule sceneAstralSchedule = new MobilusModel.SceneAstralSchedule(this);
      int i = this.bitField0_;
      if ((i & 0x1) != 0) {
        MobilusModel.SceneAstralSchedule.access$11902(sceneAstralSchedule, this.id_);
        j = 1;
      } else {
        j = 0;
      } 
      int k = j;
      if ((i & 0x2) != 0) {
        MobilusModel.SceneAstralSchedule.access$12002(sceneAstralSchedule, this.weekDay_);
        k = j | 0x2;
      } 
      int j = k;
      if ((i & 0x4) != 0) {
        MobilusModel.SceneAstralSchedule.access$12102(sceneAstralSchedule, this.interval_);
        j = k | 0x4;
      } 
      k = j;
      if ((i & 0x8) != 0) {
        MobilusModel.SceneAstralSchedule.access$12202(sceneAstralSchedule, this.astral_);
        k = j | 0x8;
      } 
      MobilusModel.SceneAstralSchedule.access$12302(sceneAstralSchedule, k);
      onBuilt();
      return sceneAstralSchedule;
    }
    
    public Builder clear() {
      super.clear();
      this.id_ = 0L;
      this.bitField0_ &= 0xFFFFFFFE;
      this.weekDay_ = 0;
      this.bitField0_ &= 0xFFFFFFFD;
      this.interval_ = 0;
      this.bitField0_ &= 0xFFFFFFFB;
      this.astral_ = 0;
      this.bitField0_ &= 0xFFFFFFF7;
      return this;
    }
    
    public Builder clearAstral() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.astral_ = 0;
      onChanged();
      return this;
    }
    
    public Builder clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return (Builder)super.clearField(param1FieldDescriptor);
    }
    
    public Builder clearId() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.id_ = 0L;
      onChanged();
      return this;
    }
    
    public Builder clearInterval() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.interval_ = 0;
      onChanged();
      return this;
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return (Builder)super.clearOneof(param1OneofDescriptor);
    }
    
    public Builder clearWeekDay() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.weekDay_ = 0;
      onChanged();
      return this;
    }
    
    public Builder clone() {
      return (Builder)super.clone();
    }
    
    public int getAstral() {
      return this.astral_;
    }
    
    public MobilusModel.SceneAstralSchedule getDefaultInstanceForType() {
      return MobilusModel.SceneAstralSchedule.getDefaultInstance();
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return MobilusModel.internal_static_SceneAstralSchedule_descriptor;
    }
    
    public long getId() {
      return this.id_;
    }
    
    public int getInterval() {
      return this.interval_;
    }
    
    public int getWeekDay() {
      return this.weekDay_;
    }
    
    public boolean hasAstral() {
      boolean bool;
      if ((this.bitField0_ & 0x8) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasId() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean hasInterval() {
      boolean bool;
      if ((this.bitField0_ & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasWeekDay() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_SceneAstralSchedule_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.SceneAstralSchedule.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      Exception exception;
      CodedInputStream codedInputStream = null;
      try {
        MobilusModel.SceneAstralSchedule sceneAstralSchedule = (MobilusModel.SceneAstralSchedule)MobilusModel.SceneAstralSchedule.PARSER.parsePartialFrom(param1CodedInputStream, param1ExtensionRegistryLite);
        return this;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        MobilusModel.SceneAstralSchedule sceneAstralSchedule = (MobilusModel.SceneAstralSchedule)invalidProtocolBufferException.getUnfinishedMessage();
      } finally {
        exception = null;
      } 
      if (param1CodedInputStream != null)
        mergeFrom((MobilusModel.SceneAstralSchedule)param1CodedInputStream); 
      throw exception;
    }
    
    public Builder mergeFrom(Message param1Message) {
      if (param1Message instanceof MobilusModel.SceneAstralSchedule)
        return mergeFrom((MobilusModel.SceneAstralSchedule)param1Message); 
      super.mergeFrom(param1Message);
      return this;
    }
    
    public Builder mergeFrom(MobilusModel.SceneAstralSchedule param1SceneAstralSchedule) {
      if (param1SceneAstralSchedule == MobilusModel.SceneAstralSchedule.getDefaultInstance())
        return this; 
      if (param1SceneAstralSchedule.hasId())
        setId(param1SceneAstralSchedule.getId()); 
      if (param1SceneAstralSchedule.hasWeekDay())
        setWeekDay(param1SceneAstralSchedule.getWeekDay()); 
      if (param1SceneAstralSchedule.hasInterval())
        setInterval(param1SceneAstralSchedule.getInterval()); 
      if (param1SceneAstralSchedule.hasAstral())
        setAstral(param1SceneAstralSchedule.getAstral()); 
      mergeUnknownFields(param1SceneAstralSchedule.unknownFields);
      onChanged();
      return this;
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.mergeUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder setAstral(int param1Int) {
      this.bitField0_ |= 0x8;
      this.astral_ = param1Int;
      onChanged();
      return this;
    }
    
    public Builder setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.setField(param1FieldDescriptor, param1Object);
    }
    
    public Builder setId(long param1Long) {
      this.bitField0_ |= 0x1;
      this.id_ = param1Long;
      onChanged();
      return this;
    }
    
    public Builder setInterval(int param1Int) {
      this.bitField0_ |= 0x4;
      this.interval_ = param1Int;
      onChanged();
      return this;
    }
    
    public Builder setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object) {
      return (Builder)super.setRepeatedField(param1FieldDescriptor, param1Int, param1Object);
    }
    
    public final Builder setUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.setUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder setWeekDay(int param1Int) {
      this.bitField0_ |= 0x2;
      this.weekDay_ = param1Int;
      onChanged();
      return this;
    }
  }
  
  public static interface SceneAstralScheduleOrBuilder extends MessageOrBuilder {
    int getAstral();
    
    long getId();
    
    int getInterval();
    
    int getWeekDay();
    
    boolean hasAstral();
    
    boolean hasId();
    
    boolean hasInterval();
    
    boolean hasWeekDay();
  }
  
  public static final class SceneEvent extends GeneratedMessageV3 implements SceneEventOrBuilder {
    private static final SceneEvent DEFAULT_INSTANCE = new SceneEvent();
    
    public static final int DEVICE_ID_FIELD_NUMBER = 2;
    
    public static final int EVENT_NUMBER_FIELD_NUMBER = 3;
    
    public static final int ID_FIELD_NUMBER = 1;
    
    @Deprecated
    public static final Parser<SceneEvent> PARSER = (Parser<SceneEvent>)new AbstractParser<SceneEvent>() {
        public MobilusModel.SceneEvent parsePartialFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws InvalidProtocolBufferException {
          return new MobilusModel.SceneEvent(param2CodedInputStream, param2ExtensionRegistryLite);
        }
      };
    
    public static final int VALUE_FIELD_NUMBER = 4;
    
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    private long deviceId_;
    
    private int eventNumber_;
    
    private long id_;
    
    private byte memoizedIsInitialized = (byte)-1;
    
    private volatile Object value_;
    
    private SceneEvent() {
      this.value_ = "";
    }
    
    private SceneEvent(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      this();
      if (param1ExtensionRegistryLite == null)
        throw new NullPointerException(); 
      UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
      for (boolean bool = false; !bool; bool = true) {
        try {
          int i = param1CodedInputStream.readTag();
          if (i != 0) {
            if (i != 8) {
              if (i != 16) {
                if (i != 24) {
                  if (i != 34) {
                    if (!parseUnknownField(param1CodedInputStream, builder, param1ExtensionRegistryLite, i))
                      continue; 
                    continue;
                  } 
                  ByteString byteString = param1CodedInputStream.readBytes();
                  this.bitField0_ |= 0x8;
                  this.value_ = byteString;
                  continue;
                } 
                this.bitField0_ |= 0x4;
                this.eventNumber_ = param1CodedInputStream.readInt32();
                continue;
              } 
              this.bitField0_ |= 0x2;
              this.deviceId_ = param1CodedInputStream.readInt64();
              continue;
            } 
            this.bitField0_ |= 0x1;
            this.id_ = param1CodedInputStream.readInt64();
            continue;
          } 
          continue;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } catch (IOException iOException) {
          InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException();
          this(iOException);
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } finally {}
        this.unknownFields = builder.build();
        makeExtensionsImmutable();
        throw param1CodedInputStream;
      } 
      this.unknownFields = builder.build();
      makeExtensionsImmutable();
    }
    
    private SceneEvent(GeneratedMessageV3.Builder<?> param1Builder) {
      super(param1Builder);
    }
    
    public static SceneEvent getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_SceneEvent_descriptor;
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(SceneEvent param1SceneEvent) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(param1SceneEvent);
    }
    
    public static SceneEvent parseDelimitedFrom(InputStream param1InputStream) throws IOException {
      return (SceneEvent)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream);
    }
    
    public static SceneEvent parseDelimitedFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (SceneEvent)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static SceneEvent parseFrom(ByteString param1ByteString) throws InvalidProtocolBufferException {
      return (SceneEvent)PARSER.parseFrom(param1ByteString);
    }
    
    public static SceneEvent parseFrom(ByteString param1ByteString, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (SceneEvent)PARSER.parseFrom(param1ByteString, param1ExtensionRegistryLite);
    }
    
    public static SceneEvent parseFrom(CodedInputStream param1CodedInputStream) throws IOException {
      return (SceneEvent)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream);
    }
    
    public static SceneEvent parseFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (SceneEvent)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream, param1ExtensionRegistryLite);
    }
    
    public static SceneEvent parseFrom(InputStream param1InputStream) throws IOException {
      return (SceneEvent)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream);
    }
    
    public static SceneEvent parseFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (SceneEvent)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static SceneEvent parseFrom(ByteBuffer param1ByteBuffer) throws InvalidProtocolBufferException {
      return (SceneEvent)PARSER.parseFrom(param1ByteBuffer);
    }
    
    public static SceneEvent parseFrom(ByteBuffer param1ByteBuffer, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (SceneEvent)PARSER.parseFrom(param1ByteBuffer, param1ExtensionRegistryLite);
    }
    
    public static SceneEvent parseFrom(byte[] param1ArrayOfbyte) throws InvalidProtocolBufferException {
      return (SceneEvent)PARSER.parseFrom(param1ArrayOfbyte);
    }
    
    public static SceneEvent parseFrom(byte[] param1ArrayOfbyte, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (SceneEvent)PARSER.parseFrom(param1ArrayOfbyte, param1ExtensionRegistryLite);
    }
    
    public static Parser<SceneEvent> parser() {
      return PARSER;
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object == this)
        return true; 
      if (!(param1Object instanceof SceneEvent))
        return super.equals(param1Object); 
      param1Object = param1Object;
      return (hasId() != param1Object.hasId()) ? false : ((hasId() && getId() != param1Object.getId()) ? false : ((hasDeviceId() != param1Object.hasDeviceId()) ? false : ((hasDeviceId() && getDeviceId() != param1Object.getDeviceId()) ? false : ((hasEventNumber() != param1Object.hasEventNumber()) ? false : ((hasEventNumber() && getEventNumber() != param1Object.getEventNumber()) ? false : ((hasValue() != param1Object.hasValue()) ? false : ((hasValue() && !getValue().equals(param1Object.getValue())) ? false : (!!this.unknownFields.equals(((SceneEvent)param1Object).unknownFields)))))))));
    }
    
    public SceneEvent getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
    
    public long getDeviceId() {
      return this.deviceId_;
    }
    
    public int getEventNumber() {
      return this.eventNumber_;
    }
    
    public long getId() {
      return this.id_;
    }
    
    public Parser<SceneEvent> getParserForType() {
      return PARSER;
    }
    
    public int getSerializedSize() {
      int i = this.memoizedSize;
      if (i != -1)
        return i; 
      i = 0;
      if ((this.bitField0_ & 0x1) != 0)
        i = 0 + CodedOutputStream.computeInt64Size(1, this.id_); 
      int j = i;
      if ((this.bitField0_ & 0x2) != 0)
        j = i + CodedOutputStream.computeInt64Size(2, this.deviceId_); 
      i = j;
      if ((this.bitField0_ & 0x4) != 0)
        i = j + CodedOutputStream.computeInt32Size(3, this.eventNumber_); 
      j = i;
      if ((this.bitField0_ & 0x8) != 0)
        j = i + GeneratedMessageV3.computeStringSize(4, this.value_); 
      i = j + this.unknownFields.getSerializedSize();
      this.memoizedSize = i;
      return i;
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    public String getValue() {
      Object object = this.value_;
      if (object instanceof String)
        return (String)object; 
      ByteString byteString = (ByteString)object;
      object = byteString.toStringUtf8();
      if (byteString.isValidUtf8())
        this.value_ = object; 
      return (String)object;
    }
    
    public ByteString getValueBytes() {
      Object object = this.value_;
      if (object instanceof String) {
        object = ByteString.copyFromUtf8((String)object);
        this.value_ = object;
        return (ByteString)object;
      } 
      return (ByteString)object;
    }
    
    public boolean hasDeviceId() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasEventNumber() {
      boolean bool;
      if ((this.bitField0_ & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasId() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean hasValue() {
      boolean bool;
      if ((this.bitField0_ & 0x8) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int hashCode() {
      if (this.memoizedHashCode != 0)
        return this.memoizedHashCode; 
      int i = 779 + getDescriptor().hashCode();
      int j = i;
      if (hasId())
        j = (i * 37 + 1) * 53 + Internal.hashLong(getId()); 
      i = j;
      if (hasDeviceId())
        i = (j * 37 + 2) * 53 + Internal.hashLong(getDeviceId()); 
      j = i;
      if (hasEventNumber())
        j = (i * 37 + 3) * 53 + getEventNumber(); 
      i = j;
      if (hasValue())
        i = (j * 37 + 4) * 53 + getValue().hashCode(); 
      j = i * 29 + this.unknownFields.hashCode();
      this.memoizedHashCode = j;
      return j;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_SceneEvent_fieldAccessorTable.ensureFieldAccessorsInitialized(SceneEvent.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      byte b = this.memoizedIsInitialized;
      if (b == 1)
        return true; 
      if (b == 0)
        return false; 
      this.memoizedIsInitialized = (byte)1;
      return true;
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      return new Builder(param1BuilderParent);
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter param1UnusedPrivateParameter) {
      return new SceneEvent();
    }
    
    public Builder toBuilder() {
      Builder builder;
      if (this == DEFAULT_INSTANCE) {
        builder = new Builder();
      } else {
        builder = (new Builder()).mergeFrom(this);
      } 
      return builder;
    }
    
    public void writeTo(CodedOutputStream param1CodedOutputStream) throws IOException {
      if ((this.bitField0_ & 0x1) != 0)
        param1CodedOutputStream.writeInt64(1, this.id_); 
      if ((this.bitField0_ & 0x2) != 0)
        param1CodedOutputStream.writeInt64(2, this.deviceId_); 
      if ((this.bitField0_ & 0x4) != 0)
        param1CodedOutputStream.writeInt32(3, this.eventNumber_); 
      if ((this.bitField0_ & 0x8) != 0)
        GeneratedMessageV3.writeString(param1CodedOutputStream, 4, this.value_); 
      this.unknownFields.writeTo(param1CodedOutputStream);
    }
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MobilusModel.SceneEventOrBuilder {
      private int bitField0_;
      
      private long deviceId_;
      
      private int eventNumber_;
      
      private long id_;
      
      private Object value_ = "";
      
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent param2BuilderParent) {
        super(param2BuilderParent);
        maybeForceBuilderInitialization();
      }
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MobilusModel.internal_static_SceneEvent_descriptor;
      }
      
      private void maybeForceBuilderInitialization() {
        MobilusModel.SceneEvent.alwaysUseFieldBuilders;
      }
      
      public Builder addRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.addRepeatedField(param2FieldDescriptor, param2Object);
      }
      
      public MobilusModel.SceneEvent build() {
        MobilusModel.SceneEvent sceneEvent = buildPartial();
        if (!sceneEvent.isInitialized())
          throw newUninitializedMessageException(sceneEvent); 
        return sceneEvent;
      }
      
      public MobilusModel.SceneEvent buildPartial() {
        MobilusModel.SceneEvent sceneEvent = new MobilusModel.SceneEvent(this);
        int i = this.bitField0_;
        if ((i & 0x1) != 0) {
          MobilusModel.SceneEvent.access$9402(sceneEvent, this.id_);
          j = 1;
        } else {
          j = 0;
        } 
        int k = j;
        if ((i & 0x2) != 0) {
          MobilusModel.SceneEvent.access$9502(sceneEvent, this.deviceId_);
          k = j | 0x2;
        } 
        int j = k;
        if ((i & 0x4) != 0) {
          MobilusModel.SceneEvent.access$9602(sceneEvent, this.eventNumber_);
          j = k | 0x4;
        } 
        k = j;
        if ((i & 0x8) != 0)
          k = j | 0x8; 
        MobilusModel.SceneEvent.access$9702(sceneEvent, this.value_);
        MobilusModel.SceneEvent.access$9802(sceneEvent, k);
        onBuilt();
        return sceneEvent;
      }
      
      public Builder clear() {
        super.clear();
        this.id_ = 0L;
        this.bitField0_ &= 0xFFFFFFFE;
        this.deviceId_ = 0L;
        this.bitField0_ &= 0xFFFFFFFD;
        this.eventNumber_ = 0;
        this.bitField0_ &= 0xFFFFFFFB;
        this.value_ = "";
        this.bitField0_ &= 0xFFFFFFF7;
        return this;
      }
      
      public Builder clearDeviceId() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.deviceId_ = 0L;
        onChanged();
        return this;
      }
      
      public Builder clearEventNumber() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.eventNumber_ = 0;
        onChanged();
        return this;
      }
      
      public Builder clearField(Descriptors.FieldDescriptor param2FieldDescriptor) {
        return (Builder)super.clearField(param2FieldDescriptor);
      }
      
      public Builder clearId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.id_ = 0L;
        onChanged();
        return this;
      }
      
      public Builder clearOneof(Descriptors.OneofDescriptor param2OneofDescriptor) {
        return (Builder)super.clearOneof(param2OneofDescriptor);
      }
      
      public Builder clearValue() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.value_ = MobilusModel.SceneEvent.getDefaultInstance().getValue();
        onChanged();
        return this;
      }
      
      public Builder clone() {
        return (Builder)super.clone();
      }
      
      public MobilusModel.SceneEvent getDefaultInstanceForType() {
        return MobilusModel.SceneEvent.getDefaultInstance();
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MobilusModel.internal_static_SceneEvent_descriptor;
      }
      
      public long getDeviceId() {
        return this.deviceId_;
      }
      
      public int getEventNumber() {
        return this.eventNumber_;
      }
      
      public long getId() {
        return this.id_;
      }
      
      public String getValue() {
        Object object = this.value_;
        if (!(object instanceof String)) {
          object = object;
          String str = object.toStringUtf8();
          if (object.isValidUtf8())
            this.value_ = str; 
          return str;
        } 
        return (String)object;
      }
      
      public ByteString getValueBytes() {
        Object object = this.value_;
        if (object instanceof String) {
          object = ByteString.copyFromUtf8((String)object);
          this.value_ = object;
          return (ByteString)object;
        } 
        return (ByteString)object;
      }
      
      public boolean hasDeviceId() {
        boolean bool;
        if ((this.bitField0_ & 0x2) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasEventNumber() {
        boolean bool;
        if ((this.bitField0_ & 0x4) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasId() {
        int i = this.bitField0_;
        boolean bool = true;
        if ((i & 0x1) == 0)
          bool = false; 
        return bool;
      }
      
      public boolean hasValue() {
        boolean bool;
        if ((this.bitField0_ & 0x8) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MobilusModel.internal_static_SceneEvent_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.SceneEvent.class, Builder.class);
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws IOException {
        ExtensionRegistryLite extensionRegistryLite = null;
        try {
          MobilusModel.SceneEvent sceneEvent = (MobilusModel.SceneEvent)MobilusModel.SceneEvent.PARSER.parsePartialFrom(param2CodedInputStream, param2ExtensionRegistryLite);
          return this;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          MobilusModel.SceneEvent sceneEvent = (MobilusModel.SceneEvent)invalidProtocolBufferException.getUnfinishedMessage();
        } finally {
          param2CodedInputStream = null;
        } 
        if (param2ExtensionRegistryLite != null)
          mergeFrom((MobilusModel.SceneEvent)param2ExtensionRegistryLite); 
        throw param2CodedInputStream;
      }
      
      public Builder mergeFrom(Message param2Message) {
        if (param2Message instanceof MobilusModel.SceneEvent)
          return mergeFrom((MobilusModel.SceneEvent)param2Message); 
        super.mergeFrom(param2Message);
        return this;
      }
      
      public Builder mergeFrom(MobilusModel.SceneEvent param2SceneEvent) {
        if (param2SceneEvent == MobilusModel.SceneEvent.getDefaultInstance())
          return this; 
        if (param2SceneEvent.hasId())
          setId(param2SceneEvent.getId()); 
        if (param2SceneEvent.hasDeviceId())
          setDeviceId(param2SceneEvent.getDeviceId()); 
        if (param2SceneEvent.hasEventNumber())
          setEventNumber(param2SceneEvent.getEventNumber()); 
        if (param2SceneEvent.hasValue()) {
          this.bitField0_ |= 0x8;
          this.value_ = param2SceneEvent.value_;
          onChanged();
        } 
        mergeUnknownFields(param2SceneEvent.unknownFields);
        onChanged();
        return this;
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.mergeUnknownFields(param2UnknownFieldSet);
      }
      
      public Builder setDeviceId(long param2Long) {
        this.bitField0_ |= 0x2;
        this.deviceId_ = param2Long;
        onChanged();
        return this;
      }
      
      public Builder setEventNumber(int param2Int) {
        this.bitField0_ |= 0x4;
        this.eventNumber_ = param2Int;
        onChanged();
        return this;
      }
      
      public Builder setField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.setField(param2FieldDescriptor, param2Object);
      }
      
      public Builder setId(long param2Long) {
        this.bitField0_ |= 0x1;
        this.id_ = param2Long;
        onChanged();
        return this;
      }
      
      public Builder setRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, int param2Int, Object param2Object) {
        return (Builder)super.setRepeatedField(param2FieldDescriptor, param2Int, param2Object);
      }
      
      public final Builder setUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.setUnknownFields(param2UnknownFieldSet);
      }
      
      public Builder setValue(String param2String) {
        if (param2String == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x8;
        this.value_ = param2String;
        onChanged();
        return this;
      }
      
      public Builder setValueBytes(ByteString param2ByteString) {
        if (param2ByteString == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x8;
        this.value_ = param2ByteString;
        onChanged();
        return this;
      }
    }
  }
  
  static final class null extends AbstractParser<SceneEvent> {
    public MobilusModel.SceneEvent parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return new MobilusModel.SceneEvent(param1CodedInputStream, param1ExtensionRegistryLite);
    }
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<SceneEvent.Builder> implements SceneEventOrBuilder {
    private int bitField0_;
    
    private long deviceId_;
    
    private int eventNumber_;
    
    private long id_;
    
    private Object value_ = "";
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      super(param1BuilderParent);
      maybeForceBuilderInitialization();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_SceneEvent_descriptor;
    }
    
    private void maybeForceBuilderInitialization() {
      MobilusModel.SceneEvent.alwaysUseFieldBuilders;
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.addRepeatedField(param1FieldDescriptor, param1Object);
    }
    
    public MobilusModel.SceneEvent build() {
      MobilusModel.SceneEvent sceneEvent = buildPartial();
      if (!sceneEvent.isInitialized())
        throw newUninitializedMessageException(sceneEvent); 
      return sceneEvent;
    }
    
    public MobilusModel.SceneEvent buildPartial() {
      MobilusModel.SceneEvent sceneEvent = new MobilusModel.SceneEvent(this);
      int i = this.bitField0_;
      if ((i & 0x1) != 0) {
        MobilusModel.SceneEvent.access$9402(sceneEvent, this.id_);
        j = 1;
      } else {
        j = 0;
      } 
      int k = j;
      if ((i & 0x2) != 0) {
        MobilusModel.SceneEvent.access$9502(sceneEvent, this.deviceId_);
        k = j | 0x2;
      } 
      int j = k;
      if ((i & 0x4) != 0) {
        MobilusModel.SceneEvent.access$9602(sceneEvent, this.eventNumber_);
        j = k | 0x4;
      } 
      k = j;
      if ((i & 0x8) != 0)
        k = j | 0x8; 
      MobilusModel.SceneEvent.access$9702(sceneEvent, this.value_);
      MobilusModel.SceneEvent.access$9802(sceneEvent, k);
      onBuilt();
      return sceneEvent;
    }
    
    public Builder clear() {
      super.clear();
      this.id_ = 0L;
      this.bitField0_ &= 0xFFFFFFFE;
      this.deviceId_ = 0L;
      this.bitField0_ &= 0xFFFFFFFD;
      this.eventNumber_ = 0;
      this.bitField0_ &= 0xFFFFFFFB;
      this.value_ = "";
      this.bitField0_ &= 0xFFFFFFF7;
      return this;
    }
    
    public Builder clearDeviceId() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.deviceId_ = 0L;
      onChanged();
      return this;
    }
    
    public Builder clearEventNumber() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.eventNumber_ = 0;
      onChanged();
      return this;
    }
    
    public Builder clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return (Builder)super.clearField(param1FieldDescriptor);
    }
    
    public Builder clearId() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.id_ = 0L;
      onChanged();
      return this;
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return (Builder)super.clearOneof(param1OneofDescriptor);
    }
    
    public Builder clearValue() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.value_ = MobilusModel.SceneEvent.getDefaultInstance().getValue();
      onChanged();
      return this;
    }
    
    public Builder clone() {
      return (Builder)super.clone();
    }
    
    public MobilusModel.SceneEvent getDefaultInstanceForType() {
      return MobilusModel.SceneEvent.getDefaultInstance();
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return MobilusModel.internal_static_SceneEvent_descriptor;
    }
    
    public long getDeviceId() {
      return this.deviceId_;
    }
    
    public int getEventNumber() {
      return this.eventNumber_;
    }
    
    public long getId() {
      return this.id_;
    }
    
    public String getValue() {
      Object object = this.value_;
      if (!(object instanceof String)) {
        object = object;
        String str = object.toStringUtf8();
        if (object.isValidUtf8())
          this.value_ = str; 
        return str;
      } 
      return (String)object;
    }
    
    public ByteString getValueBytes() {
      Object object = this.value_;
      if (object instanceof String) {
        object = ByteString.copyFromUtf8((String)object);
        this.value_ = object;
        return (ByteString)object;
      } 
      return (ByteString)object;
    }
    
    public boolean hasDeviceId() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasEventNumber() {
      boolean bool;
      if ((this.bitField0_ & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasId() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean hasValue() {
      boolean bool;
      if ((this.bitField0_ & 0x8) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_SceneEvent_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.SceneEvent.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      ExtensionRegistryLite extensionRegistryLite = null;
      try {
        MobilusModel.SceneEvent sceneEvent = (MobilusModel.SceneEvent)MobilusModel.SceneEvent.PARSER.parsePartialFrom(param1CodedInputStream, param1ExtensionRegistryLite);
        return this;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        MobilusModel.SceneEvent sceneEvent = (MobilusModel.SceneEvent)invalidProtocolBufferException.getUnfinishedMessage();
      } finally {
        param1CodedInputStream = null;
      } 
      if (param1ExtensionRegistryLite != null)
        mergeFrom((MobilusModel.SceneEvent)param1ExtensionRegistryLite); 
      throw param1CodedInputStream;
    }
    
    public Builder mergeFrom(Message param1Message) {
      if (param1Message instanceof MobilusModel.SceneEvent)
        return mergeFrom((MobilusModel.SceneEvent)param1Message); 
      super.mergeFrom(param1Message);
      return this;
    }
    
    public Builder mergeFrom(MobilusModel.SceneEvent param1SceneEvent) {
      if (param1SceneEvent == MobilusModel.SceneEvent.getDefaultInstance())
        return this; 
      if (param1SceneEvent.hasId())
        setId(param1SceneEvent.getId()); 
      if (param1SceneEvent.hasDeviceId())
        setDeviceId(param1SceneEvent.getDeviceId()); 
      if (param1SceneEvent.hasEventNumber())
        setEventNumber(param1SceneEvent.getEventNumber()); 
      if (param1SceneEvent.hasValue()) {
        this.bitField0_ |= 0x8;
        this.value_ = param1SceneEvent.value_;
        onChanged();
      } 
      mergeUnknownFields(param1SceneEvent.unknownFields);
      onChanged();
      return this;
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.mergeUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder setDeviceId(long param1Long) {
      this.bitField0_ |= 0x2;
      this.deviceId_ = param1Long;
      onChanged();
      return this;
    }
    
    public Builder setEventNumber(int param1Int) {
      this.bitField0_ |= 0x4;
      this.eventNumber_ = param1Int;
      onChanged();
      return this;
    }
    
    public Builder setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.setField(param1FieldDescriptor, param1Object);
    }
    
    public Builder setId(long param1Long) {
      this.bitField0_ |= 0x1;
      this.id_ = param1Long;
      onChanged();
      return this;
    }
    
    public Builder setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object) {
      return (Builder)super.setRepeatedField(param1FieldDescriptor, param1Int, param1Object);
    }
    
    public final Builder setUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.setUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder setValue(String param1String) {
      if (param1String == null)
        throw new NullPointerException(); 
      this.bitField0_ |= 0x8;
      this.value_ = param1String;
      onChanged();
      return this;
    }
    
    public Builder setValueBytes(ByteString param1ByteString) {
      if (param1ByteString == null)
        throw new NullPointerException(); 
      this.bitField0_ |= 0x8;
      this.value_ = param1ByteString;
      onChanged();
      return this;
    }
  }
  
  public static interface SceneEventOrBuilder extends MessageOrBuilder {
    long getDeviceId();
    
    int getEventNumber();
    
    long getId();
    
    String getValue();
    
    ByteString getValueBytes();
    
    boolean hasDeviceId();
    
    boolean hasEventNumber();
    
    boolean hasId();
    
    boolean hasValue();
  }
  
  public static interface SceneOrBuilder extends MessageOrBuilder {
    boolean getEnabled();
    
    int getIcon();
    
    long getId();
    
    String getName();
    
    ByteString getNameBytes();
    
    MobilusModel.SceneAstralSchedule getSceneAstralSchedules(int param1Int);
    
    int getSceneAstralSchedulesCount();
    
    List<MobilusModel.SceneAstralSchedule> getSceneAstralSchedulesList();
    
    MobilusModel.SceneAstralScheduleOrBuilder getSceneAstralSchedulesOrBuilder(int param1Int);
    
    List<? extends MobilusModel.SceneAstralScheduleOrBuilder> getSceneAstralSchedulesOrBuilderList();
    
    MobilusModel.SceneEvent getSceneEvents(int param1Int);
    
    int getSceneEventsCount();
    
    List<MobilusModel.SceneEvent> getSceneEventsList();
    
    MobilusModel.SceneEventOrBuilder getSceneEventsOrBuilder(int param1Int);
    
    List<? extends MobilusModel.SceneEventOrBuilder> getSceneEventsOrBuilderList();
    
    MobilusModel.SceneWeekSchedule getSceneWeekSchedules(int param1Int);
    
    int getSceneWeekSchedulesCount();
    
    List<MobilusModel.SceneWeekSchedule> getSceneWeekSchedulesList();
    
    MobilusModel.SceneWeekScheduleOrBuilder getSceneWeekSchedulesOrBuilder(int param1Int);
    
    List<? extends MobilusModel.SceneWeekScheduleOrBuilder> getSceneWeekSchedulesOrBuilderList();
    
    boolean hasEnabled();
    
    boolean hasIcon();
    
    boolean hasId();
    
    boolean hasName();
  }
  
  public static final class SceneWeekSchedule extends GeneratedMessageV3 implements SceneWeekScheduleOrBuilder {
    private static final SceneWeekSchedule DEFAULT_INSTANCE = new SceneWeekSchedule();
    
    public static final int HOUR_FIELD_NUMBER = 3;
    
    public static final int ID_FIELD_NUMBER = 1;
    
    @Deprecated
    public static final Parser<SceneWeekSchedule> PARSER = (Parser<SceneWeekSchedule>)new AbstractParser<SceneWeekSchedule>() {
        public MobilusModel.SceneWeekSchedule parsePartialFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws InvalidProtocolBufferException {
          return new MobilusModel.SceneWeekSchedule(param2CodedInputStream, param2ExtensionRegistryLite);
        }
      };
    
    public static final int WEEK_DAY_FIELD_NUMBER = 2;
    
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    private int hour_;
    
    private long id_;
    
    private byte memoizedIsInitialized = (byte)-1;
    
    private int weekDay_;
    
    private SceneWeekSchedule() {}
    
    private SceneWeekSchedule(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      this();
      if (param1ExtensionRegistryLite == null)
        throw new NullPointerException(); 
      UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
      for (boolean bool = false; !bool; bool = true) {
        try {
          int i = param1CodedInputStream.readTag();
          if (i != 0) {
            if (i != 8) {
              if (i != 16) {
                if (i != 24) {
                  if (!parseUnknownField(param1CodedInputStream, builder, param1ExtensionRegistryLite, i))
                    continue; 
                  continue;
                } 
                this.bitField0_ |= 0x4;
                this.hour_ = param1CodedInputStream.readInt32();
                continue;
              } 
              this.bitField0_ |= 0x2;
              this.weekDay_ = param1CodedInputStream.readInt32();
              continue;
            } 
            this.bitField0_ |= 0x1;
            this.id_ = param1CodedInputStream.readInt64();
            continue;
          } 
          continue;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } catch (IOException iOException) {
          InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException();
          this(iOException);
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } finally {}
        this.unknownFields = builder.build();
        makeExtensionsImmutable();
        throw param1CodedInputStream;
      } 
      this.unknownFields = builder.build();
      makeExtensionsImmutable();
    }
    
    private SceneWeekSchedule(GeneratedMessageV3.Builder<?> param1Builder) {
      super(param1Builder);
    }
    
    public static SceneWeekSchedule getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_SceneWeekSchedule_descriptor;
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(SceneWeekSchedule param1SceneWeekSchedule) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(param1SceneWeekSchedule);
    }
    
    public static SceneWeekSchedule parseDelimitedFrom(InputStream param1InputStream) throws IOException {
      return (SceneWeekSchedule)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream);
    }
    
    public static SceneWeekSchedule parseDelimitedFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (SceneWeekSchedule)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static SceneWeekSchedule parseFrom(ByteString param1ByteString) throws InvalidProtocolBufferException {
      return (SceneWeekSchedule)PARSER.parseFrom(param1ByteString);
    }
    
    public static SceneWeekSchedule parseFrom(ByteString param1ByteString, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (SceneWeekSchedule)PARSER.parseFrom(param1ByteString, param1ExtensionRegistryLite);
    }
    
    public static SceneWeekSchedule parseFrom(CodedInputStream param1CodedInputStream) throws IOException {
      return (SceneWeekSchedule)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream);
    }
    
    public static SceneWeekSchedule parseFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (SceneWeekSchedule)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream, param1ExtensionRegistryLite);
    }
    
    public static SceneWeekSchedule parseFrom(InputStream param1InputStream) throws IOException {
      return (SceneWeekSchedule)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream);
    }
    
    public static SceneWeekSchedule parseFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (SceneWeekSchedule)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static SceneWeekSchedule parseFrom(ByteBuffer param1ByteBuffer) throws InvalidProtocolBufferException {
      return (SceneWeekSchedule)PARSER.parseFrom(param1ByteBuffer);
    }
    
    public static SceneWeekSchedule parseFrom(ByteBuffer param1ByteBuffer, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (SceneWeekSchedule)PARSER.parseFrom(param1ByteBuffer, param1ExtensionRegistryLite);
    }
    
    public static SceneWeekSchedule parseFrom(byte[] param1ArrayOfbyte) throws InvalidProtocolBufferException {
      return (SceneWeekSchedule)PARSER.parseFrom(param1ArrayOfbyte);
    }
    
    public static SceneWeekSchedule parseFrom(byte[] param1ArrayOfbyte, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (SceneWeekSchedule)PARSER.parseFrom(param1ArrayOfbyte, param1ExtensionRegistryLite);
    }
    
    public static Parser<SceneWeekSchedule> parser() {
      return PARSER;
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object == this)
        return true; 
      if (!(param1Object instanceof SceneWeekSchedule))
        return super.equals(param1Object); 
      param1Object = param1Object;
      return (hasId() != param1Object.hasId()) ? false : ((hasId() && getId() != param1Object.getId()) ? false : ((hasWeekDay() != param1Object.hasWeekDay()) ? false : ((hasWeekDay() && getWeekDay() != param1Object.getWeekDay()) ? false : ((hasHour() != param1Object.hasHour()) ? false : ((hasHour() && getHour() != param1Object.getHour()) ? false : (!!this.unknownFields.equals(((SceneWeekSchedule)param1Object).unknownFields)))))));
    }
    
    public SceneWeekSchedule getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
    
    public int getHour() {
      return this.hour_;
    }
    
    public long getId() {
      return this.id_;
    }
    
    public Parser<SceneWeekSchedule> getParserForType() {
      return PARSER;
    }
    
    public int getSerializedSize() {
      int i = this.memoizedSize;
      if (i != -1)
        return i; 
      int j = 0;
      if ((this.bitField0_ & 0x1) != 0)
        j = 0 + CodedOutputStream.computeInt64Size(1, this.id_); 
      i = j;
      if ((this.bitField0_ & 0x2) != 0)
        i = j + CodedOutputStream.computeInt32Size(2, this.weekDay_); 
      j = i;
      if ((this.bitField0_ & 0x4) != 0)
        j = i + CodedOutputStream.computeInt32Size(3, this.hour_); 
      i = j + this.unknownFields.getSerializedSize();
      this.memoizedSize = i;
      return i;
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    public int getWeekDay() {
      return this.weekDay_;
    }
    
    public boolean hasHour() {
      boolean bool;
      if ((this.bitField0_ & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasId() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean hasWeekDay() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int hashCode() {
      if (this.memoizedHashCode != 0)
        return this.memoizedHashCode; 
      int i = 779 + getDescriptor().hashCode();
      int j = i;
      if (hasId())
        j = (i * 37 + 1) * 53 + Internal.hashLong(getId()); 
      i = j;
      if (hasWeekDay())
        i = (j * 37 + 2) * 53 + getWeekDay(); 
      j = i;
      if (hasHour())
        j = (i * 37 + 3) * 53 + getHour(); 
      j = j * 29 + this.unknownFields.hashCode();
      this.memoizedHashCode = j;
      return j;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_SceneWeekSchedule_fieldAccessorTable.ensureFieldAccessorsInitialized(SceneWeekSchedule.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      byte b = this.memoizedIsInitialized;
      if (b == 1)
        return true; 
      if (b == 0)
        return false; 
      this.memoizedIsInitialized = (byte)1;
      return true;
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      return new Builder(param1BuilderParent);
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter param1UnusedPrivateParameter) {
      return new SceneWeekSchedule();
    }
    
    public Builder toBuilder() {
      Builder builder;
      if (this == DEFAULT_INSTANCE) {
        builder = new Builder();
      } else {
        builder = (new Builder()).mergeFrom(this);
      } 
      return builder;
    }
    
    public void writeTo(CodedOutputStream param1CodedOutputStream) throws IOException {
      if ((this.bitField0_ & 0x1) != 0)
        param1CodedOutputStream.writeInt64(1, this.id_); 
      if ((this.bitField0_ & 0x2) != 0)
        param1CodedOutputStream.writeInt32(2, this.weekDay_); 
      if ((this.bitField0_ & 0x4) != 0)
        param1CodedOutputStream.writeInt32(3, this.hour_); 
      this.unknownFields.writeTo(param1CodedOutputStream);
    }
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MobilusModel.SceneWeekScheduleOrBuilder {
      private int bitField0_;
      
      private int hour_;
      
      private long id_;
      
      private int weekDay_;
      
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent param2BuilderParent) {
        super(param2BuilderParent);
        maybeForceBuilderInitialization();
      }
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MobilusModel.internal_static_SceneWeekSchedule_descriptor;
      }
      
      private void maybeForceBuilderInitialization() {
        MobilusModel.SceneWeekSchedule.alwaysUseFieldBuilders;
      }
      
      public Builder addRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.addRepeatedField(param2FieldDescriptor, param2Object);
      }
      
      public MobilusModel.SceneWeekSchedule build() {
        MobilusModel.SceneWeekSchedule sceneWeekSchedule = buildPartial();
        if (!sceneWeekSchedule.isInitialized())
          throw newUninitializedMessageException(sceneWeekSchedule); 
        return sceneWeekSchedule;
      }
      
      public MobilusModel.SceneWeekSchedule buildPartial() {
        MobilusModel.SceneWeekSchedule sceneWeekSchedule = new MobilusModel.SceneWeekSchedule(this);
        int i = this.bitField0_;
        if ((i & 0x1) != 0) {
          MobilusModel.SceneWeekSchedule.access$10702(sceneWeekSchedule, this.id_);
          j = 1;
        } else {
          j = 0;
        } 
        int k = j;
        if ((i & 0x2) != 0) {
          MobilusModel.SceneWeekSchedule.access$10802(sceneWeekSchedule, this.weekDay_);
          k = j | 0x2;
        } 
        int j = k;
        if ((i & 0x4) != 0) {
          MobilusModel.SceneWeekSchedule.access$10902(sceneWeekSchedule, this.hour_);
          j = k | 0x4;
        } 
        MobilusModel.SceneWeekSchedule.access$11002(sceneWeekSchedule, j);
        onBuilt();
        return sceneWeekSchedule;
      }
      
      public Builder clear() {
        super.clear();
        this.id_ = 0L;
        this.bitField0_ &= 0xFFFFFFFE;
        this.weekDay_ = 0;
        this.bitField0_ &= 0xFFFFFFFD;
        this.hour_ = 0;
        this.bitField0_ &= 0xFFFFFFFB;
        return this;
      }
      
      public Builder clearField(Descriptors.FieldDescriptor param2FieldDescriptor) {
        return (Builder)super.clearField(param2FieldDescriptor);
      }
      
      public Builder clearHour() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.hour_ = 0;
        onChanged();
        return this;
      }
      
      public Builder clearId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.id_ = 0L;
        onChanged();
        return this;
      }
      
      public Builder clearOneof(Descriptors.OneofDescriptor param2OneofDescriptor) {
        return (Builder)super.clearOneof(param2OneofDescriptor);
      }
      
      public Builder clearWeekDay() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.weekDay_ = 0;
        onChanged();
        return this;
      }
      
      public Builder clone() {
        return (Builder)super.clone();
      }
      
      public MobilusModel.SceneWeekSchedule getDefaultInstanceForType() {
        return MobilusModel.SceneWeekSchedule.getDefaultInstance();
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MobilusModel.internal_static_SceneWeekSchedule_descriptor;
      }
      
      public int getHour() {
        return this.hour_;
      }
      
      public long getId() {
        return this.id_;
      }
      
      public int getWeekDay() {
        return this.weekDay_;
      }
      
      public boolean hasHour() {
        boolean bool;
        if ((this.bitField0_ & 0x4) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasId() {
        int i = this.bitField0_;
        boolean bool = true;
        if ((i & 0x1) == 0)
          bool = false; 
        return bool;
      }
      
      public boolean hasWeekDay() {
        boolean bool;
        if ((this.bitField0_ & 0x2) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MobilusModel.internal_static_SceneWeekSchedule_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.SceneWeekSchedule.class, Builder.class);
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws IOException {
        CodedInputStream codedInputStream;
        MobilusModel.SceneWeekSchedule sceneWeekSchedule = null;
        try {
          MobilusModel.SceneWeekSchedule sceneWeekSchedule1 = (MobilusModel.SceneWeekSchedule)MobilusModel.SceneWeekSchedule.PARSER.parsePartialFrom(param2CodedInputStream, param2ExtensionRegistryLite);
          return this;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          MobilusModel.SceneWeekSchedule sceneWeekSchedule1 = (MobilusModel.SceneWeekSchedule)invalidProtocolBufferException.getUnfinishedMessage();
        } finally {
          param2CodedInputStream = null;
        } 
        if (sceneWeekSchedule != null)
          mergeFrom(sceneWeekSchedule); 
        throw codedInputStream;
      }
      
      public Builder mergeFrom(Message param2Message) {
        if (param2Message instanceof MobilusModel.SceneWeekSchedule)
          return mergeFrom((MobilusModel.SceneWeekSchedule)param2Message); 
        super.mergeFrom(param2Message);
        return this;
      }
      
      public Builder mergeFrom(MobilusModel.SceneWeekSchedule param2SceneWeekSchedule) {
        if (param2SceneWeekSchedule == MobilusModel.SceneWeekSchedule.getDefaultInstance())
          return this; 
        if (param2SceneWeekSchedule.hasId())
          setId(param2SceneWeekSchedule.getId()); 
        if (param2SceneWeekSchedule.hasWeekDay())
          setWeekDay(param2SceneWeekSchedule.getWeekDay()); 
        if (param2SceneWeekSchedule.hasHour())
          setHour(param2SceneWeekSchedule.getHour()); 
        mergeUnknownFields(param2SceneWeekSchedule.unknownFields);
        onChanged();
        return this;
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.mergeUnknownFields(param2UnknownFieldSet);
      }
      
      public Builder setField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.setField(param2FieldDescriptor, param2Object);
      }
      
      public Builder setHour(int param2Int) {
        this.bitField0_ |= 0x4;
        this.hour_ = param2Int;
        onChanged();
        return this;
      }
      
      public Builder setId(long param2Long) {
        this.bitField0_ |= 0x1;
        this.id_ = param2Long;
        onChanged();
        return this;
      }
      
      public Builder setRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, int param2Int, Object param2Object) {
        return (Builder)super.setRepeatedField(param2FieldDescriptor, param2Int, param2Object);
      }
      
      public final Builder setUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.setUnknownFields(param2UnknownFieldSet);
      }
      
      public Builder setWeekDay(int param2Int) {
        this.bitField0_ |= 0x2;
        this.weekDay_ = param2Int;
        onChanged();
        return this;
      }
    }
  }
  
  static final class null extends AbstractParser<SceneWeekSchedule> {
    public MobilusModel.SceneWeekSchedule parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return new MobilusModel.SceneWeekSchedule(param1CodedInputStream, param1ExtensionRegistryLite);
    }
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<SceneWeekSchedule.Builder> implements SceneWeekScheduleOrBuilder {
    private int bitField0_;
    
    private int hour_;
    
    private long id_;
    
    private int weekDay_;
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      super(param1BuilderParent);
      maybeForceBuilderInitialization();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_SceneWeekSchedule_descriptor;
    }
    
    private void maybeForceBuilderInitialization() {
      MobilusModel.SceneWeekSchedule.alwaysUseFieldBuilders;
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.addRepeatedField(param1FieldDescriptor, param1Object);
    }
    
    public MobilusModel.SceneWeekSchedule build() {
      MobilusModel.SceneWeekSchedule sceneWeekSchedule = buildPartial();
      if (!sceneWeekSchedule.isInitialized())
        throw newUninitializedMessageException(sceneWeekSchedule); 
      return sceneWeekSchedule;
    }
    
    public MobilusModel.SceneWeekSchedule buildPartial() {
      MobilusModel.SceneWeekSchedule sceneWeekSchedule = new MobilusModel.SceneWeekSchedule(this);
      int i = this.bitField0_;
      if ((i & 0x1) != 0) {
        MobilusModel.SceneWeekSchedule.access$10702(sceneWeekSchedule, this.id_);
        j = 1;
      } else {
        j = 0;
      } 
      int k = j;
      if ((i & 0x2) != 0) {
        MobilusModel.SceneWeekSchedule.access$10802(sceneWeekSchedule, this.weekDay_);
        k = j | 0x2;
      } 
      int j = k;
      if ((i & 0x4) != 0) {
        MobilusModel.SceneWeekSchedule.access$10902(sceneWeekSchedule, this.hour_);
        j = k | 0x4;
      } 
      MobilusModel.SceneWeekSchedule.access$11002(sceneWeekSchedule, j);
      onBuilt();
      return sceneWeekSchedule;
    }
    
    public Builder clear() {
      super.clear();
      this.id_ = 0L;
      this.bitField0_ &= 0xFFFFFFFE;
      this.weekDay_ = 0;
      this.bitField0_ &= 0xFFFFFFFD;
      this.hour_ = 0;
      this.bitField0_ &= 0xFFFFFFFB;
      return this;
    }
    
    public Builder clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return (Builder)super.clearField(param1FieldDescriptor);
    }
    
    public Builder clearHour() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.hour_ = 0;
      onChanged();
      return this;
    }
    
    public Builder clearId() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.id_ = 0L;
      onChanged();
      return this;
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return (Builder)super.clearOneof(param1OneofDescriptor);
    }
    
    public Builder clearWeekDay() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.weekDay_ = 0;
      onChanged();
      return this;
    }
    
    public Builder clone() {
      return (Builder)super.clone();
    }
    
    public MobilusModel.SceneWeekSchedule getDefaultInstanceForType() {
      return MobilusModel.SceneWeekSchedule.getDefaultInstance();
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return MobilusModel.internal_static_SceneWeekSchedule_descriptor;
    }
    
    public int getHour() {
      return this.hour_;
    }
    
    public long getId() {
      return this.id_;
    }
    
    public int getWeekDay() {
      return this.weekDay_;
    }
    
    public boolean hasHour() {
      boolean bool;
      if ((this.bitField0_ & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasId() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean hasWeekDay() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_SceneWeekSchedule_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.SceneWeekSchedule.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      CodedInputStream codedInputStream;
      MobilusModel.SceneWeekSchedule sceneWeekSchedule = null;
      try {
        MobilusModel.SceneWeekSchedule sceneWeekSchedule1 = (MobilusModel.SceneWeekSchedule)MobilusModel.SceneWeekSchedule.PARSER.parsePartialFrom(param1CodedInputStream, param1ExtensionRegistryLite);
        return this;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        MobilusModel.SceneWeekSchedule sceneWeekSchedule1 = (MobilusModel.SceneWeekSchedule)invalidProtocolBufferException.getUnfinishedMessage();
      } finally {
        param1CodedInputStream = null;
      } 
      if (sceneWeekSchedule != null)
        mergeFrom(sceneWeekSchedule); 
      throw codedInputStream;
    }
    
    public Builder mergeFrom(Message param1Message) {
      if (param1Message instanceof MobilusModel.SceneWeekSchedule)
        return mergeFrom((MobilusModel.SceneWeekSchedule)param1Message); 
      super.mergeFrom(param1Message);
      return this;
    }
    
    public Builder mergeFrom(MobilusModel.SceneWeekSchedule param1SceneWeekSchedule) {
      if (param1SceneWeekSchedule == MobilusModel.SceneWeekSchedule.getDefaultInstance())
        return this; 
      if (param1SceneWeekSchedule.hasId())
        setId(param1SceneWeekSchedule.getId()); 
      if (param1SceneWeekSchedule.hasWeekDay())
        setWeekDay(param1SceneWeekSchedule.getWeekDay()); 
      if (param1SceneWeekSchedule.hasHour())
        setHour(param1SceneWeekSchedule.getHour()); 
      mergeUnknownFields(param1SceneWeekSchedule.unknownFields);
      onChanged();
      return this;
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.mergeUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.setField(param1FieldDescriptor, param1Object);
    }
    
    public Builder setHour(int param1Int) {
      this.bitField0_ |= 0x4;
      this.hour_ = param1Int;
      onChanged();
      return this;
    }
    
    public Builder setId(long param1Long) {
      this.bitField0_ |= 0x1;
      this.id_ = param1Long;
      onChanged();
      return this;
    }
    
    public Builder setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object) {
      return (Builder)super.setRepeatedField(param1FieldDescriptor, param1Int, param1Object);
    }
    
    public final Builder setUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.setUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder setWeekDay(int param1Int) {
      this.bitField0_ |= 0x2;
      this.weekDay_ = param1Int;
      onChanged();
      return this;
    }
  }
  
  public static interface SceneWeekScheduleOrBuilder extends MessageOrBuilder {
    int getHour();
    
    long getId();
    
    int getWeekDay();
    
    boolean hasHour();
    
    boolean hasId();
    
    boolean hasWeekDay();
  }
  
  public static final class User extends GeneratedMessageV3 implements UserOrBuilder {
    public static final int ADMIN_FIELD_NUMBER = 4;
    
    public static final int ASSIGNED_DEVICES_IDS_FIELD_NUMBER = 5;
    
    private static final User DEFAULT_INSTANCE = new User();
    
    public static final int ID_FIELD_NUMBER = 1;
    
    public static final int LOGIN_FIELD_NUMBER = 2;
    
    @Deprecated
    public static final Parser<User> PARSER = (Parser<User>)new AbstractParser<User>() {
        public MobilusModel.User parsePartialFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws InvalidProtocolBufferException {
          return new MobilusModel.User(param2CodedInputStream, param2ExtensionRegistryLite);
        }
      };
    
    public static final int PASSWORD_FIELD_NUMBER = 3;
    
    private static final long serialVersionUID = 0L;
    
    private boolean admin_;
    
    private Internal.LongList assignedDevicesIds_;
    
    private int bitField0_;
    
    private long id_;
    
    private volatile Object login_;
    
    private byte memoizedIsInitialized = (byte)-1;
    
    private ByteString password_;
    
    private User() {
      this.login_ = "";
      this.password_ = ByteString.EMPTY;
      this.assignedDevicesIds_ = emptyLongList();
    }
    
    private User(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      this();
      if (param1ExtensionRegistryLite == null)
        throw new NullPointerException(); 
      UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
      boolean bool = false;
      int i = 0;
      while (!bool) {
        int j = i;
        int k = i;
        int m = i;
        try {
          int n = param1CodedInputStream.readTag();
          if (n != 0) {
            if (n != 8) {
              if (n != 18) {
                if (n != 26) {
                  if (n != 32) {
                    if (n != 40) {
                      if (n != 42) {
                        j = i;
                        k = i;
                        m = i;
                        if (!parseUnknownField(param1CodedInputStream, builder, param1ExtensionRegistryLite, n))
                          continue; 
                        continue;
                      } 
                      j = i;
                      k = i;
                      m = i;
                      int i1 = param1CodedInputStream.pushLimit(param1CodedInputStream.readRawVarint32());
                      n = i;
                      if ((i & 0x10) == 0) {
                        n = i;
                        j = i;
                        k = i;
                        m = i;
                        if (param1CodedInputStream.getBytesUntilLimit() > 0) {
                          j = i;
                          k = i;
                          m = i;
                          this.assignedDevicesIds_ = newLongList();
                          n = i | 0x10;
                        } 
                      } 
                      while (true) {
                        j = n;
                        k = n;
                        m = n;
                        if (param1CodedInputStream.getBytesUntilLimit() > 0) {
                          j = n;
                          k = n;
                          m = n;
                          this.assignedDevicesIds_.addLong(param1CodedInputStream.readInt64());
                          continue;
                        } 
                        j = n;
                        k = n;
                        m = n;
                        break;
                      } 
                      param1CodedInputStream.popLimit(i1);
                      i = n;
                      continue;
                    } 
                    n = i;
                    if ((i & 0x10) == 0) {
                      j = i;
                      k = i;
                      m = i;
                      this.assignedDevicesIds_ = newLongList();
                      n = i | 0x10;
                    } 
                    j = n;
                    k = n;
                    m = n;
                    this.assignedDevicesIds_.addLong(param1CodedInputStream.readInt64());
                    i = n;
                    continue;
                  } 
                  j = i;
                  k = i;
                  m = i;
                  this.bitField0_ |= 0x8;
                  j = i;
                  k = i;
                  m = i;
                  this.admin_ = param1CodedInputStream.readBool();
                  continue;
                } 
                j = i;
                k = i;
                m = i;
                this.bitField0_ |= 0x4;
                j = i;
                k = i;
                m = i;
                this.password_ = param1CodedInputStream.readBytes();
                continue;
              } 
              j = i;
              k = i;
              m = i;
              ByteString byteString = param1CodedInputStream.readBytes();
              j = i;
              k = i;
              m = i;
              this.bitField0_ |= 0x2;
              j = i;
              k = i;
              m = i;
              this.login_ = byteString;
              continue;
            } 
            j = i;
            k = i;
            m = i;
            this.bitField0_ |= 0x1;
            j = i;
            k = i;
            m = i;
            this.id_ = param1CodedInputStream.readInt64();
            continue;
          } 
          continue;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          j = m;
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } catch (IOException iOException) {
          j = k;
          InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException();
          j = k;
          this(iOException);
          j = k;
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } finally {}
        if ((j & 0x10) != 0)
          this.assignedDevicesIds_.makeImmutable(); 
        this.unknownFields = builder.build();
        makeExtensionsImmutable();
        throw param1CodedInputStream;
        bool = true;
      } 
      if ((i & 0x10) != 0)
        this.assignedDevicesIds_.makeImmutable(); 
      this.unknownFields = builder.build();
      makeExtensionsImmutable();
    }
    
    private User(GeneratedMessageV3.Builder<?> param1Builder) {
      super(param1Builder);
    }
    
    public static User getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_User_descriptor;
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(User param1User) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(param1User);
    }
    
    public static User parseDelimitedFrom(InputStream param1InputStream) throws IOException {
      return (User)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream);
    }
    
    public static User parseDelimitedFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (User)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static User parseFrom(ByteString param1ByteString) throws InvalidProtocolBufferException {
      return (User)PARSER.parseFrom(param1ByteString);
    }
    
    public static User parseFrom(ByteString param1ByteString, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (User)PARSER.parseFrom(param1ByteString, param1ExtensionRegistryLite);
    }
    
    public static User parseFrom(CodedInputStream param1CodedInputStream) throws IOException {
      return (User)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream);
    }
    
    public static User parseFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (User)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream, param1ExtensionRegistryLite);
    }
    
    public static User parseFrom(InputStream param1InputStream) throws IOException {
      return (User)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream);
    }
    
    public static User parseFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (User)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static User parseFrom(ByteBuffer param1ByteBuffer) throws InvalidProtocolBufferException {
      return (User)PARSER.parseFrom(param1ByteBuffer);
    }
    
    public static User parseFrom(ByteBuffer param1ByteBuffer, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (User)PARSER.parseFrom(param1ByteBuffer, param1ExtensionRegistryLite);
    }
    
    public static User parseFrom(byte[] param1ArrayOfbyte) throws InvalidProtocolBufferException {
      return (User)PARSER.parseFrom(param1ArrayOfbyte);
    }
    
    public static User parseFrom(byte[] param1ArrayOfbyte, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (User)PARSER.parseFrom(param1ArrayOfbyte, param1ExtensionRegistryLite);
    }
    
    public static Parser<User> parser() {
      return PARSER;
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object == this)
        return true; 
      if (!(param1Object instanceof User))
        return super.equals(param1Object); 
      param1Object = param1Object;
      return (hasId() != param1Object.hasId()) ? false : ((hasId() && getId() != param1Object.getId()) ? false : ((hasLogin() != param1Object.hasLogin()) ? false : ((hasLogin() && !getLogin().equals(param1Object.getLogin())) ? false : ((hasPassword() != param1Object.hasPassword()) ? false : ((hasPassword() && !getPassword().equals(param1Object.getPassword())) ? false : ((hasAdmin() != param1Object.hasAdmin()) ? false : ((hasAdmin() && getAdmin() != param1Object.getAdmin()) ? false : (!getAssignedDevicesIdsList().equals(param1Object.getAssignedDevicesIdsList()) ? false : (!!this.unknownFields.equals(((User)param1Object).unknownFields))))))))));
    }
    
    public boolean getAdmin() {
      return this.admin_;
    }
    
    public long getAssignedDevicesIds(int param1Int) {
      return this.assignedDevicesIds_.getLong(param1Int);
    }
    
    public int getAssignedDevicesIdsCount() {
      return this.assignedDevicesIds_.size();
    }
    
    public List<Long> getAssignedDevicesIdsList() {
      return (List<Long>)this.assignedDevicesIds_;
    }
    
    public User getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
    
    public long getId() {
      return this.id_;
    }
    
    public String getLogin() {
      Object object = this.login_;
      if (object instanceof String)
        return (String)object; 
      object = object;
      String str = object.toStringUtf8();
      if (object.isValidUtf8())
        this.login_ = str; 
      return str;
    }
    
    public ByteString getLoginBytes() {
      Object object = this.login_;
      if (object instanceof String) {
        object = ByteString.copyFromUtf8((String)object);
        this.login_ = object;
        return (ByteString)object;
      } 
      return (ByteString)object;
    }
    
    public Parser<User> getParserForType() {
      return PARSER;
    }
    
    public ByteString getPassword() {
      return this.password_;
    }
    
    public int getSerializedSize() {
      int i = this.memoizedSize;
      if (i != -1)
        return i; 
      i = this.bitField0_;
      byte b = 0;
      if ((i & 0x1) != 0) {
        i = CodedOutputStream.computeInt64Size(1, this.id_) + 0;
      } else {
        i = 0;
      } 
      int j = i;
      if ((this.bitField0_ & 0x2) != 0)
        j = i + GeneratedMessageV3.computeStringSize(2, this.login_); 
      i = j;
      if ((this.bitField0_ & 0x4) != 0)
        i = j + CodedOutputStream.computeBytesSize(3, this.password_); 
      j = i;
      if ((this.bitField0_ & 0x8) != 0)
        j = i + CodedOutputStream.computeBoolSize(4, this.admin_); 
      i = 0;
      while (b < this.assignedDevicesIds_.size()) {
        i += CodedOutputStream.computeInt64SizeNoTag(this.assignedDevicesIds_.getLong(b));
        b++;
      } 
      i = j + i + getAssignedDevicesIdsList().size() * 1 + this.unknownFields.getSerializedSize();
      this.memoizedSize = i;
      return i;
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    public boolean hasAdmin() {
      boolean bool;
      if ((this.bitField0_ & 0x8) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasId() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean hasLogin() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasPassword() {
      boolean bool;
      if ((this.bitField0_ & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int hashCode() {
      if (this.memoizedHashCode != 0)
        return this.memoizedHashCode; 
      int i = 779 + getDescriptor().hashCode();
      int j = i;
      if (hasId())
        j = (i * 37 + 1) * 53 + Internal.hashLong(getId()); 
      i = j;
      if (hasLogin())
        i = (j * 37 + 2) * 53 + getLogin().hashCode(); 
      j = i;
      if (hasPassword())
        j = (i * 37 + 3) * 53 + getPassword().hashCode(); 
      i = j;
      if (hasAdmin())
        i = (j * 37 + 4) * 53 + Internal.hashBoolean(getAdmin()); 
      j = i;
      if (getAssignedDevicesIdsCount() > 0)
        j = (i * 37 + 5) * 53 + getAssignedDevicesIdsList().hashCode(); 
      j = j * 29 + this.unknownFields.hashCode();
      this.memoizedHashCode = j;
      return j;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_User_fieldAccessorTable.ensureFieldAccessorsInitialized(User.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      byte b = this.memoizedIsInitialized;
      if (b == 1)
        return true; 
      if (b == 0)
        return false; 
      this.memoizedIsInitialized = (byte)1;
      return true;
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      return new Builder(param1BuilderParent);
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter param1UnusedPrivateParameter) {
      return new User();
    }
    
    public Builder toBuilder() {
      Builder builder;
      if (this == DEFAULT_INSTANCE) {
        builder = new Builder();
      } else {
        builder = (new Builder()).mergeFrom(this);
      } 
      return builder;
    }
    
    public void writeTo(CodedOutputStream param1CodedOutputStream) throws IOException {
      if ((this.bitField0_ & 0x1) != 0)
        param1CodedOutputStream.writeInt64(1, this.id_); 
      if ((this.bitField0_ & 0x2) != 0)
        GeneratedMessageV3.writeString(param1CodedOutputStream, 2, this.login_); 
      if ((this.bitField0_ & 0x4) != 0)
        param1CodedOutputStream.writeBytes(3, this.password_); 
      if ((this.bitField0_ & 0x8) != 0)
        param1CodedOutputStream.writeBool(4, this.admin_); 
      for (byte b = 0; b < this.assignedDevicesIds_.size(); b++)
        param1CodedOutputStream.writeInt64(5, this.assignedDevicesIds_.getLong(b)); 
      this.unknownFields.writeTo(param1CodedOutputStream);
    }
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MobilusModel.UserOrBuilder {
      private boolean admin_;
      
      private Internal.LongList assignedDevicesIds_ = MobilusModel.User.emptyLongList();
      
      private int bitField0_;
      
      private long id_;
      
      private Object login_ = "";
      
      private ByteString password_ = ByteString.EMPTY;
      
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent param2BuilderParent) {
        super(param2BuilderParent);
        maybeForceBuilderInitialization();
      }
      
      private void ensureAssignedDevicesIdsIsMutable() {
        if ((this.bitField0_ & 0x10) == 0) {
          this.assignedDevicesIds_ = MobilusModel.User.mutableCopy(this.assignedDevicesIds_);
          this.bitField0_ |= 0x10;
        } 
      }
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MobilusModel.internal_static_User_descriptor;
      }
      
      private void maybeForceBuilderInitialization() {
        MobilusModel.User.alwaysUseFieldBuilders;
      }
      
      public Builder addAllAssignedDevicesIds(Iterable<? extends Long> param2Iterable) {
        ensureAssignedDevicesIdsIsMutable();
        AbstractMessageLite.Builder.addAll(param2Iterable, (List)this.assignedDevicesIds_);
        onChanged();
        return this;
      }
      
      public Builder addAssignedDevicesIds(long param2Long) {
        ensureAssignedDevicesIdsIsMutable();
        this.assignedDevicesIds_.addLong(param2Long);
        onChanged();
        return this;
      }
      
      public Builder addRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.addRepeatedField(param2FieldDescriptor, param2Object);
      }
      
      public MobilusModel.User build() {
        MobilusModel.User user = buildPartial();
        if (!user.isInitialized())
          throw newUninitializedMessageException(user); 
        return user;
      }
      
      public MobilusModel.User buildPartial() {
        MobilusModel.User user = new MobilusModel.User(this);
        int i = this.bitField0_;
        if ((i & 0x1) != 0) {
          MobilusModel.User.access$702(user, this.id_);
          j = 1;
        } else {
          j = 0;
        } 
        int k = j;
        if ((i & 0x2) != 0)
          k = j | 0x2; 
        MobilusModel.User.access$802(user, this.login_);
        int j = k;
        if ((i & 0x4) != 0)
          j = k | 0x4; 
        MobilusModel.User.access$902(user, this.password_);
        k = j;
        if ((i & 0x8) != 0) {
          MobilusModel.User.access$1002(user, this.admin_);
          k = j | 0x8;
        } 
        if ((this.bitField0_ & 0x10) != 0) {
          this.assignedDevicesIds_.makeImmutable();
          this.bitField0_ &= 0xFFFFFFEF;
        } 
        MobilusModel.User.access$1102(user, this.assignedDevicesIds_);
        MobilusModel.User.access$1202(user, k);
        onBuilt();
        return user;
      }
      
      public Builder clear() {
        super.clear();
        this.id_ = 0L;
        this.bitField0_ &= 0xFFFFFFFE;
        this.login_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        this.password_ = ByteString.EMPTY;
        this.bitField0_ &= 0xFFFFFFFB;
        this.admin_ = false;
        this.bitField0_ &= 0xFFFFFFF7;
        this.assignedDevicesIds_ = MobilusModel.User.emptyLongList();
        this.bitField0_ &= 0xFFFFFFEF;
        return this;
      }
      
      public Builder clearAdmin() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.admin_ = false;
        onChanged();
        return this;
      }
      
      public Builder clearAssignedDevicesIds() {
        this.assignedDevicesIds_ = MobilusModel.User.emptyLongList();
        this.bitField0_ &= 0xFFFFFFEF;
        onChanged();
        return this;
      }
      
      public Builder clearField(Descriptors.FieldDescriptor param2FieldDescriptor) {
        return (Builder)super.clearField(param2FieldDescriptor);
      }
      
      public Builder clearId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.id_ = 0L;
        onChanged();
        return this;
      }
      
      public Builder clearLogin() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.login_ = MobilusModel.User.getDefaultInstance().getLogin();
        onChanged();
        return this;
      }
      
      public Builder clearOneof(Descriptors.OneofDescriptor param2OneofDescriptor) {
        return (Builder)super.clearOneof(param2OneofDescriptor);
      }
      
      public Builder clearPassword() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.password_ = MobilusModel.User.getDefaultInstance().getPassword();
        onChanged();
        return this;
      }
      
      public Builder clone() {
        return (Builder)super.clone();
      }
      
      public boolean getAdmin() {
        return this.admin_;
      }
      
      public long getAssignedDevicesIds(int param2Int) {
        return this.assignedDevicesIds_.getLong(param2Int);
      }
      
      public int getAssignedDevicesIdsCount() {
        return this.assignedDevicesIds_.size();
      }
      
      public List<Long> getAssignedDevicesIdsList() {
        Internal.LongList longList;
        if ((this.bitField0_ & 0x10) != 0) {
          List<?> list = Collections.unmodifiableList((List<?>)this.assignedDevicesIds_);
        } else {
          longList = this.assignedDevicesIds_;
        } 
        return (List<Long>)longList;
      }
      
      public MobilusModel.User getDefaultInstanceForType() {
        return MobilusModel.User.getDefaultInstance();
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MobilusModel.internal_static_User_descriptor;
      }
      
      public long getId() {
        return this.id_;
      }
      
      public String getLogin() {
        Object object = this.login_;
        if (!(object instanceof String)) {
          object = object;
          String str = object.toStringUtf8();
          if (object.isValidUtf8())
            this.login_ = str; 
          return str;
        } 
        return (String)object;
      }
      
      public ByteString getLoginBytes() {
        Object object = this.login_;
        if (object instanceof String) {
          object = ByteString.copyFromUtf8((String)object);
          this.login_ = object;
          return (ByteString)object;
        } 
        return (ByteString)object;
      }
      
      public ByteString getPassword() {
        return this.password_;
      }
      
      public boolean hasAdmin() {
        boolean bool;
        if ((this.bitField0_ & 0x8) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasId() {
        int i = this.bitField0_;
        boolean bool = true;
        if ((i & 0x1) == 0)
          bool = false; 
        return bool;
      }
      
      public boolean hasLogin() {
        boolean bool;
        if ((this.bitField0_ & 0x2) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasPassword() {
        boolean bool;
        if ((this.bitField0_ & 0x4) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MobilusModel.internal_static_User_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.User.class, Builder.class);
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws IOException {
        ExtensionRegistryLite extensionRegistryLite = null;
        try {
          MobilusModel.User user = (MobilusModel.User)MobilusModel.User.PARSER.parsePartialFrom(param2CodedInputStream, param2ExtensionRegistryLite);
          return this;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          MobilusModel.User user = (MobilusModel.User)invalidProtocolBufferException.getUnfinishedMessage();
        } finally {
          param2CodedInputStream = null;
        } 
        if (param2ExtensionRegistryLite != null)
          mergeFrom((MobilusModel.User)param2ExtensionRegistryLite); 
        throw param2CodedInputStream;
      }
      
      public Builder mergeFrom(Message param2Message) {
        if (param2Message instanceof MobilusModel.User)
          return mergeFrom((MobilusModel.User)param2Message); 
        super.mergeFrom(param2Message);
        return this;
      }
      
      public Builder mergeFrom(MobilusModel.User param2User) {
        if (param2User == MobilusModel.User.getDefaultInstance())
          return this; 
        if (param2User.hasId())
          setId(param2User.getId()); 
        if (param2User.hasLogin()) {
          this.bitField0_ |= 0x2;
          this.login_ = param2User.login_;
          onChanged();
        } 
        if (param2User.hasPassword())
          setPassword(param2User.getPassword()); 
        if (param2User.hasAdmin())
          setAdmin(param2User.getAdmin()); 
        if (!param2User.assignedDevicesIds_.isEmpty()) {
          if (this.assignedDevicesIds_.isEmpty()) {
            this.assignedDevicesIds_ = param2User.assignedDevicesIds_;
            this.bitField0_ &= 0xFFFFFFEF;
          } else {
            ensureAssignedDevicesIdsIsMutable();
            this.assignedDevicesIds_.addAll((Collection)param2User.assignedDevicesIds_);
          } 
          onChanged();
        } 
        mergeUnknownFields(param2User.unknownFields);
        onChanged();
        return this;
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.mergeUnknownFields(param2UnknownFieldSet);
      }
      
      public Builder setAdmin(boolean param2Boolean) {
        this.bitField0_ |= 0x8;
        this.admin_ = param2Boolean;
        onChanged();
        return this;
      }
      
      public Builder setAssignedDevicesIds(int param2Int, long param2Long) {
        ensureAssignedDevicesIdsIsMutable();
        this.assignedDevicesIds_.setLong(param2Int, param2Long);
        onChanged();
        return this;
      }
      
      public Builder setField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.setField(param2FieldDescriptor, param2Object);
      }
      
      public Builder setId(long param2Long) {
        this.bitField0_ |= 0x1;
        this.id_ = param2Long;
        onChanged();
        return this;
      }
      
      public Builder setLogin(String param2String) {
        if (param2String == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.login_ = param2String;
        onChanged();
        return this;
      }
      
      public Builder setLoginBytes(ByteString param2ByteString) {
        if (param2ByteString == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.login_ = param2ByteString;
        onChanged();
        return this;
      }
      
      public Builder setPassword(ByteString param2ByteString) {
        if (param2ByteString == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x4;
        this.password_ = param2ByteString;
        onChanged();
        return this;
      }
      
      public Builder setRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, int param2Int, Object param2Object) {
        return (Builder)super.setRepeatedField(param2FieldDescriptor, param2Int, param2Object);
      }
      
      public final Builder setUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.setUnknownFields(param2UnknownFieldSet);
      }
    }
  }
  
  static final class null extends AbstractParser<User> {
    public MobilusModel.User parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return new MobilusModel.User(param1CodedInputStream, param1ExtensionRegistryLite);
    }
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<User.Builder> implements UserOrBuilder {
    private boolean admin_;
    
    private Internal.LongList assignedDevicesIds_ = MobilusModel.User.emptyLongList();
    
    private int bitField0_;
    
    private long id_;
    
    private Object login_ = "";
    
    private ByteString password_ = ByteString.EMPTY;
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      super(param1BuilderParent);
      maybeForceBuilderInitialization();
    }
    
    private void ensureAssignedDevicesIdsIsMutable() {
      if ((this.bitField0_ & 0x10) == 0) {
        this.assignedDevicesIds_ = MobilusModel.User.mutableCopy(this.assignedDevicesIds_);
        this.bitField0_ |= 0x10;
      } 
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_User_descriptor;
    }
    
    private void maybeForceBuilderInitialization() {
      MobilusModel.User.alwaysUseFieldBuilders;
    }
    
    public Builder addAllAssignedDevicesIds(Iterable<? extends Long> param1Iterable) {
      ensureAssignedDevicesIdsIsMutable();
      AbstractMessageLite.Builder.addAll(param1Iterable, (List)this.assignedDevicesIds_);
      onChanged();
      return this;
    }
    
    public Builder addAssignedDevicesIds(long param1Long) {
      ensureAssignedDevicesIdsIsMutable();
      this.assignedDevicesIds_.addLong(param1Long);
      onChanged();
      return this;
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.addRepeatedField(param1FieldDescriptor, param1Object);
    }
    
    public MobilusModel.User build() {
      MobilusModel.User user = buildPartial();
      if (!user.isInitialized())
        throw newUninitializedMessageException(user); 
      return user;
    }
    
    public MobilusModel.User buildPartial() {
      MobilusModel.User user = new MobilusModel.User(this);
      int i = this.bitField0_;
      if ((i & 0x1) != 0) {
        MobilusModel.User.access$702(user, this.id_);
        j = 1;
      } else {
        j = 0;
      } 
      int k = j;
      if ((i & 0x2) != 0)
        k = j | 0x2; 
      MobilusModel.User.access$802(user, this.login_);
      int j = k;
      if ((i & 0x4) != 0)
        j = k | 0x4; 
      MobilusModel.User.access$902(user, this.password_);
      k = j;
      if ((i & 0x8) != 0) {
        MobilusModel.User.access$1002(user, this.admin_);
        k = j | 0x8;
      } 
      if ((this.bitField0_ & 0x10) != 0) {
        this.assignedDevicesIds_.makeImmutable();
        this.bitField0_ &= 0xFFFFFFEF;
      } 
      MobilusModel.User.access$1102(user, this.assignedDevicesIds_);
      MobilusModel.User.access$1202(user, k);
      onBuilt();
      return user;
    }
    
    public Builder clear() {
      super.clear();
      this.id_ = 0L;
      this.bitField0_ &= 0xFFFFFFFE;
      this.login_ = "";
      this.bitField0_ &= 0xFFFFFFFD;
      this.password_ = ByteString.EMPTY;
      this.bitField0_ &= 0xFFFFFFFB;
      this.admin_ = false;
      this.bitField0_ &= 0xFFFFFFF7;
      this.assignedDevicesIds_ = MobilusModel.User.emptyLongList();
      this.bitField0_ &= 0xFFFFFFEF;
      return this;
    }
    
    public Builder clearAdmin() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.admin_ = false;
      onChanged();
      return this;
    }
    
    public Builder clearAssignedDevicesIds() {
      this.assignedDevicesIds_ = MobilusModel.User.emptyLongList();
      this.bitField0_ &= 0xFFFFFFEF;
      onChanged();
      return this;
    }
    
    public Builder clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return (Builder)super.clearField(param1FieldDescriptor);
    }
    
    public Builder clearId() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.id_ = 0L;
      onChanged();
      return this;
    }
    
    public Builder clearLogin() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.login_ = MobilusModel.User.getDefaultInstance().getLogin();
      onChanged();
      return this;
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return (Builder)super.clearOneof(param1OneofDescriptor);
    }
    
    public Builder clearPassword() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.password_ = MobilusModel.User.getDefaultInstance().getPassword();
      onChanged();
      return this;
    }
    
    public Builder clone() {
      return (Builder)super.clone();
    }
    
    public boolean getAdmin() {
      return this.admin_;
    }
    
    public long getAssignedDevicesIds(int param1Int) {
      return this.assignedDevicesIds_.getLong(param1Int);
    }
    
    public int getAssignedDevicesIdsCount() {
      return this.assignedDevicesIds_.size();
    }
    
    public List<Long> getAssignedDevicesIdsList() {
      Internal.LongList longList;
      if ((this.bitField0_ & 0x10) != 0) {
        List<?> list = Collections.unmodifiableList((List<?>)this.assignedDevicesIds_);
      } else {
        longList = this.assignedDevicesIds_;
      } 
      return (List<Long>)longList;
    }
    
    public MobilusModel.User getDefaultInstanceForType() {
      return MobilusModel.User.getDefaultInstance();
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return MobilusModel.internal_static_User_descriptor;
    }
    
    public long getId() {
      return this.id_;
    }
    
    public String getLogin() {
      Object object = this.login_;
      if (!(object instanceof String)) {
        object = object;
        String str = object.toStringUtf8();
        if (object.isValidUtf8())
          this.login_ = str; 
        return str;
      } 
      return (String)object;
    }
    
    public ByteString getLoginBytes() {
      Object object = this.login_;
      if (object instanceof String) {
        object = ByteString.copyFromUtf8((String)object);
        this.login_ = object;
        return (ByteString)object;
      } 
      return (ByteString)object;
    }
    
    public ByteString getPassword() {
      return this.password_;
    }
    
    public boolean hasAdmin() {
      boolean bool;
      if ((this.bitField0_ & 0x8) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasId() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean hasLogin() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasPassword() {
      boolean bool;
      if ((this.bitField0_ & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_User_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.User.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      ExtensionRegistryLite extensionRegistryLite = null;
      try {
        MobilusModel.User user = (MobilusModel.User)MobilusModel.User.PARSER.parsePartialFrom(param1CodedInputStream, param1ExtensionRegistryLite);
        return this;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        MobilusModel.User user = (MobilusModel.User)invalidProtocolBufferException.getUnfinishedMessage();
      } finally {
        param1CodedInputStream = null;
      } 
      if (param1ExtensionRegistryLite != null)
        mergeFrom((MobilusModel.User)param1ExtensionRegistryLite); 
      throw param1CodedInputStream;
    }
    
    public Builder mergeFrom(Message param1Message) {
      if (param1Message instanceof MobilusModel.User)
        return mergeFrom((MobilusModel.User)param1Message); 
      super.mergeFrom(param1Message);
      return this;
    }
    
    public Builder mergeFrom(MobilusModel.User param1User) {
      if (param1User == MobilusModel.User.getDefaultInstance())
        return this; 
      if (param1User.hasId())
        setId(param1User.getId()); 
      if (param1User.hasLogin()) {
        this.bitField0_ |= 0x2;
        this.login_ = param1User.login_;
        onChanged();
      } 
      if (param1User.hasPassword())
        setPassword(param1User.getPassword()); 
      if (param1User.hasAdmin())
        setAdmin(param1User.getAdmin()); 
      if (!param1User.assignedDevicesIds_.isEmpty()) {
        if (this.assignedDevicesIds_.isEmpty()) {
          this.assignedDevicesIds_ = param1User.assignedDevicesIds_;
          this.bitField0_ &= 0xFFFFFFEF;
        } else {
          ensureAssignedDevicesIdsIsMutable();
          this.assignedDevicesIds_.addAll((Collection)param1User.assignedDevicesIds_);
        } 
        onChanged();
      } 
      mergeUnknownFields(param1User.unknownFields);
      onChanged();
      return this;
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.mergeUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder setAdmin(boolean param1Boolean) {
      this.bitField0_ |= 0x8;
      this.admin_ = param1Boolean;
      onChanged();
      return this;
    }
    
    public Builder setAssignedDevicesIds(int param1Int, long param1Long) {
      ensureAssignedDevicesIdsIsMutable();
      this.assignedDevicesIds_.setLong(param1Int, param1Long);
      onChanged();
      return this;
    }
    
    public Builder setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.setField(param1FieldDescriptor, param1Object);
    }
    
    public Builder setId(long param1Long) {
      this.bitField0_ |= 0x1;
      this.id_ = param1Long;
      onChanged();
      return this;
    }
    
    public Builder setLogin(String param1String) {
      if (param1String == null)
        throw new NullPointerException(); 
      this.bitField0_ |= 0x2;
      this.login_ = param1String;
      onChanged();
      return this;
    }
    
    public Builder setLoginBytes(ByteString param1ByteString) {
      if (param1ByteString == null)
        throw new NullPointerException(); 
      this.bitField0_ |= 0x2;
      this.login_ = param1ByteString;
      onChanged();
      return this;
    }
    
    public Builder setPassword(ByteString param1ByteString) {
      if (param1ByteString == null)
        throw new NullPointerException(); 
      this.bitField0_ |= 0x4;
      this.password_ = param1ByteString;
      onChanged();
      return this;
    }
    
    public Builder setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object) {
      return (Builder)super.setRepeatedField(param1FieldDescriptor, param1Int, param1Object);
    }
    
    public final Builder setUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.setUnknownFields(param1UnknownFieldSet);
    }
  }
  
  public static interface UserOrBuilder extends MessageOrBuilder {
    boolean getAdmin();
    
    long getAssignedDevicesIds(int param1Int);
    
    int getAssignedDevicesIdsCount();
    
    List<Long> getAssignedDevicesIdsList();
    
    long getId();
    
    String getLogin();
    
    ByteString getLoginBytes();
    
    ByteString getPassword();
    
    boolean hasAdmin();
    
    boolean hasId();
    
    boolean hasLogin();
    
    boolean hasPassword();
  }
  
  public static final class WifiNetwork extends GeneratedMessageV3 implements WifiNetworkOrBuilder {
    private static final WifiNetwork DEFAULT_INSTANCE = new WifiNetwork();
    
    public static final int ENCRYPTED_FIELD_NUMBER = 2;
    
    @Deprecated
    public static final Parser<WifiNetwork> PARSER = (Parser<WifiNetwork>)new AbstractParser<WifiNetwork>() {
        public MobilusModel.WifiNetwork parsePartialFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws InvalidProtocolBufferException {
          return new MobilusModel.WifiNetwork(param2CodedInputStream, param2ExtensionRegistryLite);
        }
      };
    
    public static final int SSID_FIELD_NUMBER = 1;
    
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    private boolean encrypted_;
    
    private byte memoizedIsInitialized = (byte)-1;
    
    private volatile Object ssid_;
    
    private WifiNetwork() {
      this.ssid_ = "";
    }
    
    private WifiNetwork(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      this();
      if (param1ExtensionRegistryLite == null)
        throw new NullPointerException(); 
      UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
      for (boolean bool = false; !bool; bool = true) {
        try {
          int i = param1CodedInputStream.readTag();
          if (i != 0) {
            if (i != 10) {
              if (i != 16) {
                if (!parseUnknownField(param1CodedInputStream, builder, param1ExtensionRegistryLite, i))
                  continue; 
                continue;
              } 
              this.bitField0_ |= 0x2;
              this.encrypted_ = param1CodedInputStream.readBool();
              continue;
            } 
            ByteString byteString = param1CodedInputStream.readBytes();
            this.bitField0_ = 0x1 | this.bitField0_;
            this.ssid_ = byteString;
            continue;
          } 
          continue;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } catch (IOException iOException) {
          InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException();
          this(iOException);
          throw invalidProtocolBufferException.setUnfinishedMessage(this);
        } finally {}
        this.unknownFields = builder.build();
        makeExtensionsImmutable();
        throw param1CodedInputStream;
      } 
      this.unknownFields = builder.build();
      makeExtensionsImmutable();
    }
    
    private WifiNetwork(GeneratedMessageV3.Builder<?> param1Builder) {
      super(param1Builder);
    }
    
    public static WifiNetwork getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_WifiNetwork_descriptor;
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(WifiNetwork param1WifiNetwork) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(param1WifiNetwork);
    }
    
    public static WifiNetwork parseDelimitedFrom(InputStream param1InputStream) throws IOException {
      return (WifiNetwork)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream);
    }
    
    public static WifiNetwork parseDelimitedFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (WifiNetwork)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static WifiNetwork parseFrom(ByteString param1ByteString) throws InvalidProtocolBufferException {
      return (WifiNetwork)PARSER.parseFrom(param1ByteString);
    }
    
    public static WifiNetwork parseFrom(ByteString param1ByteString, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (WifiNetwork)PARSER.parseFrom(param1ByteString, param1ExtensionRegistryLite);
    }
    
    public static WifiNetwork parseFrom(CodedInputStream param1CodedInputStream) throws IOException {
      return (WifiNetwork)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream);
    }
    
    public static WifiNetwork parseFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (WifiNetwork)GeneratedMessageV3.parseWithIOException(PARSER, param1CodedInputStream, param1ExtensionRegistryLite);
    }
    
    public static WifiNetwork parseFrom(InputStream param1InputStream) throws IOException {
      return (WifiNetwork)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream);
    }
    
    public static WifiNetwork parseFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (WifiNetwork)GeneratedMessageV3.parseWithIOException(PARSER, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static WifiNetwork parseFrom(ByteBuffer param1ByteBuffer) throws InvalidProtocolBufferException {
      return (WifiNetwork)PARSER.parseFrom(param1ByteBuffer);
    }
    
    public static WifiNetwork parseFrom(ByteBuffer param1ByteBuffer, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (WifiNetwork)PARSER.parseFrom(param1ByteBuffer, param1ExtensionRegistryLite);
    }
    
    public static WifiNetwork parseFrom(byte[] param1ArrayOfbyte) throws InvalidProtocolBufferException {
      return (WifiNetwork)PARSER.parseFrom(param1ArrayOfbyte);
    }
    
    public static WifiNetwork parseFrom(byte[] param1ArrayOfbyte, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (WifiNetwork)PARSER.parseFrom(param1ArrayOfbyte, param1ExtensionRegistryLite);
    }
    
    public static Parser<WifiNetwork> parser() {
      return PARSER;
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object == this)
        return true; 
      if (!(param1Object instanceof WifiNetwork))
        return super.equals(param1Object); 
      param1Object = param1Object;
      return (hasSsid() != param1Object.hasSsid()) ? false : ((hasSsid() && !getSsid().equals(param1Object.getSsid())) ? false : ((hasEncrypted() != param1Object.hasEncrypted()) ? false : ((hasEncrypted() && getEncrypted() != param1Object.getEncrypted()) ? false : (!!this.unknownFields.equals(((WifiNetwork)param1Object).unknownFields)))));
    }
    
    public WifiNetwork getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
    
    public boolean getEncrypted() {
      return this.encrypted_;
    }
    
    public Parser<WifiNetwork> getParserForType() {
      return PARSER;
    }
    
    public int getSerializedSize() {
      int i = this.memoizedSize;
      if (i != -1)
        return i; 
      i = 0;
      if ((this.bitField0_ & 0x1) != 0)
        i = 0 + GeneratedMessageV3.computeStringSize(1, this.ssid_); 
      int j = i;
      if ((this.bitField0_ & 0x2) != 0)
        j = i + CodedOutputStream.computeBoolSize(2, this.encrypted_); 
      i = j + this.unknownFields.getSerializedSize();
      this.memoizedSize = i;
      return i;
    }
    
    public String getSsid() {
      Object object = this.ssid_;
      if (object instanceof String)
        return (String)object; 
      ByteString byteString = (ByteString)object;
      object = byteString.toStringUtf8();
      if (byteString.isValidUtf8())
        this.ssid_ = object; 
      return (String)object;
    }
    
    public ByteString getSsidBytes() {
      Object object = this.ssid_;
      if (object instanceof String) {
        object = ByteString.copyFromUtf8((String)object);
        this.ssid_ = object;
        return (ByteString)object;
      } 
      return (ByteString)object;
    }
    
    public final UnknownFieldSet getUnknownFields() {
      return this.unknownFields;
    }
    
    public boolean hasEncrypted() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasSsid() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public int hashCode() {
      if (this.memoizedHashCode != 0)
        return this.memoizedHashCode; 
      int i = 779 + getDescriptor().hashCode();
      int j = i;
      if (hasSsid())
        j = (i * 37 + 1) * 53 + getSsid().hashCode(); 
      i = j;
      if (hasEncrypted())
        i = (j * 37 + 2) * 53 + Internal.hashBoolean(getEncrypted()); 
      j = i * 29 + this.unknownFields.hashCode();
      this.memoizedHashCode = j;
      return j;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_WifiNetwork_fieldAccessorTable.ensureFieldAccessorsInitialized(WifiNetwork.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      byte b = this.memoizedIsInitialized;
      if (b == 1)
        return true; 
      if (b == 0)
        return false; 
      this.memoizedIsInitialized = (byte)1;
      return true;
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      return new Builder(param1BuilderParent);
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter param1UnusedPrivateParameter) {
      return new WifiNetwork();
    }
    
    public Builder toBuilder() {
      Builder builder;
      if (this == DEFAULT_INSTANCE) {
        builder = new Builder();
      } else {
        builder = (new Builder()).mergeFrom(this);
      } 
      return builder;
    }
    
    public void writeTo(CodedOutputStream param1CodedOutputStream) throws IOException {
      if ((this.bitField0_ & 0x1) != 0)
        GeneratedMessageV3.writeString(param1CodedOutputStream, 1, this.ssid_); 
      if ((this.bitField0_ & 0x2) != 0)
        param1CodedOutputStream.writeBool(2, this.encrypted_); 
      this.unknownFields.writeTo(param1CodedOutputStream);
    }
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MobilusModel.WifiNetworkOrBuilder {
      private int bitField0_;
      
      private boolean encrypted_;
      
      private Object ssid_ = "";
      
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent param2BuilderParent) {
        super(param2BuilderParent);
        maybeForceBuilderInitialization();
      }
      
      public static final Descriptors.Descriptor getDescriptor() {
        return MobilusModel.internal_static_WifiNetwork_descriptor;
      }
      
      private void maybeForceBuilderInitialization() {
        MobilusModel.WifiNetwork.alwaysUseFieldBuilders;
      }
      
      public Builder addRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.addRepeatedField(param2FieldDescriptor, param2Object);
      }
      
      public MobilusModel.WifiNetwork build() {
        MobilusModel.WifiNetwork wifiNetwork = buildPartial();
        if (!wifiNetwork.isInitialized())
          throw newUninitializedMessageException(wifiNetwork); 
        return wifiNetwork;
      }
      
      public MobilusModel.WifiNetwork buildPartial() {
        byte b;
        MobilusModel.WifiNetwork wifiNetwork = new MobilusModel.WifiNetwork(this);
        int i = this.bitField0_;
        if ((i & 0x1) != 0) {
          b = 1;
        } else {
          b = 0;
        } 
        MobilusModel.WifiNetwork.access$16702(wifiNetwork, this.ssid_);
        int j = b;
        if ((i & 0x2) != 0) {
          MobilusModel.WifiNetwork.access$16802(wifiNetwork, this.encrypted_);
          j = b | 0x2;
        } 
        MobilusModel.WifiNetwork.access$16902(wifiNetwork, j);
        onBuilt();
        return wifiNetwork;
      }
      
      public Builder clear() {
        super.clear();
        this.ssid_ = "";
        this.bitField0_ &= 0xFFFFFFFE;
        this.encrypted_ = false;
        this.bitField0_ &= 0xFFFFFFFD;
        return this;
      }
      
      public Builder clearEncrypted() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.encrypted_ = false;
        onChanged();
        return this;
      }
      
      public Builder clearField(Descriptors.FieldDescriptor param2FieldDescriptor) {
        return (Builder)super.clearField(param2FieldDescriptor);
      }
      
      public Builder clearOneof(Descriptors.OneofDescriptor param2OneofDescriptor) {
        return (Builder)super.clearOneof(param2OneofDescriptor);
      }
      
      public Builder clearSsid() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.ssid_ = MobilusModel.WifiNetwork.getDefaultInstance().getSsid();
        onChanged();
        return this;
      }
      
      public Builder clone() {
        return (Builder)super.clone();
      }
      
      public MobilusModel.WifiNetwork getDefaultInstanceForType() {
        return MobilusModel.WifiNetwork.getDefaultInstance();
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return MobilusModel.internal_static_WifiNetwork_descriptor;
      }
      
      public boolean getEncrypted() {
        return this.encrypted_;
      }
      
      public String getSsid() {
        Object object = this.ssid_;
        if (!(object instanceof String)) {
          object = object;
          String str = object.toStringUtf8();
          if (object.isValidUtf8())
            this.ssid_ = str; 
          return str;
        } 
        return (String)object;
      }
      
      public ByteString getSsidBytes() {
        Object object = this.ssid_;
        if (object instanceof String) {
          object = ByteString.copyFromUtf8((String)object);
          this.ssid_ = object;
          return (ByteString)object;
        } 
        return (ByteString)object;
      }
      
      public boolean hasEncrypted() {
        boolean bool;
        if ((this.bitField0_ & 0x2) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean hasSsid() {
        int i = this.bitField0_;
        boolean bool = true;
        if ((i & 0x1) == 0)
          bool = false; 
        return bool;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MobilusModel.internal_static_WifiNetwork_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.WifiNetwork.class, Builder.class);
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream param2CodedInputStream, ExtensionRegistryLite param2ExtensionRegistryLite) throws IOException {
        CodedInputStream codedInputStream = null;
        try {
          MobilusModel.WifiNetwork wifiNetwork = (MobilusModel.WifiNetwork)MobilusModel.WifiNetwork.PARSER.parsePartialFrom(param2CodedInputStream, param2ExtensionRegistryLite);
          return this;
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
          MobilusModel.WifiNetwork wifiNetwork = (MobilusModel.WifiNetwork)invalidProtocolBufferException.getUnfinishedMessage();
        } finally {
          param2ExtensionRegistryLite = null;
        } 
        if (param2CodedInputStream != null)
          mergeFrom((MobilusModel.WifiNetwork)param2CodedInputStream); 
        throw param2ExtensionRegistryLite;
      }
      
      public Builder mergeFrom(Message param2Message) {
        if (param2Message instanceof MobilusModel.WifiNetwork)
          return mergeFrom((MobilusModel.WifiNetwork)param2Message); 
        super.mergeFrom(param2Message);
        return this;
      }
      
      public Builder mergeFrom(MobilusModel.WifiNetwork param2WifiNetwork) {
        if (param2WifiNetwork == MobilusModel.WifiNetwork.getDefaultInstance())
          return this; 
        if (param2WifiNetwork.hasSsid()) {
          this.bitField0_ |= 0x1;
          this.ssid_ = param2WifiNetwork.ssid_;
          onChanged();
        } 
        if (param2WifiNetwork.hasEncrypted())
          setEncrypted(param2WifiNetwork.getEncrypted()); 
        mergeUnknownFields(param2WifiNetwork.unknownFields);
        onChanged();
        return this;
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.mergeUnknownFields(param2UnknownFieldSet);
      }
      
      public Builder setEncrypted(boolean param2Boolean) {
        this.bitField0_ |= 0x2;
        this.encrypted_ = param2Boolean;
        onChanged();
        return this;
      }
      
      public Builder setField(Descriptors.FieldDescriptor param2FieldDescriptor, Object param2Object) {
        return (Builder)super.setField(param2FieldDescriptor, param2Object);
      }
      
      public Builder setRepeatedField(Descriptors.FieldDescriptor param2FieldDescriptor, int param2Int, Object param2Object) {
        return (Builder)super.setRepeatedField(param2FieldDescriptor, param2Int, param2Object);
      }
      
      public Builder setSsid(String param2String) {
        if (param2String == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x1;
        this.ssid_ = param2String;
        onChanged();
        return this;
      }
      
      public Builder setSsidBytes(ByteString param2ByteString) {
        if (param2ByteString == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x1;
        this.ssid_ = param2ByteString;
        onChanged();
        return this;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet param2UnknownFieldSet) {
        return (Builder)super.setUnknownFields(param2UnknownFieldSet);
      }
    }
  }
  
  static final class null extends AbstractParser<WifiNetwork> {
    public MobilusModel.WifiNetwork parsePartialFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return new MobilusModel.WifiNetwork(param1CodedInputStream, param1ExtensionRegistryLite);
    }
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<WifiNetwork.Builder> implements WifiNetworkOrBuilder {
    private int bitField0_;
    
    private boolean encrypted_;
    
    private Object ssid_ = "";
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent param1BuilderParent) {
      super(param1BuilderParent);
      maybeForceBuilderInitialization();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return MobilusModel.internal_static_WifiNetwork_descriptor;
    }
    
    private void maybeForceBuilderInitialization() {
      MobilusModel.WifiNetwork.alwaysUseFieldBuilders;
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.addRepeatedField(param1FieldDescriptor, param1Object);
    }
    
    public MobilusModel.WifiNetwork build() {
      MobilusModel.WifiNetwork wifiNetwork = buildPartial();
      if (!wifiNetwork.isInitialized())
        throw newUninitializedMessageException(wifiNetwork); 
      return wifiNetwork;
    }
    
    public MobilusModel.WifiNetwork buildPartial() {
      byte b;
      MobilusModel.WifiNetwork wifiNetwork = new MobilusModel.WifiNetwork(this);
      int i = this.bitField0_;
      if ((i & 0x1) != 0) {
        b = 1;
      } else {
        b = 0;
      } 
      MobilusModel.WifiNetwork.access$16702(wifiNetwork, this.ssid_);
      int j = b;
      if ((i & 0x2) != 0) {
        MobilusModel.WifiNetwork.access$16802(wifiNetwork, this.encrypted_);
        j = b | 0x2;
      } 
      MobilusModel.WifiNetwork.access$16902(wifiNetwork, j);
      onBuilt();
      return wifiNetwork;
    }
    
    public Builder clear() {
      super.clear();
      this.ssid_ = "";
      this.bitField0_ &= 0xFFFFFFFE;
      this.encrypted_ = false;
      this.bitField0_ &= 0xFFFFFFFD;
      return this;
    }
    
    public Builder clearEncrypted() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.encrypted_ = false;
      onChanged();
      return this;
    }
    
    public Builder clearField(Descriptors.FieldDescriptor param1FieldDescriptor) {
      return (Builder)super.clearField(param1FieldDescriptor);
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor param1OneofDescriptor) {
      return (Builder)super.clearOneof(param1OneofDescriptor);
    }
    
    public Builder clearSsid() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.ssid_ = MobilusModel.WifiNetwork.getDefaultInstance().getSsid();
      onChanged();
      return this;
    }
    
    public Builder clone() {
      return (Builder)super.clone();
    }
    
    public MobilusModel.WifiNetwork getDefaultInstanceForType() {
      return MobilusModel.WifiNetwork.getDefaultInstance();
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return MobilusModel.internal_static_WifiNetwork_descriptor;
    }
    
    public boolean getEncrypted() {
      return this.encrypted_;
    }
    
    public String getSsid() {
      Object object = this.ssid_;
      if (!(object instanceof String)) {
        object = object;
        String str = object.toStringUtf8();
        if (object.isValidUtf8())
          this.ssid_ = str; 
        return str;
      } 
      return (String)object;
    }
    
    public ByteString getSsidBytes() {
      Object object = this.ssid_;
      if (object instanceof String) {
        object = ByteString.copyFromUtf8((String)object);
        this.ssid_ = object;
        return (ByteString)object;
      } 
      return (ByteString)object;
    }
    
    public boolean hasEncrypted() {
      boolean bool;
      if ((this.bitField0_ & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean hasSsid() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return MobilusModel.internal_static_WifiNetwork_fieldAccessorTable.ensureFieldAccessorsInitialized(MobilusModel.WifiNetwork.class, Builder.class);
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      CodedInputStream codedInputStream = null;
      try {
        MobilusModel.WifiNetwork wifiNetwork = (MobilusModel.WifiNetwork)MobilusModel.WifiNetwork.PARSER.parsePartialFrom(param1CodedInputStream, param1ExtensionRegistryLite);
        return this;
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        MobilusModel.WifiNetwork wifiNetwork = (MobilusModel.WifiNetwork)invalidProtocolBufferException.getUnfinishedMessage();
      } finally {
        param1ExtensionRegistryLite = null;
      } 
      if (param1CodedInputStream != null)
        mergeFrom((MobilusModel.WifiNetwork)param1CodedInputStream); 
      throw param1ExtensionRegistryLite;
    }
    
    public Builder mergeFrom(Message param1Message) {
      if (param1Message instanceof MobilusModel.WifiNetwork)
        return mergeFrom((MobilusModel.WifiNetwork)param1Message); 
      super.mergeFrom(param1Message);
      return this;
    }
    
    public Builder mergeFrom(MobilusModel.WifiNetwork param1WifiNetwork) {
      if (param1WifiNetwork == MobilusModel.WifiNetwork.getDefaultInstance())
        return this; 
      if (param1WifiNetwork.hasSsid()) {
        this.bitField0_ |= 0x1;
        this.ssid_ = param1WifiNetwork.ssid_;
        onChanged();
      } 
      if (param1WifiNetwork.hasEncrypted())
        setEncrypted(param1WifiNetwork.getEncrypted()); 
      mergeUnknownFields(param1WifiNetwork.unknownFields);
      onChanged();
      return this;
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.mergeUnknownFields(param1UnknownFieldSet);
    }
    
    public Builder setEncrypted(boolean param1Boolean) {
      this.bitField0_ |= 0x2;
      this.encrypted_ = param1Boolean;
      onChanged();
      return this;
    }
    
    public Builder setField(Descriptors.FieldDescriptor param1FieldDescriptor, Object param1Object) {
      return (Builder)super.setField(param1FieldDescriptor, param1Object);
    }
    
    public Builder setRepeatedField(Descriptors.FieldDescriptor param1FieldDescriptor, int param1Int, Object param1Object) {
      return (Builder)super.setRepeatedField(param1FieldDescriptor, param1Int, param1Object);
    }
    
    public Builder setSsid(String param1String) {
      if (param1String == null)
        throw new NullPointerException(); 
      this.bitField0_ |= 0x1;
      this.ssid_ = param1String;
      onChanged();
      return this;
    }
    
    public Builder setSsidBytes(ByteString param1ByteString) {
      if (param1ByteString == null)
        throw new NullPointerException(); 
      this.bitField0_ |= 0x1;
      this.ssid_ = param1ByteString;
      onChanged();
      return this;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet param1UnknownFieldSet) {
      return (Builder)super.setUnknownFields(param1UnknownFieldSet);
    }
  }
  
  public static interface WifiNetworkOrBuilder extends MessageOrBuilder {
    boolean getEncrypted();
    
    String getSsid();
    
    ByteString getSsidBytes();
    
    boolean hasEncrypted();
    
    boolean hasSsid();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\communication\MobilusModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */