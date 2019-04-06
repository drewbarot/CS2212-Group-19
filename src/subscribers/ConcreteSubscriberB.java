package subscribers;

import events.AbstractEvent;
import pubSubServer.SubscriptionManager;
import states.subscriber.StateFactory;
import states.subscriber.StateName;

public class ConcreteSubscriberB extends AbstractSubscriber {

    protected ConcreteSubscriberB(int subscriberID) {
        super(subscriberID);
    }

}
