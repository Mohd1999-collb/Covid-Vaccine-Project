package com.example.Dosify.service;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.dto.RequestDto.UserRequestDto;
import com.example.Dosify.dto.ResponseDto.UserResponseDto;
import com.example.Dosify.exception.UserNotFoundException;
import com.example.Dosify.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public UserResponseDto addUser(UserRequestDto userRequestDto);

    public void updateUser(String emailId, String name, String mobNo) throws UserNotFoundException;

    public List<String> userNotTakenEvenASingleDose();

    public List<String> userTakenDose1NotTakenDose2();

    public List<String> allUserFullyVaccinated();

    public List<String> maleUserNotTakenEvenASingleDose(String male);

    public List<String> femaleUserNotTakenEvenASingleDose(String female);

    public List<String> allMaleUserFullyVaccinated(String male);

    public List<String> allFemaleUserFullyVaccinated(String female);

    void deleteUser(int id);
}
