package pl.com.mobilelabs.mobilus.model;

import java.io.Serializable;

public class Action implements Serializable {
  public static final Action DOWN;
  
  public static final Action INFO;
  
  public static final Action LEAN;
  
  public static final Action MOVE;
  
  public static final Action OFF;
  
  public static final Action ON;
  
  public static final Action STOP;
  
  public static final Action UP = new Action("UP");
  
  private String text;
  
  private int value;
  
  static {
    STOP = new Action("STOP");
    DOWN = new Action("DOWN");
    MOVE = new Action("MOVE");
    LEAN = new Action("LEAN");
    ON = new Action("ON");
    OFF = new Action("OFF");
    INFO = new Action("INFO");
  }
  
  public Action(int paramInt, boolean paramBoolean) {
    int i;
    if (paramBoolean) {
      this.text = LEAN.text;
    } else {
      this.text = MOVE.text;
    } 
    if (paramInt < 0) {
      i = 0;
    } else {
      i = paramInt;
      if (paramInt > 100)
        i = 100; 
    } 
    this.value = i;
  }
  
  private Action(String paramString) {
    this.text = paramString;
    this.value = -1;
  }
  
  public static Action fromString(String paramString) {
    return (paramString == null || paramString.isEmpty()) ? null : (UP.text.equalsIgnoreCase(paramString) ? UP : (DOWN.text.equalsIgnoreCase(paramString) ? DOWN : (STOP.text.equalsIgnoreCase(paramString) ? STOP : (ON.text.equalsIgnoreCase(paramString) ? ON : (OFF.text.equalsIgnoreCase(paramString) ? OFF : (INFO.text.equalsIgnoreCase(paramString) ? INFO : (paramString.contains("%") ? MOVE : (paramString.contains("$") ? LEAN : null))))))));
  }
  
  public String toString() {
    if (this.value == -1)
      return this.text; 
    String str = Integer.toString(this.value);
    if (this.text == MOVE.text) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(str);
      stringBuilder1.append("%");
      return stringBuilder1.toString();
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str);
    stringBuilder.append("$");
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\Action.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */