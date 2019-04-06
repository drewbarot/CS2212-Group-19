package publishers;

import events.AbstractEvent;
import strategies.publisher.AbstractStrategy;
import strategies.publisher.StrategyFactory;

public class ConcretePublisherA extends AbstractPublisher {

    /**
     * @param concreteStrategy attaches a concreteStrategy generated from the {@link StrategyFactory#createStrategy(strategies.publisher.StrategyName)}
     * method
     */
    protected ConcretePublisherA(int publisherID, AbstractStrategy concreteStrategy) {
        super(publisherID, concreteStrategy);
    }

}
