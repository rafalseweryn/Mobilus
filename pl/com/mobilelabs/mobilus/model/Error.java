package pl.com.mobilelabs.mobilus.model;

public class Error extends State {
  private ErrorType errorType;
  
  public Error(long paramLong, boolean paramBoolean, ErrorType paramErrorType) {
    super(paramLong, paramBoolean);
    this.errorType = paramErrorType;
  }
  
  public ErrorType getErrorType() {
    return this.errorType;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\model\Error.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */