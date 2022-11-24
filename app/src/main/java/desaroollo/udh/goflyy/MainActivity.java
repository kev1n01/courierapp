package desaroollo.udh.goflyy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private BottomNavigationView navigation;
    private SharedPreferences preferences;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new ListDeliveryFragment()).commit();
        navigation.setOnItemSelectedListener(item -> {
            Fragment temp = null;
            switch (item.getItemId()) {
                case R.id.list : temp = new ListDeliveryFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,temp).commit();
                    break;
                case R.id.profile: temp = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,temp).commit();
                    break;
                case R.id.logout:
                    auth.signOut();
                    closeSession();
            }
            return true;
        });
    }

    private void init(){
        auth = FirebaseAuth.getInstance();
        navigation = findViewById(R.id.bottom_nav);
        preferences = getSharedPreferences("Preferences",MODE_PRIVATE);
    }

    private void closeSession(){
        preferences.edit().clear().apply();
        startActivity(new Intent(MainActivity.this,LoginActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }
}