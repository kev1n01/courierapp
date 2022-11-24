package desaroollo.udh.goflyy.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import desaroollo.udh.goflyy.R;
import desaroollo.udh.goflyy.models.DetailDeliveryModel;

public class AdapterDetailDelivery extends FirestoreRecyclerAdapter<DetailDeliveryModel, AdapterDetailDelivery.myviewHolder> {
    public AdapterDetailDelivery(FirestoreRecyclerOptions<DetailDeliveryModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewHolder holder, int position, @NonNull DetailDeliveryModel model) {
        holder.tvdesproduct.setText(model.getDescription());
        holder.tvquantityproduct.setText(String.valueOf(model.getQuantity()));
        holder.tvpriceproduct.setText(String.valueOf("S/. "+model.getPrice()));
    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_table_detail, parent,false);
        return new myviewHolder(view);
    }

    public class myviewHolder extends RecyclerView.ViewHolder{
        TextView tvdesproduct, tvquantityproduct, tvpriceproduct;

        public myviewHolder(@NonNull View itemView) {
            super(itemView);
            tvdesproduct = itemView.findViewById(R.id.tvdesproduct);
            tvquantityproduct = itemView.findViewById(R.id.tvquantityproduct);
            tvpriceproduct = itemView.findViewById(R.id.tvpriceproduct);
        }
    }
}
