package edu.posthub.posthub.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.posthub.posthub.dtos.CreateUserDTO;
import edu.posthub.posthub.entities.Role;
import edu.posthub.posthub.entities.User;
import edu.posthub.posthub.repositories.RoleRepository;
import edu.posthub.posthub.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void createUser(CreateUserDTO createUserDTO) {
        Optional<User> userFromDb = userRepository.findByUsername(createUserDTO.username());
        if (userFromDb.isEmpty()) {
            userFromDb = userRepository.findByEmail(createUserDTO.email());
        }
        if (userFromDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Optional<Role> normalUser = roleRepository.findByName(Role.Values.USER.name());
        if (normalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Role USER not found");
        }

        User user = new User();
        user.setUsername(createUserDTO.username());
        user.setEmail(createUserDTO.email());
        user.setPassword(passwordEncoder.encode(createUserDTO.password()));
        user.setRoles(Set.of(normalUser.get()));

        userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(UUID id){
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
