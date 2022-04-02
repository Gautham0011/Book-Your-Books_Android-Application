package com.example.byb.HomeModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.byb.Prevalent.Prevalent;
import com.example.byb.R;
import com.example.byb.model.Messages;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.byb.ChatActivity.rImage;
import static com.example.byb.ChatActivity.sImage;

public class MessagesAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Messages> messagesArrayList;
    int ITEM_SEND=1;
    int ITEM_RECEIVE=2;

    public MessagesAdapter(Context context, ArrayList<Messages> messagesArrayList) {
        this.context = context;
        this.messagesArrayList = messagesArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        if(viewType== ITEM_SEND)
        {
            View view= LayoutInflater.from(context).inflate(R.layout.sender_layout_item,parent,false);
            return new SenderViewHolder(view);
        }else{
            View view=LayoutInflater.from(context).inflate(R.layout.receiver_layout_item,parent,false);
            return new ReceiverViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

        Messages messages=messagesArrayList.get(position);

        if(holder.getClass()==SenderViewHolder.class)
        {
            SenderViewHolder viewHolder=(SenderViewHolder)holder;

            viewHolder.txtmessage.setText(messages.getMessage());

//            Picasso.get().load(sImage).into(viewHolder.circleImageView);


        }else{
            ReceiverViewHolder viewHolder=(ReceiverViewHolder)holder;

            viewHolder.txtmessage.setText(messages.getMessage());

//            Picasso.get().load(rImage).into(viewHolder.circleImageView);

        }

    }

    @Override
    public int getItemCount() {
        return messagesArrayList.size();
    }

    @Override
    public int getItemViewType(int position){
        Messages messages=messagesArrayList.get(position);
        if(Prevalent.currentonlineusers.getUsn().equals(messages.getSenderId()))
        {
            return ITEM_SEND;

        }else{
            return ITEM_RECEIVE;
        }
    }
    class SenderViewHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        TextView txtmessage;

        public SenderViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            circleImageView=itemView.findViewById(R.id.profile_image);
            txtmessage=itemView.findViewById(R.id.txtMessages);
        }
    }

    class ReceiverViewHolder extends RecyclerView.ViewHolder{

        CircleImageView circleImageView;
        TextView txtmessage;

        public ReceiverViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            circleImageView=itemView.findViewById(R.id.profile_image);
            txtmessage=itemView.findViewById(R.id.txtMessages);
        }
    }
}

