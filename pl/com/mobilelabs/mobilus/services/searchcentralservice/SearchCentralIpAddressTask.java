package pl.com.mobilelabs.mobilus.services.searchcentralservice;

import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

class SearchCentralIpAddressTask extends AsyncTask<String, Integer, String> {
  private static final int IPS_TO_CHECK_FOR_THREAD = 15;
  
  private IProgressInterface progressListener;
  
  private ArrayBlockingQueue<SearchCentralIpAddressTaskWorkerResult> resultsQueue;
  
  private ArrayList<SearchCentralIpAddressTaskWorker> workers;
  
  public SearchCentralIpAddressTask(String paramString, boolean paramBoolean, IProgressInterface paramIProgressInterface) {
    String str;
    this.progressListener = paramIProgressInterface;
    if (paramBoolean) {
      str = "1";
    } else {
      str = "0";
    } 
    execute((Object[])new String[] { paramString, str });
  }
  
  protected String doInBackground(String... paramVarArgs) {
    String str = paramVarArgs[0];
    if (paramVarArgs[1].equalsIgnoreCase("1") && SearchCentralIpAddressTaskWorker.checkIpAddress("192.168.2.50"))
      return "192.168.2.50"; 
    String[] arrayOfString = str.split("\\.");
    if (arrayOfString.length != 4)
      return null; 
    byte b = 0;
    int i = b;
    boolean bool;
    for (bool = true; b < 4 && bool; bool = bool1) {
      boolean bool1;
      try {
        int k = Integer.parseInt(arrayOfString[b]);
        bool1 = bool;
        if (b == 3) {
          i = k;
          bool1 = bool;
        } 
      } catch (NumberFormatException numberFormatException) {
        bool1 = false;
      } 
      b++;
    } 
    if (!bool)
      return null; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(arrayOfString[0]);
    stringBuilder.append(".");
    stringBuilder.append(arrayOfString[1]);
    stringBuilder.append(".");
    stringBuilder.append(arrayOfString[2]);
    stringBuilder.append(".");
    ArrayList<Integer> arrayList2 = new ArrayList();
    ArrayList<Integer> arrayList1 = new ArrayList();
    for (int j = i - 1; j >= 0; j -= 15)
      arrayList2.add(Integer.valueOf(j)); 
    while (++i <= 255) {
      arrayList1.add(Integer.valueOf(i));
      i += 15;
    } 
    this.resultsQueue = new ArrayBlockingQueue<>(18);
    this.workers = new ArrayList<>();
    for (Integer integer : arrayList2)
      this.workers.add(new SearchCentralIpAddressTaskWorker(stringBuilder.toString(), integer.intValue() - 15 + 1, integer.intValue(), SearchCentralIpAddressTaskWorker.CheckDirection.DOWN, this.resultsQueue)); 
    for (Integer integer : arrayList1)
      this.workers.add(new SearchCentralIpAddressTaskWorker(stringBuilder.toString(), integer.intValue() + 15 - 1, integer.intValue(), SearchCentralIpAddressTaskWorker.CheckDirection.UP, this.resultsQueue)); 
    Iterator<SearchCentralIpAddressTaskWorker> iterator = this.workers.iterator();
    while (iterator.hasNext())
      ((SearchCentralIpAddressTaskWorker)iterator.next()).start(); 
    i = 0;
    while (true) {
      if (!isCancelled() && this.workers.size() > 0)
        try {
          SearchCentralIpAddressTaskWorker searchCentralIpAddressTaskWorker;
          Iterator<SearchCentralIpAddressTaskWorker> iterator1;
          String str1;
          SearchCentralIpAddressTaskWorkerResult searchCentralIpAddressTaskWorkerResult = this.resultsQueue.take();
          switch (searchCentralIpAddressTaskWorkerResult.getProgress()) {
            default:
              continue;
            case SEARCH_FINISHED:
              searchCentralIpAddressTaskWorker = searchCentralIpAddressTaskWorkerResult.getWorker();
              this.workers.remove(searchCentralIpAddressTaskWorker);
              continue;
            case SERVER_FOUND:
              str1 = searchCentralIpAddressTaskWorker.getWorker().getResult();
              iterator1 = this.workers.iterator();
              while (iterator1.hasNext())
                ((SearchCentralIpAddressTaskWorker)iterator1.next()).interrupt(); 
              return str1;
            case NO_SERVER_FOUND:
              break;
          } 
          publishProgress((Object[])new Integer[] { Integer.valueOf(++i) });
          continue;
        } catch (InterruptedException interruptedException) {} 
      if (isCancelled()) {
        iterator = this.workers.iterator();
        while (iterator.hasNext())
          ((SearchCentralIpAddressTaskWorker)iterator.next()).interrupt(); 
      } 
      return null;
    } 
  }
  
  protected void onPostExecute(String paramString) {
    super.onPostExecute(paramString);
    if (this.progressListener != null)
      this.progressListener.searchCentralIpAddressFinished(paramString); 
  }
  
  protected void onProgressUpdate(Integer... paramVarArgs) {
    if (this.progressListener != null && paramVarArgs != null)
      this.progressListener.searchCentralIpAddressProgress(paramVarArgs[0].intValue()); 
  }
  
  public static interface IProgressInterface {
    void searchCentralIpAddressFinished(String param1String);
    
    void searchCentralIpAddressProgress(int param1Int);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\services\searchcentralservice\SearchCentralIpAddressTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */