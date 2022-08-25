package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
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

public class SpacetimeSettingsActivity extends BackButtonHeaderActivity implements OnMapReadyCallback {
  public static final String CURRENT_TIME = "SpacetimeSettingsActivity.currentTime";
  
  public static final String CURRENT_TIME_RECEIVE_TIME = "SpacetimeSettingsActivity.currentTimeReceiveTime";
  
  public static final String LATITUDE = "SpacetimeSettingsActivity.latitude";
  
  public static final String LONGITUDE = "SpacetimeSettingsActivity.longitude";
  
  public static final String SERIAL_NUMBER = "SpacetimeSettingsActivity.serialNumber";
  
  public static final String SHOW_BUTTON = "SpacetimeSettingsActivity.showButton";
  
  private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.newConfigurationReceived")) {
          if (!param1Intent.getBooleanExtra("ManagementService.Intents.newConfigurationReceivedIsAdmin", false) || !SpacetimeSettingsActivity.this.managementBinder.isWorkingInLocalMode()) {
            SpacetimeSettingsActivity.this.showButton = false;
          } else {
            SpacetimeSettingsActivity.this.showButton = true;
          } 
        } else if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.deviceConfigurationReceived")) {
          if (param1Intent.hasExtra("ManagementService.Intents.deviceConfigurationReceivedCurrentTime")) {
            SpacetimeSettingsActivity.this.currentTime = param1Intent.getLongExtra("ManagementService.Intents.deviceConfigurationReceivedCurrentTime", 0L);
            SpacetimeSettingsActivity.this.currentTimeReceivedTime = (new Date()).getTime() / 1000L;
          } else {
            SpacetimeSettingsActivity.this.currentTime = -1L;
            SpacetimeSettingsActivity.this.currentTimeReceivedTime = -1L;
          } 
          if (param1Intent.hasExtra("ManagementService.Intents.deviceConfigurationReceivedLatitude")) {
            SpacetimeSettingsActivity.this.latitude = param1Intent.getDoubleExtra("ManagementService.Intents.deviceConfigurationReceivedLatitude", 0.0D);
          } else {
            SpacetimeSettingsActivity.this.latitude = Double.NaN;
          } 
          if (param1Intent.hasExtra("ManagementService.Intents.deviceConfigurationReceivedLongitude")) {
            SpacetimeSettingsActivity.this.longitude = param1Intent.getDoubleExtra("ManagementService.Intents.deviceConfigurationReceivedLongitude", 0.0D);
          } else {
            SpacetimeSettingsActivity.this.longitude = Double.NaN;
          } 
        } 
        SpacetimeSettingsActivity.this.presentData();
      }
    };
  
  private long counter = 0L;
  
  protected long currentTime = 0L;
  
  protected long currentTimeReceivedTime = 0L;
  
  @BindView(2131296285)
  protected TextView dateTextView;
  
  protected GoogleMap googleMap;
  
  final Handler handler = new Handler();
  
  protected double latitude = 0.0D;
  
  @BindView(2131296286)
  protected TextView latitudeTextView;
  
  protected double longitude = 0.0D;
  
  @BindView(2131296287)
  protected TextView longitudeTextView;
  
  protected WorkaroundMapFragment mapFragment;
  
  protected boolean mapReady;
  
  protected Marker marker;
  
  @BindView(2131296289)
  protected ScrollView scrollView;
  
  protected String serialNumber;
  
  protected boolean showButton;
  
  @BindView(2131296290)
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
  
  private void initializeTimerTask() {
    this.timerTask = new TimerTask() {
        public void run() {
          SpacetimeSettingsActivity.this.handler.post(new Runnable() {
                public void run() {
                  SpacetimeSettingsActivity.this.updateTimeTextViews();
                }
              });
        }
      };
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
    long l = this.counter;
    this.counter = 1L + l;
    if (l % 600L == 0L && this.managementBinder != null)
      this.managementBinder.startReadingDeviceSettings(); 
  }
  
  void moveMarker() {
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
      float f1 = this.googleMap.getMaxZoomLevel() - 7.0F;
      float f2 = this.googleMap.getMinZoomLevel();
      float f3 = f2;
      if (f1 > f2)
        f3 = f1; 
      this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, f3));
    } 
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    initializeActivity(2131492960);
    configureView(2131558767, 2131624185, 2131624046);
    ButterKnife.bind((Activity)this);
    IntentFilter intentFilter = new IntentFilter("ManagementService.Intents.newConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.deviceConfigurationReceived");
    LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.broadcastReceiver, intentFilter);
    if (getIntent().hasExtra("SpacetimeSettingsActivity.currentTime") && getIntent().hasExtra("SpacetimeSettingsActivity.currentTimeReceiveTime")) {
      this.currentTime = getIntent().getLongExtra("SpacetimeSettingsActivity.currentTime", 0L);
      this.currentTimeReceivedTime = getIntent().getLongExtra("SpacetimeSettingsActivity.currentTimeReceiveTime", 0L);
    } else {
      this.currentTime = -1L;
      this.currentTimeReceivedTime = -1L;
    } 
    if (getIntent().hasExtra("SpacetimeSettingsActivity.latitude")) {
      this.latitude = getIntent().getDoubleExtra("SpacetimeSettingsActivity.latitude", 0.0D);
    } else {
      this.latitude = Double.NaN;
    } 
    if (getIntent().hasExtra("SpacetimeSettingsActivity.longitude")) {
      this.longitude = getIntent().getDoubleExtra("SpacetimeSettingsActivity.longitude", 0.0D);
    } else {
      this.longitude = Double.NaN;
    } 
    if (getIntent().hasExtra("SpacetimeSettingsActivity.serialNumber")) {
      this.serialNumber = getIntent().getStringExtra("SpacetimeSettingsActivity.serialNumber");
    } else {
      this.serialNumber = null;
    } 
    if (getIntent().hasExtra("SpacetimeSettingsActivity.showButton")) {
      this.showButton = getIntent().getBooleanExtra("SpacetimeSettingsActivity.showButton", false);
    } else {
      this.showButton = false;
    } 
    presentData();
    this.mapReady = false;
    this.mapFragment = (WorkaroundMapFragment)getSupportFragmentManager().findFragmentById(2131296288);
    this.mapFragment.setListener(new WorkaroundMapFragment.OnTouchListener() {
          public void onTouch() {
            SpacetimeSettingsActivity.this.scrollView.requestDisallowInterceptTouchEvent(true);
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
    moveMarker();
  }
  
  protected void onPause() {
    stoptimertask();
    super.onPause();
  }
  
  protected void onResume() {
    super.onResume();
    startTimer();
  }
  
  void presentData() {
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
      moveMarker(); 
    LinearLayout linearLayout = (LinearLayout)findViewById(2131296284);
    if (this.showButton) {
      linearLayout.setVisibility(0);
    } else {
      linearLayout.setVisibility(8);
    } 
  }
  
  @OnClick({2131296284})
  protected void spacetimeChangeClicked() {
    Intent intent = new Intent((Context)this, SpacetimeChangeSettingsActivity.class);
    intent.putExtra("SpacetimeChangeSettingsActivity.currentTime", this.currentTime);
    intent.putExtra("SpacetimeChangeSettingsActivity.currentTimeReceiveTime", this.currentTimeReceivedTime);
    intent.putExtra("SpacetimeChangeSettingsActivity.latitude", this.latitude);
    intent.putExtra("SpacetimeChangeSettingsActivity.longitude", this.longitude);
    if (this.serialNumber != null)
      intent.putExtra("SpacetimeChangeSettingsActivity.serialNumber", this.serialNumber); 
    intent.putExtra("SpacetimeChangeSettingsActivity.showButton", this.showButton);
    startActivity(intent);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\SpacetimeSettingsActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */