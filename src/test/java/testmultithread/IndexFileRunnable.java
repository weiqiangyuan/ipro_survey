package testmultithread;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Created by Fairy_LFEn on 2016/3/2/0002.
 */
public class IndexFileRunnable implements Runnable{
    private String index_path = null;
    private String data_path = null;

    public IndexFileRunnable(String data_path, String index_path){
        this.data_path = data_path;
        this.index_path = index_path;
    }

    @Override
    public void run() {
//        try {
            System.out.println("runnable start");
//            Index index = new Index(data_path, index_path);
//            index.indexDoc();
            System.out.println("runnable end");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
