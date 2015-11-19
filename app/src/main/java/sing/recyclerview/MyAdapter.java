package sing.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> list;
    private LayoutInflater inflater;

    public MyAdapter(List<String> list) {
        this.list = list;
    }

    private onItemClickListener onItemClickListener;

    public void setOnItemClickListener(MyAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null){
                        onItemClickListener.onClick(v,getLayoutPosition(),list.get(getLayoutPosition()));
                    }
                }
            });
        }
    }

    interface onItemClickListener{
        void onClick(View v, int position, String a);
    }

    public void add(int position,String a){
        if (list != null){
            list.add(position,a);
            notifyItemInserted(position);
        }
    }

    public void remove(int position){
        if (list != null && list.size() > 0 ){
            list.remove(position);
            notifyItemRemoved(position);
        }
    }
}