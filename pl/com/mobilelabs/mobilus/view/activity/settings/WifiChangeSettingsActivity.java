package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import pl.com.mobilelabs.mobilus.model.WifiState;
import pl.com.mobilelabs.mobilus.model.communication.MobilusModel;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.activity.BackButtonHeaderActivity;

public class WifiChangeSettingsActivity extends BackButtonHeaderActivity {
  public static final String BOLD_IP = "WifiChangeSettingsActivity.BOLD_IP";
  
  public static final String CURRENT_IP_ADDRESS = "WifiChangeSettingsActivity.currentIpAddress";
  
  public static final String EXIT_OR_LOGOUT = "WifiChangeSettingsActivity.exitOrLogoutAction";
  
  public static final String IP_ADDRESS = "WifiChangeSettingsActivity.ipAddress";
  
  public static final String MAC_ADDRESS = "WifiChangeSettingsActivity.macAddress";
  
  public static final String MODE = "WifiChangeSettingsActivity.mode";
  
  public static final String NET_NAME = "WifiChangeSettingsActivity.netName";
  
  public static final String NET_PASSWORD = "WifiChangeSettingsActivity.netPassword";
  
  public static final int REQUEST_CODE = 8008;
  
  public static final String SHOW_BUTTON = "WifiChangeSettingsActivity.showButton";
  
  private static final String TAG = "WifiChangeSettingsA";
  
  private boolean boldIp;
  
