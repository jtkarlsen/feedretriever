package no.uit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main
{
    public static void main( String[] args ) throws Exception
    {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Scanner scanner = null;
                try {
                    scanner = new Scanner(new File("feedurls.txt"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                List<String> urls = new ArrayList<>();
                while(scanner.hasNextLine()){
                    String url = scanner.nextLine();
                    urls.add(url);
                    System.out.println(url);
                }

                FeedRetriever feedRetriever = new FeedRetriever(urls);
                feedRetriever.run();
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000*60*60*1); // hver time
    }


}
