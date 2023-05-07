package co.edu.umanizales.listasse.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetGenderDTO {
    private char gender;
    private int quantity;
}
