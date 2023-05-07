package co.edu.umanizales.listasse.controller.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LocationGenderQuantityPetsDTO {
    private String city;
    private List<PetGenderDTO> genders;
    private int total;

    public LocationGenderQuantityPetsDTO(String city){
        this.city = city;
        this.total = 0;
        this.genders = new ArrayList<>();
        this.genders.add(new PetGenderDTO('M', 0));
        this.genders.add(new PetGenderDTO('F', 0));
    }
}
