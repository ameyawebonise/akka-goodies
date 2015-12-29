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

        //the following code is to see whether codacy flags the obvious issues in this block
        String first = "first";
        String second = "second";

        if(first == second){
            System.err.println("Something went wrong");
        }
        else{
            System.out.println("Everything is fine in math land");
        }

        system.shutdown();
    }

}
