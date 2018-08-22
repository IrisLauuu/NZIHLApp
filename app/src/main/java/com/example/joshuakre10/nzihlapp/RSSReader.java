package com.example.joshuakre10.nzihlapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class RSSReader extends AppCompatActivity {

    ListView lvRSS;
    ArrayList<String> titles;
    ArrayList<String> links;
    ArrayList<String> pubDates;
    ArrayList<String> creators;
    ArrayList<String> categories;
    ArrayList<String> descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssreader);

        lvRSS = (ListView) findViewById(R.id.lvRSS);

        titles = new ArrayList<String>();
        links = new ArrayList<String>();
        pubDates = new ArrayList<String>();
        creators = new ArrayList<String>();
        categories = new ArrayList<String>();
        descriptions = new ArrayList<String>();

        lvRSS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        new ProcessInBackground().execute();
    }

    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    };

    public class ProcessInBackground extends AsyncTask<Void, Void, Exception> {

        ProgressDialog progressDialog = new ProgressDialog(RSSReader.this);
        Exception exception = null;

        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(RSSReader.this, android.R.layout.simple_list_item_1, titles);
            lvRSS.setAdapter(adapter);

            progressDialog.dismiss();
        }

        @Override
        protected Exception doInBackground(Void... voids) {
            try {
                URL url = new URL("https://puckyeah.nz/feed/");
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(getInputStream(url), "UTF_8");
                boolean insideItem = false;
                int eventType = xpp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = true;
                        } else if (xpp.getName().equalsIgnoreCase("title")) {
                            if (insideItem) {
                                titles.add(xpp.nextText());
                            }
                        } else if (xpp.getName().equalsIgnoreCase("link")) {
                            if (insideItem) {
                                links.add(xpp.nextText());
                            }
                        } else if (xpp.getName().equalsIgnoreCase("pubDate")) {
                            if (insideItem) {
                                pubDates.add(xpp.nextText());
                            }
                        } else if (xpp.getName().equalsIgnoreCase("dc:creator")) {
                            if (insideItem) {
                                creators.add(xpp.nextText());
                            }
                        } else if (xpp.getName().equalsIgnoreCase("category")) {
                            if (insideItem) {
                                categories.add(xpp.nextText());
                            }
                        } else if (xpp.getName().equalsIgnoreCase("description")) {
                            if (insideItem) {
                                descriptions.add(xpp.nextText());
                            }
                        }
                    } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = false;
                    }
                    eventType = xpp.next();
                }
            } catch (MalformedURLException e) {
                exception = e;
            } catch (XmlPullParserException e) {
                exception = e;
            } catch (IOException e) {
                exception = e;
            }

            return exception;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Busy loading news articles... please wait...");
            progressDialog.show();
        }
    }
}
