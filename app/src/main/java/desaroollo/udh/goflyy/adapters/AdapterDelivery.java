package desaroollo.udh.goflyy.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import desaroollo.udh.goflyy.DetailDeliveryFragment;
import desaroollo.udh.goflyy.R;
import desaroollo.udh.goflyy.models.ListDeliveryModel;

public class AdapterDelivery extends FirestoreRecyclerAdapter<ListDeliveryModel, AdapterDelivery.myviewHolder> {
    public AdapterDelivery(FirestoreRecyclerOptions<ListDeliveryModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewHolder holder, int position, @NonNull ListDeliveryModel model) {

        DocumentSnapshot delivery_doc = getSnapshots().getSnapshot(holder.getBindingAdapterPosition());
        String id = delivery_doc.getId();

        holder.tvcodedelivery.setText("# "+model.getCode());
        holder.tvtotal.setText(String.valueOf("S/. "+model.getTotal()));
        holder.btnstatus.setText(model.getStatus());
        if (model.getStatus().equals("entregado")){ holder.btnstatus.setBackgroundColor(Color.parseColor("#FEC706"));}
        if (model.getStatus().equals("no entregado")){ holder.btnstatus.setBackgroundColor(Color.parseColor("#FF018786"));}
        if (model.getStatus().equals("no recibido")){ holder.btnstatus.setBackgroundColor(Color.parseColor("#5A5A5A"));}
        if (model.getStatus().equals("direccion incorrecta")){ holder.btnstatus.setBackgroundColor(Color.parseColor("#FF902E"));}
        if (model.getStatus().equals("producto defectuoso")){ holder.btnstatus.setBackgroundColor(Color.parseColor("#E63838"));}

        holder.rlrowlist.setOnClickListener(v -> {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                    new DetailDeliveryFragment(model.getAddress(),model.getClient(),
                            model.getPhoneClient(),model.getCode(),model.getStatus(),model.getTotal(),id)).addToBackStack(null).commit();
        });
    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_delivery, parent,false);
        return new myviewHolder(view);
    }

    public class myviewHolder extends RecyclerView.ViewHolder{
        TextView tvcodedelivery, tvtotal;
        Button btnstatus;
        RelativeLayout rlrowlist;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);
            tvcodedelivery = itemView.findViewById(R.id.tvcodedelivery);
            tvtotal = itemView.findViewById(R.id.tvtotal);
            btnstatus = itemView.findViewById(R.id.btnstatus);
            btnstatus = itemView.findViewById(R.id.btnstatus);
            rlrowlist = itemView.findViewById(R.id.rlrowlist);
        }
    }
}
