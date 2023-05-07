package co.edu.umanizales.listasse.service;

import co.edu.umanizales.listasse.exception.ListDEException;
import co.edu.umanizales.listasse.exception.ListSEException;
import co.edu.umanizales.listasse.model.ListDE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListDEService {
    private ListDE pets;
    public ListDEService() {
        pets = new ListDE();
    }

    public void invertPets() throws ListDEException {
        pets.invertPets();
    }

    public void deletePetByIdentification(String code) throws ListSEException {
        pets.deletePetByIdentification(code);
    }
}
