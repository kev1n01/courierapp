package desaroollo.udh.goflyy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ImageView imgss = findViewById(R.id.imgss);
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreenActivity.this,
                    LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }, 1000);
    }
}