package pubSubServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author kkontog, ktsiouni, mgrigori
 *  
 *  implements the Singleton Design Pattern
 *  
 *  holds the collection of AbstractChannel type entities and provides the methods for manipulating thes collections
 */
public class ChannelPoolManager {

	private static ChannelPoolManager instance = null;
	private Map<String, AbstractChannel> channelsMap = new HashMap<String, AbstractChannel>();
	private List<AbstractChannel> channelList = new ArrayList<AbstractChannel>();

	private ChannelPoolManager() { //when we create this for the first time it reads the file. don't read in the main!

		System.out.println("\nInitializing Channels:");

		int fileFormattingErrors = 0;

		try {

			BufferedReader br = new BufferedReader(new FileReader(new File("src/Channels.chl")));
			String channel;

			while (br.ready()) {
				channel = br.readLine().trim();
				if (!channel.isEmpty()) {
					addChannel(channel);
				}
				else {
					fileFormattingErrors++;
				}
			}

			br.close();

		} catch (IOException e) {
			System.out.println("Error loading channels from file - creating one no_theme channel");
			addChannel("no_theme");
		}

		System.out.println("\n" + fileFormattingErrors + " errors in Channels.chl\n");

	}


	protected static ChannelPoolManager getInstance() {

		if (instance == null)
			instance = new ChannelPoolManager();
		return instance;
	}

	
	/**
	 * creates a new AbstractChannel, adds it to the collections and returns it to the caller
	 * @param channelName the name of the new AbstractChannel
	 */
	protected AbstractChannel addChannel(String channelName) {

		//check if the channel already exists. If so, do nothing
		//if the channel doesn't exist then create it and add it
		if (!channelsMap.containsKey(channelName)) {
			Channel ch = new Channel(channelName);
			channelsMap.put(channelName, ch);
			channelList.add(ch);
			return ch;
		}
		return null;
	}


	/**
	 * removes an AbstractChannel from the collections of available AbstractChannels
	 * @param channelName the name of the AbstractChannel to be removed
	 */
	protected void deleteChannel(String channelName) {

		channelList.remove(channelsMap.get(channelName));
		channelsMap.remove(channelName);
	}

	
	/**
	 * can be used to list all the Channels available in the PubSubServer
	 * @return a list of type {@link AbstractChannel}
	 */
	protected List<AbstractChannel> listChannels() {
		return channelList;
	}

	
	/**
	 * returns the object of AbstractChannel using a name as lookup information
	 * @param channelName the name of the AbstractChannel to be returned
	 * @return the appropriate instance of an AbstractChannel subclass
	 */
	protected AbstractChannel findChannel(String channelName) {
		return channelsMap.get(channelName);
	}

	
	/**
	 * accessor for the ChannelMap use with great caution
	 * @return a Map from String to {@link AbstractChannel}
	 */
	protected Map<String, AbstractChannel> getChannelsMap() {
		return channelsMap;
	}
	
}
