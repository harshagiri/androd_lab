package com.example.expno6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class AndroidRssReader extends ListActivity {
    private List<RSSFeed> myRssFeed = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MyTask().execute();
    }

    private class MyTask extends AsyncTask {

        URL url;
        ArrayList<String> headlines = new ArrayList();
        ArrayList<String> links = new ArrayList();

        private  String TAG_CHANNEL = "channel";
        private  String TAG_TITLE = "title";
        private  String TAG_LINK = "link";
        private  String TAG_DESCRIPTION = "description";
        private  String TAG_ITEM = "item";
        private  String TAG_PUB_DATE = "pubDate";
        private  String TAG_GUID = "guid";

        @Override
        protected Object doInBackground(Object[] objects) {
            // Initializing instance variables

            try {
                url = new URL("http://feeds.bbci.co.uk/news/rss.xml?edition=uk");

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();

                // We will get the XML from an input stream
                xpp.setInput(getInputStream(url), "UTF_8");

                /* We will parse the XML content looking for the "<title>" tag which appears inside the "<item>" tag.
                 * However, we should take in consideration that the rss feed name also is enclosed in a "<title>" tag.
                 * As we know, every feed begins with these lines: "<channel><title>Feed_Name</title>...."
                 * so we should skip the "<title>" tag which is a child of "<channel>" tag,
                 * and take in consideration only "<title>" tag which is a child of "<item>"
                 *
                 * In order to achieve this, we will make use of a boolean variable.
                 */
                boolean insideItem = false;

                // Returns the type of current event: START_TAG, END_TAG, etc..
                int eventType = xpp.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {

                        if (xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = true;
                        } else if (xpp.getName().equalsIgnoreCase("title")) {
                            if (insideItem)
                                headlines.add(xpp.nextText()); //extract the headline
                        } else if (xpp.getName().equalsIgnoreCase("link")) {
                            if (insideItem)
                                links.add(xpp.nextText()); //extract the link of article
                        }
                    } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = false;
                    }

                    eventType = xpp.next(); //move to next element

                    NodeList items = e.getElementsByTagName(TAG_ITEM);
                    for (int i = 0; i < items.getLength(); i++) {
                        Element e1 = (Element) items.item(i);

                        String title = this.getValue(e1, TAG_TITLE);
                        String link = this.getValue(e1, TAG_LINK);
                        String description = this.getValue(e1, TAG_DESRIPTION);
                        String pubdate = this.getValue(e1, TAG_PUB_DATE);
                        String guid = this.getValue(e1, TAG_GUID);

                        RSSItem rssItem = new RSSItem(title, link, description, pubdate, guid);
                        // adding item to list
                        itemsList.add(rssItem);
                    }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return headlines;
        } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            public InputStream getInputStream(URL url) {
            try {
                return url.openConnection().getInputStream();
            } catch (IOException e) {
                return null;
            }
        }

        public ArrayList<String> heads()
        {
            return headlines;
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Uri feedUri = Uri.parse(myRssFeed.get(position).getLink());
        Intent myIntent = new Intent(Intent.ACTION_VIEW, feedUri);
        startActivity(myIntent);
    }
}