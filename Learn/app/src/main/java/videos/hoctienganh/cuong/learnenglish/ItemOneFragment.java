package videos.hoctienganh.cuong.learnenglish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;
import android.support.v7.app.AlertDialog;

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
        LinearLayout layout1 = (LinearLayout)view.findViewById(R.id.linearAbout);
        LinearLayout layout2 = (LinearLayout)view.findViewById(R.id.linearShare);
        layout1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(R.layout.about_us_dialog);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(R.layout.dialog_share);
                AlertDialog dialog = builder.create();
                dialog.show();
                ImageView view1 = (ImageView)dialog.findViewById(R.id.fbdialog);
                ImageView view2 = (ImageView)dialog.findViewById(R.id.twitterdialog);
                view1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), Facebook_Main_Activity.class);
                        startActivity(intent);
                    }
                });
                view2.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), Google_Plus_Main.class);
                        startActivity(intent);
                    }
                });
            }
        });
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