package org.belosoft.SeflTrainer.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.belosoft.SeflTrainer.R;

import java.util.List;

/**
 * Created by oscar on 17/09/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> names;
    private int layout;
    private OnItemClickListener onItemClickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ViewHolder(View v){
            super(v);
            this.name = (TextView) v.findViewById(R.id.textViewPrimeraRepeticion);
        }
    }

    public  interface OnItemClickListener {
        void onItemClick(String name, int position);
    }
}
