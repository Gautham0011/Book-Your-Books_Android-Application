package com.example.byb.HomeModel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.byb.Admin.AdminChatActivity;
import com.example.byb.R;
import com.example.byb.model.users;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminmsgAdapter extends RecyclerView.Adapter<AdminmsgAdapter.ViewHolder> {
    Context ChatHomeAdmin;
    ArrayList<users> adminArrayList;
    private ImageView imageView;

    public AdminmsgAdapter(com.example.byb.Admin.ChatHomeAdmin ChatHomeAdmin , ArrayList<users> adminArrayList) {
        this.ChatHomeAdmin=ChatHomeAdmin;
        this.adminArrayList=adminArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(ChatHomeAdmin).inflate(R.layout.user_chat_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        users Users = adminArrayList.get(position);
        holder.user_name.setText(Users.getName());
        holder.user_status.setText(Users.getStatus());
        Picasso.get().load(Users.getImage()).into(holder.profile_image_chat);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChatHomeAdmin, AdminChatActivity.class);
                intent.putExtra("Name",Users.getName());
                intent.putExtra("Receiver Image",Users.getImage());
                intent.putExtra("UID",Users.getUsn());
                ChatHomeAdmin.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return adminArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profile_image_chat;
        TextView user_name;
        TextView user_status;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            profile_image_chat = itemView.findViewById(R.id.profile_image_chat);
            user_name = itemView.findViewById(R.id.user_name);
            user_status = itemView.findViewById(R.id.user_status);

        }
    }
}
