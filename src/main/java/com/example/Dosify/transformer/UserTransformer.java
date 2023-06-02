package com.example.Dosify.transformer;

import com.example.Dosify.dto.RequestDto.UserRequestDto;
import com.example.Dosify.dto.ResponseDto.UserResponseDto;
import com.example.Dosify.model.User;

public class UserTransformer {
    public static User UserRequestDtoToUser(UserRequestDto userRequestDto){
        return User.builder()
                .name(userRequestDto.getName())
                .age(userRequestDto.getAge())
                .emailId(userRequestDto.getEmailId())
                .mobNo(userRequestDto.getMobNo())
                .gender(userRequestDto.getGender())
                .build();
    }

    public static UserResponseDto UserToUserResponseDto(User user){
        return UserResponseDto.builder()
                .name(user.getName())
                .message("Congrats! You have registered on Dosify")
                .build();
    }
}
