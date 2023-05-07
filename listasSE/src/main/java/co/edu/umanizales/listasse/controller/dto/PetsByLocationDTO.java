package co.edu.umanizales.listasse.controller.dto;

import co.edu.umanizales.listasse.model.LocationPets;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetsByLocationDTO {
    private LocationPets locationPets;
    private int quantity;
}
