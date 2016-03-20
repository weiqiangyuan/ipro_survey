package testmultithread.zjut.com.uilt;

/**
 * Created by Fairy_LFEn on 2016/3/4/0004.
 */
public class Twitter {
    public Long id;
    public String content;
    public String time;
    public int num_forward; // 此tweet转发次数
    public LngLat lngLat;
    public boolean isLngLat;  //是否带经纬度信息
//    public Location location;
//    public BoundBox boundBox;
    public String mediaLinks    ;

    public long resposeTBIds; //如果该Tweet A是回复某一tweet B的话，那么这里是所回复tweet B的ID，long
    public String TBId;
    public String TBName;
    public String language;
    public User user;
}
