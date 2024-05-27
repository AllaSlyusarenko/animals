package ru.mts.hw_3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.mts.hw_3.dto.AnimalDto;
import ru.mts.hw_3.dto.JwtResponse;
import ru.mts.hw_3.dto.Signin;
import ru.mts.hw_3.dto.Signup;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.entity.Person;
import ru.mts.hw_3.mapper.AnimalMapper;
import ru.mts.hw_3.security.jwt.JWTUtility;
import ru.mts.hw_3.service.AnimalService;
import ru.mts.hw_3.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/animals")
public class AnimalMethodsController {
    private final AnimalService animalService;
    private final UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    public AnimalMethodsController(AnimalService animalService, UserService userService) {
        this.animalService = animalService;
        this.userService = userService;
    }

    @GetMapping("/one")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> getOne() {
        return new ResponseEntity<>("Done!", HttpStatus.OK);
    }

//    Если необходимо сделать авторизацию для MVC паттерна, не забудьте использовать
//    методы .formLogin и .logout, в которых нужно указать ссылки на свои html страницы
//    авторизации и выхода из системы.

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Signup signup){
        //register user
        Person person = userService.signup(signup);
        return ResponseEntity.ok(String.valueOf(person.getIdPerson()));
    }
    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> signin(@RequestBody Signin signin){
        //проверка аутентификации
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

        return ResponseEntity.ok(new JwtResponse(jwtToken,
                userDetails.getIdPerson(),
                userDetails.getUsername(),
                roles));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnimalDto>> getAllAnimals() {
        List<Animal> animals = animalService.getAllAnimals();
        return new ResponseEntity<>(AnimalMapper.animalsToAnimalDtos(animals), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<AnimalDto> createAnimal(@RequestBody Animal animal) {
        Animal animalOut = animalService.saveAnimal(animal);
        return new ResponseEntity<>(AnimalMapper.animalToAnimalDto(animalOut), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Integer id) {
        animalService.deleteAnimal(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}