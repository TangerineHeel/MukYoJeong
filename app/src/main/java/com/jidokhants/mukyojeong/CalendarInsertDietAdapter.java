package com.jidokhants.mukyojeong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarInsertDietAdapter extends RecyclerView.Adapter<CalendarInsertDietAdapter.ViewHolder> {

    private ArrayList<String> mData = null;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView, minus, plus;
        EditText editCount;
        ImageButton delete;

        ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_diet_rv);
            minus = itemView.findViewById(R.id.btn_minus_diet);
            plus = itemView.findViewById(R.id.btn_plus_diet);
            editCount = itemView.findViewById(R.id.edit_count_diet);
            delete = itemView.findViewById(R.id.btn_delete_diet);
        }
    }

    CalendarInsertDietAdapter() {};
    CalendarInsertDietAdapter(ArrayList<String> list) {
        mData = list;
    }

    @Override
    public CalendarInsertDietAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.diet_recyclerview_item, parent, false);
        CalendarInsertDietAdapter.ViewHolder vh = new CalendarInsertDietAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(final CalendarInsertDietAdapter.ViewHolder holder, final int position) {
        String text = mData.get(position);
        holder.textView.setText(text);

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double cnt = (Double.parseDouble(holder.editCount.getText().toString()) -1);
                holder.editCount.setText(cnt+"");
            }
        });

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double cnt = (Double.parseDouble(holder.editCount.getText().toString()) +1);
                holder.editCount.setText(cnt+"");
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addItem(String data) {
        mData.add(data);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}