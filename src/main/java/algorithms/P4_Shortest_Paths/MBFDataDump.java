package algorithms.P4_Shortest_Paths;

import algorithms.P6_B_Flow.NegativeCycleWithMinCapacity;
import entity.PredAndDist;
import lombok.AllArgsConstructor;

import java.util.HashMap;

@AllArgsConstructor
public class MBFDataDump {
    public HashMap<Integer, PredAndDist> kwbMap;
    public NegativeCycleWithMinCapacity negCycle;
}
