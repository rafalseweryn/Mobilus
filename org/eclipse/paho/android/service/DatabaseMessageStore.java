package org.eclipse.paho.android.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Iterator;
import java.util.UUID;
import org.eclipse.paho.client.mqttv3.MqttMessage;

class DatabaseMessageStore implements MessageStore {
  private static final String ARRIVED_MESSAGE_TABLE_NAME = "MqttArrivedMessageTable";
  
  private static final String MTIMESTAMP = "mtimestamp";
  
  private static String TAG = "DatabaseMessageStore";
  
  private SQLiteDatabase db = null;
  
  private MQTTDatabaseHelper mqttDb = null;
  
  private MqttTraceHandler traceHandler = null;
  
  public DatabaseMessageStore(MqttService paramMqttService, Context paramContext) {
    this.traceHandler = paramMqttService;
    this.mqttDb = new MQTTDatabaseHelper(this.traceHandler, paramContext);
    this.traceHandler.traceDebug(TAG, "DatabaseMessageStore<init> complete");
  }
  
  private int getArrivedRowCount(String paramString) {
    SQLiteDatabase sQLiteDatabase = this.db;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("clientHandle='");
    stringBuilder.append(paramString);
    stringBuilder.append("'");
    paramString = stringBuilder.toString();
    Cursor cursor = sQLiteDatabase.query("MqttArrivedMessageTable", new String[] { "COUNT(*)" }, paramString, null, null, null, null);
    boolean bool = cursor.moveToFirst();
    int i = 0;
    if (bool)
      i = cursor.getInt(0); 
    cursor.close();
    return i;
  }
  
  public void clearArrivedMessages(String paramString) {
    int i;
    this.db = this.mqttDb.getWritableDatabase();
    if (paramString == null) {
      this.traceHandler.traceDebug(TAG, "clearArrivedMessages: clearing the table");
      i = this.db.delete("MqttArrivedMessageTable", null, null);
    } else {
      MqttTraceHandler mqttTraceHandler1 = this.traceHandler;
      String str1 = TAG;
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("clearArrivedMessages: clearing the table of ");
      stringBuilder1.append(paramString);
      stringBuilder1.append(" messages");
      mqttTraceHandler1.traceDebug(str1, stringBuilder1.toString());
      SQLiteDatabase sQLiteDatabase = this.db;
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("clientHandle='");
      stringBuilder1.append(paramString);
      stringBuilder1.append("'");
      i = sQLiteDatabase.delete("MqttArrivedMessageTable", stringBuilder1.toString(), null);
    } 
    MqttTraceHandler mqttTraceHandler = this.traceHandler;
    String str = TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("clearArrivedMessages: rows affected = ");
    stringBuilder.append(i);
    mqttTraceHandler.traceDebug(str, stringBuilder.toString());
  }
  
  public void close() {
    if (this.db != null)
      this.db.close(); 
  }
  
  public boolean discardArrived(String paramString1, String paramString2) {
    this.db = this.mqttDb.getWritableDatabase();
    MqttTraceHandler mqttTraceHandler = this.traceHandler;
    String str = TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("discardArrived{");
    stringBuilder.append(paramString1);
    stringBuilder.append("}, {");
    stringBuilder.append(paramString2);
    stringBuilder.append("}");
    mqttTraceHandler.traceDebug(str, stringBuilder.toString());
    try {
      MqttTraceHandler mqttTraceHandler1;
      SQLiteDatabase sQLiteDatabase = this.db;
      stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("messageId='");
      stringBuilder.append(paramString2);
      stringBuilder.append("' AND ");
      stringBuilder.append("clientHandle");
      stringBuilder.append("='");
      stringBuilder.append(paramString1);
      stringBuilder.append("'");
      int i = sQLiteDatabase.delete("MqttArrivedMessageTable", stringBuilder.toString(), null);
      if (i != 1) {
        mqttTraceHandler1 = this.traceHandler;
        String str2 = TAG;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("discardArrived - Error deleting message {");
        stringBuilder2.append(paramString2);
        stringBuilder2.append("} from database: Rows affected = ");
        stringBuilder2.append(i);
        mqttTraceHandler1.traceError(str2, stringBuilder2.toString());
        return false;
      } 
      i = getArrivedRowCount((String)mqttTraceHandler1);
      MqttTraceHandler mqttTraceHandler2 = this.traceHandler;
      String str1 = TAG;
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("discardArrived - Message deleted successfully. - messages in db for this clientHandle ");
      stringBuilder1.append(i);
      mqttTraceHandler2.traceDebug(str1, stringBuilder1.toString());
      return true;
    } catch (SQLException sQLException) {
      this.traceHandler.traceException(TAG, "discardArrived", (Exception)sQLException);
      throw sQLException;
    } 
  }
  
