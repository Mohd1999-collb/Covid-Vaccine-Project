package com.example.Dosify.service.implementServiceInterface;

import com.example.Dosify.dto.RequestDto.UserRequestDto;
import com.example.Dosify.dto.ResponseDto.UserResponseDto;
import com.example.Dosify.model.User;
import com.example.Dosify.repository.UserRepository;
import com.example.Dosify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {

        /*Convert request DTO into entity*/
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setAge(userRequestDto.getAge());
        user.setEmailId(userRequestDto.getEmailId());
        user.setMobNo(userRequestDto.getMobNo());
        user.setGender(userRequestDto.getGender());

        /*Save the object in database*/
        userRepository.save(user);

        /*Convert entity into response DTO*/
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(user.getName());
        userResponseDto.setMessage("Congrats! You have registered on Dosify");
        return userResponseDto;
    }
}
