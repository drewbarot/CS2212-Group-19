package strategies.publisher;

import events.AbstractEvent;
import events.EventFactory;
import events.EventMessage;
import events.EventType;
import pubSubServer.ChannelEventDispatcher;
import publishers.AbstractPublisher;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kkontog, ktsiouni, mgrigori
 * Base Inteface for the implementation of the Strategy Design Pattern
 */
public abstract class AbstractStrategy {

    protected List<String> channels = new ArrayList<>();
    protected String name;

    protected void doStrategy() {};

    /**
     * create a random event and return it
     * @param publisherID
     * @return
     */
    protected AbstractEvent createRandomEvent(int publisherID) {
        int eventType = (int) (Math.random() * 2147483647); //create a random integer that doesn't overflow int type
        eventType = eventType%3; //get that number to be either 0,1,2
        return EventFactory.createEvent(EventType.values()[eventType], publisherID, new EventMessage("RandomHeader", "RandomBody"));
    }

    /**
     * Whenever a publisher's {@link AbstractPublisher#publish()} is called, the call is forwarded to the particular Publisher's
     * AbstractStrategy implementation of this method
     */
    public void doPublish(int publisherID) {
        AbstractEvent event = createRandomEvent(publisherID);
        doStrategy();
        print_publishing_information(event);
        ChannelEventDispatcher.getInstance().postEvent(event, channels);
    }

    /**
     * whenever a publisher's {@link AbstractPublisher#publish(AbstractEvent)} is called, the call is forwarded to the particular Publisher
     * s AbstractStrategy implementation of this method
     *
     * @param event       the event to be published
     */
    public void doPublish(AbstractEvent event) {
        doStrategy();
        print_publishing_information(event);
        ChannelEventDispatcher.getInstance().postEvent(event, channels);
    }

    public String name() {
        return name;
    }

    protected void print_publishing_information(AbstractEvent event) {
        System.out.println("Publisher " + event.getEventPublisher() + " publishes event " + event.getEventID());
    }

}


