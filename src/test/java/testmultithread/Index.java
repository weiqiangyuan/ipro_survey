//package testmultithread;//
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by Fernflower decompiler)
////
//
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.document.*;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.store.FSDirectory;
//import zjut.com.uilt.FieldNames;
//import zjut.com.uilt.Twitter;
//import zjut.com.uilt.TwitterFile;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Paths;
//import java.util.Date;
//import java.util.List;
//
///**
//* 对单独某个.txt文件创建索引
// * */
//public class Index {
//    private String indexPath = null;
//    private String dataPath = null; //单个文件的路径
//    private boolean create = true;
//
//    public Index() {
//    }
//    public Index(String dataPath, String indexPath){
//        this.indexPath = indexPath;
//        this.dataPath = dataPath;
//    }
//
//    //为文件file创建索引
//    public void indexDoc() throws IOException {
//        InputStream stream = new FileInputStream(dataPath);
//        IndexWriter writer = null;
//
//        Throwable var5 = null;
//        try {
//            writer = getIndexWriter();
//            Document document = new Document();
//            StringField pathField = new StringField("path", dataPath, Field.Store.YES);
//            TwitterFile tFile = new TwitterFile(dataPath);
//            //添加字段Field
//            List<Twitter> tweets = tFile.splitFileLines();
////            document.add(pathField);
//            for(Twitter tweet : tweets){
//                addFields(tweet, document);
//            }
//
//            if(writer != null){
//                writer.addDocument(document);
//                writer.close();
//            }
//        } catch (Throwable var15) {
//            var5 = var15;
//            throw var15;
//        } finally {
//            stream.close();
//        }
//    }
//
//    private void addFields(Twitter tweet, Document document) {
//        document.add(new LongField(FieldNames.id , tweet.id, LongField.TYPE_STORED));
//        document.add(new Field(FieldNames.content, tweet.content, StringField.TYPE_STORED));
//        document.add(new Field(FieldNames.time, tweet.time, StringField.TYPE_STORED));
//        document.add(new IntField(FieldNames.numForward, tweet.num_forward , IntField.TYPE_STORED));
//        //lnglat
//        document.add(new DoubleField(FieldNames.longitude, tweet.lngLat.longitude, DoubleField.TYPE_STORED));
//        document.add(new DoubleField(FieldNames.latitue, tweet.lngLat.latitude, DoubleField.TYPE_STORED));
//
//        document.add(new Field(FieldNames.mediaLinks, tweet.mediaLinks, StringField.TYPE_STORED));
//
//        document.add(new LongField(FieldNames.responseTBIds, tweet.resposeTBIds, LongField.TYPE_STORED));
//        document.add(new Field(FieldNames.TBId, tweet.TBId, StringField.TYPE_STORED));
//        document.add(new Field(FieldNames.TBName, tweet.TBName, StringField.TYPE_STORED));
//        document.add(new Field(FieldNames.language, tweet.language, StringField.TYPE_STORED));
//
//        //user
//        document.add(new LongField(FieldNames.userId, tweet.user.id, LongField.TYPE_STORED));
//        document.add(new Field(FieldNames.userRealName, tweet.user.realName, StringField.TYPE_STORED));
//        document.add(new Field(FieldNames.userName, tweet.user.name, StringField.TYPE_STORED));
//        document.add(new IntField(FieldNames.userNumTwts, tweet.user.numTwitters, IntField.TYPE_STORED));
//        document.add(new LongField(FieldNames.userSgnUpTime, tweet.user.signUpTime, LongField.TYPE_STORED));
//        document.add(new Field(FieldNames.userProfile, tweet.user.profile, StringField.TYPE_STORED));
//        document.add(new Field(FieldNames.userSgnUpAdd,tweet.user.signUpAdd, StringField.TYPE_STORED));
//        document.add(new IntField(FieldNames.userNumFuns, tweet.user.numFuns, IntField.TYPE_STORED));
//        document.add(new IntField(FieldNames.userNumfollows, tweet.user.numFollows, IntField.TYPE_STORED));
//        document.add(new Field(FieldNames.userTimeZone, tweet.user.timeZone, StringField.TYPE_STORED));
//        document.add(new IntField(FieldNames.UtcOffset, tweet.user.UtcOffset, IntField.TYPE_STORED));
//
//    }
//
//    //在路径indexPath上创建IndexWriter, create : 是否重建
//    private IndexWriter getIndexWriter(){
//        Date start = new Date();
//        try {
//            //用于索引的存放位置Directory
//            FSDirectory e = FSDirectory.open(Paths.get(indexPath));
//            StandardAnalyzer analyzer = new StandardAnalyzer();
//            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
////            if(create) {
////                iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
////            } else {
////                iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
////            }
//
//            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
//            return new IndexWriter(e, iwc);
//        } catch (IOException e) {
//            return null;
//        }
//    }
//}
