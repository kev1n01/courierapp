package desaroollo.udh.goflyy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText tvemail, tvpassword;
    private Button btnlogin;
    private TextView tvloginerror;
    private String userId;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        validateSesion();
        btnlogin.setOnClickListener(v -> userLogin());
    }

    private void init(){
        tvemail = findViewById(R.id.email);
        tvpassword = findViewById(R.id.password);
        tvloginerror = findViewById(R.id.tvloginerror);
        btnlogin = findViewById(R.id.btnlogin);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        preferences = getSharedPreferences("Preferences",MODE_PRIVATE);
    }

    private void userLogin() {
        String email = tvemail.getText().toString();
        String password = tvpassword.getText().toString();

        boolean validatedFieldsUI = validateFields(email,password);
        if (validatedFieldsUI){
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    userId = auth.getCurrentUser().getUid();
                    db.collection("users").document(userId).get().addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()){
                            String namedb = documentSnapshot.getString("Name");
                            String dnidb = documentSnapshot.getString("Dni");
                            String emaildb = documentSnapshot.getString("Email");
                            String phonedb = documentSnapshot.getString("Phone");
                            String passdb = documentSnapshot.getString("Password");
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("uid",userId);
                            editor.putString("name",namedb);
                            editor.putString("dni",dnidb);
                            editor.putString("phone",phonedb);
                            editor.putString("email",emaildb);
                            editor.putString("password",passdb);
                            editor.apply();
                            Log.w("dbcollection","uid:"+userId+"\n"+"name:"+namedb+"\n"+"dni:"+dnidb+"\n"+"phone:"+phonedb+"\n"+"email:"+emaildb);
                            Toast.makeText(LoginActivity.this, "Bienvenido a GoFlyy "+namedb, Toast.LENGTH_SHORT).show();
                        }
                    });
                    goMainActivity();
                }else{
                    Log.w("TAG","Error:",task.getException());
                    tvloginerror.setText("Usuario y/o constraseña incorrectos");
                }
            });
        }
    }
    private void goMainActivity(){
        startActivity( new Intent(LoginActivity.this,MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }
    private boolean validateFields(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            tvemail.setError("Ingrese su correo electronico");
            tvemail.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            tvpassword.setError("Ingrese su contraseña");
            tvpassword.requestFocus();
            return false;
        }
        return true;
    }

    private void validateSesion(){
        String uid = preferences.getString("uid", null);
        if (uid != null){
            goMainActivity();
        }
    }

    private void userRegister() {
        String email = tvemail.getText().toString();
        String password = tvpassword.getText().toString();
        String name = "arnold";
        String dni = "76370345";
        String phone = "933865935";
        int type = 1;

        if (TextUtils.isEmpty(email)){
            tvemail.setError("Ingrese un correo");
            tvemail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            tvpassword.setError("Ingrese una contraseña");
            tvpassword.requestFocus();
        }else {
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    userId = auth.getCurrentUser().getUid();
                    DocumentReference documentReference = db.collection("users").document(userId);
                    Map<String,Object> user = new HashMap<>();
                    user.put("Name",name);
                    user.put("Dni",dni);
                    user.put("Phone",phone);
                    user.put("Password",password);
                    user.put("Email",email);
                    user.put("Type",type);
                    documentReference.set(user).addOnSuccessListener(unused -> Log.d("TAG","success: Datos registrados"+userId));
                    Toast.makeText(LoginActivity.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Usuario no registrado"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}