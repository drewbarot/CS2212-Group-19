package subscribers;

public class SubscriberIDMaker {

    private static int subscriberUIDs = 1;

    /**
     *
     * @return the next number of type {@link long}in the series of UID for the AbstractEvent derived classes
     */
    protected static int getNewSubscriberID() {
        return subscriberUIDs++;
    }

}
