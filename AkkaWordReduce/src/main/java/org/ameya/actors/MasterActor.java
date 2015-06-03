package org.ameya.actors;

import akka.actor.*;
import akka.routing.RoundRobinRouter;
import org.ameya.messages.MapData;
import org.ameya.messages.ReducedData;
import org.ameya.messages.Result;

/**
 * Created by Webonise on 03/06/15.
 */
public class MasterActor extends UntypedActor {



    final ActorRef mapActor = getContext().actorOf(Props.create(MapActor.class), "map");
    final ActorRef reduceActor = getContext().actorOf(Props.create(ReduceActor.class), "reduce");
    final ActorRef aggregateActor = getContext().actorOf(Props.create(AggregateActor.class), "aggregate");


    @Override
    public void onReceive(Object message) throws  Exception{

        if(message instanceof String){
            //if a request has come to distill it then send it to the map actor
            mapActor.tell(message,getSelf());
        }else if(message instanceof MapData){
            //if the Map data is recieved then send it to the Reduce ACtor
            reduceActor.tell(message,getSelf());
        }else if(message instanceof ReducedData){
            //if it is a reduced data message send it to Aggregator Actor
            aggregateActor.tell(message,getSelf());
        }else if(message instanceof Result){
            //if it is a Result message forward it to agg actor
            aggregateActor.forward(message,getContext());
        }else{
            unhandled(message);
        }


    }
}
