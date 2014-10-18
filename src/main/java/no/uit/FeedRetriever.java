package no.uit;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import java.net.URLDecoder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by JanTore on 13.10.2014.
 */
public class FeedRetriever {

    private List<String> urls;

    public FeedRetriever(List<String> urls) {
        this.urls = urls;
    }

    public void run() {
        for(String url : urls) {
            RSSFeedParser parser = new RSSFeedParser(url);
            Feed feed = parser.readFeed();
            if (feed == null) {
                continue;
            }
            System.out.println(feed);
            for (FeedMessage message : feed.getMessages()) {
                try {
                    int response = putFeedMessage(message);
                    if (response == 201) {
                        System.out.println("New entry for "+message);
                    } else if ( response == 200) {
                        System.out.println("Updated entry for "+message);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
//                System.out.println(message);
            }
        }
    }

    // HTTP POST request
    private int putFeedMessage(FeedMessage message) throws Exception {

        Gson gson = new Gson();

        String url = "http://localhost:9200/messages/message/";

        HttpClient client = new DefaultHttpClient();
        HttpPut put = new HttpPut(url+message.getGuid().replaceAll("[^a-zA-Z0-9]+","")+"/_create");
        String body = gson.toJson(message);
//        System.out.println(url+message.guid.replaceAll("[^a-zA-Z0-9]+","")+"/_create");
//        System.out.println("\nJson body: "+body);
        StringEntity params = new StringEntity(body);
        put.addHeader("content-type", "application/json");
        put.setEntity(new StringEntity(body, HTTP.UTF_8));

        HttpResponse response = client.execute(put);

        return response.getStatusLine().getStatusCode();
    }


}
