package ru.mts.hw_3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mts.hw_3.dto.Signup;
import ru.mts.hw_3.entity.ERole;
import ru.mts.hw_3.entity.Person;
import ru.mts.hw_3.entity.Role;
import ru.mts.hw_3.repository.PersonRepository;

import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    private PersonRepository personRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public Person signup(Signup signup) {
        Person person = new Person();
        person.setUsername(signup.getUsername());
        person.setPassword(passwordEncoder.encode(signup.getPassword()));
        person.setRoles(Set.of(new Role(2, ERole.USER)));
        return personRepository.save(person);
    }
}