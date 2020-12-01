package com.imran.android.newsreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by Shah Md Imran Hossain on 01, December, 2020
 */
public class NewsListRecyclerView extends RecyclerView.Adapter<NewsListRecyclerView.ViewHolder> {
    private final Context context;
    private final LayoutInflater layoutInflater;
    private final List<String> titleList;

    public NewsListRecyclerView(Context context, List<String> titleList) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.titleList = titleList;
    }

    @NonNull
    @Override
    public NewsListRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_news_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListRecyclerView.ViewHolder holder, int position) {
        holder.textView.setText(titleList.get(position));
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.itemTitleTextView);
        }
    }
}
