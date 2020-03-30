package com.tai06.dothetai.appmarketphone;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SmartphoneAdapter extends RecyclerView.Adapter<SmartphoneAdapter.ViewHolder> {

    private Context context;
    private List<SmartPhone> list;

    public SmartphoneAdapter(Context context, List<SmartPhone> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SmartphoneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view =inflater.inflate(R.layout.row_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SmartphoneAdapter.ViewHolder holder, int position) {
        SmartPhone smartPhone =list.get(position);
        Picasso.get().load(smartPhone.getImages()).into(holder.img_phone);
        holder.lblName.setText(smartPhone.getName());
        holder.lblPrice.setText(String.valueOf(smartPhone.getPrice()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_phone;
        TextView lblName,lblPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_phone = (ImageView) itemView.findViewById(R.id.image_view);
            lblName = (TextView) itemView.findViewById(R.id.text_view);
            lblPrice = (TextView) itemView.findViewById(R.id.text_money);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SmartPhone smartPhone = list.get(getAdapterPosition());
                    ((MainActivity1)context).showPhone(smartPhone);
                }
            });
        }
    }
}
