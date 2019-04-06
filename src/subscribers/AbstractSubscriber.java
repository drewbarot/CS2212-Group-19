package subscribers;

import baseEntities.IEntity;
import events.AbstractEvent;
import pubSubServer.SubscriptionManager;
import states.subscriber.AbstractState;
import states.subscriber.StateDefault;
import states.subscriber.StateFactory;
import states.subscriber.StateName;


/**
 * @author kkontog, ktsiouni, mgrigori
 * the base Interface for the Subscriber hierarchy
 */
public abstract class AbstractSubscriber implements IEntity {

	protected int subscriberID;
	protected AbstractState state;


	protected AbstractSubscriber(int subscriberID) {
		this.subscriberID = subscriberID;
		System.out.println("Subscriber " + subscriberID + " created");
		state = StateFactory.createState(StateName.StateDefault);
	}

	/**
	 * set's the {@link AbstractState} for this ISubscriber implementation using the {@link StateFactory}
	 * @param stateName the entry from the {@link StateName} enumeration that we want the new IState of the ISubscriber to be 
	 */
	public void setState(StateName stateName) {
		state = StateFactory.createState(stateName);
		System.out.println("Subscriber " + subscriberID + " is on state " + state.name());
	}

	
	/**
	 * is the function called each time an event is published to one of the channels that the 
	 * ISubscriber is sbuscribed to
	 * @param event the AbstractEvent that's received
	 */
	public void alert(AbstractEvent event) {
		state.handleEvent(this.subscriberID, event);
	}
	
	
	/**
	 * subscribes to the channel whose name is provided by the parameter channelName 
	 * @param channelName type String
	 */
	public void subscribe(String channelName) {
		SubscriptionManager.getInstance().subscribe(channelName, this);
	}
	
	/**
	 * unsubscribes from the channel whose name is provided by the parameter channelName
	 * @param channelName type String
	 */
	public void unsubscribe(String channelName) {
		SubscriptionManager.getInstance().subscribe(channelName, this);
	}

	public int getSubscriberID() {
		return subscriberID;
	}

}
