package videos.hoctienganh.cuong.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import videos.hoctienganh.cuong.Model.Model_cauTL;
import videos.hoctienganh.cuong.learnenglish.R;

/**
 * Created by Vaio on 2017-08-14.
 */

public class Adapter_KQQuestion extends RecyclerView.Adapter<Adapter_KQQuestion.ViewITem> {
    Context context;
    List<Model_cauTL> list;

    public Adapter_KQQuestion(Context context, List<Model_cauTL> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Adapter_KQQuestion.ViewITem onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_cautl,parent,false);
        return new ViewITem(v);
    }

    @Override
    public void onBindViewHolder(Adapter_KQQuestion.ViewITem holder, int position) {
            Model_cauTL model_cauTL = list.get(position);
            holder.textView.setText(model_cauTL.getTextTL());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewITem extends RecyclerView.ViewHolder  {
        TextView textView,textTran;
        Button btn_tran;
        public ViewITem(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_TraLoi);
        }
    }
}
