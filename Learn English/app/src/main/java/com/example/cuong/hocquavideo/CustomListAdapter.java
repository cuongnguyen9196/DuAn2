package com.example.cuong.hocquavideo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.cuong.learnenglish.R;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<English> engItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<English> enItems) {
        this.activity = activity;
        this.engItems = enItems;
    }

    @Override
    public int getCount() {
        return engItems.size();
    }

    @Override
    public Object getItem(int location) {
        return engItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.youtube_item, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.imagess1);
        TextView title = (TextView) convertView.findViewById(R.id.text1);
        TextView timer = (TextView) convertView.findViewById(R.id.text2);
        English m = engItems.get(position);
        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
        title.setText(m.getTitle());
        timer.setText("Duration : " + String.valueOf(m.getDuration()) + " minutes");
        return convertView;
    }
}