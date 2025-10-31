package edu.posthub.posthub.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.posthub.posthub.dtos.CreateUserDTO;
import edu.posthub.posthub.dtos.UserDTO;
import edu.posthub.posthub.entities.User;
import edu.posthub.posthub.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(User newUser){
        User user = this.userRepository.save(newUser);
        UserDTO createdUser = new UserDTO(user.getId(), user.getUsername());
        return createdUser;
    }

    public void updateUser(Long id, CreateUserDTO newUser){
        User user = userRepository.findById(id).get();
        user.setEmail(newUser.email());
        user.setPassword(newUser.password());
        user.setUsername(newUser.username());
        userRepository.save(user);

        return;
    }

    public List<UserDTO> getAllUsers(){
        List<User> users = this.userRepository.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        users.stream().forEach(user -> usersDTO.add(new UserDTO(user.getId(), user.getUsername())));
        return usersDTO;
    }

    public UserDTO getUserById(Long id){
        User user = this.userRepository.findById(id).get();
        UserDTO userDTO = new UserDTO(id, user.getUsername());
        return userDTO;
    }

    public void deleteUserById(Long id){
        this.userRepository.deleteById(id);
    }
}