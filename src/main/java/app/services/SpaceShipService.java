package app.services;

import app.helpers.DataLoader;
import app.models.Crew;
import app.models.SpaceShip;
import app.repositories.SpaceShipRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpaceShipService {
   private DataLoader dataLoader;
    private SpaceShipRepo spaceShipRepo;

    @Autowired
    public SpaceShipService(DataLoader dataLoader, SpaceShipRepo spaceShipRepo) {
        this.dataLoader = dataLoader;
        this.spaceShipRepo = spaceShipRepo;
    }

    private Map<Long, SpaceShip> spaceShip =new TreeMap<>();

    public List<SpaceShip> getAll() {
        return (List<SpaceShip>) spaceShipRepo.findAll();
    }

    public List<SpaceShip> getByActive() {
        List<SpaceShip> result = new ArrayList<>();
        for (SpaceShip ship: getAll()){
            if (ship.isActive()){
                result.add(ship);
            }
        }

        return result;
    }

    public SpaceShip getByCode (long registrationCode) {
        return spaceShip.get(registrationCode);
    }


}
