package pl.com.mobilelabs.mobilus.view.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class BackButtonHeaderActivity extends BaseActivity {
  protected FrameLayout frame;
  
  protected ImageView iconImage;
  
  protected TextView iconText;
  
  protected View settingsView;
  
  protected TextView titleText;
  
  protected void backSelected() {
    finish();
  }
  
  protected void configureView(int paramInt1, int paramInt2, int paramInt3) {
    this.iconImage.setImageResource(paramInt1);
    this.iconText.setText(paramInt2);
    this.titleText.setText(paramInt3);
  }
  
  public View getSettingsView() {
    return this.settingsView;
  }
  
  protected void initializeActivity(int paramInt) {
    this.settingsView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(paramInt, null);
    this.frame.addView(this.settingsView);
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131492903);
    this.iconImage = (ImageView)findViewById(2131296463);
    this.iconText = (TextView)findViewById(2131296465);
    this.titleText = (TextView)findViewById(2131296466);
    this.frame = (FrameLayout)findViewById(2131296462);
    findViewById(2131296461).setOnClickListener(new View.OnClickListener() {
          public void onClick(View param1View) {
            BackButtonHeaderActivity.this.backSelected();
          }
        });
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\BackButtonHeaderActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */