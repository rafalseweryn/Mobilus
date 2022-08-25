package pl.com.mobilelabs.mobilus.services.searchcentralservice;

class SearchCentralIpAddressTaskWorkerResult {
  private WorkProgress progress;
  
  private SearchCentralIpAddressTaskWorker worker;
  
  public SearchCentralIpAddressTaskWorkerResult(WorkProgress paramWorkProgress, SearchCentralIpAddressTaskWorker paramSearchCentralIpAddressTaskWorker) {
    this.progress = paramWorkProgress;
    this.worker = paramSearchCentralIpAddressTaskWorker;
  }
  
  public WorkProgress getProgress() {
    return this.progress;
  }
  
  public SearchCentralIpAddressTaskWorker getWorker() {
    return this.worker;
  }
  
  public enum WorkProgress {
    NO_SERVER_FOUND, SEARCH_FINISHED, SERVER_FOUND;
    
    static {
      $VALUES = new WorkProgress[] { NO_SERVER_FOUND, SERVER_FOUND, SEARCH_FINISHED };
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\services\searchcentralservice\SearchCentralIpAddressTaskWorkerResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */