package states.subscriber;

import events.AbstractEvent;

public abstract class AbstractState {
    /**
     * the handleEvent method will behave appropriately depending on the implementation.
     * In general it will do something, after an AbstractEvent is published on an
     * AbstractChannel to which the ConcreteState's host Object is subscribed
     * @param event
     * @param channelName
     */
    public void handleEvent(AbstractEvent event, String channelName){};

}
