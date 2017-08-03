package videos.hoctienganh.cuong.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import videos.hoctienganh.cuong.learnenglish.R;
import videos.hoctienganh.cuong.Model.Model_Lesson;
import videos.hoctienganh.cuong.timkiemtumoi.BaiHocTheoGoiY;

public class Adapter_Lesson extends RecyclerView.Adapter<Adapter_Lesson.Item> {
    List<Model_Lesson> list_item;
    Context context;

    public Adapter_Lesson(List<Model_Lesson> list_item, Context context) {
        this.list_item = list_item;
        this.context = context;
    }

    @Override
    public Adapter_Lesson.Item onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_lesson,parent,false);
        return new Item(v);
    }

    @Override
    public void onBindViewHolder(Adapter_Lesson.Item holder, int position) {
        Model_Lesson model_lesson = list_item.get(position);
        holder.tenbai.setText(model_lesson.getTenbai());
        holder.tomtat.setText(model_lesson.getTomtatND());
        String idlesson = model_lesson.getId();
    }

    @Override
    public int getItemCount() {
        return list_item.size();
    }

    public class Item extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        TextView tenbai,tomtat;
        public Item(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardV);
            tenbai = (TextView) itemView.findViewById(R.id.txt_tenbai);
            tomtat = (TextView) itemView.findViewById(R.id.txt_tomtatNd);
            cardView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Toast.makeText(context,"Id : " + list_item.get(getAdapterPosition()).getId(),Toast.LENGTH_LONG).show();
            switch (v.getId()){
                case R.id.cardV:
                    Bundle bundle = new Bundle();
                    bundle.putString("idls",list_item.get(getAdapterPosition()).getId());
                    Intent i = new Intent(context, BaiHocTheoGoiY.class);
                    i.putExtras(bundle);
                    context.startActivity(i);
            }
        }
    }
}
