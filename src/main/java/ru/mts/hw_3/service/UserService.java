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
import ru.mts.hw_3.exception.IncorrectParameterException;
import ru.mts.hw_3.repository.PersonRepository;
import ru.mts.hw_3.repository.RoleRepository;

@Service
public class UserService implements UserDetailsService {
    private final RoleRepository roleRepository;
    private PersonRepository personRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(@Autowired PersonRepository personRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public boolean checkSignup(Signup signup) {
        return personRepository.findByUsername(signup.getUsername()).isPresent();
    }

    public Person signup(Signup signup) {
        if (signup.getUsername().isBlank() || signup.getPassword().isBlank()) {
            throw new IncorrectParameterException("Username or password is blank");
        }
        Person person = new Person();

        person.setUsername(signup.getUsername());
        person.setPassword(passwordEncoder.encode(signup.getPassword()));
        Role role = roleRepository.findByRoleName(ERole.USER).isPresent() ? roleRepository.findByRoleName(ERole.USER).get() : roleRepository.save(new Role(ERole.USER));
        person.getRoles().add(role);
        return personRepository.save(person);
    }
}