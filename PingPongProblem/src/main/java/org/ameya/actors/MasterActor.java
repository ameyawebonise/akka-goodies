package org.ameya.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import org.ameya.messages.EndMessage;
import org.ameya.messages.PingMessage;
import org.ameya.messages.PongMessage;
import org.ameya.messages.StartMessage;

/**
 * Created by Webonise on 04/06/15.
 */
public class MasterActor extends UntypedActor {

    ActorRef pingActor = getContext().actorOf(Props.create(PingActor.class));
    ActorRef pongActor = getContext().actorOf(Props.create(PongActor.class));

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof StartMessage){
          pingActor.tell(message,getSelf());
        }
        else if(message instanceof  PingMessage){
           System.out.println("ping");
            pongActor.tell(message,getSelf());
        }
        else if(message instanceof PongMessage){
            System.out.println("pong");
            pingActor.tell(message, getSelf());
        }
        else if(message instanceof EndMessage){
                pingActor.tell(akka.actor.PoisonPill.getInstance(),getSelf());
                pongActor.tell(akka.actor.PoisonPill.getInstance(),getSelf());
        }
        else{
            unhandled(message);
        }
    }
}