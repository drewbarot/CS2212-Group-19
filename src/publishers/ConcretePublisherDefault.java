package publishers;

import events.AbstractEvent;
import strategies.publisher.AbstractStrategy;
import strategies.publisher.StrategyFactory;

public class ConcretePublisherDefault extends AbstractPublisher {

    /**
     * @param concreteStrategy attaches a concreteStrategy generated from the {@link StrategyFactory#createStrategy(strategies.publisher.StrategyName)}
     *                         method
     */
    protected ConcretePublisherDefault(int publisherID, AbstractStrategy concreteStrategy) {
        super(publisherID, concreteStrategy);

    }

}
