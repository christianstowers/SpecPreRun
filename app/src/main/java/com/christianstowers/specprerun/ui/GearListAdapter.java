package com.christianstowers.specprerun.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.christianstowers.specprerun.R;
import com.christianstowers.specprerun.data.model.GearModel;

import java.util.ArrayList;

public class GearListAdapter extends RecyclerView.Adapter<GearListAdapter.MyViewHolder> {

    private ArrayList<GearModel> mGearList;

    public GearListAdapter(ArrayList<GearModel> gearList) {
        mGearList = gearList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_gear, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //TODO : set mGearImg image
        holder.mGearName.setText(mGearList.get(position).getName());
        holder.mGearDesc.setText(mGearList.get(position).getDesc());
        //TODO : optional - set mGearOrder number
    }

    @Override
    public int getItemCount() {
        return mGearList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mGearImg;
        private TextView mGearName, mGearDesc;
//        private TextView mGearOrder;

        public MyViewHolder(View view) {
            super(view);

            mGearImg = (ImageView)view.findViewById(R.id.gear_img);
            mGearName = (TextView)view.findViewById(R.id.gear_name);
            mGearDesc = (TextView)view.findViewById(R.id.gear_desc);
            // TODO : convert order integer to string and set textview
//            mGearOrder = (TextView)view.findViewById(R.id.gear_order);
        }
    }
}
