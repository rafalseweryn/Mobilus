package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.activity.BackButtonHeaderActivity;
import pl.com.mobilelabs.mobilus.view.fragment.WorkaroundMapFragment;

public class SpacetimeChangeSettingsActivity extends BackButtonHeaderActivity implements OnMapReadyCallback {
  public static final String CURRENT_TIME = "SpacetimeChangeSettingsActivity.currentTime";
  
  public static final String CURRENT_TIME_RECEIVE_TIME = "SpacetimeChangeSettingsActivity.currentTimeReceiveTime";
  
  public static final String LATITUDE = "SpacetimeChangeSettingsActivity.latitude";
  
  private static final int LOCATION_PERMISSION_REQUEST_CODE = 1337;
  
  public static final String LONGITUDE = "SpacetimeChangeSettingsActivity.longitude";
  
  public static final String SERIAL_NUMBER = "SpacetimeChangeSettingsActivity.serialNumber";
  
  public static final String SHOW_BUTTON = "SpacetimeChangeSettingsActivity.showButton";
  
  private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        // Byte code:
        //   0: aload_2
        //   1: invokevirtual getAction : ()Ljava/lang/String;
        //   4: ldc 'ManagementService.Intents.newConfigurationReceived'
        //   6: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
        //   9: istore_3
        //   10: iconst_1
        //   11: istore #4
        //   13: iload_3
        //   14: ifeq -> 65
        //   17: aload_2
        //   18: ldc 'ManagementService.Intents.newConfigurationReceivedIsAdmin'
        //   20: iconst_0
        //   21: invokevirtual getBooleanExtra : (Ljava/lang/String;Z)Z
        //   24: ifeq -> 54
        //   27: aload_0
        //   28: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/SpacetimeChangeSettingsActivity;
        //   31: invokestatic access$000 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/SpacetimeChangeSettingsActivity;)Lpl/com/mobilelabs/mobilus/services/managementservice/ManagementService$ManagementServiceBinder;
        //   34: invokevirtual isWorkingInLocalMode : ()Z
        //   37: ifne -> 43
        //   40: goto -> 54
        //   43: aload_0
        //   44: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/SpacetimeChangeSettingsActivity;
        //   47: iconst_1
        //   48: putfield showButton : Z
        //   51: goto -> 305
        //   54: aload_0
        //   55: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/SpacetimeChangeSettingsActivity;
        //   58: iconst_0
        //   59: putfield showButton : Z
        //   62: goto -> 305
        //   65: aload_2
        //   66: invokevirtual getAction : ()Ljava/lang/String;
        //   69: ldc 'ManagementService.Intents.deviceConfigurationReceived'
        //   71: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
        //   74: ifeq -> 305
        //   77: aload_2
        //   78: ldc 'ManagementService.Intents.deviceConfigurationReceivedCurrentTime'
        //   80: invokevirtual hasExtra : (Ljava/lang/String;)Z
        //   83: ifeq -> 121
        //   86: aload_0
        //   87: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/SpacetimeChangeSettingsActivity;
        //   90: aload_2
        //   91: ldc 'ManagementService.Intents.deviceConfigurationReceivedCurrentTime'
        //   93: lconst_0
        //   94: invokevirtual getLongExtra : (Ljava/lang/String;J)J
        //   97: putfield currentTime : J
        //   100: aload_0
        //   101: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/SpacetimeChangeSettingsActivity;
        //   104: new java/util/Date
        //   107: dup
        //   108: invokespecial <init> : ()V
        //   111: invokevirtual getTime : ()J
        //   114: ldc2_w 1000
        //   117: ldiv
        //   118: putfield currentTimeReceivedTime : J
        //   121: aload_2
        //   122: ldc 'ManagementService.Intents.deviceConfigurationReceivedLatitude'
        //   124: invokevirtual hasExtra : (Ljava/lang/String;)Z
        //   127: ifeq -> 144
        //   130: aload_0
        //   131: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/SpacetimeChangeSettingsActivity;
        //   134: aload_2
        //   135: ldc 'ManagementService.Intents.deviceConfigurationReceivedLatitude'
        //   137: dconst_0
        //   138: invokevirtual getDoubleExtra : (Ljava/lang/String;D)D
        //   141: putfield latitude : D
        //   144: aload_2
        //   145: ldc 'ManagementService.Intents.deviceConfigurationReceivedLongitude'
        //   147: invokevirtual hasExtra : (Ljava/lang/String;)Z
        //   150: ifeq -> 167
        //   153: aload_0
        //   154: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/SpacetimeChangeSettingsActivity;
        //   157: aload_2
        //   158: ldc 'ManagementService.Intents.deviceConfigurationReceivedLongitude'
        //   160: dconst_0
        //   161: invokevirtual getDoubleExtra : (Ljava/lang/String;D)D
        //   164: putfield longitude : D
        //   167: aload_0
        //   168: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/SpacetimeChangeSettingsActivity;
        //   171: invokestatic access$100 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/SpacetimeChangeSettingsActivity;)Z
        //   174: ifeq -> 270
        //   177: aload_2
        //   178: ldc 'ManagementService.Intents.deviceConfigurationReceivedOperationStatus'
        //   180: invokevirtual hasExtra : (Ljava/lang/String;)Z
        //   183: ifeq -> 270
        //   186: aload_0
        //   187: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/SpacetimeChangeSettingsActivity;
        //   190: iconst_0
        //   191: invokestatic access$102 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/SpacetimeChangeSettingsActivity;Z)Z
        //   194: pop
        //   195: aload_2
        //   196: ldc 'ManagementService.Intents.deviceConfigurationReceivedOperationStatus'
        //   198: iconst_0
        //   199: invokevirtual getIntExtra : (Ljava/lang/String;I)I
        //   202: tableswitch default -> 228, 0 -> 257, 1 -> 244, 2 -> 231
        //   228: goto -> 270
        //   231: aload_1
        //   232: ldc 2131624139
        //   234: iconst_0
        //   235: invokestatic makeText : (Landroid/content/Context;II)Landroid/widget/Toast;
        //   238: invokevirtual show : ()V
        //   241: goto -> 270
        //   244: aload_1
        //   245: ldc 2131624103
        //   247: iconst_0
        //   248: invokestatic makeText : (Landroid/content/Context;II)Landroid/widget/Toast;
        //   251: invokevirtual show : ()V
        //   254: goto -> 270
        //   257: aload_1
        //   258: ldc 2131624141
        //   260: iconst_0
        //   261: invokestatic makeText : (Landroid/content/Context;II)Landroid/widget/Toast;
        //   264: invokevirtual show : ()V
        //   267: goto -> 273
        //   270: iconst_0
        //   271: istore #4
        //   273: aload_2
        //   274: ldc 'ManagementService.Intents.deviceConfigurationReceivedCurrentTime'
        //   276: invokevirtual hasExtra : (Ljava/lang/String;)Z
        //   279: ifne -> 293
        //   282: aload_0
        //   283: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/SpacetimeChangeSettingsActivity;
        //   286: invokestatic access$200 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/SpacetimeChangeSettingsActivity;)Lpl/com/mobilelabs/mobilus/services/managementservice/ManagementService$ManagementServiceBinder;
        //   289: invokevirtual startReadingDeviceSettings : ()Z
        //   292: pop
        //   293: iload #4
        //   295: ifeq -> 305
        //   298: aload_0
        //   299: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/SpacetimeChangeSettingsActivity;
        //   302: invokevirtual finish : ()V
        //   305: aload_0
        //   306: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/SpacetimeChangeSettingsActivity;
        //   309: invokevirtual presentData : ()V
        //   312: return
      }
    };
  
  private long counter = 0L;
  
  protected long currentTime = 0L;
  
  protected long currentTimeReceivedTime = 0L;
  
  protected long currentTimeReceivedTimeSet = -1L;
  
  protected long currentTimeSet = -1L;
  
  @BindView(2131296278)
  protected TextView dateTextView;
  
  protected GoogleMap googleMap;
  
  final Handler handler = new Handler();
  
  protected double latitude = 0.0D;
  
  protected double latitudeSet = Double.NaN;
  
  @BindView(2131296279)
  protected TextView latitudeTextView;
  
  protected double longitude = 0.0D;
  
  protected double longitudeSet = Double.NaN;
  
  @BindView(2131296280)
  protected TextView longitudeTextView;
  
  protected WorkaroundMapFragment mapFragment;
  
  protected boolean mapReady;
  
  protected Marker marker;
  
  @BindView(2131296282)
  protected ScrollView scrollView;
  
  private boolean sentNewValues;
  
  protected String serialNumber;
  
  protected boolean showButton;
  
  @BindView(2131296283)
  protected TextView timeTextView;
  
  Timer timer;
  
  TimerTask timerTask;
  
  private String doubleToDegrees(double paramDouble) {
    paramDouble = Math.abs(paramDouble);
    int i = Double.valueOf(Math.floor(paramDouble)).intValue();
    paramDouble = (paramDouble - i) * 60.0D;
    int j = Double.valueOf(Math.floor(paramDouble)).intValue();
    return String.format("%3d°%02d'%05.2f\"", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Double.valueOf((paramDouble - j) * 60.0D) });
  }
  
  private void enableMyLocationIfPermitted() {
    if (ContextCompat.checkSelfPermission((Context)this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
      ActivityCompat.requestPermissions((Activity)this, new String[] { "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_FINE_LOCATION" }, 1337);
    } else if (this.googleMap != null) {
      this.googleMap.setMyLocationEnabled(true);
    } 
  }
  
  private void initializeTimerTask() {
    this.timerTask = new TimerTask() {
        public void run() {
          SpacetimeChangeSettingsActivity.this.handler.post(new Runnable() {
                public void run() {
                  SpacetimeChangeSettingsActivity.this.updateTimeTextViews();
                }
              });
        }
      };
  }
  
  private void showDefaultLocation() {
    Toast.makeText((Context)this, 2131624137, 0).show();
    LatLng latLng = new LatLng(52.1910972D, 19.3554056D);
    this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
  }
  
  private void startTimer() {
    this.timer = new Timer();
    initializeTimerTask();
    this.timer.schedule(this.timerTask, 100L, 100L);
  }
  
  private void stoptimertask() {
    if (this.timer != null) {
      this.timer.cancel();
      this.timer = null;
    } 
  }
  
  private void updateTimeTextViews() {
    if (this.currentTime >= 0L && this.currentTimeReceivedTime >= 0L) {
      Calendar calendar = Calendar.getInstance();
      long l1 = this.currentTime;
      long l2 = this.currentTimeReceivedTime;
      calendar.setTimeInMillis(calendar.getTimeInMillis() + (l1 - l2) * 1000L);
      SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd.MM.yyyy");
      SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");
      String str2 = simpleDateFormat1.format(calendar.getTime());
      String str1 = simpleDateFormat2.format(calendar.getTime());
      this.dateTextView.setText(str2);
      this.timeTextView.setText(str1);
    } else {
      this.dateTextView.setText("--.--.----");
      this.timeTextView.setText("--:--:--");
    } 
  }
  
  void moveMarker() {
    moveMarker(true);
  }
  
  void moveMarker(boolean paramBoolean) {
    if (this.marker != null) {
      this.marker.remove();
      this.marker = null;
    } 
    if (!Double.isNaN(this.latitude) && !Double.isNaN(this.longitude)) {
      LatLng latLng = new LatLng(this.latitude, this.longitude);
      MarkerOptions markerOptions = (new MarkerOptions()).position(latLng);
      if (this.serialNumber != null)
        markerOptions.title(this.serialNumber); 
      this.marker = this.googleMap.addMarker(markerOptions);
      if (this.serialNumber != null)
        this.marker.showInfoWindow(); 
      if (paramBoolean) {
        float f1 = this.googleMap.getMaxZoomLevel() - 7.0F;
        float f2 = this.googleMap.getMinZoomLevel();
        float f3 = f2;
        if (f1 > f2)
          f3 = f1; 
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, f3));
      } else {
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
      } 
    } 
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    initializeActivity(2131492959);
    configureView(2131558767, 2131624185, 2131624046);
    ButterKnife.bind((Activity)this);
    this.sentNewValues = false;
    IntentFilter intentFilter = new IntentFilter("ManagementService.Intents.newConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.deviceConfigurationReceived");
    LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.broadcastReceiver, intentFilter);
    if (getIntent().hasExtra("SpacetimeChangeSettingsActivity.currentTime") && getIntent().hasExtra("SpacetimeChangeSettingsActivity.currentTimeReceiveTime")) {
      this.currentTime = getIntent().getLongExtra("SpacetimeChangeSettingsActivity.currentTime", 0L);
      this.currentTimeReceivedTime = getIntent().getLongExtra("SpacetimeChangeSettingsActivity.currentTimeReceiveTime", 0L);
    } else {
      this.currentTime = -1L;
      this.currentTimeReceivedTime = -1L;
    } 
    if (getIntent().hasExtra("SpacetimeChangeSettingsActivity.latitude")) {
      this.latitude = getIntent().getDoubleExtra("SpacetimeChangeSettingsActivity.latitude", 0.0D);
    } else {
      this.latitude = Double.NaN;
    } 
    if (getIntent().hasExtra("SpacetimeChangeSettingsActivity.longitude")) {
      this.longitude = getIntent().getDoubleExtra("SpacetimeChangeSettingsActivity.longitude", 0.0D);
    } else {
      this.longitude = Double.NaN;
    } 
    if (getIntent().hasExtra("SpacetimeChangeSettingsActivity.serialNumber")) {
      this.serialNumber = getIntent().getStringExtra("SpacetimeChangeSettingsActivity.serialNumber");
    } else {
      this.serialNumber = null;
    } 
    if (getIntent().hasExtra("SpacetimeChangeSettingsActivity.showButton")) {
      this.showButton = getIntent().getBooleanExtra("SpacetimeChangeSettingsActivity.showButton", false);
    } else {
      this.showButton = false;
    } 
    presentData();
    this.mapReady = false;
    this.mapFragment = (WorkaroundMapFragment)getSupportFragmentManager().findFragmentById(2131296281);
    this.mapFragment.setListener(new WorkaroundMapFragment.OnTouchListener() {
          public void onTouch() {
            SpacetimeChangeSettingsActivity.this.scrollView.requestDisallowInterceptTouchEvent(true);
          }
        });
    this.mapFragment.getMapAsync(this);
  }
  
  protected void onDestroy() {
    LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(this.broadcastReceiver);
    super.onDestroy();
  }
  
  public void onMapReady(GoogleMap paramGoogleMap) {
    this.mapReady = true;
    this.googleMap = paramGoogleMap;
    enableMyLocationIfPermitted();
    moveMarker();
    paramGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
          public void onMapClick(LatLng param1LatLng) {
            SpacetimeChangeSettingsActivity spacetimeChangeSettingsActivity1 = SpacetimeChangeSettingsActivity.this;
            SpacetimeChangeSettingsActivity spacetimeChangeSettingsActivity2 = SpacetimeChangeSettingsActivity.this;
            double d = param1LatLng.latitude;
            spacetimeChangeSettingsActivity2.latitude = d;
            spacetimeChangeSettingsActivity1.latitudeSet = d;
            spacetimeChangeSettingsActivity2 = SpacetimeChangeSettingsActivity.this;
            spacetimeChangeSettingsActivity1 = SpacetimeChangeSettingsActivity.this;
            d = param1LatLng.longitude;
            spacetimeChangeSettingsActivity1.longitude = d;
            spacetimeChangeSettingsActivity2.longitudeSet = d;
            SpacetimeChangeSettingsActivity.this.presentData(false);
          }
        });
  }
  
  protected void onPause() {
    stoptimertask();
    super.onPause();
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfint) {
    if (paramInt != 1337)
      return; 
    if (paramArrayOfint.length > 0 && paramArrayOfint[0] == 0) {
      enableMyLocationIfPermitted();
    } else {
      showDefaultLocation();
    } 
  }
  
  protected void onResume() {
    super.onResume();
    startTimer();
  }
  
  void presentData() {
    presentData(true);
  }
  
  void presentData(boolean paramBoolean) {
    updateTimeTextViews();
    if (!Double.isNaN(this.latitude)) {
      String str2;
      TextView textView = this.latitudeTextView;
      String str1 = doubleToDegrees(this.latitude);
      if (this.latitude >= 0.0D) {
        str2 = "N";
      } else {
        str2 = "S";
      } 
      textView.setText(String.format("%s %s", new Object[] { str1, str2 }));
    } else {
      this.latitudeTextView.setText("");
    } 
    if (!Double.isNaN(this.longitude)) {
      String str2;
      TextView textView = this.longitudeTextView;
      String str1 = doubleToDegrees(this.longitude);
      if (this.longitude >= 0.0D) {
        str2 = "E";
      } else {
        str2 = "W";
      } 
      textView.setText(String.format("%s %s", new Object[] { str1, str2 }));
    } else {
      this.longitudeTextView.setText("");
    } 
    if (this.mapReady)
      moveMarker(paramBoolean); 
  }
  
  @OnClick({2131296278})
  protected void showDatePickerDialogButtonClicked() {
    Calendar calendar = Calendar.getInstance();
    if (this.currentTime >= 0L && this.currentTimeReceivedTime >= 0L) {
      long l1 = this.currentTime;
      long l2 = this.currentTimeReceivedTime;
      calendar.setTimeInMillis(calendar.getTimeInMillis() + (l1 - l2) * 1000L);
    } 
    int i = calendar.get(1);
    int j = calendar.get(2);
    int k = calendar.get(5);
    (new DatePickerDialog((Context)this, 2131690000, new DatePickerDialog.OnDateSetListener() {
          public void onDateSet(DatePicker param1DatePicker, int param1Int1, int param1Int2, int param1Int3) {
            Calendar calendar = Calendar.getInstance();
            if (SpacetimeChangeSettingsActivity.this.currentTime >= 0L && SpacetimeChangeSettingsActivity.this.currentTimeReceivedTime >= 0L) {
              long l1 = SpacetimeChangeSettingsActivity.this.currentTime;
              long l2 = SpacetimeChangeSettingsActivity.this.currentTimeReceivedTime;
              calendar.setTimeInMillis(calendar.getTimeInMillis() + (l1 - l2) * 1000L);
            } 
            calendar.set(1, param1Int1);
            calendar.set(2, param1Int2);
            calendar.set(5, param1Int3);
            SpacetimeChangeSettingsActivity spacetimeChangeSettingsActivity2 = SpacetimeChangeSettingsActivity.this;
            SpacetimeChangeSettingsActivity spacetimeChangeSettingsActivity1 = SpacetimeChangeSettingsActivity.this;
            long l = calendar.getTimeInMillis() / 1000L;
            spacetimeChangeSettingsActivity1.currentTime = l;
            spacetimeChangeSettingsActivity2.currentTimeSet = l;
            spacetimeChangeSettingsActivity1 = SpacetimeChangeSettingsActivity.this;
            spacetimeChangeSettingsActivity2 = SpacetimeChangeSettingsActivity.this;
            l = (new Date()).getTime() / 1000L;
            spacetimeChangeSettingsActivity2.currentTimeReceivedTime = l;
            spacetimeChangeSettingsActivity1.currentTimeReceivedTimeSet = l;
            SpacetimeChangeSettingsActivity.this.presentData(false);
          }
        }i, j, k)).show();
  }
  
  @OnClick({2131296283})
  protected void showTimePickerDialogButtonClicked() {
    Calendar calendar = Calendar.getInstance();
    if (this.currentTime >= 0L && this.currentTimeReceivedTime >= 0L) {
      long l1 = this.currentTime;
      long l2 = this.currentTimeReceivedTime;
      calendar.setTimeInMillis(calendar.getTimeInMillis() + (l1 - l2) * 1000L);
    } 
    int i = calendar.get(11);
    int j = calendar.get(12);
    calendar.get(13);
    (new TimePickerDialog((Context)this, 2131690000, new TimePickerDialog.OnTimeSetListener() {
          public void onTimeSet(TimePicker param1TimePicker, int param1Int1, int param1Int2) {
            Calendar calendar = Calendar.getInstance();
            if (SpacetimeChangeSettingsActivity.this.currentTime >= 0L && SpacetimeChangeSettingsActivity.this.currentTimeReceivedTime >= 0L) {
              long l1 = SpacetimeChangeSettingsActivity.this.currentTime;
              long l2 = SpacetimeChangeSettingsActivity.this.currentTimeReceivedTime;
              calendar.setTimeInMillis(calendar.getTimeInMillis() + (l1 - l2) * 1000L);
            } 
            calendar.set(11, param1Int1);
            calendar.set(12, param1Int2);
            calendar.set(13, 0);
            SpacetimeChangeSettingsActivity spacetimeChangeSettingsActivity1 = SpacetimeChangeSettingsActivity.this;
            SpacetimeChangeSettingsActivity spacetimeChangeSettingsActivity3 = SpacetimeChangeSettingsActivity.this;
            long l = calendar.getTimeInMillis() / 1000L;
            spacetimeChangeSettingsActivity3.currentTime = l;
            spacetimeChangeSettingsActivity1.currentTimeSet = l;
            SpacetimeChangeSettingsActivity spacetimeChangeSettingsActivity2 = SpacetimeChangeSettingsActivity.this;
            spacetimeChangeSettingsActivity1 = SpacetimeChangeSettingsActivity.this;
            l = (new Date()).getTime() / 1000L;
            spacetimeChangeSettingsActivity1.currentTimeReceivedTime = l;
            spacetimeChangeSettingsActivity2.currentTimeReceivedTimeSet = l;
            SpacetimeChangeSettingsActivity.this.presentData(false);
          }
        }i, j, true)).show();
  }
  
  @OnClick({2131296276})
  protected void spacetimeApplyClicked() {
    long l;
    if (this.currentTimeSet >= 0L && this.currentTimeReceivedTimeSet >= 0L) {
      Calendar calendar = Calendar.getInstance();
      long l1 = this.currentTimeSet;
      l = this.currentTimeReceivedTimeSet;
      calendar.setTimeInMillis(calendar.getTimeInMillis() + (l1 - l) * 1000L);
      l = calendar.getTimeInMillis() / 1000L;
    } else {
      l = -1L;
    } 
    this.sentNewValues = this.managementBinder.startWritingDeviceSettings(this.latitudeSet, this.longitudeSet, l);
  }
  
  @OnClick({2131296277})
  protected void spacetimeCancelClicked() {
    finish();
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\SpacetimeChangeSettingsActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */