package algorithms.P4_Shortest_Paths;

import algorithms.P6_B_Flow.NegativeCycleWithMinCapacity;

public class NegativeCyclesException extends RuntimeException {
    public NegativeCycleWithMinCapacity cycle;
    public NegativeCyclesException(String s, NegativeCycleWithMinCapacity cycle) {
        super(s);
        this.cycle = cycle;
    }
}
