package pl.com.mobilelabs.mobilus.model;

public class Reading extends State {
  private Action action;
  
  private int lean;
  
  private int position;
  
  public Reading(long paramLong, boolean paramBoolean, Action paramAction, int paramInt1, int paramInt2) {
    super(paramLong, paramBoolean);
    this.action = paramAction;
    this.position = paramInt1;
    this.lean = paramInt2;
  }
  
  public Action getAction() {
    return this.action;
  }
  
  public int getLean() {
    return this.lean;
  }
  
  public int getPosition() {
    return this.position;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\Reading.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */