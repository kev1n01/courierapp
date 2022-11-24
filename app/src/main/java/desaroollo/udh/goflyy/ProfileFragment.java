package desaroollo.udh.goflyy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    SharedPreferences preferences;
    TextView tvnameprofile, tvemailprofile, tvphoneprofile, tvpasswordprofile, tvdniprofile;
    String name, email, phone, password, dni;
    public ProfileFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = this.getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        name = preferences.getString("name",null);
        email = preferences.getString("email",null);
        phone = preferences.getString("phone",null);
        password = preferences.getString("password",null);
        dni = preferences.getString("dni",null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        tvnameprofile = v.findViewById(R.id.tvnameprofile);
        tvemailprofile = v.findViewById(R.id.tvemailprofile);
        tvphoneprofile = v.findViewById(R.id.tvphoneprofile);
        tvpasswordprofile = v.findViewById(R.id.tvpasswordprofile);
        tvdniprofile = v.findViewById(R.id.tvdniprofile);

        tvnameprofile.setText(name);
        tvemailprofile.setText(email);
        tvphoneprofile.setText(phone);
        tvpasswordprofile.setText(password);
        tvdniprofile.setText(dni);
        return v;
    }
}