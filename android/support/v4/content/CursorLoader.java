package android.support.v4.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.os.CancellationSignal;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

public class CursorLoader extends AsyncTaskLoader<Cursor> {
  CancellationSignal mCancellationSignal;
  
  Cursor mCursor;
  
  final Loader<Cursor>.ForceLoadContentObserver mObserver = new Loader.ForceLoadContentObserver(this);
  
  String[] mProjection;
  
  String mSelection;
  
  String[] mSelectionArgs;
  
  String mSortOrder;
  
  Uri mUri;
  
  public CursorLoader(@NonNull Context paramContext) {
    super(paramContext);
  }
  
  public CursorLoader(@NonNull Context paramContext, @NonNull Uri paramUri, @Nullable String[] paramArrayOfString1, @Nullable String paramString1, @Nullable String[] paramArrayOfString2, @Nullable String paramString2) {
    super(paramContext);
    this.mUri = paramUri;
    this.mProjection = paramArrayOfString1;
    this.mSelection = paramString1;
    this.mSelectionArgs = paramArrayOfString2;
    this.mSortOrder = paramString2;
  }
  
  public void cancelLoadInBackground() {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial cancelLoadInBackground : ()V
    //   4: aload_0
    //   5: monitorenter
    //   6: aload_0
    //   7: getfield mCancellationSignal : Landroid/support/v4/os/CancellationSignal;
    //   10: ifnull -> 20
    //   13: aload_0
    //   14: getfield mCancellationSignal : Landroid/support/v4/os/CancellationSignal;
    //   17: invokevirtual cancel : ()V
    //   20: aload_0
    //   21: monitorexit
    //   22: return
    //   23: astore_1
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_1
    //   27: athrow
    // Exception table:
    //   from	to	target	type
    //   6	20	23	finally
    //   20	22	23	finally
    //   24	26	23	finally
  }
  
  public void deliverResult(Cursor paramCursor) {
    if (isReset()) {
      if (paramCursor != null)
        paramCursor.close(); 
      return;
    } 
    Cursor cursor = this.mCursor;
    this.mCursor = paramCursor;
    if (isStarted())
      super.deliverResult(paramCursor); 
    if (cursor != null && cursor != paramCursor && !cursor.isClosed())
      cursor.close(); 
  }
  
