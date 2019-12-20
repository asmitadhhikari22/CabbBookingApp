package com.batuhanozdamar;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class secondRecyclerAdapter extends RecyclerView.Adapter<secondRecyclerAdapter.MyViewHolder> {

    Context context;
    summaryScreen mainActivity;

    public secondRecyclerAdapter(Context context) {
        this.context = context;
        mainActivity = (summaryScreen) context;
    }

    public void modifyData(){
        notifyDataSetChanged();
        notifyItemRangeChanged(0, commons.data.size());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_item_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //BIND DATA
        final reservationInfo contact = commons.data.get(position);

        holder.tvName.setText(contact.getName());
        holder.tvAmount.setText(contact.getSurname()+"");
    }

    @Override
    public int getItemCount() {
        return commons.data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout layout;
        TextView tvName, tvAmount;
        ImageView img;

        MyViewHolder(View viewItem){
            super(viewItem);
            tvName = viewItem.findViewById(R.id.tvName);
            img = viewItem.findViewById(R.id.img);
            tvAmount = viewItem.findViewById(R.id.tvAmount);
            layout = viewItem.findViewById(R.id.constLayout);

            viewItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    commons.curentReservation = commons.data.get(getLayoutPosition());

                    commons.curentItemIndex = getLayoutPosition();

                    //Toast.makeText(context, "burda",Toast.LENGTH_LONG).show();
                    return false;
                }
            });
        }
    }
}
