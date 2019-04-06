package subscribers;

import events.AbstractEvent;
import pubSubServer.SubscriptionManager;
import states.subscriber.StateFactory;
import states.subscriber.StateName;

public class ConcreteSubscriberA extends AbstractSubscriber {

    protected ConcreteSubscriberA(int subscriberID) {
        super(subscriberID);
    }


}
