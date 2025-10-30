package edu.posthub.posthub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.posthub.posthub.entities.User;
import edu.posthub.posthub.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User createUser(User newUser){
        return this.userRepository.save(newUser);
    }

    public Optional<User> getUserById(Long id){
        return this.userRepository.findById(id);
    }

    public void deleteUserById(Long id){
        this.userRepository.deleteById(id);
    }
}
