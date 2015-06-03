package org.ameya.actors;

import akka.actor.UntypedActor;
import org.ameya.messages.ReducedData;
import org.ameya.messages.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Webonise on 03/06/15.
 */
public class AggregateActor  extends UntypedActor {
    private Map<String,Integer> aggregatedReducedMap =  new HashMap<String,Integer>();
    @Override
    public void onReceive(Object message) throws  Exception{

        if(message instanceof ReducedData){
            ReducedData data = (ReducedData) message;
            aggregate(data);
        }
        else if(message instanceof Result){
            getSender().tell(aggregatedReducedMap.toString(),getSelf());
        }
        else{
            unhandled(message);
        }

    }

    private Map<String,Integer> aggregate(ReducedData data){
        Map<String,Integer>reducedData = data.getReducedWordCount();

        for(String key:reducedData.keySet()){
            if(!aggregatedReducedMap.containsKey(key)){
                aggregatedReducedMap.put(key,1);
            }
            else{
                //if present then increment the value in the aggregated state
                Integer count = reducedData.get(key) + aggregatedReducedMap.get(key);
                aggregatedReducedMap.put(key,count);
            }
        }
        return aggregatedReducedMap;
    }
}
