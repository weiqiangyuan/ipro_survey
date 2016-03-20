package testmultithread.zjut.com.uilt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fairy_LFEn on 2016/3/4/0004.
 *
 * split twitter file
 */
public class TwitterFile {
    private String dataPath = null;
    public TwitterFile(){
    }
    public TwitterFile(String dataPath){
        this.dataPath = dataPath;
    }
    public List<Twitter> splitFileLines(){
        BufferedReader br = null;
        try{
            File file = new File(dataPath);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            br = new BufferedReader(reader);
            List<Twitter> list = new ArrayList<Twitter>();
            String line = null;
            int i = 0;
            int NUM = 2;
            while((line = br.readLine()) != null){
                String[] str_arry = line.split("\t");
                boolean isLngLat = Boolean.valueOf(str_arry[6]);
                System.out.println("isLngLat  :  " + isLngLat);
                if(isLngLat){
                    if(i <= NUM)
                    {
                        Twitter twitter = createTwitter(str_arry);
                        list.add(twitter);
                        i++;
                    }

                    else{
                        break;
                    }
                }
                            }
            return list;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(br!= null){
                try {
                    br.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private Twitter createTwitter(String[] str_arry) {
        Twitter twitter = new Twitter();
        twitter.id = Long.valueOf(str_arry[0]);
        twitter.content = str_arry[1];
        twitter.time = str_arry[2];
        twitter.num_forward = Integer.valueOf(str_arry[3]);

        twitter.lngLat =  new LngLat(Double.valueOf(str_arry[4]), Double.valueOf(str_arry[5]));
        twitter.isLngLat = Boolean.valueOf(str_arry[6]);

        twitter.mediaLinks = str_arry[21];
        if(str_arry[22].length() == 0)
        {
            twitter.resposeTBIds = 0;
        }
        else
        {
            twitter.resposeTBIds = Long.valueOf(str_arry[22]);
        }
        twitter.TBId = str_arry[23];
        twitter.TBName = str_arry[24];

        twitter.language = str_arry[25];
        twitter.user = new User(subStrArray(str_arry, 26, 36));
        return twitter;
    }

    private String[] subStrArray(String[] str_arry, int m, int n) {
        List<String> list = new ArrayList<String>();
        for(int i = m ; i <= n; i++){
            list.add(str_arry[i]);
        }
        return list.toArray(new String[list.size()]);
    }

}
