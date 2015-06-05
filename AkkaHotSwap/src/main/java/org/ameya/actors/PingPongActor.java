package org.ameya.actors;

import akka.actor.UntypedActor;
import akka.japi.Procedure;
import org.ameya.messages.Messages;
import scala.util.parsing.combinator.testing.Str;

/**
 * Created by Webonise on 05/06/15.
 */
public class PingPongActor extends UntypedActor {

    private static Integer count = 0;
    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof String) {
            String actualMessage = (String)message;
            if(message.equals((Messages.START))){
                //become ping
                System.out.println("Starting the game");
                getSelf().tell(Messages.PING,getSelf());
            }
            else if(message.equals(Messages.PING)){
                //become Pong till count is till 10
                Thread.sleep(100);
                System.out.println("PING");
                getSelf().tell(Messages.PONG,getSelf());
                getContext().become(new Procedure<Object>() {
                    @Override
                    public void apply(Object message) throws Exception {
                        if(message instanceof  String){
                            if(((String) message).matches(Messages.PONG)){
                                System.out.println("PONG");
                                getSelf().tell(Messages.PING,getSelf());
                                getContext().unbecome();
                            }
                        }
                    }
                });

            }
            else{
                unhandled(message);
            }
        }

    }
}
