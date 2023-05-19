package com.example.Dosify.service;

import com.example.Dosify.dto.RequestDto.UserRequestDto;
import com.example.Dosify.dto.ResponseDto.UserResponseDto;
import com.example.Dosify.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public UserResponseDto addUser(UserRequestDto userRequestDto);
}
