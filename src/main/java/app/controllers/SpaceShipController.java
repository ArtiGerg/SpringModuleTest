package app.controllers;

import app.helpers.DataLoader;
import app.models.SpaceShip;
import app.models.SpaceShipClass;
import app.services.SpaceShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SpaceShipController {

    private SpaceShipService spaceShipService;


    @Autowired
    public SpaceShipController(SpaceShipService spaceShipService) {
        this.spaceShipService = spaceShipService;

    }

    @GetMapping(value = {"/spaceship"})
    public String saveNewSpaceShip(Model model) {
        model.addAttribute("newShip", new SpaceShip());
        model.addAttribute("shipClasses", SpaceShipClass.values());

        return "spaceship_new";
    }

    @GetMapping(value = {"/spaceships"})
    public String allSpaceShips(Model model){

        List<SpaceShip> ship = spaceShipService.getAll();
        model.addAttribute("ship", ship);

        return "spaceships";
    }
    @GetMapping(value = {"/result"})
    public String result(Model model){
        List<SpaceShip> ship = spaceShipService.getByActive();
        model.addAttribute("ship", ship);
        return "result";
    }

   /* @GetMapping(value = {"/spaceships/{registrationCode}"})
    public String crews(@PathVariable long id, Model model){
        SpaceShip ship = spaceShipService.getByCode(id);
        model.addAttribute("ship", ship);
        return "shipWithCrew";
    }

    */
}
