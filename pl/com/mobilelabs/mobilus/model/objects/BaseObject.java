package pl.com.mobilelabs.mobilus.model.objects;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public abstract class BaseObject implements Serializable {
  protected int icon;
  
  protected long id;
  
  protected String name;
  
  protected BaseObject(long paramLong, String paramString, int paramInt) {
    this.id = paramLong;
    this.name = paramString;
    this.icon = paramInt;
  }
  
  protected boolean belongsTo(List<? extends BaseObject> paramList, long paramLong) {
    Iterator<? extends BaseObject> iterator = paramList.iterator();
    while (iterator.hasNext()) {
      if (((BaseObject)iterator.next()).getId() == paramLong)
        return true; 
    } 
    return false;
  }
  
  public int getIcon() {
    return this.icon;
  }
  
  public long getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\objects\BaseObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */