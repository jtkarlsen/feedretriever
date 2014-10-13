package no.uit;

import java.io.File;
import java.util.*;


public class Main
{
    public static void main( String[] args ) throws Exception
    {
        Scanner scanner = new Scanner(new File("feedurls.txt"));
        List<String> urls = new ArrayList<>();
        while(scanner.hasNextLine()){
            String url = scanner.nextLine();
            urls.add(url);
            System.out.println(url);
        }

        final List<String> finalUrls = urls;


        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                FeedRetriever feedRetriever = new FeedRetriever(finalUrls);
                feedRetriever.run();
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000*60*60*3); // hver 3. time
    }


}
