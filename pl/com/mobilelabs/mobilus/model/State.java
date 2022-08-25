package pl.com.mobilelabs.mobilus.model;

import android.support.annotation.Nullable;
import java.io.Serializable;
import pl.com.mobilelabs.mobilus.model.communication.MobilusModel;

public abstract class State implements Serializable {
  protected long id;
  
  private boolean working;
  
  protected State(long paramLong, boolean paramBoolean) {
    this.id = paramLong;
    this.working = paramBoolean;
  }
  
  @Nullable
  public static State fromEvent(MobilusModel.Event paramEvent) {
    boolean bool;
    if (paramEvent == null)
      return null; 
    if (!paramEvent.hasEventNumber() || !paramEvent.hasDeviceId() || !paramEvent.hasValue())
      return null; 
    if (paramEvent.getEventNumber() != 7 && paramEvent.getEventNumber() != 8 && paramEvent.getEventNumber() != 9)
      return null; 
    long l = paramEvent.getDeviceId();
    String str = paramEvent.getValue();
    int i = paramEvent.getEventNumber();
    byte b = 0;
    if (i == 9)
      return new Error(l, false, ErrorType.fromString(str)); 
    if (paramEvent.getEventNumber() == 7) {
      bool = true;
    } else {
      bool = false;
    } 
    Action action = Action.fromString(str);
    if (action == null)
      return null; 
    if (action == Action.DOWN) {
      i = 0;
    } else if (action == Action.UP) {
      i = 100;
    } else {
      i = -1;
    } 
    String[] arrayOfString = str.split(":");
    int j = arrayOfString.length;
    int k = -1;
    int m;
    for (m = i; b < j; m = i) {
      String str1 = arrayOfString[b];
      if (str1.lastIndexOf("%") == str1.length() - 1) {
        str1 = str1.replace("%", "");
        try {
          i = Integer.parseInt(str1);
        } catch (NumberFormatException numberFormatException) {
          i = -1;
        } 
      } else {
        i = m;
        if (numberFormatException.lastIndexOf("$") == numberFormatException.length() - 1) {
          String str2 = numberFormatException.replace("$", "");
          try {
            k = Integer.parseInt(str2);
            i = m;
          } catch (NumberFormatException numberFormatException1) {
            k = -1;
            i = m;
          } 
        } 
      } 
      b++;
    } 
    return new Reading(l, bool, action, m, k);
  }
  
  public long getId() {
    return this.id;
  }
  
  public boolean isWorking() {
    return this.working;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\State.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */