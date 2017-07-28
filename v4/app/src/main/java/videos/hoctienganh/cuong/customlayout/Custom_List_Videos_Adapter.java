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
import videos.hoctienganh.cuong.objects.English;

public class Custom_List_Videos_Adapter extends RecyclerView.Adapter<Custom_List_Videos_Adapter.MyViewHolder> {

    public List<English> musicList;
    Context context;
    LayoutInflater layoutInflater;

    public Custom_List_Videos_Adapter(Context context, List<English> musicList) {
        this.context = context;
        this.musicList = musicList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = layoutInflater.inflate(R.layout.item_youtube_swipe, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        English music = musicList.get(position);
        holder.tieuDe1.setText(music.getTitle());
        holder.thoiLuong.setText("Duration : " + music.getDuration() + " minutes");
        holder.networkImageView.setImageUrl(music.getThumbnailUrl(), NetworkController.getInstance(context).getImageLoader());
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
