
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

import com.example.byb.ChatActivity;
import com.example.byb.R;
import com.example.byb.model.users;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

    public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
        Context ChatHomeActivity;
        ArrayList<users> usersArrayList;
        private ImageView imageView;

        public UserAdapter(com.example.byb.ChatHomeActivity ChatHomeActivity, ArrayList<users> usersArrayList) {
            this.ChatHomeActivity=ChatHomeActivity;
            this.usersArrayList=usersArrayList;
        }

        @NonNull
        @NotNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(ChatHomeActivity).inflate(R.layout.user_chat_row,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
            users Users = usersArrayList.get(position);

            holder.user_name.setText(Users.getName());
            holder.user_status.setText(Users.getStatus());
            Picasso.get().load(Users.getImage()).into(holder.profile_image_chat);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(ChatHomeActivity, ChatActivity.class);
                    intent.putExtra("Name",Users.getName());
                    intent.putExtra("Receiver Image",Users.getImage());
                    intent.putExtra("UID",Users.getUsn());
                    ChatHomeActivity.startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return usersArrayList.size();
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

