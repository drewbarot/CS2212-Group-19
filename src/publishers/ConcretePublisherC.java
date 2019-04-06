package publishers;

import events.AbstractEvent;
import strategies.publisher.AbstractStrategy;
import strategies.publisher.StrategyFactory;

public class ConcretePublisherC extends AbstractPublisher {

    /**
     * @param concreteStrategy attaches a concreteStrategy generated from the {@link StrategyFactory#createStrategy(strategies.publisher.StrategyName)}
     * method
     */
    protected ConcretePublisherC(int publisherID, AbstractStrategy concreteStrategy) {
        super(publisherID, concreteStrategy);
    }

}
