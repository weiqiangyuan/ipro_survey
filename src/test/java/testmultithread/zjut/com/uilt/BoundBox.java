package testmultithread.zjut.com.uilt;

/**
 * Created by Fairy_LFEn on 2016/3/5/0005.
 */
public class BoundBox {

    public String type;
    public LngLat boxLnglat_01 = new LngLat();
    public LngLat boxLnglat_02 = new LngLat();
    public LngLat boxLnglat_03 = new LngLat();
    public LngLat boxLnglat_04 = new LngLat();

    public BoundBox(String[] str_arry){
        this.type = str_arry[0];
        this.boxLnglat_01.longitude = Double.valueOf(str_arry[1]);
        this.boxLnglat_01.latitude = Double.valueOf(str_arry[2]);

        this.boxLnglat_02.longitude = Double.valueOf(str_arry[1]);
        this.boxLnglat_02.latitude = Double.valueOf(str_arry[2]);

        this.boxLnglat_03.longitude = Double.valueOf(str_arry[1]);
        this.boxLnglat_03.latitude = Double.valueOf(str_arry[2]);

        this.boxLnglat_04.longitude = Double.valueOf(str_arry[1]);
        this.boxLnglat_04.latitude = Double.valueOf(str_arry[2]);

    }
}
