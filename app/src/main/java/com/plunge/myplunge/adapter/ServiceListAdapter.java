package com.plunge.myplunge.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.plunge.myplunge.R;
import com.plunge.myplunge.model.Datum;

import java.util.List;


public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.ViewHolder>{
    Context context;
    Activity mActivity;
    List<Datum> mServiceList;
    public ServiceListAdapter(Activity mActivity, Context context, List<Datum> mServiceList ) {
        this.context = context;
        this.mActivity = mActivity;

        this.mServiceList=mServiceList;
    }

    @Override
    public ServiceListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("adapter", "onCreateViewHolder called");
        View view = LayoutInflater.from(context).inflate(R.layout.available_list, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ServiceListAdapter.ViewHolder holder, final int position) {

        holder.mName.setText(mServiceList.get(position).getArthiyaName());
        holder.mTime.setText(mServiceList.get(position).getCreatedAt());
        holder.mArtist.setText(mServiceList.get(position).getInventoryType());

    }

    @Override
    public int getItemCount() {
        return mServiceList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mName,mTime,mArtist;
        private ImageView mImage;
        private Button mBook;
        public ViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.title);
            mTime = (TextView) itemView.findViewById(R.id.time);
            mArtist = (TextView) itemView.findViewById(R.id.artist);

            mImage = (ImageView) itemView.findViewById(R.id.thumbnail);
            mBook = (Button) itemView.findViewById(R.id.book);
        }
    }
 }
