package strategies.publisher;

import pubSubServer.ChannelDiscovery;
import java.util.List;
import java.util.ArrayList;
import pubSubServer.AbstractChannel;

/*
    Strategy A posts the channel to every channel that starts with an A, case insensitive
 */

public class StrategyA extends AbstractStrategy {

    protected StrategyA() {
        name = "A";
    }

    /**
     * this function applied the strategy of StrategyA to compile a list of channels to post the event to
     * This strategy finds all the channels that begin with the letter A and adds them to the list
     * We publish to the main channel automatically
     * @return
     */
    protected void doStrategy() {
        channels.clear();
        channels.add("Main");
        List<AbstractChannel> listOfChannels = ChannelDiscovery.getInstance().listChannels(); //list of channels
        for (int i = 0; i < listOfChannels.size(); i++) {
            String channelName = listOfChannels.get(i).getChannelTopic();
            if (channelName.startsWith("a") || channelName.startsWith("A")) {
                channels.add(channelName);
            }
        }
    }

}
