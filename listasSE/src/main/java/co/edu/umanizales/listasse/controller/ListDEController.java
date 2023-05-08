package co.edu.umanizales.listasse.controller;

import co.edu.umanizales.listasse.controller.dto.PetsByLocationDTO;
import co.edu.umanizales.listasse.controller.dto.ReportPetsDTO;
import co.edu.umanizales.listasse.controller.dto.ResponseDTO;
import co.edu.umanizales.listasse.exception.ListDEException;
import co.edu.umanizales.listasse.exception.ListSEException;
import co.edu.umanizales.listasse.model.LocationPets;
import co.edu.umanizales.listasse.model.Pet;
import co.edu.umanizales.listasse.service.ListDEService;
import co.edu.umanizales.listasse.service.LocationPetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/listde")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;

    @Autowired
    private LocationPetsService locationPetsService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getPets() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listDEService.getPets(), null), HttpStatus.OK);
    }

    //Adicionar niños
    @GetMapping(path = "/addpet")
    public ResponseEntity<ResponseDTO> add(@RequestBody Pet pet) {
        try {
            if (pet == null) {
                throw new ListDEException("La mascota no tiene datos");
            }
            listDEService.getPets().addPet(pet);
            return new ResponseEntity<>(new ResponseDTO(200,
                    "La mascota ha sido añadida", null), HttpStatus.OK);
        } catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(500,
                    "Error al añadir la mascota", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Invertir lista
    @GetMapping("/invertpets")
    public ResponseEntity<ResponseDTO> invertPets() {
        try {
            listDEService.invertPets();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha invertido la lista",
                    null), HttpStatus.OK);
        } catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Error al invertir la lista",
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Mascotas masculinas al inicio y femeninos al final.
    @GetMapping("/orderpetsmaletostart")
    public ResponseEntity<ResponseDTO> orderBoysToStart() {
        try {
            listDEService.getPets().orderPetsToStart();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se han añadido las mascotas masculinas al inicio, las femeninas al final.",
                    null), HttpStatus.OK);
        } catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Ocurrió un error al ordenar el género de las mascotas.", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Intercalar mascota masculina, femenina, masculina, femenina
    @GetMapping(path="/petsintercalate")
    public ResponseEntity<ResponseDTO> boyIntercalateGirl()  {
        try {
            listDEService.getPets().intercalatePetsGender();
            return new ResponseEntity<>(new ResponseDTO(200, "Las mascotas se han intercalado.",
                    null), HttpStatus.OK);
        } catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Ocurrió un error al intercalar el género de las mascotas",
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Dada una edad eliminar a las mascotas del código dado
    @GetMapping(path = "/deletepet/{code}")
    public ResponseEntity<ResponseDTO> deleteKidByAge(@PathVariable String code)  {
        try {
            listDEService.deletePetByIdentification(code);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Las mascotas con el código" + code + "han sido eliminados.",
                    null), HttpStatus.OK);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Error al eliminar las mascotas.",
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Obtener el promedio de edad de las mascotas de la lista
    @GetMapping(path="/averageagepets")
    public ResponseEntity<ResponseDTO> averageAge() {
        try {
            listDEService.getPets().averageAgePets();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha calculado el promedio de edad de las mascotas",
                    null), HttpStatus.OK);
        } catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Error al calcular la edad promedio.",
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Generar un reporte que me diga cuantas mascotas hay de cada ciudad.
    @GetMapping(path = "/petsbylocations")
    public ResponseEntity<ResponseDTO> getKidsByLocation() {
        try {
            List<PetsByLocationDTO> petsByLocationDTOList = new ArrayList<>();
            for (LocationPets loc : locationPetsService.getLocationsPets()) {
                int count = listDEService.getPets().getCountPetsByLocationCode(loc.getCode());
                if (count > 0) {
                    petsByLocationDTOList.add(new PetsByLocationDTO(loc, count));
                }
            }
            return new ResponseEntity<>(new ResponseDTO(
                    200, petsByLocationDTOList,
                    null), HttpStatus.OK);
        } catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Ocurrió un error al obtener las mascotas por ubicación.", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/petsbydepartments")
    public ResponseEntity<ResponseDTO> getKidsByDepartmentCode() {
        List<PetsByLocationDTO> petsByLocationDTOList = new ArrayList<>();
        try {
            for (LocationPets loc : locationPetsService.getLocationsPets()) {
                int count = listDEService.getPets().getCountPetsByDepartmentCode(loc.getCode());
                if (count > 0) {
                    petsByLocationDTOList.add(new PetsByLocationDTO(loc, count));
                }
            }
            return new ResponseEntity<>(new ResponseDTO(
                    200, petsByLocationDTOList,
                    null), HttpStatus.OK);
        } catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Error al obtener la lista de mascotas por departamento.",
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/petsbylocationgenders/{age}")
    public ResponseEntity<ResponseDTO> getReportPetsLocationGenders(@PathVariable byte age) {
        ReportPetsDTO reportPets =
                new ReportPetsDTO(locationPetsService.getLocationsPetsByCodeSize(8));
        listDEService.getPets().getReportPetsByLocationGendersByAge(age,reportPets);
        return new ResponseEntity<>(new ResponseDTO(
                200, reportPets,null),HttpStatus.OK);
    }

    //Método que me permita decirle a un niño determinado que adelante un numero de posiciones dadas
    @GetMapping(path="/passpetspositions/{positions}")
    public ResponseEntity<ResponseDTO> passByPosition(@PathVariable String identification, int positions) {
        try {
            listDEService.getPets().passPetByPosition(identification, positions);
            return new ResponseEntity<>(new ResponseDTO(200, "La mascota se ha adelantado de posición", null), HttpStatus.OK);
        } catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(500, "Ha ocurrido un error al adelantar la posición de la mascota",
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Método que me permita decirle a un niño determinado que pierda un numero de posiciones dadas
    @GetMapping(path="/afterwardspetspositions/{positions}")
    public ResponseEntity<ResponseDTO> afterwardsPositions(@PathVariable String identification, int positions) {
        try {
            listDEService.getPets().afterwardsPetsPositions(identification, positions);
            return new ResponseEntity<>(new ResponseDTO(200, "La mascota cambio de posicion", null), HttpStatus.OK);
        } catch (ListDEException e) {
            return new ResponseEntity<>(new ResponseDTO(500, "Error al intentar cambiar la mascota de posicion", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/deletepetKamikaze/{code}")
    public ResponseEntity<ResponseDTO> deletePetByIdKamikaze(@PathVariable String code)  {
        try {
            listDEService.deletePetByIdentification(code);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Las mascotas con el código" + code + "han sido eliminados.",
                    null), HttpStatus.OK);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Error al eliminar las mascotas.",
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
