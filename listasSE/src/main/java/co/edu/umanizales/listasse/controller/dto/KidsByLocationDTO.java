package co.edu.umanizales.listasse.controller.dto;

import co.edu.umanizales.listasse.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KidsByLocationDTO {
    private Location location;
    private int quantity;
}
