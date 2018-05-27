package com.androidbasicsnd.android.newsapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Activity context, ArrayList<News> newsList) {
        super(context, 0, newsList);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        News news = getItem(position);

        TextView newsTitleTextView = (TextView) listItemView.findViewById(R.id.news_title);
        newsTitleTextView.setText(news.getTitle());

        TextView newsSectionTextView = (TextView) listItemView.findViewById(R.id.news_section);
        newsSectionTextView.setText(news.getSection());

        TextView authorSectionTextView = (TextView) listItemView.findViewById(R.id.news_author);
        authorSectionTextView.setText(news.getAuthor());

        ImageView newsImgageView = (ImageView) listItemView.findViewById(R.id.news_image);

        if(news.getThumbnail().equals(QueryUtils.DEFAULT_THUMBNAIL_VALUE)) {
            newsImgageView.setImageResource(R.drawable.baseline_note_black_48);
        } else {
            new ImageTask(newsImgageView).execute(news.getThumbnail());
        }

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        Date dateObject = null;

        try {
            dateObject = df.parse(news.getDate());
        } catch (java.text.ParseException e) {
            Log.e("NewsAdapter", "Problem parsing the date", e);
        }

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy");
        String dateToDisplay = dateFormatter.format(dateObject);

        dateTextView.setText(dateToDisplay);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        timeView.setText(formattedTime);

        return listItemView;
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private class ImageTask extends AsyncTask<String, Void, Bitmap> {

        ImageView imageView;

        public ImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap imageBitmamp = null;

            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                imageBitmamp = BitmapFactory.decodeStream(in);
            } catch (MalformedURLException e) {
                Log.e("NewsAdapter", "MalformedURLException", e);
            } catch (IOException e) {
                Log.e("NewsAdapter", "IOException", e);
            }

            return imageBitmamp;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }

}
