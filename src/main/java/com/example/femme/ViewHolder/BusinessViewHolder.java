package com.example.femme.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.femme.ItemClickListner;
import com.example.femme.R;

public class BusinessViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtBusinessName, txtAddress, txtCategory;
    public ItemClickListner listner;




    public BusinessViewHolder(@NonNull View itemView)
    {
        super(itemView);

        txtBusinessName = (TextView) itemView.findViewById(R.id.business_name);
        txtAddress      = (TextView) itemView.findViewById(R.id.business_address);
        txtCategory     = (TextView) itemView.findViewById(R.id.business_category);



    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);

    }
}
