package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.HashSet;
import pl.com.mobilelabs.mobilus.model.communication.MobilusModel;
import pl.com.mobilelabs.mobilus.model.objects.Device;
import pl.com.mobilelabs.mobilus.model.objects.Group;
import pl.com.mobilelabs.mobilus.model.objects.Place;
import pl.com.mobilelabs.mobilus.model.objects.UserUtils;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.utils.Encryption;
import pl.com.mobilelabs.mobilus.view.activity.BackButtonHeaderActivity;

public class UserDetailsSettingsActivity extends BackButtonHeaderActivity {
  public static final String CURRENT_USER_ID = "UserDetailsSettingsActivity.currentUserId";
  
  private static final String TAG = "EditUser";
  
  public static final String USER = "UserDetailsSettingsActivity.user";
  
  public static final String USERS_LIST = "UserDetailsSettingsActivity.usersList";
  
  @BindView(2131296298)
  protected TextInputEditText adminPasswordEditText;
  
  @BindView(2131296297)
  protected TextInputLayout adminPasswordTextInput;
  
  @BindView(2131296299)
  protected SwitchCompat adminSwitch;
  
  @BindView(2131296300)
  protected LinearLayout applyButton;
  
  private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        boolean bool = param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.newConfigurationReceived");
        boolean bool1 = false;
        if (bool) {
          if (!param1Intent.getBooleanExtra("ManagementService.Intents.newConfigurationReceivedIsAdmin", false) || !UserDetailsSettingsActivity.this.managementBinder.isWorkingInLocalMode())
            UserDetailsSettingsActivity.this.finish(); 
        } else if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.updateUserResponseReceived")) {
          switch (param1Intent.getIntExtra("ManagementService.Intents.updateUserResponseReceivedOperationStatus", 0)) {
            case 2:
              Toast.makeText(param1Context, 2131624139, 0).show();
              break;
            case 1:
              Toast.makeText(param1Context, 2131624103, 0).show();
              break;
            case 0:
              Toast.makeText(param1Context, 2131624141, 0).show();
              bool1 = true;
              break;
          } 
          if (bool1)
            UserDetailsSettingsActivity.this.finish(); 
        } 
      }
    };
  
  protected long currentUserId;
  
  protected boolean devicesChanged = false;
  
  @BindView(2131296302)
  protected LinearLayout devicesGroupsPlacesLayout;
  
  @BindView(2131296303)
  protected TableLayout devicesListTable;
  
  @BindView(2131296304)
  protected TableLayout groupsListTable;
  
  @BindView(2131296306)
  protected TextInputEditText newPasswordEditText;
  
  @BindView(2131296305)
  protected TextInputLayout newPasswordTextInput;
  
  @BindView(2131296307)
  protected TableLayout placesListTable;
  
  protected MobilusModel.User receivedUser;
  
  @BindView(2131296309)
  protected TextInputEditText repeatPasswordEditText;
  
  @BindView(2131296308)
  protected TextInputLayout repeatPasswordTextInput;
  
  @BindView(2131296310)
  protected TextView titleView;
  
  protected MobilusModel.User.Builder userToSendBuilder;
  
  @BindView(2131296312)
  protected TextInputEditText usernameEditText;
  
  @BindView(2131296311)
  protected TextInputLayout usernameTextInput;
  
  protected ArrayList<MobilusModel.User> usersList;
  
  private void applyChanges() {
    MobilusModel.User user = this.userToSendBuilder.build();
    if (this.receivedUser == null) {
      this.managementBinder.sendAddUserRequest(user, this.adminPasswordEditText.getText().toString());
    } else {
      this.managementBinder.sendEditUserRequest(user, this.adminPasswordEditText.getText().toString());
    } 
  }
  
  private boolean checkValidity() {
    if (!this.managementBinder.isPasswordValid(this.adminPasswordEditText.getText().toString())) {
      this.adminPasswordTextInput.setError(getString(2131624226));
      return false;
    } 
    if (this.userToSendBuilder.hasLogin() && this.usernameEditText.getText().toString().isEmpty()) {
      this.usernameTextInput.setError(getString(2131624008));
      return false;
    } 
    if (this.userToSendBuilder.hasLogin() && !UserUtils.isUsernameAvailaible(this.usersList, this.usernameEditText.getText().toString())) {
      this.usernameTextInput.setError(getString(2131624115));
      return false;
    } 
    if ((!this.newPasswordEditText.getText().toString().isEmpty() || !this.repeatPasswordEditText.getText().toString().isEmpty()) && !this.newPasswordEditText.getText().toString().contentEquals((CharSequence)this.repeatPasswordEditText.getText())) {
      this.newPasswordTextInput.setError(getString(2131624144));
      this.repeatPasswordTextInput.setError(getString(2131624144));
      return false;
    } 
    if (this.userToSendBuilder.hasPassword() && !this.newPasswordEditText.getText().toString().contentEquals((CharSequence)this.repeatPasswordEditText.getText())) {
      this.newPasswordTextInput.setError(getString(2131624144));
      this.repeatPasswordTextInput.setError(getString(2131624144));
    } 
    return true;
  }
  
  private void clearPasswordValidityError() {
    if (this.newPasswordEditText.getText().toString().contentEquals((CharSequence)this.repeatPasswordEditText.getText())) {
      this.newPasswordTextInput.setError(null);
      this.repeatPasswordTextInput.setError(null);
    } 
  }
  
  private void copyUserRights() {
    if (!this.devicesChanged && this.receivedUser != null && !this.receivedUser.getAdmin()) {
      this.userToSendBuilder.addAllAssignedDevicesIds(this.receivedUser.getAssignedDevicesIdsList());
      this.devicesChanged = true;
    } 
  }
  
  private void initNewUser() {
    this.userToSendBuilder.setId(UserUtils.firstAvailaibleUserId(this.usersList));
    this.userToSendBuilder.setLogin(UserUtils.firstAvailaibleNewUsername(this.usersList));
    setPasswordIfValid();
    this.userToSendBuilder.setAdmin(false);
    populateUserToSendDevices();
  }
  
  private void setPasswordIfValid() {
    if (this.newPasswordEditText.getText().toString().contentEquals((CharSequence)this.repeatPasswordEditText.getText())) {
      byte[] arrayOfByte = Encryption.getSha256Hash(this.newPasswordEditText.getText().toString().getBytes());
      this.userToSendBuilder.setPassword(ByteString.copyFrom(arrayOfByte));
    } 
  }
  
  @OnClick({2131296300})
  protected void applyButtonClicked() {
    copyUserRights();
    if (checkValidity())
      if (this.userToSendBuilder.hasPassword() && this.newPasswordEditText.getText().toString().isEmpty()) {
        Snackbar.make(getSettingsView(), 2131624070, 0).setAction(getString(2131624061).toUpperCase(), new View.OnClickListener() {
              public void onClick(View param1View) {
                UserDetailsSettingsActivity.this.applyChanges();
              }
            }).show();
      } else {
        applyChanges();
      }  
  }
  
  @OnClick({2131296301})
  protected void cancelButtonClicked() {
    finish();
  }
  
  protected void managementServiceConnected() {
    super.managementServiceConnected();
    populateTableLayouts();
  }
  
  @OnCheckedChanged({2131296299})
  protected void onAdminRightsCheckedChanged() {
    boolean bool;
    LinearLayout linearLayout = this.devicesGroupsPlacesLayout;
    if (this.adminSwitch.isChecked()) {
      bool = true;
    } else {
      bool = false;
    } 
    linearLayout.setVisibility(bool);
    if (!this.adminSwitch.isChecked()) {
      populateUserToSendDevices();
      populateTableLayouts();
    } 
    this.userToSendBuilder.setAdmin(this.adminSwitch.isChecked());
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    initializeActivity(2131492966);
    configureView(2131558767, 2131624206, 2131624046);
    ButterKnife.bind((Activity)this);
    IntentFilter intentFilter = new IntentFilter("ManagementService.Intents.newConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.updateUserResponseReceived");
    LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.broadcastReceiver, intentFilter);
    this.userToSendBuilder = MobilusModel.User.newBuilder();
    if (getIntent().hasExtra("UserDetailsSettingsActivity.currentUserId")) {
      this.currentUserId = getIntent().getLongExtra("UserDetailsSettingsActivity.currentUserId", -1L);
    } else {
      this.currentUserId = -1L;
    } 
    if (getIntent().hasExtra("UserDetailsSettingsActivity.usersList")) {
      this.usersList = (ArrayList<MobilusModel.User>)getIntent().getSerializableExtra("UserDetailsSettingsActivity.usersList");
    } else {
      this.usersList = new ArrayList<>();
    } 
    if (getIntent().hasExtra("UserDetailsSettingsActivity.user")) {
      try {
        this.receivedUser = MobilusModel.User.parseFrom(getIntent().getByteArrayExtra("UserDetailsSettingsActivity.user"));
        this.userToSendBuilder.setId(this.receivedUser.getId());
      } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
        invalidProtocolBufferException.printStackTrace();
        this.receivedUser = null;
        initNewUser();
      } 
    } else {
      this.receivedUser = null;
      initNewUser();
    } 
    presentData();
    this.usernameEditText.addTextChangedListener(new TextWatcher() {
          public void afterTextChanged(Editable param1Editable) {}
          
          public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
          
          public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            if (UserDetailsSettingsActivity.this.usernameTextInput.getError() != null && !UserDetailsSettingsActivity.this.usernameEditText.getText().toString().isEmpty() && UserUtils.isUsernameAvailaible(UserDetailsSettingsActivity.this.usersList, UserDetailsSettingsActivity.this.usernameEditText.getText().toString()))
              UserDetailsSettingsActivity.this.usernameTextInput.setError(null); 
            if (UserDetailsSettingsActivity.this.receivedUser == null || (UserDetailsSettingsActivity.this.receivedUser != null && !UserDetailsSettingsActivity.this.receivedUser.getLogin().contentEquals(UserDetailsSettingsActivity.this.usernameEditText.getText().toString()))) {
              UserDetailsSettingsActivity.this.userToSendBuilder.setLogin(UserDetailsSettingsActivity.this.usernameEditText.getText().toString());
              return;
            } 
            UserDetailsSettingsActivity.this.userToSendBuilder.clearLogin();
          }
        });
    this.adminPasswordEditText.addTextChangedListener(new TextWatcher() {
          public void afterTextChanged(Editable param1Editable) {}
          
          public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
          
          public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            if (UserDetailsSettingsActivity.this.adminPasswordTextInput.getError() != null && UserDetailsSettingsActivity.this.managementBinder.isPasswordValid(UserDetailsSettingsActivity.this.adminPasswordEditText.getText().toString()))
              UserDetailsSettingsActivity.this.adminPasswordTextInput.setError(null); 
          }
        });
    this.newPasswordEditText.addTextChangedListener(new TextWatcher() {
          public void afterTextChanged(Editable param1Editable) {}
          
          public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
          
          public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            if (UserDetailsSettingsActivity.this.newPasswordTextInput.getError() != null)
              UserDetailsSettingsActivity.this.clearPasswordValidityError(); 
            UserDetailsSettingsActivity.this.setPasswordIfValid();
          }
        });
    this.repeatPasswordEditText.addTextChangedListener(new TextWatcher() {
          public void afterTextChanged(Editable param1Editable) {}
          
          public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
          
          public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            if (UserDetailsSettingsActivity.this.repeatPasswordTextInput.getError() != null)
              UserDetailsSettingsActivity.this.clearPasswordValidityError(); 
            UserDetailsSettingsActivity.this.setPasswordIfValid();
          }
        });
  }
  
  protected void onDestroy() {
    LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(this.broadcastReceiver);
    super.onDestroy();
  }
  
  void populateTableLayouts() {
    this.devicesListTable.removeAllViews();
    this.groupsListTable.removeAllViews();
    this.placesListTable.removeAllViews();
    ArrayList<Device> arrayList = this.managementBinder.getDevices();
    HashSet<Long> hashSet = new HashSet();
    boolean bool = this.devicesChanged;
    boolean bool1 = false;
    if (!bool && this.receivedUser != null) {
      if (!this.receivedUser.getAdmin()) {
        for (byte b1 = 0; b1 < this.receivedUser.getAssignedDevicesIdsCount(); b1++)
          hashSet.add(Long.valueOf(this.receivedUser.getAssignedDevicesIds(b1))); 
      } else {
        for (byte b1 = 0; b1 < arrayList.size(); b1++)
          hashSet.add(Long.valueOf(((Device)arrayList.get(b1)).getId())); 
      } 
    } else {
      for (byte b1 = 0; b1 < this.userToSendBuilder.getAssignedDevicesIdsCount(); b1++)
        hashSet.add(Long.valueOf(this.userToSendBuilder.getAssignedDevicesIds(b1))); 
    } 
    ArrayList<AppCompatCheckBox> arrayList1 = new ArrayList();
    byte b;
    for (b = 0; b < arrayList.size(); b++) {
      Device device = arrayList.get(b);
      TableRow tableRow = (TableRow)getLayoutInflater().inflate(2131492965, null);
      AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox)tableRow.findViewById(2131296490);
      appCompatCheckBox.setText(device.getName());
      appCompatCheckBox.setChecked(hashSet.contains(Long.valueOf(device.getId())));
      appCompatCheckBox.setTag(device);
      appCompatCheckBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
              AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox)param1View;
              UserDetailsSettingsActivity.this.populateUserToSendDevices();
              UserUtils.addRemoveDeviceId(UserDetailsSettingsActivity.this.userToSendBuilder, (Device)param1View.getTag(), appCompatCheckBox.isChecked());
              UserDetailsSettingsActivity.this.populateTableLayouts();
            }
          });
      arrayList1.add(appCompatCheckBox);
      this.devicesListTable.addView((View)tableRow, b);
    } 
    ArrayList<Group> arrayList2 = this.managementBinder.getGroups();
    ArrayList<AppCompatCheckBox> arrayList3 = new ArrayList();
    for (b = 0; b < arrayList2.size(); b++) {
      Group group = arrayList2.get(b);
      TableRow tableRow = (TableRow)getLayoutInflater().inflate(2131492965, null);
      AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox)tableRow.findViewById(2131296490);
      appCompatCheckBox.setText(group.getName());
      appCompatCheckBox.setTag(group);
      appCompatCheckBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
              AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox)param1View;
              UserDetailsSettingsActivity.this.populateUserToSendDevices();
              UserUtils.addRemoveGroupId(UserDetailsSettingsActivity.this.userToSendBuilder, (Group)param1View.getTag(), appCompatCheckBox.isChecked());
              UserDetailsSettingsActivity.this.populateTableLayouts();
            }
          });
      arrayList3.add(appCompatCheckBox);
      this.groupsListTable.addView((View)tableRow, b);
    } 
    UserUtils.changeGroupsCheckBoxSelections(hashSet, arrayList3);
    ArrayList<Place> arrayList4 = this.managementBinder.getPlaces();
    arrayList = new ArrayList<>();
    for (b = bool1; b < arrayList4.size(); b++) {
      Place place = arrayList4.get(b);
      TableRow tableRow = (TableRow)getLayoutInflater().inflate(2131492965, null);
      AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox)tableRow.findViewById(2131296490);
      appCompatCheckBox.setText(place.getName());
      appCompatCheckBox.setTag(place);
      appCompatCheckBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
              AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox)param1View;
              UserDetailsSettingsActivity.this.populateUserToSendDevices();
              UserUtils.addRemovePlaceId(UserDetailsSettingsActivity.this.userToSendBuilder, (Place)param1View.getTag(), appCompatCheckBox.isChecked());
              UserDetailsSettingsActivity.this.populateTableLayouts();
            }
          });
      arrayList.add(appCompatCheckBox);
      this.placesListTable.addView((View)tableRow, b);
    } 
    UserUtils.changePlacesCheckBoxSelections(hashSet, arrayList3, arrayList);
  }
  
  void populateUserToSendDevices() {
    if (!this.devicesChanged) {
      if (this.receivedUser != null)
        if (this.receivedUser.getAdmin()) {
          ArrayList<Device> arrayList = this.managementBinder.getDevices();
          for (byte b = 0; b < arrayList.size(); b++)
            this.userToSendBuilder.addAssignedDevicesIds(((Device)arrayList.get(b)).getId()); 
        } else {
          this.userToSendBuilder.addAllAssignedDevicesIds(this.receivedUser.getAssignedDevicesIdsList());
        }  
      this.devicesChanged = true;
    } 
  }
  
  void presentData() {
    if (this.receivedUser == null) {
      this.titleView.setText(2131624132);
      this.usernameEditText.setText(this.userToSendBuilder.getLogin());
    } else {
      boolean bool;
      this.titleView.setText(2131624015);
      this.usernameEditText.setText(this.receivedUser.getLogin());
      this.adminSwitch.setChecked(this.receivedUser.getAdmin());
      SwitchCompat switchCompat = this.adminSwitch;
      if (this.receivedUser.getId() != this.currentUserId) {
        bool = true;
      } else {
        bool = false;
      } 
      switchCompat.setEnabled(bool);
    } 
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\UserDetailsSettingsActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */