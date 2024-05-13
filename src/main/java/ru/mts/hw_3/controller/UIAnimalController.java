package ru.mts.hw_3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.mts.hw_3.service.AnimalService;

@Controller
public class UIAnimalController {
    private final AnimalService animalService;

    @Autowired
    public UIAnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("animalList",animalService.getAllAnimals());
        return "index";
    }
}
