package com.example.johke.nzihl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ArticleAdapter extends ArrayAdapter<Article> {
    private final Context context;
    private final ArrayList<Article> values;

    public ArticleAdapter(@NonNull Context context, ArrayList<Article> list) {
        super(context, R.layout.row_layout, list);
        this.context = context;
        this.values = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);

        TextView tvCategory = rowView.findViewById(R.id.tvCategory);
        TextView tvTitle = rowView.findViewById(R.id.tvTitle);
        TextView tvCreatorPubDate = rowView.findViewById(R.id.tvCreatorPubDate);
        TextView tvDescription = rowView.findViewById(R.id.tvDescription);

        ImageView ivArticleImage = rowView.findViewById(R.id.ivArticleImage);

        // TODO: Fix Time Format
        SimpleDateFormat fromRss = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
        SimpleDateFormat articleFormat = new SimpleDateFormat("HH:mm EEE d");

        String reformattedDate = "";

        try {
            reformattedDate = articleFormat.format(fromRss.parse(values.get(position).getPubDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tvCategory.setText(values.get(position).getCategory());
        tvTitle.setText(values.get(position).getTitle());
        tvCreatorPubDate.setText(values.get(position).getCreator() + " | " + reformattedDate);
        tvDescription.setText(values.get(position).getDescription());

        new DownLoadImageTask(ivArticleImage).execute(values.get(position).getImage());

        return rowView;
    }
}

