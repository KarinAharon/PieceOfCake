package com.jok.pieceofcake.customerSide;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jok.pieceofcake.R;
import com.jok.pieceofcake.Upload;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

    public class pastryAdapterCustomer extends RecyclerView.Adapter<pastryAdapterCustomer.ImageViewHolder> {
        private Context context;
        private List<Upload> uploads;

        public pastryAdapterCustomer(Context context, List<Upload> uploads){
            this.context=context;
            this.uploads=uploads;
        }

        @NonNull
        @Override
        public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
            return new ImageViewHolder(v);

        }

        @Override
        public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
            Upload uploadCurrent = uploads.get(position);
            Picasso.with(context)
                    .load(uploadCurrent.getImageURL())
                    .placeholder(R.mipmap.cake)
                    .fit()
                    .centerCrop()
                    .into(holder.imageView);

        }

        @Override
        public int getItemCount() {
            return uploads.size();
        }

        public class ImageViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;

            public ImageViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.pastryImage);
            }
    }

}
