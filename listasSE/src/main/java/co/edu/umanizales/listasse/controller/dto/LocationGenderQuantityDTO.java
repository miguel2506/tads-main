package co.edu.umanizales.listasse.controller.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class LocationGenderQuantityDTO {
    private String city;
    private List<KidGenderDTO> genders;
    private int total;

    public LocationGenderQuantityDTO(String city){
        this.city = city;
        this.total = 0;
        this.genders = new ArrayList<>();
        this.genders.add(new KidGenderDTO('M', 0));
        this.genders.add(new KidGenderDTO('F', 0));
    }
}
