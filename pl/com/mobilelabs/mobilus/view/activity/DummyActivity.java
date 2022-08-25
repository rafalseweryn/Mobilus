package pl.com.mobilelabs.mobilus.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class DummyActivity extends AppCompatActivity {
  protected void onCreate(@Nullable Bundle paramBundle) {
    super.onCreate(paramBundle);
    (new Handler()).postDelayed(new Runnable() {
          public void run() {
            DummyActivity.this.finish();
          }
        },  500L);
  }
}


/* Location:              C:\Users\rafal\Downloads\Mobius_jar.jar!\pl\com\mobilelabs\mobilus\view\activity\DummyActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */