package org.ameya.actors;

import akka.actor.UntypedActor;
import org.ameya.messages.MapData;
import org.ameya.messages.ReducedData;
import org.ameya.messages.WordCount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Webonise on 02/06/15.
 */
public class ReduceActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws  Exception{
        if(message instanceof MapData){
            MapData data = (MapData)message;
            getSender().tell(reduceWordCountList(data),getSelf());
        }
        else{
            unhandled(message);
        }
    }

    private ReducedData reduceWordCountList(MapData mapData){
        Map<String,Integer> wordCountMap =  new HashMap<String,Integer>();
        for(WordCount element: mapData.getWordCountList()){
            if(!wordCountMap.containsKey(element.getWord())){
                //if it is not found
                wordCountMap.put(element.getWord(),1);
            }
            else{
                //increment the count
                Integer occurenceCount = wordCountMap.get(element.getWord());
                occurenceCount ++;
            }

        }
        return new ReducedData(wordCountMap);
    }
}
