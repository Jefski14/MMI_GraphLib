package algorithms.P4;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DistAndPred {
    // No idea why lombok doesnt work here
    public DistAndPred(Integer id, Double dist) {
        this.predecessorId = id;
        this.distance = dist;
    }

    Double distance;
    Integer predecessorId;
}
