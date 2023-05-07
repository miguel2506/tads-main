package co.edu.umanizales.listasse.service;

import co.edu.umanizales.listasse.model.Location;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class LocationService {
    private List<Location> locations;

    public LocationService() {
        //Conectaría a una base de datos
        locations = new ArrayList<>();
        locations.add(new Location("169","Colombia"));
        locations.add(new Location("16905","Antioquia"));
        locations.add(new Location("16917","Caldas"));
        locations.add(new Location("16963","Risaralda"));
        locations.add(new Location("16905001","Medellín"));
        locations.add(new Location("16963001","Pereira"));
        locations.add(new Location("16917001","Manizales"));
        locations.add(new Location("16917003","Chinchiná"));
    }

    public List<Location> getLocationsByCodeSize(int size){
        List<Location> listLocations = new ArrayList<>();

        for(Location loc: locations) {
            if (loc.getCode().length() == size) {
                listLocations.add(loc);
            }
        }
        return listLocations;
    }

    public Location getLocationsByCode(String code){

        for(Location loc: locations) {
            if (loc.getCode().equals(code)) {
                return loc;
            }
        }
        return null;
    }
}
