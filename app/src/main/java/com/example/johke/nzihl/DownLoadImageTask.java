package com.example.johke.nzihl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

class DownLoadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;

    public DownLoadImageTask(ImageView imageView) {
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String...urls) {
        String urlOfImage = urls[0];
        Bitmap image = null;

        try {
            InputStream is = new URL(urlOfImage).openStream();
            image = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }

    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }
}