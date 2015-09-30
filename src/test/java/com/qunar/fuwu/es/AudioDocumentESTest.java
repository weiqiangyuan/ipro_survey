package com.qunar.fuwu.es;

import com.qunar.fuwu.web.vo.CallSearchParam;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AudioDocumentESTest {
    private AudioDocumentES audioDocumentES;

    @Before
    public void init(){
        audioDocumentES = new AudioDocumentES();
    }

    @Test
    public void testSelectAudioDoc1() throws Exception {
        CallSearchParam param = new CallSearchParam();
        List<AudioDocument> audioDocuments = audioDocumentES.selectAudioDoc(param);
        for(AudioDocument audioDocument : audioDocuments){
            System.out.println(ToStringBuilder.reflectionToString(audioDocument));
        }
    }

    @Test
    public void testSelectAudioDoc2() throws Exception {
        CallSearchParam param = new CallSearchParam();
        param.setOrderNo("abc456789");
        List<AudioDocument> audioDocuments = audioDocumentES.selectAudioDoc(param);
        for(AudioDocument audioDocument : audioDocuments){
            System.out.println(ToStringBuilder.reflectionToString(audioDocument));
        }
    }

    @Test
    public void testSelectAudioDoc3() throws Exception {
        CallSearchParam param = new CallSearchParam();
        param.setKeyWord("退改签 航司");
        List<AudioDocument> audioDocuments = audioDocumentES.selectAudioDoc(param);
        for(AudioDocument audioDocument : audioDocuments){
            System.out.println(ToStringBuilder.reflectionToString(audioDocument));
        }
    }

    @Test
    public void testSelectAudioDoc4() throws Exception {
        CallSearchParam param = new CallSearchParam();
        param.setClientName("翔游五洲优选");
        List<AudioDocument> audioDocuments = audioDocumentES.selectAudioDoc(param);
        for(AudioDocument audioDocument : audioDocuments){
            System.out.println(ToStringBuilder.reflectionToString(audioDocument));
        }
    }

    @Test
    public void testSelectAudioDoc5() throws Exception {
        CallSearchParam param = new CallSearchParam();
        param.setOrderNo("yjw140528224904157");
        param.setKeyWord("代理商");
        List<AudioDocument> audioDocuments = audioDocumentES.selectAudioDoc(param);
        for(AudioDocument audioDocument : audioDocuments){
            System.out.println(ToStringBuilder.reflectionToString(audioDocument));
        }
    }

}