package org.ameya;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import org.ameya.actors.MasterActor;
import org.ameya.messages.Result;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws  Exception
    {
        Timeout to = new Timeout(new FiniteDuration(10, TimeUnit.MILLISECONDS));
        ActorSystem system = ActorSystem.create("MapReduceApp");

        final ActorRef master = system.actorOf(Props.create(MasterActor.class), "master");
        master.tell("The quick brown fox tried to jump over the lazy dog and fell on the dog",null);
        master.tell("Dog is man's best friend",null);
        master.tell("Dog and Fox belong to the same family",null);
        Thread.sleep(5000);


        Future<Object> future = Patterns.ask(master,new Result(),to);
        String result = (String) Await.result(future, to.duration());

        System.out.println(result);

    }
}
