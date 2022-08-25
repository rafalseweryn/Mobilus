package pl.com.mobilelabs.mobilus.services.searchcentralservice;

import java.util.concurrent.ArrayBlockingQueue;

class SearchCentralIpAddressTaskWorker extends Thread {
  private static final int READ_BUFFER_SIZE = 256;
  
  private CheckDirection checkDirection;
  
  private int currentIpPart;
  
  private String ipAddressConstantPart;
  
  private String result;
  
  private ArrayBlockingQueue<SearchCentralIpAddressTaskWorkerResult> resultsQueue;
  
  private boolean running;
  
  private int stopIpPart;
  
  public SearchCentralIpAddressTaskWorker(String paramString, int paramInt1, int paramInt2, CheckDirection paramCheckDirection, ArrayBlockingQueue<SearchCentralIpAddressTaskWorkerResult> paramArrayBlockingQueue) {
    int i;
    this.ipAddressConstantPart = paramString;
    if (paramInt2 < 0) {
      i = 0;
    } else {
      i = paramInt2;
      if (paramInt2 > 255)
        i = 255; 
    } 
    this.stopIpPart = paramInt1;
    this.currentIpPart = i;
    this.checkDirection = paramCheckDirection;
    this.resultsQueue = paramArrayBlockingQueue;
    this.result = null;
  }
  
  public static boolean checkIpAddress(String paramString) {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aconst_null
    //   3: astore_2
    //   4: aconst_null
    //   5: astore_3
    //   6: aconst_null
    //   7: astore #4
    //   9: aconst_null
    //   10: astore #5
    //   12: new java/net/Socket
    //   15: astore #6
    //   17: aload #6
    //   19: invokespecial <init> : ()V
    //   22: new java/net/InetSocketAddress
    //   25: astore #7
    //   27: aload #7
    //   29: aload_0
    //   30: sipush #8883
    //   33: invokespecial <init> : (Ljava/lang/String;I)V
    //   36: aload #6
    //   38: aload #7
    //   40: sipush #1000
    //   43: invokevirtual connect : (Ljava/net/SocketAddress;I)V
    //   46: aload #6
    //   48: sipush #1000
    //   51: invokevirtual setSoTimeout : (I)V
    //   54: aload #6
    //   56: invokevirtual getOutputStream : ()Ljava/io/OutputStream;
    //   59: astore_0
    //   60: aload #6
    //   62: invokevirtual getInputStream : ()Ljava/io/InputStream;
    //   65: astore #5
    //   67: aload #5
    //   69: ifnull -> 77
    //   72: aload #5
    //   74: invokevirtual close : ()V
    //   77: aload_0
    //   78: ifnull -> 85
    //   81: aload_0
    //   82: invokevirtual close : ()V
    //   85: aload #6
    //   87: ifnull -> 95
    //   90: aload #6
    //   92: invokevirtual close : ()V
    //   95: iconst_1
    //   96: ireturn
    //   97: astore_1
    //   98: aload_0
    //   99: astore #5
    //   101: aload_1
    //   102: astore_0
    //   103: goto -> 134
    //   106: astore #5
    //   108: goto -> 162
    //   111: astore #5
    //   113: goto -> 188
    //   116: astore #5
    //   118: goto -> 214
    //   121: astore #5
    //   123: goto -> 241
    //   126: astore_0
    //   127: goto -> 134
    //   130: astore_0
    //   131: aconst_null
    //   132: astore #6
    //   134: aload #5
    //   136: ifnull -> 144
    //   139: aload #5
    //   141: invokevirtual close : ()V
    //   144: aload #6
    //   146: ifnull -> 154
    //   149: aload #6
    //   151: invokevirtual close : ()V
    //   154: aload_0
    //   155: athrow
    //   156: astore_0
    //   157: aconst_null
    //   158: astore #6
    //   160: aload_1
    //   161: astore_0
    //   162: aload_0
    //   163: ifnull -> 170
    //   166: aload_0
    //   167: invokevirtual close : ()V
    //   170: aload #6
    //   172: ifnull -> 180
    //   175: aload #6
    //   177: invokevirtual close : ()V
    //   180: iconst_0
    //   181: ireturn
    //   182: astore_0
    //   183: aconst_null
    //   184: astore #6
    //   186: aload_2
    //   187: astore_0
    //   188: aload_0
    //   189: ifnull -> 196
    //   192: aload_0
    //   193: invokevirtual close : ()V
    //   196: aload #6
    //   198: ifnull -> 206
    //   201: aload #6
    //   203: invokevirtual close : ()V
    //   206: iconst_0
    //   207: ireturn
    //   208: astore_0
    //   209: aconst_null
    //   210: astore #6
    //   212: aload_3
    //   213: astore_0
    //   214: aload_0
    //   215: ifnull -> 222
    //   218: aload_0
    //   219: invokevirtual close : ()V
    //   222: aload #6
    //   224: ifnull -> 232
    //   227: aload #6
    //   229: invokevirtual close : ()V
    //   232: iconst_0
    //   233: ireturn
    //   234: astore_0
    //   235: aconst_null
    //   236: astore #6
    //   238: aload #4
    //   240: astore_0
    //   241: aload_0
    //   242: ifnull -> 249
    //   245: aload_0
    //   246: invokevirtual close : ()V
    //   249: aload #6
    //   251: ifnull -> 259
    //   254: aload #6
    //   256: invokevirtual close : ()V
    //   259: iconst_0
    //   260: ireturn
    //   261: astore_0
    //   262: aload #4
    //   264: astore_0
    //   265: goto -> 241
    //   268: astore_0
    //   269: aload_3
    //   270: astore_0
    //   271: goto -> 214
    //   274: astore_0
    //   275: aload_2
    //   276: astore_0
    //   277: goto -> 188
    //   280: astore_0
    //   281: aload_1
    //   282: astore_0
    //   283: goto -> 162
    //   286: astore_0
    //   287: goto -> 95
    //   290: astore #6
    //   292: goto -> 154
    //   295: astore_0
    //   296: goto -> 180
    //   299: astore_0
    //   300: goto -> 206
    //   303: astore_0
    //   304: goto -> 232
    //   307: astore_0
    //   308: goto -> 259
    // Exception table:
    //   from	to	target	type
    //   12	22	234	java/net/UnknownHostException
    //   12	22	208	java/net/ConnectException
    //   12	22	182	java/net/SocketTimeoutException
    //   12	22	156	java/io/IOException
    //   12	22	130	finally
    //   22	60	261	java/net/UnknownHostException
    //   22	60	268	java/net/ConnectException
    //   22	60	274	java/net/SocketTimeoutException
    //   22	60	280	java/io/IOException
    //   22	60	126	finally
    //   60	67	121	java/net/UnknownHostException
    //   60	67	116	java/net/ConnectException
    //   60	67	111	java/net/SocketTimeoutException
    //   60	67	106	java/io/IOException
    //   60	67	97	finally
    //   72	77	286	java/io/IOException
    //   81	85	286	java/io/IOException
    //   90	95	286	java/io/IOException
    //   139	144	290	java/io/IOException
    //   149	154	290	java/io/IOException
    //   166	170	295	java/io/IOException
    //   175	180	295	java/io/IOException
    //   192	196	299	java/io/IOException
    //   201	206	299	java/io/IOException
    //   218	222	303	java/io/IOException
    //   227	232	303	java/io/IOException
    //   245	249	307	java/io/IOException
    //   254	259	307	java/io/IOException
  }
  
