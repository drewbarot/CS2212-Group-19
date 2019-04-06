package subscribers;

import events.AbstractEvent;
import pubSubServer.SubscriptionManager;
import states.subscriber.StateFactory;
import states.subscriber.StateName;

public class ConcreteSubscriberDefault extends AbstractSubscriber {

    protected ConcreteSubscriberDefault(int subscriberID) {
        super(subscriberID);
    }

}
