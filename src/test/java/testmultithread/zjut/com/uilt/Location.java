package testmultithread.zjut.com.uilt;

import java.util.List;

/**
 * Created by Fairy_LFEn on 2016/3/4/0004.
 */
public class Location {
    public String type;
    public String id;
    public String name;
    public String countryId;
    public String countryName;

    public Location(String[] str_arry){
        this.type = str_arry[0];
        this.type = str_arry[1];
        this.type = str_arry[2];
        this.type = str_arry[3];
        this.type = str_arry[4];
    }
}
