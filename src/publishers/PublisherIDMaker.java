package publishers;

public class PublisherIDMaker {

    private static int publisherUIDs = 1;

    /**
     *
     * @return the next number of type {@link long}in the series of UID for the AbstractEvent derived classes
     */
    protected static int getNewPublisherID() {
        return publisherUIDs++;
    }

}
