package estudo.spring.estudoSpring.service;

import estudo.spring.estudoSpring.controller.CreateUserDto;
import estudo.spring.estudoSpring.entity.User;
import estudo.spring.estudoSpring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto){

        //DT0 -> ENTITY
       var entity =  new User(
                UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.cargo(),
                Instant.now(),
                null
        );

        var userSaved = userRepository.save(entity);

        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId){

        return userRepository.findById(UUID.fromString(userId));
    }
}