  public Iterator<MessageStore.StoredMessage> getAllArrivedMessages(final String clientHandle) {
    return new Iterator<MessageStore.StoredMessage>() {
        private Cursor c;
        
        private boolean hasNext;
        
        protected void finalize() throws Throwable {
          this.c.close();
          super.finalize();
        }
        
        public boolean hasNext() {
          if (!this.hasNext)
            this.c.close(); 
          return this.hasNext;
        }
        
        public MessageStore.StoredMessage next() {
          String str1 = this.c.getString(this.c.getColumnIndex("messageId"));
          String str2 = this.c.getString(this.c.getColumnIndex("clientHandle"));
          String str3 = this.c.getString(this.c.getColumnIndex("destinationName"));
          byte[] arrayOfByte = this.c.getBlob(this.c.getColumnIndex("payload"));
          int i = this.c.getInt(this.c.getColumnIndex("qos"));
          boolean bool1 = Boolean.parseBoolean(this.c.getString(this.c.getColumnIndex("retained")));
          boolean bool2 = Boolean.parseBoolean(this.c.getString(this.c.getColumnIndex("duplicate")));
          DatabaseMessageStore.MqttMessageHack mqttMessageHack = new DatabaseMessageStore.MqttMessageHack(arrayOfByte);
          mqttMessageHack.setQos(i);
          mqttMessageHack.setRetained(bool1);
          mqttMessageHack.setDuplicate(bool2);
          this.hasNext = this.c.moveToNext();
          return new DatabaseMessageStore.DbStoredData(str1, str2, str3, mqttMessageHack);
        }
        
        public void remove() {
          throw new UnsupportedOperationException();
        }
      };
  }
  
  public String storeArrived(String paramString1, String paramString2, MqttMessage paramMqttMessage) {
    this.db = this.mqttDb.getWritableDatabase();
    MqttTraceHandler mqttTraceHandler = this.traceHandler;
    String str2 = TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("storeArrived{");
    stringBuilder.append(paramString1);
    stringBuilder.append("}, {");
    stringBuilder.append(paramMqttMessage.toString());
    stringBuilder.append("}");
    mqttTraceHandler.traceDebug(str2, stringBuilder.toString());
    byte[] arrayOfByte = paramMqttMessage.getPayload();
    int i = paramMqttMessage.getQos();
    boolean bool1 = paramMqttMessage.isRetained();
    boolean bool2 = paramMqttMessage.isDuplicate();
    ContentValues contentValues = new ContentValues();
    String str1 = UUID.randomUUID().toString();
    contentValues.put("messageId", str1);
    contentValues.put("clientHandle", paramString1);
    contentValues.put("destinationName", paramString2);
    contentValues.put("payload", arrayOfByte);
    contentValues.put("qos", Integer.valueOf(i));
    contentValues.put("retained", Boolean.valueOf(bool1));
    contentValues.put("duplicate", Boolean.valueOf(bool2));
    contentValues.put("mtimestamp", Long.valueOf(System.currentTimeMillis()));
    try {
      this.db.insertOrThrow("MqttArrivedMessageTable", null, contentValues);
      i = getArrivedRowCount(paramString1);
      MqttTraceHandler mqttTraceHandler1 = this.traceHandler;
      paramString2 = TAG;
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("storeArrived: inserted message with id of {");
      stringBuilder1.append(str1);
      stringBuilder1.append("} - Number of messages in database for this clientHandle = ");
      stringBuilder1.append(i);
      mqttTraceHandler1.traceDebug(paramString2, stringBuilder1.toString());
      return str1;
    } catch (SQLException sQLException) {
      this.traceHandler.traceException(TAG, "onUpgrade", (Exception)sQLException);
      throw sQLException;
    } 
  }
  
