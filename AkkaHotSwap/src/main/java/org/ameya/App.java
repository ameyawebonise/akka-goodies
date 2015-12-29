package org.ameya;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.ameya.actors.PingPongActor;
import org.ameya.messages.Messages;

/**
 * Created by Webonise on 05/06/15.
 */
public class App {


    public static void main(String args[]) throws  Exception{
        ActorSystem system = ActorSystem.create();
        ActorRef pingPongActor = system.actorOf(Props.create(PingPongActor.class));
        pingPongActor.tell(Messages.START,null);
        Thread.sleep(10000);

        System.out.println("this line has been added by other user who is not part of codacy.To test whether this is flagged in the PR");
        
        
        system.shutdown();
    }

}