  public String getResult() {
    return this.result;
  }
  
  public void interrupt() {
    super.interrupt();
    this.running = false;
  }
  
  public void run() {
    byte b = 1;
    this.running = true;
    if (this.checkDirection != CheckDirection.UP)
      b = -1; 
    while (true) {
      if (this.running && ((this.checkDirection == CheckDirection.UP && this.currentIpPart <= this.stopIpPart && this.currentIpPart < 256) || (this.checkDirection == CheckDirection.DOWN && this.currentIpPart >= this.stopIpPart && this.currentIpPart >= 0))) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.ipAddressConstantPart);
        stringBuilder.append(Integer.toString(this.currentIpPart));
        String str = stringBuilder.toString();
        try {
          if (checkIpAddress(str)) {
            ArrayBlockingQueue<SearchCentralIpAddressTaskWorkerResult> arrayBlockingQueue1 = this.resultsQueue;
            SearchCentralIpAddressTaskWorkerResult searchCentralIpAddressTaskWorkerResult1 = new SearchCentralIpAddressTaskWorkerResult();
            this(SearchCentralIpAddressTaskWorkerResult.WorkProgress.SERVER_FOUND, this);
            arrayBlockingQueue1.put(searchCentralIpAddressTaskWorkerResult1);
            this.result = str;
            this.running = false;
            continue;
          } 
          ArrayBlockingQueue<SearchCentralIpAddressTaskWorkerResult> arrayBlockingQueue = this.resultsQueue;
          SearchCentralIpAddressTaskWorkerResult searchCentralIpAddressTaskWorkerResult = new SearchCentralIpAddressTaskWorkerResult();
          this(SearchCentralIpAddressTaskWorkerResult.WorkProgress.NO_SERVER_FOUND, this);
          arrayBlockingQueue.put(searchCentralIpAddressTaskWorkerResult);
          this.currentIpPart += b;
        } catch (InterruptedException interruptedException) {}
        continue;
      } 
      try {
        ArrayBlockingQueue<SearchCentralIpAddressTaskWorkerResult> arrayBlockingQueue = this.resultsQueue;
        SearchCentralIpAddressTaskWorkerResult searchCentralIpAddressTaskWorkerResult = new SearchCentralIpAddressTaskWorkerResult();
        this(SearchCentralIpAddressTaskWorkerResult.WorkProgress.SEARCH_FINISHED, this);
        arrayBlockingQueue.put(searchCentralIpAddressTaskWorkerResult);
      } catch (InterruptedException interruptedException) {}
      this.running = false;
      return;
    } 
  }
  
  public enum CheckDirection {
    DOWN, UP;
    
    static {
      $VALUES = new CheckDirection[] { UP, DOWN };
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\services\searchcentralservice\SearchCentralIpAddressTaskWorker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */