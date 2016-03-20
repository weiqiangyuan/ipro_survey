package testmultithread;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args) {
        String index_path = "D:\\TwitterData\\twitter_index";
        String data_path = "D:\\TwitterData\\twitter_data";

        File data_file = new File(data_path);
        File[] files = data_file.listFiles();
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        if (files != null) {
            for (int i = 0; i < files.length - 1; i++) {
                scheduledThreadPool.schedule(
                        new IndexFileRunnable(files[i].getAbsolutePath(), index_path)
                        , 0, TimeUnit.SECONDS);
                System.out.println("--------");
            }

            System.out.println("index file is over");
        }
    }
}