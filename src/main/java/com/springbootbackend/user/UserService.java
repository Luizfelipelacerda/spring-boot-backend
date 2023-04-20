package com.springbootbackend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO getUser(String email){
        Optional<User> optionalUser = this.userRepository.findByEmail(email);
        if(!optionalUser.isEmpty()){
            User user = optionalUser.get();
            return UserDTO.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .build();
        }
        return new UserDTO();
    }

    public void registerUser(User user){
        this.userRepository.save(user);
    }

    public void updateUser(UserRequest userRequest) {
        Optional<User> userOptional = userRepository.findByEmail(userRequest.getEmail());
        if(userOptional.isPresent()){
            if(passwordEncoder.matches(userRequest.getOldPassword(),userOptional.get().getPassword())){
                User newUser = userOptional.get();
                newUser.setFirstname(userRequest.getFirstname());
                newUser.setLastname(userRequest.getLastname());
                newUser.setPassword(passwordEncoder.encode(userRequest.getNewPassword()));
                userRepository.save(newUser);
            }
        }

    }
}
