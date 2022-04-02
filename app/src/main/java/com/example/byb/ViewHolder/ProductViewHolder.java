package com.example.byb.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.byb.Interface.ItemClickListener;
import com.example.byb.R;

import org.jetbrains.annotations.NotNull;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{  public TextView productname,productdescription,productprice;
   public ImageView productimage;
   public ItemClickListener listener;

    public ProductViewHolder(@NonNull  View itemView) {
        super(itemView);
        productimage=(ImageView)itemView.findViewById(R.id.product_image);
        productname=(TextView) itemView.findViewById(R.id.product_name);
        productdescription=(TextView) itemView.findViewById(R.id.product_description);
        productprice=(TextView)itemView.findViewById(R.id.product_price);

    }
    public  void setItemClickListener(ItemClickListener listener){
        this.listener=listener;


    }

    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition(),false);
    }
}
