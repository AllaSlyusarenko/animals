package ru.mts.hw_3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.mts.hw_3.dto.AnimalDto;
import ru.mts.hw_3.dto.Signup;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.entity.Person;
import ru.mts.hw_3.mapper.AnimalMapper;
import ru.mts.hw_3.service.AnimalService;
import ru.mts.hw_3.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/animals")
public class AnimalMethodsController {
    private final AnimalService animalService;
    private final UserService userService;

    @Autowired
    public AnimalMethodsController(AnimalService animalService, UserService userService) {
        this.animalService = animalService;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Signup signup) {
        //register user
        Person person = userService.signup(signup);
        return ResponseEntity.ok(String.valueOf(person.getIdPerson()));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<AnimalDto>> getAllAnimals() {
        List<Animal> animals = animalService.getAllAnimals();
        return new ResponseEntity<>(AnimalMapper.animalsToAnimalDtos(animals), HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AnimalDto> createAnimal(@RequestBody Animal animal) {
        Animal animalOut = animalService.saveAnimal(animal);
        return new ResponseEntity<>(AnimalMapper.animalToAnimalDto(animalOut), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Integer id) {
        animalService.deleteAnimal(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}