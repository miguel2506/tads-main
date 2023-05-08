package co.edu.umanizales.listasse.service;

import co.edu.umanizales.listasse.exception.ListSEException;
import co.edu.umanizales.listasse.model.Kid;
import co.edu.umanizales.listasse.model.ListSE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListSEService {
    private ListSE kids;

    public ListSEService() {
        kids = new ListSE();
    }

    public void KidsDataService() {
        kids = new ListSE();
    }

     //public Node getKids() { return kids.getHead();}
    /*
    public Node getKids()
     */

    public void deleteByAge(byte age) throws ListSEException {
        kids.deleteByAge(age);
    }

    public void invert() throws ListSEException {
        kids.invert();
    }

    public void addToStart(Kid kid) throws ListSEException {
        kids.addToStart(kid);
    }


}
