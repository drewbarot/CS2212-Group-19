package publishers;

import baseEntities.IEntity;
import events.AbstractEvent;
import strategies.publisher.AbstractStrategy;
import strategies.publisher.StrategyFactory;

/**
 * @author kkontog, ktsiouni, mgrigori base Interface implemented by all
 *         ConcretePublisher Classes
 */
public abstract class AbstractPublisher implements IEntity {

	protected int publisherID;
	protected AbstractStrategy publishingStrategy = null;


	/**
	 * @param concreteStrategy attaches a concreteStrategy generated from the {@link StrategyFactory#createStrategy(strategies.publisher.StrategyName)}
	 * method
	 */
	protected AbstractPublisher(int publisherID, AbstractStrategy concreteStrategy) {
		this.publisherID = publisherID;
		System.out.println("Publisher " + publisherID + " created");
		setPublishingStrategy(concreteStrategy);
	}

	/**
	 * all implementations of this Interface MUST contain an instance variable of 
	 * type {@link AbstractStrategy} which has the methods:
	 * 1) {@link AbstractStrategy#doPublish(int)}
	 * 2) {@link AbstractStrategy#doPublish(AbstractEvent)}
	 * which allows for the publishing of an {@link AbstractEvent} object
	 * 
	 * @param event an event which is to be published
	 * 
	 */
	public void publish(AbstractEvent event) {
		publishingStrategy.doPublish(event);
	}

	/**
	 * all implementations of this Interface MUST contain an instance variable of
	 * type {@link AbstractStrategy} which has the methods:
	 * 1) {@link AbstractStrategy#doPublish(int)}
	 * 2) {@link AbstractStrategy#doPublish(AbstractEvent)}
	 * which allows for the publishing of an {@link AbstractEvent} object
	 *
	 */
	public void publish() {
		publishingStrategy.doPublish(publisherID);
	}

	/**
	 * return the publisher ID
	 * @return
	 */
	public int getPublisherID() {
		return publisherID;
	}

	public void setPublishingStrategy(AbstractStrategy publishingStrategy) {
		this.publishingStrategy = publishingStrategy;
		System.out.println("Publisher " + publisherID + " has strategy " + publishingStrategy.name());
	}

}