  private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        // Byte code:
        //   0: aload_2
        //   1: invokevirtual getAction : ()Ljava/lang/String;
        //   4: ldc 'ManagementService.Intents.newConfigurationReceived'
        //   6: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
        //   9: istore_3
        //   10: iconst_0
        //   11: istore #4
        //   13: iconst_0
        //   14: istore #5
        //   16: iload_3
        //   17: ifeq -> 53
        //   20: aload_2
        //   21: ldc 'ManagementService.Intents.newConfigurationReceivedIsAdmin'
        //   23: iconst_0
        //   24: invokevirtual getBooleanExtra : (Ljava/lang/String;Z)Z
        //   27: ifeq -> 43
        //   30: aload_0
        //   31: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   34: invokestatic access$000 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;)Lpl/com/mobilelabs/mobilus/services/managementservice/ManagementService$ManagementServiceBinder;
        //   37: invokevirtual isWorkingInLocalMode : ()Z
        //   40: ifne -> 634
        //   43: aload_0
        //   44: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   47: invokevirtual finish : ()V
        //   50: goto -> 634
        //   53: aload_2
        //   54: invokevirtual getAction : ()Ljava/lang/String;
        //   57: ldc 'ManagementService.Intents.deviceConfigurationReceived'
        //   59: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
        //   62: ifeq -> 140
        //   65: aload_2
        //   66: ldc 'ManagementService.Intents.deviceConfigurationReceivedEthernetIp'
        //   68: invokevirtual hasExtra : (Ljava/lang/String;)Z
        //   71: ifeq -> 86
        //   74: aload_0
        //   75: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   78: iconst_1
        //   79: invokestatic access$102 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;Z)Z
        //   82: pop
        //   83: goto -> 95
        //   86: aload_0
        //   87: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   90: iconst_0
        //   91: invokestatic access$102 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;Z)Z
        //   94: pop
        //   95: aload_0
        //   96: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   99: aload_2
        //   100: ldc 'ManagementService.Intents.deviceConfigurationReceivedWifiIp'
        //   102: invokevirtual getStringExtra : (Ljava/lang/String;)Ljava/lang/String;
        //   105: invokestatic access$202 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;Ljava/lang/String;)Ljava/lang/String;
        //   108: pop
        //   109: aload_0
        //   110: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   113: aload_2
        //   114: ldc 'ManagementService.Intents.deviceConfigurationReceivedWifiMac'
        //   116: invokevirtual getStringExtra : (Ljava/lang/String;)Ljava/lang/String;
        //   119: invokestatic access$302 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;Ljava/lang/String;)Ljava/lang/String;
        //   122: pop
        //   123: aload_0
        //   124: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   127: invokestatic access$400 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;)Lpl/com/mobilelabs/mobilus/services/managementservice/ManagementService$ManagementServiceBinder;
        //   130: invokevirtual startReadingNetworkSettings : ()Z
        //   133: pop
        //   134: iload #4
        //   136: istore_3
        //   137: goto -> 636
        //   140: aload_2
        //   141: invokevirtual getAction : ()Ljava/lang/String;
        //   144: ldc 'ManagementService.Intents.networkConfigurationReceived'
        //   146: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
        //   149: ifeq -> 466
        //   152: aload_2
        //   153: ldc 'ManagementService.Intents.networkConfigurationReceivedOperationStatus'
        //   155: iconst_0
        //   156: invokevirtual getIntExtra : (Ljava/lang/String;I)I
        //   159: istore #6
        //   161: aload_2
        //   162: ldc 'ManagementService.Intents.networkConfigurationReceivedWifiState'
        //   164: invokevirtual hasExtra : (Ljava/lang/String;)Z
        //   167: ifeq -> 193
        //   170: aload_0
        //   171: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   174: aload_2
        //   175: ldc 'ManagementService.Intents.networkConfigurationReceivedWifiState'
        //   177: invokevirtual getSerializableExtra : (Ljava/lang/String;)Ljava/io/Serializable;
        //   180: checkcast pl/com/mobilelabs/mobilus/model/WifiState
        //   183: invokevirtual getValue : ()I
        //   186: invokestatic access$502 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;I)I
        //   189: pop
        //   190: goto -> 202
        //   193: aload_0
        //   194: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   197: iconst_0
        //   198: invokestatic access$502 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;I)I
        //   201: pop
        //   202: aload_2
        //   203: ldc 'ManagementService.Intents.networkConfigurationReceivedNetName'
        //   205: invokevirtual hasExtra : (Ljava/lang/String;)Z
        //   208: ifeq -> 228
        //   211: aload_0
        //   212: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   215: aload_2
        //   216: ldc 'ManagementService.Intents.networkConfigurationReceivedNetName'
        //   218: invokevirtual getStringExtra : (Ljava/lang/String;)Ljava/lang/String;
        //   221: invokestatic access$602 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;Ljava/lang/String;)Ljava/lang/String;
        //   224: pop
        //   225: goto -> 237
        //   228: aload_0
        //   229: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   232: aconst_null
        //   233: invokestatic access$602 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;Ljava/lang/String;)Ljava/lang/String;
        //   236: pop
        //   237: aload_2
        //   238: ldc 'ManagementService.Intents.networkConfigurationReceivedNetPassword'
        //   240: invokevirtual hasExtra : (Ljava/lang/String;)Z
        //   243: ifeq -> 263
        //   246: aload_0
        //   247: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   250: aload_2
        //   251: ldc 'ManagementService.Intents.networkConfigurationReceivedNetPassword'
        //   253: invokevirtual getStringExtra : (Ljava/lang/String;)Ljava/lang/String;
        //   256: invokestatic access$702 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;Ljava/lang/String;)Ljava/lang/String;
        //   259: pop
        //   260: goto -> 272
        //   263: aload_0
        //   264: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   267: aconst_null
        //   268: invokestatic access$702 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;Ljava/lang/String;)Ljava/lang/String;
        //   271: pop
        //   272: aload_0
        //   273: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   276: invokestatic access$800 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;)Z
        //   279: ifeq -> 634
        //   282: aload_2
        //   283: ldc 'ManagementService.Intents.networkConfigurationReceivedOperationStatus'
        //   285: invokevirtual hasExtra : (Ljava/lang/String;)Z
        //   288: ifeq -> 634
        //   291: aload_0
        //   292: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   295: iconst_0
        //   296: invokestatic access$802 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;Z)Z
        //   299: pop
        //   300: iload #6
        //   302: tableswitch default -> 328, 0 -> 357, 1 -> 344, 2 -> 331
        //   328: goto -> 370
        //   331: aload_1
        //   332: ldc 2131624139
        //   334: iconst_0
        //   335: invokestatic makeText : (Landroid/content/Context;II)Landroid/widget/Toast;
        //   338: invokevirtual show : ()V
        //   341: goto -> 370
        //   344: aload_1
        //   345: ldc 2131624103
        //   347: iconst_0
        //   348: invokestatic makeText : (Landroid/content/Context;II)Landroid/widget/Toast;
        //   351: invokevirtual show : ()V
        //   354: goto -> 370
        //   357: aload_1
        //   358: ldc 2131624141
        //   360: iconst_0
        //   361: invokestatic makeText : (Landroid/content/Context;II)Landroid/widget/Toast;
        //   364: invokevirtual show : ()V
        //   367: iconst_1
        //   368: istore #5
        //   370: aload_0
        //   371: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   374: invokestatic access$600 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;)Ljava/lang/String;
        //   377: ifnonnull -> 391
        //   380: aload_0
        //   381: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   384: invokestatic access$900 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;)Lpl/com/mobilelabs/mobilus/services/managementservice/ManagementService$ManagementServiceBinder;
        //   387: invokevirtual startReadingDeviceSettings : ()Z
        //   390: pop
        //   391: iload #5
        //   393: ifeq -> 634
        //   396: aload_0
        //   397: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   400: invokestatic access$100 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;)Z
        //   403: ifeq -> 456
        //   406: aload_0
        //   407: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   410: invokestatic access$200 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;)Ljava/lang/String;
        //   413: ifnull -> 456
        //   416: aload_0
        //   417: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   420: invokestatic access$1000 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;)Ljava/lang/String;
        //   423: ifnull -> 456
        //   426: aload_0
        //   427: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   430: invokestatic access$200 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;)Ljava/lang/String;
        //   433: aload_0
        //   434: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   437: invokestatic access$1000 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;)Ljava/lang/String;
        //   440: invokevirtual equals : (Ljava/lang/Object;)Z
        //   443: ifne -> 456
        //   446: aload_0
        //   447: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   450: invokevirtual finish : ()V
        //   453: goto -> 634
        //   456: aload_0
        //   457: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   460: invokestatic access$1100 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;)V
        //   463: goto -> 634
        //   466: aload_2
        //   467: invokevirtual getAction : ()Ljava/lang/String;
        //   470: ldc 'ManagementService.Intents.wifiNetworksListReceived'
        //   472: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
        //   475: ifeq -> 579
        //   478: iload #4
        //   480: istore_3
        //   481: aload_2
        //   482: ldc 'ManagementService.Intents.wifiNetworksListReceivedNetworksList'
        //   484: invokevirtual hasExtra : (Ljava/lang/String;)Z
        //   487: ifeq -> 636
        //   490: aload_0
        //   491: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   494: invokestatic access$1200 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;)Ljava/util/ArrayList;
        //   497: ifnonnull -> 515
        //   500: aload_0
        //   501: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   504: new java/util/ArrayList
        //   507: dup
        //   508: invokespecial <init> : ()V
        //   511: invokestatic access$1202 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;Ljava/util/ArrayList;)Ljava/util/ArrayList;
        //   514: pop
        //   515: aload_0
        //   516: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   519: invokestatic access$1200 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;)Ljava/util/ArrayList;
        //   522: invokevirtual clear : ()V
        //   525: aload_2
        //   526: ldc 'ManagementService.Intents.wifiNetworksListReceivedNetworksList'
        //   528: invokevirtual getSerializableExtra : (Ljava/lang/String;)Ljava/io/Serializable;
        //   531: checkcast java/util/ArrayList
        //   534: astore_2
        //   535: aload_0
        //   536: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   539: invokestatic access$1200 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;)Ljava/util/ArrayList;
        //   542: aload_2
        //   543: invokevirtual addAll : (Ljava/util/Collection;)Z
        //   546: pop
        //   547: aload_0
        //   548: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   551: getfield netNameSpinner : Landroid/support/v7/widget/AppCompatSpinner;
        //   554: invokevirtual getAdapter : ()Landroid/widget/SpinnerAdapter;
        //   557: checkcast pl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity$WifiNetworkAdapter
        //   560: invokevirtual notifyDataSetChanged : ()V
        //   563: aload_1
        //   564: ldc 2131624223
        //   566: iconst_0
        //   567: invokestatic makeText : (Landroid/content/Context;II)Landroid/widget/Toast;
        //   570: invokevirtual show : ()V
        //   573: iload #4
        //   575: istore_3
        //   576: goto -> 636
        //   579: aload_2
        //   580: invokevirtual getAction : ()Ljava/lang/String;
        //   583: ldc 'ManagementService.Intents.connectionStatusChanged'
        //   585: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
        //   588: ifeq -> 634
        //   591: iload #4
        //   593: istore_3
        //   594: aload_2
        //   595: ldc 'ManagementService.Intents.connectionStatusChangedConnectionState'
        //   597: invokevirtual hasExtra : (Ljava/lang/String;)Z
        //   600: ifeq -> 636
        //   603: iload #4
        //   605: istore_3
        //   606: aload_2
        //   607: ldc 'ManagementService.Intents.connectionStatusChangedConnectionState'
        //   609: invokevirtual getSerializableExtra : (Ljava/lang/String;)Ljava/io/Serializable;
        //   612: checkcast pl/com/mobilelabs/mobilus/model/ConnectionStatus
        //   615: getstatic pl/com/mobilelabs/mobilus/model/ConnectionStatus.NO_CONNECTION : Lpl/com/mobilelabs/mobilus/model/ConnectionStatus;
        //   618: if_acmpne -> 636
        //   621: aload_0
        //   622: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   625: invokevirtual finish : ()V
        //   628: iload #4
        //   630: istore_3
        //   631: goto -> 636
        //   634: iconst_1
        //   635: istore_3
        //   636: aload_0
        //   637: getfield this$0 : Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;
        //   640: iload_3
        //   641: invokestatic access$1300 : (Lpl/com/mobilelabs/mobilus/view/activity/settings/WifiChangeSettingsActivity;Z)V
        //   644: return
      }
    };
  
  private String centralIpAddress;
  
  @BindView(2131296333)
  protected TextView ipTextView;
  
  @BindView(2131296334)
  protected TextView macTextView;
  
  @BindView(2131296335)
  protected AppCompatSpinner modeSpinner;
  
  @BindView(2131296336)
  protected RelativeLayout netLayout;
  
  @BindView(2131296339)
  protected AppCompatSpinner netNameSpinner;
  
  @BindView(2131296341)
  protected TextInputEditText netNameTextInputEditText;
  
  @BindView(2131296340)
  protected TextInputLayout netNameTextInputLayout;
  
  @BindView(2131296343)
  protected TextInputEditText netPasswordTextInputEditText;
  
  @BindView(2131296342)
  protected TextInputLayout netPasswordTextInputLayout;
  
  private ArrayList<MobilusModel.WifiNetwork> networksList;
  
  @BindView(2131296344)
  protected AppCompatImageButton refreshButton;
  
  private boolean sentNewValues;
  
  private boolean showButton;
  
  private String wifiIp;
  
  private String wifiMac;
  
  private String wifiNetName;
  
  private String wifiPasswordName;
  
  private int wifiState;
  
  private void exitAndLogout() {
    setResult(-1, (new Intent()).setAction("WifiChangeSettingsActivity.exitOrLogoutAction"));
    finish();
  }
  
  private void presentData() {
    presentData(true);
  }
  
  private void presentData(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: getfield showButton : Z
    //   4: ifne -> 11
    //   7: aload_0
    //   8: invokevirtual finish : ()V
    //   11: iload_1
    //   12: ifeq -> 26
    //   15: aload_0
    //   16: getfield modeSpinner : Landroid/support/v7/widget/AppCompatSpinner;
    //   19: aload_0
    //   20: getfield wifiState : I
    //   23: invokevirtual setSelection : (I)V
    //   26: aload_0
    //   27: getfield wifiState : I
    //   30: istore_2
    //   31: iconst_0
    //   32: istore_3
    //   33: iload_2
    //   34: tableswitch default -> 60, 0 -> 215, 1 -> 167, 2 -> 63
    //   60: goto -> 215
    //   63: iconst_0
    //   64: istore_2
    //   65: iload_2
    //   66: aload_0
    //   67: getfield networksList : Ljava/util/ArrayList;
    //   70: invokevirtual size : ()I
    //   73: if_icmpge -> 109
    //   76: aload_0
    //   77: getfield networksList : Ljava/util/ArrayList;
    //   80: iload_2
    //   81: invokevirtual get : (I)Ljava/lang/Object;
    //   84: checkcast pl/com/mobilelabs/mobilus/model/communication/MobilusModel$WifiNetwork
    //   87: invokevirtual getSsid : ()Ljava/lang/String;
    //   90: aload_0
    //   91: getfield wifiNetName : Ljava/lang/String;
    //   94: invokevirtual compareTo : (Ljava/lang/String;)I
    //   97: ifne -> 103
    //   100: goto -> 111
    //   103: iinc #2, 1
    //   106: goto -> 65
    //   109: iconst_m1
    //   110: istore_2
    //   111: iload_2
    //   112: iflt -> 126
    //   115: aload_0
    //   116: getfield netNameSpinner : Landroid/support/v7/widget/AppCompatSpinner;
    //   119: iload_2
    //   120: invokevirtual setSelection : (I)V
    //   123: goto -> 134
    //   126: aload_0
    //   127: getfield netNameSpinner : Landroid/support/v7/widget/AppCompatSpinner;
    //   130: iconst_m1
    //   131: invokevirtual setSelection : (I)V
    //   134: aload_0
    //   135: getfield wifiPasswordName : Ljava/lang/String;
    //   138: ifnull -> 155
    //   141: aload_0
    //   142: getfield netPasswordTextInputEditText : Landroid/support/design/widget/TextInputEditText;
    //   145: aload_0
    //   146: getfield wifiPasswordName : Ljava/lang/String;
    //   149: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   152: goto -> 215
    //   155: aload_0
    //   156: getfield netPasswordTextInputEditText : Landroid/support/design/widget/TextInputEditText;
    //   159: ldc ''
    //   161: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   164: goto -> 215
    //   167: aload_0
    //   168: getfield wifiNetName : Ljava/lang/String;
    //   171: ifnull -> 185
    //   174: aload_0
    //   175: getfield netNameTextInputEditText : Landroid/support/design/widget/TextInputEditText;
    //   178: aload_0
    //   179: getfield wifiNetName : Ljava/lang/String;
    //   182: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   185: aload_0
    //   186: getfield wifiPasswordName : Ljava/lang/String;
    //   189: ifnull -> 206
    //   192: aload_0
    //   193: getfield netPasswordTextInputEditText : Landroid/support/design/widget/TextInputEditText;
    //   196: aload_0
    //   197: getfield wifiPasswordName : Ljava/lang/String;
    //   200: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   203: goto -> 215
    //   206: aload_0
    //   207: getfield netPasswordTextInputEditText : Landroid/support/design/widget/TextInputEditText;
    //   210: ldc ''
    //   212: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   215: aload_0
    //   216: getfield wifiIp : Ljava/lang/String;
    //   219: ifnull -> 306
    //   222: aload_0
    //   223: getfield ipTextView : Landroid/widget/TextView;
    //   226: aload_0
    //   227: getfield wifiIp : Ljava/lang/String;
    //   230: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   233: aload_0
    //   234: getfield boldIp : Z
    //   237: ifeq -> 291
    //   240: aload_0
    //   241: getfield centralIpAddress : Ljava/lang/String;
    //   244: ifnull -> 291
    //   247: aload_0
    //   248: getfield ipTextView : Landroid/widget/TextView;
    //   251: astore #4
    //   253: aload_0
    //   254: getfield ipTextView : Landroid/widget/TextView;
    //   257: invokevirtual getTypeface : ()Landroid/graphics/Typeface;
    //   260: astore #5
    //   262: iload_3
    //   263: istore_2
    //   264: aload_0
    //   265: getfield wifiIp : Ljava/lang/String;
    //   268: aload_0
    //   269: getfield centralIpAddress : Ljava/lang/String;
    //   272: invokevirtual compareTo : (Ljava/lang/String;)I
    //   275: ifne -> 280
    //   278: iconst_1
    //   279: istore_2
    //   280: aload #4
    //   282: aload #5
    //   284: iload_2
    //   285: invokevirtual setTypeface : (Landroid/graphics/Typeface;I)V
    //   288: goto -> 306
    //   291: aload_0
    //   292: getfield ipTextView : Landroid/widget/TextView;
    //   295: aload_0
    //   296: getfield ipTextView : Landroid/widget/TextView;
    //   299: invokevirtual getTypeface : ()Landroid/graphics/Typeface;
    //   302: iconst_0
    //   303: invokevirtual setTypeface : (Landroid/graphics/Typeface;I)V
    //   306: aload_0
    //   307: getfield wifiMac : Ljava/lang/String;
    //   310: ifnull -> 324
    //   313: aload_0
    //   314: getfield macTextView : Landroid/widget/TextView;
    //   317: aload_0
    //   318: getfield wifiMac : Ljava/lang/String;
    //   321: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   324: return
  }
  
  protected void managementServiceConnected() {
    this.managementBinder.startReadingWifiNetworkList();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setResult(0);
    initializeActivity(2131492981);
    configureView(2131558767, 2131624185, 2131624046);
    ButterKnife.bind((Activity)this);
    this.sentNewValues = false;
    ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource((Context)this, 2130903045, 2131492982);
    arrayAdapter.setDropDownViewResource(17367049);
    this.modeSpinner.setAdapter((SpinnerAdapter)arrayAdapter);
    this.modeSpinner.setOnItemSelectedListener(new ModeSpinnerOnItemListener());
    this.networksList = new ArrayList<>();
    WifiNetworkAdapter wifiNetworkAdapter = new WifiNetworkAdapter((Context)this, this.networksList);
    this.netNameSpinner.setAdapter(wifiNetworkAdapter);
    this.netNameSpinner.setOnItemSelectedListener(new NetworkListSpinnerOnItemListener());
    this.netNameTextInputLayout.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Regular.otf"));
    this.netPasswordTextInputLayout.setTypeface(Typeface.createFromAsset(getAssets(), "Titillium-Regular.otf"));
    IntentFilter intentFilter = new IntentFilter("ManagementService.Intents.newConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.deviceConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.networkConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.wifiNetworksListReceived");
    intentFilter.addAction("ManagementService.Intents.connectionStatusChanged");
    LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.broadcastReceiver, intentFilter);
    if (getIntent().hasExtra("WifiChangeSettingsActivity.mode")) {
      this.wifiState = getIntent().getIntExtra("WifiChangeSettingsActivity.mode", 0);
    } else {
      this.wifiState = -1;
    } 
    if (getIntent().hasExtra("WifiChangeSettingsActivity.ipAddress")) {
      this.wifiIp = getIntent().getStringExtra("WifiChangeSettingsActivity.ipAddress");
    } else {
      this.wifiIp = null;
    } 
    if (getIntent().hasExtra("WifiChangeSettingsActivity.currentIpAddress")) {
      this.centralIpAddress = getIntent().getStringExtra("WifiChangeSettingsActivity.currentIpAddress");
    } else {
      this.centralIpAddress = null;
    } 
    if (getIntent().hasExtra("WifiChangeSettingsActivity.macAddress")) {
      this.wifiMac = getIntent().getStringExtra("WifiChangeSettingsActivity.macAddress");
    } else {
      this.wifiMac = null;
    } 
    if (getIntent().hasExtra("WifiChangeSettingsActivity.netName")) {
      this.wifiNetName = getIntent().getStringExtra("WifiChangeSettingsActivity.netName");
    } else {
      this.wifiNetName = null;
    } 
    if (this.networksList == null)
      this.networksList = new ArrayList<>(); 
    if (getIntent().hasExtra("WifiChangeSettingsActivity.netPassword")) {
      this.wifiPasswordName = getIntent().getStringExtra("WifiChangeSettingsActivity.netPassword");
    } else {
      this.wifiPasswordName = null;
    } 
    wifiNetworkAdapter.notifyDataSetChanged();
    if (getIntent().hasExtra("WifiChangeSettingsActivity.showButton")) {
      this.showButton = getIntent().getBooleanExtra("WifiChangeSettingsActivity.showButton", false);
    } else {
      this.showButton = false;
    } 
    if (getIntent().hasExtra("WifiChangeSettingsActivity.BOLD_IP")) {
      this.boldIp = getIntent().getBooleanExtra("WifiChangeSettingsActivity.BOLD_IP", false);
    } else {
      this.boldIp = false;
    } 
    presentData();
  }
  
  protected void onDestroy() {
    LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(this.broadcastReceiver);
    super.onDestroy();
  }
  
  @OnClick({2131296332})
  protected void wifiConfigCancelClicked() {
    finish();
  }
  
  @OnClick({2131296331})
  protected void wifiConfigChangeClicked() {
    MobilusModel.WifiNetwork wifiNetwork;
    int i = this.modeSpinner.getSelectedItemPosition();
    WifiState wifiState = WifiState.fromValue(i);
    switch (i) {
      default:
        return;
      case 2:
        wifiNetwork = (MobilusModel.WifiNetwork)this.netNameSpinner.getAdapter().getItem(this.netNameSpinner.getSelectedItemPosition());
        if (!wifiNetwork.getSsid().isEmpty())
          if (!wifiNetwork.getEncrypted()) {
            this.sentNewValues = this.managementBinder.startWritingNetworkSettings(wifiState, wifiNetwork.getSsid(), null);
          } else {
            this.sentNewValues = this.managementBinder.startWritingNetworkSettings(wifiState, wifiNetwork.getSsid(), this.netPasswordTextInputEditText.getText().toString());
          }  
      case 1:
        if (!this.netNameTextInputEditText.getText().toString().isEmpty())
          if (this.netPasswordTextInputEditText.getText().toString().isEmpty()) {
            this.sentNewValues = this.managementBinder.startWritingNetworkSettings(wifiState, this.netNameTextInputEditText.getText().toString(), null);
          } else {
            this.sentNewValues = this.managementBinder.startWritingNetworkSettings(wifiState, this.netNameTextInputEditText.getText().toString(), this.netPasswordTextInputEditText.getText().toString());
          }  
      case 0:
        break;
    } 
    this.sentNewValues = this.managementBinder.startWritingNetworkSettings(wifiState, null, null);
  }
  
  @OnClick({2131296344})
  protected void wifiNetworksRefreshButtonClicked() {
    Toast.makeText((Context)this, 2131624154, 0).show();
    this.networksList.clear();
    ((WifiNetworkAdapter)this.netNameSpinner.getAdapter()).notifyDataSetChanged();
    this.managementBinder.startReadingWifiNetworkList();
  }
  
  private class ModeSpinnerOnItemListener implements AdapterView.OnItemSelectedListener {
    private ModeSpinnerOnItemListener() {}
    
    public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
      byte b;
      byte b1;
      switch (param1Int) {
        default:
          return;
        case 2:
          WifiChangeSettingsActivity.this.netLayout.setVisibility(0);
          WifiChangeSettingsActivity.this.netNameTextInputLayout.setVisibility(8);
          WifiChangeSettingsActivity.this.netNameSpinner.setVisibility(0);
          WifiChangeSettingsActivity.this.refreshButton.setVisibility(0);
          b = -1;
          for (b1 = 0; b1 < WifiChangeSettingsActivity.this.networksList.size(); b1++) {
            if (((MobilusModel.WifiNetwork)WifiChangeSettingsActivity.this.networksList.get(b1)).getSsid().compareTo(WifiChangeSettingsActivity.this.wifiNetName) == 0)
              b = b1; 
          } 
          if (WifiChangeSettingsActivity.this.wifiNetName != null && b >= 0 && WifiChangeSettingsActivity.this.wifiNetName.compareTo(((MobilusModel.WifiNetwork)WifiChangeSettingsActivity.this.networksList.get(b)).getSsid()) == 0)
            if (WifiChangeSettingsActivity.this.wifiPasswordName != null) {
              WifiChangeSettingsActivity.this.netPasswordTextInputLayout.setVisibility(0);
            } else {
              WifiChangeSettingsActivity.this.netPasswordTextInputLayout.setVisibility(8);
            }  
          if (param1Int == WifiChangeSettingsActivity.this.wifiState && WifiChangeSettingsActivity.this.wifiPasswordName != null) {
            WifiChangeSettingsActivity.this.netPasswordTextInputEditText.setText(WifiChangeSettingsActivity.this.wifiPasswordName);
          } else {
            WifiChangeSettingsActivity.this.netPasswordTextInputEditText.setText("");
          } 
        case 1:
          WifiChangeSettingsActivity.this.netLayout.setVisibility(0);
          WifiChangeSettingsActivity.this.netNameTextInputLayout.setVisibility(0);
          WifiChangeSettingsActivity.this.netNameSpinner.setVisibility(8);
          WifiChangeSettingsActivity.this.netPasswordTextInputLayout.setVisibility(0);
          if (param1Int == WifiChangeSettingsActivity.this.wifiState && WifiChangeSettingsActivity.this.wifiPasswordName != null) {
            WifiChangeSettingsActivity.this.netPasswordTextInputEditText.setText(WifiChangeSettingsActivity.this.wifiPasswordName);
          } else {
            WifiChangeSettingsActivity.this.netPasswordTextInputEditText.setText("");
          } 
          WifiChangeSettingsActivity.this.refreshButton.setVisibility(4);
        case 0:
          break;
      } 
      WifiChangeSettingsActivity.this.netLayout.setVisibility(8);
    }
    
    public void onNothingSelected(AdapterView<?> param1AdapterView) {}
  }
  
  private class NetworkListSpinnerOnItemListener implements AdapterView.OnItemSelectedListener {
    private NetworkListSpinnerOnItemListener() {}
    
    public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
      MobilusModel.WifiNetwork wifiNetwork = (MobilusModel.WifiNetwork)WifiChangeSettingsActivity.this.netNameSpinner.getAdapter().getItem(param1Int);
      TextInputLayout textInputLayout = WifiChangeSettingsActivity.this.netPasswordTextInputLayout;
      if (wifiNetwork.getEncrypted()) {
        param1Int = 0;
      } else {
        param1Int = 8;
      } 
      textInputLayout.setVisibility(param1Int);
      if (wifiNetwork.getSsid().compareTo(WifiChangeSettingsActivity.this.wifiNetName) == 0) {
        WifiChangeSettingsActivity.this.netPasswordTextInputEditText.setText(WifiChangeSettingsActivity.this.wifiPasswordName);
      } else {
        WifiChangeSettingsActivity.this.netPasswordTextInputEditText.setText("");
      } 
    }
    
    public void onNothingSelected(AdapterView<?> param1AdapterView) {}
  }
  
  private class WifiNetworkAdapter extends BaseAdapter implements SpinnerAdapter {
    private Context context;
    
    private ArrayList<MobilusModel.WifiNetwork> list;
    
    public WifiNetworkAdapter(Context param1Context, ArrayList<MobilusModel.WifiNetwork> param1ArrayList) {
      this.list = param1ArrayList;
      this.context = param1Context;
    }
    
    public int getCount() {
      int i = this.list.size();
      return (i > 0) ? i : ((WifiChangeSettingsActivity.this.wifiNetName != null) ? 1 : 1);
    }
    
    public View getDropDownView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
      if (this.list.size() > 0) {
        View view = View.inflate(this.context, 2131492978, null);
        TextView textView = (TextView)view.findViewById(2131296562);
        AppCompatImageView appCompatImageView = (AppCompatImageView)view.findViewById(2131296561);
        textView.setText(((MobilusModel.WifiNetwork)this.list.get(param1Int)).getSsid());
        if (((MobilusModel.WifiNetwork)this.list.get(param1Int)).getEncrypted()) {
          appCompatImageView.setImageResource(2131558428);
        } else {
          appCompatImageView.setImageResource(2131558427);
        } 
        return view;
      } 
      return View.inflate(this.context, 2131492979, null);
    }
    
    public Object getItem(int param1Int) {
      if (this.list.size() > 0)
        return this.list.get(param1Int); 
      if (WifiChangeSettingsActivity.this.wifiNetName != null) {
        boolean bool;
        MobilusModel.WifiNetwork.Builder builder = MobilusModel.WifiNetwork.newBuilder();
        builder.setSsid(WifiChangeSettingsActivity.this.wifiNetName);
        if (WifiChangeSettingsActivity.this.wifiPasswordName != null) {
          bool = true;
        } else {
          bool = false;
        } 
        builder.setEncrypted(bool);
        return builder.build();
      } 
      return null;
    }
    
    public long getItemId(int param1Int) {
      return param1Int;
    }
    
    public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
      param1View = View.inflate(this.context, 2131492980, null);
      TextView textView = (TextView)param1View.findViewById(2131296650);
      if (this.list.size() > 0) {
        textView.setText(((MobilusModel.WifiNetwork)this.list.get(param1Int)).getSsid());
      } else if (WifiChangeSettingsActivity.this.wifiNetName != null) {
        textView.setText(WifiChangeSettingsActivity.this.wifiNetName);
      } else {
        textView.setText(2131624138);
      } 
      return param1View;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\WifiChangeSettingsActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */