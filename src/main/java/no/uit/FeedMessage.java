/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author jt
 */
public class FeedMessage {

    private String title;
    private String description;
    private String link;
    private String author;
    private String guid;
    private String pubDate;

      public String getTitle() {
        return title;
      }

      public void setTitle(String title) {
        this.title = title;
      }

      public String getDescription() {
        return description;
      }

      public void setDescription(String description) {
        this.description = description;
      }

      public String getLink() {
        return link;
      }

      public void setLink(String link) {
        this.link = link;
      }

      public String getAuthor() {
        return author;
      }

      public void setAuthor(String author) {
        this.author = author;
      }

      public String getGuid() {
          if(guid == null || guid == "") {
              return link;
          } else {
              return guid;
          }
      }

      public void setGuid(String guid) {
        this.guid = guid;
      }

      public String getPubDate() {
          return pubDate;
      }

      public void setPubDate(String pubDate) {
          TimeZone tz = TimeZone.getTimeZone("UTC");
          DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
          df.setTimeZone(tz);
          this.pubDate = df.format(new Date());
      }

      @Override
      public String toString() {
        return "FeedMessage [title=" + title + ", description=" + description
            + ", link=" + link + ", author=" + author + ", pubDate=" + pubDate + ", guid=" + guid
            + "]";
      }
}
