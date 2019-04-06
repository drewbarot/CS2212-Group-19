package states.subscriber;

import events.AbstractEvent;
import subscribers.AbstractSubscriber;

public abstract class AbstractState {

    protected String name;

    /**
     * the handleEvent method will behave appropriately depending on the implementation.
     * In general it will do something, after an AbstractEvent is published on an
     * AbstractChannel to which the ConcreteState's host Object is subscribed
     * @param subscriberID
     * @param event
     */
    public void handleEvent(int subscriberID, AbstractEvent event){
        System.out.println("Subscriber " + subscriberID +
                " receives event " + event.getEventID() + " and handles it at state " + name);
    }

    public String name() {
        return name;
    }

}
