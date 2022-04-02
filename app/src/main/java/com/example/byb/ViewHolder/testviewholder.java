package com.example.byb.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.byb.R;

import org.jetbrains.annotations.NotNull;

import java.text.BreakIterator;

public class testviewholder extends RecyclerView.ViewHolder {
    public static TextView usn;
    public static TextView name;
    public static TextView copies;
    public static TextView link;
    public static TextView description;
    public static Button download;

    public testviewholder(@NonNull @NotNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.document_name);
        usn= itemView.findViewById(R.id.document_link_usn);
        copies = itemView.findViewById(R.id.document_number_of_copies);
        //link = itemView.findViewById(R.id.document_link);
        description=itemView.findViewById(R.id.document_link_description);
        download=itemView.findViewById(R.id.document_download);
    }
}
