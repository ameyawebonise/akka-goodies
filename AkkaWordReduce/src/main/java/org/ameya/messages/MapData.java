package org.ameya.messages;

import java.util.List;

/**
 * Created by Webonise on 02/06/15.
 */
public class MapData {

    private final List<WordCount> wordCountList;


    public MapData(List<WordCount> wordCountList){
        this.wordCountList = wordCountList;
    }

    public List<WordCount> getWordCountList(){
        return this.wordCountList;
    }

}
