package com.example.johke.nzihl;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class NewsActivity extends AppCompatActivity {

    Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.news_action_bar_layout);

        View view = getSupportActionBar().getCustomView();
        TextView title = view.findViewById(R.id.action_bar_title);


        TextView tvCategoryArticle = findViewById(R.id.tvCategoryArticle);
        TextView tvTitleArticle = findViewById(R.id.tvTitleArticle);
        TextView tvCreatorPubDateArticle = findViewById(R.id.tvCreatorPubDateArticle);
        WebView wvContentArticle = findViewById(R.id.wvContentArticle);

        Intent intent = getIntent();
        article = (Article) intent.getParcelableExtra("article");

        // TODO: Fix Time Format
        SimpleDateFormat fromRss = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
        SimpleDateFormat articleFormat = new SimpleDateFormat("h:mm a EEE d");

        String reformattedDate = "";

        try {
            reformattedDate = articleFormat.format(fromRss.parse(article.getPubDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }



        title.setText(article.getIndex()+1 + " of " + article.getTotal());
        tvCategoryArticle.setText(article.getCategory());
        tvTitleArticle.setText(article.getTitle());
        tvCreatorPubDateArticle.setText(article.getCreator() + " | " + reformattedDate);
        wvContentArticle.getSettings().setJavaScriptEnabled(true);
        wvContentArticle.getSettings().setDomStorageEnabled(true);
        wvContentArticle.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        wvContentArticle.loadDataWithBaseURL("","<style>img{display: inline; height: auto;max-width: 100%;} iframe{display:block; max-width:100%; margin-top:10px; margin-bottom:10px;}</style>" + article.getContent(), "text/html", "UTF-8", "");
    }
}
