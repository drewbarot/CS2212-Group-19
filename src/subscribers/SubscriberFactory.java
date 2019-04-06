package subscribers;

import states.subscriber.StateName;


/**
 * @author kkontog, ktsiouni, mgrigori
 *  
 */
/**
 * @author kkontog, ktsiouni, mgrigori
 * creates new {@link AbstractSubscriber} objects
 * contributes to the State design pattern
 * implements the FactoryMethod design pattern   
 */
public class SubscriberFactory {

	
	/**
	 * creates a new {@link AbstractSubscriber}
	 * @return the newly created {@link AbstractSubscriber} instance
	 */
	public static AbstractSubscriber createSubscriber(SubscriberType subscriberType, StateName stateName) {

		AbstractSubscriber concreteSubscriber;
		switch (subscriberType) {
			case SubscriberTypeA:
				concreteSubscriber = new ConcreteSubscriberA(SubscriberIDMaker.getNewSubscriberID());
				break;
			case SubscriberTypeB:
				concreteSubscriber = new ConcreteSubscriberB(SubscriberIDMaker.getNewSubscriberID());
				break;
			default:
				concreteSubscriber = new ConcreteSubscriberDefault(SubscriberIDMaker.getNewSubscriberID());
		}
		concreteSubscriber.setState(stateName);
		return concreteSubscriber;
	}
}
