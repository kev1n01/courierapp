package desaroollo.udh.goflyy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import desaroollo.udh.goflyy.adapters.AdapterDetailDelivery;
import desaroollo.udh.goflyy.models.DetailDeliveryModel;


public class DetailDeliveryFragment extends Fragment {

    String address, client, code, phone,status,iddoc;
    Integer total;
    RecyclerView recyclerView;
    AdapterDetailDelivery adapterDetailDelivery;
    Spinner spinner;
    FirebaseFirestore db;
    public DetailDeliveryFragment(String address, String client, String phone, String code, String status, Integer total, String iddoc) {
        this.address = address;
        this.client = client;
        this.code = code;
        this.phone = phone;
        this.status = status;
        this.total = total;
        this.iddoc = iddoc;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView tvdbaddress, tvdbcode, tvdbstatus, tvdbtotal, tvdvclient, tvdvclientphone;
        View view =  inflater.inflate(R.layout.fragment_detail_delivery, container, false);
        tvdbaddress = view.findViewById(R.id.tvdbaddress);
        tvdbcode = view.findViewById(R.id.tvdbcode);
        tvdbstatus = view.findViewById(R.id.tvdbstatus);
        tvdbtotal = view.findViewById(R.id.tvdbtotal);
        tvdvclient = view.findViewById(R.id.tvdvclient);
        tvdvclientphone = view.findViewById(R.id.tvdvclientphone);

        spinner = view.findViewById(R.id.spinnerstatusdelivery);
        List<String> statuses = new ArrayList<>();
        int positionStatusDeliverySelected=0;
        statuses.add("no entregado");
        statuses.add("entregado");
        statuses.add("no recibido");
        statuses.add("direccion incorrecta");
        statuses.add("producto defectuoso");
        spinner.setAdapter(new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, statuses));
        switch(status){
            case "no entregado": positionStatusDeliverySelected = 0; break;
            case "entregado": positionStatusDeliverySelected = 1; break;
            case "no recibido": positionStatusDeliverySelected = 2; break;
            case "direccion incorreta": positionStatusDeliverySelected = 3; break;
            case "producto defectuoso": positionStatusDeliverySelected = 4; break;
        }
        spinner.setSelection(positionStatusDeliverySelected);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String statusselected = statuses.get(position);
                Map<String, Object> delivery = new HashMap<>();
                delivery.put("Status",statusselected);
                db.collection("deliveries").document(iddoc).update(delivery);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        recyclerView = view.findViewById(R.id.rvtableproducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tvdbaddress.setText(address);
        tvdbcode.setText(code);
        tvdbstatus.setText(status);
        tvdbtotal.setText(String.valueOf(total));
        tvdvclient.setText(client);
        tvdvclientphone.setText(phone);

        FirestoreRecyclerOptions<DetailDeliveryModel> data = new FirestoreRecyclerOptions.Builder<DetailDeliveryModel>()
                .setQuery(FirebaseFirestore.getInstance().collection("detail_delivery")
                        .whereEqualTo("delivery_code",code) , DetailDeliveryModel.class)
                .build();
        adapterDetailDelivery = new AdapterDetailDelivery(data);
        recyclerView.setAdapter(adapterDetailDelivery);
        return view;
    }

    public void onBackPressed() {
        AppCompatActivity activity = (AppCompatActivity) getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ListDeliveryFragment()).addToBackStack(null).commit();
    }

    @Override
    public void onStart(){
        super.onStart();
        adapterDetailDelivery.startListening();
    }
    @Override
    public void onStop(){
        super.onStop();
        adapterDetailDelivery.startListening();
    }
}