  @Deprecated
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    super.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mUri=");
    paramPrintWriter.println(this.mUri);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mProjection=");
    paramPrintWriter.println(Arrays.toString((Object[])this.mProjection));
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mSelection=");
    paramPrintWriter.println(this.mSelection);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mSelectionArgs=");
    paramPrintWriter.println(Arrays.toString((Object[])this.mSelectionArgs));
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mSortOrder=");
    paramPrintWriter.println(this.mSortOrder);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mCursor=");
    paramPrintWriter.println(this.mCursor);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mContentChanged=");
    paramPrintWriter.println(this.mContentChanged);
  }
  
  @Nullable
  public String[] getProjection() {
    return this.mProjection;
  }
  
  @Nullable
  public String getSelection() {
    return this.mSelection;
  }
  
  @Nullable
  public String[] getSelectionArgs() {
    return this.mSelectionArgs;
  }
  
  @Nullable
  public String getSortOrder() {
    return this.mSortOrder;
  }
  
  @NonNull
  public Uri getUri() {
    return this.mUri;
  }
  
  public Cursor loadInBackground() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual isLoadInBackgroundCanceled : ()Z
    //   6: ifeq -> 19
    //   9: new android/support/v4/os/OperationCanceledException
    //   12: astore_1
    //   13: aload_1
    //   14: invokespecial <init> : ()V
    //   17: aload_1
    //   18: athrow
    //   19: new android/support/v4/os/CancellationSignal
    //   22: astore_1
    //   23: aload_1
    //   24: invokespecial <init> : ()V
    //   27: aload_0
    //   28: aload_1
    //   29: putfield mCancellationSignal : Landroid/support/v4/os/CancellationSignal;
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_0
    //   35: invokevirtual getContext : ()Landroid/content/Context;
    //   38: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
    //   41: aload_0
    //   42: getfield mUri : Landroid/net/Uri;
    //   45: aload_0
    //   46: getfield mProjection : [Ljava/lang/String;
    //   49: aload_0
    //   50: getfield mSelection : Ljava/lang/String;
    //   53: aload_0
    //   54: getfield mSelectionArgs : [Ljava/lang/String;
    //   57: aload_0
    //   58: getfield mSortOrder : Ljava/lang/String;
    //   61: aload_0
    //   62: getfield mCancellationSignal : Landroid/support/v4/os/CancellationSignal;
    //   65: invokestatic query : (Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Landroid/support/v4/os/CancellationSignal;)Landroid/database/Cursor;
    //   68: astore_2
    //   69: aload_2
    //   70: ifnull -> 102
    //   73: aload_2
    //   74: invokeinterface getCount : ()I
    //   79: pop
    //   80: aload_2
    //   81: aload_0
    //   82: getfield mObserver : Landroid/support/v4/content/Loader$ForceLoadContentObserver;
    //   85: invokeinterface registerContentObserver : (Landroid/database/ContentObserver;)V
    //   90: goto -> 102
    //   93: astore_1
    //   94: aload_2
    //   95: invokeinterface close : ()V
    //   100: aload_1
    //   101: athrow
    //   102: aload_0
    //   103: monitorenter
    //   104: aload_0
    //   105: aconst_null
    //   106: putfield mCancellationSignal : Landroid/support/v4/os/CancellationSignal;
    //   109: aload_0
    //   110: monitorexit
    //   111: aload_2
    //   112: areturn
    //   113: astore_1
    //   114: aload_0
    //   115: monitorexit
    //   116: aload_1
    //   117: athrow
    //   118: astore_1
    //   119: aload_0
    //   120: monitorenter
    //   121: aload_0
    //   122: aconst_null
    //   123: putfield mCancellationSignal : Landroid/support/v4/os/CancellationSignal;
    //   126: aload_0
    //   127: monitorexit
    //   128: aload_1
    //   129: athrow
    //   130: astore_1
    //   131: aload_0
    //   132: monitorexit
    //   133: aload_1
    //   134: athrow
    //   135: astore_1
    //   136: aload_0
    //   137: monitorexit
    //   138: aload_1
    //   139: athrow
    // Exception table:
    //   from	to	target	type
    //   2	19	135	finally
    //   19	34	135	finally
    //   34	69	118	finally
    //   73	90	93	java/lang/RuntimeException
    //   73	90	118	finally
    //   94	102	118	finally
    //   104	111	113	finally
    //   114	116	113	finally
    //   121	128	130	finally
    //   131	133	130	finally
    //   136	138	135	finally
  }
  
  public void onCanceled(Cursor paramCursor) {
    if (paramCursor != null && !paramCursor.isClosed())
      paramCursor.close(); 
  }
  
  protected void onReset() {
    super.onReset();
    onStopLoading();
    if (this.mCursor != null && !this.mCursor.isClosed())
      this.mCursor.close(); 
    this.mCursor = null;
  }
  
  protected void onStartLoading() {
    if (this.mCursor != null)
      deliverResult(this.mCursor); 
    if (takeContentChanged() || this.mCursor == null)
      forceLoad(); 
  }
  
  protected void onStopLoading() {
    cancelLoad();
  }
  
  public void setProjection(@Nullable String[] paramArrayOfString) {
    this.mProjection = paramArrayOfString;
  }
  
  public void setSelection(@Nullable String paramString) {
    this.mSelection = paramString;
  }
  
  public void setSelectionArgs(@Nullable String[] paramArrayOfString) {
    this.mSelectionArgs = paramArrayOfString;
  }
  
  public void setSortOrder(@Nullable String paramString) {
    this.mSortOrder = paramString;
  }
  
  public void setUri(@NonNull Uri paramUri) {
    this.mUri = paramUri;
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\android\support\v4\content\CursorLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */