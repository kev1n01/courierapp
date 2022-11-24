package desaroollo.udh.goflyy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import desaroollo.udh.goflyy.adapters.AdapterDelivery;
import desaroollo.udh.goflyy.models.ListDeliveryModel;


public class ListDeliveryFragment extends Fragment {
    RecyclerView recyclerView;
    SharedPreferences preferences;
    AdapterDelivery adapterDelivery;
    String UID;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = this.getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        UID = preferences.getString("uid",null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_delivery, container, false);
        recyclerView = view.findViewById(R.id.rLvistDelivery);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirestoreRecyclerOptions<ListDeliveryModel> options = new FirestoreRecyclerOptions.Builder<ListDeliveryModel>()
                .setQuery(FirebaseFirestore.getInstance().collection("deliveries")
                                .whereEqualTo("Personal",UID) , ListDeliveryModel.class)
                .build();
        adapterDelivery = new AdapterDelivery(options);
        recyclerView.setAdapter(adapterDelivery);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        adapterDelivery.startListening();
    }
    @Override
    public void onStop(){
        super.onStop();
        adapterDelivery.startListening();
    }
}