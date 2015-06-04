package org.ameya.actors;

import akka.actor.UntypedActor;
import org.ameya.messages.EndMessage;
import org.ameya.messages.PingMessage;
import org.ameya.messages.PongMessage;

/**
 * Created by Webonise on 04/06/15.
 */
public class PongActor extends UntypedActor {

    private static Integer count = 0;

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof PongMessage){
            play(message);
            getSender().tell(new PingMessage(), getSelf());
        }
        else if(message instanceof EndMessage){
            System.out.println("Stopping pong");
            getContext().stop(getSelf());
        }
        else{
            unhandled(message);
        }
    }

    private void play(Object data){
        System.out.println("pong");
    }
}
