package co.edu.umanizales.listasse.service;

import co.edu.umanizales.listasse.model.RangeAgeKids;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class RangeAgeService {
    private List<RangeAgeKids> ranges;

    public RangeAgeService(){
        ranges = new ArrayList<>();
        ranges.add(new RangeAgeKids(1,3));
        ranges.add(new RangeAgeKids(4,6));
        ranges.add(new RangeAgeKids(7,9));
        ranges.add(new RangeAgeKids(10,12));
        ranges.add(new RangeAgeKids(13,15));
    }
}
