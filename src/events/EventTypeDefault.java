package events;

public class EventTypeDefault extends AbstractEvent {

    /**
     * shouldn't be used outside the EventFactory
     * for information on how to call it and what the parameters are please look
     * at the documentation {@link EventFactory#createEvent(EventType, int, EventMessage)}
     *
     * @param eventID
     * @param eventPublisherId
     * @param payload
     */
    protected EventTypeDefault(long eventID, int eventPublisherId, EventMessage payload) {
        super(eventID, eventPublisherId, payload);
    }
}
