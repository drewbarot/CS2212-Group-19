package pubSubServer;

import events.AbstractEvent;
import events.EventFactory;
import publishers.AbstractPublisher;
import publishers.PublisherFactory;
import subscribers.AbstractSubscriber;
import events.EventType;
import events.EventMessage;
import publishers.PublisherType;
import strategies.publisher.StrategyName;
import subscribers.SubscriberFactory;
import subscribers.SubscriberType;
import states.subscriber.StateName;

/**
 * the administration server will be used by the driver function to access all other classes and methods
 */
public class AdministrationServer {

    protected static AdministrationServer instance = null;
    private ChannelPoolManager cpm;
    private ChannelAccessControl cac;

    private AdministrationServer() {
        cac = ChannelAccessControl.getInstance();
        cpm = ChannelPoolManager.getInstance();
    }

    /**
     * return instance of the singleton
     * @return
     */
    public static AdministrationServer getInstance() {
        if (instance == null) {
            instance = new AdministrationServer();
        }
        return instance;
    }

    /**
     * create a publisher
     * @param publisherType
     * @param strategyName
     * @return
     */
    public AbstractPublisher createPublisher(PublisherType publisherType, StrategyName strategyName) {
        return PublisherFactory.createPublisher(publisherType, strategyName);
    }

    /**
     * create a new subscriber
     * @param subscriberType
     * @param stateName
     * @return
     */
    public AbstractSubscriber createSubscriber(SubscriberType subscriberType, StateName stateName) {
        return SubscriberFactory.createSubscriber(subscriberType, stateName);
    }

    /**
     * block a subscriber from a channel
     * @param subscriber
     * @param channelName
     */
    public void blockSubscriber(AbstractSubscriber subscriber, String channelName) {
        cac.blockSubscriber(subscriber, channelName);
    }

    /**
     * unblock a subscriber from a channel
     * @param subscriber
     * @param channelName
     */
    public void unblockSubscriber(AbstractSubscriber subscriber, String channelName) {
        cac.unBlockSubscriber(subscriber, channelName);
    }

    /**
     * publish an event
     * @param publisher
     * @param event
     */
    public void publish(AbstractPublisher publisher, AbstractEvent event) {
        publisher.publish(event);
    }

    /**
     * publish a randomly generated event
     * @param publisher
     */
    public void publish(AbstractPublisher publisher) {
        publisher.publish();
    }

    /**
     * create a new event
     * @param eventType
     * @param eventPublisherID
     * @param payload
     * @return
     */
    public AbstractEvent newEvent(EventType eventType, int eventPublisherID, EventMessage payload) {
        return EventFactory.createEvent(eventType, eventPublisherID, payload);
    }

    /**
     * create a new event message
     * @param header
     * @param body
     * @return
     */
    public EventMessage newEventMessage(String header, String body) {
        return new EventMessage(header, body);
    }

    /**
     * create a new channel if it doesn't already exist
     * @param channelName
     */
    public void createChannel(String channelName) {
        ChannelCreator.getInstance().addChannel(channelName);
    }

    /**
     * subscribe to a channel
     * @param subscriber
     * @param channelName
     */
    public void subscribe(AbstractSubscriber subscriber, String channelName) {
        subscriber.subscribe(channelName);
    }

    /**
     * unsubscribe from a channel
     * @param subscriber
     * @param channelName
     */
    public void unsubscribe(AbstractSubscriber subscriber, String channelName) {
        subscriber.unsubscribe(channelName);
    }

}
