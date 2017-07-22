package com.example.cuong.learnenglish;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.cuong.hocquavideo.Main_Hoc_Qua_Youtube;
import com.example.cuong.nghenhac.Main_Nghe_Nhac_Youtube;

public class ItemHomeFragment extends Fragment {

    public static ItemHomeFragment newInstance() {
        ItemHomeFragment fragment = new ItemHomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_home, container, false);
        TextView textView = (TextView)view.findViewById(R.id.tetView1);
        LinearLayout linearLayout1 = (LinearLayout)view.findViewById(R.id.ln1);
        LinearLayout linearLayout2 = (LinearLayout)view.findViewById(R.id.ln2);
        LinearLayout linearLayout3 = (LinearLayout)view.findViewById(R.id.ln3);
        LinearLayout linearLayout4 = (LinearLayout)view.findViewById(R.id.ln4);

        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(R.layout.exit_app_dialog);
                final AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();
                ImageView view1 = (ImageView)dialog.findViewById(R.id.im1);
                ImageView view2 = (ImageView)dialog.findViewById(R.id.im2);

                view1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });

                view2.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        linearLayout1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Main_Hoc_Qua_Youtube.class);
                startActivity(intent);
            }
        });

        linearLayout3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });

        linearLayout4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(R.layout.chon__cn__nghe__nhac);
                final AlertDialog dialog = builder.create();
                dialog.show();
                Button button1 = (Button) dialog.findViewById(R.id.btn1);
                Button button2 = (Button) dialog.findViewById(R.id.btn2);
                button1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
/*                        Intent intent = new Intent(getActivity(), Main_Nghe_Nhac_Online.class);
                        startActivity(intent);*/
                    }
                });

                button2.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), Main_Nghe_Nhac_Youtube.class);
                        startActivity(intent);
                    }
                });
            }
        });
        return view;
    }
}