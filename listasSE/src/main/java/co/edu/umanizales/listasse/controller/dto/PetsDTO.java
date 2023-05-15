package co.edu.umanizales.listasse.controller.dto;

import lombok.Data;
import javax.validation.constraints.*;


@Data

public class PetsDTO {


    @NotBlank(message = "Este campo requiere información.")
    private String identificationPet;
    @NotBlank(message = "Este campo requiere información.")
    @Size(min = 2, max = 30, message = "La longitud máxima del nombre es de 30 caracteres.")
    private String namePet;
    @NotNull(message = "Este campo requiere información.")
    @Positive
    @Min(value = 1, message = "La edad mínima debe ser mayor a 0.")
    @Max(value = 15, message = "La edad máxima debe ser menor o igual a 15.")
    private byte agePet;
    @NotNull(message = "Este campo requiere información.")
    private char genderPet;
    @NotBlank(message = "Este campo requiere información.")
    private String codeLocationPet;
}

