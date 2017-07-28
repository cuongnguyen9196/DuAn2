package videos.hoctienganh.cuong.learnenglish;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

public class ItemOneFragment extends Fragment {

    public static ItemOneFragment newInstance() {
        ItemOneFragment fragment = new ItemOneFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_item_one, container, false);
        RatingBar ratingBar = (RatingBar)view.findViewById(R.id.ratting);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int soSao = (int)v;
                Toast.makeText(getActivity(), "Bạn bình chọn " + soSao + " sao\n\t\t\t\tThank You", Toast.LENGTH_SHORT).show();
                ratingBar.setEnabled(false);
            }
        });
        return view;
    }
}