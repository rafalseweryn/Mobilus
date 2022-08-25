package pl.com.mobilelabs.mobilus.services.searchcentralservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class SearchCentralService extends Service implements SearchCentralIpAddressTask.IProgressInterface {
  private Binder binder;
  
  private ISearchCentralServiceListener listener;
  
  private SearchCentralIpAddressTask searchTask;
  
  public void cancelSearch() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield searchTask : Lpl/com/mobilelabs/mobilus/services/searchcentralservice/SearchCentralIpAddressTask;
    //   6: ifnull -> 23
    //   9: aload_0
    //   10: getfield searchTask : Lpl/com/mobilelabs/mobilus/services/searchcentralservice/SearchCentralIpAddressTask;
    //   13: iconst_1
    //   14: invokevirtual cancel : (Z)Z
    //   17: pop
    //   18: aload_0
    //   19: aconst_null
    //   20: putfield searchTask : Lpl/com/mobilelabs/mobilus/services/searchcentralservice/SearchCentralIpAddressTask;
    //   23: aload_0
    //   24: monitorexit
    //   25: return
    //   26: astore_1
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: athrow
    // Exception table:
    //   from	to	target	type
    //   2	23	26	finally
    //   23	25	26	finally
    //   27	29	26	finally
  }
  
  @Nullable
  public IBinder onBind(Intent paramIntent) {
    return (IBinder)this.binder;
  }
  
  public void onCreate() {
    super.onCreate();
    this.binder = new SearchCentralServiceBinder();
  }
  
  public void onDestroy() {
    cancelSearch();
    this.listener = null;
    super.onDestroy();
  }
  
  public void searchCentralIpAddressFinished(String paramString) {
    this.listener.searchCentralIpAddressFinished(paramString);
    this.searchTask = null;
  }
  
  public void searchCentralIpAddressProgress(int paramInt) {
    this.listener.searchCentralIpAddressProgress(paramInt);
  }
  
  public boolean startSearch() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield listener : Lpl/com/mobilelabs/mobilus/services/searchcentralservice/ISearchCentralServiceListener;
    //   6: ifnonnull -> 13
    //   9: aload_0
    //   10: monitorexit
    //   11: iconst_0
    //   12: ireturn
    //   13: aload_0
    //   14: getfield searchTask : Lpl/com/mobilelabs/mobilus/services/searchcentralservice/SearchCentralIpAddressTask;
    //   17: ifnull -> 24
    //   20: aload_0
    //   21: monitorexit
    //   22: iconst_0
    //   23: ireturn
    //   24: invokestatic getDeviceIpAddress : ()Ljava/lang/String;
    //   27: astore_1
    //   28: aload_0
    //   29: invokestatic getCurrentWifiNetworkName : (Landroid/content/Context;)Ljava/lang/String;
    //   32: astore_2
    //   33: aload_1
    //   34: ifnull -> 71
    //   37: aload_2
    //   38: ifnonnull -> 44
    //   41: goto -> 71
    //   44: aload_2
    //   45: ldc 'mobilus'
    //   47: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   50: istore_3
    //   51: new pl/com/mobilelabs/mobilus/services/searchcentralservice/SearchCentralIpAddressTask
    //   54: astore_2
    //   55: aload_2
    //   56: aload_1
    //   57: iload_3
    //   58: aload_0
    //   59: invokespecial <init> : (Ljava/lang/String;ZLpl/com/mobilelabs/mobilus/services/searchcentralservice/SearchCentralIpAddressTask$IProgressInterface;)V
    //   62: aload_0
    //   63: aload_2
    //   64: putfield searchTask : Lpl/com/mobilelabs/mobilus/services/searchcentralservice/SearchCentralIpAddressTask;
    //   67: aload_0
    //   68: monitorexit
    //   69: iconst_1
    //   70: ireturn
    //   71: aload_0
    //   72: monitorexit
    //   73: iconst_0
    //   74: ireturn
    //   75: astore_1
    //   76: aload_0
    //   77: monitorexit
    //   78: aload_1
    //   79: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	75	finally
    //   13	22	75	finally
    //   24	33	75	finally
    //   44	69	75	finally
    //   71	73	75	finally
    //   76	78	75	finally
  }
  
  public class SearchCentralServiceBinder extends Binder {
    public void cancelSearch() {
      SearchCentralService.this.cancelSearch();
    }
    
    public void setListener(ISearchCentralServiceListener param1ISearchCentralServiceListener) {
      SearchCentralService.access$002(SearchCentralService.this, param1ISearchCentralServiceListener);
    }
    
    public boolean startSearch() {
      return SearchCentralService.this.startSearch();
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\services\searchcentralservice\SearchCentralService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */