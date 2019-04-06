package strategies.publisher;

import pubSubServer.AbstractChannel;
import pubSubServer.ChannelDiscovery;
import java.util.List;
import java.util.ArrayList;


/*
    the strategy of StrategyB is to post to all channels that have an even amount of characters in their name
    we also post to Main automatically. main has 4 letters so it will be covered by the even character filter
 */
public class StrategyB extends AbstractStrategy {

    protected StrategyB() {
        name = "B";
    }

    /**
     * this is the function that implements the strategy of StrategyB and adds all channels with even number of
     * characters into the list of channels to be published to
     */
    protected void doStrategy() {
        channels.clear();
        List<AbstractChannel> listOfChannels = ChannelDiscovery.getInstance().listChannels();
        for (int i=0; i < listOfChannels.size(); i++) {
            String channelName = listOfChannels.get(i).getChannelTopic();
            if ((channelName.length() % 2) == 0) { //if the number of characters is even
                channels.add(channelName);
            }
        }
    }

}
