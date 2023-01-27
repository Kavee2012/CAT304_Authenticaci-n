package com.example.auntentication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends FirebaseRecyclerAdapter<Credentials,MyAdapter.myViewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MyAdapter(@NonNull FirebaseRecyclerOptions<Credentials> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Credentials model) {

        holder.id.setText(model.id);
        holder.password.setText(model.password);
        holder.platform.setText(model.platform);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_box,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{


        TextView id, password, platform;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);


            id = (TextView) itemView.findViewById(R.id.tv_id);
            password = (TextView) itemView.findViewById(R.id.tv_password);
            platform = (TextView) itemView.findViewById(R.id.tv_platform);
        }
    }


}
