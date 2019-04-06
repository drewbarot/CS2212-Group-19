package strategies.publisher;

import java.util.List;
import java.util.ArrayList;

public class StrategyDefault extends AbstractStrategy {

    protected StrategyDefault() {
        name = "Default";
    }

    /**
     * this function implements the actual strategy of StrategyDefault
     * this adds the list only the Main channel
     */
    protected void doStrategy() {
        channels.clear();
        channels.add("Main"); //add just the main channel to the list
    }

}
