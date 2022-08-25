package pl.com.mobilelabs.mobilus.services.managementservice;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ExtendTimeout extends Thread {
  private IListener listener;
  
  private Lock lock;
  
  private boolean shouldExtend;
  
  private int timeoutSeconds;
  
  public ExtendTimeout(int paramInt, IListener paramIListener) {
    this.timeoutSeconds = paramInt;
    this.listener = paramIListener;
    this.shouldExtend = false;
    this.lock = new ReentrantLock();
    start();
  }
  
  public boolean extend() {
    if (this.lock == null)
      return false; 
    synchronized (this.lock) {
      this.shouldExtend = true;
      this.lock.notifyAll();
      return true;
    } 
  }
  
  public void run() {
    try {
      while (true) {
        synchronized (this.lock) {
          this.shouldExtend = false;
          this.lock.wait((this.timeoutSeconds * 1000));
          if (!this.shouldExtend) {
            if (this.listener != null)
              this.listener.timeoutOccurred(); 
            break;
          } 
        } 
      } 
    } catch (InterruptedException interruptedException) {
      if (this.listener != null)
        this.listener.errorOccurred(); 
    } 
    this.lock = null;
  }
  
  public static interface IListener {
    void errorOccurred();
    
    void timeoutOccurred();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\services\managementservice\ExtendTimeout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */