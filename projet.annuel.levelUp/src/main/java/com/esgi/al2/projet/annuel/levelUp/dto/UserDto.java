package com.esgi.al2.projet.annuel.levelUp.dto;

import com.esgi.al2.projet.annuel.levelUp.model.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {

    private Integer id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    public static UserDto fromEntity(User user){
        if (user == null){
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static User toEntity(UserDto userDto){
        if (userDto == null){
            return null;
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
