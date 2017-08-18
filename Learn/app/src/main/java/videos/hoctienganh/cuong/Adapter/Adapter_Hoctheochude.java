package videos.hoctienganh.cuong.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import videos.hoctienganh.cuong.Model.Model_hocTheochude;
import videos.hoctienganh.cuong.learnenglish.R;
import videos.hoctienganh.cuong.timkiemtumoi.BaiHocTheoGoiY;
import videos.hoctienganh.cuong.timkiemtumoi.Main_CauTL;

/**
 * Created by Vaio on 2017-08-11.
 */

public class Adapter_Hoctheochude extends RecyclerView.Adapter<Adapter_Hoctheochude.ItemView> {
        Context context;
        List<Model_hocTheochude> listtheoChude;

    public Adapter_Hoctheochude(Context context, List<Model_hocTheochude> listtheoChude) {
        this.context = context;
        this.listtheoChude = listtheoChude;
    }

    @Override
    public ItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_hoctheochude,parent,false);
        return new ItemView(v);
    }

    @Override
    public void onBindViewHolder(ItemView holder, int position) {
        Model_hocTheochude model_hocTheochude =listtheoChude.get(position);
        holder.txtQuess.setText(model_hocTheochude.getCauHoi());
        holder.tnameQues.setText("Question :" +model_hocTheochude.getIdChude());
        holder.imgHoctheoChude.setImageResource(R.drawable.quess);

    }

    @Override
    public int getItemCount() {
        return listtheoChude.size();
    }

    public class ItemView extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardhoctheochude;
        ImageView imgHoctheoChude;
        TextView txtQuess,tnameQues;
        public ItemView(View itemView) {
            super(itemView);
            cardhoctheochude = itemView.findViewById(R.id.card_hoctheoChude);
            imgHoctheoChude = itemView.findViewById(R.id.igVQuess);
            txtQuess = itemView.findViewById(R.id.txt_cauhoi);
            cardhoctheochude.setOnClickListener(this);
            tnameQues =itemView.findViewById(R.id.nameQuess);
        }

        @Override
            public void onClick(View view) {

                switch (view.getId()){
                case R.id.card_hoctheoChude:

                    Toast.makeText(context,listtheoChude.get(getAdapterPosition()).getNameQuest(), Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("IdQuest",listtheoChude.get(getAdapterPosition()).getNameQuest());
                    Intent i = new Intent(context, Main_CauTL.class);
                    i.putExtras(bundle);
                    context.startActivity(i);
                }
            }
        }


}

