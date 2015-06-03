package org.ameya.actors;

import akka.actor.UntypedActor;
import org.ameya.messages.MapData;
import org.ameya.messages.WordCount;

import java.util.*;

/**This actor will recieve a String and find out the number of distince number of words
 * Created by Webonise on 02/06/15.
 */
public class MapActor extends UntypedActor {

    String[] STOP_WORDS = { "a", "am", "an", "and", "are", "as", "at",
            "be","do", "go", "if", "in", "is", "it", "of", "on", "the", "to" };
    final List<String> STOP_WORDS_LIST = Arrays.asList(STOP_WORDS);

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof String){
            //check whether the message is type of a string
            //if yes then cast it to a String safely

            String workLoad = (String)message;
            System.out.println("recieved");
            //Inform the sender (in this case the master actor ) through a message that it expects(MapData) about the result of the computation
            getSender().tell(mapWords(workLoad),getSelf());
        }
        else{
            //throw a exception saying that a message of any other type than String is not being able to be handled
            unhandled(message);
        }
    }

    private MapData mapWords(String workLoad){
        List<WordCount> wordCountList =  new ArrayList<WordCount>();
        StringTokenizer tokenizer =  new StringTokenizer(workLoad);

        while (tokenizer.hasMoreTokens()){
            String currentword =  tokenizer.nextToken().toLowerCase();
            if(!STOP_WORDS_LIST.contains(currentword)){
              wordCountList.add(new WordCount(currentword,1));
            }

        }

        MapData mapData =  new MapData(wordCountList);
        return mapData;
    }
}
