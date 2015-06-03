package org.ameya.messages;

import java.util.Map;

/**
 * Created by Webonise on 02/06/15.
 */
public class ReducedData {

    private final Map<String,Integer> reducedWordCount;


    public ReducedData(Map<String,Integer> reducedWordCount){
        this.reducedWordCount = reducedWordCount;
    }

    public Map<String,Integer>getReducedWordCount(){
        return this.reducedWordCount;
    }


}
