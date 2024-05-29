package ru.mts.hw_3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mts.hw_3.dto.JwtResponse;
import ru.mts.hw_3.dto.Signin;
import ru.mts.hw_3.dto.Signup;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.entity.AnimalType;
import ru.mts.hw_3.entity.Breed;
import ru.mts.hw_3.entity.Person;
import ru.mts.hw_3.security.jwt.JWTUtility;
import ru.mts.hw_3.service.AnimalService;
import ru.mts.hw_3.service.AnimalTypeService;
import ru.mts.hw_3.service.BreedService;
import ru.mts.hw_3.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UIAnimalController {
    private final AnimalService animalService;
    private final BreedService breedService;
    private final AnimalTypeService animalTypeService;
    private final UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    public UIAnimalController(AnimalService animalService, BreedService breedService, AnimalTypeService animalTypeService, UserService userService) {
        this.animalService = animalService;
        this.breedService = breedService;
        this.animalTypeService = animalTypeService;
        this.userService = userService;
    }

    // Registration form
    @GetMapping("/start") //две кнопки - регистрация и вход
    public String start(Model model) {
        return "start";
    }

    //
//    @GetMapping(value = "/start", params = "action=signup") //регистрация
//    public String signupSubmit(Model model) {
//        return "signup";
//    }
//
//    @GetMapping(value = "/start", params = "action=signin") //вход
//    public String signinSubmit(Model model) {
//        return "signin";
//    }
//


    //signup регистрация
    @GetMapping(value = "/signup")
    public String signup(Model model) {
        model.addAttribute("signup", new Signup());
        return "signup";
    }

    @PostMapping(value = "/signup", params = "action=save")
    public String signupSave(Model model, Signup signup) {
        userService.signup(signup);
        return "redirect:/start";
    }

    @PostMapping(value = "/signup", params = "action=cancel")
    public String signupCancel(Model model) {
        return "redirect:/start";
    }


    //signin вход в аккаунт
    @GetMapping(value = "/signin")
    public String signin(Model model) {
        model.addAttribute("signin", new Signin());
        return "signin";
    }

    @PostMapping(value = "/signin", params = "action=enter")
    public String signinEnter(Model model, Signin signin) {
        Authentication authenticated =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signin.getUsername(), signin.getPassword()));
        //устанавливаем в контекст, в ThreadLocal нашего контекста
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        //создаем токен
        String jwtToken = jwtUtility.generateJwtToken(authenticated);

        Person userDetails = (Person) authenticated.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        JwtResponse jwtResponse = new JwtResponse(jwtToken,
                userDetails.getIdPerson(),
                userDetails.getUsername(),
                roles);
        return "redirect:/index";
    }

    @PostMapping(value = "/signin", params = "action=cancel")
    public String signinCancel(Model model) {
        return "redirect:/start";
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
        List<AnimalType> animalTypes = animalTypeService.getAllAnimalTypes();
        model.addAttribute("animalTypes", animalTypes);
        List<Breed> breeds = breedService.getBreeds();
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