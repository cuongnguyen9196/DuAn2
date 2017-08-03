package videos.hoctienganh.cuong.customlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import java.util.List;
import videos.hoctienganh.cuong.learnenglish.R;
import videos.hoctienganh.cuong.network.NetworkController;
import videos.hoctienganh.cuong.objects.MusicOnline;


public class Custom_List_Music_Adapter extends RecyclerView.Adapter<Custom_List_Music_Adapter.MyViewHolder> {

    public List<MusicOnline> musicList;
    Context context;
    LayoutInflater layoutInflater;

    public Custom_List_Music_Adapter(Context context, List<MusicOnline> musicList) {
        this.context = context;
        this.musicList = musicList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Custom_List_Music_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = layoutInflater.inflate(R.layout.item_nghe_online, parent, false);
        return new Custom_List_Music_Adapter.MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(Custom_List_Music_Adapter.MyViewHolder holder, int position) {
        MusicOnline music = musicList.get(position);
        holder.tieuDe1.setText(music.getSongName());
        holder.thoiLuong.setText("Duration : " + music.getDuration());
        holder.casi.setText(music.getCaSiName());
        holder.networkImageView.setImageUrl(music.getLinkImages(), NetworkController.getInstance(context).getImageLoader());
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tieuDe1, thoiLuong, casi;
        NetworkImageView networkImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tieuDe1 = (TextView) itemView.findViewById(R.id.person_name);
            thoiLuong = (TextView) itemView.findViewById(R.id.music_duration);
            casi = (TextView) itemView.findViewById(R.id.person_age);
            networkImageView = (NetworkImageView) itemView.findViewById(R.id.person_photo);
        }
    }
}
