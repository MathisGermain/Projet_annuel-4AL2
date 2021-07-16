package com.esgi.al2.projet.annuel.levelUp.service;

import com.esgi.al2.projet.annuel.levelUp.dto.UserDto;
import com.esgi.al2.projet.annuel.levelUp.exception.EntityNotFoundException;
import com.esgi.al2.projet.annuel.levelUp.exception.ErrorCode;
import com.esgi.al2.projet.annuel.levelUp.exception.InvalidEntityException;
import com.esgi.al2.projet.annuel.levelUp.repository.UserRepository;
import com.esgi.al2.projet.annuel.levelUp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(
            UserRepository userRepository
    ){
        this.userRepository = userRepository;
    }

    public UserDto create(UserDto dto){
        List<String> errors = UserValidator.validate(dto);
        if(!errors.isEmpty()) {
            throw new InvalidEntityException("This user is not valid", ErrorCode.USER_NOT_VALID);
        }
        if((userRepository.existsByEmail(dto.getEmail()) || userRepository.existsByUsername(dto.getUsername()))){
            throw new InvalidEntityException(
                    "This user exists yet",
                    ErrorCode.USER_ALREADY_EXISTS,
                    Collections.singletonList("This user exists yet")
            );
        }
        return UserDto.fromEntity(userRepository.save(
                UserDto.toEntity(dto)
        ));
    }

    public UserDto findById(Integer id) {
        if(id == null) return null;

        return userRepository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("User doesn't exist", ErrorCode.USER_NOT_FOUND));
    }

    public UserDto findByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("User doesn't exist", ErrorCode.USER_NOT_FOUND));
    }


    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }
}