package co.edu.umanizales.listasse.controller.dto;

import co.edu.umanizales.listasse.model.RangeAgeKids;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RangeAgesKidsDTO {
    private RangeAgeKids range;
    int quantity;
}
