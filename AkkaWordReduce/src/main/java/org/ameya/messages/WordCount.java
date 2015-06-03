package org.ameya.messages;

/**
 * Created by Webonise on 02/06/15.
 */
public class WordCount{
    private final String word;
    private final Integer count;


    public WordCount(String word,Integer count){
        this.word = word;
        this.count = count;
    }

    public String getWord(){
        return this.word;
    }

    public Integer getCount(){
        return this.count;
    }

}