  private class DbStoredData implements MessageStore.StoredMessage {
    private String clientHandle;
    
    private MqttMessage message;
    
    private String messageId;
    
    private String topic;
    
    DbStoredData(String param1String1, String param1String2, String param1String3, MqttMessage param1MqttMessage) {
      this.messageId = param1String1;
      this.topic = param1String3;
      this.message = param1MqttMessage;
    }
    
    public String getClientHandle() {
      return this.clientHandle;
    }
    
    public MqttMessage getMessage() {
      return this.message;
    }
    
    public String getMessageId() {
      return this.messageId;
    }
    
    public String getTopic() {
      return this.topic;
    }
  }
  
  private static class MQTTDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mqttAndroidService.db";
    
    private static final int DATABASE_VERSION = 1;
    
    private static String TAG = "MQTTDatabaseHelper";
    
    private MqttTraceHandler traceHandler = null;
    
    public MQTTDatabaseHelper(MqttTraceHandler param1MqttTraceHandler, Context param1Context) {
      super(param1Context, "mqttAndroidService.db", null, 1);
      this.traceHandler = param1MqttTraceHandler;
    }
    
    public void onCreate(SQLiteDatabase param1SQLiteDatabase) {
      MqttTraceHandler mqttTraceHandler = this.traceHandler;
      String str = TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("onCreate {");
      stringBuilder.append("CREATE TABLE MqttArrivedMessageTable(messageId TEXT PRIMARY KEY, clientHandle TEXT, destinationName TEXT, payload BLOB, qos INTEGER, retained TEXT, duplicate TEXT, mtimestamp INTEGER);");
      stringBuilder.append("}");
      mqttTraceHandler.traceDebug(str, stringBuilder.toString());
      try {
        param1SQLiteDatabase.execSQL("CREATE TABLE MqttArrivedMessageTable(messageId TEXT PRIMARY KEY, clientHandle TEXT, destinationName TEXT, payload BLOB, qos INTEGER, retained TEXT, duplicate TEXT, mtimestamp INTEGER);");
        this.traceHandler.traceDebug(TAG, "created the table");
        return;
      } catch (SQLException sQLException) {
        this.traceHandler.traceException(TAG, "onCreate", (Exception)sQLException);
        throw sQLException;
      } 
    }
    
    public void onUpgrade(SQLiteDatabase param1SQLiteDatabase, int param1Int1, int param1Int2) {
      this.traceHandler.traceDebug(TAG, "onUpgrade");
      try {
        param1SQLiteDatabase.execSQL("DROP TABLE IF EXISTS MqttArrivedMessageTable");
        onCreate(param1SQLiteDatabase);
        this.traceHandler.traceDebug(TAG, "onUpgrade complete");
        return;
      } catch (SQLException sQLException) {
        this.traceHandler.traceException(TAG, "onUpgrade", (Exception)sQLException);
        throw sQLException;
      } 
    }
  }
  
  private class MqttMessageHack extends MqttMessage {
    public MqttMessageHack(byte[] param1ArrayOfbyte) {
      super(param1ArrayOfbyte);
    }
    
    protected void setDuplicate(boolean param1Boolean) {
      super.setDuplicate(param1Boolean);
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\org\eclipse\paho\android\service\DatabaseMessageStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */