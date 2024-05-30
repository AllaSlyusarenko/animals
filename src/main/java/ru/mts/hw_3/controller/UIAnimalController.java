package ru.mts.hw_3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mts.hw_3.dto.Signin;
import ru.mts.hw_3.dto.Signup;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.entity.AnimalType;
import ru.mts.hw_3.entity.Breed;
import ru.mts.hw_3.service.AnimalService;
import ru.mts.hw_3.service.AnimalTypeService;
import ru.mts.hw_3.service.BreedService;
import ru.mts.hw_3.service.UserService;

import java.util.List;

@Controller
public class UIAnimalController {
    private final AnimalService animalService;
    private final BreedService breedService;
    private final AnimalTypeService animalTypeService;
    private final UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public UIAnimalController(AnimalService animalService, BreedService breedService, AnimalTypeService animalTypeService, UserService userService) {
        this.animalService = animalService;
        this.breedService = breedService;
        this.animalTypeService = animalTypeService;
        this.userService = userService;
    }

    // Registration form
    @GetMapping("/start")
    public String start(Model model) {
        model.addAttribute("signup", new Signup());
        model.addAttribute("signin", new Signin());
        return "start";
    }

    //signup регистрация
    @GetMapping(value = "/signup")
    public String signup(Model model, Signup signup) {
        if (userService.checkSignup(signup)) {
            return "error";
        }
        userService.signup(signup);
        return "redirect:/start";
    }

    //signin вход в аккаунт
    @GetMapping(value = "/signin")
    public String signin(Model model, Signin signin) {
        Authentication authenticated =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signin.getUsername(), signin.getPassword()));
        //устанавливаем в контекст, в ThreadLocal нашего контекста
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        return "redirect:/index";
    }

    @GetMapping(value = "/logout")
    @PreAuthorize("hasRole('USER')")
    public String logout(Model model) {
        return "redirect:/start";
    }

    @GetMapping("/index")
    @PreAuthorize("hasRole('USER')")
    public String index(Model model) {
        model.addAttribute("animalList", animalService.getAllAnimals());
        return "index";
    }

    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable("id") Integer id) {
        animalService.deleteAnimal(id);
        return "redirect:/index";
    }

    @GetMapping(value = "/add")
    @PreAuthorize("hasRole('USER')")
    public String addAnimal(Model model) {
        model.addAttribute("animal", new Animal());
        List<AnimalType> animalTypes = animalTypeService.getAllAnimalTypes();
        model.addAttribute("animalTypes", animalTypes);
        List<Breed> breeds = breedService.getBreeds();
        model.addAttribute("breeds", breeds);
        return "add";
    }

    @PostMapping(value = "/add", params = "action=save")
    @PreAuthorize("hasRole('USER')")
    public String save(Model model, Animal animal) {
        animalService.saveAnimal(animal);
        return "redirect:/index";
    }

    @PostMapping(value = "/add", params = "action=cancel")
    @PreAuthorize("hasRole('USER')")
    public String cancel(Model model) {
        return "redirect:/index";
    }
}