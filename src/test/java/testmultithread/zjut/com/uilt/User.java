package testmultithread.zjut.com.uilt;

/**
 * Created by Fairy_LFEn on 2016/3/5/0005.
 */
public class User {
    public long id;
    public String realName; //用户名字
    public String name;     //用户显示名字
    public int numTwitters;  //发表的twitter树
    public long signUpTime; //注册时间
    public String profile; //用户个人描述
    public String signUpAdd; //注册时间
    public int numFuns;//粉丝数
    public int numFollows; //关注人数
    public String timeZone; // 用户所在时区
    public int UtcOffset; // 发表改tweet的用户的UtcOffset;

    public User(String[] str_arry){
        this.id = Long.valueOf(str_arry[0]);
        this.realName = str_arry[1];
        this.name = str_arry[2];
        this.numTwitters = Integer.valueOf(str_arry[3]);
        this.signUpTime = Long.valueOf(str_arry[4]);
        this.profile = str_arry[5];
        this.signUpAdd = str_arry[6];
        this.numFuns = Integer.valueOf(str_arry[7]);
        this.numFollows = Integer.valueOf(str_arry[8]);
        this.timeZone = str_arry[9];
        this.UtcOffset = Integer.valueOf(str_arry[10]);
    }
}
