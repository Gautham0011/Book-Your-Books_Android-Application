package com.example.byb.HomeModel;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.byb.Admin.admin_printout_document;
import com.example.byb.R;
import com.example.byb.ViewHolder.testviewholder;
import com.example.byb.model.down_model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class testadapter extends RecyclerView.Adapter<testviewholder> {
    admin_printout_document Admin_printout_document;
    ArrayList<down_model> downModels;
    public testadapter(admin_printout_document Admin_printout_document,ArrayList<down_model>downModels){
        this.Admin_printout_document=Admin_printout_document;
        this.downModels=downModels;

    }

    @NonNull
    @NotNull
    @Override
    public testviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(Admin_printout_document.getBaseContext());
        View view = layoutInflater.inflate(R.layout.admin_document_item_list, null, false);

        return new testviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull testviewholder holder, int position) {
        testviewholder.usn.setText(" USN = " +downModels.get(position).getUsn());
        testviewholder.description.setText(downModels.get(position).getDescription());
        testviewholder.name.setText(" Name = " +downModels.get(position).getName());
        testviewholder.copies.setText(" Number of Copies = " +downModels.get(position).getNumberofcopies());
        testviewholder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFile(testviewholder.name.getContext(),downModels.get(position).getName(),".pdf",DIRECTORY_DOWNLOADS,downModels.get(position).getUrl());
            }
        });
    }
    public void downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {

        DownloadManager downloadmanager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        downloadmanager.enqueue(request);
    }

    @Override
    public int getItemCount() {
        return downModels.size();
    }
}
