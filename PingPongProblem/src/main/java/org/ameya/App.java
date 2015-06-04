package org.ameya;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.Creator;
import org.ameya.actors.MasterActor;
import org.ameya.actors.PingActor;
import org.ameya.actors.PongActor;
import org.ameya.messages.EndMessage;
import org.ameya.messages.StartMessage;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws  Exception
    {
        ActorSystem system = ActorSystem.create();
        final ActorRef pongActor = system.actorOf(Props.create(PongActor.class));
        final ActorRef pingActor = system.actorOf(Props.create(new Creator<PingActor>() {
            @Override
            public PingActor create() throws Exception {
               return new PingActor(pongActor);
            }
        }));

        pingActor.tell(new StartMessage(),null);
    }


}
