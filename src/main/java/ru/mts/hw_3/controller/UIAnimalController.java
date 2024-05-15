package ru.mts.hw_3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.entity.AnimalType;
import ru.mts.hw_3.entity.Breed;
import ru.mts.hw_3.repository.AnimalTypeRepository;
import ru.mts.hw_3.repository.BreedRepository;
import ru.mts.hw_3.service.AnimalService;

import java.util.List;

@Controller
public class UIAnimalController {
    private final AnimalService animalService;
    private final AnimalTypeRepository animalTypeRepository;
    private final BreedRepository breedRepository;

    @Autowired
    public UIAnimalController(AnimalService animalService, AnimalTypeRepository animalTypeRepository, BreedRepository breedRepository) {
        this.animalService = animalService;
        this.animalTypeRepository = animalTypeRepository;
        this.breedRepository = breedRepository;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("animalList", animalService.getAllAnimals());
        return "index";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        animalService.deleteAnimal(id);
        return "redirect:/index";
    }

    @GetMapping(value = "/add")
    public String addAnimal(Model model) {
        model.addAttribute("animal", new Animal());
        List<AnimalType> animalTypes = (List<AnimalType>) animalTypeRepository.findAll();
        model.addAttribute("animalTypes", animalTypes);
        List<Breed> breeds = (List<Breed>) breedRepository.findAll();
        model.addAttribute("breeds", breeds);
        return "add";
    }

    @PostMapping(value = "/add", params = "action=save")
    public String save(Model model, Animal animal) {
        animalService.saveAnimal(animal);
        return "redirect:/index";
    }

    @PostMapping(value = "/add", params = "action=cancel")
    public String cancel(Model model) {
        return "redirect:/index";
    }
}