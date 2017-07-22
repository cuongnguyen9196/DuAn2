package com.example.cuong.nghenhac;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.example.cuong.learnenglish.NetworkController;
import com.example.cuong.learnenglish.R;

import java.util.List;

public class Custom_List_Youtube_MV extends RecyclerView.Adapter<Custom_List_Youtube_MV.MyViewHolder> {

    public List<YoutubeMV> musicList;
    Context context;
    LayoutInflater layoutInflater;

    public Custom_List_Youtube_MV(Context context, List<YoutubeMV> musicList) {
        this.context = context;
        this.musicList = musicList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Custom_List_Youtube_MV.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = layoutInflater.inflate(R.layout.item_youtube_swipe, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        YoutubeMV music = musicList.get(position);
        holder.tieuDe1.setText(music.getTenClip());
        holder.thoiLuong.setText("Duration : " + music.getThoigian());
        holder.networkImageView.setImageUrl(music.getImgURL(), NetworkController.getInstance(context).getImageLoader());
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tieuDe1, thoiLuong;
        NetworkImageView networkImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tieuDe1 = (TextView) itemView.findViewById(R.id.title_view);
            thoiLuong = (TextView) itemView.findViewById(R.id.content_view);
            networkImageView = (NetworkImageView) itemView.findViewById(R.id.thumbnail);
        }
    }
}
