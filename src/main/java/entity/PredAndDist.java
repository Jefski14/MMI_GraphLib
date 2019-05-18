package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PredAndDist {

    private Integer predecessorId;
    private Double distance;
}
