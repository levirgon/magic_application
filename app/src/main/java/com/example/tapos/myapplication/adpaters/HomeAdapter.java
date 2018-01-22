package com.example.tapos.myapplication.adpaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tapos.myapplication.activity.MainActivity;
import com.example.tapos.myapplication.R;
import com.example.tapos.myapplication.models.Magic;

import java.util.List;

/**
 * Created by tapos on 1/21/18.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private Context mContext;
    private List<Magic> mMagicList;

    public void addALL(List<Magic> allMagics) {
        mMagicList = allMagics;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            ((MainActivity)mContext).showDetails(mMagicList.get(getAdapterPosition()));
        }
    }


    public HomeAdapter(Context mContext, List<Magic> magicList) {
        this.mContext = mContext;
        this.mMagicList = magicList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Magic magic = mMagicList.get(position);
        holder.title.setText(magic.getTitle());
        // loading magic cover using Glide library
    }

    @Override
    public int getItemCount() {
        return mMagicList.size();
    }

    public void addItems(List<Magic> borrowModelList) {
        this.mMagicList = borrowModelList;
        notifyDataSetChanged();
    }
}



