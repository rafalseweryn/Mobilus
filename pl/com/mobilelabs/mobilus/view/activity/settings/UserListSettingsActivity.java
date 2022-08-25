package pl.com.mobilelabs.mobilus.view.activity.settings;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import pl.com.mobilelabs.mobilus.model.communication.MobilusModel;
import pl.com.mobilelabs.mobilus.services.managementservice.ManagementService;
import pl.com.mobilelabs.mobilus.view.activity.BackButtonHeaderActivity;

public class UserListSettingsActivity extends BackButtonHeaderActivity {
  private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      public void onReceive(Context param1Context, Intent param1Intent) {
        ArrayList<? extends MobilusModel.User> arrayList;
        if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.newConfigurationReceived"))
          if (param1Intent.getBooleanExtra("ManagementService.Intents.newConfigurationReceivedIsAdmin", false) && UserListSettingsActivity.this.managementBinder.isWorkingInLocalMode())
            UserListSettingsActivity.this.managementBinder.startReadingUsersList();  
        if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.usersListReceived")) {
          UserListSettingsActivity.this.users.clear();
          if (param1Intent.hasExtra("ManagementService.Intents.usersListReceivedUsersList")) {
            arrayList = (ArrayList)param1Intent.getSerializableExtra("ManagementService.Intents.usersListReceivedUsersList");
            UserListSettingsActivity.this.users.addAll(arrayList);
            UserListSettingsActivity.this.mAdapter.notifyDataSetChanged();
          } 
        } 
        if (param1Intent.getAction().equalsIgnoreCase("ManagementService.Intents.updateUserResponseReceived")) {
          switch (param1Intent.getIntExtra("ManagementService.Intents.updateUserResponseReceivedOperationStatus", 0)) {
            default:
              return;
            case 2:
              Toast.makeText((Context)arrayList, 2131624139, 0).show();
            case 1:
              Toast.makeText((Context)arrayList, 2131624103, 0).show();
            case 0:
              break;
          } 
          Toast.makeText((Context)arrayList, 2131624141, 0).show();
        } 
      }
    };
  
  private UserAdapter mAdapter;
  
  protected ArrayList<MobilusModel.User> users = new ArrayList<>();
  
  private void showRemoveUserDialog(final MobilusModel.User user) {
    AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
    View view = getLayoutInflater().inflate(2131492899, null);
    LinearLayout linearLayout1 = (LinearLayout)view.findViewById(2131296270);
    LinearLayout linearLayout2 = (LinearLayout)view.findViewById(2131296272);
    ((TextView)view.findViewById(2131296273)).setText(user.getLogin());
    final TextInputLayout passwordLayout = (TextInputLayout)view.findViewById(2131296268);
    final TextInputEditText passwordTextEdit = (TextInputEditText)view.findViewById(2131296269);
    textInputEditText.addTextChangedListener(new TextWatcher() {
          public void afterTextChanged(Editable param1Editable) {}
          
          public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
          
          public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            if (passwordLayout.getError() != null && UserListSettingsActivity.this.managementBinder.isPasswordValid(passwordTextEdit.getText().toString()))
              passwordLayout.setError(null); 
          }
        });
    builder.setView(view);
    final AlertDialog dialog = builder.create();
    linearLayout1.setOnClickListener(new View.OnClickListener() {
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    linearLayout2.setOnClickListener(new View.OnClickListener() {
          public void onClick(View param1View) {
            if (!UserListSettingsActivity.this.managementBinder.isPasswordValid(passwordTextEdit.getText().toString())) {
              passwordLayout.setError(UserListSettingsActivity.this.getString(2131624226));
            } else {
              UserListSettingsActivity.this.managementBinder.sendRemoveUserRequest(user, passwordTextEdit.getText().toString());
              dialog.dismiss();
            } 
          }
        });
    alertDialog.show();
  }
  
  @OnClick({2131296577})
  protected void fabClicked() {
    Intent intent = new Intent(getApplicationContext(), UserDetailsSettingsActivity.class);
    intent.putExtra("UserDetailsSettingsActivity.currentUserId", this.managementBinder.getUserId());
    intent.putExtra("UserDetailsSettingsActivity.usersList", this.users);
    startActivity(intent);
  }
  
  protected void managementServiceConnected() {
    super.managementServiceConnected();
    this.managementBinder.startReadingUsersList();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    initializeActivity(2131492968);
    configureView(2131558767, 2131624209, 2131624046);
    ButterKnife.bind((Activity)this);
    RecyclerView recyclerView = (RecyclerView)getSettingsView().findViewById(2131296711);
    recyclerView.setLayoutManager((RecyclerView.LayoutManager)new LinearLayoutManager((Context)this));
    this.mAdapter = new UserAdapter();
    recyclerView.setAdapter(this.mAdapter);
    IntentFilter intentFilter = new IntentFilter("ManagementService.Intents.newConfigurationReceived");
    intentFilter.addAction("ManagementService.Intents.usersListReceived");
    intentFilter.addAction("ManagementService.Intents.updateUserResponseReceived");
    LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.broadcastReceiver, intentFilter);
  }
  
  protected void onDestroy() {
    LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(this.broadcastReceiver);
    super.onDestroy();
  }
  
  private class UserAdapter extends RecyclerView.Adapter<ViewHolder> {
    private int selectedItem = -1;
    
    public int getItemCount() {
      return UserListSettingsActivity.this.users.size();
    }
    
    public void onBindViewHolder(@NonNull UserListSettingsActivity.ViewHolder param1ViewHolder, final int position) {
      int i = this.selectedItem;
      boolean bool = false;
      if (i == position) {
        j = 1;
      } else {
        j = 0;
      } 
      final MobilusModel.User user = UserListSettingsActivity.this.users.get(position);
      ImageView imageView2 = param1ViewHolder.userIcon;
      if (user.getAdmin()) {
        if (j) {
          i = 2131558405;
        } else {
          i = 2131558404;
        } 
      } else if (j) {
        i = 2131558788;
      } else {
        i = 2131558787;
      } 
      imageView2.setImageResource(i);
      param1ViewHolder.userName.setText(user.getLogin());
      Resources resources = UserListSettingsActivity.this.getResources();
      int k = 2131099705;
      if (j) {
        i = 2131099705;
      } else {
        i = 2131099696;
      } 
      int m = resources.getColor(i);
      resources = UserListSettingsActivity.this.getResources();
      i = k;
      if (j)
        i = 2131099697; 
      int j = resources.getColor(i);
      param1ViewHolder.userName.setTextColor(m);
      ImageView imageView1 = param1ViewHolder.trashButton;
      if (user.getId() == UserListSettingsActivity.this.managementBinder.getUserId()) {
        i = 8;
      } else {
        i = 0;
      } 
      imageView1.setVisibility(i);
      param1ViewHolder.trashButton.setTag(user);
      if (user.getId() != UserListSettingsActivity.this.managementBinder.getUserId())
        param1ViewHolder.trashButton.setOnClickListener(new View.OnClickListener() {
              public void onClick(View param2View) {
                UserListSettingsActivity.this.showRemoveUserDialog(user);
              }
            }); 
      View view = param1ViewHolder.divider;
      if (position + 1 < UserListSettingsActivity.this.users.size()) {
        i = bool;
      } else {
        i = 8;
      } 
      view.setVisibility(i);
      param1ViewHolder.viewItem.setBackgroundColor(j);
      param1ViewHolder.viewItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param2View) {
              int i = UserListSettingsActivity.UserAdapter.this.selectedItem;
              UserListSettingsActivity.UserAdapter.access$602(UserListSettingsActivity.UserAdapter.this, position);
              if (i >= 0)
                UserListSettingsActivity.UserAdapter.this.notifyItemChanged(i); 
              UserListSettingsActivity.UserAdapter.this.notifyItemChanged(UserListSettingsActivity.UserAdapter.this.selectedItem);
              Intent intent = new Intent(UserListSettingsActivity.this.getApplicationContext(), UserDetailsSettingsActivity.class);
              intent.putExtra("UserDetailsSettingsActivity.currentUserId", UserListSettingsActivity.this.managementBinder.getUserId());
              intent.putExtra("UserDetailsSettingsActivity.usersList", UserListSettingsActivity.this.users);
              intent.putExtra("UserDetailsSettingsActivity.user", ((MobilusModel.User)UserListSettingsActivity.this.users.get(position)).toByteArray());
              UserListSettingsActivity.this.startActivity(intent);
            }
          });
    }
    
    @NonNull
    public UserListSettingsActivity.ViewHolder onCreateViewHolder(@NonNull ViewGroup param1ViewGroup, int param1Int) {
      View view = LayoutInflater.from(param1ViewGroup.getContext()).inflate(2131492967, param1ViewGroup, false);
      return new UserListSettingsActivity.ViewHolder(view);
    }
  }
  
  class null implements View.OnClickListener {
    public void onClick(View param1View) {
      UserListSettingsActivity.this.showRemoveUserDialog(user);
    }
  }
  
  class null implements View.OnClickListener {
    public void onClick(View param1View) {
      int i = this.this$1.selectedItem;
      UserListSettingsActivity.UserAdapter.access$602(this.this$1, position);
      if (i >= 0)
        this.this$1.notifyItemChanged(i); 
      this.this$1.notifyItemChanged(this.this$1.selectedItem);
      Intent intent = new Intent(UserListSettingsActivity.this.getApplicationContext(), UserDetailsSettingsActivity.class);
      intent.putExtra("UserDetailsSettingsActivity.currentUserId", UserListSettingsActivity.this.managementBinder.getUserId());
      intent.putExtra("UserDetailsSettingsActivity.usersList", UserListSettingsActivity.this.users);
      intent.putExtra("UserDetailsSettingsActivity.user", ((MobilusModel.User)UserListSettingsActivity.this.users.get(position)).toByteArray());
      UserListSettingsActivity.this.startActivity(intent);
    }
  }
  
  private class ViewHolder extends RecyclerView.ViewHolder {
    public View divider;
    
    public ImageView trashButton;
    
    public ImageView userIcon;
    
    public TextView userName;
    
    public View viewItem;
    
    public ViewHolder(View param1View) {
      super(param1View);
      param1View.setTag(this);
      this.userIcon = (ImageView)param1View.findViewById(2131296314);
      this.userName = (TextView)param1View.findViewById(2131296315);
      this.trashButton = (ImageView)param1View.findViewById(2131296317);
      this.divider = param1View.findViewById(2131296313);
      this.viewItem = param1View;
    }
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\settings\UserListSettingsActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */