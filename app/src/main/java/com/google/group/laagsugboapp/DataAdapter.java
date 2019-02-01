package com.google.group.laagsugboapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ImageViewHolder> {
    private Context mContext;
    private List<UserData> mUploads;

    public DataAdapter(Context context, List<UserData> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.data_upload, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        UserData uploadCurrent = mUploads.get(position);
        holder.placeTo.setText(uploadCurrent.getPlaceData());
        holder.addressTo.setText("Address: "+uploadCurrent.getAddressData());
        holder.descriptionTo.setText("Description: "+uploadCurrent.getDescriptionData());
        Picasso.get()
                .load(uploadCurrent.getImageUriData())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageViewTo);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView placeTo, addressTo, descriptionTo;
        public ImageView imageViewTo;

        public ImageViewHolder(View itemView) {
            super(itemView);

            placeTo = itemView.findViewById(R.id.placeOfPlace);
            addressTo = itemView.findViewById(R.id.addressOfPlace);
            descriptionTo = itemView.findViewById(R.id.descriptionOfPlace);
            imageViewTo = itemView.findViewById(R.id.imageOfPlace);
        }
    }
}