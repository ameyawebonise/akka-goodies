package org.ameya.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import org.ameya.messages.EndMessage;
import org.ameya.messages.PingMessage;
import org.ameya.messages.PongMessage;
import org.ameya.messages.StartMessage;

/**
 * Created by Webonise on 04/06/15.
 */
public class PingActor extends UntypedActor {

    private static Integer count = 0;
    private final ActorRef pong;
    public PingActor(ActorRef pong){
        this.pong = pong;
    }
    @Override
    public void onReceive(Object message) throws Exception {

        if(message instanceof PingMessage){
            play();
            if(count > 99 ){
                System.out.println("Stopping ping");
                getContext().stop(getSelf());
                pong.tell(new EndMessage(),getSelf());
            }
            else{
                pong.tell(new PongMessage(),getSelf());
            }
        }
        else if(message instanceof  StartMessage){
            play();
            pong.tell(new PongMessage(),getSelf());
        }
        else{
            unhandled(message);
        }
    }

    private void play(){
        ++count;
        System.out.println("ping");

    }
}
