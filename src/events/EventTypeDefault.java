package events;

public class EventTypeDefault extends AbstractEvent {
    protected EventTypeDefault(long eventID, int eventPublisherId, EventMessage payload) {
        super(eventID, eventPublisherId, payload);
    }
}
